<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 54441ec952f7 src/share/classes/sun/security/ssl/RenegoInfoExtension.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/ssl/RenegoInfoExtension.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/54441ec952f7/src/share/classes/sun/security/ssl/RenegoInfoExtension.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/54441ec952f7/src/share/classes/sun/security/ssl/RenegoInfoExtension.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/54441ec952f7/src/share/classes/sun/security/ssl/RenegoInfoExtension.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/54441ec952f7/src/share/classes/sun/security/ssl/RenegoInfoExtension.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/54441ec952f7/src/share/classes/sun/security/ssl/RenegoInfoExtension.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/ssl/RenegoInfoExtension.java @ 14574:54441ec952f7</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/a73004866eec/src/share/classes/sun/security/ssl/RenegoInfoExtension.java">a73004866eec</a> </td>
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
<span id="l2"> * Copyright (c) 2015, 2018, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l30">import java.security.MessageDigest;</span><a href="#l30"></a>
<span id="l31">import java.text.MessageFormat;</span><a href="#l31"></a>
<span id="l32">import java.util.Arrays;</span><a href="#l32"></a>
<span id="l33">import java.util.Locale;</span><a href="#l33"></a>
<span id="l34">import javax.net.ssl.SSLProtocolException;</span><a href="#l34"></a>
<span id="l35">import sun.security.ssl.ClientHello.ClientHelloMessage;</span><a href="#l35"></a>
<span id="l36">import static sun.security.ssl.SSLExtension.CH_RENEGOTIATION_INFO;</span><a href="#l36"></a>
<span id="l37">import sun.security.ssl.SSLExtension.ExtensionConsumer;</span><a href="#l37"></a>
<span id="l38">import static sun.security.ssl.SSLExtension.SH_RENEGOTIATION_INFO;</span><a href="#l38"></a>
<span id="l39">import sun.security.ssl.SSLExtension.SSLExtensionSpec;</span><a href="#l39"></a>
<span id="l40">import sun.security.ssl.SSLHandshake.HandshakeMessage;</span><a href="#l40"></a>
<span id="l41">import sun.security.util.ByteArrays;</span><a href="#l41"></a>
<span id="l42"></span><a href="#l42"></a>
<span id="l43">/**</span><a href="#l43"></a>
<span id="l44"> * Pack of the &quot;renegotiation_info&quot; extensions [RFC 5746].</span><a href="#l44"></a>
<span id="l45"> */</span><a href="#l45"></a>
<span id="l46">final class RenegoInfoExtension {</span><a href="#l46"></a>
<span id="l47">    static final HandshakeProducer chNetworkProducer =</span><a href="#l47"></a>
<span id="l48">            new CHRenegotiationInfoProducer();</span><a href="#l48"></a>
<span id="l49">    static final ExtensionConsumer chOnLoadConsumer =</span><a href="#l49"></a>
<span id="l50">            new CHRenegotiationInfoConsumer();</span><a href="#l50"></a>
<span id="l51">    static final HandshakeAbsence chOnLoadAbsence =</span><a href="#l51"></a>
<span id="l52">            new CHRenegotiationInfoAbsence();</span><a href="#l52"></a>
<span id="l53"></span><a href="#l53"></a>
<span id="l54">    static final HandshakeProducer shNetworkProducer =</span><a href="#l54"></a>
<span id="l55">            new SHRenegotiationInfoProducer();</span><a href="#l55"></a>
<span id="l56">    static final ExtensionConsumer shOnLoadConsumer =</span><a href="#l56"></a>
<span id="l57">            new SHRenegotiationInfoConsumer();</span><a href="#l57"></a>
<span id="l58">    static final HandshakeAbsence shOnLoadAbsence =</span><a href="#l58"></a>
<span id="l59">            new SHRenegotiationInfoAbsence();</span><a href="#l59"></a>
<span id="l60"></span><a href="#l60"></a>
<span id="l61">    static final SSLStringizer rniStringizer =</span><a href="#l61"></a>
<span id="l62">            new RenegotiationInfoStringizer();</span><a href="#l62"></a>
<span id="l63"></span><a href="#l63"></a>
<span id="l64">    /**</span><a href="#l64"></a>
<span id="l65">     * The &quot;renegotiation_info&quot; extension.</span><a href="#l65"></a>
<span id="l66">     */</span><a href="#l66"></a>
<span id="l67">    static final class RenegotiationInfoSpec implements SSLExtensionSpec {</span><a href="#l67"></a>
<span id="l68">        // A nominal object that does not holding any real renegotiation info.</span><a href="#l68"></a>
<span id="l69">        static final RenegotiationInfoSpec NOMINAL =</span><a href="#l69"></a>
<span id="l70">                new RenegotiationInfoSpec(new byte[0]);</span><a href="#l70"></a>
<span id="l71"></span><a href="#l71"></a>
<span id="l72">        private final byte[] renegotiatedConnection;</span><a href="#l72"></a>
<span id="l73"></span><a href="#l73"></a>
<span id="l74">        private RenegotiationInfoSpec(byte[] renegotiatedConnection) {</span><a href="#l74"></a>
<span id="l75">            this.renegotiatedConnection = Arrays.copyOf(</span><a href="#l75"></a>
<span id="l76">                    renegotiatedConnection, renegotiatedConnection.length);</span><a href="#l76"></a>
<span id="l77">        }</span><a href="#l77"></a>
<span id="l78"></span><a href="#l78"></a>
<span id="l79">        private RenegotiationInfoSpec(ByteBuffer m) throws IOException {</span><a href="#l79"></a>
<span id="l80">            // Parse the extension.</span><a href="#l80"></a>
<span id="l81">            if (!m.hasRemaining() || m.remaining() &lt; 1) {</span><a href="#l81"></a>
<span id="l82">                throw new SSLProtocolException(</span><a href="#l82"></a>
<span id="l83">                    &quot;Invalid renegotiation_info extension data: &quot; +</span><a href="#l83"></a>
<span id="l84">                    &quot;insufficient data&quot;);</span><a href="#l84"></a>
<span id="l85">            }</span><a href="#l85"></a>
<span id="l86">            this.renegotiatedConnection = Record.getBytes8(m);</span><a href="#l86"></a>
<span id="l87">        }</span><a href="#l87"></a>
<span id="l88"></span><a href="#l88"></a>
<span id="l89">        @Override</span><a href="#l89"></a>
<span id="l90">        public String toString() {</span><a href="#l90"></a>
<span id="l91">            MessageFormat messageFormat = new MessageFormat(</span><a href="#l91"></a>
<span id="l92">                &quot;\&quot;renegotiated connection\&quot;: '['{0}']'&quot;, Locale.ENGLISH);</span><a href="#l92"></a>
<span id="l93">            if (renegotiatedConnection.length == 0) {</span><a href="#l93"></a>
<span id="l94">                Object[] messageFields = {</span><a href="#l94"></a>
<span id="l95">                        &quot;&lt;no renegotiated connection&gt;&quot;</span><a href="#l95"></a>
<span id="l96">                    };</span><a href="#l96"></a>
<span id="l97">                return messageFormat.format(messageFields);</span><a href="#l97"></a>
<span id="l98">            } else {</span><a href="#l98"></a>
<span id="l99">                Object[] messageFields = {</span><a href="#l99"></a>
<span id="l100">                        Utilities.toHexString(renegotiatedConnection)</span><a href="#l100"></a>
<span id="l101">                    };</span><a href="#l101"></a>
<span id="l102">                return messageFormat.format(messageFields);</span><a href="#l102"></a>
<span id="l103">            }</span><a href="#l103"></a>
<span id="l104">        }</span><a href="#l104"></a>
<span id="l105">    }</span><a href="#l105"></a>
<span id="l106"></span><a href="#l106"></a>
<span id="l107">    private static final</span><a href="#l107"></a>
<span id="l108">            class RenegotiationInfoStringizer implements SSLStringizer {</span><a href="#l108"></a>
<span id="l109">        @Override</span><a href="#l109"></a>
<span id="l110">        public String toString(ByteBuffer buffer) {</span><a href="#l110"></a>
<span id="l111">            try {</span><a href="#l111"></a>
<span id="l112">                return (new RenegotiationInfoSpec(buffer)).toString();</span><a href="#l112"></a>
<span id="l113">            } catch (IOException ioe) {</span><a href="#l113"></a>
<span id="l114">                // For debug logging only, so please swallow exceptions.</span><a href="#l114"></a>
<span id="l115">                return ioe.getMessage();</span><a href="#l115"></a>
<span id="l116">            }</span><a href="#l116"></a>
<span id="l117">        }</span><a href="#l117"></a>
<span id="l118">    }</span><a href="#l118"></a>
<span id="l119"></span><a href="#l119"></a>
<span id="l120">    /**</span><a href="#l120"></a>
<span id="l121">     * Network data producer of a &quot;renegotiation_info&quot; extension in</span><a href="#l121"></a>
<span id="l122">     * the ClientHello handshake message.</span><a href="#l122"></a>
<span id="l123">     */</span><a href="#l123"></a>
<span id="l124">    private static final</span><a href="#l124"></a>
<span id="l125">            class CHRenegotiationInfoProducer implements HandshakeProducer {</span><a href="#l125"></a>
<span id="l126">        // Prevent instantiation of this class.</span><a href="#l126"></a>
<span id="l127">        private CHRenegotiationInfoProducer() {</span><a href="#l127"></a>
<span id="l128">            // blank</span><a href="#l128"></a>
<span id="l129">        }</span><a href="#l129"></a>
<span id="l130"></span><a href="#l130"></a>
<span id="l131">        @Override</span><a href="#l131"></a>
<span id="l132">        public byte[] produce(ConnectionContext context,</span><a href="#l132"></a>
<span id="l133">                HandshakeMessage message) throws IOException {</span><a href="#l133"></a>
<span id="l134">            // The producing happens in client side only.</span><a href="#l134"></a>
<span id="l135">            ClientHandshakeContext chc = (ClientHandshakeContext)context;</span><a href="#l135"></a>
<span id="l136"></span><a href="#l136"></a>
<span id="l137">            // Is it a supported and enabled extension?</span><a href="#l137"></a>
<span id="l138">            if (!chc.sslConfig.isAvailable(CH_RENEGOTIATION_INFO)) {</span><a href="#l138"></a>
<span id="l139">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l139"></a>
<span id="l140">                    SSLLogger.fine(</span><a href="#l140"></a>
<span id="l141">                            &quot;Ignore unavailable renegotiation_info extension&quot;);</span><a href="#l141"></a>
<span id="l142">                }</span><a href="#l142"></a>
<span id="l143"></span><a href="#l143"></a>
<span id="l144">                return null;</span><a href="#l144"></a>
<span id="l145">            }</span><a href="#l145"></a>
<span id="l146"></span><a href="#l146"></a>
<span id="l147">            if (!chc.conContext.isNegotiated) {</span><a href="#l147"></a>
<span id="l148">                if (chc.activeCipherSuites.contains(</span><a href="#l148"></a>
<span id="l149">                        CipherSuite.TLS_EMPTY_RENEGOTIATION_INFO_SCSV)) {</span><a href="#l149"></a>
<span id="l150">                    // Using the the TLS_EMPTY_RENEGOTIATION_INFO_SCSV instead.</span><a href="#l150"></a>
<span id="l151">                    return null;</span><a href="#l151"></a>
<span id="l152">                }</span><a href="#l152"></a>
<span id="l153"></span><a href="#l153"></a>
<span id="l154">                // initial handshaking.</span><a href="#l154"></a>
<span id="l155">                //</span><a href="#l155"></a>
<span id="l156">                // If this is the initial handshake for a connection, then the</span><a href="#l156"></a>
<span id="l157">                // &quot;renegotiated_connection&quot; field is of zero length in both</span><a href="#l157"></a>
<span id="l158">                // the ClientHello and the ServerHello. [RFC 5746]</span><a href="#l158"></a>
<span id="l159">                byte[] extData = new byte[] { 0x00 };</span><a href="#l159"></a>
<span id="l160">                chc.handshakeExtensions.put(</span><a href="#l160"></a>
<span id="l161">                        CH_RENEGOTIATION_INFO, RenegotiationInfoSpec.NOMINAL);</span><a href="#l161"></a>
<span id="l162"></span><a href="#l162"></a>
<span id="l163">                return extData;</span><a href="#l163"></a>
<span id="l164">            } else if (chc.conContext.secureRenegotiation) {</span><a href="#l164"></a>
<span id="l165">                // secure renegotiation</span><a href="#l165"></a>
<span id="l166">                //</span><a href="#l166"></a>
<span id="l167">                // For ClientHello handshake message in renegotiation, this</span><a href="#l167"></a>
<span id="l168">                // field contains the &quot;client_verify_data&quot;.</span><a href="#l168"></a>
<span id="l169">                byte[] extData =</span><a href="#l169"></a>
<span id="l170">                        new byte[chc.conContext.clientVerifyData.length + 1];</span><a href="#l170"></a>
<span id="l171">                ByteBuffer m = ByteBuffer.wrap(extData);</span><a href="#l171"></a>
<span id="l172">                Record.putBytes8(m, chc.conContext.clientVerifyData);</span><a href="#l172"></a>
<span id="l173"></span><a href="#l173"></a>
<span id="l174">                // The conContext.clientVerifyData will be used for further</span><a href="#l174"></a>
<span id="l175">                // processing, so it does not matter to save whatever in the</span><a href="#l175"></a>
<span id="l176">                // RenegotiationInfoSpec object.</span><a href="#l176"></a>
<span id="l177">                chc.handshakeExtensions.put(</span><a href="#l177"></a>
<span id="l178">                        CH_RENEGOTIATION_INFO, RenegotiationInfoSpec.NOMINAL);</span><a href="#l178"></a>
<span id="l179"></span><a href="#l179"></a>
<span id="l180">                return extData;</span><a href="#l180"></a>
<span id="l181">            } else {    // not secure renegotiation</span><a href="#l181"></a>
<span id="l182">                if (HandshakeContext.allowUnsafeRenegotiation) {</span><a href="#l182"></a>
<span id="l183">                    if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l183"></a>
<span id="l184">                        SSLLogger.warning(&quot;Using insecure renegotiation&quot;);</span><a href="#l184"></a>
<span id="l185">                    }</span><a href="#l185"></a>
<span id="l186"></span><a href="#l186"></a>
<span id="l187">                    return null;</span><a href="#l187"></a>
<span id="l188">                } else {</span><a href="#l188"></a>
<span id="l189">                    // terminate the session.</span><a href="#l189"></a>
<span id="l190">                    throw chc.conContext.fatal(Alert.HANDSHAKE_FAILURE,</span><a href="#l190"></a>
<span id="l191">                            &quot;insecure renegotiation is not allowed&quot;);</span><a href="#l191"></a>
<span id="l192">                }</span><a href="#l192"></a>
<span id="l193">            }</span><a href="#l193"></a>
<span id="l194">        }</span><a href="#l194"></a>
<span id="l195">    }</span><a href="#l195"></a>
<span id="l196"></span><a href="#l196"></a>
<span id="l197">    /**</span><a href="#l197"></a>
<span id="l198">     * Network data producer of a &quot;renegotiation_info&quot; extension in</span><a href="#l198"></a>
<span id="l199">     * the ServerHello handshake message.</span><a href="#l199"></a>
<span id="l200">     */</span><a href="#l200"></a>
<span id="l201">    private static final</span><a href="#l201"></a>
<span id="l202">            class CHRenegotiationInfoConsumer implements ExtensionConsumer {</span><a href="#l202"></a>
<span id="l203">        // Prevent instantiation of this class.</span><a href="#l203"></a>
<span id="l204">        private CHRenegotiationInfoConsumer() {</span><a href="#l204"></a>
<span id="l205">            // blank</span><a href="#l205"></a>
<span id="l206">        }</span><a href="#l206"></a>
<span id="l207"></span><a href="#l207"></a>
<span id="l208">        @Override</span><a href="#l208"></a>
<span id="l209">        public void consume(ConnectionContext context,</span><a href="#l209"></a>
<span id="l210">            HandshakeMessage message, ByteBuffer buffer) throws IOException {</span><a href="#l210"></a>
<span id="l211"></span><a href="#l211"></a>
<span id="l212">            // The consuming happens in server side only.</span><a href="#l212"></a>
<span id="l213">            ServerHandshakeContext shc = (ServerHandshakeContext)context;</span><a href="#l213"></a>
<span id="l214"></span><a href="#l214"></a>
<span id="l215">            // Is it a supported and enabled extension?</span><a href="#l215"></a>
<span id="l216">            if (!shc.sslConfig.isAvailable(CH_RENEGOTIATION_INFO)) {</span><a href="#l216"></a>
<span id="l217">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l217"></a>
<span id="l218">                    SSLLogger.fine(&quot;Ignore unavailable extension: &quot; +</span><a href="#l218"></a>
<span id="l219">                            CH_RENEGOTIATION_INFO.name);</span><a href="#l219"></a>
<span id="l220">                }</span><a href="#l220"></a>
<span id="l221">                return;     // ignore the extension</span><a href="#l221"></a>
<span id="l222">            }</span><a href="#l222"></a>
<span id="l223"></span><a href="#l223"></a>
<span id="l224">            // Parse the extension.</span><a href="#l224"></a>
<span id="l225">            RenegotiationInfoSpec spec;</span><a href="#l225"></a>
<span id="l226">            try {</span><a href="#l226"></a>
<span id="l227">                spec = new RenegotiationInfoSpec(buffer);</span><a href="#l227"></a>
<span id="l228">            } catch (IOException ioe) {</span><a href="#l228"></a>
<span id="l229">                throw shc.conContext.fatal(Alert.UNEXPECTED_MESSAGE, ioe);</span><a href="#l229"></a>
<span id="l230">            }</span><a href="#l230"></a>
<span id="l231"></span><a href="#l231"></a>
<span id="l232">            if (!shc.conContext.isNegotiated) {</span><a href="#l232"></a>
<span id="l233">                // initial handshaking.</span><a href="#l233"></a>
<span id="l234">                if (spec.renegotiatedConnection.length != 0) {</span><a href="#l234"></a>
<span id="l235">                    throw shc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,</span><a href="#l235"></a>
<span id="l236">                        &quot;Invalid renegotiation_info extension data: not empty&quot;);</span><a href="#l236"></a>
<span id="l237">                }</span><a href="#l237"></a>
<span id="l238">                shc.conContext.secureRenegotiation = true;</span><a href="#l238"></a>
<span id="l239">            } else {</span><a href="#l239"></a>
<span id="l240">                if (!shc.conContext.secureRenegotiation) {</span><a href="#l240"></a>
<span id="l241">                    // Unexpected RI extension for insecure renegotiation,</span><a href="#l241"></a>
<span id="l242">                    // abort the handshake with a fatal handshake_failure alert.</span><a href="#l242"></a>
<span id="l243">                    throw shc.conContext.fatal(Alert.HANDSHAKE_FAILURE,</span><a href="#l243"></a>
<span id="l244">                            &quot;The renegotiation_info is present in a insecure &quot; +</span><a href="#l244"></a>
<span id="l245">                            &quot;renegotiation&quot;);</span><a href="#l245"></a>
<span id="l246">                } else {</span><a href="#l246"></a>
<span id="l247">                    // verify the client_verify_data value</span><a href="#l247"></a>
<span id="l248">                    if (!MessageDigest.isEqual(shc.conContext.clientVerifyData,</span><a href="#l248"></a>
<span id="l249">                            spec.renegotiatedConnection)) {</span><a href="#l249"></a>
<span id="l250">                        throw shc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,</span><a href="#l250"></a>
<span id="l251">                            &quot;Invalid renegotiation_info extension data: &quot; +</span><a href="#l251"></a>
<span id="l252">                            &quot;incorrect verify data in ClientHello&quot;);</span><a href="#l252"></a>
<span id="l253">                    }</span><a href="#l253"></a>
<span id="l254">                }</span><a href="#l254"></a>
<span id="l255">            }</span><a href="#l255"></a>
<span id="l256"></span><a href="#l256"></a>
<span id="l257">            // Update the context.</span><a href="#l257"></a>
<span id="l258">            //</span><a href="#l258"></a>
<span id="l259">            // The conContext.clientVerifyData will be used for further</span><a href="#l259"></a>
<span id="l260">            // processing, so it does not matter to save whatever in the</span><a href="#l260"></a>
<span id="l261">            // RenegotiationInfoSpec object.</span><a href="#l261"></a>
<span id="l262">            shc.handshakeExtensions.put(</span><a href="#l262"></a>
<span id="l263">                    CH_RENEGOTIATION_INFO, RenegotiationInfoSpec.NOMINAL);</span><a href="#l263"></a>
<span id="l264"></span><a href="#l264"></a>
<span id="l265">            // No impact on session resumption.</span><a href="#l265"></a>
<span id="l266">        }</span><a href="#l266"></a>
<span id="l267">    }</span><a href="#l267"></a>
<span id="l268"></span><a href="#l268"></a>
<span id="l269">    /**</span><a href="#l269"></a>
<span id="l270">     * The absence processing if a &quot;renegotiation_info&quot; extension is</span><a href="#l270"></a>
<span id="l271">     * not present in the ClientHello handshake message.</span><a href="#l271"></a>
<span id="l272">     */</span><a href="#l272"></a>
<span id="l273">    private static final</span><a href="#l273"></a>
<span id="l274">            class CHRenegotiationInfoAbsence implements HandshakeAbsence {</span><a href="#l274"></a>
<span id="l275">        @Override</span><a href="#l275"></a>
<span id="l276">        public void absent(ConnectionContext context,</span><a href="#l276"></a>
<span id="l277">                HandshakeMessage message) throws IOException {</span><a href="#l277"></a>
<span id="l278">            // The producing happens in server side only.</span><a href="#l278"></a>
<span id="l279">            ServerHandshakeContext shc = (ServerHandshakeContext)context;</span><a href="#l279"></a>
<span id="l280">            ClientHelloMessage clientHello = (ClientHelloMessage)message;</span><a href="#l280"></a>
<span id="l281"></span><a href="#l281"></a>
<span id="l282">            if (!shc.conContext.isNegotiated) {</span><a href="#l282"></a>
<span id="l283">                // initial handshaking.</span><a href="#l283"></a>
<span id="l284">                for (int id : clientHello.cipherSuiteIds) {</span><a href="#l284"></a>
<span id="l285">                    if (id ==</span><a href="#l285"></a>
<span id="l286">                            CipherSuite.TLS_EMPTY_RENEGOTIATION_INFO_SCSV.id) {</span><a href="#l286"></a>
<span id="l287">                        if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l287"></a>
<span id="l288">                            SSLLogger.finest(</span><a href="#l288"></a>
<span id="l289">                                &quot;Safe renegotiation, using the SCSV signgling&quot;);</span><a href="#l289"></a>
<span id="l290">                        }</span><a href="#l290"></a>
<span id="l291">                        shc.conContext.secureRenegotiation = true;</span><a href="#l291"></a>
<span id="l292">                        return;</span><a href="#l292"></a>
<span id="l293">                    }</span><a href="#l293"></a>
<span id="l294">                }</span><a href="#l294"></a>
<span id="l295"></span><a href="#l295"></a>
<span id="l296">                if (!HandshakeContext.allowLegacyHelloMessages) {</span><a href="#l296"></a>
<span id="l297">                    throw shc.conContext.fatal(Alert.HANDSHAKE_FAILURE,</span><a href="#l297"></a>
<span id="l298">                        &quot;Failed to negotiate the use of secure renegotiation&quot;);</span><a href="#l298"></a>
<span id="l299">                }   // otherwise, allow legacy hello message</span><a href="#l299"></a>
<span id="l300"></span><a href="#l300"></a>
<span id="l301">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l301"></a>
<span id="l302">                    SSLLogger.warning(&quot;Warning: No renegotiation &quot; +</span><a href="#l302"></a>
<span id="l303">                        &quot;indication in ClientHello, allow legacy ClientHello&quot;);</span><a href="#l303"></a>
<span id="l304">                }</span><a href="#l304"></a>
<span id="l305"></span><a href="#l305"></a>
<span id="l306">                shc.conContext.secureRenegotiation = false;</span><a href="#l306"></a>
<span id="l307">            } else if (shc.conContext.secureRenegotiation) {</span><a href="#l307"></a>
<span id="l308">                // Require secure renegotiation, terminate the connection.</span><a href="#l308"></a>
<span id="l309">                throw shc.conContext.fatal(Alert.HANDSHAKE_FAILURE,</span><a href="#l309"></a>
<span id="l310">                        &quot;Inconsistent secure renegotiation indication&quot;);</span><a href="#l310"></a>
<span id="l311">            } else {    // renegotiation, not secure</span><a href="#l311"></a>
<span id="l312">                if (HandshakeContext.allowUnsafeRenegotiation) {</span><a href="#l312"></a>
<span id="l313">                    if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l313"></a>
<span id="l314">                        SSLLogger.warning(&quot;Using insecure renegotiation&quot;);</span><a href="#l314"></a>
<span id="l315">                    }</span><a href="#l315"></a>
<span id="l316">                } else {</span><a href="#l316"></a>
<span id="l317">                    // Unsafe renegotiation should have been aborted in</span><a href="#l317"></a>
<span id="l318">                    // ealier processes.</span><a href="#l318"></a>
<span id="l319">                    if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l319"></a>
<span id="l320">                        SSLLogger.fine(&quot;Terminate insecure renegotiation&quot;);</span><a href="#l320"></a>
<span id="l321">                    }</span><a href="#l321"></a>
<span id="l322">                    throw shc.conContext.fatal(Alert.HANDSHAKE_FAILURE,</span><a href="#l322"></a>
<span id="l323">                        &quot;Unsafe renegotiation is not allowed&quot;);</span><a href="#l323"></a>
<span id="l324">                }</span><a href="#l324"></a>
<span id="l325">            }</span><a href="#l325"></a>
<span id="l326">        }</span><a href="#l326"></a>
<span id="l327">    }</span><a href="#l327"></a>
<span id="l328"></span><a href="#l328"></a>
<span id="l329">    /**</span><a href="#l329"></a>
<span id="l330">     * Network data producer of a &quot;renegotiation_info&quot; extension in</span><a href="#l330"></a>
<span id="l331">     * the ServerHello handshake message.</span><a href="#l331"></a>
<span id="l332">     */</span><a href="#l332"></a>
<span id="l333">    private static final</span><a href="#l333"></a>
<span id="l334">            class SHRenegotiationInfoProducer implements HandshakeProducer {</span><a href="#l334"></a>
<span id="l335">        // Prevent instantiation of this class.</span><a href="#l335"></a>
<span id="l336">        private SHRenegotiationInfoProducer() {</span><a href="#l336"></a>
<span id="l337">            // blank</span><a href="#l337"></a>
<span id="l338">        }</span><a href="#l338"></a>
<span id="l339"></span><a href="#l339"></a>
<span id="l340">        @Override</span><a href="#l340"></a>
<span id="l341">        public byte[] produce(ConnectionContext context,</span><a href="#l341"></a>
<span id="l342">                HandshakeMessage message) throws IOException {</span><a href="#l342"></a>
<span id="l343">            // The producing happens in server side only.</span><a href="#l343"></a>
<span id="l344">            ServerHandshakeContext shc = (ServerHandshakeContext)context;</span><a href="#l344"></a>
<span id="l345"></span><a href="#l345"></a>
<span id="l346">            // In response to &quot;renegotiation_info&quot; extension request only.</span><a href="#l346"></a>
<span id="l347">            RenegotiationInfoSpec requestedSpec = (RenegotiationInfoSpec)</span><a href="#l347"></a>
<span id="l348">                    shc.handshakeExtensions.get(CH_RENEGOTIATION_INFO);</span><a href="#l348"></a>
<span id="l349">            if (requestedSpec == null &amp;&amp; !shc.conContext.secureRenegotiation) {</span><a href="#l349"></a>
<span id="l350">                // Ignore, no renegotiation_info extension or SCSV signgling</span><a href="#l350"></a>
<span id="l351">                // requested.</span><a href="#l351"></a>
<span id="l352">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l352"></a>
<span id="l353">                    SSLLogger.finest(</span><a href="#l353"></a>
<span id="l354">                        &quot;Ignore unavailable renegotiation_info extension&quot;);</span><a href="#l354"></a>
<span id="l355">                }</span><a href="#l355"></a>
<span id="l356">                return null;        // ignore the extension</span><a href="#l356"></a>
<span id="l357">            }</span><a href="#l357"></a>
<span id="l358"></span><a href="#l358"></a>
<span id="l359">            if (!shc.conContext.secureRenegotiation) {</span><a href="#l359"></a>
<span id="l360">                // Ignore, no secure renegotiation is negotiated.</span><a href="#l360"></a>
<span id="l361">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l361"></a>
<span id="l362">                    SSLLogger.finest(</span><a href="#l362"></a>
<span id="l363">                        &quot;No secure renegotiation has been negotiated&quot;);</span><a href="#l363"></a>
<span id="l364">                }</span><a href="#l364"></a>
<span id="l365">                return null;        // ignore the extension</span><a href="#l365"></a>
<span id="l366">            }</span><a href="#l366"></a>
<span id="l367"></span><a href="#l367"></a>
<span id="l368">            if (!shc.conContext.isNegotiated) {</span><a href="#l368"></a>
<span id="l369">                // initial handshaking.</span><a href="#l369"></a>
<span id="l370">                //</span><a href="#l370"></a>
<span id="l371">                // If this is the initial handshake for a connection, then the</span><a href="#l371"></a>
<span id="l372">                // &quot;renegotiated_connection&quot; field is of zero length in both</span><a href="#l372"></a>
<span id="l373">                // the ClientHello and the ServerHello. [RFC 5746]</span><a href="#l373"></a>
<span id="l374">                byte[] extData = new byte[] { 0x00 };</span><a href="#l374"></a>
<span id="l375"></span><a href="#l375"></a>
<span id="l376">                // The conContext.client/serverVerifyData will be used for</span><a href="#l376"></a>
<span id="l377">                // further processing, so it does not matter to save whatever</span><a href="#l377"></a>
<span id="l378">                // in the RenegotiationInfoSpec object.</span><a href="#l378"></a>
<span id="l379">                shc.handshakeExtensions.put(</span><a href="#l379"></a>
<span id="l380">                        SH_RENEGOTIATION_INFO, RenegotiationInfoSpec.NOMINAL);</span><a href="#l380"></a>
<span id="l381"></span><a href="#l381"></a>
<span id="l382">                return extData;</span><a href="#l382"></a>
<span id="l383">            } else {</span><a href="#l383"></a>
<span id="l384">                // secure renegotiation</span><a href="#l384"></a>
<span id="l385">                //</span><a href="#l385"></a>
<span id="l386">                // For secure renegotiation, the server MUST include a</span><a href="#l386"></a>
<span id="l387">                // &quot;renegotiation_info&quot; extension containing the saved</span><a href="#l387"></a>
<span id="l388">                // client_verify_data and server_verify_data in the ServerHello.</span><a href="#l388"></a>
<span id="l389">                int infoLen = shc.conContext.clientVerifyData.length +</span><a href="#l389"></a>
<span id="l390">                              shc.conContext.serverVerifyData.length;</span><a href="#l390"></a>
<span id="l391">                byte[] extData = new byte[infoLen + 1];</span><a href="#l391"></a>
<span id="l392">                ByteBuffer m = ByteBuffer.wrap(extData);</span><a href="#l392"></a>
<span id="l393">                Record.putInt8(m, infoLen);</span><a href="#l393"></a>
<span id="l394">                m.put(shc.conContext.clientVerifyData);</span><a href="#l394"></a>
<span id="l395">                m.put(shc.conContext.serverVerifyData);</span><a href="#l395"></a>
<span id="l396"></span><a href="#l396"></a>
<span id="l397">                // The conContext.client/serverVerifyData will be used for</span><a href="#l397"></a>
<span id="l398">                // further processing, so it does not matter to save whatever</span><a href="#l398"></a>
<span id="l399">                // in the RenegotiationInfoSpec object.</span><a href="#l399"></a>
<span id="l400">                shc.handshakeExtensions.put(</span><a href="#l400"></a>
<span id="l401">                        SH_RENEGOTIATION_INFO, RenegotiationInfoSpec.NOMINAL);</span><a href="#l401"></a>
<span id="l402"></span><a href="#l402"></a>
<span id="l403">                return extData;</span><a href="#l403"></a>
<span id="l404">            }</span><a href="#l404"></a>
<span id="l405">        }</span><a href="#l405"></a>
<span id="l406">    }</span><a href="#l406"></a>
<span id="l407"></span><a href="#l407"></a>
<span id="l408">    /**</span><a href="#l408"></a>
<span id="l409">     * Network data consumer of a &quot;renegotiation_info&quot; extension in</span><a href="#l409"></a>
<span id="l410">     * the ServerHello handshake message.</span><a href="#l410"></a>
<span id="l411">     */</span><a href="#l411"></a>
<span id="l412">    private static final</span><a href="#l412"></a>
<span id="l413">            class SHRenegotiationInfoConsumer implements ExtensionConsumer {</span><a href="#l413"></a>
<span id="l414">        // Prevent instantiation of this class.</span><a href="#l414"></a>
<span id="l415">        private SHRenegotiationInfoConsumer() {</span><a href="#l415"></a>
<span id="l416">            // blank</span><a href="#l416"></a>
<span id="l417">        }</span><a href="#l417"></a>
<span id="l418"></span><a href="#l418"></a>
<span id="l419">        @Override</span><a href="#l419"></a>
<span id="l420">        public void consume(ConnectionContext context,</span><a href="#l420"></a>
<span id="l421">            HandshakeMessage message, ByteBuffer buffer) throws IOException {</span><a href="#l421"></a>
<span id="l422">            // The producing happens in client side only.</span><a href="#l422"></a>
<span id="l423">            ClientHandshakeContext chc = (ClientHandshakeContext)context;</span><a href="#l423"></a>
<span id="l424"></span><a href="#l424"></a>
<span id="l425">            // In response to the client renegotiation_info extension request</span><a href="#l425"></a>
<span id="l426">            // or SCSV signling, which is mandatory for ClientHello message.</span><a href="#l426"></a>
<span id="l427">            RenegotiationInfoSpec requestedSpec = (RenegotiationInfoSpec)</span><a href="#l427"></a>
<span id="l428">                    chc.handshakeExtensions.get(CH_RENEGOTIATION_INFO);</span><a href="#l428"></a>
<span id="l429">            if (requestedSpec == null &amp;&amp;</span><a href="#l429"></a>
<span id="l430">                    !chc.activeCipherSuites.contains(</span><a href="#l430"></a>
<span id="l431">                            CipherSuite.TLS_EMPTY_RENEGOTIATION_INFO_SCSV)) {</span><a href="#l431"></a>
<span id="l432">                throw chc.conContext.fatal(Alert.INTERNAL_ERROR,</span><a href="#l432"></a>
<span id="l433">                    &quot;Missing renegotiation_info and SCSV detected in &quot; +</span><a href="#l433"></a>
<span id="l434">                    &quot;ClientHello&quot;);</span><a href="#l434"></a>
<span id="l435">            }</span><a href="#l435"></a>
<span id="l436"></span><a href="#l436"></a>
<span id="l437">            // Parse the extension.</span><a href="#l437"></a>
<span id="l438">            RenegotiationInfoSpec spec;</span><a href="#l438"></a>
<span id="l439">            try {</span><a href="#l439"></a>
<span id="l440">                spec = new RenegotiationInfoSpec(buffer);</span><a href="#l440"></a>
<span id="l441">            } catch (IOException ioe) {</span><a href="#l441"></a>
<span id="l442">                throw chc.conContext.fatal(Alert.UNEXPECTED_MESSAGE, ioe);</span><a href="#l442"></a>
<span id="l443">            }</span><a href="#l443"></a>
<span id="l444"></span><a href="#l444"></a>
<span id="l445"></span><a href="#l445"></a>
<span id="l446">            if (!chc.conContext.isNegotiated) {     // initial handshake</span><a href="#l446"></a>
<span id="l447">                // If the extension is present, set the secure_renegotiation</span><a href="#l447"></a>
<span id="l448">                // flag to TRUE.  The client MUST then verify that the</span><a href="#l448"></a>
<span id="l449">                // length of the &quot;renegotiated_connection&quot; field is zero,</span><a href="#l449"></a>
<span id="l450">                // and if it is not, MUST abort the handshake (by sending</span><a href="#l450"></a>
<span id="l451">                // a fatal handshake_failure alert). [RFC 5746]</span><a href="#l451"></a>
<span id="l452">                if (spec.renegotiatedConnection.length != 0) {</span><a href="#l452"></a>
<span id="l453">                    throw chc.conContext.fatal(Alert.HANDSHAKE_FAILURE,</span><a href="#l453"></a>
<span id="l454">                        &quot;Invalid renegotiation_info in ServerHello: &quot; +</span><a href="#l454"></a>
<span id="l455">                        &quot;not empty renegotiated_connection&quot;);</span><a href="#l455"></a>
<span id="l456">                }</span><a href="#l456"></a>
<span id="l457"></span><a href="#l457"></a>
<span id="l458">                chc.conContext.secureRenegotiation = true;</span><a href="#l458"></a>
<span id="l459">            } else {        // renegotiation</span><a href="#l459"></a>
<span id="l460">                // The client MUST then verify that the first half of the</span><a href="#l460"></a>
<span id="l461">                // &quot;renegotiated_connection&quot; field is equal to the saved</span><a href="#l461"></a>
<span id="l462">                // client_verify_data value, and the second half is equal to the</span><a href="#l462"></a>
<span id="l463">                // saved server_verify_data value.  If they are not, the client</span><a href="#l463"></a>
<span id="l464">                // MUST abort the handshake. [RFC 5746]</span><a href="#l464"></a>
<span id="l465">                int infoLen = chc.conContext.clientVerifyData.length +</span><a href="#l465"></a>
<span id="l466">                              chc.conContext.serverVerifyData.length;</span><a href="#l466"></a>
<span id="l467">                if (spec.renegotiatedConnection.length != infoLen) {</span><a href="#l467"></a>
<span id="l468">                    throw chc.conContext.fatal(Alert.HANDSHAKE_FAILURE,</span><a href="#l468"></a>
<span id="l469">                        &quot;Invalid renegotiation_info in ServerHello: &quot; +</span><a href="#l469"></a>
<span id="l470">                        &quot;invalid renegotiated_connection length (&quot; +</span><a href="#l470"></a>
<span id="l471">                        spec.renegotiatedConnection.length + &quot;)&quot;);</span><a href="#l471"></a>
<span id="l472">                }</span><a href="#l472"></a>
<span id="l473"></span><a href="#l473"></a>
<span id="l474">                byte[] cvd = chc.conContext.clientVerifyData;</span><a href="#l474"></a>
<span id="l475">                if (!ByteArrays.isEqual(spec.renegotiatedConnection,</span><a href="#l475"></a>
<span id="l476">                        0, cvd.length, cvd, 0, cvd.length)) {</span><a href="#l476"></a>
<span id="l477">                    throw chc.conContext.fatal(Alert.HANDSHAKE_FAILURE,</span><a href="#l477"></a>
<span id="l478">                        &quot;Invalid renegotiation_info in ServerHello: &quot; +</span><a href="#l478"></a>
<span id="l479">                        &quot;unmatched client_verify_data value&quot;);</span><a href="#l479"></a>
<span id="l480">                }</span><a href="#l480"></a>
<span id="l481">                byte[] svd = chc.conContext.serverVerifyData;</span><a href="#l481"></a>
<span id="l482">                if (!ByteArrays.isEqual(spec.renegotiatedConnection,</span><a href="#l482"></a>
<span id="l483">                        cvd.length, infoLen, svd, 0, svd.length)) {</span><a href="#l483"></a>
<span id="l484">                    throw chc.conContext.fatal(Alert.HANDSHAKE_FAILURE,</span><a href="#l484"></a>
<span id="l485">                        &quot;Invalid renegotiation_info in ServerHello: &quot; +</span><a href="#l485"></a>
<span id="l486">                        &quot;unmatched server_verify_data value&quot;);</span><a href="#l486"></a>
<span id="l487">                }</span><a href="#l487"></a>
<span id="l488">            }</span><a href="#l488"></a>
<span id="l489"></span><a href="#l489"></a>
<span id="l490">            // Update the context.</span><a href="#l490"></a>
<span id="l491">            chc.handshakeExtensions.put(</span><a href="#l491"></a>
<span id="l492">                    SH_RENEGOTIATION_INFO, RenegotiationInfoSpec.NOMINAL);</span><a href="#l492"></a>
<span id="l493"></span><a href="#l493"></a>
<span id="l494">            // No impact on session resumption.</span><a href="#l494"></a>
<span id="l495">        }</span><a href="#l495"></a>
<span id="l496">    }</span><a href="#l496"></a>
<span id="l497"></span><a href="#l497"></a>
<span id="l498">    /**</span><a href="#l498"></a>
<span id="l499">     * The absence processing if a &quot;renegotiation_info&quot; extension is</span><a href="#l499"></a>
<span id="l500">     * not present in the ServerHello handshake message.</span><a href="#l500"></a>
<span id="l501">     */</span><a href="#l501"></a>
<span id="l502">    private static final</span><a href="#l502"></a>
<span id="l503">            class SHRenegotiationInfoAbsence implements HandshakeAbsence {</span><a href="#l503"></a>
<span id="l504">        @Override</span><a href="#l504"></a>
<span id="l505">        public void absent(ConnectionContext context,</span><a href="#l505"></a>
<span id="l506">                HandshakeMessage message) throws IOException {</span><a href="#l506"></a>
<span id="l507">            // The producing happens in client side only.</span><a href="#l507"></a>
<span id="l508">            ClientHandshakeContext chc = (ClientHandshakeContext)context;</span><a href="#l508"></a>
<span id="l509"></span><a href="#l509"></a>
<span id="l510">            // In response to the client renegotiation_info extension request</span><a href="#l510"></a>
<span id="l511">            // or SCSV signling, which is mandatory for ClientHello message.</span><a href="#l511"></a>
<span id="l512">            RenegotiationInfoSpec requestedSpec = (RenegotiationInfoSpec)</span><a href="#l512"></a>
<span id="l513">                    chc.handshakeExtensions.get(CH_RENEGOTIATION_INFO);</span><a href="#l513"></a>
<span id="l514">            if (requestedSpec == null &amp;&amp;</span><a href="#l514"></a>
<span id="l515">                    !chc.activeCipherSuites.contains(</span><a href="#l515"></a>
<span id="l516">                            CipherSuite.TLS_EMPTY_RENEGOTIATION_INFO_SCSV)) {</span><a href="#l516"></a>
<span id="l517">                throw chc.conContext.fatal(Alert.INTERNAL_ERROR,</span><a href="#l517"></a>
<span id="l518">                    &quot;Missing renegotiation_info and SCSV detected in &quot; +</span><a href="#l518"></a>
<span id="l519">                    &quot;ClientHello&quot;);</span><a href="#l519"></a>
<span id="l520">            }</span><a href="#l520"></a>
<span id="l521"></span><a href="#l521"></a>
<span id="l522">            if (!chc.conContext.isNegotiated) {</span><a href="#l522"></a>
<span id="l523">                // initial handshaking.</span><a href="#l523"></a>
<span id="l524">                if (!HandshakeContext.allowLegacyHelloMessages) {</span><a href="#l524"></a>
<span id="l525">                    throw chc.conContext.fatal(Alert.HANDSHAKE_FAILURE,</span><a href="#l525"></a>
<span id="l526">                        &quot;Failed to negotiate the use of secure renegotiation&quot;);</span><a href="#l526"></a>
<span id="l527">                }   // otherwise, allow legacy hello message</span><a href="#l527"></a>
<span id="l528"></span><a href="#l528"></a>
<span id="l529">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l529"></a>
<span id="l530">                    SSLLogger.warning(&quot;Warning: No renegotiation &quot; +</span><a href="#l530"></a>
<span id="l531">                        &quot;indication in ServerHello, allow legacy ServerHello&quot;);</span><a href="#l531"></a>
<span id="l532">                }</span><a href="#l532"></a>
<span id="l533"></span><a href="#l533"></a>
<span id="l534">                chc.conContext.secureRenegotiation = false;</span><a href="#l534"></a>
<span id="l535">            } else if (chc.conContext.secureRenegotiation) {</span><a href="#l535"></a>
<span id="l536">                // Require secure renegotiation, terminate the connection.</span><a href="#l536"></a>
<span id="l537">                throw chc.conContext.fatal(Alert.HANDSHAKE_FAILURE,</span><a href="#l537"></a>
<span id="l538">                        &quot;Inconsistent secure renegotiation indication&quot;);</span><a href="#l538"></a>
<span id="l539">            } else {    // renegotiation, not secure</span><a href="#l539"></a>
<span id="l540">                if (HandshakeContext.allowUnsafeRenegotiation) {</span><a href="#l540"></a>
<span id="l541">                    if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l541"></a>
<span id="l542">                        SSLLogger.warning(&quot;Using insecure renegotiation&quot;);</span><a href="#l542"></a>
<span id="l543">                    }</span><a href="#l543"></a>
<span id="l544">                } else {</span><a href="#l544"></a>
<span id="l545">                    // Unsafe renegotiation should have been aborted in</span><a href="#l545"></a>
<span id="l546">                    // ealier processes.</span><a href="#l546"></a>
<span id="l547">                    if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l547"></a>
<span id="l548">                        SSLLogger.fine(&quot;Terminate insecure renegotiation&quot;);</span><a href="#l548"></a>
<span id="l549">                    }</span><a href="#l549"></a>
<span id="l550">                    throw chc.conContext.fatal(Alert.HANDSHAKE_FAILURE,</span><a href="#l550"></a>
<span id="l551">                        &quot;Unsafe renegotiation is not allowed&quot;);</span><a href="#l551"></a>
<span id="l552">                }</span><a href="#l552"></a>
<span id="l553">            }</span><a href="#l553"></a>
<span id="l554">        }</span><a href="#l554"></a>
<span id="l555">    }</span><a href="#l555"></a>
<span id="l556">}</span><a href="#l556"></a></pre>
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

