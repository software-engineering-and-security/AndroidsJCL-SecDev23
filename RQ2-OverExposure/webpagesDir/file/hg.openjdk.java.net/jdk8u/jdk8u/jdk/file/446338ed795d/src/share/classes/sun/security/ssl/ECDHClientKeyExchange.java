<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 446338ed795d src/share/classes/sun/security/ssl/ECDHClientKeyExchange.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk8u/jdk8u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/shortlog/446338ed795d">log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/graph/446338ed795d">graph</a></li>
<li><a href="/jdk8u/jdk8u/jdk/tags">tags</a></li>
<li><a href="/jdk8u/jdk8u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/rev/446338ed795d">changeset</a></li>
<li><a href="/jdk8u/jdk8u/jdk/file/446338ed795d/src/share/classes/sun/security/ssl/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/ssl/ECDHClientKeyExchange.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/446338ed795d/src/share/classes/sun/security/ssl/ECDHClientKeyExchange.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/446338ed795d/src/share/classes/sun/security/ssl/ECDHClientKeyExchange.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/446338ed795d/src/share/classes/sun/security/ssl/ECDHClientKeyExchange.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/446338ed795d/src/share/classes/sun/security/ssl/ECDHClientKeyExchange.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/446338ed795d/src/share/classes/sun/security/ssl/ECDHClientKeyExchange.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/ssl/ECDHClientKeyExchange.java @ 14569:446338ed795d</h3>

<form class="search" action="/jdk8u/jdk8u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk8u/jdk8u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8267729: Improve TLS client handshaking
Reviewed-by: andrew</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#109;&#98;&#97;&#108;&#97;&#111;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Thu, 16 Sep 2021 14:49:37 +0000</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/4546aa3faf37/src/share/classes/sun/security/ssl/ECDHClientKeyExchange.java">4546aa3faf37</a> </td>
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
<span id="l2"> * Copyright (c) 2003, 2018, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l29">import java.nio.ByteBuffer;</span><a href="#l29"></a>
<span id="l30">import java.security.AlgorithmConstraints;</span><a href="#l30"></a>
<span id="l31">import java.security.CryptoPrimitive;</span><a href="#l31"></a>
<span id="l32">import java.security.GeneralSecurityException;</span><a href="#l32"></a>
<span id="l33">import java.security.KeyFactory;</span><a href="#l33"></a>
<span id="l34">import java.security.PublicKey;</span><a href="#l34"></a>
<span id="l35">import java.security.interfaces.ECPublicKey;</span><a href="#l35"></a>
<span id="l36">import java.security.spec.ECParameterSpec;</span><a href="#l36"></a>
<span id="l37">import java.security.spec.ECPoint;</span><a href="#l37"></a>
<span id="l38">import java.security.spec.ECPublicKeySpec;</span><a href="#l38"></a>
<span id="l39">import java.text.MessageFormat;</span><a href="#l39"></a>
<span id="l40">import java.util.EnumSet;</span><a href="#l40"></a>
<span id="l41">import java.util.Locale;</span><a href="#l41"></a>
<span id="l42">import javax.crypto.SecretKey;</span><a href="#l42"></a>
<span id="l43">import javax.net.ssl.SSLHandshakeException;</span><a href="#l43"></a>
<span id="l44">import sun.security.ssl.ECDHKeyExchange.ECDHECredentials;</span><a href="#l44"></a>
<span id="l45">import sun.security.ssl.ECDHKeyExchange.ECDHEPossession;</span><a href="#l45"></a>
<span id="l46">import sun.security.ssl.SSLHandshake.HandshakeMessage;</span><a href="#l46"></a>
<span id="l47">import sun.security.ssl.SupportedGroupsExtension.NamedGroup;</span><a href="#l47"></a>
<span id="l48">import sun.security.ssl.X509Authentication.X509Credentials;</span><a href="#l48"></a>
<span id="l49">import sun.security.ssl.X509Authentication.X509Possession;</span><a href="#l49"></a>
<span id="l50">import sun.misc.HexDumpEncoder;</span><a href="#l50"></a>
<span id="l51"></span><a href="#l51"></a>
<span id="l52">/**</span><a href="#l52"></a>
<span id="l53"> * Pack of the &quot;ClientKeyExchange&quot; handshake message.</span><a href="#l53"></a>
<span id="l54"> */</span><a href="#l54"></a>
<span id="l55">final class ECDHClientKeyExchange {</span><a href="#l55"></a>
<span id="l56">    static final SSLConsumer ecdhHandshakeConsumer =</span><a href="#l56"></a>
<span id="l57">            new ECDHClientKeyExchangeConsumer();</span><a href="#l57"></a>
<span id="l58">    static final HandshakeProducer ecdhHandshakeProducer =</span><a href="#l58"></a>
<span id="l59">            new ECDHClientKeyExchangeProducer();</span><a href="#l59"></a>
<span id="l60"></span><a href="#l60"></a>
<span id="l61">    static final SSLConsumer ecdheHandshakeConsumer =</span><a href="#l61"></a>
<span id="l62">            new ECDHEClientKeyExchangeConsumer();</span><a href="#l62"></a>
<span id="l63">    static final HandshakeProducer ecdheHandshakeProducer =</span><a href="#l63"></a>
<span id="l64">            new ECDHEClientKeyExchangeProducer();</span><a href="#l64"></a>
<span id="l65"></span><a href="#l65"></a>
<span id="l66">    /**</span><a href="#l66"></a>
<span id="l67">     * The ECDH/ECDHE ClientKeyExchange handshake message.</span><a href="#l67"></a>
<span id="l68">     */</span><a href="#l68"></a>
<span id="l69">    private static final</span><a href="#l69"></a>
<span id="l70">            class ECDHClientKeyExchangeMessage extends HandshakeMessage {</span><a href="#l70"></a>
<span id="l71">        private final byte[] encodedPoint;</span><a href="#l71"></a>
<span id="l72"></span><a href="#l72"></a>
<span id="l73">        ECDHClientKeyExchangeMessage(HandshakeContext handshakeContext,</span><a href="#l73"></a>
<span id="l74">                ECPublicKey publicKey) {</span><a href="#l74"></a>
<span id="l75">            super(handshakeContext);</span><a href="#l75"></a>
<span id="l76"></span><a href="#l76"></a>
<span id="l77">            ECPoint point = publicKey.getW();</span><a href="#l77"></a>
<span id="l78">            ECParameterSpec params = publicKey.getParams();</span><a href="#l78"></a>
<span id="l79">            encodedPoint = JsseJce.encodePoint(point, params.getCurve());</span><a href="#l79"></a>
<span id="l80">        }</span><a href="#l80"></a>
<span id="l81"></span><a href="#l81"></a>
<span id="l82">        ECDHClientKeyExchangeMessage(HandshakeContext handshakeContext,</span><a href="#l82"></a>
<span id="l83">                ByteBuffer m) throws IOException {</span><a href="#l83"></a>
<span id="l84">            super(handshakeContext);</span><a href="#l84"></a>
<span id="l85">            if (m.remaining() != 0) {       // explicit PublicValueEncoding</span><a href="#l85"></a>
<span id="l86">                this.encodedPoint = Record.getBytes8(m);</span><a href="#l86"></a>
<span id="l87">            } else {</span><a href="#l87"></a>
<span id="l88">                this.encodedPoint = new byte[0];</span><a href="#l88"></a>
<span id="l89">            }</span><a href="#l89"></a>
<span id="l90">        }</span><a href="#l90"></a>
<span id="l91"></span><a href="#l91"></a>
<span id="l92">        // Check constraints of the specified EC public key.</span><a href="#l92"></a>
<span id="l93">        static void checkConstraints(AlgorithmConstraints constraints,</span><a href="#l93"></a>
<span id="l94">                ECPublicKey publicKey,</span><a href="#l94"></a>
<span id="l95">                byte[] encodedPoint) throws SSLHandshakeException {</span><a href="#l95"></a>
<span id="l96"></span><a href="#l96"></a>
<span id="l97">            try {</span><a href="#l97"></a>
<span id="l98">                ECParameterSpec params = publicKey.getParams();</span><a href="#l98"></a>
<span id="l99">                ECPoint point =</span><a href="#l99"></a>
<span id="l100">                        JsseJce.decodePoint(encodedPoint, params.getCurve());</span><a href="#l100"></a>
<span id="l101">                ECPublicKeySpec spec = new ECPublicKeySpec(point, params);</span><a href="#l101"></a>
<span id="l102"></span><a href="#l102"></a>
<span id="l103">                KeyFactory kf = JsseJce.getKeyFactory(&quot;EC&quot;);</span><a href="#l103"></a>
<span id="l104">                ECPublicKey peerPublicKey =</span><a href="#l104"></a>
<span id="l105">                        (ECPublicKey)kf.generatePublic(spec);</span><a href="#l105"></a>
<span id="l106"></span><a href="#l106"></a>
<span id="l107">                // check constraints of ECPublicKey</span><a href="#l107"></a>
<span id="l108">                if (constraints != null &amp;&amp;</span><a href="#l108"></a>
<span id="l109">                        !constraints.permits(</span><a href="#l109"></a>
<span id="l110">                                EnumSet.of(CryptoPrimitive.KEY_AGREEMENT),</span><a href="#l110"></a>
<span id="l111">                                peerPublicKey)) {</span><a href="#l111"></a>
<span id="l112">                    throw new SSLHandshakeException(</span><a href="#l112"></a>
<span id="l113">                        &quot;ECPublicKey does not comply to algorithm constraints&quot;);</span><a href="#l113"></a>
<span id="l114">                }</span><a href="#l114"></a>
<span id="l115">            } catch (GeneralSecurityException | java.io.IOException e) {</span><a href="#l115"></a>
<span id="l116">                throw (SSLHandshakeException) new SSLHandshakeException(</span><a href="#l116"></a>
<span id="l117">                        &quot;Could not generate ECPublicKey&quot;).initCause(e);</span><a href="#l117"></a>
<span id="l118">            }</span><a href="#l118"></a>
<span id="l119">        }</span><a href="#l119"></a>
<span id="l120"></span><a href="#l120"></a>
<span id="l121">        @Override</span><a href="#l121"></a>
<span id="l122">        public SSLHandshake handshakeType() {</span><a href="#l122"></a>
<span id="l123">            return SSLHandshake.CLIENT_KEY_EXCHANGE;</span><a href="#l123"></a>
<span id="l124">        }</span><a href="#l124"></a>
<span id="l125"></span><a href="#l125"></a>
<span id="l126">        @Override</span><a href="#l126"></a>
<span id="l127">        public int messageLength() {</span><a href="#l127"></a>
<span id="l128">            if (encodedPoint == null || encodedPoint.length == 0) {</span><a href="#l128"></a>
<span id="l129">                return 0;</span><a href="#l129"></a>
<span id="l130">            } else {</span><a href="#l130"></a>
<span id="l131">                return 1 + encodedPoint.length;</span><a href="#l131"></a>
<span id="l132">            }</span><a href="#l132"></a>
<span id="l133">        }</span><a href="#l133"></a>
<span id="l134"></span><a href="#l134"></a>
<span id="l135">        @Override</span><a href="#l135"></a>
<span id="l136">        public void send(HandshakeOutStream hos) throws IOException {</span><a href="#l136"></a>
<span id="l137">            if (encodedPoint != null &amp;&amp; encodedPoint.length != 0) {</span><a href="#l137"></a>
<span id="l138">                hos.putBytes8(encodedPoint);</span><a href="#l138"></a>
<span id="l139">            }</span><a href="#l139"></a>
<span id="l140">        }</span><a href="#l140"></a>
<span id="l141"></span><a href="#l141"></a>
<span id="l142">        @Override</span><a href="#l142"></a>
<span id="l143">        public String toString() {</span><a href="#l143"></a>
<span id="l144">            MessageFormat messageFormat = new MessageFormat(</span><a href="#l144"></a>
<span id="l145">                &quot;\&quot;ECDH ClientKeyExchange\&quot;: '{'\n&quot; +</span><a href="#l145"></a>
<span id="l146">                &quot;  \&quot;ecdh public\&quot;: '{'\n&quot; +</span><a href="#l146"></a>
<span id="l147">                &quot;{0}\n&quot; +</span><a href="#l147"></a>
<span id="l148">                &quot;  '}',\n&quot; +</span><a href="#l148"></a>
<span id="l149">                &quot;'}'&quot;,</span><a href="#l149"></a>
<span id="l150">                Locale.ENGLISH);</span><a href="#l150"></a>
<span id="l151">            if (encodedPoint == null || encodedPoint.length == 0) {</span><a href="#l151"></a>
<span id="l152">                Object[] messageFields = {</span><a href="#l152"></a>
<span id="l153">                    &quot;    &lt;implicit&gt;&quot;</span><a href="#l153"></a>
<span id="l154">                };</span><a href="#l154"></a>
<span id="l155">                return messageFormat.format(messageFields);</span><a href="#l155"></a>
<span id="l156">            } else {</span><a href="#l156"></a>
<span id="l157">                HexDumpEncoder hexEncoder = new HexDumpEncoder();</span><a href="#l157"></a>
<span id="l158">                Object[] messageFields = {</span><a href="#l158"></a>
<span id="l159">                    Utilities.indent(</span><a href="#l159"></a>
<span id="l160">                            hexEncoder.encodeBuffer(encodedPoint), &quot;    &quot;),</span><a href="#l160"></a>
<span id="l161">                };</span><a href="#l161"></a>
<span id="l162">                return messageFormat.format(messageFields);</span><a href="#l162"></a>
<span id="l163">            }</span><a href="#l163"></a>
<span id="l164">        }</span><a href="#l164"></a>
<span id="l165">    }</span><a href="#l165"></a>
<span id="l166"></span><a href="#l166"></a>
<span id="l167">    /**</span><a href="#l167"></a>
<span id="l168">     * The ECDH &quot;ClientKeyExchange&quot; handshake message producer.</span><a href="#l168"></a>
<span id="l169">     */</span><a href="#l169"></a>
<span id="l170">    private static final</span><a href="#l170"></a>
<span id="l171">            class ECDHClientKeyExchangeProducer implements HandshakeProducer {</span><a href="#l171"></a>
<span id="l172">        // Prevent instantiation of this class.</span><a href="#l172"></a>
<span id="l173">        private ECDHClientKeyExchangeProducer() {</span><a href="#l173"></a>
<span id="l174">            // blank</span><a href="#l174"></a>
<span id="l175">        }</span><a href="#l175"></a>
<span id="l176"></span><a href="#l176"></a>
<span id="l177">        @Override</span><a href="#l177"></a>
<span id="l178">        public byte[] produce(ConnectionContext context,</span><a href="#l178"></a>
<span id="l179">                HandshakeMessage message) throws IOException {</span><a href="#l179"></a>
<span id="l180">            // The producing happens in client side only.</span><a href="#l180"></a>
<span id="l181">            ClientHandshakeContext chc = (ClientHandshakeContext)context;</span><a href="#l181"></a>
<span id="l182"></span><a href="#l182"></a>
<span id="l183">            X509Credentials x509Credentials = null;</span><a href="#l183"></a>
<span id="l184">            for (SSLCredentials credential : chc.handshakeCredentials) {</span><a href="#l184"></a>
<span id="l185">                if (credential instanceof X509Credentials) {</span><a href="#l185"></a>
<span id="l186">                    x509Credentials = (X509Credentials)credential;</span><a href="#l186"></a>
<span id="l187">                    break;</span><a href="#l187"></a>
<span id="l188">                }</span><a href="#l188"></a>
<span id="l189">            }</span><a href="#l189"></a>
<span id="l190"></span><a href="#l190"></a>
<span id="l191">            if (x509Credentials == null) {</span><a href="#l191"></a>
<span id="l192">                throw chc.conContext.fatal(Alert.INTERNAL_ERROR,</span><a href="#l192"></a>
<span id="l193">                    &quot;No server certificate for ECDH client key exchange&quot;);</span><a href="#l193"></a>
<span id="l194">            }</span><a href="#l194"></a>
<span id="l195"></span><a href="#l195"></a>
<span id="l196">            PublicKey publicKey = x509Credentials.popPublicKey;</span><a href="#l196"></a>
<span id="l197">            if (!publicKey.getAlgorithm().equals(&quot;EC&quot;)) {</span><a href="#l197"></a>
<span id="l198">                throw chc.conContext.fatal(Alert.ILLEGAL_PARAMETER,</span><a href="#l198"></a>
<span id="l199">                    &quot;Not EC server certificate for ECDH client key exchange&quot;);</span><a href="#l199"></a>
<span id="l200">            }</span><a href="#l200"></a>
<span id="l201"></span><a href="#l201"></a>
<span id="l202">            ECParameterSpec params = ((ECPublicKey)publicKey).getParams();</span><a href="#l202"></a>
<span id="l203">            NamedGroup namedGroup = NamedGroup.valueOf(params);</span><a href="#l203"></a>
<span id="l204">            if (namedGroup == null) {</span><a href="#l204"></a>
<span id="l205">                throw chc.conContext.fatal(Alert.ILLEGAL_PARAMETER,</span><a href="#l205"></a>
<span id="l206">                    &quot;Unsupported EC server cert for ECDH client key exchange&quot;);</span><a href="#l206"></a>
<span id="l207">            }</span><a href="#l207"></a>
<span id="l208"></span><a href="#l208"></a>
<span id="l209">            ECDHEPossession ecdhePossession = new ECDHEPossession(</span><a href="#l209"></a>
<span id="l210">                    namedGroup, chc.sslContext.getSecureRandom());</span><a href="#l210"></a>
<span id="l211">            chc.handshakePossessions.add(ecdhePossession);</span><a href="#l211"></a>
<span id="l212">            ECDHClientKeyExchangeMessage cke =</span><a href="#l212"></a>
<span id="l213">                    new ECDHClientKeyExchangeMessage(</span><a href="#l213"></a>
<span id="l214">                            chc, ecdhePossession.publicKey);</span><a href="#l214"></a>
<span id="l215">            if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l215"></a>
<span id="l216">                SSLLogger.fine(</span><a href="#l216"></a>
<span id="l217">                    &quot;Produced ECDH ClientKeyExchange handshake message&quot;, cke);</span><a href="#l217"></a>
<span id="l218">            }</span><a href="#l218"></a>
<span id="l219"></span><a href="#l219"></a>
<span id="l220">            // Output the handshake message.</span><a href="#l220"></a>
<span id="l221">            cke.write(chc.handshakeOutput);</span><a href="#l221"></a>
<span id="l222">            chc.handshakeOutput.flush();</span><a href="#l222"></a>
<span id="l223"></span><a href="#l223"></a>
<span id="l224">            // update the states</span><a href="#l224"></a>
<span id="l225">            SSLKeyExchange ke = SSLKeyExchange.valueOf(</span><a href="#l225"></a>
<span id="l226">                    chc.negotiatedCipherSuite.keyExchange,</span><a href="#l226"></a>
<span id="l227">                    chc.negotiatedProtocol);</span><a href="#l227"></a>
<span id="l228">            if (ke == null) {</span><a href="#l228"></a>
<span id="l229">                // unlikely</span><a href="#l229"></a>
<span id="l230">                throw chc.conContext.fatal(Alert.INTERNAL_ERROR,</span><a href="#l230"></a>
<span id="l231">                        &quot;Not supported key exchange type&quot;);</span><a href="#l231"></a>
<span id="l232">            } else {</span><a href="#l232"></a>
<span id="l233">                SSLKeyDerivation masterKD = ke.createKeyDerivation(chc);</span><a href="#l233"></a>
<span id="l234">                SecretKey masterSecret =</span><a href="#l234"></a>
<span id="l235">                        masterKD.deriveKey(&quot;MasterSecret&quot;, null);</span><a href="#l235"></a>
<span id="l236">                chc.handshakeSession.setMasterSecret(masterSecret);</span><a href="#l236"></a>
<span id="l237"></span><a href="#l237"></a>
<span id="l238">                SSLTrafficKeyDerivation kd =</span><a href="#l238"></a>
<span id="l239">                        SSLTrafficKeyDerivation.valueOf(chc.negotiatedProtocol);</span><a href="#l239"></a>
<span id="l240">                if (kd == null) {</span><a href="#l240"></a>
<span id="l241">                    // unlikely</span><a href="#l241"></a>
<span id="l242">                    throw chc.conContext.fatal(Alert.INTERNAL_ERROR,</span><a href="#l242"></a>
<span id="l243">                            &quot;Not supported key derivation: &quot; +</span><a href="#l243"></a>
<span id="l244">                            chc.negotiatedProtocol);</span><a href="#l244"></a>
<span id="l245">                } else {</span><a href="#l245"></a>
<span id="l246">                    chc.handshakeKeyDerivation =</span><a href="#l246"></a>
<span id="l247">                        kd.createKeyDerivation(chc, masterSecret);</span><a href="#l247"></a>
<span id="l248">                }</span><a href="#l248"></a>
<span id="l249">            }</span><a href="#l249"></a>
<span id="l250"></span><a href="#l250"></a>
<span id="l251">            // The handshake message has been delivered.</span><a href="#l251"></a>
<span id="l252">            return null;</span><a href="#l252"></a>
<span id="l253">        }</span><a href="#l253"></a>
<span id="l254">    }</span><a href="#l254"></a>
<span id="l255"></span><a href="#l255"></a>
<span id="l256">    /**</span><a href="#l256"></a>
<span id="l257">     * The ECDH &quot;ClientKeyExchange&quot; handshake message consumer.</span><a href="#l257"></a>
<span id="l258">     */</span><a href="#l258"></a>
<span id="l259">    private static final</span><a href="#l259"></a>
<span id="l260">            class ECDHClientKeyExchangeConsumer implements SSLConsumer {</span><a href="#l260"></a>
<span id="l261">        // Prevent instantiation of this class.</span><a href="#l261"></a>
<span id="l262">        private ECDHClientKeyExchangeConsumer() {</span><a href="#l262"></a>
<span id="l263">            // blank</span><a href="#l263"></a>
<span id="l264">        }</span><a href="#l264"></a>
<span id="l265"></span><a href="#l265"></a>
<span id="l266">        @Override</span><a href="#l266"></a>
<span id="l267">        public void consume(ConnectionContext context,</span><a href="#l267"></a>
<span id="l268">                ByteBuffer message) throws IOException {</span><a href="#l268"></a>
<span id="l269">            // The consuming happens in server side only.</span><a href="#l269"></a>
<span id="l270">            ServerHandshakeContext shc = (ServerHandshakeContext)context;</span><a href="#l270"></a>
<span id="l271"></span><a href="#l271"></a>
<span id="l272">            X509Possession x509Possession = null;</span><a href="#l272"></a>
<span id="l273">            for (SSLPossession possession : shc.handshakePossessions) {</span><a href="#l273"></a>
<span id="l274">                if (possession instanceof X509Possession) {</span><a href="#l274"></a>
<span id="l275">                    x509Possession = (X509Possession)possession;</span><a href="#l275"></a>
<span id="l276">                    break;</span><a href="#l276"></a>
<span id="l277">                }</span><a href="#l277"></a>
<span id="l278">            }</span><a href="#l278"></a>
<span id="l279"></span><a href="#l279"></a>
<span id="l280">            if (x509Possession == null) {</span><a href="#l280"></a>
<span id="l281">                // unlikely, have been checked during cipher suite negotiation.</span><a href="#l281"></a>
<span id="l282">                throw shc.conContext.fatal(Alert.INTERNAL_ERROR,</span><a href="#l282"></a>
<span id="l283">                    &quot;No expected EC server cert for ECDH client key exchange&quot;);</span><a href="#l283"></a>
<span id="l284">            }</span><a href="#l284"></a>
<span id="l285"></span><a href="#l285"></a>
<span id="l286">            ECParameterSpec params = x509Possession.getECParameterSpec();</span><a href="#l286"></a>
<span id="l287">            if (params == null) {</span><a href="#l287"></a>
<span id="l288">                // unlikely, have been checked during cipher suite negotiation.</span><a href="#l288"></a>
<span id="l289">                throw shc.conContext.fatal(Alert.ILLEGAL_PARAMETER,</span><a href="#l289"></a>
<span id="l290">                    &quot;Not EC server cert for ECDH client key exchange&quot;);</span><a href="#l290"></a>
<span id="l291">            }</span><a href="#l291"></a>
<span id="l292"></span><a href="#l292"></a>
<span id="l293">            NamedGroup namedGroup = NamedGroup.valueOf(params);</span><a href="#l293"></a>
<span id="l294">            if (namedGroup == null) {</span><a href="#l294"></a>
<span id="l295">                // unlikely, have been checked during cipher suite negotiation.</span><a href="#l295"></a>
<span id="l296">                throw shc.conContext.fatal(Alert.ILLEGAL_PARAMETER,</span><a href="#l296"></a>
<span id="l297">                    &quot;Unsupported EC server cert for ECDH client key exchange&quot;);</span><a href="#l297"></a>
<span id="l298">            }</span><a href="#l298"></a>
<span id="l299"></span><a href="#l299"></a>
<span id="l300">            SSLKeyExchange ke = SSLKeyExchange.valueOf(</span><a href="#l300"></a>
<span id="l301">                    shc.negotiatedCipherSuite.keyExchange,</span><a href="#l301"></a>
<span id="l302">                    shc.negotiatedProtocol);</span><a href="#l302"></a>
<span id="l303">            if (ke == null) {</span><a href="#l303"></a>
<span id="l304">                // unlikely</span><a href="#l304"></a>
<span id="l305">                throw shc.conContext.fatal(Alert.INTERNAL_ERROR,</span><a href="#l305"></a>
<span id="l306">                        &quot;Not supported key exchange type&quot;);</span><a href="#l306"></a>
<span id="l307">            }</span><a href="#l307"></a>
<span id="l308"></span><a href="#l308"></a>
<span id="l309">            // parse the handshake message</span><a href="#l309"></a>
<span id="l310">            ECDHClientKeyExchangeMessage cke =</span><a href="#l310"></a>
<span id="l311">                    new ECDHClientKeyExchangeMessage(shc, message);</span><a href="#l311"></a>
<span id="l312">            if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l312"></a>
<span id="l313">                SSLLogger.fine(</span><a href="#l313"></a>
<span id="l314">                    &quot;Consuming ECDH ClientKeyExchange handshake message&quot;, cke);</span><a href="#l314"></a>
<span id="l315">            }</span><a href="#l315"></a>
<span id="l316"></span><a href="#l316"></a>
<span id="l317">            // create the credentials</span><a href="#l317"></a>
<span id="l318">            try {</span><a href="#l318"></a>
<span id="l319">                ECPoint point =</span><a href="#l319"></a>
<span id="l320">                    JsseJce.decodePoint(cke.encodedPoint, params.getCurve());</span><a href="#l320"></a>
<span id="l321">                ECPublicKeySpec spec = new ECPublicKeySpec(point, params);</span><a href="#l321"></a>
<span id="l322"></span><a href="#l322"></a>
<span id="l323">                KeyFactory kf = JsseJce.getKeyFactory(&quot;EC&quot;);</span><a href="#l323"></a>
<span id="l324">                ECPublicKey peerPublicKey =</span><a href="#l324"></a>
<span id="l325">                        (ECPublicKey)kf.generatePublic(spec);</span><a href="#l325"></a>
<span id="l326"></span><a href="#l326"></a>
<span id="l327">                // check constraints of peer ECPublicKey</span><a href="#l327"></a>
<span id="l328">                if (shc.algorithmConstraints != null &amp;&amp;</span><a href="#l328"></a>
<span id="l329">                        !shc.algorithmConstraints.permits(</span><a href="#l329"></a>
<span id="l330">                                EnumSet.of(CryptoPrimitive.KEY_AGREEMENT),</span><a href="#l330"></a>
<span id="l331">                                peerPublicKey)) {</span><a href="#l331"></a>
<span id="l332">                    throw new SSLHandshakeException(</span><a href="#l332"></a>
<span id="l333">                        &quot;ECPublicKey does not comply to algorithm constraints&quot;);</span><a href="#l333"></a>
<span id="l334">                }</span><a href="#l334"></a>
<span id="l335"></span><a href="#l335"></a>
<span id="l336">                shc.handshakeCredentials.add(new ECDHECredentials(</span><a href="#l336"></a>
<span id="l337">                        peerPublicKey, namedGroup));</span><a href="#l337"></a>
<span id="l338">            } catch (GeneralSecurityException | java.io.IOException e) {</span><a href="#l338"></a>
<span id="l339">                throw (SSLHandshakeException)(new SSLHandshakeException(</span><a href="#l339"></a>
<span id="l340">                        &quot;Could not generate ECPublicKey&quot;).initCause(e));</span><a href="#l340"></a>
<span id="l341">            }</span><a href="#l341"></a>
<span id="l342"></span><a href="#l342"></a>
<span id="l343">            // update the states</span><a href="#l343"></a>
<span id="l344">            SSLKeyDerivation masterKD = ke.createKeyDerivation(shc);</span><a href="#l344"></a>
<span id="l345">            SecretKey masterSecret =</span><a href="#l345"></a>
<span id="l346">                    masterKD.deriveKey(&quot;MasterSecret&quot;, null);</span><a href="#l346"></a>
<span id="l347">            shc.handshakeSession.setMasterSecret(masterSecret);</span><a href="#l347"></a>
<span id="l348"></span><a href="#l348"></a>
<span id="l349">            SSLTrafficKeyDerivation kd =</span><a href="#l349"></a>
<span id="l350">                    SSLTrafficKeyDerivation.valueOf(shc.negotiatedProtocol);</span><a href="#l350"></a>
<span id="l351">            if (kd == null) {</span><a href="#l351"></a>
<span id="l352">                // unlikely</span><a href="#l352"></a>
<span id="l353">                throw shc.conContext.fatal(Alert.INTERNAL_ERROR,</span><a href="#l353"></a>
<span id="l354">                    &quot;Not supported key derivation: &quot; + shc.negotiatedProtocol);</span><a href="#l354"></a>
<span id="l355">            } else {</span><a href="#l355"></a>
<span id="l356">                shc.handshakeKeyDerivation =</span><a href="#l356"></a>
<span id="l357">                    kd.createKeyDerivation(shc, masterSecret);</span><a href="#l357"></a>
<span id="l358">            }</span><a href="#l358"></a>
<span id="l359">        }</span><a href="#l359"></a>
<span id="l360">    }</span><a href="#l360"></a>
<span id="l361"></span><a href="#l361"></a>
<span id="l362">    /**</span><a href="#l362"></a>
<span id="l363">     * The ECDHE &quot;ClientKeyExchange&quot; handshake message producer.</span><a href="#l363"></a>
<span id="l364">     */</span><a href="#l364"></a>
<span id="l365">    private static final</span><a href="#l365"></a>
<span id="l366">            class ECDHEClientKeyExchangeProducer implements HandshakeProducer {</span><a href="#l366"></a>
<span id="l367">        // Prevent instantiation of this class.</span><a href="#l367"></a>
<span id="l368">        private ECDHEClientKeyExchangeProducer() {</span><a href="#l368"></a>
<span id="l369">            // blank</span><a href="#l369"></a>
<span id="l370">        }</span><a href="#l370"></a>
<span id="l371"></span><a href="#l371"></a>
<span id="l372">        @Override</span><a href="#l372"></a>
<span id="l373">        public byte[] produce(ConnectionContext context,</span><a href="#l373"></a>
<span id="l374">                HandshakeMessage message) throws IOException {</span><a href="#l374"></a>
<span id="l375">            // The producing happens in client side only.</span><a href="#l375"></a>
<span id="l376">            ClientHandshakeContext chc = (ClientHandshakeContext)context;</span><a href="#l376"></a>
<span id="l377"></span><a href="#l377"></a>
<span id="l378">            ECDHECredentials ecdheCredentials = null;</span><a href="#l378"></a>
<span id="l379">            for (SSLCredentials cd : chc.handshakeCredentials) {</span><a href="#l379"></a>
<span id="l380">                if (cd instanceof ECDHECredentials) {</span><a href="#l380"></a>
<span id="l381">                    ecdheCredentials = (ECDHECredentials)cd;</span><a href="#l381"></a>
<span id="l382">                    break;</span><a href="#l382"></a>
<span id="l383">                }</span><a href="#l383"></a>
<span id="l384">            }</span><a href="#l384"></a>
<span id="l385"></span><a href="#l385"></a>
<span id="l386">            if (ecdheCredentials == null) {</span><a href="#l386"></a>
<span id="l387">                throw chc.conContext.fatal(Alert.INTERNAL_ERROR,</span><a href="#l387"></a>
<span id="l388">                    &quot;No ECDHE credentials negotiated for client key exchange&quot;);</span><a href="#l388"></a>
<span id="l389">            }</span><a href="#l389"></a>
<span id="l390"></span><a href="#l390"></a>
<span id="l391">            ECDHEPossession ecdhePossession = new ECDHEPossession(</span><a href="#l391"></a>
<span id="l392">                    ecdheCredentials, chc.sslContext.getSecureRandom());</span><a href="#l392"></a>
<span id="l393">            chc.handshakePossessions.add(ecdhePossession);</span><a href="#l393"></a>
<span id="l394">            ECDHClientKeyExchangeMessage cke =</span><a href="#l394"></a>
<span id="l395">                    new ECDHClientKeyExchangeMessage(</span><a href="#l395"></a>
<span id="l396">                            chc, ecdhePossession.publicKey);</span><a href="#l396"></a>
<span id="l397">            if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l397"></a>
<span id="l398">                SSLLogger.fine(</span><a href="#l398"></a>
<span id="l399">                    &quot;Produced ECDHE ClientKeyExchange handshake message&quot;, cke);</span><a href="#l399"></a>
<span id="l400">            }</span><a href="#l400"></a>
<span id="l401"></span><a href="#l401"></a>
<span id="l402">            // Output the handshake message.</span><a href="#l402"></a>
<span id="l403">            cke.write(chc.handshakeOutput);</span><a href="#l403"></a>
<span id="l404">            chc.handshakeOutput.flush();</span><a href="#l404"></a>
<span id="l405"></span><a href="#l405"></a>
<span id="l406">            // update the states</span><a href="#l406"></a>
<span id="l407">            SSLKeyExchange ke = SSLKeyExchange.valueOf(</span><a href="#l407"></a>
<span id="l408">                    chc.negotiatedCipherSuite.keyExchange,</span><a href="#l408"></a>
<span id="l409">                    chc.negotiatedProtocol);</span><a href="#l409"></a>
<span id="l410">            if (ke == null) {</span><a href="#l410"></a>
<span id="l411">                // unlikely</span><a href="#l411"></a>
<span id="l412">                throw chc.conContext.fatal(Alert.INTERNAL_ERROR,</span><a href="#l412"></a>
<span id="l413">                        &quot;Not supported key exchange type&quot;);</span><a href="#l413"></a>
<span id="l414">            } else {</span><a href="#l414"></a>
<span id="l415">                SSLKeyDerivation masterKD = ke.createKeyDerivation(chc);</span><a href="#l415"></a>
<span id="l416">                SecretKey masterSecret =</span><a href="#l416"></a>
<span id="l417">                        masterKD.deriveKey(&quot;MasterSecret&quot;, null);</span><a href="#l417"></a>
<span id="l418">                chc.handshakeSession.setMasterSecret(masterSecret);</span><a href="#l418"></a>
<span id="l419"></span><a href="#l419"></a>
<span id="l420">                SSLTrafficKeyDerivation kd =</span><a href="#l420"></a>
<span id="l421">                        SSLTrafficKeyDerivation.valueOf(chc.negotiatedProtocol);</span><a href="#l421"></a>
<span id="l422">                if (kd == null) {</span><a href="#l422"></a>
<span id="l423">                    // unlikely</span><a href="#l423"></a>
<span id="l424">                    throw chc.conContext.fatal(Alert.INTERNAL_ERROR,</span><a href="#l424"></a>
<span id="l425">                            &quot;Not supported key derivation: &quot; +</span><a href="#l425"></a>
<span id="l426">                            chc.negotiatedProtocol);</span><a href="#l426"></a>
<span id="l427">                } else {</span><a href="#l427"></a>
<span id="l428">                    chc.handshakeKeyDerivation =</span><a href="#l428"></a>
<span id="l429">                        kd.createKeyDerivation(chc, masterSecret);</span><a href="#l429"></a>
<span id="l430">                }</span><a href="#l430"></a>
<span id="l431">            }</span><a href="#l431"></a>
<span id="l432"></span><a href="#l432"></a>
<span id="l433">            // The handshake message has been delivered.</span><a href="#l433"></a>
<span id="l434">            return null;</span><a href="#l434"></a>
<span id="l435">        }</span><a href="#l435"></a>
<span id="l436">    }</span><a href="#l436"></a>
<span id="l437"></span><a href="#l437"></a>
<span id="l438">    /**</span><a href="#l438"></a>
<span id="l439">     * The ECDHE &quot;ClientKeyExchange&quot; handshake message consumer.</span><a href="#l439"></a>
<span id="l440">     */</span><a href="#l440"></a>
<span id="l441">    private static final</span><a href="#l441"></a>
<span id="l442">            class ECDHEClientKeyExchangeConsumer implements SSLConsumer {</span><a href="#l442"></a>
<span id="l443">        // Prevent instantiation of this class.</span><a href="#l443"></a>
<span id="l444">        private ECDHEClientKeyExchangeConsumer() {</span><a href="#l444"></a>
<span id="l445">            // blank</span><a href="#l445"></a>
<span id="l446">        }</span><a href="#l446"></a>
<span id="l447"></span><a href="#l447"></a>
<span id="l448">        @Override</span><a href="#l448"></a>
<span id="l449">        public void consume(ConnectionContext context,</span><a href="#l449"></a>
<span id="l450">                ByteBuffer message) throws IOException {</span><a href="#l450"></a>
<span id="l451">            // The consuming happens in server side only.</span><a href="#l451"></a>
<span id="l452">            ServerHandshakeContext shc = (ServerHandshakeContext)context;</span><a href="#l452"></a>
<span id="l453"></span><a href="#l453"></a>
<span id="l454">            ECDHEPossession ecdhePossession = null;</span><a href="#l454"></a>
<span id="l455">            for (SSLPossession possession : shc.handshakePossessions) {</span><a href="#l455"></a>
<span id="l456">                if (possession instanceof ECDHEPossession) {</span><a href="#l456"></a>
<span id="l457">                    ecdhePossession = (ECDHEPossession)possession;</span><a href="#l457"></a>
<span id="l458">                    break;</span><a href="#l458"></a>
<span id="l459">                }</span><a href="#l459"></a>
<span id="l460">            }</span><a href="#l460"></a>
<span id="l461">            if (ecdhePossession == null) {</span><a href="#l461"></a>
<span id="l462">                // unlikely</span><a href="#l462"></a>
<span id="l463">                throw shc.conContext.fatal(Alert.INTERNAL_ERROR,</span><a href="#l463"></a>
<span id="l464">                    &quot;No expected ECDHE possessions for client key exchange&quot;);</span><a href="#l464"></a>
<span id="l465">            }</span><a href="#l465"></a>
<span id="l466"></span><a href="#l466"></a>
<span id="l467">            ECParameterSpec params = ecdhePossession.publicKey.getParams();</span><a href="#l467"></a>
<span id="l468">            NamedGroup namedGroup = NamedGroup.valueOf(params);</span><a href="#l468"></a>
<span id="l469">            if (namedGroup == null) {</span><a href="#l469"></a>
<span id="l470">                // unlikely, have been checked during cipher suite negotiation.</span><a href="#l470"></a>
<span id="l471">                throw shc.conContext.fatal(Alert.ILLEGAL_PARAMETER,</span><a href="#l471"></a>
<span id="l472">                    &quot;Unsupported EC server cert for ECDHE client key exchange&quot;);</span><a href="#l472"></a>
<span id="l473">            }</span><a href="#l473"></a>
<span id="l474"></span><a href="#l474"></a>
<span id="l475">            SSLKeyExchange ke = SSLKeyExchange.valueOf(</span><a href="#l475"></a>
<span id="l476">                    shc.negotiatedCipherSuite.keyExchange,</span><a href="#l476"></a>
<span id="l477">                    shc.negotiatedProtocol);</span><a href="#l477"></a>
<span id="l478">            if (ke == null) {</span><a href="#l478"></a>
<span id="l479">                // unlikely</span><a href="#l479"></a>
<span id="l480">                throw shc.conContext.fatal(Alert.INTERNAL_ERROR,</span><a href="#l480"></a>
<span id="l481">                        &quot;Not supported key exchange type&quot;);</span><a href="#l481"></a>
<span id="l482">            }</span><a href="#l482"></a>
<span id="l483"></span><a href="#l483"></a>
<span id="l484">            // parse the handshake message</span><a href="#l484"></a>
<span id="l485">            ECDHClientKeyExchangeMessage cke =</span><a href="#l485"></a>
<span id="l486">                    new ECDHClientKeyExchangeMessage(shc, message);</span><a href="#l486"></a>
<span id="l487">            if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l487"></a>
<span id="l488">                SSLLogger.fine(</span><a href="#l488"></a>
<span id="l489">                    &quot;Consuming ECDHE ClientKeyExchange handshake message&quot;, cke);</span><a href="#l489"></a>
<span id="l490">            }</span><a href="#l490"></a>
<span id="l491"></span><a href="#l491"></a>
<span id="l492">            // create the credentials</span><a href="#l492"></a>
<span id="l493">            try {</span><a href="#l493"></a>
<span id="l494">                ECPoint point =</span><a href="#l494"></a>
<span id="l495">                    JsseJce.decodePoint(cke.encodedPoint, params.getCurve());</span><a href="#l495"></a>
<span id="l496">                ECPublicKeySpec spec = new ECPublicKeySpec(point, params);</span><a href="#l496"></a>
<span id="l497"></span><a href="#l497"></a>
<span id="l498">                KeyFactory kf = JsseJce.getKeyFactory(&quot;EC&quot;);</span><a href="#l498"></a>
<span id="l499">                ECPublicKey peerPublicKey =</span><a href="#l499"></a>
<span id="l500">                        (ECPublicKey)kf.generatePublic(spec);</span><a href="#l500"></a>
<span id="l501"></span><a href="#l501"></a>
<span id="l502">                // check constraints of peer ECPublicKey</span><a href="#l502"></a>
<span id="l503">                if (shc.algorithmConstraints != null &amp;&amp;</span><a href="#l503"></a>
<span id="l504">                        !shc.algorithmConstraints.permits(</span><a href="#l504"></a>
<span id="l505">                                EnumSet.of(CryptoPrimitive.KEY_AGREEMENT),</span><a href="#l505"></a>
<span id="l506">                                peerPublicKey)) {</span><a href="#l506"></a>
<span id="l507">                    throw new SSLHandshakeException(</span><a href="#l507"></a>
<span id="l508">                        &quot;ECPublicKey does not comply to algorithm constraints&quot;);</span><a href="#l508"></a>
<span id="l509">                }</span><a href="#l509"></a>
<span id="l510"></span><a href="#l510"></a>
<span id="l511">                shc.handshakeCredentials.add(new ECDHECredentials(</span><a href="#l511"></a>
<span id="l512">                        peerPublicKey, namedGroup));</span><a href="#l512"></a>
<span id="l513">            } catch (GeneralSecurityException | java.io.IOException e) {</span><a href="#l513"></a>
<span id="l514">                throw (SSLHandshakeException)(new SSLHandshakeException(</span><a href="#l514"></a>
<span id="l515">                        &quot;Could not generate ECPublicKey&quot;).initCause(e));</span><a href="#l515"></a>
<span id="l516">            }</span><a href="#l516"></a>
<span id="l517"></span><a href="#l517"></a>
<span id="l518">            // update the states</span><a href="#l518"></a>
<span id="l519">            SSLKeyDerivation masterKD = ke.createKeyDerivation(shc);</span><a href="#l519"></a>
<span id="l520">            SecretKey masterSecret =</span><a href="#l520"></a>
<span id="l521">                    masterKD.deriveKey(&quot;MasterSecret&quot;, null);</span><a href="#l521"></a>
<span id="l522">            shc.handshakeSession.setMasterSecret(masterSecret);</span><a href="#l522"></a>
<span id="l523"></span><a href="#l523"></a>
<span id="l524">            SSLTrafficKeyDerivation kd =</span><a href="#l524"></a>
<span id="l525">                    SSLTrafficKeyDerivation.valueOf(shc.negotiatedProtocol);</span><a href="#l525"></a>
<span id="l526">            if (kd == null) {</span><a href="#l526"></a>
<span id="l527">                // unlikely</span><a href="#l527"></a>
<span id="l528">                throw shc.conContext.fatal(Alert.INTERNAL_ERROR,</span><a href="#l528"></a>
<span id="l529">                    &quot;Not supported key derivation: &quot; + shc.negotiatedProtocol);</span><a href="#l529"></a>
<span id="l530">            } else {</span><a href="#l530"></a>
<span id="l531">                shc.handshakeKeyDerivation =</span><a href="#l531"></a>
<span id="l532">                    kd.createKeyDerivation(shc, masterSecret);</span><a href="#l532"></a>
<span id="l533">            }</span><a href="#l533"></a>
<span id="l534">        }</span><a href="#l534"></a>
<span id="l535">    }</span><a href="#l535"></a>
<span id="l536">}</span><a href="#l536"></a></pre>
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

