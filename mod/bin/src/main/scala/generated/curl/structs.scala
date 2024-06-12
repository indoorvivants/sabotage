package curl

import _root_.scala.scalanative.unsafe.*
import _root_.scala.scalanative.unsigned.*
import _root_.scala.scalanative.libc.*
import _root_.scala.scalanative.*

opaque type CURLMsg = CStruct3[CURLMSG, Ptr[CURL], CURLMsg.Union0]
object CURLMsg:
  opaque type Union0 = CArray[Byte, Nat._8]
  object Union0:
    given _tag: Tag[Union0] = Tag.CArray[CChar, Nat._8](Tag.Byte, Tag.Nat8)
    def apply()(using Zone): Ptr[Union0] = 
      val ___ptr = alloc[Union0](1)
      ___ptr
    @scala.annotation.targetName("apply_whatever")
    def apply(whatever: Ptr[Byte])(using Zone): Ptr[Union0] =
      val ___ptr = alloc[Union0](1)
      val un = !___ptr
      un.at(0).asInstanceOf[Ptr[Ptr[Byte]]].update(0, whatever)
      ___ptr
    @scala.annotation.targetName("apply_result")
    def apply(result: CURLcode)(using Zone): Ptr[Union0] =
      val ___ptr = alloc[Union0](1)
      val un = !___ptr
      un.at(0).asInstanceOf[Ptr[CURLcode]].update(0, result)
      ___ptr
    extension (struct: Union0)
      def whatever : Ptr[Byte] = !struct.at(0).asInstanceOf[Ptr[Ptr[Byte]]]
      def whatever_=(value: Ptr[Byte]): Unit = !struct.at(0).asInstanceOf[Ptr[Ptr[Byte]]] = value
      def result : CURLcode = !struct.at(0).asInstanceOf[Ptr[CURLcode]]
      def result_=(value: CURLcode): Unit = !struct.at(0).asInstanceOf[Ptr[CURLcode]] = value
  given _tag: Tag[CURLMsg] = Tag.materializeCStruct3Tag[CURLMSG, Ptr[CURL], CURLMsg.Union0]
  def apply()(using Zone): Ptr[CURLMsg] = scala.scalanative.unsafe.alloc[CURLMsg](1)
  def apply(msg : CURLMSG, easy_handle : Ptr[CURL], data : CURLMsg.Union0)(using Zone): Ptr[CURLMsg] = 
    val ____ptr = apply()
    (!____ptr).msg = msg
    (!____ptr).easy_handle = easy_handle
    (!____ptr).data = data
    ____ptr
  extension (struct: CURLMsg)
    def msg : CURLMSG = struct._1
    def msg_=(value: CURLMSG): Unit = !struct.at1 = value
    def easy_handle : Ptr[CURL] = struct._2
    def easy_handle_=(value: Ptr[CURL]): Unit = !struct.at2 = value
    def data : CURLMsg.Union0 = struct._3
    def data_=(value: CURLMsg.Union0): Unit = !struct.at3 = value

opaque type CURLU = CStruct0
object CURLU:
  given _tag: Tag[CURLU] = Tag.materializeCStruct0Tag

opaque type Curl_URL = CStruct0
object Curl_URL:
  given _tag: Tag[Curl_URL] = Tag.materializeCStruct0Tag

opaque type curl_blob = CStruct3[Ptr[Byte], size_t, CUnsignedInt]
object curl_blob:
  given _tag: Tag[curl_blob] = Tag.materializeCStruct3Tag[Ptr[Byte], size_t, CUnsignedInt]
  def apply()(using Zone): Ptr[curl_blob] = scala.scalanative.unsafe.alloc[curl_blob](1)
  def apply(data : Ptr[Byte], len : size_t, flags : CUnsignedInt)(using Zone): Ptr[curl_blob] = 
    val ____ptr = apply()
    (!____ptr).data = data
    (!____ptr).len = len
    (!____ptr).flags = flags
    ____ptr
  extension (struct: curl_blob)
    def data : Ptr[Byte] = struct._1
    def data_=(value: Ptr[Byte]): Unit = !struct.at1 = value
    def len : size_t = struct._2
    def len_=(value: size_t): Unit = !struct.at2 = value
    def flags : CUnsignedInt = struct._3
    def flags_=(value: CUnsignedInt): Unit = !struct.at3 = value

opaque type curl_certinfo = CStruct2[CInt, Ptr[Byte]]
object curl_certinfo:
  given _tag: Tag[curl_certinfo] = Tag.materializeCStruct2Tag[CInt, Ptr[Byte]]
  def apply()(using Zone): Ptr[curl_certinfo] = scala.scalanative.unsafe.alloc[curl_certinfo](1)
  def apply(num_of_certs : CInt, certinfo : Ptr[Ptr[curl_slist]])(using Zone): Ptr[curl_certinfo] = 
    val ____ptr = apply()
    (!____ptr).num_of_certs = num_of_certs
    (!____ptr).certinfo = certinfo
    ____ptr
  extension (struct: curl_certinfo)
    def num_of_certs : CInt = struct._1
    def num_of_certs_=(value: CInt): Unit = !struct.at1 = value
    def certinfo : Ptr[Ptr[curl_slist]] = struct._2.asInstanceOf[Ptr[Ptr[curl_slist]]]
    def certinfo_=(value: Ptr[Ptr[curl_slist]]): Unit = !struct.at2 = value.asInstanceOf[Ptr[Byte]]

opaque type curl_easyoption = CStruct4[CString, CURLoption, curl_easytype, CUnsignedInt]
object curl_easyoption:
  given _tag: Tag[curl_easyoption] = Tag.materializeCStruct4Tag[CString, CURLoption, curl_easytype, CUnsignedInt]
  def apply()(using Zone): Ptr[curl_easyoption] = scala.scalanative.unsafe.alloc[curl_easyoption](1)
  def apply(name : CString, id : CURLoption, `type` : curl_easytype, flags : CUnsignedInt)(using Zone): Ptr[curl_easyoption] = 
    val ____ptr = apply()
    (!____ptr).name = name
    (!____ptr).id = id
    (!____ptr).`type` = `type`
    (!____ptr).flags = flags
    ____ptr
  extension (struct: curl_easyoption)
    def name : CString = struct._1
    def name_=(value: CString): Unit = !struct.at1 = value
    def id : CURLoption = struct._2
    def id_=(value: CURLoption): Unit = !struct.at2 = value
    def `type` : curl_easytype = struct._3
    def type_=(value: curl_easytype): Unit = !struct.at3 = value
    def flags : CUnsignedInt = struct._4
    def flags_=(value: CUnsignedInt): Unit = !struct.at4 = value

opaque type curl_fileinfo = CStruct13[CString, curlfiletype, time_t, CUnsignedInt, CInt, CInt, curl_off_t, CLongInt, curl_fileinfo.Struct0, CUnsignedInt, CString, size_t, size_t]
object curl_fileinfo:
  opaque type Struct0 = CStruct5[CString, CString, CString, CString, CString]
  object Struct0:
    given _tag: Tag[Struct0] = Tag.materializeCStruct5Tag[CString, CString, CString, CString, CString]
    def apply()(using Zone): Ptr[Struct0] = scala.scalanative.unsafe.alloc[Struct0](1)
    def apply(time : CString, perm : CString, user : CString, group : CString, target : CString)(using Zone): Ptr[Struct0] = 
      val ____ptr = apply()
      (!____ptr).time = time
      (!____ptr).perm = perm
      (!____ptr).user = user
      (!____ptr).group = group
      (!____ptr).target = target
      ____ptr
    extension (struct: Struct0)
      def time : CString = struct._1
      def time_=(value: CString): Unit = !struct.at1 = value
      def perm : CString = struct._2
      def perm_=(value: CString): Unit = !struct.at2 = value
      def user : CString = struct._3
      def user_=(value: CString): Unit = !struct.at3 = value
      def group : CString = struct._4
      def group_=(value: CString): Unit = !struct.at4 = value
      def target : CString = struct._5
      def target_=(value: CString): Unit = !struct.at5 = value
  given _tag: Tag[curl_fileinfo] = Tag.materializeCStruct13Tag[CString, curlfiletype, time_t, CUnsignedInt, CInt, CInt, curl_off_t, CLongInt, curl_fileinfo.Struct0, CUnsignedInt, CString, size_t, size_t]
  def apply()(using Zone): Ptr[curl_fileinfo] = scala.scalanative.unsafe.alloc[curl_fileinfo](1)
  def apply(filename : CString, filetype : curlfiletype, time : time_t, perm : CUnsignedInt, uid : CInt, gid : CInt, size : curl_off_t, hardlinks : CLongInt, strings : curl_fileinfo.Struct0, flags : CUnsignedInt, b_data : CString, b_size : size_t, b_used : size_t)(using Zone): Ptr[curl_fileinfo] = 
    val ____ptr = apply()
    (!____ptr).filename = filename
    (!____ptr).filetype = filetype
    (!____ptr).time = time
    (!____ptr).perm = perm
    (!____ptr).uid = uid
    (!____ptr).gid = gid
    (!____ptr).size = size
    (!____ptr).hardlinks = hardlinks
    (!____ptr).strings = strings
    (!____ptr).flags = flags
    (!____ptr).b_data = b_data
    (!____ptr).b_size = b_size
    (!____ptr).b_used = b_used
    ____ptr
  extension (struct: curl_fileinfo)
    def filename : CString = struct._1
    def filename_=(value: CString): Unit = !struct.at1 = value
    def filetype : curlfiletype = struct._2
    def filetype_=(value: curlfiletype): Unit = !struct.at2 = value
    def time : time_t = struct._3
    def time_=(value: time_t): Unit = !struct.at3 = value
    def perm : CUnsignedInt = struct._4
    def perm_=(value: CUnsignedInt): Unit = !struct.at4 = value
    def uid : CInt = struct._5
    def uid_=(value: CInt): Unit = !struct.at5 = value
    def gid : CInt = struct._6
    def gid_=(value: CInt): Unit = !struct.at6 = value
    def size : curl_off_t = struct._7
    def size_=(value: curl_off_t): Unit = !struct.at7 = value
    def hardlinks : CLongInt = struct._8
    def hardlinks_=(value: CLongInt): Unit = !struct.at8 = value
    def strings : curl_fileinfo.Struct0 = struct._9
    def strings_=(value: curl_fileinfo.Struct0): Unit = !struct.at9 = value
    def flags : CUnsignedInt = struct._10
    def flags_=(value: CUnsignedInt): Unit = !struct.at10 = value
    def b_data : CString = struct._11
    def b_data_=(value: CString): Unit = !struct.at11 = value
    def b_size : size_t = struct._12
    def b_size_=(value: size_t): Unit = !struct.at12 = value
    def b_used : size_t = struct._13
    def b_used_=(value: size_t): Unit = !struct.at13 = value

opaque type curl_forms = CStruct2[CURLformoption, CString]
object curl_forms:
  given _tag: Tag[curl_forms] = Tag.materializeCStruct2Tag[CURLformoption, CString]
  def apply()(using Zone): Ptr[curl_forms] = scala.scalanative.unsafe.alloc[curl_forms](1)
  def apply(option : CURLformoption, value : CString)(using Zone): Ptr[curl_forms] = 
    val ____ptr = apply()
    (!____ptr).option = option
    (!____ptr).value = value
    ____ptr
  extension (struct: curl_forms)
    def option : CURLformoption = struct._1
    def option_=(value: CURLformoption): Unit = !struct.at1 = value
    def value : CString = struct._2
    def value_=(value: CString): Unit = !struct.at2 = value

opaque type curl_header = CStruct6[CString, CString, size_t, size_t, CUnsignedInt, Ptr[Byte]]
object curl_header:
  given _tag: Tag[curl_header] = Tag.materializeCStruct6Tag[CString, CString, size_t, size_t, CUnsignedInt, Ptr[Byte]]
  def apply()(using Zone): Ptr[curl_header] = scala.scalanative.unsafe.alloc[curl_header](1)
  def apply(name : CString, value : CString, amount : size_t, index : size_t, origin : CUnsignedInt, anchor : Ptr[Byte])(using Zone): Ptr[curl_header] = 
    val ____ptr = apply()
    (!____ptr).name = name
    (!____ptr).value = value
    (!____ptr).amount = amount
    (!____ptr).index = index
    (!____ptr).origin = origin
    (!____ptr).anchor = anchor
    ____ptr
  extension (struct: curl_header)
    def name : CString = struct._1
    def name_=(value: CString): Unit = !struct.at1 = value
    def value : CString = struct._2
    def value_=(value: CString): Unit = !struct.at2 = value
    def amount : size_t = struct._3
    def amount_=(value: size_t): Unit = !struct.at3 = value
    def index : size_t = struct._4
    def index_=(value: size_t): Unit = !struct.at4 = value
    def origin : CUnsignedInt = struct._5
    def origin_=(value: CUnsignedInt): Unit = !struct.at5 = value
    def anchor : Ptr[Byte] = struct._6
    def anchor_=(value: Ptr[Byte]): Unit = !struct.at6 = value

opaque type curl_hstsentry = CStruct4[CString, size_t, CUnsignedInt, CArray[CChar, Nat.Digit2[Nat._1, Nat._8]]]
object curl_hstsentry:
  given _tag: Tag[curl_hstsentry] = Tag.materializeCStruct4Tag[CString, size_t, CUnsignedInt, CArray[CChar, Nat.Digit2[Nat._1, Nat._8]]]
  def apply()(using Zone): Ptr[curl_hstsentry] = scala.scalanative.unsafe.alloc[curl_hstsentry](1)
  def apply(name : CString, namelen : size_t, includeSubDomains : CUnsignedInt, expire : CArray[CChar, Nat.Digit2[Nat._1, Nat._8]])(using Zone): Ptr[curl_hstsentry] = 
    val ____ptr = apply()
    (!____ptr).name = name
    (!____ptr).namelen = namelen
    (!____ptr).includeSubDomains = includeSubDomains
    (!____ptr).expire = expire
    ____ptr
  extension (struct: curl_hstsentry)
    def name : CString = struct._1
    def name_=(value: CString): Unit = !struct.at1 = value
    def namelen : size_t = struct._2
    def namelen_=(value: size_t): Unit = !struct.at2 = value
    def includeSubDomains : CUnsignedInt = struct._3
    def includeSubDomains_=(value: CUnsignedInt): Unit = !struct.at3 = value
    def expire : CArray[CChar, Nat.Digit2[Nat._1, Nat._8]] = struct._4
    def expire_=(value: CArray[CChar, Nat.Digit2[Nat._1, Nat._8]]): Unit = !struct.at4 = value

opaque type curl_httppost = CStruct14[Ptr[Byte], CString, CLongInt, CString, CLongInt, CString, CLongInt, CString, Ptr[Byte], Ptr[Byte], CLongInt, CString, Ptr[Byte], curl_off_t]
object curl_httppost:
  opaque type Struct0 = CStruct0
  object Struct0:
    given _tag: Tag[Struct0] = Tag.materializeCStruct0Tag
  given _tag: Tag[curl_httppost] = Tag.materializeCStruct14Tag[Ptr[Byte], CString, CLongInt, CString, CLongInt, CString, CLongInt, CString, Ptr[Byte], Ptr[Byte], CLongInt, CString, Ptr[Byte], curl_off_t]
  def apply()(using Zone): Ptr[curl_httppost] = scala.scalanative.unsafe.alloc[curl_httppost](1)
  def apply(next : Ptr[curl_httppost], name : CString, namelength : CLongInt, contents : CString, contentslength : CLongInt, buffer : CString, bufferlength : CLongInt, contenttype : CString, contentheader : Ptr[curl_slist], more : Ptr[curl_httppost], flags : CLongInt, showfilename : CString, userp : Ptr[Byte], contentlen : curl_off_t)(using Zone): Ptr[curl_httppost] = 
    val ____ptr = apply()
    (!____ptr).next = next
    (!____ptr).name = name
    (!____ptr).namelength = namelength
    (!____ptr).contents = contents
    (!____ptr).contentslength = contentslength
    (!____ptr).buffer = buffer
    (!____ptr).bufferlength = bufferlength
    (!____ptr).contenttype = contenttype
    (!____ptr).contentheader = contentheader
    (!____ptr).more = more
    (!____ptr).flags = flags
    (!____ptr).showfilename = showfilename
    (!____ptr).userp = userp
    (!____ptr).contentlen = contentlen
    ____ptr
  extension (struct: curl_httppost)
    def next : Ptr[curl_httppost] = struct._1.asInstanceOf[Ptr[curl_httppost]]
    def next_=(value: Ptr[curl_httppost]): Unit = !struct.at1 = value.asInstanceOf[Ptr[Byte]]
    def name : CString = struct._2
    def name_=(value: CString): Unit = !struct.at2 = value
    def namelength : CLongInt = struct._3
    def namelength_=(value: CLongInt): Unit = !struct.at3 = value
    def contents : CString = struct._4
    def contents_=(value: CString): Unit = !struct.at4 = value
    def contentslength : CLongInt = struct._5
    def contentslength_=(value: CLongInt): Unit = !struct.at5 = value
    def buffer : CString = struct._6
    def buffer_=(value: CString): Unit = !struct.at6 = value
    def bufferlength : CLongInt = struct._7
    def bufferlength_=(value: CLongInt): Unit = !struct.at7 = value
    def contenttype : CString = struct._8
    def contenttype_=(value: CString): Unit = !struct.at8 = value
    def contentheader : Ptr[curl_slist] = struct._9.asInstanceOf[Ptr[curl_slist]]
    def contentheader_=(value: Ptr[curl_slist]): Unit = !struct.at9 = value.asInstanceOf[Ptr[Byte]]
    def more : Ptr[curl_httppost] = struct._10.asInstanceOf[Ptr[curl_httppost]]
    def more_=(value: Ptr[curl_httppost]): Unit = !struct.at10 = value.asInstanceOf[Ptr[Byte]]
    def flags : CLongInt = struct._11
    def flags_=(value: CLongInt): Unit = !struct.at11 = value
    def showfilename : CString = struct._12
    def showfilename_=(value: CString): Unit = !struct.at12 = value
    def userp : Ptr[Byte] = struct._13
    def userp_=(value: Ptr[Byte]): Unit = !struct.at13 = value
    def contentlen : curl_off_t = struct._14
    def contentlen_=(value: curl_off_t): Unit = !struct.at14 = value

opaque type curl_index = CStruct2[size_t, size_t]
object curl_index:
  given _tag: Tag[curl_index] = Tag.materializeCStruct2Tag[size_t, size_t]
  def apply()(using Zone): Ptr[curl_index] = scala.scalanative.unsafe.alloc[curl_index](1)
  def apply(index : size_t, total : size_t)(using Zone): Ptr[curl_index] = 
    val ____ptr = apply()
    (!____ptr).index = index
    (!____ptr).total = total
    ____ptr
  extension (struct: curl_index)
    def index : size_t = struct._1
    def index_=(value: size_t): Unit = !struct.at1 = value
    def total : size_t = struct._2
    def total_=(value: size_t): Unit = !struct.at2 = value

opaque type curl_khkey = CStruct3[CString, size_t, curl_khtype]
object curl_khkey:
  given _tag: Tag[curl_khkey] = Tag.materializeCStruct3Tag[CString, size_t, curl_khtype]
  def apply()(using Zone): Ptr[curl_khkey] = scala.scalanative.unsafe.alloc[curl_khkey](1)
  def apply(key : CString, len : size_t, keytype : curl_khtype)(using Zone): Ptr[curl_khkey] = 
    val ____ptr = apply()
    (!____ptr).key = key
    (!____ptr).len = len
    (!____ptr).keytype = keytype
    ____ptr
  extension (struct: curl_khkey)
    def key : CString = struct._1
    def key_=(value: CString): Unit = !struct.at1 = value
    def len : size_t = struct._2
    def len_=(value: size_t): Unit = !struct.at2 = value
    def keytype : curl_khtype = struct._3
    def keytype_=(value: curl_khtype): Unit = !struct.at3 = value

opaque type curl_mime = CStruct0
object curl_mime:
  given _tag: Tag[curl_mime] = Tag.materializeCStruct0Tag

opaque type curl_mimepart = CStruct0
object curl_mimepart:
  given _tag: Tag[curl_mimepart] = Tag.materializeCStruct0Tag

opaque type curl_pushheaders = CStruct0
object curl_pushheaders:
  given _tag: Tag[curl_pushheaders] = Tag.materializeCStruct0Tag

opaque type curl_slist = CStruct2[CString, Ptr[Byte]]
object curl_slist:
  given _tag: Tag[curl_slist] = Tag.materializeCStruct2Tag[CString, Ptr[Byte]]
  def apply()(using Zone): Ptr[curl_slist] = scala.scalanative.unsafe.alloc[curl_slist](1)
  def apply(data : CString, next : Ptr[curl_slist])(using Zone): Ptr[curl_slist] = 
    val ____ptr = apply()
    (!____ptr).data = data
    (!____ptr).next = next
    ____ptr
  extension (struct: curl_slist)
    def data : CString = struct._1
    def data_=(value: CString): Unit = !struct.at1 = value
    def next : Ptr[curl_slist] = struct._2.asInstanceOf[Ptr[curl_slist]]
    def next_=(value: Ptr[curl_slist]): Unit = !struct.at2 = value.asInstanceOf[Ptr[Byte]]

opaque type curl_sockaddr = CStruct5[CInt, CInt, CInt, CUnsignedInt, sockaddr]
object curl_sockaddr:
  given _tag: Tag[curl_sockaddr] = Tag.materializeCStruct5Tag[CInt, CInt, CInt, CUnsignedInt, sockaddr]
  def apply()(using Zone): Ptr[curl_sockaddr] = scala.scalanative.unsafe.alloc[curl_sockaddr](1)
  def apply(family : CInt, socktype : CInt, protocol : CInt, addrlen : CUnsignedInt, addr : sockaddr)(using Zone): Ptr[curl_sockaddr] = 
    val ____ptr = apply()
    (!____ptr).family = family
    (!____ptr).socktype = socktype
    (!____ptr).protocol = protocol
    (!____ptr).addrlen = addrlen
    (!____ptr).addr = addr
    ____ptr
  extension (struct: curl_sockaddr)
    def family : CInt = struct._1
    def family_=(value: CInt): Unit = !struct.at1 = value
    def socktype : CInt = struct._2
    def socktype_=(value: CInt): Unit = !struct.at2 = value
    def protocol : CInt = struct._3
    def protocol_=(value: CInt): Unit = !struct.at3 = value
    def addrlen : CUnsignedInt = struct._4
    def addrlen_=(value: CUnsignedInt): Unit = !struct.at4 = value
    def addr : sockaddr = struct._5
    def addr_=(value: sockaddr): Unit = !struct.at5 = value

opaque type curl_ssl_backend = CStruct2[curl_sslbackend, CString]
object curl_ssl_backend:
  given _tag: Tag[curl_ssl_backend] = Tag.materializeCStruct2Tag[curl_sslbackend, CString]
  def apply()(using Zone): Ptr[curl_ssl_backend] = scala.scalanative.unsafe.alloc[curl_ssl_backend](1)
  def apply(id : curl_sslbackend, name : CString)(using Zone): Ptr[curl_ssl_backend] = 
    val ____ptr = apply()
    (!____ptr).id = id
    (!____ptr).name = name
    ____ptr
  extension (struct: curl_ssl_backend)
    def id : curl_sslbackend = struct._1
    def id_=(value: curl_sslbackend): Unit = !struct.at1 = value
    def name : CString = struct._2
    def name_=(value: CString): Unit = !struct.at2 = value

opaque type curl_tlssessioninfo = CStruct2[curl_sslbackend, Ptr[Byte]]
object curl_tlssessioninfo:
  given _tag: Tag[curl_tlssessioninfo] = Tag.materializeCStruct2Tag[curl_sslbackend, Ptr[Byte]]
  def apply()(using Zone): Ptr[curl_tlssessioninfo] = scala.scalanative.unsafe.alloc[curl_tlssessioninfo](1)
  def apply(backend : curl_sslbackend, internals : Ptr[Byte])(using Zone): Ptr[curl_tlssessioninfo] = 
    val ____ptr = apply()
    (!____ptr).backend = backend
    (!____ptr).internals = internals
    ____ptr
  extension (struct: curl_tlssessioninfo)
    def backend : curl_sslbackend = struct._1
    def backend_=(value: curl_sslbackend): Unit = !struct.at1 = value
    def internals : Ptr[Byte] = struct._2
    def internals_=(value: Ptr[Byte]): Unit = !struct.at2 = value

opaque type curl_version_info_data = CArray[CChar, Nat.Digit3[Nat._2, Nat._0, Nat._8]]
object curl_version_info_data:
  given _tag: Tag[curl_version_info_data] = Tag.CArray[CChar, Nat.Digit3[Nat._2, Nat._0, Nat._8]](Tag.Byte, Tag.Digit3[Nat._2, Nat._0, Nat._8](Tag.Nat2, Tag.Nat0, Tag.Nat8))
  def apply()(using Zone): Ptr[curl_version_info_data] = scala.scalanative.unsafe.alloc[curl_version_info_data](1)
  def apply(age : CURLversion, version : CString, version_num : CUnsignedInt, host : CString, features : CInt, ssl_version : CString, ssl_version_num : CLongInt, libz_version : CString, protocols : Ptr[CString], ares : CString, ares_num : CInt, libidn : CString, iconv_ver_num : CInt, libssh_version : CString, brotli_ver_num : CUnsignedInt, brotli_version : CString, nghttp2_ver_num : CUnsignedInt, nghttp2_version : CString, quic_version : CString, cainfo : CString, capath : CString, zstd_ver_num : CUnsignedInt, zstd_version : CString, hyper_version : CString, gsasl_version : CString, feature_names : Ptr[CString])(using Zone): Ptr[curl_version_info_data] = 
    val ____ptr = apply()
    (!____ptr).age = age
    (!____ptr).version = version
    (!____ptr).version_num = version_num
    (!____ptr).host = host
    (!____ptr).features = features
    (!____ptr).ssl_version = ssl_version
    (!____ptr).ssl_version_num = ssl_version_num
    (!____ptr).libz_version = libz_version
    (!____ptr).protocols = protocols
    (!____ptr).ares = ares
    (!____ptr).ares_num = ares_num
    (!____ptr).libidn = libidn
    (!____ptr).iconv_ver_num = iconv_ver_num
    (!____ptr).libssh_version = libssh_version
    (!____ptr).brotli_ver_num = brotli_ver_num
    (!____ptr).brotli_version = brotli_version
    (!____ptr).nghttp2_ver_num = nghttp2_ver_num
    (!____ptr).nghttp2_version = nghttp2_version
    (!____ptr).quic_version = quic_version
    (!____ptr).cainfo = cainfo
    (!____ptr).capath = capath
    (!____ptr).zstd_ver_num = zstd_ver_num
    (!____ptr).zstd_version = zstd_version
    (!____ptr).hyper_version = hyper_version
    (!____ptr).gsasl_version = gsasl_version
    (!____ptr).feature_names = feature_names
    ____ptr
  extension (struct: curl_version_info_data)
    def age: CURLversion = !struct.at(0).asInstanceOf[Ptr[CURLversion]]
    def age_=(value: CURLversion): Unit = !struct.at(0).asInstanceOf[Ptr[CURLversion]] = value
    def version: CString = !struct.at(8).asInstanceOf[Ptr[CString]]
    def version_=(value: CString): Unit = !struct.at(8).asInstanceOf[Ptr[CString]] = value
    def version_num: CUnsignedInt = !struct.at(16).asInstanceOf[Ptr[CUnsignedInt]]
    def version_num_=(value: CUnsignedInt): Unit = !struct.at(16).asInstanceOf[Ptr[CUnsignedInt]] = value
    def host: CString = !struct.at(24).asInstanceOf[Ptr[CString]]
    def host_=(value: CString): Unit = !struct.at(24).asInstanceOf[Ptr[CString]] = value
    def features: CInt = !struct.at(32).asInstanceOf[Ptr[CInt]]
    def features_=(value: CInt): Unit = !struct.at(32).asInstanceOf[Ptr[CInt]] = value
    def ssl_version: CString = !struct.at(40).asInstanceOf[Ptr[CString]]
    def ssl_version_=(value: CString): Unit = !struct.at(40).asInstanceOf[Ptr[CString]] = value
    def ssl_version_num: CLongInt = !struct.at(48).asInstanceOf[Ptr[CLongInt]]
    def ssl_version_num_=(value: CLongInt): Unit = !struct.at(48).asInstanceOf[Ptr[CLongInt]] = value
    def libz_version: CString = !struct.at(56).asInstanceOf[Ptr[CString]]
    def libz_version_=(value: CString): Unit = !struct.at(56).asInstanceOf[Ptr[CString]] = value
    def protocols: Ptr[CString] = !struct.at(64).asInstanceOf[Ptr[Ptr[CString]]]
    def protocols_=(value: Ptr[CString]): Unit = !struct.at(64).asInstanceOf[Ptr[Ptr[CString]]] = value
    def ares: CString = !struct.at(72).asInstanceOf[Ptr[CString]]
    def ares_=(value: CString): Unit = !struct.at(72).asInstanceOf[Ptr[CString]] = value
    def ares_num: CInt = !struct.at(80).asInstanceOf[Ptr[CInt]]
    def ares_num_=(value: CInt): Unit = !struct.at(80).asInstanceOf[Ptr[CInt]] = value
    def libidn: CString = !struct.at(88).asInstanceOf[Ptr[CString]]
    def libidn_=(value: CString): Unit = !struct.at(88).asInstanceOf[Ptr[CString]] = value
    def iconv_ver_num: CInt = !struct.at(96).asInstanceOf[Ptr[CInt]]
    def iconv_ver_num_=(value: CInt): Unit = !struct.at(96).asInstanceOf[Ptr[CInt]] = value
    def libssh_version: CString = !struct.at(104).asInstanceOf[Ptr[CString]]
    def libssh_version_=(value: CString): Unit = !struct.at(104).asInstanceOf[Ptr[CString]] = value
    def brotli_ver_num: CUnsignedInt = !struct.at(112).asInstanceOf[Ptr[CUnsignedInt]]
    def brotli_ver_num_=(value: CUnsignedInt): Unit = !struct.at(112).asInstanceOf[Ptr[CUnsignedInt]] = value
    def brotli_version: CString = !struct.at(120).asInstanceOf[Ptr[CString]]
    def brotli_version_=(value: CString): Unit = !struct.at(120).asInstanceOf[Ptr[CString]] = value
    def nghttp2_ver_num: CUnsignedInt = !struct.at(128).asInstanceOf[Ptr[CUnsignedInt]]
    def nghttp2_ver_num_=(value: CUnsignedInt): Unit = !struct.at(128).asInstanceOf[Ptr[CUnsignedInt]] = value
    def nghttp2_version: CString = !struct.at(136).asInstanceOf[Ptr[CString]]
    def nghttp2_version_=(value: CString): Unit = !struct.at(136).asInstanceOf[Ptr[CString]] = value
    def quic_version: CString = !struct.at(144).asInstanceOf[Ptr[CString]]
    def quic_version_=(value: CString): Unit = !struct.at(144).asInstanceOf[Ptr[CString]] = value
    def cainfo: CString = !struct.at(152).asInstanceOf[Ptr[CString]]
    def cainfo_=(value: CString): Unit = !struct.at(152).asInstanceOf[Ptr[CString]] = value
    def capath: CString = !struct.at(160).asInstanceOf[Ptr[CString]]
    def capath_=(value: CString): Unit = !struct.at(160).asInstanceOf[Ptr[CString]] = value
    def zstd_ver_num: CUnsignedInt = !struct.at(168).asInstanceOf[Ptr[CUnsignedInt]]
    def zstd_ver_num_=(value: CUnsignedInt): Unit = !struct.at(168).asInstanceOf[Ptr[CUnsignedInt]] = value
    def zstd_version: CString = !struct.at(176).asInstanceOf[Ptr[CString]]
    def zstd_version_=(value: CString): Unit = !struct.at(176).asInstanceOf[Ptr[CString]] = value
    def hyper_version: CString = !struct.at(184).asInstanceOf[Ptr[CString]]
    def hyper_version_=(value: CString): Unit = !struct.at(184).asInstanceOf[Ptr[CString]] = value
    def gsasl_version: CString = !struct.at(192).asInstanceOf[Ptr[CString]]
    def gsasl_version_=(value: CString): Unit = !struct.at(192).asInstanceOf[Ptr[CString]] = value
    def feature_names: Ptr[CString] = !struct.at(200).asInstanceOf[Ptr[Ptr[CString]]]
    def feature_names_=(value: Ptr[CString]): Unit = !struct.at(200).asInstanceOf[Ptr[Ptr[CString]]] = value

opaque type curl_waitfd = CStruct3[curl_socket_t, CShort, CShort]
object curl_waitfd:
  given _tag: Tag[curl_waitfd] = Tag.materializeCStruct3Tag[curl_socket_t, CShort, CShort]
  def apply()(using Zone): Ptr[curl_waitfd] = scala.scalanative.unsafe.alloc[curl_waitfd](1)
  def apply(fd : curl_socket_t, events : CShort, revents : CShort)(using Zone): Ptr[curl_waitfd] = 
    val ____ptr = apply()
    (!____ptr).fd = fd
    (!____ptr).events = events
    (!____ptr).revents = revents
    ____ptr
  extension (struct: curl_waitfd)
    def fd : curl_socket_t = struct._1
    def fd_=(value: curl_socket_t): Unit = !struct.at1 = value
    def events : CShort = struct._2
    def events_=(value: CShort): Unit = !struct.at2 = value
    def revents : CShort = struct._3
    def revents_=(value: CShort): Unit = !struct.at3 = value

opaque type curl_ws_frame = CStruct5[CInt, CInt, curl_off_t, curl_off_t, size_t]
object curl_ws_frame:
  given _tag: Tag[curl_ws_frame] = Tag.materializeCStruct5Tag[CInt, CInt, curl_off_t, curl_off_t, size_t]
  def apply()(using Zone): Ptr[curl_ws_frame] = scala.scalanative.unsafe.alloc[curl_ws_frame](1)
  def apply(age : CInt, flags : CInt, offset : curl_off_t, bytesleft : curl_off_t, len : size_t)(using Zone): Ptr[curl_ws_frame] = 
    val ____ptr = apply()
    (!____ptr).age = age
    (!____ptr).flags = flags
    (!____ptr).offset = offset
    (!____ptr).bytesleft = bytesleft
    (!____ptr).len = len
    ____ptr
  extension (struct: curl_ws_frame)
    def age : CInt = struct._1
    def age_=(value: CInt): Unit = !struct.at1 = value
    def flags : CInt = struct._2
    def flags_=(value: CInt): Unit = !struct.at2 = value
    def offset : curl_off_t = struct._3
    def offset_=(value: curl_off_t): Unit = !struct.at3 = value
    def bytesleft : curl_off_t = struct._4
    def bytesleft_=(value: curl_off_t): Unit = !struct.at4 = value
    def len : size_t = struct._5
    def len_=(value: size_t): Unit = !struct.at5 = value