<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 412d2b1381a4 src/share/classes/sun/security/ssl/SSLContextImpl.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk8u/jdk8u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/shortlog/412d2b1381a4">log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/graph/412d2b1381a4">graph</a></li>
<li><a href="/jdk8u/jdk8u/jdk/tags">tags</a></li>
<li><a href="/jdk8u/jdk8u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/rev/412d2b1381a4">changeset</a></li>
<li><a href="/jdk8u/jdk8u/jdk/file/412d2b1381a4/src/share/classes/sun/security/ssl/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/ssl/SSLContextImpl.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/412d2b1381a4/src/share/classes/sun/security/ssl/SSLContextImpl.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/412d2b1381a4/src/share/classes/sun/security/ssl/SSLContextImpl.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/412d2b1381a4/src/share/classes/sun/security/ssl/SSLContextImpl.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/412d2b1381a4/src/share/classes/sun/security/ssl/SSLContextImpl.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/412d2b1381a4/src/share/classes/sun/security/ssl/SSLContextImpl.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/ssl/SSLContextImpl.java @ 14391:412d2b1381a4</h3>

<form class="search" action="/jdk8u/jdk8u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk8u/jdk8u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8249906: Enhance opening JARs
8258247: Couple of issues in fix for JDK-8249906
8259428: AlgorithmId.getEncodedParams() should return copy
Reviewed-by: mbalao, andrew</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#97;&#118;&#111;&#105;&#116;&#121;&#108;&#111;&#118;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Wed, 07 Apr 2021 05:57:56 +0100</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/25ef0910e622/src/share/classes/sun/security/ssl/SSLContextImpl.java">25ef0910e622</a> </td>
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
<span id="l2"> * Copyright (c) 1999, 2018, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l26">package sun.security.ssl;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.io.*;</span><a href="#l28"></a>
<span id="l29">import java.net.Socket;</span><a href="#l29"></a>
<span id="l30">import java.security.*;</span><a href="#l30"></a>
<span id="l31">import java.security.cert.*;</span><a href="#l31"></a>
<span id="l32">import java.util.*;</span><a href="#l32"></a>
<span id="l33">import javax.net.ssl.*;</span><a href="#l33"></a>
<span id="l34">import sun.security.action.GetPropertyAction;</span><a href="#l34"></a>
<span id="l35">import sun.security.provider.certpath.AlgorithmChecker;</span><a href="#l35"></a>
<span id="l36">import sun.security.validator.Validator;</span><a href="#l36"></a>
<span id="l37"></span><a href="#l37"></a>
<span id="l38">/**</span><a href="#l38"></a>
<span id="l39"> * Implementation of an SSLContext.</span><a href="#l39"></a>
<span id="l40"> *</span><a href="#l40"></a>
<span id="l41"> * Implementation note: Instances of this class and the child classes are</span><a href="#l41"></a>
<span id="l42"> * immutable, except that the context initialization (SSLContext.init()) may</span><a href="#l42"></a>
<span id="l43"> * reset the key, trust managers and source of secure random.</span><a href="#l43"></a>
<span id="l44"> */</span><a href="#l44"></a>
<span id="l45"></span><a href="#l45"></a>
<span id="l46">public abstract class SSLContextImpl extends SSLContextSpi {</span><a href="#l46"></a>
<span id="l47"></span><a href="#l47"></a>
<span id="l48">    private final EphemeralKeyManager ephemeralKeyManager;</span><a href="#l48"></a>
<span id="l49">    private final SSLSessionContextImpl clientCache;</span><a href="#l49"></a>
<span id="l50">    private final SSLSessionContextImpl serverCache;</span><a href="#l50"></a>
<span id="l51"></span><a href="#l51"></a>
<span id="l52">    private boolean isInitialized;</span><a href="#l52"></a>
<span id="l53"></span><a href="#l53"></a>
<span id="l54">    private X509ExtendedKeyManager keyManager;</span><a href="#l54"></a>
<span id="l55">    private X509TrustManager trustManager;</span><a href="#l55"></a>
<span id="l56">    private SecureRandom secureRandom;</span><a href="#l56"></a>
<span id="l57"></span><a href="#l57"></a>
<span id="l58">    // DTLS cookie exchange manager</span><a href="#l58"></a>
<span id="l59">    private volatile HelloCookieManager.Builder helloCookieManagerBuilder;</span><a href="#l59"></a>
<span id="l60"></span><a href="#l60"></a>
<span id="l61">    private final boolean clientEnableStapling = Utilities.getBooleanProperty(</span><a href="#l61"></a>
<span id="l62">            &quot;jdk.tls.client.enableStatusRequestExtension&quot;, false);</span><a href="#l62"></a>
<span id="l63">    private final boolean serverEnableStapling = Utilities.getBooleanProperty(</span><a href="#l63"></a>
<span id="l64">            &quot;jdk.tls.server.enableStatusRequestExtension&quot;, false);</span><a href="#l64"></a>
<span id="l65">    private static final Collection&lt;CipherSuite&gt; clientCustomizedCipherSuites =</span><a href="#l65"></a>
<span id="l66">            getCustomizedCipherSuites(&quot;jdk.tls.client.cipherSuites&quot;);</span><a href="#l66"></a>
<span id="l67">    private static final Collection&lt;CipherSuite&gt; serverCustomizedCipherSuites =</span><a href="#l67"></a>
<span id="l68">            getCustomizedCipherSuites(&quot;jdk.tls.server.cipherSuites&quot;);</span><a href="#l68"></a>
<span id="l69"></span><a href="#l69"></a>
<span id="l70">    private volatile StatusResponseManager statusResponseManager;</span><a href="#l70"></a>
<span id="l71"></span><a href="#l71"></a>
<span id="l72">    SSLContextImpl() {</span><a href="#l72"></a>
<span id="l73">        ephemeralKeyManager = new EphemeralKeyManager();</span><a href="#l73"></a>
<span id="l74">        clientCache = new SSLSessionContextImpl();</span><a href="#l74"></a>
<span id="l75">        serverCache = new SSLSessionContextImpl();</span><a href="#l75"></a>
<span id="l76">    }</span><a href="#l76"></a>
<span id="l77"></span><a href="#l77"></a>
<span id="l78">    @Override</span><a href="#l78"></a>
<span id="l79">    protected void engineInit(KeyManager[] km, TrustManager[] tm,</span><a href="#l79"></a>
<span id="l80">                                SecureRandom sr) throws KeyManagementException {</span><a href="#l80"></a>
<span id="l81">        isInitialized = false;</span><a href="#l81"></a>
<span id="l82">        keyManager = chooseKeyManager(km);</span><a href="#l82"></a>
<span id="l83"></span><a href="#l83"></a>
<span id="l84">        if (tm == null) {</span><a href="#l84"></a>
<span id="l85">            try {</span><a href="#l85"></a>
<span id="l86">                TrustManagerFactory tmf = TrustManagerFactory.getInstance(</span><a href="#l86"></a>
<span id="l87">                        TrustManagerFactory.getDefaultAlgorithm());</span><a href="#l87"></a>
<span id="l88">                tmf.init((KeyStore)null);</span><a href="#l88"></a>
<span id="l89">                tm = tmf.getTrustManagers();</span><a href="#l89"></a>
<span id="l90">            } catch (Exception e) {</span><a href="#l90"></a>
<span id="l91">                // eat</span><a href="#l91"></a>
<span id="l92">            }</span><a href="#l92"></a>
<span id="l93">        }</span><a href="#l93"></a>
<span id="l94">        trustManager = chooseTrustManager(tm);</span><a href="#l94"></a>
<span id="l95"></span><a href="#l95"></a>
<span id="l96">        if (sr == null) {</span><a href="#l96"></a>
<span id="l97">            secureRandom = JsseJce.getSecureRandom();</span><a href="#l97"></a>
<span id="l98">        } else {</span><a href="#l98"></a>
<span id="l99">            if (SunJSSE.isFIPS() &amp;&amp;</span><a href="#l99"></a>
<span id="l100">                        (sr.getProvider() != SunJSSE.cryptoProvider)) {</span><a href="#l100"></a>
<span id="l101">                throw new KeyManagementException</span><a href="#l101"></a>
<span id="l102">                    (&quot;FIPS mode: SecureRandom must be from provider &quot;</span><a href="#l102"></a>
<span id="l103">                    + SunJSSE.cryptoProvider.getName());</span><a href="#l103"></a>
<span id="l104">            }</span><a href="#l104"></a>
<span id="l105">            secureRandom = sr;</span><a href="#l105"></a>
<span id="l106">        }</span><a href="#l106"></a>
<span id="l107"></span><a href="#l107"></a>
<span id="l108">        /*</span><a href="#l108"></a>
<span id="l109">         * The initial delay of seeding the random number generator</span><a href="#l109"></a>
<span id="l110">         * could be long enough to cause the initial handshake on our</span><a href="#l110"></a>
<span id="l111">         * first connection to timeout and fail. Make sure it is</span><a href="#l111"></a>
<span id="l112">         * primed and ready by getting some initial output from it.</span><a href="#l112"></a>
<span id="l113">         */</span><a href="#l113"></a>
<span id="l114">        if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,sslctx&quot;)) {</span><a href="#l114"></a>
<span id="l115">            SSLLogger.finest(&quot;trigger seeding of SecureRandom&quot;);</span><a href="#l115"></a>
<span id="l116">        }</span><a href="#l116"></a>
<span id="l117">        secureRandom.nextInt();</span><a href="#l117"></a>
<span id="l118">        if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,sslctx&quot;)) {</span><a href="#l118"></a>
<span id="l119">            SSLLogger.finest(&quot;done seeding of SecureRandom&quot;);</span><a href="#l119"></a>
<span id="l120">        }</span><a href="#l120"></a>
<span id="l121"></span><a href="#l121"></a>
<span id="l122">        isInitialized = true;</span><a href="#l122"></a>
<span id="l123">    }</span><a href="#l123"></a>
<span id="l124"></span><a href="#l124"></a>
<span id="l125">    private X509TrustManager chooseTrustManager(TrustManager[] tm)</span><a href="#l125"></a>
<span id="l126">            throws KeyManagementException {</span><a href="#l126"></a>
<span id="l127">        // We only use the first instance of X509TrustManager passed to us.</span><a href="#l127"></a>
<span id="l128">        for (int i = 0; tm != null &amp;&amp; i &lt; tm.length; i++) {</span><a href="#l128"></a>
<span id="l129">            if (tm[i] instanceof X509TrustManager) {</span><a href="#l129"></a>
<span id="l130">                if (SunJSSE.isFIPS() &amp;&amp;</span><a href="#l130"></a>
<span id="l131">                        !(tm[i] instanceof X509TrustManagerImpl)) {</span><a href="#l131"></a>
<span id="l132">                    throw new KeyManagementException</span><a href="#l132"></a>
<span id="l133">                        (&quot;FIPS mode: only SunJSSE TrustManagers may be used&quot;);</span><a href="#l133"></a>
<span id="l134">                }</span><a href="#l134"></a>
<span id="l135"></span><a href="#l135"></a>
<span id="l136">                if (tm[i] instanceof X509ExtendedTrustManager) {</span><a href="#l136"></a>
<span id="l137">                    return (X509TrustManager)tm[i];</span><a href="#l137"></a>
<span id="l138">                } else {</span><a href="#l138"></a>
<span id="l139">                    return new AbstractTrustManagerWrapper(</span><a href="#l139"></a>
<span id="l140">                                        (X509TrustManager)tm[i]);</span><a href="#l140"></a>
<span id="l141">                }</span><a href="#l141"></a>
<span id="l142">            }</span><a href="#l142"></a>
<span id="l143">        }</span><a href="#l143"></a>
<span id="l144"></span><a href="#l144"></a>
<span id="l145">        // nothing found, return a dummy X509TrustManager.</span><a href="#l145"></a>
<span id="l146">        return DummyX509TrustManager.INSTANCE;</span><a href="#l146"></a>
<span id="l147">    }</span><a href="#l147"></a>
<span id="l148"></span><a href="#l148"></a>
<span id="l149">    private X509ExtendedKeyManager chooseKeyManager(KeyManager[] kms)</span><a href="#l149"></a>
<span id="l150">            throws KeyManagementException {</span><a href="#l150"></a>
<span id="l151">        for (int i = 0; kms != null &amp;&amp; i &lt; kms.length; i++) {</span><a href="#l151"></a>
<span id="l152">            KeyManager km = kms[i];</span><a href="#l152"></a>
<span id="l153">            if (!(km instanceof X509KeyManager)) {</span><a href="#l153"></a>
<span id="l154">                continue;</span><a href="#l154"></a>
<span id="l155">            }</span><a href="#l155"></a>
<span id="l156">            if (SunJSSE.isFIPS()) {</span><a href="#l156"></a>
<span id="l157">                // In FIPS mode, require that one of SunJSSE's own keymanagers</span><a href="#l157"></a>
<span id="l158">                // is used. Otherwise, we cannot be sure that only keys from</span><a href="#l158"></a>
<span id="l159">                // the FIPS token are used.</span><a href="#l159"></a>
<span id="l160">                if ((km instanceof X509KeyManagerImpl)</span><a href="#l160"></a>
<span id="l161">                            || (km instanceof SunX509KeyManagerImpl)) {</span><a href="#l161"></a>
<span id="l162">                    return (X509ExtendedKeyManager)km;</span><a href="#l162"></a>
<span id="l163">                } else {</span><a href="#l163"></a>
<span id="l164">                    // throw exception, we don't want to silently use the</span><a href="#l164"></a>
<span id="l165">                    // dummy keymanager without telling the user.</span><a href="#l165"></a>
<span id="l166">                    throw new KeyManagementException</span><a href="#l166"></a>
<span id="l167">                        (&quot;FIPS mode: only SunJSSE KeyManagers may be used&quot;);</span><a href="#l167"></a>
<span id="l168">                }</span><a href="#l168"></a>
<span id="l169">            }</span><a href="#l169"></a>
<span id="l170">            if (km instanceof X509ExtendedKeyManager) {</span><a href="#l170"></a>
<span id="l171">                return (X509ExtendedKeyManager)km;</span><a href="#l171"></a>
<span id="l172">            }</span><a href="#l172"></a>
<span id="l173"></span><a href="#l173"></a>
<span id="l174">            if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,sslctx&quot;)) {</span><a href="#l174"></a>
<span id="l175">                SSLLogger.warning(</span><a href="#l175"></a>
<span id="l176">                    &quot;X509KeyManager passed to SSLContext.init():  need an &quot; +</span><a href="#l176"></a>
<span id="l177">                    &quot;X509ExtendedKeyManager for SSLEngine use&quot;);</span><a href="#l177"></a>
<span id="l178">            }</span><a href="#l178"></a>
<span id="l179">            return new AbstractKeyManagerWrapper((X509KeyManager)km);</span><a href="#l179"></a>
<span id="l180">        }</span><a href="#l180"></a>
<span id="l181"></span><a href="#l181"></a>
<span id="l182">        // nothing found, return a dummy X509ExtendedKeyManager</span><a href="#l182"></a>
<span id="l183">        return DummyX509KeyManager.INSTANCE;</span><a href="#l183"></a>
<span id="l184">    }</span><a href="#l184"></a>
<span id="l185"></span><a href="#l185"></a>
<span id="l186">    abstract SSLEngine createSSLEngineImpl();</span><a href="#l186"></a>
<span id="l187">    abstract SSLEngine createSSLEngineImpl(String host, int port);</span><a href="#l187"></a>
<span id="l188"></span><a href="#l188"></a>
<span id="l189">    @Override</span><a href="#l189"></a>
<span id="l190">    protected SSLEngine engineCreateSSLEngine() {</span><a href="#l190"></a>
<span id="l191">        if (!isInitialized) {</span><a href="#l191"></a>
<span id="l192">            throw new IllegalStateException(&quot;SSLContext is not initialized&quot;);</span><a href="#l192"></a>
<span id="l193">        }</span><a href="#l193"></a>
<span id="l194">        return createSSLEngineImpl();</span><a href="#l194"></a>
<span id="l195">    }</span><a href="#l195"></a>
<span id="l196"></span><a href="#l196"></a>
<span id="l197">    @Override</span><a href="#l197"></a>
<span id="l198">    protected SSLEngine engineCreateSSLEngine(String host, int port) {</span><a href="#l198"></a>
<span id="l199">        if (!isInitialized) {</span><a href="#l199"></a>
<span id="l200">            throw new IllegalStateException(&quot;SSLContext is not initialized&quot;);</span><a href="#l200"></a>
<span id="l201">        }</span><a href="#l201"></a>
<span id="l202">        return createSSLEngineImpl(host, port);</span><a href="#l202"></a>
<span id="l203">    }</span><a href="#l203"></a>
<span id="l204"></span><a href="#l204"></a>
<span id="l205">    @Override</span><a href="#l205"></a>
<span id="l206">    protected SSLSocketFactory engineGetSocketFactory() {</span><a href="#l206"></a>
<span id="l207">        if (!isInitialized) {</span><a href="#l207"></a>
<span id="l208">            throw new IllegalStateException(&quot;SSLContext is not initialized&quot;);</span><a href="#l208"></a>
<span id="l209">        }</span><a href="#l209"></a>
<span id="l210">       return new SSLSocketFactoryImpl(this);</span><a href="#l210"></a>
<span id="l211">    }</span><a href="#l211"></a>
<span id="l212"></span><a href="#l212"></a>
<span id="l213">    @Override</span><a href="#l213"></a>
<span id="l214">    protected SSLServerSocketFactory engineGetServerSocketFactory() {</span><a href="#l214"></a>
<span id="l215">        if (!isInitialized) {</span><a href="#l215"></a>
<span id="l216">            throw new IllegalStateException(&quot;SSLContext is not initialized&quot;);</span><a href="#l216"></a>
<span id="l217">        }</span><a href="#l217"></a>
<span id="l218">        return new SSLServerSocketFactoryImpl(this);</span><a href="#l218"></a>
<span id="l219">    }</span><a href="#l219"></a>
<span id="l220"></span><a href="#l220"></a>
<span id="l221">    @Override</span><a href="#l221"></a>
<span id="l222">    protected SSLSessionContext engineGetClientSessionContext() {</span><a href="#l222"></a>
<span id="l223">        return clientCache;</span><a href="#l223"></a>
<span id="l224">    }</span><a href="#l224"></a>
<span id="l225"></span><a href="#l225"></a>
<span id="l226">    @Override</span><a href="#l226"></a>
<span id="l227">    protected SSLSessionContext engineGetServerSessionContext() {</span><a href="#l227"></a>
<span id="l228">        return serverCache;</span><a href="#l228"></a>
<span id="l229">    }</span><a href="#l229"></a>
<span id="l230"></span><a href="#l230"></a>
<span id="l231">    SecureRandom getSecureRandom() {</span><a href="#l231"></a>
<span id="l232">        return secureRandom;</span><a href="#l232"></a>
<span id="l233">    }</span><a href="#l233"></a>
<span id="l234"></span><a href="#l234"></a>
<span id="l235">    X509ExtendedKeyManager getX509KeyManager() {</span><a href="#l235"></a>
<span id="l236">        return keyManager;</span><a href="#l236"></a>
<span id="l237">    }</span><a href="#l237"></a>
<span id="l238"></span><a href="#l238"></a>
<span id="l239">    X509TrustManager getX509TrustManager() {</span><a href="#l239"></a>
<span id="l240">        return trustManager;</span><a href="#l240"></a>
<span id="l241">    }</span><a href="#l241"></a>
<span id="l242"></span><a href="#l242"></a>
<span id="l243">    EphemeralKeyManager getEphemeralKeyManager() {</span><a href="#l243"></a>
<span id="l244">        return ephemeralKeyManager;</span><a href="#l244"></a>
<span id="l245">    }</span><a href="#l245"></a>
<span id="l246"></span><a href="#l246"></a>
<span id="l247">    // Used for DTLS in server mode only.</span><a href="#l247"></a>
<span id="l248">    HelloCookieManager getHelloCookieManager(ProtocolVersion protocolVersion) {</span><a href="#l248"></a>
<span id="l249">        if (helloCookieManagerBuilder == null) {</span><a href="#l249"></a>
<span id="l250">            synchronized (this) {</span><a href="#l250"></a>
<span id="l251">                if (helloCookieManagerBuilder == null) {</span><a href="#l251"></a>
<span id="l252">                    helloCookieManagerBuilder =</span><a href="#l252"></a>
<span id="l253">                            new HelloCookieManager.Builder(secureRandom);</span><a href="#l253"></a>
<span id="l254">                }</span><a href="#l254"></a>
<span id="l255">            }</span><a href="#l255"></a>
<span id="l256">        }</span><a href="#l256"></a>
<span id="l257"></span><a href="#l257"></a>
<span id="l258">        return helloCookieManagerBuilder.valueOf(protocolVersion);</span><a href="#l258"></a>
<span id="l259">    }</span><a href="#l259"></a>
<span id="l260"></span><a href="#l260"></a>
<span id="l261">    StatusResponseManager getStatusResponseManager() {</span><a href="#l261"></a>
<span id="l262">        if (serverEnableStapling &amp;&amp; statusResponseManager == null) {</span><a href="#l262"></a>
<span id="l263">            synchronized (this) {</span><a href="#l263"></a>
<span id="l264">                if (statusResponseManager == null) {</span><a href="#l264"></a>
<span id="l265">                    if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,sslctx&quot;)) {</span><a href="#l265"></a>
<span id="l266">                        SSLLogger.finest(</span><a href="#l266"></a>
<span id="l267">                                &quot;Initializing StatusResponseManager&quot;);</span><a href="#l267"></a>
<span id="l268">                    }</span><a href="#l268"></a>
<span id="l269">                    statusResponseManager = new StatusResponseManager();</span><a href="#l269"></a>
<span id="l270">                }</span><a href="#l270"></a>
<span id="l271">            }</span><a href="#l271"></a>
<span id="l272">        }</span><a href="#l272"></a>
<span id="l273"></span><a href="#l273"></a>
<span id="l274">        return statusResponseManager;</span><a href="#l274"></a>
<span id="l275">    }</span><a href="#l275"></a>
<span id="l276"></span><a href="#l276"></a>
<span id="l277">    // Get supported protocols.</span><a href="#l277"></a>
<span id="l278">    abstract List&lt;ProtocolVersion&gt; getSupportedProtocolVersions();</span><a href="#l278"></a>
<span id="l279"></span><a href="#l279"></a>
<span id="l280">    // Get default protocols for server mode.</span><a href="#l280"></a>
<span id="l281">    abstract List&lt;ProtocolVersion&gt; getServerDefaultProtocolVersions();</span><a href="#l281"></a>
<span id="l282"></span><a href="#l282"></a>
<span id="l283">    // Get default protocols for client mode.</span><a href="#l283"></a>
<span id="l284">    abstract List&lt;ProtocolVersion&gt; getClientDefaultProtocolVersions();</span><a href="#l284"></a>
<span id="l285"></span><a href="#l285"></a>
<span id="l286">    // Get supported CipherSuite list.</span><a href="#l286"></a>
<span id="l287">    abstract List&lt;CipherSuite&gt; getSupportedCipherSuites();</span><a href="#l287"></a>
<span id="l288"></span><a href="#l288"></a>
<span id="l289">    // Get default CipherSuite list for server mode.</span><a href="#l289"></a>
<span id="l290">    abstract List&lt;CipherSuite&gt; getServerDefaultCipherSuites();</span><a href="#l290"></a>
<span id="l291"></span><a href="#l291"></a>
<span id="l292">    // Get default CipherSuite list for client mode.</span><a href="#l292"></a>
<span id="l293">    abstract List&lt;CipherSuite&gt; getClientDefaultCipherSuites();</span><a href="#l293"></a>
<span id="l294"></span><a href="#l294"></a>
<span id="l295">    // Get default protocols.</span><a href="#l295"></a>
<span id="l296">    List&lt;ProtocolVersion&gt; getDefaultProtocolVersions(boolean roleIsServer) {</span><a href="#l296"></a>
<span id="l297">        return roleIsServer ? getServerDefaultProtocolVersions()</span><a href="#l297"></a>
<span id="l298">                            : getClientDefaultProtocolVersions();</span><a href="#l298"></a>
<span id="l299">    }</span><a href="#l299"></a>
<span id="l300"></span><a href="#l300"></a>
<span id="l301">    // Get default CipherSuite list.</span><a href="#l301"></a>
<span id="l302">    List&lt;CipherSuite&gt; getDefaultCipherSuites(boolean roleIsServer) {</span><a href="#l302"></a>
<span id="l303">        return roleIsServer ? getServerDefaultCipherSuites()</span><a href="#l303"></a>
<span id="l304">                            : getClientDefaultCipherSuites();</span><a href="#l304"></a>
<span id="l305">    }</span><a href="#l305"></a>
<span id="l306"></span><a href="#l306"></a>
<span id="l307">    /**</span><a href="#l307"></a>
<span id="l308">     * Return whether a protocol list is the original default enabled</span><a href="#l308"></a>
<span id="l309">     * protocols.  See: SSLSocket/SSLEngine.setEnabledProtocols()</span><a href="#l309"></a>
<span id="l310">     */</span><a href="#l310"></a>
<span id="l311">    boolean isDefaultProtocolVesions(List&lt;ProtocolVersion&gt; protocols) {</span><a href="#l311"></a>
<span id="l312">        return (protocols == getServerDefaultProtocolVersions()) ||</span><a href="#l312"></a>
<span id="l313">               (protocols == getClientDefaultProtocolVersions());</span><a href="#l313"></a>
<span id="l314">    }</span><a href="#l314"></a>
<span id="l315"></span><a href="#l315"></a>
<span id="l316">    /**</span><a href="#l316"></a>
<span id="l317">     * Return whether a protocol list is the original default enabled</span><a href="#l317"></a>
<span id="l318">     * protocols.  See: SSLSocket/SSLEngine.setEnabledProtocols()</span><a href="#l318"></a>
<span id="l319">     */</span><a href="#l319"></a>
<span id="l320">    boolean isDefaultCipherSuiteList(List&lt;CipherSuite&gt; cipherSuites) {</span><a href="#l320"></a>
<span id="l321">        return (cipherSuites == getServerDefaultCipherSuites()) ||</span><a href="#l321"></a>
<span id="l322">               (cipherSuites == getClientDefaultCipherSuites());</span><a href="#l322"></a>
<span id="l323">    }</span><a href="#l323"></a>
<span id="l324"></span><a href="#l324"></a>
<span id="l325">    /**</span><a href="#l325"></a>
<span id="l326">     * Return whether client or server side stapling has been enabled</span><a href="#l326"></a>
<span id="l327">     * for this SSLContextImpl</span><a href="#l327"></a>
<span id="l328">     * @param isClient true if the caller is operating in a client side role,</span><a href="#l328"></a>
<span id="l329">     * false if acting as a server.</span><a href="#l329"></a>
<span id="l330">     * @return true if stapling has been enabled for the specified role, false</span><a href="#l330"></a>
<span id="l331">     * otherwise.</span><a href="#l331"></a>
<span id="l332">     */</span><a href="#l332"></a>
<span id="l333">    boolean isStaplingEnabled(boolean isClient) {</span><a href="#l333"></a>
<span id="l334">        return isClient ? clientEnableStapling : serverEnableStapling;</span><a href="#l334"></a>
<span id="l335">    }</span><a href="#l335"></a>
<span id="l336"></span><a href="#l336"></a>
<span id="l337">    /*</span><a href="#l337"></a>
<span id="l338">     * Return the list of all available CipherSuites that are supported</span><a href="#l338"></a>
<span id="l339">     * using currently installed providers.</span><a href="#l339"></a>
<span id="l340">     */</span><a href="#l340"></a>
<span id="l341">    private static List&lt;CipherSuite&gt; getApplicableSupportedCipherSuites(</span><a href="#l341"></a>
<span id="l342">            List&lt;ProtocolVersion&gt; protocols) {</span><a href="#l342"></a>
<span id="l343"></span><a href="#l343"></a>
<span id="l344">        return getApplicableCipherSuites(</span><a href="#l344"></a>
<span id="l345">                CipherSuite.allowedCipherSuites(), protocols);</span><a href="#l345"></a>
<span id="l346">    }</span><a href="#l346"></a>
<span id="l347"></span><a href="#l347"></a>
<span id="l348">    /*</span><a href="#l348"></a>
<span id="l349">     * Return the list of all available CipherSuites that are default enabled</span><a href="#l349"></a>
<span id="l350">     * in client or server side.</span><a href="#l350"></a>
<span id="l351">     */</span><a href="#l351"></a>
<span id="l352">    private static List&lt;CipherSuite&gt; getApplicableEnabledCipherSuites(</span><a href="#l352"></a>
<span id="l353">            List&lt;ProtocolVersion&gt; protocols, boolean isClient) {</span><a href="#l353"></a>
<span id="l354"></span><a href="#l354"></a>
<span id="l355">        if (isClient) {</span><a href="#l355"></a>
<span id="l356">            if (!clientCustomizedCipherSuites.isEmpty()) {</span><a href="#l356"></a>
<span id="l357">                return getApplicableCipherSuites(</span><a href="#l357"></a>
<span id="l358">                        clientCustomizedCipherSuites, protocols);</span><a href="#l358"></a>
<span id="l359">            }</span><a href="#l359"></a>
<span id="l360">        } else {</span><a href="#l360"></a>
<span id="l361">            if (!serverCustomizedCipherSuites.isEmpty()) {</span><a href="#l361"></a>
<span id="l362">                return getApplicableCipherSuites(</span><a href="#l362"></a>
<span id="l363">                        serverCustomizedCipherSuites, protocols);</span><a href="#l363"></a>
<span id="l364">            }</span><a href="#l364"></a>
<span id="l365">        }</span><a href="#l365"></a>
<span id="l366"></span><a href="#l366"></a>
<span id="l367">        return getApplicableCipherSuites(</span><a href="#l367"></a>
<span id="l368">                CipherSuite.defaultCipherSuites(), protocols);</span><a href="#l368"></a>
<span id="l369">    }</span><a href="#l369"></a>
<span id="l370"></span><a href="#l370"></a>
<span id="l371">    /*</span><a href="#l371"></a>
<span id="l372">     * Return the list of available CipherSuites which are applicable to</span><a href="#l372"></a>
<span id="l373">     * the specified protocols.</span><a href="#l373"></a>
<span id="l374">     */</span><a href="#l374"></a>
<span id="l375">    private static List&lt;CipherSuite&gt; getApplicableCipherSuites(</span><a href="#l375"></a>
<span id="l376">            Collection&lt;CipherSuite&gt; allowedCipherSuites,</span><a href="#l376"></a>
<span id="l377">            List&lt;ProtocolVersion&gt; protocols) {</span><a href="#l377"></a>
<span id="l378">        LinkedHashSet&lt;CipherSuite&gt; suites = new LinkedHashSet&lt;&gt;();</span><a href="#l378"></a>
<span id="l379">        if (protocols != null &amp;&amp; (!protocols.isEmpty())) {</span><a href="#l379"></a>
<span id="l380">            for (CipherSuite suite : allowedCipherSuites) {</span><a href="#l380"></a>
<span id="l381">                if (!suite.isAvailable()) {</span><a href="#l381"></a>
<span id="l382">                    continue;</span><a href="#l382"></a>
<span id="l383">                }</span><a href="#l383"></a>
<span id="l384"></span><a href="#l384"></a>
<span id="l385">                boolean isSupported = false;</span><a href="#l385"></a>
<span id="l386">                for (ProtocolVersion protocol : protocols) {</span><a href="#l386"></a>
<span id="l387">                    if (!suite.supports(protocol) ||</span><a href="#l387"></a>
<span id="l388">                            !suite.bulkCipher.isAvailable()) {</span><a href="#l388"></a>
<span id="l389">                        continue;</span><a href="#l389"></a>
<span id="l390">                    }</span><a href="#l390"></a>
<span id="l391"></span><a href="#l391"></a>
<span id="l392">                    if (SSLAlgorithmConstraints.DEFAULT.permits(</span><a href="#l392"></a>
<span id="l393">                            EnumSet.of(CryptoPrimitive.KEY_AGREEMENT),</span><a href="#l393"></a>
<span id="l394">                            suite.name, null)) {</span><a href="#l394"></a>
<span id="l395">                        suites.add(suite);</span><a href="#l395"></a>
<span id="l396">                        isSupported = true;</span><a href="#l396"></a>
<span id="l397">                    } else if (SSLLogger.isOn &amp;&amp;</span><a href="#l397"></a>
<span id="l398">                            SSLLogger.isOn(&quot;ssl,sslctx,verbose&quot;)) {</span><a href="#l398"></a>
<span id="l399">                        SSLLogger.fine(</span><a href="#l399"></a>
<span id="l400">                                &quot;Ignore disabled cipher suite: &quot; + suite.name);</span><a href="#l400"></a>
<span id="l401">                    }</span><a href="#l401"></a>
<span id="l402"></span><a href="#l402"></a>
<span id="l403">                    break;</span><a href="#l403"></a>
<span id="l404">                }</span><a href="#l404"></a>
<span id="l405"></span><a href="#l405"></a>
<span id="l406">                if (!isSupported &amp;&amp; SSLLogger.isOn &amp;&amp;</span><a href="#l406"></a>
<span id="l407">                        SSLLogger.isOn(&quot;ssl,sslctx,verbose&quot;)) {</span><a href="#l407"></a>
<span id="l408">                    SSLLogger.finest(</span><a href="#l408"></a>
<span id="l409">                            &quot;Ignore unsupported cipher suite: &quot; + suite);</span><a href="#l409"></a>
<span id="l410">                }</span><a href="#l410"></a>
<span id="l411">            }</span><a href="#l411"></a>
<span id="l412">        }</span><a href="#l412"></a>
<span id="l413"></span><a href="#l413"></a>
<span id="l414">        return new ArrayList&lt;&gt;(suites);</span><a href="#l414"></a>
<span id="l415">    }</span><a href="#l415"></a>
<span id="l416"></span><a href="#l416"></a>
<span id="l417">    /*</span><a href="#l417"></a>
<span id="l418">     * Get the customized cipher suites specified by the given system property.</span><a href="#l418"></a>
<span id="l419">     */</span><a href="#l419"></a>
<span id="l420">    private static Collection&lt;CipherSuite&gt; getCustomizedCipherSuites(</span><a href="#l420"></a>
<span id="l421">            String propertyName) {</span><a href="#l421"></a>
<span id="l422"></span><a href="#l422"></a>
<span id="l423">        String property = GetPropertyAction.privilegedGetProperty(propertyName);</span><a href="#l423"></a>
<span id="l424">        if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,sslctx&quot;)) {</span><a href="#l424"></a>
<span id="l425">            SSLLogger.fine(</span><a href="#l425"></a>
<span id="l426">                    &quot;System property &quot; + propertyName + &quot; is set to '&quot; +</span><a href="#l426"></a>
<span id="l427">                    property + &quot;'&quot;);</span><a href="#l427"></a>
<span id="l428">        }</span><a href="#l428"></a>
<span id="l429">        if (property != null &amp;&amp; !property.isEmpty()) {</span><a href="#l429"></a>
<span id="l430">            // remove double quote marks from beginning/end of the property</span><a href="#l430"></a>
<span id="l431">            if (property.length() &gt; 1 &amp;&amp; property.charAt(0) == '&quot;' &amp;&amp;</span><a href="#l431"></a>
<span id="l432">                    property.charAt(property.length() - 1) == '&quot;') {</span><a href="#l432"></a>
<span id="l433">                property = property.substring(1, property.length() - 1);</span><a href="#l433"></a>
<span id="l434">            }</span><a href="#l434"></a>
<span id="l435">        }</span><a href="#l435"></a>
<span id="l436"></span><a href="#l436"></a>
<span id="l437">        if (property != null &amp;&amp; !property.isEmpty()) {</span><a href="#l437"></a>
<span id="l438">            String[] cipherSuiteNames = property.split(&quot;,&quot;);</span><a href="#l438"></a>
<span id="l439">            Collection&lt;CipherSuite&gt; cipherSuites =</span><a href="#l439"></a>
<span id="l440">                        new ArrayList&lt;&gt;(cipherSuiteNames.length);</span><a href="#l440"></a>
<span id="l441">            for (int i = 0; i &lt; cipherSuiteNames.length; i++) {</span><a href="#l441"></a>
<span id="l442">                cipherSuiteNames[i] = cipherSuiteNames[i].trim();</span><a href="#l442"></a>
<span id="l443">                if (cipherSuiteNames[i].isEmpty()) {</span><a href="#l443"></a>
<span id="l444">                    continue;</span><a href="#l444"></a>
<span id="l445">                }</span><a href="#l445"></a>
<span id="l446"></span><a href="#l446"></a>
<span id="l447">                CipherSuite suite;</span><a href="#l447"></a>
<span id="l448">                try {</span><a href="#l448"></a>
<span id="l449">                    suite = CipherSuite.nameOf(cipherSuiteNames[i]);</span><a href="#l449"></a>
<span id="l450">                } catch (IllegalArgumentException iae) {</span><a href="#l450"></a>
<span id="l451">                    if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,sslctx&quot;)) {</span><a href="#l451"></a>
<span id="l452">                        SSLLogger.fine(</span><a href="#l452"></a>
<span id="l453">                                &quot;Unknown or unsupported cipher suite name: &quot; +</span><a href="#l453"></a>
<span id="l454">                                cipherSuiteNames[i]);</span><a href="#l454"></a>
<span id="l455">                    }</span><a href="#l455"></a>
<span id="l456"></span><a href="#l456"></a>
<span id="l457">                    continue;</span><a href="#l457"></a>
<span id="l458">                }</span><a href="#l458"></a>
<span id="l459"></span><a href="#l459"></a>
<span id="l460">                if (suite != null &amp;&amp; suite.isAvailable()) {</span><a href="#l460"></a>
<span id="l461">                    cipherSuites.add(suite);</span><a href="#l461"></a>
<span id="l462">                } else {</span><a href="#l462"></a>
<span id="l463">                    if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,sslctx&quot;)) {</span><a href="#l463"></a>
<span id="l464">                        SSLLogger.fine(</span><a href="#l464"></a>
<span id="l465">                                &quot;The current installed providers do not &quot; +</span><a href="#l465"></a>
<span id="l466">                                &quot;support cipher suite: &quot; + cipherSuiteNames[i]);</span><a href="#l466"></a>
<span id="l467">                    }</span><a href="#l467"></a>
<span id="l468">                }</span><a href="#l468"></a>
<span id="l469">            }</span><a href="#l469"></a>
<span id="l470"></span><a href="#l470"></a>
<span id="l471">            return cipherSuites;</span><a href="#l471"></a>
<span id="l472">        }</span><a href="#l472"></a>
<span id="l473"></span><a href="#l473"></a>
<span id="l474">        return Collections.emptyList();</span><a href="#l474"></a>
<span id="l475">    }</span><a href="#l475"></a>
<span id="l476"></span><a href="#l476"></a>
<span id="l477"></span><a href="#l477"></a>
<span id="l478">    private static List&lt;ProtocolVersion&gt; getAvailableProtocols(</span><a href="#l478"></a>
<span id="l479">            ProtocolVersion[] protocolCandidates) {</span><a href="#l479"></a>
<span id="l480"></span><a href="#l480"></a>
<span id="l481">        List&lt;ProtocolVersion&gt; availableProtocols =</span><a href="#l481"></a>
<span id="l482">                Collections.&lt;ProtocolVersion&gt;emptyList();</span><a href="#l482"></a>
<span id="l483">        if (protocolCandidates != null &amp;&amp; protocolCandidates.length != 0) {</span><a href="#l483"></a>
<span id="l484">            availableProtocols = new ArrayList&lt;&gt;(protocolCandidates.length);</span><a href="#l484"></a>
<span id="l485">            for (ProtocolVersion p : protocolCandidates) {</span><a href="#l485"></a>
<span id="l486">                if (p.isAvailable) {</span><a href="#l486"></a>
<span id="l487">                    availableProtocols.add(p);</span><a href="#l487"></a>
<span id="l488">                }</span><a href="#l488"></a>
<span id="l489">            }</span><a href="#l489"></a>
<span id="l490">        }</span><a href="#l490"></a>
<span id="l491"></span><a href="#l491"></a>
<span id="l492">        return availableProtocols;</span><a href="#l492"></a>
<span id="l493">    }</span><a href="#l493"></a>
<span id="l494"></span><a href="#l494"></a>
<span id="l495">    /*</span><a href="#l495"></a>
<span id="l496">     * The SSLContext implementation for SSL/TLS algorithm</span><a href="#l496"></a>
<span id="l497">     *</span><a href="#l497"></a>
<span id="l498">     * SSL/TLS protocols specify the forward compatibility and version</span><a href="#l498"></a>
<span id="l499">     * roll-back attack protections, however, a number of SSL/TLS server</span><a href="#l499"></a>
<span id="l500">     * vendors did not implement these aspects properly, and some current</span><a href="#l500"></a>
<span id="l501">     * SSL/TLS servers may refuse to talk to a TLS 1.1 or later client.</span><a href="#l501"></a>
<span id="l502">     *</span><a href="#l502"></a>
<span id="l503">     * Considering above interoperability issues, SunJSSE will not set</span><a href="#l503"></a>
<span id="l504">     * TLS 1.1 and TLS 1.2 as the enabled protocols for client by default.</span><a href="#l504"></a>
<span id="l505">     *</span><a href="#l505"></a>
<span id="l506">     * For SSL/TLS servers, there is no such interoperability issues as</span><a href="#l506"></a>
<span id="l507">     * SSL/TLS clients. In SunJSSE, TLS 1.1 or later version will be the</span><a href="#l507"></a>
<span id="l508">     * enabled protocols for server by default.</span><a href="#l508"></a>
<span id="l509">     *</span><a href="#l509"></a>
<span id="l510">     * We may change the behavior when popular TLS/SSL vendors support TLS</span><a href="#l510"></a>
<span id="l511">     * forward compatibility properly.</span><a href="#l511"></a>
<span id="l512">     *</span><a href="#l512"></a>
<span id="l513">     * SSLv2Hello is no longer necessary.  This interoperability option was</span><a href="#l513"></a>
<span id="l514">     * put in place in the late 90's when SSLv3/TLS1.0 were relatively new</span><a href="#l514"></a>
<span id="l515">     * and there were a fair number of SSLv2-only servers deployed.  Because</span><a href="#l515"></a>
<span id="l516">     * of the security issues in SSLv2, it is rarely (if ever) used, as</span><a href="#l516"></a>
<span id="l517">     * deployments should now be using SSLv3 and TLSv1.</span><a href="#l517"></a>
<span id="l518">     *</span><a href="#l518"></a>
<span id="l519">     * Considering the issues of SSLv2Hello, we should not enable SSLv2Hello</span><a href="#l519"></a>
<span id="l520">     * by default. Applications still can use it by enabling SSLv2Hello with</span><a href="#l520"></a>
<span id="l521">     * the series of setEnabledProtocols APIs.</span><a href="#l521"></a>
<span id="l522">     */</span><a href="#l522"></a>
<span id="l523"></span><a href="#l523"></a>
<span id="l524">    /*</span><a href="#l524"></a>
<span id="l525">     * The base abstract SSLContext implementation for the Transport Layer</span><a href="#l525"></a>
<span id="l526">     * Security (TLS) protocols.</span><a href="#l526"></a>
<span id="l527">     *</span><a href="#l527"></a>
<span id="l528">     * This abstract class encapsulates supported and the default server</span><a href="#l528"></a>
<span id="l529">     * SSL/TLS parameters.</span><a href="#l529"></a>
<span id="l530">     *</span><a href="#l530"></a>
<span id="l531">     * @see SSLContext</span><a href="#l531"></a>
<span id="l532">     */</span><a href="#l532"></a>
<span id="l533">    private abstract static class AbstractTLSContext extends SSLContextImpl {</span><a href="#l533"></a>
<span id="l534">        private static final List&lt;ProtocolVersion&gt; supportedProtocols;</span><a href="#l534"></a>
<span id="l535">        private static final List&lt;ProtocolVersion&gt; serverDefaultProtocols;</span><a href="#l535"></a>
<span id="l536"></span><a href="#l536"></a>
<span id="l537">        private static final List&lt;CipherSuite&gt; supportedCipherSuites;</span><a href="#l537"></a>
<span id="l538">        private static final List&lt;CipherSuite&gt; serverDefaultCipherSuites;</span><a href="#l538"></a>
<span id="l539"></span><a href="#l539"></a>
<span id="l540">        static {</span><a href="#l540"></a>
<span id="l541">            if (SunJSSE.isFIPS()) {</span><a href="#l541"></a>
<span id="l542">                supportedProtocols = Arrays.asList(</span><a href="#l542"></a>
<span id="l543">                    ProtocolVersion.TLS13,</span><a href="#l543"></a>
<span id="l544">                    ProtocolVersion.TLS12,</span><a href="#l544"></a>
<span id="l545">                    ProtocolVersion.TLS11,</span><a href="#l545"></a>
<span id="l546">                    ProtocolVersion.TLS10</span><a href="#l546"></a>
<span id="l547">                );</span><a href="#l547"></a>
<span id="l548"></span><a href="#l548"></a>
<span id="l549">                serverDefaultProtocols = getAvailableProtocols(</span><a href="#l549"></a>
<span id="l550">                        new ProtocolVersion[] {</span><a href="#l550"></a>
<span id="l551">                    ProtocolVersion.TLS13,</span><a href="#l551"></a>
<span id="l552">                    ProtocolVersion.TLS12,</span><a href="#l552"></a>
<span id="l553">                    ProtocolVersion.TLS11,</span><a href="#l553"></a>
<span id="l554">                    ProtocolVersion.TLS10</span><a href="#l554"></a>
<span id="l555">                });</span><a href="#l555"></a>
<span id="l556">            } else {</span><a href="#l556"></a>
<span id="l557">                supportedProtocols = Arrays.asList(</span><a href="#l557"></a>
<span id="l558">                    ProtocolVersion.TLS13,</span><a href="#l558"></a>
<span id="l559">                    ProtocolVersion.TLS12,</span><a href="#l559"></a>
<span id="l560">                    ProtocolVersion.TLS11,</span><a href="#l560"></a>
<span id="l561">                    ProtocolVersion.TLS10,</span><a href="#l561"></a>
<span id="l562">                    ProtocolVersion.SSL30,</span><a href="#l562"></a>
<span id="l563">                    ProtocolVersion.SSL20Hello</span><a href="#l563"></a>
<span id="l564">                );</span><a href="#l564"></a>
<span id="l565"></span><a href="#l565"></a>
<span id="l566">                serverDefaultProtocols = getAvailableProtocols(</span><a href="#l566"></a>
<span id="l567">                        new ProtocolVersion[] {</span><a href="#l567"></a>
<span id="l568">                    ProtocolVersion.TLS13,</span><a href="#l568"></a>
<span id="l569">                    ProtocolVersion.TLS12,</span><a href="#l569"></a>
<span id="l570">                    ProtocolVersion.TLS11,</span><a href="#l570"></a>
<span id="l571">                    ProtocolVersion.TLS10,</span><a href="#l571"></a>
<span id="l572">                    ProtocolVersion.SSL30,</span><a href="#l572"></a>
<span id="l573">                    ProtocolVersion.SSL20Hello</span><a href="#l573"></a>
<span id="l574">                });</span><a href="#l574"></a>
<span id="l575">            }</span><a href="#l575"></a>
<span id="l576"></span><a href="#l576"></a>
<span id="l577">            supportedCipherSuites = getApplicableSupportedCipherSuites(</span><a href="#l577"></a>
<span id="l578">                    supportedProtocols);</span><a href="#l578"></a>
<span id="l579">            serverDefaultCipherSuites = getApplicableEnabledCipherSuites(</span><a href="#l579"></a>
<span id="l580">                    serverDefaultProtocols, false);</span><a href="#l580"></a>
<span id="l581">        }</span><a href="#l581"></a>
<span id="l582"></span><a href="#l582"></a>
<span id="l583">        @Override</span><a href="#l583"></a>
<span id="l584">        List&lt;ProtocolVersion&gt; getSupportedProtocolVersions() {</span><a href="#l584"></a>
<span id="l585">            return supportedProtocols;</span><a href="#l585"></a>
<span id="l586">        }</span><a href="#l586"></a>
<span id="l587"></span><a href="#l587"></a>
<span id="l588">        @Override</span><a href="#l588"></a>
<span id="l589">        List&lt;CipherSuite&gt; getSupportedCipherSuites() {</span><a href="#l589"></a>
<span id="l590">            return supportedCipherSuites;</span><a href="#l590"></a>
<span id="l591">        }</span><a href="#l591"></a>
<span id="l592"></span><a href="#l592"></a>
<span id="l593">        @Override</span><a href="#l593"></a>
<span id="l594">        List&lt;ProtocolVersion&gt; getServerDefaultProtocolVersions() {</span><a href="#l594"></a>
<span id="l595">            return serverDefaultProtocols;</span><a href="#l595"></a>
<span id="l596">        }</span><a href="#l596"></a>
<span id="l597"></span><a href="#l597"></a>
<span id="l598">        @Override</span><a href="#l598"></a>
<span id="l599">        List&lt;CipherSuite&gt; getServerDefaultCipherSuites() {</span><a href="#l599"></a>
<span id="l600">            return serverDefaultCipherSuites;</span><a href="#l600"></a>
<span id="l601">        }</span><a href="#l601"></a>
<span id="l602"></span><a href="#l602"></a>
<span id="l603">        @Override</span><a href="#l603"></a>
<span id="l604">        SSLEngine createSSLEngineImpl() {</span><a href="#l604"></a>
<span id="l605">            return new SSLEngineImpl(this);</span><a href="#l605"></a>
<span id="l606">        }</span><a href="#l606"></a>
<span id="l607"></span><a href="#l607"></a>
<span id="l608">        @Override</span><a href="#l608"></a>
<span id="l609">        SSLEngine createSSLEngineImpl(String host, int port) {</span><a href="#l609"></a>
<span id="l610">            return new SSLEngineImpl(this, host, port);</span><a href="#l610"></a>
<span id="l611">        }</span><a href="#l611"></a>
<span id="l612"></span><a href="#l612"></a>
<span id="l613">        static ProtocolVersion[] getSupportedProtocols() {</span><a href="#l613"></a>
<span id="l614">            if (SunJSSE.isFIPS()) {</span><a href="#l614"></a>
<span id="l615">                return new ProtocolVersion[] {</span><a href="#l615"></a>
<span id="l616">                        ProtocolVersion.TLS13,</span><a href="#l616"></a>
<span id="l617">                        ProtocolVersion.TLS12,</span><a href="#l617"></a>
<span id="l618">                        ProtocolVersion.TLS11,</span><a href="#l618"></a>
<span id="l619">                        ProtocolVersion.TLS10</span><a href="#l619"></a>
<span id="l620">                };</span><a href="#l620"></a>
<span id="l621">            } else {</span><a href="#l621"></a>
<span id="l622">                return new ProtocolVersion[]{</span><a href="#l622"></a>
<span id="l623">                        ProtocolVersion.TLS13,</span><a href="#l623"></a>
<span id="l624">                        ProtocolVersion.TLS12,</span><a href="#l624"></a>
<span id="l625">                        ProtocolVersion.TLS11,</span><a href="#l625"></a>
<span id="l626">                        ProtocolVersion.TLS10,</span><a href="#l626"></a>
<span id="l627">                        ProtocolVersion.SSL30,</span><a href="#l627"></a>
<span id="l628">                        ProtocolVersion.SSL20Hello</span><a href="#l628"></a>
<span id="l629">                };</span><a href="#l629"></a>
<span id="l630">            }</span><a href="#l630"></a>
<span id="l631">        }</span><a href="#l631"></a>
<span id="l632">    }</span><a href="#l632"></a>
<span id="l633"></span><a href="#l633"></a>
<span id="l634">    /*</span><a href="#l634"></a>
<span id="l635">     * The SSLContext implementation for SSLv3 and TLS10 algorithm</span><a href="#l635"></a>
<span id="l636">     *</span><a href="#l636"></a>
<span id="l637">     * @see SSLContext</span><a href="#l637"></a>
<span id="l638">     */</span><a href="#l638"></a>
<span id="l639">    public static final class TLS10Context extends AbstractTLSContext {</span><a href="#l639"></a>
<span id="l640">        private static final List&lt;ProtocolVersion&gt; clientDefaultProtocols;</span><a href="#l640"></a>
<span id="l641">        private static final List&lt;CipherSuite&gt; clientDefaultCipherSuites;</span><a href="#l641"></a>
<span id="l642"></span><a href="#l642"></a>
<span id="l643">        static {</span><a href="#l643"></a>
<span id="l644">            if (SunJSSE.isFIPS()) {</span><a href="#l644"></a>
<span id="l645">                clientDefaultProtocols = getAvailableProtocols(</span><a href="#l645"></a>
<span id="l646">                        new ProtocolVersion[] {</span><a href="#l646"></a>
<span id="l647">                    ProtocolVersion.TLS10</span><a href="#l647"></a>
<span id="l648">                });</span><a href="#l648"></a>
<span id="l649">            } else {</span><a href="#l649"></a>
<span id="l650">                clientDefaultProtocols = getAvailableProtocols(</span><a href="#l650"></a>
<span id="l651">                        new ProtocolVersion[] {</span><a href="#l651"></a>
<span id="l652">                    ProtocolVersion.TLS10,</span><a href="#l652"></a>
<span id="l653">                    ProtocolVersion.SSL30</span><a href="#l653"></a>
<span id="l654">                });</span><a href="#l654"></a>
<span id="l655">            }</span><a href="#l655"></a>
<span id="l656"></span><a href="#l656"></a>
<span id="l657">            clientDefaultCipherSuites = getApplicableEnabledCipherSuites(</span><a href="#l657"></a>
<span id="l658">                    clientDefaultProtocols, true);</span><a href="#l658"></a>
<span id="l659">        }</span><a href="#l659"></a>
<span id="l660"></span><a href="#l660"></a>
<span id="l661">        @Override</span><a href="#l661"></a>
<span id="l662">        List&lt;ProtocolVersion&gt; getClientDefaultProtocolVersions() {</span><a href="#l662"></a>
<span id="l663">            return clientDefaultProtocols;</span><a href="#l663"></a>
<span id="l664">        }</span><a href="#l664"></a>
<span id="l665"></span><a href="#l665"></a>
<span id="l666">        @Override</span><a href="#l666"></a>
<span id="l667">        List&lt;CipherSuite&gt; getClientDefaultCipherSuites() {</span><a href="#l667"></a>
<span id="l668">            return clientDefaultCipherSuites;</span><a href="#l668"></a>
<span id="l669">        }</span><a href="#l669"></a>
<span id="l670">    }</span><a href="#l670"></a>
<span id="l671"></span><a href="#l671"></a>
<span id="l672">    /*</span><a href="#l672"></a>
<span id="l673">     * The SSLContext implementation for TLS11 algorithm</span><a href="#l673"></a>
<span id="l674">     *</span><a href="#l674"></a>
<span id="l675">     * @see SSLContext</span><a href="#l675"></a>
<span id="l676">     */</span><a href="#l676"></a>
<span id="l677">    public static final class TLS11Context extends AbstractTLSContext {</span><a href="#l677"></a>
<span id="l678">        private static final List&lt;ProtocolVersion&gt; clientDefaultProtocols;</span><a href="#l678"></a>
<span id="l679">        private static final List&lt;CipherSuite&gt; clientDefaultCipherSuites;</span><a href="#l679"></a>
<span id="l680"></span><a href="#l680"></a>
<span id="l681">        static {</span><a href="#l681"></a>
<span id="l682">            if (SunJSSE.isFIPS()) {</span><a href="#l682"></a>
<span id="l683">                clientDefaultProtocols = getAvailableProtocols(</span><a href="#l683"></a>
<span id="l684">                        new ProtocolVersion[] {</span><a href="#l684"></a>
<span id="l685">                    ProtocolVersion.TLS11,</span><a href="#l685"></a>
<span id="l686">                    ProtocolVersion.TLS10</span><a href="#l686"></a>
<span id="l687">                });</span><a href="#l687"></a>
<span id="l688">            } else {</span><a href="#l688"></a>
<span id="l689">                clientDefaultProtocols = getAvailableProtocols(</span><a href="#l689"></a>
<span id="l690">                        new ProtocolVersion[] {</span><a href="#l690"></a>
<span id="l691">                    ProtocolVersion.TLS11,</span><a href="#l691"></a>
<span id="l692">                    ProtocolVersion.TLS10,</span><a href="#l692"></a>
<span id="l693">                    ProtocolVersion.SSL30</span><a href="#l693"></a>
<span id="l694">                });</span><a href="#l694"></a>
<span id="l695">            }</span><a href="#l695"></a>
<span id="l696"></span><a href="#l696"></a>
<span id="l697">            clientDefaultCipherSuites = getApplicableEnabledCipherSuites(</span><a href="#l697"></a>
<span id="l698">                    clientDefaultProtocols, true);</span><a href="#l698"></a>
<span id="l699"></span><a href="#l699"></a>
<span id="l700">        }</span><a href="#l700"></a>
<span id="l701"></span><a href="#l701"></a>
<span id="l702">        @Override</span><a href="#l702"></a>
<span id="l703">        List&lt;ProtocolVersion&gt; getClientDefaultProtocolVersions() {</span><a href="#l703"></a>
<span id="l704">            return clientDefaultProtocols;</span><a href="#l704"></a>
<span id="l705">        }</span><a href="#l705"></a>
<span id="l706"></span><a href="#l706"></a>
<span id="l707">        @Override</span><a href="#l707"></a>
<span id="l708">        List&lt;CipherSuite&gt; getClientDefaultCipherSuites() {</span><a href="#l708"></a>
<span id="l709">            return clientDefaultCipherSuites;</span><a href="#l709"></a>
<span id="l710">        }</span><a href="#l710"></a>
<span id="l711">    }</span><a href="#l711"></a>
<span id="l712"></span><a href="#l712"></a>
<span id="l713">    /*</span><a href="#l713"></a>
<span id="l714">     * The SSLContext implementation for TLS12 algorithm</span><a href="#l714"></a>
<span id="l715">     *</span><a href="#l715"></a>
<span id="l716">     * @see SSLContext</span><a href="#l716"></a>
<span id="l717">     */</span><a href="#l717"></a>
<span id="l718">    public static final class TLS12Context extends AbstractTLSContext {</span><a href="#l718"></a>
<span id="l719">        private static final List&lt;ProtocolVersion&gt; clientDefaultProtocols;</span><a href="#l719"></a>
<span id="l720">        private static final List&lt;CipherSuite&gt; clientDefaultCipherSuites;</span><a href="#l720"></a>
<span id="l721"></span><a href="#l721"></a>
<span id="l722">        static {</span><a href="#l722"></a>
<span id="l723">            if (SunJSSE.isFIPS()) {</span><a href="#l723"></a>
<span id="l724">                clientDefaultProtocols = getAvailableProtocols(</span><a href="#l724"></a>
<span id="l725">                        new ProtocolVersion[] {</span><a href="#l725"></a>
<span id="l726">                    ProtocolVersion.TLS12,</span><a href="#l726"></a>
<span id="l727">                    ProtocolVersion.TLS11,</span><a href="#l727"></a>
<span id="l728">                    ProtocolVersion.TLS10</span><a href="#l728"></a>
<span id="l729">                });</span><a href="#l729"></a>
<span id="l730">            } else {</span><a href="#l730"></a>
<span id="l731">                clientDefaultProtocols = getAvailableProtocols(</span><a href="#l731"></a>
<span id="l732">                        new ProtocolVersion[] {</span><a href="#l732"></a>
<span id="l733">                    ProtocolVersion.TLS12,</span><a href="#l733"></a>
<span id="l734">                    ProtocolVersion.TLS11,</span><a href="#l734"></a>
<span id="l735">                    ProtocolVersion.TLS10,</span><a href="#l735"></a>
<span id="l736">                    ProtocolVersion.SSL30</span><a href="#l736"></a>
<span id="l737">                });</span><a href="#l737"></a>
<span id="l738">            }</span><a href="#l738"></a>
<span id="l739"></span><a href="#l739"></a>
<span id="l740">            clientDefaultCipherSuites = getApplicableEnabledCipherSuites(</span><a href="#l740"></a>
<span id="l741">                    clientDefaultProtocols, true);</span><a href="#l741"></a>
<span id="l742">        }</span><a href="#l742"></a>
<span id="l743"></span><a href="#l743"></a>
<span id="l744">        @Override</span><a href="#l744"></a>
<span id="l745">        List&lt;ProtocolVersion&gt; getClientDefaultProtocolVersions() {</span><a href="#l745"></a>
<span id="l746">            return clientDefaultProtocols;</span><a href="#l746"></a>
<span id="l747">        }</span><a href="#l747"></a>
<span id="l748"></span><a href="#l748"></a>
<span id="l749">        @Override</span><a href="#l749"></a>
<span id="l750">        List&lt;CipherSuite&gt; getClientDefaultCipherSuites() {</span><a href="#l750"></a>
<span id="l751">            return clientDefaultCipherSuites;</span><a href="#l751"></a>
<span id="l752">        }</span><a href="#l752"></a>
<span id="l753">    }</span><a href="#l753"></a>
<span id="l754"></span><a href="#l754"></a>
<span id="l755">    /*</span><a href="#l755"></a>
<span id="l756">     * The SSLContext implementation for TLS1.3 algorithm</span><a href="#l756"></a>
<span id="l757">     *</span><a href="#l757"></a>
<span id="l758">     * @see SSLContext</span><a href="#l758"></a>
<span id="l759">     */</span><a href="#l759"></a>
<span id="l760">    public static final class TLS13Context extends AbstractTLSContext {</span><a href="#l760"></a>
<span id="l761">        private static final List&lt;ProtocolVersion&gt; clientDefaultProtocols;</span><a href="#l761"></a>
<span id="l762">        private static final List&lt;CipherSuite&gt; clientDefaultCipherSuites;</span><a href="#l762"></a>
<span id="l763"></span><a href="#l763"></a>
<span id="l764">        static {</span><a href="#l764"></a>
<span id="l765">            if (SunJSSE.isFIPS()) {</span><a href="#l765"></a>
<span id="l766">                clientDefaultProtocols = getAvailableProtocols(</span><a href="#l766"></a>
<span id="l767">                        new ProtocolVersion[] {</span><a href="#l767"></a>
<span id="l768">                    ProtocolVersion.TLS13,</span><a href="#l768"></a>
<span id="l769">                    ProtocolVersion.TLS12,</span><a href="#l769"></a>
<span id="l770">                    ProtocolVersion.TLS11,</span><a href="#l770"></a>
<span id="l771">                    ProtocolVersion.TLS10</span><a href="#l771"></a>
<span id="l772">                });</span><a href="#l772"></a>
<span id="l773">            } else {</span><a href="#l773"></a>
<span id="l774">                clientDefaultProtocols = getAvailableProtocols(</span><a href="#l774"></a>
<span id="l775">                        new ProtocolVersion[] {</span><a href="#l775"></a>
<span id="l776">                    ProtocolVersion.TLS13,</span><a href="#l776"></a>
<span id="l777">                    ProtocolVersion.TLS12,</span><a href="#l777"></a>
<span id="l778">                    ProtocolVersion.TLS11,</span><a href="#l778"></a>
<span id="l779">                    ProtocolVersion.TLS10,</span><a href="#l779"></a>
<span id="l780">                    ProtocolVersion.SSL30</span><a href="#l780"></a>
<span id="l781">                });</span><a href="#l781"></a>
<span id="l782">            }</span><a href="#l782"></a>
<span id="l783"></span><a href="#l783"></a>
<span id="l784">            clientDefaultCipherSuites = getApplicableEnabledCipherSuites(</span><a href="#l784"></a>
<span id="l785">                    clientDefaultProtocols, true);</span><a href="#l785"></a>
<span id="l786">        }</span><a href="#l786"></a>
<span id="l787"></span><a href="#l787"></a>
<span id="l788">        @Override</span><a href="#l788"></a>
<span id="l789">        List&lt;ProtocolVersion&gt; getClientDefaultProtocolVersions() {</span><a href="#l789"></a>
<span id="l790">            return clientDefaultProtocols;</span><a href="#l790"></a>
<span id="l791">        }</span><a href="#l791"></a>
<span id="l792"></span><a href="#l792"></a>
<span id="l793">        @Override</span><a href="#l793"></a>
<span id="l794">        List&lt;CipherSuite&gt; getClientDefaultCipherSuites() {</span><a href="#l794"></a>
<span id="l795">            return clientDefaultCipherSuites;</span><a href="#l795"></a>
<span id="l796">        }</span><a href="#l796"></a>
<span id="l797">    }</span><a href="#l797"></a>
<span id="l798"></span><a href="#l798"></a>
<span id="l799">    /*</span><a href="#l799"></a>
<span id="l800">     * The interface for the customized SSL/TLS SSLContext.</span><a href="#l800"></a>
<span id="l801">     *</span><a href="#l801"></a>
<span id="l802">     * @see SSLContext</span><a href="#l802"></a>
<span id="l803">     */</span><a href="#l803"></a>
<span id="l804">    private static class CustomizedSSLProtocols {</span><a href="#l804"></a>
<span id="l805">        private static final String JDK_TLS_CLIENT_PROTOCOLS =</span><a href="#l805"></a>
<span id="l806">                &quot;jdk.tls.client.protocols&quot;;</span><a href="#l806"></a>
<span id="l807">        private static final String JDK_TLS_SERVER_PROTOCOLS =</span><a href="#l807"></a>
<span id="l808">                &quot;jdk.tls.server.protocols&quot;;</span><a href="#l808"></a>
<span id="l809">        static IllegalArgumentException reservedException = null;</span><a href="#l809"></a>
<span id="l810">        static final ArrayList&lt;ProtocolVersion&gt; customizedClientProtocols =</span><a href="#l810"></a>
<span id="l811">                new ArrayList&lt;&gt;();</span><a href="#l811"></a>
<span id="l812">        static final ArrayList&lt;ProtocolVersion&gt; customizedServerProtocols =</span><a href="#l812"></a>
<span id="l813">                new ArrayList&lt;&gt;();</span><a href="#l813"></a>
<span id="l814"></span><a href="#l814"></a>
<span id="l815">        // Don't want a java.lang.LinkageError for illegal system property.</span><a href="#l815"></a>
<span id="l816">        //</span><a href="#l816"></a>
<span id="l817">        // Please don't throw exception in this static block.  Otherwise,</span><a href="#l817"></a>
<span id="l818">        // java.lang.LinkageError may be thrown during the instantiation of</span><a href="#l818"></a>
<span id="l819">        // the provider service. Instead, please handle the initialization</span><a href="#l819"></a>
<span id="l820">        // exception in the caller's constructor.</span><a href="#l820"></a>
<span id="l821">        static {</span><a href="#l821"></a>
<span id="l822">            populate(JDK_TLS_CLIENT_PROTOCOLS, customizedClientProtocols);</span><a href="#l822"></a>
<span id="l823">            populate(JDK_TLS_SERVER_PROTOCOLS, customizedServerProtocols);</span><a href="#l823"></a>
<span id="l824">        }</span><a href="#l824"></a>
<span id="l825"></span><a href="#l825"></a>
<span id="l826">        private static void populate(String propname,</span><a href="#l826"></a>
<span id="l827">                ArrayList&lt;ProtocolVersion&gt; arrayList) {</span><a href="#l827"></a>
<span id="l828">            String property = GetPropertyAction.privilegedGetProperty(propname);</span><a href="#l828"></a>
<span id="l829">            if (property == null) {</span><a href="#l829"></a>
<span id="l830">                return;</span><a href="#l830"></a>
<span id="l831">            }</span><a href="#l831"></a>
<span id="l832"></span><a href="#l832"></a>
<span id="l833">            if (!property.isEmpty()) {</span><a href="#l833"></a>
<span id="l834">                // remove double quote marks from beginning/end of the property</span><a href="#l834"></a>
<span id="l835">                if (property.length() &gt; 1 &amp;&amp; property.charAt(0) == '&quot;' &amp;&amp;</span><a href="#l835"></a>
<span id="l836">                        property.charAt(property.length() - 1) == '&quot;') {</span><a href="#l836"></a>
<span id="l837">                    property = property.substring(1, property.length() - 1);</span><a href="#l837"></a>
<span id="l838">                }</span><a href="#l838"></a>
<span id="l839">            }</span><a href="#l839"></a>
<span id="l840"></span><a href="#l840"></a>
<span id="l841">            if (!property.isEmpty()) {</span><a href="#l841"></a>
<span id="l842">                String[] protocols = property.split(&quot;,&quot;);</span><a href="#l842"></a>
<span id="l843">                for (int i = 0; i &lt; protocols.length; i++) {</span><a href="#l843"></a>
<span id="l844">                    protocols[i] = protocols[i].trim();</span><a href="#l844"></a>
<span id="l845">                    // Is it a supported protocol name?</span><a href="#l845"></a>
<span id="l846">                    ProtocolVersion pv =</span><a href="#l846"></a>
<span id="l847">                            ProtocolVersion.nameOf(protocols[i]);</span><a href="#l847"></a>
<span id="l848">                    if (pv == null) {</span><a href="#l848"></a>
<span id="l849">                        reservedException = new IllegalArgumentException(</span><a href="#l849"></a>
<span id="l850">                            propname + &quot;: &quot; + protocols[i] +</span><a href="#l850"></a>
<span id="l851">                            &quot; is not a supported SSL protocol name&quot;);</span><a href="#l851"></a>
<span id="l852">                    }</span><a href="#l852"></a>
<span id="l853"></span><a href="#l853"></a>
<span id="l854">                    if (SunJSSE.isFIPS() &amp;&amp;</span><a href="#l854"></a>
<span id="l855">                            ((pv == ProtocolVersion.SSL30) ||</span><a href="#l855"></a>
<span id="l856">                             (pv == ProtocolVersion.SSL20Hello))) {</span><a href="#l856"></a>
<span id="l857">                        reservedException = new IllegalArgumentException(</span><a href="#l857"></a>
<span id="l858">                                propname + &quot;: &quot; + pv +</span><a href="#l858"></a>
<span id="l859">                                &quot; is not FIPS compliant&quot;);</span><a href="#l859"></a>
<span id="l860"></span><a href="#l860"></a>
<span id="l861">                        break;</span><a href="#l861"></a>
<span id="l862">                    }</span><a href="#l862"></a>
<span id="l863"></span><a href="#l863"></a>
<span id="l864">                    // ignore duplicated protocols</span><a href="#l864"></a>
<span id="l865">                    if (!arrayList.contains(pv)) {</span><a href="#l865"></a>
<span id="l866">                        arrayList.add(pv);</span><a href="#l866"></a>
<span id="l867">                    }</span><a href="#l867"></a>
<span id="l868">                }</span><a href="#l868"></a>
<span id="l869">            }</span><a href="#l869"></a>
<span id="l870">        }</span><a href="#l870"></a>
<span id="l871">    }</span><a href="#l871"></a>
<span id="l872"></span><a href="#l872"></a>
<span id="l873">    /*</span><a href="#l873"></a>
<span id="l874">     * The SSLContext implementation for customized TLS protocols</span><a href="#l874"></a>
<span id="l875">     *</span><a href="#l875"></a>
<span id="l876">     * @see SSLContext</span><a href="#l876"></a>
<span id="l877">     */</span><a href="#l877"></a>
<span id="l878">    private static class CustomizedTLSContext extends AbstractTLSContext {</span><a href="#l878"></a>
<span id="l879"></span><a href="#l879"></a>
<span id="l880">        private static final List&lt;ProtocolVersion&gt; clientDefaultProtocols;</span><a href="#l880"></a>
<span id="l881">        private static final List&lt;ProtocolVersion&gt; serverDefaultProtocols;</span><a href="#l881"></a>
<span id="l882">        private static final List&lt;CipherSuite&gt; clientDefaultCipherSuites;</span><a href="#l882"></a>
<span id="l883">        private static final List&lt;CipherSuite&gt; serverDefaultCipherSuites;</span><a href="#l883"></a>
<span id="l884">        private static final IllegalArgumentException reservedException;</span><a href="#l884"></a>
<span id="l885"></span><a href="#l885"></a>
<span id="l886">        // Don't want a java.lang.LinkageError for illegal system property.</span><a href="#l886"></a>
<span id="l887">        //</span><a href="#l887"></a>
<span id="l888">        // Please don't throw exception in this static block.  Otherwise,</span><a href="#l888"></a>
<span id="l889">        // java.lang.LinkageError may be thrown during the instantiation of</span><a href="#l889"></a>
<span id="l890">        // the provider service. Instead, let's handle the initialization</span><a href="#l890"></a>
<span id="l891">        // exception in constructor.</span><a href="#l891"></a>
<span id="l892">        static {</span><a href="#l892"></a>
<span id="l893">            reservedException = CustomizedSSLProtocols.reservedException;</span><a href="#l893"></a>
<span id="l894">            if (reservedException == null) {</span><a href="#l894"></a>
<span id="l895">                clientDefaultProtocols = customizedProtocols(true,</span><a href="#l895"></a>
<span id="l896">                        CustomizedSSLProtocols.customizedClientProtocols);</span><a href="#l896"></a>
<span id="l897">                serverDefaultProtocols = customizedProtocols(false,</span><a href="#l897"></a>
<span id="l898">                        CustomizedSSLProtocols.customizedServerProtocols);</span><a href="#l898"></a>
<span id="l899"></span><a href="#l899"></a>
<span id="l900">                clientDefaultCipherSuites =</span><a href="#l900"></a>
<span id="l901">                        getApplicableEnabledCipherSuites(</span><a href="#l901"></a>
<span id="l902">                                clientDefaultProtocols, true);</span><a href="#l902"></a>
<span id="l903">                serverDefaultCipherSuites =</span><a href="#l903"></a>
<span id="l904">                        getApplicableEnabledCipherSuites(</span><a href="#l904"></a>
<span id="l905">                                serverDefaultProtocols, false);</span><a href="#l905"></a>
<span id="l906"></span><a href="#l906"></a>
<span id="l907">            } else {</span><a href="#l907"></a>
<span id="l908">                // unlikely to be used</span><a href="#l908"></a>
<span id="l909">                clientDefaultProtocols = null;</span><a href="#l909"></a>
<span id="l910">                serverDefaultProtocols = null;</span><a href="#l910"></a>
<span id="l911">                clientDefaultCipherSuites = null;</span><a href="#l911"></a>
<span id="l912">                serverDefaultCipherSuites = null;</span><a href="#l912"></a>
<span id="l913">            }</span><a href="#l913"></a>
<span id="l914">        }</span><a href="#l914"></a>
<span id="l915"></span><a href="#l915"></a>
<span id="l916">        private static List&lt;ProtocolVersion&gt; customizedProtocols(</span><a href="#l916"></a>
<span id="l917">                boolean client, List&lt;ProtocolVersion&gt; customized) {</span><a href="#l917"></a>
<span id="l918">            List&lt;ProtocolVersion&gt; refactored = new ArrayList&lt;&gt;();</span><a href="#l918"></a>
<span id="l919">            for (ProtocolVersion pv : customized) {</span><a href="#l919"></a>
<span id="l920">                refactored.add(pv);</span><a href="#l920"></a>
<span id="l921">            }</span><a href="#l921"></a>
<span id="l922"></span><a href="#l922"></a>
<span id="l923">            // Use the default enabled protocols if no customization</span><a href="#l923"></a>
<span id="l924">            ProtocolVersion[] candidates;</span><a href="#l924"></a>
<span id="l925">            if (refactored.isEmpty()) {</span><a href="#l925"></a>
<span id="l926">                if (client) {</span><a href="#l926"></a>
<span id="l927">                    candidates = getProtocols();</span><a href="#l927"></a>
<span id="l928">                } else {</span><a href="#l928"></a>
<span id="l929">                    candidates = getSupportedProtocols();</span><a href="#l929"></a>
<span id="l930">                }</span><a href="#l930"></a>
<span id="l931">            } else {</span><a href="#l931"></a>
<span id="l932">                // Use the customized TLS protocols.</span><a href="#l932"></a>
<span id="l933">                candidates =</span><a href="#l933"></a>
<span id="l934">                    refactored.toArray(new ProtocolVersion[refactored.size()]);</span><a href="#l934"></a>
<span id="l935">            }</span><a href="#l935"></a>
<span id="l936"></span><a href="#l936"></a>
<span id="l937">            return getAvailableProtocols(candidates);</span><a href="#l937"></a>
<span id="l938">        }</span><a href="#l938"></a>
<span id="l939"></span><a href="#l939"></a>
<span id="l940">        static ProtocolVersion[] getProtocols() {</span><a href="#l940"></a>
<span id="l941">            if (SunJSSE.isFIPS()) {</span><a href="#l941"></a>
<span id="l942">                return new ProtocolVersion[]{</span><a href="#l942"></a>
<span id="l943">                        ProtocolVersion.TLS12,</span><a href="#l943"></a>
<span id="l944">                        ProtocolVersion.TLS11,</span><a href="#l944"></a>
<span id="l945">                        ProtocolVersion.TLS10</span><a href="#l945"></a>
<span id="l946">                };</span><a href="#l946"></a>
<span id="l947">            } else {</span><a href="#l947"></a>
<span id="l948">                return new ProtocolVersion[]{</span><a href="#l948"></a>
<span id="l949">                        ProtocolVersion.TLS12,</span><a href="#l949"></a>
<span id="l950">                        ProtocolVersion.TLS11,</span><a href="#l950"></a>
<span id="l951">                        ProtocolVersion.TLS10,</span><a href="#l951"></a>
<span id="l952">                        ProtocolVersion.SSL30</span><a href="#l952"></a>
<span id="l953">                };</span><a href="#l953"></a>
<span id="l954">            }</span><a href="#l954"></a>
<span id="l955">        }</span><a href="#l955"></a>
<span id="l956"></span><a href="#l956"></a>
<span id="l957">        protected CustomizedTLSContext() {</span><a href="#l957"></a>
<span id="l958">            if (reservedException != null) {</span><a href="#l958"></a>
<span id="l959">                throw reservedException;</span><a href="#l959"></a>
<span id="l960">            }</span><a href="#l960"></a>
<span id="l961">        }</span><a href="#l961"></a>
<span id="l962"></span><a href="#l962"></a>
<span id="l963">        @Override</span><a href="#l963"></a>
<span id="l964">        List&lt;ProtocolVersion&gt; getClientDefaultProtocolVersions() {</span><a href="#l964"></a>
<span id="l965">            return clientDefaultProtocols;</span><a href="#l965"></a>
<span id="l966">        }</span><a href="#l966"></a>
<span id="l967"></span><a href="#l967"></a>
<span id="l968">        @Override</span><a href="#l968"></a>
<span id="l969">        List&lt;ProtocolVersion&gt; getServerDefaultProtocolVersions() {</span><a href="#l969"></a>
<span id="l970">            return serverDefaultProtocols;</span><a href="#l970"></a>
<span id="l971">        }</span><a href="#l971"></a>
<span id="l972"></span><a href="#l972"></a>
<span id="l973">        @Override</span><a href="#l973"></a>
<span id="l974">        List&lt;CipherSuite&gt; getClientDefaultCipherSuites() {</span><a href="#l974"></a>
<span id="l975">            return clientDefaultCipherSuites;</span><a href="#l975"></a>
<span id="l976">        }</span><a href="#l976"></a>
<span id="l977"></span><a href="#l977"></a>
<span id="l978">        @Override</span><a href="#l978"></a>
<span id="l979">        List&lt;CipherSuite&gt; getServerDefaultCipherSuites() {</span><a href="#l979"></a>
<span id="l980">            return serverDefaultCipherSuites;</span><a href="#l980"></a>
<span id="l981">        }</span><a href="#l981"></a>
<span id="l982"></span><a href="#l982"></a>
<span id="l983"></span><a href="#l983"></a>
<span id="l984">    }</span><a href="#l984"></a>
<span id="l985"></span><a href="#l985"></a>
<span id="l986">    /*</span><a href="#l986"></a>
<span id="l987">     * The SSLContext implementation for default &quot;TLS&quot; algorithm</span><a href="#l987"></a>
<span id="l988">     *</span><a href="#l988"></a>
<span id="l989">     * @see SSLContext</span><a href="#l989"></a>
<span id="l990">     */</span><a href="#l990"></a>
<span id="l991">    public static final class TLSContext extends CustomizedTLSContext {</span><a href="#l991"></a>
<span id="l992">        // use the default constructor and methods</span><a href="#l992"></a>
<span id="l993">    }</span><a href="#l993"></a>
<span id="l994"></span><a href="#l994"></a>
<span id="l995">    // lazy initialization holder class idiom for static default parameters</span><a href="#l995"></a>
<span id="l996">    //</span><a href="#l996"></a>
<span id="l997">    // See Effective Java Second Edition: Item 71.</span><a href="#l997"></a>
<span id="l998">    private static final class DefaultManagersHolder {</span><a href="#l998"></a>
<span id="l999">        private static final String NONE = &quot;NONE&quot;;</span><a href="#l999"></a>
<span id="l1000">        private static final String P11KEYSTORE = &quot;PKCS11&quot;;</span><a href="#l1000"></a>
<span id="l1001"></span><a href="#l1001"></a>
<span id="l1002">        private static final TrustManager[] trustManagers;</span><a href="#l1002"></a>
<span id="l1003">        private static final KeyManager[] keyManagers;</span><a href="#l1003"></a>
<span id="l1004"></span><a href="#l1004"></a>
<span id="l1005">        private static final Exception reservedException;</span><a href="#l1005"></a>
<span id="l1006"></span><a href="#l1006"></a>
<span id="l1007">        static {</span><a href="#l1007"></a>
<span id="l1008">            Exception reserved = null;</span><a href="#l1008"></a>
<span id="l1009">            TrustManager[] tmMediator;</span><a href="#l1009"></a>
<span id="l1010">            try {</span><a href="#l1010"></a>
<span id="l1011">                tmMediator = getTrustManagers();</span><a href="#l1011"></a>
<span id="l1012">            } catch (Exception e) {</span><a href="#l1012"></a>
<span id="l1013">                reserved = e;</span><a href="#l1013"></a>
<span id="l1014">                tmMediator = new TrustManager[0];</span><a href="#l1014"></a>
<span id="l1015">            }</span><a href="#l1015"></a>
<span id="l1016">            trustManagers = tmMediator;</span><a href="#l1016"></a>
<span id="l1017"></span><a href="#l1017"></a>
<span id="l1018">            if (reserved == null) {</span><a href="#l1018"></a>
<span id="l1019">                KeyManager[] kmMediator;</span><a href="#l1019"></a>
<span id="l1020">                try {</span><a href="#l1020"></a>
<span id="l1021">                    kmMediator = getKeyManagers();</span><a href="#l1021"></a>
<span id="l1022">                } catch (Exception e) {</span><a href="#l1022"></a>
<span id="l1023">                    reserved = e;</span><a href="#l1023"></a>
<span id="l1024">                    kmMediator = new KeyManager[0];</span><a href="#l1024"></a>
<span id="l1025">                }</span><a href="#l1025"></a>
<span id="l1026">                keyManagers = kmMediator;</span><a href="#l1026"></a>
<span id="l1027">            } else {</span><a href="#l1027"></a>
<span id="l1028">                keyManagers = new KeyManager[0];</span><a href="#l1028"></a>
<span id="l1029">            }</span><a href="#l1029"></a>
<span id="l1030"></span><a href="#l1030"></a>
<span id="l1031">            reservedException = reserved;</span><a href="#l1031"></a>
<span id="l1032">        }</span><a href="#l1032"></a>
<span id="l1033"></span><a href="#l1033"></a>
<span id="l1034">        private static TrustManager[] getTrustManagers() throws Exception {</span><a href="#l1034"></a>
<span id="l1035">            TrustManagerFactory tmf = TrustManagerFactory.getInstance(</span><a href="#l1035"></a>
<span id="l1036">                    TrustManagerFactory.getDefaultAlgorithm());</span><a href="#l1036"></a>
<span id="l1037">            if (&quot;SunJSSE&quot;.equals(tmf.getProvider().getName())) {</span><a href="#l1037"></a>
<span id="l1038">                // The implementation will load the default KeyStore</span><a href="#l1038"></a>
<span id="l1039">                // automatically.  Cached trust materials may be used</span><a href="#l1039"></a>
<span id="l1040">                // for performance improvement.</span><a href="#l1040"></a>
<span id="l1041">                tmf.init((KeyStore)null);</span><a href="#l1041"></a>
<span id="l1042">            } else {</span><a href="#l1042"></a>
<span id="l1043">                // Use the explicitly specified KeyStore for third party's</span><a href="#l1043"></a>
<span id="l1044">                // TrustManagerFactory implementation.</span><a href="#l1044"></a>
<span id="l1045">                KeyStore ks = TrustStoreManager.getTrustedKeyStore();</span><a href="#l1045"></a>
<span id="l1046">                tmf.init(ks);</span><a href="#l1046"></a>
<span id="l1047">            }</span><a href="#l1047"></a>
<span id="l1048"></span><a href="#l1048"></a>
<span id="l1049">            return tmf.getTrustManagers();</span><a href="#l1049"></a>
<span id="l1050">        }</span><a href="#l1050"></a>
<span id="l1051"></span><a href="#l1051"></a>
<span id="l1052">        private static KeyManager[] getKeyManagers() throws Exception {</span><a href="#l1052"></a>
<span id="l1053"></span><a href="#l1053"></a>
<span id="l1054">            final Map&lt;String,String&gt; props = new HashMap&lt;&gt;();</span><a href="#l1054"></a>
<span id="l1055">            AccessController.doPrivileged(</span><a href="#l1055"></a>
<span id="l1056">                        new PrivilegedExceptionAction&lt;Object&gt;() {</span><a href="#l1056"></a>
<span id="l1057">                @Override</span><a href="#l1057"></a>
<span id="l1058">                public Object run() throws Exception {</span><a href="#l1058"></a>
<span id="l1059">                    props.put(&quot;keyStore&quot;,  System.getProperty(</span><a href="#l1059"></a>
<span id="l1060">                                &quot;javax.net.ssl.keyStore&quot;, &quot;&quot;));</span><a href="#l1060"></a>
<span id="l1061">                    props.put(&quot;keyStoreType&quot;, System.getProperty(</span><a href="#l1061"></a>
<span id="l1062">                                &quot;javax.net.ssl.keyStoreType&quot;,</span><a href="#l1062"></a>
<span id="l1063">                                KeyStore.getDefaultType()));</span><a href="#l1063"></a>
<span id="l1064">                    props.put(&quot;keyStoreProvider&quot;, System.getProperty(</span><a href="#l1064"></a>
<span id="l1065">                                &quot;javax.net.ssl.keyStoreProvider&quot;, &quot;&quot;));</span><a href="#l1065"></a>
<span id="l1066">                    props.put(&quot;keyStorePasswd&quot;, System.getProperty(</span><a href="#l1066"></a>
<span id="l1067">                                &quot;javax.net.ssl.keyStorePassword&quot;, &quot;&quot;));</span><a href="#l1067"></a>
<span id="l1068">                    return null;</span><a href="#l1068"></a>
<span id="l1069">                }</span><a href="#l1069"></a>
<span id="l1070">            });</span><a href="#l1070"></a>
<span id="l1071"></span><a href="#l1071"></a>
<span id="l1072">            final String defaultKeyStore = props.get(&quot;keyStore&quot;);</span><a href="#l1072"></a>
<span id="l1073">            String defaultKeyStoreType = props.get(&quot;keyStoreType&quot;);</span><a href="#l1073"></a>
<span id="l1074">            String defaultKeyStoreProvider = props.get(&quot;keyStoreProvider&quot;);</span><a href="#l1074"></a>
<span id="l1075">            if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,defaultctx&quot;)) {</span><a href="#l1075"></a>
<span id="l1076">                SSLLogger.fine(&quot;keyStore is : &quot; + defaultKeyStore);</span><a href="#l1076"></a>
<span id="l1077">                SSLLogger.fine(&quot;keyStore type is : &quot; +</span><a href="#l1077"></a>
<span id="l1078">                                        defaultKeyStoreType);</span><a href="#l1078"></a>
<span id="l1079">                SSLLogger.fine(&quot;keyStore provider is : &quot; +</span><a href="#l1079"></a>
<span id="l1080">                                        defaultKeyStoreProvider);</span><a href="#l1080"></a>
<span id="l1081">            }</span><a href="#l1081"></a>
<span id="l1082"></span><a href="#l1082"></a>
<span id="l1083">            if (P11KEYSTORE.equals(defaultKeyStoreType) &amp;&amp;</span><a href="#l1083"></a>
<span id="l1084">                    !NONE.equals(defaultKeyStore)) {</span><a href="#l1084"></a>
<span id="l1085">                throw new IllegalArgumentException(&quot;if keyStoreType is &quot;</span><a href="#l1085"></a>
<span id="l1086">                    + P11KEYSTORE + &quot;, then keyStore must be &quot; + NONE);</span><a href="#l1086"></a>
<span id="l1087">            }</span><a href="#l1087"></a>
<span id="l1088"></span><a href="#l1088"></a>
<span id="l1089">            FileInputStream fs = null;</span><a href="#l1089"></a>
<span id="l1090">            KeyStore ks = null;</span><a href="#l1090"></a>
<span id="l1091">            char[] passwd = null;</span><a href="#l1091"></a>
<span id="l1092">            try {</span><a href="#l1092"></a>
<span id="l1093">                if (!defaultKeyStore.isEmpty() &amp;&amp;</span><a href="#l1093"></a>
<span id="l1094">                        !NONE.equals(defaultKeyStore)) {</span><a href="#l1094"></a>
<span id="l1095">                    fs = AccessController.doPrivileged(</span><a href="#l1095"></a>
<span id="l1096">                            new PrivilegedExceptionAction&lt;FileInputStream&gt;() {</span><a href="#l1096"></a>
<span id="l1097">                        @Override</span><a href="#l1097"></a>
<span id="l1098">                        public FileInputStream run() throws Exception {</span><a href="#l1098"></a>
<span id="l1099">                            return new FileInputStream(defaultKeyStore);</span><a href="#l1099"></a>
<span id="l1100">                        }</span><a href="#l1100"></a>
<span id="l1101">                    });</span><a href="#l1101"></a>
<span id="l1102">                }</span><a href="#l1102"></a>
<span id="l1103"></span><a href="#l1103"></a>
<span id="l1104">                String defaultKeyStorePassword = props.get(&quot;keyStorePasswd&quot;);</span><a href="#l1104"></a>
<span id="l1105">                if (!defaultKeyStorePassword.isEmpty()) {</span><a href="#l1105"></a>
<span id="l1106">                    passwd = defaultKeyStorePassword.toCharArray();</span><a href="#l1106"></a>
<span id="l1107">                }</span><a href="#l1107"></a>
<span id="l1108"></span><a href="#l1108"></a>
<span id="l1109">                /**</span><a href="#l1109"></a>
<span id="l1110">                 * Try to initialize key store.</span><a href="#l1110"></a>
<span id="l1111">                 */</span><a href="#l1111"></a>
<span id="l1112">                if ((defaultKeyStoreType.length()) != 0) {</span><a href="#l1112"></a>
<span id="l1113">                    if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,defaultctx&quot;)) {</span><a href="#l1113"></a>
<span id="l1114">                        SSLLogger.finest(&quot;init keystore&quot;);</span><a href="#l1114"></a>
<span id="l1115">                    }</span><a href="#l1115"></a>
<span id="l1116">                    if (defaultKeyStoreProvider.isEmpty()) {</span><a href="#l1116"></a>
<span id="l1117">                        ks = KeyStore.getInstance(defaultKeyStoreType);</span><a href="#l1117"></a>
<span id="l1118">                    } else {</span><a href="#l1118"></a>
<span id="l1119">                        ks = KeyStore.getInstance(defaultKeyStoreType,</span><a href="#l1119"></a>
<span id="l1120">                                            defaultKeyStoreProvider);</span><a href="#l1120"></a>
<span id="l1121">                    }</span><a href="#l1121"></a>
<span id="l1122"></span><a href="#l1122"></a>
<span id="l1123">                    // if defaultKeyStore is NONE, fs will be null</span><a href="#l1123"></a>
<span id="l1124">                    ks.load(fs, passwd);</span><a href="#l1124"></a>
<span id="l1125">                }</span><a href="#l1125"></a>
<span id="l1126">            } finally {</span><a href="#l1126"></a>
<span id="l1127">                if (fs != null) {</span><a href="#l1127"></a>
<span id="l1128">                    fs.close();</span><a href="#l1128"></a>
<span id="l1129">                    fs = null;</span><a href="#l1129"></a>
<span id="l1130">                }</span><a href="#l1130"></a>
<span id="l1131">            }</span><a href="#l1131"></a>
<span id="l1132"></span><a href="#l1132"></a>
<span id="l1133">            /*</span><a href="#l1133"></a>
<span id="l1134">             * Try to initialize key manager.</span><a href="#l1134"></a>
<span id="l1135">             */</span><a href="#l1135"></a>
<span id="l1136">            if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,defaultctx&quot;)) {</span><a href="#l1136"></a>
<span id="l1137">                SSLLogger.fine(&quot;init keymanager of type &quot; +</span><a href="#l1137"></a>
<span id="l1138">                    KeyManagerFactory.getDefaultAlgorithm());</span><a href="#l1138"></a>
<span id="l1139">            }</span><a href="#l1139"></a>
<span id="l1140">            KeyManagerFactory kmf = KeyManagerFactory.getInstance(</span><a href="#l1140"></a>
<span id="l1141">                KeyManagerFactory.getDefaultAlgorithm());</span><a href="#l1141"></a>
<span id="l1142"></span><a href="#l1142"></a>
<span id="l1143">            if (P11KEYSTORE.equals(defaultKeyStoreType)) {</span><a href="#l1143"></a>
<span id="l1144">                kmf.init(ks, null); // do not pass key passwd if using token</span><a href="#l1144"></a>
<span id="l1145">            } else {</span><a href="#l1145"></a>
<span id="l1146">                kmf.init(ks, passwd);</span><a href="#l1146"></a>
<span id="l1147">            }</span><a href="#l1147"></a>
<span id="l1148"></span><a href="#l1148"></a>
<span id="l1149">            return kmf.getKeyManagers();</span><a href="#l1149"></a>
<span id="l1150">        }</span><a href="#l1150"></a>
<span id="l1151">    }</span><a href="#l1151"></a>
<span id="l1152"></span><a href="#l1152"></a>
<span id="l1153">    // lazy initialization holder class idiom for static default parameters</span><a href="#l1153"></a>
<span id="l1154">    //</span><a href="#l1154"></a>
<span id="l1155">    // See Effective Java Second Edition: Item 71.</span><a href="#l1155"></a>
<span id="l1156">    private static final class DefaultSSLContextHolder {</span><a href="#l1156"></a>
<span id="l1157"></span><a href="#l1157"></a>
<span id="l1158">        private static final SSLContextImpl sslContext;</span><a href="#l1158"></a>
<span id="l1159">        static Exception reservedException = null;</span><a href="#l1159"></a>
<span id="l1160"></span><a href="#l1160"></a>
<span id="l1161">        static {</span><a href="#l1161"></a>
<span id="l1162">            SSLContextImpl mediator = null;</span><a href="#l1162"></a>
<span id="l1163">            if (DefaultManagersHolder.reservedException != null) {</span><a href="#l1163"></a>
<span id="l1164">                reservedException = DefaultManagersHolder.reservedException;</span><a href="#l1164"></a>
<span id="l1165">            } else {</span><a href="#l1165"></a>
<span id="l1166">                try {</span><a href="#l1166"></a>
<span id="l1167">                    mediator = new DefaultSSLContext();</span><a href="#l1167"></a>
<span id="l1168">                } catch (Exception e) {</span><a href="#l1168"></a>
<span id="l1169">                    reservedException = e;</span><a href="#l1169"></a>
<span id="l1170">                }</span><a href="#l1170"></a>
<span id="l1171">            }</span><a href="#l1171"></a>
<span id="l1172"></span><a href="#l1172"></a>
<span id="l1173">            sslContext = mediator;</span><a href="#l1173"></a>
<span id="l1174">        }</span><a href="#l1174"></a>
<span id="l1175">    }</span><a href="#l1175"></a>
<span id="l1176"></span><a href="#l1176"></a>
<span id="l1177">    /*</span><a href="#l1177"></a>
<span id="l1178">     * The SSLContext implementation for default &quot;Default&quot; algorithm</span><a href="#l1178"></a>
<span id="l1179">     *</span><a href="#l1179"></a>
<span id="l1180">     * @see SSLContext</span><a href="#l1180"></a>
<span id="l1181">     */</span><a href="#l1181"></a>
<span id="l1182">    public static final class DefaultSSLContext extends CustomizedTLSContext {</span><a href="#l1182"></a>
<span id="l1183"></span><a href="#l1183"></a>
<span id="l1184">        // public constructor for SSLContext.getInstance(&quot;Default&quot;)</span><a href="#l1184"></a>
<span id="l1185">        public DefaultSSLContext() throws Exception {</span><a href="#l1185"></a>
<span id="l1186">            if (DefaultManagersHolder.reservedException != null) {</span><a href="#l1186"></a>
<span id="l1187">                throw DefaultManagersHolder.reservedException;</span><a href="#l1187"></a>
<span id="l1188">            }</span><a href="#l1188"></a>
<span id="l1189"></span><a href="#l1189"></a>
<span id="l1190">            try {</span><a href="#l1190"></a>
<span id="l1191">                super.engineInit(DefaultManagersHolder.keyManagers,</span><a href="#l1191"></a>
<span id="l1192">                        DefaultManagersHolder.trustManagers, null);</span><a href="#l1192"></a>
<span id="l1193">            } catch (Exception e) {</span><a href="#l1193"></a>
<span id="l1194">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,defaultctx&quot;)) {</span><a href="#l1194"></a>
<span id="l1195">                    SSLLogger.fine(&quot;default context init failed: &quot;, e);</span><a href="#l1195"></a>
<span id="l1196">                }</span><a href="#l1196"></a>
<span id="l1197">                throw e;</span><a href="#l1197"></a>
<span id="l1198">            }</span><a href="#l1198"></a>
<span id="l1199">        }</span><a href="#l1199"></a>
<span id="l1200"></span><a href="#l1200"></a>
<span id="l1201">        @Override</span><a href="#l1201"></a>
<span id="l1202">        protected void engineInit(KeyManager[] km, TrustManager[] tm,</span><a href="#l1202"></a>
<span id="l1203">            SecureRandom sr) throws KeyManagementException {</span><a href="#l1203"></a>
<span id="l1204">            throw new KeyManagementException</span><a href="#l1204"></a>
<span id="l1205">                (&quot;Default SSLContext is initialized automatically&quot;);</span><a href="#l1205"></a>
<span id="l1206">        }</span><a href="#l1206"></a>
<span id="l1207"></span><a href="#l1207"></a>
<span id="l1208">        static SSLContextImpl getDefaultImpl() throws Exception {</span><a href="#l1208"></a>
<span id="l1209">            if (DefaultSSLContextHolder.reservedException != null) {</span><a href="#l1209"></a>
<span id="l1210">                throw DefaultSSLContextHolder.reservedException;</span><a href="#l1210"></a>
<span id="l1211">            }</span><a href="#l1211"></a>
<span id="l1212"></span><a href="#l1212"></a>
<span id="l1213">            return DefaultSSLContextHolder.sslContext;</span><a href="#l1213"></a>
<span id="l1214">        }</span><a href="#l1214"></a>
<span id="l1215">    }</span><a href="#l1215"></a>
<span id="l1216"></span><a href="#l1216"></a>
<span id="l1217">}</span><a href="#l1217"></a>
<span id="l1218"></span><a href="#l1218"></a>
<span id="l1219">final class AbstractTrustManagerWrapper extends X509ExtendedTrustManager</span><a href="#l1219"></a>
<span id="l1220">            implements X509TrustManager {</span><a href="#l1220"></a>
<span id="l1221"></span><a href="#l1221"></a>
<span id="l1222">    // the delegated trust manager</span><a href="#l1222"></a>
<span id="l1223">    private final X509TrustManager tm;</span><a href="#l1223"></a>
<span id="l1224"></span><a href="#l1224"></a>
<span id="l1225">    AbstractTrustManagerWrapper(X509TrustManager tm) {</span><a href="#l1225"></a>
<span id="l1226">        this.tm = tm;</span><a href="#l1226"></a>
<span id="l1227">    }</span><a href="#l1227"></a>
<span id="l1228"></span><a href="#l1228"></a>
<span id="l1229">    @Override</span><a href="#l1229"></a>
<span id="l1230">    public void checkClientTrusted(X509Certificate[] chain, String authType)</span><a href="#l1230"></a>
<span id="l1231">        throws CertificateException {</span><a href="#l1231"></a>
<span id="l1232">        tm.checkClientTrusted(chain, authType);</span><a href="#l1232"></a>
<span id="l1233">    }</span><a href="#l1233"></a>
<span id="l1234"></span><a href="#l1234"></a>
<span id="l1235">    @Override</span><a href="#l1235"></a>
<span id="l1236">    public void checkServerTrusted(X509Certificate[] chain, String authType)</span><a href="#l1236"></a>
<span id="l1237">        throws CertificateException {</span><a href="#l1237"></a>
<span id="l1238">        tm.checkServerTrusted(chain, authType);</span><a href="#l1238"></a>
<span id="l1239">    }</span><a href="#l1239"></a>
<span id="l1240"></span><a href="#l1240"></a>
<span id="l1241">    @Override</span><a href="#l1241"></a>
<span id="l1242">    public X509Certificate[] getAcceptedIssuers() {</span><a href="#l1242"></a>
<span id="l1243">        return tm.getAcceptedIssuers();</span><a href="#l1243"></a>
<span id="l1244">    }</span><a href="#l1244"></a>
<span id="l1245"></span><a href="#l1245"></a>
<span id="l1246">    @Override</span><a href="#l1246"></a>
<span id="l1247">    public void checkClientTrusted(X509Certificate[] chain, String authType,</span><a href="#l1247"></a>
<span id="l1248">                Socket socket) throws CertificateException {</span><a href="#l1248"></a>
<span id="l1249">        tm.checkClientTrusted(chain, authType);</span><a href="#l1249"></a>
<span id="l1250">        checkAdditionalTrust(chain, authType, socket, true);</span><a href="#l1250"></a>
<span id="l1251">    }</span><a href="#l1251"></a>
<span id="l1252"></span><a href="#l1252"></a>
<span id="l1253">    @Override</span><a href="#l1253"></a>
<span id="l1254">    public void checkServerTrusted(X509Certificate[] chain, String authType,</span><a href="#l1254"></a>
<span id="l1255">            Socket socket) throws CertificateException {</span><a href="#l1255"></a>
<span id="l1256">        tm.checkServerTrusted(chain, authType);</span><a href="#l1256"></a>
<span id="l1257">        checkAdditionalTrust(chain, authType, socket, false);</span><a href="#l1257"></a>
<span id="l1258">    }</span><a href="#l1258"></a>
<span id="l1259"></span><a href="#l1259"></a>
<span id="l1260">    @Override</span><a href="#l1260"></a>
<span id="l1261">    public void checkClientTrusted(X509Certificate[] chain, String authType,</span><a href="#l1261"></a>
<span id="l1262">            SSLEngine engine) throws CertificateException {</span><a href="#l1262"></a>
<span id="l1263">        tm.checkClientTrusted(chain, authType);</span><a href="#l1263"></a>
<span id="l1264">        checkAdditionalTrust(chain, authType, engine, true);</span><a href="#l1264"></a>
<span id="l1265">    }</span><a href="#l1265"></a>
<span id="l1266"></span><a href="#l1266"></a>
<span id="l1267">    @Override</span><a href="#l1267"></a>
<span id="l1268">    public void checkServerTrusted(X509Certificate[] chain, String authType,</span><a href="#l1268"></a>
<span id="l1269">            SSLEngine engine) throws CertificateException {</span><a href="#l1269"></a>
<span id="l1270">        tm.checkServerTrusted(chain, authType);</span><a href="#l1270"></a>
<span id="l1271">        checkAdditionalTrust(chain, authType, engine, false);</span><a href="#l1271"></a>
<span id="l1272">    }</span><a href="#l1272"></a>
<span id="l1273"></span><a href="#l1273"></a>
<span id="l1274">    private void checkAdditionalTrust(X509Certificate[] chain,</span><a href="#l1274"></a>
<span id="l1275">            String authType, Socket socket,</span><a href="#l1275"></a>
<span id="l1276">            boolean checkClientTrusted) throws CertificateException {</span><a href="#l1276"></a>
<span id="l1277">        if (socket != null &amp;&amp; socket.isConnected() &amp;&amp;</span><a href="#l1277"></a>
<span id="l1278">                                    socket instanceof SSLSocket) {</span><a href="#l1278"></a>
<span id="l1279"></span><a href="#l1279"></a>
<span id="l1280">            SSLSocket sslSocket = (SSLSocket)socket;</span><a href="#l1280"></a>
<span id="l1281">            SSLSession session = sslSocket.getHandshakeSession();</span><a href="#l1281"></a>
<span id="l1282">            if (session == null) {</span><a href="#l1282"></a>
<span id="l1283">                throw new CertificateException(&quot;No handshake session&quot;);</span><a href="#l1283"></a>
<span id="l1284">            }</span><a href="#l1284"></a>
<span id="l1285"></span><a href="#l1285"></a>
<span id="l1286">            // check endpoint identity</span><a href="#l1286"></a>
<span id="l1287">            String identityAlg = sslSocket.getSSLParameters().</span><a href="#l1287"></a>
<span id="l1288">                                        getEndpointIdentificationAlgorithm();</span><a href="#l1288"></a>
<span id="l1289">            if (identityAlg != null &amp;&amp; !identityAlg.isEmpty()) {</span><a href="#l1289"></a>
<span id="l1290">                X509TrustManagerImpl.checkIdentity(session, chain,</span><a href="#l1290"></a>
<span id="l1291">                                    identityAlg, checkClientTrusted);</span><a href="#l1291"></a>
<span id="l1292">            }</span><a href="#l1292"></a>
<span id="l1293"></span><a href="#l1293"></a>
<span id="l1294">            // try the best to check the algorithm constraints</span><a href="#l1294"></a>
<span id="l1295">            AlgorithmConstraints constraints;</span><a href="#l1295"></a>
<span id="l1296">            if (ProtocolVersion.useTLS12PlusSpec(session.getProtocol())) {</span><a href="#l1296"></a>
<span id="l1297">                if (session instanceof ExtendedSSLSession) {</span><a href="#l1297"></a>
<span id="l1298">                    ExtendedSSLSession extSession =</span><a href="#l1298"></a>
<span id="l1299">                                    (ExtendedSSLSession)session;</span><a href="#l1299"></a>
<span id="l1300">                    String[] peerSupportedSignAlgs =</span><a href="#l1300"></a>
<span id="l1301">                            extSession.getLocalSupportedSignatureAlgorithms();</span><a href="#l1301"></a>
<span id="l1302"></span><a href="#l1302"></a>
<span id="l1303">                    constraints = new SSLAlgorithmConstraints(</span><a href="#l1303"></a>
<span id="l1304">                                    sslSocket, peerSupportedSignAlgs, true);</span><a href="#l1304"></a>
<span id="l1305">                } else {</span><a href="#l1305"></a>
<span id="l1306">                    constraints =</span><a href="#l1306"></a>
<span id="l1307">                            new SSLAlgorithmConstraints(sslSocket, true);</span><a href="#l1307"></a>
<span id="l1308">                }</span><a href="#l1308"></a>
<span id="l1309">            } else {</span><a href="#l1309"></a>
<span id="l1310">                constraints = new SSLAlgorithmConstraints(sslSocket, true);</span><a href="#l1310"></a>
<span id="l1311">            }</span><a href="#l1311"></a>
<span id="l1312"></span><a href="#l1312"></a>
<span id="l1313">            checkAlgorithmConstraints(chain, constraints, checkClientTrusted);</span><a href="#l1313"></a>
<span id="l1314">        }</span><a href="#l1314"></a>
<span id="l1315">    }</span><a href="#l1315"></a>
<span id="l1316"></span><a href="#l1316"></a>
<span id="l1317">    private void checkAdditionalTrust(X509Certificate[] chain,</span><a href="#l1317"></a>
<span id="l1318">            String authType, SSLEngine engine,</span><a href="#l1318"></a>
<span id="l1319">            boolean checkClientTrusted) throws CertificateException {</span><a href="#l1319"></a>
<span id="l1320">        if (engine != null) {</span><a href="#l1320"></a>
<span id="l1321">            SSLSession session = engine.getHandshakeSession();</span><a href="#l1321"></a>
<span id="l1322">            if (session == null) {</span><a href="#l1322"></a>
<span id="l1323">                throw new CertificateException(&quot;No handshake session&quot;);</span><a href="#l1323"></a>
<span id="l1324">            }</span><a href="#l1324"></a>
<span id="l1325"></span><a href="#l1325"></a>
<span id="l1326">            // check endpoint identity</span><a href="#l1326"></a>
<span id="l1327">            String identityAlg = engine.getSSLParameters().</span><a href="#l1327"></a>
<span id="l1328">                                        getEndpointIdentificationAlgorithm();</span><a href="#l1328"></a>
<span id="l1329">            if (identityAlg != null &amp;&amp; !identityAlg.isEmpty()) {</span><a href="#l1329"></a>
<span id="l1330">                X509TrustManagerImpl.checkIdentity(session, chain,</span><a href="#l1330"></a>
<span id="l1331">                                    identityAlg, checkClientTrusted);</span><a href="#l1331"></a>
<span id="l1332">            }</span><a href="#l1332"></a>
<span id="l1333"></span><a href="#l1333"></a>
<span id="l1334">            // try the best to check the algorithm constraints</span><a href="#l1334"></a>
<span id="l1335">            AlgorithmConstraints constraints;</span><a href="#l1335"></a>
<span id="l1336">            if (ProtocolVersion.useTLS12PlusSpec(session.getProtocol())) {</span><a href="#l1336"></a>
<span id="l1337">                if (session instanceof ExtendedSSLSession) {</span><a href="#l1337"></a>
<span id="l1338">                    ExtendedSSLSession extSession =</span><a href="#l1338"></a>
<span id="l1339">                                    (ExtendedSSLSession)session;</span><a href="#l1339"></a>
<span id="l1340">                    String[] peerSupportedSignAlgs =</span><a href="#l1340"></a>
<span id="l1341">                            extSession.getLocalSupportedSignatureAlgorithms();</span><a href="#l1341"></a>
<span id="l1342"></span><a href="#l1342"></a>
<span id="l1343">                    constraints = new SSLAlgorithmConstraints(</span><a href="#l1343"></a>
<span id="l1344">                                    engine, peerSupportedSignAlgs, true);</span><a href="#l1344"></a>
<span id="l1345">                } else {</span><a href="#l1345"></a>
<span id="l1346">                    constraints =</span><a href="#l1346"></a>
<span id="l1347">                            new SSLAlgorithmConstraints(engine, true);</span><a href="#l1347"></a>
<span id="l1348">                }</span><a href="#l1348"></a>
<span id="l1349">            } else {</span><a href="#l1349"></a>
<span id="l1350">                constraints = new SSLAlgorithmConstraints(engine, true);</span><a href="#l1350"></a>
<span id="l1351">            }</span><a href="#l1351"></a>
<span id="l1352"></span><a href="#l1352"></a>
<span id="l1353">            checkAlgorithmConstraints(chain, constraints, checkClientTrusted);</span><a href="#l1353"></a>
<span id="l1354">        }</span><a href="#l1354"></a>
<span id="l1355">    }</span><a href="#l1355"></a>
<span id="l1356"></span><a href="#l1356"></a>
<span id="l1357">    private void checkAlgorithmConstraints(X509Certificate[] chain,</span><a href="#l1357"></a>
<span id="l1358">            AlgorithmConstraints constraints,</span><a href="#l1358"></a>
<span id="l1359">            boolean checkClientTrusted) throws CertificateException {</span><a href="#l1359"></a>
<span id="l1360">        try {</span><a href="#l1360"></a>
<span id="l1361">            // Does the certificate chain end with a trusted certificate?</span><a href="#l1361"></a>
<span id="l1362">            int checkedLength = chain.length - 1;</span><a href="#l1362"></a>
<span id="l1363"></span><a href="#l1363"></a>
<span id="l1364">            Collection&lt;X509Certificate&gt; trustedCerts = new HashSet&lt;&gt;();</span><a href="#l1364"></a>
<span id="l1365">            X509Certificate[] certs = tm.getAcceptedIssuers();</span><a href="#l1365"></a>
<span id="l1366">            if ((certs != null) &amp;&amp; (certs.length &gt; 0)){</span><a href="#l1366"></a>
<span id="l1367">                Collections.addAll(trustedCerts, certs);</span><a href="#l1367"></a>
<span id="l1368">            }</span><a href="#l1368"></a>
<span id="l1369"></span><a href="#l1369"></a>
<span id="l1370">            if (trustedCerts.contains(chain[checkedLength])) {</span><a href="#l1370"></a>
<span id="l1371">                    checkedLength--;</span><a href="#l1371"></a>
<span id="l1372">            }</span><a href="#l1372"></a>
<span id="l1373"></span><a href="#l1373"></a>
<span id="l1374">            // A forward checker, need to check from trust to target</span><a href="#l1374"></a>
<span id="l1375">            if (checkedLength &gt;= 0) {</span><a href="#l1375"></a>
<span id="l1376">                AlgorithmChecker checker =</span><a href="#l1376"></a>
<span id="l1377">                    new AlgorithmChecker(constraints,</span><a href="#l1377"></a>
<span id="l1378">                            (checkClientTrusted ? Validator.VAR_TLS_CLIENT :</span><a href="#l1378"></a>
<span id="l1379">                                        Validator.VAR_TLS_SERVER));</span><a href="#l1379"></a>
<span id="l1380">                checker.init(false);</span><a href="#l1380"></a>
<span id="l1381">                for (int i = checkedLength; i &gt;= 0; i--) {</span><a href="#l1381"></a>
<span id="l1382">                    X509Certificate cert = chain[i];</span><a href="#l1382"></a>
<span id="l1383">                    // We don't care about the unresolved critical extensions.</span><a href="#l1383"></a>
<span id="l1384">                    checker.check(cert, Collections.&lt;String&gt;emptySet());</span><a href="#l1384"></a>
<span id="l1385">                }</span><a href="#l1385"></a>
<span id="l1386">            }</span><a href="#l1386"></a>
<span id="l1387">        } catch (CertPathValidatorException cpve) {</span><a href="#l1387"></a>
<span id="l1388">            throw new CertificateException(</span><a href="#l1388"></a>
<span id="l1389">                &quot;Certificates do not conform to algorithm constraints&quot;, cpve);</span><a href="#l1389"></a>
<span id="l1390">        }</span><a href="#l1390"></a>
<span id="l1391">    }</span><a href="#l1391"></a>
<span id="l1392">}</span><a href="#l1392"></a>
<span id="l1393"></span><a href="#l1393"></a>
<span id="l1394">// Dummy X509TrustManager implementation, rejects all peer certificates.</span><a href="#l1394"></a>
<span id="l1395">// Used if the application did not specify a proper X509TrustManager.</span><a href="#l1395"></a>
<span id="l1396">final class DummyX509TrustManager extends X509ExtendedTrustManager</span><a href="#l1396"></a>
<span id="l1397">            implements X509TrustManager {</span><a href="#l1397"></a>
<span id="l1398"></span><a href="#l1398"></a>
<span id="l1399">    static final X509TrustManager INSTANCE = new DummyX509TrustManager();</span><a href="#l1399"></a>
<span id="l1400"></span><a href="#l1400"></a>
<span id="l1401">    private DummyX509TrustManager() {</span><a href="#l1401"></a>
<span id="l1402">        // empty</span><a href="#l1402"></a>
<span id="l1403">    }</span><a href="#l1403"></a>
<span id="l1404"></span><a href="#l1404"></a>
<span id="l1405">    /*</span><a href="#l1405"></a>
<span id="l1406">     * Given the partial or complete certificate chain</span><a href="#l1406"></a>
<span id="l1407">     * provided by the peer, build a certificate path</span><a href="#l1407"></a>
<span id="l1408">     * to a trusted root and return if it can be</span><a href="#l1408"></a>
<span id="l1409">     * validated and is trusted for client SSL authentication.</span><a href="#l1409"></a>
<span id="l1410">     * If not, it throws an exception.</span><a href="#l1410"></a>
<span id="l1411">     */</span><a href="#l1411"></a>
<span id="l1412">    @Override</span><a href="#l1412"></a>
<span id="l1413">    public void checkClientTrusted(X509Certificate[] chain, String authType)</span><a href="#l1413"></a>
<span id="l1414">        throws CertificateException {</span><a href="#l1414"></a>
<span id="l1415">        throw new CertificateException(</span><a href="#l1415"></a>
<span id="l1416">            &quot;No X509TrustManager implementation avaiable&quot;);</span><a href="#l1416"></a>
<span id="l1417">    }</span><a href="#l1417"></a>
<span id="l1418"></span><a href="#l1418"></a>
<span id="l1419">    /*</span><a href="#l1419"></a>
<span id="l1420">     * Given the partial or complete certificate chain</span><a href="#l1420"></a>
<span id="l1421">     * provided by the peer, build a certificate path</span><a href="#l1421"></a>
<span id="l1422">     * to a trusted root and return if it can be</span><a href="#l1422"></a>
<span id="l1423">     * validated and is trusted for server SSL authentication.</span><a href="#l1423"></a>
<span id="l1424">     * If not, it throws an exception.</span><a href="#l1424"></a>
<span id="l1425">     */</span><a href="#l1425"></a>
<span id="l1426">    @Override</span><a href="#l1426"></a>
<span id="l1427">    public void checkServerTrusted(X509Certificate[] chain, String authType)</span><a href="#l1427"></a>
<span id="l1428">        throws CertificateException {</span><a href="#l1428"></a>
<span id="l1429">        throw new CertificateException(</span><a href="#l1429"></a>
<span id="l1430">            &quot;No X509TrustManager implementation available&quot;);</span><a href="#l1430"></a>
<span id="l1431">    }</span><a href="#l1431"></a>
<span id="l1432"></span><a href="#l1432"></a>
<span id="l1433">    /*</span><a href="#l1433"></a>
<span id="l1434">     * Return an array of issuer certificates which are trusted</span><a href="#l1434"></a>
<span id="l1435">     * for authenticating peers.</span><a href="#l1435"></a>
<span id="l1436">     */</span><a href="#l1436"></a>
<span id="l1437">    @Override</span><a href="#l1437"></a>
<span id="l1438">    public X509Certificate[] getAcceptedIssuers() {</span><a href="#l1438"></a>
<span id="l1439">        return new X509Certificate[0];</span><a href="#l1439"></a>
<span id="l1440">    }</span><a href="#l1440"></a>
<span id="l1441"></span><a href="#l1441"></a>
<span id="l1442">    @Override</span><a href="#l1442"></a>
<span id="l1443">    public void checkClientTrusted(X509Certificate[] chain, String authType,</span><a href="#l1443"></a>
<span id="l1444">                Socket socket) throws CertificateException {</span><a href="#l1444"></a>
<span id="l1445">        throw new CertificateException(</span><a href="#l1445"></a>
<span id="l1446">            &quot;No X509TrustManager implementation available&quot;);</span><a href="#l1446"></a>
<span id="l1447">    }</span><a href="#l1447"></a>
<span id="l1448"></span><a href="#l1448"></a>
<span id="l1449">    @Override</span><a href="#l1449"></a>
<span id="l1450">    public void checkServerTrusted(X509Certificate[] chain, String authType,</span><a href="#l1450"></a>
<span id="l1451">            Socket socket) throws CertificateException {</span><a href="#l1451"></a>
<span id="l1452">        throw new CertificateException(</span><a href="#l1452"></a>
<span id="l1453">            &quot;No X509TrustManager implementation available&quot;);</span><a href="#l1453"></a>
<span id="l1454">    }</span><a href="#l1454"></a>
<span id="l1455"></span><a href="#l1455"></a>
<span id="l1456">    @Override</span><a href="#l1456"></a>
<span id="l1457">    public void checkClientTrusted(X509Certificate[] chain, String authType,</span><a href="#l1457"></a>
<span id="l1458">            SSLEngine engine) throws CertificateException {</span><a href="#l1458"></a>
<span id="l1459">        throw new CertificateException(</span><a href="#l1459"></a>
<span id="l1460">            &quot;No X509TrustManager implementation available&quot;);</span><a href="#l1460"></a>
<span id="l1461">    }</span><a href="#l1461"></a>
<span id="l1462"></span><a href="#l1462"></a>
<span id="l1463">    @Override</span><a href="#l1463"></a>
<span id="l1464">    public void checkServerTrusted(X509Certificate[] chain, String authType,</span><a href="#l1464"></a>
<span id="l1465">            SSLEngine engine) throws CertificateException {</span><a href="#l1465"></a>
<span id="l1466">        throw new CertificateException(</span><a href="#l1466"></a>
<span id="l1467">            &quot;No X509TrustManager implementation available&quot;);</span><a href="#l1467"></a>
<span id="l1468">    }</span><a href="#l1468"></a>
<span id="l1469">}</span><a href="#l1469"></a>
<span id="l1470"></span><a href="#l1470"></a>
<span id="l1471">/*</span><a href="#l1471"></a>
<span id="l1472"> * A wrapper class to turn a X509KeyManager into an X509ExtendedKeyManager</span><a href="#l1472"></a>
<span id="l1473"> */</span><a href="#l1473"></a>
<span id="l1474">final class AbstractKeyManagerWrapper extends X509ExtendedKeyManager {</span><a href="#l1474"></a>
<span id="l1475"></span><a href="#l1475"></a>
<span id="l1476">    private final X509KeyManager km;</span><a href="#l1476"></a>
<span id="l1477"></span><a href="#l1477"></a>
<span id="l1478">    AbstractKeyManagerWrapper(X509KeyManager km) {</span><a href="#l1478"></a>
<span id="l1479">        this.km = km;</span><a href="#l1479"></a>
<span id="l1480">    }</span><a href="#l1480"></a>
<span id="l1481"></span><a href="#l1481"></a>
<span id="l1482">    @Override</span><a href="#l1482"></a>
<span id="l1483">    public String[] getClientAliases(String keyType, Principal[] issuers) {</span><a href="#l1483"></a>
<span id="l1484">        return km.getClientAliases(keyType, issuers);</span><a href="#l1484"></a>
<span id="l1485">    }</span><a href="#l1485"></a>
<span id="l1486"></span><a href="#l1486"></a>
<span id="l1487">    @Override</span><a href="#l1487"></a>
<span id="l1488">    public String chooseClientAlias(String[] keyType, Principal[] issuers,</span><a href="#l1488"></a>
<span id="l1489">            Socket socket) {</span><a href="#l1489"></a>
<span id="l1490">        return km.chooseClientAlias(keyType, issuers, socket);</span><a href="#l1490"></a>
<span id="l1491">    }</span><a href="#l1491"></a>
<span id="l1492"></span><a href="#l1492"></a>
<span id="l1493">    @Override</span><a href="#l1493"></a>
<span id="l1494">    public String[] getServerAliases(String keyType, Principal[] issuers) {</span><a href="#l1494"></a>
<span id="l1495">        return km.getServerAliases(keyType, issuers);</span><a href="#l1495"></a>
<span id="l1496">    }</span><a href="#l1496"></a>
<span id="l1497"></span><a href="#l1497"></a>
<span id="l1498">    @Override</span><a href="#l1498"></a>
<span id="l1499">    public String chooseServerAlias(String keyType, Principal[] issuers,</span><a href="#l1499"></a>
<span id="l1500">            Socket socket) {</span><a href="#l1500"></a>
<span id="l1501">        return km.chooseServerAlias(keyType, issuers, socket);</span><a href="#l1501"></a>
<span id="l1502">    }</span><a href="#l1502"></a>
<span id="l1503"></span><a href="#l1503"></a>
<span id="l1504">    @Override</span><a href="#l1504"></a>
<span id="l1505">    public X509Certificate[] getCertificateChain(String alias) {</span><a href="#l1505"></a>
<span id="l1506">        return km.getCertificateChain(alias);</span><a href="#l1506"></a>
<span id="l1507">    }</span><a href="#l1507"></a>
<span id="l1508"></span><a href="#l1508"></a>
<span id="l1509">    @Override</span><a href="#l1509"></a>
<span id="l1510">    public PrivateKey getPrivateKey(String alias) {</span><a href="#l1510"></a>
<span id="l1511">        return km.getPrivateKey(alias);</span><a href="#l1511"></a>
<span id="l1512">    }</span><a href="#l1512"></a>
<span id="l1513"></span><a href="#l1513"></a>
<span id="l1514">    // Inherit chooseEngineClientAlias() and chooseEngineServerAlias() from</span><a href="#l1514"></a>
<span id="l1515">    // X509ExtendedKeymanager. It defines them to return null;</span><a href="#l1515"></a>
<span id="l1516">}</span><a href="#l1516"></a>
<span id="l1517"></span><a href="#l1517"></a>
<span id="l1518"></span><a href="#l1518"></a>
<span id="l1519">// Dummy X509KeyManager implementation, never returns any certificates/keys.</span><a href="#l1519"></a>
<span id="l1520">// Used if the application did not specify a proper X509TrustManager.</span><a href="#l1520"></a>
<span id="l1521">final class DummyX509KeyManager extends X509ExtendedKeyManager {</span><a href="#l1521"></a>
<span id="l1522"></span><a href="#l1522"></a>
<span id="l1523">    static final X509ExtendedKeyManager INSTANCE = new DummyX509KeyManager();</span><a href="#l1523"></a>
<span id="l1524"></span><a href="#l1524"></a>
<span id="l1525">    private DummyX509KeyManager() {</span><a href="#l1525"></a>
<span id="l1526">        // empty</span><a href="#l1526"></a>
<span id="l1527">    }</span><a href="#l1527"></a>
<span id="l1528"></span><a href="#l1528"></a>
<span id="l1529">    /*</span><a href="#l1529"></a>
<span id="l1530">     * Get the matching aliases for authenticating the client side of a secure</span><a href="#l1530"></a>
<span id="l1531">     * socket given the public key type and the list of</span><a href="#l1531"></a>
<span id="l1532">     * certificate issuer authorities recognized by the peer (if any).</span><a href="#l1532"></a>
<span id="l1533">     */</span><a href="#l1533"></a>
<span id="l1534">    @Override</span><a href="#l1534"></a>
<span id="l1535">    public String[] getClientAliases(String keyType, Principal[] issuers) {</span><a href="#l1535"></a>
<span id="l1536">        return null;</span><a href="#l1536"></a>
<span id="l1537">    }</span><a href="#l1537"></a>
<span id="l1538"></span><a href="#l1538"></a>
<span id="l1539">    /*</span><a href="#l1539"></a>
<span id="l1540">     * Choose an alias to authenticate the client side of a secure</span><a href="#l1540"></a>
<span id="l1541">     * socket given the public key type and the list of</span><a href="#l1541"></a>
<span id="l1542">     * certificate issuer authorities recognized by the peer (if any).</span><a href="#l1542"></a>
<span id="l1543">     */</span><a href="#l1543"></a>
<span id="l1544">    @Override</span><a href="#l1544"></a>
<span id="l1545">    public String chooseClientAlias(String[] keyTypes, Principal[] issuers,</span><a href="#l1545"></a>
<span id="l1546">            Socket socket) {</span><a href="#l1546"></a>
<span id="l1547">        return null;</span><a href="#l1547"></a>
<span id="l1548">    }</span><a href="#l1548"></a>
<span id="l1549"></span><a href="#l1549"></a>
<span id="l1550">    /*</span><a href="#l1550"></a>
<span id="l1551">     * Choose an alias to authenticate the client side of an</span><a href="#l1551"></a>
<span id="l1552">     * engine given the public key type and the list of</span><a href="#l1552"></a>
<span id="l1553">     * certificate issuer authorities recognized by the peer (if any).</span><a href="#l1553"></a>
<span id="l1554">     */</span><a href="#l1554"></a>
<span id="l1555">    @Override</span><a href="#l1555"></a>
<span id="l1556">    public String chooseEngineClientAlias(</span><a href="#l1556"></a>
<span id="l1557">            String[] keyTypes, Principal[] issuers, SSLEngine engine) {</span><a href="#l1557"></a>
<span id="l1558">        return null;</span><a href="#l1558"></a>
<span id="l1559">    }</span><a href="#l1559"></a>
<span id="l1560"></span><a href="#l1560"></a>
<span id="l1561">    /*</span><a href="#l1561"></a>
<span id="l1562">     * Get the matching aliases for authenticating the server side of a secure</span><a href="#l1562"></a>
<span id="l1563">     * socket given the public key type and the list of</span><a href="#l1563"></a>
<span id="l1564">     * certificate issuer authorities recognized by the peer (if any).</span><a href="#l1564"></a>
<span id="l1565">     */</span><a href="#l1565"></a>
<span id="l1566">    @Override</span><a href="#l1566"></a>
<span id="l1567">    public String[] getServerAliases(String keyType, Principal[] issuers) {</span><a href="#l1567"></a>
<span id="l1568">        return null;</span><a href="#l1568"></a>
<span id="l1569">    }</span><a href="#l1569"></a>
<span id="l1570"></span><a href="#l1570"></a>
<span id="l1571">    /*</span><a href="#l1571"></a>
<span id="l1572">     * Choose an alias to authenticate the server side of a secure</span><a href="#l1572"></a>
<span id="l1573">     * socket given the public key type and the list of</span><a href="#l1573"></a>
<span id="l1574">     * certificate issuer authorities recognized by the peer (if any).</span><a href="#l1574"></a>
<span id="l1575">     */</span><a href="#l1575"></a>
<span id="l1576">    @Override</span><a href="#l1576"></a>
<span id="l1577">    public String chooseServerAlias(String keyType, Principal[] issuers,</span><a href="#l1577"></a>
<span id="l1578">            Socket socket) {</span><a href="#l1578"></a>
<span id="l1579">        return null;</span><a href="#l1579"></a>
<span id="l1580">    }</span><a href="#l1580"></a>
<span id="l1581"></span><a href="#l1581"></a>
<span id="l1582">    /*</span><a href="#l1582"></a>
<span id="l1583">     * Choose an alias to authenticate the server side of an engine</span><a href="#l1583"></a>
<span id="l1584">     * given the public key type and the list of</span><a href="#l1584"></a>
<span id="l1585">     * certificate issuer authorities recognized by the peer (if any).</span><a href="#l1585"></a>
<span id="l1586">     */</span><a href="#l1586"></a>
<span id="l1587">    @Override</span><a href="#l1587"></a>
<span id="l1588">    public String chooseEngineServerAlias(</span><a href="#l1588"></a>
<span id="l1589">            String keyType, Principal[] issuers, SSLEngine engine) {</span><a href="#l1589"></a>
<span id="l1590">        return null;</span><a href="#l1590"></a>
<span id="l1591">    }</span><a href="#l1591"></a>
<span id="l1592"></span><a href="#l1592"></a>
<span id="l1593">    /**</span><a href="#l1593"></a>
<span id="l1594">     * Returns the certificate chain associated with the given alias.</span><a href="#l1594"></a>
<span id="l1595">     *</span><a href="#l1595"></a>
<span id="l1596">     * @param alias the alias name</span><a href="#l1596"></a>
<span id="l1597">     *</span><a href="#l1597"></a>
<span id="l1598">     * @return the certificate chain (ordered with the user's certificate first</span><a href="#l1598"></a>
<span id="l1599">     * and the root certificate authority last)</span><a href="#l1599"></a>
<span id="l1600">     */</span><a href="#l1600"></a>
<span id="l1601">    @Override</span><a href="#l1601"></a>
<span id="l1602">    public X509Certificate[] getCertificateChain(String alias) {</span><a href="#l1602"></a>
<span id="l1603">        return null;</span><a href="#l1603"></a>
<span id="l1604">    }</span><a href="#l1604"></a>
<span id="l1605"></span><a href="#l1605"></a>
<span id="l1606">    /*</span><a href="#l1606"></a>
<span id="l1607">     * Returns the key associated with the given alias, using the given</span><a href="#l1607"></a>
<span id="l1608">     * password to recover it.</span><a href="#l1608"></a>
<span id="l1609">     *</span><a href="#l1609"></a>
<span id="l1610">     * @param alias the alias name</span><a href="#l1610"></a>
<span id="l1611">     *</span><a href="#l1611"></a>
<span id="l1612">     * @return the requested key</span><a href="#l1612"></a>
<span id="l1613">     */</span><a href="#l1613"></a>
<span id="l1614">    @Override</span><a href="#l1614"></a>
<span id="l1615">    public PrivateKey getPrivateKey(String alias) {</span><a href="#l1615"></a>
<span id="l1616">        return null;</span><a href="#l1616"></a>
<span id="l1617">    }</span><a href="#l1617"></a>
<span id="l1618">}</span><a href="#l1618"></a></pre>
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

