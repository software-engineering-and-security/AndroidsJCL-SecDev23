<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 2464c9fe4c11 src/share/classes/sun/net/ftp/impl/FtpClient.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk8u/jdk8u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/shortlog/2464c9fe4c11">log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/graph/2464c9fe4c11">graph</a></li>
<li><a href="/jdk8u/jdk8u/jdk/tags">tags</a></li>
<li><a href="/jdk8u/jdk8u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/rev/2464c9fe4c11">changeset</a></li>
<li><a href="/jdk8u/jdk8u/jdk/file/2464c9fe4c11/src/share/classes/sun/net/ftp/impl/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/net/ftp/impl/FtpClient.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/2464c9fe4c11/src/share/classes/sun/net/ftp/impl/FtpClient.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/2464c9fe4c11/src/share/classes/sun/net/ftp/impl/FtpClient.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/2464c9fe4c11/src/share/classes/sun/net/ftp/impl/FtpClient.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/2464c9fe4c11/src/share/classes/sun/net/ftp/impl/FtpClient.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/2464c9fe4c11/src/share/classes/sun/net/ftp/impl/FtpClient.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/net/ftp/impl/FtpClient.java @ 14485:2464c9fe4c11</h3>

<form class="search" action="/jdk8u/jdk8u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk8u/jdk8u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8258432: Improve file transfers
Reviewed-by: mbalao, andrew</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#97;&#108;&#118;&#100;&#97;&#118;&#105;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Fri, 21 May 2021 19:56:49 +0000</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/3b1bfef6f82b/src/share/classes/sun/net/ftp/impl/FtpClient.java">3b1bfef6f82b</a> </td>
</tr>
<tr>
 <th class="author">children</th>
 <td class="author"></td>
</tr>
</table>

<div class="overflow">
<div class="sourcefirst linewraptoggle">line wrap: <a class="linewraplink" href="javascript:toggleLinewrap()">on</a></div>
<div class="sourcefirst"> line source</div>
<pre class="sourcelines stripes4 wrap">
<span id="l1">/*</span><a href="#l1"></a>
<span id="l2"> * Copyright (c) 2009, 2017, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
<span id="l3"> * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.</span><a href="#l3"></a>
<span id="l4"> *</span><a href="#l4"></a>
<span id="l5"> * This code is free software; you can redistribute it and/or modify it</span><a href="#l5"></a>
<span id="l6"> * under the terms of the GNU General Public License version 2 only, as</span><a href="#l6"></a>
<span id="l7"> * published by the Free Software Foundation.  Oracle designates this</span><a href="#l7"></a>
<span id="l8"> * particular file as subject to the &quot;Classpath&quot; exception as provided</span><a href="#l8"></a>
<span id="l9"> * by Oracle in the LICENSE file that accompanied this code.</span><a href="#l9"></a>
<span id="l10"> *</span><a href="#l10"></a>
<span id="l11"> * This code is distributed in the hope that it will be useful, but WITHOUT</span><a href="#l11"></a>
<span id="l12"> * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or</span><a href="#l12"></a>
<span id="l13"> * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License</span><a href="#l13"></a>
<span id="l14"> * version 2 for more details (a copy is included in the LICENSE file that</span><a href="#l14"></a>
<span id="l15"> * accompanied this code).</span><a href="#l15"></a>
<span id="l16"> *</span><a href="#l16"></a>
<span id="l17"> * You should have received a copy of the GNU General Public License version</span><a href="#l17"></a>
<span id="l18"> * 2 along with this work; if not, write to the Free Software Foundation,</span><a href="#l18"></a>
<span id="l19"> * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.</span><a href="#l19"></a>
<span id="l20"> *</span><a href="#l20"></a>
<span id="l21"> * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA</span><a href="#l21"></a>
<span id="l22"> * or visit www.oracle.com if you need additional information or have any</span><a href="#l22"></a>
<span id="l23"> * questions.</span><a href="#l23"></a>
<span id="l24"> */</span><a href="#l24"></a>
<span id="l25">package sun.net.ftp.impl;</span><a href="#l25"></a>
<span id="l26"></span><a href="#l26"></a>
<span id="l27">import java.io.BufferedInputStream;</span><a href="#l27"></a>
<span id="l28">import java.io.BufferedOutputStream;</span><a href="#l28"></a>
<span id="l29">import java.io.BufferedReader;</span><a href="#l29"></a>
<span id="l30">import java.io.Closeable;</span><a href="#l30"></a>
<span id="l31">import java.io.FileNotFoundException;</span><a href="#l31"></a>
<span id="l32">import java.io.IOException;</span><a href="#l32"></a>
<span id="l33">import java.io.InputStream;</span><a href="#l33"></a>
<span id="l34">import java.io.InputStreamReader;</span><a href="#l34"></a>
<span id="l35">import java.io.OutputStream;</span><a href="#l35"></a>
<span id="l36">import java.io.PrintStream;</span><a href="#l36"></a>
<span id="l37">import java.io.UnsupportedEncodingException;</span><a href="#l37"></a>
<span id="l38">import java.net.Inet6Address;</span><a href="#l38"></a>
<span id="l39">import java.net.InetAddress;</span><a href="#l39"></a>
<span id="l40">import java.net.InetSocketAddress;</span><a href="#l40"></a>
<span id="l41">import java.net.Proxy;</span><a href="#l41"></a>
<span id="l42">import java.net.ServerSocket;</span><a href="#l42"></a>
<span id="l43">import java.net.Socket;</span><a href="#l43"></a>
<span id="l44">import java.net.SocketAddress;</span><a href="#l44"></a>
<span id="l45">import java.security.AccessController;</span><a href="#l45"></a>
<span id="l46">import java.security.PrivilegedAction;</span><a href="#l46"></a>
<span id="l47">import java.security.PrivilegedExceptionAction;</span><a href="#l47"></a>
<span id="l48">import java.text.DateFormat;</span><a href="#l48"></a>
<span id="l49">import java.text.ParseException;</span><a href="#l49"></a>
<span id="l50">import java.text.SimpleDateFormat;</span><a href="#l50"></a>
<span id="l51">import java.util.ArrayList;</span><a href="#l51"></a>
<span id="l52">import java.util.Arrays;</span><a href="#l52"></a>
<span id="l53">import java.util.Calendar;</span><a href="#l53"></a>
<span id="l54">import java.util.Date;</span><a href="#l54"></a>
<span id="l55">import java.util.Iterator;</span><a href="#l55"></a>
<span id="l56">import java.util.List;</span><a href="#l56"></a>
<span id="l57">import java.util.TimeZone;</span><a href="#l57"></a>
<span id="l58">import java.util.Vector;</span><a href="#l58"></a>
<span id="l59">import java.util.regex.Matcher;</span><a href="#l59"></a>
<span id="l60">import java.util.regex.Pattern;</span><a href="#l60"></a>
<span id="l61">import javax.net.ssl.SSLSocket;</span><a href="#l61"></a>
<span id="l62">import javax.net.ssl.SSLSocketFactory;</span><a href="#l62"></a>
<span id="l63">import sun.misc.BASE64Decoder;</span><a href="#l63"></a>
<span id="l64">import sun.misc.BASE64Encoder;</span><a href="#l64"></a>
<span id="l65">import sun.net.ftp.FtpDirEntry;</span><a href="#l65"></a>
<span id="l66">import sun.net.ftp.FtpDirParser;</span><a href="#l66"></a>
<span id="l67">import sun.net.ftp.FtpProtocolException;</span><a href="#l67"></a>
<span id="l68">import sun.net.ftp.FtpReplyCode;</span><a href="#l68"></a>
<span id="l69">import sun.net.util.IPAddressUtil;</span><a href="#l69"></a>
<span id="l70">import sun.util.logging.PlatformLogger;</span><a href="#l70"></a>
<span id="l71"></span><a href="#l71"></a>
<span id="l72"></span><a href="#l72"></a>
<span id="l73">public class FtpClient extends sun.net.ftp.FtpClient {</span><a href="#l73"></a>
<span id="l74"></span><a href="#l74"></a>
<span id="l75">    private static int defaultSoTimeout;</span><a href="#l75"></a>
<span id="l76">    private static int defaultConnectTimeout;</span><a href="#l76"></a>
<span id="l77">    private static final PlatformLogger logger =</span><a href="#l77"></a>
<span id="l78">             PlatformLogger.getLogger(&quot;sun.net.ftp.FtpClient&quot;);</span><a href="#l78"></a>
<span id="l79">    private Proxy proxy;</span><a href="#l79"></a>
<span id="l80">    private Socket server;</span><a href="#l80"></a>
<span id="l81">    private PrintStream out;</span><a href="#l81"></a>
<span id="l82">    private InputStream in;</span><a href="#l82"></a>
<span id="l83">    private int readTimeout = -1;</span><a href="#l83"></a>
<span id="l84">    private int connectTimeout = -1;</span><a href="#l84"></a>
<span id="l85"></span><a href="#l85"></a>
<span id="l86">    /* Name of encoding to use for output */</span><a href="#l86"></a>
<span id="l87">    private static String encoding = &quot;ISO8859_1&quot;;</span><a href="#l87"></a>
<span id="l88">    /** remember the ftp server name because we may need it */</span><a href="#l88"></a>
<span id="l89">    private InetSocketAddress serverAddr;</span><a href="#l89"></a>
<span id="l90">    private boolean replyPending = false;</span><a href="#l90"></a>
<span id="l91">    private boolean loggedIn = false;</span><a href="#l91"></a>
<span id="l92">    private boolean useCrypto = false;</span><a href="#l92"></a>
<span id="l93">    private SSLSocketFactory sslFact;</span><a href="#l93"></a>
<span id="l94">    private Socket oldSocket;</span><a href="#l94"></a>
<span id="l95">    /** Array of strings (usually 1 entry) for the last reply from the server. */</span><a href="#l95"></a>
<span id="l96">    private Vector&lt;String&gt; serverResponse = new Vector&lt;String&gt;(1);</span><a href="#l96"></a>
<span id="l97">    /** The last reply code from the ftp daemon. */</span><a href="#l97"></a>
<span id="l98">    private FtpReplyCode lastReplyCode = null;</span><a href="#l98"></a>
<span id="l99">    /** Welcome message from the server, if any. */</span><a href="#l99"></a>
<span id="l100">    private String welcomeMsg;</span><a href="#l100"></a>
<span id="l101">    /**</span><a href="#l101"></a>
<span id="l102">     * Only passive mode used in JDK. See Bug 8010784.</span><a href="#l102"></a>
<span id="l103">     */</span><a href="#l103"></a>
<span id="l104">    private final boolean passiveMode = true;</span><a href="#l104"></a>
<span id="l105">    private TransferType type = TransferType.BINARY;</span><a href="#l105"></a>
<span id="l106">    private long restartOffset = 0;</span><a href="#l106"></a>
<span id="l107">    private long lastTransSize = -1; // -1 means 'unknown size'</span><a href="#l107"></a>
<span id="l108">    private String lastFileName;</span><a href="#l108"></a>
<span id="l109">    /**</span><a href="#l109"></a>
<span id="l110">     * Static members used by the parser</span><a href="#l110"></a>
<span id="l111">     */</span><a href="#l111"></a>
<span id="l112">    private static String[] patStrings = {</span><a href="#l112"></a>
<span id="l113">        // drwxr-xr-x  1 user01        ftp   512 Jan 29 23:32 prog</span><a href="#l113"></a>
<span id="l114">        &quot;([\\-ld](?:[r\\-][w\\-][x\\-]){3})\\s*\\d+ (\\w+)\\s*(\\w+)\\s*(\\d+)\\s*([A-Z][a-z][a-z]\\s*\\d+)\\s*(\\d\\d:\\d\\d)\\s*(\\p{Print}*)&quot;,</span><a href="#l114"></a>
<span id="l115">        // drwxr-xr-x  1 user01        ftp   512 Jan 29 1997 prog</span><a href="#l115"></a>
<span id="l116">        &quot;([\\-ld](?:[r\\-][w\\-][x\\-]){3})\\s*\\d+ (\\w+)\\s*(\\w+)\\s*(\\d+)\\s*([A-Z][a-z][a-z]\\s*\\d+)\\s*(\\d{4})\\s*(\\p{Print}*)&quot;,</span><a href="#l116"></a>
<span id="l117">        // 04/28/2006  09:12a               3,563 genBuffer.sh</span><a href="#l117"></a>
<span id="l118">        &quot;(\\d{2}/\\d{2}/\\d{4})\\s*(\\d{2}:\\d{2}[ap])\\s*((?:[0-9,]+)|(?:&lt;DIR&gt;))\\s*(\\p{Graph}*)&quot;,</span><a href="#l118"></a>
<span id="l119">        // 01-29-97    11:32PM &lt;DIR&gt; prog</span><a href="#l119"></a>
<span id="l120">        &quot;(\\d{2}-\\d{2}-\\d{2})\\s*(\\d{2}:\\d{2}[AP]M)\\s*((?:[0-9,]+)|(?:&lt;DIR&gt;))\\s*(\\p{Graph}*)&quot;</span><a href="#l120"></a>
<span id="l121">    };</span><a href="#l121"></a>
<span id="l122">    private static int[][] patternGroups = {</span><a href="#l122"></a>
<span id="l123">        // 0 - file, 1 - size, 2 - date, 3 - time, 4 - year, 5 - permissions,</span><a href="#l123"></a>
<span id="l124">        // 6 - user, 7 - group</span><a href="#l124"></a>
<span id="l125">        {7, 4, 5, 6, 0, 1, 2, 3},</span><a href="#l125"></a>
<span id="l126">        {7, 4, 5, 0, 6, 1, 2, 3},</span><a href="#l126"></a>
<span id="l127">        {4, 3, 1, 2, 0, 0, 0, 0},</span><a href="#l127"></a>
<span id="l128">        {4, 3, 1, 2, 0, 0, 0, 0}};</span><a href="#l128"></a>
<span id="l129">    private static Pattern[] patterns;</span><a href="#l129"></a>
<span id="l130">    private static Pattern linkp = Pattern.compile(&quot;(\\p{Print}+) \\-\\&gt; (\\p{Print}+)$&quot;);</span><a href="#l130"></a>
<span id="l131">    private DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, java.util.Locale.US);</span><a href="#l131"></a>
<span id="l132">    private static final boolean acceptPasvAddressVal;</span><a href="#l132"></a>
<span id="l133">    static {</span><a href="#l133"></a>
<span id="l134">        final int vals[] = {0, 0};</span><a href="#l134"></a>
<span id="l135">        final String encs[] = {null};</span><a href="#l135"></a>
<span id="l136">        final String acceptPasvAddress[] = {null};</span><a href="#l136"></a>
<span id="l137">        AccessController.doPrivileged(</span><a href="#l137"></a>
<span id="l138">                new PrivilegedAction&lt;Object&gt;() {</span><a href="#l138"></a>
<span id="l139"></span><a href="#l139"></a>
<span id="l140">                    public Object run() {</span><a href="#l140"></a>
<span id="l141">                        acceptPasvAddress[0] = System.getProperty(&quot;jdk.net.ftp.trustPasvAddress&quot;, &quot;false&quot;);</span><a href="#l141"></a>
<span id="l142">                        vals[0] = Integer.getInteger(&quot;sun.net.client.defaultReadTimeout&quot;, 300_000).intValue();</span><a href="#l142"></a>
<span id="l143">                        vals[1] = Integer.getInteger(&quot;sun.net.client.defaultConnectTimeout&quot;, 300_000).intValue();</span><a href="#l143"></a>
<span id="l144">                        encs[0] = System.getProperty(&quot;file.encoding&quot;, &quot;ISO8859_1&quot;);</span><a href="#l144"></a>
<span id="l145">                        return null;</span><a href="#l145"></a>
<span id="l146">                    }</span><a href="#l146"></a>
<span id="l147">                });</span><a href="#l147"></a>
<span id="l148">        if (vals[0] == 0) {</span><a href="#l148"></a>
<span id="l149">            defaultSoTimeout = -1;</span><a href="#l149"></a>
<span id="l150">        } else {</span><a href="#l150"></a>
<span id="l151">            defaultSoTimeout = vals[0];</span><a href="#l151"></a>
<span id="l152">        }</span><a href="#l152"></a>
<span id="l153"></span><a href="#l153"></a>
<span id="l154">        if (vals[1] == 0) {</span><a href="#l154"></a>
<span id="l155">            defaultConnectTimeout = -1;</span><a href="#l155"></a>
<span id="l156">        } else {</span><a href="#l156"></a>
<span id="l157">            defaultConnectTimeout = vals[1];</span><a href="#l157"></a>
<span id="l158">        }</span><a href="#l158"></a>
<span id="l159"></span><a href="#l159"></a>
<span id="l160">        encoding = encs[0];</span><a href="#l160"></a>
<span id="l161">        try {</span><a href="#l161"></a>
<span id="l162">            if (!isASCIISuperset(encoding)) {</span><a href="#l162"></a>
<span id="l163">                encoding = &quot;ISO8859_1&quot;;</span><a href="#l163"></a>
<span id="l164">            }</span><a href="#l164"></a>
<span id="l165">        } catch (Exception e) {</span><a href="#l165"></a>
<span id="l166">            encoding = &quot;ISO8859_1&quot;;</span><a href="#l166"></a>
<span id="l167">        }</span><a href="#l167"></a>
<span id="l168"></span><a href="#l168"></a>
<span id="l169">        patterns = new Pattern[patStrings.length];</span><a href="#l169"></a>
<span id="l170">        for (int i = 0; i &lt; patStrings.length; i++) {</span><a href="#l170"></a>
<span id="l171">            patterns[i] = Pattern.compile(patStrings[i]);</span><a href="#l171"></a>
<span id="l172">        }</span><a href="#l172"></a>
<span id="l173"></span><a href="#l173"></a>
<span id="l174">        acceptPasvAddressVal = Boolean.parseBoolean(acceptPasvAddress[0]);</span><a href="#l174"></a>
<span id="l175">    }</span><a href="#l175"></a>
<span id="l176"></span><a href="#l176"></a>
<span id="l177">    /**</span><a href="#l177"></a>
<span id="l178">     * Test the named character encoding to verify that it converts ASCII</span><a href="#l178"></a>
<span id="l179">     * characters correctly. We have to use an ASCII based encoding, or else</span><a href="#l179"></a>
<span id="l180">     * the NetworkClients will not work correctly in EBCDIC based systems.</span><a href="#l180"></a>
<span id="l181">     * However, we cannot just use ASCII or ISO8859_1 universally, because in</span><a href="#l181"></a>
<span id="l182">     * Asian locales, non-ASCII characters may be embedded in otherwise</span><a href="#l182"></a>
<span id="l183">     * ASCII based protocols (eg. HTTP). The specifications (RFC2616, 2398)</span><a href="#l183"></a>
<span id="l184">     * are a little ambiguous in this matter. For instance, RFC2398 [part 2.1]</span><a href="#l184"></a>
<span id="l185">     * says that the HTTP request URI should be escaped using a defined</span><a href="#l185"></a>
<span id="l186">     * mechanism, but there is no way to specify in the escaped string what</span><a href="#l186"></a>
<span id="l187">     * the original character set is. It is not correct to assume that</span><a href="#l187"></a>
<span id="l188">     * UTF-8 is always used (as in URLs in HTML 4.0).  For this reason,</span><a href="#l188"></a>
<span id="l189">     * until the specifications are updated to deal with this issue more</span><a href="#l189"></a>
<span id="l190">     * comprehensively, and more importantly, HTTP servers are known to</span><a href="#l190"></a>
<span id="l191">     * support these mechanisms, we will maintain the current behavior</span><a href="#l191"></a>
<span id="l192">     * where it is possible to send non-ASCII characters in their original</span><a href="#l192"></a>
<span id="l193">     * unescaped form.</span><a href="#l193"></a>
<span id="l194">     */</span><a href="#l194"></a>
<span id="l195">    private static boolean isASCIISuperset(String encoding) throws Exception {</span><a href="#l195"></a>
<span id="l196">        String chkS = &quot;0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ&quot; +</span><a href="#l196"></a>
<span id="l197">                &quot;abcdefghijklmnopqrstuvwxyz-_.!~*'();/?:@&amp;=+$,&quot;;</span><a href="#l197"></a>
<span id="l198"></span><a href="#l198"></a>
<span id="l199">        // Expected byte sequence for string above</span><a href="#l199"></a>
<span id="l200">        byte[] chkB = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72,</span><a href="#l200"></a>
<span id="l201">            73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99,</span><a href="#l201"></a>
<span id="l202">            100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114,</span><a href="#l202"></a>
<span id="l203">            115, 116, 117, 118, 119, 120, 121, 122, 45, 95, 46, 33, 126, 42, 39, 40, 41, 59,</span><a href="#l203"></a>
<span id="l204">            47, 63, 58, 64, 38, 61, 43, 36, 44};</span><a href="#l204"></a>
<span id="l205"></span><a href="#l205"></a>
<span id="l206">        byte[] b = chkS.getBytes(encoding);</span><a href="#l206"></a>
<span id="l207">        return java.util.Arrays.equals(b, chkB);</span><a href="#l207"></a>
<span id="l208">    }</span><a href="#l208"></a>
<span id="l209"></span><a href="#l209"></a>
<span id="l210">    private class DefaultParser implements FtpDirParser {</span><a href="#l210"></a>
<span id="l211"></span><a href="#l211"></a>
<span id="l212">        /**</span><a href="#l212"></a>
<span id="l213">         * Possible patterns:</span><a href="#l213"></a>
<span id="l214">         *</span><a href="#l214"></a>
<span id="l215">         *  drwxr-xr-x  1 user01        ftp   512 Jan 29 23:32 prog</span><a href="#l215"></a>
<span id="l216">         *  drwxr-xr-x  1 user01        ftp   512 Jan 29 1997 prog</span><a href="#l216"></a>
<span id="l217">         *  drwxr-xr-x  1 1             1     512 Jan 29 23:32 prog</span><a href="#l217"></a>
<span id="l218">         *  lrwxr-xr-x  1 user01        ftp   512 Jan 29 23:32 prog -&gt; prog2000</span><a href="#l218"></a>
<span id="l219">         *  drwxr-xr-x  1 username      ftp   512 Jan 29 23:32 prog</span><a href="#l219"></a>
<span id="l220">         *  -rw-r--r--  1 jcc      staff     105009 Feb  3 15:05 test.1</span><a href="#l220"></a>
<span id="l221">         *</span><a href="#l221"></a>
<span id="l222">         *  01-29-97    11:32PM &lt;DIR&gt; prog</span><a href="#l222"></a>
<span id="l223">         *  04/28/2006  09:12a               3,563 genBuffer.sh</span><a href="#l223"></a>
<span id="l224">         *</span><a href="#l224"></a>
<span id="l225">         *  drwxr-xr-x  folder   0       Jan 29 23:32 prog</span><a href="#l225"></a>
<span id="l226">         *</span><a href="#l226"></a>
<span id="l227">         *  0 DIR 01-29-97 23:32 PROG</span><a href="#l227"></a>
<span id="l228">         */</span><a href="#l228"></a>
<span id="l229">        private DefaultParser() {</span><a href="#l229"></a>
<span id="l230">        }</span><a href="#l230"></a>
<span id="l231"></span><a href="#l231"></a>
<span id="l232">        public FtpDirEntry parseLine(String line) {</span><a href="#l232"></a>
<span id="l233">            String fdate = null;</span><a href="#l233"></a>
<span id="l234">            String fsize = null;</span><a href="#l234"></a>
<span id="l235">            String time = null;</span><a href="#l235"></a>
<span id="l236">            String filename = null;</span><a href="#l236"></a>
<span id="l237">            String permstring = null;</span><a href="#l237"></a>
<span id="l238">            String username = null;</span><a href="#l238"></a>
<span id="l239">            String groupname = null;</span><a href="#l239"></a>
<span id="l240">            boolean dir = false;</span><a href="#l240"></a>
<span id="l241">            Calendar now = Calendar.getInstance();</span><a href="#l241"></a>
<span id="l242">            int year = now.get(Calendar.YEAR);</span><a href="#l242"></a>
<span id="l243"></span><a href="#l243"></a>
<span id="l244">            Matcher m = null;</span><a href="#l244"></a>
<span id="l245">            for (int j = 0; j &lt; patterns.length; j++) {</span><a href="#l245"></a>
<span id="l246">                m = patterns[j].matcher(line);</span><a href="#l246"></a>
<span id="l247">                if (m.find()) {</span><a href="#l247"></a>
<span id="l248">                    // 0 - file, 1 - size, 2 - date, 3 - time, 4 - year,</span><a href="#l248"></a>
<span id="l249">                    // 5 - permissions, 6 - user, 7 - group</span><a href="#l249"></a>
<span id="l250">                    filename = m.group(patternGroups[j][0]);</span><a href="#l250"></a>
<span id="l251">                    fsize = m.group(patternGroups[j][1]);</span><a href="#l251"></a>
<span id="l252">                    fdate = m.group(patternGroups[j][2]);</span><a href="#l252"></a>
<span id="l253">                    if (patternGroups[j][4] &gt; 0) {</span><a href="#l253"></a>
<span id="l254">                        fdate += (&quot;, &quot; + m.group(patternGroups[j][4]));</span><a href="#l254"></a>
<span id="l255">                    } else if (patternGroups[j][3] &gt; 0) {</span><a href="#l255"></a>
<span id="l256">                        fdate += (&quot;, &quot; + String.valueOf(year));</span><a href="#l256"></a>
<span id="l257">                    }</span><a href="#l257"></a>
<span id="l258">                    if (patternGroups[j][3] &gt; 0) {</span><a href="#l258"></a>
<span id="l259">                        time = m.group(patternGroups[j][3]);</span><a href="#l259"></a>
<span id="l260">                    }</span><a href="#l260"></a>
<span id="l261">                    if (patternGroups[j][5] &gt; 0) {</span><a href="#l261"></a>
<span id="l262">                        permstring = m.group(patternGroups[j][5]);</span><a href="#l262"></a>
<span id="l263">                        dir = permstring.startsWith(&quot;d&quot;);</span><a href="#l263"></a>
<span id="l264">                    }</span><a href="#l264"></a>
<span id="l265">                    if (patternGroups[j][6] &gt; 0) {</span><a href="#l265"></a>
<span id="l266">                        username = m.group(patternGroups[j][6]);</span><a href="#l266"></a>
<span id="l267">                    }</span><a href="#l267"></a>
<span id="l268">                    if (patternGroups[j][7] &gt; 0) {</span><a href="#l268"></a>
<span id="l269">                        groupname = m.group(patternGroups[j][7]);</span><a href="#l269"></a>
<span id="l270">                    }</span><a href="#l270"></a>
<span id="l271">                    // Old DOS format</span><a href="#l271"></a>
<span id="l272">                    if (&quot;&lt;DIR&gt;&quot;.equals(fsize)) {</span><a href="#l272"></a>
<span id="l273">                        dir = true;</span><a href="#l273"></a>
<span id="l274">                        fsize = null;</span><a href="#l274"></a>
<span id="l275">                    }</span><a href="#l275"></a>
<span id="l276">                }</span><a href="#l276"></a>
<span id="l277">            }</span><a href="#l277"></a>
<span id="l278"></span><a href="#l278"></a>
<span id="l279">            if (filename != null) {</span><a href="#l279"></a>
<span id="l280">                Date d;</span><a href="#l280"></a>
<span id="l281">                try {</span><a href="#l281"></a>
<span id="l282">                    d = df.parse(fdate);</span><a href="#l282"></a>
<span id="l283">                } catch (Exception e) {</span><a href="#l283"></a>
<span id="l284">                    d = null;</span><a href="#l284"></a>
<span id="l285">                }</span><a href="#l285"></a>
<span id="l286">                if (d != null &amp;&amp; time != null) {</span><a href="#l286"></a>
<span id="l287">                    int c = time.indexOf(&quot;:&quot;);</span><a href="#l287"></a>
<span id="l288">                    now.setTime(d);</span><a href="#l288"></a>
<span id="l289">                    now.set(Calendar.HOUR, Integer.parseInt(time.substring(0, c)));</span><a href="#l289"></a>
<span id="l290">                    now.set(Calendar.MINUTE, Integer.parseInt(time.substring(c + 1)));</span><a href="#l290"></a>
<span id="l291">                    d = now.getTime();</span><a href="#l291"></a>
<span id="l292">                }</span><a href="#l292"></a>
<span id="l293">                // see if it's a symbolic link, i.e. the name if followed</span><a href="#l293"></a>
<span id="l294">                // by a -&gt; and a path</span><a href="#l294"></a>
<span id="l295">                Matcher m2 = linkp.matcher(filename);</span><a href="#l295"></a>
<span id="l296">                if (m2.find()) {</span><a href="#l296"></a>
<span id="l297">                    // Keep only the name then</span><a href="#l297"></a>
<span id="l298">                    filename = m2.group(1);</span><a href="#l298"></a>
<span id="l299">                }</span><a href="#l299"></a>
<span id="l300">                boolean[][] perms = new boolean[3][3];</span><a href="#l300"></a>
<span id="l301">                for (int i = 0; i &lt; 3; i++) {</span><a href="#l301"></a>
<span id="l302">                    for (int j = 0; j &lt; 3; j++) {</span><a href="#l302"></a>
<span id="l303">                        perms[i][j] = (permstring.charAt((i * 3) + j) != '-');</span><a href="#l303"></a>
<span id="l304">                    }</span><a href="#l304"></a>
<span id="l305">                }</span><a href="#l305"></a>
<span id="l306">                FtpDirEntry file = new FtpDirEntry(filename);</span><a href="#l306"></a>
<span id="l307">                file.setUser(username).setGroup(groupname);</span><a href="#l307"></a>
<span id="l308">                file.setSize(Long.parseLong(fsize)).setLastModified(d);</span><a href="#l308"></a>
<span id="l309">                file.setPermissions(perms);</span><a href="#l309"></a>
<span id="l310">                file.setType(dir ? FtpDirEntry.Type.DIR : (line.charAt(0) == 'l' ? FtpDirEntry.Type.LINK : FtpDirEntry.Type.FILE));</span><a href="#l310"></a>
<span id="l311">                return file;</span><a href="#l311"></a>
<span id="l312">            }</span><a href="#l312"></a>
<span id="l313">            return null;</span><a href="#l313"></a>
<span id="l314">        }</span><a href="#l314"></a>
<span id="l315">    }</span><a href="#l315"></a>
<span id="l316"></span><a href="#l316"></a>
<span id="l317">    private class MLSxParser implements FtpDirParser {</span><a href="#l317"></a>
<span id="l318"></span><a href="#l318"></a>
<span id="l319">        private SimpleDateFormat df = new SimpleDateFormat(&quot;yyyyMMddhhmmss&quot;);</span><a href="#l319"></a>
<span id="l320"></span><a href="#l320"></a>
<span id="l321">        public FtpDirEntry parseLine(String line) {</span><a href="#l321"></a>
<span id="l322">            String name = null;</span><a href="#l322"></a>
<span id="l323">            int i = line.lastIndexOf(&quot;;&quot;);</span><a href="#l323"></a>
<span id="l324">            if (i &gt; 0) {</span><a href="#l324"></a>
<span id="l325">                name = line.substring(i + 1).trim();</span><a href="#l325"></a>
<span id="l326">                line = line.substring(0, i);</span><a href="#l326"></a>
<span id="l327">            } else {</span><a href="#l327"></a>
<span id="l328">                name = line.trim();</span><a href="#l328"></a>
<span id="l329">                line = &quot;&quot;;</span><a href="#l329"></a>
<span id="l330">            }</span><a href="#l330"></a>
<span id="l331">            FtpDirEntry file = new FtpDirEntry(name);</span><a href="#l331"></a>
<span id="l332">            while (!line.isEmpty()) {</span><a href="#l332"></a>
<span id="l333">                String s;</span><a href="#l333"></a>
<span id="l334">                i = line.indexOf(&quot;;&quot;);</span><a href="#l334"></a>
<span id="l335">                if (i &gt; 0) {</span><a href="#l335"></a>
<span id="l336">                    s = line.substring(0, i);</span><a href="#l336"></a>
<span id="l337">                    line = line.substring(i + 1);</span><a href="#l337"></a>
<span id="l338">                } else {</span><a href="#l338"></a>
<span id="l339">                    s = line;</span><a href="#l339"></a>
<span id="l340">                    line = &quot;&quot;;</span><a href="#l340"></a>
<span id="l341">                }</span><a href="#l341"></a>
<span id="l342">                i = s.indexOf(&quot;=&quot;);</span><a href="#l342"></a>
<span id="l343">                if (i &gt; 0) {</span><a href="#l343"></a>
<span id="l344">                    String fact = s.substring(0, i);</span><a href="#l344"></a>
<span id="l345">                    String value = s.substring(i + 1);</span><a href="#l345"></a>
<span id="l346">                    file.addFact(fact, value);</span><a href="#l346"></a>
<span id="l347">                }</span><a href="#l347"></a>
<span id="l348">            }</span><a href="#l348"></a>
<span id="l349">            String s = file.getFact(&quot;Size&quot;);</span><a href="#l349"></a>
<span id="l350">            if (s != null) {</span><a href="#l350"></a>
<span id="l351">                file.setSize(Long.parseLong(s));</span><a href="#l351"></a>
<span id="l352">            }</span><a href="#l352"></a>
<span id="l353">            s = file.getFact(&quot;Modify&quot;);</span><a href="#l353"></a>
<span id="l354">            if (s != null) {</span><a href="#l354"></a>
<span id="l355">                Date d = null;</span><a href="#l355"></a>
<span id="l356">                try {</span><a href="#l356"></a>
<span id="l357">                    d = df.parse(s);</span><a href="#l357"></a>
<span id="l358">                } catch (ParseException ex) {</span><a href="#l358"></a>
<span id="l359">                }</span><a href="#l359"></a>
<span id="l360">                if (d != null) {</span><a href="#l360"></a>
<span id="l361">                    file.setLastModified(d);</span><a href="#l361"></a>
<span id="l362">                }</span><a href="#l362"></a>
<span id="l363">            }</span><a href="#l363"></a>
<span id="l364">            s = file.getFact(&quot;Create&quot;);</span><a href="#l364"></a>
<span id="l365">            if (s != null) {</span><a href="#l365"></a>
<span id="l366">                Date d = null;</span><a href="#l366"></a>
<span id="l367">                try {</span><a href="#l367"></a>
<span id="l368">                    d = df.parse(s);</span><a href="#l368"></a>
<span id="l369">                } catch (ParseException ex) {</span><a href="#l369"></a>
<span id="l370">                }</span><a href="#l370"></a>
<span id="l371">                if (d != null) {</span><a href="#l371"></a>
<span id="l372">                    file.setCreated(d);</span><a href="#l372"></a>
<span id="l373">                }</span><a href="#l373"></a>
<span id="l374">            }</span><a href="#l374"></a>
<span id="l375">            s = file.getFact(&quot;Type&quot;);</span><a href="#l375"></a>
<span id="l376">            if (s != null) {</span><a href="#l376"></a>
<span id="l377">                if (s.equalsIgnoreCase(&quot;file&quot;)) {</span><a href="#l377"></a>
<span id="l378">                    file.setType(FtpDirEntry.Type.FILE);</span><a href="#l378"></a>
<span id="l379">                }</span><a href="#l379"></a>
<span id="l380">                if (s.equalsIgnoreCase(&quot;dir&quot;)) {</span><a href="#l380"></a>
<span id="l381">                    file.setType(FtpDirEntry.Type.DIR);</span><a href="#l381"></a>
<span id="l382">                }</span><a href="#l382"></a>
<span id="l383">                if (s.equalsIgnoreCase(&quot;cdir&quot;)) {</span><a href="#l383"></a>
<span id="l384">                    file.setType(FtpDirEntry.Type.CDIR);</span><a href="#l384"></a>
<span id="l385">                }</span><a href="#l385"></a>
<span id="l386">                if (s.equalsIgnoreCase(&quot;pdir&quot;)) {</span><a href="#l386"></a>
<span id="l387">                    file.setType(FtpDirEntry.Type.PDIR);</span><a href="#l387"></a>
<span id="l388">                }</span><a href="#l388"></a>
<span id="l389">            }</span><a href="#l389"></a>
<span id="l390">            return file;</span><a href="#l390"></a>
<span id="l391">        }</span><a href="#l391"></a>
<span id="l392">    };</span><a href="#l392"></a>
<span id="l393">    private FtpDirParser parser = new DefaultParser();</span><a href="#l393"></a>
<span id="l394">    private FtpDirParser mlsxParser = new MLSxParser();</span><a href="#l394"></a>
<span id="l395">    private static Pattern transPat = null;</span><a href="#l395"></a>
<span id="l396"></span><a href="#l396"></a>
<span id="l397">    private void getTransferSize() {</span><a href="#l397"></a>
<span id="l398">        lastTransSize = -1;</span><a href="#l398"></a>
<span id="l399">        /**</span><a href="#l399"></a>
<span id="l400">         * If it's a start of data transfer response, let's try to extract</span><a href="#l400"></a>
<span id="l401">         * the size from the response string. Usually it looks like that:</span><a href="#l401"></a>
<span id="l402">         *</span><a href="#l402"></a>
<span id="l403">         * 150 Opening BINARY mode data connection for foo (6701 bytes).</span><a href="#l403"></a>
<span id="l404">         */</span><a href="#l404"></a>
<span id="l405">        String response = getLastResponseString();</span><a href="#l405"></a>
<span id="l406">        if (transPat == null) {</span><a href="#l406"></a>
<span id="l407">            transPat = Pattern.compile(&quot;150 Opening .*\\((\\d+) bytes\\).&quot;);</span><a href="#l407"></a>
<span id="l408">        }</span><a href="#l408"></a>
<span id="l409">        Matcher m = transPat.matcher(response);</span><a href="#l409"></a>
<span id="l410">        if (m.find()) {</span><a href="#l410"></a>
<span id="l411">            String s = m.group(1);</span><a href="#l411"></a>
<span id="l412">            lastTransSize = Long.parseLong(s);</span><a href="#l412"></a>
<span id="l413">        }</span><a href="#l413"></a>
<span id="l414">    }</span><a href="#l414"></a>
<span id="l415"></span><a href="#l415"></a>
<span id="l416">    /**</span><a href="#l416"></a>
<span id="l417">     * extract the created file name from the response string:</span><a href="#l417"></a>
<span id="l418">     * 226 Transfer complete (unique file name:toto.txt.1).</span><a href="#l418"></a>
<span id="l419">     * Usually happens when a STOU (store unique) command had been issued.</span><a href="#l419"></a>
<span id="l420">     */</span><a href="#l420"></a>
<span id="l421">    private void getTransferName() {</span><a href="#l421"></a>
<span id="l422">        lastFileName = null;</span><a href="#l422"></a>
<span id="l423">        String response = getLastResponseString();</span><a href="#l423"></a>
<span id="l424">        int i = response.indexOf(&quot;unique file name:&quot;);</span><a href="#l424"></a>
<span id="l425">        int e = response.lastIndexOf(')');</span><a href="#l425"></a>
<span id="l426">        if (i &gt;= 0) {</span><a href="#l426"></a>
<span id="l427">            i += 17; // Length of &quot;unique file name:&quot;</span><a href="#l427"></a>
<span id="l428">            lastFileName = response.substring(i, e);</span><a href="#l428"></a>
<span id="l429">        }</span><a href="#l429"></a>
<span id="l430">    }</span><a href="#l430"></a>
<span id="l431"></span><a href="#l431"></a>
<span id="l432">    /**</span><a href="#l432"></a>
<span id="l433">     * Pulls the response from the server and returns the code as a</span><a href="#l433"></a>
<span id="l434">     * number. Returns -1 on failure.</span><a href="#l434"></a>
<span id="l435">     */</span><a href="#l435"></a>
<span id="l436">    private int readServerResponse() throws IOException {</span><a href="#l436"></a>
<span id="l437">        StringBuffer replyBuf = new StringBuffer(32);</span><a href="#l437"></a>
<span id="l438">        int c;</span><a href="#l438"></a>
<span id="l439">        int continuingCode = -1;</span><a href="#l439"></a>
<span id="l440">        int code;</span><a href="#l440"></a>
<span id="l441">        String response;</span><a href="#l441"></a>
<span id="l442"></span><a href="#l442"></a>
<span id="l443">        serverResponse.setSize(0);</span><a href="#l443"></a>
<span id="l444">        while (true) {</span><a href="#l444"></a>
<span id="l445">            while ((c = in.read()) != -1) {</span><a href="#l445"></a>
<span id="l446">                if (c == '\r') {</span><a href="#l446"></a>
<span id="l447">                    if ((c = in.read()) != '\n') {</span><a href="#l447"></a>
<span id="l448">                        replyBuf.append('\r');</span><a href="#l448"></a>
<span id="l449">                    }</span><a href="#l449"></a>
<span id="l450">                }</span><a href="#l450"></a>
<span id="l451">                replyBuf.append((char) c);</span><a href="#l451"></a>
<span id="l452">                if (c == '\n') {</span><a href="#l452"></a>
<span id="l453">                    break;</span><a href="#l453"></a>
<span id="l454">                }</span><a href="#l454"></a>
<span id="l455">            }</span><a href="#l455"></a>
<span id="l456">            response = replyBuf.toString();</span><a href="#l456"></a>
<span id="l457">            replyBuf.setLength(0);</span><a href="#l457"></a>
<span id="l458">            if (logger.isLoggable(PlatformLogger.Level.FINEST)) {</span><a href="#l458"></a>
<span id="l459">                logger.finest(&quot;Server [&quot; + serverAddr + &quot;] --&gt; &quot; + response);</span><a href="#l459"></a>
<span id="l460">            }</span><a href="#l460"></a>
<span id="l461"></span><a href="#l461"></a>
<span id="l462">            if (response.length() == 0) {</span><a href="#l462"></a>
<span id="l463">                code = -1;</span><a href="#l463"></a>
<span id="l464">            } else {</span><a href="#l464"></a>
<span id="l465">                try {</span><a href="#l465"></a>
<span id="l466">                    code = Integer.parseInt(response.substring(0, 3));</span><a href="#l466"></a>
<span id="l467">                } catch (NumberFormatException e) {</span><a href="#l467"></a>
<span id="l468">                    code = -1;</span><a href="#l468"></a>
<span id="l469">                } catch (StringIndexOutOfBoundsException e) {</span><a href="#l469"></a>
<span id="l470">                    /* this line doesn't contain a response code, so</span><a href="#l470"></a>
<span id="l471">                    we just completely ignore it */</span><a href="#l471"></a>
<span id="l472">                    continue;</span><a href="#l472"></a>
<span id="l473">                }</span><a href="#l473"></a>
<span id="l474">            }</span><a href="#l474"></a>
<span id="l475">            serverResponse.addElement(response);</span><a href="#l475"></a>
<span id="l476">            if (continuingCode != -1) {</span><a href="#l476"></a>
<span id="l477">                /* we've seen a ###- sequence */</span><a href="#l477"></a>
<span id="l478">                if (code != continuingCode ||</span><a href="#l478"></a>
<span id="l479">                        (response.length() &gt;= 4 &amp;&amp; response.charAt(3) == '-')) {</span><a href="#l479"></a>
<span id="l480">                    continue;</span><a href="#l480"></a>
<span id="l481">                } else {</span><a href="#l481"></a>
<span id="l482">                    /* seen the end of code sequence */</span><a href="#l482"></a>
<span id="l483">                    continuingCode = -1;</span><a href="#l483"></a>
<span id="l484">                    break;</span><a href="#l484"></a>
<span id="l485">                }</span><a href="#l485"></a>
<span id="l486">            } else if (response.length() &gt;= 4 &amp;&amp; response.charAt(3) == '-') {</span><a href="#l486"></a>
<span id="l487">                continuingCode = code;</span><a href="#l487"></a>
<span id="l488">                continue;</span><a href="#l488"></a>
<span id="l489">            } else {</span><a href="#l489"></a>
<span id="l490">                break;</span><a href="#l490"></a>
<span id="l491">            }</span><a href="#l491"></a>
<span id="l492">        }</span><a href="#l492"></a>
<span id="l493"></span><a href="#l493"></a>
<span id="l494">        return code;</span><a href="#l494"></a>
<span id="l495">    }</span><a href="#l495"></a>
<span id="l496"></span><a href="#l496"></a>
<span id="l497">    /** Sends command &lt;i&gt;cmd&lt;/i&gt; to the server. */</span><a href="#l497"></a>
<span id="l498">    private void sendServer(String cmd) {</span><a href="#l498"></a>
<span id="l499">        out.print(cmd);</span><a href="#l499"></a>
<span id="l500">        if (logger.isLoggable(PlatformLogger.Level.FINEST)) {</span><a href="#l500"></a>
<span id="l501">            logger.finest(&quot;Server [&quot; + serverAddr + &quot;] &lt;-- &quot; + cmd);</span><a href="#l501"></a>
<span id="l502">        }</span><a href="#l502"></a>
<span id="l503">    }</span><a href="#l503"></a>
<span id="l504"></span><a href="#l504"></a>
<span id="l505">    /** converts the server response into a string. */</span><a href="#l505"></a>
<span id="l506">    private String getResponseString() {</span><a href="#l506"></a>
<span id="l507">        return serverResponse.elementAt(0);</span><a href="#l507"></a>
<span id="l508">    }</span><a href="#l508"></a>
<span id="l509"></span><a href="#l509"></a>
<span id="l510">    /** Returns all server response strings. */</span><a href="#l510"></a>
<span id="l511">    private Vector&lt;String&gt; getResponseStrings() {</span><a href="#l511"></a>
<span id="l512">        return serverResponse;</span><a href="#l512"></a>
<span id="l513">    }</span><a href="#l513"></a>
<span id="l514"></span><a href="#l514"></a>
<span id="l515">    /**</span><a href="#l515"></a>
<span id="l516">     * Read the reply from the FTP server.</span><a href="#l516"></a>
<span id="l517">     *</span><a href="#l517"></a>
<span id="l518">     * @return &lt;code&gt;true&lt;/code&gt; if the command was successful</span><a href="#l518"></a>
<span id="l519">     * @throws IOException if an error occurred</span><a href="#l519"></a>
<span id="l520">     */</span><a href="#l520"></a>
<span id="l521">    private boolean readReply() throws IOException {</span><a href="#l521"></a>
<span id="l522">        lastReplyCode = FtpReplyCode.find(readServerResponse());</span><a href="#l522"></a>
<span id="l523"></span><a href="#l523"></a>
<span id="l524">        if (lastReplyCode.isPositivePreliminary()) {</span><a href="#l524"></a>
<span id="l525">            replyPending = true;</span><a href="#l525"></a>
<span id="l526">            return true;</span><a href="#l526"></a>
<span id="l527">        }</span><a href="#l527"></a>
<span id="l528">        if (lastReplyCode.isPositiveCompletion() || lastReplyCode.isPositiveIntermediate()) {</span><a href="#l528"></a>
<span id="l529">            if (lastReplyCode == FtpReplyCode.CLOSING_DATA_CONNECTION) {</span><a href="#l529"></a>
<span id="l530">                getTransferName();</span><a href="#l530"></a>
<span id="l531">            }</span><a href="#l531"></a>
<span id="l532">            return true;</span><a href="#l532"></a>
<span id="l533">        }</span><a href="#l533"></a>
<span id="l534">        return false;</span><a href="#l534"></a>
<span id="l535">    }</span><a href="#l535"></a>
<span id="l536"></span><a href="#l536"></a>
<span id="l537">    /**</span><a href="#l537"></a>
<span id="l538">     * Sends a command to the FTP server and returns the error code</span><a href="#l538"></a>
<span id="l539">     * (which can be a &quot;success&quot;) sent by the server.</span><a href="#l539"></a>
<span id="l540">     *</span><a href="#l540"></a>
<span id="l541">     * @param cmd</span><a href="#l541"></a>
<span id="l542">     * @return &lt;code&gt;true&lt;/code&gt; if the command was successful</span><a href="#l542"></a>
<span id="l543">     * @throws IOException</span><a href="#l543"></a>
<span id="l544">     */</span><a href="#l544"></a>
<span id="l545">    private boolean issueCommand(String cmd) throws IOException,</span><a href="#l545"></a>
<span id="l546">            sun.net.ftp.FtpProtocolException {</span><a href="#l546"></a>
<span id="l547">        if (!isConnected()) {</span><a href="#l547"></a>
<span id="l548">            throw new IllegalStateException(&quot;Not connected&quot;);</span><a href="#l548"></a>
<span id="l549">        }</span><a href="#l549"></a>
<span id="l550">        if (replyPending) {</span><a href="#l550"></a>
<span id="l551">            try {</span><a href="#l551"></a>
<span id="l552">                completePending();</span><a href="#l552"></a>
<span id="l553">            } catch (sun.net.ftp.FtpProtocolException e) {</span><a href="#l553"></a>
<span id="l554">                // ignore...</span><a href="#l554"></a>
<span id="l555">            }</span><a href="#l555"></a>
<span id="l556">        }</span><a href="#l556"></a>
<span id="l557">        if (cmd.indexOf('\n') != -1) {</span><a href="#l557"></a>
<span id="l558">            sun.net.ftp.FtpProtocolException ex</span><a href="#l558"></a>
<span id="l559">                    = new sun.net.ftp.FtpProtocolException(&quot;Illegal FTP command&quot;);</span><a href="#l559"></a>
<span id="l560">            ex.initCause(new IllegalArgumentException(&quot;Illegal carriage return&quot;));</span><a href="#l560"></a>
<span id="l561">            throw ex;</span><a href="#l561"></a>
<span id="l562">        }</span><a href="#l562"></a>
<span id="l563">        sendServer(cmd + &quot;\r\n&quot;);</span><a href="#l563"></a>
<span id="l564">        return readReply();</span><a href="#l564"></a>
<span id="l565">    }</span><a href="#l565"></a>
<span id="l566"></span><a href="#l566"></a>
<span id="l567">    /**</span><a href="#l567"></a>
<span id="l568">     * Send a command to the FTP server and check for success.</span><a href="#l568"></a>
<span id="l569">     *</span><a href="#l569"></a>
<span id="l570">     * @param cmd String containing the command</span><a href="#l570"></a>
<span id="l571">     *</span><a href="#l571"></a>
<span id="l572">     * @throws FtpProtocolException if an error occurred</span><a href="#l572"></a>
<span id="l573">     */</span><a href="#l573"></a>
<span id="l574">    private void issueCommandCheck(String cmd) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l574"></a>
<span id="l575">        if (!issueCommand(cmd)) {</span><a href="#l575"></a>
<span id="l576">            throw new sun.net.ftp.FtpProtocolException(cmd + &quot;:&quot; + getResponseString(), getLastReplyCode());</span><a href="#l576"></a>
<span id="l577">        }</span><a href="#l577"></a>
<span id="l578">    }</span><a href="#l578"></a>
<span id="l579">    private static Pattern epsvPat = null;</span><a href="#l579"></a>
<span id="l580">    private static Pattern pasvPat = null;</span><a href="#l580"></a>
<span id="l581"></span><a href="#l581"></a>
<span id="l582">    /**</span><a href="#l582"></a>
<span id="l583">     * Opens a &quot;PASSIVE&quot; connection with the server and returns the connected</span><a href="#l583"></a>
<span id="l584">     * &lt;code&gt;Socket&lt;/code&gt;.</span><a href="#l584"></a>
<span id="l585">     *</span><a href="#l585"></a>
<span id="l586">     * @return the connected &lt;code&gt;Socket&lt;/code&gt;</span><a href="#l586"></a>
<span id="l587">     * @throws IOException if the connection was unsuccessful.</span><a href="#l587"></a>
<span id="l588">     */</span><a href="#l588"></a>
<span id="l589">    private Socket openPassiveDataConnection(String cmd) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l589"></a>
<span id="l590">        String serverAnswer;</span><a href="#l590"></a>
<span id="l591">        int port;</span><a href="#l591"></a>
<span id="l592">        InetSocketAddress dest = null;</span><a href="#l592"></a>
<span id="l593"></span><a href="#l593"></a>
<span id="l594">        /**</span><a href="#l594"></a>
<span id="l595">         * Here is the idea:</span><a href="#l595"></a>
<span id="l596">         *</span><a href="#l596"></a>
<span id="l597">         * - First we want to try the new (and IPv6 compatible) EPSV command</span><a href="#l597"></a>
<span id="l598">         *   But since we want to be nice with NAT software, we'll issue the</span><a href="#l598"></a>
<span id="l599">         *   EPSV ALL command first.</span><a href="#l599"></a>
<span id="l600">         *   EPSV is documented in RFC2428</span><a href="#l600"></a>
<span id="l601">         * - If EPSV fails, then we fall back to the older, yet ok, PASV</span><a href="#l601"></a>
<span id="l602">         * - If PASV fails as well, then we throw an exception and the calling</span><a href="#l602"></a>
<span id="l603">         *   method will have to try the EPRT or PORT command</span><a href="#l603"></a>
<span id="l604">         */</span><a href="#l604"></a>
<span id="l605">        if (issueCommand(&quot;EPSV ALL&quot;)) {</span><a href="#l605"></a>
<span id="l606">            // We can safely use EPSV commands</span><a href="#l606"></a>
<span id="l607">            issueCommandCheck(&quot;EPSV&quot;);</span><a href="#l607"></a>
<span id="l608">            serverAnswer = getResponseString();</span><a href="#l608"></a>
<span id="l609"></span><a href="#l609"></a>
<span id="l610">            // The response string from a EPSV command will contain the port number</span><a href="#l610"></a>
<span id="l611">            // the format will be :</span><a href="#l611"></a>
<span id="l612">            //  229 Entering Extended PASSIVE Mode (|||58210|)</span><a href="#l612"></a>
<span id="l613">            //</span><a href="#l613"></a>
<span id="l614">            // So we'll use the regular expresions package to parse the output.</span><a href="#l614"></a>
<span id="l615"></span><a href="#l615"></a>
<span id="l616">            if (epsvPat == null) {</span><a href="#l616"></a>
<span id="l617">                epsvPat = Pattern.compile(&quot;^229 .* \\(\\|\\|\\|(\\d+)\\|\\)&quot;);</span><a href="#l617"></a>
<span id="l618">            }</span><a href="#l618"></a>
<span id="l619">            Matcher m = epsvPat.matcher(serverAnswer);</span><a href="#l619"></a>
<span id="l620">            if (!m.find()) {</span><a href="#l620"></a>
<span id="l621">                throw new sun.net.ftp.FtpProtocolException(&quot;EPSV failed : &quot; + serverAnswer);</span><a href="#l621"></a>
<span id="l622">            }</span><a href="#l622"></a>
<span id="l623">            // Yay! Let's extract the port number</span><a href="#l623"></a>
<span id="l624">            String s = m.group(1);</span><a href="#l624"></a>
<span id="l625">            port = Integer.parseInt(s);</span><a href="#l625"></a>
<span id="l626">            InetAddress add = server.getInetAddress();</span><a href="#l626"></a>
<span id="l627">            if (add != null) {</span><a href="#l627"></a>
<span id="l628">                dest = new InetSocketAddress(add, port);</span><a href="#l628"></a>
<span id="l629">            } else {</span><a href="#l629"></a>
<span id="l630">                // This means we used an Unresolved address to connect in</span><a href="#l630"></a>
<span id="l631">                // the first place. Most likely because the proxy is doing</span><a href="#l631"></a>
<span id="l632">                // the name resolution for us, so let's keep using unresolved</span><a href="#l632"></a>
<span id="l633">                // address.</span><a href="#l633"></a>
<span id="l634">                dest = InetSocketAddress.createUnresolved(serverAddr.getHostName(), port);</span><a href="#l634"></a>
<span id="l635">            }</span><a href="#l635"></a>
<span id="l636">        } else {</span><a href="#l636"></a>
<span id="l637">            // EPSV ALL failed, so Let's try the regular PASV cmd</span><a href="#l637"></a>
<span id="l638">            issueCommandCheck(&quot;PASV&quot;);</span><a href="#l638"></a>
<span id="l639">            serverAnswer = getResponseString();</span><a href="#l639"></a>
<span id="l640"></span><a href="#l640"></a>
<span id="l641">            // Let's parse the response String to get the IP &amp; port to connect</span><a href="#l641"></a>
<span id="l642">            // to. The String should be in the following format :</span><a href="#l642"></a>
<span id="l643">            //</span><a href="#l643"></a>
<span id="l644">            // 227 Entering PASSIVE Mode (A1,A2,A3,A4,p1,p2)</span><a href="#l644"></a>
<span id="l645">            //</span><a href="#l645"></a>
<span id="l646">            // Note that the two parenthesis are optional</span><a href="#l646"></a>
<span id="l647">            //</span><a href="#l647"></a>
<span id="l648">            // The IP address is A1.A2.A3.A4 and the port is p1 * 256 + p2</span><a href="#l648"></a>
<span id="l649">            //</span><a href="#l649"></a>
<span id="l650">            // The regular expression is a bit more complex this time, because</span><a href="#l650"></a>
<span id="l651">            // the parenthesis are optionals and we have to use 3 groups.</span><a href="#l651"></a>
<span id="l652">            if (pasvPat == null) {</span><a href="#l652"></a>
<span id="l653">                pasvPat = Pattern.compile(&quot;227 .* \\(?(\\d{1,3},\\d{1,3},\\d{1,3},\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)?&quot;);</span><a href="#l653"></a>
<span id="l654">            }</span><a href="#l654"></a>
<span id="l655">            Matcher m = pasvPat.matcher(serverAnswer);</span><a href="#l655"></a>
<span id="l656">            if (!m.find()) {</span><a href="#l656"></a>
<span id="l657">                throw new sun.net.ftp.FtpProtocolException(&quot;PASV failed : &quot; + serverAnswer);</span><a href="#l657"></a>
<span id="l658">            }</span><a href="#l658"></a>
<span id="l659">            // Get port number out of group 2 &amp; 3</span><a href="#l659"></a>
<span id="l660">            port = Integer.parseInt(m.group(3)) + (Integer.parseInt(m.group(2)) &lt;&lt; 8);</span><a href="#l660"></a>
<span id="l661">            // IP address is simple</span><a href="#l661"></a>
<span id="l662">            String s = m.group(1).replace(',', '.');</span><a href="#l662"></a>
<span id="l663">            if (!IPAddressUtil.isIPv4LiteralAddress(s))</span><a href="#l663"></a>
<span id="l664">                throw new FtpProtocolException(&quot;PASV failed : &quot;  + serverAnswer);</span><a href="#l664"></a>
<span id="l665">            if (acceptPasvAddressVal) {</span><a href="#l665"></a>
<span id="l666">                dest = new InetSocketAddress(s, port);</span><a href="#l666"></a>
<span id="l667">            } else {</span><a href="#l667"></a>
<span id="l668">                dest = validatePasvAddress(port, s, server.getInetAddress());</span><a href="#l668"></a>
<span id="l669">            }</span><a href="#l669"></a>
<span id="l670">        }</span><a href="#l670"></a>
<span id="l671"></span><a href="#l671"></a>
<span id="l672">        // Got everything, let's open the socket!</span><a href="#l672"></a>
<span id="l673">        Socket s;</span><a href="#l673"></a>
<span id="l674">        if (proxy != null) {</span><a href="#l674"></a>
<span id="l675">            if (proxy.type() == Proxy.Type.SOCKS) {</span><a href="#l675"></a>
<span id="l676">                s = AccessController.doPrivileged(</span><a href="#l676"></a>
<span id="l677">                        new PrivilegedAction&lt;Socket&gt;() {</span><a href="#l677"></a>
<span id="l678"></span><a href="#l678"></a>
<span id="l679">                            public Socket run() {</span><a href="#l679"></a>
<span id="l680">                                return new Socket(proxy);</span><a href="#l680"></a>
<span id="l681">                            }</span><a href="#l681"></a>
<span id="l682">                        });</span><a href="#l682"></a>
<span id="l683">            } else {</span><a href="#l683"></a>
<span id="l684">                s = new Socket(Proxy.NO_PROXY);</span><a href="#l684"></a>
<span id="l685">            }</span><a href="#l685"></a>
<span id="l686">        } else {</span><a href="#l686"></a>
<span id="l687">            s = new Socket();</span><a href="#l687"></a>
<span id="l688">        }</span><a href="#l688"></a>
<span id="l689"></span><a href="#l689"></a>
<span id="l690">        InetAddress serverAddress = AccessController.doPrivileged(</span><a href="#l690"></a>
<span id="l691">                new PrivilegedAction&lt;InetAddress&gt;() {</span><a href="#l691"></a>
<span id="l692">                    @Override</span><a href="#l692"></a>
<span id="l693">                    public InetAddress run() {</span><a href="#l693"></a>
<span id="l694">                        return server.getLocalAddress();</span><a href="#l694"></a>
<span id="l695">                    }</span><a href="#l695"></a>
<span id="l696">                });</span><a href="#l696"></a>
<span id="l697"></span><a href="#l697"></a>
<span id="l698">        // Bind the socket to the same address as the control channel. This</span><a href="#l698"></a>
<span id="l699">        // is needed in case of multi-homed systems.</span><a href="#l699"></a>
<span id="l700">        s.bind(new InetSocketAddress(serverAddress, 0));</span><a href="#l700"></a>
<span id="l701">        if (connectTimeout &gt;= 0) {</span><a href="#l701"></a>
<span id="l702">            s.connect(dest, connectTimeout);</span><a href="#l702"></a>
<span id="l703">        } else {</span><a href="#l703"></a>
<span id="l704">            if (defaultConnectTimeout &gt; 0) {</span><a href="#l704"></a>
<span id="l705">                s.connect(dest, defaultConnectTimeout);</span><a href="#l705"></a>
<span id="l706">            } else {</span><a href="#l706"></a>
<span id="l707">                s.connect(dest);</span><a href="#l707"></a>
<span id="l708">            }</span><a href="#l708"></a>
<span id="l709">        }</span><a href="#l709"></a>
<span id="l710">        if (readTimeout &gt;= 0) {</span><a href="#l710"></a>
<span id="l711">            s.setSoTimeout(readTimeout);</span><a href="#l711"></a>
<span id="l712">        } else if (defaultSoTimeout &gt; 0) {</span><a href="#l712"></a>
<span id="l713">            s.setSoTimeout(defaultSoTimeout);</span><a href="#l713"></a>
<span id="l714">        }</span><a href="#l714"></a>
<span id="l715">        if (useCrypto) {</span><a href="#l715"></a>
<span id="l716">            try {</span><a href="#l716"></a>
<span id="l717">                s = sslFact.createSocket(s, dest.getHostName(), dest.getPort(), true);</span><a href="#l717"></a>
<span id="l718">            } catch (Exception e) {</span><a href="#l718"></a>
<span id="l719">                throw new sun.net.ftp.FtpProtocolException(&quot;Can't open secure data channel: &quot; + e);</span><a href="#l719"></a>
<span id="l720">            }</span><a href="#l720"></a>
<span id="l721">        }</span><a href="#l721"></a>
<span id="l722">        if (!issueCommand(cmd)) {</span><a href="#l722"></a>
<span id="l723">            s.close();</span><a href="#l723"></a>
<span id="l724">            if (getLastReplyCode() == FtpReplyCode.FILE_UNAVAILABLE) {</span><a href="#l724"></a>
<span id="l725">                // Ensure backward compatibility</span><a href="#l725"></a>
<span id="l726">                throw new FileNotFoundException(cmd);</span><a href="#l726"></a>
<span id="l727">            }</span><a href="#l727"></a>
<span id="l728">            throw new sun.net.ftp.FtpProtocolException(cmd + &quot;:&quot; + getResponseString(), getLastReplyCode());</span><a href="#l728"></a>
<span id="l729">        }</span><a href="#l729"></a>
<span id="l730">        return s;</span><a href="#l730"></a>
<span id="l731">    }</span><a href="#l731"></a>
<span id="l732"></span><a href="#l732"></a>
<span id="l733">    static final String ERROR_MSG = &quot;Address should be the same as originating server&quot;;</span><a href="#l733"></a>
<span id="l734"></span><a href="#l734"></a>
<span id="l735">    /**</span><a href="#l735"></a>
<span id="l736">     * Returns an InetSocketAddress, based on value of acceptPasvAddressVal</span><a href="#l736"></a>
<span id="l737">     * and other conditions such as the server address returned by pasv</span><a href="#l737"></a>
<span id="l738">     * is not a hostname, is a socks proxy, or the loopback. An exception</span><a href="#l738"></a>
<span id="l739">     * is thrown if none of the valid conditions are met.</span><a href="#l739"></a>
<span id="l740">     */</span><a href="#l740"></a>
<span id="l741">    private InetSocketAddress validatePasvAddress(int port, String s, InetAddress address)</span><a href="#l741"></a>
<span id="l742">        throws FtpProtocolException</span><a href="#l742"></a>
<span id="l743">    {</span><a href="#l743"></a>
<span id="l744">        if (address == null) {</span><a href="#l744"></a>
<span id="l745">            return InetSocketAddress.createUnresolved(serverAddr.getHostName(), port);</span><a href="#l745"></a>
<span id="l746">        }</span><a href="#l746"></a>
<span id="l747">        String serverAddress = address.getHostAddress();</span><a href="#l747"></a>
<span id="l748">        if (serverAddress.equals(s)) {</span><a href="#l748"></a>
<span id="l749">            return new InetSocketAddress(s, port);</span><a href="#l749"></a>
<span id="l750">        } else if (address.isLoopbackAddress() &amp;&amp; s.startsWith(&quot;127.&quot;)) { // can be 127.0</span><a href="#l750"></a>
<span id="l751">            return new InetSocketAddress(s, port);</span><a href="#l751"></a>
<span id="l752">        } else if (address.isLoopbackAddress()) {</span><a href="#l752"></a>
<span id="l753">            if (privilegedLocalHost().getHostAddress().equals(s)) {</span><a href="#l753"></a>
<span id="l754">                return new InetSocketAddress(s, port);</span><a href="#l754"></a>
<span id="l755">            } else {</span><a href="#l755"></a>
<span id="l756">                throw new FtpProtocolException(ERROR_MSG);</span><a href="#l756"></a>
<span id="l757">            }</span><a href="#l757"></a>
<span id="l758">        } else if (s.startsWith(&quot;127.&quot;)) {</span><a href="#l758"></a>
<span id="l759">            if (privilegedLocalHost().equals(address)) {</span><a href="#l759"></a>
<span id="l760">                return new InetSocketAddress(s, port);</span><a href="#l760"></a>
<span id="l761">            } else {</span><a href="#l761"></a>
<span id="l762">                throw new FtpProtocolException(ERROR_MSG);</span><a href="#l762"></a>
<span id="l763">            }</span><a href="#l763"></a>
<span id="l764">        }</span><a href="#l764"></a>
<span id="l765">        String hostName = address.getHostName();</span><a href="#l765"></a>
<span id="l766">        if (!(IPAddressUtil.isIPv4LiteralAddress(hostName) || IPAddressUtil.isIPv6LiteralAddress(hostName))) {</span><a href="#l766"></a>
<span id="l767">            InetAddress[] names = privilegedGetAllByName(hostName);</span><a href="#l767"></a>
<span id="l768">            String resAddress = Arrays</span><a href="#l768"></a>
<span id="l769">                .stream(names)</span><a href="#l769"></a>
<span id="l770">                .map(InetAddress::getHostAddress)</span><a href="#l770"></a>
<span id="l771">                .filter(s::equalsIgnoreCase)</span><a href="#l771"></a>
<span id="l772">                .findFirst()</span><a href="#l772"></a>
<span id="l773">                .orElse(null);</span><a href="#l773"></a>
<span id="l774">            if (resAddress != null) {</span><a href="#l774"></a>
<span id="l775">                return new InetSocketAddress(s, port);</span><a href="#l775"></a>
<span id="l776">            }</span><a href="#l776"></a>
<span id="l777">        }</span><a href="#l777"></a>
<span id="l778">        throw new FtpProtocolException(ERROR_MSG);</span><a href="#l778"></a>
<span id="l779">    }</span><a href="#l779"></a>
<span id="l780"></span><a href="#l780"></a>
<span id="l781">    private static InetAddress privilegedLocalHost() throws FtpProtocolException {</span><a href="#l781"></a>
<span id="l782">        PrivilegedExceptionAction&lt;InetAddress&gt; action = InetAddress::getLocalHost;</span><a href="#l782"></a>
<span id="l783">        try {</span><a href="#l783"></a>
<span id="l784">            return AccessController.doPrivileged(action);</span><a href="#l784"></a>
<span id="l785">        } catch (Exception e) {</span><a href="#l785"></a>
<span id="l786">            FtpProtocolException ftpEx = new FtpProtocolException(ERROR_MSG);</span><a href="#l786"></a>
<span id="l787">            ftpEx.initCause(e);</span><a href="#l787"></a>
<span id="l788">            throw ftpEx;</span><a href="#l788"></a>
<span id="l789">        }</span><a href="#l789"></a>
<span id="l790">    }</span><a href="#l790"></a>
<span id="l791"></span><a href="#l791"></a>
<span id="l792">    private static InetAddress[] privilegedGetAllByName(String hostName) throws FtpProtocolException {</span><a href="#l792"></a>
<span id="l793">        PrivilegedExceptionAction&lt;InetAddress[]&gt; pAction = () -&gt; InetAddress.getAllByName(hostName);</span><a href="#l793"></a>
<span id="l794">        try {</span><a href="#l794"></a>
<span id="l795">            return AccessController.doPrivileged(pAction);</span><a href="#l795"></a>
<span id="l796">        } catch (Exception e) {</span><a href="#l796"></a>
<span id="l797">            FtpProtocolException ftpEx = new FtpProtocolException(ERROR_MSG);</span><a href="#l797"></a>
<span id="l798">            ftpEx.initCause(e);</span><a href="#l798"></a>
<span id="l799">            throw ftpEx;</span><a href="#l799"></a>
<span id="l800">        }</span><a href="#l800"></a>
<span id="l801">    }</span><a href="#l801"></a>
<span id="l802"></span><a href="#l802"></a>
<span id="l803">    /**</span><a href="#l803"></a>
<span id="l804">     * Opens a data connection with the server according to the set mode</span><a href="#l804"></a>
<span id="l805">     * (ACTIVE or PASSIVE) then send the command passed as an argument.</span><a href="#l805"></a>
<span id="l806">     *</span><a href="#l806"></a>
<span id="l807">     * @param cmd the &lt;code&gt;String&lt;/code&gt; containing the command to execute</span><a href="#l807"></a>
<span id="l808">     * @return the connected &lt;code&gt;Socket&lt;/code&gt;</span><a href="#l808"></a>
<span id="l809">     * @throws IOException if the connection or command failed</span><a href="#l809"></a>
<span id="l810">     */</span><a href="#l810"></a>
<span id="l811">    private Socket openDataConnection(String cmd) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l811"></a>
<span id="l812">        Socket clientSocket;</span><a href="#l812"></a>
<span id="l813">        if (passiveMode) {</span><a href="#l813"></a>
<span id="l814">            try {</span><a href="#l814"></a>
<span id="l815">                return openPassiveDataConnection(cmd);</span><a href="#l815"></a>
<span id="l816">            } catch (sun.net.ftp.FtpProtocolException e) {</span><a href="#l816"></a>
<span id="l817">                // If Passive mode failed, fall back on PORT</span><a href="#l817"></a>
<span id="l818">                // Otherwise throw exception</span><a href="#l818"></a>
<span id="l819">                String errmsg = e.getMessage();</span><a href="#l819"></a>
<span id="l820">                if (!errmsg.startsWith(&quot;PASV&quot;) &amp;&amp; !errmsg.startsWith(&quot;EPSV&quot;)) {</span><a href="#l820"></a>
<span id="l821">                    throw e;</span><a href="#l821"></a>
<span id="l822">                }</span><a href="#l822"></a>
<span id="l823">            }</span><a href="#l823"></a>
<span id="l824">        }</span><a href="#l824"></a>
<span id="l825">        ServerSocket portSocket;</span><a href="#l825"></a>
<span id="l826">        InetAddress myAddress;</span><a href="#l826"></a>
<span id="l827">        String portCmd;</span><a href="#l827"></a>
<span id="l828"></span><a href="#l828"></a>
<span id="l829">        if (proxy != null &amp;&amp; proxy.type() == Proxy.Type.SOCKS) {</span><a href="#l829"></a>
<span id="l830">            // We're behind a firewall and the passive mode fail,</span><a href="#l830"></a>
<span id="l831">            // since we can't accept a connection through SOCKS (yet)</span><a href="#l831"></a>
<span id="l832">            // throw an exception</span><a href="#l832"></a>
<span id="l833">            throw new sun.net.ftp.FtpProtocolException(&quot;Passive mode failed&quot;);</span><a href="#l833"></a>
<span id="l834">        }</span><a href="#l834"></a>
<span id="l835">        // Bind the ServerSocket to the same address as the control channel</span><a href="#l835"></a>
<span id="l836">        // This is needed for multi-homed systems</span><a href="#l836"></a>
<span id="l837">        portSocket = new ServerSocket(0, 1, server.getLocalAddress());</span><a href="#l837"></a>
<span id="l838">        try {</span><a href="#l838"></a>
<span id="l839">            myAddress = portSocket.getInetAddress();</span><a href="#l839"></a>
<span id="l840">            if (myAddress.isAnyLocalAddress()) {</span><a href="#l840"></a>
<span id="l841">                myAddress = server.getLocalAddress();</span><a href="#l841"></a>
<span id="l842">            }</span><a href="#l842"></a>
<span id="l843">            // Let's try the new, IPv6 compatible EPRT command</span><a href="#l843"></a>
<span id="l844">            // See RFC2428 for specifics</span><a href="#l844"></a>
<span id="l845">            // Some FTP servers (like the one on Solaris) are bugged, they</span><a href="#l845"></a>
<span id="l846">            // will accept the EPRT command but then, the subsequent command</span><a href="#l846"></a>
<span id="l847">            // (e.g. RETR) will fail, so we have to check BOTH results (the</span><a href="#l847"></a>
<span id="l848">            // EPRT cmd then the actual command) to decide whether we should</span><a href="#l848"></a>
<span id="l849">            // fall back on the older PORT command.</span><a href="#l849"></a>
<span id="l850">            portCmd = &quot;EPRT |&quot; + ((myAddress instanceof Inet6Address) ? &quot;2&quot; : &quot;1&quot;) + &quot;|&quot; +</span><a href="#l850"></a>
<span id="l851">                    myAddress.getHostAddress() + &quot;|&quot; + portSocket.getLocalPort() + &quot;|&quot;;</span><a href="#l851"></a>
<span id="l852">            if (!issueCommand(portCmd) || !issueCommand(cmd)) {</span><a href="#l852"></a>
<span id="l853">                // The EPRT command failed, let's fall back to good old PORT</span><a href="#l853"></a>
<span id="l854">                portCmd = &quot;PORT &quot;;</span><a href="#l854"></a>
<span id="l855">                byte[] addr = myAddress.getAddress();</span><a href="#l855"></a>
<span id="l856"></span><a href="#l856"></a>
<span id="l857">                /* append host addr */</span><a href="#l857"></a>
<span id="l858">                for (int i = 0; i &lt; addr.length; i++) {</span><a href="#l858"></a>
<span id="l859">                    portCmd = portCmd + (addr[i] &amp; 0xFF) + &quot;,&quot;;</span><a href="#l859"></a>
<span id="l860">                }</span><a href="#l860"></a>
<span id="l861"></span><a href="#l861"></a>
<span id="l862">                /* append port number */</span><a href="#l862"></a>
<span id="l863">                portCmd = portCmd + ((portSocket.getLocalPort() &gt;&gt;&gt; 8) &amp; 0xff) + &quot;,&quot; + (portSocket.getLocalPort() &amp; 0xff);</span><a href="#l863"></a>
<span id="l864">                issueCommandCheck(portCmd);</span><a href="#l864"></a>
<span id="l865">                issueCommandCheck(cmd);</span><a href="#l865"></a>
<span id="l866">            }</span><a href="#l866"></a>
<span id="l867">            // Either the EPRT or the PORT command was successful</span><a href="#l867"></a>
<span id="l868">            // Let's create the client socket</span><a href="#l868"></a>
<span id="l869">            if (connectTimeout &gt;= 0) {</span><a href="#l869"></a>
<span id="l870">                portSocket.setSoTimeout(connectTimeout);</span><a href="#l870"></a>
<span id="l871">            } else {</span><a href="#l871"></a>
<span id="l872">                if (defaultConnectTimeout &gt; 0) {</span><a href="#l872"></a>
<span id="l873">                    portSocket.setSoTimeout(defaultConnectTimeout);</span><a href="#l873"></a>
<span id="l874">                }</span><a href="#l874"></a>
<span id="l875">            }</span><a href="#l875"></a>
<span id="l876">            clientSocket = portSocket.accept();</span><a href="#l876"></a>
<span id="l877">            if (readTimeout &gt;= 0) {</span><a href="#l877"></a>
<span id="l878">                clientSocket.setSoTimeout(readTimeout);</span><a href="#l878"></a>
<span id="l879">            } else {</span><a href="#l879"></a>
<span id="l880">                if (defaultSoTimeout &gt; 0) {</span><a href="#l880"></a>
<span id="l881">                    clientSocket.setSoTimeout(defaultSoTimeout);</span><a href="#l881"></a>
<span id="l882">                }</span><a href="#l882"></a>
<span id="l883">            }</span><a href="#l883"></a>
<span id="l884">        } finally {</span><a href="#l884"></a>
<span id="l885">            portSocket.close();</span><a href="#l885"></a>
<span id="l886">        }</span><a href="#l886"></a>
<span id="l887">        if (useCrypto) {</span><a href="#l887"></a>
<span id="l888">            try {</span><a href="#l888"></a>
<span id="l889">                clientSocket = sslFact.createSocket(clientSocket, serverAddr.getHostName(), serverAddr.getPort(), true);</span><a href="#l889"></a>
<span id="l890">            } catch (Exception ex) {</span><a href="#l890"></a>
<span id="l891">                throw new IOException(ex.getLocalizedMessage());</span><a href="#l891"></a>
<span id="l892">            }</span><a href="#l892"></a>
<span id="l893">        }</span><a href="#l893"></a>
<span id="l894">        return clientSocket;</span><a href="#l894"></a>
<span id="l895">    }</span><a href="#l895"></a>
<span id="l896"></span><a href="#l896"></a>
<span id="l897">    private InputStream createInputStream(InputStream in) {</span><a href="#l897"></a>
<span id="l898">        if (type == TransferType.ASCII) {</span><a href="#l898"></a>
<span id="l899">            return new sun.net.TelnetInputStream(in, false);</span><a href="#l899"></a>
<span id="l900">        }</span><a href="#l900"></a>
<span id="l901">        return in;</span><a href="#l901"></a>
<span id="l902">    }</span><a href="#l902"></a>
<span id="l903"></span><a href="#l903"></a>
<span id="l904">    private OutputStream createOutputStream(OutputStream out) {</span><a href="#l904"></a>
<span id="l905">        if (type == TransferType.ASCII) {</span><a href="#l905"></a>
<span id="l906">            return new sun.net.TelnetOutputStream(out, false);</span><a href="#l906"></a>
<span id="l907">        }</span><a href="#l907"></a>
<span id="l908">        return out;</span><a href="#l908"></a>
<span id="l909">    }</span><a href="#l909"></a>
<span id="l910"></span><a href="#l910"></a>
<span id="l911">    /**</span><a href="#l911"></a>
<span id="l912">     * Creates an instance of FtpClient. The client is not connected to any</span><a href="#l912"></a>
<span id="l913">     * server yet.</span><a href="#l913"></a>
<span id="l914">     *</span><a href="#l914"></a>
<span id="l915">     */</span><a href="#l915"></a>
<span id="l916">    protected FtpClient() {</span><a href="#l916"></a>
<span id="l917">    }</span><a href="#l917"></a>
<span id="l918"></span><a href="#l918"></a>
<span id="l919">    /**</span><a href="#l919"></a>
<span id="l920">     * Creates an instance of FtpClient. The client is not connected to any</span><a href="#l920"></a>
<span id="l921">     * server yet.</span><a href="#l921"></a>
<span id="l922">     *</span><a href="#l922"></a>
<span id="l923">     */</span><a href="#l923"></a>
<span id="l924">    public static sun.net.ftp.FtpClient create() {</span><a href="#l924"></a>
<span id="l925">        return new FtpClient();</span><a href="#l925"></a>
<span id="l926">    }</span><a href="#l926"></a>
<span id="l927"></span><a href="#l927"></a>
<span id="l928">    /**</span><a href="#l928"></a>
<span id="l929">     * Set the transfer mode to &lt;I&gt;passive&lt;/I&gt;. In that mode, data connections</span><a href="#l929"></a>
<span id="l930">     * are established by having the client connect to the server.</span><a href="#l930"></a>
<span id="l931">     * This is the recommended default mode as it will work best through</span><a href="#l931"></a>
<span id="l932">     * firewalls and NATs.</span><a href="#l932"></a>
<span id="l933">     *</span><a href="#l933"></a>
<span id="l934">     * @return This FtpClient</span><a href="#l934"></a>
<span id="l935">     * @see #setActiveMode()</span><a href="#l935"></a>
<span id="l936">     */</span><a href="#l936"></a>
<span id="l937">    public sun.net.ftp.FtpClient enablePassiveMode(boolean passive) {</span><a href="#l937"></a>
<span id="l938"></span><a href="#l938"></a>
<span id="l939">        // Only passive mode used in JDK. See Bug 8010784.</span><a href="#l939"></a>
<span id="l940">        // passiveMode = passive;</span><a href="#l940"></a>
<span id="l941">        return this;</span><a href="#l941"></a>
<span id="l942">    }</span><a href="#l942"></a>
<span id="l943"></span><a href="#l943"></a>
<span id="l944">    /**</span><a href="#l944"></a>
<span id="l945">     * Gets the current transfer mode.</span><a href="#l945"></a>
<span id="l946">     *</span><a href="#l946"></a>
<span id="l947">     * @return the current &lt;code&gt;FtpTransferMode&lt;/code&gt;</span><a href="#l947"></a>
<span id="l948">     */</span><a href="#l948"></a>
<span id="l949">    public boolean isPassiveModeEnabled() {</span><a href="#l949"></a>
<span id="l950">        return passiveMode;</span><a href="#l950"></a>
<span id="l951">    }</span><a href="#l951"></a>
<span id="l952"></span><a href="#l952"></a>
<span id="l953">    /**</span><a href="#l953"></a>
<span id="l954">     * Sets the timeout value to use when connecting to the server,</span><a href="#l954"></a>
<span id="l955">     *</span><a href="#l955"></a>
<span id="l956">     * @param timeout the timeout value, in milliseconds, to use for the connect</span><a href="#l956"></a>
<span id="l957">     *        operation. A value of zero or less, means use the default timeout.</span><a href="#l957"></a>
<span id="l958">     *</span><a href="#l958"></a>
<span id="l959">     * @return This FtpClient</span><a href="#l959"></a>
<span id="l960">     */</span><a href="#l960"></a>
<span id="l961">    public sun.net.ftp.FtpClient setConnectTimeout(int timeout) {</span><a href="#l961"></a>
<span id="l962">        connectTimeout = timeout;</span><a href="#l962"></a>
<span id="l963">        return this;</span><a href="#l963"></a>
<span id="l964">    }</span><a href="#l964"></a>
<span id="l965"></span><a href="#l965"></a>
<span id="l966">    /**</span><a href="#l966"></a>
<span id="l967">     * Returns the current connection timeout value.</span><a href="#l967"></a>
<span id="l968">     *</span><a href="#l968"></a>
<span id="l969">     * @return the value, in milliseconds, of the current connect timeout.</span><a href="#l969"></a>
<span id="l970">     * @see #setConnectTimeout(int)</span><a href="#l970"></a>
<span id="l971">     */</span><a href="#l971"></a>
<span id="l972">    public int getConnectTimeout() {</span><a href="#l972"></a>
<span id="l973">        return connectTimeout;</span><a href="#l973"></a>
<span id="l974">    }</span><a href="#l974"></a>
<span id="l975"></span><a href="#l975"></a>
<span id="l976">    /**</span><a href="#l976"></a>
<span id="l977">     * Sets the timeout value to use when reading from the server,</span><a href="#l977"></a>
<span id="l978">     *</span><a href="#l978"></a>
<span id="l979">     * @param timeout the timeout value, in milliseconds, to use for the read</span><a href="#l979"></a>
<span id="l980">     *        operation. A value of zero or less, means use the default timeout.</span><a href="#l980"></a>
<span id="l981">     * @return This FtpClient</span><a href="#l981"></a>
<span id="l982">     */</span><a href="#l982"></a>
<span id="l983">    public sun.net.ftp.FtpClient setReadTimeout(int timeout) {</span><a href="#l983"></a>
<span id="l984">        readTimeout = timeout;</span><a href="#l984"></a>
<span id="l985">        return this;</span><a href="#l985"></a>
<span id="l986">    }</span><a href="#l986"></a>
<span id="l987"></span><a href="#l987"></a>
<span id="l988">    /**</span><a href="#l988"></a>
<span id="l989">     * Returns the current read timeout value.</span><a href="#l989"></a>
<span id="l990">     *</span><a href="#l990"></a>
<span id="l991">     * @return the value, in milliseconds, of the current read timeout.</span><a href="#l991"></a>
<span id="l992">     * @see #setReadTimeout(int)</span><a href="#l992"></a>
<span id="l993">     */</span><a href="#l993"></a>
<span id="l994">    public int getReadTimeout() {</span><a href="#l994"></a>
<span id="l995">        return readTimeout;</span><a href="#l995"></a>
<span id="l996">    }</span><a href="#l996"></a>
<span id="l997"></span><a href="#l997"></a>
<span id="l998">    public sun.net.ftp.FtpClient setProxy(Proxy p) {</span><a href="#l998"></a>
<span id="l999">        proxy = p;</span><a href="#l999"></a>
<span id="l1000">        return this;</span><a href="#l1000"></a>
<span id="l1001">    }</span><a href="#l1001"></a>
<span id="l1002"></span><a href="#l1002"></a>
<span id="l1003">    /**</span><a href="#l1003"></a>
<span id="l1004">     * Get the proxy of this FtpClient</span><a href="#l1004"></a>
<span id="l1005">     *</span><a href="#l1005"></a>
<span id="l1006">     * @return the &lt;code&gt;Proxy&lt;/code&gt;, this client is using, or &lt;code&gt;null&lt;/code&gt;</span><a href="#l1006"></a>
<span id="l1007">     *         if none is used.</span><a href="#l1007"></a>
<span id="l1008">     * @see #setProxy(Proxy)</span><a href="#l1008"></a>
<span id="l1009">     */</span><a href="#l1009"></a>
<span id="l1010">    public Proxy getProxy() {</span><a href="#l1010"></a>
<span id="l1011">        return proxy;</span><a href="#l1011"></a>
<span id="l1012">    }</span><a href="#l1012"></a>
<span id="l1013"></span><a href="#l1013"></a>
<span id="l1014">    /**</span><a href="#l1014"></a>
<span id="l1015">     * Connects to the specified destination.</span><a href="#l1015"></a>
<span id="l1016">     *</span><a href="#l1016"></a>
<span id="l1017">     * @param dest the &lt;code&gt;InetSocketAddress&lt;/code&gt; to connect to.</span><a href="#l1017"></a>
<span id="l1018">     * @throws IOException if the connection fails.</span><a href="#l1018"></a>
<span id="l1019">     */</span><a href="#l1019"></a>
<span id="l1020">    private void tryConnect(InetSocketAddress dest, int timeout) throws IOException {</span><a href="#l1020"></a>
<span id="l1021">        if (isConnected()) {</span><a href="#l1021"></a>
<span id="l1022">            disconnect();</span><a href="#l1022"></a>
<span id="l1023">        }</span><a href="#l1023"></a>
<span id="l1024">        server = doConnect(dest, timeout);</span><a href="#l1024"></a>
<span id="l1025">        try {</span><a href="#l1025"></a>
<span id="l1026">            out = new PrintStream(new BufferedOutputStream(server.getOutputStream()),</span><a href="#l1026"></a>
<span id="l1027">                    true, encoding);</span><a href="#l1027"></a>
<span id="l1028">        } catch (UnsupportedEncodingException e) {</span><a href="#l1028"></a>
<span id="l1029">            throw new InternalError(encoding + &quot;encoding not found&quot;, e);</span><a href="#l1029"></a>
<span id="l1030">        }</span><a href="#l1030"></a>
<span id="l1031">        in = new BufferedInputStream(server.getInputStream());</span><a href="#l1031"></a>
<span id="l1032">    }</span><a href="#l1032"></a>
<span id="l1033"></span><a href="#l1033"></a>
<span id="l1034">    private Socket doConnect(InetSocketAddress dest, int timeout) throws IOException {</span><a href="#l1034"></a>
<span id="l1035">        Socket s;</span><a href="#l1035"></a>
<span id="l1036">        if (proxy != null) {</span><a href="#l1036"></a>
<span id="l1037">            if (proxy.type() == Proxy.Type.SOCKS) {</span><a href="#l1037"></a>
<span id="l1038">                s = AccessController.doPrivileged(</span><a href="#l1038"></a>
<span id="l1039">                        new PrivilegedAction&lt;Socket&gt;() {</span><a href="#l1039"></a>
<span id="l1040"></span><a href="#l1040"></a>
<span id="l1041">                            public Socket run() {</span><a href="#l1041"></a>
<span id="l1042">                                return new Socket(proxy);</span><a href="#l1042"></a>
<span id="l1043">                            }</span><a href="#l1043"></a>
<span id="l1044">                        });</span><a href="#l1044"></a>
<span id="l1045">            } else {</span><a href="#l1045"></a>
<span id="l1046">                s = new Socket(Proxy.NO_PROXY);</span><a href="#l1046"></a>
<span id="l1047">            }</span><a href="#l1047"></a>
<span id="l1048">        } else {</span><a href="#l1048"></a>
<span id="l1049">            s = new Socket();</span><a href="#l1049"></a>
<span id="l1050">        }</span><a href="#l1050"></a>
<span id="l1051">        // Instance specific timeouts do have priority, that means</span><a href="#l1051"></a>
<span id="l1052">        // connectTimeout &amp; readTimeout (-1 means not set)</span><a href="#l1052"></a>
<span id="l1053">        // Then global default timeouts</span><a href="#l1053"></a>
<span id="l1054">        // Then no timeout.</span><a href="#l1054"></a>
<span id="l1055">        if (timeout &gt;= 0) {</span><a href="#l1055"></a>
<span id="l1056">            s.connect(dest, timeout);</span><a href="#l1056"></a>
<span id="l1057">        } else {</span><a href="#l1057"></a>
<span id="l1058">            if (connectTimeout &gt;= 0) {</span><a href="#l1058"></a>
<span id="l1059">                s.connect(dest, connectTimeout);</span><a href="#l1059"></a>
<span id="l1060">            } else {</span><a href="#l1060"></a>
<span id="l1061">                if (defaultConnectTimeout &gt; 0) {</span><a href="#l1061"></a>
<span id="l1062">                    s.connect(dest, defaultConnectTimeout);</span><a href="#l1062"></a>
<span id="l1063">                } else {</span><a href="#l1063"></a>
<span id="l1064">                    s.connect(dest);</span><a href="#l1064"></a>
<span id="l1065">                }</span><a href="#l1065"></a>
<span id="l1066">            }</span><a href="#l1066"></a>
<span id="l1067">        }</span><a href="#l1067"></a>
<span id="l1068">        if (readTimeout &gt;= 0) {</span><a href="#l1068"></a>
<span id="l1069">            s.setSoTimeout(readTimeout);</span><a href="#l1069"></a>
<span id="l1070">        } else if (defaultSoTimeout &gt; 0) {</span><a href="#l1070"></a>
<span id="l1071">            s.setSoTimeout(defaultSoTimeout);</span><a href="#l1071"></a>
<span id="l1072">        }</span><a href="#l1072"></a>
<span id="l1073">        return s;</span><a href="#l1073"></a>
<span id="l1074">    }</span><a href="#l1074"></a>
<span id="l1075"></span><a href="#l1075"></a>
<span id="l1076">    private void disconnect() throws IOException {</span><a href="#l1076"></a>
<span id="l1077">        if (isConnected()) {</span><a href="#l1077"></a>
<span id="l1078">            server.close();</span><a href="#l1078"></a>
<span id="l1079">        }</span><a href="#l1079"></a>
<span id="l1080">        server = null;</span><a href="#l1080"></a>
<span id="l1081">        in = null;</span><a href="#l1081"></a>
<span id="l1082">        out = null;</span><a href="#l1082"></a>
<span id="l1083">        lastTransSize = -1;</span><a href="#l1083"></a>
<span id="l1084">        lastFileName = null;</span><a href="#l1084"></a>
<span id="l1085">        restartOffset = 0;</span><a href="#l1085"></a>
<span id="l1086">        welcomeMsg = null;</span><a href="#l1086"></a>
<span id="l1087">        lastReplyCode = null;</span><a href="#l1087"></a>
<span id="l1088">        serverResponse.setSize(0);</span><a href="#l1088"></a>
<span id="l1089">    }</span><a href="#l1089"></a>
<span id="l1090"></span><a href="#l1090"></a>
<span id="l1091">    /**</span><a href="#l1091"></a>
<span id="l1092">     * Tests whether this client is connected or not to a server.</span><a href="#l1092"></a>
<span id="l1093">     *</span><a href="#l1093"></a>
<span id="l1094">     * @return &lt;code&gt;true&lt;/code&gt; if the client is connected.</span><a href="#l1094"></a>
<span id="l1095">     */</span><a href="#l1095"></a>
<span id="l1096">    public boolean isConnected() {</span><a href="#l1096"></a>
<span id="l1097">        return server != null;</span><a href="#l1097"></a>
<span id="l1098">    }</span><a href="#l1098"></a>
<span id="l1099"></span><a href="#l1099"></a>
<span id="l1100">    public SocketAddress getServerAddress() {</span><a href="#l1100"></a>
<span id="l1101">        return server == null ? null : server.getRemoteSocketAddress();</span><a href="#l1101"></a>
<span id="l1102">    }</span><a href="#l1102"></a>
<span id="l1103"></span><a href="#l1103"></a>
<span id="l1104">    public sun.net.ftp.FtpClient connect(SocketAddress dest) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1104"></a>
<span id="l1105">        return connect(dest, -1);</span><a href="#l1105"></a>
<span id="l1106">    }</span><a href="#l1106"></a>
<span id="l1107"></span><a href="#l1107"></a>
<span id="l1108">    /**</span><a href="#l1108"></a>
<span id="l1109">     * Connects the FtpClient to the specified destination.</span><a href="#l1109"></a>
<span id="l1110">     *</span><a href="#l1110"></a>
<span id="l1111">     * @param dest the address of the destination server</span><a href="#l1111"></a>
<span id="l1112">     * @throws IOException if connection failed.</span><a href="#l1112"></a>
<span id="l1113">     */</span><a href="#l1113"></a>
<span id="l1114">    public sun.net.ftp.FtpClient connect(SocketAddress dest, int timeout) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1114"></a>
<span id="l1115">        if (!(dest instanceof InetSocketAddress)) {</span><a href="#l1115"></a>
<span id="l1116">            throw new IllegalArgumentException(&quot;Wrong address type&quot;);</span><a href="#l1116"></a>
<span id="l1117">        }</span><a href="#l1117"></a>
<span id="l1118">        serverAddr = (InetSocketAddress) dest;</span><a href="#l1118"></a>
<span id="l1119">        tryConnect(serverAddr, timeout);</span><a href="#l1119"></a>
<span id="l1120">        if (!readReply()) {</span><a href="#l1120"></a>
<span id="l1121">            throw new sun.net.ftp.FtpProtocolException(&quot;Welcome message: &quot; +</span><a href="#l1121"></a>
<span id="l1122">                    getResponseString(), lastReplyCode);</span><a href="#l1122"></a>
<span id="l1123">        }</span><a href="#l1123"></a>
<span id="l1124">        welcomeMsg = getResponseString().substring(4);</span><a href="#l1124"></a>
<span id="l1125">        return this;</span><a href="#l1125"></a>
<span id="l1126">    }</span><a href="#l1126"></a>
<span id="l1127"></span><a href="#l1127"></a>
<span id="l1128">    private void tryLogin(String user, char[] password) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1128"></a>
<span id="l1129">        issueCommandCheck(&quot;USER &quot; + user);</span><a href="#l1129"></a>
<span id="l1130"></span><a href="#l1130"></a>
<span id="l1131">        /*</span><a href="#l1131"></a>
<span id="l1132">         * Checks for &quot;331 User name okay, need password.&quot; answer</span><a href="#l1132"></a>
<span id="l1133">         */</span><a href="#l1133"></a>
<span id="l1134">        if (lastReplyCode == FtpReplyCode.NEED_PASSWORD) {</span><a href="#l1134"></a>
<span id="l1135">            if ((password != null) &amp;&amp; (password.length &gt; 0)) {</span><a href="#l1135"></a>
<span id="l1136">                issueCommandCheck(&quot;PASS &quot; + String.valueOf(password));</span><a href="#l1136"></a>
<span id="l1137">            }</span><a href="#l1137"></a>
<span id="l1138">        }</span><a href="#l1138"></a>
<span id="l1139">    }</span><a href="#l1139"></a>
<span id="l1140"></span><a href="#l1140"></a>
<span id="l1141">    /**</span><a href="#l1141"></a>
<span id="l1142">     * Attempts to log on the server with the specified user name and password.</span><a href="#l1142"></a>
<span id="l1143">     *</span><a href="#l1143"></a>
<span id="l1144">     * @param user The user name</span><a href="#l1144"></a>
<span id="l1145">     * @param password The password for that user</span><a href="#l1145"></a>
<span id="l1146">     * @return &lt;code&gt;true&lt;/code&gt; if the login was successful.</span><a href="#l1146"></a>
<span id="l1147">     * @throws IOException if an error occurred during the transmission</span><a href="#l1147"></a>
<span id="l1148">     */</span><a href="#l1148"></a>
<span id="l1149">    public sun.net.ftp.FtpClient login(String user, char[] password) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1149"></a>
<span id="l1150">        if (!isConnected()) {</span><a href="#l1150"></a>
<span id="l1151">            throw new sun.net.ftp.FtpProtocolException(&quot;Not connected yet&quot;, FtpReplyCode.BAD_SEQUENCE);</span><a href="#l1151"></a>
<span id="l1152">        }</span><a href="#l1152"></a>
<span id="l1153">        if (user == null || user.length() == 0) {</span><a href="#l1153"></a>
<span id="l1154">            throw new IllegalArgumentException(&quot;User name can't be null or empty&quot;);</span><a href="#l1154"></a>
<span id="l1155">        }</span><a href="#l1155"></a>
<span id="l1156">        tryLogin(user, password);</span><a href="#l1156"></a>
<span id="l1157"></span><a href="#l1157"></a>
<span id="l1158">        // keep the welcome message around so we can</span><a href="#l1158"></a>
<span id="l1159">        // put it in the resulting HTML page.</span><a href="#l1159"></a>
<span id="l1160">        String l;</span><a href="#l1160"></a>
<span id="l1161">        StringBuffer sb = new StringBuffer();</span><a href="#l1161"></a>
<span id="l1162">        for (int i = 0; i &lt; serverResponse.size(); i++) {</span><a href="#l1162"></a>
<span id="l1163">            l = serverResponse.elementAt(i);</span><a href="#l1163"></a>
<span id="l1164">            if (l != null) {</span><a href="#l1164"></a>
<span id="l1165">                if (l.length() &gt;= 4 &amp;&amp; l.startsWith(&quot;230&quot;)) {</span><a href="#l1165"></a>
<span id="l1166">                    // get rid of the &quot;230-&quot; prefix</span><a href="#l1166"></a>
<span id="l1167">                    l = l.substring(4);</span><a href="#l1167"></a>
<span id="l1168">                }</span><a href="#l1168"></a>
<span id="l1169">                sb.append(l);</span><a href="#l1169"></a>
<span id="l1170">            }</span><a href="#l1170"></a>
<span id="l1171">        }</span><a href="#l1171"></a>
<span id="l1172">        welcomeMsg = sb.toString();</span><a href="#l1172"></a>
<span id="l1173">        loggedIn = true;</span><a href="#l1173"></a>
<span id="l1174">        return this;</span><a href="#l1174"></a>
<span id="l1175">    }</span><a href="#l1175"></a>
<span id="l1176"></span><a href="#l1176"></a>
<span id="l1177">    /**</span><a href="#l1177"></a>
<span id="l1178">     * Attempts to log on the server with the specified user name, password and</span><a href="#l1178"></a>
<span id="l1179">     * account name.</span><a href="#l1179"></a>
<span id="l1180">     *</span><a href="#l1180"></a>
<span id="l1181">     * @param user The user name</span><a href="#l1181"></a>
<span id="l1182">     * @param password The password for that user.</span><a href="#l1182"></a>
<span id="l1183">     * @param account The account name for that user.</span><a href="#l1183"></a>
<span id="l1184">     * @return &lt;code&gt;true&lt;/code&gt; if the login was successful.</span><a href="#l1184"></a>
<span id="l1185">     * @throws IOException if an error occurs during the transmission.</span><a href="#l1185"></a>
<span id="l1186">     */</span><a href="#l1186"></a>
<span id="l1187">    public sun.net.ftp.FtpClient login(String user, char[] password, String account) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1187"></a>
<span id="l1188"></span><a href="#l1188"></a>
<span id="l1189">        if (!isConnected()) {</span><a href="#l1189"></a>
<span id="l1190">            throw new sun.net.ftp.FtpProtocolException(&quot;Not connected yet&quot;, FtpReplyCode.BAD_SEQUENCE);</span><a href="#l1190"></a>
<span id="l1191">        }</span><a href="#l1191"></a>
<span id="l1192">        if (user == null || user.length() == 0) {</span><a href="#l1192"></a>
<span id="l1193">            throw new IllegalArgumentException(&quot;User name can't be null or empty&quot;);</span><a href="#l1193"></a>
<span id="l1194">        }</span><a href="#l1194"></a>
<span id="l1195">        tryLogin(user, password);</span><a href="#l1195"></a>
<span id="l1196"></span><a href="#l1196"></a>
<span id="l1197">        /*</span><a href="#l1197"></a>
<span id="l1198">         * Checks for &quot;332 Need account for login.&quot; answer</span><a href="#l1198"></a>
<span id="l1199">         */</span><a href="#l1199"></a>
<span id="l1200">        if (lastReplyCode == FtpReplyCode.NEED_ACCOUNT) {</span><a href="#l1200"></a>
<span id="l1201">            issueCommandCheck(&quot;ACCT &quot; + account);</span><a href="#l1201"></a>
<span id="l1202">        }</span><a href="#l1202"></a>
<span id="l1203"></span><a href="#l1203"></a>
<span id="l1204">        // keep the welcome message around so we can</span><a href="#l1204"></a>
<span id="l1205">        // put it in the resulting HTML page.</span><a href="#l1205"></a>
<span id="l1206">        StringBuffer sb = new StringBuffer();</span><a href="#l1206"></a>
<span id="l1207">        if (serverResponse != null) {</span><a href="#l1207"></a>
<span id="l1208">            for (String l : serverResponse) {</span><a href="#l1208"></a>
<span id="l1209">                if (l != null) {</span><a href="#l1209"></a>
<span id="l1210">                    if (l.length() &gt;= 4 &amp;&amp; l.startsWith(&quot;230&quot;)) {</span><a href="#l1210"></a>
<span id="l1211">                        // get rid of the &quot;230-&quot; prefix</span><a href="#l1211"></a>
<span id="l1212">                        l = l.substring(4);</span><a href="#l1212"></a>
<span id="l1213">                    }</span><a href="#l1213"></a>
<span id="l1214">                    sb.append(l);</span><a href="#l1214"></a>
<span id="l1215">                }</span><a href="#l1215"></a>
<span id="l1216">            }</span><a href="#l1216"></a>
<span id="l1217">        }</span><a href="#l1217"></a>
<span id="l1218">        welcomeMsg = sb.toString();</span><a href="#l1218"></a>
<span id="l1219">        loggedIn = true;</span><a href="#l1219"></a>
<span id="l1220">        return this;</span><a href="#l1220"></a>
<span id="l1221">    }</span><a href="#l1221"></a>
<span id="l1222"></span><a href="#l1222"></a>
<span id="l1223">    /**</span><a href="#l1223"></a>
<span id="l1224">     * Logs out the current user. This is in effect terminates the current</span><a href="#l1224"></a>
<span id="l1225">     * session and the connection to the server will be closed.</span><a href="#l1225"></a>
<span id="l1226">     *</span><a href="#l1226"></a>
<span id="l1227">     */</span><a href="#l1227"></a>
<span id="l1228">    public void close() throws IOException {</span><a href="#l1228"></a>
<span id="l1229">        if (isConnected()) {</span><a href="#l1229"></a>
<span id="l1230">            try {</span><a href="#l1230"></a>
<span id="l1231">                issueCommand(&quot;QUIT&quot;);</span><a href="#l1231"></a>
<span id="l1232">            } catch (FtpProtocolException e) {</span><a href="#l1232"></a>
<span id="l1233">            }</span><a href="#l1233"></a>
<span id="l1234">            loggedIn = false;</span><a href="#l1234"></a>
<span id="l1235">        }</span><a href="#l1235"></a>
<span id="l1236">        disconnect();</span><a href="#l1236"></a>
<span id="l1237">    }</span><a href="#l1237"></a>
<span id="l1238"></span><a href="#l1238"></a>
<span id="l1239">    /**</span><a href="#l1239"></a>
<span id="l1240">     * Checks whether the client is logged in to the server or not.</span><a href="#l1240"></a>
<span id="l1241">     *</span><a href="#l1241"></a>
<span id="l1242">     * @return &lt;code&gt;true&lt;/code&gt; if the client has already completed a login.</span><a href="#l1242"></a>
<span id="l1243">     */</span><a href="#l1243"></a>
<span id="l1244">    public boolean isLoggedIn() {</span><a href="#l1244"></a>
<span id="l1245">        return loggedIn;</span><a href="#l1245"></a>
<span id="l1246">    }</span><a href="#l1246"></a>
<span id="l1247"></span><a href="#l1247"></a>
<span id="l1248">    /**</span><a href="#l1248"></a>
<span id="l1249">     * Changes to a specific directory on a remote FTP server</span><a href="#l1249"></a>
<span id="l1250">     *</span><a href="#l1250"></a>
<span id="l1251">     * @param remoteDirectory path of the directory to CD to.</span><a href="#l1251"></a>
<span id="l1252">     * @return &lt;code&gt;true&lt;/code&gt; if the operation was successful.</span><a href="#l1252"></a>
<span id="l1253">     * @exception &lt;code&gt;FtpProtocolException&lt;/code&gt;</span><a href="#l1253"></a>
<span id="l1254">     */</span><a href="#l1254"></a>
<span id="l1255">    public sun.net.ftp.FtpClient changeDirectory(String remoteDirectory) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1255"></a>
<span id="l1256">        if (remoteDirectory == null || &quot;&quot;.equals(remoteDirectory)) {</span><a href="#l1256"></a>
<span id="l1257">            throw new IllegalArgumentException(&quot;directory can't be null or empty&quot;);</span><a href="#l1257"></a>
<span id="l1258">        }</span><a href="#l1258"></a>
<span id="l1259"></span><a href="#l1259"></a>
<span id="l1260">        issueCommandCheck(&quot;CWD &quot; + remoteDirectory);</span><a href="#l1260"></a>
<span id="l1261">        return this;</span><a href="#l1261"></a>
<span id="l1262">    }</span><a href="#l1262"></a>
<span id="l1263"></span><a href="#l1263"></a>
<span id="l1264">    /**</span><a href="#l1264"></a>
<span id="l1265">     * Changes to the parent directory, sending the CDUP command to the server.</span><a href="#l1265"></a>
<span id="l1266">     *</span><a href="#l1266"></a>
<span id="l1267">     * @return &lt;code&gt;true&lt;/code&gt; if the command was successful.</span><a href="#l1267"></a>
<span id="l1268">     * @throws IOException</span><a href="#l1268"></a>
<span id="l1269">     */</span><a href="#l1269"></a>
<span id="l1270">    public sun.net.ftp.FtpClient changeToParentDirectory() throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1270"></a>
<span id="l1271">        issueCommandCheck(&quot;CDUP&quot;);</span><a href="#l1271"></a>
<span id="l1272">        return this;</span><a href="#l1272"></a>
<span id="l1273">    }</span><a href="#l1273"></a>
<span id="l1274"></span><a href="#l1274"></a>
<span id="l1275">    /**</span><a href="#l1275"></a>
<span id="l1276">     * Returns the server current working directory, or &lt;code&gt;null&lt;/code&gt; if</span><a href="#l1276"></a>
<span id="l1277">     * the PWD command failed.</span><a href="#l1277"></a>
<span id="l1278">     *</span><a href="#l1278"></a>
<span id="l1279">     * @return a &lt;code&gt;String&lt;/code&gt; containing the current working directory,</span><a href="#l1279"></a>
<span id="l1280">     *         or &lt;code&gt;null&lt;/code&gt;</span><a href="#l1280"></a>
<span id="l1281">     * @throws IOException</span><a href="#l1281"></a>
<span id="l1282">     */</span><a href="#l1282"></a>
<span id="l1283">    public String getWorkingDirectory() throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1283"></a>
<span id="l1284">        issueCommandCheck(&quot;PWD&quot;);</span><a href="#l1284"></a>
<span id="l1285">        /*</span><a href="#l1285"></a>
<span id="l1286">         * answer will be of the following format :</span><a href="#l1286"></a>
<span id="l1287">         *</span><a href="#l1287"></a>
<span id="l1288">         * 257 &quot;/&quot; is current directory.</span><a href="#l1288"></a>
<span id="l1289">         */</span><a href="#l1289"></a>
<span id="l1290">        String answ = getResponseString();</span><a href="#l1290"></a>
<span id="l1291">        if (!answ.startsWith(&quot;257&quot;)) {</span><a href="#l1291"></a>
<span id="l1292">            return null;</span><a href="#l1292"></a>
<span id="l1293">        }</span><a href="#l1293"></a>
<span id="l1294">        return answ.substring(5, answ.lastIndexOf('&quot;'));</span><a href="#l1294"></a>
<span id="l1295">    }</span><a href="#l1295"></a>
<span id="l1296"></span><a href="#l1296"></a>
<span id="l1297">    /**</span><a href="#l1297"></a>
<span id="l1298">     * Sets the restart offset to the specified value.  That value will be</span><a href="#l1298"></a>
<span id="l1299">     * sent through a &lt;code&gt;REST&lt;/code&gt; command to server before a file</span><a href="#l1299"></a>
<span id="l1300">     * transfer and has the effect of resuming a file transfer from the</span><a href="#l1300"></a>
<span id="l1301">     * specified point. After a transfer the restart offset is set back to</span><a href="#l1301"></a>
<span id="l1302">     * zero.</span><a href="#l1302"></a>
<span id="l1303">     *</span><a href="#l1303"></a>
<span id="l1304">     * @param offset the offset in the remote file at which to start the next</span><a href="#l1304"></a>
<span id="l1305">     *        transfer. This must be a value greater than or equal to zero.</span><a href="#l1305"></a>
<span id="l1306">     * @throws IllegalArgumentException if the offset is negative.</span><a href="#l1306"></a>
<span id="l1307">     */</span><a href="#l1307"></a>
<span id="l1308">    public sun.net.ftp.FtpClient setRestartOffset(long offset) {</span><a href="#l1308"></a>
<span id="l1309">        if (offset &lt; 0) {</span><a href="#l1309"></a>
<span id="l1310">            throw new IllegalArgumentException(&quot;offset can't be negative&quot;);</span><a href="#l1310"></a>
<span id="l1311">        }</span><a href="#l1311"></a>
<span id="l1312">        restartOffset = offset;</span><a href="#l1312"></a>
<span id="l1313">        return this;</span><a href="#l1313"></a>
<span id="l1314">    }</span><a href="#l1314"></a>
<span id="l1315"></span><a href="#l1315"></a>
<span id="l1316">    /**</span><a href="#l1316"></a>
<span id="l1317">     * Retrieves a file from the ftp server and writes it to the specified</span><a href="#l1317"></a>
<span id="l1318">     * &lt;code&gt;OutputStream&lt;/code&gt;.</span><a href="#l1318"></a>
<span id="l1319">     * If the restart offset was set, then a &lt;code&gt;REST&lt;/code&gt; command will be</span><a href="#l1319"></a>
<span id="l1320">     * sent before the RETR in order to restart the tranfer from the specified</span><a href="#l1320"></a>
<span id="l1321">     * offset.</span><a href="#l1321"></a>
<span id="l1322">     * The &lt;code&gt;OutputStream&lt;/code&gt; is not closed by this method at the end</span><a href="#l1322"></a>
<span id="l1323">     * of the transfer.</span><a href="#l1323"></a>
<span id="l1324">     *</span><a href="#l1324"></a>
<span id="l1325">     * @param name a &lt;code&gt;String&lt;code&gt; containing the name of the file to</span><a href="#l1325"></a>
<span id="l1326">     *        retreive from the server.</span><a href="#l1326"></a>
<span id="l1327">     * @param local the &lt;code&gt;OutputStream&lt;/code&gt; the file should be written to.</span><a href="#l1327"></a>
<span id="l1328">     * @throws IOException if the transfer fails.</span><a href="#l1328"></a>
<span id="l1329">     */</span><a href="#l1329"></a>
<span id="l1330">    public sun.net.ftp.FtpClient getFile(String name, OutputStream local) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1330"></a>
<span id="l1331">        int mtu = 1500;</span><a href="#l1331"></a>
<span id="l1332">        if (restartOffset &gt; 0) {</span><a href="#l1332"></a>
<span id="l1333">            Socket s;</span><a href="#l1333"></a>
<span id="l1334">            try {</span><a href="#l1334"></a>
<span id="l1335">                s = openDataConnection(&quot;REST &quot; + restartOffset);</span><a href="#l1335"></a>
<span id="l1336">            } finally {</span><a href="#l1336"></a>
<span id="l1337">                restartOffset = 0;</span><a href="#l1337"></a>
<span id="l1338">            }</span><a href="#l1338"></a>
<span id="l1339">            issueCommandCheck(&quot;RETR &quot; + name);</span><a href="#l1339"></a>
<span id="l1340">            getTransferSize();</span><a href="#l1340"></a>
<span id="l1341">            InputStream remote = createInputStream(s.getInputStream());</span><a href="#l1341"></a>
<span id="l1342">            byte[] buf = new byte[mtu * 10];</span><a href="#l1342"></a>
<span id="l1343">            int l;</span><a href="#l1343"></a>
<span id="l1344">            while ((l = remote.read(buf)) &gt;= 0) {</span><a href="#l1344"></a>
<span id="l1345">                if (l &gt; 0) {</span><a href="#l1345"></a>
<span id="l1346">                    local.write(buf, 0, l);</span><a href="#l1346"></a>
<span id="l1347">                }</span><a href="#l1347"></a>
<span id="l1348">            }</span><a href="#l1348"></a>
<span id="l1349">            remote.close();</span><a href="#l1349"></a>
<span id="l1350">        } else {</span><a href="#l1350"></a>
<span id="l1351">            Socket s = openDataConnection(&quot;RETR &quot; + name);</span><a href="#l1351"></a>
<span id="l1352">            getTransferSize();</span><a href="#l1352"></a>
<span id="l1353">            InputStream remote = createInputStream(s.getInputStream());</span><a href="#l1353"></a>
<span id="l1354">            byte[] buf = new byte[mtu * 10];</span><a href="#l1354"></a>
<span id="l1355">            int l;</span><a href="#l1355"></a>
<span id="l1356">            while ((l = remote.read(buf)) &gt;= 0) {</span><a href="#l1356"></a>
<span id="l1357">                if (l &gt; 0) {</span><a href="#l1357"></a>
<span id="l1358">                    local.write(buf, 0, l);</span><a href="#l1358"></a>
<span id="l1359">                }</span><a href="#l1359"></a>
<span id="l1360">            }</span><a href="#l1360"></a>
<span id="l1361">            remote.close();</span><a href="#l1361"></a>
<span id="l1362">        }</span><a href="#l1362"></a>
<span id="l1363">        return completePending();</span><a href="#l1363"></a>
<span id="l1364">    }</span><a href="#l1364"></a>
<span id="l1365"></span><a href="#l1365"></a>
<span id="l1366">    /**</span><a href="#l1366"></a>
<span id="l1367">     * Retrieves a file from the ftp server, using the RETR command, and</span><a href="#l1367"></a>
<span id="l1368">     * returns the InputStream from* the established data connection.</span><a href="#l1368"></a>
<span id="l1369">     * {@link #completePending()} &lt;b&gt;has&lt;/b&gt; to be called once the application</span><a href="#l1369"></a>
<span id="l1370">     * is done reading from the returned stream.</span><a href="#l1370"></a>
<span id="l1371">     *</span><a href="#l1371"></a>
<span id="l1372">     * @param name the name of the remote file</span><a href="#l1372"></a>
<span id="l1373">     * @return the {@link java.io.InputStream} from the data connection, or</span><a href="#l1373"></a>
<span id="l1374">     *         &lt;code&gt;null&lt;/code&gt; if the command was unsuccessful.</span><a href="#l1374"></a>
<span id="l1375">     * @throws IOException if an error occurred during the transmission.</span><a href="#l1375"></a>
<span id="l1376">     */</span><a href="#l1376"></a>
<span id="l1377">    public InputStream getFileStream(String name) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1377"></a>
<span id="l1378">        Socket s;</span><a href="#l1378"></a>
<span id="l1379">        if (restartOffset &gt; 0) {</span><a href="#l1379"></a>
<span id="l1380">            try {</span><a href="#l1380"></a>
<span id="l1381">                s = openDataConnection(&quot;REST &quot; + restartOffset);</span><a href="#l1381"></a>
<span id="l1382">            } finally {</span><a href="#l1382"></a>
<span id="l1383">                restartOffset = 0;</span><a href="#l1383"></a>
<span id="l1384">            }</span><a href="#l1384"></a>
<span id="l1385">            if (s == null) {</span><a href="#l1385"></a>
<span id="l1386">                return null;</span><a href="#l1386"></a>
<span id="l1387">            }</span><a href="#l1387"></a>
<span id="l1388">            issueCommandCheck(&quot;RETR &quot; + name);</span><a href="#l1388"></a>
<span id="l1389">            getTransferSize();</span><a href="#l1389"></a>
<span id="l1390">            return createInputStream(s.getInputStream());</span><a href="#l1390"></a>
<span id="l1391">        }</span><a href="#l1391"></a>
<span id="l1392"></span><a href="#l1392"></a>
<span id="l1393">        s = openDataConnection(&quot;RETR &quot; + name);</span><a href="#l1393"></a>
<span id="l1394">        if (s == null) {</span><a href="#l1394"></a>
<span id="l1395">            return null;</span><a href="#l1395"></a>
<span id="l1396">        }</span><a href="#l1396"></a>
<span id="l1397">        getTransferSize();</span><a href="#l1397"></a>
<span id="l1398">        return createInputStream(s.getInputStream());</span><a href="#l1398"></a>
<span id="l1399">    }</span><a href="#l1399"></a>
<span id="l1400"></span><a href="#l1400"></a>
<span id="l1401">    /**</span><a href="#l1401"></a>
<span id="l1402">     * Transfers a file from the client to the server (aka a &lt;I&gt;put&lt;/I&gt;)</span><a href="#l1402"></a>
<span id="l1403">     * by sending the STOR or STOU command, depending on the</span><a href="#l1403"></a>
<span id="l1404">     * &lt;code&gt;unique&lt;/code&gt; argument, and returns the &lt;code&gt;OutputStream&lt;/code&gt;</span><a href="#l1404"></a>
<span id="l1405">     * from the established data connection.</span><a href="#l1405"></a>
<span id="l1406">     * {@link #completePending()} &lt;b&gt;has&lt;/b&gt; to be called once the application</span><a href="#l1406"></a>
<span id="l1407">     * is finished writing to the stream.</span><a href="#l1407"></a>
<span id="l1408">     *</span><a href="#l1408"></a>
<span id="l1409">     * A new file is created at the server site if the file specified does</span><a href="#l1409"></a>
<span id="l1410">     * not already exist.</span><a href="#l1410"></a>
<span id="l1411">     *</span><a href="#l1411"></a>
<span id="l1412">     * If &lt;code&gt;unique&lt;/code&gt; is set to &lt;code&gt;true&lt;/code&gt;, the resultant file</span><a href="#l1412"></a>
<span id="l1413">     * is to be created under a name unique to that directory, meaning</span><a href="#l1413"></a>
<span id="l1414">     * it will not overwrite an existing file, instead the server will</span><a href="#l1414"></a>
<span id="l1415">     * generate a new, unique, file name.</span><a href="#l1415"></a>
<span id="l1416">     * The name of the remote file can be retrieved, after completion of the</span><a href="#l1416"></a>
<span id="l1417">     * transfer, by calling {@link #getLastFileName()}.</span><a href="#l1417"></a>
<span id="l1418">     *</span><a href="#l1418"></a>
<span id="l1419">     * @param name the name of the remote file to write.</span><a href="#l1419"></a>
<span id="l1420">     * @param unique &lt;code&gt;true&lt;/code&gt; if the remote files should be unique,</span><a href="#l1420"></a>
<span id="l1421">     *        in which case the STOU command will be used.</span><a href="#l1421"></a>
<span id="l1422">     * @return the {@link java.io.OutputStream} from the data connection or</span><a href="#l1422"></a>
<span id="l1423">     *         &lt;code&gt;null&lt;/code&gt; if the command was unsuccessful.</span><a href="#l1423"></a>
<span id="l1424">     * @throws IOException if an error occurred during the transmission.</span><a href="#l1424"></a>
<span id="l1425">     */</span><a href="#l1425"></a>
<span id="l1426">    public OutputStream putFileStream(String name, boolean unique)</span><a href="#l1426"></a>
<span id="l1427">        throws sun.net.ftp.FtpProtocolException, IOException</span><a href="#l1427"></a>
<span id="l1428">    {</span><a href="#l1428"></a>
<span id="l1429">        String cmd = unique ? &quot;STOU &quot; : &quot;STOR &quot;;</span><a href="#l1429"></a>
<span id="l1430">        Socket s = openDataConnection(cmd + name);</span><a href="#l1430"></a>
<span id="l1431">        if (s == null) {</span><a href="#l1431"></a>
<span id="l1432">            return null;</span><a href="#l1432"></a>
<span id="l1433">        }</span><a href="#l1433"></a>
<span id="l1434">        boolean bm = (type == TransferType.BINARY);</span><a href="#l1434"></a>
<span id="l1435">        return new sun.net.TelnetOutputStream(s.getOutputStream(), bm);</span><a href="#l1435"></a>
<span id="l1436">    }</span><a href="#l1436"></a>
<span id="l1437"></span><a href="#l1437"></a>
<span id="l1438">    /**</span><a href="#l1438"></a>
<span id="l1439">     * Transfers a file from the client to the server (aka a &lt;I&gt;put&lt;/I&gt;)</span><a href="#l1439"></a>
<span id="l1440">     * by sending the STOR command. The content of the &lt;code&gt;InputStream&lt;/code&gt;</span><a href="#l1440"></a>
<span id="l1441">     * passed in argument is written into the remote file, overwriting any</span><a href="#l1441"></a>
<span id="l1442">     * existing data.</span><a href="#l1442"></a>
<span id="l1443">     *</span><a href="#l1443"></a>
<span id="l1444">     * A new file is created at the server site if the file specified does</span><a href="#l1444"></a>
<span id="l1445">     * not already exist.</span><a href="#l1445"></a>
<span id="l1446">     *</span><a href="#l1446"></a>
<span id="l1447">     * @param name the name of the remote file to write.</span><a href="#l1447"></a>
<span id="l1448">     * @param local the &lt;code&gt;InputStream&lt;/code&gt; that points to the data to</span><a href="#l1448"></a>
<span id="l1449">     *        transfer.</span><a href="#l1449"></a>
<span id="l1450">     * @param unique &lt;code&gt;true&lt;/code&gt; if the remote file should be unique</span><a href="#l1450"></a>
<span id="l1451">     *        (i.e. not already existing), &lt;code&gt;false&lt;/code&gt; otherwise.</span><a href="#l1451"></a>
<span id="l1452">     * @return &lt;code&gt;true&lt;/code&gt; if the transfer was successful.</span><a href="#l1452"></a>
<span id="l1453">     * @throws IOException if an error occurred during the transmission.</span><a href="#l1453"></a>
<span id="l1454">     * @see #getLastFileName()</span><a href="#l1454"></a>
<span id="l1455">     */</span><a href="#l1455"></a>
<span id="l1456">    public sun.net.ftp.FtpClient putFile(String name, InputStream local, boolean unique) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1456"></a>
<span id="l1457">        String cmd = unique ? &quot;STOU &quot; : &quot;STOR &quot;;</span><a href="#l1457"></a>
<span id="l1458">        int mtu = 1500;</span><a href="#l1458"></a>
<span id="l1459">        if (type == TransferType.BINARY) {</span><a href="#l1459"></a>
<span id="l1460">            Socket s = openDataConnection(cmd + name);</span><a href="#l1460"></a>
<span id="l1461">            OutputStream remote = createOutputStream(s.getOutputStream());</span><a href="#l1461"></a>
<span id="l1462">            byte[] buf = new byte[mtu * 10];</span><a href="#l1462"></a>
<span id="l1463">            int l;</span><a href="#l1463"></a>
<span id="l1464">            while ((l = local.read(buf)) &gt;= 0) {</span><a href="#l1464"></a>
<span id="l1465">                if (l &gt; 0) {</span><a href="#l1465"></a>
<span id="l1466">                    remote.write(buf, 0, l);</span><a href="#l1466"></a>
<span id="l1467">                }</span><a href="#l1467"></a>
<span id="l1468">            }</span><a href="#l1468"></a>
<span id="l1469">            remote.close();</span><a href="#l1469"></a>
<span id="l1470">        }</span><a href="#l1470"></a>
<span id="l1471">        return completePending();</span><a href="#l1471"></a>
<span id="l1472">    }</span><a href="#l1472"></a>
<span id="l1473"></span><a href="#l1473"></a>
<span id="l1474">    /**</span><a href="#l1474"></a>
<span id="l1475">     * Sends the APPE command to the server in order to transfer a data stream</span><a href="#l1475"></a>
<span id="l1476">     * passed in argument and append it to the content of the specified remote</span><a href="#l1476"></a>
<span id="l1477">     * file.</span><a href="#l1477"></a>
<span id="l1478">     *</span><a href="#l1478"></a>
<span id="l1479">     * @param name A &lt;code&gt;String&lt;/code&gt; containing the name of the remote file</span><a href="#l1479"></a>
<span id="l1480">     *        to append to.</span><a href="#l1480"></a>
<span id="l1481">     * @param local The &lt;code&gt;InputStream&lt;/code&gt; providing access to the data</span><a href="#l1481"></a>
<span id="l1482">     *        to be appended.</span><a href="#l1482"></a>
<span id="l1483">     * @return &lt;code&gt;true&lt;/code&gt; if the transfer was successful.</span><a href="#l1483"></a>
<span id="l1484">     * @throws IOException if an error occurred during the transmission.</span><a href="#l1484"></a>
<span id="l1485">     */</span><a href="#l1485"></a>
<span id="l1486">    public sun.net.ftp.FtpClient appendFile(String name, InputStream local) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1486"></a>
<span id="l1487">        int mtu = 1500;</span><a href="#l1487"></a>
<span id="l1488">        Socket s = openDataConnection(&quot;APPE &quot; + name);</span><a href="#l1488"></a>
<span id="l1489">        OutputStream remote = createOutputStream(s.getOutputStream());</span><a href="#l1489"></a>
<span id="l1490">        byte[] buf = new byte[mtu * 10];</span><a href="#l1490"></a>
<span id="l1491">        int l;</span><a href="#l1491"></a>
<span id="l1492">        while ((l = local.read(buf)) &gt;= 0) {</span><a href="#l1492"></a>
<span id="l1493">            if (l &gt; 0) {</span><a href="#l1493"></a>
<span id="l1494">                remote.write(buf, 0, l);</span><a href="#l1494"></a>
<span id="l1495">            }</span><a href="#l1495"></a>
<span id="l1496">        }</span><a href="#l1496"></a>
<span id="l1497">        remote.close();</span><a href="#l1497"></a>
<span id="l1498">        return completePending();</span><a href="#l1498"></a>
<span id="l1499">    }</span><a href="#l1499"></a>
<span id="l1500"></span><a href="#l1500"></a>
<span id="l1501">    /**</span><a href="#l1501"></a>
<span id="l1502">     * Renames a file on the server.</span><a href="#l1502"></a>
<span id="l1503">     *</span><a href="#l1503"></a>
<span id="l1504">     * @param from the name of the file being renamed</span><a href="#l1504"></a>
<span id="l1505">     * @param to the new name for the file</span><a href="#l1505"></a>
<span id="l1506">     * @throws IOException if the command fails</span><a href="#l1506"></a>
<span id="l1507">     */</span><a href="#l1507"></a>
<span id="l1508">    public sun.net.ftp.FtpClient rename(String from, String to) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1508"></a>
<span id="l1509">        issueCommandCheck(&quot;RNFR &quot; + from);</span><a href="#l1509"></a>
<span id="l1510">        issueCommandCheck(&quot;RNTO &quot; + to);</span><a href="#l1510"></a>
<span id="l1511">        return this;</span><a href="#l1511"></a>
<span id="l1512">    }</span><a href="#l1512"></a>
<span id="l1513"></span><a href="#l1513"></a>
<span id="l1514">    /**</span><a href="#l1514"></a>
<span id="l1515">     * Deletes a file on the server.</span><a href="#l1515"></a>
<span id="l1516">     *</span><a href="#l1516"></a>
<span id="l1517">     * @param name a &lt;code&gt;String&lt;/code&gt; containing the name of the file</span><a href="#l1517"></a>
<span id="l1518">     *        to delete.</span><a href="#l1518"></a>
<span id="l1519">     * @return &lt;code&gt;true&lt;/code&gt; if the command was successful</span><a href="#l1519"></a>
<span id="l1520">     * @throws IOException if an error occurred during the exchange</span><a href="#l1520"></a>
<span id="l1521">     */</span><a href="#l1521"></a>
<span id="l1522">    public sun.net.ftp.FtpClient deleteFile(String name) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1522"></a>
<span id="l1523">        issueCommandCheck(&quot;DELE &quot; + name);</span><a href="#l1523"></a>
<span id="l1524">        return this;</span><a href="#l1524"></a>
<span id="l1525">    }</span><a href="#l1525"></a>
<span id="l1526"></span><a href="#l1526"></a>
<span id="l1527">    /**</span><a href="#l1527"></a>
<span id="l1528">     * Creates a new directory on the server.</span><a href="#l1528"></a>
<span id="l1529">     *</span><a href="#l1529"></a>
<span id="l1530">     * @param name a &lt;code&gt;String&lt;/code&gt; containing the name of the directory</span><a href="#l1530"></a>
<span id="l1531">     *        to create.</span><a href="#l1531"></a>
<span id="l1532">     * @return &lt;code&gt;true&lt;/code&gt; if the operation was successful.</span><a href="#l1532"></a>
<span id="l1533">     * @throws IOException if an error occurred during the exchange</span><a href="#l1533"></a>
<span id="l1534">     */</span><a href="#l1534"></a>
<span id="l1535">    public sun.net.ftp.FtpClient makeDirectory(String name) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1535"></a>
<span id="l1536">        issueCommandCheck(&quot;MKD &quot; + name);</span><a href="#l1536"></a>
<span id="l1537">        return this;</span><a href="#l1537"></a>
<span id="l1538">    }</span><a href="#l1538"></a>
<span id="l1539"></span><a href="#l1539"></a>
<span id="l1540">    /**</span><a href="#l1540"></a>
<span id="l1541">     * Removes a directory on the server.</span><a href="#l1541"></a>
<span id="l1542">     *</span><a href="#l1542"></a>
<span id="l1543">     * @param name a &lt;code&gt;String&lt;/code&gt; containing the name of the directory</span><a href="#l1543"></a>
<span id="l1544">     *        to remove.</span><a href="#l1544"></a>
<span id="l1545">     *</span><a href="#l1545"></a>
<span id="l1546">     * @return &lt;code&gt;true&lt;/code&gt; if the operation was successful.</span><a href="#l1546"></a>
<span id="l1547">     * @throws IOException if an error occurred during the exchange.</span><a href="#l1547"></a>
<span id="l1548">     */</span><a href="#l1548"></a>
<span id="l1549">    public sun.net.ftp.FtpClient removeDirectory(String name) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1549"></a>
<span id="l1550">        issueCommandCheck(&quot;RMD &quot; + name);</span><a href="#l1550"></a>
<span id="l1551">        return this;</span><a href="#l1551"></a>
<span id="l1552">    }</span><a href="#l1552"></a>
<span id="l1553"></span><a href="#l1553"></a>
<span id="l1554">    /**</span><a href="#l1554"></a>
<span id="l1555">     * Sends a No-operation command. It's useful for testing the connection</span><a href="#l1555"></a>
<span id="l1556">     * status or as a &lt;I&gt;keep alive&lt;/I&gt; mechanism.</span><a href="#l1556"></a>
<span id="l1557">     *</span><a href="#l1557"></a>
<span id="l1558">     * @throws FtpProtocolException if the command fails</span><a href="#l1558"></a>
<span id="l1559">     */</span><a href="#l1559"></a>
<span id="l1560">    public sun.net.ftp.FtpClient noop() throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1560"></a>
<span id="l1561">        issueCommandCheck(&quot;NOOP&quot;);</span><a href="#l1561"></a>
<span id="l1562">        return this;</span><a href="#l1562"></a>
<span id="l1563">    }</span><a href="#l1563"></a>
<span id="l1564"></span><a href="#l1564"></a>
<span id="l1565">    /**</span><a href="#l1565"></a>
<span id="l1566">     * Sends the STAT command to the server.</span><a href="#l1566"></a>
<span id="l1567">     * This can be used while a data connection is open to get a status</span><a href="#l1567"></a>
<span id="l1568">     * on the current transfer, in that case the parameter should be</span><a href="#l1568"></a>
<span id="l1569">     * &lt;code&gt;null&lt;/code&gt;.</span><a href="#l1569"></a>
<span id="l1570">     * If used between file transfers, it may have a pathname as argument</span><a href="#l1570"></a>
<span id="l1571">     * in which case it will work as the LIST command except no data</span><a href="#l1571"></a>
<span id="l1572">     * connection will be created.</span><a href="#l1572"></a>
<span id="l1573">     *</span><a href="#l1573"></a>
<span id="l1574">     * @param name an optional &lt;code&gt;String&lt;/code&gt; containing the pathname</span><a href="#l1574"></a>
<span id="l1575">     *        the STAT command should apply to.</span><a href="#l1575"></a>
<span id="l1576">     * @return the response from the server or &lt;code&gt;null&lt;/code&gt; if the</span><a href="#l1576"></a>
<span id="l1577">     *         command failed.</span><a href="#l1577"></a>
<span id="l1578">     * @throws IOException if an error occurred during the transmission.</span><a href="#l1578"></a>
<span id="l1579">     */</span><a href="#l1579"></a>
<span id="l1580">    public String getStatus(String name) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1580"></a>
<span id="l1581">        issueCommandCheck((name == null ? &quot;STAT&quot; : &quot;STAT &quot; + name));</span><a href="#l1581"></a>
<span id="l1582">        /*</span><a href="#l1582"></a>
<span id="l1583">         * A typical response will be:</span><a href="#l1583"></a>
<span id="l1584">         *  213-status of t32.gif:</span><a href="#l1584"></a>
<span id="l1585">         * -rw-r--r--   1 jcc      staff     247445 Feb 17  1998 t32.gif</span><a href="#l1585"></a>
<span id="l1586">         * 213 End of Status</span><a href="#l1586"></a>
<span id="l1587">         *</span><a href="#l1587"></a>
<span id="l1588">         * or</span><a href="#l1588"></a>
<span id="l1589">         *</span><a href="#l1589"></a>
<span id="l1590">         * 211-jsn FTP server status:</span><a href="#l1590"></a>
<span id="l1591">         *     Version wu-2.6.2+Sun</span><a href="#l1591"></a>
<span id="l1592">         *     Connected to localhost (::1)</span><a href="#l1592"></a>
<span id="l1593">         *     Logged in as jccollet</span><a href="#l1593"></a>
<span id="l1594">         *     TYPE: ASCII, FORM: Nonprint; STRUcture: File; transfer MODE: Stream</span><a href="#l1594"></a>
<span id="l1595">         *      No data connection</span><a href="#l1595"></a>
<span id="l1596">         *     0 data bytes received in 0 files</span><a href="#l1596"></a>
<span id="l1597">         *     0 data bytes transmitted in 0 files</span><a href="#l1597"></a>
<span id="l1598">         *     0 data bytes total in 0 files</span><a href="#l1598"></a>
<span id="l1599">         *     53 traffic bytes received in 0 transfers</span><a href="#l1599"></a>
<span id="l1600">         *     485 traffic bytes transmitted in 0 transfers</span><a href="#l1600"></a>
<span id="l1601">         *     587 traffic bytes total in 0 transfers</span><a href="#l1601"></a>
<span id="l1602">         * 211 End of status</span><a href="#l1602"></a>
<span id="l1603">         *</span><a href="#l1603"></a>
<span id="l1604">         * So we need to remove the 1st and last line</span><a href="#l1604"></a>
<span id="l1605">         */</span><a href="#l1605"></a>
<span id="l1606">        Vector&lt;String&gt; resp = getResponseStrings();</span><a href="#l1606"></a>
<span id="l1607">        StringBuffer sb = new StringBuffer();</span><a href="#l1607"></a>
<span id="l1608">        for (int i = 1; i &lt; resp.size() - 1; i++) {</span><a href="#l1608"></a>
<span id="l1609">            sb.append(resp.get(i));</span><a href="#l1609"></a>
<span id="l1610">        }</span><a href="#l1610"></a>
<span id="l1611">        return sb.toString();</span><a href="#l1611"></a>
<span id="l1612">    }</span><a href="#l1612"></a>
<span id="l1613"></span><a href="#l1613"></a>
<span id="l1614">    /**</span><a href="#l1614"></a>
<span id="l1615">     * Sends the FEAT command to the server and returns the list of supported</span><a href="#l1615"></a>
<span id="l1616">     * features in the form of strings.</span><a href="#l1616"></a>
<span id="l1617">     *</span><a href="#l1617"></a>
<span id="l1618">     * The features are the supported commands, like AUTH TLS, PROT or PASV.</span><a href="#l1618"></a>
<span id="l1619">     * See the RFCs for a complete list.</span><a href="#l1619"></a>
<span id="l1620">     *</span><a href="#l1620"></a>
<span id="l1621">     * Note that not all FTP servers support that command, in which case</span><a href="#l1621"></a>
<span id="l1622">     * the method will return &lt;code&gt;null&lt;/code&gt;</span><a href="#l1622"></a>
<span id="l1623">     *</span><a href="#l1623"></a>
<span id="l1624">     * @return a &lt;code&gt;List&lt;/code&gt; of &lt;code&gt;Strings&lt;/code&gt; describing the</span><a href="#l1624"></a>
<span id="l1625">     *         supported additional features, or &lt;code&gt;null&lt;/code&gt;</span><a href="#l1625"></a>
<span id="l1626">     *         if the command is not supported.</span><a href="#l1626"></a>
<span id="l1627">     * @throws IOException if an error occurs during the transmission.</span><a href="#l1627"></a>
<span id="l1628">     */</span><a href="#l1628"></a>
<span id="l1629">    public List&lt;String&gt; getFeatures() throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1629"></a>
<span id="l1630">        /*</span><a href="#l1630"></a>
<span id="l1631">         * The FEAT command, when implemented will return something like:</span><a href="#l1631"></a>
<span id="l1632">         *</span><a href="#l1632"></a>
<span id="l1633">         * 211-Features:</span><a href="#l1633"></a>
<span id="l1634">         *   AUTH TLS</span><a href="#l1634"></a>
<span id="l1635">         *   PBSZ</span><a href="#l1635"></a>
<span id="l1636">         *   PROT</span><a href="#l1636"></a>
<span id="l1637">         *   EPSV</span><a href="#l1637"></a>
<span id="l1638">         *   EPRT</span><a href="#l1638"></a>
<span id="l1639">         *   PASV</span><a href="#l1639"></a>
<span id="l1640">         *   REST STREAM</span><a href="#l1640"></a>
<span id="l1641">         *  211 END</span><a href="#l1641"></a>
<span id="l1642">         */</span><a href="#l1642"></a>
<span id="l1643">        ArrayList&lt;String&gt; features = new ArrayList&lt;String&gt;();</span><a href="#l1643"></a>
<span id="l1644">        issueCommandCheck(&quot;FEAT&quot;);</span><a href="#l1644"></a>
<span id="l1645">        Vector&lt;String&gt; resp = getResponseStrings();</span><a href="#l1645"></a>
<span id="l1646">        // Note that we start at index 1 to skip the 1st line (211-...)</span><a href="#l1646"></a>
<span id="l1647">        // and we stop before the last line.</span><a href="#l1647"></a>
<span id="l1648">        for (int i = 1; i &lt; resp.size() - 1; i++) {</span><a href="#l1648"></a>
<span id="l1649">            String s = resp.get(i);</span><a href="#l1649"></a>
<span id="l1650">            // Get rid of leading space and trailing newline</span><a href="#l1650"></a>
<span id="l1651">            features.add(s.substring(1, s.length() - 1));</span><a href="#l1651"></a>
<span id="l1652">        }</span><a href="#l1652"></a>
<span id="l1653">        return features;</span><a href="#l1653"></a>
<span id="l1654">    }</span><a href="#l1654"></a>
<span id="l1655"></span><a href="#l1655"></a>
<span id="l1656">    /**</span><a href="#l1656"></a>
<span id="l1657">     * sends the ABOR command to the server.</span><a href="#l1657"></a>
<span id="l1658">     * It tells the server to stop the previous command or transfer.</span><a href="#l1658"></a>
<span id="l1659">     *</span><a href="#l1659"></a>
<span id="l1660">     * @return &lt;code&gt;true&lt;/code&gt; if the command was successful.</span><a href="#l1660"></a>
<span id="l1661">     * @throws IOException if an error occurred during the transmission.</span><a href="#l1661"></a>
<span id="l1662">     */</span><a href="#l1662"></a>
<span id="l1663">    public sun.net.ftp.FtpClient abort() throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1663"></a>
<span id="l1664">        issueCommandCheck(&quot;ABOR&quot;);</span><a href="#l1664"></a>
<span id="l1665">        // TODO: Must check the ReplyCode:</span><a href="#l1665"></a>
<span id="l1666">        /*</span><a href="#l1666"></a>
<span id="l1667">         * From the RFC:</span><a href="#l1667"></a>
<span id="l1668">         * There are two cases for the server upon receipt of this</span><a href="#l1668"></a>
<span id="l1669">         * command: (1) the FTP service command was already completed,</span><a href="#l1669"></a>
<span id="l1670">         * or (2) the FTP service command is still in progress.</span><a href="#l1670"></a>
<span id="l1671">         * In the first case, the server closes the data connection</span><a href="#l1671"></a>
<span id="l1672">         * (if it is open) and responds with a 226 reply, indicating</span><a href="#l1672"></a>
<span id="l1673">         * that the abort command was successfully processed.</span><a href="#l1673"></a>
<span id="l1674">         * In the second case, the server aborts the FTP service in</span><a href="#l1674"></a>
<span id="l1675">         * progress and closes the data connection, returning a 426</span><a href="#l1675"></a>
<span id="l1676">         * reply to indicate that the service request terminated</span><a href="#l1676"></a>
<span id="l1677">         * abnormally.  The server then sends a 226 reply,</span><a href="#l1677"></a>
<span id="l1678">         * indicating that the abort command was successfully</span><a href="#l1678"></a>
<span id="l1679">         * processed.</span><a href="#l1679"></a>
<span id="l1680">         */</span><a href="#l1680"></a>
<span id="l1681"></span><a href="#l1681"></a>
<span id="l1682"></span><a href="#l1682"></a>
<span id="l1683">        return this;</span><a href="#l1683"></a>
<span id="l1684">    }</span><a href="#l1684"></a>
<span id="l1685"></span><a href="#l1685"></a>
<span id="l1686">    /**</span><a href="#l1686"></a>
<span id="l1687">     * Some methods do not wait until completion before returning, so this</span><a href="#l1687"></a>
<span id="l1688">     * method can be called to wait until completion. This is typically the case</span><a href="#l1688"></a>
<span id="l1689">     * with commands that trigger a transfer like {@link #getFileStream(String)}.</span><a href="#l1689"></a>
<span id="l1690">     * So this method should be called before accessing information related to</span><a href="#l1690"></a>
<span id="l1691">     * such a command.</span><a href="#l1691"></a>
<span id="l1692">     * &lt;p&gt;This method will actually block reading on the command channel for a</span><a href="#l1692"></a>
<span id="l1693">     * notification from the server that the command is finished. Such a</span><a href="#l1693"></a>
<span id="l1694">     * notification often carries extra information concerning the completion</span><a href="#l1694"></a>
<span id="l1695">     * of the pending action (e.g. number of bytes transfered).&lt;/p&gt;</span><a href="#l1695"></a>
<span id="l1696">     * &lt;p&gt;Note that this will return true immediately if no command or action</span><a href="#l1696"></a>
<span id="l1697">     * is pending&lt;/p&gt;</span><a href="#l1697"></a>
<span id="l1698">     * &lt;p&gt;It should be also noted that most methods issuing commands to the ftp</span><a href="#l1698"></a>
<span id="l1699">     * server will call this method if a previous command is pending.</span><a href="#l1699"></a>
<span id="l1700">     * &lt;p&gt;Example of use:</span><a href="#l1700"></a>
<span id="l1701">     * &lt;pre&gt;</span><a href="#l1701"></a>
<span id="l1702">     * InputStream in = cl.getFileStream(&quot;file&quot;);</span><a href="#l1702"></a>
<span id="l1703">     * ...</span><a href="#l1703"></a>
<span id="l1704">     * cl.completePending();</span><a href="#l1704"></a>
<span id="l1705">     * long size = cl.getLastTransferSize();</span><a href="#l1705"></a>
<span id="l1706">     * &lt;/pre&gt;</span><a href="#l1706"></a>
<span id="l1707">     * On the other hand, it's not necessary in a case like:</span><a href="#l1707"></a>
<span id="l1708">     * &lt;pre&gt;</span><a href="#l1708"></a>
<span id="l1709">     * InputStream in = cl.getFileStream(&quot;file&quot;);</span><a href="#l1709"></a>
<span id="l1710">     * // read content</span><a href="#l1710"></a>
<span id="l1711">     * ...</span><a href="#l1711"></a>
<span id="l1712">     * cl.logout();</span><a href="#l1712"></a>
<span id="l1713">     * &lt;/pre&gt;</span><a href="#l1713"></a>
<span id="l1714">     * &lt;p&gt;Since {@link #logout()} will call completePending() if necessary.&lt;/p&gt;</span><a href="#l1714"></a>
<span id="l1715">     * @return &lt;code&gt;true&lt;/code&gt; if the completion was successful or if no</span><a href="#l1715"></a>
<span id="l1716">     *         action was pending.</span><a href="#l1716"></a>
<span id="l1717">     * @throws IOException</span><a href="#l1717"></a>
<span id="l1718">     */</span><a href="#l1718"></a>
<span id="l1719">    public sun.net.ftp.FtpClient completePending() throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1719"></a>
<span id="l1720">        while (replyPending) {</span><a href="#l1720"></a>
<span id="l1721">            replyPending = false;</span><a href="#l1721"></a>
<span id="l1722">            if (!readReply()) {</span><a href="#l1722"></a>
<span id="l1723">                throw new sun.net.ftp.FtpProtocolException(getLastResponseString(), lastReplyCode);</span><a href="#l1723"></a>
<span id="l1724">            }</span><a href="#l1724"></a>
<span id="l1725">        }</span><a href="#l1725"></a>
<span id="l1726">        return this;</span><a href="#l1726"></a>
<span id="l1727">    }</span><a href="#l1727"></a>
<span id="l1728"></span><a href="#l1728"></a>
<span id="l1729">    /**</span><a href="#l1729"></a>
<span id="l1730">     * Reinitializes the USER parameters on the FTP server</span><a href="#l1730"></a>
<span id="l1731">     *</span><a href="#l1731"></a>
<span id="l1732">     * @throws FtpProtocolException if the command fails</span><a href="#l1732"></a>
<span id="l1733">     */</span><a href="#l1733"></a>
<span id="l1734">    public sun.net.ftp.FtpClient reInit() throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1734"></a>
<span id="l1735">        issueCommandCheck(&quot;REIN&quot;);</span><a href="#l1735"></a>
<span id="l1736">        loggedIn = false;</span><a href="#l1736"></a>
<span id="l1737">        if (useCrypto) {</span><a href="#l1737"></a>
<span id="l1738">            if (server instanceof SSLSocket) {</span><a href="#l1738"></a>
<span id="l1739">                javax.net.ssl.SSLSession session = ((SSLSocket) server).getSession();</span><a href="#l1739"></a>
<span id="l1740">                session.invalidate();</span><a href="#l1740"></a>
<span id="l1741">                // Restore previous socket and streams</span><a href="#l1741"></a>
<span id="l1742">                server = oldSocket;</span><a href="#l1742"></a>
<span id="l1743">                oldSocket = null;</span><a href="#l1743"></a>
<span id="l1744">                try {</span><a href="#l1744"></a>
<span id="l1745">                    out = new PrintStream(new BufferedOutputStream(server.getOutputStream()),</span><a href="#l1745"></a>
<span id="l1746">                            true, encoding);</span><a href="#l1746"></a>
<span id="l1747">                } catch (UnsupportedEncodingException e) {</span><a href="#l1747"></a>
<span id="l1748">                    throw new InternalError(encoding + &quot;encoding not found&quot;, e);</span><a href="#l1748"></a>
<span id="l1749">                }</span><a href="#l1749"></a>
<span id="l1750">                in = new BufferedInputStream(server.getInputStream());</span><a href="#l1750"></a>
<span id="l1751">            }</span><a href="#l1751"></a>
<span id="l1752">        }</span><a href="#l1752"></a>
<span id="l1753">        useCrypto = false;</span><a href="#l1753"></a>
<span id="l1754">        return this;</span><a href="#l1754"></a>
<span id="l1755">    }</span><a href="#l1755"></a>
<span id="l1756"></span><a href="#l1756"></a>
<span id="l1757">    /**</span><a href="#l1757"></a>
<span id="l1758">     * Changes the transfer type (binary, ascii, ebcdic) and issue the</span><a href="#l1758"></a>
<span id="l1759">     * proper command (e.g. TYPE A) to the server.</span><a href="#l1759"></a>
<span id="l1760">     *</span><a href="#l1760"></a>
<span id="l1761">     * @param type the &lt;code&gt;FtpTransferType&lt;/code&gt; to use.</span><a href="#l1761"></a>
<span id="l1762">     * @return This FtpClient</span><a href="#l1762"></a>
<span id="l1763">     * @throws IOException if an error occurs during transmission.</span><a href="#l1763"></a>
<span id="l1764">     */</span><a href="#l1764"></a>
<span id="l1765">    public sun.net.ftp.FtpClient setType(TransferType type) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1765"></a>
<span id="l1766">        String cmd = &quot;NOOP&quot;;</span><a href="#l1766"></a>
<span id="l1767"></span><a href="#l1767"></a>
<span id="l1768">        this.type = type;</span><a href="#l1768"></a>
<span id="l1769">        if (type == TransferType.ASCII) {</span><a href="#l1769"></a>
<span id="l1770">            cmd = &quot;TYPE A&quot;;</span><a href="#l1770"></a>
<span id="l1771">        }</span><a href="#l1771"></a>
<span id="l1772">        if (type == TransferType.BINARY) {</span><a href="#l1772"></a>
<span id="l1773">            cmd = &quot;TYPE I&quot;;</span><a href="#l1773"></a>
<span id="l1774">        }</span><a href="#l1774"></a>
<span id="l1775">        if (type == TransferType.EBCDIC) {</span><a href="#l1775"></a>
<span id="l1776">            cmd = &quot;TYPE E&quot;;</span><a href="#l1776"></a>
<span id="l1777">        }</span><a href="#l1777"></a>
<span id="l1778">        issueCommandCheck(cmd);</span><a href="#l1778"></a>
<span id="l1779">        return this;</span><a href="#l1779"></a>
<span id="l1780">    }</span><a href="#l1780"></a>
<span id="l1781"></span><a href="#l1781"></a>
<span id="l1782">    /**</span><a href="#l1782"></a>
<span id="l1783">     * Issues a LIST command to the server to get the current directory</span><a href="#l1783"></a>
<span id="l1784">     * listing, and returns the InputStream from the data connection.</span><a href="#l1784"></a>
<span id="l1785">     * {@link #completePending()} &lt;b&gt;has&lt;/b&gt; to be called once the application</span><a href="#l1785"></a>
<span id="l1786">     * is finished writing to the stream.</span><a href="#l1786"></a>
<span id="l1787">     *</span><a href="#l1787"></a>
<span id="l1788">     * @param path the pathname of the directory to list, or &lt;code&gt;null&lt;/code&gt;</span><a href="#l1788"></a>
<span id="l1789">     *        for the current working directory.</span><a href="#l1789"></a>
<span id="l1790">     * @return the &lt;code&gt;InputStream&lt;/code&gt; from the resulting data connection</span><a href="#l1790"></a>
<span id="l1791">     * @throws IOException if an error occurs during the transmission.</span><a href="#l1791"></a>
<span id="l1792">     * @see #changeDirectory(String)</span><a href="#l1792"></a>
<span id="l1793">     * @see #listFiles(String)</span><a href="#l1793"></a>
<span id="l1794">     */</span><a href="#l1794"></a>
<span id="l1795">    public InputStream list(String path) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1795"></a>
<span id="l1796">        Socket s;</span><a href="#l1796"></a>
<span id="l1797">        s = openDataConnection(path == null ? &quot;LIST&quot; : &quot;LIST &quot; + path);</span><a href="#l1797"></a>
<span id="l1798">        if (s != null) {</span><a href="#l1798"></a>
<span id="l1799">            return createInputStream(s.getInputStream());</span><a href="#l1799"></a>
<span id="l1800">        }</span><a href="#l1800"></a>
<span id="l1801">        return null;</span><a href="#l1801"></a>
<span id="l1802">    }</span><a href="#l1802"></a>
<span id="l1803"></span><a href="#l1803"></a>
<span id="l1804">    /**</span><a href="#l1804"></a>
<span id="l1805">     * Issues a NLST path command to server to get the specified directory</span><a href="#l1805"></a>
<span id="l1806">     * content. It differs from {@link #list(String)} method by the fact that</span><a href="#l1806"></a>
<span id="l1807">     * it will only list the file names which would make the parsing of the</span><a href="#l1807"></a>
<span id="l1808">     * somewhat easier.</span><a href="#l1808"></a>
<span id="l1809">     *</span><a href="#l1809"></a>
<span id="l1810">     * {@link #completePending()} &lt;b&gt;has&lt;/b&gt; to be called once the application</span><a href="#l1810"></a>
<span id="l1811">     * is finished writing to the stream.</span><a href="#l1811"></a>
<span id="l1812">     *</span><a href="#l1812"></a>
<span id="l1813">     * @param path a &lt;code&gt;String&lt;/code&gt; containing the pathname of the</span><a href="#l1813"></a>
<span id="l1814">     *        directory to list or &lt;code&gt;null&lt;/code&gt; for the current working</span><a href="#l1814"></a>
<span id="l1815">     *        directory.</span><a href="#l1815"></a>
<span id="l1816">     * @return the &lt;code&gt;InputStream&lt;/code&gt; from the resulting data connection</span><a href="#l1816"></a>
<span id="l1817">     * @throws IOException if an error occurs during the transmission.</span><a href="#l1817"></a>
<span id="l1818">     */</span><a href="#l1818"></a>
<span id="l1819">    public InputStream nameList(String path) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1819"></a>
<span id="l1820">        Socket s;</span><a href="#l1820"></a>
<span id="l1821">        s = openDataConnection(path == null ? &quot;NLST&quot; : &quot;NLST &quot; + path);</span><a href="#l1821"></a>
<span id="l1822">        if (s != null) {</span><a href="#l1822"></a>
<span id="l1823">            return createInputStream(s.getInputStream());</span><a href="#l1823"></a>
<span id="l1824">        }</span><a href="#l1824"></a>
<span id="l1825">        return null;</span><a href="#l1825"></a>
<span id="l1826">    }</span><a href="#l1826"></a>
<span id="l1827"></span><a href="#l1827"></a>
<span id="l1828">    /**</span><a href="#l1828"></a>
<span id="l1829">     * Issues the SIZE [path] command to the server to get the size of a</span><a href="#l1829"></a>
<span id="l1830">     * specific file on the server.</span><a href="#l1830"></a>
<span id="l1831">     * Note that this command may not be supported by the server. In which</span><a href="#l1831"></a>
<span id="l1832">     * case -1 will be returned.</span><a href="#l1832"></a>
<span id="l1833">     *</span><a href="#l1833"></a>
<span id="l1834">     * @param path a &lt;code&gt;String&lt;/code&gt; containing the pathname of the</span><a href="#l1834"></a>
<span id="l1835">     *        file.</span><a href="#l1835"></a>
<span id="l1836">     * @return a &lt;code&gt;long&lt;/code&gt; containing the size of the file or -1 if</span><a href="#l1836"></a>
<span id="l1837">     *         the server returned an error, which can be checked with</span><a href="#l1837"></a>
<span id="l1838">     *         {@link #getLastReplyCode()}.</span><a href="#l1838"></a>
<span id="l1839">     * @throws IOException if an error occurs during the transmission.</span><a href="#l1839"></a>
<span id="l1840">     */</span><a href="#l1840"></a>
<span id="l1841">    public long getSize(String path) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1841"></a>
<span id="l1842">        if (path == null || path.length() == 0) {</span><a href="#l1842"></a>
<span id="l1843">            throw new IllegalArgumentException(&quot;path can't be null or empty&quot;);</span><a href="#l1843"></a>
<span id="l1844">        }</span><a href="#l1844"></a>
<span id="l1845">        issueCommandCheck(&quot;SIZE &quot; + path);</span><a href="#l1845"></a>
<span id="l1846">        if (lastReplyCode == FtpReplyCode.FILE_STATUS) {</span><a href="#l1846"></a>
<span id="l1847">            String s = getResponseString();</span><a href="#l1847"></a>
<span id="l1848">            s = s.substring(4, s.length() - 1);</span><a href="#l1848"></a>
<span id="l1849">            return Long.parseLong(s);</span><a href="#l1849"></a>
<span id="l1850">        }</span><a href="#l1850"></a>
<span id="l1851">        return -1;</span><a href="#l1851"></a>
<span id="l1852">    }</span><a href="#l1852"></a>
<span id="l1853">    private static String[] MDTMformats = {</span><a href="#l1853"></a>
<span id="l1854">        &quot;yyyyMMddHHmmss.SSS&quot;,</span><a href="#l1854"></a>
<span id="l1855">        &quot;yyyyMMddHHmmss&quot;</span><a href="#l1855"></a>
<span id="l1856">    };</span><a href="#l1856"></a>
<span id="l1857">    private static SimpleDateFormat[] dateFormats = new SimpleDateFormat[MDTMformats.length];</span><a href="#l1857"></a>
<span id="l1858"></span><a href="#l1858"></a>
<span id="l1859">    static {</span><a href="#l1859"></a>
<span id="l1860">        for (int i = 0; i &lt; MDTMformats.length; i++) {</span><a href="#l1860"></a>
<span id="l1861">            dateFormats[i] = new SimpleDateFormat(MDTMformats[i]);</span><a href="#l1861"></a>
<span id="l1862">            dateFormats[i].setTimeZone(TimeZone.getTimeZone(&quot;GMT&quot;));</span><a href="#l1862"></a>
<span id="l1863">        }</span><a href="#l1863"></a>
<span id="l1864">    }</span><a href="#l1864"></a>
<span id="l1865"></span><a href="#l1865"></a>
<span id="l1866">    /**</span><a href="#l1866"></a>
<span id="l1867">     * Issues the MDTM [path] command to the server to get the modification</span><a href="#l1867"></a>
<span id="l1868">     * time of a specific file on the server.</span><a href="#l1868"></a>
<span id="l1869">     * Note that this command may not be supported by the server, in which</span><a href="#l1869"></a>
<span id="l1870">     * case &lt;code&gt;null&lt;/code&gt; will be returned.</span><a href="#l1870"></a>
<span id="l1871">     *</span><a href="#l1871"></a>
<span id="l1872">     * @param path a &lt;code&gt;String&lt;/code&gt; containing the pathname of the file.</span><a href="#l1872"></a>
<span id="l1873">     * @return a &lt;code&gt;Date&lt;/code&gt; representing the last modification time</span><a href="#l1873"></a>
<span id="l1874">     *         or &lt;code&gt;null&lt;/code&gt; if the server returned an error, which</span><a href="#l1874"></a>
<span id="l1875">     *         can be checked with {@link #getLastReplyCode()}.</span><a href="#l1875"></a>
<span id="l1876">     * @throws IOException if an error occurs during the transmission.</span><a href="#l1876"></a>
<span id="l1877">     */</span><a href="#l1877"></a>
<span id="l1878">    public Date getLastModified(String path) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1878"></a>
<span id="l1879">        issueCommandCheck(&quot;MDTM &quot; + path);</span><a href="#l1879"></a>
<span id="l1880">        if (lastReplyCode == FtpReplyCode.FILE_STATUS) {</span><a href="#l1880"></a>
<span id="l1881">            String s = getResponseString().substring(4);</span><a href="#l1881"></a>
<span id="l1882">            Date d = null;</span><a href="#l1882"></a>
<span id="l1883">            for (SimpleDateFormat dateFormat : dateFormats) {</span><a href="#l1883"></a>
<span id="l1884">                try {</span><a href="#l1884"></a>
<span id="l1885">                    d = dateFormat.parse(s);</span><a href="#l1885"></a>
<span id="l1886">                } catch (ParseException ex) {</span><a href="#l1886"></a>
<span id="l1887">                }</span><a href="#l1887"></a>
<span id="l1888">                if (d != null) {</span><a href="#l1888"></a>
<span id="l1889">                    return d;</span><a href="#l1889"></a>
<span id="l1890">                }</span><a href="#l1890"></a>
<span id="l1891">            }</span><a href="#l1891"></a>
<span id="l1892">        }</span><a href="#l1892"></a>
<span id="l1893">        return null;</span><a href="#l1893"></a>
<span id="l1894">    }</span><a href="#l1894"></a>
<span id="l1895"></span><a href="#l1895"></a>
<span id="l1896">    /**</span><a href="#l1896"></a>
<span id="l1897">     * Sets the parser used to handle the directory output to the specified</span><a href="#l1897"></a>
<span id="l1898">     * one. By default the parser is set to one that can handle most FTP</span><a href="#l1898"></a>
<span id="l1899">     * servers output (Unix base mostly). However it may be necessary for</span><a href="#l1899"></a>
<span id="l1900">     * and application to provide its own parser due to some uncommon</span><a href="#l1900"></a>
<span id="l1901">     * output format.</span><a href="#l1901"></a>
<span id="l1902">     *</span><a href="#l1902"></a>
<span id="l1903">     * @param p The &lt;code&gt;FtpDirParser&lt;/code&gt; to use.</span><a href="#l1903"></a>
<span id="l1904">     * @see #listFiles(String)</span><a href="#l1904"></a>
<span id="l1905">     */</span><a href="#l1905"></a>
<span id="l1906">    public sun.net.ftp.FtpClient setDirParser(FtpDirParser p) {</span><a href="#l1906"></a>
<span id="l1907">        parser = p;</span><a href="#l1907"></a>
<span id="l1908">        return this;</span><a href="#l1908"></a>
<span id="l1909">    }</span><a href="#l1909"></a>
<span id="l1910"></span><a href="#l1910"></a>
<span id="l1911">    private class FtpFileIterator implements Iterator&lt;FtpDirEntry&gt;, Closeable {</span><a href="#l1911"></a>
<span id="l1912"></span><a href="#l1912"></a>
<span id="l1913">        private BufferedReader in = null;</span><a href="#l1913"></a>
<span id="l1914">        private FtpDirEntry nextFile = null;</span><a href="#l1914"></a>
<span id="l1915">        private FtpDirParser fparser = null;</span><a href="#l1915"></a>
<span id="l1916">        private boolean eof = false;</span><a href="#l1916"></a>
<span id="l1917"></span><a href="#l1917"></a>
<span id="l1918">        public FtpFileIterator(FtpDirParser p, BufferedReader in) {</span><a href="#l1918"></a>
<span id="l1919">            this.in = in;</span><a href="#l1919"></a>
<span id="l1920">            this.fparser = p;</span><a href="#l1920"></a>
<span id="l1921">            readNext();</span><a href="#l1921"></a>
<span id="l1922">        }</span><a href="#l1922"></a>
<span id="l1923"></span><a href="#l1923"></a>
<span id="l1924">        private void readNext() {</span><a href="#l1924"></a>
<span id="l1925">            nextFile = null;</span><a href="#l1925"></a>
<span id="l1926">            if (eof) {</span><a href="#l1926"></a>
<span id="l1927">                return;</span><a href="#l1927"></a>
<span id="l1928">            }</span><a href="#l1928"></a>
<span id="l1929">            String line = null;</span><a href="#l1929"></a>
<span id="l1930">            try {</span><a href="#l1930"></a>
<span id="l1931">                do {</span><a href="#l1931"></a>
<span id="l1932">                    line = in.readLine();</span><a href="#l1932"></a>
<span id="l1933">                    if (line != null) {</span><a href="#l1933"></a>
<span id="l1934">                        nextFile = fparser.parseLine(line);</span><a href="#l1934"></a>
<span id="l1935">                        if (nextFile != null) {</span><a href="#l1935"></a>
<span id="l1936">                            return;</span><a href="#l1936"></a>
<span id="l1937">                        }</span><a href="#l1937"></a>
<span id="l1938">                    }</span><a href="#l1938"></a>
<span id="l1939">                } while (line != null);</span><a href="#l1939"></a>
<span id="l1940">                in.close();</span><a href="#l1940"></a>
<span id="l1941">            } catch (IOException iOException) {</span><a href="#l1941"></a>
<span id="l1942">            }</span><a href="#l1942"></a>
<span id="l1943">            eof = true;</span><a href="#l1943"></a>
<span id="l1944">        }</span><a href="#l1944"></a>
<span id="l1945"></span><a href="#l1945"></a>
<span id="l1946">        public boolean hasNext() {</span><a href="#l1946"></a>
<span id="l1947">            return nextFile != null;</span><a href="#l1947"></a>
<span id="l1948">        }</span><a href="#l1948"></a>
<span id="l1949"></span><a href="#l1949"></a>
<span id="l1950">        public FtpDirEntry next() {</span><a href="#l1950"></a>
<span id="l1951">            FtpDirEntry ret = nextFile;</span><a href="#l1951"></a>
<span id="l1952">            readNext();</span><a href="#l1952"></a>
<span id="l1953">            return ret;</span><a href="#l1953"></a>
<span id="l1954">        }</span><a href="#l1954"></a>
<span id="l1955"></span><a href="#l1955"></a>
<span id="l1956">        public void remove() {</span><a href="#l1956"></a>
<span id="l1957">            throw new UnsupportedOperationException(&quot;Not supported yet.&quot;);</span><a href="#l1957"></a>
<span id="l1958">        }</span><a href="#l1958"></a>
<span id="l1959"></span><a href="#l1959"></a>
<span id="l1960">        public void close() throws IOException {</span><a href="#l1960"></a>
<span id="l1961">            if (in != null &amp;&amp; !eof) {</span><a href="#l1961"></a>
<span id="l1962">                in.close();</span><a href="#l1962"></a>
<span id="l1963">            }</span><a href="#l1963"></a>
<span id="l1964">            eof = true;</span><a href="#l1964"></a>
<span id="l1965">            nextFile = null;</span><a href="#l1965"></a>
<span id="l1966">        }</span><a href="#l1966"></a>
<span id="l1967">    }</span><a href="#l1967"></a>
<span id="l1968"></span><a href="#l1968"></a>
<span id="l1969">    /**</span><a href="#l1969"></a>
<span id="l1970">     * Issues a MLSD command to the server to get the specified directory</span><a href="#l1970"></a>
<span id="l1971">     * listing and applies the current parser to create an Iterator of</span><a href="#l1971"></a>
<span id="l1972">     * {@link java.net.ftp.FtpDirEntry}. Note that the Iterator returned is also a</span><a href="#l1972"></a>
<span id="l1973">     * {@link java.io.Closeable}.</span><a href="#l1973"></a>
<span id="l1974">     * If the server doesn't support the MLSD command, the LIST command is used</span><a href="#l1974"></a>
<span id="l1975">     * instead.</span><a href="#l1975"></a>
<span id="l1976">     *</span><a href="#l1976"></a>
<span id="l1977">     * {@link #completePending()} &lt;b&gt;has&lt;/b&gt; to be called once the application</span><a href="#l1977"></a>
<span id="l1978">     * is finished iterating through the files.</span><a href="#l1978"></a>
<span id="l1979">     *</span><a href="#l1979"></a>
<span id="l1980">     * @param path the pathname of the directory to list or &lt;code&gt;null&lt;/code&gt;</span><a href="#l1980"></a>
<span id="l1981">     *        for the current working directoty.</span><a href="#l1981"></a>
<span id="l1982">     * @return a &lt;code&gt;Iterator&lt;/code&gt; of files or &lt;code&gt;null&lt;/code&gt; if the</span><a href="#l1982"></a>
<span id="l1983">     *         command failed.</span><a href="#l1983"></a>
<span id="l1984">     * @throws IOException if an error occurred during the transmission</span><a href="#l1984"></a>
<span id="l1985">     * @see #setDirParser(FtpDirParser)</span><a href="#l1985"></a>
<span id="l1986">     * @see #changeDirectory(String)</span><a href="#l1986"></a>
<span id="l1987">     */</span><a href="#l1987"></a>
<span id="l1988">    public Iterator&lt;FtpDirEntry&gt; listFiles(String path) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l1988"></a>
<span id="l1989">        Socket s = null;</span><a href="#l1989"></a>
<span id="l1990">        BufferedReader sin = null;</span><a href="#l1990"></a>
<span id="l1991">        try {</span><a href="#l1991"></a>
<span id="l1992">            s = openDataConnection(path == null ? &quot;MLSD&quot; : &quot;MLSD &quot; + path);</span><a href="#l1992"></a>
<span id="l1993">        } catch (sun.net.ftp.FtpProtocolException FtpException) {</span><a href="#l1993"></a>
<span id="l1994">            // The server doesn't understand new MLSD command, ignore and fall</span><a href="#l1994"></a>
<span id="l1995">            // back to LIST</span><a href="#l1995"></a>
<span id="l1996">        }</span><a href="#l1996"></a>
<span id="l1997"></span><a href="#l1997"></a>
<span id="l1998">        if (s != null) {</span><a href="#l1998"></a>
<span id="l1999">            sin = new BufferedReader(new InputStreamReader(s.getInputStream()));</span><a href="#l1999"></a>
<span id="l2000">            return new FtpFileIterator(mlsxParser, sin);</span><a href="#l2000"></a>
<span id="l2001">        } else {</span><a href="#l2001"></a>
<span id="l2002">            s = openDataConnection(path == null ? &quot;LIST&quot; : &quot;LIST &quot; + path);</span><a href="#l2002"></a>
<span id="l2003">            if (s != null) {</span><a href="#l2003"></a>
<span id="l2004">                sin = new BufferedReader(new InputStreamReader(s.getInputStream()));</span><a href="#l2004"></a>
<span id="l2005">                return new FtpFileIterator(parser, sin);</span><a href="#l2005"></a>
<span id="l2006">            }</span><a href="#l2006"></a>
<span id="l2007">        }</span><a href="#l2007"></a>
<span id="l2008">        return null;</span><a href="#l2008"></a>
<span id="l2009">    }</span><a href="#l2009"></a>
<span id="l2010"></span><a href="#l2010"></a>
<span id="l2011">    private boolean sendSecurityData(byte[] buf) throws IOException,</span><a href="#l2011"></a>
<span id="l2012">            sun.net.ftp.FtpProtocolException {</span><a href="#l2012"></a>
<span id="l2013">        BASE64Encoder encoder = new BASE64Encoder();</span><a href="#l2013"></a>
<span id="l2014">        String s = encoder.encode(buf);</span><a href="#l2014"></a>
<span id="l2015">        return issueCommand(&quot;ADAT &quot; + s);</span><a href="#l2015"></a>
<span id="l2016">    }</span><a href="#l2016"></a>
<span id="l2017"></span><a href="#l2017"></a>
<span id="l2018">    private byte[] getSecurityData() {</span><a href="#l2018"></a>
<span id="l2019">        String s = getLastResponseString();</span><a href="#l2019"></a>
<span id="l2020">        if (s.substring(4, 9).equalsIgnoreCase(&quot;ADAT=&quot;)) {</span><a href="#l2020"></a>
<span id="l2021">            BASE64Decoder decoder = new BASE64Decoder();</span><a href="#l2021"></a>
<span id="l2022">            try {</span><a href="#l2022"></a>
<span id="l2023">                // Need to get rid of the leading '315 ADAT='</span><a href="#l2023"></a>
<span id="l2024">                // and the trailing newline</span><a href="#l2024"></a>
<span id="l2025">                return decoder.decodeBuffer(s.substring(9, s.length() - 1));</span><a href="#l2025"></a>
<span id="l2026">            } catch (IOException e) {</span><a href="#l2026"></a>
<span id="l2027">                //</span><a href="#l2027"></a>
<span id="l2028">            }</span><a href="#l2028"></a>
<span id="l2029">        }</span><a href="#l2029"></a>
<span id="l2030">        return null;</span><a href="#l2030"></a>
<span id="l2031">    }</span><a href="#l2031"></a>
<span id="l2032"></span><a href="#l2032"></a>
<span id="l2033">    /**</span><a href="#l2033"></a>
<span id="l2034">     * Attempts to use Kerberos GSSAPI as an authentication mechanism with the</span><a href="#l2034"></a>
<span id="l2035">     * ftp server. This will issue an &lt;code&gt;AUTH GSSAPI&lt;/code&gt; command, and if</span><a href="#l2035"></a>
<span id="l2036">     * it is accepted by the server, will followup with &lt;code&gt;ADAT&lt;/code&gt;</span><a href="#l2036"></a>
<span id="l2037">     * command to exchange the various tokens until authentification is</span><a href="#l2037"></a>
<span id="l2038">     * successful. This conforms to Appendix I of RFC 2228.</span><a href="#l2038"></a>
<span id="l2039">     *</span><a href="#l2039"></a>
<span id="l2040">     * @return &lt;code&gt;true&lt;/code&gt; if authentication was successful.</span><a href="#l2040"></a>
<span id="l2041">     * @throws IOException if an error occurs during the transmission.</span><a href="#l2041"></a>
<span id="l2042">     */</span><a href="#l2042"></a>
<span id="l2043">    public sun.net.ftp.FtpClient useKerberos() throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l2043"></a>
<span id="l2044">        /*</span><a href="#l2044"></a>
<span id="l2045">         * Comment out for the moment since it's not in use and would create</span><a href="#l2045"></a>
<span id="l2046">         * needless cross-package links.</span><a href="#l2046"></a>
<span id="l2047">         *</span><a href="#l2047"></a>
<span id="l2048">        issueCommandCheck(&quot;AUTH GSSAPI&quot;);</span><a href="#l2048"></a>
<span id="l2049">        if (lastReplyCode != FtpReplyCode.NEED_ADAT)</span><a href="#l2049"></a>
<span id="l2050">        throw new sun.net.ftp.FtpProtocolException(&quot;Unexpected reply from server&quot;);</span><a href="#l2050"></a>
<span id="l2051">        try {</span><a href="#l2051"></a>
<span id="l2052">        GSSManager manager = GSSManager.getInstance();</span><a href="#l2052"></a>
<span id="l2053">        GSSName name = manager.createName(&quot;SERVICE:ftp@&quot;+</span><a href="#l2053"></a>
<span id="l2054">        serverAddr.getHostName(), null);</span><a href="#l2054"></a>
<span id="l2055">        GSSContext context = manager.createContext(name, null, null,</span><a href="#l2055"></a>
<span id="l2056">        GSSContext.DEFAULT_LIFETIME);</span><a href="#l2056"></a>
<span id="l2057">        context.requestMutualAuth(true);</span><a href="#l2057"></a>
<span id="l2058">        context.requestReplayDet(true);</span><a href="#l2058"></a>
<span id="l2059">        context.requestSequenceDet(true);</span><a href="#l2059"></a>
<span id="l2060">        context.requestCredDeleg(true);</span><a href="#l2060"></a>
<span id="l2061">        byte []inToken = new byte[0];</span><a href="#l2061"></a>
<span id="l2062">        while (!context.isEstablished()) {</span><a href="#l2062"></a>
<span id="l2063">        byte[] outToken</span><a href="#l2063"></a>
<span id="l2064">        = context.initSecContext(inToken, 0, inToken.length);</span><a href="#l2064"></a>
<span id="l2065">        // send the output token if generated</span><a href="#l2065"></a>
<span id="l2066">        if (outToken != null) {</span><a href="#l2066"></a>
<span id="l2067">        if (sendSecurityData(outToken)) {</span><a href="#l2067"></a>
<span id="l2068">        inToken = getSecurityData();</span><a href="#l2068"></a>
<span id="l2069">        }</span><a href="#l2069"></a>
<span id="l2070">        }</span><a href="#l2070"></a>
<span id="l2071">        }</span><a href="#l2071"></a>
<span id="l2072">        loggedIn = true;</span><a href="#l2072"></a>
<span id="l2073">        } catch (GSSException e) {</span><a href="#l2073"></a>
<span id="l2074"></span><a href="#l2074"></a>
<span id="l2075">        }</span><a href="#l2075"></a>
<span id="l2076">         */</span><a href="#l2076"></a>
<span id="l2077">        return this;</span><a href="#l2077"></a>
<span id="l2078">    }</span><a href="#l2078"></a>
<span id="l2079"></span><a href="#l2079"></a>
<span id="l2080">    /**</span><a href="#l2080"></a>
<span id="l2081">     * Returns the Welcome string the server sent during initial connection.</span><a href="#l2081"></a>
<span id="l2082">     *</span><a href="#l2082"></a>
<span id="l2083">     * @return a &lt;code&gt;String&lt;/code&gt; containing the message the server</span><a href="#l2083"></a>
<span id="l2084">     *         returned during connection or &lt;code&gt;null&lt;/code&gt;.</span><a href="#l2084"></a>
<span id="l2085">     */</span><a href="#l2085"></a>
<span id="l2086">    public String getWelcomeMsg() {</span><a href="#l2086"></a>
<span id="l2087">        return welcomeMsg;</span><a href="#l2087"></a>
<span id="l2088">    }</span><a href="#l2088"></a>
<span id="l2089"></span><a href="#l2089"></a>
<span id="l2090">    /**</span><a href="#l2090"></a>
<span id="l2091">     * Returns the last reply code sent by the server.</span><a href="#l2091"></a>
<span id="l2092">     *</span><a href="#l2092"></a>
<span id="l2093">     * @return the lastReplyCode</span><a href="#l2093"></a>
<span id="l2094">     */</span><a href="#l2094"></a>
<span id="l2095">    public FtpReplyCode getLastReplyCode() {</span><a href="#l2095"></a>
<span id="l2096">        return lastReplyCode;</span><a href="#l2096"></a>
<span id="l2097">    }</span><a href="#l2097"></a>
<span id="l2098"></span><a href="#l2098"></a>
<span id="l2099">    /**</span><a href="#l2099"></a>
<span id="l2100">     * Returns the last response string sent by the server.</span><a href="#l2100"></a>
<span id="l2101">     *</span><a href="#l2101"></a>
<span id="l2102">     * @return the message string, which can be quite long, last returned</span><a href="#l2102"></a>
<span id="l2103">     *         by the server.</span><a href="#l2103"></a>
<span id="l2104">     */</span><a href="#l2104"></a>
<span id="l2105">    public String getLastResponseString() {</span><a href="#l2105"></a>
<span id="l2106">        StringBuffer sb = new StringBuffer();</span><a href="#l2106"></a>
<span id="l2107">        if (serverResponse != null) {</span><a href="#l2107"></a>
<span id="l2108">            for (String l : serverResponse) {</span><a href="#l2108"></a>
<span id="l2109">                if (l != null) {</span><a href="#l2109"></a>
<span id="l2110">                    sb.append(l);</span><a href="#l2110"></a>
<span id="l2111">                }</span><a href="#l2111"></a>
<span id="l2112">            }</span><a href="#l2112"></a>
<span id="l2113">        }</span><a href="#l2113"></a>
<span id="l2114">        return sb.toString();</span><a href="#l2114"></a>
<span id="l2115">    }</span><a href="#l2115"></a>
<span id="l2116"></span><a href="#l2116"></a>
<span id="l2117">    /**</span><a href="#l2117"></a>
<span id="l2118">     * Returns, when available, the size of the latest started transfer.</span><a href="#l2118"></a>
<span id="l2119">     * This is retreived by parsing the response string received as an initial</span><a href="#l2119"></a>
<span id="l2120">     * response to a RETR or similar request.</span><a href="#l2120"></a>
<span id="l2121">     *</span><a href="#l2121"></a>
<span id="l2122">     * @return the size of the latest transfer or -1 if either there was no</span><a href="#l2122"></a>
<span id="l2123">     *         transfer or the information was unavailable.</span><a href="#l2123"></a>
<span id="l2124">     */</span><a href="#l2124"></a>
<span id="l2125">    public long getLastTransferSize() {</span><a href="#l2125"></a>
<span id="l2126">        return lastTransSize;</span><a href="#l2126"></a>
<span id="l2127">    }</span><a href="#l2127"></a>
<span id="l2128"></span><a href="#l2128"></a>
<span id="l2129">    /**</span><a href="#l2129"></a>
<span id="l2130">     * Returns, when available, the remote name of the last transfered file.</span><a href="#l2130"></a>
<span id="l2131">     * This is mainly useful for &quot;put&quot; operation when the unique flag was</span><a href="#l2131"></a>
<span id="l2132">     * set since it allows to recover the unique file name created on the</span><a href="#l2132"></a>
<span id="l2133">     * server which may be different from the one submitted with the command.</span><a href="#l2133"></a>
<span id="l2134">     *</span><a href="#l2134"></a>
<span id="l2135">     * @return the name the latest transfered file remote name, or</span><a href="#l2135"></a>
<span id="l2136">     *         &lt;code&gt;null&lt;/code&gt; if that information is unavailable.</span><a href="#l2136"></a>
<span id="l2137">     */</span><a href="#l2137"></a>
<span id="l2138">    public String getLastFileName() {</span><a href="#l2138"></a>
<span id="l2139">        return lastFileName;</span><a href="#l2139"></a>
<span id="l2140">    }</span><a href="#l2140"></a>
<span id="l2141"></span><a href="#l2141"></a>
<span id="l2142">    /**</span><a href="#l2142"></a>
<span id="l2143">     * Attempts to switch to a secure, encrypted connection. This is done by</span><a href="#l2143"></a>
<span id="l2144">     * sending the &quot;AUTH TLS&quot; command.</span><a href="#l2144"></a>
<span id="l2145">     * &lt;p&gt;See &lt;a href=&quot;http://www.ietf.org/rfc/rfc4217.txt&quot;&gt;RFC 4217&lt;/a&gt;&lt;/p&gt;</span><a href="#l2145"></a>
<span id="l2146">     * If successful this will establish a secure command channel with the</span><a href="#l2146"></a>
<span id="l2147">     * server, it will also make it so that all other transfers (e.g. a RETR</span><a href="#l2147"></a>
<span id="l2148">     * command) will be done over an encrypted channel as well unless a</span><a href="#l2148"></a>
<span id="l2149">     * {@link #reInit()} command or a {@link #endSecureSession()} command is issued.</span><a href="#l2149"></a>
<span id="l2150">     *</span><a href="#l2150"></a>
<span id="l2151">     * @return &lt;code&gt;true&lt;/code&gt; if the operation was successful.</span><a href="#l2151"></a>
<span id="l2152">     * @throws IOException if an error occurred during the transmission.</span><a href="#l2152"></a>
<span id="l2153">     * @see #endSecureSession()</span><a href="#l2153"></a>
<span id="l2154">     */</span><a href="#l2154"></a>
<span id="l2155">    public sun.net.ftp.FtpClient startSecureSession() throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l2155"></a>
<span id="l2156">        if (!isConnected()) {</span><a href="#l2156"></a>
<span id="l2157">            throw new sun.net.ftp.FtpProtocolException(&quot;Not connected yet&quot;, FtpReplyCode.BAD_SEQUENCE);</span><a href="#l2157"></a>
<span id="l2158">        }</span><a href="#l2158"></a>
<span id="l2159">        if (sslFact == null) {</span><a href="#l2159"></a>
<span id="l2160">            try {</span><a href="#l2160"></a>
<span id="l2161">                sslFact = (SSLSocketFactory) SSLSocketFactory.getDefault();</span><a href="#l2161"></a>
<span id="l2162">            } catch (Exception e) {</span><a href="#l2162"></a>
<span id="l2163">                throw new IOException(e.getLocalizedMessage());</span><a href="#l2163"></a>
<span id="l2164">            }</span><a href="#l2164"></a>
<span id="l2165">        }</span><a href="#l2165"></a>
<span id="l2166">        issueCommandCheck(&quot;AUTH TLS&quot;);</span><a href="#l2166"></a>
<span id="l2167">        Socket s = null;</span><a href="#l2167"></a>
<span id="l2168">        try {</span><a href="#l2168"></a>
<span id="l2169">            s = sslFact.createSocket(server, serverAddr.getHostName(), serverAddr.getPort(), true);</span><a href="#l2169"></a>
<span id="l2170">        } catch (javax.net.ssl.SSLException ssle) {</span><a href="#l2170"></a>
<span id="l2171">            try {</span><a href="#l2171"></a>
<span id="l2172">                disconnect();</span><a href="#l2172"></a>
<span id="l2173">            } catch (Exception e) {</span><a href="#l2173"></a>
<span id="l2174">            }</span><a href="#l2174"></a>
<span id="l2175">            throw ssle;</span><a href="#l2175"></a>
<span id="l2176">        }</span><a href="#l2176"></a>
<span id="l2177">        // Remember underlying socket so we can restore it later</span><a href="#l2177"></a>
<span id="l2178">        oldSocket = server;</span><a href="#l2178"></a>
<span id="l2179">        server = s;</span><a href="#l2179"></a>
<span id="l2180">        try {</span><a href="#l2180"></a>
<span id="l2181">            out = new PrintStream(new BufferedOutputStream(server.getOutputStream()),</span><a href="#l2181"></a>
<span id="l2182">                    true, encoding);</span><a href="#l2182"></a>
<span id="l2183">        } catch (UnsupportedEncodingException e) {</span><a href="#l2183"></a>
<span id="l2184">            throw new InternalError(encoding + &quot;encoding not found&quot;, e);</span><a href="#l2184"></a>
<span id="l2185">        }</span><a href="#l2185"></a>
<span id="l2186">        in = new BufferedInputStream(server.getInputStream());</span><a href="#l2186"></a>
<span id="l2187"></span><a href="#l2187"></a>
<span id="l2188">        issueCommandCheck(&quot;PBSZ 0&quot;);</span><a href="#l2188"></a>
<span id="l2189">        issueCommandCheck(&quot;PROT P&quot;);</span><a href="#l2189"></a>
<span id="l2190">        useCrypto = true;</span><a href="#l2190"></a>
<span id="l2191">        return this;</span><a href="#l2191"></a>
<span id="l2192">    }</span><a href="#l2192"></a>
<span id="l2193"></span><a href="#l2193"></a>
<span id="l2194">    /**</span><a href="#l2194"></a>
<span id="l2195">     * Sends a &lt;code&gt;CCC&lt;/code&gt; command followed by a &lt;code&gt;PROT C&lt;/code&gt;</span><a href="#l2195"></a>
<span id="l2196">     * command to the server terminating an encrypted session and reverting</span><a href="#l2196"></a>
<span id="l2197">     * back to a non crypted transmission.</span><a href="#l2197"></a>
<span id="l2198">     *</span><a href="#l2198"></a>
<span id="l2199">     * @return &lt;code&gt;true&lt;/code&gt; if the operation was successful.</span><a href="#l2199"></a>
<span id="l2200">     * @throws IOException if an error occurred during transmission.</span><a href="#l2200"></a>
<span id="l2201">     * @see #startSecureSession()</span><a href="#l2201"></a>
<span id="l2202">     */</span><a href="#l2202"></a>
<span id="l2203">    public sun.net.ftp.FtpClient endSecureSession() throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l2203"></a>
<span id="l2204">        if (!useCrypto) {</span><a href="#l2204"></a>
<span id="l2205">            return this;</span><a href="#l2205"></a>
<span id="l2206">        }</span><a href="#l2206"></a>
<span id="l2207"></span><a href="#l2207"></a>
<span id="l2208">        issueCommandCheck(&quot;CCC&quot;);</span><a href="#l2208"></a>
<span id="l2209">        issueCommandCheck(&quot;PROT C&quot;);</span><a href="#l2209"></a>
<span id="l2210">        useCrypto = false;</span><a href="#l2210"></a>
<span id="l2211">        // Restore previous socket and streams</span><a href="#l2211"></a>
<span id="l2212">        server = oldSocket;</span><a href="#l2212"></a>
<span id="l2213">        oldSocket = null;</span><a href="#l2213"></a>
<span id="l2214">        try {</span><a href="#l2214"></a>
<span id="l2215">            out = new PrintStream(new BufferedOutputStream(server.getOutputStream()),</span><a href="#l2215"></a>
<span id="l2216">                    true, encoding);</span><a href="#l2216"></a>
<span id="l2217">        } catch (UnsupportedEncodingException e) {</span><a href="#l2217"></a>
<span id="l2218">            throw new InternalError(encoding + &quot;encoding not found&quot;, e);</span><a href="#l2218"></a>
<span id="l2219">        }</span><a href="#l2219"></a>
<span id="l2220">        in = new BufferedInputStream(server.getInputStream());</span><a href="#l2220"></a>
<span id="l2221"></span><a href="#l2221"></a>
<span id="l2222">        return this;</span><a href="#l2222"></a>
<span id="l2223">    }</span><a href="#l2223"></a>
<span id="l2224"></span><a href="#l2224"></a>
<span id="l2225">    /**</span><a href="#l2225"></a>
<span id="l2226">     * Sends the &quot;Allocate&quot; (ALLO) command to the server telling it to</span><a href="#l2226"></a>
<span id="l2227">     * pre-allocate the specified number of bytes for the next transfer.</span><a href="#l2227"></a>
<span id="l2228">     *</span><a href="#l2228"></a>
<span id="l2229">     * @param size The number of bytes to allocate.</span><a href="#l2229"></a>
<span id="l2230">     * @return &lt;code&gt;true&lt;/code&gt; if the operation was successful.</span><a href="#l2230"></a>
<span id="l2231">     * @throws IOException if an error occurred during the transmission.</span><a href="#l2231"></a>
<span id="l2232">     */</span><a href="#l2232"></a>
<span id="l2233">    public sun.net.ftp.FtpClient allocate(long size) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l2233"></a>
<span id="l2234">        issueCommandCheck(&quot;ALLO &quot; + size);</span><a href="#l2234"></a>
<span id="l2235">        return this;</span><a href="#l2235"></a>
<span id="l2236">    }</span><a href="#l2236"></a>
<span id="l2237"></span><a href="#l2237"></a>
<span id="l2238">    /**</span><a href="#l2238"></a>
<span id="l2239">     * Sends the &quot;Structure Mount&quot; (SMNT) command to the server. This let the</span><a href="#l2239"></a>
<span id="l2240">     * user mount a different file system data structure without altering his</span><a href="#l2240"></a>
<span id="l2241">     * login or accounting information.</span><a href="#l2241"></a>
<span id="l2242">     *</span><a href="#l2242"></a>
<span id="l2243">     * @param struct a &lt;code&gt;String&lt;/code&gt; containing the name of the</span><a href="#l2243"></a>
<span id="l2244">     *        structure to mount.</span><a href="#l2244"></a>
<span id="l2245">     * @return &lt;code&gt;true&lt;/code&gt; if the operation was successful.</span><a href="#l2245"></a>
<span id="l2246">     * @throws IOException if an error occurred during the transmission.</span><a href="#l2246"></a>
<span id="l2247">     */</span><a href="#l2247"></a>
<span id="l2248">    public sun.net.ftp.FtpClient structureMount(String struct) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l2248"></a>
<span id="l2249">        issueCommandCheck(&quot;SMNT &quot; + struct);</span><a href="#l2249"></a>
<span id="l2250">        return this;</span><a href="#l2250"></a>
<span id="l2251">    }</span><a href="#l2251"></a>
<span id="l2252"></span><a href="#l2252"></a>
<span id="l2253">    /**</span><a href="#l2253"></a>
<span id="l2254">     * Sends a SYST (System) command to the server and returns the String</span><a href="#l2254"></a>
<span id="l2255">     * sent back by the server describing the operating system at the</span><a href="#l2255"></a>
<span id="l2256">     * server.</span><a href="#l2256"></a>
<span id="l2257">     *</span><a href="#l2257"></a>
<span id="l2258">     * @return a &lt;code&gt;String&lt;/code&gt; describing the OS, or &lt;code&gt;null&lt;/code&gt;</span><a href="#l2258"></a>
<span id="l2259">     *         if the operation was not successful.</span><a href="#l2259"></a>
<span id="l2260">     * @throws IOException if an error occurred during the transmission.</span><a href="#l2260"></a>
<span id="l2261">     */</span><a href="#l2261"></a>
<span id="l2262">    public String getSystem() throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l2262"></a>
<span id="l2263">        issueCommandCheck(&quot;SYST&quot;);</span><a href="#l2263"></a>
<span id="l2264">        /*</span><a href="#l2264"></a>
<span id="l2265">         * 215 UNIX Type: L8 Version: SUNOS</span><a href="#l2265"></a>
<span id="l2266">         */</span><a href="#l2266"></a>
<span id="l2267">        String resp = getResponseString();</span><a href="#l2267"></a>
<span id="l2268">        // Get rid of the leading code and blank</span><a href="#l2268"></a>
<span id="l2269">        return resp.substring(4);</span><a href="#l2269"></a>
<span id="l2270">    }</span><a href="#l2270"></a>
<span id="l2271"></span><a href="#l2271"></a>
<span id="l2272">    /**</span><a href="#l2272"></a>
<span id="l2273">     * Sends the HELP command to the server, with an optional command, like</span><a href="#l2273"></a>
<span id="l2274">     * SITE, and returns the text sent back by the server.</span><a href="#l2274"></a>
<span id="l2275">     *</span><a href="#l2275"></a>
<span id="l2276">     * @param cmd the command for which the help is requested or</span><a href="#l2276"></a>
<span id="l2277">     *        &lt;code&gt;null&lt;/code&gt; for the general help</span><a href="#l2277"></a>
<span id="l2278">     * @return a &lt;code&gt;String&lt;/code&gt; containing the text sent back by the</span><a href="#l2278"></a>
<span id="l2279">     *         server, or &lt;code&gt;null&lt;/code&gt; if the command failed.</span><a href="#l2279"></a>
<span id="l2280">     * @throws IOException if an error occurred during transmission</span><a href="#l2280"></a>
<span id="l2281">     */</span><a href="#l2281"></a>
<span id="l2282">    public String getHelp(String cmd) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l2282"></a>
<span id="l2283">        issueCommandCheck(&quot;HELP &quot; + cmd);</span><a href="#l2283"></a>
<span id="l2284">        /**</span><a href="#l2284"></a>
<span id="l2285">         *</span><a href="#l2285"></a>
<span id="l2286">         * HELP</span><a href="#l2286"></a>
<span id="l2287">         * 214-The following commands are implemented.</span><a href="#l2287"></a>
<span id="l2288">         *   USER    EPRT    STRU    ALLO    DELE    SYST    RMD     MDTM    ADAT</span><a href="#l2288"></a>
<span id="l2289">         *   PASS    EPSV    MODE    REST    CWD     STAT    PWD     PROT</span><a href="#l2289"></a>
<span id="l2290">         *   QUIT    LPRT    RETR    RNFR    LIST    HELP    CDUP    PBSZ</span><a href="#l2290"></a>
<span id="l2291">         *   PORT    LPSV    STOR    RNTO    NLST    NOOP    STOU    AUTH</span><a href="#l2291"></a>
<span id="l2292">         *   PASV    TYPE    APPE    ABOR    SITE    MKD     SIZE    CCC</span><a href="#l2292"></a>
<span id="l2293">         * 214 Direct comments to ftp-bugs@jsn.</span><a href="#l2293"></a>
<span id="l2294">         *</span><a href="#l2294"></a>
<span id="l2295">         * HELP SITE</span><a href="#l2295"></a>
<span id="l2296">         * 214-The following SITE commands are implemented.</span><a href="#l2296"></a>
<span id="l2297">         *   UMASK           HELP            GROUPS</span><a href="#l2297"></a>
<span id="l2298">         *   IDLE            ALIAS           CHECKMETHOD</span><a href="#l2298"></a>
<span id="l2299">         *   CHMOD           CDPATH          CHECKSUM</span><a href="#l2299"></a>
<span id="l2300">         * 214 Direct comments to ftp-bugs@jsn.</span><a href="#l2300"></a>
<span id="l2301">         */</span><a href="#l2301"></a>
<span id="l2302">        Vector&lt;String&gt; resp = getResponseStrings();</span><a href="#l2302"></a>
<span id="l2303">        if (resp.size() == 1) {</span><a href="#l2303"></a>
<span id="l2304">            // Single line response</span><a href="#l2304"></a>
<span id="l2305">            return resp.get(0).substring(4);</span><a href="#l2305"></a>
<span id="l2306">        }</span><a href="#l2306"></a>
<span id="l2307">        // on multiple lines answers, like the ones above, remove 1st and last</span><a href="#l2307"></a>
<span id="l2308">        // line, concat the the others.</span><a href="#l2308"></a>
<span id="l2309">        StringBuffer sb = new StringBuffer();</span><a href="#l2309"></a>
<span id="l2310">        for (int i = 1; i &lt; resp.size() - 1; i++) {</span><a href="#l2310"></a>
<span id="l2311">            sb.append(resp.get(i).substring(3));</span><a href="#l2311"></a>
<span id="l2312">        }</span><a href="#l2312"></a>
<span id="l2313">        return sb.toString();</span><a href="#l2313"></a>
<span id="l2314">    }</span><a href="#l2314"></a>
<span id="l2315"></span><a href="#l2315"></a>
<span id="l2316">    /**</span><a href="#l2316"></a>
<span id="l2317">     * Sends the SITE command to the server. This is used by the server</span><a href="#l2317"></a>
<span id="l2318">     * to provide services specific to his system that are essential</span><a href="#l2318"></a>
<span id="l2319">     * to file transfer.</span><a href="#l2319"></a>
<span id="l2320">     *</span><a href="#l2320"></a>
<span id="l2321">     * @param cmd the command to be sent.</span><a href="#l2321"></a>
<span id="l2322">     * @return &lt;code&gt;true&lt;/code&gt; if the command was successful.</span><a href="#l2322"></a>
<span id="l2323">     * @throws IOException if an error occurred during transmission</span><a href="#l2323"></a>
<span id="l2324">     */</span><a href="#l2324"></a>
<span id="l2325">    public sun.net.ftp.FtpClient siteCmd(String cmd) throws sun.net.ftp.FtpProtocolException, IOException {</span><a href="#l2325"></a>
<span id="l2326">        issueCommandCheck(&quot;SITE &quot; + cmd);</span><a href="#l2326"></a>
<span id="l2327">        return this;</span><a href="#l2327"></a>
<span id="l2328">    }</span><a href="#l2328"></a>
<span id="l2329">}</span><a href="#l2329"></a></pre>
<div class="sourcelast"></div>
</div>
</div>
</div>



<div class="container"><div class="main footer">
&copy 2007, <script>document.write(new Date().getFullYear())</script> Oracle and/or its affiliates<br/>
<a href="http://openjdk.java.net/legal/tou/">Terms of Use</a>
&#183; <a href="http://www.oracle.com/us/legal/privacy/">Privacy</a>
&#183; <a href="https://openjdk.java.net/legal/openjdk-trademark-notice.html">Trademarks</a>
</div></div>

</body>
</html>

