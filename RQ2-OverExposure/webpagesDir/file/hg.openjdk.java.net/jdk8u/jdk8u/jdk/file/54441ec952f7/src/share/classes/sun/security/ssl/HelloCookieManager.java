<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 54441ec952f7 src/share/classes/sun/security/ssl/HelloCookieManager.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk8u/jdk8u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/shortlog/54441ec952f7">log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/graph/54441ec952f7">graph</a></li>
<li><a href="/jdk8u/jdk8u/jdk/tags">tags</a></li>
<li><a href="/jdk8u/jdk8u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/rev/54441ec952f7">changeset</a></li>
<li><a href="/jdk8u/jdk8u/jdk/file/54441ec952f7/src/share/classes/sun/security/ssl/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/ssl/HelloCookieManager.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/54441ec952f7/src/share/classes/sun/security/ssl/HelloCookieManager.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/54441ec952f7/src/share/classes/sun/security/ssl/HelloCookieManager.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/54441ec952f7/src/share/classes/sun/security/ssl/HelloCookieManager.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/54441ec952f7/src/share/classes/sun/security/ssl/HelloCookieManager.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/54441ec952f7/src/share/classes/sun/security/ssl/HelloCookieManager.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/ssl/HelloCookieManager.java @ 14574:54441ec952f7</h3>

<form class="search" action="/jdk8u/jdk8u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk8u/jdk8u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8269618: Better session identification
Reviewed-by: andrew</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#99;&#118;&#101;&#114;&#103;&#104;&#101;&#115;&#101;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Fri, 15 Oct 2021 03:11:56 +0100</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/646e29e5330b/src/share/classes/sun/security/ssl/HelloCookieManager.java">646e29e5330b</a> </td>
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
<span id="l2"> * Copyright (c) 2018, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l28">import java.io.IOException;</span><a href="#l28"></a>
<span id="l29">import java.security.MessageDigest;</span><a href="#l29"></a>
<span id="l30">import java.security.SecureRandom;</span><a href="#l30"></a>
<span id="l31">import java.util.Arrays;</span><a href="#l31"></a>
<span id="l32">import static sun.security.ssl.ClientHello.ClientHelloMessage;</span><a href="#l32"></a>
<span id="l33"></span><a href="#l33"></a>
<span id="l34">/**</span><a href="#l34"></a>
<span id="l35"> *  TLS handshake cookie manager</span><a href="#l35"></a>
<span id="l36"> */</span><a href="#l36"></a>
<span id="l37">abstract class HelloCookieManager {</span><a href="#l37"></a>
<span id="l38"></span><a href="#l38"></a>
<span id="l39">    static class Builder {</span><a href="#l39"></a>
<span id="l40"></span><a href="#l40"></a>
<span id="l41">        final SecureRandom secureRandom;</span><a href="#l41"></a>
<span id="l42"></span><a href="#l42"></a>
<span id="l43">        private volatile T13HelloCookieManager t13HelloCookieManager;</span><a href="#l43"></a>
<span id="l44"></span><a href="#l44"></a>
<span id="l45">        Builder(SecureRandom secureRandom) {</span><a href="#l45"></a>
<span id="l46">            this.secureRandom = secureRandom;</span><a href="#l46"></a>
<span id="l47">        }</span><a href="#l47"></a>
<span id="l48"></span><a href="#l48"></a>
<span id="l49">        HelloCookieManager valueOf(ProtocolVersion protocolVersion) {</span><a href="#l49"></a>
<span id="l50">            if (protocolVersion.useTLS13PlusSpec()) {</span><a href="#l50"></a>
<span id="l51">                if (t13HelloCookieManager != null) {</span><a href="#l51"></a>
<span id="l52">                    return t13HelloCookieManager;</span><a href="#l52"></a>
<span id="l53">                }</span><a href="#l53"></a>
<span id="l54"></span><a href="#l54"></a>
<span id="l55">                synchronized (this) {</span><a href="#l55"></a>
<span id="l56">                    if (t13HelloCookieManager == null) {</span><a href="#l56"></a>
<span id="l57">                        t13HelloCookieManager =</span><a href="#l57"></a>
<span id="l58">                                new T13HelloCookieManager(secureRandom);</span><a href="#l58"></a>
<span id="l59">                    }</span><a href="#l59"></a>
<span id="l60">                }</span><a href="#l60"></a>
<span id="l61"></span><a href="#l61"></a>
<span id="l62">                return t13HelloCookieManager;</span><a href="#l62"></a>
<span id="l63">            }</span><a href="#l63"></a>
<span id="l64"></span><a href="#l64"></a>
<span id="l65">            return null;</span><a href="#l65"></a>
<span id="l66">        }</span><a href="#l66"></a>
<span id="l67">    }</span><a href="#l67"></a>
<span id="l68"></span><a href="#l68"></a>
<span id="l69">    abstract byte[] createCookie(ServerHandshakeContext context,</span><a href="#l69"></a>
<span id="l70">                ClientHelloMessage clientHello) throws IOException;</span><a href="#l70"></a>
<span id="l71"></span><a href="#l71"></a>
<span id="l72">    abstract boolean isCookieValid(ServerHandshakeContext context,</span><a href="#l72"></a>
<span id="l73">            ClientHelloMessage clientHello, byte[] cookie) throws IOException;</span><a href="#l73"></a>
<span id="l74"></span><a href="#l74"></a>
<span id="l75">    private static final</span><a href="#l75"></a>
<span id="l76">            class T13HelloCookieManager extends HelloCookieManager {</span><a href="#l76"></a>
<span id="l77"></span><a href="#l77"></a>
<span id="l78">        final SecureRandom secureRandom;</span><a href="#l78"></a>
<span id="l79">        private int             cookieVersion;      // version + sequence</span><a href="#l79"></a>
<span id="l80">        private final byte[]    cookieSecret;</span><a href="#l80"></a>
<span id="l81">        private final byte[]    legacySecret;</span><a href="#l81"></a>
<span id="l82"></span><a href="#l82"></a>
<span id="l83">        T13HelloCookieManager(SecureRandom secureRandom) {</span><a href="#l83"></a>
<span id="l84">            this.secureRandom = secureRandom;</span><a href="#l84"></a>
<span id="l85">            this.cookieVersion = secureRandom.nextInt();</span><a href="#l85"></a>
<span id="l86">            this.cookieSecret = new byte[64];</span><a href="#l86"></a>
<span id="l87">            this.legacySecret = new byte[64];</span><a href="#l87"></a>
<span id="l88"></span><a href="#l88"></a>
<span id="l89">            secureRandom.nextBytes(cookieSecret);</span><a href="#l89"></a>
<span id="l90">            System.arraycopy(cookieSecret, 0, legacySecret, 0, 64);</span><a href="#l90"></a>
<span id="l91">        }</span><a href="#l91"></a>
<span id="l92"></span><a href="#l92"></a>
<span id="l93">        @Override</span><a href="#l93"></a>
<span id="l94">        byte[] createCookie(ServerHandshakeContext context,</span><a href="#l94"></a>
<span id="l95">                ClientHelloMessage clientHello) throws IOException {</span><a href="#l95"></a>
<span id="l96">            int version;</span><a href="#l96"></a>
<span id="l97">            byte[] secret;</span><a href="#l97"></a>
<span id="l98"></span><a href="#l98"></a>
<span id="l99">            synchronized (this) {</span><a href="#l99"></a>
<span id="l100">                version = cookieVersion;</span><a href="#l100"></a>
<span id="l101">                secret = cookieSecret;</span><a href="#l101"></a>
<span id="l102"></span><a href="#l102"></a>
<span id="l103">                // the cookie secret usage limit is 2^24</span><a href="#l103"></a>
<span id="l104">                if ((cookieVersion &amp; 0xFFFFFF) == 0) {  // reset the secret</span><a href="#l104"></a>
<span id="l105">                    System.arraycopy(cookieSecret, 0, legacySecret, 0, 64);</span><a href="#l105"></a>
<span id="l106">                    secureRandom.nextBytes(cookieSecret);</span><a href="#l106"></a>
<span id="l107">                }</span><a href="#l107"></a>
<span id="l108"></span><a href="#l108"></a>
<span id="l109">                cookieVersion++;        // allow wrapped version number</span><a href="#l109"></a>
<span id="l110">            }</span><a href="#l110"></a>
<span id="l111"></span><a href="#l111"></a>
<span id="l112">            MessageDigest md = JsseJce.getMessageDigest(</span><a href="#l112"></a>
<span id="l113">                    context.negotiatedCipherSuite.hashAlg.name);</span><a href="#l113"></a>
<span id="l114">            byte[] headerBytes = clientHello.getHeaderBytes();</span><a href="#l114"></a>
<span id="l115">            md.update(headerBytes);</span><a href="#l115"></a>
<span id="l116">            byte[] headerCookie = md.digest(secret);</span><a href="#l116"></a>
<span id="l117"></span><a href="#l117"></a>
<span id="l118">            // hash of ClientHello handshake message</span><a href="#l118"></a>
<span id="l119">            context.handshakeHash.update();</span><a href="#l119"></a>
<span id="l120">            byte[] clientHelloHash = context.handshakeHash.digest();</span><a href="#l120"></a>
<span id="l121"></span><a href="#l121"></a>
<span id="l122">            // version and cipher suite</span><a href="#l122"></a>
<span id="l123">            //</span><a href="#l123"></a>
<span id="l124">            // Store the negotiated cipher suite in the cookie as well.</span><a href="#l124"></a>
<span id="l125">            // cookie[0]/[1]: cipher suite</span><a href="#l125"></a>
<span id="l126">            // cookie[2]: cookie version</span><a href="#l126"></a>
<span id="l127">            // + (hash length): Mac(ClientHello header)</span><a href="#l127"></a>
<span id="l128">            // + (hash length): Hash(ClientHello)</span><a href="#l128"></a>
<span id="l129">            byte[] prefix = new byte[] {</span><a href="#l129"></a>
<span id="l130">                    (byte)((context.negotiatedCipherSuite.id &gt;&gt; 8) &amp; 0xFF),</span><a href="#l130"></a>
<span id="l131">                    (byte)(context.negotiatedCipherSuite.id &amp; 0xFF),</span><a href="#l131"></a>
<span id="l132">                    (byte)((version &gt;&gt; 24) &amp; 0xFF)</span><a href="#l132"></a>
<span id="l133">                };</span><a href="#l133"></a>
<span id="l134"></span><a href="#l134"></a>
<span id="l135">            byte[] cookie = Arrays.copyOf(prefix,</span><a href="#l135"></a>
<span id="l136">                prefix.length + headerCookie.length + clientHelloHash.length);</span><a href="#l136"></a>
<span id="l137">            System.arraycopy(headerCookie, 0, cookie,</span><a href="#l137"></a>
<span id="l138">                prefix.length, headerCookie.length);</span><a href="#l138"></a>
<span id="l139">            System.arraycopy(clientHelloHash, 0, cookie,</span><a href="#l139"></a>
<span id="l140">                prefix.length + headerCookie.length, clientHelloHash.length);</span><a href="#l140"></a>
<span id="l141"></span><a href="#l141"></a>
<span id="l142">            return cookie;</span><a href="#l142"></a>
<span id="l143">        }</span><a href="#l143"></a>
<span id="l144"></span><a href="#l144"></a>
<span id="l145">        @Override</span><a href="#l145"></a>
<span id="l146">        boolean isCookieValid(ServerHandshakeContext context,</span><a href="#l146"></a>
<span id="l147">            ClientHelloMessage clientHello, byte[] cookie) throws IOException {</span><a href="#l147"></a>
<span id="l148">            // no cookie exchange or not a valid cookie length</span><a href="#l148"></a>
<span id="l149">            if ((cookie == null) || (cookie.length &lt;= 32)) {    // 32: roughly</span><a href="#l149"></a>
<span id="l150">                return false;</span><a href="#l150"></a>
<span id="l151">            }</span><a href="#l151"></a>
<span id="l152"></span><a href="#l152"></a>
<span id="l153">            int csId = ((cookie[0] &amp; 0xFF) &lt;&lt; 8) | (cookie[1] &amp; 0xFF);</span><a href="#l153"></a>
<span id="l154">            CipherSuite cs = CipherSuite.valueOf(csId);</span><a href="#l154"></a>
<span id="l155">            if (cs == null || cs.hashAlg == null || cs.hashAlg.hashLength == 0) {</span><a href="#l155"></a>
<span id="l156">                return false;</span><a href="#l156"></a>
<span id="l157">            }</span><a href="#l157"></a>
<span id="l158"></span><a href="#l158"></a>
<span id="l159">            int hashLen = cs.hashAlg.hashLength;</span><a href="#l159"></a>
<span id="l160">            if (cookie.length != (3 + hashLen * 2)) {</span><a href="#l160"></a>
<span id="l161">                return false;</span><a href="#l161"></a>
<span id="l162">            }</span><a href="#l162"></a>
<span id="l163"></span><a href="#l163"></a>
<span id="l164">            byte[] prevHeadCookie =</span><a href="#l164"></a>
<span id="l165">                    Arrays.copyOfRange(cookie, 3, 3 + hashLen);</span><a href="#l165"></a>
<span id="l166">            byte[] prevClientHelloHash =</span><a href="#l166"></a>
<span id="l167">                    Arrays.copyOfRange(cookie, 3 + hashLen, cookie.length);</span><a href="#l167"></a>
<span id="l168"></span><a href="#l168"></a>
<span id="l169">            byte[] secret;</span><a href="#l169"></a>
<span id="l170">            synchronized (this) {</span><a href="#l170"></a>
<span id="l171">                if ((byte)((cookieVersion &gt;&gt; 24) &amp; 0xFF) == cookie[2]) {</span><a href="#l171"></a>
<span id="l172">                    secret = cookieSecret;</span><a href="#l172"></a>
<span id="l173">                } else {</span><a href="#l173"></a>
<span id="l174">                    secret = legacySecret;  // including out of window cookies</span><a href="#l174"></a>
<span id="l175">                }</span><a href="#l175"></a>
<span id="l176">            }</span><a href="#l176"></a>
<span id="l177"></span><a href="#l177"></a>
<span id="l178">            MessageDigest md = JsseJce.getMessageDigest(cs.hashAlg.name);</span><a href="#l178"></a>
<span id="l179">            byte[] headerBytes = clientHello.getHeaderBytes();</span><a href="#l179"></a>
<span id="l180">            md.update(headerBytes);</span><a href="#l180"></a>
<span id="l181">            byte[] headerCookie = md.digest(secret);</span><a href="#l181"></a>
<span id="l182"></span><a href="#l182"></a>
<span id="l183">            if (!MessageDigest.isEqual(headerCookie, prevHeadCookie)) {</span><a href="#l183"></a>
<span id="l184">                return false;</span><a href="#l184"></a>
<span id="l185">            }</span><a href="#l185"></a>
<span id="l186"></span><a href="#l186"></a>
<span id="l187">            // Use the ClientHello hash in the cookie for transtript</span><a href="#l187"></a>
<span id="l188">            // hash calculation for stateless HelloRetryRequest.</span><a href="#l188"></a>
<span id="l189">            //</span><a href="#l189"></a>
<span id="l190">            // Transcript-Hash(ClientHello1, HelloRetryRequest, ... Mn) =</span><a href="#l190"></a>
<span id="l191">            //   Hash(message_hash ||    /* Handshake type */</span><a href="#l191"></a>
<span id="l192">            //     00 00 Hash.length ||  /* Handshake message length (bytes) */</span><a href="#l192"></a>
<span id="l193">            //     Hash(ClientHello1) || /* Hash of ClientHello1 */</span><a href="#l193"></a>
<span id="l194">            //     HelloRetryRequest || ... || Mn)</span><a href="#l194"></a>
<span id="l195"></span><a href="#l195"></a>
<span id="l196">            // Reproduce HelloRetryRequest handshake message</span><a href="#l196"></a>
<span id="l197">            byte[] hrrMessage =</span><a href="#l197"></a>
<span id="l198">                    ServerHello.hrrReproducer.produce(context, clientHello);</span><a href="#l198"></a>
<span id="l199">            context.handshakeHash.push(hrrMessage);</span><a href="#l199"></a>
<span id="l200"></span><a href="#l200"></a>
<span id="l201">            // Construct the 1st ClientHello message for transcript hash</span><a href="#l201"></a>
<span id="l202">            byte[] hashedClientHello = new byte[4 + hashLen];</span><a href="#l202"></a>
<span id="l203">            hashedClientHello[0] = SSLHandshake.MESSAGE_HASH.id;</span><a href="#l203"></a>
<span id="l204">            hashedClientHello[1] = (byte)0x00;</span><a href="#l204"></a>
<span id="l205">            hashedClientHello[2] = (byte)0x00;</span><a href="#l205"></a>
<span id="l206">            hashedClientHello[3] = (byte)(hashLen &amp; 0xFF);</span><a href="#l206"></a>
<span id="l207">            System.arraycopy(prevClientHelloHash, 0,</span><a href="#l207"></a>
<span id="l208">                    hashedClientHello, 4, hashLen);</span><a href="#l208"></a>
<span id="l209"></span><a href="#l209"></a>
<span id="l210">            context.handshakeHash.push(hashedClientHello);</span><a href="#l210"></a>
<span id="l211"></span><a href="#l211"></a>
<span id="l212">            return true;</span><a href="#l212"></a>
<span id="l213">        }</span><a href="#l213"></a>
<span id="l214">    }</span><a href="#l214"></a>
<span id="l215">}</span><a href="#l215"></a></pre>
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

