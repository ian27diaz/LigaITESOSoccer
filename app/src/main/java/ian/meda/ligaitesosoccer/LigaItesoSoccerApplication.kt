package ian.meda.ligaitesosoccer

import android.app.Application
import com.parse.Parse

class LigaItesoSoccerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Parse.initialize(
            Parse.Configuration.Builder(this).applicationId("fTd1N4HlCZjtESub0nQjg00aSYpJa022Nn91fJhY")
                .clientKey("4jn4IuJiUMU7C9OtDvaDa7v5UyEmqM1nkYfWE1hg")
                .server("https://parseapi.back4app.com/")
                .build()
        )
    }
}