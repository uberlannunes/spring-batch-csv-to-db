package dev.uberlan.springbatchcsvtodb.jobs.importcsvtodb;

import dev.uberlan.springbatchcsvtodb.jobs.importcsvtodb.model.StockPrice;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.EntityManagerFactory;

//@Configuration
public class ImportCsvToDbSingleThreadJob {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    public ImportCsvToDbSingleThreadJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, EntityManagerFactory entityManagerFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Bean
    @StepScope
    public FlatFileItemReader<StockPrice> reader(@Value("#{jobParameters['file_name']}") String fileName) {

        FlatFileItemReader<StockPrice> reader = new FlatFileItemReader<>();
//        reader.setResource(new FileSystemResource(fileName));
        reader.setResource(new ClassPathResource(fileName));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer(",") {
                    {
                        setNames("open_time", "open", "high", "low", "close", "volume", "close_time", "quote_volume", "count", "taker_buy_volume", "taker_buy_quote_volume", "ignore");
                        setStrict(false);
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {
                    {
                        setTargetType(StockPrice.class);
                    }
                });
            }
        });

        return reader;
    }

    @Bean
    @StepScope
    public ItemProcessor<StockPrice, StockPrice> processor(@Value("#{jobParameters['ticket']}") String ticket) {
        return sp -> {
            sp.setTicketPair(ticket);
            return sp;
        };
    }

    @Bean
    public JpaItemWriter<StockPrice> writer() {
        return new JpaItemWriterBuilder<StockPrice>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean("import-csv-to-db-job")
    public Job importCsvToDbJob() {

        Step importCsvStep = stepBuilderFactory.get("importCsvStep")
                .<StockPrice, StockPrice>chunk(100)
                .reader(reader(null))
                .processor(processor(null))
                .writer(writer())
                .build();

        return jobBuilderFactory.get("import-csv-to-db-job")
                .incrementer(new RunIdIncrementer())
                .start(importCsvStep)
                .build();
    }
}
