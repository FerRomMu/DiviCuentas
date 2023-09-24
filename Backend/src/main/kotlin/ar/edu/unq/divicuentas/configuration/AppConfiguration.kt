package ar.edu.unq.divicuentas.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfiguration {

    @Bean
    fun groupName() : String {
        val groupName :String?  = System.getenv()["GROUP_NAME"]
        if (groupName == null)
            return "EPERS"
        else
            return groupName!!
    }


}