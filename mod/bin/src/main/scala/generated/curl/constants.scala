package curl

import _root_.scala.scalanative.unsafe.*
import _root_.scala.scalanative.unsigned.*
import _root_.scala.scalanative.libc.*
import _root_.scala.scalanative.*

val CURL_HTTP_VERSION_NONE: CUnsignedInt = 0.toUInt
val CURL_HTTP_VERSION_1_0: CUnsignedInt = 1.toUInt
val CURL_HTTP_VERSION_1_1: CUnsignedInt = 2.toUInt
val CURL_HTTP_VERSION_2_0: CUnsignedInt = 3.toUInt
val CURL_HTTP_VERSION_2TLS: CUnsignedInt = 4.toUInt
val CURL_HTTP_VERSION_2_PRIOR_KNOWLEDGE: CUnsignedInt = 5.toUInt
val CURL_HTTP_VERSION_3: CUnsignedInt = 30.toUInt
val CURL_HTTP_VERSION_3ONLY: CUnsignedInt = 31.toUInt
val CURL_HTTP_VERSION_LAST: CUnsignedInt = 32.toUInt

val CURL_RTSPREQ_NONE: CUnsignedInt = 0.toUInt
val CURL_RTSPREQ_OPTIONS: CUnsignedInt = 1.toUInt
val CURL_RTSPREQ_DESCRIBE: CUnsignedInt = 2.toUInt
val CURL_RTSPREQ_ANNOUNCE: CUnsignedInt = 3.toUInt
val CURL_RTSPREQ_SETUP: CUnsignedInt = 4.toUInt
val CURL_RTSPREQ_PLAY: CUnsignedInt = 5.toUInt
val CURL_RTSPREQ_PAUSE: CUnsignedInt = 6.toUInt
val CURL_RTSPREQ_TEARDOWN: CUnsignedInt = 7.toUInt
val CURL_RTSPREQ_GET_PARAMETER: CUnsignedInt = 8.toUInt
val CURL_RTSPREQ_SET_PARAMETER: CUnsignedInt = 9.toUInt
val CURL_RTSPREQ_RECORD: CUnsignedInt = 10.toUInt
val CURL_RTSPREQ_RECEIVE: CUnsignedInt = 11.toUInt
val CURL_RTSPREQ_LAST: CUnsignedInt = 12.toUInt

val CURL_SSLVERSION_DEFAULT: CUnsignedInt = 0.toUInt
val CURL_SSLVERSION_TLSv1: CUnsignedInt = 1.toUInt
val CURL_SSLVERSION_SSLv2: CUnsignedInt = 2.toUInt
val CURL_SSLVERSION_SSLv3: CUnsignedInt = 3.toUInt
val CURL_SSLVERSION_TLSv1_0: CUnsignedInt = 4.toUInt
val CURL_SSLVERSION_TLSv1_1: CUnsignedInt = 5.toUInt
val CURL_SSLVERSION_TLSv1_2: CUnsignedInt = 6.toUInt
val CURL_SSLVERSION_TLSv1_3: CUnsignedInt = 7.toUInt
val CURL_SSLVERSION_LAST: CUnsignedInt = 8.toUInt

val CURL_SSLVERSION_MAX_NONE: CUnsignedInt = 0.toUInt
val CURL_SSLVERSION_MAX_DEFAULT: CUnsignedInt = 65536.toUInt
val CURL_SSLVERSION_MAX_TLSv1_0: CUnsignedInt = 262144.toUInt
val CURL_SSLVERSION_MAX_TLSv1_1: CUnsignedInt = 327680.toUInt
val CURL_SSLVERSION_MAX_TLSv1_2: CUnsignedInt = 393216.toUInt
val CURL_SSLVERSION_MAX_TLSv1_3: CUnsignedInt = 458752.toUInt
val CURL_SSLVERSION_MAX_LAST: CUnsignedInt = 524288.toUInt