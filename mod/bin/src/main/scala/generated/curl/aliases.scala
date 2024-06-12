package curl

import _root_.scala.scalanative.unsafe.*
import _root_.scala.scalanative.unsigned.*
import _root_.scala.scalanative.libc.*
import _root_.scala.scalanative.*

type CURL = Unit
object CURL: 
  given _tag: Tag[CURL] = Tag.Unit
  inline def apply(inline o: Unit): CURL = o
  extension (v: CURL)
    inline def value: Unit = v

type CURLM = Unit
object CURLM: 
  given _tag: Tag[CURLM] = Tag.Unit
  inline def apply(inline o: Unit): CURLM = o
  extension (v: CURLM)
    inline def value: Unit = v

type CURLSH = Unit
object CURLSH: 
  given _tag: Tag[CURLSH] = Tag.Unit
  inline def apply(inline o: Unit): CURLSH = o
  extension (v: CURLSH)
    inline def value: Unit = v

opaque type curl_calloc_callback = CFuncPtr2[size_t, size_t, Ptr[Byte]]
object curl_calloc_callback: 
  given _tag: Tag[curl_calloc_callback] = Tag.materializeCFuncPtr2[size_t, size_t, Ptr[Byte]]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_calloc_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr2[size_t, size_t, Ptr[Byte]]): curl_calloc_callback = o
  extension (v: curl_calloc_callback)
    inline def value: CFuncPtr2[size_t, size_t, Ptr[Byte]] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_chunk_bgn_callback = CFuncPtr3[Ptr[Byte], Ptr[Byte], CInt, CLongInt]
object curl_chunk_bgn_callback: 
  given _tag: Tag[curl_chunk_bgn_callback] = Tag.materializeCFuncPtr3[Ptr[Byte], Ptr[Byte], CInt, CLongInt]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_chunk_bgn_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr3[Ptr[Byte], Ptr[Byte], CInt, CLongInt]): curl_chunk_bgn_callback = o
  extension (v: curl_chunk_bgn_callback)
    inline def value: CFuncPtr3[Ptr[Byte], Ptr[Byte], CInt, CLongInt] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_chunk_end_callback = CFuncPtr1[Ptr[Byte], CLongInt]
object curl_chunk_end_callback: 
  given _tag: Tag[curl_chunk_end_callback] = Tag.materializeCFuncPtr1[Ptr[Byte], CLongInt]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_chunk_end_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr1[Ptr[Byte], CLongInt]): curl_chunk_end_callback = o
  extension (v: curl_chunk_end_callback)
    inline def value: CFuncPtr1[Ptr[Byte], CLongInt] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_closesocket_callback = CFuncPtr2[Ptr[Byte], curl_socket_t, CInt]
object curl_closesocket_callback: 
  given _tag: Tag[curl_closesocket_callback] = Tag.materializeCFuncPtr2[Ptr[Byte], curl_socket_t, CInt]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_closesocket_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr2[Ptr[Byte], curl_socket_t, CInt]): curl_closesocket_callback = o
  extension (v: curl_closesocket_callback)
    inline def value: CFuncPtr2[Ptr[Byte], curl_socket_t, CInt] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_conv_callback = CFuncPtr2[CString, size_t, CURLcode]
object curl_conv_callback: 
  given _tag: Tag[curl_conv_callback] = Tag.materializeCFuncPtr2[CString, size_t, CURLcode]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_conv_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr2[CString, size_t, CURLcode]): curl_conv_callback = o
  extension (v: curl_conv_callback)
    inline def value: CFuncPtr2[CString, size_t, CURLcode] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_debug_callback = CFuncPtr5[Ptr[CURL], curl_infotype, CString, size_t, Ptr[Byte], CInt]
object curl_debug_callback: 
  given _tag: Tag[curl_debug_callback] = Tag.materializeCFuncPtr5[Ptr[CURL], curl_infotype, CString, size_t, Ptr[Byte], CInt]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_debug_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr5[Ptr[CURL], curl_infotype, CString, size_t, Ptr[Byte], CInt]): curl_debug_callback = o
  extension (v: curl_debug_callback)
    inline def value: CFuncPtr5[Ptr[CURL], curl_infotype, CString, size_t, Ptr[Byte], CInt] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_fnmatch_callback = CFuncPtr3[Ptr[Byte], CString, CString, CInt]
object curl_fnmatch_callback: 
  given _tag: Tag[curl_fnmatch_callback] = Tag.materializeCFuncPtr3[Ptr[Byte], CString, CString, CInt]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_fnmatch_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr3[Ptr[Byte], CString, CString, CInt]): curl_fnmatch_callback = o
  extension (v: curl_fnmatch_callback)
    inline def value: CFuncPtr3[Ptr[Byte], CString, CString, CInt] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_formget_callback = CFuncPtr3[Ptr[Byte], CString, size_t, size_t]
object curl_formget_callback: 
  given _tag: Tag[curl_formget_callback] = Tag.materializeCFuncPtr3[Ptr[Byte], CString, size_t, size_t]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_formget_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr3[Ptr[Byte], CString, size_t, size_t]): curl_formget_callback = o
  extension (v: curl_formget_callback)
    inline def value: CFuncPtr3[Ptr[Byte], CString, size_t, size_t] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_free_callback = CFuncPtr1[Ptr[Byte], Unit]
object curl_free_callback: 
  given _tag: Tag[curl_free_callback] = Tag.materializeCFuncPtr1[Ptr[Byte], Unit]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_free_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr1[Ptr[Byte], Unit]): curl_free_callback = o
  extension (v: curl_free_callback)
    inline def value: CFuncPtr1[Ptr[Byte], Unit] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_hstsread_callback = CFuncPtr3[Ptr[CURL], Ptr[curl_hstsentry], Ptr[Byte], CURLSTScode]
object curl_hstsread_callback: 
  given _tag: Tag[curl_hstsread_callback] = Tag.materializeCFuncPtr3[Ptr[CURL], Ptr[curl_hstsentry], Ptr[Byte], CURLSTScode]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_hstsread_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr3[Ptr[CURL], Ptr[curl_hstsentry], Ptr[Byte], CURLSTScode]): curl_hstsread_callback = o
  extension (v: curl_hstsread_callback)
    inline def value: CFuncPtr3[Ptr[CURL], Ptr[curl_hstsentry], Ptr[Byte], CURLSTScode] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_hstswrite_callback = CFuncPtr4[Ptr[CURL], Ptr[curl_hstsentry], Ptr[curl_index], Ptr[Byte], CURLSTScode]
object curl_hstswrite_callback: 
  given _tag: Tag[curl_hstswrite_callback] = Tag.materializeCFuncPtr4[Ptr[CURL], Ptr[curl_hstsentry], Ptr[curl_index], Ptr[Byte], CURLSTScode]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_hstswrite_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr4[Ptr[CURL], Ptr[curl_hstsentry], Ptr[curl_index], Ptr[Byte], CURLSTScode]): curl_hstswrite_callback = o
  extension (v: curl_hstswrite_callback)
    inline def value: CFuncPtr4[Ptr[CURL], Ptr[curl_hstsentry], Ptr[curl_index], Ptr[Byte], CURLSTScode] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_ioctl_callback = CFuncPtr3[Ptr[CURL], CInt, Ptr[Byte], curlioerr]
object curl_ioctl_callback: 
  given _tag: Tag[curl_ioctl_callback] = Tag.materializeCFuncPtr3[Ptr[CURL], CInt, Ptr[Byte], curlioerr]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_ioctl_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr3[Ptr[CURL], CInt, Ptr[Byte], curlioerr]): curl_ioctl_callback = o
  extension (v: curl_ioctl_callback)
    inline def value: CFuncPtr3[Ptr[CURL], CInt, Ptr[Byte], curlioerr] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_lock_function = CFuncPtr4[Ptr[CURL], curl_lock_data, curl_lock_access, Ptr[Byte], Unit]
object curl_lock_function: 
  given _tag: Tag[curl_lock_function] = Tag.materializeCFuncPtr4[Ptr[CURL], curl_lock_data, curl_lock_access, Ptr[Byte], Unit]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_lock_function = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr4[Ptr[CURL], curl_lock_data, curl_lock_access, Ptr[Byte], Unit]): curl_lock_function = o
  extension (v: curl_lock_function)
    inline def value: CFuncPtr4[Ptr[CURL], curl_lock_data, curl_lock_access, Ptr[Byte], Unit] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_malloc_callback = CFuncPtr1[size_t, Ptr[Byte]]
object curl_malloc_callback: 
  given _tag: Tag[curl_malloc_callback] = Tag.materializeCFuncPtr1[size_t, Ptr[Byte]]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_malloc_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr1[size_t, Ptr[Byte]]): curl_malloc_callback = o
  extension (v: curl_malloc_callback)
    inline def value: CFuncPtr1[size_t, Ptr[Byte]] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_multi_timer_callback = CFuncPtr3[Ptr[CURLM], CLongInt, Ptr[Byte], CInt]
object curl_multi_timer_callback: 
  given _tag: Tag[curl_multi_timer_callback] = Tag.materializeCFuncPtr3[Ptr[CURLM], CLongInt, Ptr[Byte], CInt]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_multi_timer_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr3[Ptr[CURLM], CLongInt, Ptr[Byte], CInt]): curl_multi_timer_callback = o
  extension (v: curl_multi_timer_callback)
    inline def value: CFuncPtr3[Ptr[CURLM], CLongInt, Ptr[Byte], CInt] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_off_t = CLongInt
object curl_off_t: 
  given _tag: Tag[curl_off_t] = Tag.Size
  inline def apply(inline o: CLongInt): curl_off_t = o
  extension (v: curl_off_t)
    inline def value: CLongInt = v

opaque type curl_opensocket_callback = CFuncPtr3[Ptr[Byte], curlsocktype, Ptr[curl_sockaddr], curl_socket_t]
object curl_opensocket_callback: 
  given _tag: Tag[curl_opensocket_callback] = Tag.materializeCFuncPtr3[Ptr[Byte], curlsocktype, Ptr[curl_sockaddr], curl_socket_t]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_opensocket_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr3[Ptr[Byte], curlsocktype, Ptr[curl_sockaddr], curl_socket_t]): curl_opensocket_callback = o
  extension (v: curl_opensocket_callback)
    inline def value: CFuncPtr3[Ptr[Byte], curlsocktype, Ptr[curl_sockaddr], curl_socket_t] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_prereq_callback = CFuncPtr5[Ptr[Byte], CString, CString, CInt, CInt, CInt]
object curl_prereq_callback: 
  given _tag: Tag[curl_prereq_callback] = Tag.materializeCFuncPtr5[Ptr[Byte], CString, CString, CInt, CInt, CInt]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_prereq_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr5[Ptr[Byte], CString, CString, CInt, CInt, CInt]): curl_prereq_callback = o
  extension (v: curl_prereq_callback)
    inline def value: CFuncPtr5[Ptr[Byte], CString, CString, CInt, CInt, CInt] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_progress_callback = CFuncPtr5[Ptr[Byte], Double, Double, Double, Double, CInt]
object curl_progress_callback: 
  given _tag: Tag[curl_progress_callback] = Tag.materializeCFuncPtr5[Ptr[Byte], Double, Double, Double, Double, CInt]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_progress_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr5[Ptr[Byte], Double, Double, Double, Double, CInt]): curl_progress_callback = o
  extension (v: curl_progress_callback)
    inline def value: CFuncPtr5[Ptr[Byte], Double, Double, Double, Double, CInt] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_push_callback = CFuncPtr5[Ptr[CURL], Ptr[CURL], size_t, Ptr[curl_pushheaders], Ptr[Byte], CInt]
object curl_push_callback: 
  given _tag: Tag[curl_push_callback] = Tag.materializeCFuncPtr5[Ptr[CURL], Ptr[CURL], size_t, Ptr[curl_pushheaders], Ptr[Byte], CInt]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_push_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr5[Ptr[CURL], Ptr[CURL], size_t, Ptr[curl_pushheaders], Ptr[Byte], CInt]): curl_push_callback = o
  extension (v: curl_push_callback)
    inline def value: CFuncPtr5[Ptr[CURL], Ptr[CURL], size_t, Ptr[curl_pushheaders], Ptr[Byte], CInt] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_read_callback = CFuncPtr4[CString, size_t, size_t, Ptr[Byte], size_t]
object curl_read_callback: 
  given _tag: Tag[curl_read_callback] = Tag.materializeCFuncPtr4[CString, size_t, size_t, Ptr[Byte], size_t]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_read_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr4[CString, size_t, size_t, Ptr[Byte], size_t]): curl_read_callback = o
  extension (v: curl_read_callback)
    inline def value: CFuncPtr4[CString, size_t, size_t, Ptr[Byte], size_t] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_realloc_callback = CFuncPtr2[Ptr[Byte], size_t, Ptr[Byte]]
object curl_realloc_callback: 
  given _tag: Tag[curl_realloc_callback] = Tag.materializeCFuncPtr2[Ptr[Byte], size_t, Ptr[Byte]]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_realloc_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr2[Ptr[Byte], size_t, Ptr[Byte]]): curl_realloc_callback = o
  extension (v: curl_realloc_callback)
    inline def value: CFuncPtr2[Ptr[Byte], size_t, Ptr[Byte]] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_resolver_start_callback = CFuncPtr3[Ptr[Byte], Ptr[Byte], Ptr[Byte], CInt]
object curl_resolver_start_callback: 
  given _tag: Tag[curl_resolver_start_callback] = Tag.materializeCFuncPtr3[Ptr[Byte], Ptr[Byte], Ptr[Byte], CInt]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_resolver_start_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr3[Ptr[Byte], Ptr[Byte], Ptr[Byte], CInt]): curl_resolver_start_callback = o
  extension (v: curl_resolver_start_callback)
    inline def value: CFuncPtr3[Ptr[Byte], Ptr[Byte], Ptr[Byte], CInt] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_seek_callback = CFuncPtr3[Ptr[Byte], curl_off_t, CInt, CInt]
object curl_seek_callback: 
  given _tag: Tag[curl_seek_callback] = Tag.materializeCFuncPtr3[Ptr[Byte], curl_off_t, CInt, CInt]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_seek_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr3[Ptr[Byte], curl_off_t, CInt, CInt]): curl_seek_callback = o
  extension (v: curl_seek_callback)
    inline def value: CFuncPtr3[Ptr[Byte], curl_off_t, CInt, CInt] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_socket_callback = CFuncPtr5[Ptr[CURL], curl_socket_t, CInt, Ptr[Byte], Ptr[Byte], CInt]
object curl_socket_callback: 
  given _tag: Tag[curl_socket_callback] = Tag.materializeCFuncPtr5[Ptr[CURL], curl_socket_t, CInt, Ptr[Byte], Ptr[Byte], CInt]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_socket_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr5[Ptr[CURL], curl_socket_t, CInt, Ptr[Byte], Ptr[Byte], CInt]): curl_socket_callback = o
  extension (v: curl_socket_callback)
    inline def value: CFuncPtr5[Ptr[CURL], curl_socket_t, CInt, Ptr[Byte], Ptr[Byte], CInt] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_socket_t = CInt
object curl_socket_t: 
  given _tag: Tag[curl_socket_t] = Tag.Int
  inline def apply(inline o: CInt): curl_socket_t = o
  extension (v: curl_socket_t)
    inline def value: CInt = v

type curl_socklen_t = socklen_t
object curl_socklen_t: 
  given _tag: Tag[curl_socklen_t] = socklen_t._tag
  inline def apply(inline o: socklen_t): curl_socklen_t = o
  extension (v: curl_socklen_t)
    inline def value: socklen_t = v

opaque type curl_sockopt_callback = CFuncPtr3[Ptr[Byte], curl_socket_t, curlsocktype, CInt]
object curl_sockopt_callback: 
  given _tag: Tag[curl_sockopt_callback] = Tag.materializeCFuncPtr3[Ptr[Byte], curl_socket_t, curlsocktype, CInt]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_sockopt_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr3[Ptr[Byte], curl_socket_t, curlsocktype, CInt]): curl_sockopt_callback = o
  extension (v: curl_sockopt_callback)
    inline def value: CFuncPtr3[Ptr[Byte], curl_socket_t, curlsocktype, CInt] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_sshhostkeycallback = CFuncPtr4[Ptr[Byte], CInt, CString, size_t, CInt]
object curl_sshhostkeycallback: 
  given _tag: Tag[curl_sshhostkeycallback] = Tag.materializeCFuncPtr4[Ptr[Byte], CInt, CString, size_t, CInt]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_sshhostkeycallback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr4[Ptr[Byte], CInt, CString, size_t, CInt]): curl_sshhostkeycallback = o
  extension (v: curl_sshhostkeycallback)
    inline def value: CFuncPtr4[Ptr[Byte], CInt, CString, size_t, CInt] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_sshkeycallback = CFuncPtr5[Ptr[CURL], Ptr[curl_khkey], Ptr[curl_khkey], curl_khmatch, Ptr[Byte], CInt]
object curl_sshkeycallback: 
  given _tag: Tag[curl_sshkeycallback] = Tag.materializeCFuncPtr5[Ptr[CURL], Ptr[curl_khkey], Ptr[curl_khkey], curl_khmatch, Ptr[Byte], CInt]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_sshkeycallback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr5[Ptr[CURL], Ptr[curl_khkey], Ptr[curl_khkey], curl_khmatch, Ptr[Byte], CInt]): curl_sshkeycallback = o
  extension (v: curl_sshkeycallback)
    inline def value: CFuncPtr5[Ptr[CURL], Ptr[curl_khkey], Ptr[curl_khkey], curl_khmatch, Ptr[Byte], CInt] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_ssl_ctx_callback = CFuncPtr3[Ptr[CURL], Ptr[Byte], Ptr[Byte], CURLcode]
object curl_ssl_ctx_callback: 
  given _tag: Tag[curl_ssl_ctx_callback] = Tag.materializeCFuncPtr3[Ptr[CURL], Ptr[Byte], Ptr[Byte], CURLcode]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_ssl_ctx_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr3[Ptr[CURL], Ptr[Byte], Ptr[Byte], CURLcode]): curl_ssl_ctx_callback = o
  extension (v: curl_ssl_ctx_callback)
    inline def value: CFuncPtr3[Ptr[CURL], Ptr[Byte], Ptr[Byte], CURLcode] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_strdup_callback = CFuncPtr1[CString, CString]
object curl_strdup_callback: 
  given _tag: Tag[curl_strdup_callback] = Tag.materializeCFuncPtr1[CString, CString]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_strdup_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr1[CString, CString]): curl_strdup_callback = o
  extension (v: curl_strdup_callback)
    inline def value: CFuncPtr1[CString, CString] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_trailer_callback = CFuncPtr2[Ptr[Ptr[curl_slist]], Ptr[Byte], CInt]
object curl_trailer_callback: 
  given _tag: Tag[curl_trailer_callback] = Tag.materializeCFuncPtr2[Ptr[Ptr[curl_slist]], Ptr[Byte], CInt]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_trailer_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr2[Ptr[Ptr[curl_slist]], Ptr[Byte], CInt]): curl_trailer_callback = o
  extension (v: curl_trailer_callback)
    inline def value: CFuncPtr2[Ptr[Ptr[curl_slist]], Ptr[Byte], CInt] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_unlock_function = CFuncPtr3[Ptr[CURL], curl_lock_data, Ptr[Byte], Unit]
object curl_unlock_function: 
  given _tag: Tag[curl_unlock_function] = Tag.materializeCFuncPtr3[Ptr[CURL], curl_lock_data, Ptr[Byte], Unit]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_unlock_function = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr3[Ptr[CURL], curl_lock_data, Ptr[Byte], Unit]): curl_unlock_function = o
  extension (v: curl_unlock_function)
    inline def value: CFuncPtr3[Ptr[CURL], curl_lock_data, Ptr[Byte], Unit] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_write_callback = CFuncPtr4[CString, size_t, size_t, Ptr[Byte], size_t]
object curl_write_callback: 
  given _tag: Tag[curl_write_callback] = Tag.materializeCFuncPtr4[CString, size_t, size_t, Ptr[Byte], size_t]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_write_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr4[CString, size_t, size_t, Ptr[Byte], size_t]): curl_write_callback = o
  extension (v: curl_write_callback)
    inline def value: CFuncPtr4[CString, size_t, size_t, Ptr[Byte], size_t] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

opaque type curl_xferinfo_callback = CFuncPtr5[Ptr[Byte], curl_off_t, curl_off_t, curl_off_t, curl_off_t, CInt]
object curl_xferinfo_callback: 
  given _tag: Tag[curl_xferinfo_callback] = Tag.materializeCFuncPtr5[Ptr[Byte], curl_off_t, curl_off_t, curl_off_t, curl_off_t, CInt]
  inline def fromPtr(ptr: Ptr[Byte] | CVoidPtr): curl_xferinfo_callback = CFuncPtr.fromPtr(ptr.asInstanceOf[Ptr[Byte]])
  inline def apply(inline o: CFuncPtr5[Ptr[Byte], curl_off_t, curl_off_t, curl_off_t, curl_off_t, CInt]): curl_xferinfo_callback = o
  extension (v: curl_xferinfo_callback)
    inline def value: CFuncPtr5[Ptr[Byte], curl_off_t, curl_off_t, curl_off_t, curl_off_t, CInt] = v
    inline def toPtr: CVoidPtr = CFuncPtr.toPtr(v)

type fd_set = posix.sys.select.fd_set
object fd_set: 
  val _tag: Tag[fd_set] = summon[Tag[posix.sys.select.fd_set]]
  inline def apply(inline o: posix.sys.select.fd_set): fd_set = o
  extension (v: fd_set)
    inline def value: posix.sys.select.fd_set = v

type size_t = libc.stddef.size_t
object size_t: 
  val _tag: Tag[size_t] = summon[Tag[libc.stddef.size_t]]
  inline def apply(inline o: libc.stddef.size_t): size_t = o
  extension (v: size_t)
    inline def value: libc.stddef.size_t = v

type sockaddr = posix.sys.socket.sockaddr
object sockaddr: 
  val _tag: Tag[sockaddr] = summon[Tag[posix.sys.socket.sockaddr]]
  inline def apply(inline o: posix.sys.socket.sockaddr): sockaddr = o
  extension (v: sockaddr)
    inline def value: posix.sys.socket.sockaddr = v

type socklen_t = posix.sys.socket.socklen_t
object socklen_t: 
  val _tag: Tag[socklen_t] = summon[Tag[posix.sys.socket.socklen_t]]
  inline def apply(inline o: posix.sys.socket.socklen_t): socklen_t = o
  extension (v: socklen_t)
    inline def value: posix.sys.socket.socklen_t = v

type time_t = posix.sys.types.time_t
object time_t: 
  val _tag: Tag[time_t] = summon[Tag[posix.sys.types.time_t]]
  inline def apply(inline o: posix.sys.types.time_t): time_t = o
  extension (v: time_t)
    inline def value: posix.sys.types.time_t = v