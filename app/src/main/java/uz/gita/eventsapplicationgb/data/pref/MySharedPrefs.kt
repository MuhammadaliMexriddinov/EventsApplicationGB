package uz.gita.eventsapplicationgb.data.pref

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.gita.eventsapplicationgb.utils.SharedPreference
import javax.inject.Inject

/**
Mobile Developer
Creator:Mekhriddinov Muhammadali
 */
class MySharedPrefs @Inject constructor(
    @ApplicationContext context: Context
) : SharedPreference(context, context.getSharedPreferences("app_data", Context.MODE_PRIVATE)) {

    val screenOn: Boolean by Booleans(false)

    val screenOf: Boolean by Booleans(false)

    val wifiOn: Boolean by Booleans(false)

    val wifiOff: Boolean by Booleans(false)

    val bluetoothOn: Boolean by Booleans(false)

    val bluetoothOff: Boolean by Booleans(false)

    val headPhonesOn: Boolean by Booleans(false)

    val headPhonesOff: Boolean by Booleans(false)

    val planeOn: Boolean by Booleans(false)

    val planeOff: Boolean by Booleans(false)

    val changedTime: Boolean by Booleans(false)

    val shutDown: Boolean by Booleans(false)

    val lowBattery: Boolean by Booleans(false)

    val fullBattery: Boolean by Booleans(false)


}