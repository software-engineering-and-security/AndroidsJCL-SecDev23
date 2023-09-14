<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 446338ed795d src/share/classes/sun/security/ssl/KeyShareExtension.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/ssl/KeyShareExtension.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/446338ed795d/src/share/classes/sun/security/ssl/KeyShareExtension.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/446338ed795d/src/share/classes/sun/security/ssl/KeyShareExtension.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/446338ed795d/src/share/classes/sun/security/ssl/KeyShareExtension.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/446338ed795d/src/share/classes/sun/security/ssl/KeyShareExtension.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/446338ed795d/src/share/classes/sun/security/ssl/KeyShareExtension.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/ssl/KeyShareExtension.java @ 14569:446338ed795d</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/a73004866eec/src/share/classes/sun/security/ssl/KeyShareExtension.java">a73004866eec</a> </td>
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
<span id="l30">import java.security.CryptoPrimitive;</span><a href="#l30"></a>
<span id="l31">import java.security.GeneralSecurityException;</span><a href="#l31"></a>
<span id="l32">import java.text.MessageFormat;</span><a href="#l32"></a>
<span id="l33">import java.util.Arrays;</span><a href="#l33"></a>
<span id="l34">import java.util.Collections;</span><a href="#l34"></a>
<span id="l35">import java.util.EnumSet;</span><a href="#l35"></a>
<span id="l36">import java.util.LinkedList;</span><a href="#l36"></a>
<span id="l37">import java.util.List;</span><a href="#l37"></a>
<span id="l38">import java.util.Locale;</span><a href="#l38"></a>
<span id="l39">import java.util.Map;</span><a href="#l39"></a>
<span id="l40">import javax.net.ssl.SSLProtocolException;</span><a href="#l40"></a>
<span id="l41">import sun.security.ssl.DHKeyExchange.DHECredentials;</span><a href="#l41"></a>
<span id="l42">import sun.security.ssl.DHKeyExchange.DHEPossession;</span><a href="#l42"></a>
<span id="l43">import sun.security.ssl.ECDHKeyExchange.ECDHECredentials;</span><a href="#l43"></a>
<span id="l44">import sun.security.ssl.ECDHKeyExchange.ECDHEPossession;</span><a href="#l44"></a>
<span id="l45">import sun.security.ssl.KeyShareExtension.CHKeyShareSpec;</span><a href="#l45"></a>
<span id="l46">import sun.security.ssl.SSLExtension.ExtensionConsumer;</span><a href="#l46"></a>
<span id="l47">import sun.security.ssl.SSLExtension.SSLExtensionSpec;</span><a href="#l47"></a>
<span id="l48">import sun.security.ssl.SSLHandshake.HandshakeMessage;</span><a href="#l48"></a>
<span id="l49">import sun.security.ssl.SupportedGroupsExtension.NamedGroup;</span><a href="#l49"></a>
<span id="l50">import sun.security.ssl.SupportedGroupsExtension.NamedGroupType;</span><a href="#l50"></a>
<span id="l51">import sun.security.ssl.SupportedGroupsExtension.SupportedGroups;</span><a href="#l51"></a>
<span id="l52">import sun.misc.HexDumpEncoder;</span><a href="#l52"></a>
<span id="l53"></span><a href="#l53"></a>
<span id="l54">/**</span><a href="#l54"></a>
<span id="l55"> * Pack of the &quot;key_share&quot; extensions.</span><a href="#l55"></a>
<span id="l56"> */</span><a href="#l56"></a>
<span id="l57">final class KeyShareExtension {</span><a href="#l57"></a>
<span id="l58">    static final HandshakeProducer chNetworkProducer =</span><a href="#l58"></a>
<span id="l59">            new CHKeyShareProducer();</span><a href="#l59"></a>
<span id="l60">    static final ExtensionConsumer chOnLoadConsumer =</span><a href="#l60"></a>
<span id="l61">            new CHKeyShareConsumer();</span><a href="#l61"></a>
<span id="l62">    static final SSLStringizer chStringizer =</span><a href="#l62"></a>
<span id="l63">            new CHKeyShareStringizer();</span><a href="#l63"></a>
<span id="l64"></span><a href="#l64"></a>
<span id="l65">    static final HandshakeProducer shNetworkProducer =</span><a href="#l65"></a>
<span id="l66">            new SHKeyShareProducer();</span><a href="#l66"></a>
<span id="l67">    static final ExtensionConsumer shOnLoadConsumer =</span><a href="#l67"></a>
<span id="l68">            new SHKeyShareConsumer();</span><a href="#l68"></a>
<span id="l69">    static final HandshakeAbsence shOnLoadAbsence =</span><a href="#l69"></a>
<span id="l70">            new SHKeyShareAbsence();</span><a href="#l70"></a>
<span id="l71">    static final SSLStringizer shStringizer =</span><a href="#l71"></a>
<span id="l72">            new SHKeyShareStringizer();</span><a href="#l72"></a>
<span id="l73"></span><a href="#l73"></a>
<span id="l74">    static final HandshakeProducer hrrNetworkProducer =</span><a href="#l74"></a>
<span id="l75">            new HRRKeyShareProducer();</span><a href="#l75"></a>
<span id="l76">    static final ExtensionConsumer hrrOnLoadConsumer =</span><a href="#l76"></a>
<span id="l77">            new HRRKeyShareConsumer();</span><a href="#l77"></a>
<span id="l78">    static final HandshakeProducer hrrNetworkReproducer =</span><a href="#l78"></a>
<span id="l79">            new HRRKeyShareReproducer();</span><a href="#l79"></a>
<span id="l80">    static final SSLStringizer hrrStringizer =</span><a href="#l80"></a>
<span id="l81">            new HRRKeyShareStringizer();</span><a href="#l81"></a>
<span id="l82"></span><a href="#l82"></a>
<span id="l83">    /**</span><a href="#l83"></a>
<span id="l84">     * The key share entry used in &quot;key_share&quot; extensions.</span><a href="#l84"></a>
<span id="l85">     */</span><a href="#l85"></a>
<span id="l86">    private static final class KeyShareEntry {</span><a href="#l86"></a>
<span id="l87">        final int namedGroupId;</span><a href="#l87"></a>
<span id="l88">        final byte[] keyExchange;</span><a href="#l88"></a>
<span id="l89"></span><a href="#l89"></a>
<span id="l90">        private KeyShareEntry(int namedGroupId, byte[] keyExchange) {</span><a href="#l90"></a>
<span id="l91">            this.namedGroupId = namedGroupId;</span><a href="#l91"></a>
<span id="l92">            this.keyExchange = keyExchange;</span><a href="#l92"></a>
<span id="l93">        }</span><a href="#l93"></a>
<span id="l94"></span><a href="#l94"></a>
<span id="l95">        private byte[] getEncoded() {</span><a href="#l95"></a>
<span id="l96">            byte[] buffer = new byte[keyExchange.length + 4];</span><a href="#l96"></a>
<span id="l97">                                            //  2: named group id</span><a href="#l97"></a>
<span id="l98">                                            // +2: key exchange length</span><a href="#l98"></a>
<span id="l99">            ByteBuffer m = ByteBuffer.wrap(buffer);</span><a href="#l99"></a>
<span id="l100">            try {</span><a href="#l100"></a>
<span id="l101">                Record.putInt16(m, namedGroupId);</span><a href="#l101"></a>
<span id="l102">                Record.putBytes16(m, keyExchange);</span><a href="#l102"></a>
<span id="l103">            } catch (IOException ioe) {</span><a href="#l103"></a>
<span id="l104">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l104"></a>
<span id="l105">                    SSLLogger.warning(</span><a href="#l105"></a>
<span id="l106">                        &quot;Unlikely IOException&quot;, ioe);</span><a href="#l106"></a>
<span id="l107">                }</span><a href="#l107"></a>
<span id="l108">            }</span><a href="#l108"></a>
<span id="l109"></span><a href="#l109"></a>
<span id="l110">            return buffer;</span><a href="#l110"></a>
<span id="l111">        }</span><a href="#l111"></a>
<span id="l112"></span><a href="#l112"></a>
<span id="l113">        private int getEncodedSize() {</span><a href="#l113"></a>
<span id="l114">            return keyExchange.length + 4;  //  2: named group id</span><a href="#l114"></a>
<span id="l115">                                            // +2: key exchange length</span><a href="#l115"></a>
<span id="l116">        }</span><a href="#l116"></a>
<span id="l117"></span><a href="#l117"></a>
<span id="l118">        @Override</span><a href="#l118"></a>
<span id="l119">        public String toString() {</span><a href="#l119"></a>
<span id="l120">            MessageFormat messageFormat = new MessageFormat(</span><a href="#l120"></a>
<span id="l121">                &quot;\n'{'\n&quot; +</span><a href="#l121"></a>
<span id="l122">                &quot;  \&quot;named group\&quot;: {0}\n&quot; +</span><a href="#l122"></a>
<span id="l123">                &quot;  \&quot;key_exchange\&quot;: '{'\n&quot; +</span><a href="#l123"></a>
<span id="l124">                &quot;{1}\n&quot; +</span><a href="#l124"></a>
<span id="l125">                &quot;  '}'\n&quot; +</span><a href="#l125"></a>
<span id="l126">                &quot;'}',&quot;, Locale.ENGLISH);</span><a href="#l126"></a>
<span id="l127"></span><a href="#l127"></a>
<span id="l128">            HexDumpEncoder hexEncoder = new HexDumpEncoder();</span><a href="#l128"></a>
<span id="l129">            Object[] messageFields = {</span><a href="#l129"></a>
<span id="l130">                NamedGroup.nameOf(namedGroupId),</span><a href="#l130"></a>
<span id="l131">                Utilities.indent(hexEncoder.encode(keyExchange), &quot;    &quot;)</span><a href="#l131"></a>
<span id="l132">            };</span><a href="#l132"></a>
<span id="l133"></span><a href="#l133"></a>
<span id="l134">            return messageFormat.format(messageFields);</span><a href="#l134"></a>
<span id="l135">        }</span><a href="#l135"></a>
<span id="l136">    }</span><a href="#l136"></a>
<span id="l137"></span><a href="#l137"></a>
<span id="l138">    /**</span><a href="#l138"></a>
<span id="l139">     * The &quot;key_share&quot; extension in a ClientHello handshake message.</span><a href="#l139"></a>
<span id="l140">     */</span><a href="#l140"></a>
<span id="l141">    static final class CHKeyShareSpec implements SSLExtensionSpec {</span><a href="#l141"></a>
<span id="l142">        final List&lt;KeyShareEntry&gt; clientShares;</span><a href="#l142"></a>
<span id="l143"></span><a href="#l143"></a>
<span id="l144">        private CHKeyShareSpec(List&lt;KeyShareEntry&gt; clientShares) {</span><a href="#l144"></a>
<span id="l145">            this.clientShares = clientShares;</span><a href="#l145"></a>
<span id="l146">        }</span><a href="#l146"></a>
<span id="l147"></span><a href="#l147"></a>
<span id="l148">        private CHKeyShareSpec(ByteBuffer buffer) throws IOException {</span><a href="#l148"></a>
<span id="l149">            // struct {</span><a href="#l149"></a>
<span id="l150">            //      KeyShareEntry client_shares&lt;0..2^16-1&gt;;</span><a href="#l150"></a>
<span id="l151">            // } KeyShareClientHello;</span><a href="#l151"></a>
<span id="l152">            if (buffer.remaining() &lt; 2) {</span><a href="#l152"></a>
<span id="l153">                throw new SSLProtocolException(</span><a href="#l153"></a>
<span id="l154">                    &quot;Invalid key_share extension: &quot; +</span><a href="#l154"></a>
<span id="l155">                    &quot;insufficient data (length=&quot; + buffer.remaining() + &quot;)&quot;);</span><a href="#l155"></a>
<span id="l156">            }</span><a href="#l156"></a>
<span id="l157"></span><a href="#l157"></a>
<span id="l158">            int listLen = Record.getInt16(buffer);</span><a href="#l158"></a>
<span id="l159">            if (listLen != buffer.remaining()) {</span><a href="#l159"></a>
<span id="l160">                throw new SSLProtocolException(</span><a href="#l160"></a>
<span id="l161">                    &quot;Invalid key_share extension: &quot; +</span><a href="#l161"></a>
<span id="l162">                    &quot;incorrect list length (length=&quot; + listLen + &quot;)&quot;);</span><a href="#l162"></a>
<span id="l163">            }</span><a href="#l163"></a>
<span id="l164"></span><a href="#l164"></a>
<span id="l165">            List&lt;KeyShareEntry&gt; keyShares = new LinkedList&lt;&gt;();</span><a href="#l165"></a>
<span id="l166">            while (buffer.hasRemaining()) {</span><a href="#l166"></a>
<span id="l167">                int namedGroupId = Record.getInt16(buffer);</span><a href="#l167"></a>
<span id="l168">                byte[] keyExchange = Record.getBytes16(buffer);</span><a href="#l168"></a>
<span id="l169">                if (keyExchange.length == 0) {</span><a href="#l169"></a>
<span id="l170">                    throw new SSLProtocolException(</span><a href="#l170"></a>
<span id="l171">                        &quot;Invalid key_share extension: empty key_exchange&quot;);</span><a href="#l171"></a>
<span id="l172">                }</span><a href="#l172"></a>
<span id="l173"></span><a href="#l173"></a>
<span id="l174">                keyShares.add(new KeyShareEntry(namedGroupId, keyExchange));</span><a href="#l174"></a>
<span id="l175">            }</span><a href="#l175"></a>
<span id="l176"></span><a href="#l176"></a>
<span id="l177">            this.clientShares = Collections.unmodifiableList(keyShares);</span><a href="#l177"></a>
<span id="l178">        }</span><a href="#l178"></a>
<span id="l179"></span><a href="#l179"></a>
<span id="l180">        @Override</span><a href="#l180"></a>
<span id="l181">        public String toString() {</span><a href="#l181"></a>
<span id="l182">            MessageFormat messageFormat = new MessageFormat(</span><a href="#l182"></a>
<span id="l183">                &quot;\&quot;client_shares\&quot;: '['{0}\n']'&quot;, Locale.ENGLISH);</span><a href="#l183"></a>
<span id="l184"></span><a href="#l184"></a>
<span id="l185">            StringBuilder builder = new StringBuilder(512);</span><a href="#l185"></a>
<span id="l186">            for (KeyShareEntry entry : clientShares) {</span><a href="#l186"></a>
<span id="l187">                builder.append(entry.toString());</span><a href="#l187"></a>
<span id="l188">            }</span><a href="#l188"></a>
<span id="l189"></span><a href="#l189"></a>
<span id="l190">            Object[] messageFields = {</span><a href="#l190"></a>
<span id="l191">                Utilities.indent(builder.toString())</span><a href="#l191"></a>
<span id="l192">            };</span><a href="#l192"></a>
<span id="l193"></span><a href="#l193"></a>
<span id="l194">            return messageFormat.format(messageFields);</span><a href="#l194"></a>
<span id="l195">        }</span><a href="#l195"></a>
<span id="l196">    }</span><a href="#l196"></a>
<span id="l197"></span><a href="#l197"></a>
<span id="l198">    private static final class CHKeyShareStringizer implements SSLStringizer {</span><a href="#l198"></a>
<span id="l199">        @Override</span><a href="#l199"></a>
<span id="l200">        public String toString(ByteBuffer buffer) {</span><a href="#l200"></a>
<span id="l201">            try {</span><a href="#l201"></a>
<span id="l202">                return (new CHKeyShareSpec(buffer)).toString();</span><a href="#l202"></a>
<span id="l203">            } catch (IOException ioe) {</span><a href="#l203"></a>
<span id="l204">                // For debug logging only, so please swallow exceptions.</span><a href="#l204"></a>
<span id="l205">                return ioe.getMessage();</span><a href="#l205"></a>
<span id="l206">            }</span><a href="#l206"></a>
<span id="l207">        }</span><a href="#l207"></a>
<span id="l208">    }</span><a href="#l208"></a>
<span id="l209"></span><a href="#l209"></a>
<span id="l210">    /**</span><a href="#l210"></a>
<span id="l211">     * Network data producer of the extension in a ClientHello</span><a href="#l211"></a>
<span id="l212">     * handshake message.</span><a href="#l212"></a>
<span id="l213">     */</span><a href="#l213"></a>
<span id="l214">    private static final</span><a href="#l214"></a>
<span id="l215">            class CHKeyShareProducer implements HandshakeProducer {</span><a href="#l215"></a>
<span id="l216">        // Prevent instantiation of this class.</span><a href="#l216"></a>
<span id="l217">        private CHKeyShareProducer() {</span><a href="#l217"></a>
<span id="l218">            // blank</span><a href="#l218"></a>
<span id="l219">        }</span><a href="#l219"></a>
<span id="l220"></span><a href="#l220"></a>
<span id="l221">        @Override</span><a href="#l221"></a>
<span id="l222">        public byte[] produce(ConnectionContext context,</span><a href="#l222"></a>
<span id="l223">                HandshakeMessage message) throws IOException {</span><a href="#l223"></a>
<span id="l224">            // The producing happens in client side only.</span><a href="#l224"></a>
<span id="l225">            ClientHandshakeContext chc = (ClientHandshakeContext)context;</span><a href="#l225"></a>
<span id="l226"></span><a href="#l226"></a>
<span id="l227">            // Is it a supported and enabled extension?</span><a href="#l227"></a>
<span id="l228">            if (!chc.sslConfig.isAvailable(SSLExtension.CH_KEY_SHARE)) {</span><a href="#l228"></a>
<span id="l229">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l229"></a>
<span id="l230">                    SSLLogger.fine(</span><a href="#l230"></a>
<span id="l231">                        &quot;Ignore unavailable key_share extension&quot;);</span><a href="#l231"></a>
<span id="l232">                }</span><a href="#l232"></a>
<span id="l233">                return null;</span><a href="#l233"></a>
<span id="l234">            }</span><a href="#l234"></a>
<span id="l235"></span><a href="#l235"></a>
<span id="l236">            List&lt;NamedGroup&gt; namedGroups;</span><a href="#l236"></a>
<span id="l237">            if (chc.serverSelectedNamedGroup != null) {</span><a href="#l237"></a>
<span id="l238">                // Response to HelloRetryRequest</span><a href="#l238"></a>
<span id="l239">                namedGroups = Arrays.asList(chc.serverSelectedNamedGroup);</span><a href="#l239"></a>
<span id="l240">            } else {</span><a href="#l240"></a>
<span id="l241">                namedGroups = chc.clientRequestedNamedGroups;</span><a href="#l241"></a>
<span id="l242">                if (namedGroups == null || namedGroups.isEmpty()) {</span><a href="#l242"></a>
<span id="l243">                    // No supported groups.</span><a href="#l243"></a>
<span id="l244">                    if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l244"></a>
<span id="l245">                        SSLLogger.warning(</span><a href="#l245"></a>
<span id="l246">                            &quot;Ignore key_share extension, no supported groups&quot;);</span><a href="#l246"></a>
<span id="l247">                    }</span><a href="#l247"></a>
<span id="l248">                    return null;</span><a href="#l248"></a>
<span id="l249">                }</span><a href="#l249"></a>
<span id="l250">            }</span><a href="#l250"></a>
<span id="l251"></span><a href="#l251"></a>
<span id="l252">            List&lt;KeyShareEntry&gt; keyShares = new LinkedList&lt;&gt;();</span><a href="#l252"></a>
<span id="l253">            for (NamedGroup ng : namedGroups) {</span><a href="#l253"></a>
<span id="l254">                SSLKeyExchange ke = SSLKeyExchange.valueOf(ng);</span><a href="#l254"></a>
<span id="l255">                if (ke == null) {</span><a href="#l255"></a>
<span id="l256">                    if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l256"></a>
<span id="l257">                        SSLLogger.warning(</span><a href="#l257"></a>
<span id="l258">                            &quot;No key exchange for named group &quot; + ng.name);</span><a href="#l258"></a>
<span id="l259">                    }</span><a href="#l259"></a>
<span id="l260">                    continue;</span><a href="#l260"></a>
<span id="l261">                }</span><a href="#l261"></a>
<span id="l262"></span><a href="#l262"></a>
<span id="l263">                SSLPossession[] poses = ke.createPossessions(chc);</span><a href="#l263"></a>
<span id="l264">                for (SSLPossession pos : poses) {</span><a href="#l264"></a>
<span id="l265">                    // update the context</span><a href="#l265"></a>
<span id="l266">                    chc.handshakePossessions.add(pos);</span><a href="#l266"></a>
<span id="l267">                    if (!(pos instanceof ECDHEPossession) &amp;&amp;</span><a href="#l267"></a>
<span id="l268">                            !(pos instanceof DHEPossession)) {</span><a href="#l268"></a>
<span id="l269">                        // May need more possesion types in the future.</span><a href="#l269"></a>
<span id="l270">                        continue;</span><a href="#l270"></a>
<span id="l271">                    }</span><a href="#l271"></a>
<span id="l272"></span><a href="#l272"></a>
<span id="l273">                    keyShares.add(new KeyShareEntry(ng.id, pos.encode()));</span><a href="#l273"></a>
<span id="l274">                }</span><a href="#l274"></a>
<span id="l275"></span><a href="#l275"></a>
<span id="l276">                // One key share entry only.  Too much key share entries makes</span><a href="#l276"></a>
<span id="l277">                // the ClientHello handshake message really big.</span><a href="#l277"></a>
<span id="l278">                if (!keyShares.isEmpty()) {</span><a href="#l278"></a>
<span id="l279">                    break;</span><a href="#l279"></a>
<span id="l280">                }</span><a href="#l280"></a>
<span id="l281">            }</span><a href="#l281"></a>
<span id="l282"></span><a href="#l282"></a>
<span id="l283">            int listLen = 0;</span><a href="#l283"></a>
<span id="l284">            for (KeyShareEntry entry : keyShares) {</span><a href="#l284"></a>
<span id="l285">                listLen += entry.getEncodedSize();</span><a href="#l285"></a>
<span id="l286">            }</span><a href="#l286"></a>
<span id="l287">            byte[] extData = new byte[listLen + 2];     //  2: list length</span><a href="#l287"></a>
<span id="l288">            ByteBuffer m = ByteBuffer.wrap(extData);</span><a href="#l288"></a>
<span id="l289">            Record.putInt16(m, listLen);</span><a href="#l289"></a>
<span id="l290">            for (KeyShareEntry entry : keyShares) {</span><a href="#l290"></a>
<span id="l291">                m.put(entry.getEncoded());</span><a href="#l291"></a>
<span id="l292">            }</span><a href="#l292"></a>
<span id="l293"></span><a href="#l293"></a>
<span id="l294">            // update the context</span><a href="#l294"></a>
<span id="l295">            chc.handshakeExtensions.put(SSLExtension.CH_KEY_SHARE,</span><a href="#l295"></a>
<span id="l296">                    new CHKeyShareSpec(keyShares));</span><a href="#l296"></a>
<span id="l297"></span><a href="#l297"></a>
<span id="l298">            return extData;</span><a href="#l298"></a>
<span id="l299">        }</span><a href="#l299"></a>
<span id="l300">    }</span><a href="#l300"></a>
<span id="l301"></span><a href="#l301"></a>
<span id="l302">    /**</span><a href="#l302"></a>
<span id="l303">     * Network data consumer of the extension in a ClientHello</span><a href="#l303"></a>
<span id="l304">     * handshake message.</span><a href="#l304"></a>
<span id="l305">     */</span><a href="#l305"></a>
<span id="l306">    private static final class CHKeyShareConsumer implements ExtensionConsumer {</span><a href="#l306"></a>
<span id="l307">        // Prevent instantiation of this class.</span><a href="#l307"></a>
<span id="l308">        private CHKeyShareConsumer() {</span><a href="#l308"></a>
<span id="l309">            // blank</span><a href="#l309"></a>
<span id="l310">        }</span><a href="#l310"></a>
<span id="l311"></span><a href="#l311"></a>
<span id="l312">        @Override</span><a href="#l312"></a>
<span id="l313">        public void consume(ConnectionContext context,</span><a href="#l313"></a>
<span id="l314">            HandshakeMessage message, ByteBuffer buffer) throws IOException {</span><a href="#l314"></a>
<span id="l315">            // The consuming happens in server side only.</span><a href="#l315"></a>
<span id="l316">            ServerHandshakeContext shc = (ServerHandshakeContext)context;</span><a href="#l316"></a>
<span id="l317"></span><a href="#l317"></a>
<span id="l318">            if (shc.handshakeExtensions.containsKey(SSLExtension.CH_KEY_SHARE)) {</span><a href="#l318"></a>
<span id="l319">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l319"></a>
<span id="l320">                    SSLLogger.fine(</span><a href="#l320"></a>
<span id="l321">                            &quot;The key_share extension has been loaded&quot;);</span><a href="#l321"></a>
<span id="l322">                }</span><a href="#l322"></a>
<span id="l323">                return;</span><a href="#l323"></a>
<span id="l324">            }</span><a href="#l324"></a>
<span id="l325"></span><a href="#l325"></a>
<span id="l326">            // Is it a supported and enabled extension?</span><a href="#l326"></a>
<span id="l327">            if (!shc.sslConfig.isAvailable(SSLExtension.CH_KEY_SHARE)) {</span><a href="#l327"></a>
<span id="l328">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l328"></a>
<span id="l329">                    SSLLogger.fine(</span><a href="#l329"></a>
<span id="l330">                            &quot;Ignore unavailable key_share extension&quot;);</span><a href="#l330"></a>
<span id="l331">                }</span><a href="#l331"></a>
<span id="l332">                return;     // ignore the extension</span><a href="#l332"></a>
<span id="l333">            }</span><a href="#l333"></a>
<span id="l334"></span><a href="#l334"></a>
<span id="l335">            // Parse the extension</span><a href="#l335"></a>
<span id="l336">            CHKeyShareSpec spec;</span><a href="#l336"></a>
<span id="l337">            try {</span><a href="#l337"></a>
<span id="l338">                spec = new CHKeyShareSpec(buffer);</span><a href="#l338"></a>
<span id="l339">            } catch (IOException ioe) {</span><a href="#l339"></a>
<span id="l340">                throw shc.conContext.fatal(Alert.UNEXPECTED_MESSAGE, ioe);</span><a href="#l340"></a>
<span id="l341">            }</span><a href="#l341"></a>
<span id="l342"></span><a href="#l342"></a>
<span id="l343">            List&lt;SSLCredentials&gt; credentials = new LinkedList&lt;&gt;();</span><a href="#l343"></a>
<span id="l344">            for (KeyShareEntry entry : spec.clientShares) {</span><a href="#l344"></a>
<span id="l345">                NamedGroup ng = NamedGroup.valueOf(entry.namedGroupId);</span><a href="#l345"></a>
<span id="l346">                if (ng == null || !SupportedGroups.isActivatable(</span><a href="#l346"></a>
<span id="l347">                        shc.algorithmConstraints, ng)) {</span><a href="#l347"></a>
<span id="l348">                    if (SSLLogger.isOn &amp;&amp;</span><a href="#l348"></a>
<span id="l349">                            SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l349"></a>
<span id="l350">                        SSLLogger.fine(</span><a href="#l350"></a>
<span id="l351">                                &quot;Ignore unsupported named group: &quot; +</span><a href="#l351"></a>
<span id="l352">                                NamedGroup.nameOf(entry.namedGroupId));</span><a href="#l352"></a>
<span id="l353">                    }</span><a href="#l353"></a>
<span id="l354">                    continue;</span><a href="#l354"></a>
<span id="l355">                }</span><a href="#l355"></a>
<span id="l356"></span><a href="#l356"></a>
<span id="l357">                if (ng.type == NamedGroupType.NAMED_GROUP_ECDHE) {</span><a href="#l357"></a>
<span id="l358">                    try {</span><a href="#l358"></a>
<span id="l359">                        ECDHECredentials ecdhec =</span><a href="#l359"></a>
<span id="l360">                            ECDHECredentials.valueOf(ng, entry.keyExchange);</span><a href="#l360"></a>
<span id="l361">                        if (ecdhec != null) {</span><a href="#l361"></a>
<span id="l362">                            if (shc.algorithmConstraints != null &amp;&amp;</span><a href="#l362"></a>
<span id="l363">                                    !shc.algorithmConstraints.permits(</span><a href="#l363"></a>
<span id="l364">                                            EnumSet.of(CryptoPrimitive.KEY_AGREEMENT),</span><a href="#l364"></a>
<span id="l365">                                            ecdhec.popPublicKey)) {</span><a href="#l365"></a>
<span id="l366">                                if (SSLLogger.isOn &amp;&amp;</span><a href="#l366"></a>
<span id="l367">                                        SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l367"></a>
<span id="l368">                                    SSLLogger.warning(</span><a href="#l368"></a>
<span id="l369">                                            &quot;ECDHE key share entry does not &quot; +</span><a href="#l369"></a>
<span id="l370">                                            &quot;comply to algorithm constraints&quot;);</span><a href="#l370"></a>
<span id="l371">                                }</span><a href="#l371"></a>
<span id="l372">                            } else {</span><a href="#l372"></a>
<span id="l373">                                credentials.add(ecdhec);</span><a href="#l373"></a>
<span id="l374">                            }</span><a href="#l374"></a>
<span id="l375">                        }</span><a href="#l375"></a>
<span id="l376">                    } catch (IOException | GeneralSecurityException ex) {</span><a href="#l376"></a>
<span id="l377">                        if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l377"></a>
<span id="l378">                            SSLLogger.warning(</span><a href="#l378"></a>
<span id="l379">                                    &quot;Cannot decode named group: &quot; +</span><a href="#l379"></a>
<span id="l380">                                    NamedGroup.nameOf(entry.namedGroupId));</span><a href="#l380"></a>
<span id="l381">                        }</span><a href="#l381"></a>
<span id="l382">                    }</span><a href="#l382"></a>
<span id="l383">                } else if (ng.type == NamedGroupType.NAMED_GROUP_FFDHE) {</span><a href="#l383"></a>
<span id="l384">                    try {</span><a href="#l384"></a>
<span id="l385">                        DHECredentials dhec =</span><a href="#l385"></a>
<span id="l386">                                DHECredentials.valueOf(ng, entry.keyExchange);</span><a href="#l386"></a>
<span id="l387">                        if (dhec != null) {</span><a href="#l387"></a>
<span id="l388">                            if (shc.algorithmConstraints != null &amp;&amp;</span><a href="#l388"></a>
<span id="l389">                                    !shc.algorithmConstraints.permits(</span><a href="#l389"></a>
<span id="l390">                                            EnumSet.of(CryptoPrimitive.KEY_AGREEMENT),</span><a href="#l390"></a>
<span id="l391">                                            dhec.popPublicKey)) {</span><a href="#l391"></a>
<span id="l392">                                if (SSLLogger.isOn &amp;&amp;</span><a href="#l392"></a>
<span id="l393">                                        SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l393"></a>
<span id="l394">                                    SSLLogger.warning(</span><a href="#l394"></a>
<span id="l395">                                            &quot;DHE key share entry does not &quot; +</span><a href="#l395"></a>
<span id="l396">                                            &quot;comply to algorithm constraints&quot;);</span><a href="#l396"></a>
<span id="l397">                                }</span><a href="#l397"></a>
<span id="l398">                            } else {</span><a href="#l398"></a>
<span id="l399">                                credentials.add(dhec);</span><a href="#l399"></a>
<span id="l400">                            }</span><a href="#l400"></a>
<span id="l401">                        }</span><a href="#l401"></a>
<span id="l402">                    } catch (IOException | GeneralSecurityException ex) {</span><a href="#l402"></a>
<span id="l403">                        if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l403"></a>
<span id="l404">                            SSLLogger.warning(</span><a href="#l404"></a>
<span id="l405">                                    &quot;Cannot decode named group: &quot; +</span><a href="#l405"></a>
<span id="l406">                                    NamedGroup.nameOf(entry.namedGroupId));</span><a href="#l406"></a>
<span id="l407">                        }</span><a href="#l407"></a>
<span id="l408">                    }</span><a href="#l408"></a>
<span id="l409">                }</span><a href="#l409"></a>
<span id="l410">            }</span><a href="#l410"></a>
<span id="l411"></span><a href="#l411"></a>
<span id="l412">            if (!credentials.isEmpty()) {</span><a href="#l412"></a>
<span id="l413">                shc.handshakeCredentials.addAll(credentials);</span><a href="#l413"></a>
<span id="l414">            } else {</span><a href="#l414"></a>
<span id="l415">                // New handshake credentials are required from the client side.</span><a href="#l415"></a>
<span id="l416">                shc.handshakeProducers.put(</span><a href="#l416"></a>
<span id="l417">                        SSLHandshake.HELLO_RETRY_REQUEST.id,</span><a href="#l417"></a>
<span id="l418">                        SSLHandshake.HELLO_RETRY_REQUEST);</span><a href="#l418"></a>
<span id="l419">            }</span><a href="#l419"></a>
<span id="l420"></span><a href="#l420"></a>
<span id="l421">            // update the context</span><a href="#l421"></a>
<span id="l422">            shc.handshakeExtensions.put(SSLExtension.CH_KEY_SHARE, spec);</span><a href="#l422"></a>
<span id="l423">        }</span><a href="#l423"></a>
<span id="l424">    }</span><a href="#l424"></a>
<span id="l425"></span><a href="#l425"></a>
<span id="l426">    /**</span><a href="#l426"></a>
<span id="l427">     * The key share entry used in ServerHello &quot;key_share&quot; extensions.</span><a href="#l427"></a>
<span id="l428">     */</span><a href="#l428"></a>
<span id="l429">    static final class SHKeyShareSpec implements SSLExtensionSpec {</span><a href="#l429"></a>
<span id="l430">        final KeyShareEntry serverShare;</span><a href="#l430"></a>
<span id="l431"></span><a href="#l431"></a>
<span id="l432">        SHKeyShareSpec(KeyShareEntry serverShare) {</span><a href="#l432"></a>
<span id="l433">            this.serverShare = serverShare;</span><a href="#l433"></a>
<span id="l434">        }</span><a href="#l434"></a>
<span id="l435"></span><a href="#l435"></a>
<span id="l436">        private SHKeyShareSpec(ByteBuffer buffer) throws IOException {</span><a href="#l436"></a>
<span id="l437">            // struct {</span><a href="#l437"></a>
<span id="l438">            //      KeyShareEntry server_share;</span><a href="#l438"></a>
<span id="l439">            // } KeyShareServerHello;</span><a href="#l439"></a>
<span id="l440">            if (buffer.remaining() &lt; 5) {       // 5: minimal server_share</span><a href="#l440"></a>
<span id="l441">                throw new SSLProtocolException(</span><a href="#l441"></a>
<span id="l442">                    &quot;Invalid key_share extension: &quot; +</span><a href="#l442"></a>
<span id="l443">                    &quot;insufficient data (length=&quot; + buffer.remaining() + &quot;)&quot;);</span><a href="#l443"></a>
<span id="l444">            }</span><a href="#l444"></a>
<span id="l445"></span><a href="#l445"></a>
<span id="l446">            int namedGroupId = Record.getInt16(buffer);</span><a href="#l446"></a>
<span id="l447">            byte[] keyExchange = Record.getBytes16(buffer);</span><a href="#l447"></a>
<span id="l448"></span><a href="#l448"></a>
<span id="l449">            if (buffer.hasRemaining()) {</span><a href="#l449"></a>
<span id="l450">                throw new SSLProtocolException(</span><a href="#l450"></a>
<span id="l451">                    &quot;Invalid key_share extension: unknown extra data&quot;);</span><a href="#l451"></a>
<span id="l452">            }</span><a href="#l452"></a>
<span id="l453"></span><a href="#l453"></a>
<span id="l454">            this.serverShare = new KeyShareEntry(namedGroupId, keyExchange);</span><a href="#l454"></a>
<span id="l455">        }</span><a href="#l455"></a>
<span id="l456"></span><a href="#l456"></a>
<span id="l457">        @Override</span><a href="#l457"></a>
<span id="l458">        public String toString() {</span><a href="#l458"></a>
<span id="l459">            MessageFormat messageFormat = new MessageFormat(</span><a href="#l459"></a>
<span id="l460">                &quot;\&quot;server_share\&quot;: '{'\n&quot; +</span><a href="#l460"></a>
<span id="l461">                &quot;  \&quot;named group\&quot;: {0}\n&quot; +</span><a href="#l461"></a>
<span id="l462">                &quot;  \&quot;key_exchange\&quot;: '{'\n&quot; +</span><a href="#l462"></a>
<span id="l463">                &quot;{1}\n&quot; +</span><a href="#l463"></a>
<span id="l464">                &quot;  '}'\n&quot; +</span><a href="#l464"></a>
<span id="l465">                &quot;'}',&quot;, Locale.ENGLISH);</span><a href="#l465"></a>
<span id="l466"></span><a href="#l466"></a>
<span id="l467">            HexDumpEncoder hexEncoder = new HexDumpEncoder();</span><a href="#l467"></a>
<span id="l468">            Object[] messageFields = {</span><a href="#l468"></a>
<span id="l469">                NamedGroup.nameOf(serverShare.namedGroupId),</span><a href="#l469"></a>
<span id="l470">                Utilities.indent(</span><a href="#l470"></a>
<span id="l471">                        hexEncoder.encode(serverShare.keyExchange), &quot;    &quot;)</span><a href="#l471"></a>
<span id="l472">            };</span><a href="#l472"></a>
<span id="l473"></span><a href="#l473"></a>
<span id="l474">            return messageFormat.format(messageFields);</span><a href="#l474"></a>
<span id="l475">        }</span><a href="#l475"></a>
<span id="l476">    }</span><a href="#l476"></a>
<span id="l477"></span><a href="#l477"></a>
<span id="l478">    private static final class SHKeyShareStringizer implements SSLStringizer {</span><a href="#l478"></a>
<span id="l479">        @Override</span><a href="#l479"></a>
<span id="l480">        public String toString(ByteBuffer buffer) {</span><a href="#l480"></a>
<span id="l481">            try {</span><a href="#l481"></a>
<span id="l482">                return (new SHKeyShareSpec(buffer)).toString();</span><a href="#l482"></a>
<span id="l483">            } catch (IOException ioe) {</span><a href="#l483"></a>
<span id="l484">                // For debug logging only, so please swallow exceptions.</span><a href="#l484"></a>
<span id="l485">                return ioe.getMessage();</span><a href="#l485"></a>
<span id="l486">            }</span><a href="#l486"></a>
<span id="l487">        }</span><a href="#l487"></a>
<span id="l488">    }</span><a href="#l488"></a>
<span id="l489"></span><a href="#l489"></a>
<span id="l490">    /**</span><a href="#l490"></a>
<span id="l491">     * Network data producer of the extension in a ServerHello</span><a href="#l491"></a>
<span id="l492">     * handshake message.</span><a href="#l492"></a>
<span id="l493">     */</span><a href="#l493"></a>
<span id="l494">    private static final class SHKeyShareProducer implements HandshakeProducer {</span><a href="#l494"></a>
<span id="l495">        // Prevent instantiation of this class.</span><a href="#l495"></a>
<span id="l496">        private SHKeyShareProducer() {</span><a href="#l496"></a>
<span id="l497">            // blank</span><a href="#l497"></a>
<span id="l498">        }</span><a href="#l498"></a>
<span id="l499"></span><a href="#l499"></a>
<span id="l500">        @Override</span><a href="#l500"></a>
<span id="l501">        public byte[] produce(ConnectionContext context,</span><a href="#l501"></a>
<span id="l502">                HandshakeMessage message) throws IOException {</span><a href="#l502"></a>
<span id="l503">            // The producing happens in client side only.</span><a href="#l503"></a>
<span id="l504">            ServerHandshakeContext shc = (ServerHandshakeContext)context;</span><a href="#l504"></a>
<span id="l505"></span><a href="#l505"></a>
<span id="l506">            // In response to key_share request only</span><a href="#l506"></a>
<span id="l507">            CHKeyShareSpec kss =</span><a href="#l507"></a>
<span id="l508">                    (CHKeyShareSpec)shc.handshakeExtensions.get(</span><a href="#l508"></a>
<span id="l509">                            SSLExtension.CH_KEY_SHARE);</span><a href="#l509"></a>
<span id="l510">            if (kss == null) {</span><a href="#l510"></a>
<span id="l511">                // Unlikely, no key_share extension requested.</span><a href="#l511"></a>
<span id="l512">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l512"></a>
<span id="l513">                    SSLLogger.warning(</span><a href="#l513"></a>
<span id="l514">                            &quot;Ignore, no client key_share extension&quot;);</span><a href="#l514"></a>
<span id="l515">                }</span><a href="#l515"></a>
<span id="l516">                return null;</span><a href="#l516"></a>
<span id="l517">            }</span><a href="#l517"></a>
<span id="l518"></span><a href="#l518"></a>
<span id="l519">            // Is it a supported and enabled extension?</span><a href="#l519"></a>
<span id="l520">            if (!shc.sslConfig.isAvailable(SSLExtension.SH_KEY_SHARE)) {</span><a href="#l520"></a>
<span id="l521">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l521"></a>
<span id="l522">                    SSLLogger.warning(</span><a href="#l522"></a>
<span id="l523">                            &quot;Ignore, no available server key_share extension&quot;);</span><a href="#l523"></a>
<span id="l524">                }</span><a href="#l524"></a>
<span id="l525">                return null;</span><a href="#l525"></a>
<span id="l526">            }</span><a href="#l526"></a>
<span id="l527"></span><a href="#l527"></a>
<span id="l528">            // use requested key share entries</span><a href="#l528"></a>
<span id="l529">            if ((shc.handshakeCredentials == null) ||</span><a href="#l529"></a>
<span id="l530">                    shc.handshakeCredentials.isEmpty()) {</span><a href="#l530"></a>
<span id="l531">                // Unlikely, HelloRetryRequest should be used ealier.</span><a href="#l531"></a>
<span id="l532">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l532"></a>
<span id="l533">                    SSLLogger.warning(</span><a href="#l533"></a>
<span id="l534">                            &quot;No available client key share entries&quot;);</span><a href="#l534"></a>
<span id="l535">                }</span><a href="#l535"></a>
<span id="l536">                return null;</span><a href="#l536"></a>
<span id="l537">            }</span><a href="#l537"></a>
<span id="l538"></span><a href="#l538"></a>
<span id="l539">            KeyShareEntry keyShare = null;</span><a href="#l539"></a>
<span id="l540">            for (SSLCredentials cd : shc.handshakeCredentials) {</span><a href="#l540"></a>
<span id="l541">                NamedGroup ng = null;</span><a href="#l541"></a>
<span id="l542">                if (cd instanceof ECDHECredentials) {</span><a href="#l542"></a>
<span id="l543">                    ng = ((ECDHECredentials)cd).namedGroup;</span><a href="#l543"></a>
<span id="l544">                } else if (cd instanceof DHECredentials) {</span><a href="#l544"></a>
<span id="l545">                    ng = ((DHECredentials)cd).namedGroup;</span><a href="#l545"></a>
<span id="l546">                }</span><a href="#l546"></a>
<span id="l547"></span><a href="#l547"></a>
<span id="l548">                if (ng == null) {</span><a href="#l548"></a>
<span id="l549">                    continue;</span><a href="#l549"></a>
<span id="l550">                }</span><a href="#l550"></a>
<span id="l551"></span><a href="#l551"></a>
<span id="l552">                SSLKeyExchange ke = SSLKeyExchange.valueOf(ng);</span><a href="#l552"></a>
<span id="l553">                if (ke == null) {</span><a href="#l553"></a>
<span id="l554">                    if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l554"></a>
<span id="l555">                        SSLLogger.warning(</span><a href="#l555"></a>
<span id="l556">                            &quot;No key exchange for named group &quot; + ng.name);</span><a href="#l556"></a>
<span id="l557">                    }</span><a href="#l557"></a>
<span id="l558">                    continue;</span><a href="#l558"></a>
<span id="l559">                }</span><a href="#l559"></a>
<span id="l560"></span><a href="#l560"></a>
<span id="l561">                SSLPossession[] poses = ke.createPossessions(shc);</span><a href="#l561"></a>
<span id="l562">                for (SSLPossession pos : poses) {</span><a href="#l562"></a>
<span id="l563">                    if (!(pos instanceof ECDHEPossession) &amp;&amp;</span><a href="#l563"></a>
<span id="l564">                            !(pos instanceof DHEPossession)) {</span><a href="#l564"></a>
<span id="l565">                        // May need more possesion types in the future.</span><a href="#l565"></a>
<span id="l566">                        continue;</span><a href="#l566"></a>
<span id="l567">                    }</span><a href="#l567"></a>
<span id="l568"></span><a href="#l568"></a>
<span id="l569">                    // update the context</span><a href="#l569"></a>
<span id="l570">                    shc.handshakeKeyExchange = ke;</span><a href="#l570"></a>
<span id="l571">                    shc.handshakePossessions.add(pos);</span><a href="#l571"></a>
<span id="l572">                    keyShare = new KeyShareEntry(ng.id, pos.encode());</span><a href="#l572"></a>
<span id="l573">                    break;</span><a href="#l573"></a>
<span id="l574">                }</span><a href="#l574"></a>
<span id="l575"></span><a href="#l575"></a>
<span id="l576">                if (keyShare != null) {</span><a href="#l576"></a>
<span id="l577">                    for (Map.Entry&lt;Byte, HandshakeProducer&gt; me :</span><a href="#l577"></a>
<span id="l578">                            ke.getHandshakeProducers(shc)) {</span><a href="#l578"></a>
<span id="l579">                        shc.handshakeProducers.put(</span><a href="#l579"></a>
<span id="l580">                                me.getKey(), me.getValue());</span><a href="#l580"></a>
<span id="l581">                    }</span><a href="#l581"></a>
<span id="l582"></span><a href="#l582"></a>
<span id="l583">                    // We have got one! Don't forgor to break.</span><a href="#l583"></a>
<span id="l584">                    break;</span><a href="#l584"></a>
<span id="l585">                }</span><a href="#l585"></a>
<span id="l586">            }</span><a href="#l586"></a>
<span id="l587"></span><a href="#l587"></a>
<span id="l588">            if (keyShare == null) {</span><a href="#l588"></a>
<span id="l589">                // Unlikely, HelloRetryRequest should be used instead ealier.</span><a href="#l589"></a>
<span id="l590">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l590"></a>
<span id="l591">                    SSLLogger.warning(</span><a href="#l591"></a>
<span id="l592">                            &quot;No available server key_share extension&quot;);</span><a href="#l592"></a>
<span id="l593">                }</span><a href="#l593"></a>
<span id="l594">                return null;</span><a href="#l594"></a>
<span id="l595">            }</span><a href="#l595"></a>
<span id="l596"></span><a href="#l596"></a>
<span id="l597">            byte[] extData = keyShare.getEncoded();</span><a href="#l597"></a>
<span id="l598"></span><a href="#l598"></a>
<span id="l599">            // update the context</span><a href="#l599"></a>
<span id="l600">            SHKeyShareSpec spec = new SHKeyShareSpec(keyShare);</span><a href="#l600"></a>
<span id="l601">            shc.handshakeExtensions.put(SSLExtension.SH_KEY_SHARE, spec);</span><a href="#l601"></a>
<span id="l602"></span><a href="#l602"></a>
<span id="l603">            return extData;</span><a href="#l603"></a>
<span id="l604">        }</span><a href="#l604"></a>
<span id="l605">    }</span><a href="#l605"></a>
<span id="l606"></span><a href="#l606"></a>
<span id="l607">    /**</span><a href="#l607"></a>
<span id="l608">     * Network data consumer of the extension in a ServerHello</span><a href="#l608"></a>
<span id="l609">     * handshake message.</span><a href="#l609"></a>
<span id="l610">     */</span><a href="#l610"></a>
<span id="l611">    private static final class SHKeyShareConsumer implements ExtensionConsumer {</span><a href="#l611"></a>
<span id="l612">        // Prevent instantiation of this class.</span><a href="#l612"></a>
<span id="l613">        private SHKeyShareConsumer() {</span><a href="#l613"></a>
<span id="l614">            // blank</span><a href="#l614"></a>
<span id="l615">        }</span><a href="#l615"></a>
<span id="l616"></span><a href="#l616"></a>
<span id="l617">        @Override</span><a href="#l617"></a>
<span id="l618">        public void consume(ConnectionContext context,</span><a href="#l618"></a>
<span id="l619">            HandshakeMessage message, ByteBuffer buffer) throws IOException {</span><a href="#l619"></a>
<span id="l620">            // Happens in client side only.</span><a href="#l620"></a>
<span id="l621">            ClientHandshakeContext chc = (ClientHandshakeContext)context;</span><a href="#l621"></a>
<span id="l622">            if (chc.clientRequestedNamedGroups == null ||</span><a href="#l622"></a>
<span id="l623">                    chc.clientRequestedNamedGroups.isEmpty()) {</span><a href="#l623"></a>
<span id="l624">                // No supported groups.</span><a href="#l624"></a>
<span id="l625">                throw chc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,</span><a href="#l625"></a>
<span id="l626">                        &quot;Unexpected key_share extension in ServerHello&quot;);</span><a href="#l626"></a>
<span id="l627">            }</span><a href="#l627"></a>
<span id="l628"></span><a href="#l628"></a>
<span id="l629">            // Is it a supported and enabled extension?</span><a href="#l629"></a>
<span id="l630">            if (!chc.sslConfig.isAvailable(SSLExtension.SH_KEY_SHARE)) {</span><a href="#l630"></a>
<span id="l631">                throw chc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,</span><a href="#l631"></a>
<span id="l632">                        &quot;Unsupported key_share extension in ServerHello&quot;);</span><a href="#l632"></a>
<span id="l633">            }</span><a href="#l633"></a>
<span id="l634"></span><a href="#l634"></a>
<span id="l635">            // Parse the extension</span><a href="#l635"></a>
<span id="l636">            SHKeyShareSpec spec;</span><a href="#l636"></a>
<span id="l637">            try {</span><a href="#l637"></a>
<span id="l638">                spec = new SHKeyShareSpec(buffer);</span><a href="#l638"></a>
<span id="l639">            } catch (IOException ioe) {</span><a href="#l639"></a>
<span id="l640">                throw chc.conContext.fatal(Alert.UNEXPECTED_MESSAGE, ioe);</span><a href="#l640"></a>
<span id="l641">            }</span><a href="#l641"></a>
<span id="l642"></span><a href="#l642"></a>
<span id="l643">            KeyShareEntry keyShare = spec.serverShare;</span><a href="#l643"></a>
<span id="l644">            NamedGroup ng = NamedGroup.valueOf(keyShare.namedGroupId);</span><a href="#l644"></a>
<span id="l645">            if (ng == null || !SupportedGroups.isActivatable(</span><a href="#l645"></a>
<span id="l646">                    chc.algorithmConstraints, ng)) {</span><a href="#l646"></a>
<span id="l647">                throw chc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,</span><a href="#l647"></a>
<span id="l648">                        &quot;Unsupported named group: &quot; +</span><a href="#l648"></a>
<span id="l649">                        NamedGroup.nameOf(keyShare.namedGroupId));</span><a href="#l649"></a>
<span id="l650">            }</span><a href="#l650"></a>
<span id="l651"></span><a href="#l651"></a>
<span id="l652">            SSLKeyExchange ke = SSLKeyExchange.valueOf(ng);</span><a href="#l652"></a>
<span id="l653">            if (ke == null) {</span><a href="#l653"></a>
<span id="l654">                throw chc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,</span><a href="#l654"></a>
<span id="l655">                        &quot;No key exchange for named group &quot; + ng.name);</span><a href="#l655"></a>
<span id="l656">            }</span><a href="#l656"></a>
<span id="l657"></span><a href="#l657"></a>
<span id="l658">            SSLCredentials credentials = null;</span><a href="#l658"></a>
<span id="l659">            if (ng.type == NamedGroupType.NAMED_GROUP_ECDHE) {</span><a href="#l659"></a>
<span id="l660">                try {</span><a href="#l660"></a>
<span id="l661">                    ECDHECredentials ecdhec =</span><a href="#l661"></a>
<span id="l662">                            ECDHECredentials.valueOf(ng, keyShare.keyExchange);</span><a href="#l662"></a>
<span id="l663">                    if (ecdhec != null) {</span><a href="#l663"></a>
<span id="l664">                        if (chc.algorithmConstraints != null &amp;&amp;</span><a href="#l664"></a>
<span id="l665">                                !chc.algorithmConstraints.permits(</span><a href="#l665"></a>
<span id="l666">                                        EnumSet.of(CryptoPrimitive.KEY_AGREEMENT),</span><a href="#l666"></a>
<span id="l667">                                        ecdhec.popPublicKey)) {</span><a href="#l667"></a>
<span id="l668">                            throw chc.conContext.fatal(Alert.INSUFFICIENT_SECURITY,</span><a href="#l668"></a>
<span id="l669">                                    &quot;ECDHE key share entry does not &quot; +</span><a href="#l669"></a>
<span id="l670">                                    &quot;comply to algorithm constraints&quot;);</span><a href="#l670"></a>
<span id="l671">                        } else {</span><a href="#l671"></a>
<span id="l672">                            credentials = ecdhec;</span><a href="#l672"></a>
<span id="l673">                        }</span><a href="#l673"></a>
<span id="l674">                    }</span><a href="#l674"></a>
<span id="l675">                } catch (IOException | GeneralSecurityException ex) {</span><a href="#l675"></a>
<span id="l676">                    throw chc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,</span><a href="#l676"></a>
<span id="l677">                            &quot;Cannot decode named group: &quot; +</span><a href="#l677"></a>
<span id="l678">                            NamedGroup.nameOf(keyShare.namedGroupId));</span><a href="#l678"></a>
<span id="l679">                }</span><a href="#l679"></a>
<span id="l680">            } else if (ng.type == NamedGroupType.NAMED_GROUP_FFDHE) {</span><a href="#l680"></a>
<span id="l681">                try {</span><a href="#l681"></a>
<span id="l682">                    DHECredentials dhec =</span><a href="#l682"></a>
<span id="l683">                            DHECredentials.valueOf(ng, keyShare.keyExchange);</span><a href="#l683"></a>
<span id="l684">                    if (dhec != null) {</span><a href="#l684"></a>
<span id="l685">                        if (chc.algorithmConstraints != null &amp;&amp;</span><a href="#l685"></a>
<span id="l686">                                !chc.algorithmConstraints.permits(</span><a href="#l686"></a>
<span id="l687">                                        EnumSet.of(CryptoPrimitive.KEY_AGREEMENT),</span><a href="#l687"></a>
<span id="l688">                                        dhec.popPublicKey)) {</span><a href="#l688"></a>
<span id="l689">                            throw chc.conContext.fatal(Alert.INSUFFICIENT_SECURITY,</span><a href="#l689"></a>
<span id="l690">                                    &quot;DHE key share entry does not &quot; +</span><a href="#l690"></a>
<span id="l691">                                    &quot;comply to algorithm constraints&quot;);</span><a href="#l691"></a>
<span id="l692">                        } else {</span><a href="#l692"></a>
<span id="l693">                            credentials = dhec;</span><a href="#l693"></a>
<span id="l694">                        }</span><a href="#l694"></a>
<span id="l695">                    }</span><a href="#l695"></a>
<span id="l696">                } catch (IOException | GeneralSecurityException ex) {</span><a href="#l696"></a>
<span id="l697">                    throw chc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,</span><a href="#l697"></a>
<span id="l698">                            &quot;Cannot decode named group: &quot; +</span><a href="#l698"></a>
<span id="l699">                            NamedGroup.nameOf(keyShare.namedGroupId));</span><a href="#l699"></a>
<span id="l700">                }</span><a href="#l700"></a>
<span id="l701">            } else {</span><a href="#l701"></a>
<span id="l702">                throw chc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,</span><a href="#l702"></a>
<span id="l703">                        &quot;Unsupported named group: &quot; +</span><a href="#l703"></a>
<span id="l704">                        NamedGroup.nameOf(keyShare.namedGroupId));</span><a href="#l704"></a>
<span id="l705">            }</span><a href="#l705"></a>
<span id="l706"></span><a href="#l706"></a>
<span id="l707">            if (credentials == null) {</span><a href="#l707"></a>
<span id="l708">                throw chc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,</span><a href="#l708"></a>
<span id="l709">                        &quot;Unsupported named group: &quot; + ng.name);</span><a href="#l709"></a>
<span id="l710">            }</span><a href="#l710"></a>
<span id="l711"></span><a href="#l711"></a>
<span id="l712">            // update the context</span><a href="#l712"></a>
<span id="l713">            chc.handshakeKeyExchange = ke;</span><a href="#l713"></a>
<span id="l714">            chc.handshakeCredentials.add(credentials);</span><a href="#l714"></a>
<span id="l715">            chc.handshakeExtensions.put(SSLExtension.SH_KEY_SHARE, spec);</span><a href="#l715"></a>
<span id="l716">        }</span><a href="#l716"></a>
<span id="l717">    }</span><a href="#l717"></a>
<span id="l718"></span><a href="#l718"></a>
<span id="l719">    /**</span><a href="#l719"></a>
<span id="l720">     * The absence processing if the extension is not present in</span><a href="#l720"></a>
<span id="l721">     * the ServerHello handshake message.</span><a href="#l721"></a>
<span id="l722">     */</span><a href="#l722"></a>
<span id="l723">    private static final class SHKeyShareAbsence implements HandshakeAbsence {</span><a href="#l723"></a>
<span id="l724">        @Override</span><a href="#l724"></a>
<span id="l725">        public void absent(ConnectionContext context,</span><a href="#l725"></a>
<span id="l726">                HandshakeMessage message) throws IOException {</span><a href="#l726"></a>
<span id="l727">            // The producing happens in client side only.</span><a href="#l727"></a>
<span id="l728">            ClientHandshakeContext chc = (ClientHandshakeContext)context;</span><a href="#l728"></a>
<span id="l729"></span><a href="#l729"></a>
<span id="l730">            // Cannot use the previous requested key shares any more.</span><a href="#l730"></a>
<span id="l731">            if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;handshake&quot;)) {</span><a href="#l731"></a>
<span id="l732">                SSLLogger.fine(</span><a href="#l732"></a>
<span id="l733">                        &quot;No key_share extension in ServerHello, &quot; +</span><a href="#l733"></a>
<span id="l734">                        &quot;cleanup the key shares if necessary&quot;);</span><a href="#l734"></a>
<span id="l735">            }</span><a href="#l735"></a>
<span id="l736">            chc.handshakePossessions.clear();</span><a href="#l736"></a>
<span id="l737">        }</span><a href="#l737"></a>
<span id="l738">    }</span><a href="#l738"></a>
<span id="l739"></span><a href="#l739"></a>
<span id="l740">    /**</span><a href="#l740"></a>
<span id="l741">     * The key share entry used in HelloRetryRequest &quot;key_share&quot; extensions.</span><a href="#l741"></a>
<span id="l742">     */</span><a href="#l742"></a>
<span id="l743">    static final class HRRKeyShareSpec implements SSLExtensionSpec {</span><a href="#l743"></a>
<span id="l744">        final int selectedGroup;</span><a href="#l744"></a>
<span id="l745"></span><a href="#l745"></a>
<span id="l746">        HRRKeyShareSpec(NamedGroup serverGroup) {</span><a href="#l746"></a>
<span id="l747">            this.selectedGroup = serverGroup.id;</span><a href="#l747"></a>
<span id="l748">        }</span><a href="#l748"></a>
<span id="l749"></span><a href="#l749"></a>
<span id="l750">        private HRRKeyShareSpec(ByteBuffer buffer) throws IOException {</span><a href="#l750"></a>
<span id="l751">            // struct {</span><a href="#l751"></a>
<span id="l752">            //     NamedGroup selected_group;</span><a href="#l752"></a>
<span id="l753">            // } KeyShareHelloRetryRequest;</span><a href="#l753"></a>
<span id="l754">            if (buffer.remaining() != 2) {</span><a href="#l754"></a>
<span id="l755">                throw new SSLProtocolException(</span><a href="#l755"></a>
<span id="l756">                    &quot;Invalid key_share extension: &quot; +</span><a href="#l756"></a>
<span id="l757">                    &quot;improper data (length=&quot; + buffer.remaining() + &quot;)&quot;);</span><a href="#l757"></a>
<span id="l758">            }</span><a href="#l758"></a>
<span id="l759"></span><a href="#l759"></a>
<span id="l760">            this.selectedGroup = Record.getInt16(buffer);</span><a href="#l760"></a>
<span id="l761">        }</span><a href="#l761"></a>
<span id="l762"></span><a href="#l762"></a>
<span id="l763">        @Override</span><a href="#l763"></a>
<span id="l764">        public String toString() {</span><a href="#l764"></a>
<span id="l765">            MessageFormat messageFormat = new MessageFormat(</span><a href="#l765"></a>
<span id="l766">                &quot;\&quot;selected group\&quot;: '['{0}']'&quot;, Locale.ENGLISH);</span><a href="#l766"></a>
<span id="l767"></span><a href="#l767"></a>
<span id="l768">            Object[] messageFields = {</span><a href="#l768"></a>
<span id="l769">                    NamedGroup.nameOf(selectedGroup)</span><a href="#l769"></a>
<span id="l770">                };</span><a href="#l770"></a>
<span id="l771">            return messageFormat.format(messageFields);</span><a href="#l771"></a>
<span id="l772">        }</span><a href="#l772"></a>
<span id="l773">    }</span><a href="#l773"></a>
<span id="l774"></span><a href="#l774"></a>
<span id="l775">    private static final class HRRKeyShareStringizer implements SSLStringizer {</span><a href="#l775"></a>
<span id="l776">        @Override</span><a href="#l776"></a>
<span id="l777">        public String toString(ByteBuffer buffer) {</span><a href="#l777"></a>
<span id="l778">            try {</span><a href="#l778"></a>
<span id="l779">                return (new HRRKeyShareSpec(buffer)).toString();</span><a href="#l779"></a>
<span id="l780">            } catch (IOException ioe) {</span><a href="#l780"></a>
<span id="l781">                // For debug logging only, so please swallow exceptions.</span><a href="#l781"></a>
<span id="l782">                return ioe.getMessage();</span><a href="#l782"></a>
<span id="l783">            }</span><a href="#l783"></a>
<span id="l784">        }</span><a href="#l784"></a>
<span id="l785">    }</span><a href="#l785"></a>
<span id="l786"></span><a href="#l786"></a>
<span id="l787">    /**</span><a href="#l787"></a>
<span id="l788">     * Network data producer of the extension in a HelloRetryRequest</span><a href="#l788"></a>
<span id="l789">     * handshake message.</span><a href="#l789"></a>
<span id="l790">     */</span><a href="#l790"></a>
<span id="l791">    private static final</span><a href="#l791"></a>
<span id="l792">            class HRRKeyShareProducer implements HandshakeProducer {</span><a href="#l792"></a>
<span id="l793">        // Prevent instantiation of this class.</span><a href="#l793"></a>
<span id="l794">        private HRRKeyShareProducer() {</span><a href="#l794"></a>
<span id="l795">            // blank</span><a href="#l795"></a>
<span id="l796">        }</span><a href="#l796"></a>
<span id="l797"></span><a href="#l797"></a>
<span id="l798">        @Override</span><a href="#l798"></a>
<span id="l799">        public byte[] produce(ConnectionContext context,</span><a href="#l799"></a>
<span id="l800">                HandshakeMessage message) throws IOException {</span><a href="#l800"></a>
<span id="l801">            // The producing happens in server side only.</span><a href="#l801"></a>
<span id="l802">            ServerHandshakeContext shc = (ServerHandshakeContext) context;</span><a href="#l802"></a>
<span id="l803"></span><a href="#l803"></a>
<span id="l804">            // Is it a supported and enabled extension?</span><a href="#l804"></a>
<span id="l805">            if (!shc.sslConfig.isAvailable(SSLExtension.HRR_KEY_SHARE)) {</span><a href="#l805"></a>
<span id="l806">                throw shc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,</span><a href="#l806"></a>
<span id="l807">                        &quot;Unsupported key_share extension in HelloRetryRequest&quot;);</span><a href="#l807"></a>
<span id="l808">            }</span><a href="#l808"></a>
<span id="l809"></span><a href="#l809"></a>
<span id="l810">            if (shc.clientRequestedNamedGroups == null ||</span><a href="#l810"></a>
<span id="l811">                    shc.clientRequestedNamedGroups.isEmpty()) {</span><a href="#l811"></a>
<span id="l812">                // No supported groups.</span><a href="#l812"></a>
<span id="l813">                throw shc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,</span><a href="#l813"></a>
<span id="l814">                        &quot;Unexpected key_share extension in HelloRetryRequest&quot;);</span><a href="#l814"></a>
<span id="l815">            }</span><a href="#l815"></a>
<span id="l816"></span><a href="#l816"></a>
<span id="l817">            NamedGroup selectedGroup = null;</span><a href="#l817"></a>
<span id="l818">            for (NamedGroup ng : shc.clientRequestedNamedGroups) {</span><a href="#l818"></a>
<span id="l819">                if (SupportedGroups.isActivatable(</span><a href="#l819"></a>
<span id="l820">                        shc.algorithmConstraints, ng)) {</span><a href="#l820"></a>
<span id="l821">                    if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l821"></a>
<span id="l822">                        SSLLogger.fine(</span><a href="#l822"></a>
<span id="l823">                                &quot;HelloRetryRequest selected named group: &quot; +</span><a href="#l823"></a>
<span id="l824">                                ng.name);</span><a href="#l824"></a>
<span id="l825">                    }</span><a href="#l825"></a>
<span id="l826"></span><a href="#l826"></a>
<span id="l827">                    selectedGroup = ng;</span><a href="#l827"></a>
<span id="l828">                    break;</span><a href="#l828"></a>
<span id="l829">                }</span><a href="#l829"></a>
<span id="l830">            }</span><a href="#l830"></a>
<span id="l831"></span><a href="#l831"></a>
<span id="l832">            if (selectedGroup == null) {</span><a href="#l832"></a>
<span id="l833">                throw shc.conContext.fatal(</span><a href="#l833"></a>
<span id="l834">                        Alert.UNEXPECTED_MESSAGE, &quot;No common named group&quot;);</span><a href="#l834"></a>
<span id="l835">            }</span><a href="#l835"></a>
<span id="l836"></span><a href="#l836"></a>
<span id="l837">            byte[] extdata = new byte[] {</span><a href="#l837"></a>
<span id="l838">                    (byte)((selectedGroup.id &gt;&gt; 8) &amp; 0xFF),</span><a href="#l838"></a>
<span id="l839">                    (byte)(selectedGroup.id &amp; 0xFF)</span><a href="#l839"></a>
<span id="l840">                };</span><a href="#l840"></a>
<span id="l841"></span><a href="#l841"></a>
<span id="l842">            // update the context</span><a href="#l842"></a>
<span id="l843">            shc.serverSelectedNamedGroup = selectedGroup;</span><a href="#l843"></a>
<span id="l844">            shc.handshakeExtensions.put(SSLExtension.HRR_KEY_SHARE,</span><a href="#l844"></a>
<span id="l845">                    new HRRKeyShareSpec(selectedGroup));</span><a href="#l845"></a>
<span id="l846"></span><a href="#l846"></a>
<span id="l847">            return extdata;</span><a href="#l847"></a>
<span id="l848">        }</span><a href="#l848"></a>
<span id="l849">    }</span><a href="#l849"></a>
<span id="l850"></span><a href="#l850"></a>
<span id="l851">    /**</span><a href="#l851"></a>
<span id="l852">     * Network data producer of the extension for stateless</span><a href="#l852"></a>
<span id="l853">     * HelloRetryRequest reconstruction.</span><a href="#l853"></a>
<span id="l854">     */</span><a href="#l854"></a>
<span id="l855">    private static final</span><a href="#l855"></a>
<span id="l856">            class HRRKeyShareReproducer implements HandshakeProducer {</span><a href="#l856"></a>
<span id="l857">        // Prevent instantiation of this class.</span><a href="#l857"></a>
<span id="l858">        private HRRKeyShareReproducer() {</span><a href="#l858"></a>
<span id="l859">            // blank</span><a href="#l859"></a>
<span id="l860">        }</span><a href="#l860"></a>
<span id="l861"></span><a href="#l861"></a>
<span id="l862">        @Override</span><a href="#l862"></a>
<span id="l863">        public byte[] produce(ConnectionContext context,</span><a href="#l863"></a>
<span id="l864">                HandshakeMessage message) throws IOException {</span><a href="#l864"></a>
<span id="l865">            // The producing happens in server side only.</span><a href="#l865"></a>
<span id="l866">            ServerHandshakeContext shc = (ServerHandshakeContext) context;</span><a href="#l866"></a>
<span id="l867"></span><a href="#l867"></a>
<span id="l868">            // Is it a supported and enabled extension?</span><a href="#l868"></a>
<span id="l869">            if (!shc.sslConfig.isAvailable(SSLExtension.HRR_KEY_SHARE)) {</span><a href="#l869"></a>
<span id="l870">                throw shc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,</span><a href="#l870"></a>
<span id="l871">                        &quot;Unsupported key_share extension in HelloRetryRequest&quot;);</span><a href="#l871"></a>
<span id="l872">            }</span><a href="#l872"></a>
<span id="l873"></span><a href="#l873"></a>
<span id="l874">            CHKeyShareSpec spec = (CHKeyShareSpec)shc.handshakeExtensions.get(</span><a href="#l874"></a>
<span id="l875">                    SSLExtension.CH_KEY_SHARE);</span><a href="#l875"></a>
<span id="l876">            if (spec != null &amp;&amp; spec.clientShares != null &amp;&amp;</span><a href="#l876"></a>
<span id="l877">                    spec.clientShares.size() == 1) {</span><a href="#l877"></a>
<span id="l878">                int namedGroupId = spec.clientShares.get(0).namedGroupId;</span><a href="#l878"></a>
<span id="l879"></span><a href="#l879"></a>
<span id="l880">                byte[] extdata = new byte[] {</span><a href="#l880"></a>
<span id="l881">                        (byte)((namedGroupId &gt;&gt; 8) &amp; 0xFF),</span><a href="#l881"></a>
<span id="l882">                        (byte)(namedGroupId &amp; 0xFF)</span><a href="#l882"></a>
<span id="l883">                    };</span><a href="#l883"></a>
<span id="l884"></span><a href="#l884"></a>
<span id="l885">                return extdata;</span><a href="#l885"></a>
<span id="l886">            }</span><a href="#l886"></a>
<span id="l887"></span><a href="#l887"></a>
<span id="l888">            return null;</span><a href="#l888"></a>
<span id="l889">        }</span><a href="#l889"></a>
<span id="l890">    }</span><a href="#l890"></a>
<span id="l891"></span><a href="#l891"></a>
<span id="l892">    /**</span><a href="#l892"></a>
<span id="l893">     * Network data consumer of the extension in a HelloRetryRequest</span><a href="#l893"></a>
<span id="l894">     * handshake message.</span><a href="#l894"></a>
<span id="l895">     */</span><a href="#l895"></a>
<span id="l896">    private static final</span><a href="#l896"></a>
<span id="l897">            class HRRKeyShareConsumer implements ExtensionConsumer {</span><a href="#l897"></a>
<span id="l898">        // Prevent instantiation of this class.</span><a href="#l898"></a>
<span id="l899">        private HRRKeyShareConsumer() {</span><a href="#l899"></a>
<span id="l900">            // blank</span><a href="#l900"></a>
<span id="l901">        }</span><a href="#l901"></a>
<span id="l902"></span><a href="#l902"></a>
<span id="l903">        @Override</span><a href="#l903"></a>
<span id="l904">        public void consume(ConnectionContext context,</span><a href="#l904"></a>
<span id="l905">            HandshakeMessage message, ByteBuffer buffer) throws IOException {</span><a href="#l905"></a>
<span id="l906">            // The producing happens in client side only.</span><a href="#l906"></a>
<span id="l907">            ClientHandshakeContext chc = (ClientHandshakeContext)context;</span><a href="#l907"></a>
<span id="l908"></span><a href="#l908"></a>
<span id="l909">            // Is it a supported and enabled extension?</span><a href="#l909"></a>
<span id="l910">            if (!chc.sslConfig.isAvailable(SSLExtension.HRR_KEY_SHARE)) {</span><a href="#l910"></a>
<span id="l911">                throw chc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,</span><a href="#l911"></a>
<span id="l912">                        &quot;Unsupported key_share extension in HelloRetryRequest&quot;);</span><a href="#l912"></a>
<span id="l913">            }</span><a href="#l913"></a>
<span id="l914"></span><a href="#l914"></a>
<span id="l915">            if (chc.clientRequestedNamedGroups == null ||</span><a href="#l915"></a>
<span id="l916">                    chc.clientRequestedNamedGroups.isEmpty()) {</span><a href="#l916"></a>
<span id="l917">                // No supported groups.</span><a href="#l917"></a>
<span id="l918">                throw chc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,</span><a href="#l918"></a>
<span id="l919">                        &quot;Unexpected key_share extension in HelloRetryRequest&quot;);</span><a href="#l919"></a>
<span id="l920">            }</span><a href="#l920"></a>
<span id="l921"></span><a href="#l921"></a>
<span id="l922">            // Parse the extension</span><a href="#l922"></a>
<span id="l923">            HRRKeyShareSpec spec;</span><a href="#l923"></a>
<span id="l924">            try {</span><a href="#l924"></a>
<span id="l925">                spec = new HRRKeyShareSpec(buffer);</span><a href="#l925"></a>
<span id="l926">            } catch (IOException ioe) {</span><a href="#l926"></a>
<span id="l927">                throw chc.conContext.fatal(Alert.UNEXPECTED_MESSAGE, ioe);</span><a href="#l927"></a>
<span id="l928">            }</span><a href="#l928"></a>
<span id="l929"></span><a href="#l929"></a>
<span id="l930">            NamedGroup serverGroup = NamedGroup.valueOf(spec.selectedGroup);</span><a href="#l930"></a>
<span id="l931">            if (serverGroup == null) {</span><a href="#l931"></a>
<span id="l932">                throw chc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,</span><a href="#l932"></a>
<span id="l933">                        &quot;Unsupported HelloRetryRequest selected group: &quot; +</span><a href="#l933"></a>
<span id="l934">                                NamedGroup.nameOf(spec.selectedGroup));</span><a href="#l934"></a>
<span id="l935">            }</span><a href="#l935"></a>
<span id="l936"></span><a href="#l936"></a>
<span id="l937">            if (!chc.clientRequestedNamedGroups.contains(serverGroup)) {</span><a href="#l937"></a>
<span id="l938">                throw chc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,</span><a href="#l938"></a>
<span id="l939">                        &quot;Unexpected HelloRetryRequest selected group: &quot; +</span><a href="#l939"></a>
<span id="l940">                                serverGroup.name);</span><a href="#l940"></a>
<span id="l941">            }</span><a href="#l941"></a>
<span id="l942"></span><a href="#l942"></a>
<span id="l943">            // update the context</span><a href="#l943"></a>
<span id="l944"></span><a href="#l944"></a>
<span id="l945">            // When sending the new ClientHello, the client MUST replace the</span><a href="#l945"></a>
<span id="l946">            // original &quot;key_share&quot; extension with one containing only a new</span><a href="#l946"></a>
<span id="l947">            // KeyShareEntry for the group indicated in the selected_group</span><a href="#l947"></a>
<span id="l948">            // field of the triggering HelloRetryRequest.</span><a href="#l948"></a>
<span id="l949">            //</span><a href="#l949"></a>
<span id="l950">            chc.serverSelectedNamedGroup = serverGroup;</span><a href="#l950"></a>
<span id="l951">            chc.handshakeExtensions.put(SSLExtension.HRR_KEY_SHARE, spec);</span><a href="#l951"></a>
<span id="l952">        }</span><a href="#l952"></a>
<span id="l953">    }</span><a href="#l953"></a>
<span id="l954">}</span><a href="#l954"></a></pre>
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

