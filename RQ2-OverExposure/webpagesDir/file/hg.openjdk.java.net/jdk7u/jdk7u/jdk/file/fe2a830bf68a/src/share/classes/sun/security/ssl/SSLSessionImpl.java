<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk7u/jdk7u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk7u/jdk7u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk7u/jdk7u/jdk/static/mercurial.js"></script>

<title>jdk7u/jdk7u/jdk: fe2a830bf68a src/share/classes/sun/security/ssl/SSLSessionImpl.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk7u/jdk7u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/shortlog/fe2a830bf68a">log</a></li>
<li><a href="/jdk7u/jdk7u/jdk/graph/fe2a830bf68a">graph</a></li>
<li><a href="/jdk7u/jdk7u/jdk/tags">tags</a></li>
<li><a href="/jdk7u/jdk7u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/rev/fe2a830bf68a">changeset</a></li>
<li><a href="/jdk7u/jdk7u/jdk/file/fe2a830bf68a/src/share/classes/sun/security/ssl/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk7u/jdk7u/jdk/file/tip/src/share/classes/sun/security/ssl/SSLSessionImpl.java">latest</a></li>
<li><a href="/jdk7u/jdk7u/jdk/diff/fe2a830bf68a/src/share/classes/sun/security/ssl/SSLSessionImpl.java">diff</a></li>
<li><a href="/jdk7u/jdk7u/jdk/comparison/fe2a830bf68a/src/share/classes/sun/security/ssl/SSLSessionImpl.java">comparison</a></li>
<li><a href="/jdk7u/jdk7u/jdk/annotate/fe2a830bf68a/src/share/classes/sun/security/ssl/SSLSessionImpl.java">annotate</a></li>
<li><a href="/jdk7u/jdk7u/jdk/log/fe2a830bf68a/src/share/classes/sun/security/ssl/SSLSessionImpl.java">file log</a></li>
<li><a href="/jdk7u/jdk7u/jdk/raw-file/fe2a830bf68a/src/share/classes/sun/security/ssl/SSLSessionImpl.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/ssl/SSLSessionImpl.java @ 9001:fe2a830bf68a</h3>

<form class="search" action="/jdk7u/jdk7u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk7u/jdk7u/jdk/help/revsets">revset expression</a>.</div>
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
 <td class="author"><a href="/jdk7u/jdk7u/jdk/file/e2125a9b0933/src/share/classes/sun/security/ssl/SSLSessionImpl.java">e2125a9b0933</a> </td>
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
<span id="l2"> * Copyright (c) 1996, 2018, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l31">import java.util.Enumeration;</span><a href="#l31"></a>
<span id="l32">import java.util.Hashtable;</span><a href="#l32"></a>
<span id="l33">import java.util.Vector;</span><a href="#l33"></a>
<span id="l34">import java.util.Arrays;</span><a href="#l34"></a>
<span id="l35">import java.util.Collection;</span><a href="#l35"></a>
<span id="l36"></span><a href="#l36"></a>
<span id="l37">import java.security.Principal;</span><a href="#l37"></a>
<span id="l38">import java.security.PrivateKey;</span><a href="#l38"></a>
<span id="l39">import java.security.SecureRandom;</span><a href="#l39"></a>
<span id="l40">import java.security.cert.X509Certificate;</span><a href="#l40"></a>
<span id="l41">import java.security.cert.CertificateEncodingException;</span><a href="#l41"></a>
<span id="l42"></span><a href="#l42"></a>
<span id="l43">import javax.crypto.SecretKey;</span><a href="#l43"></a>
<span id="l44"></span><a href="#l44"></a>
<span id="l45">import javax.net.ssl.SSLSession;</span><a href="#l45"></a>
<span id="l46">import javax.net.ssl.SSLSessionContext;</span><a href="#l46"></a>
<span id="l47">import javax.net.ssl.SSLSessionBindingListener;</span><a href="#l47"></a>
<span id="l48">import javax.net.ssl.SSLSessionBindingEvent;</span><a href="#l48"></a>
<span id="l49">import javax.net.ssl.SSLPeerUnverifiedException;</span><a href="#l49"></a>
<span id="l50">import javax.net.ssl.SSLSession;</span><a href="#l50"></a>
<span id="l51">import javax.net.ssl.SSLPermission;</span><a href="#l51"></a>
<span id="l52">import javax.net.ssl.SSLException;</span><a href="#l52"></a>
<span id="l53">import javax.net.ssl.ExtendedSSLSession;</span><a href="#l53"></a>
<span id="l54"></span><a href="#l54"></a>
<span id="l55">import javax.security.auth.x500.X500Principal;</span><a href="#l55"></a>
<span id="l56"></span><a href="#l56"></a>
<span id="l57">import static sun.security.ssl.CipherSuite.*;</span><a href="#l57"></a>
<span id="l58">import static sun.security.ssl.CipherSuite.KeyExchange.*;</span><a href="#l58"></a>
<span id="l59"></span><a href="#l59"></a>
<span id="l60">/**</span><a href="#l60"></a>
<span id="l61"> * Implements the SSL session interface, and exposes the session context</span><a href="#l61"></a>
<span id="l62"> * which is maintained by SSL servers.</span><a href="#l62"></a>
<span id="l63"> *</span><a href="#l63"></a>
<span id="l64"> * &lt;P&gt; Servers have the ability to manage the sessions associated with</span><a href="#l64"></a>
<span id="l65"> * their authentication context(s).  They can do this by enumerating the</span><a href="#l65"></a>
<span id="l66"> * IDs of the sessions which are cached, examining those sessions, and then</span><a href="#l66"></a>
<span id="l67"> * perhaps invalidating a given session so that it can't be used again.</span><a href="#l67"></a>
<span id="l68"> * If servers do not explicitly manage the cache, sessions will linger</span><a href="#l68"></a>
<span id="l69"> * until memory is low enough that the runtime environment purges cache</span><a href="#l69"></a>
<span id="l70"> * entries automatically to reclaim space.</span><a href="#l70"></a>
<span id="l71"> *</span><a href="#l71"></a>
<span id="l72"> * &lt;P&gt;&lt;em&gt; The only reason this class is not package-private is that</span><a href="#l72"></a>
<span id="l73"> * there's no other public way to get at the server session context which</span><a href="#l73"></a>
<span id="l74"> * is associated with any given authentication context. &lt;/em&gt;</span><a href="#l74"></a>
<span id="l75"> *</span><a href="#l75"></a>
<span id="l76"> * @author David Brownell</span><a href="#l76"></a>
<span id="l77"> */</span><a href="#l77"></a>
<span id="l78">final class SSLSessionImpl extends ExtendedSSLSession {</span><a href="#l78"></a>
<span id="l79"></span><a href="#l79"></a>
<span id="l80">    // compression methods</span><a href="#l80"></a>
<span id="l81">    private static final byte           compression_null = 0;</span><a href="#l81"></a>
<span id="l82"></span><a href="#l82"></a>
<span id="l83">    /*</span><a href="#l83"></a>
<span id="l84">     * The state of a single session, as described in section 7.1</span><a href="#l84"></a>
<span id="l85">     * of the SSLv3 spec.</span><a href="#l85"></a>
<span id="l86">     */</span><a href="#l86"></a>
<span id="l87">    private final ProtocolVersion       protocolVersion;</span><a href="#l87"></a>
<span id="l88">    private final SessionId             sessionId;</span><a href="#l88"></a>
<span id="l89">    private X509Certificate[]   peerCerts;</span><a href="#l89"></a>
<span id="l90">    private byte                compressionMethod;</span><a href="#l90"></a>
<span id="l91">    private CipherSuite         cipherSuite;</span><a href="#l91"></a>
<span id="l92">    private SecretKey           masterSecret;</span><a href="#l92"></a>
<span id="l93">    private final boolean       useExtendedMasterSecret;</span><a href="#l93"></a>
<span id="l94"></span><a href="#l94"></a>
<span id="l95">    /*</span><a href="#l95"></a>
<span id="l96">     * Information not part of the SSLv3 protocol spec, but used</span><a href="#l96"></a>
<span id="l97">     * to support session management policies.</span><a href="#l97"></a>
<span id="l98">     */</span><a href="#l98"></a>
<span id="l99">    private final long          creationTime = System.currentTimeMillis();</span><a href="#l99"></a>
<span id="l100">    private long                lastUsedTime = 0;</span><a href="#l100"></a>
<span id="l101">    private final String        host;</span><a href="#l101"></a>
<span id="l102">    private final int           port;</span><a href="#l102"></a>
<span id="l103">    private SSLSessionContextImpl       context;</span><a href="#l103"></a>
<span id="l104">    private int                 sessionCount;</span><a href="#l104"></a>
<span id="l105">    private boolean             invalidated;</span><a href="#l105"></a>
<span id="l106">    private X509Certificate[]   localCerts;</span><a href="#l106"></a>
<span id="l107">    private PrivateKey          localPrivateKey;</span><a href="#l107"></a>
<span id="l108">    private String[]            localSupportedSignAlgs;</span><a href="#l108"></a>
<span id="l109">    private String[]            peerSupportedSignAlgs;</span><a href="#l109"></a>
<span id="l110"></span><a href="#l110"></a>
<span id="l111">    // Principals for non-certificate based cipher suites</span><a href="#l111"></a>
<span id="l112">    private Principal peerPrincipal;</span><a href="#l112"></a>
<span id="l113">    private Principal localPrincipal;</span><a href="#l113"></a>
<span id="l114"></span><a href="#l114"></a>
<span id="l115">    // The endpoint identification algorithm used to check certificates</span><a href="#l115"></a>
<span id="l116">    // in this session.</span><a href="#l116"></a>
<span id="l117">    private final String              endpointIdentificationAlgorithm;</span><a href="#l117"></a>
<span id="l118"></span><a href="#l118"></a>
<span id="l119">    /*</span><a href="#l119"></a>
<span id="l120">     * Is the session currently re-established with a session-resumption</span><a href="#l120"></a>
<span id="l121">     * abbreviated initial handshake?</span><a href="#l121"></a>
<span id="l122">     *</span><a href="#l122"></a>
<span id="l123">     * Note that currently we only set this variable in client side.</span><a href="#l123"></a>
<span id="l124">     */</span><a href="#l124"></a>
<span id="l125">    private boolean isSessionResumption = false;</span><a href="#l125"></a>
<span id="l126"></span><a href="#l126"></a>
<span id="l127">    /*</span><a href="#l127"></a>
<span id="l128">     * We count session creations, eventually for statistical data but</span><a href="#l128"></a>
<span id="l129">     * also since counters make shorter debugging IDs than the big ones</span><a href="#l129"></a>
<span id="l130">     * we use in the protocol for uniqueness-over-time.</span><a href="#l130"></a>
<span id="l131">     */</span><a href="#l131"></a>
<span id="l132">    private static volatile int counter = 0;</span><a href="#l132"></a>
<span id="l133"></span><a href="#l133"></a>
<span id="l134">    /*</span><a href="#l134"></a>
<span id="l135">     * Use of session caches is globally enabled/disabled.</span><a href="#l135"></a>
<span id="l136">     */</span><a href="#l136"></a>
<span id="l137">    private static boolean      defaultRejoinable = true;</span><a href="#l137"></a>
<span id="l138"></span><a href="#l138"></a>
<span id="l139">    /* Class and subclass dynamic debugging support */</span><a href="#l139"></a>
<span id="l140">    private static final Debug debug = Debug.getInstance(&quot;ssl&quot;);</span><a href="#l140"></a>
<span id="l141"></span><a href="#l141"></a>
<span id="l142">    /*</span><a href="#l142"></a>
<span id="l143">     * Create a new non-rejoinable session, using the default (null)</span><a href="#l143"></a>
<span id="l144">     * cipher spec.  This constructor returns a session which could</span><a href="#l144"></a>
<span id="l145">     * be used either by a client or by a server, as a connection is</span><a href="#l145"></a>
<span id="l146">     * first opened and before handshaking begins.</span><a href="#l146"></a>
<span id="l147">     */</span><a href="#l147"></a>
<span id="l148">    SSLSessionImpl() {</span><a href="#l148"></a>
<span id="l149">        this(ProtocolVersion.NONE, CipherSuite.C_NULL, null,</span><a href="#l149"></a>
<span id="l150">            new SessionId(false, null), null, -1, false, null);</span><a href="#l150"></a>
<span id="l151">    }</span><a href="#l151"></a>
<span id="l152"></span><a href="#l152"></a>
<span id="l153">    /*</span><a href="#l153"></a>
<span id="l154">     * Create a new session, using a given cipher spec.  This will</span><a href="#l154"></a>
<span id="l155">     * be rejoinable if session caching is enabled; the constructor</span><a href="#l155"></a>
<span id="l156">     * is intended mostly for use by serves.</span><a href="#l156"></a>
<span id="l157">     */</span><a href="#l157"></a>
<span id="l158">    SSLSessionImpl(ProtocolVersion protocolVersion, CipherSuite cipherSuite,</span><a href="#l158"></a>
<span id="l159">            Collection&lt;SignatureAndHashAlgorithm&gt; algorithms,</span><a href="#l159"></a>
<span id="l160">            SecureRandom generator, String host, int port,</span><a href="#l160"></a>
<span id="l161">            boolean useExtendedMasterSecret, String endpointIdAlgorithm) {</span><a href="#l161"></a>
<span id="l162">        this(protocolVersion, cipherSuite, algorithms,</span><a href="#l162"></a>
<span id="l163">             new SessionId(defaultRejoinable, generator), host, port,</span><a href="#l163"></a>
<span id="l164">             useExtendedMasterSecret, endpointIdAlgorithm);</span><a href="#l164"></a>
<span id="l165">    }</span><a href="#l165"></a>
<span id="l166"></span><a href="#l166"></a>
<span id="l167">    /*</span><a href="#l167"></a>
<span id="l168">     * Record a new session, using a given cipher spec and session ID.</span><a href="#l168"></a>
<span id="l169">     */</span><a href="#l169"></a>
<span id="l170">    SSLSessionImpl(ProtocolVersion protocolVersion, CipherSuite cipherSuite,</span><a href="#l170"></a>
<span id="l171">            Collection&lt;SignatureAndHashAlgorithm&gt; algorithms,</span><a href="#l171"></a>
<span id="l172">            SessionId id, String host, int port,</span><a href="#l172"></a>
<span id="l173">            boolean useExtendedMasterSecret,</span><a href="#l173"></a>
<span id="l174">            String endpointIdAlgorithm){</span><a href="#l174"></a>
<span id="l175">        this.protocolVersion = protocolVersion;</span><a href="#l175"></a>
<span id="l176">        sessionId = id;</span><a href="#l176"></a>
<span id="l177">        peerCerts = null;</span><a href="#l177"></a>
<span id="l178">        compressionMethod = compression_null;</span><a href="#l178"></a>
<span id="l179">        this.cipherSuite = cipherSuite;</span><a href="#l179"></a>
<span id="l180">        masterSecret = null;</span><a href="#l180"></a>
<span id="l181">        this.host = host;</span><a href="#l181"></a>
<span id="l182">        this.port = port;</span><a href="#l182"></a>
<span id="l183">        sessionCount = ++counter;</span><a href="#l183"></a>
<span id="l184">        localSupportedSignAlgs =</span><a href="#l184"></a>
<span id="l185">            SignatureAndHashAlgorithm.getAlgorithmNames(algorithms);</span><a href="#l185"></a>
<span id="l186">        this.useExtendedMasterSecret = useExtendedMasterSecret;</span><a href="#l186"></a>
<span id="l187">        this.endpointIdentificationAlgorithm = endpointIdAlgorithm;</span><a href="#l187"></a>
<span id="l188"></span><a href="#l188"></a>
<span id="l189">        if (debug != null &amp;&amp; Debug.isOn(&quot;session&quot;)) {</span><a href="#l189"></a>
<span id="l190">            System.out.println(&quot;%% Initialized:  &quot; + this);</span><a href="#l190"></a>
<span id="l191">        }</span><a href="#l191"></a>
<span id="l192">    }</span><a href="#l192"></a>
<span id="l193"></span><a href="#l193"></a>
<span id="l194">    void setMasterSecret(SecretKey secret) {</span><a href="#l194"></a>
<span id="l195">        if (masterSecret == null) {</span><a href="#l195"></a>
<span id="l196">            masterSecret = secret;</span><a href="#l196"></a>
<span id="l197">        } else {</span><a href="#l197"></a>
<span id="l198">            throw new RuntimeException(&quot;setMasterSecret() error&quot;);</span><a href="#l198"></a>
<span id="l199">        }</span><a href="#l199"></a>
<span id="l200">    }</span><a href="#l200"></a>
<span id="l201"></span><a href="#l201"></a>
<span id="l202">    /**</span><a href="#l202"></a>
<span id="l203">     * Returns the master secret ... treat with extreme caution!</span><a href="#l203"></a>
<span id="l204">     */</span><a href="#l204"></a>
<span id="l205">    SecretKey getMasterSecret() {</span><a href="#l205"></a>
<span id="l206">        return masterSecret;</span><a href="#l206"></a>
<span id="l207">    }</span><a href="#l207"></a>
<span id="l208"></span><a href="#l208"></a>
<span id="l209">    boolean getUseExtendedMasterSecret() {</span><a href="#l209"></a>
<span id="l210">        return useExtendedMasterSecret;</span><a href="#l210"></a>
<span id="l211">    }</span><a href="#l211"></a>
<span id="l212"></span><a href="#l212"></a>
<span id="l213">    void setPeerCertificates(X509Certificate[] peer) {</span><a href="#l213"></a>
<span id="l214">        if (peerCerts == null) {</span><a href="#l214"></a>
<span id="l215">            peerCerts = peer;</span><a href="#l215"></a>
<span id="l216">        }</span><a href="#l216"></a>
<span id="l217">    }</span><a href="#l217"></a>
<span id="l218"></span><a href="#l218"></a>
<span id="l219">    void setLocalCertificates(X509Certificate[] local) {</span><a href="#l219"></a>
<span id="l220">        localCerts = local;</span><a href="#l220"></a>
<span id="l221">    }</span><a href="#l221"></a>
<span id="l222"></span><a href="#l222"></a>
<span id="l223">    void setLocalPrivateKey(PrivateKey privateKey) {</span><a href="#l223"></a>
<span id="l224">        localPrivateKey = privateKey;</span><a href="#l224"></a>
<span id="l225">    }</span><a href="#l225"></a>
<span id="l226"></span><a href="#l226"></a>
<span id="l227">    void setPeerSupportedSignatureAlgorithms(</span><a href="#l227"></a>
<span id="l228">            Collection&lt;SignatureAndHashAlgorithm&gt; algorithms) {</span><a href="#l228"></a>
<span id="l229">        peerSupportedSignAlgs =</span><a href="#l229"></a>
<span id="l230">            SignatureAndHashAlgorithm.getAlgorithmNames(algorithms);</span><a href="#l230"></a>
<span id="l231">    }</span><a href="#l231"></a>
<span id="l232"></span><a href="#l232"></a>
<span id="l233">    /**</span><a href="#l233"></a>
<span id="l234">     * Set the peer principal.</span><a href="#l234"></a>
<span id="l235">     */</span><a href="#l235"></a>
<span id="l236">    void setPeerPrincipal(Principal principal) {</span><a href="#l236"></a>
<span id="l237">        if (peerPrincipal == null) {</span><a href="#l237"></a>
<span id="l238">            peerPrincipal = principal;</span><a href="#l238"></a>
<span id="l239">        }</span><a href="#l239"></a>
<span id="l240">    }</span><a href="#l240"></a>
<span id="l241"></span><a href="#l241"></a>
<span id="l242">    /**</span><a href="#l242"></a>
<span id="l243">     * Set the local principal.</span><a href="#l243"></a>
<span id="l244">     */</span><a href="#l244"></a>
<span id="l245">    void setLocalPrincipal(Principal principal) {</span><a href="#l245"></a>
<span id="l246">        localPrincipal = principal;</span><a href="#l246"></a>
<span id="l247">    }</span><a href="#l247"></a>
<span id="l248"></span><a href="#l248"></a>
<span id="l249">    String getEndpointIdentificationAlgorithm() {</span><a href="#l249"></a>
<span id="l250">        return this.endpointIdentificationAlgorithm;</span><a href="#l250"></a>
<span id="l251">    }</span><a href="#l251"></a>
<span id="l252"></span><a href="#l252"></a>
<span id="l253">    /**</span><a href="#l253"></a>
<span id="l254">     * Returns true iff this session may be resumed ... sessions are</span><a href="#l254"></a>
<span id="l255">     * usually resumable.  Security policies may suggest otherwise,</span><a href="#l255"></a>
<span id="l256">     * for example sessions that haven't been used for a while (say,</span><a href="#l256"></a>
<span id="l257">     * a working day) won't be resumable, and sessions might have a</span><a href="#l257"></a>
<span id="l258">     * maximum lifetime in any case.</span><a href="#l258"></a>
<span id="l259">     */</span><a href="#l259"></a>
<span id="l260">    boolean isRejoinable() {</span><a href="#l260"></a>
<span id="l261">        return sessionId != null &amp;&amp; sessionId.length() != 0 &amp;&amp;</span><a href="#l261"></a>
<span id="l262">            !invalidated &amp;&amp; isLocalAuthenticationValid();</span><a href="#l262"></a>
<span id="l263">    }</span><a href="#l263"></a>
<span id="l264"></span><a href="#l264"></a>
<span id="l265">    public synchronized boolean isValid() {</span><a href="#l265"></a>
<span id="l266">        return isRejoinable();</span><a href="#l266"></a>
<span id="l267">    }</span><a href="#l267"></a>
<span id="l268"></span><a href="#l268"></a>
<span id="l269">    /**</span><a href="#l269"></a>
<span id="l270">     * Check if the authentication used when establishing this session</span><a href="#l270"></a>
<span id="l271">     * is still valid. Returns true if no authentication was used</span><a href="#l271"></a>
<span id="l272">     */</span><a href="#l272"></a>
<span id="l273">    boolean isLocalAuthenticationValid() {</span><a href="#l273"></a>
<span id="l274">        if (localPrivateKey != null) {</span><a href="#l274"></a>
<span id="l275">            try {</span><a href="#l275"></a>
<span id="l276">                // if the private key is no longer valid, getAlgorithm()</span><a href="#l276"></a>
<span id="l277">                // should throw an exception</span><a href="#l277"></a>
<span id="l278">                // (e.g. Smartcard has been removed from the reader)</span><a href="#l278"></a>
<span id="l279">                localPrivateKey.getAlgorithm();</span><a href="#l279"></a>
<span id="l280">            } catch (Exception e) {</span><a href="#l280"></a>
<span id="l281">                invalidate();</span><a href="#l281"></a>
<span id="l282">                return false;</span><a href="#l282"></a>
<span id="l283">            }</span><a href="#l283"></a>
<span id="l284">        }</span><a href="#l284"></a>
<span id="l285">        return true;</span><a href="#l285"></a>
<span id="l286">    }</span><a href="#l286"></a>
<span id="l287"></span><a href="#l287"></a>
<span id="l288">    /**</span><a href="#l288"></a>
<span id="l289">     * Returns the ID for this session.  The ID is fixed for the</span><a href="#l289"></a>
<span id="l290">     * duration of the session; neither it, nor its value, changes.</span><a href="#l290"></a>
<span id="l291">     */</span><a href="#l291"></a>
<span id="l292">    public byte[] getId() {</span><a href="#l292"></a>
<span id="l293">        return sessionId.getId();</span><a href="#l293"></a>
<span id="l294">    }</span><a href="#l294"></a>
<span id="l295"></span><a href="#l295"></a>
<span id="l296">    /**</span><a href="#l296"></a>
<span id="l297">     * For server sessions, this returns the set of sessions which</span><a href="#l297"></a>
<span id="l298">     * are currently valid in this process.  For client sessions,</span><a href="#l298"></a>
<span id="l299">     * this returns null.</span><a href="#l299"></a>
<span id="l300">     */</span><a href="#l300"></a>
<span id="l301">    public SSLSessionContext getSessionContext() {</span><a href="#l301"></a>
<span id="l302">        /*</span><a href="#l302"></a>
<span id="l303">         * An interim security policy until we can do something</span><a href="#l303"></a>
<span id="l304">         * more specific in 1.2. Only allow trusted code (code which</span><a href="#l304"></a>
<span id="l305">         * can set system properties) to get an</span><a href="#l305"></a>
<span id="l306">         * SSLSessionContext. This is to limit the ability of code to</span><a href="#l306"></a>
<span id="l307">         * look up specific sessions or enumerate over them. Otherwise,</span><a href="#l307"></a>
<span id="l308">         * code can only get session objects from successful SSL</span><a href="#l308"></a>
<span id="l309">         * connections which implies that they must have had permission</span><a href="#l309"></a>
<span id="l310">         * to make the network connection in the first place.</span><a href="#l310"></a>
<span id="l311">         */</span><a href="#l311"></a>
<span id="l312">        SecurityManager sm;</span><a href="#l312"></a>
<span id="l313">        if ((sm = System.getSecurityManager()) != null) {</span><a href="#l313"></a>
<span id="l314">            sm.checkPermission(new SSLPermission(&quot;getSSLSessionContext&quot;));</span><a href="#l314"></a>
<span id="l315">        }</span><a href="#l315"></a>
<span id="l316"></span><a href="#l316"></a>
<span id="l317">        return context;</span><a href="#l317"></a>
<span id="l318">    }</span><a href="#l318"></a>
<span id="l319"></span><a href="#l319"></a>
<span id="l320"></span><a href="#l320"></a>
<span id="l321">    SessionId getSessionId() {</span><a href="#l321"></a>
<span id="l322">        return sessionId;</span><a href="#l322"></a>
<span id="l323">    }</span><a href="#l323"></a>
<span id="l324"></span><a href="#l324"></a>
<span id="l325"></span><a href="#l325"></a>
<span id="l326">    /**</span><a href="#l326"></a>
<span id="l327">     * Returns the cipher spec in use on this session</span><a href="#l327"></a>
<span id="l328">     */</span><a href="#l328"></a>
<span id="l329">    CipherSuite getSuite() {</span><a href="#l329"></a>
<span id="l330">        return cipherSuite;</span><a href="#l330"></a>
<span id="l331">    }</span><a href="#l331"></a>
<span id="l332"></span><a href="#l332"></a>
<span id="l333">    /**</span><a href="#l333"></a>
<span id="l334">     * Resets the cipher spec in use on this session</span><a href="#l334"></a>
<span id="l335">     */</span><a href="#l335"></a>
<span id="l336">    void setSuite(CipherSuite suite) {</span><a href="#l336"></a>
<span id="l337">       cipherSuite = suite;</span><a href="#l337"></a>
<span id="l338"></span><a href="#l338"></a>
<span id="l339">       if (debug != null &amp;&amp; Debug.isOn(&quot;session&quot;)) {</span><a href="#l339"></a>
<span id="l340">           System.out.println(&quot;%% Negotiating:  &quot; + this);</span><a href="#l340"></a>
<span id="l341">       }</span><a href="#l341"></a>
<span id="l342">    }</span><a href="#l342"></a>
<span id="l343"></span><a href="#l343"></a>
<span id="l344">    /**</span><a href="#l344"></a>
<span id="l345">     * Return true if the session is currently re-established with a</span><a href="#l345"></a>
<span id="l346">     * session-resumption abreviated initial handshake.</span><a href="#l346"></a>
<span id="l347">     */</span><a href="#l347"></a>
<span id="l348">    boolean isSessionResumption() {</span><a href="#l348"></a>
<span id="l349">        return isSessionResumption;</span><a href="#l349"></a>
<span id="l350">    }</span><a href="#l350"></a>
<span id="l351"></span><a href="#l351"></a>
<span id="l352">    /**</span><a href="#l352"></a>
<span id="l353">     * Resets whether the session is re-established with a session-resumption</span><a href="#l353"></a>
<span id="l354">     * abbreviated initial handshake.</span><a href="#l354"></a>
<span id="l355">     */</span><a href="#l355"></a>
<span id="l356">    void setAsSessionResumption(boolean flag) {</span><a href="#l356"></a>
<span id="l357">        isSessionResumption = flag;</span><a href="#l357"></a>
<span id="l358">    }</span><a href="#l358"></a>
<span id="l359"></span><a href="#l359"></a>
<span id="l360">    /**</span><a href="#l360"></a>
<span id="l361">     * Returns the name of the cipher suite in use on this session</span><a href="#l361"></a>
<span id="l362">     */</span><a href="#l362"></a>
<span id="l363">    public String getCipherSuite() {</span><a href="#l363"></a>
<span id="l364">        return getSuite().name;</span><a href="#l364"></a>
<span id="l365">    }</span><a href="#l365"></a>
<span id="l366"></span><a href="#l366"></a>
<span id="l367">    ProtocolVersion getProtocolVersion() {</span><a href="#l367"></a>
<span id="l368">        return protocolVersion;</span><a href="#l368"></a>
<span id="l369">    }</span><a href="#l369"></a>
<span id="l370"></span><a href="#l370"></a>
<span id="l371">    /**</span><a href="#l371"></a>
<span id="l372">     * Returns the standard name of the protocol in use on this session</span><a href="#l372"></a>
<span id="l373">     */</span><a href="#l373"></a>
<span id="l374">    public String getProtocol() {</span><a href="#l374"></a>
<span id="l375">        return getProtocolVersion().name;</span><a href="#l375"></a>
<span id="l376">    }</span><a href="#l376"></a>
<span id="l377"></span><a href="#l377"></a>
<span id="l378">    /**</span><a href="#l378"></a>
<span id="l379">     * Returns the compression technique used in this session</span><a href="#l379"></a>
<span id="l380">     */</span><a href="#l380"></a>
<span id="l381">    byte getCompression() {</span><a href="#l381"></a>
<span id="l382">        return compressionMethod;</span><a href="#l382"></a>
<span id="l383">    }</span><a href="#l383"></a>
<span id="l384"></span><a href="#l384"></a>
<span id="l385">    /**</span><a href="#l385"></a>
<span id="l386">     * Returns the hashcode for this session</span><a href="#l386"></a>
<span id="l387">     */</span><a href="#l387"></a>
<span id="l388">    public int hashCode() {</span><a href="#l388"></a>
<span id="l389">        return sessionId.hashCode();</span><a href="#l389"></a>
<span id="l390">    }</span><a href="#l390"></a>
<span id="l391"></span><a href="#l391"></a>
<span id="l392"></span><a href="#l392"></a>
<span id="l393">    /**</span><a href="#l393"></a>
<span id="l394">     * Returns true if sessions have same ids, false otherwise.</span><a href="#l394"></a>
<span id="l395">     */</span><a href="#l395"></a>
<span id="l396">    public boolean equals(Object obj) {</span><a href="#l396"></a>
<span id="l397"></span><a href="#l397"></a>
<span id="l398">        if (obj == this) {</span><a href="#l398"></a>
<span id="l399">            return true;</span><a href="#l399"></a>
<span id="l400">        }</span><a href="#l400"></a>
<span id="l401"></span><a href="#l401"></a>
<span id="l402">        if (obj instanceof SSLSessionImpl) {</span><a href="#l402"></a>
<span id="l403">            SSLSessionImpl sess = (SSLSessionImpl) obj;</span><a href="#l403"></a>
<span id="l404">            return (sessionId != null) &amp;&amp; (sessionId.equals(</span><a href="#l404"></a>
<span id="l405">                        sess.getSessionId()));</span><a href="#l405"></a>
<span id="l406">        }</span><a href="#l406"></a>
<span id="l407"></span><a href="#l407"></a>
<span id="l408">        return false;</span><a href="#l408"></a>
<span id="l409">    }</span><a href="#l409"></a>
<span id="l410"></span><a href="#l410"></a>
<span id="l411"></span><a href="#l411"></a>
<span id="l412">    /**</span><a href="#l412"></a>
<span id="l413">     * Return the cert chain presented by the peer in the</span><a href="#l413"></a>
<span id="l414">     * java.security.cert format.</span><a href="#l414"></a>
<span id="l415">     * Note: This method can be used only when using certificate-based</span><a href="#l415"></a>
<span id="l416">     * cipher suites; using it with non-certificate-based cipher suites,</span><a href="#l416"></a>
<span id="l417">     * such as Kerberos, will throw an SSLPeerUnverifiedException.</span><a href="#l417"></a>
<span id="l418">     *</span><a href="#l418"></a>
<span id="l419">     * @return array of peer X.509 certs, with the peer's own cert</span><a href="#l419"></a>
<span id="l420">     *  first in the chain, and with the &quot;root&quot; CA last.</span><a href="#l420"></a>
<span id="l421">     */</span><a href="#l421"></a>
<span id="l422">    public java.security.cert.Certificate[] getPeerCertificates()</span><a href="#l422"></a>
<span id="l423">            throws SSLPeerUnverifiedException {</span><a href="#l423"></a>
<span id="l424">        //</span><a href="#l424"></a>
<span id="l425">        // clone to preserve integrity of session ... caller can't</span><a href="#l425"></a>
<span id="l426">        // change record of peer identity even by accident, much</span><a href="#l426"></a>
<span id="l427">        // less do it intentionally.</span><a href="#l427"></a>
<span id="l428">        //</span><a href="#l428"></a>
<span id="l429">        if ((cipherSuite.keyExchange == K_KRB5) ||</span><a href="#l429"></a>
<span id="l430">            (cipherSuite.keyExchange == K_KRB5_EXPORT)) {</span><a href="#l430"></a>
<span id="l431">            throw new SSLPeerUnverifiedException(&quot;no certificates expected&quot;</span><a href="#l431"></a>
<span id="l432">                        + &quot; for Kerberos cipher suites&quot;);</span><a href="#l432"></a>
<span id="l433">        }</span><a href="#l433"></a>
<span id="l434">        if (peerCerts == null) {</span><a href="#l434"></a>
<span id="l435">            throw new SSLPeerUnverifiedException(&quot;peer not authenticated&quot;);</span><a href="#l435"></a>
<span id="l436">        }</span><a href="#l436"></a>
<span id="l437">        // Certs are immutable objects, therefore we don't clone them.</span><a href="#l437"></a>
<span id="l438">        // But do need to clone the array, so that nothing is inserted</span><a href="#l438"></a>
<span id="l439">        // into peerCerts.</span><a href="#l439"></a>
<span id="l440">        return (java.security.cert.Certificate[])peerCerts.clone();</span><a href="#l440"></a>
<span id="l441">    }</span><a href="#l441"></a>
<span id="l442"></span><a href="#l442"></a>
<span id="l443">    /**</span><a href="#l443"></a>
<span id="l444">     * Return the cert chain presented to the peer in the</span><a href="#l444"></a>
<span id="l445">     * java.security.cert format.</span><a href="#l445"></a>
<span id="l446">     * Note: This method is useful only when using certificate-based</span><a href="#l446"></a>
<span id="l447">     * cipher suites.</span><a href="#l447"></a>
<span id="l448">     *</span><a href="#l448"></a>
<span id="l449">     * @return array of peer X.509 certs, with the peer's own cert</span><a href="#l449"></a>
<span id="l450">     *  first in the chain, and with the &quot;root&quot; CA last.</span><a href="#l450"></a>
<span id="l451">     */</span><a href="#l451"></a>
<span id="l452">    public java.security.cert.Certificate[] getLocalCertificates() {</span><a href="#l452"></a>
<span id="l453">        //</span><a href="#l453"></a>
<span id="l454">        // clone to preserve integrity of session ... caller can't</span><a href="#l454"></a>
<span id="l455">        // change record of peer identity even by accident, much</span><a href="#l455"></a>
<span id="l456">        // less do it intentionally.</span><a href="#l456"></a>
<span id="l457">        return (localCerts == null ? null :</span><a href="#l457"></a>
<span id="l458">            (java.security.cert.Certificate[])localCerts.clone());</span><a href="#l458"></a>
<span id="l459">    }</span><a href="#l459"></a>
<span id="l460"></span><a href="#l460"></a>
<span id="l461">    /**</span><a href="#l461"></a>
<span id="l462">     * Return the cert chain presented by the peer in the</span><a href="#l462"></a>
<span id="l463">     * javax.security.cert format.</span><a href="#l463"></a>
<span id="l464">     * Note: This method can be used only when using certificate-based</span><a href="#l464"></a>
<span id="l465">     * cipher suites; using it with non-certificate-based cipher suites,</span><a href="#l465"></a>
<span id="l466">     * such as Kerberos, will throw an SSLPeerUnverifiedException.</span><a href="#l466"></a>
<span id="l467">     *</span><a href="#l467"></a>
<span id="l468">     * @return array of peer X.509 certs, with the peer's own cert</span><a href="#l468"></a>
<span id="l469">     *  first in the chain, and with the &quot;root&quot; CA last.</span><a href="#l469"></a>
<span id="l470">     */</span><a href="#l470"></a>
<span id="l471">    public javax.security.cert.X509Certificate[] getPeerCertificateChain()</span><a href="#l471"></a>
<span id="l472">            throws SSLPeerUnverifiedException {</span><a href="#l472"></a>
<span id="l473">        //</span><a href="#l473"></a>
<span id="l474">        // clone to preserve integrity of session ... caller can't</span><a href="#l474"></a>
<span id="l475">        // change record of peer identity even by accident, much</span><a href="#l475"></a>
<span id="l476">        // less do it intentionally.</span><a href="#l476"></a>
<span id="l477">        //</span><a href="#l477"></a>
<span id="l478">        if ((cipherSuite.keyExchange == K_KRB5) ||</span><a href="#l478"></a>
<span id="l479">            (cipherSuite.keyExchange == K_KRB5_EXPORT)) {</span><a href="#l479"></a>
<span id="l480">            throw new SSLPeerUnverifiedException(&quot;no certificates expected&quot;</span><a href="#l480"></a>
<span id="l481">                        + &quot; for Kerberos cipher suites&quot;);</span><a href="#l481"></a>
<span id="l482">        }</span><a href="#l482"></a>
<span id="l483">        if (peerCerts == null) {</span><a href="#l483"></a>
<span id="l484">            throw new SSLPeerUnverifiedException(&quot;peer not authenticated&quot;);</span><a href="#l484"></a>
<span id="l485">        }</span><a href="#l485"></a>
<span id="l486">        javax.security.cert.X509Certificate[] certs;</span><a href="#l486"></a>
<span id="l487">        certs = new javax.security.cert.X509Certificate[peerCerts.length];</span><a href="#l487"></a>
<span id="l488">        for (int i = 0; i &lt; peerCerts.length; i++) {</span><a href="#l488"></a>
<span id="l489">            byte[] der = null;</span><a href="#l489"></a>
<span id="l490">            try {</span><a href="#l490"></a>
<span id="l491">                der = peerCerts[i].getEncoded();</span><a href="#l491"></a>
<span id="l492">                certs[i] = javax.security.cert.X509Certificate.getInstance(der);</span><a href="#l492"></a>
<span id="l493">            } catch (CertificateEncodingException e) {</span><a href="#l493"></a>
<span id="l494">                throw new SSLPeerUnverifiedException(e.getMessage());</span><a href="#l494"></a>
<span id="l495">            } catch (javax.security.cert.CertificateException e) {</span><a href="#l495"></a>
<span id="l496">                throw new SSLPeerUnverifiedException(e.getMessage());</span><a href="#l496"></a>
<span id="l497">            }</span><a href="#l497"></a>
<span id="l498">        }</span><a href="#l498"></a>
<span id="l499"></span><a href="#l499"></a>
<span id="l500">        return certs;</span><a href="#l500"></a>
<span id="l501">    }</span><a href="#l501"></a>
<span id="l502"></span><a href="#l502"></a>
<span id="l503">    /**</span><a href="#l503"></a>
<span id="l504">     * Return the cert chain presented by the peer.</span><a href="#l504"></a>
<span id="l505">     * Note: This method can be used only when using certificate-based</span><a href="#l505"></a>
<span id="l506">     * cipher suites; using it with non-certificate-based cipher suites,</span><a href="#l506"></a>
<span id="l507">     * such as Kerberos, will throw an SSLPeerUnverifiedException.</span><a href="#l507"></a>
<span id="l508">     *</span><a href="#l508"></a>
<span id="l509">     * @return array of peer X.509 certs, with the peer's own cert</span><a href="#l509"></a>
<span id="l510">     *  first in the chain, and with the &quot;root&quot; CA last.</span><a href="#l510"></a>
<span id="l511">     */</span><a href="#l511"></a>
<span id="l512">    public X509Certificate[] getCertificateChain()</span><a href="#l512"></a>
<span id="l513">            throws SSLPeerUnverifiedException {</span><a href="#l513"></a>
<span id="l514">        /*</span><a href="#l514"></a>
<span id="l515">         * clone to preserve integrity of session ... caller can't</span><a href="#l515"></a>
<span id="l516">         * change record of peer identity even by accident, much</span><a href="#l516"></a>
<span id="l517">         * less do it intentionally.</span><a href="#l517"></a>
<span id="l518">         */</span><a href="#l518"></a>
<span id="l519">        if ((cipherSuite.keyExchange == K_KRB5) ||</span><a href="#l519"></a>
<span id="l520">            (cipherSuite.keyExchange == K_KRB5_EXPORT)) {</span><a href="#l520"></a>
<span id="l521">            throw new SSLPeerUnverifiedException(&quot;no certificates expected&quot;</span><a href="#l521"></a>
<span id="l522">                        + &quot; for Kerberos cipher suites&quot;);</span><a href="#l522"></a>
<span id="l523">        }</span><a href="#l523"></a>
<span id="l524">        if (peerCerts != null) {</span><a href="#l524"></a>
<span id="l525">            return peerCerts.clone();</span><a href="#l525"></a>
<span id="l526">        } else {</span><a href="#l526"></a>
<span id="l527">            throw new SSLPeerUnverifiedException(&quot;peer not authenticated&quot;);</span><a href="#l527"></a>
<span id="l528">        }</span><a href="#l528"></a>
<span id="l529">    }</span><a href="#l529"></a>
<span id="l530"></span><a href="#l530"></a>
<span id="l531">    /**</span><a href="#l531"></a>
<span id="l532">     * Returns the identity of the peer which was established as part of</span><a href="#l532"></a>
<span id="l533">     * defining the session.</span><a href="#l533"></a>
<span id="l534">     *</span><a href="#l534"></a>
<span id="l535">     * @return the peer's principal. Returns an X500Principal of the</span><a href="#l535"></a>
<span id="l536">     * end-entity certificate for X509-based cipher suites, and</span><a href="#l536"></a>
<span id="l537">     * Principal for Kerberos cipher suites.</span><a href="#l537"></a>
<span id="l538">     *</span><a href="#l538"></a>
<span id="l539">     * @throws SSLPeerUnverifiedException if the peer's identity has not</span><a href="#l539"></a>
<span id="l540">     *          been verified</span><a href="#l540"></a>
<span id="l541">     */</span><a href="#l541"></a>
<span id="l542">    public Principal getPeerPrincipal()</span><a href="#l542"></a>
<span id="l543">                throws SSLPeerUnverifiedException</span><a href="#l543"></a>
<span id="l544">    {</span><a href="#l544"></a>
<span id="l545">        if ((cipherSuite.keyExchange == K_KRB5) ||</span><a href="#l545"></a>
<span id="l546">            (cipherSuite.keyExchange == K_KRB5_EXPORT)) {</span><a href="#l546"></a>
<span id="l547">            if (peerPrincipal == null) {</span><a href="#l547"></a>
<span id="l548">                throw new SSLPeerUnverifiedException(&quot;peer not authenticated&quot;);</span><a href="#l548"></a>
<span id="l549">            } else {</span><a href="#l549"></a>
<span id="l550">                // Eliminate dependency on KerberosPrincipal</span><a href="#l550"></a>
<span id="l551">                return peerPrincipal;</span><a href="#l551"></a>
<span id="l552">            }</span><a href="#l552"></a>
<span id="l553">        }</span><a href="#l553"></a>
<span id="l554">        if (peerCerts == null) {</span><a href="#l554"></a>
<span id="l555">            throw new SSLPeerUnverifiedException(&quot;peer not authenticated&quot;);</span><a href="#l555"></a>
<span id="l556">        }</span><a href="#l556"></a>
<span id="l557">        return peerCerts[0].getSubjectX500Principal();</span><a href="#l557"></a>
<span id="l558">    }</span><a href="#l558"></a>
<span id="l559"></span><a href="#l559"></a>
<span id="l560">    /**</span><a href="#l560"></a>
<span id="l561">     * Returns the principal that was sent to the peer during handshaking.</span><a href="#l561"></a>
<span id="l562">     *</span><a href="#l562"></a>
<span id="l563">     * @return the principal sent to the peer. Returns an X500Principal</span><a href="#l563"></a>
<span id="l564">     * of the end-entity certificate for X509-based cipher suites, and</span><a href="#l564"></a>
<span id="l565">     * Principal for Kerberos cipher suites. If no principal was</span><a href="#l565"></a>
<span id="l566">     * sent, then null is returned.</span><a href="#l566"></a>
<span id="l567">     */</span><a href="#l567"></a>
<span id="l568">    public Principal getLocalPrincipal() {</span><a href="#l568"></a>
<span id="l569"></span><a href="#l569"></a>
<span id="l570">        if ((cipherSuite.keyExchange == K_KRB5) ||</span><a href="#l570"></a>
<span id="l571">            (cipherSuite.keyExchange == K_KRB5_EXPORT)) {</span><a href="#l571"></a>
<span id="l572">                // Eliminate dependency on KerberosPrincipal</span><a href="#l572"></a>
<span id="l573">                return (localPrincipal == null ? null : localPrincipal);</span><a href="#l573"></a>
<span id="l574">        }</span><a href="#l574"></a>
<span id="l575">        return (localCerts == null ? null :</span><a href="#l575"></a>
<span id="l576">                localCerts[0].getSubjectX500Principal());</span><a href="#l576"></a>
<span id="l577">    }</span><a href="#l577"></a>
<span id="l578"></span><a href="#l578"></a>
<span id="l579">    /**</span><a href="#l579"></a>
<span id="l580">     * Returns the time this session was created.</span><a href="#l580"></a>
<span id="l581">     */</span><a href="#l581"></a>
<span id="l582">    public long getCreationTime() {</span><a href="#l582"></a>
<span id="l583">        return creationTime;</span><a href="#l583"></a>
<span id="l584">    }</span><a href="#l584"></a>
<span id="l585"></span><a href="#l585"></a>
<span id="l586">    /**</span><a href="#l586"></a>
<span id="l587">     * Returns the last time this session was used to initialize</span><a href="#l587"></a>
<span id="l588">     * a connection.</span><a href="#l588"></a>
<span id="l589">     */</span><a href="#l589"></a>
<span id="l590">    public long getLastAccessedTime() {</span><a href="#l590"></a>
<span id="l591">        return (lastUsedTime != 0) ? lastUsedTime : creationTime;</span><a href="#l591"></a>
<span id="l592">    }</span><a href="#l592"></a>
<span id="l593"></span><a href="#l593"></a>
<span id="l594">    void setLastAccessedTime(long time) {</span><a href="#l594"></a>
<span id="l595">        lastUsedTime = time;</span><a href="#l595"></a>
<span id="l596">    }</span><a href="#l596"></a>
<span id="l597"></span><a href="#l597"></a>
<span id="l598"></span><a href="#l598"></a>
<span id="l599">    /**</span><a href="#l599"></a>
<span id="l600">     * Returns the network address of the session's peer.  This</span><a href="#l600"></a>
<span id="l601">     * implementation does not insist that connections between</span><a href="#l601"></a>
<span id="l602">     * different ports on the same host must necessarily belong</span><a href="#l602"></a>
<span id="l603">     * to different sessions, though that is of course allowed.</span><a href="#l603"></a>
<span id="l604">     */</span><a href="#l604"></a>
<span id="l605">    public InetAddress getPeerAddress() {</span><a href="#l605"></a>
<span id="l606">        try {</span><a href="#l606"></a>
<span id="l607">            return InetAddress.getByName(host);</span><a href="#l607"></a>
<span id="l608">        } catch (java.net.UnknownHostException e) {</span><a href="#l608"></a>
<span id="l609">            return null;</span><a href="#l609"></a>
<span id="l610">        }</span><a href="#l610"></a>
<span id="l611">    }</span><a href="#l611"></a>
<span id="l612"></span><a href="#l612"></a>
<span id="l613">    public String getPeerHost() {</span><a href="#l613"></a>
<span id="l614">        return host;</span><a href="#l614"></a>
<span id="l615">    }</span><a href="#l615"></a>
<span id="l616"></span><a href="#l616"></a>
<span id="l617">    /**</span><a href="#l617"></a>
<span id="l618">     * Need to provide the port info for caching sessions based on</span><a href="#l618"></a>
<span id="l619">     * host and port. Accessed by SSLSessionContextImpl</span><a href="#l619"></a>
<span id="l620">     */</span><a href="#l620"></a>
<span id="l621">    public int getPeerPort() {</span><a href="#l621"></a>
<span id="l622">        return port;</span><a href="#l622"></a>
<span id="l623">    }</span><a href="#l623"></a>
<span id="l624"></span><a href="#l624"></a>
<span id="l625">    void setContext(SSLSessionContextImpl ctx) {</span><a href="#l625"></a>
<span id="l626">        if (context == null) {</span><a href="#l626"></a>
<span id="l627">            context = ctx;</span><a href="#l627"></a>
<span id="l628">        }</span><a href="#l628"></a>
<span id="l629">    }</span><a href="#l629"></a>
<span id="l630"></span><a href="#l630"></a>
<span id="l631">    /**</span><a href="#l631"></a>
<span id="l632">     * Invalidate a session.  Active connections may still exist, but</span><a href="#l632"></a>
<span id="l633">     * no connections will be able to rejoin this session.</span><a href="#l633"></a>
<span id="l634">     */</span><a href="#l634"></a>
<span id="l635">    synchronized public void invalidate() {</span><a href="#l635"></a>
<span id="l636">        invalidated = true;</span><a href="#l636"></a>
<span id="l637">        if (debug != null &amp;&amp; Debug.isOn(&quot;session&quot;)) {</span><a href="#l637"></a>
<span id="l638">            System.out.println(&quot;%% Invalidated:  &quot; + this);</span><a href="#l638"></a>
<span id="l639">        }</span><a href="#l639"></a>
<span id="l640">        if (context != null) {</span><a href="#l640"></a>
<span id="l641">            context.remove(sessionId);</span><a href="#l641"></a>
<span id="l642">            context = null;</span><a href="#l642"></a>
<span id="l643">        }</span><a href="#l643"></a>
<span id="l644">    }</span><a href="#l644"></a>
<span id="l645"></span><a href="#l645"></a>
<span id="l646">    /*</span><a href="#l646"></a>
<span id="l647">     * Table of application-specific session data indexed by an application</span><a href="#l647"></a>
<span id="l648">     * key and the calling security context. This is important since</span><a href="#l648"></a>
<span id="l649">     * sessions can be shared across different protection domains.</span><a href="#l649"></a>
<span id="l650">     */</span><a href="#l650"></a>
<span id="l651">    private Hashtable&lt;SecureKey, Object&gt; table = new Hashtable&lt;&gt;();</span><a href="#l651"></a>
<span id="l652"></span><a href="#l652"></a>
<span id="l653">    /**</span><a href="#l653"></a>
<span id="l654">     * Assigns a session value.  Session change events are given if</span><a href="#l654"></a>
<span id="l655">     * appropriate, to any original value as well as the new value.</span><a href="#l655"></a>
<span id="l656">     */</span><a href="#l656"></a>
<span id="l657">    public void putValue(String key, Object value) {</span><a href="#l657"></a>
<span id="l658">        if ((key == null) || (value == null)) {</span><a href="#l658"></a>
<span id="l659">            throw new IllegalArgumentException(&quot;arguments can not be null&quot;);</span><a href="#l659"></a>
<span id="l660">        }</span><a href="#l660"></a>
<span id="l661"></span><a href="#l661"></a>
<span id="l662">        SecureKey secureKey = new SecureKey(key);</span><a href="#l662"></a>
<span id="l663">        Object oldValue = table.put(secureKey, value);</span><a href="#l663"></a>
<span id="l664"></span><a href="#l664"></a>
<span id="l665">        if (oldValue instanceof SSLSessionBindingListener) {</span><a href="#l665"></a>
<span id="l666">            SSLSessionBindingEvent e;</span><a href="#l666"></a>
<span id="l667"></span><a href="#l667"></a>
<span id="l668">            e = new SSLSessionBindingEvent(this, key);</span><a href="#l668"></a>
<span id="l669">            ((SSLSessionBindingListener)oldValue).valueUnbound(e);</span><a href="#l669"></a>
<span id="l670">        }</span><a href="#l670"></a>
<span id="l671">        if (value instanceof SSLSessionBindingListener) {</span><a href="#l671"></a>
<span id="l672">            SSLSessionBindingEvent e;</span><a href="#l672"></a>
<span id="l673"></span><a href="#l673"></a>
<span id="l674">            e = new SSLSessionBindingEvent(this, key);</span><a href="#l674"></a>
<span id="l675">            ((SSLSessionBindingListener)value).valueBound(e);</span><a href="#l675"></a>
<span id="l676">        }</span><a href="#l676"></a>
<span id="l677">    }</span><a href="#l677"></a>
<span id="l678"></span><a href="#l678"></a>
<span id="l679"></span><a href="#l679"></a>
<span id="l680">    /**</span><a href="#l680"></a>
<span id="l681">     * Returns the specified session value.</span><a href="#l681"></a>
<span id="l682">     */</span><a href="#l682"></a>
<span id="l683">    public Object getValue(String key) {</span><a href="#l683"></a>
<span id="l684">        if (key == null) {</span><a href="#l684"></a>
<span id="l685">            throw new IllegalArgumentException(&quot;argument can not be null&quot;);</span><a href="#l685"></a>
<span id="l686">        }</span><a href="#l686"></a>
<span id="l687"></span><a href="#l687"></a>
<span id="l688">        SecureKey secureKey = new SecureKey(key);</span><a href="#l688"></a>
<span id="l689">        return table.get(secureKey);</span><a href="#l689"></a>
<span id="l690">    }</span><a href="#l690"></a>
<span id="l691"></span><a href="#l691"></a>
<span id="l692"></span><a href="#l692"></a>
<span id="l693">    /**</span><a href="#l693"></a>
<span id="l694">     * Removes the specified session value, delivering a session changed</span><a href="#l694"></a>
<span id="l695">     * event as appropriate.</span><a href="#l695"></a>
<span id="l696">     */</span><a href="#l696"></a>
<span id="l697">    public void removeValue(String key) {</span><a href="#l697"></a>
<span id="l698">        if (key == null) {</span><a href="#l698"></a>
<span id="l699">            throw new IllegalArgumentException(&quot;argument can not be null&quot;);</span><a href="#l699"></a>
<span id="l700">        }</span><a href="#l700"></a>
<span id="l701"></span><a href="#l701"></a>
<span id="l702">        SecureKey secureKey = new SecureKey(key);</span><a href="#l702"></a>
<span id="l703">        Object value = table.remove(secureKey);</span><a href="#l703"></a>
<span id="l704"></span><a href="#l704"></a>
<span id="l705">        if (value instanceof SSLSessionBindingListener) {</span><a href="#l705"></a>
<span id="l706">            SSLSessionBindingEvent e;</span><a href="#l706"></a>
<span id="l707"></span><a href="#l707"></a>
<span id="l708">            e = new SSLSessionBindingEvent(this, key);</span><a href="#l708"></a>
<span id="l709">            ((SSLSessionBindingListener)value).valueUnbound(e);</span><a href="#l709"></a>
<span id="l710">        }</span><a href="#l710"></a>
<span id="l711">    }</span><a href="#l711"></a>
<span id="l712"></span><a href="#l712"></a>
<span id="l713"></span><a href="#l713"></a>
<span id="l714">    /**</span><a href="#l714"></a>
<span id="l715">     * Lists the names of the session values.</span><a href="#l715"></a>
<span id="l716">     */</span><a href="#l716"></a>
<span id="l717">    public String[] getValueNames() {</span><a href="#l717"></a>
<span id="l718">        Enumeration&lt;SecureKey&gt; e;</span><a href="#l718"></a>
<span id="l719">        Vector&lt;Object&gt; v = new Vector&lt;&gt;();</span><a href="#l719"></a>
<span id="l720">        SecureKey key;</span><a href="#l720"></a>
<span id="l721">        Object securityCtx = SecureKey.getCurrentSecurityContext();</span><a href="#l721"></a>
<span id="l722"></span><a href="#l722"></a>
<span id="l723">        for (e = table.keys(); e.hasMoreElements(); ) {</span><a href="#l723"></a>
<span id="l724">            key = e.nextElement();</span><a href="#l724"></a>
<span id="l725"></span><a href="#l725"></a>
<span id="l726">            if (securityCtx.equals(key.getSecurityContext())) {</span><a href="#l726"></a>
<span id="l727">                v.addElement(key.getAppKey());</span><a href="#l727"></a>
<span id="l728">            }</span><a href="#l728"></a>
<span id="l729">        }</span><a href="#l729"></a>
<span id="l730">        String[] names = new String[v.size()];</span><a href="#l730"></a>
<span id="l731">        v.copyInto(names);</span><a href="#l731"></a>
<span id="l732"></span><a href="#l732"></a>
<span id="l733">        return names;</span><a href="#l733"></a>
<span id="l734">    }</span><a href="#l734"></a>
<span id="l735"></span><a href="#l735"></a>
<span id="l736">    /**</span><a href="#l736"></a>
<span id="l737">     * Use large packet sizes now or follow RFC 2246 packet sizes (2^14)</span><a href="#l737"></a>
<span id="l738">     * until changed.</span><a href="#l738"></a>
<span id="l739">     *</span><a href="#l739"></a>
<span id="l740">     * In the TLS specification (section 6.2.1, RFC2246), it is not</span><a href="#l740"></a>
<span id="l741">     * recommended that the plaintext has more than 2^14 bytes.</span><a href="#l741"></a>
<span id="l742">     * However, some TLS implementations violate the specification.</span><a href="#l742"></a>
<span id="l743">     * This is a workaround for interoperability with these stacks.</span><a href="#l743"></a>
<span id="l744">     *</span><a href="#l744"></a>
<span id="l745">     * Application could accept large fragments up to 2^15 bytes by</span><a href="#l745"></a>
<span id="l746">     * setting the system property jsse.SSLEngine.acceptLargeFragments</span><a href="#l746"></a>
<span id="l747">     * to &quot;true&quot;.</span><a href="#l747"></a>
<span id="l748">     */</span><a href="#l748"></a>
<span id="l749">    private boolean acceptLargeFragments =</span><a href="#l749"></a>
<span id="l750">        Debug.getBooleanProperty(&quot;jsse.SSLEngine.acceptLargeFragments&quot;, false);</span><a href="#l750"></a>
<span id="l751"></span><a href="#l751"></a>
<span id="l752">    /**</span><a href="#l752"></a>
<span id="l753">     * Expand the buffer size of both SSL/TLS network packet and</span><a href="#l753"></a>
<span id="l754">     * application data.</span><a href="#l754"></a>
<span id="l755">     */</span><a href="#l755"></a>
<span id="l756">    protected synchronized void expandBufferSizes() {</span><a href="#l756"></a>
<span id="l757">        acceptLargeFragments = true;</span><a href="#l757"></a>
<span id="l758">    }</span><a href="#l758"></a>
<span id="l759"></span><a href="#l759"></a>
<span id="l760">    /**</span><a href="#l760"></a>
<span id="l761">     * Gets the current size of the largest SSL/TLS packet that is expected</span><a href="#l761"></a>
<span id="l762">     * when using this session.</span><a href="#l762"></a>
<span id="l763">     */</span><a href="#l763"></a>
<span id="l764">    public synchronized int getPacketBufferSize() {</span><a href="#l764"></a>
<span id="l765">        return acceptLargeFragments ?</span><a href="#l765"></a>
<span id="l766">                Record.maxLargeRecordSize : Record.maxRecordSize;</span><a href="#l766"></a>
<span id="l767">    }</span><a href="#l767"></a>
<span id="l768"></span><a href="#l768"></a>
<span id="l769">    /**</span><a href="#l769"></a>
<span id="l770">     * Gets the current size of the largest application data that is</span><a href="#l770"></a>
<span id="l771">     * expected when using this session.</span><a href="#l771"></a>
<span id="l772">     */</span><a href="#l772"></a>
<span id="l773">    public synchronized int getApplicationBufferSize() {</span><a href="#l773"></a>
<span id="l774">        return getPacketBufferSize() - Record.headerSize;</span><a href="#l774"></a>
<span id="l775">    }</span><a href="#l775"></a>
<span id="l776"></span><a href="#l776"></a>
<span id="l777">    /**</span><a href="#l777"></a>
<span id="l778">     * Gets an array of supported signature algorithms that the local side is</span><a href="#l778"></a>
<span id="l779">     * willing to verify.</span><a href="#l779"></a>
<span id="l780">     */</span><a href="#l780"></a>
<span id="l781">    public String[] getLocalSupportedSignatureAlgorithms() {</span><a href="#l781"></a>
<span id="l782">        if (localSupportedSignAlgs != null) {</span><a href="#l782"></a>
<span id="l783">            return localSupportedSignAlgs.clone();</span><a href="#l783"></a>
<span id="l784">        }</span><a href="#l784"></a>
<span id="l785"></span><a href="#l785"></a>
<span id="l786">        return new String[0];</span><a href="#l786"></a>
<span id="l787">    }</span><a href="#l787"></a>
<span id="l788"></span><a href="#l788"></a>
<span id="l789">    /**</span><a href="#l789"></a>
<span id="l790">     * Gets an array of supported signature algorithms that the peer is</span><a href="#l790"></a>
<span id="l791">     * able to verify.</span><a href="#l791"></a>
<span id="l792">     */</span><a href="#l792"></a>
<span id="l793">    public String[] getPeerSupportedSignatureAlgorithms() {</span><a href="#l793"></a>
<span id="l794">        if (peerSupportedSignAlgs != null) {</span><a href="#l794"></a>
<span id="l795">            return peerSupportedSignAlgs.clone();</span><a href="#l795"></a>
<span id="l796">        }</span><a href="#l796"></a>
<span id="l797"></span><a href="#l797"></a>
<span id="l798">        return new String[0];</span><a href="#l798"></a>
<span id="l799">    }</span><a href="#l799"></a>
<span id="l800"></span><a href="#l800"></a>
<span id="l801">    /** Returns a string representation of this SSL session */</span><a href="#l801"></a>
<span id="l802">    public String toString() {</span><a href="#l802"></a>
<span id="l803">        return &quot;[Session-&quot; + sessionCount</span><a href="#l803"></a>
<span id="l804">            + &quot;, &quot; + getCipherSuite()</span><a href="#l804"></a>
<span id="l805">            + &quot;]&quot;;</span><a href="#l805"></a>
<span id="l806">    }</span><a href="#l806"></a>
<span id="l807"></span><a href="#l807"></a>
<span id="l808">    /**</span><a href="#l808"></a>
<span id="l809">     * When SSL sessions are finalized, all values bound to</span><a href="#l809"></a>
<span id="l810">     * them are removed.</span><a href="#l810"></a>
<span id="l811">     */</span><a href="#l811"></a>
<span id="l812">    public void finalize() {</span><a href="#l812"></a>
<span id="l813">        String[] names = getValueNames();</span><a href="#l813"></a>
<span id="l814">        for (int i = 0; i &lt; names.length; i++) {</span><a href="#l814"></a>
<span id="l815">            removeValue(names[i]);</span><a href="#l815"></a>
<span id="l816">        }</span><a href="#l816"></a>
<span id="l817">    }</span><a href="#l817"></a>
<span id="l818">}</span><a href="#l818"></a>
<span id="l819"></span><a href="#l819"></a>
<span id="l820"></span><a href="#l820"></a>
<span id="l821">/**</span><a href="#l821"></a>
<span id="l822"> * This &quot;struct&quot; class serves as a Hash Key that combines an</span><a href="#l822"></a>
<span id="l823"> * application-specific key and a security context.</span><a href="#l823"></a>
<span id="l824"> */</span><a href="#l824"></a>
<span id="l825">class SecureKey {</span><a href="#l825"></a>
<span id="l826">    private static Object       nullObject = new Object();</span><a href="#l826"></a>
<span id="l827">    private Object        appKey;</span><a href="#l827"></a>
<span id="l828">    private Object      securityCtx;</span><a href="#l828"></a>
<span id="l829"></span><a href="#l829"></a>
<span id="l830">    static Object getCurrentSecurityContext() {</span><a href="#l830"></a>
<span id="l831">        SecurityManager sm = System.getSecurityManager();</span><a href="#l831"></a>
<span id="l832">        Object context = null;</span><a href="#l832"></a>
<span id="l833"></span><a href="#l833"></a>
<span id="l834">        if (sm != null)</span><a href="#l834"></a>
<span id="l835">            context = sm.getSecurityContext();</span><a href="#l835"></a>
<span id="l836">        if (context == null)</span><a href="#l836"></a>
<span id="l837">            context = nullObject;</span><a href="#l837"></a>
<span id="l838">        return context;</span><a href="#l838"></a>
<span id="l839">    }</span><a href="#l839"></a>
<span id="l840"></span><a href="#l840"></a>
<span id="l841">    SecureKey(Object key) {</span><a href="#l841"></a>
<span id="l842">        this.appKey = key;</span><a href="#l842"></a>
<span id="l843">        this.securityCtx = getCurrentSecurityContext();</span><a href="#l843"></a>
<span id="l844">    }</span><a href="#l844"></a>
<span id="l845"></span><a href="#l845"></a>
<span id="l846">    Object getAppKey() {</span><a href="#l846"></a>
<span id="l847">        return appKey;</span><a href="#l847"></a>
<span id="l848">    }</span><a href="#l848"></a>
<span id="l849"></span><a href="#l849"></a>
<span id="l850">    Object getSecurityContext() {</span><a href="#l850"></a>
<span id="l851">        return securityCtx;</span><a href="#l851"></a>
<span id="l852">    }</span><a href="#l852"></a>
<span id="l853"></span><a href="#l853"></a>
<span id="l854">    public int hashCode() {</span><a href="#l854"></a>
<span id="l855">        return appKey.hashCode() ^ securityCtx.hashCode();</span><a href="#l855"></a>
<span id="l856">    }</span><a href="#l856"></a>
<span id="l857"></span><a href="#l857"></a>
<span id="l858">    public boolean equals(Object o) {</span><a href="#l858"></a>
<span id="l859">        return o instanceof SecureKey &amp;&amp; ((SecureKey)o).appKey.equals(appKey)</span><a href="#l859"></a>
<span id="l860">                        &amp;&amp; ((SecureKey)o).securityCtx.equals(securityCtx);</span><a href="#l860"></a>
<span id="l861">    }</span><a href="#l861"></a>
<span id="l862">}</span><a href="#l862"></a></pre>
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

