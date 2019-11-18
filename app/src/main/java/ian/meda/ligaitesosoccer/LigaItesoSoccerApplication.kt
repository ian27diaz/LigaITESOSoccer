package ian.meda.ligaitesosoccer

import android.app.Application
import com.parse.Parse

class LigaItesoSoccerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Parse.initialize(
            Parse.Configuration.Builder(this).applicationId("mQzc8WhqPWFqhS6pwPxCFE11oHLzyyunomXjVPr9")
                .clientKey("HKXlSMTOHvsHzfEeZEB3Cxci3CMcKTRPNsoUxCG9")
                .server("https://parseapi.back4app.com/")
                .build()
        )
    }
}