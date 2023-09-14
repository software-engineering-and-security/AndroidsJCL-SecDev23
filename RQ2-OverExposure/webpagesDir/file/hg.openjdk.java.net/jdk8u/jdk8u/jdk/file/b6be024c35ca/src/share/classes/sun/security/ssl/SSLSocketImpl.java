<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: b6be024c35ca src/share/classes/sun/security/ssl/SSLSocketImpl.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk8u/jdk8u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/shortlog/b6be024c35ca">log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/graph/b6be024c35ca">graph</a></li>
<li><a href="/jdk8u/jdk8u/jdk/tags">tags</a></li>
<li><a href="/jdk8u/jdk8u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/rev/b6be024c35ca">changeset</a></li>
<li><a href="/jdk8u/jdk8u/jdk/file/b6be024c35ca/src/share/classes/sun/security/ssl/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/ssl/SSLSocketImpl.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/b6be024c35ca/src/share/classes/sun/security/ssl/SSLSocketImpl.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/b6be024c35ca/src/share/classes/sun/security/ssl/SSLSocketImpl.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/b6be024c35ca/src/share/classes/sun/security/ssl/SSLSocketImpl.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/b6be024c35ca/src/share/classes/sun/security/ssl/SSLSocketImpl.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/b6be024c35ca/src/share/classes/sun/security/ssl/SSLSocketImpl.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/ssl/SSLSocketImpl.java @ 13892:b6be024c35ca</h3>

<form class="search" action="/jdk8u/jdk8u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk8u/jdk8u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8234408: Improve TLS session handling
Reviewed-by: andrew</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#97;&#98;&#97;&#107;&#104;&#116;&#105;&#110;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Mon, 25 Nov 2019 09:50:30 -0800</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/795199aad0e9/src/share/classes/sun/security/ssl/SSLSocketImpl.java">795199aad0e9</a> </td>
</tr>
<tr>
 <th class="author">children</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/4546aa3faf37/src/share/classes/sun/security/ssl/SSLSocketImpl.java">4546aa3faf37</a> </td>
</tr>
</table>

<div class="overflow">
<div class="sourcefirst linewraptoggle">line wrap: <a class="linewraplink" href="javascript:toggleLinewrap()">on</a></div>
<div class="sourcefirst"> line source</div>
<pre class="sourcelines stripes4 wrap">
<span id="l1">/*</span><a href="#l1"></a>
<span id="l2"> * Copyright (c) 1996, 2020, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l25"></span><a href="#l25"></a>
<span id="l26"></span><a href="#l26"></a>
<span id="l27">package sun.security.ssl;</span><a href="#l27"></a>
<span id="l28"></span><a href="#l28"></a>
<span id="l29">import java.io.*;</span><a href="#l29"></a>
<span id="l30">import java.net.*;</span><a href="#l30"></a>
<span id="l31">import java.security.GeneralSecurityException;</span><a href="#l31"></a>
<span id="l32">import java.security.AccessController;</span><a href="#l32"></a>
<span id="l33">import java.security.AccessControlContext;</span><a href="#l33"></a>
<span id="l34">import java.security.PrivilegedAction;</span><a href="#l34"></a>
<span id="l35">import java.security.AlgorithmConstraints;</span><a href="#l35"></a>
<span id="l36">import java.util.*;</span><a href="#l36"></a>
<span id="l37">import java.util.concurrent.TimeUnit;</span><a href="#l37"></a>
<span id="l38">import java.util.concurrent.locks.ReentrantLock;</span><a href="#l38"></a>
<span id="l39">import java.util.function.BiFunction;</span><a href="#l39"></a>
<span id="l40"></span><a href="#l40"></a>
<span id="l41">import javax.crypto.BadPaddingException;</span><a href="#l41"></a>
<span id="l42">import javax.net.ssl.*;</span><a href="#l42"></a>
<span id="l43"></span><a href="#l43"></a>
<span id="l44">import sun.misc.JavaNetAccess;</span><a href="#l44"></a>
<span id="l45">import sun.misc.SharedSecrets;</span><a href="#l45"></a>
<span id="l46"></span><a href="#l46"></a>
<span id="l47">/**</span><a href="#l47"></a>
<span id="l48"> * Implementation of an SSL socket.  This is a normal connection type</span><a href="#l48"></a>
<span id="l49"> * socket, implementing SSL over some lower level socket, such as TCP.</span><a href="#l49"></a>
<span id="l50"> * Because it is layered over some lower level socket, it MUST override</span><a href="#l50"></a>
<span id="l51"> * all default socket methods.</span><a href="#l51"></a>
<span id="l52"> *</span><a href="#l52"></a>
<span id="l53"> * &lt;P&gt; This API offers a non-traditional option for establishing SSL</span><a href="#l53"></a>
<span id="l54"> * connections.  You may first establish the connection directly, then pass</span><a href="#l54"></a>
<span id="l55"> * that connection to the SSL socket constructor with a flag saying which</span><a href="#l55"></a>
<span id="l56"> * role should be taken in the handshake protocol.  (The two ends of the</span><a href="#l56"></a>
<span id="l57"> * connection must not choose the same role!)  This allows setup of SSL</span><a href="#l57"></a>
<span id="l58"> * proxying or tunneling, and also allows the kind of &quot;role reversal&quot;</span><a href="#l58"></a>
<span id="l59"> * that is required for most FTP data transfers.</span><a href="#l59"></a>
<span id="l60"> *</span><a href="#l60"></a>
<span id="l61"> * @see javax.net.ssl.SSLSocket</span><a href="#l61"></a>
<span id="l62"> * @see SSLServerSocket</span><a href="#l62"></a>
<span id="l63"> *</span><a href="#l63"></a>
<span id="l64"> * @author David Brownell</span><a href="#l64"></a>
<span id="l65"> */</span><a href="#l65"></a>
<span id="l66">final public class SSLSocketImpl extends BaseSSLSocketImpl {</span><a href="#l66"></a>
<span id="l67"></span><a href="#l67"></a>
<span id="l68">    /*</span><a href="#l68"></a>
<span id="l69">     * ERROR HANDLING GUIDELINES</span><a href="#l69"></a>
<span id="l70">     * (which exceptions to throw and catch and which not to throw and catch)</span><a href="#l70"></a>
<span id="l71">     *</span><a href="#l71"></a>
<span id="l72">     * . if there is an IOException (SocketException) when accessing the</span><a href="#l72"></a>
<span id="l73">     *   underlying Socket, pass it through</span><a href="#l73"></a>
<span id="l74">     *</span><a href="#l74"></a>
<span id="l75">     * . do not throw IOExceptions, throw SSLExceptions (or a subclass)</span><a href="#l75"></a>
<span id="l76">     *</span><a href="#l76"></a>
<span id="l77">     * . for internal errors (things that indicate a bug in JSSE or a</span><a href="#l77"></a>
<span id="l78">     *   grossly misconfigured J2RE), throw either an SSLException or</span><a href="#l78"></a>
<span id="l79">     *   a RuntimeException at your convenience.</span><a href="#l79"></a>
<span id="l80">     *</span><a href="#l80"></a>
<span id="l81">     * . handshaking code (Handshaker or HandshakeMessage) should generally</span><a href="#l81"></a>
<span id="l82">     *   pass through exceptions, but can handle them if they know what to</span><a href="#l82"></a>
<span id="l83">     *   do.</span><a href="#l83"></a>
<span id="l84">     *</span><a href="#l84"></a>
<span id="l85">     * . exception chaining should be used for all new code. If you happen</span><a href="#l85"></a>
<span id="l86">     *   to touch old code that does not use chaining, you should change it.</span><a href="#l86"></a>
<span id="l87">     *</span><a href="#l87"></a>
<span id="l88">     * . there is a top level exception handler that sits at all entry</span><a href="#l88"></a>
<span id="l89">     *   points from application code to SSLSocket read/write code. It</span><a href="#l89"></a>
<span id="l90">     *   makes sure that all errors are handled (see handleException()).</span><a href="#l90"></a>
<span id="l91">     *</span><a href="#l91"></a>
<span id="l92">     * . JSSE internal code should generally not call close(), call</span><a href="#l92"></a>
<span id="l93">     *   closeInternal().</span><a href="#l93"></a>
<span id="l94">     */</span><a href="#l94"></a>
<span id="l95"></span><a href="#l95"></a>
<span id="l96">    /*</span><a href="#l96"></a>
<span id="l97">     * There's a state machine associated with each connection, which</span><a href="#l97"></a>
<span id="l98">     * among other roles serves to negotiate session changes.</span><a href="#l98"></a>
<span id="l99">     *</span><a href="#l99"></a>
<span id="l100">     * - START with constructor, until the TCP connection's around.</span><a href="#l100"></a>
<span id="l101">     * - HANDSHAKE picks session parameters before allowing traffic.</span><a href="#l101"></a>
<span id="l102">     *          There are many substates due to sequencing requirements</span><a href="#l102"></a>
<span id="l103">     *          for handshake messages.</span><a href="#l103"></a>
<span id="l104">     * - DATA may be transmitted.</span><a href="#l104"></a>
<span id="l105">     * - RENEGOTIATE state allows concurrent data and handshaking</span><a href="#l105"></a>
<span id="l106">     *          traffic (&quot;same&quot; substates as HANDSHAKE), and terminates</span><a href="#l106"></a>
<span id="l107">     *          in selection of new session (and connection) parameters</span><a href="#l107"></a>
<span id="l108">     * - ERROR state immediately precedes abortive disconnect.</span><a href="#l108"></a>
<span id="l109">     * - SENT_CLOSE sent a close_notify to the peer. For layered,</span><a href="#l109"></a>
<span id="l110">     *          non-autoclose socket, must now read close_notify</span><a href="#l110"></a>
<span id="l111">     *          from peer before closing the connection. For nonlayered or</span><a href="#l111"></a>
<span id="l112">     *          non-autoclose socket, close connection and go onto</span><a href="#l112"></a>
<span id="l113">     *          cs_CLOSED state.</span><a href="#l113"></a>
<span id="l114">     * - CLOSED after sending close_notify alert, &amp; socket is closed.</span><a href="#l114"></a>
<span id="l115">     *          SSL connection objects are not reused.</span><a href="#l115"></a>
<span id="l116">     * - APP_CLOSED once the application calls close(). Then it behaves like</span><a href="#l116"></a>
<span id="l117">     *          a closed socket, e.g.. getInputStream() throws an Exception.</span><a href="#l117"></a>
<span id="l118">     *</span><a href="#l118"></a>
<span id="l119">     * State affects what SSL record types may legally be sent:</span><a href="#l119"></a>
<span id="l120">     *</span><a href="#l120"></a>
<span id="l121">     * - Handshake ... only in HANDSHAKE and RENEGOTIATE states</span><a href="#l121"></a>
<span id="l122">     * - App Data ... only in DATA and RENEGOTIATE states</span><a href="#l122"></a>
<span id="l123">     * - Alert ... in HANDSHAKE, DATA, RENEGOTIATE</span><a href="#l123"></a>
<span id="l124">     *</span><a href="#l124"></a>
<span id="l125">     * Re what may be received:  same as what may be sent, except that</span><a href="#l125"></a>
<span id="l126">     * HandshakeRequest handshaking messages can come from servers even</span><a href="#l126"></a>
<span id="l127">     * in the application data state, to request entry to RENEGOTIATE.</span><a href="#l127"></a>
<span id="l128">     *</span><a href="#l128"></a>
<span id="l129">     * The state machine within HANDSHAKE and RENEGOTIATE states controls</span><a href="#l129"></a>
<span id="l130">     * the pending session, not the connection state, until the change</span><a href="#l130"></a>
<span id="l131">     * cipher spec and &quot;Finished&quot; handshake messages are processed and</span><a href="#l131"></a>
<span id="l132">     * make the &quot;new&quot; session become the current one.</span><a href="#l132"></a>
<span id="l133">     *</span><a href="#l133"></a>
<span id="l134">     * NOTE: details of the SMs always need to be nailed down better.</span><a href="#l134"></a>
<span id="l135">     * The text above illustrates the core ideas.</span><a href="#l135"></a>
<span id="l136">     *</span><a href="#l136"></a>
<span id="l137">     *                +----&gt;-------+------&gt;---------&gt;-------+</span><a href="#l137"></a>
<span id="l138">     *                |            |                        |</span><a href="#l138"></a>
<span id="l139">     *     &lt;-----&lt;    ^            ^  &lt;-----&lt;               v</span><a href="#l139"></a>
<span id="l140">     *START&gt;-----&gt;HANDSHAKE&gt;-----&gt;DATA&gt;-----&gt;RENEGOTIATE  SENT_CLOSE</span><a href="#l140"></a>
<span id="l141">     *                v            v               v        |   |</span><a href="#l141"></a>
<span id="l142">     *                |            |               |        |   v</span><a href="#l142"></a>
<span id="l143">     *                +------------+---------------+        v ERROR</span><a href="#l143"></a>
<span id="l144">     *                |                                     |   |</span><a href="#l144"></a>
<span id="l145">     *                v                                     |   |</span><a href="#l145"></a>
<span id="l146">     *               ERROR&gt;------&gt;-----&gt;CLOSED&lt;--------&lt;----+-- +</span><a href="#l146"></a>
<span id="l147">     *                                     |</span><a href="#l147"></a>
<span id="l148">     *                                     v</span><a href="#l148"></a>
<span id="l149">     *                                 APP_CLOSED</span><a href="#l149"></a>
<span id="l150">     *</span><a href="#l150"></a>
<span id="l151">     * ALSO, note that the the purpose of handshaking (renegotiation is</span><a href="#l151"></a>
<span id="l152">     * included) is to assign a different, and perhaps new, session to</span><a href="#l152"></a>
<span id="l153">     * the connection.  The SSLv3 spec is a bit confusing on that new</span><a href="#l153"></a>
<span id="l154">     * protocol feature.</span><a href="#l154"></a>
<span id="l155">     */</span><a href="#l155"></a>
<span id="l156">    private static final int    cs_START = 0;</span><a href="#l156"></a>
<span id="l157">    private static final int    cs_HANDSHAKE = 1;</span><a href="#l157"></a>
<span id="l158">    private static final int    cs_DATA = 2;</span><a href="#l158"></a>
<span id="l159">    private static final int    cs_RENEGOTIATE = 3;</span><a href="#l159"></a>
<span id="l160">    private static final int    cs_ERROR = 4;</span><a href="#l160"></a>
<span id="l161">    private static final int   cs_SENT_CLOSE = 5;</span><a href="#l161"></a>
<span id="l162">    private static final int    cs_CLOSED = 6;</span><a href="#l162"></a>
<span id="l163">    private static final int    cs_APP_CLOSED = 7;</span><a href="#l163"></a>
<span id="l164"></span><a href="#l164"></a>
<span id="l165"></span><a href="#l165"></a>
<span id="l166">    /*</span><a href="#l166"></a>
<span id="l167">     * Client authentication be off, requested, or required.</span><a href="#l167"></a>
<span id="l168">     *</span><a href="#l168"></a>
<span id="l169">     * Migrated to SSLEngineImpl:</span><a href="#l169"></a>
<span id="l170">     *    clauth_none/cl_auth_requested/clauth_required</span><a href="#l170"></a>
<span id="l171">     */</span><a href="#l171"></a>
<span id="l172"></span><a href="#l172"></a>
<span id="l173">    /*</span><a href="#l173"></a>
<span id="l174">     * Drives the protocol state machine.</span><a href="#l174"></a>
<span id="l175">     */</span><a href="#l175"></a>
<span id="l176">    private volatile int        connectionState;</span><a href="#l176"></a>
<span id="l177"></span><a href="#l177"></a>
<span id="l178">    /*</span><a href="#l178"></a>
<span id="l179">     * Flag indicating if the next record we receive MUST be a Finished</span><a href="#l179"></a>
<span id="l180">     * message. Temporarily set during the handshake to ensure that</span><a href="#l180"></a>
<span id="l181">     * a change cipher spec message is followed by a finished message.</span><a href="#l181"></a>
<span id="l182">     */</span><a href="#l182"></a>
<span id="l183">    private boolean             expectingFinished;</span><a href="#l183"></a>
<span id="l184"></span><a href="#l184"></a>
<span id="l185">    /*</span><a href="#l185"></a>
<span id="l186">     * For improved diagnostics, we detail connection closure</span><a href="#l186"></a>
<span id="l187">     * If the socket is closed (connectionState &gt;= cs_ERROR),</span><a href="#l187"></a>
<span id="l188">     * closeReason != null indicates if the socket was closed</span><a href="#l188"></a>
<span id="l189">     * because of an error or because or normal shutdown.</span><a href="#l189"></a>
<span id="l190">     */</span><a href="#l190"></a>
<span id="l191">    private SSLException        closeReason;</span><a href="#l191"></a>
<span id="l192"></span><a href="#l192"></a>
<span id="l193">    /*</span><a href="#l193"></a>
<span id="l194">     * Per-connection private state that doesn't change when the</span><a href="#l194"></a>
<span id="l195">     * session is changed.</span><a href="#l195"></a>
<span id="l196">     */</span><a href="#l196"></a>
<span id="l197">    private byte                doClientAuth;</span><a href="#l197"></a>
<span id="l198">    private boolean             roleIsServer;</span><a href="#l198"></a>
<span id="l199">    private boolean             enableSessionCreation = true;</span><a href="#l199"></a>
<span id="l200">    private String              host;</span><a href="#l200"></a>
<span id="l201">    private boolean             autoClose = true;</span><a href="#l201"></a>
<span id="l202">    private AccessControlContext acc;</span><a href="#l202"></a>
<span id="l203"></span><a href="#l203"></a>
<span id="l204">    // The cipher suites enabled for use on this connection.</span><a href="#l204"></a>
<span id="l205">    private CipherSuiteList     enabledCipherSuites;</span><a href="#l205"></a>
<span id="l206"></span><a href="#l206"></a>
<span id="l207">    // The endpoint identification protocol</span><a href="#l207"></a>
<span id="l208">    private String              identificationProtocol = null;</span><a href="#l208"></a>
<span id="l209"></span><a href="#l209"></a>
<span id="l210">    // The cryptographic algorithm constraints</span><a href="#l210"></a>
<span id="l211">    private AlgorithmConstraints    algorithmConstraints = null;</span><a href="#l211"></a>
<span id="l212"></span><a href="#l212"></a>
<span id="l213">    // The server name indication and matchers</span><a href="#l213"></a>
<span id="l214">    List&lt;SNIServerName&gt;         serverNames =</span><a href="#l214"></a>
<span id="l215">                                    Collections.&lt;SNIServerName&gt;emptyList();</span><a href="#l215"></a>
<span id="l216">    Collection&lt;SNIMatcher&gt;      sniMatchers =</span><a href="#l216"></a>
<span id="l217">                                    Collections.&lt;SNIMatcher&gt;emptyList();</span><a href="#l217"></a>
<span id="l218">    // Is the serverNames set to empty with SSLParameters.setServerNames()?</span><a href="#l218"></a>
<span id="l219">    private boolean             noSniExtension = false;</span><a href="#l219"></a>
<span id="l220"></span><a href="#l220"></a>
<span id="l221">    // Is the sniMatchers set to empty with SSLParameters.setSNIMatchers()?</span><a href="#l221"></a>
<span id="l222">    private boolean             noSniMatcher = false;</span><a href="#l222"></a>
<span id="l223"></span><a href="#l223"></a>
<span id="l224">    // Configured application protocol values</span><a href="#l224"></a>
<span id="l225">    String[] applicationProtocols = new String[0];</span><a href="#l225"></a>
<span id="l226"></span><a href="#l226"></a>
<span id="l227">    // Negotiated application protocol value.</span><a href="#l227"></a>
<span id="l228">    //</span><a href="#l228"></a>
<span id="l229">    // The value under negotiation will be obtained from handshaker.</span><a href="#l229"></a>
<span id="l230">    String applicationProtocol = null;</span><a href="#l230"></a>
<span id="l231"></span><a href="#l231"></a>
<span id="l232">    // Callback function that selects the application protocol value during</span><a href="#l232"></a>
<span id="l233">    // the SSL/TLS handshake.</span><a href="#l233"></a>
<span id="l234">    BiFunction&lt;SSLSocket, List&lt;String&gt;, String&gt; applicationProtocolSelector;</span><a href="#l234"></a>
<span id="l235"></span><a href="#l235"></a>
<span id="l236">    /*</span><a href="#l236"></a>
<span id="l237">     * READ ME * READ ME * READ ME * READ ME * READ ME * READ ME *</span><a href="#l237"></a>
<span id="l238">     * IMPORTANT STUFF TO UNDERSTANDING THE SYNCHRONIZATION ISSUES.</span><a href="#l238"></a>
<span id="l239">     * READ ME * READ ME * READ ME * READ ME * READ ME * READ ME *</span><a href="#l239"></a>
<span id="l240">     *</span><a href="#l240"></a>
<span id="l241">     * There are several locks here.</span><a href="#l241"></a>
<span id="l242">     *</span><a href="#l242"></a>
<span id="l243">     * The primary lock is the per-instance lock used by</span><a href="#l243"></a>
<span id="l244">     * synchronized(this) and the synchronized methods.  It controls all</span><a href="#l244"></a>
<span id="l245">     * access to things such as the connection state and variables which</span><a href="#l245"></a>
<span id="l246">     * affect handshaking.  If we are inside a synchronized method, we</span><a href="#l246"></a>
<span id="l247">     * can access the state directly, otherwise, we must use the</span><a href="#l247"></a>
<span id="l248">     * synchronized equivalents.</span><a href="#l248"></a>
<span id="l249">     *</span><a href="#l249"></a>
<span id="l250">     * The handshakeLock is used to ensure that only one thread performs</span><a href="#l250"></a>
<span id="l251">     * the *complete initial* handshake.  If someone is handshaking, any</span><a href="#l251"></a>
<span id="l252">     * stray application or startHandshake() requests who find the</span><a href="#l252"></a>
<span id="l253">     * connection state is cs_HANDSHAKE will stall on handshakeLock</span><a href="#l253"></a>
<span id="l254">     * until handshaking is done.  Once the handshake is done, we either</span><a href="#l254"></a>
<span id="l255">     * succeeded or failed, but we can never go back to the cs_HANDSHAKE</span><a href="#l255"></a>
<span id="l256">     * or cs_START state again.</span><a href="#l256"></a>
<span id="l257">     *</span><a href="#l257"></a>
<span id="l258">     * Note that the read/write() calls here in SSLSocketImpl are not</span><a href="#l258"></a>
<span id="l259">     * obviously synchronized.  In fact, it's very nonintuitive, and</span><a href="#l259"></a>
<span id="l260">     * requires careful examination of code paths.  Grab some coffee,</span><a href="#l260"></a>
<span id="l261">     * and be careful with any code changes.</span><a href="#l261"></a>
<span id="l262">     *</span><a href="#l262"></a>
<span id="l263">     * There can be only three threads active at a time in the I/O</span><a href="#l263"></a>
<span id="l264">     * subsection of this class.</span><a href="#l264"></a>
<span id="l265">     *    1.  startHandshake</span><a href="#l265"></a>
<span id="l266">     *    2.  AppInputStream</span><a href="#l266"></a>
<span id="l267">     *    3.  AppOutputStream</span><a href="#l267"></a>
<span id="l268">     * One thread could call startHandshake().</span><a href="#l268"></a>
<span id="l269">     * AppInputStream/AppOutputStream read() and write() calls are each</span><a href="#l269"></a>
<span id="l270">     * synchronized on 'this' in their respective classes, so only one</span><a href="#l270"></a>
<span id="l271">     * app. thread will be doing a SSLSocketImpl.read() or .write()'s at</span><a href="#l271"></a>
<span id="l272">     * a time.</span><a href="#l272"></a>
<span id="l273">     *</span><a href="#l273"></a>
<span id="l274">     * If handshaking is required (state cs_HANDSHAKE), and</span><a href="#l274"></a>
<span id="l275">     * getConnectionState() for some/all threads returns cs_HANDSHAKE,</span><a href="#l275"></a>
<span id="l276">     * only one can grab the handshakeLock, and the rest will stall</span><a href="#l276"></a>
<span id="l277">     * either on getConnectionState(), or on the handshakeLock if they</span><a href="#l277"></a>
<span id="l278">     * happen to successfully race through the getConnectionState().</span><a href="#l278"></a>
<span id="l279">     *</span><a href="#l279"></a>
<span id="l280">     * If a writer is doing the initial handshaking, it must create a</span><a href="#l280"></a>
<span id="l281">     * temporary reader to read the responses from the other side.  As a</span><a href="#l281"></a>
<span id="l282">     * side-effect, the writer's reader will have priority over any</span><a href="#l282"></a>
<span id="l283">     * other reader.  However, the writer's reader is not allowed to</span><a href="#l283"></a>
<span id="l284">     * consume any application data.  When handshakeLock is finally</span><a href="#l284"></a>
<span id="l285">     * released, we either have a cs_DATA connection, or a</span><a href="#l285"></a>
<span id="l286">     * cs_CLOSED/cs_ERROR socket.</span><a href="#l286"></a>
<span id="l287">     *</span><a href="#l287"></a>
<span id="l288">     * The writeLock is held while writing on a socket connection and</span><a href="#l288"></a>
<span id="l289">     * also to protect the MAC and cipher for their direction.  The</span><a href="#l289"></a>
<span id="l290">     * writeLock is package private for Handshaker which holds it while</span><a href="#l290"></a>
<span id="l291">     * writing the ChangeCipherSpec message.</span><a href="#l291"></a>
<span id="l292">     *</span><a href="#l292"></a>
<span id="l293">     * To avoid the problem of a thread trying to change operational</span><a href="#l293"></a>
<span id="l294">     * modes on a socket while handshaking is going on, we synchronize</span><a href="#l294"></a>
<span id="l295">     * on 'this'.  If handshaking has not started yet, we tell the</span><a href="#l295"></a>
<span id="l296">     * handshaker to change its mode.  If handshaking has started,</span><a href="#l296"></a>
<span id="l297">     * we simply store that request until the next pending session</span><a href="#l297"></a>
<span id="l298">     * is created, at which time the new handshaker's state is set.</span><a href="#l298"></a>
<span id="l299">     *</span><a href="#l299"></a>
<span id="l300">     * The readLock is held during readRecord(), which is responsible</span><a href="#l300"></a>
<span id="l301">     * for reading an InputRecord, decrypting it, and processing it.</span><a href="#l301"></a>
<span id="l302">     * The readLock ensures that these three steps are done atomically</span><a href="#l302"></a>
<span id="l303">     * and that once started, no other thread can block on InputRecord.read.</span><a href="#l303"></a>
<span id="l304">     * This is necessary so that processing of close_notify alerts</span><a href="#l304"></a>
<span id="l305">     * from the peer are handled properly.</span><a href="#l305"></a>
<span id="l306">     */</span><a href="#l306"></a>
<span id="l307">    final private Object        handshakeLock = new Object();</span><a href="#l307"></a>
<span id="l308">    final ReentrantLock         writeLock = new ReentrantLock();</span><a href="#l308"></a>
<span id="l309">    final private Object        readLock = new Object();</span><a href="#l309"></a>
<span id="l310"></span><a href="#l310"></a>
<span id="l311">    private InputRecord         inrec;</span><a href="#l311"></a>
<span id="l312"></span><a href="#l312"></a>
<span id="l313">    /*</span><a href="#l313"></a>
<span id="l314">     * Crypto state that's reinitialized when the session changes.</span><a href="#l314"></a>
<span id="l315">     */</span><a href="#l315"></a>
<span id="l316">    private Authenticator       readAuthenticator, writeAuthenticator;</span><a href="#l316"></a>
<span id="l317">    private CipherBox           readCipher, writeCipher;</span><a href="#l317"></a>
<span id="l318">    // NOTE: compression state would be saved here</span><a href="#l318"></a>
<span id="l319"></span><a href="#l319"></a>
<span id="l320">    /*</span><a href="#l320"></a>
<span id="l321">     * security parameters for secure renegotiation.</span><a href="#l321"></a>
<span id="l322">     */</span><a href="#l322"></a>
<span id="l323">    private boolean             secureRenegotiation;</span><a href="#l323"></a>
<span id="l324">    private byte[]              clientVerifyData;</span><a href="#l324"></a>
<span id="l325">    private byte[]              serverVerifyData;</span><a href="#l325"></a>
<span id="l326"></span><a href="#l326"></a>
<span id="l327">    /*</span><a href="#l327"></a>
<span id="l328">     * The authentication context holds all information used to establish</span><a href="#l328"></a>
<span id="l329">     * who this end of the connection is (certificate chains, private keys,</span><a href="#l329"></a>
<span id="l330">     * etc) and who is trusted (e.g. as CAs or websites).</span><a href="#l330"></a>
<span id="l331">     */</span><a href="#l331"></a>
<span id="l332">    private SSLContextImpl      sslContext;</span><a href="#l332"></a>
<span id="l333"></span><a href="#l333"></a>
<span id="l334"></span><a href="#l334"></a>
<span id="l335">    /*</span><a href="#l335"></a>
<span id="l336">     * This connection is one of (potentially) many associated with</span><a href="#l336"></a>
<span id="l337">     * any given session.  The output of the handshake protocol is a</span><a href="#l337"></a>
<span id="l338">     * new session ... although all the protocol description talks</span><a href="#l338"></a>
<span id="l339">     * about changing the cipher spec (and it does change), in fact</span><a href="#l339"></a>
<span id="l340">     * that's incidental since it's done by changing everything that</span><a href="#l340"></a>
<span id="l341">     * is associated with a session at the same time.  (TLS/IETF may</span><a href="#l341"></a>
<span id="l342">     * change that to add client authentication w/o new key exchg.)</span><a href="#l342"></a>
<span id="l343">     */</span><a href="#l343"></a>
<span id="l344">    private Handshaker                  handshaker;</span><a href="#l344"></a>
<span id="l345">    private SSLSessionImpl              sess;</span><a href="#l345"></a>
<span id="l346">    private volatile SSLSessionImpl     handshakeSession;</span><a href="#l346"></a>
<span id="l347"></span><a href="#l347"></a>
<span id="l348"></span><a href="#l348"></a>
<span id="l349">    /*</span><a href="#l349"></a>
<span id="l350">     * If anyone wants to get notified about handshake completions,</span><a href="#l350"></a>
<span id="l351">     * they'll show up on this list.</span><a href="#l351"></a>
<span id="l352">     */</span><a href="#l352"></a>
<span id="l353">    private HashMap&lt;HandshakeCompletedListener, AccessControlContext&gt;</span><a href="#l353"></a>
<span id="l354">                                                        handshakeListeners;</span><a href="#l354"></a>
<span id="l355"></span><a href="#l355"></a>
<span id="l356">    /*</span><a href="#l356"></a>
<span id="l357">     * Reuse the same internal input/output streams.</span><a href="#l357"></a>
<span id="l358">     */</span><a href="#l358"></a>
<span id="l359">    private InputStream         sockInput;</span><a href="#l359"></a>
<span id="l360">    private OutputStream        sockOutput;</span><a href="#l360"></a>
<span id="l361"></span><a href="#l361"></a>
<span id="l362"></span><a href="#l362"></a>
<span id="l363">    /*</span><a href="#l363"></a>
<span id="l364">     * These input and output streams block their data in SSL records,</span><a href="#l364"></a>
<span id="l365">     * and usually arrange integrity and privacy protection for those</span><a href="#l365"></a>
<span id="l366">     * records.  The guts of the SSL protocol are wrapped up in these</span><a href="#l366"></a>
<span id="l367">     * streams, and in the handshaking that establishes the details of</span><a href="#l367"></a>
<span id="l368">     * that integrity and privacy protection.</span><a href="#l368"></a>
<span id="l369">     */</span><a href="#l369"></a>
<span id="l370">    private AppInputStream      input;</span><a href="#l370"></a>
<span id="l371">    private AppOutputStream     output;</span><a href="#l371"></a>
<span id="l372"></span><a href="#l372"></a>
<span id="l373">    /*</span><a href="#l373"></a>
<span id="l374">     * The protocol versions enabled for use on this connection.</span><a href="#l374"></a>
<span id="l375">     *</span><a href="#l375"></a>
<span id="l376">     * Note: we support a pseudo protocol called SSLv2Hello which when</span><a href="#l376"></a>
<span id="l377">     * set will result in an SSL v2 Hello being sent with SSL (version 3.0)</span><a href="#l377"></a>
<span id="l378">     * or TLS (version 3.1, 3.2, etc.) version info.</span><a href="#l378"></a>
<span id="l379">     */</span><a href="#l379"></a>
<span id="l380">    private ProtocolList enabledProtocols;</span><a href="#l380"></a>
<span id="l381"></span><a href="#l381"></a>
<span id="l382">    /*</span><a href="#l382"></a>
<span id="l383">     * The SSL version associated with this connection.</span><a href="#l383"></a>
<span id="l384">     */</span><a href="#l384"></a>
<span id="l385">    private ProtocolVersion     protocolVersion = ProtocolVersion.DEFAULT;</span><a href="#l385"></a>
<span id="l386"></span><a href="#l386"></a>
<span id="l387">    /* Class and subclass dynamic debugging support */</span><a href="#l387"></a>
<span id="l388">    private static final Debug debug = Debug.getInstance(&quot;ssl&quot;);</span><a href="#l388"></a>
<span id="l389"></span><a href="#l389"></a>
<span id="l390">    /*</span><a href="#l390"></a>
<span id="l391">     * Is it the first application record to write?</span><a href="#l391"></a>
<span id="l392">     */</span><a href="#l392"></a>
<span id="l393">    private boolean isFirstAppOutputRecord = true;</span><a href="#l393"></a>
<span id="l394"></span><a href="#l394"></a>
<span id="l395">    /*</span><a href="#l395"></a>
<span id="l396">     * If AppOutputStream needs to delay writes of small packets, we</span><a href="#l396"></a>
<span id="l397">     * will use this to store the data until we actually do the write.</span><a href="#l397"></a>
<span id="l398">     */</span><a href="#l398"></a>
<span id="l399">    private ByteArrayOutputStream heldRecordBuffer = null;</span><a href="#l399"></a>
<span id="l400"></span><a href="#l400"></a>
<span id="l401">    /*</span><a href="#l401"></a>
<span id="l402">     * Whether local cipher suites preference in server side should be</span><a href="#l402"></a>
<span id="l403">     * honored during handshaking?</span><a href="#l403"></a>
<span id="l404">     */</span><a href="#l404"></a>
<span id="l405">    private boolean preferLocalCipherSuites = false;</span><a href="#l405"></a>
<span id="l406"></span><a href="#l406"></a>
<span id="l407">    /*</span><a href="#l407"></a>
<span id="l408">     * Is the local name service trustworthy?</span><a href="#l408"></a>
<span id="l409">     *</span><a href="#l409"></a>
<span id="l410">     * If the local name service is not trustworthy, reverse host name</span><a href="#l410"></a>
<span id="l411">     * resolution should not be performed for endpoint identification.</span><a href="#l411"></a>
<span id="l412">     */</span><a href="#l412"></a>
<span id="l413">    static final boolean trustNameService =</span><a href="#l413"></a>
<span id="l414">            Debug.getBooleanProperty(&quot;jdk.tls.trustNameService&quot;, false);</span><a href="#l414"></a>
<span id="l415"></span><a href="#l415"></a>
<span id="l416">    //</span><a href="#l416"></a>
<span id="l417">    // CONSTRUCTORS AND INITIALIZATION CODE</span><a href="#l417"></a>
<span id="l418">    //</span><a href="#l418"></a>
<span id="l419"></span><a href="#l419"></a>
<span id="l420">    /**</span><a href="#l420"></a>
<span id="l421">     * Constructs an SSL connection to a named host at a specified port,</span><a href="#l421"></a>
<span id="l422">     * using the authentication context provided.  This endpoint acts as</span><a href="#l422"></a>
<span id="l423">     * the client, and may rejoin an existing SSL session if appropriate.</span><a href="#l423"></a>
<span id="l424">     *</span><a href="#l424"></a>
<span id="l425">     * @param context authentication context to use</span><a href="#l425"></a>
<span id="l426">     * @param host name of the host with which to connect</span><a href="#l426"></a>
<span id="l427">     * @param port number of the server's port</span><a href="#l427"></a>
<span id="l428">     */</span><a href="#l428"></a>
<span id="l429">    SSLSocketImpl(SSLContextImpl context, String host, int port)</span><a href="#l429"></a>
<span id="l430">            throws IOException, UnknownHostException {</span><a href="#l430"></a>
<span id="l431">        super();</span><a href="#l431"></a>
<span id="l432">        this.host = host;</span><a href="#l432"></a>
<span id="l433">        this.serverNames =</span><a href="#l433"></a>
<span id="l434">            Utilities.addToSNIServerNameList(this.serverNames, this.host);</span><a href="#l434"></a>
<span id="l435">        init(context, false);</span><a href="#l435"></a>
<span id="l436">        SocketAddress socketAddress =</span><a href="#l436"></a>
<span id="l437">               host != null ? new InetSocketAddress(host, port) :</span><a href="#l437"></a>
<span id="l438">               new InetSocketAddress(InetAddress.getByName(null), port);</span><a href="#l438"></a>
<span id="l439">        connect(socketAddress, 0);</span><a href="#l439"></a>
<span id="l440">    }</span><a href="#l440"></a>
<span id="l441"></span><a href="#l441"></a>
<span id="l442"></span><a href="#l442"></a>
<span id="l443">    /**</span><a href="#l443"></a>
<span id="l444">     * Constructs an SSL connection to a server at a specified address.</span><a href="#l444"></a>
<span id="l445">     * and TCP port, using the authentication context provided.  This</span><a href="#l445"></a>
<span id="l446">     * endpoint acts as the client, and may rejoin an existing SSL session</span><a href="#l446"></a>
<span id="l447">     * if appropriate.</span><a href="#l447"></a>
<span id="l448">     *</span><a href="#l448"></a>
<span id="l449">     * @param context authentication context to use</span><a href="#l449"></a>
<span id="l450">     * @param address the server's host</span><a href="#l450"></a>
<span id="l451">     * @param port its port</span><a href="#l451"></a>
<span id="l452">     */</span><a href="#l452"></a>
<span id="l453">    SSLSocketImpl(SSLContextImpl context, InetAddress host, int port)</span><a href="#l453"></a>
<span id="l454">            throws IOException {</span><a href="#l454"></a>
<span id="l455">        super();</span><a href="#l455"></a>
<span id="l456">        init(context, false);</span><a href="#l456"></a>
<span id="l457">        SocketAddress socketAddress = new InetSocketAddress(host, port);</span><a href="#l457"></a>
<span id="l458">        connect(socketAddress, 0);</span><a href="#l458"></a>
<span id="l459">    }</span><a href="#l459"></a>
<span id="l460"></span><a href="#l460"></a>
<span id="l461">    /**</span><a href="#l461"></a>
<span id="l462">     * Constructs an SSL connection to a named host at a specified port,</span><a href="#l462"></a>
<span id="l463">     * using the authentication context provided.  This endpoint acts as</span><a href="#l463"></a>
<span id="l464">     * the client, and may rejoin an existing SSL session if appropriate.</span><a href="#l464"></a>
<span id="l465">     *</span><a href="#l465"></a>
<span id="l466">     * @param context authentication context to use</span><a href="#l466"></a>
<span id="l467">     * @param host name of the host with which to connect</span><a href="#l467"></a>
<span id="l468">     * @param port number of the server's port</span><a href="#l468"></a>
<span id="l469">     * @param localAddr the local address the socket is bound to</span><a href="#l469"></a>
<span id="l470">     * @param localPort the local port the socket is bound to</span><a href="#l470"></a>
<span id="l471">     */</span><a href="#l471"></a>
<span id="l472">    SSLSocketImpl(SSLContextImpl context, String host, int port,</span><a href="#l472"></a>
<span id="l473">            InetAddress localAddr, int localPort)</span><a href="#l473"></a>
<span id="l474">            throws IOException, UnknownHostException {</span><a href="#l474"></a>
<span id="l475">        super();</span><a href="#l475"></a>
<span id="l476">        this.host = host;</span><a href="#l476"></a>
<span id="l477">        this.serverNames =</span><a href="#l477"></a>
<span id="l478">            Utilities.addToSNIServerNameList(this.serverNames, this.host);</span><a href="#l478"></a>
<span id="l479">        init(context, false);</span><a href="#l479"></a>
<span id="l480">        bind(new InetSocketAddress(localAddr, localPort));</span><a href="#l480"></a>
<span id="l481">        SocketAddress socketAddress =</span><a href="#l481"></a>
<span id="l482">               host != null ? new InetSocketAddress(host, port) :</span><a href="#l482"></a>
<span id="l483">               new InetSocketAddress(InetAddress.getByName(null), port);</span><a href="#l483"></a>
<span id="l484">        connect(socketAddress, 0);</span><a href="#l484"></a>
<span id="l485">    }</span><a href="#l485"></a>
<span id="l486"></span><a href="#l486"></a>
<span id="l487"></span><a href="#l487"></a>
<span id="l488">    /**</span><a href="#l488"></a>
<span id="l489">     * Constructs an SSL connection to a server at a specified address.</span><a href="#l489"></a>
<span id="l490">     * and TCP port, using the authentication context provided.  This</span><a href="#l490"></a>
<span id="l491">     * endpoint acts as the client, and may rejoin an existing SSL session</span><a href="#l491"></a>
<span id="l492">     * if appropriate.</span><a href="#l492"></a>
<span id="l493">     *</span><a href="#l493"></a>
<span id="l494">     * @param context authentication context to use</span><a href="#l494"></a>
<span id="l495">     * @param address the server's host</span><a href="#l495"></a>
<span id="l496">     * @param port its port</span><a href="#l496"></a>
<span id="l497">     * @param localAddr the local address the socket is bound to</span><a href="#l497"></a>
<span id="l498">     * @param localPort the local port the socket is bound to</span><a href="#l498"></a>
<span id="l499">     */</span><a href="#l499"></a>
<span id="l500">    SSLSocketImpl(SSLContextImpl context, InetAddress host, int port,</span><a href="#l500"></a>
<span id="l501">            InetAddress localAddr, int localPort)</span><a href="#l501"></a>
<span id="l502">            throws IOException {</span><a href="#l502"></a>
<span id="l503">        super();</span><a href="#l503"></a>
<span id="l504">        init(context, false);</span><a href="#l504"></a>
<span id="l505">        bind(new InetSocketAddress(localAddr, localPort));</span><a href="#l505"></a>
<span id="l506">        SocketAddress socketAddress = new InetSocketAddress(host, port);</span><a href="#l506"></a>
<span id="l507">        connect(socketAddress, 0);</span><a href="#l507"></a>
<span id="l508">    }</span><a href="#l508"></a>
<span id="l509"></span><a href="#l509"></a>
<span id="l510">    /*</span><a href="#l510"></a>
<span id="l511">     * Package-private constructor used ONLY by SSLServerSocket.  The</span><a href="#l511"></a>
<span id="l512">     * java.net package accepts the TCP connection after this call is</span><a href="#l512"></a>
<span id="l513">     * made.  This just initializes handshake state to use &quot;server mode&quot;,</span><a href="#l513"></a>
<span id="l514">     * giving control over the use of SSL client authentication.</span><a href="#l514"></a>
<span id="l515">     */</span><a href="#l515"></a>
<span id="l516">    SSLSocketImpl(SSLContextImpl context, boolean serverMode,</span><a href="#l516"></a>
<span id="l517">            CipherSuiteList suites, byte clientAuth,</span><a href="#l517"></a>
<span id="l518">            boolean sessionCreation, ProtocolList protocols,</span><a href="#l518"></a>
<span id="l519">            String identificationProtocol,</span><a href="#l519"></a>
<span id="l520">            AlgorithmConstraints algorithmConstraints,</span><a href="#l520"></a>
<span id="l521">            Collection&lt;SNIMatcher&gt; sniMatchers,</span><a href="#l521"></a>
<span id="l522">            boolean preferLocalCipherSuites,</span><a href="#l522"></a>
<span id="l523">            String[] applicationProtocols) throws IOException {</span><a href="#l523"></a>
<span id="l524"></span><a href="#l524"></a>
<span id="l525">        super();</span><a href="#l525"></a>
<span id="l526">        doClientAuth = clientAuth;</span><a href="#l526"></a>
<span id="l527">        enableSessionCreation = sessionCreation;</span><a href="#l527"></a>
<span id="l528">        this.identificationProtocol = identificationProtocol;</span><a href="#l528"></a>
<span id="l529">        this.algorithmConstraints = algorithmConstraints;</span><a href="#l529"></a>
<span id="l530">        this.sniMatchers = sniMatchers;</span><a href="#l530"></a>
<span id="l531">        this.preferLocalCipherSuites = preferLocalCipherSuites;</span><a href="#l531"></a>
<span id="l532">        this.applicationProtocols = applicationProtocols;</span><a href="#l532"></a>
<span id="l533">        init(context, serverMode);</span><a href="#l533"></a>
<span id="l534"></span><a href="#l534"></a>
<span id="l535">        /*</span><a href="#l535"></a>
<span id="l536">         * Override what was picked out for us.</span><a href="#l536"></a>
<span id="l537">         */</span><a href="#l537"></a>
<span id="l538">        enabledCipherSuites = suites;</span><a href="#l538"></a>
<span id="l539">        enabledProtocols = protocols;</span><a href="#l539"></a>
<span id="l540">    }</span><a href="#l540"></a>
<span id="l541"></span><a href="#l541"></a>
<span id="l542"></span><a href="#l542"></a>
<span id="l543">    /**</span><a href="#l543"></a>
<span id="l544">     * Package-private constructor used to instantiate an unconnected</span><a href="#l544"></a>
<span id="l545">     * socket. The java.net package will connect it, either when the</span><a href="#l545"></a>
<span id="l546">     * connect() call is made by the application.  This instance is</span><a href="#l546"></a>
<span id="l547">     * meant to set handshake state to use &quot;client mode&quot;.</span><a href="#l547"></a>
<span id="l548">     */</span><a href="#l548"></a>
<span id="l549">    SSLSocketImpl(SSLContextImpl context) {</span><a href="#l549"></a>
<span id="l550">        super();</span><a href="#l550"></a>
<span id="l551">        init(context, false);</span><a href="#l551"></a>
<span id="l552">    }</span><a href="#l552"></a>
<span id="l553"></span><a href="#l553"></a>
<span id="l554"></span><a href="#l554"></a>
<span id="l555">    /**</span><a href="#l555"></a>
<span id="l556">     * Layer SSL traffic over an existing connection, rather than creating</span><a href="#l556"></a>
<span id="l557">     * a new connection.  The existing connection may be used only for SSL</span><a href="#l557"></a>
<span id="l558">     * traffic (using this SSLSocket) until the SSLSocket.close() call</span><a href="#l558"></a>
<span id="l559">     * returns. However, if a protocol error is detected, that existing</span><a href="#l559"></a>
<span id="l560">     * connection is automatically closed.</span><a href="#l560"></a>
<span id="l561">     *</span><a href="#l561"></a>
<span id="l562">     * &lt;P&gt; This particular constructor always uses the socket in the</span><a href="#l562"></a>
<span id="l563">     * role of an SSL client. It may be useful in cases which start</span><a href="#l563"></a>
<span id="l564">     * using SSL after some initial data transfers, for example in some</span><a href="#l564"></a>
<span id="l565">     * SSL tunneling applications or as part of some kinds of application</span><a href="#l565"></a>
<span id="l566">     * protocols which negotiate use of a SSL based security.</span><a href="#l566"></a>
<span id="l567">     *</span><a href="#l567"></a>
<span id="l568">     * @param sock the existing connection</span><a href="#l568"></a>
<span id="l569">     * @param context the authentication context to use</span><a href="#l569"></a>
<span id="l570">     */</span><a href="#l570"></a>
<span id="l571">    SSLSocketImpl(SSLContextImpl context, Socket sock, String host,</span><a href="#l571"></a>
<span id="l572">            int port, boolean autoClose) throws IOException {</span><a href="#l572"></a>
<span id="l573">        super(sock);</span><a href="#l573"></a>
<span id="l574">        // We always layer over a connected socket</span><a href="#l574"></a>
<span id="l575">        if (!sock.isConnected()) {</span><a href="#l575"></a>
<span id="l576">            throw new SocketException(&quot;Underlying socket is not connected&quot;);</span><a href="#l576"></a>
<span id="l577">        }</span><a href="#l577"></a>
<span id="l578">        this.host = host;</span><a href="#l578"></a>
<span id="l579">        this.serverNames =</span><a href="#l579"></a>
<span id="l580">            Utilities.addToSNIServerNameList(this.serverNames, this.host);</span><a href="#l580"></a>
<span id="l581">        init(context, false);</span><a href="#l581"></a>
<span id="l582">        this.autoClose = autoClose;</span><a href="#l582"></a>
<span id="l583">        doneConnect();</span><a href="#l583"></a>
<span id="l584">    }</span><a href="#l584"></a>
<span id="l585"></span><a href="#l585"></a>
<span id="l586">    /**</span><a href="#l586"></a>
<span id="l587">     * Creates a server mode {@link Socket} layered over an</span><a href="#l587"></a>
<span id="l588">     * existing connected socket, and is able to read data which has</span><a href="#l588"></a>
<span id="l589">     * already been consumed/removed from the {@link Socket}'s</span><a href="#l589"></a>
<span id="l590">     * underlying {@link InputStream}.</span><a href="#l590"></a>
<span id="l591">     */</span><a href="#l591"></a>
<span id="l592">    SSLSocketImpl(SSLContextImpl context, Socket sock,</span><a href="#l592"></a>
<span id="l593">            InputStream consumed, boolean autoClose) throws IOException {</span><a href="#l593"></a>
<span id="l594">        super(sock, consumed);</span><a href="#l594"></a>
<span id="l595">        // We always layer over a connected socket</span><a href="#l595"></a>
<span id="l596">        if (!sock.isConnected()) {</span><a href="#l596"></a>
<span id="l597">            throw new SocketException(&quot;Underlying socket is not connected&quot;);</span><a href="#l597"></a>
<span id="l598">        }</span><a href="#l598"></a>
<span id="l599"></span><a href="#l599"></a>
<span id="l600">        // In server mode, it is not necessary to set host and serverNames.</span><a href="#l600"></a>
<span id="l601">        // Otherwise, would require a reverse DNS lookup to get the hostname.</span><a href="#l601"></a>
<span id="l602"></span><a href="#l602"></a>
<span id="l603">        init(context, true);</span><a href="#l603"></a>
<span id="l604">        this.autoClose = autoClose;</span><a href="#l604"></a>
<span id="l605">        doneConnect();</span><a href="#l605"></a>
<span id="l606">    }</span><a href="#l606"></a>
<span id="l607"></span><a href="#l607"></a>
<span id="l608">    /**</span><a href="#l608"></a>
<span id="l609">     * Initializes the client socket.</span><a href="#l609"></a>
<span id="l610">     */</span><a href="#l610"></a>
<span id="l611">    private void init(SSLContextImpl context, boolean isServer) {</span><a href="#l611"></a>
<span id="l612">        sslContext = context;</span><a href="#l612"></a>
<span id="l613">        sess = new SSLSessionImpl();</span><a href="#l613"></a>
<span id="l614">        handshakeSession = null;</span><a href="#l614"></a>
<span id="l615"></span><a href="#l615"></a>
<span id="l616">        /*</span><a href="#l616"></a>
<span id="l617">         * role is as specified, state is START until after</span><a href="#l617"></a>
<span id="l618">         * the low level connection's established.</span><a href="#l618"></a>
<span id="l619">         */</span><a href="#l619"></a>
<span id="l620">        roleIsServer = isServer;</span><a href="#l620"></a>
<span id="l621">        connectionState = cs_START;</span><a href="#l621"></a>
<span id="l622"></span><a href="#l622"></a>
<span id="l623">        /*</span><a href="#l623"></a>
<span id="l624">         * default read and write side cipher and MAC support</span><a href="#l624"></a>
<span id="l625">         *</span><a href="#l625"></a>
<span id="l626">         * Note:  compression support would go here too</span><a href="#l626"></a>
<span id="l627">         */</span><a href="#l627"></a>
<span id="l628">        readCipher = CipherBox.NULL;</span><a href="#l628"></a>
<span id="l629">        readAuthenticator = MAC.NULL;</span><a href="#l629"></a>
<span id="l630">        writeCipher = CipherBox.NULL;</span><a href="#l630"></a>
<span id="l631">        writeAuthenticator = MAC.NULL;</span><a href="#l631"></a>
<span id="l632"></span><a href="#l632"></a>
<span id="l633">        // initial security parameters for secure renegotiation</span><a href="#l633"></a>
<span id="l634">        secureRenegotiation = false;</span><a href="#l634"></a>
<span id="l635">        clientVerifyData = new byte[0];</span><a href="#l635"></a>
<span id="l636">        serverVerifyData = new byte[0];</span><a href="#l636"></a>
<span id="l637"></span><a href="#l637"></a>
<span id="l638">        enabledCipherSuites =</span><a href="#l638"></a>
<span id="l639">                sslContext.getDefaultCipherSuiteList(roleIsServer);</span><a href="#l639"></a>
<span id="l640">        enabledProtocols =</span><a href="#l640"></a>
<span id="l641">                sslContext.getDefaultProtocolList(roleIsServer);</span><a href="#l641"></a>
<span id="l642"></span><a href="#l642"></a>
<span id="l643">        inrec = null;</span><a href="#l643"></a>
<span id="l644"></span><a href="#l644"></a>
<span id="l645">        // save the acc</span><a href="#l645"></a>
<span id="l646">        acc = AccessController.getContext();</span><a href="#l646"></a>
<span id="l647"></span><a href="#l647"></a>
<span id="l648">        input = new AppInputStream(this);</span><a href="#l648"></a>
<span id="l649">        output = new AppOutputStream(this);</span><a href="#l649"></a>
<span id="l650">    }</span><a href="#l650"></a>
<span id="l651"></span><a href="#l651"></a>
<span id="l652">    /**</span><a href="#l652"></a>
<span id="l653">     * Connects this socket to the server with a specified timeout</span><a href="#l653"></a>
<span id="l654">     * value.</span><a href="#l654"></a>
<span id="l655">     *</span><a href="#l655"></a>
<span id="l656">     * This method is either called on an unconnected SSLSocketImpl by the</span><a href="#l656"></a>
<span id="l657">     * application, or it is called in the constructor of a regular</span><a href="#l657"></a>
<span id="l658">     * SSLSocketImpl. If we are layering on top on another socket, then</span><a href="#l658"></a>
<span id="l659">     * this method should not be called, because we assume that the</span><a href="#l659"></a>
<span id="l660">     * underlying socket is already connected by the time it is passed to</span><a href="#l660"></a>
<span id="l661">     * us.</span><a href="#l661"></a>
<span id="l662">     *</span><a href="#l662"></a>
<span id="l663">     * @param   endpoint the &lt;code&gt;SocketAddress&lt;/code&gt;</span><a href="#l663"></a>
<span id="l664">     * @param   timeout  the timeout value to be used, 0 is no timeout</span><a href="#l664"></a>
<span id="l665">     * @throws  IOException if an error occurs during the connection</span><a href="#l665"></a>
<span id="l666">     * @throws  SocketTimeoutException if timeout expires before connecting</span><a href="#l666"></a>
<span id="l667">     */</span><a href="#l667"></a>
<span id="l668">    @Override</span><a href="#l668"></a>
<span id="l669">    public void connect(SocketAddress endpoint, int timeout)</span><a href="#l669"></a>
<span id="l670">            throws IOException {</span><a href="#l670"></a>
<span id="l671"></span><a href="#l671"></a>
<span id="l672">        if (isLayered()) {</span><a href="#l672"></a>
<span id="l673">            throw new SocketException(&quot;Already connected&quot;);</span><a href="#l673"></a>
<span id="l674">        }</span><a href="#l674"></a>
<span id="l675"></span><a href="#l675"></a>
<span id="l676">        if (!(endpoint instanceof InetSocketAddress)) {</span><a href="#l676"></a>
<span id="l677">            throw new SocketException(</span><a href="#l677"></a>
<span id="l678">                                  &quot;Cannot handle non-Inet socket addresses.&quot;);</span><a href="#l678"></a>
<span id="l679">        }</span><a href="#l679"></a>
<span id="l680"></span><a href="#l680"></a>
<span id="l681">        super.connect(endpoint, timeout);</span><a href="#l681"></a>
<span id="l682"></span><a href="#l682"></a>
<span id="l683">        if (host == null || host.length() == 0) {</span><a href="#l683"></a>
<span id="l684">            useImplicitHost(false);</span><a href="#l684"></a>
<span id="l685">        }</span><a href="#l685"></a>
<span id="l686"></span><a href="#l686"></a>
<span id="l687">        doneConnect();</span><a href="#l687"></a>
<span id="l688">    }</span><a href="#l688"></a>
<span id="l689"></span><a href="#l689"></a>
<span id="l690">    /**</span><a href="#l690"></a>
<span id="l691">     * Initialize the handshaker and socket streams.</span><a href="#l691"></a>
<span id="l692">     *</span><a href="#l692"></a>
<span id="l693">     * Called by connect, the layered constructor, and SSLServerSocket.</span><a href="#l693"></a>
<span id="l694">     */</span><a href="#l694"></a>
<span id="l695">    void doneConnect() throws IOException {</span><a href="#l695"></a>
<span id="l696">        /*</span><a href="#l696"></a>
<span id="l697">         * Save the input and output streams.  May be done only after</span><a href="#l697"></a>
<span id="l698">         * java.net actually connects using the socket &quot;self&quot;, else</span><a href="#l698"></a>
<span id="l699">         * we get some pretty bizarre failure modes.</span><a href="#l699"></a>
<span id="l700">         */</span><a href="#l700"></a>
<span id="l701">        sockInput = super.getInputStream();</span><a href="#l701"></a>
<span id="l702">        sockOutput = super.getOutputStream();</span><a href="#l702"></a>
<span id="l703"></span><a href="#l703"></a>
<span id="l704">        /*</span><a href="#l704"></a>
<span id="l705">         * Move to handshaking state, with pending session initialized</span><a href="#l705"></a>
<span id="l706">         * to defaults and the appropriate kind of handshaker set up.</span><a href="#l706"></a>
<span id="l707">         */</span><a href="#l707"></a>
<span id="l708">        initHandshaker();</span><a href="#l708"></a>
<span id="l709">    }</span><a href="#l709"></a>
<span id="l710"></span><a href="#l710"></a>
<span id="l711">    synchronized private int getConnectionState() {</span><a href="#l711"></a>
<span id="l712">        return connectionState;</span><a href="#l712"></a>
<span id="l713">    }</span><a href="#l713"></a>
<span id="l714"></span><a href="#l714"></a>
<span id="l715">    synchronized private void setConnectionState(int state) {</span><a href="#l715"></a>
<span id="l716">        connectionState = state;</span><a href="#l716"></a>
<span id="l717">    }</span><a href="#l717"></a>
<span id="l718"></span><a href="#l718"></a>
<span id="l719">    AccessControlContext getAcc() {</span><a href="#l719"></a>
<span id="l720">        return acc;</span><a href="#l720"></a>
<span id="l721">    }</span><a href="#l721"></a>
<span id="l722"></span><a href="#l722"></a>
<span id="l723">    //</span><a href="#l723"></a>
<span id="l724">    // READING AND WRITING RECORDS</span><a href="#l724"></a>
<span id="l725">    //</span><a href="#l725"></a>
<span id="l726"></span><a href="#l726"></a>
<span id="l727">    /*</span><a href="#l727"></a>
<span id="l728">     * AppOutputStream calls may need to buffer multiple outbound</span><a href="#l728"></a>
<span id="l729">     * application packets.</span><a href="#l729"></a>
<span id="l730">     *</span><a href="#l730"></a>
<span id="l731">     * All other writeRecord() calls will not buffer, so do not hold</span><a href="#l731"></a>
<span id="l732">     * these records.</span><a href="#l732"></a>
<span id="l733">     */</span><a href="#l733"></a>
<span id="l734">    void writeRecord(OutputRecord r) throws IOException {</span><a href="#l734"></a>
<span id="l735">        writeRecord(r, false);</span><a href="#l735"></a>
<span id="l736">    }</span><a href="#l736"></a>
<span id="l737"></span><a href="#l737"></a>
<span id="l738">    /*</span><a href="#l738"></a>
<span id="l739">     * Record Output. Application data can't be sent until the first</span><a href="#l739"></a>
<span id="l740">     * handshake establishes a session.</span><a href="#l740"></a>
<span id="l741">     *</span><a href="#l741"></a>
<span id="l742">     * NOTE:  we let empty records be written as a hook to force some</span><a href="#l742"></a>
<span id="l743">     * TCP-level activity, notably handshaking, to occur.</span><a href="#l743"></a>
<span id="l744">     */</span><a href="#l744"></a>
<span id="l745">    void writeRecord(OutputRecord r, boolean holdRecord) throws IOException {</span><a href="#l745"></a>
<span id="l746">        /*</span><a href="#l746"></a>
<span id="l747">         * The loop is in case of HANDSHAKE --&gt; ERROR transitions, etc</span><a href="#l747"></a>
<span id="l748">         */</span><a href="#l748"></a>
<span id="l749">    loop:</span><a href="#l749"></a>
<span id="l750">        while (r.contentType() == Record.ct_application_data) {</span><a href="#l750"></a>
<span id="l751">            /*</span><a href="#l751"></a>
<span id="l752">             * Not all states support passing application data.  We</span><a href="#l752"></a>
<span id="l753">             * synchronize access to the connection state, so that</span><a href="#l753"></a>
<span id="l754">             * synchronous handshakes can complete cleanly.</span><a href="#l754"></a>
<span id="l755">             */</span><a href="#l755"></a>
<span id="l756">            switch (getConnectionState()) {</span><a href="#l756"></a>
<span id="l757"></span><a href="#l757"></a>
<span id="l758">            /*</span><a href="#l758"></a>
<span id="l759">             * We've deferred the initial handshaking till just now,</span><a href="#l759"></a>
<span id="l760">             * when presumably a thread's decided it's OK to block for</span><a href="#l760"></a>
<span id="l761">             * longish periods of time for I/O purposes (as well as</span><a href="#l761"></a>
<span id="l762">             * configured the cipher suites it wants to use).</span><a href="#l762"></a>
<span id="l763">             */</span><a href="#l763"></a>
<span id="l764">            case cs_HANDSHAKE:</span><a href="#l764"></a>
<span id="l765">                performInitialHandshake();</span><a href="#l765"></a>
<span id="l766">                break;</span><a href="#l766"></a>
<span id="l767"></span><a href="#l767"></a>
<span id="l768">            case cs_DATA:</span><a href="#l768"></a>
<span id="l769">            case cs_RENEGOTIATE:</span><a href="#l769"></a>
<span id="l770">                break loop;</span><a href="#l770"></a>
<span id="l771"></span><a href="#l771"></a>
<span id="l772">            case cs_ERROR:</span><a href="#l772"></a>
<span id="l773">                fatal(Alerts.alert_close_notify,</span><a href="#l773"></a>
<span id="l774">                    &quot;error while writing to socket&quot;);</span><a href="#l774"></a>
<span id="l775">                break; // dummy</span><a href="#l775"></a>
<span id="l776"></span><a href="#l776"></a>
<span id="l777">            case cs_SENT_CLOSE:</span><a href="#l777"></a>
<span id="l778">            case cs_CLOSED:</span><a href="#l778"></a>
<span id="l779">            case cs_APP_CLOSED:</span><a href="#l779"></a>
<span id="l780">                // we should never get here (check in AppOutputStream)</span><a href="#l780"></a>
<span id="l781">                // this is just a fallback</span><a href="#l781"></a>
<span id="l782">                if (closeReason != null) {</span><a href="#l782"></a>
<span id="l783">                    throw closeReason;</span><a href="#l783"></a>
<span id="l784">                } else {</span><a href="#l784"></a>
<span id="l785">                    throw new SocketException(&quot;Socket closed&quot;);</span><a href="#l785"></a>
<span id="l786">                }</span><a href="#l786"></a>
<span id="l787"></span><a href="#l787"></a>
<span id="l788">            /*</span><a href="#l788"></a>
<span id="l789">             * Else something's goofy in this state machine's use.</span><a href="#l789"></a>
<span id="l790">             */</span><a href="#l790"></a>
<span id="l791">            default:</span><a href="#l791"></a>
<span id="l792">                throw new SSLProtocolException(&quot;State error, send app data&quot;);</span><a href="#l792"></a>
<span id="l793">            }</span><a href="#l793"></a>
<span id="l794">        }</span><a href="#l794"></a>
<span id="l795"></span><a href="#l795"></a>
<span id="l796">        //</span><a href="#l796"></a>
<span id="l797">        // Don't bother to really write empty records.  We went this</span><a href="#l797"></a>
<span id="l798">        // far to drive the handshake machinery, for correctness; not</span><a href="#l798"></a>
<span id="l799">        // writing empty records improves performance by cutting CPU</span><a href="#l799"></a>
<span id="l800">        // time and network resource usage.  However, some protocol</span><a href="#l800"></a>
<span id="l801">        // implementations are fragile and don't like to see empty</span><a href="#l801"></a>
<span id="l802">        // records, so this also increases robustness.</span><a href="#l802"></a>
<span id="l803">        //</span><a href="#l803"></a>
<span id="l804">        if (!r.isEmpty()) {</span><a href="#l804"></a>
<span id="l805"></span><a href="#l805"></a>
<span id="l806">            // If the record is a close notify alert, we need to honor</span><a href="#l806"></a>
<span id="l807">            // socket option SO_LINGER. Note that we will try to send</span><a href="#l807"></a>
<span id="l808">            // the close notify even if the SO_LINGER set to zero.</span><a href="#l808"></a>
<span id="l809">            if (r.isAlert(Alerts.alert_close_notify) &amp;&amp; getSoLinger() &gt;= 0) {</span><a href="#l809"></a>
<span id="l810"></span><a href="#l810"></a>
<span id="l811">                // keep and clear the current thread interruption status.</span><a href="#l811"></a>
<span id="l812">                boolean interrupted = Thread.interrupted();</span><a href="#l812"></a>
<span id="l813">                try {</span><a href="#l813"></a>
<span id="l814">                    if (writeLock.tryLock(getSoLinger(), TimeUnit.SECONDS)) {</span><a href="#l814"></a>
<span id="l815">                        try {</span><a href="#l815"></a>
<span id="l816">                            writeRecordInternal(r, holdRecord);</span><a href="#l816"></a>
<span id="l817">                        } finally {</span><a href="#l817"></a>
<span id="l818">                            writeLock.unlock();</span><a href="#l818"></a>
<span id="l819">                        }</span><a href="#l819"></a>
<span id="l820">                    } else {</span><a href="#l820"></a>
<span id="l821">                        SSLException ssle = new SSLException(</span><a href="#l821"></a>
<span id="l822">                                &quot;SO_LINGER timeout,&quot; +</span><a href="#l822"></a>
<span id="l823">                                &quot; close_notify message cannot be sent.&quot;);</span><a href="#l823"></a>
<span id="l824"></span><a href="#l824"></a>
<span id="l825"></span><a href="#l825"></a>
<span id="l826">                        // For layered, non-autoclose sockets, we are not</span><a href="#l826"></a>
<span id="l827">                        // able to bring them into a usable state, so we</span><a href="#l827"></a>
<span id="l828">                        // treat it as fatal error.</span><a href="#l828"></a>
<span id="l829">                        if (isLayered() &amp;&amp; !autoClose) {</span><a href="#l829"></a>
<span id="l830">                            // Note that the alert description is</span><a href="#l830"></a>
<span id="l831">                            // specified as -1, so no message will be send</span><a href="#l831"></a>
<span id="l832">                            // to peer anymore.</span><a href="#l832"></a>
<span id="l833">                            fatal((byte)(-1), ssle);</span><a href="#l833"></a>
<span id="l834">                        } else if ((debug != null) &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l834"></a>
<span id="l835">                            System.out.println(</span><a href="#l835"></a>
<span id="l836">                                Thread.currentThread().getName() +</span><a href="#l836"></a>
<span id="l837">                                &quot;, received Exception: &quot; + ssle);</span><a href="#l837"></a>
<span id="l838">                        }</span><a href="#l838"></a>
<span id="l839"></span><a href="#l839"></a>
<span id="l840">                        // RFC2246 requires that the session becomes</span><a href="#l840"></a>
<span id="l841">                        // unresumable if any connection is terminated</span><a href="#l841"></a>
<span id="l842">                        // without proper close_notify messages with</span><a href="#l842"></a>
<span id="l843">                        // level equal to warning.</span><a href="#l843"></a>
<span id="l844">                        //</span><a href="#l844"></a>
<span id="l845">                        // RFC4346 no longer requires that a session not be</span><a href="#l845"></a>
<span id="l846">                        // resumed if failure to properly close a connection.</span><a href="#l846"></a>
<span id="l847">                        //</span><a href="#l847"></a>
<span id="l848">                        // We choose to make the session unresumable if</span><a href="#l848"></a>
<span id="l849">                        // failed to send the close_notify message.</span><a href="#l849"></a>
<span id="l850">                        //</span><a href="#l850"></a>
<span id="l851">                        sess.invalidate();</span><a href="#l851"></a>
<span id="l852">                    }</span><a href="#l852"></a>
<span id="l853">                } catch (InterruptedException ie) {</span><a href="#l853"></a>
<span id="l854">                    // keep interrupted status</span><a href="#l854"></a>
<span id="l855">                    interrupted = true;</span><a href="#l855"></a>
<span id="l856">                }</span><a href="#l856"></a>
<span id="l857"></span><a href="#l857"></a>
<span id="l858">                // restore the interrupted status</span><a href="#l858"></a>
<span id="l859">                if (interrupted) {</span><a href="#l859"></a>
<span id="l860">                    Thread.currentThread().interrupt();</span><a href="#l860"></a>
<span id="l861">                }</span><a href="#l861"></a>
<span id="l862">            } else {</span><a href="#l862"></a>
<span id="l863">                writeLock.lock();</span><a href="#l863"></a>
<span id="l864">                try {</span><a href="#l864"></a>
<span id="l865">                    writeRecordInternal(r, holdRecord);</span><a href="#l865"></a>
<span id="l866">                } finally {</span><a href="#l866"></a>
<span id="l867">                    writeLock.unlock();</span><a href="#l867"></a>
<span id="l868">                }</span><a href="#l868"></a>
<span id="l869">            }</span><a href="#l869"></a>
<span id="l870">        }</span><a href="#l870"></a>
<span id="l871">    }</span><a href="#l871"></a>
<span id="l872"></span><a href="#l872"></a>
<span id="l873">    private void writeRecordInternal(OutputRecord r,</span><a href="#l873"></a>
<span id="l874">            boolean holdRecord) throws IOException {</span><a href="#l874"></a>
<span id="l875"></span><a href="#l875"></a>
<span id="l876">        // r.compress(c);</span><a href="#l876"></a>
<span id="l877">        r.encrypt(writeAuthenticator, writeCipher);</span><a href="#l877"></a>
<span id="l878"></span><a href="#l878"></a>
<span id="l879">        if (holdRecord) {</span><a href="#l879"></a>
<span id="l880">            // If we were requested to delay the record due to possibility</span><a href="#l880"></a>
<span id="l881">            // of Nagle's being active when finally got to writing, and</span><a href="#l881"></a>
<span id="l882">            // it's actually not, we don't really need to delay it.</span><a href="#l882"></a>
<span id="l883">            if (getTcpNoDelay()) {</span><a href="#l883"></a>
<span id="l884">                holdRecord = false;</span><a href="#l884"></a>
<span id="l885">            } else {</span><a href="#l885"></a>
<span id="l886">                // We need to hold the record, so let's provide</span><a href="#l886"></a>
<span id="l887">                // a per-socket place to do it.</span><a href="#l887"></a>
<span id="l888">                if (heldRecordBuffer == null) {</span><a href="#l888"></a>
<span id="l889">                    // Likely only need 37 bytes.</span><a href="#l889"></a>
<span id="l890">                    heldRecordBuffer = new ByteArrayOutputStream(40);</span><a href="#l890"></a>
<span id="l891">                }</span><a href="#l891"></a>
<span id="l892">            }</span><a href="#l892"></a>
<span id="l893">        }</span><a href="#l893"></a>
<span id="l894">        r.write(sockOutput, holdRecord, heldRecordBuffer);</span><a href="#l894"></a>
<span id="l895"></span><a href="#l895"></a>
<span id="l896">        /*</span><a href="#l896"></a>
<span id="l897">         * Check the sequence number state</span><a href="#l897"></a>
<span id="l898">         *</span><a href="#l898"></a>
<span id="l899">         * Note that in order to maintain the connection I/O</span><a href="#l899"></a>
<span id="l900">         * properly, we check the sequence number after the last</span><a href="#l900"></a>
<span id="l901">         * record writing process. As we request renegotiation</span><a href="#l901"></a>
<span id="l902">         * or close the connection for wrapped sequence number</span><a href="#l902"></a>
<span id="l903">         * when there is enough sequence number space left to</span><a href="#l903"></a>
<span id="l904">         * handle a few more records, so the sequence number</span><a href="#l904"></a>
<span id="l905">         * of the last record cannot be wrapped.</span><a href="#l905"></a>
<span id="l906">         */</span><a href="#l906"></a>
<span id="l907">        if (connectionState &lt; cs_ERROR) {</span><a href="#l907"></a>
<span id="l908">            checkSequenceNumber(writeAuthenticator, r.contentType());</span><a href="#l908"></a>
<span id="l909">        }</span><a href="#l909"></a>
<span id="l910"></span><a href="#l910"></a>
<span id="l911">        // turn off the flag of the first application record</span><a href="#l911"></a>
<span id="l912">        if (isFirstAppOutputRecord &amp;&amp;</span><a href="#l912"></a>
<span id="l913">                r.contentType() == Record.ct_application_data) {</span><a href="#l913"></a>
<span id="l914">            isFirstAppOutputRecord = false;</span><a href="#l914"></a>
<span id="l915">        }</span><a href="#l915"></a>
<span id="l916">    }</span><a href="#l916"></a>
<span id="l917"></span><a href="#l917"></a>
<span id="l918">    /*</span><a href="#l918"></a>
<span id="l919">     * Need to split the payload except the following cases:</span><a href="#l919"></a>
<span id="l920">     *</span><a href="#l920"></a>
<span id="l921">     * 1. protocol version is TLS 1.1 or later;</span><a href="#l921"></a>
<span id="l922">     * 2. bulk cipher does not use CBC mode, including null bulk cipher suites.</span><a href="#l922"></a>
<span id="l923">     * 3. the payload is the first application record of a freshly</span><a href="#l923"></a>
<span id="l924">     *    negotiated TLS session.</span><a href="#l924"></a>
<span id="l925">     * 4. the CBC protection is disabled;</span><a href="#l925"></a>
<span id="l926">     *</span><a href="#l926"></a>
<span id="l927">     * More details, please refer to AppOutputStream.write(byte[], int, int).</span><a href="#l927"></a>
<span id="l928">     */</span><a href="#l928"></a>
<span id="l929">    boolean needToSplitPayload() {</span><a href="#l929"></a>
<span id="l930">        writeLock.lock();</span><a href="#l930"></a>
<span id="l931">        try {</span><a href="#l931"></a>
<span id="l932">            return (protocolVersion.v &lt;= ProtocolVersion.TLS10.v) &amp;&amp;</span><a href="#l932"></a>
<span id="l933">                    writeCipher.isCBCMode() &amp;&amp; !isFirstAppOutputRecord &amp;&amp;</span><a href="#l933"></a>
<span id="l934">                    Record.enableCBCProtection;</span><a href="#l934"></a>
<span id="l935">        } finally {</span><a href="#l935"></a>
<span id="l936">            writeLock.unlock();</span><a href="#l936"></a>
<span id="l937">        }</span><a href="#l937"></a>
<span id="l938">    }</span><a href="#l938"></a>
<span id="l939"></span><a href="#l939"></a>
<span id="l940">    /*</span><a href="#l940"></a>
<span id="l941">     * Read an application data record.  Alerts and handshake</span><a href="#l941"></a>
<span id="l942">     * messages are handled directly.</span><a href="#l942"></a>
<span id="l943">     */</span><a href="#l943"></a>
<span id="l944">    void readDataRecord(InputRecord r) throws IOException {</span><a href="#l944"></a>
<span id="l945">        if (getConnectionState() == cs_HANDSHAKE) {</span><a href="#l945"></a>
<span id="l946">            performInitialHandshake();</span><a href="#l946"></a>
<span id="l947">        }</span><a href="#l947"></a>
<span id="l948">        readRecord(r, true);</span><a href="#l948"></a>
<span id="l949">    }</span><a href="#l949"></a>
<span id="l950"></span><a href="#l950"></a>
<span id="l951">    /*</span><a href="#l951"></a>
<span id="l952">     * Clear the pipeline of records from the peer, optionally returning</span><a href="#l952"></a>
<span id="l953">     * application data.   Caller is responsible for knowing that it's</span><a href="#l953"></a>
<span id="l954">     * possible to do this kind of clearing, if they don't want app</span><a href="#l954"></a>
<span id="l955">     * data -- e.g. since it's the initial SSL handshake.</span><a href="#l955"></a>
<span id="l956">     *</span><a href="#l956"></a>
<span id="l957">     * Don't synchronize (this) during a blocking read() since it</span><a href="#l957"></a>
<span id="l958">     * protects data which is accessed on the write side as well.</span><a href="#l958"></a>
<span id="l959">     */</span><a href="#l959"></a>
<span id="l960">    private void readRecord(InputRecord r, boolean needAppData)</span><a href="#l960"></a>
<span id="l961">            throws IOException {</span><a href="#l961"></a>
<span id="l962">        int state;</span><a href="#l962"></a>
<span id="l963"></span><a href="#l963"></a>
<span id="l964">        // readLock protects reading and processing of an InputRecord.</span><a href="#l964"></a>
<span id="l965">        // It keeps the reading from sockInput and processing of the record</span><a href="#l965"></a>
<span id="l966">        // atomic so that no two threads can be blocked on the</span><a href="#l966"></a>
<span id="l967">        // read from the same input stream at the same time.</span><a href="#l967"></a>
<span id="l968">        // This is required for example when a reader thread is</span><a href="#l968"></a>
<span id="l969">        // blocked on the read and another thread is trying to</span><a href="#l969"></a>
<span id="l970">        // close the socket. For a non-autoclose, layered socket,</span><a href="#l970"></a>
<span id="l971">        // the thread performing the close needs to read the close_notify.</span><a href="#l971"></a>
<span id="l972">        //</span><a href="#l972"></a>
<span id="l973">        // Use readLock instead of 'this' for locking because</span><a href="#l973"></a>
<span id="l974">        // 'this' also protects data accessed during writing.</span><a href="#l974"></a>
<span id="l975">      synchronized (readLock) {</span><a href="#l975"></a>
<span id="l976">        /*</span><a href="#l976"></a>
<span id="l977">         * Read and handle records ... return application data</span><a href="#l977"></a>
<span id="l978">         * ONLY if it's needed.</span><a href="#l978"></a>
<span id="l979">         */</span><a href="#l979"></a>
<span id="l980"></span><a href="#l980"></a>
<span id="l981">        while (((state = getConnectionState()) != cs_CLOSED) &amp;&amp;</span><a href="#l981"></a>
<span id="l982">                (state != cs_ERROR) &amp;&amp; (state != cs_APP_CLOSED)) {</span><a href="#l982"></a>
<span id="l983">            /*</span><a href="#l983"></a>
<span id="l984">             * Read a record ... maybe emitting an alert if we get a</span><a href="#l984"></a>
<span id="l985">             * comprehensible but unsupported &quot;hello&quot; message during</span><a href="#l985"></a>
<span id="l986">             * format checking (e.g. V2).</span><a href="#l986"></a>
<span id="l987">             */</span><a href="#l987"></a>
<span id="l988">            try {</span><a href="#l988"></a>
<span id="l989">                r.setAppDataValid(false);</span><a href="#l989"></a>
<span id="l990">                r.read(sockInput, sockOutput);</span><a href="#l990"></a>
<span id="l991">            } catch (SSLProtocolException e) {</span><a href="#l991"></a>
<span id="l992">                try {</span><a href="#l992"></a>
<span id="l993">                    fatal(Alerts.alert_unexpected_message, e);</span><a href="#l993"></a>
<span id="l994">                } catch (IOException x) {</span><a href="#l994"></a>
<span id="l995">                    // discard this exception</span><a href="#l995"></a>
<span id="l996">                }</span><a href="#l996"></a>
<span id="l997">                throw e;</span><a href="#l997"></a>
<span id="l998">            } catch (EOFException eof) {</span><a href="#l998"></a>
<span id="l999">                boolean handshaking = (getConnectionState() &lt;= cs_HANDSHAKE);</span><a href="#l999"></a>
<span id="l1000">                boolean rethrow = requireCloseNotify || handshaking;</span><a href="#l1000"></a>
<span id="l1001">                if ((debug != null) &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l1001"></a>
<span id="l1002">                    System.out.println(Thread.currentThread().getName() +</span><a href="#l1002"></a>
<span id="l1003">                        &quot;, received EOFException: &quot;</span><a href="#l1003"></a>
<span id="l1004">                        + (rethrow ? &quot;error&quot; : &quot;ignored&quot;));</span><a href="#l1004"></a>
<span id="l1005">                }</span><a href="#l1005"></a>
<span id="l1006">                if (rethrow) {</span><a href="#l1006"></a>
<span id="l1007">                    SSLException e;</span><a href="#l1007"></a>
<span id="l1008">                    if (handshaking) {</span><a href="#l1008"></a>
<span id="l1009">                        e = new SSLHandshakeException</span><a href="#l1009"></a>
<span id="l1010">                            (&quot;Remote host closed connection during handshake&quot;);</span><a href="#l1010"></a>
<span id="l1011">                    } else {</span><a href="#l1011"></a>
<span id="l1012">                        e = new SSLProtocolException</span><a href="#l1012"></a>
<span id="l1013">                            (&quot;Remote host closed connection incorrectly&quot;);</span><a href="#l1013"></a>
<span id="l1014">                    }</span><a href="#l1014"></a>
<span id="l1015">                    e.initCause(eof);</span><a href="#l1015"></a>
<span id="l1016">                    throw e;</span><a href="#l1016"></a>
<span id="l1017">                } else {</span><a href="#l1017"></a>
<span id="l1018">                    // treat as if we had received a close_notify</span><a href="#l1018"></a>
<span id="l1019">                    closeInternal(false);</span><a href="#l1019"></a>
<span id="l1020">                    continue;</span><a href="#l1020"></a>
<span id="l1021">                }</span><a href="#l1021"></a>
<span id="l1022">            }</span><a href="#l1022"></a>
<span id="l1023"></span><a href="#l1023"></a>
<span id="l1024"></span><a href="#l1024"></a>
<span id="l1025">            /*</span><a href="#l1025"></a>
<span id="l1026">             * The basic SSLv3 record protection involves (optional)</span><a href="#l1026"></a>
<span id="l1027">             * encryption for privacy, and an integrity check ensuring</span><a href="#l1027"></a>
<span id="l1028">             * data origin authentication.  We do them both here, and</span><a href="#l1028"></a>
<span id="l1029">             * throw a fatal alert if the integrity check fails.</span><a href="#l1029"></a>
<span id="l1030">             */</span><a href="#l1030"></a>
<span id="l1031">            try {</span><a href="#l1031"></a>
<span id="l1032">                r.decrypt(readAuthenticator, readCipher);</span><a href="#l1032"></a>
<span id="l1033">            } catch (BadPaddingException e) {</span><a href="#l1033"></a>
<span id="l1034">                byte alertType = (r.contentType() == Record.ct_handshake)</span><a href="#l1034"></a>
<span id="l1035">                                        ? Alerts.alert_handshake_failure</span><a href="#l1035"></a>
<span id="l1036">                                        : Alerts.alert_bad_record_mac;</span><a href="#l1036"></a>
<span id="l1037">                fatal(alertType, e.getMessage(), e);</span><a href="#l1037"></a>
<span id="l1038">            }</span><a href="#l1038"></a>
<span id="l1039"></span><a href="#l1039"></a>
<span id="l1040">            // if (!r.decompress(c))</span><a href="#l1040"></a>
<span id="l1041">            //     fatal(Alerts.alert_decompression_failure,</span><a href="#l1041"></a>
<span id="l1042">            //         &quot;decompression failure&quot;);</span><a href="#l1042"></a>
<span id="l1043"></span><a href="#l1043"></a>
<span id="l1044">            /*</span><a href="#l1044"></a>
<span id="l1045">             * Process the record.</span><a href="#l1045"></a>
<span id="l1046">             */</span><a href="#l1046"></a>
<span id="l1047">            synchronized (this) {</span><a href="#l1047"></a>
<span id="l1048">              switch (r.contentType()) {</span><a href="#l1048"></a>
<span id="l1049">                case Record.ct_handshake:</span><a href="#l1049"></a>
<span id="l1050">                    /*</span><a href="#l1050"></a>
<span id="l1051">                     * Handshake messages always go to a pending session</span><a href="#l1051"></a>
<span id="l1052">                     * handshaker ... if there isn't one, create one.  This</span><a href="#l1052"></a>
<span id="l1053">                     * must work asynchronously, for renegotiation.</span><a href="#l1053"></a>
<span id="l1054">                     *</span><a href="#l1054"></a>
<span id="l1055">                     * NOTE that handshaking will either resume a session</span><a href="#l1055"></a>
<span id="l1056">                     * which was in the cache (and which might have other</span><a href="#l1056"></a>
<span id="l1057">                     * connections in it already), or else will start a new</span><a href="#l1057"></a>
<span id="l1058">                     * session (new keys exchanged) with just this connection</span><a href="#l1058"></a>
<span id="l1059">                     * in it.</span><a href="#l1059"></a>
<span id="l1060">                     */</span><a href="#l1060"></a>
<span id="l1061">                    initHandshaker();</span><a href="#l1061"></a>
<span id="l1062">                    if (!handshaker.activated()) {</span><a href="#l1062"></a>
<span id="l1063">                        // prior to handshaking, activate the handshake</span><a href="#l1063"></a>
<span id="l1064">                        if (connectionState == cs_RENEGOTIATE) {</span><a href="#l1064"></a>
<span id="l1065">                            // don't use SSLv2Hello when renegotiating</span><a href="#l1065"></a>
<span id="l1066">                            handshaker.activate(protocolVersion);</span><a href="#l1066"></a>
<span id="l1067">                        } else {</span><a href="#l1067"></a>
<span id="l1068">                            handshaker.activate(null);</span><a href="#l1068"></a>
<span id="l1069">                        }</span><a href="#l1069"></a>
<span id="l1070">                    }</span><a href="#l1070"></a>
<span id="l1071"></span><a href="#l1071"></a>
<span id="l1072">                    /*</span><a href="#l1072"></a>
<span id="l1073">                     * process the handshake record ... may contain just</span><a href="#l1073"></a>
<span id="l1074">                     * a partial handshake message or multiple messages.</span><a href="#l1074"></a>
<span id="l1075">                     *</span><a href="#l1075"></a>
<span id="l1076">                     * The handshaker state machine will ensure that it's</span><a href="#l1076"></a>
<span id="l1077">                     * a finished message.</span><a href="#l1077"></a>
<span id="l1078">                     */</span><a href="#l1078"></a>
<span id="l1079">                    handshaker.process_record(r, expectingFinished);</span><a href="#l1079"></a>
<span id="l1080">                    expectingFinished = false;</span><a href="#l1080"></a>
<span id="l1081"></span><a href="#l1081"></a>
<span id="l1082">                    if (handshaker.invalidated) {</span><a href="#l1082"></a>
<span id="l1083">                        handshaker = null;</span><a href="#l1083"></a>
<span id="l1084">                        inrec.setHandshakeHash(null);</span><a href="#l1084"></a>
<span id="l1085"></span><a href="#l1085"></a>
<span id="l1086">                        // if state is cs_RENEGOTIATE, revert it to cs_DATA</span><a href="#l1086"></a>
<span id="l1087">                        if (connectionState == cs_RENEGOTIATE) {</span><a href="#l1087"></a>
<span id="l1088">                            connectionState = cs_DATA;</span><a href="#l1088"></a>
<span id="l1089">                        }</span><a href="#l1089"></a>
<span id="l1090">                    } else if (handshaker.isDone()) {</span><a href="#l1090"></a>
<span id="l1091">                        // reset the parameters for secure renegotiation.</span><a href="#l1091"></a>
<span id="l1092">                        secureRenegotiation =</span><a href="#l1092"></a>
<span id="l1093">                                        handshaker.isSecureRenegotiation();</span><a href="#l1093"></a>
<span id="l1094">                        clientVerifyData = handshaker.getClientVerifyData();</span><a href="#l1094"></a>
<span id="l1095">                        serverVerifyData = handshaker.getServerVerifyData();</span><a href="#l1095"></a>
<span id="l1096">                        // set connection ALPN value</span><a href="#l1096"></a>
<span id="l1097">                        applicationProtocol =</span><a href="#l1097"></a>
<span id="l1098">                            handshaker.getHandshakeApplicationProtocol();</span><a href="#l1098"></a>
<span id="l1099"></span><a href="#l1099"></a>
<span id="l1100">                        sess = handshaker.getSession();</span><a href="#l1100"></a>
<span id="l1101">                        handshakeSession = null;</span><a href="#l1101"></a>
<span id="l1102">                        handshaker = null;</span><a href="#l1102"></a>
<span id="l1103">                        connectionState = cs_DATA;</span><a href="#l1103"></a>
<span id="l1104"></span><a href="#l1104"></a>
<span id="l1105">                        //</span><a href="#l1105"></a>
<span id="l1106">                        // Tell folk about handshake completion, but do</span><a href="#l1106"></a>
<span id="l1107">                        // it in a separate thread.</span><a href="#l1107"></a>
<span id="l1108">                        //</span><a href="#l1108"></a>
<span id="l1109">                        if (handshakeListeners != null) {</span><a href="#l1109"></a>
<span id="l1110">                            HandshakeCompletedEvent event =</span><a href="#l1110"></a>
<span id="l1111">                                new HandshakeCompletedEvent(this, sess);</span><a href="#l1111"></a>
<span id="l1112"></span><a href="#l1112"></a>
<span id="l1113">                            Thread t = new NotifyHandshakeThread(</span><a href="#l1113"></a>
<span id="l1114">                                handshakeListeners.entrySet(), event);</span><a href="#l1114"></a>
<span id="l1115">                            t.start();</span><a href="#l1115"></a>
<span id="l1116">                        }</span><a href="#l1116"></a>
<span id="l1117">                    }</span><a href="#l1117"></a>
<span id="l1118"></span><a href="#l1118"></a>
<span id="l1119">                    if (needAppData || connectionState != cs_DATA) {</span><a href="#l1119"></a>
<span id="l1120">                        continue;</span><a href="#l1120"></a>
<span id="l1121">                    }</span><a href="#l1121"></a>
<span id="l1122">                    break;</span><a href="#l1122"></a>
<span id="l1123"></span><a href="#l1123"></a>
<span id="l1124">                case Record.ct_application_data:</span><a href="#l1124"></a>
<span id="l1125">                    // Pass this right back up to the application.</span><a href="#l1125"></a>
<span id="l1126">                    if (connectionState != cs_DATA</span><a href="#l1126"></a>
<span id="l1127">                            &amp;&amp; connectionState != cs_RENEGOTIATE</span><a href="#l1127"></a>
<span id="l1128">                            &amp;&amp; connectionState != cs_SENT_CLOSE) {</span><a href="#l1128"></a>
<span id="l1129">                        throw new SSLProtocolException(</span><a href="#l1129"></a>
<span id="l1130">                            &quot;Data received in non-data state: &quot; +</span><a href="#l1130"></a>
<span id="l1131">                            connectionState);</span><a href="#l1131"></a>
<span id="l1132">                    }</span><a href="#l1132"></a>
<span id="l1133">                    if (expectingFinished) {</span><a href="#l1133"></a>
<span id="l1134">                        throw new SSLProtocolException</span><a href="#l1134"></a>
<span id="l1135">                                (&quot;Expecting finished message, received data&quot;);</span><a href="#l1135"></a>
<span id="l1136">                    }</span><a href="#l1136"></a>
<span id="l1137">                    if (!needAppData) {</span><a href="#l1137"></a>
<span id="l1138">                        throw new SSLException(&quot;Discarding app data&quot;);</span><a href="#l1138"></a>
<span id="l1139">                    }</span><a href="#l1139"></a>
<span id="l1140"></span><a href="#l1140"></a>
<span id="l1141">                    r.setAppDataValid(true);</span><a href="#l1141"></a>
<span id="l1142">                    break;</span><a href="#l1142"></a>
<span id="l1143"></span><a href="#l1143"></a>
<span id="l1144">                case Record.ct_alert:</span><a href="#l1144"></a>
<span id="l1145">                    recvAlert(r);</span><a href="#l1145"></a>
<span id="l1146">                    continue;</span><a href="#l1146"></a>
<span id="l1147"></span><a href="#l1147"></a>
<span id="l1148">                case Record.ct_change_cipher_spec:</span><a href="#l1148"></a>
<span id="l1149">                    if ((connectionState != cs_HANDSHAKE</span><a href="#l1149"></a>
<span id="l1150">                                &amp;&amp; connectionState != cs_RENEGOTIATE)) {</span><a href="#l1150"></a>
<span id="l1151">                        // For the CCS message arriving in the wrong state</span><a href="#l1151"></a>
<span id="l1152">                        fatal(Alerts.alert_unexpected_message,</span><a href="#l1152"></a>
<span id="l1153">                                &quot;illegal change cipher spec msg, conn state = &quot;</span><a href="#l1153"></a>
<span id="l1154">                                + connectionState);</span><a href="#l1154"></a>
<span id="l1155">                    } else if (r.available() != 1 || r.read() != 1) {</span><a href="#l1155"></a>
<span id="l1156">                        // For structural/content issues with the CCS</span><a href="#l1156"></a>
<span id="l1157">                        fatal(Alerts.alert_unexpected_message,</span><a href="#l1157"></a>
<span id="l1158">                                &quot;Malformed change cipher spec msg&quot;);</span><a href="#l1158"></a>
<span id="l1159">                    }</span><a href="#l1159"></a>
<span id="l1160"></span><a href="#l1160"></a>
<span id="l1161">                    //</span><a href="#l1161"></a>
<span id="l1162">                    // The first message after a change_cipher_spec</span><a href="#l1162"></a>
<span id="l1163">                    // record MUST be a &quot;Finished&quot; handshake record,</span><a href="#l1163"></a>
<span id="l1164">                    // else it's a protocol violation.  We force this</span><a href="#l1164"></a>
<span id="l1165">                    // to be checked by a minor tweak to the state</span><a href="#l1165"></a>
<span id="l1166">                    // machine.</span><a href="#l1166"></a>
<span id="l1167">                    //</span><a href="#l1167"></a>
<span id="l1168">                    handshaker.receiveChangeCipherSpec();</span><a href="#l1168"></a>
<span id="l1169">                    changeReadCiphers();</span><a href="#l1169"></a>
<span id="l1170">                    // next message MUST be a finished message</span><a href="#l1170"></a>
<span id="l1171">                    expectingFinished = true;</span><a href="#l1171"></a>
<span id="l1172">                    continue;</span><a href="#l1172"></a>
<span id="l1173"></span><a href="#l1173"></a>
<span id="l1174">                default:</span><a href="#l1174"></a>
<span id="l1175">                    //</span><a href="#l1175"></a>
<span id="l1176">                    // TLS requires that unrecognized records be ignored.</span><a href="#l1176"></a>
<span id="l1177">                    //</span><a href="#l1177"></a>
<span id="l1178">                    if (debug != null &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l1178"></a>
<span id="l1179">                        System.out.println(Thread.currentThread().getName() +</span><a href="#l1179"></a>
<span id="l1180">                            &quot;, Received record type: &quot;</span><a href="#l1180"></a>
<span id="l1181">                            + r.contentType());</span><a href="#l1181"></a>
<span id="l1182">                    }</span><a href="#l1182"></a>
<span id="l1183">                    continue;</span><a href="#l1183"></a>
<span id="l1184">              } // switch</span><a href="#l1184"></a>
<span id="l1185"></span><a href="#l1185"></a>
<span id="l1186">              /*</span><a href="#l1186"></a>
<span id="l1187">               * Check the sequence number state</span><a href="#l1187"></a>
<span id="l1188">               *</span><a href="#l1188"></a>
<span id="l1189">               * Note that in order to maintain the connection I/O</span><a href="#l1189"></a>
<span id="l1190">               * properly, we check the sequence number after the last</span><a href="#l1190"></a>
<span id="l1191">               * record reading process. As we request renegotiation</span><a href="#l1191"></a>
<span id="l1192">               * or close the connection for wrapped sequence number</span><a href="#l1192"></a>
<span id="l1193">               * when there is enough sequence number space left to</span><a href="#l1193"></a>
<span id="l1194">               * handle a few more records, so the sequence number</span><a href="#l1194"></a>
<span id="l1195">               * of the last record cannot be wrapped.</span><a href="#l1195"></a>
<span id="l1196">               */</span><a href="#l1196"></a>
<span id="l1197">              if (connectionState &lt; cs_ERROR) {</span><a href="#l1197"></a>
<span id="l1198">                  checkSequenceNumber(readAuthenticator, r.contentType());</span><a href="#l1198"></a>
<span id="l1199">              }</span><a href="#l1199"></a>
<span id="l1200"></span><a href="#l1200"></a>
<span id="l1201">              return;</span><a href="#l1201"></a>
<span id="l1202">            } // synchronized (this)</span><a href="#l1202"></a>
<span id="l1203">        }</span><a href="#l1203"></a>
<span id="l1204"></span><a href="#l1204"></a>
<span id="l1205">        //</span><a href="#l1205"></a>
<span id="l1206">        // couldn't read, due to some kind of error</span><a href="#l1206"></a>
<span id="l1207">        //</span><a href="#l1207"></a>
<span id="l1208">        r.close();</span><a href="#l1208"></a>
<span id="l1209">        return;</span><a href="#l1209"></a>
<span id="l1210">      }  // synchronized (readLock)</span><a href="#l1210"></a>
<span id="l1211">    }</span><a href="#l1211"></a>
<span id="l1212"></span><a href="#l1212"></a>
<span id="l1213">    /**</span><a href="#l1213"></a>
<span id="l1214">     * Check the sequence number state</span><a href="#l1214"></a>
<span id="l1215">     *</span><a href="#l1215"></a>
<span id="l1216">     * RFC 4346 states that, &quot;Sequence numbers are of type uint64 and</span><a href="#l1216"></a>
<span id="l1217">     * may not exceed 2^64-1.  Sequence numbers do not wrap. If a TLS</span><a href="#l1217"></a>
<span id="l1218">     * implementation would need to wrap a sequence number, it must</span><a href="#l1218"></a>
<span id="l1219">     * renegotiate instead.&quot;</span><a href="#l1219"></a>
<span id="l1220">     */</span><a href="#l1220"></a>
<span id="l1221">    private void checkSequenceNumber(Authenticator authenticator, byte type)</span><a href="#l1221"></a>
<span id="l1222">            throws IOException {</span><a href="#l1222"></a>
<span id="l1223"></span><a href="#l1223"></a>
<span id="l1224">        /*</span><a href="#l1224"></a>
<span id="l1225">         * Don't bother to check the sequence number for error or</span><a href="#l1225"></a>
<span id="l1226">         * closed connections, or NULL MAC.</span><a href="#l1226"></a>
<span id="l1227">         */</span><a href="#l1227"></a>
<span id="l1228">        if (connectionState &gt;= cs_ERROR || authenticator == MAC.NULL) {</span><a href="#l1228"></a>
<span id="l1229">            return;</span><a href="#l1229"></a>
<span id="l1230">        }</span><a href="#l1230"></a>
<span id="l1231"></span><a href="#l1231"></a>
<span id="l1232">        /*</span><a href="#l1232"></a>
<span id="l1233">         * Conservatively, close the connection immediately when the</span><a href="#l1233"></a>
<span id="l1234">         * sequence number is close to overflow</span><a href="#l1234"></a>
<span id="l1235">         */</span><a href="#l1235"></a>
<span id="l1236">        if (authenticator.seqNumOverflow()) {</span><a href="#l1236"></a>
<span id="l1237">            /*</span><a href="#l1237"></a>
<span id="l1238">             * TLS protocols do not define a error alert for sequence</span><a href="#l1238"></a>
<span id="l1239">             * number overflow. We use handshake_failure error alert</span><a href="#l1239"></a>
<span id="l1240">             * for handshaking and bad_record_mac for other records.</span><a href="#l1240"></a>
<span id="l1241">             */</span><a href="#l1241"></a>
<span id="l1242">            if (debug != null &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l1242"></a>
<span id="l1243">                System.out.println(Thread.currentThread().getName() +</span><a href="#l1243"></a>
<span id="l1244">                    &quot;, sequence number extremely close to overflow &quot; +</span><a href="#l1244"></a>
<span id="l1245">                    &quot;(2^64-1 packets). Closing connection.&quot;);</span><a href="#l1245"></a>
<span id="l1246"></span><a href="#l1246"></a>
<span id="l1247">            }</span><a href="#l1247"></a>
<span id="l1248"></span><a href="#l1248"></a>
<span id="l1249">            fatal(Alerts.alert_handshake_failure, &quot;sequence number overflow&quot;);</span><a href="#l1249"></a>
<span id="l1250">        }</span><a href="#l1250"></a>
<span id="l1251"></span><a href="#l1251"></a>
<span id="l1252">        /*</span><a href="#l1252"></a>
<span id="l1253">         * Ask for renegotiation when need to renew sequence number.</span><a href="#l1253"></a>
<span id="l1254">         *</span><a href="#l1254"></a>
<span id="l1255">         * Don't bother to kickstart the renegotiation when the local is</span><a href="#l1255"></a>
<span id="l1256">         * asking for it.</span><a href="#l1256"></a>
<span id="l1257">         */</span><a href="#l1257"></a>
<span id="l1258">        if ((type != Record.ct_handshake) &amp;&amp; authenticator.seqNumIsHuge()) {</span><a href="#l1258"></a>
<span id="l1259">            if (debug != null &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l1259"></a>
<span id="l1260">                System.out.println(Thread.currentThread().getName() +</span><a href="#l1260"></a>
<span id="l1261">                        &quot;, request renegotiation &quot; +</span><a href="#l1261"></a>
<span id="l1262">                        &quot;to avoid sequence number overflow&quot;);</span><a href="#l1262"></a>
<span id="l1263">            }</span><a href="#l1263"></a>
<span id="l1264"></span><a href="#l1264"></a>
<span id="l1265">            startHandshake();</span><a href="#l1265"></a>
<span id="l1266">        }</span><a href="#l1266"></a>
<span id="l1267">    }</span><a href="#l1267"></a>
<span id="l1268"></span><a href="#l1268"></a>
<span id="l1269">    //</span><a href="#l1269"></a>
<span id="l1270">    // HANDSHAKE RELATED CODE</span><a href="#l1270"></a>
<span id="l1271">    //</span><a href="#l1271"></a>
<span id="l1272"></span><a href="#l1272"></a>
<span id="l1273">    /**</span><a href="#l1273"></a>
<span id="l1274">     * Return the AppInputStream. For use by Handshaker only.</span><a href="#l1274"></a>
<span id="l1275">     */</span><a href="#l1275"></a>
<span id="l1276">    AppInputStream getAppInputStream() {</span><a href="#l1276"></a>
<span id="l1277">        return input;</span><a href="#l1277"></a>
<span id="l1278">    }</span><a href="#l1278"></a>
<span id="l1279"></span><a href="#l1279"></a>
<span id="l1280">    /**</span><a href="#l1280"></a>
<span id="l1281">     * Return the AppOutputStream. For use by Handshaker only.</span><a href="#l1281"></a>
<span id="l1282">     */</span><a href="#l1282"></a>
<span id="l1283">    AppOutputStream getAppOutputStream() {</span><a href="#l1283"></a>
<span id="l1284">        return output;</span><a href="#l1284"></a>
<span id="l1285">    }</span><a href="#l1285"></a>
<span id="l1286"></span><a href="#l1286"></a>
<span id="l1287">    /**</span><a href="#l1287"></a>
<span id="l1288">     * Initialize the handshaker object. This means:</span><a href="#l1288"></a>
<span id="l1289">     *</span><a href="#l1289"></a>
<span id="l1290">     *  . if a handshake is already in progress (state is cs_HANDSHAKE</span><a href="#l1290"></a>
<span id="l1291">     *    or cs_RENEGOTIATE), do nothing and return</span><a href="#l1291"></a>
<span id="l1292">     *</span><a href="#l1292"></a>
<span id="l1293">     *  . if the socket is already closed, throw an Exception (internal error)</span><a href="#l1293"></a>
<span id="l1294">     *</span><a href="#l1294"></a>
<span id="l1295">     *  . otherwise (cs_START or cs_DATA), create the appropriate handshaker</span><a href="#l1295"></a>
<span id="l1296">     *    object, and advance the connection state (to cs_HANDSHAKE or</span><a href="#l1296"></a>
<span id="l1297">     *    cs_RENEGOTIATE, respectively).</span><a href="#l1297"></a>
<span id="l1298">     *</span><a href="#l1298"></a>
<span id="l1299">     * This method is called right after a new socket is created, when</span><a href="#l1299"></a>
<span id="l1300">     * starting renegotiation, or when changing client/ server mode of the</span><a href="#l1300"></a>
<span id="l1301">     * socket.</span><a href="#l1301"></a>
<span id="l1302">     */</span><a href="#l1302"></a>
<span id="l1303">    private void initHandshaker() {</span><a href="#l1303"></a>
<span id="l1304">        switch (connectionState) {</span><a href="#l1304"></a>
<span id="l1305"></span><a href="#l1305"></a>
<span id="l1306">        //</span><a href="#l1306"></a>
<span id="l1307">        // Starting a new handshake.</span><a href="#l1307"></a>
<span id="l1308">        //</span><a href="#l1308"></a>
<span id="l1309">        case cs_START:</span><a href="#l1309"></a>
<span id="l1310">        case cs_DATA:</span><a href="#l1310"></a>
<span id="l1311">            break;</span><a href="#l1311"></a>
<span id="l1312"></span><a href="#l1312"></a>
<span id="l1313">        //</span><a href="#l1313"></a>
<span id="l1314">        // We're already in the middle of a handshake.</span><a href="#l1314"></a>
<span id="l1315">        //</span><a href="#l1315"></a>
<span id="l1316">        case cs_HANDSHAKE:</span><a href="#l1316"></a>
<span id="l1317">        case cs_RENEGOTIATE:</span><a href="#l1317"></a>
<span id="l1318">            return;</span><a href="#l1318"></a>
<span id="l1319"></span><a href="#l1319"></a>
<span id="l1320">        //</span><a href="#l1320"></a>
<span id="l1321">        // Anyone allowed to call this routine is required to</span><a href="#l1321"></a>
<span id="l1322">        // do so ONLY if the connection state is reasonable...</span><a href="#l1322"></a>
<span id="l1323">        //</span><a href="#l1323"></a>
<span id="l1324">        default:</span><a href="#l1324"></a>
<span id="l1325">            throw new IllegalStateException(&quot;Internal error&quot;);</span><a href="#l1325"></a>
<span id="l1326">        }</span><a href="#l1326"></a>
<span id="l1327"></span><a href="#l1327"></a>
<span id="l1328">        // state is either cs_START or cs_DATA</span><a href="#l1328"></a>
<span id="l1329">        if (connectionState == cs_START) {</span><a href="#l1329"></a>
<span id="l1330">            connectionState = cs_HANDSHAKE;</span><a href="#l1330"></a>
<span id="l1331">        } else { // cs_DATA</span><a href="#l1331"></a>
<span id="l1332">            connectionState = cs_RENEGOTIATE;</span><a href="#l1332"></a>
<span id="l1333">        }</span><a href="#l1333"></a>
<span id="l1334">        if (roleIsServer) {</span><a href="#l1334"></a>
<span id="l1335">            handshaker = new ServerHandshaker(this, sslContext,</span><a href="#l1335"></a>
<span id="l1336">                    enabledProtocols, doClientAuth,</span><a href="#l1336"></a>
<span id="l1337">                    protocolVersion, connectionState == cs_HANDSHAKE,</span><a href="#l1337"></a>
<span id="l1338">                    secureRenegotiation, clientVerifyData, serverVerifyData);</span><a href="#l1338"></a>
<span id="l1339">            handshaker.setSNIMatchers(sniMatchers);</span><a href="#l1339"></a>
<span id="l1340">            handshaker.setUseCipherSuitesOrder(preferLocalCipherSuites);</span><a href="#l1340"></a>
<span id="l1341">        } else {</span><a href="#l1341"></a>
<span id="l1342">            handshaker = new ClientHandshaker(this, sslContext,</span><a href="#l1342"></a>
<span id="l1343">                    enabledProtocols,</span><a href="#l1343"></a>
<span id="l1344">                    protocolVersion, connectionState == cs_HANDSHAKE,</span><a href="#l1344"></a>
<span id="l1345">                    secureRenegotiation, clientVerifyData, serverVerifyData);</span><a href="#l1345"></a>
<span id="l1346">            handshaker.setSNIServerNames(serverNames);</span><a href="#l1346"></a>
<span id="l1347">        }</span><a href="#l1347"></a>
<span id="l1348">        handshaker.setEnabledCipherSuites(enabledCipherSuites);</span><a href="#l1348"></a>
<span id="l1349">        handshaker.setEnableSessionCreation(enableSessionCreation);</span><a href="#l1349"></a>
<span id="l1350">        handshaker.setApplicationProtocols(applicationProtocols);</span><a href="#l1350"></a>
<span id="l1351">        handshaker.setApplicationProtocolSelectorSSLSocket(</span><a href="#l1351"></a>
<span id="l1352">            applicationProtocolSelector);</span><a href="#l1352"></a>
<span id="l1353">    }</span><a href="#l1353"></a>
<span id="l1354"></span><a href="#l1354"></a>
<span id="l1355">    /**</span><a href="#l1355"></a>
<span id="l1356">     * Synchronously perform the initial handshake.</span><a href="#l1356"></a>
<span id="l1357">     *</span><a href="#l1357"></a>
<span id="l1358">     * If the handshake is already in progress, this method blocks until it</span><a href="#l1358"></a>
<span id="l1359">     * is completed. If the initial handshake has already been completed,</span><a href="#l1359"></a>
<span id="l1360">     * it returns immediately.</span><a href="#l1360"></a>
<span id="l1361">     */</span><a href="#l1361"></a>
<span id="l1362">    private void performInitialHandshake() throws IOException {</span><a href="#l1362"></a>
<span id="l1363">        // use handshakeLock and the state check to make sure only</span><a href="#l1363"></a>
<span id="l1364">        // one thread performs the handshake</span><a href="#l1364"></a>
<span id="l1365">        synchronized (handshakeLock) {</span><a href="#l1365"></a>
<span id="l1366">            if (getConnectionState() == cs_HANDSHAKE) {</span><a href="#l1366"></a>
<span id="l1367">                kickstartHandshake();</span><a href="#l1367"></a>
<span id="l1368"></span><a href="#l1368"></a>
<span id="l1369">                /*</span><a href="#l1369"></a>
<span id="l1370">                 * All initial handshaking goes through this operation</span><a href="#l1370"></a>
<span id="l1371">                 * until we have a valid SSL connection.</span><a href="#l1371"></a>
<span id="l1372">                 *</span><a href="#l1372"></a>
<span id="l1373">                 * Handle handshake messages only, need no application data.</span><a href="#l1373"></a>
<span id="l1374">                 */</span><a href="#l1374"></a>
<span id="l1375">                if (inrec == null) {</span><a href="#l1375"></a>
<span id="l1376">                    inrec = new InputRecord();</span><a href="#l1376"></a>
<span id="l1377"></span><a href="#l1377"></a>
<span id="l1378">                    /*</span><a href="#l1378"></a>
<span id="l1379">                     * Grab the characteristics already assigned to</span><a href="#l1379"></a>
<span id="l1380">                     * AppInputStream's InputRecord.  Enable checking for</span><a href="#l1380"></a>
<span id="l1381">                     * SSLv2 hellos on this first handshake.</span><a href="#l1381"></a>
<span id="l1382">                     */</span><a href="#l1382"></a>
<span id="l1383">                    inrec.setHandshakeHash(input.r.getHandshakeHash());</span><a href="#l1383"></a>
<span id="l1384">                    inrec.setHelloVersion(input.r.getHelloVersion());</span><a href="#l1384"></a>
<span id="l1385">                    inrec.enableFormatChecks();</span><a href="#l1385"></a>
<span id="l1386">                }</span><a href="#l1386"></a>
<span id="l1387"></span><a href="#l1387"></a>
<span id="l1388">                readRecord(inrec, false);</span><a href="#l1388"></a>
<span id="l1389">                inrec = null;</span><a href="#l1389"></a>
<span id="l1390">            }</span><a href="#l1390"></a>
<span id="l1391">        }</span><a href="#l1391"></a>
<span id="l1392">    }</span><a href="#l1392"></a>
<span id="l1393"></span><a href="#l1393"></a>
<span id="l1394">    /**</span><a href="#l1394"></a>
<span id="l1395">     * Starts an SSL handshake on this connection.</span><a href="#l1395"></a>
<span id="l1396">     */</span><a href="#l1396"></a>
<span id="l1397">    @Override</span><a href="#l1397"></a>
<span id="l1398">    public void startHandshake() throws IOException {</span><a href="#l1398"></a>
<span id="l1399">        // start an ssl handshake that could be resumed from timeout exception</span><a href="#l1399"></a>
<span id="l1400">        startHandshake(true);</span><a href="#l1400"></a>
<span id="l1401">    }</span><a href="#l1401"></a>
<span id="l1402"></span><a href="#l1402"></a>
<span id="l1403">    /**</span><a href="#l1403"></a>
<span id="l1404">     * Starts an ssl handshake on this connection.</span><a href="#l1404"></a>
<span id="l1405">     *</span><a href="#l1405"></a>
<span id="l1406">     * @param resumable indicates the handshake process is resumable from a</span><a href="#l1406"></a>
<span id="l1407">     *          certain exception. If &lt;code&gt;resumable&lt;/code&gt;, the socket will</span><a href="#l1407"></a>
<span id="l1408">     *          be reserved for exceptions like timeout; otherwise, the socket</span><a href="#l1408"></a>
<span id="l1409">     *          will be closed, no further communications could be done.</span><a href="#l1409"></a>
<span id="l1410">     */</span><a href="#l1410"></a>
<span id="l1411">    private void startHandshake(boolean resumable) throws IOException {</span><a href="#l1411"></a>
<span id="l1412">        checkWrite();</span><a href="#l1412"></a>
<span id="l1413">        try {</span><a href="#l1413"></a>
<span id="l1414">            if (getConnectionState() == cs_HANDSHAKE) {</span><a href="#l1414"></a>
<span id="l1415">                // do initial handshake</span><a href="#l1415"></a>
<span id="l1416">                performInitialHandshake();</span><a href="#l1416"></a>
<span id="l1417">            } else {</span><a href="#l1417"></a>
<span id="l1418">                // start renegotiation</span><a href="#l1418"></a>
<span id="l1419">                kickstartHandshake();</span><a href="#l1419"></a>
<span id="l1420">            }</span><a href="#l1420"></a>
<span id="l1421">        } catch (Exception e) {</span><a href="#l1421"></a>
<span id="l1422">            // shutdown and rethrow (wrapped) exception as appropriate</span><a href="#l1422"></a>
<span id="l1423">            handleException(e, resumable);</span><a href="#l1423"></a>
<span id="l1424">        }</span><a href="#l1424"></a>
<span id="l1425">    }</span><a href="#l1425"></a>
<span id="l1426"></span><a href="#l1426"></a>
<span id="l1427">    /**</span><a href="#l1427"></a>
<span id="l1428">     * Kickstart the handshake if it is not already in progress.</span><a href="#l1428"></a>
<span id="l1429">     * This means:</span><a href="#l1429"></a>
<span id="l1430">     *</span><a href="#l1430"></a>
<span id="l1431">     *  . if handshaking is already underway, do nothing and return</span><a href="#l1431"></a>
<span id="l1432">     *</span><a href="#l1432"></a>
<span id="l1433">     *  . if the socket is not connected or already closed, throw an</span><a href="#l1433"></a>
<span id="l1434">     *    Exception.</span><a href="#l1434"></a>
<span id="l1435">     *</span><a href="#l1435"></a>
<span id="l1436">     *  . otherwise, call initHandshake() to initialize the handshaker</span><a href="#l1436"></a>
<span id="l1437">     *    object and progress the state. Then, send the initial</span><a href="#l1437"></a>
<span id="l1438">     *    handshaking message if appropriate (always on clients and</span><a href="#l1438"></a>
<span id="l1439">     *    on servers when renegotiating).</span><a href="#l1439"></a>
<span id="l1440">     */</span><a href="#l1440"></a>
<span id="l1441">    private synchronized void kickstartHandshake() throws IOException {</span><a href="#l1441"></a>
<span id="l1442"></span><a href="#l1442"></a>
<span id="l1443">        switch (connectionState) {</span><a href="#l1443"></a>
<span id="l1444"></span><a href="#l1444"></a>
<span id="l1445">        case cs_HANDSHAKE:</span><a href="#l1445"></a>
<span id="l1446">            // handshaker already setup, proceed</span><a href="#l1446"></a>
<span id="l1447">            break;</span><a href="#l1447"></a>
<span id="l1448"></span><a href="#l1448"></a>
<span id="l1449">        case cs_DATA:</span><a href="#l1449"></a>
<span id="l1450">            if (!secureRenegotiation &amp;&amp; !Handshaker.allowUnsafeRenegotiation) {</span><a href="#l1450"></a>
<span id="l1451">                throw new SSLHandshakeException(</span><a href="#l1451"></a>
<span id="l1452">                        &quot;Insecure renegotiation is not allowed&quot;);</span><a href="#l1452"></a>
<span id="l1453">            }</span><a href="#l1453"></a>
<span id="l1454"></span><a href="#l1454"></a>
<span id="l1455">            if (!secureRenegotiation) {</span><a href="#l1455"></a>
<span id="l1456">                if (debug != null &amp;&amp; Debug.isOn(&quot;handshake&quot;)) {</span><a href="#l1456"></a>
<span id="l1457">                    System.out.println(</span><a href="#l1457"></a>
<span id="l1458">                        &quot;Warning: Using insecure renegotiation&quot;);</span><a href="#l1458"></a>
<span id="l1459">                }</span><a href="#l1459"></a>
<span id="l1460">            }</span><a href="#l1460"></a>
<span id="l1461"></span><a href="#l1461"></a>
<span id="l1462">            // initialize the handshaker, move to cs_RENEGOTIATE</span><a href="#l1462"></a>
<span id="l1463">            initHandshaker();</span><a href="#l1463"></a>
<span id="l1464">            break;</span><a href="#l1464"></a>
<span id="l1465"></span><a href="#l1465"></a>
<span id="l1466">        case cs_RENEGOTIATE:</span><a href="#l1466"></a>
<span id="l1467">            // handshaking already in progress, return</span><a href="#l1467"></a>
<span id="l1468">            return;</span><a href="#l1468"></a>
<span id="l1469"></span><a href="#l1469"></a>
<span id="l1470">        /*</span><a href="#l1470"></a>
<span id="l1471">         * The only way to get a socket in the state is when</span><a href="#l1471"></a>
<span id="l1472">         * you have an unconnected socket.</span><a href="#l1472"></a>
<span id="l1473">         */</span><a href="#l1473"></a>
<span id="l1474">        case cs_START:</span><a href="#l1474"></a>
<span id="l1475">            throw new SocketException(</span><a href="#l1475"></a>
<span id="l1476">                &quot;handshaking attempted on unconnected socket&quot;);</span><a href="#l1476"></a>
<span id="l1477"></span><a href="#l1477"></a>
<span id="l1478">        default:</span><a href="#l1478"></a>
<span id="l1479">            throw new SocketException(&quot;connection is closed&quot;);</span><a href="#l1479"></a>
<span id="l1480">        }</span><a href="#l1480"></a>
<span id="l1481"></span><a href="#l1481"></a>
<span id="l1482">        //</span><a href="#l1482"></a>
<span id="l1483">        // Kickstart handshake state machine if we need to ...</span><a href="#l1483"></a>
<span id="l1484">        //</span><a href="#l1484"></a>
<span id="l1485">        // Note that handshaker.kickstart() writes the message</span><a href="#l1485"></a>
<span id="l1486">        // to its HandshakeOutStream, which calls back into</span><a href="#l1486"></a>
<span id="l1487">        // SSLSocketImpl.writeRecord() to send it.</span><a href="#l1487"></a>
<span id="l1488">        //</span><a href="#l1488"></a>
<span id="l1489">        if (!handshaker.activated()) {</span><a href="#l1489"></a>
<span id="l1490">             // prior to handshaking, activate the handshake</span><a href="#l1490"></a>
<span id="l1491">            if (connectionState == cs_RENEGOTIATE) {</span><a href="#l1491"></a>
<span id="l1492">                // don't use SSLv2Hello when renegotiating</span><a href="#l1492"></a>
<span id="l1493">                handshaker.activate(protocolVersion);</span><a href="#l1493"></a>
<span id="l1494">            } else {</span><a href="#l1494"></a>
<span id="l1495">                handshaker.activate(null);</span><a href="#l1495"></a>
<span id="l1496">            }</span><a href="#l1496"></a>
<span id="l1497"></span><a href="#l1497"></a>
<span id="l1498">            if (handshaker instanceof ClientHandshaker) {</span><a href="#l1498"></a>
<span id="l1499">                // send client hello</span><a href="#l1499"></a>
<span id="l1500">                handshaker.kickstart();</span><a href="#l1500"></a>
<span id="l1501">            } else {</span><a href="#l1501"></a>
<span id="l1502">                if (connectionState == cs_HANDSHAKE) {</span><a href="#l1502"></a>
<span id="l1503">                    // initial handshake, no kickstart message to send</span><a href="#l1503"></a>
<span id="l1504">                } else {</span><a href="#l1504"></a>
<span id="l1505">                    // we want to renegotiate, send hello request</span><a href="#l1505"></a>
<span id="l1506">                    handshaker.kickstart();</span><a href="#l1506"></a>
<span id="l1507">                    // hello request is not included in the handshake</span><a href="#l1507"></a>
<span id="l1508">                    // hashes, reset them</span><a href="#l1508"></a>
<span id="l1509">                    handshaker.handshakeHash.reset();</span><a href="#l1509"></a>
<span id="l1510">                }</span><a href="#l1510"></a>
<span id="l1511">            }</span><a href="#l1511"></a>
<span id="l1512">        }</span><a href="#l1512"></a>
<span id="l1513">    }</span><a href="#l1513"></a>
<span id="l1514"></span><a href="#l1514"></a>
<span id="l1515">    //</span><a href="#l1515"></a>
<span id="l1516">    // CLOSURE RELATED CALLS</span><a href="#l1516"></a>
<span id="l1517">    //</span><a href="#l1517"></a>
<span id="l1518"></span><a href="#l1518"></a>
<span id="l1519">    /**</span><a href="#l1519"></a>
<span id="l1520">     * Return whether the socket has been explicitly closed by the application.</span><a href="#l1520"></a>
<span id="l1521">     */</span><a href="#l1521"></a>
<span id="l1522">    @Override</span><a href="#l1522"></a>
<span id="l1523">    public boolean isClosed() {</span><a href="#l1523"></a>
<span id="l1524">        return connectionState == cs_APP_CLOSED;</span><a href="#l1524"></a>
<span id="l1525">    }</span><a href="#l1525"></a>
<span id="l1526"></span><a href="#l1526"></a>
<span id="l1527">    /**</span><a href="#l1527"></a>
<span id="l1528">     * Return whether we have reached end-of-file.</span><a href="#l1528"></a>
<span id="l1529">     *</span><a href="#l1529"></a>
<span id="l1530">     * If the socket is not connected, has been shutdown because of an error</span><a href="#l1530"></a>
<span id="l1531">     * or has been closed, throw an Exception.</span><a href="#l1531"></a>
<span id="l1532">     */</span><a href="#l1532"></a>
<span id="l1533">    boolean checkEOF() throws IOException {</span><a href="#l1533"></a>
<span id="l1534">        switch (getConnectionState()) {</span><a href="#l1534"></a>
<span id="l1535">        case cs_START:</span><a href="#l1535"></a>
<span id="l1536">            throw new SocketException(&quot;Socket is not connected&quot;);</span><a href="#l1536"></a>
<span id="l1537"></span><a href="#l1537"></a>
<span id="l1538">        case cs_HANDSHAKE:</span><a href="#l1538"></a>
<span id="l1539">        case cs_DATA:</span><a href="#l1539"></a>
<span id="l1540">        case cs_RENEGOTIATE:</span><a href="#l1540"></a>
<span id="l1541">        case cs_SENT_CLOSE:</span><a href="#l1541"></a>
<span id="l1542">            return false;</span><a href="#l1542"></a>
<span id="l1543"></span><a href="#l1543"></a>
<span id="l1544">        case cs_APP_CLOSED:</span><a href="#l1544"></a>
<span id="l1545">            throw new SocketException(&quot;Socket is closed&quot;);</span><a href="#l1545"></a>
<span id="l1546"></span><a href="#l1546"></a>
<span id="l1547">        case cs_ERROR:</span><a href="#l1547"></a>
<span id="l1548">        case cs_CLOSED:</span><a href="#l1548"></a>
<span id="l1549">        default:</span><a href="#l1549"></a>
<span id="l1550">            // either closed because of error, or normal EOF</span><a href="#l1550"></a>
<span id="l1551">            if (closeReason == null) {</span><a href="#l1551"></a>
<span id="l1552">                return true;</span><a href="#l1552"></a>
<span id="l1553">            }</span><a href="#l1553"></a>
<span id="l1554">            IOException e = new SSLException</span><a href="#l1554"></a>
<span id="l1555">                        (&quot;Connection has been shutdown: &quot; + closeReason);</span><a href="#l1555"></a>
<span id="l1556">            e.initCause(closeReason);</span><a href="#l1556"></a>
<span id="l1557">            throw e;</span><a href="#l1557"></a>
<span id="l1558"></span><a href="#l1558"></a>
<span id="l1559">        }</span><a href="#l1559"></a>
<span id="l1560">    }</span><a href="#l1560"></a>
<span id="l1561"></span><a href="#l1561"></a>
<span id="l1562">    /**</span><a href="#l1562"></a>
<span id="l1563">     * Check if we can write data to this socket. If not, throw an IOException.</span><a href="#l1563"></a>
<span id="l1564">     */</span><a href="#l1564"></a>
<span id="l1565">    void checkWrite() throws IOException {</span><a href="#l1565"></a>
<span id="l1566">        if (checkEOF() || (getConnectionState() == cs_SENT_CLOSE)) {</span><a href="#l1566"></a>
<span id="l1567">            // we are at EOF, write must throw Exception</span><a href="#l1567"></a>
<span id="l1568">            throw new SocketException(&quot;Connection closed by remote host&quot;);</span><a href="#l1568"></a>
<span id="l1569">        }</span><a href="#l1569"></a>
<span id="l1570">    }</span><a href="#l1570"></a>
<span id="l1571"></span><a href="#l1571"></a>
<span id="l1572">    protected void closeSocket() throws IOException {</span><a href="#l1572"></a>
<span id="l1573"></span><a href="#l1573"></a>
<span id="l1574">        if ((debug != null) &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l1574"></a>
<span id="l1575">            System.out.println(Thread.currentThread().getName() +</span><a href="#l1575"></a>
<span id="l1576">                                                &quot;, called closeSocket()&quot;);</span><a href="#l1576"></a>
<span id="l1577">        }</span><a href="#l1577"></a>
<span id="l1578"></span><a href="#l1578"></a>
<span id="l1579">        super.close();</span><a href="#l1579"></a>
<span id="l1580">    }</span><a href="#l1580"></a>
<span id="l1581"></span><a href="#l1581"></a>
<span id="l1582">    private void closeSocket(boolean selfInitiated) throws IOException {</span><a href="#l1582"></a>
<span id="l1583">        if ((debug != null) &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l1583"></a>
<span id="l1584">            System.out.println(Thread.currentThread().getName() +</span><a href="#l1584"></a>
<span id="l1585">                &quot;, called closeSocket(&quot; + selfInitiated + &quot;)&quot;);</span><a href="#l1585"></a>
<span id="l1586">        }</span><a href="#l1586"></a>
<span id="l1587">        if (!isLayered() || autoClose) {</span><a href="#l1587"></a>
<span id="l1588">            super.close();</span><a href="#l1588"></a>
<span id="l1589">        } else if (selfInitiated) {</span><a href="#l1589"></a>
<span id="l1590">            // layered &amp;&amp; non-autoclose</span><a href="#l1590"></a>
<span id="l1591">            // read close_notify alert to clear input stream</span><a href="#l1591"></a>
<span id="l1592">            waitForClose(false);</span><a href="#l1592"></a>
<span id="l1593">        }</span><a href="#l1593"></a>
<span id="l1594">    }</span><a href="#l1594"></a>
<span id="l1595"></span><a href="#l1595"></a>
<span id="l1596">    /*</span><a href="#l1596"></a>
<span id="l1597">     * Closing the connection is tricky ... we can't officially close the</span><a href="#l1597"></a>
<span id="l1598">     * connection until we know the other end is ready to go away too,</span><a href="#l1598"></a>
<span id="l1599">     * and if ever the connection gets aborted we must forget session</span><a href="#l1599"></a>
<span id="l1600">     * state (it becomes invalid).</span><a href="#l1600"></a>
<span id="l1601">     */</span><a href="#l1601"></a>
<span id="l1602"></span><a href="#l1602"></a>
<span id="l1603">    /**</span><a href="#l1603"></a>
<span id="l1604">     * Closes the SSL connection.  SSL includes an application level</span><a href="#l1604"></a>
<span id="l1605">     * shutdown handshake; you should close SSL sockets explicitly</span><a href="#l1605"></a>
<span id="l1606">     * rather than leaving it for finalization, so that your remote</span><a href="#l1606"></a>
<span id="l1607">     * peer does not experience a protocol error.</span><a href="#l1607"></a>
<span id="l1608">     */</span><a href="#l1608"></a>
<span id="l1609">    @Override</span><a href="#l1609"></a>
<span id="l1610">    public void close() throws IOException {</span><a href="#l1610"></a>
<span id="l1611">        if ((debug != null) &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l1611"></a>
<span id="l1612">            System.out.println(Thread.currentThread().getName() +</span><a href="#l1612"></a>
<span id="l1613">                                                    &quot;, called close()&quot;);</span><a href="#l1613"></a>
<span id="l1614">        }</span><a href="#l1614"></a>
<span id="l1615">        closeInternal(true);  // caller is initiating close</span><a href="#l1615"></a>
<span id="l1616">        setConnectionState(cs_APP_CLOSED);</span><a href="#l1616"></a>
<span id="l1617">    }</span><a href="#l1617"></a>
<span id="l1618"></span><a href="#l1618"></a>
<span id="l1619">    /**</span><a href="#l1619"></a>
<span id="l1620">     * Don't synchronize the whole method because waitForClose()</span><a href="#l1620"></a>
<span id="l1621">     * (which calls readRecord()) might be called.</span><a href="#l1621"></a>
<span id="l1622">     *</span><a href="#l1622"></a>
<span id="l1623">     * @param selfInitiated Indicates which party initiated the close.</span><a href="#l1623"></a>
<span id="l1624">     * If selfInitiated, this side is initiating a close; for layered and</span><a href="#l1624"></a>
<span id="l1625">     * non-autoclose socket, wait for close_notify response.</span><a href="#l1625"></a>
<span id="l1626">     * If !selfInitiated, peer sent close_notify; we reciprocate but</span><a href="#l1626"></a>
<span id="l1627">     * no need to wait for response.</span><a href="#l1627"></a>
<span id="l1628">     */</span><a href="#l1628"></a>
<span id="l1629">    private void closeInternal(boolean selfInitiated) throws IOException {</span><a href="#l1629"></a>
<span id="l1630">        if ((debug != null) &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l1630"></a>
<span id="l1631">            System.out.println(Thread.currentThread().getName() +</span><a href="#l1631"></a>
<span id="l1632">                        &quot;, called closeInternal(&quot; + selfInitiated + &quot;)&quot;);</span><a href="#l1632"></a>
<span id="l1633">        }</span><a href="#l1633"></a>
<span id="l1634"></span><a href="#l1634"></a>
<span id="l1635">        int state = getConnectionState();</span><a href="#l1635"></a>
<span id="l1636">        boolean closeSocketCalled = false;</span><a href="#l1636"></a>
<span id="l1637">        Throwable cachedThrowable = null;</span><a href="#l1637"></a>
<span id="l1638">        try {</span><a href="#l1638"></a>
<span id="l1639">            switch (state) {</span><a href="#l1639"></a>
<span id="l1640">            case cs_START:</span><a href="#l1640"></a>
<span id="l1641">                // unconnected socket or handshaking has not been initialized</span><a href="#l1641"></a>
<span id="l1642">                closeSocket(selfInitiated);</span><a href="#l1642"></a>
<span id="l1643">                break;</span><a href="#l1643"></a>
<span id="l1644"></span><a href="#l1644"></a>
<span id="l1645">            /*</span><a href="#l1645"></a>
<span id="l1646">             * If we're closing down due to error, we already sent (or else</span><a href="#l1646"></a>
<span id="l1647">             * received) the fatal alert ... no niceties, blow the connection</span><a href="#l1647"></a>
<span id="l1648">             * away as quickly as possible (even if we didn't allocate the</span><a href="#l1648"></a>
<span id="l1649">             * socket ourselves; it's unusable, regardless).</span><a href="#l1649"></a>
<span id="l1650">             */</span><a href="#l1650"></a>
<span id="l1651">            case cs_ERROR:</span><a href="#l1651"></a>
<span id="l1652">                closeSocket();</span><a href="#l1652"></a>
<span id="l1653">                break;</span><a href="#l1653"></a>
<span id="l1654"></span><a href="#l1654"></a>
<span id="l1655">            /*</span><a href="#l1655"></a>
<span id="l1656">             * Sometimes close() gets called more than once.</span><a href="#l1656"></a>
<span id="l1657">             */</span><a href="#l1657"></a>
<span id="l1658">            case cs_CLOSED:</span><a href="#l1658"></a>
<span id="l1659">            case cs_APP_CLOSED:</span><a href="#l1659"></a>
<span id="l1660">                 break;</span><a href="#l1660"></a>
<span id="l1661"></span><a href="#l1661"></a>
<span id="l1662">            /*</span><a href="#l1662"></a>
<span id="l1663">             * Otherwise we indicate clean termination.</span><a href="#l1663"></a>
<span id="l1664">             */</span><a href="#l1664"></a>
<span id="l1665">            // case cs_HANDSHAKE:</span><a href="#l1665"></a>
<span id="l1666">            // case cs_DATA:</span><a href="#l1666"></a>
<span id="l1667">            // case cs_RENEGOTIATE:</span><a href="#l1667"></a>
<span id="l1668">            // case cs_SENT_CLOSE:</span><a href="#l1668"></a>
<span id="l1669">            default:</span><a href="#l1669"></a>
<span id="l1670">                synchronized (this) {</span><a href="#l1670"></a>
<span id="l1671">                    if (((state = getConnectionState()) == cs_CLOSED) ||</span><a href="#l1671"></a>
<span id="l1672">                       (state == cs_ERROR) || (state == cs_APP_CLOSED)) {</span><a href="#l1672"></a>
<span id="l1673">                        return;  // connection was closed while we waited</span><a href="#l1673"></a>
<span id="l1674">                    }</span><a href="#l1674"></a>
<span id="l1675">                    if (state != cs_SENT_CLOSE) {</span><a href="#l1675"></a>
<span id="l1676">                        try {</span><a href="#l1676"></a>
<span id="l1677">                            warning(Alerts.alert_close_notify);</span><a href="#l1677"></a>
<span id="l1678">                            connectionState = cs_SENT_CLOSE;</span><a href="#l1678"></a>
<span id="l1679">                        } catch (Throwable th) {</span><a href="#l1679"></a>
<span id="l1680">                            // we need to ensure socket is closed out</span><a href="#l1680"></a>
<span id="l1681">                            // if we encounter any errors.</span><a href="#l1681"></a>
<span id="l1682">                            connectionState = cs_ERROR;</span><a href="#l1682"></a>
<span id="l1683">                            // cache this for later use</span><a href="#l1683"></a>
<span id="l1684">                            cachedThrowable = th;</span><a href="#l1684"></a>
<span id="l1685">                            closeSocketCalled = true;</span><a href="#l1685"></a>
<span id="l1686">                            closeSocket(selfInitiated);</span><a href="#l1686"></a>
<span id="l1687">                        }</span><a href="#l1687"></a>
<span id="l1688">                    }</span><a href="#l1688"></a>
<span id="l1689">                }</span><a href="#l1689"></a>
<span id="l1690">                // If state was cs_SENT_CLOSE before, we don't do the actual</span><a href="#l1690"></a>
<span id="l1691">                // closing since it is already in progress.</span><a href="#l1691"></a>
<span id="l1692">                if (state == cs_SENT_CLOSE) {</span><a href="#l1692"></a>
<span id="l1693">                    if (debug != null &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l1693"></a>
<span id="l1694">                        System.out.println(Thread.currentThread().getName() +</span><a href="#l1694"></a>
<span id="l1695">                            &quot;, close invoked again; state = &quot; +</span><a href="#l1695"></a>
<span id="l1696">                            getConnectionState());</span><a href="#l1696"></a>
<span id="l1697">                    }</span><a href="#l1697"></a>
<span id="l1698">                    if (selfInitiated == false) {</span><a href="#l1698"></a>
<span id="l1699">                        // We were called because a close_notify message was</span><a href="#l1699"></a>
<span id="l1700">                        // received. This may be due to another thread calling</span><a href="#l1700"></a>
<span id="l1701">                        // read() or due to our call to waitForClose() below.</span><a href="#l1701"></a>
<span id="l1702">                        // In either case, just return.</span><a href="#l1702"></a>
<span id="l1703">                        return;</span><a href="#l1703"></a>
<span id="l1704">                    }</span><a href="#l1704"></a>
<span id="l1705">                    // Another thread explicitly called close(). We need to</span><a href="#l1705"></a>
<span id="l1706">                    // wait for the closing to complete before returning.</span><a href="#l1706"></a>
<span id="l1707">                    synchronized (this) {</span><a href="#l1707"></a>
<span id="l1708">                        while (connectionState &lt; cs_CLOSED) {</span><a href="#l1708"></a>
<span id="l1709">                            try {</span><a href="#l1709"></a>
<span id="l1710">                                this.wait();</span><a href="#l1710"></a>
<span id="l1711">                            } catch (InterruptedException e) {</span><a href="#l1711"></a>
<span id="l1712">                                // ignore</span><a href="#l1712"></a>
<span id="l1713">                            }</span><a href="#l1713"></a>
<span id="l1714">                        }</span><a href="#l1714"></a>
<span id="l1715">                    }</span><a href="#l1715"></a>
<span id="l1716">                    if ((debug != null) &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l1716"></a>
<span id="l1717">                        System.out.println(Thread.currentThread().getName() +</span><a href="#l1717"></a>
<span id="l1718">                            &quot;, after primary close; state = &quot; +</span><a href="#l1718"></a>
<span id="l1719">                            getConnectionState());</span><a href="#l1719"></a>
<span id="l1720">                    }</span><a href="#l1720"></a>
<span id="l1721">                    return;</span><a href="#l1721"></a>
<span id="l1722">                }</span><a href="#l1722"></a>
<span id="l1723"></span><a href="#l1723"></a>
<span id="l1724">                if (!closeSocketCalled)  {</span><a href="#l1724"></a>
<span id="l1725">                    closeSocketCalled = true;</span><a href="#l1725"></a>
<span id="l1726">                    closeSocket(selfInitiated);</span><a href="#l1726"></a>
<span id="l1727">                }</span><a href="#l1727"></a>
<span id="l1728"></span><a href="#l1728"></a>
<span id="l1729">                break;</span><a href="#l1729"></a>
<span id="l1730">            }</span><a href="#l1730"></a>
<span id="l1731">        } finally {</span><a href="#l1731"></a>
<span id="l1732">            synchronized (this) {</span><a href="#l1732"></a>
<span id="l1733">                // Upon exit from this method, the state is always &gt;= cs_CLOSED</span><a href="#l1733"></a>
<span id="l1734">                connectionState = (connectionState == cs_APP_CLOSED)</span><a href="#l1734"></a>
<span id="l1735">                                ? cs_APP_CLOSED : cs_CLOSED;</span><a href="#l1735"></a>
<span id="l1736">                // notify any threads waiting for the closing to finish</span><a href="#l1736"></a>
<span id="l1737">                this.notifyAll();</span><a href="#l1737"></a>
<span id="l1738">            }</span><a href="#l1738"></a>
<span id="l1739">            if (closeSocketCalled) {</span><a href="#l1739"></a>
<span id="l1740">                // Dispose of ciphers since we've closed socket</span><a href="#l1740"></a>
<span id="l1741">                disposeCiphers();</span><a href="#l1741"></a>
<span id="l1742">            }</span><a href="#l1742"></a>
<span id="l1743">            if (cachedThrowable != null) {</span><a href="#l1743"></a>
<span id="l1744">               /*</span><a href="#l1744"></a>
<span id="l1745">                * Rethrow the error to the calling method</span><a href="#l1745"></a>
<span id="l1746">                * The Throwable caught can only be an Error or RuntimeException</span><a href="#l1746"></a>
<span id="l1747">                */</span><a href="#l1747"></a>
<span id="l1748">                if (cachedThrowable instanceof Error)</span><a href="#l1748"></a>
<span id="l1749">                    throw (Error) cachedThrowable;</span><a href="#l1749"></a>
<span id="l1750">                if (cachedThrowable instanceof RuntimeException)</span><a href="#l1750"></a>
<span id="l1751">                    throw (RuntimeException) cachedThrowable;</span><a href="#l1751"></a>
<span id="l1752">            }</span><a href="#l1752"></a>
<span id="l1753">        }</span><a href="#l1753"></a>
<span id="l1754">    }</span><a href="#l1754"></a>
<span id="l1755"></span><a href="#l1755"></a>
<span id="l1756">    /**</span><a href="#l1756"></a>
<span id="l1757">     * Reads a close_notify or a fatal alert from the input stream.</span><a href="#l1757"></a>
<span id="l1758">     * Keep reading records until we get a close_notify or until</span><a href="#l1758"></a>
<span id="l1759">     * the connection is otherwise closed.  The close_notify or alert</span><a href="#l1759"></a>
<span id="l1760">     * might be read by another reader,</span><a href="#l1760"></a>
<span id="l1761">     * which will then process the close and set the connection state.</span><a href="#l1761"></a>
<span id="l1762">     */</span><a href="#l1762"></a>
<span id="l1763">    void waitForClose(boolean rethrow) throws IOException {</span><a href="#l1763"></a>
<span id="l1764">        if (debug != null &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l1764"></a>
<span id="l1765">            System.out.println(Thread.currentThread().getName() +</span><a href="#l1765"></a>
<span id="l1766">                &quot;, waiting for close_notify or alert: state &quot;</span><a href="#l1766"></a>
<span id="l1767">                + getConnectionState());</span><a href="#l1767"></a>
<span id="l1768">        }</span><a href="#l1768"></a>
<span id="l1769"></span><a href="#l1769"></a>
<span id="l1770">        try {</span><a href="#l1770"></a>
<span id="l1771">            int state;</span><a href="#l1771"></a>
<span id="l1772"></span><a href="#l1772"></a>
<span id="l1773">            while (((state = getConnectionState()) != cs_CLOSED) &amp;&amp;</span><a href="#l1773"></a>
<span id="l1774">                   (state != cs_ERROR) &amp;&amp; (state != cs_APP_CLOSED)) {</span><a href="#l1774"></a>
<span id="l1775">                // create the InputRecord if it isn't initialized.</span><a href="#l1775"></a>
<span id="l1776">                if (inrec == null) {</span><a href="#l1776"></a>
<span id="l1777">                    inrec = new InputRecord();</span><a href="#l1777"></a>
<span id="l1778">                }</span><a href="#l1778"></a>
<span id="l1779"></span><a href="#l1779"></a>
<span id="l1780">                // Ask for app data and then throw it away</span><a href="#l1780"></a>
<span id="l1781">                try {</span><a href="#l1781"></a>
<span id="l1782">                    readRecord(inrec, true);</span><a href="#l1782"></a>
<span id="l1783">                } catch (SocketTimeoutException e) {</span><a href="#l1783"></a>
<span id="l1784">                    if ((debug != null) &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l1784"></a>
<span id="l1785">                        System.out.println(</span><a href="#l1785"></a>
<span id="l1786">                            Thread.currentThread().getName() +</span><a href="#l1786"></a>
<span id="l1787">                            &quot;, received Exception: &quot; + e);</span><a href="#l1787"></a>
<span id="l1788">                    }</span><a href="#l1788"></a>
<span id="l1789">                    fatal((byte)(-1), &quot;Did not receive close_notify from peer&quot;, e);</span><a href="#l1789"></a>
<span id="l1790">                }</span><a href="#l1790"></a>
<span id="l1791">            }</span><a href="#l1791"></a>
<span id="l1792">            inrec = null;</span><a href="#l1792"></a>
<span id="l1793">        } catch (IOException e) {</span><a href="#l1793"></a>
<span id="l1794">            if (debug != null &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l1794"></a>
<span id="l1795">                System.out.println(Thread.currentThread().getName() +</span><a href="#l1795"></a>
<span id="l1796">                    &quot;, Exception while waiting for close &quot; +e);</span><a href="#l1796"></a>
<span id="l1797">            }</span><a href="#l1797"></a>
<span id="l1798">            if (rethrow) {</span><a href="#l1798"></a>
<span id="l1799">                throw e; // pass exception up</span><a href="#l1799"></a>
<span id="l1800">            }</span><a href="#l1800"></a>
<span id="l1801">        }</span><a href="#l1801"></a>
<span id="l1802">    }</span><a href="#l1802"></a>
<span id="l1803"></span><a href="#l1803"></a>
<span id="l1804">    /**</span><a href="#l1804"></a>
<span id="l1805">     * Called by closeInternal() only. Be sure to consider the</span><a href="#l1805"></a>
<span id="l1806">     * synchronization locks carefully before calling it elsewhere.</span><a href="#l1806"></a>
<span id="l1807">     */</span><a href="#l1807"></a>
<span id="l1808">    private void disposeCiphers() {</span><a href="#l1808"></a>
<span id="l1809">        // See comment in changeReadCiphers()</span><a href="#l1809"></a>
<span id="l1810">        synchronized (readLock) {</span><a href="#l1810"></a>
<span id="l1811">            readCipher.dispose();</span><a href="#l1811"></a>
<span id="l1812">        }</span><a href="#l1812"></a>
<span id="l1813">        // See comment in changeReadCiphers()</span><a href="#l1813"></a>
<span id="l1814">        writeLock.lock();</span><a href="#l1814"></a>
<span id="l1815">        try {</span><a href="#l1815"></a>
<span id="l1816">            writeCipher.dispose();</span><a href="#l1816"></a>
<span id="l1817">        } finally {</span><a href="#l1817"></a>
<span id="l1818">            writeLock.unlock();</span><a href="#l1818"></a>
<span id="l1819">        }</span><a href="#l1819"></a>
<span id="l1820">    }</span><a href="#l1820"></a>
<span id="l1821"></span><a href="#l1821"></a>
<span id="l1822">    //</span><a href="#l1822"></a>
<span id="l1823">    // EXCEPTION AND ALERT HANDLING</span><a href="#l1823"></a>
<span id="l1824">    //</span><a href="#l1824"></a>
<span id="l1825"></span><a href="#l1825"></a>
<span id="l1826">    /**</span><a href="#l1826"></a>
<span id="l1827">     * Handle an exception. This method is called by top level exception</span><a href="#l1827"></a>
<span id="l1828">     * handlers (in read(), write()) to make sure we always shutdown the</span><a href="#l1828"></a>
<span id="l1829">     * connection correctly and do not pass runtime exception to the</span><a href="#l1829"></a>
<span id="l1830">     * application.</span><a href="#l1830"></a>
<span id="l1831">     */</span><a href="#l1831"></a>
<span id="l1832">    void handleException(Exception e) throws IOException {</span><a href="#l1832"></a>
<span id="l1833">        handleException(e, true);</span><a href="#l1833"></a>
<span id="l1834">    }</span><a href="#l1834"></a>
<span id="l1835"></span><a href="#l1835"></a>
<span id="l1836">    /**</span><a href="#l1836"></a>
<span id="l1837">     * Handle an exception. This method is called by top level exception</span><a href="#l1837"></a>
<span id="l1838">     * handlers (in read(), write(), startHandshake()) to make sure we</span><a href="#l1838"></a>
<span id="l1839">     * always shutdown the connection correctly and do not pass runtime</span><a href="#l1839"></a>
<span id="l1840">     * exception to the application.</span><a href="#l1840"></a>
<span id="l1841">     *</span><a href="#l1841"></a>
<span id="l1842">     * This method never returns normally, it always throws an IOException.</span><a href="#l1842"></a>
<span id="l1843">     *</span><a href="#l1843"></a>
<span id="l1844">     * We first check if the socket has already been shutdown because of an</span><a href="#l1844"></a>
<span id="l1845">     * error. If so, we just rethrow the exception. If the socket has not</span><a href="#l1845"></a>
<span id="l1846">     * been shutdown, we sent a fatal alert and remember the exception.</span><a href="#l1846"></a>
<span id="l1847">     *</span><a href="#l1847"></a>
<span id="l1848">     * @param e the Exception</span><a href="#l1848"></a>
<span id="l1849">     * @param resumable indicates the caller process is resumable from the</span><a href="#l1849"></a>
<span id="l1850">     *          exception. If &lt;code&gt;resumable&lt;/code&gt;, the socket will be</span><a href="#l1850"></a>
<span id="l1851">     *          reserved for exceptions like timeout; otherwise, the socket</span><a href="#l1851"></a>
<span id="l1852">     *          will be closed, no further communications could be done.</span><a href="#l1852"></a>
<span id="l1853">     */</span><a href="#l1853"></a>
<span id="l1854">    synchronized private void handleException(Exception e, boolean resumable)</span><a href="#l1854"></a>
<span id="l1855">        throws IOException {</span><a href="#l1855"></a>
<span id="l1856">        if ((debug != null) &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l1856"></a>
<span id="l1857">            System.out.println(Thread.currentThread().getName() +</span><a href="#l1857"></a>
<span id="l1858">                        &quot;, handling exception: &quot; + e.toString());</span><a href="#l1858"></a>
<span id="l1859">        }</span><a href="#l1859"></a>
<span id="l1860"></span><a href="#l1860"></a>
<span id="l1861">        // don't close the Socket in case of timeouts or interrupts if</span><a href="#l1861"></a>
<span id="l1862">        // the process is resumable.</span><a href="#l1862"></a>
<span id="l1863">        if (e instanceof InterruptedIOException &amp;&amp; resumable) {</span><a href="#l1863"></a>
<span id="l1864">            throw (IOException)e;</span><a href="#l1864"></a>
<span id="l1865">        }</span><a href="#l1865"></a>
<span id="l1866"></span><a href="#l1866"></a>
<span id="l1867">        // if we've already shutdown because of an error,</span><a href="#l1867"></a>
<span id="l1868">        // there is nothing to do except rethrow the exception</span><a href="#l1868"></a>
<span id="l1869">        if (closeReason != null) {</span><a href="#l1869"></a>
<span id="l1870">            if (e instanceof IOException) { // includes SSLException</span><a href="#l1870"></a>
<span id="l1871">                throw (IOException)e;</span><a href="#l1871"></a>
<span id="l1872">            } else {</span><a href="#l1872"></a>
<span id="l1873">                // this is odd, not an IOException.</span><a href="#l1873"></a>
<span id="l1874">                // normally, this should not happen</span><a href="#l1874"></a>
<span id="l1875">                // if closeReason has been already been set</span><a href="#l1875"></a>
<span id="l1876">                throw Alerts.getSSLException(Alerts.alert_internal_error, e,</span><a href="#l1876"></a>
<span id="l1877">                                      &quot;Unexpected exception&quot;);</span><a href="#l1877"></a>
<span id="l1878">            }</span><a href="#l1878"></a>
<span id="l1879">        }</span><a href="#l1879"></a>
<span id="l1880"></span><a href="#l1880"></a>
<span id="l1881">        // need to perform error shutdown</span><a href="#l1881"></a>
<span id="l1882">        boolean isSSLException = (e instanceof SSLException);</span><a href="#l1882"></a>
<span id="l1883">        if ((isSSLException == false) &amp;&amp; (e instanceof IOException)) {</span><a href="#l1883"></a>
<span id="l1884">            // IOException from the socket</span><a href="#l1884"></a>
<span id="l1885">            // this means the TCP connection is already dead</span><a href="#l1885"></a>
<span id="l1886">            // we call fatal just to set the error status</span><a href="#l1886"></a>
<span id="l1887">            try {</span><a href="#l1887"></a>
<span id="l1888">                fatal(Alerts.alert_unexpected_message, e);</span><a href="#l1888"></a>
<span id="l1889">            } catch (IOException ee) {</span><a href="#l1889"></a>
<span id="l1890">                // ignore (IOException wrapped in SSLException)</span><a href="#l1890"></a>
<span id="l1891">            }</span><a href="#l1891"></a>
<span id="l1892">            // rethrow original IOException</span><a href="#l1892"></a>
<span id="l1893">            throw (IOException)e;</span><a href="#l1893"></a>
<span id="l1894">        }</span><a href="#l1894"></a>
<span id="l1895"></span><a href="#l1895"></a>
<span id="l1896">        // must be SSLException or RuntimeException</span><a href="#l1896"></a>
<span id="l1897">        byte alertType;</span><a href="#l1897"></a>
<span id="l1898">        if (isSSLException) {</span><a href="#l1898"></a>
<span id="l1899">            if (e instanceof SSLHandshakeException) {</span><a href="#l1899"></a>
<span id="l1900">                alertType = Alerts.alert_handshake_failure;</span><a href="#l1900"></a>
<span id="l1901">            } else {</span><a href="#l1901"></a>
<span id="l1902">                alertType = Alerts.alert_unexpected_message;</span><a href="#l1902"></a>
<span id="l1903">            }</span><a href="#l1903"></a>
<span id="l1904">        } else {</span><a href="#l1904"></a>
<span id="l1905">            alertType = Alerts.alert_internal_error;</span><a href="#l1905"></a>
<span id="l1906">        }</span><a href="#l1906"></a>
<span id="l1907">        fatal(alertType, e);</span><a href="#l1907"></a>
<span id="l1908">    }</span><a href="#l1908"></a>
<span id="l1909"></span><a href="#l1909"></a>
<span id="l1910">    /*</span><a href="#l1910"></a>
<span id="l1911">     * Send a warning alert.</span><a href="#l1911"></a>
<span id="l1912">     */</span><a href="#l1912"></a>
<span id="l1913">    void warning(byte description) {</span><a href="#l1913"></a>
<span id="l1914">        sendAlert(Alerts.alert_warning, description);</span><a href="#l1914"></a>
<span id="l1915">    }</span><a href="#l1915"></a>
<span id="l1916"></span><a href="#l1916"></a>
<span id="l1917">    synchronized void fatal(byte description, String diagnostic)</span><a href="#l1917"></a>
<span id="l1918">            throws IOException {</span><a href="#l1918"></a>
<span id="l1919">        fatal(description, diagnostic, null);</span><a href="#l1919"></a>
<span id="l1920">    }</span><a href="#l1920"></a>
<span id="l1921"></span><a href="#l1921"></a>
<span id="l1922">    synchronized void fatal(byte description, Throwable cause)</span><a href="#l1922"></a>
<span id="l1923">            throws IOException {</span><a href="#l1923"></a>
<span id="l1924">        fatal(description, null, cause);</span><a href="#l1924"></a>
<span id="l1925">    }</span><a href="#l1925"></a>
<span id="l1926"></span><a href="#l1926"></a>
<span id="l1927">    /*</span><a href="#l1927"></a>
<span id="l1928">     * Send a fatal alert, and throw an exception so that callers will</span><a href="#l1928"></a>
<span id="l1929">     * need to stand on their heads to accidentally continue processing.</span><a href="#l1929"></a>
<span id="l1930">     */</span><a href="#l1930"></a>
<span id="l1931">    synchronized void fatal(byte description, String diagnostic,</span><a href="#l1931"></a>
<span id="l1932">            Throwable cause) throws IOException {</span><a href="#l1932"></a>
<span id="l1933">        if ((input != null) &amp;&amp; (input.r != null)) {</span><a href="#l1933"></a>
<span id="l1934">            input.r.close();</span><a href="#l1934"></a>
<span id="l1935">        }</span><a href="#l1935"></a>
<span id="l1936">        sess.invalidate();</span><a href="#l1936"></a>
<span id="l1937">        if (handshakeSession != null) {</span><a href="#l1937"></a>
<span id="l1938">            handshakeSession.invalidate();</span><a href="#l1938"></a>
<span id="l1939">        }</span><a href="#l1939"></a>
<span id="l1940"></span><a href="#l1940"></a>
<span id="l1941">        int oldState = connectionState;</span><a href="#l1941"></a>
<span id="l1942">        if (connectionState &lt; cs_ERROR) {</span><a href="#l1942"></a>
<span id="l1943">            connectionState = cs_ERROR;</span><a href="#l1943"></a>
<span id="l1944">        }</span><a href="#l1944"></a>
<span id="l1945"></span><a href="#l1945"></a>
<span id="l1946">        /*</span><a href="#l1946"></a>
<span id="l1947">         * Has there been an error received yet?  If not, remember it.</span><a href="#l1947"></a>
<span id="l1948">         * By RFC 2246, we don't bother waiting for a response.</span><a href="#l1948"></a>
<span id="l1949">         * Fatal errors require immediate shutdown.</span><a href="#l1949"></a>
<span id="l1950">         */</span><a href="#l1950"></a>
<span id="l1951">        if (closeReason == null) {</span><a href="#l1951"></a>
<span id="l1952">            /*</span><a href="#l1952"></a>
<span id="l1953">             * Try to clear the kernel buffer to avoid TCP connection resets.</span><a href="#l1953"></a>
<span id="l1954">             */</span><a href="#l1954"></a>
<span id="l1955">            if (oldState == cs_HANDSHAKE) {</span><a href="#l1955"></a>
<span id="l1956">                sockInput.skip(sockInput.available());</span><a href="#l1956"></a>
<span id="l1957">            }</span><a href="#l1957"></a>
<span id="l1958"></span><a href="#l1958"></a>
<span id="l1959">            // If the description equals -1, the alert won't be sent to peer.</span><a href="#l1959"></a>
<span id="l1960">            if (description != -1) {</span><a href="#l1960"></a>
<span id="l1961">                sendAlert(Alerts.alert_fatal, description);</span><a href="#l1961"></a>
<span id="l1962">            }</span><a href="#l1962"></a>
<span id="l1963">            if (cause instanceof SSLException) { // only true if != null</span><a href="#l1963"></a>
<span id="l1964">                closeReason = (SSLException)cause;</span><a href="#l1964"></a>
<span id="l1965">            } else {</span><a href="#l1965"></a>
<span id="l1966">                closeReason =</span><a href="#l1966"></a>
<span id="l1967">                    Alerts.getSSLException(description, cause, diagnostic);</span><a href="#l1967"></a>
<span id="l1968">            }</span><a href="#l1968"></a>
<span id="l1969">        }</span><a href="#l1969"></a>
<span id="l1970"></span><a href="#l1970"></a>
<span id="l1971">        /*</span><a href="#l1971"></a>
<span id="l1972">         * Clean up our side.</span><a href="#l1972"></a>
<span id="l1973">         */</span><a href="#l1973"></a>
<span id="l1974">        closeSocket();</span><a href="#l1974"></a>
<span id="l1975">        // Another thread may have disposed the ciphers during closing</span><a href="#l1975"></a>
<span id="l1976">        if (connectionState &lt; cs_CLOSED) {</span><a href="#l1976"></a>
<span id="l1977">            connectionState = (oldState == cs_APP_CLOSED) ? cs_APP_CLOSED</span><a href="#l1977"></a>
<span id="l1978">                                                              : cs_CLOSED;</span><a href="#l1978"></a>
<span id="l1979"></span><a href="#l1979"></a>
<span id="l1980">            // We should lock readLock and writeLock if no deadlock risks.</span><a href="#l1980"></a>
<span id="l1981">            // See comment in changeReadCiphers()</span><a href="#l1981"></a>
<span id="l1982">            readCipher.dispose();</span><a href="#l1982"></a>
<span id="l1983">            writeCipher.dispose();</span><a href="#l1983"></a>
<span id="l1984">        }</span><a href="#l1984"></a>
<span id="l1985"></span><a href="#l1985"></a>
<span id="l1986">        throw closeReason;</span><a href="#l1986"></a>
<span id="l1987">    }</span><a href="#l1987"></a>
<span id="l1988"></span><a href="#l1988"></a>
<span id="l1989"></span><a href="#l1989"></a>
<span id="l1990">    /*</span><a href="#l1990"></a>
<span id="l1991">     * Process an incoming alert ... caller must already have synchronized</span><a href="#l1991"></a>
<span id="l1992">     * access to &quot;this&quot;.</span><a href="#l1992"></a>
<span id="l1993">     */</span><a href="#l1993"></a>
<span id="l1994">    private void recvAlert(InputRecord r) throws IOException {</span><a href="#l1994"></a>
<span id="l1995">        byte level = (byte)r.read();</span><a href="#l1995"></a>
<span id="l1996">        byte description = (byte)r.read();</span><a href="#l1996"></a>
<span id="l1997">        if (description == -1) { // check for short message</span><a href="#l1997"></a>
<span id="l1998">            fatal(Alerts.alert_illegal_parameter, &quot;Short alert message&quot;);</span><a href="#l1998"></a>
<span id="l1999">        }</span><a href="#l1999"></a>
<span id="l2000"></span><a href="#l2000"></a>
<span id="l2001">        if (debug != null &amp;&amp; (Debug.isOn(&quot;record&quot;) ||</span><a href="#l2001"></a>
<span id="l2002">                Debug.isOn(&quot;handshake&quot;))) {</span><a href="#l2002"></a>
<span id="l2003">            synchronized (System.out) {</span><a href="#l2003"></a>
<span id="l2004">                System.out.print(Thread.currentThread().getName());</span><a href="#l2004"></a>
<span id="l2005">                System.out.print(&quot;, RECV &quot; + protocolVersion + &quot; ALERT:  &quot;);</span><a href="#l2005"></a>
<span id="l2006">                if (level == Alerts.alert_fatal) {</span><a href="#l2006"></a>
<span id="l2007">                    System.out.print(&quot;fatal, &quot;);</span><a href="#l2007"></a>
<span id="l2008">                } else if (level == Alerts.alert_warning) {</span><a href="#l2008"></a>
<span id="l2009">                    System.out.print(&quot;warning, &quot;);</span><a href="#l2009"></a>
<span id="l2010">                } else {</span><a href="#l2010"></a>
<span id="l2011">                    System.out.print(&quot;&lt;level &quot; + (0x0ff &amp; level) + &quot;&gt;, &quot;);</span><a href="#l2011"></a>
<span id="l2012">                }</span><a href="#l2012"></a>
<span id="l2013">                System.out.println(Alerts.alertDescription(description));</span><a href="#l2013"></a>
<span id="l2014">            }</span><a href="#l2014"></a>
<span id="l2015">        }</span><a href="#l2015"></a>
<span id="l2016"></span><a href="#l2016"></a>
<span id="l2017">        if (level == Alerts.alert_warning) {</span><a href="#l2017"></a>
<span id="l2018">            if (description == Alerts.alert_close_notify) {</span><a href="#l2018"></a>
<span id="l2019">                if (connectionState == cs_HANDSHAKE) {</span><a href="#l2019"></a>
<span id="l2020">                    fatal(Alerts.alert_unexpected_message,</span><a href="#l2020"></a>
<span id="l2021">                                &quot;Received close_notify during handshake&quot;);</span><a href="#l2021"></a>
<span id="l2022">                } else {</span><a href="#l2022"></a>
<span id="l2023">                    closeInternal(false);  // reply to close</span><a href="#l2023"></a>
<span id="l2024">                }</span><a href="#l2024"></a>
<span id="l2025">            } else {</span><a href="#l2025"></a>
<span id="l2026"></span><a href="#l2026"></a>
<span id="l2027">                //</span><a href="#l2027"></a>
<span id="l2028">                // The other legal warnings relate to certificates,</span><a href="#l2028"></a>
<span id="l2029">                // e.g. no_certificate, bad_certificate, etc; these</span><a href="#l2029"></a>
<span id="l2030">                // are important to the handshaking code, which can</span><a href="#l2030"></a>
<span id="l2031">                // also handle illegal protocol alerts if needed.</span><a href="#l2031"></a>
<span id="l2032">                //</span><a href="#l2032"></a>
<span id="l2033">                if (handshaker != null) {</span><a href="#l2033"></a>
<span id="l2034">                    handshaker.handshakeAlert(description);</span><a href="#l2034"></a>
<span id="l2035">                }</span><a href="#l2035"></a>
<span id="l2036">            }</span><a href="#l2036"></a>
<span id="l2037">        } else { // fatal or unknown level</span><a href="#l2037"></a>
<span id="l2038">            String reason = &quot;Received fatal alert: &quot;</span><a href="#l2038"></a>
<span id="l2039">                + Alerts.alertDescription(description);</span><a href="#l2039"></a>
<span id="l2040">            if (closeReason == null) {</span><a href="#l2040"></a>
<span id="l2041">                closeReason = Alerts.getSSLException(description, reason);</span><a href="#l2041"></a>
<span id="l2042">            }</span><a href="#l2042"></a>
<span id="l2043">            fatal(Alerts.alert_unexpected_message, reason);</span><a href="#l2043"></a>
<span id="l2044">        }</span><a href="#l2044"></a>
<span id="l2045">    }</span><a href="#l2045"></a>
<span id="l2046"></span><a href="#l2046"></a>
<span id="l2047"></span><a href="#l2047"></a>
<span id="l2048">    /*</span><a href="#l2048"></a>
<span id="l2049">     * Emit alerts.  Caller must have synchronized with &quot;this&quot;.</span><a href="#l2049"></a>
<span id="l2050">     */</span><a href="#l2050"></a>
<span id="l2051">    private void sendAlert(byte level, byte description) {</span><a href="#l2051"></a>
<span id="l2052">        // the connectionState cannot be cs_START</span><a href="#l2052"></a>
<span id="l2053">        if (connectionState &gt;= cs_SENT_CLOSE) {</span><a href="#l2053"></a>
<span id="l2054">            return;</span><a href="#l2054"></a>
<span id="l2055">        }</span><a href="#l2055"></a>
<span id="l2056"></span><a href="#l2056"></a>
<span id="l2057">        // For initial handshaking, don't send alert message to peer if</span><a href="#l2057"></a>
<span id="l2058">        // handshaker has not started.</span><a href="#l2058"></a>
<span id="l2059">        if (connectionState == cs_HANDSHAKE &amp;&amp;</span><a href="#l2059"></a>
<span id="l2060">            (handshaker == null || !handshaker.started())) {</span><a href="#l2060"></a>
<span id="l2061">            return;</span><a href="#l2061"></a>
<span id="l2062">        }</span><a href="#l2062"></a>
<span id="l2063"></span><a href="#l2063"></a>
<span id="l2064">        OutputRecord r = new OutputRecord(Record.ct_alert);</span><a href="#l2064"></a>
<span id="l2065">        r.setVersion(protocolVersion);</span><a href="#l2065"></a>
<span id="l2066"></span><a href="#l2066"></a>
<span id="l2067">        boolean useDebug = debug != null &amp;&amp; Debug.isOn(&quot;ssl&quot;);</span><a href="#l2067"></a>
<span id="l2068">        if (useDebug) {</span><a href="#l2068"></a>
<span id="l2069">            synchronized (System.out) {</span><a href="#l2069"></a>
<span id="l2070">                System.out.print(Thread.currentThread().getName());</span><a href="#l2070"></a>
<span id="l2071">                System.out.print(&quot;, SEND &quot; + protocolVersion + &quot; ALERT:  &quot;);</span><a href="#l2071"></a>
<span id="l2072">                if (level == Alerts.alert_fatal) {</span><a href="#l2072"></a>
<span id="l2073">                    System.out.print(&quot;fatal, &quot;);</span><a href="#l2073"></a>
<span id="l2074">                } else if (level == Alerts.alert_warning) {</span><a href="#l2074"></a>
<span id="l2075">                    System.out.print(&quot;warning, &quot;);</span><a href="#l2075"></a>
<span id="l2076">                } else {</span><a href="#l2076"></a>
<span id="l2077">                    System.out.print(&quot;&lt;level = &quot; + (0x0ff &amp; level) + &quot;&gt;, &quot;);</span><a href="#l2077"></a>
<span id="l2078">                }</span><a href="#l2078"></a>
<span id="l2079">                System.out.println(&quot;description = &quot;</span><a href="#l2079"></a>
<span id="l2080">                        + Alerts.alertDescription(description));</span><a href="#l2080"></a>
<span id="l2081">            }</span><a href="#l2081"></a>
<span id="l2082">        }</span><a href="#l2082"></a>
<span id="l2083"></span><a href="#l2083"></a>
<span id="l2084">        r.write(level);</span><a href="#l2084"></a>
<span id="l2085">        r.write(description);</span><a href="#l2085"></a>
<span id="l2086">        try {</span><a href="#l2086"></a>
<span id="l2087">            writeRecord(r);</span><a href="#l2087"></a>
<span id="l2088">        } catch (IOException e) {</span><a href="#l2088"></a>
<span id="l2089">            if (useDebug) {</span><a href="#l2089"></a>
<span id="l2090">                System.out.println(Thread.currentThread().getName() +</span><a href="#l2090"></a>
<span id="l2091">                    &quot;, Exception sending alert: &quot; + e);</span><a href="#l2091"></a>
<span id="l2092">            }</span><a href="#l2092"></a>
<span id="l2093">        }</span><a href="#l2093"></a>
<span id="l2094">    }</span><a href="#l2094"></a>
<span id="l2095"></span><a href="#l2095"></a>
<span id="l2096">    //</span><a href="#l2096"></a>
<span id="l2097">    // VARIOUS OTHER METHODS</span><a href="#l2097"></a>
<span id="l2098">    //</span><a href="#l2098"></a>
<span id="l2099"></span><a href="#l2099"></a>
<span id="l2100">    /*</span><a href="#l2100"></a>
<span id="l2101">     * When a connection finishes handshaking by enabling use of a newly</span><a href="#l2101"></a>
<span id="l2102">     * negotiated session, each end learns about it in two halves (read,</span><a href="#l2102"></a>
<span id="l2103">     * and write).  When both read and write ciphers have changed, and the</span><a href="#l2103"></a>
<span id="l2104">     * last handshake message has been read, the connection has joined</span><a href="#l2104"></a>
<span id="l2105">     * (rejoined) the new session.</span><a href="#l2105"></a>
<span id="l2106">     *</span><a href="#l2106"></a>
<span id="l2107">     * NOTE:  The SSLv3 spec is rather unclear on the concepts here.</span><a href="#l2107"></a>
<span id="l2108">     * Sessions don't change once they're established (including cipher</span><a href="#l2108"></a>
<span id="l2109">     * suite and master secret) but connections can join them (and leave</span><a href="#l2109"></a>
<span id="l2110">     * them).  They're created by handshaking, though sometime handshaking</span><a href="#l2110"></a>
<span id="l2111">     * causes connections to join up with pre-established sessions.</span><a href="#l2111"></a>
<span id="l2112">     */</span><a href="#l2112"></a>
<span id="l2113">    private void changeReadCiphers() throws SSLException {</span><a href="#l2113"></a>
<span id="l2114">        if (connectionState != cs_HANDSHAKE</span><a href="#l2114"></a>
<span id="l2115">                &amp;&amp; connectionState != cs_RENEGOTIATE) {</span><a href="#l2115"></a>
<span id="l2116">            throw new SSLProtocolException(</span><a href="#l2116"></a>
<span id="l2117">                &quot;State error, change cipher specs&quot;);</span><a href="#l2117"></a>
<span id="l2118">        }</span><a href="#l2118"></a>
<span id="l2119"></span><a href="#l2119"></a>
<span id="l2120">        // ... create decompressor</span><a href="#l2120"></a>
<span id="l2121"></span><a href="#l2121"></a>
<span id="l2122">        CipherBox oldCipher = readCipher;</span><a href="#l2122"></a>
<span id="l2123"></span><a href="#l2123"></a>
<span id="l2124">        try {</span><a href="#l2124"></a>
<span id="l2125">            readCipher = handshaker.newReadCipher();</span><a href="#l2125"></a>
<span id="l2126">            readAuthenticator = handshaker.newReadAuthenticator();</span><a href="#l2126"></a>
<span id="l2127">        } catch (GeneralSecurityException e) {</span><a href="#l2127"></a>
<span id="l2128">            // &quot;can't happen&quot;</span><a href="#l2128"></a>
<span id="l2129">            throw new SSLException(&quot;Algorithm missing:  &quot;, e);</span><a href="#l2129"></a>
<span id="l2130">        }</span><a href="#l2130"></a>
<span id="l2131"></span><a href="#l2131"></a>
<span id="l2132">        /*</span><a href="#l2132"></a>
<span id="l2133">         * Dispose of any intermediate state in the underlying cipher.</span><a href="#l2133"></a>
<span id="l2134">         * For PKCS11 ciphers, this will release any attached sessions,</span><a href="#l2134"></a>
<span id="l2135">         * and thus make finalization faster.</span><a href="#l2135"></a>
<span id="l2136">         *</span><a href="#l2136"></a>
<span id="l2137">         * Since MAC's doFinal() is called for every SSL/TLS packet, it's</span><a href="#l2137"></a>
<span id="l2138">         * not necessary to do the same with MAC's.</span><a href="#l2138"></a>
<span id="l2139">         */</span><a href="#l2139"></a>
<span id="l2140">        oldCipher.dispose();</span><a href="#l2140"></a>
<span id="l2141">    }</span><a href="#l2141"></a>
<span id="l2142"></span><a href="#l2142"></a>
<span id="l2143">    // used by Handshaker</span><a href="#l2143"></a>
<span id="l2144">    void changeWriteCiphers() throws SSLException {</span><a href="#l2144"></a>
<span id="l2145">        if (connectionState != cs_HANDSHAKE</span><a href="#l2145"></a>
<span id="l2146">                &amp;&amp; connectionState != cs_RENEGOTIATE) {</span><a href="#l2146"></a>
<span id="l2147">            throw new SSLProtocolException(</span><a href="#l2147"></a>
<span id="l2148">                &quot;State error, change cipher specs&quot;);</span><a href="#l2148"></a>
<span id="l2149">        }</span><a href="#l2149"></a>
<span id="l2150"></span><a href="#l2150"></a>
<span id="l2151">        // ... create compressor</span><a href="#l2151"></a>
<span id="l2152"></span><a href="#l2152"></a>
<span id="l2153">        CipherBox oldCipher = writeCipher;</span><a href="#l2153"></a>
<span id="l2154"></span><a href="#l2154"></a>
<span id="l2155">        try {</span><a href="#l2155"></a>
<span id="l2156">            writeCipher = handshaker.newWriteCipher();</span><a href="#l2156"></a>
<span id="l2157">            writeAuthenticator = handshaker.newWriteAuthenticator();</span><a href="#l2157"></a>
<span id="l2158">        } catch (GeneralSecurityException e) {</span><a href="#l2158"></a>
<span id="l2159">            // &quot;can't happen&quot;</span><a href="#l2159"></a>
<span id="l2160">            throw new SSLException(&quot;Algorithm missing:  &quot;, e);</span><a href="#l2160"></a>
<span id="l2161">        }</span><a href="#l2161"></a>
<span id="l2162"></span><a href="#l2162"></a>
<span id="l2163">        // See comment above.</span><a href="#l2163"></a>
<span id="l2164">        oldCipher.dispose();</span><a href="#l2164"></a>
<span id="l2165"></span><a href="#l2165"></a>
<span id="l2166">        // reset the flag of the first application record</span><a href="#l2166"></a>
<span id="l2167">        isFirstAppOutputRecord = true;</span><a href="#l2167"></a>
<span id="l2168">    }</span><a href="#l2168"></a>
<span id="l2169"></span><a href="#l2169"></a>
<span id="l2170">    /*</span><a href="#l2170"></a>
<span id="l2171">     * Updates the SSL version associated with this connection.</span><a href="#l2171"></a>
<span id="l2172">     * Called from Handshaker once it has determined the negotiated version.</span><a href="#l2172"></a>
<span id="l2173">     */</span><a href="#l2173"></a>
<span id="l2174">    synchronized void setVersion(ProtocolVersion protocolVersion) {</span><a href="#l2174"></a>
<span id="l2175">        this.protocolVersion = protocolVersion;</span><a href="#l2175"></a>
<span id="l2176">        output.r.setVersion(protocolVersion);</span><a href="#l2176"></a>
<span id="l2177">    }</span><a href="#l2177"></a>
<span id="l2178"></span><a href="#l2178"></a>
<span id="l2179">    //</span><a href="#l2179"></a>
<span id="l2180">    // ONLY used by ClientHandshaker for the server hostname during handshaking</span><a href="#l2180"></a>
<span id="l2181">    //</span><a href="#l2181"></a>
<span id="l2182">    synchronized String getHost() {</span><a href="#l2182"></a>
<span id="l2183">        // Note that the host may be null or empty for localhost.</span><a href="#l2183"></a>
<span id="l2184">        if (host == null || host.length() == 0) {</span><a href="#l2184"></a>
<span id="l2185">            useImplicitHost(true);</span><a href="#l2185"></a>
<span id="l2186">        }</span><a href="#l2186"></a>
<span id="l2187"></span><a href="#l2187"></a>
<span id="l2188">        return host;</span><a href="#l2188"></a>
<span id="l2189">    }</span><a href="#l2189"></a>
<span id="l2190"></span><a href="#l2190"></a>
<span id="l2191">    /*</span><a href="#l2191"></a>
<span id="l2192">     * Try to set and use the implicit specified hostname</span><a href="#l2192"></a>
<span id="l2193">     */</span><a href="#l2193"></a>
<span id="l2194">    private synchronized void useImplicitHost(boolean noSniUpdate) {</span><a href="#l2194"></a>
<span id="l2195"></span><a href="#l2195"></a>
<span id="l2196">        // Note: If the local name service is not trustworthy, reverse</span><a href="#l2196"></a>
<span id="l2197">        // host name resolution should not be performed for endpoint</span><a href="#l2197"></a>
<span id="l2198">        // identification.  Use the application original specified</span><a href="#l2198"></a>
<span id="l2199">        // hostname or IP address instead.</span><a href="#l2199"></a>
<span id="l2200"></span><a href="#l2200"></a>
<span id="l2201">        // Get the original hostname via jdk.internal.misc.SharedSecrets</span><a href="#l2201"></a>
<span id="l2202">        InetAddress inetAddress = getInetAddress();</span><a href="#l2202"></a>
<span id="l2203">        if (inetAddress == null) {      // not connected</span><a href="#l2203"></a>
<span id="l2204">            return;</span><a href="#l2204"></a>
<span id="l2205">        }</span><a href="#l2205"></a>
<span id="l2206"></span><a href="#l2206"></a>
<span id="l2207">        JavaNetAccess jna = SharedSecrets.getJavaNetAccess();</span><a href="#l2207"></a>
<span id="l2208">        String originalHostname = jna.getOriginalHostName(inetAddress);</span><a href="#l2208"></a>
<span id="l2209">        if ((originalHostname != null) &amp;&amp;</span><a href="#l2209"></a>
<span id="l2210">                (originalHostname.length() != 0)) {</span><a href="#l2210"></a>
<span id="l2211"></span><a href="#l2211"></a>
<span id="l2212">            host = originalHostname;</span><a href="#l2212"></a>
<span id="l2213">            if (!noSniUpdate &amp;&amp; serverNames.isEmpty() &amp;&amp; !noSniExtension) {</span><a href="#l2213"></a>
<span id="l2214">                serverNames =</span><a href="#l2214"></a>
<span id="l2215">                        Utilities.addToSNIServerNameList(serverNames, host);</span><a href="#l2215"></a>
<span id="l2216"></span><a href="#l2216"></a>
<span id="l2217">                if (!roleIsServer &amp;&amp;</span><a href="#l2217"></a>
<span id="l2218">                        (handshaker != null) &amp;&amp; !handshaker.started()) {</span><a href="#l2218"></a>
<span id="l2219">                    handshaker.setSNIServerNames(serverNames);</span><a href="#l2219"></a>
<span id="l2220">                }</span><a href="#l2220"></a>
<span id="l2221">            }</span><a href="#l2221"></a>
<span id="l2222"></span><a href="#l2222"></a>
<span id="l2223">            return;</span><a href="#l2223"></a>
<span id="l2224">        }</span><a href="#l2224"></a>
<span id="l2225"></span><a href="#l2225"></a>
<span id="l2226">        // No explicitly specified hostname, no server name indication.</span><a href="#l2226"></a>
<span id="l2227">        if (!trustNameService) {</span><a href="#l2227"></a>
<span id="l2228">            // The local name service is not trustworthy, use IP address.</span><a href="#l2228"></a>
<span id="l2229">            host = inetAddress.getHostAddress();</span><a href="#l2229"></a>
<span id="l2230">        } else {</span><a href="#l2230"></a>
<span id="l2231">            // Use the underlying reverse host name resolution service.</span><a href="#l2231"></a>
<span id="l2232">            host = getInetAddress().getHostName();</span><a href="#l2232"></a>
<span id="l2233">        }</span><a href="#l2233"></a>
<span id="l2234">    }</span><a href="#l2234"></a>
<span id="l2235"></span><a href="#l2235"></a>
<span id="l2236"></span><a href="#l2236"></a>
<span id="l2237">    // ONLY used by HttpsClient to setup the URI specified hostname</span><a href="#l2237"></a>
<span id="l2238">    //</span><a href="#l2238"></a>
<span id="l2239">    // Please NOTE that this method MUST be called before calling to</span><a href="#l2239"></a>
<span id="l2240">    // SSLSocket.setSSLParameters(). Otherwise, the {@code host} parameter</span><a href="#l2240"></a>
<span id="l2241">    // may override SNIHostName in the customized server name indication.</span><a href="#l2241"></a>
<span id="l2242">    synchronized public void setHost(String host) {</span><a href="#l2242"></a>
<span id="l2243">        this.host = host;</span><a href="#l2243"></a>
<span id="l2244">        this.serverNames =</span><a href="#l2244"></a>
<span id="l2245">            Utilities.addToSNIServerNameList(this.serverNames, this.host);</span><a href="#l2245"></a>
<span id="l2246"></span><a href="#l2246"></a>
<span id="l2247">        if (!roleIsServer &amp;&amp; (handshaker != null) &amp;&amp; !handshaker.started()) {</span><a href="#l2247"></a>
<span id="l2248">            handshaker.setSNIServerNames(serverNames);</span><a href="#l2248"></a>
<span id="l2249">        }</span><a href="#l2249"></a>
<span id="l2250">    }</span><a href="#l2250"></a>
<span id="l2251"></span><a href="#l2251"></a>
<span id="l2252">    /**</span><a href="#l2252"></a>
<span id="l2253">     * Gets an input stream to read from the peer on the other side.</span><a href="#l2253"></a>
<span id="l2254">     * Data read from this stream was always integrity protected in</span><a href="#l2254"></a>
<span id="l2255">     * transit, and will usually have been confidentiality protected.</span><a href="#l2255"></a>
<span id="l2256">     */</span><a href="#l2256"></a>
<span id="l2257">    @Override</span><a href="#l2257"></a>
<span id="l2258">    synchronized public InputStream getInputStream() throws IOException {</span><a href="#l2258"></a>
<span id="l2259">        if (isClosed()) {</span><a href="#l2259"></a>
<span id="l2260">            throw new SocketException(&quot;Socket is closed&quot;);</span><a href="#l2260"></a>
<span id="l2261">        }</span><a href="#l2261"></a>
<span id="l2262"></span><a href="#l2262"></a>
<span id="l2263">        /*</span><a href="#l2263"></a>
<span id="l2264">         * Can't call isConnected() here, because the Handshakers</span><a href="#l2264"></a>
<span id="l2265">         * do some initialization before we actually connect.</span><a href="#l2265"></a>
<span id="l2266">         */</span><a href="#l2266"></a>
<span id="l2267">        if (connectionState == cs_START) {</span><a href="#l2267"></a>
<span id="l2268">            throw new SocketException(&quot;Socket is not connected&quot;);</span><a href="#l2268"></a>
<span id="l2269">        }</span><a href="#l2269"></a>
<span id="l2270"></span><a href="#l2270"></a>
<span id="l2271">        return input;</span><a href="#l2271"></a>
<span id="l2272">    }</span><a href="#l2272"></a>
<span id="l2273"></span><a href="#l2273"></a>
<span id="l2274">    /**</span><a href="#l2274"></a>
<span id="l2275">     * Gets an output stream to write to the peer on the other side.</span><a href="#l2275"></a>
<span id="l2276">     * Data written on this stream is always integrity protected, and</span><a href="#l2276"></a>
<span id="l2277">     * will usually be confidentiality protected.</span><a href="#l2277"></a>
<span id="l2278">     */</span><a href="#l2278"></a>
<span id="l2279">    @Override</span><a href="#l2279"></a>
<span id="l2280">    synchronized public OutputStream getOutputStream() throws IOException {</span><a href="#l2280"></a>
<span id="l2281">        if (isClosed()) {</span><a href="#l2281"></a>
<span id="l2282">            throw new SocketException(&quot;Socket is closed&quot;);</span><a href="#l2282"></a>
<span id="l2283">        }</span><a href="#l2283"></a>
<span id="l2284"></span><a href="#l2284"></a>
<span id="l2285">        /*</span><a href="#l2285"></a>
<span id="l2286">         * Can't call isConnected() here, because the Handshakers</span><a href="#l2286"></a>
<span id="l2287">         * do some initialization before we actually connect.</span><a href="#l2287"></a>
<span id="l2288">         */</span><a href="#l2288"></a>
<span id="l2289">        if (connectionState == cs_START) {</span><a href="#l2289"></a>
<span id="l2290">            throw new SocketException(&quot;Socket is not connected&quot;);</span><a href="#l2290"></a>
<span id="l2291">        }</span><a href="#l2291"></a>
<span id="l2292"></span><a href="#l2292"></a>
<span id="l2293">        return output;</span><a href="#l2293"></a>
<span id="l2294">    }</span><a href="#l2294"></a>
<span id="l2295"></span><a href="#l2295"></a>
<span id="l2296">    /**</span><a href="#l2296"></a>
<span id="l2297">     * Returns the the SSL Session in use by this connection.  These can</span><a href="#l2297"></a>
<span id="l2298">     * be long lived, and frequently correspond to an entire login session</span><a href="#l2298"></a>
<span id="l2299">     * for some user.</span><a href="#l2299"></a>
<span id="l2300">     */</span><a href="#l2300"></a>
<span id="l2301">    @Override</span><a href="#l2301"></a>
<span id="l2302">    public SSLSession getSession() {</span><a href="#l2302"></a>
<span id="l2303">        /*</span><a href="#l2303"></a>
<span id="l2304">         * Force a synchronous handshake, if appropriate.</span><a href="#l2304"></a>
<span id="l2305">         */</span><a href="#l2305"></a>
<span id="l2306">        if (getConnectionState() == cs_HANDSHAKE) {</span><a href="#l2306"></a>
<span id="l2307">            try {</span><a href="#l2307"></a>
<span id="l2308">                // start handshaking, if failed, the connection will be closed.</span><a href="#l2308"></a>
<span id="l2309">                startHandshake(false);</span><a href="#l2309"></a>
<span id="l2310">            } catch (IOException e) {</span><a href="#l2310"></a>
<span id="l2311">                // handshake failed. log and return a nullSession</span><a href="#l2311"></a>
<span id="l2312">                if (debug != null &amp;&amp; Debug.isOn(&quot;handshake&quot;)) {</span><a href="#l2312"></a>
<span id="l2313">                      System.out.println(Thread.currentThread().getName() +</span><a href="#l2313"></a>
<span id="l2314">                          &quot;, IOException in getSession():  &quot; + e);</span><a href="#l2314"></a>
<span id="l2315">                }</span><a href="#l2315"></a>
<span id="l2316">            }</span><a href="#l2316"></a>
<span id="l2317">        }</span><a href="#l2317"></a>
<span id="l2318">        synchronized (this) {</span><a href="#l2318"></a>
<span id="l2319">            return sess;</span><a href="#l2319"></a>
<span id="l2320">        }</span><a href="#l2320"></a>
<span id="l2321">    }</span><a href="#l2321"></a>
<span id="l2322"></span><a href="#l2322"></a>
<span id="l2323">    @Override</span><a href="#l2323"></a>
<span id="l2324">    synchronized public SSLSession getHandshakeSession() {</span><a href="#l2324"></a>
<span id="l2325">        return handshakeSession;</span><a href="#l2325"></a>
<span id="l2326">    }</span><a href="#l2326"></a>
<span id="l2327"></span><a href="#l2327"></a>
<span id="l2328">    synchronized void setHandshakeSession(SSLSessionImpl session) {</span><a href="#l2328"></a>
<span id="l2329">        handshakeSession = session;</span><a href="#l2329"></a>
<span id="l2330">    }</span><a href="#l2330"></a>
<span id="l2331"></span><a href="#l2331"></a>
<span id="l2332">    /**</span><a href="#l2332"></a>
<span id="l2333">     * Controls whether new connections may cause creation of new SSL</span><a href="#l2333"></a>
<span id="l2334">     * sessions.</span><a href="#l2334"></a>
<span id="l2335">     *</span><a href="#l2335"></a>
<span id="l2336">     * As long as handshaking has not started, we can change</span><a href="#l2336"></a>
<span id="l2337">     * whether we enable session creations.  Otherwise,</span><a href="#l2337"></a>
<span id="l2338">     * we will need to wait for the next handshake.</span><a href="#l2338"></a>
<span id="l2339">     */</span><a href="#l2339"></a>
<span id="l2340">    @Override</span><a href="#l2340"></a>
<span id="l2341">    synchronized public void setEnableSessionCreation(boolean flag) {</span><a href="#l2341"></a>
<span id="l2342">        enableSessionCreation = flag;</span><a href="#l2342"></a>
<span id="l2343"></span><a href="#l2343"></a>
<span id="l2344">        if ((handshaker != null) &amp;&amp; !handshaker.activated()) {</span><a href="#l2344"></a>
<span id="l2345">            handshaker.setEnableSessionCreation(enableSessionCreation);</span><a href="#l2345"></a>
<span id="l2346">        }</span><a href="#l2346"></a>
<span id="l2347">    }</span><a href="#l2347"></a>
<span id="l2348"></span><a href="#l2348"></a>
<span id="l2349">    /**</span><a href="#l2349"></a>
<span id="l2350">     * Returns true if new connections may cause creation of new SSL</span><a href="#l2350"></a>
<span id="l2351">     * sessions.</span><a href="#l2351"></a>
<span id="l2352">     */</span><a href="#l2352"></a>
<span id="l2353">    @Override</span><a href="#l2353"></a>
<span id="l2354">    synchronized public boolean getEnableSessionCreation() {</span><a href="#l2354"></a>
<span id="l2355">        return enableSessionCreation;</span><a href="#l2355"></a>
<span id="l2356">    }</span><a href="#l2356"></a>
<span id="l2357"></span><a href="#l2357"></a>
<span id="l2358"></span><a href="#l2358"></a>
<span id="l2359">    /**</span><a href="#l2359"></a>
<span id="l2360">     * Sets the flag controlling whether a server mode socket</span><a href="#l2360"></a>
<span id="l2361">     * *REQUIRES* SSL client authentication.</span><a href="#l2361"></a>
<span id="l2362">     *</span><a href="#l2362"></a>
<span id="l2363">     * As long as handshaking has not started, we can change</span><a href="#l2363"></a>
<span id="l2364">     * whether client authentication is needed.  Otherwise,</span><a href="#l2364"></a>
<span id="l2365">     * we will need to wait for the next handshake.</span><a href="#l2365"></a>
<span id="l2366">     */</span><a href="#l2366"></a>
<span id="l2367">    @Override</span><a href="#l2367"></a>
<span id="l2368">    synchronized public void setNeedClientAuth(boolean flag) {</span><a href="#l2368"></a>
<span id="l2369">        doClientAuth = (flag ?</span><a href="#l2369"></a>
<span id="l2370">            SSLEngineImpl.clauth_required : SSLEngineImpl.clauth_none);</span><a href="#l2370"></a>
<span id="l2371"></span><a href="#l2371"></a>
<span id="l2372">        if ((handshaker != null) &amp;&amp;</span><a href="#l2372"></a>
<span id="l2373">                (handshaker instanceof ServerHandshaker) &amp;&amp;</span><a href="#l2373"></a>
<span id="l2374">                !handshaker.activated()) {</span><a href="#l2374"></a>
<span id="l2375">            ((ServerHandshaker) handshaker).setClientAuth(doClientAuth);</span><a href="#l2375"></a>
<span id="l2376">        }</span><a href="#l2376"></a>
<span id="l2377">    }</span><a href="#l2377"></a>
<span id="l2378"></span><a href="#l2378"></a>
<span id="l2379">    @Override</span><a href="#l2379"></a>
<span id="l2380">    synchronized public boolean getNeedClientAuth() {</span><a href="#l2380"></a>
<span id="l2381">        return (doClientAuth == SSLEngineImpl.clauth_required);</span><a href="#l2381"></a>
<span id="l2382">    }</span><a href="#l2382"></a>
<span id="l2383"></span><a href="#l2383"></a>
<span id="l2384">    /**</span><a href="#l2384"></a>
<span id="l2385">     * Sets the flag controlling whether a server mode socket</span><a href="#l2385"></a>
<span id="l2386">     * *REQUESTS* SSL client authentication.</span><a href="#l2386"></a>
<span id="l2387">     *</span><a href="#l2387"></a>
<span id="l2388">     * As long as handshaking has not started, we can change</span><a href="#l2388"></a>
<span id="l2389">     * whether client authentication is requested.  Otherwise,</span><a href="#l2389"></a>
<span id="l2390">     * we will need to wait for the next handshake.</span><a href="#l2390"></a>
<span id="l2391">     */</span><a href="#l2391"></a>
<span id="l2392">    @Override</span><a href="#l2392"></a>
<span id="l2393">    synchronized public void setWantClientAuth(boolean flag) {</span><a href="#l2393"></a>
<span id="l2394">        doClientAuth = (flag ?</span><a href="#l2394"></a>
<span id="l2395">            SSLEngineImpl.clauth_requested : SSLEngineImpl.clauth_none);</span><a href="#l2395"></a>
<span id="l2396"></span><a href="#l2396"></a>
<span id="l2397">        if ((handshaker != null) &amp;&amp;</span><a href="#l2397"></a>
<span id="l2398">                (handshaker instanceof ServerHandshaker) &amp;&amp;</span><a href="#l2398"></a>
<span id="l2399">                !handshaker.activated()) {</span><a href="#l2399"></a>
<span id="l2400">            ((ServerHandshaker) handshaker).setClientAuth(doClientAuth);</span><a href="#l2400"></a>
<span id="l2401">        }</span><a href="#l2401"></a>
<span id="l2402">    }</span><a href="#l2402"></a>
<span id="l2403"></span><a href="#l2403"></a>
<span id="l2404">    @Override</span><a href="#l2404"></a>
<span id="l2405">    synchronized public boolean getWantClientAuth() {</span><a href="#l2405"></a>
<span id="l2406">        return (doClientAuth == SSLEngineImpl.clauth_requested);</span><a href="#l2406"></a>
<span id="l2407">    }</span><a href="#l2407"></a>
<span id="l2408"></span><a href="#l2408"></a>
<span id="l2409"></span><a href="#l2409"></a>
<span id="l2410">    /**</span><a href="#l2410"></a>
<span id="l2411">     * Sets the flag controlling whether the socket is in SSL</span><a href="#l2411"></a>
<span id="l2412">     * client or server mode.  Must be called before any SSL</span><a href="#l2412"></a>
<span id="l2413">     * traffic has started.</span><a href="#l2413"></a>
<span id="l2414">     */</span><a href="#l2414"></a>
<span id="l2415">    @Override</span><a href="#l2415"></a>
<span id="l2416">    @SuppressWarnings(&quot;fallthrough&quot;)</span><a href="#l2416"></a>
<span id="l2417">    synchronized public void setUseClientMode(boolean flag) {</span><a href="#l2417"></a>
<span id="l2418">        switch (connectionState) {</span><a href="#l2418"></a>
<span id="l2419"></span><a href="#l2419"></a>
<span id="l2420">        case cs_START:</span><a href="#l2420"></a>
<span id="l2421">            /*</span><a href="#l2421"></a>
<span id="l2422">             * If we need to change the socket mode and the enabled</span><a href="#l2422"></a>
<span id="l2423">             * protocols and cipher suites haven't specifically been</span><a href="#l2423"></a>
<span id="l2424">             * set by the user, change them to the corresponding</span><a href="#l2424"></a>
<span id="l2425">             * default ones.</span><a href="#l2425"></a>
<span id="l2426">             */</span><a href="#l2426"></a>
<span id="l2427">            if (roleIsServer != (!flag)) {</span><a href="#l2427"></a>
<span id="l2428">                if (sslContext.isDefaultProtocolList(enabledProtocols)) {</span><a href="#l2428"></a>
<span id="l2429">                    enabledProtocols =</span><a href="#l2429"></a>
<span id="l2430">                            sslContext.getDefaultProtocolList(!flag);</span><a href="#l2430"></a>
<span id="l2431">                }</span><a href="#l2431"></a>
<span id="l2432"></span><a href="#l2432"></a>
<span id="l2433">                if (sslContext.isDefaultCipherSuiteList(enabledCipherSuites)) {</span><a href="#l2433"></a>
<span id="l2434">                    enabledCipherSuites =</span><a href="#l2434"></a>
<span id="l2435">                            sslContext.getDefaultCipherSuiteList(!flag);</span><a href="#l2435"></a>
<span id="l2436">                }</span><a href="#l2436"></a>
<span id="l2437">            }</span><a href="#l2437"></a>
<span id="l2438"></span><a href="#l2438"></a>
<span id="l2439">            roleIsServer = !flag;</span><a href="#l2439"></a>
<span id="l2440">            break;</span><a href="#l2440"></a>
<span id="l2441"></span><a href="#l2441"></a>
<span id="l2442">        case cs_HANDSHAKE:</span><a href="#l2442"></a>
<span id="l2443">            /*</span><a href="#l2443"></a>
<span id="l2444">             * If we have a handshaker, but haven't started</span><a href="#l2444"></a>
<span id="l2445">             * SSL traffic, we can throw away our current</span><a href="#l2445"></a>
<span id="l2446">             * handshaker, and start from scratch.  Don't</span><a href="#l2446"></a>
<span id="l2447">             * need to call doneConnect() again, we already</span><a href="#l2447"></a>
<span id="l2448">             * have the streams.</span><a href="#l2448"></a>
<span id="l2449">             */</span><a href="#l2449"></a>
<span id="l2450">            assert(handshaker != null);</span><a href="#l2450"></a>
<span id="l2451">            if (!handshaker.activated()) {</span><a href="#l2451"></a>
<span id="l2452">                /*</span><a href="#l2452"></a>
<span id="l2453">                 * If we need to change the socket mode and the enabled</span><a href="#l2453"></a>
<span id="l2454">                 * protocols haven't specifically been set by the user,</span><a href="#l2454"></a>
<span id="l2455">                 * change them to the corresponding default ones.</span><a href="#l2455"></a>
<span id="l2456">                 */</span><a href="#l2456"></a>
<span id="l2457">                if (roleIsServer != (!flag) &amp;&amp;</span><a href="#l2457"></a>
<span id="l2458">                        sslContext.isDefaultProtocolList(enabledProtocols)) {</span><a href="#l2458"></a>
<span id="l2459">                    enabledProtocols = sslContext.getDefaultProtocolList(!flag);</span><a href="#l2459"></a>
<span id="l2460">                }</span><a href="#l2460"></a>
<span id="l2461">                roleIsServer = !flag;</span><a href="#l2461"></a>
<span id="l2462">                connectionState = cs_START;</span><a href="#l2462"></a>
<span id="l2463">                initHandshaker();</span><a href="#l2463"></a>
<span id="l2464">                break;</span><a href="#l2464"></a>
<span id="l2465">            }</span><a href="#l2465"></a>
<span id="l2466"></span><a href="#l2466"></a>
<span id="l2467">            // If handshake has started, that's an error.  Fall through...</span><a href="#l2467"></a>
<span id="l2468"></span><a href="#l2468"></a>
<span id="l2469">        default:</span><a href="#l2469"></a>
<span id="l2470">            if (debug != null &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l2470"></a>
<span id="l2471">                System.out.println(Thread.currentThread().getName() +</span><a href="#l2471"></a>
<span id="l2472">                    &quot;, setUseClientMode() invoked in state = &quot; +</span><a href="#l2472"></a>
<span id="l2473">                    connectionState);</span><a href="#l2473"></a>
<span id="l2474">            }</span><a href="#l2474"></a>
<span id="l2475">            throw new IllegalArgumentException(</span><a href="#l2475"></a>
<span id="l2476">                &quot;Cannot change mode after SSL traffic has started&quot;);</span><a href="#l2476"></a>
<span id="l2477">        }</span><a href="#l2477"></a>
<span id="l2478">    }</span><a href="#l2478"></a>
<span id="l2479"></span><a href="#l2479"></a>
<span id="l2480">    @Override</span><a href="#l2480"></a>
<span id="l2481">    synchronized public boolean getUseClientMode() {</span><a href="#l2481"></a>
<span id="l2482">        return !roleIsServer;</span><a href="#l2482"></a>
<span id="l2483">    }</span><a href="#l2483"></a>
<span id="l2484"></span><a href="#l2484"></a>
<span id="l2485"></span><a href="#l2485"></a>
<span id="l2486">    /**</span><a href="#l2486"></a>
<span id="l2487">     * Returns the names of the cipher suites which could be enabled for use</span><a href="#l2487"></a>
<span id="l2488">     * on an SSL connection.  Normally, only a subset of these will actually</span><a href="#l2488"></a>
<span id="l2489">     * be enabled by default, since this list may include cipher suites which</span><a href="#l2489"></a>
<span id="l2490">     * do not support the mutual authentication of servers and clients, or</span><a href="#l2490"></a>
<span id="l2491">     * which do not protect data confidentiality.  Servers may also need</span><a href="#l2491"></a>
<span id="l2492">     * certain kinds of certificates to use certain cipher suites.</span><a href="#l2492"></a>
<span id="l2493">     *</span><a href="#l2493"></a>
<span id="l2494">     * @return an array of cipher suite names</span><a href="#l2494"></a>
<span id="l2495">     */</span><a href="#l2495"></a>
<span id="l2496">    @Override</span><a href="#l2496"></a>
<span id="l2497">    public String[] getSupportedCipherSuites() {</span><a href="#l2497"></a>
<span id="l2498">        return sslContext.getSupportedCipherSuiteList().toStringArray();</span><a href="#l2498"></a>
<span id="l2499">    }</span><a href="#l2499"></a>
<span id="l2500"></span><a href="#l2500"></a>
<span id="l2501">    /**</span><a href="#l2501"></a>
<span id="l2502">     * Controls which particular cipher suites are enabled for use on</span><a href="#l2502"></a>
<span id="l2503">     * this connection.  The cipher suites must have been listed by</span><a href="#l2503"></a>
<span id="l2504">     * getCipherSuites() as being supported.  Even if a suite has been</span><a href="#l2504"></a>
<span id="l2505">     * enabled, it might never be used if no peer supports it or the</span><a href="#l2505"></a>
<span id="l2506">     * requisite certificates (and private keys) are not available.</span><a href="#l2506"></a>
<span id="l2507">     *</span><a href="#l2507"></a>
<span id="l2508">     * @param suites Names of all the cipher suites to enable.</span><a href="#l2508"></a>
<span id="l2509">     */</span><a href="#l2509"></a>
<span id="l2510">    @Override</span><a href="#l2510"></a>
<span id="l2511">    synchronized public void setEnabledCipherSuites(String[] suites) {</span><a href="#l2511"></a>
<span id="l2512">        enabledCipherSuites = new CipherSuiteList(suites);</span><a href="#l2512"></a>
<span id="l2513">        if ((handshaker != null) &amp;&amp; !handshaker.activated()) {</span><a href="#l2513"></a>
<span id="l2514">            handshaker.setEnabledCipherSuites(enabledCipherSuites);</span><a href="#l2514"></a>
<span id="l2515">        }</span><a href="#l2515"></a>
<span id="l2516">    }</span><a href="#l2516"></a>
<span id="l2517"></span><a href="#l2517"></a>
<span id="l2518">    /**</span><a href="#l2518"></a>
<span id="l2519">     * Returns the names of the SSL cipher suites which are currently enabled</span><a href="#l2519"></a>
<span id="l2520">     * for use on this connection.  When an SSL socket is first created,</span><a href="#l2520"></a>
<span id="l2521">     * all enabled cipher suites &lt;em&gt;(a)&lt;/em&gt; protect data confidentiality,</span><a href="#l2521"></a>
<span id="l2522">     * by traffic encryption, and &lt;em&gt;(b)&lt;/em&gt; can mutually authenticate</span><a href="#l2522"></a>
<span id="l2523">     * both clients and servers.  Thus, in some environments, this value</span><a href="#l2523"></a>
<span id="l2524">     * might be empty.</span><a href="#l2524"></a>
<span id="l2525">     *</span><a href="#l2525"></a>
<span id="l2526">     * @return an array of cipher suite names</span><a href="#l2526"></a>
<span id="l2527">     */</span><a href="#l2527"></a>
<span id="l2528">    @Override</span><a href="#l2528"></a>
<span id="l2529">    synchronized public String[] getEnabledCipherSuites() {</span><a href="#l2529"></a>
<span id="l2530">        return enabledCipherSuites.toStringArray();</span><a href="#l2530"></a>
<span id="l2531">    }</span><a href="#l2531"></a>
<span id="l2532"></span><a href="#l2532"></a>
<span id="l2533"></span><a href="#l2533"></a>
<span id="l2534">    /**</span><a href="#l2534"></a>
<span id="l2535">     * Returns the protocols that are supported by this implementation.</span><a href="#l2535"></a>
<span id="l2536">     * A subset of the supported protocols may be enabled for this connection</span><a href="#l2536"></a>
<span id="l2537">     * @return an array of protocol names.</span><a href="#l2537"></a>
<span id="l2538">     */</span><a href="#l2538"></a>
<span id="l2539">    @Override</span><a href="#l2539"></a>
<span id="l2540">    public String[] getSupportedProtocols() {</span><a href="#l2540"></a>
<span id="l2541">        return sslContext.getSuportedProtocolList().toStringArray();</span><a href="#l2541"></a>
<span id="l2542">    }</span><a href="#l2542"></a>
<span id="l2543"></span><a href="#l2543"></a>
<span id="l2544">    /**</span><a href="#l2544"></a>
<span id="l2545">     * Controls which protocols are enabled for use on</span><a href="#l2545"></a>
<span id="l2546">     * this connection.  The protocols must have been listed by</span><a href="#l2546"></a>
<span id="l2547">     * getSupportedProtocols() as being supported.</span><a href="#l2547"></a>
<span id="l2548">     *</span><a href="#l2548"></a>
<span id="l2549">     * @param protocols protocols to enable.</span><a href="#l2549"></a>
<span id="l2550">     * @exception IllegalArgumentException when one of the protocols</span><a href="#l2550"></a>
<span id="l2551">     *  named by the parameter is not supported.</span><a href="#l2551"></a>
<span id="l2552">     */</span><a href="#l2552"></a>
<span id="l2553">    @Override</span><a href="#l2553"></a>
<span id="l2554">    synchronized public void setEnabledProtocols(String[] protocols) {</span><a href="#l2554"></a>
<span id="l2555">        enabledProtocols = new ProtocolList(protocols);</span><a href="#l2555"></a>
<span id="l2556">        if ((handshaker != null) &amp;&amp; !handshaker.activated()) {</span><a href="#l2556"></a>
<span id="l2557">            handshaker.setEnabledProtocols(enabledProtocols);</span><a href="#l2557"></a>
<span id="l2558">        }</span><a href="#l2558"></a>
<span id="l2559">    }</span><a href="#l2559"></a>
<span id="l2560"></span><a href="#l2560"></a>
<span id="l2561">    @Override</span><a href="#l2561"></a>
<span id="l2562">    synchronized public String[] getEnabledProtocols() {</span><a href="#l2562"></a>
<span id="l2563">        return enabledProtocols.toStringArray();</span><a href="#l2563"></a>
<span id="l2564">    }</span><a href="#l2564"></a>
<span id="l2565"></span><a href="#l2565"></a>
<span id="l2566">    /**</span><a href="#l2566"></a>
<span id="l2567">     * Assigns the socket timeout.</span><a href="#l2567"></a>
<span id="l2568">     * @see java.net.Socket#setSoTimeout</span><a href="#l2568"></a>
<span id="l2569">     */</span><a href="#l2569"></a>
<span id="l2570">    @Override</span><a href="#l2570"></a>
<span id="l2571">    public void setSoTimeout(int timeout) throws SocketException {</span><a href="#l2571"></a>
<span id="l2572">        if ((debug != null) &amp;&amp; Debug.isOn(&quot;ssl&quot;)) {</span><a href="#l2572"></a>
<span id="l2573">            System.out.println(Thread.currentThread().getName() +</span><a href="#l2573"></a>
<span id="l2574">                &quot;, setSoTimeout(&quot; + timeout + &quot;) called&quot;);</span><a href="#l2574"></a>
<span id="l2575">        }</span><a href="#l2575"></a>
<span id="l2576"></span><a href="#l2576"></a>
<span id="l2577">        super.setSoTimeout(timeout);</span><a href="#l2577"></a>
<span id="l2578">    }</span><a href="#l2578"></a>
<span id="l2579"></span><a href="#l2579"></a>
<span id="l2580">    /**</span><a href="#l2580"></a>
<span id="l2581">     * Registers an event listener to receive notifications that an</span><a href="#l2581"></a>
<span id="l2582">     * SSL handshake has completed on this connection.</span><a href="#l2582"></a>
<span id="l2583">     */</span><a href="#l2583"></a>
<span id="l2584">    @Override</span><a href="#l2584"></a>
<span id="l2585">    public synchronized void addHandshakeCompletedListener(</span><a href="#l2585"></a>
<span id="l2586">            HandshakeCompletedListener listener) {</span><a href="#l2586"></a>
<span id="l2587">        if (listener == null) {</span><a href="#l2587"></a>
<span id="l2588">            throw new IllegalArgumentException(&quot;listener is null&quot;);</span><a href="#l2588"></a>
<span id="l2589">        }</span><a href="#l2589"></a>
<span id="l2590">        if (handshakeListeners == null) {</span><a href="#l2590"></a>
<span id="l2591">            handshakeListeners = new</span><a href="#l2591"></a>
<span id="l2592">                HashMap&lt;HandshakeCompletedListener, AccessControlContext&gt;(4);</span><a href="#l2592"></a>
<span id="l2593">        }</span><a href="#l2593"></a>
<span id="l2594">        handshakeListeners.put(listener, AccessController.getContext());</span><a href="#l2594"></a>
<span id="l2595">    }</span><a href="#l2595"></a>
<span id="l2596"></span><a href="#l2596"></a>
<span id="l2597"></span><a href="#l2597"></a>
<span id="l2598">    /**</span><a href="#l2598"></a>
<span id="l2599">     * Removes a previously registered handshake completion listener.</span><a href="#l2599"></a>
<span id="l2600">     */</span><a href="#l2600"></a>
<span id="l2601">    @Override</span><a href="#l2601"></a>
<span id="l2602">    public synchronized void removeHandshakeCompletedListener(</span><a href="#l2602"></a>
<span id="l2603">            HandshakeCompletedListener listener) {</span><a href="#l2603"></a>
<span id="l2604">        if (handshakeListeners == null) {</span><a href="#l2604"></a>
<span id="l2605">            throw new IllegalArgumentException(&quot;no listeners&quot;);</span><a href="#l2605"></a>
<span id="l2606">        }</span><a href="#l2606"></a>
<span id="l2607">        if (handshakeListeners.remove(listener) == null) {</span><a href="#l2607"></a>
<span id="l2608">            throw new IllegalArgumentException(&quot;listener not registered&quot;);</span><a href="#l2608"></a>
<span id="l2609">        }</span><a href="#l2609"></a>
<span id="l2610">        if (handshakeListeners.isEmpty()) {</span><a href="#l2610"></a>
<span id="l2611">            handshakeListeners = null;</span><a href="#l2611"></a>
<span id="l2612">        }</span><a href="#l2612"></a>
<span id="l2613">    }</span><a href="#l2613"></a>
<span id="l2614"></span><a href="#l2614"></a>
<span id="l2615">    /**</span><a href="#l2615"></a>
<span id="l2616">     * Returns the SSLParameters in effect for this SSLSocket.</span><a href="#l2616"></a>
<span id="l2617">     */</span><a href="#l2617"></a>
<span id="l2618">    @Override</span><a href="#l2618"></a>
<span id="l2619">    synchronized public SSLParameters getSSLParameters() {</span><a href="#l2619"></a>
<span id="l2620">        SSLParameters params = super.getSSLParameters();</span><a href="#l2620"></a>
<span id="l2621"></span><a href="#l2621"></a>
<span id="l2622">        // the super implementation does not handle the following parameters</span><a href="#l2622"></a>
<span id="l2623">        params.setEndpointIdentificationAlgorithm(identificationProtocol);</span><a href="#l2623"></a>
<span id="l2624">        params.setAlgorithmConstraints(algorithmConstraints);</span><a href="#l2624"></a>
<span id="l2625"></span><a href="#l2625"></a>
<span id="l2626">        if (sniMatchers.isEmpty() &amp;&amp; !noSniMatcher) {</span><a href="#l2626"></a>
<span id="l2627">            // 'null' indicates none has been set</span><a href="#l2627"></a>
<span id="l2628">            params.setSNIMatchers(null);</span><a href="#l2628"></a>
<span id="l2629">        } else {</span><a href="#l2629"></a>
<span id="l2630">            params.setSNIMatchers(sniMatchers);</span><a href="#l2630"></a>
<span id="l2631">        }</span><a href="#l2631"></a>
<span id="l2632"></span><a href="#l2632"></a>
<span id="l2633">        if (serverNames.isEmpty() &amp;&amp; !noSniExtension) {</span><a href="#l2633"></a>
<span id="l2634">            // 'null' indicates none has been set</span><a href="#l2634"></a>
<span id="l2635">            params.setServerNames(null);</span><a href="#l2635"></a>
<span id="l2636">        } else {</span><a href="#l2636"></a>
<span id="l2637">            params.setServerNames(serverNames);</span><a href="#l2637"></a>
<span id="l2638">        }</span><a href="#l2638"></a>
<span id="l2639"></span><a href="#l2639"></a>
<span id="l2640">        params.setUseCipherSuitesOrder(preferLocalCipherSuites);</span><a href="#l2640"></a>
<span id="l2641">        params.setApplicationProtocols(applicationProtocols);</span><a href="#l2641"></a>
<span id="l2642"></span><a href="#l2642"></a>
<span id="l2643">        return params;</span><a href="#l2643"></a>
<span id="l2644">    }</span><a href="#l2644"></a>
<span id="l2645"></span><a href="#l2645"></a>
<span id="l2646">    /**</span><a href="#l2646"></a>
<span id="l2647">     * Applies SSLParameters to this socket.</span><a href="#l2647"></a>
<span id="l2648">     */</span><a href="#l2648"></a>
<span id="l2649">    @Override</span><a href="#l2649"></a>
<span id="l2650">    synchronized public void setSSLParameters(SSLParameters params) {</span><a href="#l2650"></a>
<span id="l2651">        super.setSSLParameters(params);</span><a href="#l2651"></a>
<span id="l2652"></span><a href="#l2652"></a>
<span id="l2653">        // the super implementation does not handle the following parameters</span><a href="#l2653"></a>
<span id="l2654">        identificationProtocol = params.getEndpointIdentificationAlgorithm();</span><a href="#l2654"></a>
<span id="l2655">        algorithmConstraints = params.getAlgorithmConstraints();</span><a href="#l2655"></a>
<span id="l2656">        preferLocalCipherSuites = params.getUseCipherSuitesOrder();</span><a href="#l2656"></a>
<span id="l2657"></span><a href="#l2657"></a>
<span id="l2658">        List&lt;SNIServerName&gt; sniNames = params.getServerNames();</span><a href="#l2658"></a>
<span id="l2659">        if (sniNames != null) {</span><a href="#l2659"></a>
<span id="l2660">            noSniExtension = sniNames.isEmpty();</span><a href="#l2660"></a>
<span id="l2661">            serverNames = sniNames;</span><a href="#l2661"></a>
<span id="l2662">        }</span><a href="#l2662"></a>
<span id="l2663"></span><a href="#l2663"></a>
<span id="l2664">        Collection&lt;SNIMatcher&gt; matchers = params.getSNIMatchers();</span><a href="#l2664"></a>
<span id="l2665">        if (matchers != null) {</span><a href="#l2665"></a>
<span id="l2666">            noSniMatcher = matchers.isEmpty();</span><a href="#l2666"></a>
<span id="l2667">            sniMatchers = matchers;</span><a href="#l2667"></a>
<span id="l2668">        }</span><a href="#l2668"></a>
<span id="l2669"></span><a href="#l2669"></a>
<span id="l2670">        applicationProtocols = params.getApplicationProtocols();</span><a href="#l2670"></a>
<span id="l2671"></span><a href="#l2671"></a>
<span id="l2672">        if ((handshaker != null) &amp;&amp; !handshaker.started()) {</span><a href="#l2672"></a>
<span id="l2673">            handshaker.setIdentificationProtocol(identificationProtocol);</span><a href="#l2673"></a>
<span id="l2674">            handshaker.setAlgorithmConstraints(algorithmConstraints);</span><a href="#l2674"></a>
<span id="l2675">            handshaker.setApplicationProtocols(applicationProtocols);</span><a href="#l2675"></a>
<span id="l2676">            if (roleIsServer) {</span><a href="#l2676"></a>
<span id="l2677">                handshaker.setSNIMatchers(sniMatchers);</span><a href="#l2677"></a>
<span id="l2678">                handshaker.setUseCipherSuitesOrder(preferLocalCipherSuites);</span><a href="#l2678"></a>
<span id="l2679">            } else {</span><a href="#l2679"></a>
<span id="l2680">                handshaker.setSNIServerNames(serverNames);</span><a href="#l2680"></a>
<span id="l2681">            }</span><a href="#l2681"></a>
<span id="l2682">        }</span><a href="#l2682"></a>
<span id="l2683">    }</span><a href="#l2683"></a>
<span id="l2684"></span><a href="#l2684"></a>
<span id="l2685">    @Override</span><a href="#l2685"></a>
<span id="l2686">    public synchronized String getApplicationProtocol() {</span><a href="#l2686"></a>
<span id="l2687">        return applicationProtocol;</span><a href="#l2687"></a>
<span id="l2688">    }</span><a href="#l2688"></a>
<span id="l2689"></span><a href="#l2689"></a>
<span id="l2690">    @Override</span><a href="#l2690"></a>
<span id="l2691">    public synchronized String getHandshakeApplicationProtocol() {</span><a href="#l2691"></a>
<span id="l2692">         if ((handshaker != null) &amp;&amp; handshaker.started()) {</span><a href="#l2692"></a>
<span id="l2693">            return handshaker.getHandshakeApplicationProtocol();</span><a href="#l2693"></a>
<span id="l2694">        }</span><a href="#l2694"></a>
<span id="l2695">        return null;</span><a href="#l2695"></a>
<span id="l2696">    }</span><a href="#l2696"></a>
<span id="l2697"></span><a href="#l2697"></a>
<span id="l2698">    @Override</span><a href="#l2698"></a>
<span id="l2699">    public synchronized void setHandshakeApplicationProtocolSelector(</span><a href="#l2699"></a>
<span id="l2700">        BiFunction&lt;SSLSocket, List&lt;String&gt;, String&gt; selector) {</span><a href="#l2700"></a>
<span id="l2701">        applicationProtocolSelector = selector;</span><a href="#l2701"></a>
<span id="l2702">        if ((handshaker != null) &amp;&amp; !handshaker.activated()) {</span><a href="#l2702"></a>
<span id="l2703">            handshaker.setApplicationProtocolSelectorSSLSocket(selector);</span><a href="#l2703"></a>
<span id="l2704">        }</span><a href="#l2704"></a>
<span id="l2705">    }</span><a href="#l2705"></a>
<span id="l2706"></span><a href="#l2706"></a>
<span id="l2707">    @Override</span><a href="#l2707"></a>
<span id="l2708">    public synchronized BiFunction&lt;SSLSocket, List&lt;String&gt;, String&gt;</span><a href="#l2708"></a>
<span id="l2709">        getHandshakeApplicationProtocolSelector() {</span><a href="#l2709"></a>
<span id="l2710">        return this.applicationProtocolSelector;</span><a href="#l2710"></a>
<span id="l2711">    }</span><a href="#l2711"></a>
<span id="l2712"></span><a href="#l2712"></a>
<span id="l2713">    //</span><a href="#l2713"></a>
<span id="l2714">    // We allocate a separate thread to deliver handshake completion</span><a href="#l2714"></a>
<span id="l2715">    // events.  This ensures that the notifications don't block the</span><a href="#l2715"></a>
<span id="l2716">    // protocol state machine.</span><a href="#l2716"></a>
<span id="l2717">    //</span><a href="#l2717"></a>
<span id="l2718">    private static class NotifyHandshakeThread extends Thread {</span><a href="#l2718"></a>
<span id="l2719"></span><a href="#l2719"></a>
<span id="l2720">        private Set&lt;Map.Entry&lt;HandshakeCompletedListener,AccessControlContext&gt;&gt;</span><a href="#l2720"></a>
<span id="l2721">                targets;        // who gets notified</span><a href="#l2721"></a>
<span id="l2722">        private HandshakeCompletedEvent event;          // the notification</span><a href="#l2722"></a>
<span id="l2723"></span><a href="#l2723"></a>
<span id="l2724">        NotifyHandshakeThread(</span><a href="#l2724"></a>
<span id="l2725">            Set&lt;Map.Entry&lt;HandshakeCompletedListener,AccessControlContext&gt;&gt;</span><a href="#l2725"></a>
<span id="l2726">            entrySet, HandshakeCompletedEvent e) {</span><a href="#l2726"></a>
<span id="l2727"></span><a href="#l2727"></a>
<span id="l2728">            super(&quot;HandshakeCompletedNotify-Thread&quot;);</span><a href="#l2728"></a>
<span id="l2729">            targets = new HashSet&lt;&gt;(entrySet);          // clone the entry set</span><a href="#l2729"></a>
<span id="l2730">            event = e;</span><a href="#l2730"></a>
<span id="l2731">        }</span><a href="#l2731"></a>
<span id="l2732"></span><a href="#l2732"></a>
<span id="l2733">        @Override</span><a href="#l2733"></a>
<span id="l2734">        public void run() {</span><a href="#l2734"></a>
<span id="l2735">            // Don't need to synchronize, as it only runs in one thread.</span><a href="#l2735"></a>
<span id="l2736">            for (Map.Entry&lt;HandshakeCompletedListener,AccessControlContext&gt;</span><a href="#l2736"></a>
<span id="l2737">                entry : targets) {</span><a href="#l2737"></a>
<span id="l2738"></span><a href="#l2738"></a>
<span id="l2739">                final HandshakeCompletedListener l = entry.getKey();</span><a href="#l2739"></a>
<span id="l2740">                AccessControlContext acc = entry.getValue();</span><a href="#l2740"></a>
<span id="l2741">                AccessController.doPrivileged(new PrivilegedAction&lt;Void&gt;() {</span><a href="#l2741"></a>
<span id="l2742">                    @Override</span><a href="#l2742"></a>
<span id="l2743">                    public Void run() {</span><a href="#l2743"></a>
<span id="l2744">                        l.handshakeCompleted(event);</span><a href="#l2744"></a>
<span id="l2745">                        return null;</span><a href="#l2745"></a>
<span id="l2746">                    }</span><a href="#l2746"></a>
<span id="l2747">                }, acc);</span><a href="#l2747"></a>
<span id="l2748">            }</span><a href="#l2748"></a>
<span id="l2749">        }</span><a href="#l2749"></a>
<span id="l2750">    }</span><a href="#l2750"></a>
<span id="l2751"></span><a href="#l2751"></a>
<span id="l2752">    /**</span><a href="#l2752"></a>
<span id="l2753">     * Returns a printable representation of this end of the connection.</span><a href="#l2753"></a>
<span id="l2754">     */</span><a href="#l2754"></a>
<span id="l2755">    @Override</span><a href="#l2755"></a>
<span id="l2756">    public String toString() {</span><a href="#l2756"></a>
<span id="l2757">        StringBuffer retval = new StringBuffer(80);</span><a href="#l2757"></a>
<span id="l2758"></span><a href="#l2758"></a>
<span id="l2759">        retval.append(Integer.toHexString(hashCode()));</span><a href="#l2759"></a>
<span id="l2760">        retval.append(&quot;[&quot;);</span><a href="#l2760"></a>
<span id="l2761">        retval.append(sess.getCipherSuite());</span><a href="#l2761"></a>
<span id="l2762">        retval.append(&quot;: &quot;);</span><a href="#l2762"></a>
<span id="l2763"></span><a href="#l2763"></a>
<span id="l2764">        retval.append(super.toString());</span><a href="#l2764"></a>
<span id="l2765">        retval.append(&quot;]&quot;);</span><a href="#l2765"></a>
<span id="l2766"></span><a href="#l2766"></a>
<span id="l2767">        return retval.toString();</span><a href="#l2767"></a>
<span id="l2768">    }</span><a href="#l2768"></a>
<span id="l2769">}</span><a href="#l2769"></a></pre>
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

