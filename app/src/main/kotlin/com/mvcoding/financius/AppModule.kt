/*
 * Copyright (C) 2015 Mantas Varnagiris.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package com.mvcoding.financius

import com.memoizrlabs.Shank
import com.memoizrlabs.ShankModule
import com.mvcoding.financius.feature.splash.SplashPresenter

class AppModule : ShankModule {
    override fun registerFactories() {
        userSettings()
        session()

        splashPresenter()
    }

    private fun splashPresenter() {
        val userSettings = Shank.provide(UserSettings::class.java)
        val session = Shank.provide(Session::class.java)
        Shank.registerFactory(SplashPresenter::class.java, { SplashPresenter(userSettings, session) })
    }

    private fun session() {
        Shank.registerFactory(Session::class.java, { UserSession() })
    }

    private fun userSettings() {
        Shank.registerFactory(UserSettings::class.java, { AppUserSettings() })
    }
}

