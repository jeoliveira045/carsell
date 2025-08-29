package monokai.com.carsell.config;


import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.ses.SesClient


@Configuration
class EmailConfig {

    @Value("\${aws.ses.configuration.access-key}")
    var accessKey: String? = null

    @Value("\${aws.ses.configuration.secret-key}")
    var secretKey: String? = null


    @Bean
    fun emailCredentials(): SesClient{
        println(accessKey)
        println(secretKey)
        return SesClient.builder().region(Region.SA_EAST_1).credentialsProvider(
            StaticCredentialsProvider.create(
                AwsBasicCredentials.create(accessKey, secretKey)
            )
        ).build();
    }
}
