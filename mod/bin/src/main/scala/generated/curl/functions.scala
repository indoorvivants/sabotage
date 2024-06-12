package curl

import _root_.scala.scalanative.unsafe.*
import _root_.scala.scalanative.unsigned.*
import _root_.scala.scalanative.libc.*
import _root_.scala.scalanative.*



@extern def curl_easy_cleanup(curl : Ptr[CURL]): Unit = extern

@extern def curl_easy_duphandle(curl : Ptr[CURL]): Ptr[CURL] = extern

@extern def curl_easy_escape(handle : Ptr[CURL], string : CString, length : CInt): CString = extern

@extern def curl_easy_getinfo(curl : Ptr[CURL], info : CURLINFO, rest: Any*): CURLcode = extern

@extern def curl_easy_header(easy : Ptr[CURL], name : CString, index : size_t, origin : CUnsignedInt, request : CInt, hout : Ptr[Ptr[curl_header]]): CURLHcode = extern

@extern def curl_easy_init(): Ptr[CURL] = extern

@extern def curl_easy_nextheader(easy : Ptr[CURL], origin : CUnsignedInt, request : CInt, prev : Ptr[curl_header]): Ptr[curl_header] = extern

@extern def curl_easy_option_by_id(id : CURLoption): Ptr[curl_easyoption] = extern

@extern def curl_easy_option_by_name(name : CString): Ptr[curl_easyoption] = extern

@extern def curl_easy_option_next(prev : Ptr[curl_easyoption]): Ptr[curl_easyoption] = extern

@extern def curl_easy_pause(handle : Ptr[CURL], bitmask : CInt): CURLcode = extern

@extern def curl_easy_perform(curl : Ptr[CURL]): CURLcode = extern

@extern def curl_easy_recv(curl : Ptr[CURL], buffer : Ptr[Byte], buflen : size_t, n : Ptr[size_t]): CURLcode = extern

@extern def curl_easy_reset(curl : Ptr[CURL]): Unit = extern

@extern def curl_easy_send(curl : Ptr[CURL], buffer : Ptr[Byte], buflen : size_t, n : Ptr[size_t]): CURLcode = extern

@extern def curl_easy_setopt(curl : Ptr[CURL], option : CURLoption, rest: Any*): CURLcode = extern

@extern def curl_easy_strerror(_0 : CURLcode): CString = extern

@extern def curl_easy_unescape(handle : Ptr[CURL], string : CString, length : CInt, outlength : Ptr[CInt]): CString = extern

@extern def curl_easy_upkeep(curl : Ptr[CURL]): CURLcode = extern

@extern def curl_escape(string : CString, length : CInt): CString = extern

@extern def curl_formadd(httppost : Ptr[Ptr[curl_httppost]], last_post : Ptr[Ptr[curl_httppost]], rest: Any*): CURLFORMcode = extern

@extern def curl_formfree(form : Ptr[curl_httppost]): Unit = extern

@extern def curl_formget(form : Ptr[curl_httppost], arg : Ptr[Byte], append : curl_formget_callback): CInt = extern

@extern def curl_free(p : Ptr[Byte]): Unit = extern

@extern def curl_getdate(p : CString, unused : Ptr[time_t]): time_t = extern

@extern def curl_getenv(variable : CString): CString = extern

@extern def curl_global_cleanup(): Unit = extern

@extern def curl_global_init(flags : CLongInt): CURLcode = extern

@extern def curl_global_init_mem(flags : CLongInt, m : curl_malloc_callback, f : curl_free_callback, r : curl_realloc_callback, s : curl_strdup_callback, c : curl_calloc_callback): CURLcode = extern

@extern def curl_global_sslset(id : curl_sslbackend, name : CString, avail : Ptr[Ptr[Ptr[curl_ssl_backend]]]): CURLsslset = extern

@extern def curl_mime_addpart(mime : Ptr[curl_mime]): Ptr[curl_mimepart] = extern

@extern def curl_mime_data(part : Ptr[curl_mimepart], data : CString, datasize : size_t): CURLcode = extern

@extern def curl_mime_data_cb(part : Ptr[curl_mimepart], datasize : curl_off_t, readfunc : curl_read_callback, seekfunc : curl_seek_callback, freefunc : curl_free_callback, arg : Ptr[Byte]): CURLcode = extern

@extern def curl_mime_encoder(part : Ptr[curl_mimepart], encoding : CString): CURLcode = extern

@extern def curl_mime_filedata(part : Ptr[curl_mimepart], filename : CString): CURLcode = extern

@extern def curl_mime_filename(part : Ptr[curl_mimepart], filename : CString): CURLcode = extern

@extern def curl_mime_free(mime : Ptr[curl_mime]): Unit = extern

@extern def curl_mime_headers(part : Ptr[curl_mimepart], headers : Ptr[curl_slist], take_ownership : CInt): CURLcode = extern

@extern def curl_mime_init(easy : Ptr[CURL]): Ptr[curl_mime] = extern

@extern def curl_mime_name(part : Ptr[curl_mimepart], name : CString): CURLcode = extern

@extern def curl_mime_subparts(part : Ptr[curl_mimepart], subparts : Ptr[curl_mime]): CURLcode = extern

@extern def curl_mime_type(part : Ptr[curl_mimepart], mimetype : CString): CURLcode = extern

@extern def curl_multi_add_handle(multi_handle : Ptr[CURLM], curl_handle : Ptr[CURL]): CURLMcode = extern

@extern def curl_multi_assign(multi_handle : Ptr[CURLM], sockfd : curl_socket_t, sockp : Ptr[Byte]): CURLMcode = extern

@extern def curl_multi_cleanup(multi_handle : Ptr[CURLM]): CURLMcode = extern

@extern def curl_multi_fdset(multi_handle : Ptr[CURLM], read_fd_set : Ptr[fd_set], write_fd_set : Ptr[fd_set], exc_fd_set : Ptr[fd_set], max_fd : Ptr[CInt]): CURLMcode = extern

@extern def curl_multi_info_read(multi_handle : Ptr[CURLM], msgs_in_queue : Ptr[CInt]): Ptr[CURLMsg] = extern

@extern def curl_multi_init(): Ptr[CURLM] = extern

@extern def curl_multi_perform(multi_handle : Ptr[CURLM], running_handles : Ptr[CInt]): CURLMcode = extern

@extern def curl_multi_poll(multi_handle : Ptr[CURLM], extra_fds : Ptr[curl_waitfd], extra_nfds : CUnsignedInt, timeout_ms : CInt, ret : Ptr[CInt]): CURLMcode = extern

@extern def curl_multi_remove_handle(multi_handle : Ptr[CURLM], curl_handle : Ptr[CURL]): CURLMcode = extern

@extern def curl_multi_setopt(multi_handle : Ptr[CURLM], option : CURLMoption, rest: Any*): CURLMcode = extern

@extern def curl_multi_socket(multi_handle : Ptr[CURLM], s : curl_socket_t, running_handles : Ptr[CInt]): CURLMcode = extern

@extern def curl_multi_socket_action(multi_handle : Ptr[CURLM], s : curl_socket_t, ev_bitmask : CInt, running_handles : Ptr[CInt]): CURLMcode = extern

@extern def curl_multi_socket_all(multi_handle : Ptr[CURLM], running_handles : Ptr[CInt]): CURLMcode = extern

@extern def curl_multi_strerror(_0 : CURLMcode): CString = extern

@extern def curl_multi_timeout(multi_handle : Ptr[CURLM], milliseconds : Ptr[CLongInt]): CURLMcode = extern

@extern def curl_multi_wait(multi_handle : Ptr[CURLM], extra_fds : Ptr[curl_waitfd], extra_nfds : CUnsignedInt, timeout_ms : CInt, ret : Ptr[CInt]): CURLMcode = extern

@extern def curl_multi_wakeup(multi_handle : Ptr[CURLM]): CURLMcode = extern

@extern def curl_pushheader_byname(h : Ptr[curl_pushheaders], name : CString): CString = extern

@extern def curl_pushheader_bynum(h : Ptr[curl_pushheaders], num : size_t): CString = extern

@extern def curl_share_cleanup(share : Ptr[CURLSH]): CURLSHcode = extern

@extern def curl_share_init(): Ptr[CURLSH] = extern

@extern def curl_share_setopt(share : Ptr[CURLSH], option : CURLSHoption, rest: Any*): CURLSHcode = extern

@extern def curl_share_strerror(_0 : CURLSHcode): CString = extern

@extern def curl_slist_append(list : Ptr[curl_slist], data : CString): Ptr[curl_slist] = extern

@extern def curl_slist_free_all(list : Ptr[curl_slist]): Unit = extern

@extern def curl_strequal(s1 : CString, s2 : CString): CInt = extern

@extern def curl_strnequal(s1 : CString, s2 : CString, n : size_t): CInt = extern

@extern def curl_unescape(string : CString, length : CInt): CString = extern

@extern def curl_url(): Ptr[CURLU] = extern

@extern def curl_url_cleanup(handle : Ptr[CURLU]): Unit = extern

@extern def curl_url_dup(in : Ptr[CURLU]): Ptr[CURLU] = extern

@extern def curl_url_get(handle : Ptr[CURLU], what : CURLUPart, part : Ptr[CString], flags : CUnsignedInt): CURLUcode = extern

@extern def curl_url_set(handle : Ptr[CURLU], what : CURLUPart, part : CString, flags : CUnsignedInt): CURLUcode = extern

@extern def curl_url_strerror(_0 : CURLUcode): CString = extern

@extern def curl_version(): CString = extern

@extern def curl_version_info(_0 : CURLversion): Ptr[curl_version_info_data] = extern

@extern def curl_ws_meta(curl : Ptr[CURL]): Ptr[curl_ws_frame] = extern

@extern def curl_ws_recv(curl : Ptr[CURL], buffer : Ptr[Byte], buflen : size_t, recv : Ptr[size_t], metap : Ptr[Ptr[curl_ws_frame]]): CURLcode = extern

@extern def curl_ws_send(curl : Ptr[CURL], buffer : Ptr[Byte], buflen : size_t, sent : Ptr[size_t], framesize : curl_off_t, sendflags : CUnsignedInt): CURLcode = extern