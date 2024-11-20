package com.mtc.buildconfig.impl

import com.mtc.common.buildconfig.BuildConfigFieldProvider
import com.mtc.common.buildconfig.BuildConfigFields
import javax.inject.Inject

class BuildConfigFieldsProviderImpl @Inject constructor() : BuildConfigFieldProvider {
    override fun get(): BuildConfigFields =
        BuildConfigFields(
            baseUrl = "123",
            isDebug = true,
        )
}
