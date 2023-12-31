package org.sopt.linkmind

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import dagger.hilt.android.HiltAndroidApp
import org.sopt.linkmind.BuildConfig.KAKAO_NATIVE_KEY
import timber.log.Timber

@HiltAndroidApp
class LinkMindApp : Application() {
  override fun onCreate() {
    super.onCreate()
    setTimber()
    setDarkMode()
    setKakaoSdk()

    var keyHash = Utility.getKeyHash(this)
    Log.e("키해시", keyHash)
  }

  private fun setTimber() {
    Timber.plant(Timber.DebugTree())
  }

  private fun setDarkMode() {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
  }

  private fun setKakaoSdk() {
    KakaoSdk.init(this, KAKAO_NATIVE_KEY)
  }
}
