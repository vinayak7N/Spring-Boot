package com.boot.batch.config;

import java.net.MalformedURLException;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.boot.batch.model.Person;
import com.boot.batch.processor.PersonItemProcessor;

@SpringBootConfiguration
@EnableBatchProcessing
public class SpringBatchConfig extends DefaultBatchConfigurer {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

	@Override
	protected JobRepository createJobRepository() throws Exception {
		MapJobRepositoryFactoryBean factoryBean = new MapJobRepositoryFactoryBean();
		factoryBean.afterPropertiesSet();
		return factoryBean.getObject();
	}

//	@Bean
//	public FlatFileItemReader<Person> itemReader() throws MalformedURLException {
//		return new FlatFileItemReaderBuilder<Person>().name("personItemReader")
//				.resource(new ClassPathResource("persons.csv")).delimited()
//				.names(new String[] { "firstName", "lastName", "email", "age" }).strict(false)
//				.fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {
//					{
//						setTargetType(Person.class);
//					}
//				}).build();
//	}
//	
	@Bean
	public JdbcCursorItemReader<Person> itemReader() throws MalformedURLException {
		return new JdbcCursorItemReaderBuilder<Person>()

				.name("personItemReader").dataSource(dataSource)
				.sql("select first_name,last_name,email,age from person")
				.rowMapper(new BeanPropertyRowMapper<Person>(Person.class)).build();
	}

	@Bean
	public PersonItemProcessor personItemProcessor() {
		return new PersonItemProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Person>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("Insert into person_2(first_name,last_name,email,age) values(" + ":firstName,:lastName,:email,:age)")
				.dataSource(dataSource).build();
	}

	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {

		return jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer()).listener(listener).flow(step1)
				.end().build();
	}

	@Bean
	public Step step1(JdbcBatchItemWriter<Person> writer) throws MalformedURLException {
		return stepBuilderFactory.get("step1").<Person, Person>chunk(100).reader(itemReader())
				.processor(personItemProcessor()).writer(writer).build();
	}

}
