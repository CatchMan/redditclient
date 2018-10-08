package com.catchman.data

class ApiConst private constructor() {

    init {
        throw AssertionError("Don't make entity of " + ApiConst::class.java.simpleName)
    }

    companion object {

        const val BASE_URL = "http://reddit.com/"
        const val ENDPOINT = BASE_URL + ""

    }


}
