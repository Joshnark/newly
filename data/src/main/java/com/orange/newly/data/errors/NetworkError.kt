package com.orange.newly.data.errors

import java.lang.Exception

class NetworkException(reason: Throwable): Exception()
class TooManyRequestsException: Exception()