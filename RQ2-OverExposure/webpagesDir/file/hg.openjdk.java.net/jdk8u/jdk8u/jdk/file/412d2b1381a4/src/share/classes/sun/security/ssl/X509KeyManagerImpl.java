<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 412d2b1381a4 src/share/classes/sun/security/ssl/X509KeyManagerImpl.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/ssl/X509KeyManagerImpl.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/412d2b1381a4/src/share/classes/sun/security/ssl/X509KeyManagerImpl.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/412d2b1381a4/src/share/classes/sun/security/ssl/X509KeyManagerImpl.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/412d2b1381a4/src/share/classes/sun/security/ssl/X509KeyManagerImpl.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/412d2b1381a4/src/share/classes/sun/security/ssl/X509KeyManagerImpl.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/412d2b1381a4/src/share/classes/sun/security/ssl/X509KeyManagerImpl.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/ssl/X509KeyManagerImpl.java @ 14391:412d2b1381a4</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/4546aa3faf37/src/share/classes/sun/security/ssl/X509KeyManagerImpl.java">4546aa3faf37</a> </td>
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
<span id="l2"> * Copyright (c) 2004, 2018, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l28">import java.lang.ref.*;</span><a href="#l28"></a>
<span id="l29">import java.net.Socket;</span><a href="#l29"></a>
<span id="l30">import java.security.AlgorithmConstraints;</span><a href="#l30"></a>
<span id="l31">import java.security.KeyStore;</span><a href="#l31"></a>
<span id="l32">import java.security.KeyStore.Builder;</span><a href="#l32"></a>
<span id="l33">import java.security.KeyStore.Entry;</span><a href="#l33"></a>
<span id="l34">import java.security.KeyStore.PrivateKeyEntry;</span><a href="#l34"></a>
<span id="l35">import java.security.Principal;</span><a href="#l35"></a>
<span id="l36">import java.security.PrivateKey;</span><a href="#l36"></a>
<span id="l37">import java.security.cert.CertPathValidatorException;</span><a href="#l37"></a>
<span id="l38">import java.security.cert.Certificate;</span><a href="#l38"></a>
<span id="l39">import java.security.cert.CertificateException;</span><a href="#l39"></a>
<span id="l40">import java.security.cert.X509Certificate;</span><a href="#l40"></a>
<span id="l41">import java.util.*;</span><a href="#l41"></a>
<span id="l42">import java.util.concurrent.atomic.AtomicLong;</span><a href="#l42"></a>
<span id="l43">import javax.net.ssl.*;</span><a href="#l43"></a>
<span id="l44">import sun.security.provider.certpath.AlgorithmChecker;</span><a href="#l44"></a>
<span id="l45">import sun.security.validator.Validator;</span><a href="#l45"></a>
<span id="l46"></span><a href="#l46"></a>
<span id="l47">/**</span><a href="#l47"></a>
<span id="l48"> * The new X509 key manager implementation. The main differences to the</span><a href="#l48"></a>
<span id="l49"> * old SunX509 key manager are:</span><a href="#l49"></a>
<span id="l50"> *  . it is based around the KeyStore.Builder API. This allows it to use</span><a href="#l50"></a>
<span id="l51"> *    other forms of KeyStore protection or password input (e.g. a</span><a href="#l51"></a>
<span id="l52"> *    CallbackHandler) or to have keys within one KeyStore protected by</span><a href="#l52"></a>
<span id="l53"> *    different keys.</span><a href="#l53"></a>
<span id="l54"> *  . it can use multiple KeyStores at the same time.</span><a href="#l54"></a>
<span id="l55"> *  . it is explicitly designed to accommodate KeyStores that change over</span><a href="#l55"></a>
<span id="l56"> *    the lifetime of the process.</span><a href="#l56"></a>
<span id="l57"> *  . it makes an effort to choose the key that matches best, i.e. one that</span><a href="#l57"></a>
<span id="l58"> *    is not expired and has the appropriate certificate extensions.</span><a href="#l58"></a>
<span id="l59"> *</span><a href="#l59"></a>
<span id="l60"> * Note that this code is not explicitly performance optimzied yet.</span><a href="#l60"></a>
<span id="l61"> *</span><a href="#l61"></a>
<span id="l62"> * @author  Andreas Sterbenz</span><a href="#l62"></a>
<span id="l63"> */</span><a href="#l63"></a>
<span id="l64">final class X509KeyManagerImpl extends X509ExtendedKeyManager</span><a href="#l64"></a>
<span id="l65">        implements X509KeyManager {</span><a href="#l65"></a>
<span id="l66"></span><a href="#l66"></a>
<span id="l67">    // for unit testing only, set via privileged reflection</span><a href="#l67"></a>
<span id="l68">    private static Date verificationDate;</span><a href="#l68"></a>
<span id="l69"></span><a href="#l69"></a>
<span id="l70">    // list of the builders</span><a href="#l70"></a>
<span id="l71">    private final List&lt;Builder&gt; builders;</span><a href="#l71"></a>
<span id="l72"></span><a href="#l72"></a>
<span id="l73">    // counter to generate unique ids for the aliases</span><a href="#l73"></a>
<span id="l74">    private final AtomicLong uidCounter;</span><a href="#l74"></a>
<span id="l75"></span><a href="#l75"></a>
<span id="l76">    // cached entries</span><a href="#l76"></a>
<span id="l77">    private final Map&lt;String,Reference&lt;PrivateKeyEntry&gt;&gt; entryCacheMap;</span><a href="#l77"></a>
<span id="l78"></span><a href="#l78"></a>
<span id="l79">    X509KeyManagerImpl(Builder builder) {</span><a href="#l79"></a>
<span id="l80">        this(Collections.singletonList(builder));</span><a href="#l80"></a>
<span id="l81">    }</span><a href="#l81"></a>
<span id="l82"></span><a href="#l82"></a>
<span id="l83">    X509KeyManagerImpl(List&lt;Builder&gt; builders) {</span><a href="#l83"></a>
<span id="l84">        this.builders = builders;</span><a href="#l84"></a>
<span id="l85">        uidCounter = new AtomicLong();</span><a href="#l85"></a>
<span id="l86">        entryCacheMap = Collections.synchronizedMap</span><a href="#l86"></a>
<span id="l87">                        (new SizedMap&lt;String,Reference&lt;PrivateKeyEntry&gt;&gt;());</span><a href="#l87"></a>
<span id="l88">    }</span><a href="#l88"></a>
<span id="l89"></span><a href="#l89"></a>
<span id="l90">    // LinkedHashMap with a max size of 10</span><a href="#l90"></a>
<span id="l91">    // see LinkedHashMap JavaDocs</span><a href="#l91"></a>
<span id="l92">    private static class SizedMap&lt;K,V&gt; extends LinkedHashMap&lt;K,V&gt; {</span><a href="#l92"></a>
<span id="l93">        private static final long serialVersionUID = -8211222668790986062L;</span><a href="#l93"></a>
<span id="l94"></span><a href="#l94"></a>
<span id="l95">        @Override protected boolean removeEldestEntry(Map.Entry&lt;K,V&gt; eldest) {</span><a href="#l95"></a>
<span id="l96">            return size() &gt; 10;</span><a href="#l96"></a>
<span id="l97">        }</span><a href="#l97"></a>
<span id="l98">    }</span><a href="#l98"></a>
<span id="l99"></span><a href="#l99"></a>
<span id="l100">    //</span><a href="#l100"></a>
<span id="l101">    // public methods</span><a href="#l101"></a>
<span id="l102">    //</span><a href="#l102"></a>
<span id="l103"></span><a href="#l103"></a>
<span id="l104">    @Override</span><a href="#l104"></a>
<span id="l105">    public X509Certificate[] getCertificateChain(String alias) {</span><a href="#l105"></a>
<span id="l106">        PrivateKeyEntry entry = getEntry(alias);</span><a href="#l106"></a>
<span id="l107">        return entry == null ? null :</span><a href="#l107"></a>
<span id="l108">                (X509Certificate[])entry.getCertificateChain();</span><a href="#l108"></a>
<span id="l109">    }</span><a href="#l109"></a>
<span id="l110"></span><a href="#l110"></a>
<span id="l111">    @Override</span><a href="#l111"></a>
<span id="l112">    public PrivateKey getPrivateKey(String alias) {</span><a href="#l112"></a>
<span id="l113">        PrivateKeyEntry entry = getEntry(alias);</span><a href="#l113"></a>
<span id="l114">        return entry == null ? null : entry.getPrivateKey();</span><a href="#l114"></a>
<span id="l115">    }</span><a href="#l115"></a>
<span id="l116"></span><a href="#l116"></a>
<span id="l117">    @Override</span><a href="#l117"></a>
<span id="l118">    public String chooseClientAlias(String[] keyTypes, Principal[] issuers,</span><a href="#l118"></a>
<span id="l119">            Socket socket) {</span><a href="#l119"></a>
<span id="l120">        return chooseAlias(getKeyTypes(keyTypes), issuers, CheckType.CLIENT,</span><a href="#l120"></a>
<span id="l121">                        getAlgorithmConstraints(socket));</span><a href="#l121"></a>
<span id="l122">    }</span><a href="#l122"></a>
<span id="l123"></span><a href="#l123"></a>
<span id="l124">    @Override</span><a href="#l124"></a>
<span id="l125">    public String chooseEngineClientAlias(String[] keyTypes,</span><a href="#l125"></a>
<span id="l126">            Principal[] issuers, SSLEngine engine) {</span><a href="#l126"></a>
<span id="l127">        return chooseAlias(getKeyTypes(keyTypes), issuers, CheckType.CLIENT,</span><a href="#l127"></a>
<span id="l128">                        getAlgorithmConstraints(engine));</span><a href="#l128"></a>
<span id="l129">    }</span><a href="#l129"></a>
<span id="l130"></span><a href="#l130"></a>
<span id="l131">    @Override</span><a href="#l131"></a>
<span id="l132">    public String chooseServerAlias(String keyType,</span><a href="#l132"></a>
<span id="l133">            Principal[] issuers, Socket socket) {</span><a href="#l133"></a>
<span id="l134">        return chooseAlias(getKeyTypes(keyType), issuers, CheckType.SERVER,</span><a href="#l134"></a>
<span id="l135">            getAlgorithmConstraints(socket),</span><a href="#l135"></a>
<span id="l136">            X509TrustManagerImpl.getRequestedServerNames(socket),</span><a href="#l136"></a>
<span id="l137">            &quot;HTTPS&quot;);    // The SNI HostName is a fully qualified domain name.</span><a href="#l137"></a>
<span id="l138">                         // The certificate selection scheme for SNI HostName</span><a href="#l138"></a>
<span id="l139">                         // is similar to HTTPS endpoint identification scheme</span><a href="#l139"></a>
<span id="l140">                         // implemented in this provider.</span><a href="#l140"></a>
<span id="l141">                         //</span><a href="#l141"></a>
<span id="l142">                         // Using HTTPS endpoint identification scheme to guide</span><a href="#l142"></a>
<span id="l143">                         // the selection of an appropriate authentication</span><a href="#l143"></a>
<span id="l144">                         // certificate according to requested SNI extension.</span><a href="#l144"></a>
<span id="l145">                         //</span><a href="#l145"></a>
<span id="l146">                         // It is not a really HTTPS endpoint identification.</span><a href="#l146"></a>
<span id="l147">    }</span><a href="#l147"></a>
<span id="l148"></span><a href="#l148"></a>
<span id="l149">    @Override</span><a href="#l149"></a>
<span id="l150">    public String chooseEngineServerAlias(String keyType,</span><a href="#l150"></a>
<span id="l151">            Principal[] issuers, SSLEngine engine) {</span><a href="#l151"></a>
<span id="l152">        return chooseAlias(getKeyTypes(keyType), issuers, CheckType.SERVER,</span><a href="#l152"></a>
<span id="l153">            getAlgorithmConstraints(engine),</span><a href="#l153"></a>
<span id="l154">            X509TrustManagerImpl.getRequestedServerNames(engine),</span><a href="#l154"></a>
<span id="l155">            &quot;HTTPS&quot;);    // The SNI HostName is a fully qualified domain name.</span><a href="#l155"></a>
<span id="l156">                         // The certificate selection scheme for SNI HostName</span><a href="#l156"></a>
<span id="l157">                         // is similar to HTTPS endpoint identification scheme</span><a href="#l157"></a>
<span id="l158">                         // implemented in this provider.</span><a href="#l158"></a>
<span id="l159">                         //</span><a href="#l159"></a>
<span id="l160">                         // Using HTTPS endpoint identification scheme to guide</span><a href="#l160"></a>
<span id="l161">                         // the selection of an appropriate authentication</span><a href="#l161"></a>
<span id="l162">                         // certificate according to requested SNI extension.</span><a href="#l162"></a>
<span id="l163">                         //</span><a href="#l163"></a>
<span id="l164">                         // It is not a really HTTPS endpoint identification.</span><a href="#l164"></a>
<span id="l165">    }</span><a href="#l165"></a>
<span id="l166"></span><a href="#l166"></a>
<span id="l167">    @Override</span><a href="#l167"></a>
<span id="l168">    public String[] getClientAliases(String keyType, Principal[] issuers) {</span><a href="#l168"></a>
<span id="l169">        return getAliases(keyType, issuers, CheckType.CLIENT, null);</span><a href="#l169"></a>
<span id="l170">    }</span><a href="#l170"></a>
<span id="l171"></span><a href="#l171"></a>
<span id="l172">    @Override</span><a href="#l172"></a>
<span id="l173">    public String[] getServerAliases(String keyType, Principal[] issuers) {</span><a href="#l173"></a>
<span id="l174">        return getAliases(keyType, issuers, CheckType.SERVER, null);</span><a href="#l174"></a>
<span id="l175">    }</span><a href="#l175"></a>
<span id="l176"></span><a href="#l176"></a>
<span id="l177">    //</span><a href="#l177"></a>
<span id="l178">    // implementation private methods</span><a href="#l178"></a>
<span id="l179">    //</span><a href="#l179"></a>
<span id="l180"></span><a href="#l180"></a>
<span id="l181">    // Gets algorithm constraints of the socket.</span><a href="#l181"></a>
<span id="l182">    private AlgorithmConstraints getAlgorithmConstraints(Socket socket) {</span><a href="#l182"></a>
<span id="l183">        if (socket != null &amp;&amp; socket.isConnected() &amp;&amp;</span><a href="#l183"></a>
<span id="l184">                                        socket instanceof SSLSocket) {</span><a href="#l184"></a>
<span id="l185"></span><a href="#l185"></a>
<span id="l186">            SSLSocket sslSocket = (SSLSocket)socket;</span><a href="#l186"></a>
<span id="l187">            SSLSession session = sslSocket.getHandshakeSession();</span><a href="#l187"></a>
<span id="l188"></span><a href="#l188"></a>
<span id="l189">            if (session != null) {</span><a href="#l189"></a>
<span id="l190">                if (ProtocolVersion.useTLS12PlusSpec(session.getProtocol())) {</span><a href="#l190"></a>
<span id="l191">                    String[] peerSupportedSignAlgs = null;</span><a href="#l191"></a>
<span id="l192"></span><a href="#l192"></a>
<span id="l193">                    if (session instanceof ExtendedSSLSession) {</span><a href="#l193"></a>
<span id="l194">                        ExtendedSSLSession extSession =</span><a href="#l194"></a>
<span id="l195">                            (ExtendedSSLSession)session;</span><a href="#l195"></a>
<span id="l196">                        peerSupportedSignAlgs =</span><a href="#l196"></a>
<span id="l197">                            extSession.getPeerSupportedSignatureAlgorithms();</span><a href="#l197"></a>
<span id="l198">                    }</span><a href="#l198"></a>
<span id="l199"></span><a href="#l199"></a>
<span id="l200">                    return new SSLAlgorithmConstraints(</span><a href="#l200"></a>
<span id="l201">                        sslSocket, peerSupportedSignAlgs, true);</span><a href="#l201"></a>
<span id="l202">                }</span><a href="#l202"></a>
<span id="l203">            }</span><a href="#l203"></a>
<span id="l204"></span><a href="#l204"></a>
<span id="l205">            return new SSLAlgorithmConstraints(sslSocket, true);</span><a href="#l205"></a>
<span id="l206">        }</span><a href="#l206"></a>
<span id="l207"></span><a href="#l207"></a>
<span id="l208">        return new SSLAlgorithmConstraints((SSLSocket)null, true);</span><a href="#l208"></a>
<span id="l209">    }</span><a href="#l209"></a>
<span id="l210"></span><a href="#l210"></a>
<span id="l211">    // Gets algorithm constraints of the engine.</span><a href="#l211"></a>
<span id="l212">    private AlgorithmConstraints getAlgorithmConstraints(SSLEngine engine) {</span><a href="#l212"></a>
<span id="l213">        if (engine != null) {</span><a href="#l213"></a>
<span id="l214">            SSLSession session = engine.getHandshakeSession();</span><a href="#l214"></a>
<span id="l215">            if (session != null) {</span><a href="#l215"></a>
<span id="l216">                if (ProtocolVersion.useTLS12PlusSpec(session.getProtocol())) {</span><a href="#l216"></a>
<span id="l217">                    String[] peerSupportedSignAlgs = null;</span><a href="#l217"></a>
<span id="l218"></span><a href="#l218"></a>
<span id="l219">                    if (session instanceof ExtendedSSLSession) {</span><a href="#l219"></a>
<span id="l220">                        ExtendedSSLSession extSession =</span><a href="#l220"></a>
<span id="l221">                            (ExtendedSSLSession)session;</span><a href="#l221"></a>
<span id="l222">                        peerSupportedSignAlgs =</span><a href="#l222"></a>
<span id="l223">                            extSession.getPeerSupportedSignatureAlgorithms();</span><a href="#l223"></a>
<span id="l224">                    }</span><a href="#l224"></a>
<span id="l225"></span><a href="#l225"></a>
<span id="l226">                    return new SSLAlgorithmConstraints(</span><a href="#l226"></a>
<span id="l227">                        engine, peerSupportedSignAlgs, true);</span><a href="#l227"></a>
<span id="l228">                }</span><a href="#l228"></a>
<span id="l229">            }</span><a href="#l229"></a>
<span id="l230">        }</span><a href="#l230"></a>
<span id="l231"></span><a href="#l231"></a>
<span id="l232">        return new SSLAlgorithmConstraints(engine, true);</span><a href="#l232"></a>
<span id="l233">    }</span><a href="#l233"></a>
<span id="l234"></span><a href="#l234"></a>
<span id="l235">    // we construct the alias we return to JSSE as seen in the code below</span><a href="#l235"></a>
<span id="l236">    // a unique id is included to allow us to reliably cache entries</span><a href="#l236"></a>
<span id="l237">    // between the calls to getCertificateChain() and getPrivateKey()</span><a href="#l237"></a>
<span id="l238">    // even if tokens are inserted or removed</span><a href="#l238"></a>
<span id="l239">    private String makeAlias(EntryStatus entry) {</span><a href="#l239"></a>
<span id="l240">        return uidCounter.incrementAndGet() + &quot;.&quot; + entry.builderIndex + &quot;.&quot;</span><a href="#l240"></a>
<span id="l241">                + entry.alias;</span><a href="#l241"></a>
<span id="l242">    }</span><a href="#l242"></a>
<span id="l243"></span><a href="#l243"></a>
<span id="l244">    private PrivateKeyEntry getEntry(String alias) {</span><a href="#l244"></a>
<span id="l245">        // if the alias is null, return immediately</span><a href="#l245"></a>
<span id="l246">        if (alias == null) {</span><a href="#l246"></a>
<span id="l247">            return null;</span><a href="#l247"></a>
<span id="l248">        }</span><a href="#l248"></a>
<span id="l249"></span><a href="#l249"></a>
<span id="l250">        // try to get the entry from cache</span><a href="#l250"></a>
<span id="l251">        Reference&lt;PrivateKeyEntry&gt; ref = entryCacheMap.get(alias);</span><a href="#l251"></a>
<span id="l252">        PrivateKeyEntry entry = (ref != null) ? ref.get() : null;</span><a href="#l252"></a>
<span id="l253">        if (entry != null) {</span><a href="#l253"></a>
<span id="l254">            return entry;</span><a href="#l254"></a>
<span id="l255">        }</span><a href="#l255"></a>
<span id="l256"></span><a href="#l256"></a>
<span id="l257">        // parse the alias</span><a href="#l257"></a>
<span id="l258">        int firstDot = alias.indexOf('.');</span><a href="#l258"></a>
<span id="l259">        int secondDot = alias.indexOf('.', firstDot + 1);</span><a href="#l259"></a>
<span id="l260">        if ((firstDot == -1) || (secondDot == firstDot)) {</span><a href="#l260"></a>
<span id="l261">            // invalid alias</span><a href="#l261"></a>
<span id="l262">            return null;</span><a href="#l262"></a>
<span id="l263">        }</span><a href="#l263"></a>
<span id="l264">        try {</span><a href="#l264"></a>
<span id="l265">            int builderIndex = Integer.parseInt</span><a href="#l265"></a>
<span id="l266">                                (alias.substring(firstDot + 1, secondDot));</span><a href="#l266"></a>
<span id="l267">            String keyStoreAlias = alias.substring(secondDot + 1);</span><a href="#l267"></a>
<span id="l268">            Builder builder = builders.get(builderIndex);</span><a href="#l268"></a>
<span id="l269">            KeyStore ks = builder.getKeyStore();</span><a href="#l269"></a>
<span id="l270">            Entry newEntry = ks.getEntry</span><a href="#l270"></a>
<span id="l271">                    (keyStoreAlias, builder.getProtectionParameter(alias));</span><a href="#l271"></a>
<span id="l272">            if (newEntry instanceof PrivateKeyEntry == false) {</span><a href="#l272"></a>
<span id="l273">                // unexpected type of entry</span><a href="#l273"></a>
<span id="l274">                return null;</span><a href="#l274"></a>
<span id="l275">            }</span><a href="#l275"></a>
<span id="l276">            entry = (PrivateKeyEntry)newEntry;</span><a href="#l276"></a>
<span id="l277">            entryCacheMap.put(alias, new SoftReference&lt;PrivateKeyEntry&gt;(entry));</span><a href="#l277"></a>
<span id="l278">            return entry;</span><a href="#l278"></a>
<span id="l279">        } catch (Exception e) {</span><a href="#l279"></a>
<span id="l280">            // ignore</span><a href="#l280"></a>
<span id="l281">            return null;</span><a href="#l281"></a>
<span id="l282">        }</span><a href="#l282"></a>
<span id="l283">    }</span><a href="#l283"></a>
<span id="l284"></span><a href="#l284"></a>
<span id="l285">    // Class to help verify that the public key algorithm (and optionally</span><a href="#l285"></a>
<span id="l286">    // the signature algorithm) of a certificate matches what we need.</span><a href="#l286"></a>
<span id="l287">    private static class KeyType {</span><a href="#l287"></a>
<span id="l288"></span><a href="#l288"></a>
<span id="l289">        final String keyAlgorithm;</span><a href="#l289"></a>
<span id="l290"></span><a href="#l290"></a>
<span id="l291">        // In TLS 1.2, the signature algorithm  has been obsoleted by the</span><a href="#l291"></a>
<span id="l292">        // supported_signature_algorithms, and the certificate type no longer</span><a href="#l292"></a>
<span id="l293">        // restricts the algorithm used to sign the certificate.</span><a href="#l293"></a>
<span id="l294">        //</span><a href="#l294"></a>
<span id="l295">        // However, because we don't support certificate type checking other</span><a href="#l295"></a>
<span id="l296">        // than rsa_sign, dss_sign and ecdsa_sign, we don't have to check the</span><a href="#l296"></a>
<span id="l297">        // protocol version here.</span><a href="#l297"></a>
<span id="l298">        final String sigKeyAlgorithm;</span><a href="#l298"></a>
<span id="l299"></span><a href="#l299"></a>
<span id="l300">        KeyType(String algorithm) {</span><a href="#l300"></a>
<span id="l301">            int k = algorithm.indexOf('_');</span><a href="#l301"></a>
<span id="l302">            if (k == -1) {</span><a href="#l302"></a>
<span id="l303">                keyAlgorithm = algorithm;</span><a href="#l303"></a>
<span id="l304">                sigKeyAlgorithm = null;</span><a href="#l304"></a>
<span id="l305">            } else {</span><a href="#l305"></a>
<span id="l306">                keyAlgorithm = algorithm.substring(0, k);</span><a href="#l306"></a>
<span id="l307">                sigKeyAlgorithm = algorithm.substring(k + 1);</span><a href="#l307"></a>
<span id="l308">            }</span><a href="#l308"></a>
<span id="l309">        }</span><a href="#l309"></a>
<span id="l310"></span><a href="#l310"></a>
<span id="l311">        boolean matches(Certificate[] chain) {</span><a href="#l311"></a>
<span id="l312">            if (!chain[0].getPublicKey().getAlgorithm().equals(keyAlgorithm)) {</span><a href="#l312"></a>
<span id="l313">                return false;</span><a href="#l313"></a>
<span id="l314">            }</span><a href="#l314"></a>
<span id="l315">            if (sigKeyAlgorithm == null) {</span><a href="#l315"></a>
<span id="l316">                return true;</span><a href="#l316"></a>
<span id="l317">            }</span><a href="#l317"></a>
<span id="l318">            if (chain.length &gt; 1) {</span><a href="#l318"></a>
<span id="l319">                // if possible, check the public key in the issuer cert</span><a href="#l319"></a>
<span id="l320">                return sigKeyAlgorithm.equals(</span><a href="#l320"></a>
<span id="l321">                        chain[1].getPublicKey().getAlgorithm());</span><a href="#l321"></a>
<span id="l322">            } else {</span><a href="#l322"></a>
<span id="l323">                // Check the signature algorithm of the certificate itself.</span><a href="#l323"></a>
<span id="l324">                // Look for the &quot;withRSA&quot; in &quot;SHA1withRSA&quot;, etc.</span><a href="#l324"></a>
<span id="l325">                X509Certificate issuer = (X509Certificate)chain[0];</span><a href="#l325"></a>
<span id="l326">                String sigAlgName =</span><a href="#l326"></a>
<span id="l327">                        issuer.getSigAlgName().toUpperCase(Locale.ENGLISH);</span><a href="#l327"></a>
<span id="l328">                String pattern =</span><a href="#l328"></a>
<span id="l329">                        &quot;WITH&quot; + sigKeyAlgorithm.toUpperCase(Locale.ENGLISH);</span><a href="#l329"></a>
<span id="l330">                return sigAlgName.contains(pattern);</span><a href="#l330"></a>
<span id="l331">            }</span><a href="#l331"></a>
<span id="l332">        }</span><a href="#l332"></a>
<span id="l333">    }</span><a href="#l333"></a>
<span id="l334"></span><a href="#l334"></a>
<span id="l335">    private static List&lt;KeyType&gt; getKeyTypes(String ... keyTypes) {</span><a href="#l335"></a>
<span id="l336">        if ((keyTypes == null) ||</span><a href="#l336"></a>
<span id="l337">                (keyTypes.length == 0) || (keyTypes[0] == null)) {</span><a href="#l337"></a>
<span id="l338">            return null;</span><a href="#l338"></a>
<span id="l339">        }</span><a href="#l339"></a>
<span id="l340">        List&lt;KeyType&gt; list = new ArrayList&lt;&gt;(keyTypes.length);</span><a href="#l340"></a>
<span id="l341">        for (String keyType : keyTypes) {</span><a href="#l341"></a>
<span id="l342">            list.add(new KeyType(keyType));</span><a href="#l342"></a>
<span id="l343">        }</span><a href="#l343"></a>
<span id="l344">        return list;</span><a href="#l344"></a>
<span id="l345">    }</span><a href="#l345"></a>
<span id="l346"></span><a href="#l346"></a>
<span id="l347">    /*</span><a href="#l347"></a>
<span id="l348">     * Return the best alias that fits the given parameters.</span><a href="#l348"></a>
<span id="l349">     * The algorithm we use is:</span><a href="#l349"></a>
<span id="l350">     *   . scan through all the aliases in all builders in order</span><a href="#l350"></a>
<span id="l351">     *   . as soon as we find a perfect match, return</span><a href="#l351"></a>
<span id="l352">     *     (i.e. a match with a cert that has appropriate key usage,</span><a href="#l352"></a>
<span id="l353">     *      qualified endpoint identity, and is not expired).</span><a href="#l353"></a>
<span id="l354">     *   . if we do not find a perfect match, keep looping and remember</span><a href="#l354"></a>
<span id="l355">     *     the imperfect matches</span><a href="#l355"></a>
<span id="l356">     *   . at the end, sort the imperfect matches. we prefer expired certs</span><a href="#l356"></a>
<span id="l357">     *     with appropriate key usage to certs with the wrong key usage.</span><a href="#l357"></a>
<span id="l358">     *     return the first one of them.</span><a href="#l358"></a>
<span id="l359">     */</span><a href="#l359"></a>
<span id="l360">    private String chooseAlias(List&lt;KeyType&gt; keyTypeList, Principal[] issuers,</span><a href="#l360"></a>
<span id="l361">            CheckType checkType, AlgorithmConstraints constraints) {</span><a href="#l361"></a>
<span id="l362"></span><a href="#l362"></a>
<span id="l363">        return chooseAlias(keyTypeList, issuers,</span><a href="#l363"></a>
<span id="l364">                                    checkType, constraints, null, null);</span><a href="#l364"></a>
<span id="l365">    }</span><a href="#l365"></a>
<span id="l366"></span><a href="#l366"></a>
<span id="l367">    private String chooseAlias(List&lt;KeyType&gt; keyTypeList, Principal[] issuers,</span><a href="#l367"></a>
<span id="l368">            CheckType checkType, AlgorithmConstraints constraints,</span><a href="#l368"></a>
<span id="l369">            List&lt;SNIServerName&gt; requestedServerNames, String idAlgorithm) {</span><a href="#l369"></a>
<span id="l370"></span><a href="#l370"></a>
<span id="l371">        if (keyTypeList == null || keyTypeList.isEmpty()) {</span><a href="#l371"></a>
<span id="l372">            return null;</span><a href="#l372"></a>
<span id="l373">        }</span><a href="#l373"></a>
<span id="l374"></span><a href="#l374"></a>
<span id="l375">        Set&lt;Principal&gt; issuerSet = getIssuerSet(issuers);</span><a href="#l375"></a>
<span id="l376">        List&lt;EntryStatus&gt; allResults = null;</span><a href="#l376"></a>
<span id="l377">        for (int i = 0, n = builders.size(); i &lt; n; i++) {</span><a href="#l377"></a>
<span id="l378">            try {</span><a href="#l378"></a>
<span id="l379">                List&lt;EntryStatus&gt; results = getAliases(i, keyTypeList,</span><a href="#l379"></a>
<span id="l380">                            issuerSet, false, checkType, constraints,</span><a href="#l380"></a>
<span id="l381">                            requestedServerNames, idAlgorithm);</span><a href="#l381"></a>
<span id="l382">                if (results != null) {</span><a href="#l382"></a>
<span id="l383">                    // the results will either be a single perfect match</span><a href="#l383"></a>
<span id="l384">                    // or 1 or more imperfect matches</span><a href="#l384"></a>
<span id="l385">                    // if it's a perfect match, return immediately</span><a href="#l385"></a>
<span id="l386">                    EntryStatus status = results.get(0);</span><a href="#l386"></a>
<span id="l387">                    if (status.checkResult == CheckResult.OK) {</span><a href="#l387"></a>
<span id="l388">                        if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;keymanager&quot;)) {</span><a href="#l388"></a>
<span id="l389">                            SSLLogger.fine(&quot;KeyMgr: choosing key: &quot; + status);</span><a href="#l389"></a>
<span id="l390">                        }</span><a href="#l390"></a>
<span id="l391">                        return makeAlias(status);</span><a href="#l391"></a>
<span id="l392">                    }</span><a href="#l392"></a>
<span id="l393">                    if (allResults == null) {</span><a href="#l393"></a>
<span id="l394">                        allResults = new ArrayList&lt;EntryStatus&gt;();</span><a href="#l394"></a>
<span id="l395">                    }</span><a href="#l395"></a>
<span id="l396">                    allResults.addAll(results);</span><a href="#l396"></a>
<span id="l397">                }</span><a href="#l397"></a>
<span id="l398">            } catch (Exception e) {</span><a href="#l398"></a>
<span id="l399">                // ignore</span><a href="#l399"></a>
<span id="l400">            }</span><a href="#l400"></a>
<span id="l401">        }</span><a href="#l401"></a>
<span id="l402">        if (allResults == null) {</span><a href="#l402"></a>
<span id="l403">            if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;keymanager&quot;)) {</span><a href="#l403"></a>
<span id="l404">                SSLLogger.fine(&quot;KeyMgr: no matching key found&quot;);</span><a href="#l404"></a>
<span id="l405">            }</span><a href="#l405"></a>
<span id="l406">            return null;</span><a href="#l406"></a>
<span id="l407">        }</span><a href="#l407"></a>
<span id="l408">        Collections.sort(allResults);</span><a href="#l408"></a>
<span id="l409">        if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;keymanager&quot;)) {</span><a href="#l409"></a>
<span id="l410">            SSLLogger.fine(</span><a href="#l410"></a>
<span id="l411">                    &quot;KeyMgr: no good matching key found, &quot;</span><a href="#l411"></a>
<span id="l412">                    + &quot;returning best match out of&quot;, allResults);</span><a href="#l412"></a>
<span id="l413">        }</span><a href="#l413"></a>
<span id="l414">        return makeAlias(allResults.get(0));</span><a href="#l414"></a>
<span id="l415">    }</span><a href="#l415"></a>
<span id="l416"></span><a href="#l416"></a>
<span id="l417">    /*</span><a href="#l417"></a>
<span id="l418">     * Return all aliases that (approximately) fit the parameters.</span><a href="#l418"></a>
<span id="l419">     * These are perfect matches plus imperfect matches (expired certificates</span><a href="#l419"></a>
<span id="l420">     * and certificates with the wrong extensions).</span><a href="#l420"></a>
<span id="l421">     * The perfect matches will be first in the array.</span><a href="#l421"></a>
<span id="l422">     */</span><a href="#l422"></a>
<span id="l423">    public String[] getAliases(String keyType, Principal[] issuers,</span><a href="#l423"></a>
<span id="l424">            CheckType checkType, AlgorithmConstraints constraints) {</span><a href="#l424"></a>
<span id="l425">        if (keyType == null) {</span><a href="#l425"></a>
<span id="l426">            return null;</span><a href="#l426"></a>
<span id="l427">        }</span><a href="#l427"></a>
<span id="l428"></span><a href="#l428"></a>
<span id="l429">        Set&lt;Principal&gt; issuerSet = getIssuerSet(issuers);</span><a href="#l429"></a>
<span id="l430">        List&lt;KeyType&gt; keyTypeList = getKeyTypes(keyType);</span><a href="#l430"></a>
<span id="l431">        List&lt;EntryStatus&gt; allResults = null;</span><a href="#l431"></a>
<span id="l432">        for (int i = 0, n = builders.size(); i &lt; n; i++) {</span><a href="#l432"></a>
<span id="l433">            try {</span><a href="#l433"></a>
<span id="l434">                List&lt;EntryStatus&gt; results = getAliases(i, keyTypeList,</span><a href="#l434"></a>
<span id="l435">                                    issuerSet, true, checkType, constraints,</span><a href="#l435"></a>
<span id="l436">                                    null, null);</span><a href="#l436"></a>
<span id="l437">                if (results != null) {</span><a href="#l437"></a>
<span id="l438">                    if (allResults == null) {</span><a href="#l438"></a>
<span id="l439">                        allResults = new ArrayList&lt;&gt;();</span><a href="#l439"></a>
<span id="l440">                    }</span><a href="#l440"></a>
<span id="l441">                    allResults.addAll(results);</span><a href="#l441"></a>
<span id="l442">                }</span><a href="#l442"></a>
<span id="l443">            } catch (Exception e) {</span><a href="#l443"></a>
<span id="l444">                // ignore</span><a href="#l444"></a>
<span id="l445">            }</span><a href="#l445"></a>
<span id="l446">        }</span><a href="#l446"></a>
<span id="l447">        if (allResults == null || allResults.isEmpty()) {</span><a href="#l447"></a>
<span id="l448">            if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;keymanager&quot;)) {</span><a href="#l448"></a>
<span id="l449">                SSLLogger.fine(&quot;KeyMgr: no matching alias found&quot;);</span><a href="#l449"></a>
<span id="l450">            }</span><a href="#l450"></a>
<span id="l451">            return null;</span><a href="#l451"></a>
<span id="l452">        }</span><a href="#l452"></a>
<span id="l453">        Collections.sort(allResults);</span><a href="#l453"></a>
<span id="l454">        if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;keymanager&quot;)) {</span><a href="#l454"></a>
<span id="l455">            SSLLogger.fine(&quot;KeyMgr: getting aliases&quot;, allResults);</span><a href="#l455"></a>
<span id="l456">        }</span><a href="#l456"></a>
<span id="l457">        return toAliases(allResults);</span><a href="#l457"></a>
<span id="l458">    }</span><a href="#l458"></a>
<span id="l459"></span><a href="#l459"></a>
<span id="l460">    // turn candidate entries into unique aliases we can return to JSSE</span><a href="#l460"></a>
<span id="l461">    private String[] toAliases(List&lt;EntryStatus&gt; results) {</span><a href="#l461"></a>
<span id="l462">        String[] s = new String[results.size()];</span><a href="#l462"></a>
<span id="l463">        int i = 0;</span><a href="#l463"></a>
<span id="l464">        for (EntryStatus result : results) {</span><a href="#l464"></a>
<span id="l465">            s[i++] = makeAlias(result);</span><a href="#l465"></a>
<span id="l466">        }</span><a href="#l466"></a>
<span id="l467">        return s;</span><a href="#l467"></a>
<span id="l468">    }</span><a href="#l468"></a>
<span id="l469"></span><a href="#l469"></a>
<span id="l470">    // make a Set out of the array</span><a href="#l470"></a>
<span id="l471">    private Set&lt;Principal&gt; getIssuerSet(Principal[] issuers) {</span><a href="#l471"></a>
<span id="l472">        if ((issuers != null) &amp;&amp; (issuers.length != 0)) {</span><a href="#l472"></a>
<span id="l473">            return new HashSet&lt;&gt;(Arrays.asList(issuers));</span><a href="#l473"></a>
<span id="l474">        } else {</span><a href="#l474"></a>
<span id="l475">            return null;</span><a href="#l475"></a>
<span id="l476">        }</span><a href="#l476"></a>
<span id="l477">    }</span><a href="#l477"></a>
<span id="l478"></span><a href="#l478"></a>
<span id="l479">    // a candidate match</span><a href="#l479"></a>
<span id="l480">    // identifies the entry by builder and alias</span><a href="#l480"></a>
<span id="l481">    // and includes the result of the certificate check</span><a href="#l481"></a>
<span id="l482">    private static class EntryStatus implements Comparable&lt;EntryStatus&gt; {</span><a href="#l482"></a>
<span id="l483"></span><a href="#l483"></a>
<span id="l484">        final int builderIndex;</span><a href="#l484"></a>
<span id="l485">        final int keyIndex;</span><a href="#l485"></a>
<span id="l486">        final String alias;</span><a href="#l486"></a>
<span id="l487">        final CheckResult checkResult;</span><a href="#l487"></a>
<span id="l488"></span><a href="#l488"></a>
<span id="l489">        EntryStatus(int builderIndex, int keyIndex, String alias,</span><a href="#l489"></a>
<span id="l490">                Certificate[] chain, CheckResult checkResult) {</span><a href="#l490"></a>
<span id="l491">            this.builderIndex = builderIndex;</span><a href="#l491"></a>
<span id="l492">            this.keyIndex = keyIndex;</span><a href="#l492"></a>
<span id="l493">            this.alias = alias;</span><a href="#l493"></a>
<span id="l494">            this.checkResult = checkResult;</span><a href="#l494"></a>
<span id="l495">        }</span><a href="#l495"></a>
<span id="l496"></span><a href="#l496"></a>
<span id="l497">        @Override</span><a href="#l497"></a>
<span id="l498">        public int compareTo(EntryStatus other) {</span><a href="#l498"></a>
<span id="l499">            int result = this.checkResult.compareTo(other.checkResult);</span><a href="#l499"></a>
<span id="l500">            return (result == 0) ? (this.keyIndex - other.keyIndex) : result;</span><a href="#l500"></a>
<span id="l501">        }</span><a href="#l501"></a>
<span id="l502"></span><a href="#l502"></a>
<span id="l503">        @Override</span><a href="#l503"></a>
<span id="l504">        public String toString() {</span><a href="#l504"></a>
<span id="l505">            String s = alias + &quot; (verified: &quot; + checkResult + &quot;)&quot;;</span><a href="#l505"></a>
<span id="l506">            if (builderIndex == 0) {</span><a href="#l506"></a>
<span id="l507">                return s;</span><a href="#l507"></a>
<span id="l508">            } else {</span><a href="#l508"></a>
<span id="l509">                return &quot;Builder #&quot; + builderIndex + &quot;, alias: &quot; + s;</span><a href="#l509"></a>
<span id="l510">            }</span><a href="#l510"></a>
<span id="l511">        }</span><a href="#l511"></a>
<span id="l512">    }</span><a href="#l512"></a>
<span id="l513"></span><a href="#l513"></a>
<span id="l514">    // enum for the type of certificate check we want to perform</span><a href="#l514"></a>
<span id="l515">    // (client or server)</span><a href="#l515"></a>
<span id="l516">    // also includes the check code itself</span><a href="#l516"></a>
<span id="l517">    private static enum CheckType {</span><a href="#l517"></a>
<span id="l518"></span><a href="#l518"></a>
<span id="l519">        // enum constant for &quot;no check&quot; (currently not used)</span><a href="#l519"></a>
<span id="l520">        NONE(Collections.&lt;String&gt;emptySet()),</span><a href="#l520"></a>
<span id="l521"></span><a href="#l521"></a>
<span id="l522">        // enum constant for &quot;tls client&quot; check</span><a href="#l522"></a>
<span id="l523">        // valid EKU for TLS client: any, tls_client</span><a href="#l523"></a>
<span id="l524">        CLIENT(new HashSet&lt;String&gt;(Arrays.asList(new String[] {</span><a href="#l524"></a>
<span id="l525">            &quot;2.5.29.37.0&quot;, &quot;1.3.6.1.5.5.7.3.2&quot; }))),</span><a href="#l525"></a>
<span id="l526"></span><a href="#l526"></a>
<span id="l527">        // enum constant for &quot;tls server&quot; check</span><a href="#l527"></a>
<span id="l528">        // valid EKU for TLS server: any, tls_server, ns_sgc, ms_sgc</span><a href="#l528"></a>
<span id="l529">        SERVER(new HashSet&lt;String&gt;(Arrays.asList(new String[] {</span><a href="#l529"></a>
<span id="l530">            &quot;2.5.29.37.0&quot;, &quot;1.3.6.1.5.5.7.3.1&quot;, &quot;2.16.840.1.113730.4.1&quot;,</span><a href="#l530"></a>
<span id="l531">            &quot;1.3.6.1.4.1.311.10.3.3&quot; })));</span><a href="#l531"></a>
<span id="l532"></span><a href="#l532"></a>
<span id="l533">        // set of valid EKU values for this type</span><a href="#l533"></a>
<span id="l534">        final Set&lt;String&gt; validEku;</span><a href="#l534"></a>
<span id="l535"></span><a href="#l535"></a>
<span id="l536">        CheckType(Set&lt;String&gt; validEku) {</span><a href="#l536"></a>
<span id="l537">            this.validEku = validEku;</span><a href="#l537"></a>
<span id="l538">        }</span><a href="#l538"></a>
<span id="l539"></span><a href="#l539"></a>
<span id="l540">        private static boolean getBit(boolean[] keyUsage, int bit) {</span><a href="#l540"></a>
<span id="l541">            return (bit &lt; keyUsage.length) &amp;&amp; keyUsage[bit];</span><a href="#l541"></a>
<span id="l542">        }</span><a href="#l542"></a>
<span id="l543"></span><a href="#l543"></a>
<span id="l544">        // Check if this certificate is appropriate for this type of use</span><a href="#l544"></a>
<span id="l545">        // first check extensions, if they match, check expiration.</span><a href="#l545"></a>
<span id="l546">        //</span><a href="#l546"></a>
<span id="l547">        // Note: we may want to move this code into the sun.security.validator</span><a href="#l547"></a>
<span id="l548">        // package</span><a href="#l548"></a>
<span id="l549">        CheckResult check(X509Certificate cert, Date date,</span><a href="#l549"></a>
<span id="l550">                List&lt;SNIServerName&gt; serverNames, String idAlgorithm) {</span><a href="#l550"></a>
<span id="l551"></span><a href="#l551"></a>
<span id="l552">            if (this == NONE) {</span><a href="#l552"></a>
<span id="l553">                return CheckResult.OK;</span><a href="#l553"></a>
<span id="l554">            }</span><a href="#l554"></a>
<span id="l555"></span><a href="#l555"></a>
<span id="l556">            // check extensions</span><a href="#l556"></a>
<span id="l557">            try {</span><a href="#l557"></a>
<span id="l558">                // check extended key usage</span><a href="#l558"></a>
<span id="l559">                List&lt;String&gt; certEku = cert.getExtendedKeyUsage();</span><a href="#l559"></a>
<span id="l560">                if ((certEku != null) &amp;&amp;</span><a href="#l560"></a>
<span id="l561">                        Collections.disjoint(validEku, certEku)) {</span><a href="#l561"></a>
<span id="l562">                    // if extension present and it does not contain any of</span><a href="#l562"></a>
<span id="l563">                    // the valid EKU OIDs, return extension_mismatch</span><a href="#l563"></a>
<span id="l564">                    return CheckResult.EXTENSION_MISMATCH;</span><a href="#l564"></a>
<span id="l565">                }</span><a href="#l565"></a>
<span id="l566"></span><a href="#l566"></a>
<span id="l567">                // check key usage</span><a href="#l567"></a>
<span id="l568">                boolean[] ku = cert.getKeyUsage();</span><a href="#l568"></a>
<span id="l569">                if (ku != null) {</span><a href="#l569"></a>
<span id="l570">                    String algorithm = cert.getPublicKey().getAlgorithm();</span><a href="#l570"></a>
<span id="l571">                    boolean supportsDigitalSignature = getBit(ku, 0);</span><a href="#l571"></a>
<span id="l572">                    switch (algorithm) {</span><a href="#l572"></a>
<span id="l573">                        case &quot;RSA&quot;:</span><a href="#l573"></a>
<span id="l574">                            // require either signature bit</span><a href="#l574"></a>
<span id="l575">                            // or if server also allow key encipherment bit</span><a href="#l575"></a>
<span id="l576">                            if (!supportsDigitalSignature) {</span><a href="#l576"></a>
<span id="l577">                                if (this == CLIENT || getBit(ku, 2) == false) {</span><a href="#l577"></a>
<span id="l578">                                    return CheckResult.EXTENSION_MISMATCH;</span><a href="#l578"></a>
<span id="l579">                                }</span><a href="#l579"></a>
<span id="l580">                            }</span><a href="#l580"></a>
<span id="l581">                            break;</span><a href="#l581"></a>
<span id="l582">                        case &quot;RSASSA-PSS&quot;:</span><a href="#l582"></a>
<span id="l583">                            if (!supportsDigitalSignature &amp;&amp; (this == SERVER)) {</span><a href="#l583"></a>
<span id="l584">                                return CheckResult.EXTENSION_MISMATCH;</span><a href="#l584"></a>
<span id="l585">                            }</span><a href="#l585"></a>
<span id="l586">                            break;</span><a href="#l586"></a>
<span id="l587">                        case &quot;DSA&quot;:</span><a href="#l587"></a>
<span id="l588">                            // require signature bit</span><a href="#l588"></a>
<span id="l589">                            if (!supportsDigitalSignature) {</span><a href="#l589"></a>
<span id="l590">                                return CheckResult.EXTENSION_MISMATCH;</span><a href="#l590"></a>
<span id="l591">                            }</span><a href="#l591"></a>
<span id="l592">                            break;</span><a href="#l592"></a>
<span id="l593">                        case &quot;DH&quot;:</span><a href="#l593"></a>
<span id="l594">                            // require keyagreement bit</span><a href="#l594"></a>
<span id="l595">                            if (getBit(ku, 4) == false) {</span><a href="#l595"></a>
<span id="l596">                                return CheckResult.EXTENSION_MISMATCH;</span><a href="#l596"></a>
<span id="l597">                            }</span><a href="#l597"></a>
<span id="l598">                            break;</span><a href="#l598"></a>
<span id="l599">                        case &quot;EC&quot;:</span><a href="#l599"></a>
<span id="l600">                            // require signature bit</span><a href="#l600"></a>
<span id="l601">                            if (!supportsDigitalSignature) {</span><a href="#l601"></a>
<span id="l602">                                return CheckResult.EXTENSION_MISMATCH;</span><a href="#l602"></a>
<span id="l603">                            }</span><a href="#l603"></a>
<span id="l604">                            // For servers, also require key agreement.</span><a href="#l604"></a>
<span id="l605">                            // This is not totally accurate as the keyAgreement</span><a href="#l605"></a>
<span id="l606">                            // bit is only necessary for static ECDH key</span><a href="#l606"></a>
<span id="l607">                            // exchange and not ephemeral ECDH. We leave it in</span><a href="#l607"></a>
<span id="l608">                            // for now until there are signs that this check</span><a href="#l608"></a>
<span id="l609">                            // causes problems for real world EC certificates.</span><a href="#l609"></a>
<span id="l610">                            if ((this == SERVER) &amp;&amp; (getBit(ku, 4) == false)) {</span><a href="#l610"></a>
<span id="l611">                                return CheckResult.EXTENSION_MISMATCH;</span><a href="#l611"></a>
<span id="l612">                            }</span><a href="#l612"></a>
<span id="l613">                            break;</span><a href="#l613"></a>
<span id="l614">                    }</span><a href="#l614"></a>
<span id="l615">                }</span><a href="#l615"></a>
<span id="l616">            } catch (CertificateException e) {</span><a href="#l616"></a>
<span id="l617">                // extensions unparseable, return failure</span><a href="#l617"></a>
<span id="l618">                return CheckResult.EXTENSION_MISMATCH;</span><a href="#l618"></a>
<span id="l619">            }</span><a href="#l619"></a>
<span id="l620"></span><a href="#l620"></a>
<span id="l621">            try {</span><a href="#l621"></a>
<span id="l622">                cert.checkValidity(date);</span><a href="#l622"></a>
<span id="l623">            } catch (CertificateException e) {</span><a href="#l623"></a>
<span id="l624">                return CheckResult.EXPIRED;</span><a href="#l624"></a>
<span id="l625">            }</span><a href="#l625"></a>
<span id="l626"></span><a href="#l626"></a>
<span id="l627">            if (serverNames != null &amp;&amp; !serverNames.isEmpty()) {</span><a href="#l627"></a>
<span id="l628">                for (SNIServerName serverName : serverNames) {</span><a href="#l628"></a>
<span id="l629">                    if (serverName.getType() ==</span><a href="#l629"></a>
<span id="l630">                                StandardConstants.SNI_HOST_NAME) {</span><a href="#l630"></a>
<span id="l631">                        if (!(serverName instanceof SNIHostName)) {</span><a href="#l631"></a>
<span id="l632">                            try {</span><a href="#l632"></a>
<span id="l633">                                serverName =</span><a href="#l633"></a>
<span id="l634">                                    new SNIHostName(serverName.getEncoded());</span><a href="#l634"></a>
<span id="l635">                            } catch (IllegalArgumentException iae) {</span><a href="#l635"></a>
<span id="l636">                                // unlikely to happen, just in case ...</span><a href="#l636"></a>
<span id="l637">                                if (SSLLogger.isOn &amp;&amp;</span><a href="#l637"></a>
<span id="l638">                                        SSLLogger.isOn(&quot;keymanager&quot;)) {</span><a href="#l638"></a>
<span id="l639">                                    SSLLogger.fine(</span><a href="#l639"></a>
<span id="l640">                                       &quot;Illegal server name: &quot; + serverName);</span><a href="#l640"></a>
<span id="l641">                                }</span><a href="#l641"></a>
<span id="l642"></span><a href="#l642"></a>
<span id="l643">                                return CheckResult.INSENSITIVE;</span><a href="#l643"></a>
<span id="l644">                            }</span><a href="#l644"></a>
<span id="l645">                        }</span><a href="#l645"></a>
<span id="l646">                        String hostname =</span><a href="#l646"></a>
<span id="l647">                                ((SNIHostName)serverName).getAsciiName();</span><a href="#l647"></a>
<span id="l648"></span><a href="#l648"></a>
<span id="l649">                        try {</span><a href="#l649"></a>
<span id="l650">                            X509TrustManagerImpl.checkIdentity(hostname,</span><a href="#l650"></a>
<span id="l651">                                                        cert, idAlgorithm);</span><a href="#l651"></a>
<span id="l652">                        } catch (CertificateException e) {</span><a href="#l652"></a>
<span id="l653">                            if (SSLLogger.isOn &amp;&amp;</span><a href="#l653"></a>
<span id="l654">                                    SSLLogger.isOn(&quot;keymanager&quot;)) {</span><a href="#l654"></a>
<span id="l655">                                SSLLogger.fine(</span><a href="#l655"></a>
<span id="l656">                                    &quot;Certificate identity does not match &quot; +</span><a href="#l656"></a>
<span id="l657">                                    &quot;Server Name Inidication (SNI): &quot; +</span><a href="#l657"></a>
<span id="l658">                                    hostname);</span><a href="#l658"></a>
<span id="l659">                            }</span><a href="#l659"></a>
<span id="l660">                            return CheckResult.INSENSITIVE;</span><a href="#l660"></a>
<span id="l661">                        }</span><a href="#l661"></a>
<span id="l662"></span><a href="#l662"></a>
<span id="l663">                        break;</span><a href="#l663"></a>
<span id="l664">                    }</span><a href="#l664"></a>
<span id="l665">                }</span><a href="#l665"></a>
<span id="l666">            }</span><a href="#l666"></a>
<span id="l667"></span><a href="#l667"></a>
<span id="l668">            return CheckResult.OK;</span><a href="#l668"></a>
<span id="l669">        }</span><a href="#l669"></a>
<span id="l670"></span><a href="#l670"></a>
<span id="l671">        public String getValidator() {</span><a href="#l671"></a>
<span id="l672">            if (this == CLIENT) {</span><a href="#l672"></a>
<span id="l673">                return Validator.VAR_TLS_CLIENT;</span><a href="#l673"></a>
<span id="l674">            } else if (this == SERVER) {</span><a href="#l674"></a>
<span id="l675">                return Validator.VAR_TLS_SERVER;</span><a href="#l675"></a>
<span id="l676">            }</span><a href="#l676"></a>
<span id="l677">            return Validator.VAR_GENERIC;</span><a href="#l677"></a>
<span id="l678">        }</span><a href="#l678"></a>
<span id="l679">    }</span><a href="#l679"></a>
<span id="l680"></span><a href="#l680"></a>
<span id="l681">    // enum for the result of the extension check</span><a href="#l681"></a>
<span id="l682">    // NOTE: the order of the constants is important as they are used</span><a href="#l682"></a>
<span id="l683">    // for sorting, i.e. OK is best, followed by EXPIRED and EXTENSION_MISMATCH</span><a href="#l683"></a>
<span id="l684">    private static enum CheckResult {</span><a href="#l684"></a>
<span id="l685">        OK,                     // ok or not checked</span><a href="#l685"></a>
<span id="l686">        INSENSITIVE,            // server name indication insensitive</span><a href="#l686"></a>
<span id="l687">        EXPIRED,                // extensions valid but cert expired</span><a href="#l687"></a>
<span id="l688">        EXTENSION_MISMATCH,     // extensions invalid (expiration not checked)</span><a href="#l688"></a>
<span id="l689">    }</span><a href="#l689"></a>
<span id="l690"></span><a href="#l690"></a>
<span id="l691">    /*</span><a href="#l691"></a>
<span id="l692">     * Return a List of all candidate matches in the specified builder</span><a href="#l692"></a>
<span id="l693">     * that fit the parameters.</span><a href="#l693"></a>
<span id="l694">     * We exclude entries in the KeyStore if they are not:</span><a href="#l694"></a>
<span id="l695">     *  . private key entries</span><a href="#l695"></a>
<span id="l696">     *  . the certificates are not X509 certificates</span><a href="#l696"></a>
<span id="l697">     *  . the algorithm of the key in the EE cert doesn't match one of keyTypes</span><a href="#l697"></a>
<span id="l698">     *  . none of the certs is issued by a Principal in issuerSet</span><a href="#l698"></a>
<span id="l699">     * Using those entries would not be possible or they would almost</span><a href="#l699"></a>
<span id="l700">     * certainly be rejected by the peer.</span><a href="#l700"></a>
<span id="l701">     *</span><a href="#l701"></a>
<span id="l702">     * In addition to those checks, we also check the extensions in the EE</span><a href="#l702"></a>
<span id="l703">     * cert and its expiration. Even if there is a mismatch, we include</span><a href="#l703"></a>
<span id="l704">     * such certificates because they technically work and might be accepted</span><a href="#l704"></a>
<span id="l705">     * by the peer. This leads to more graceful failure and better error</span><a href="#l705"></a>
<span id="l706">     * messages if the cert expires from one day to the next.</span><a href="#l706"></a>
<span id="l707">     *</span><a href="#l707"></a>
<span id="l708">     * The return values are:</span><a href="#l708"></a>
<span id="l709">     *   . null, if there are no matching entries at all</span><a href="#l709"></a>
<span id="l710">     *   . if 'findAll' is 'false' and there is a perfect match, a List</span><a href="#l710"></a>
<span id="l711">     *     with a single element (early return)</span><a href="#l711"></a>
<span id="l712">     *   . if 'findAll' is 'false' and there is NO perfect match, a List</span><a href="#l712"></a>
<span id="l713">     *     with all the imperfect matches (expired, wrong extensions)</span><a href="#l713"></a>
<span id="l714">     *   . if 'findAll' is 'true', a List with all perfect and imperfect</span><a href="#l714"></a>
<span id="l715">     *     matches</span><a href="#l715"></a>
<span id="l716">     */</span><a href="#l716"></a>
<span id="l717">    private List&lt;EntryStatus&gt; getAliases(int builderIndex,</span><a href="#l717"></a>
<span id="l718">            List&lt;KeyType&gt; keyTypes, Set&lt;Principal&gt; issuerSet,</span><a href="#l718"></a>
<span id="l719">            boolean findAll, CheckType checkType,</span><a href="#l719"></a>
<span id="l720">            AlgorithmConstraints constraints,</span><a href="#l720"></a>
<span id="l721">            List&lt;SNIServerName&gt; requestedServerNames,</span><a href="#l721"></a>
<span id="l722">            String idAlgorithm) throws Exception {</span><a href="#l722"></a>
<span id="l723"></span><a href="#l723"></a>
<span id="l724">        Builder builder = builders.get(builderIndex);</span><a href="#l724"></a>
<span id="l725">        KeyStore ks = builder.getKeyStore();</span><a href="#l725"></a>
<span id="l726">        List&lt;EntryStatus&gt; results = null;</span><a href="#l726"></a>
<span id="l727">        Date date = verificationDate;</span><a href="#l727"></a>
<span id="l728">        boolean preferred = false;</span><a href="#l728"></a>
<span id="l729">        for (Enumeration&lt;String&gt; e = ks.aliases(); e.hasMoreElements(); ) {</span><a href="#l729"></a>
<span id="l730">            String alias = e.nextElement();</span><a href="#l730"></a>
<span id="l731">            // check if it is a key entry (private key or secret key)</span><a href="#l731"></a>
<span id="l732">            if (!ks.isKeyEntry(alias)) {</span><a href="#l732"></a>
<span id="l733">                continue;</span><a href="#l733"></a>
<span id="l734">            }</span><a href="#l734"></a>
<span id="l735"></span><a href="#l735"></a>
<span id="l736">            Certificate[] chain = ks.getCertificateChain(alias);</span><a href="#l736"></a>
<span id="l737">            if ((chain == null) || (chain.length == 0)) {</span><a href="#l737"></a>
<span id="l738">                // must be secret key entry, ignore</span><a href="#l738"></a>
<span id="l739">                continue;</span><a href="#l739"></a>
<span id="l740">            }</span><a href="#l740"></a>
<span id="l741"></span><a href="#l741"></a>
<span id="l742">            boolean incompatible = false;</span><a href="#l742"></a>
<span id="l743">            for (Certificate cert : chain) {</span><a href="#l743"></a>
<span id="l744">                if (cert instanceof X509Certificate == false) {</span><a href="#l744"></a>
<span id="l745">                    // not an X509Certificate, ignore this alias</span><a href="#l745"></a>
<span id="l746">                    incompatible = true;</span><a href="#l746"></a>
<span id="l747">                    break;</span><a href="#l747"></a>
<span id="l748">                }</span><a href="#l748"></a>
<span id="l749">            }</span><a href="#l749"></a>
<span id="l750">            if (incompatible) {</span><a href="#l750"></a>
<span id="l751">                continue;</span><a href="#l751"></a>
<span id="l752">            }</span><a href="#l752"></a>
<span id="l753"></span><a href="#l753"></a>
<span id="l754">            // check keytype</span><a href="#l754"></a>
<span id="l755">            int keyIndex = -1;</span><a href="#l755"></a>
<span id="l756">            int j = 0;</span><a href="#l756"></a>
<span id="l757">            for (KeyType keyType : keyTypes) {</span><a href="#l757"></a>
<span id="l758">                if (keyType.matches(chain)) {</span><a href="#l758"></a>
<span id="l759">                    keyIndex = j;</span><a href="#l759"></a>
<span id="l760">                    break;</span><a href="#l760"></a>
<span id="l761">                }</span><a href="#l761"></a>
<span id="l762">                j++;</span><a href="#l762"></a>
<span id="l763">            }</span><a href="#l763"></a>
<span id="l764">            if (keyIndex == -1) {</span><a href="#l764"></a>
<span id="l765">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;keymanager&quot;)) {</span><a href="#l765"></a>
<span id="l766">                    SSLLogger.fine(&quot;Ignore alias &quot; + alias</span><a href="#l766"></a>
<span id="l767">                                + &quot;: key algorithm does not match&quot;);</span><a href="#l767"></a>
<span id="l768">                }</span><a href="#l768"></a>
<span id="l769">                continue;</span><a href="#l769"></a>
<span id="l770">            }</span><a href="#l770"></a>
<span id="l771">            // check issuers</span><a href="#l771"></a>
<span id="l772">            if (issuerSet != null) {</span><a href="#l772"></a>
<span id="l773">                boolean found = false;</span><a href="#l773"></a>
<span id="l774">                for (Certificate cert : chain) {</span><a href="#l774"></a>
<span id="l775">                    X509Certificate xcert = (X509Certificate)cert;</span><a href="#l775"></a>
<span id="l776">                    if (issuerSet.contains(xcert.getIssuerX500Principal())) {</span><a href="#l776"></a>
<span id="l777">                        found = true;</span><a href="#l777"></a>
<span id="l778">                        break;</span><a href="#l778"></a>
<span id="l779">                    }</span><a href="#l779"></a>
<span id="l780">                }</span><a href="#l780"></a>
<span id="l781">                if (found == false) {</span><a href="#l781"></a>
<span id="l782">                    if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;keymanager&quot;)) {</span><a href="#l782"></a>
<span id="l783">                        SSLLogger.fine(</span><a href="#l783"></a>
<span id="l784">                                &quot;Ignore alias &quot; + alias</span><a href="#l784"></a>
<span id="l785">                                + &quot;: issuers do not match&quot;);</span><a href="#l785"></a>
<span id="l786">                    }</span><a href="#l786"></a>
<span id="l787">                    continue;</span><a href="#l787"></a>
<span id="l788">                }</span><a href="#l788"></a>
<span id="l789">            }</span><a href="#l789"></a>
<span id="l790"></span><a href="#l790"></a>
<span id="l791">            // check the algorithm constraints</span><a href="#l791"></a>
<span id="l792">            if (constraints != null &amp;&amp;</span><a href="#l792"></a>
<span id="l793">                    !conformsToAlgorithmConstraints(constraints, chain,</span><a href="#l793"></a>
<span id="l794">                            checkType.getValidator())) {</span><a href="#l794"></a>
<span id="l795"></span><a href="#l795"></a>
<span id="l796">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;keymanager&quot;)) {</span><a href="#l796"></a>
<span id="l797">                    SSLLogger.fine(&quot;Ignore alias &quot; + alias +</span><a href="#l797"></a>
<span id="l798">                            &quot;: certificate list does not conform to &quot; +</span><a href="#l798"></a>
<span id="l799">                            &quot;algorithm constraints&quot;);</span><a href="#l799"></a>
<span id="l800">                }</span><a href="#l800"></a>
<span id="l801">                continue;</span><a href="#l801"></a>
<span id="l802">            }</span><a href="#l802"></a>
<span id="l803"></span><a href="#l803"></a>
<span id="l804">            if (date == null) {</span><a href="#l804"></a>
<span id="l805">                date = new Date();</span><a href="#l805"></a>
<span id="l806">            }</span><a href="#l806"></a>
<span id="l807">            CheckResult checkResult =</span><a href="#l807"></a>
<span id="l808">                    checkType.check((X509Certificate)chain[0], date,</span><a href="#l808"></a>
<span id="l809">                                    requestedServerNames, idAlgorithm);</span><a href="#l809"></a>
<span id="l810">            EntryStatus status =</span><a href="#l810"></a>
<span id="l811">                    new EntryStatus(builderIndex, keyIndex,</span><a href="#l811"></a>
<span id="l812">                                        alias, chain, checkResult);</span><a href="#l812"></a>
<span id="l813">            if (!preferred &amp;&amp; checkResult == CheckResult.OK &amp;&amp; keyIndex == 0) {</span><a href="#l813"></a>
<span id="l814">                preferred = true;</span><a href="#l814"></a>
<span id="l815">            }</span><a href="#l815"></a>
<span id="l816">            if (preferred &amp;&amp; (findAll == false)) {</span><a href="#l816"></a>
<span id="l817">                // if we have a good match and do not need all matches,</span><a href="#l817"></a>
<span id="l818">                // return immediately</span><a href="#l818"></a>
<span id="l819">                return Collections.singletonList(status);</span><a href="#l819"></a>
<span id="l820">            } else {</span><a href="#l820"></a>
<span id="l821">                if (results == null) {</span><a href="#l821"></a>
<span id="l822">                    results = new ArrayList&lt;&gt;();</span><a href="#l822"></a>
<span id="l823">                }</span><a href="#l823"></a>
<span id="l824">                results.add(status);</span><a href="#l824"></a>
<span id="l825">            }</span><a href="#l825"></a>
<span id="l826">        }</span><a href="#l826"></a>
<span id="l827">        return results;</span><a href="#l827"></a>
<span id="l828">    }</span><a href="#l828"></a>
<span id="l829"></span><a href="#l829"></a>
<span id="l830">    private static boolean conformsToAlgorithmConstraints(</span><a href="#l830"></a>
<span id="l831">            AlgorithmConstraints constraints, Certificate[] chain,</span><a href="#l831"></a>
<span id="l832">            String variant) {</span><a href="#l832"></a>
<span id="l833"></span><a href="#l833"></a>
<span id="l834">        AlgorithmChecker checker = new AlgorithmChecker(constraints, variant);</span><a href="#l834"></a>
<span id="l835">        try {</span><a href="#l835"></a>
<span id="l836">            checker.init(false);</span><a href="#l836"></a>
<span id="l837">        } catch (CertPathValidatorException cpve) {</span><a href="#l837"></a>
<span id="l838">            // unlikely to happen</span><a href="#l838"></a>
<span id="l839">            if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;keymanager&quot;)) {</span><a href="#l839"></a>
<span id="l840">                SSLLogger.fine(</span><a href="#l840"></a>
<span id="l841">                    &quot;Cannot initialize algorithm constraints checker&quot;, cpve);</span><a href="#l841"></a>
<span id="l842">            }</span><a href="#l842"></a>
<span id="l843"></span><a href="#l843"></a>
<span id="l844">            return false;</span><a href="#l844"></a>
<span id="l845">        }</span><a href="#l845"></a>
<span id="l846"></span><a href="#l846"></a>
<span id="l847">        // It is a forward checker, so we need to check from trust to target.</span><a href="#l847"></a>
<span id="l848">        for (int i = chain.length - 1; i &gt;= 0; i--) {</span><a href="#l848"></a>
<span id="l849">            Certificate cert = chain[i];</span><a href="#l849"></a>
<span id="l850">            try {</span><a href="#l850"></a>
<span id="l851">                // We don't care about the unresolved critical extensions.</span><a href="#l851"></a>
<span id="l852">                checker.check(cert, Collections.&lt;String&gt;emptySet());</span><a href="#l852"></a>
<span id="l853">            } catch (CertPathValidatorException cpve) {</span><a href="#l853"></a>
<span id="l854">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;keymanager&quot;)) {</span><a href="#l854"></a>
<span id="l855">                    SSLLogger.fine(&quot;Certificate does not conform to &quot; +</span><a href="#l855"></a>
<span id="l856">                            &quot;algorithm constraints&quot;, cert, cpve);</span><a href="#l856"></a>
<span id="l857">                }</span><a href="#l857"></a>
<span id="l858"></span><a href="#l858"></a>
<span id="l859">                return false;</span><a href="#l859"></a>
<span id="l860">            }</span><a href="#l860"></a>
<span id="l861">        }</span><a href="#l861"></a>
<span id="l862"></span><a href="#l862"></a>
<span id="l863">        return true;</span><a href="#l863"></a>
<span id="l864">    }</span><a href="#l864"></a>
<span id="l865">}</span><a href="#l865"></a></pre>
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

