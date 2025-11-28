package com.orange.newly.domain.exceptions

import java.lang.Exception

class NetworkException(reason: Throwable): Exception()
class TooManyRequestsException: Exception()