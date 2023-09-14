<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 54441ec952f7 src/share/classes/sun/security/ssl/PreSharedKeyExtension.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/ssl/PreSharedKeyExtension.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/54441ec952f7/src/share/classes/sun/security/ssl/PreSharedKeyExtension.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/54441ec952f7/src/share/classes/sun/security/ssl/PreSharedKeyExtension.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/54441ec952f7/src/share/classes/sun/security/ssl/PreSharedKeyExtension.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/54441ec952f7/src/share/classes/sun/security/ssl/PreSharedKeyExtension.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/54441ec952f7/src/share/classes/sun/security/ssl/PreSharedKeyExtension.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/ssl/PreSharedKeyExtension.java @ 14574:54441ec952f7</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/3b17a40d9a8d/src/share/classes/sun/security/ssl/PreSharedKeyExtension.java">3b17a40d9a8d</a> </td>
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
<span id="l2"> * Copyright (c) 2015, 2019, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l25">package sun.security.ssl;</span><a href="#l25"></a>
<span id="l26"></span><a href="#l26"></a>
<span id="l27">import java.io.IOException;</span><a href="#l27"></a>
<span id="l28">import java.nio.ByteBuffer;</span><a href="#l28"></a>
<span id="l29">import java.security.*;</span><a href="#l29"></a>
<span id="l30">import java.text.MessageFormat;</span><a href="#l30"></a>
<span id="l31">import java.util.List;</span><a href="#l31"></a>
<span id="l32">import java.util.ArrayList;</span><a href="#l32"></a>
<span id="l33">import java.util.Locale;</span><a href="#l33"></a>
<span id="l34">import java.util.Objects;</span><a href="#l34"></a>
<span id="l35">import java.util.Collection;</span><a href="#l35"></a>
<span id="l36">import javax.crypto.Mac;</span><a href="#l36"></a>
<span id="l37">import javax.crypto.SecretKey;</span><a href="#l37"></a>
<span id="l38">import javax.net.ssl.SSLPeerUnverifiedException;</span><a href="#l38"></a>
<span id="l39">import static sun.security.ssl.ClientAuthType.CLIENT_AUTH_REQUIRED;</span><a href="#l39"></a>
<span id="l40">import sun.security.ssl.ClientHello.ClientHelloMessage;</span><a href="#l40"></a>
<span id="l41">import sun.security.ssl.SSLExtension.ExtensionConsumer;</span><a href="#l41"></a>
<span id="l42">import sun.security.ssl.SSLExtension.SSLExtensionSpec;</span><a href="#l42"></a>
<span id="l43">import sun.security.ssl.SSLHandshake.HandshakeMessage;</span><a href="#l43"></a>
<span id="l44">import static sun.security.ssl.SSLExtension.*;</span><a href="#l44"></a>
<span id="l45"></span><a href="#l45"></a>
<span id="l46">/**</span><a href="#l46"></a>
<span id="l47"> * Pack of the &quot;pre_shared_key&quot; extension.</span><a href="#l47"></a>
<span id="l48"> */</span><a href="#l48"></a>
<span id="l49">final class PreSharedKeyExtension {</span><a href="#l49"></a>
<span id="l50">    static final HandshakeProducer chNetworkProducer =</span><a href="#l50"></a>
<span id="l51">            new CHPreSharedKeyProducer();</span><a href="#l51"></a>
<span id="l52">    static final ExtensionConsumer chOnLoadConsumer =</span><a href="#l52"></a>
<span id="l53">            new CHPreSharedKeyConsumer();</span><a href="#l53"></a>
<span id="l54">    static final HandshakeAbsence chOnLoadAbsence =</span><a href="#l54"></a>
<span id="l55">            new CHPreSharedKeyAbsence();</span><a href="#l55"></a>
<span id="l56">    static final HandshakeConsumer chOnTradeConsumer =</span><a href="#l56"></a>
<span id="l57">            new CHPreSharedKeyUpdate();</span><a href="#l57"></a>
<span id="l58">    static final SSLStringizer chStringizer =</span><a href="#l58"></a>
<span id="l59">            new CHPreSharedKeyStringizer();</span><a href="#l59"></a>
<span id="l60"></span><a href="#l60"></a>
<span id="l61">    static final HandshakeProducer shNetworkProducer =</span><a href="#l61"></a>
<span id="l62">            new SHPreSharedKeyProducer();</span><a href="#l62"></a>
<span id="l63">    static final ExtensionConsumer shOnLoadConsumer =</span><a href="#l63"></a>
<span id="l64">            new SHPreSharedKeyConsumer();</span><a href="#l64"></a>
<span id="l65">    static final HandshakeAbsence shOnLoadAbsence =</span><a href="#l65"></a>
<span id="l66">            new SHPreSharedKeyAbsence();</span><a href="#l66"></a>
<span id="l67">    static final SSLStringizer shStringizer =</span><a href="#l67"></a>
<span id="l68">            new SHPreSharedKeyStringizer();</span><a href="#l68"></a>
<span id="l69"></span><a href="#l69"></a>
<span id="l70">    private static final class PskIdentity {</span><a href="#l70"></a>
<span id="l71">        final byte[] identity;</span><a href="#l71"></a>
<span id="l72">        final int obfuscatedAge;</span><a href="#l72"></a>
<span id="l73"></span><a href="#l73"></a>
<span id="l74">        PskIdentity(byte[] identity, int obfuscatedAge) {</span><a href="#l74"></a>
<span id="l75">            this.identity = identity;</span><a href="#l75"></a>
<span id="l76">            this.obfuscatedAge = obfuscatedAge;</span><a href="#l76"></a>
<span id="l77">        }</span><a href="#l77"></a>
<span id="l78"></span><a href="#l78"></a>
<span id="l79">        int getEncodedLength() {</span><a href="#l79"></a>
<span id="l80">            return 2 + identity.length + 4;</span><a href="#l80"></a>
<span id="l81">        }</span><a href="#l81"></a>
<span id="l82"></span><a href="#l82"></a>
<span id="l83">        void writeEncoded(ByteBuffer m) throws IOException {</span><a href="#l83"></a>
<span id="l84">            Record.putBytes16(m, identity);</span><a href="#l84"></a>
<span id="l85">            Record.putInt32(m, obfuscatedAge);</span><a href="#l85"></a>
<span id="l86">        }</span><a href="#l86"></a>
<span id="l87"></span><a href="#l87"></a>
<span id="l88">        @Override</span><a href="#l88"></a>
<span id="l89">        public String toString() {</span><a href="#l89"></a>
<span id="l90">            return &quot;{&quot; + Utilities.toHexString(identity) + &quot;,&quot; +</span><a href="#l90"></a>
<span id="l91">                obfuscatedAge + &quot;}&quot;;</span><a href="#l91"></a>
<span id="l92">        }</span><a href="#l92"></a>
<span id="l93">    }</span><a href="#l93"></a>
<span id="l94"></span><a href="#l94"></a>
<span id="l95">    private static final</span><a href="#l95"></a>
<span id="l96">            class CHPreSharedKeySpec implements SSLExtensionSpec {</span><a href="#l96"></a>
<span id="l97">        final List&lt;PskIdentity&gt; identities;</span><a href="#l97"></a>
<span id="l98">        final List&lt;byte[]&gt; binders;</span><a href="#l98"></a>
<span id="l99"></span><a href="#l99"></a>
<span id="l100">        CHPreSharedKeySpec(List&lt;PskIdentity&gt; identities, List&lt;byte[]&gt; binders) {</span><a href="#l100"></a>
<span id="l101">            this.identities = identities;</span><a href="#l101"></a>
<span id="l102">            this.binders = binders;</span><a href="#l102"></a>
<span id="l103">        }</span><a href="#l103"></a>
<span id="l104"></span><a href="#l104"></a>
<span id="l105">        CHPreSharedKeySpec(HandshakeContext context,</span><a href="#l105"></a>
<span id="l106">                ByteBuffer m) throws IOException {</span><a href="#l106"></a>
<span id="l107">            // struct {</span><a href="#l107"></a>
<span id="l108">            //     PskIdentity identities&lt;7..2^16-1&gt;;</span><a href="#l108"></a>
<span id="l109">            //     PskBinderEntry binders&lt;33..2^16-1&gt;;</span><a href="#l109"></a>
<span id="l110">            // } OfferedPsks;</span><a href="#l110"></a>
<span id="l111">            if (m.remaining() &lt; 44) {</span><a href="#l111"></a>
<span id="l112">                throw context.conContext.fatal(Alert.ILLEGAL_PARAMETER,</span><a href="#l112"></a>
<span id="l113">                    &quot;Invalid pre_shared_key extension: &quot; +</span><a href="#l113"></a>
<span id="l114">                    &quot;insufficient data (length=&quot; + m.remaining() + &quot;)&quot;);</span><a href="#l114"></a>
<span id="l115">            }</span><a href="#l115"></a>
<span id="l116"></span><a href="#l116"></a>
<span id="l117">            int idEncodedLength = Record.getInt16(m);</span><a href="#l117"></a>
<span id="l118">            if (idEncodedLength &lt; 7) {</span><a href="#l118"></a>
<span id="l119">                throw context.conContext.fatal(Alert.ILLEGAL_PARAMETER,</span><a href="#l119"></a>
<span id="l120">                    &quot;Invalid pre_shared_key extension: &quot; +</span><a href="#l120"></a>
<span id="l121">                    &quot;insufficient identities (length=&quot; + idEncodedLength + &quot;)&quot;);</span><a href="#l121"></a>
<span id="l122">            }</span><a href="#l122"></a>
<span id="l123"></span><a href="#l123"></a>
<span id="l124">            identities = new ArrayList&lt;&gt;();</span><a href="#l124"></a>
<span id="l125">            int idReadLength = 0;</span><a href="#l125"></a>
<span id="l126">            while (idReadLength &lt; idEncodedLength) {</span><a href="#l126"></a>
<span id="l127">                byte[] id = Record.getBytes16(m);</span><a href="#l127"></a>
<span id="l128">                if (id.length &lt; 1) {</span><a href="#l128"></a>
<span id="l129">                    throw context.conContext.fatal(Alert.ILLEGAL_PARAMETER,</span><a href="#l129"></a>
<span id="l130">                        &quot;Invalid pre_shared_key extension: &quot; +</span><a href="#l130"></a>
<span id="l131">                        &quot;insufficient identity (length=&quot; + id.length + &quot;)&quot;);</span><a href="#l131"></a>
<span id="l132">                }</span><a href="#l132"></a>
<span id="l133">                int obfuscatedTicketAge = Record.getInt32(m);</span><a href="#l133"></a>
<span id="l134"></span><a href="#l134"></a>
<span id="l135">                PskIdentity pskId = new PskIdentity(id, obfuscatedTicketAge);</span><a href="#l135"></a>
<span id="l136">                identities.add(pskId);</span><a href="#l136"></a>
<span id="l137">                idReadLength += pskId.getEncodedLength();</span><a href="#l137"></a>
<span id="l138">            }</span><a href="#l138"></a>
<span id="l139"></span><a href="#l139"></a>
<span id="l140">            if (m.remaining() &lt; 35) {</span><a href="#l140"></a>
<span id="l141">                throw context.conContext.fatal(Alert.ILLEGAL_PARAMETER,</span><a href="#l141"></a>
<span id="l142">                        &quot;Invalid pre_shared_key extension: &quot; +</span><a href="#l142"></a>
<span id="l143">                        &quot;insufficient binders data (length=&quot; +</span><a href="#l143"></a>
<span id="l144">                        m.remaining() + &quot;)&quot;);</span><a href="#l144"></a>
<span id="l145">            }</span><a href="#l145"></a>
<span id="l146"></span><a href="#l146"></a>
<span id="l147">            int bindersEncodedLen = Record.getInt16(m);</span><a href="#l147"></a>
<span id="l148">            if (bindersEncodedLen &lt; 33) {</span><a href="#l148"></a>
<span id="l149">                throw context.conContext.fatal(Alert.ILLEGAL_PARAMETER,</span><a href="#l149"></a>
<span id="l150">                        &quot;Invalid pre_shared_key extension: &quot; +</span><a href="#l150"></a>
<span id="l151">                        &quot;insufficient binders (length=&quot; +</span><a href="#l151"></a>
<span id="l152">                        bindersEncodedLen + &quot;)&quot;);</span><a href="#l152"></a>
<span id="l153">            }</span><a href="#l153"></a>
<span id="l154"></span><a href="#l154"></a>
<span id="l155">            binders = new ArrayList&lt;&gt;();</span><a href="#l155"></a>
<span id="l156">            int bindersReadLength = 0;</span><a href="#l156"></a>
<span id="l157">            while (bindersReadLength &lt; bindersEncodedLen) {</span><a href="#l157"></a>
<span id="l158">                byte[] binder = Record.getBytes8(m);</span><a href="#l158"></a>
<span id="l159">                if (binder.length &lt; 32) {</span><a href="#l159"></a>
<span id="l160">                    throw context.conContext.fatal(Alert.ILLEGAL_PARAMETER,</span><a href="#l160"></a>
<span id="l161">                            &quot;Invalid pre_shared_key extension: &quot; +</span><a href="#l161"></a>
<span id="l162">                            &quot;insufficient binder entry (length=&quot; +</span><a href="#l162"></a>
<span id="l163">                            binder.length + &quot;)&quot;);</span><a href="#l163"></a>
<span id="l164">                }</span><a href="#l164"></a>
<span id="l165">                binders.add(binder);</span><a href="#l165"></a>
<span id="l166">                bindersReadLength += 1 + binder.length;</span><a href="#l166"></a>
<span id="l167">            }</span><a href="#l167"></a>
<span id="l168">        }</span><a href="#l168"></a>
<span id="l169"></span><a href="#l169"></a>
<span id="l170">        int getIdsEncodedLength() {</span><a href="#l170"></a>
<span id="l171">            int idEncodedLength = 0;</span><a href="#l171"></a>
<span id="l172">            for (PskIdentity curId : identities) {</span><a href="#l172"></a>
<span id="l173">                idEncodedLength += curId.getEncodedLength();</span><a href="#l173"></a>
<span id="l174">            }</span><a href="#l174"></a>
<span id="l175"></span><a href="#l175"></a>
<span id="l176">            return idEncodedLength;</span><a href="#l176"></a>
<span id="l177">        }</span><a href="#l177"></a>
<span id="l178"></span><a href="#l178"></a>
<span id="l179">        int getBindersEncodedLength() {</span><a href="#l179"></a>
<span id="l180">            int binderEncodedLength = 0;</span><a href="#l180"></a>
<span id="l181">            for (byte[] curBinder : binders) {</span><a href="#l181"></a>
<span id="l182">                binderEncodedLength += 1 + curBinder.length;</span><a href="#l182"></a>
<span id="l183">            }</span><a href="#l183"></a>
<span id="l184"></span><a href="#l184"></a>
<span id="l185">            return binderEncodedLength;</span><a href="#l185"></a>
<span id="l186">        }</span><a href="#l186"></a>
<span id="l187"></span><a href="#l187"></a>
<span id="l188">        byte[] getEncoded() throws IOException {</span><a href="#l188"></a>
<span id="l189">            int idsEncodedLength = getIdsEncodedLength();</span><a href="#l189"></a>
<span id="l190">            int bindersEncodedLength = getBindersEncodedLength();</span><a href="#l190"></a>
<span id="l191">            int encodedLength = 4 + idsEncodedLength + bindersEncodedLength;</span><a href="#l191"></a>
<span id="l192">            byte[] buffer = new byte[encodedLength];</span><a href="#l192"></a>
<span id="l193">            ByteBuffer m = ByteBuffer.wrap(buffer);</span><a href="#l193"></a>
<span id="l194">            Record.putInt16(m, idsEncodedLength);</span><a href="#l194"></a>
<span id="l195">            for (PskIdentity curId : identities) {</span><a href="#l195"></a>
<span id="l196">                curId.writeEncoded(m);</span><a href="#l196"></a>
<span id="l197">            }</span><a href="#l197"></a>
<span id="l198">            Record.putInt16(m, bindersEncodedLength);</span><a href="#l198"></a>
<span id="l199">            for (byte[] curBinder : binders) {</span><a href="#l199"></a>
<span id="l200">                Record.putBytes8(m, curBinder);</span><a href="#l200"></a>
<span id="l201">            }</span><a href="#l201"></a>
<span id="l202"></span><a href="#l202"></a>
<span id="l203">            return buffer;</span><a href="#l203"></a>
<span id="l204">        }</span><a href="#l204"></a>
<span id="l205"></span><a href="#l205"></a>
<span id="l206">        @Override</span><a href="#l206"></a>
<span id="l207">        public String toString() {</span><a href="#l207"></a>
<span id="l208">            MessageFormat messageFormat = new MessageFormat(</span><a href="#l208"></a>
<span id="l209">                &quot;\&quot;PreSharedKey\&quot;: '{'\n&quot; +</span><a href="#l209"></a>
<span id="l210">                &quot;  \&quot;identities\&quot;    : \&quot;{0}\&quot;,\n&quot; +</span><a href="#l210"></a>
<span id="l211">                &quot;  \&quot;binders\&quot;       : \&quot;{1}\&quot;,\n&quot; +</span><a href="#l211"></a>
<span id="l212">                &quot;'}'&quot;,</span><a href="#l212"></a>
<span id="l213">                Locale.ENGLISH);</span><a href="#l213"></a>
<span id="l214"></span><a href="#l214"></a>
<span id="l215">            Object[] messageFields = {</span><a href="#l215"></a>
<span id="l216">                Utilities.indent(identitiesString()),</span><a href="#l216"></a>
<span id="l217">                Utilities.indent(bindersString())</span><a href="#l217"></a>
<span id="l218">            };</span><a href="#l218"></a>
<span id="l219"></span><a href="#l219"></a>
<span id="l220">            return messageFormat.format(messageFields);</span><a href="#l220"></a>
<span id="l221">        }</span><a href="#l221"></a>
<span id="l222"></span><a href="#l222"></a>
<span id="l223">        String identitiesString() {</span><a href="#l223"></a>
<span id="l224">            StringBuilder result = new StringBuilder();</span><a href="#l224"></a>
<span id="l225">            for (PskIdentity curId : identities) {</span><a href="#l225"></a>
<span id="l226">                result.append(curId.toString() + &quot;\n&quot;);</span><a href="#l226"></a>
<span id="l227">            }</span><a href="#l227"></a>
<span id="l228"></span><a href="#l228"></a>
<span id="l229">            return result.toString();</span><a href="#l229"></a>
<span id="l230">        }</span><a href="#l230"></a>
<span id="l231"></span><a href="#l231"></a>
<span id="l232">        String bindersString() {</span><a href="#l232"></a>
<span id="l233">            StringBuilder result = new StringBuilder();</span><a href="#l233"></a>
<span id="l234">            for (byte[] curBinder : binders) {</span><a href="#l234"></a>
<span id="l235">                result.append(&quot;{&quot; + Utilities.toHexString(curBinder) + &quot;}\n&quot;);</span><a href="#l235"></a>
<span id="l236">            }</span><a href="#l236"></a>
<span id="l237"></span><a href="#l237"></a>
<span id="l238">            return result.toString();</span><a href="#l238"></a>
<span id="l239">        }</span><a href="#l239"></a>
<span id="l240">    }</span><a href="#l240"></a>
<span id="l241"></span><a href="#l241"></a>
<span id="l242">    private static final</span><a href="#l242"></a>
<span id="l243">            class CHPreSharedKeyStringizer implements SSLStringizer {</span><a href="#l243"></a>
<span id="l244">        @Override</span><a href="#l244"></a>
<span id="l245">        public String toString(ByteBuffer buffer) {</span><a href="#l245"></a>
<span id="l246">            try {</span><a href="#l246"></a>
<span id="l247">                // As the HandshakeContext parameter of CHPreSharedKeySpec</span><a href="#l247"></a>
<span id="l248">                // constructor is used for fatal alert only, we can use</span><a href="#l248"></a>
<span id="l249">                // null HandshakeContext here as we don't care about exception.</span><a href="#l249"></a>
<span id="l250">                //</span><a href="#l250"></a>
<span id="l251">                // Please take care of this code if the CHPreSharedKeySpec</span><a href="#l251"></a>
<span id="l252">                // constructor is updated in the future.</span><a href="#l252"></a>
<span id="l253">                return (new CHPreSharedKeySpec(null, buffer)).toString();</span><a href="#l253"></a>
<span id="l254">            } catch (Exception ex) {</span><a href="#l254"></a>
<span id="l255">                // For debug logging only, so please swallow exceptions.</span><a href="#l255"></a>
<span id="l256">                return ex.getMessage();</span><a href="#l256"></a>
<span id="l257">            }</span><a href="#l257"></a>
<span id="l258">        }</span><a href="#l258"></a>
<span id="l259">    }</span><a href="#l259"></a>
<span id="l260"></span><a href="#l260"></a>
<span id="l261">    private static final</span><a href="#l261"></a>
<span id="l262">            class SHPreSharedKeySpec implements SSLExtensionSpec {</span><a href="#l262"></a>
<span id="l263">        final int selectedIdentity;</span><a href="#l263"></a>
<span id="l264"></span><a href="#l264"></a>
<span id="l265">        SHPreSharedKeySpec(int selectedIdentity) {</span><a href="#l265"></a>
<span id="l266">            this.selectedIdentity = selectedIdentity;</span><a href="#l266"></a>
<span id="l267">        }</span><a href="#l267"></a>
<span id="l268"></span><a href="#l268"></a>
<span id="l269">        SHPreSharedKeySpec(HandshakeContext context,</span><a href="#l269"></a>
<span id="l270">                ByteBuffer m) throws IOException {</span><a href="#l270"></a>
<span id="l271">            if (m.remaining() &lt; 2) {</span><a href="#l271"></a>
<span id="l272">                throw context.conContext.fatal(Alert.ILLEGAL_PARAMETER,</span><a href="#l272"></a>
<span id="l273">                        &quot;Invalid pre_shared_key extension: &quot; +</span><a href="#l273"></a>
<span id="l274">                        &quot;insufficient selected_identity (length=&quot; +</span><a href="#l274"></a>
<span id="l275">                        m.remaining() + &quot;)&quot;);</span><a href="#l275"></a>
<span id="l276">            }</span><a href="#l276"></a>
<span id="l277">            this.selectedIdentity = Record.getInt16(m);</span><a href="#l277"></a>
<span id="l278">        }</span><a href="#l278"></a>
<span id="l279"></span><a href="#l279"></a>
<span id="l280">        byte[] getEncoded() throws IOException {</span><a href="#l280"></a>
<span id="l281">            return new byte[] {</span><a href="#l281"></a>
<span id="l282">                (byte)((selectedIdentity &gt;&gt; 8) &amp; 0xFF),</span><a href="#l282"></a>
<span id="l283">                (byte)(selectedIdentity &amp; 0xFF)</span><a href="#l283"></a>
<span id="l284">            };</span><a href="#l284"></a>
<span id="l285">        }</span><a href="#l285"></a>
<span id="l286"></span><a href="#l286"></a>
<span id="l287">        @Override</span><a href="#l287"></a>
<span id="l288">        public String toString() {</span><a href="#l288"></a>
<span id="l289">            MessageFormat messageFormat = new MessageFormat(</span><a href="#l289"></a>
<span id="l290">                &quot;\&quot;PreSharedKey\&quot;: '{'\n&quot; +</span><a href="#l290"></a>
<span id="l291">                &quot;  \&quot;selected_identity\&quot;      : \&quot;{0}\&quot;,\n&quot; +</span><a href="#l291"></a>
<span id="l292">                &quot;'}'&quot;,</span><a href="#l292"></a>
<span id="l293">                Locale.ENGLISH);</span><a href="#l293"></a>
<span id="l294"></span><a href="#l294"></a>
<span id="l295">            Object[] messageFields = {</span><a href="#l295"></a>
<span id="l296">                Utilities.byte16HexString(selectedIdentity)</span><a href="#l296"></a>
<span id="l297">            };</span><a href="#l297"></a>
<span id="l298"></span><a href="#l298"></a>
<span id="l299">            return messageFormat.format(messageFields);</span><a href="#l299"></a>
<span id="l300">        }</span><a href="#l300"></a>
<span id="l301">    }</span><a href="#l301"></a>
<span id="l302"></span><a href="#l302"></a>
<span id="l303">    private static final</span><a href="#l303"></a>
<span id="l304">            class SHPreSharedKeyStringizer implements SSLStringizer {</span><a href="#l304"></a>
<span id="l305">        @Override</span><a href="#l305"></a>
<span id="l306">        public String toString(ByteBuffer buffer) {</span><a href="#l306"></a>
<span id="l307">            try {</span><a href="#l307"></a>
<span id="l308">                // As the HandshakeContext parameter of SHPreSharedKeySpec</span><a href="#l308"></a>
<span id="l309">                // constructor is used for fatal alert only, we can use</span><a href="#l309"></a>
<span id="l310">                // null HandshakeContext here as we don't care about exception.</span><a href="#l310"></a>
<span id="l311">                //</span><a href="#l311"></a>
<span id="l312">                // Please take care of this code if the SHPreSharedKeySpec</span><a href="#l312"></a>
<span id="l313">                // constructor is updated in the future.</span><a href="#l313"></a>
<span id="l314">                return (new SHPreSharedKeySpec(null, buffer)).toString();</span><a href="#l314"></a>
<span id="l315">            } catch (Exception ex) {</span><a href="#l315"></a>
<span id="l316">                // For debug logging only, so please swallow exceptions.</span><a href="#l316"></a>
<span id="l317">                return ex.getMessage();</span><a href="#l317"></a>
<span id="l318">            }</span><a href="#l318"></a>
<span id="l319">        }</span><a href="#l319"></a>
<span id="l320">    }</span><a href="#l320"></a>
<span id="l321"></span><a href="#l321"></a>
<span id="l322">    private static final</span><a href="#l322"></a>
<span id="l323">            class CHPreSharedKeyConsumer implements ExtensionConsumer {</span><a href="#l323"></a>
<span id="l324">        // Prevent instantiation of this class.</span><a href="#l324"></a>
<span id="l325">        private CHPreSharedKeyConsumer() {</span><a href="#l325"></a>
<span id="l326">            // blank</span><a href="#l326"></a>
<span id="l327">        }</span><a href="#l327"></a>
<span id="l328"></span><a href="#l328"></a>
<span id="l329">        @Override</span><a href="#l329"></a>
<span id="l330">        public void consume(ConnectionContext context,</span><a href="#l330"></a>
<span id="l331">                            HandshakeMessage message,</span><a href="#l331"></a>
<span id="l332">                            ByteBuffer buffer) throws IOException {</span><a href="#l332"></a>
<span id="l333">            ClientHelloMessage clientHello = (ClientHelloMessage) message;</span><a href="#l333"></a>
<span id="l334">            ServerHandshakeContext shc = (ServerHandshakeContext)context;</span><a href="#l334"></a>
<span id="l335">            // Is it a supported and enabled extension?</span><a href="#l335"></a>
<span id="l336">            if (!shc.sslConfig.isAvailable(SSLExtension.CH_PRE_SHARED_KEY)) {</span><a href="#l336"></a>
<span id="l337">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l337"></a>
<span id="l338">                    SSLLogger.fine(</span><a href="#l338"></a>
<span id="l339">                            &quot;Ignore unavailable pre_shared_key extension&quot;);</span><a href="#l339"></a>
<span id="l340">                }</span><a href="#l340"></a>
<span id="l341">                return;     // ignore the extension</span><a href="#l341"></a>
<span id="l342">            }</span><a href="#l342"></a>
<span id="l343"></span><a href="#l343"></a>
<span id="l344">            // Parse the extension.</span><a href="#l344"></a>
<span id="l345">            CHPreSharedKeySpec pskSpec = null;</span><a href="#l345"></a>
<span id="l346">            try {</span><a href="#l346"></a>
<span id="l347">                pskSpec = new CHPreSharedKeySpec(shc, buffer);</span><a href="#l347"></a>
<span id="l348">            } catch (IOException ioe) {</span><a href="#l348"></a>
<span id="l349">                throw shc.conContext.fatal(Alert.UNEXPECTED_MESSAGE, ioe);</span><a href="#l349"></a>
<span id="l350">            }</span><a href="#l350"></a>
<span id="l351"></span><a href="#l351"></a>
<span id="l352">            // The &quot;psk_key_exchange_modes&quot; extension should have been loaded.</span><a href="#l352"></a>
<span id="l353">            if (!shc.handshakeExtensions.containsKey(</span><a href="#l353"></a>
<span id="l354">                    SSLExtension.PSK_KEY_EXCHANGE_MODES)) {</span><a href="#l354"></a>
<span id="l355">                throw shc.conContext.fatal(Alert.ILLEGAL_PARAMETER,</span><a href="#l355"></a>
<span id="l356">                        &quot;Client sent PSK but not PSK modes, or the PSK &quot; +</span><a href="#l356"></a>
<span id="l357">                        &quot;extension is not the last extension&quot;);</span><a href="#l357"></a>
<span id="l358">            }</span><a href="#l358"></a>
<span id="l359"></span><a href="#l359"></a>
<span id="l360">            // error if id and binder lists are not the same length</span><a href="#l360"></a>
<span id="l361">            if (pskSpec.identities.size() != pskSpec.binders.size()) {</span><a href="#l361"></a>
<span id="l362">                throw shc.conContext.fatal(Alert.ILLEGAL_PARAMETER,</span><a href="#l362"></a>
<span id="l363">                        &quot;PSK extension has incorrect number of binders&quot;);</span><a href="#l363"></a>
<span id="l364">            }</span><a href="#l364"></a>
<span id="l365"></span><a href="#l365"></a>
<span id="l366">            if (shc.isResumption) {     // resumingSession may not be set</span><a href="#l366"></a>
<span id="l367">                SSLSessionContextImpl sessionCache = (SSLSessionContextImpl)</span><a href="#l367"></a>
<span id="l368">                        shc.sslContext.engineGetServerSessionContext();</span><a href="#l368"></a>
<span id="l369">                int idIndex = 0;</span><a href="#l369"></a>
<span id="l370">                for (PskIdentity requestedId : pskSpec.identities) {</span><a href="#l370"></a>
<span id="l371">                    SSLSessionImpl s = sessionCache.pull(requestedId.identity);</span><a href="#l371"></a>
<span id="l372">                    if (s != null &amp;&amp; canRejoin(clientHello, shc, s)) {</span><a href="#l372"></a>
<span id="l373">                        if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l373"></a>
<span id="l374">                            SSLLogger.fine(&quot;Resuming session: &quot;, s);</span><a href="#l374"></a>
<span id="l375">                        }</span><a href="#l375"></a>
<span id="l376"></span><a href="#l376"></a>
<span id="l377">                        // binder will be checked later</span><a href="#l377"></a>
<span id="l378">                        shc.resumingSession = s;</span><a href="#l378"></a>
<span id="l379">                        shc.handshakeExtensions.put(SH_PRE_SHARED_KEY,</span><a href="#l379"></a>
<span id="l380">                            new SHPreSharedKeySpec(idIndex));   // for the index</span><a href="#l380"></a>
<span id="l381">                        break;</span><a href="#l381"></a>
<span id="l382">                    }</span><a href="#l382"></a>
<span id="l383"></span><a href="#l383"></a>
<span id="l384">                    ++idIndex;</span><a href="#l384"></a>
<span id="l385">                }</span><a href="#l385"></a>
<span id="l386"></span><a href="#l386"></a>
<span id="l387">                if (idIndex == pskSpec.identities.size()) {</span><a href="#l387"></a>
<span id="l388">                    // no resumable session</span><a href="#l388"></a>
<span id="l389">                    shc.isResumption = false;</span><a href="#l389"></a>
<span id="l390">                    shc.resumingSession = null;</span><a href="#l390"></a>
<span id="l391">                }</span><a href="#l391"></a>
<span id="l392">            }</span><a href="#l392"></a>
<span id="l393"></span><a href="#l393"></a>
<span id="l394">            // update the context</span><a href="#l394"></a>
<span id="l395">            shc.handshakeExtensions.put(</span><a href="#l395"></a>
<span id="l396">                SSLExtension.CH_PRE_SHARED_KEY, pskSpec);</span><a href="#l396"></a>
<span id="l397">        }</span><a href="#l397"></a>
<span id="l398">    }</span><a href="#l398"></a>
<span id="l399"></span><a href="#l399"></a>
<span id="l400">    private static boolean canRejoin(ClientHelloMessage clientHello,</span><a href="#l400"></a>
<span id="l401">        ServerHandshakeContext shc, SSLSessionImpl s) {</span><a href="#l401"></a>
<span id="l402"></span><a href="#l402"></a>
<span id="l403">        boolean result = s.isRejoinable() &amp;&amp; (s.getPreSharedKey() != null);</span><a href="#l403"></a>
<span id="l404"></span><a href="#l404"></a>
<span id="l405">        // Check protocol version</span><a href="#l405"></a>
<span id="l406">        if (result &amp;&amp; s.getProtocolVersion() != shc.negotiatedProtocol) {</span><a href="#l406"></a>
<span id="l407">            if (SSLLogger.isOn &amp;&amp;</span><a href="#l407"></a>
<span id="l408">                SSLLogger.isOn(&quot;ssl,handshake,verbose&quot;)) {</span><a href="#l408"></a>
<span id="l409"></span><a href="#l409"></a>
<span id="l410">                SSLLogger.finest(&quot;Can't resume, incorrect protocol version&quot;);</span><a href="#l410"></a>
<span id="l411">            }</span><a href="#l411"></a>
<span id="l412">            result = false;</span><a href="#l412"></a>
<span id="l413">        }</span><a href="#l413"></a>
<span id="l414"></span><a href="#l414"></a>
<span id="l415">        // Make sure that the server handshake context's localSupportedSignAlgs</span><a href="#l415"></a>
<span id="l416">        // field is populated.  This is particularly important when</span><a href="#l416"></a>
<span id="l417">        // client authentication was used in an initial session and it is</span><a href="#l417"></a>
<span id="l418">        // now being resumed.</span><a href="#l418"></a>
<span id="l419">        if (shc.localSupportedSignAlgs == null) {</span><a href="#l419"></a>
<span id="l420">            shc.localSupportedSignAlgs =</span><a href="#l420"></a>
<span id="l421">                    SignatureScheme.getSupportedAlgorithms(</span><a href="#l421"></a>
<span id="l422">                            shc.sslConfig,</span><a href="#l422"></a>
<span id="l423">                            shc.algorithmConstraints, shc.activeProtocols);</span><a href="#l423"></a>
<span id="l424">        }</span><a href="#l424"></a>
<span id="l425"></span><a href="#l425"></a>
<span id="l426">        // Validate the required client authentication.</span><a href="#l426"></a>
<span id="l427">        if (result &amp;&amp;</span><a href="#l427"></a>
<span id="l428">            (shc.sslConfig.clientAuthType == CLIENT_AUTH_REQUIRED)) {</span><a href="#l428"></a>
<span id="l429">            try {</span><a href="#l429"></a>
<span id="l430">                s.getPeerPrincipal();</span><a href="#l430"></a>
<span id="l431">            } catch (SSLPeerUnverifiedException e) {</span><a href="#l431"></a>
<span id="l432">                if (SSLLogger.isOn &amp;&amp;</span><a href="#l432"></a>
<span id="l433">                        SSLLogger.isOn(&quot;ssl,handshake,verbose&quot;)) {</span><a href="#l433"></a>
<span id="l434">                    SSLLogger.finest(</span><a href="#l434"></a>
<span id="l435">                        &quot;Can't resume, &quot; +</span><a href="#l435"></a>
<span id="l436">                        &quot;client authentication is required&quot;);</span><a href="#l436"></a>
<span id="l437">                }</span><a href="#l437"></a>
<span id="l438">                result = false;</span><a href="#l438"></a>
<span id="l439">            }</span><a href="#l439"></a>
<span id="l440"></span><a href="#l440"></a>
<span id="l441">            // Make sure the list of supported signature algorithms matches</span><a href="#l441"></a>
<span id="l442">            Collection&lt;SignatureScheme&gt; sessionSigAlgs =</span><a href="#l442"></a>
<span id="l443">                s.getLocalSupportedSignatureSchemes();</span><a href="#l443"></a>
<span id="l444">            if (result &amp;&amp;</span><a href="#l444"></a>
<span id="l445">                !shc.localSupportedSignAlgs.containsAll(sessionSigAlgs)) {</span><a href="#l445"></a>
<span id="l446"></span><a href="#l446"></a>
<span id="l447">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l447"></a>
<span id="l448">                    SSLLogger.fine(&quot;Can't resume. Session uses different &quot; +</span><a href="#l448"></a>
<span id="l449">                        &quot;signature algorithms&quot;);</span><a href="#l449"></a>
<span id="l450">                }</span><a href="#l450"></a>
<span id="l451">                result = false;</span><a href="#l451"></a>
<span id="l452">            }</span><a href="#l452"></a>
<span id="l453">        }</span><a href="#l453"></a>
<span id="l454"></span><a href="#l454"></a>
<span id="l455">        // ensure that the endpoint identification algorithm matches the</span><a href="#l455"></a>
<span id="l456">        // one in the session</span><a href="#l456"></a>
<span id="l457">        String identityAlg = shc.sslConfig.identificationProtocol;</span><a href="#l457"></a>
<span id="l458">        if (result &amp;&amp; identityAlg != null) {</span><a href="#l458"></a>
<span id="l459">            String sessionIdentityAlg = s.getIdentificationProtocol();</span><a href="#l459"></a>
<span id="l460">            if (!identityAlg.equalsIgnoreCase(sessionIdentityAlg)) {</span><a href="#l460"></a>
<span id="l461">                if (SSLLogger.isOn &amp;&amp;</span><a href="#l461"></a>
<span id="l462">                    SSLLogger.isOn(&quot;ssl,handshake,verbose&quot;)) {</span><a href="#l462"></a>
<span id="l463"></span><a href="#l463"></a>
<span id="l464">                    SSLLogger.finest(&quot;Can't resume, endpoint id&quot; +</span><a href="#l464"></a>
<span id="l465">                        &quot; algorithm does not match, requested: &quot; +</span><a href="#l465"></a>
<span id="l466">                        identityAlg + &quot;, cached: &quot; + sessionIdentityAlg);</span><a href="#l466"></a>
<span id="l467">                }</span><a href="#l467"></a>
<span id="l468">                result = false;</span><a href="#l468"></a>
<span id="l469">            }</span><a href="#l469"></a>
<span id="l470">        }</span><a href="#l470"></a>
<span id="l471"></span><a href="#l471"></a>
<span id="l472">        // Ensure cipher suite can be negotiated</span><a href="#l472"></a>
<span id="l473">        if (result &amp;&amp; (!shc.isNegotiable(s.getSuite()) ||</span><a href="#l473"></a>
<span id="l474">            !clientHello.cipherSuites.contains(s.getSuite()))) {</span><a href="#l474"></a>
<span id="l475">            if (SSLLogger.isOn &amp;&amp;</span><a href="#l475"></a>
<span id="l476">                    SSLLogger.isOn(&quot;ssl,handshake,verbose&quot;)) {</span><a href="#l476"></a>
<span id="l477">                SSLLogger.finest(</span><a href="#l477"></a>
<span id="l478">                    &quot;Can't resume, unavailable session cipher suite&quot;);</span><a href="#l478"></a>
<span id="l479">            }</span><a href="#l479"></a>
<span id="l480">            result = false;</span><a href="#l480"></a>
<span id="l481">        }</span><a href="#l481"></a>
<span id="l482"></span><a href="#l482"></a>
<span id="l483">        return result;</span><a href="#l483"></a>
<span id="l484">    }</span><a href="#l484"></a>
<span id="l485"></span><a href="#l485"></a>
<span id="l486">    private static final</span><a href="#l486"></a>
<span id="l487">            class CHPreSharedKeyUpdate implements HandshakeConsumer {</span><a href="#l487"></a>
<span id="l488">        // Prevent instantiation of this class.</span><a href="#l488"></a>
<span id="l489">        private CHPreSharedKeyUpdate() {</span><a href="#l489"></a>
<span id="l490">            // blank</span><a href="#l490"></a>
<span id="l491">        }</span><a href="#l491"></a>
<span id="l492"></span><a href="#l492"></a>
<span id="l493">        @Override</span><a href="#l493"></a>
<span id="l494">        public void consume(ConnectionContext context,</span><a href="#l494"></a>
<span id="l495">                HandshakeMessage message) throws IOException {</span><a href="#l495"></a>
<span id="l496">            ServerHandshakeContext shc = (ServerHandshakeContext)context;</span><a href="#l496"></a>
<span id="l497">            if (!shc.isResumption || shc.resumingSession == null) {</span><a href="#l497"></a>
<span id="l498">                // not resuming---nothing to do</span><a href="#l498"></a>
<span id="l499">                return;</span><a href="#l499"></a>
<span id="l500">            }</span><a href="#l500"></a>
<span id="l501"></span><a href="#l501"></a>
<span id="l502">            CHPreSharedKeySpec chPsk = (CHPreSharedKeySpec)</span><a href="#l502"></a>
<span id="l503">                    shc.handshakeExtensions.get(SSLExtension.CH_PRE_SHARED_KEY);</span><a href="#l503"></a>
<span id="l504">            SHPreSharedKeySpec shPsk = (SHPreSharedKeySpec)</span><a href="#l504"></a>
<span id="l505">                    shc.handshakeExtensions.get(SSLExtension.SH_PRE_SHARED_KEY);</span><a href="#l505"></a>
<span id="l506">            if (chPsk == null || shPsk == null) {</span><a href="#l506"></a>
<span id="l507">                throw shc.conContext.fatal(Alert.INTERNAL_ERROR,</span><a href="#l507"></a>
<span id="l508">                        &quot;Required extensions are unavailable&quot;);</span><a href="#l508"></a>
<span id="l509">            }</span><a href="#l509"></a>
<span id="l510"></span><a href="#l510"></a>
<span id="l511">            byte[] binder = chPsk.binders.get(shPsk.selectedIdentity);</span><a href="#l511"></a>
<span id="l512"></span><a href="#l512"></a>
<span id="l513">            // set up PSK binder hash</span><a href="#l513"></a>
<span id="l514">            HandshakeHash pskBinderHash = shc.handshakeHash.copy();</span><a href="#l514"></a>
<span id="l515">            byte[] lastMessage = pskBinderHash.removeLastReceived();</span><a href="#l515"></a>
<span id="l516">            ByteBuffer messageBuf = ByteBuffer.wrap(lastMessage);</span><a href="#l516"></a>
<span id="l517">            // skip the type and length</span><a href="#l517"></a>
<span id="l518">            messageBuf.position(4);</span><a href="#l518"></a>
<span id="l519">            // read to find the beginning of the binders</span><a href="#l519"></a>
<span id="l520">            ClientHelloMessage.readPartial(shc.conContext, messageBuf);</span><a href="#l520"></a>
<span id="l521">            int length = messageBuf.position();</span><a href="#l521"></a>
<span id="l522">            messageBuf.position(0);</span><a href="#l522"></a>
<span id="l523">            pskBinderHash.receive(messageBuf, length);</span><a href="#l523"></a>
<span id="l524"></span><a href="#l524"></a>
<span id="l525">            checkBinder(shc, shc.resumingSession, pskBinderHash, binder);</span><a href="#l525"></a>
<span id="l526">        }</span><a href="#l526"></a>
<span id="l527">    }</span><a href="#l527"></a>
<span id="l528"></span><a href="#l528"></a>
<span id="l529">    private static void checkBinder(ServerHandshakeContext shc,</span><a href="#l529"></a>
<span id="l530">            SSLSessionImpl session,</span><a href="#l530"></a>
<span id="l531">            HandshakeHash pskBinderHash, byte[] binder) throws IOException {</span><a href="#l531"></a>
<span id="l532">        SecretKey psk = session.getPreSharedKey();</span><a href="#l532"></a>
<span id="l533">        if (psk == null) {</span><a href="#l533"></a>
<span id="l534">            throw shc.conContext.fatal(Alert.INTERNAL_ERROR,</span><a href="#l534"></a>
<span id="l535">                    &quot;Session has no PSK&quot;);</span><a href="#l535"></a>
<span id="l536">        }</span><a href="#l536"></a>
<span id="l537"></span><a href="#l537"></a>
<span id="l538">        SecretKey binderKey = deriveBinderKey(shc, psk, session);</span><a href="#l538"></a>
<span id="l539">        byte[] computedBinder =</span><a href="#l539"></a>
<span id="l540">                computeBinder(shc, binderKey, session, pskBinderHash);</span><a href="#l540"></a>
<span id="l541">        if (!MessageDigest.isEqual(binder, computedBinder)) {</span><a href="#l541"></a>
<span id="l542">            throw shc.conContext.fatal(Alert.ILLEGAL_PARAMETER,</span><a href="#l542"></a>
<span id="l543">                    &quot;Incorect PSK binder value&quot;);</span><a href="#l543"></a>
<span id="l544">        }</span><a href="#l544"></a>
<span id="l545">    }</span><a href="#l545"></a>
<span id="l546"></span><a href="#l546"></a>
<span id="l547">    // Class that produces partial messages used to compute binder hash</span><a href="#l547"></a>
<span id="l548">    static final class PartialClientHelloMessage extends HandshakeMessage {</span><a href="#l548"></a>
<span id="l549"></span><a href="#l549"></a>
<span id="l550">        private final ClientHello.ClientHelloMessage msg;</span><a href="#l550"></a>
<span id="l551">        private final CHPreSharedKeySpec psk;</span><a href="#l551"></a>
<span id="l552"></span><a href="#l552"></a>
<span id="l553">        PartialClientHelloMessage(HandshakeContext ctx,</span><a href="#l553"></a>
<span id="l554">                                  ClientHello.ClientHelloMessage msg,</span><a href="#l554"></a>
<span id="l555">                                  CHPreSharedKeySpec psk) {</span><a href="#l555"></a>
<span id="l556">            super(ctx);</span><a href="#l556"></a>
<span id="l557"></span><a href="#l557"></a>
<span id="l558">            this.msg = msg;</span><a href="#l558"></a>
<span id="l559">            this.psk = psk;</span><a href="#l559"></a>
<span id="l560">        }</span><a href="#l560"></a>
<span id="l561"></span><a href="#l561"></a>
<span id="l562">        @Override</span><a href="#l562"></a>
<span id="l563">        SSLHandshake handshakeType() {</span><a href="#l563"></a>
<span id="l564">            return msg.handshakeType();</span><a href="#l564"></a>
<span id="l565">        }</span><a href="#l565"></a>
<span id="l566"></span><a href="#l566"></a>
<span id="l567">        private int pskTotalLength() {</span><a href="#l567"></a>
<span id="l568">            return psk.getIdsEncodedLength() +</span><a href="#l568"></a>
<span id="l569">                psk.getBindersEncodedLength() + 8;</span><a href="#l569"></a>
<span id="l570">        }</span><a href="#l570"></a>
<span id="l571"></span><a href="#l571"></a>
<span id="l572">        @Override</span><a href="#l572"></a>
<span id="l573">        int messageLength() {</span><a href="#l573"></a>
<span id="l574"></span><a href="#l574"></a>
<span id="l575">            if (msg.extensions.get(SSLExtension.CH_PRE_SHARED_KEY) != null) {</span><a href="#l575"></a>
<span id="l576">                return msg.messageLength();</span><a href="#l576"></a>
<span id="l577">            } else {</span><a href="#l577"></a>
<span id="l578">                return msg.messageLength() + pskTotalLength();</span><a href="#l578"></a>
<span id="l579">            }</span><a href="#l579"></a>
<span id="l580">        }</span><a href="#l580"></a>
<span id="l581"></span><a href="#l581"></a>
<span id="l582">        @Override</span><a href="#l582"></a>
<span id="l583">        void send(HandshakeOutStream hos) throws IOException {</span><a href="#l583"></a>
<span id="l584">            msg.sendCore(hos);</span><a href="#l584"></a>
<span id="l585"></span><a href="#l585"></a>
<span id="l586">            // complete extensions</span><a href="#l586"></a>
<span id="l587">            int extsLen = msg.extensions.length();</span><a href="#l587"></a>
<span id="l588">            if (msg.extensions.get(SSLExtension.CH_PRE_SHARED_KEY) == null) {</span><a href="#l588"></a>
<span id="l589">                extsLen += pskTotalLength();</span><a href="#l589"></a>
<span id="l590">            }</span><a href="#l590"></a>
<span id="l591">            hos.putInt16(extsLen - 2);</span><a href="#l591"></a>
<span id="l592">            // write the complete extensions</span><a href="#l592"></a>
<span id="l593">            for (SSLExtension ext : SSLExtension.values()) {</span><a href="#l593"></a>
<span id="l594">                byte[] extData = msg.extensions.get(ext);</span><a href="#l594"></a>
<span id="l595">                if (extData == null) {</span><a href="#l595"></a>
<span id="l596">                    continue;</span><a href="#l596"></a>
<span id="l597">                }</span><a href="#l597"></a>
<span id="l598">                // the PSK could be there from an earlier round</span><a href="#l598"></a>
<span id="l599">                if (ext == SSLExtension.CH_PRE_SHARED_KEY) {</span><a href="#l599"></a>
<span id="l600">                    continue;</span><a href="#l600"></a>
<span id="l601">                }</span><a href="#l601"></a>
<span id="l602">                int extID = ext.id;</span><a href="#l602"></a>
<span id="l603">                hos.putInt16(extID);</span><a href="#l603"></a>
<span id="l604">                hos.putBytes16(extData);</span><a href="#l604"></a>
<span id="l605">            }</span><a href="#l605"></a>
<span id="l606"></span><a href="#l606"></a>
<span id="l607">            // partial PSK extension</span><a href="#l607"></a>
<span id="l608">            int extID = SSLExtension.CH_PRE_SHARED_KEY.id;</span><a href="#l608"></a>
<span id="l609">            hos.putInt16(extID);</span><a href="#l609"></a>
<span id="l610">            byte[] encodedPsk = psk.getEncoded();</span><a href="#l610"></a>
<span id="l611">            hos.putInt16(encodedPsk.length);</span><a href="#l611"></a>
<span id="l612">            hos.write(encodedPsk, 0, psk.getIdsEncodedLength() + 2);</span><a href="#l612"></a>
<span id="l613">        }</span><a href="#l613"></a>
<span id="l614">    }</span><a href="#l614"></a>
<span id="l615"></span><a href="#l615"></a>
<span id="l616">    private static final</span><a href="#l616"></a>
<span id="l617">            class CHPreSharedKeyProducer implements HandshakeProducer {</span><a href="#l617"></a>
<span id="l618">        // Prevent instantiation of this class.</span><a href="#l618"></a>
<span id="l619">        private CHPreSharedKeyProducer() {</span><a href="#l619"></a>
<span id="l620">            // blank</span><a href="#l620"></a>
<span id="l621">        }</span><a href="#l621"></a>
<span id="l622"></span><a href="#l622"></a>
<span id="l623">        @Override</span><a href="#l623"></a>
<span id="l624">        public byte[] produce(ConnectionContext context,</span><a href="#l624"></a>
<span id="l625">                HandshakeMessage message) throws IOException {</span><a href="#l625"></a>
<span id="l626"></span><a href="#l626"></a>
<span id="l627">            // The producing happens in client side only.</span><a href="#l627"></a>
<span id="l628">            ClientHandshakeContext chc = (ClientHandshakeContext)context;</span><a href="#l628"></a>
<span id="l629">            if (!chc.isResumption || chc.resumingSession == null) {</span><a href="#l629"></a>
<span id="l630">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l630"></a>
<span id="l631">                    SSLLogger.fine(&quot;No session to resume.&quot;);</span><a href="#l631"></a>
<span id="l632">                }</span><a href="#l632"></a>
<span id="l633">                return null;</span><a href="#l633"></a>
<span id="l634">            }</span><a href="#l634"></a>
<span id="l635"></span><a href="#l635"></a>
<span id="l636">            // Make sure the list of supported signature algorithms matches</span><a href="#l636"></a>
<span id="l637">            Collection&lt;SignatureScheme&gt; sessionSigAlgs =</span><a href="#l637"></a>
<span id="l638">                chc.resumingSession.getLocalSupportedSignatureSchemes();</span><a href="#l638"></a>
<span id="l639">            if (!chc.localSupportedSignAlgs.containsAll(sessionSigAlgs)) {</span><a href="#l639"></a>
<span id="l640">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l640"></a>
<span id="l641">                    SSLLogger.fine(&quot;Existing session uses different &quot; +</span><a href="#l641"></a>
<span id="l642">                        &quot;signature algorithms&quot;);</span><a href="#l642"></a>
<span id="l643">                }</span><a href="#l643"></a>
<span id="l644">                return null;</span><a href="#l644"></a>
<span id="l645">            }</span><a href="#l645"></a>
<span id="l646"></span><a href="#l646"></a>
<span id="l647">            // The session must have a pre-shared key</span><a href="#l647"></a>
<span id="l648">            SecretKey psk = chc.resumingSession.getPreSharedKey();</span><a href="#l648"></a>
<span id="l649">            if (psk == null) {</span><a href="#l649"></a>
<span id="l650">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l650"></a>
<span id="l651">                    SSLLogger.fine(&quot;Existing session has no PSK.&quot;);</span><a href="#l651"></a>
<span id="l652">                }</span><a href="#l652"></a>
<span id="l653">                return null;</span><a href="#l653"></a>
<span id="l654">            }</span><a href="#l654"></a>
<span id="l655"></span><a href="#l655"></a>
<span id="l656">            // The PSK ID can only be used in one connections, but this method</span><a href="#l656"></a>
<span id="l657">            // may be called twice in a connection if the server sends HRR.</span><a href="#l657"></a>
<span id="l658">            // ID is saved in the context so it can be used in the second call.</span><a href="#l658"></a>
<span id="l659">            if (chc.pskIdentity == null) {</span><a href="#l659"></a>
<span id="l660">                chc.pskIdentity = chc.resumingSession.consumePskIdentity();</span><a href="#l660"></a>
<span id="l661">            }</span><a href="#l661"></a>
<span id="l662"></span><a href="#l662"></a>
<span id="l663">            if (chc.pskIdentity == null) {</span><a href="#l663"></a>
<span id="l664">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l664"></a>
<span id="l665">                    SSLLogger.fine(</span><a href="#l665"></a>
<span id="l666">                        &quot;PSK has no identity, or identity was already used&quot;);</span><a href="#l666"></a>
<span id="l667">                }</span><a href="#l667"></a>
<span id="l668">                return null;</span><a href="#l668"></a>
<span id="l669">            }</span><a href="#l669"></a>
<span id="l670"></span><a href="#l670"></a>
<span id="l671">            //The session cannot be used again. Remove it from the cache.</span><a href="#l671"></a>
<span id="l672">            SSLSessionContextImpl sessionCache = (SSLSessionContextImpl)</span><a href="#l672"></a>
<span id="l673">                chc.sslContext.engineGetClientSessionContext();</span><a href="#l673"></a>
<span id="l674">            sessionCache.remove(chc.resumingSession.getSessionId());</span><a href="#l674"></a>
<span id="l675"></span><a href="#l675"></a>
<span id="l676">            if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l676"></a>
<span id="l677">                SSLLogger.fine(</span><a href="#l677"></a>
<span id="l678">                    &quot;Found resumable session. Preparing PSK message.&quot;);</span><a href="#l678"></a>
<span id="l679">            }</span><a href="#l679"></a>
<span id="l680"></span><a href="#l680"></a>
<span id="l681">            List&lt;PskIdentity&gt; identities = new ArrayList&lt;&gt;();</span><a href="#l681"></a>
<span id="l682">            int ageMillis = (int)(System.currentTimeMillis() -</span><a href="#l682"></a>
<span id="l683">                    chc.resumingSession.getTicketCreationTime());</span><a href="#l683"></a>
<span id="l684">            int obfuscatedAge =</span><a href="#l684"></a>
<span id="l685">                    ageMillis + chc.resumingSession.getTicketAgeAdd();</span><a href="#l685"></a>
<span id="l686">            identities.add(new PskIdentity(chc.pskIdentity, obfuscatedAge));</span><a href="#l686"></a>
<span id="l687"></span><a href="#l687"></a>
<span id="l688">            SecretKey binderKey =</span><a href="#l688"></a>
<span id="l689">                    deriveBinderKey(chc, psk, chc.resumingSession);</span><a href="#l689"></a>
<span id="l690">            ClientHelloMessage clientHello = (ClientHelloMessage)message;</span><a href="#l690"></a>
<span id="l691">            CHPreSharedKeySpec pskPrototype = createPskPrototype(</span><a href="#l691"></a>
<span id="l692">                chc.resumingSession.getSuite().hashAlg.hashLength, identities);</span><a href="#l692"></a>
<span id="l693">            HandshakeHash pskBinderHash = chc.handshakeHash.copy();</span><a href="#l693"></a>
<span id="l694"></span><a href="#l694"></a>
<span id="l695">            byte[] binder = computeBinder(chc, binderKey, pskBinderHash,</span><a href="#l695"></a>
<span id="l696">                    chc.resumingSession, chc, clientHello, pskPrototype);</span><a href="#l696"></a>
<span id="l697"></span><a href="#l697"></a>
<span id="l698">            List&lt;byte[]&gt; binders = new ArrayList&lt;&gt;();</span><a href="#l698"></a>
<span id="l699">            binders.add(binder);</span><a href="#l699"></a>
<span id="l700"></span><a href="#l700"></a>
<span id="l701">            CHPreSharedKeySpec pskMessage =</span><a href="#l701"></a>
<span id="l702">                    new CHPreSharedKeySpec(identities, binders);</span><a href="#l702"></a>
<span id="l703">            chc.handshakeExtensions.put(CH_PRE_SHARED_KEY, pskMessage);</span><a href="#l703"></a>
<span id="l704">            return pskMessage.getEncoded();</span><a href="#l704"></a>
<span id="l705">        }</span><a href="#l705"></a>
<span id="l706"></span><a href="#l706"></a>
<span id="l707">        private CHPreSharedKeySpec createPskPrototype(</span><a href="#l707"></a>
<span id="l708">                int hashLength, List&lt;PskIdentity&gt; identities) {</span><a href="#l708"></a>
<span id="l709">            List&lt;byte[]&gt; binders = new ArrayList&lt;&gt;();</span><a href="#l709"></a>
<span id="l710">            byte[] binderProto = new byte[hashLength];</span><a href="#l710"></a>
<span id="l711">            for (PskIdentity curId : identities) {</span><a href="#l711"></a>
<span id="l712">                binders.add(binderProto);</span><a href="#l712"></a>
<span id="l713">            }</span><a href="#l713"></a>
<span id="l714"></span><a href="#l714"></a>
<span id="l715">            return new CHPreSharedKeySpec(identities, binders);</span><a href="#l715"></a>
<span id="l716">        }</span><a href="#l716"></a>
<span id="l717">    }</span><a href="#l717"></a>
<span id="l718"></span><a href="#l718"></a>
<span id="l719">    private static byte[] computeBinder(</span><a href="#l719"></a>
<span id="l720">            HandshakeContext context, SecretKey binderKey,</span><a href="#l720"></a>
<span id="l721">            SSLSessionImpl session,</span><a href="#l721"></a>
<span id="l722">            HandshakeHash pskBinderHash) throws IOException {</span><a href="#l722"></a>
<span id="l723"></span><a href="#l723"></a>
<span id="l724">        pskBinderHash.determine(</span><a href="#l724"></a>
<span id="l725">                session.getProtocolVersion(), session.getSuite());</span><a href="#l725"></a>
<span id="l726">        pskBinderHash.update();</span><a href="#l726"></a>
<span id="l727">        byte[] digest = pskBinderHash.digest();</span><a href="#l727"></a>
<span id="l728"></span><a href="#l728"></a>
<span id="l729">        return computeBinder(context, binderKey, session, digest);</span><a href="#l729"></a>
<span id="l730">    }</span><a href="#l730"></a>
<span id="l731"></span><a href="#l731"></a>
<span id="l732">    private static byte[] computeBinder(</span><a href="#l732"></a>
<span id="l733">            HandshakeContext context, SecretKey binderKey,</span><a href="#l733"></a>
<span id="l734">            HandshakeHash hash, SSLSessionImpl session,</span><a href="#l734"></a>
<span id="l735">            HandshakeContext ctx, ClientHello.ClientHelloMessage hello,</span><a href="#l735"></a>
<span id="l736">            CHPreSharedKeySpec pskPrototype) throws IOException {</span><a href="#l736"></a>
<span id="l737"></span><a href="#l737"></a>
<span id="l738">        PartialClientHelloMessage partialMsg =</span><a href="#l738"></a>
<span id="l739">                new PartialClientHelloMessage(ctx, hello, pskPrototype);</span><a href="#l739"></a>
<span id="l740"></span><a href="#l740"></a>
<span id="l741">        SSLEngineOutputRecord record = new SSLEngineOutputRecord(hash);</span><a href="#l741"></a>
<span id="l742">        HandshakeOutStream hos = new HandshakeOutStream(record);</span><a href="#l742"></a>
<span id="l743">        partialMsg.write(hos);</span><a href="#l743"></a>
<span id="l744"></span><a href="#l744"></a>
<span id="l745">        hash.determine(session.getProtocolVersion(), session.getSuite());</span><a href="#l745"></a>
<span id="l746">        hash.update();</span><a href="#l746"></a>
<span id="l747">        byte[] digest = hash.digest();</span><a href="#l747"></a>
<span id="l748"></span><a href="#l748"></a>
<span id="l749">        return computeBinder(context, binderKey, session, digest);</span><a href="#l749"></a>
<span id="l750">    }</span><a href="#l750"></a>
<span id="l751"></span><a href="#l751"></a>
<span id="l752">    private static byte[] computeBinder(HandshakeContext context,</span><a href="#l752"></a>
<span id="l753">            SecretKey binderKey,</span><a href="#l753"></a>
<span id="l754">            SSLSessionImpl session, byte[] digest) throws IOException {</span><a href="#l754"></a>
<span id="l755">        try {</span><a href="#l755"></a>
<span id="l756">            CipherSuite.HashAlg hashAlg = session.getSuite().hashAlg;</span><a href="#l756"></a>
<span id="l757">            HKDF hkdf = new HKDF(hashAlg.name);</span><a href="#l757"></a>
<span id="l758">            byte[] label = (&quot;tls13 finished&quot;).getBytes();</span><a href="#l758"></a>
<span id="l759">            byte[] hkdfInfo = SSLSecretDerivation.createHkdfInfo(</span><a href="#l759"></a>
<span id="l760">                    label, new byte[0], hashAlg.hashLength);</span><a href="#l760"></a>
<span id="l761">            SecretKey finishedKey = hkdf.expand(</span><a href="#l761"></a>
<span id="l762">                    binderKey, hkdfInfo, hashAlg.hashLength, &quot;TlsBinderKey&quot;);</span><a href="#l762"></a>
<span id="l763"></span><a href="#l763"></a>
<span id="l764">            String hmacAlg =</span><a href="#l764"></a>
<span id="l765">                &quot;Hmac&quot; + hashAlg.name.replace(&quot;-&quot;, &quot;&quot;);</span><a href="#l765"></a>
<span id="l766">            try {</span><a href="#l766"></a>
<span id="l767">                Mac hmac = JsseJce.getMac(hmacAlg);</span><a href="#l767"></a>
<span id="l768">                hmac.init(finishedKey);</span><a href="#l768"></a>
<span id="l769">                return hmac.doFinal(digest);</span><a href="#l769"></a>
<span id="l770">            } catch (NoSuchAlgorithmException | InvalidKeyException ex) {</span><a href="#l770"></a>
<span id="l771">                throw context.conContext.fatal(Alert.INTERNAL_ERROR, ex);</span><a href="#l771"></a>
<span id="l772">            }</span><a href="#l772"></a>
<span id="l773">        } catch (GeneralSecurityException ex) {</span><a href="#l773"></a>
<span id="l774">            throw context.conContext.fatal(Alert.INTERNAL_ERROR, ex);</span><a href="#l774"></a>
<span id="l775">        }</span><a href="#l775"></a>
<span id="l776">    }</span><a href="#l776"></a>
<span id="l777"></span><a href="#l777"></a>
<span id="l778">    private static SecretKey deriveBinderKey(HandshakeContext context,</span><a href="#l778"></a>
<span id="l779">            SecretKey psk, SSLSessionImpl session) throws IOException {</span><a href="#l779"></a>
<span id="l780">        try {</span><a href="#l780"></a>
<span id="l781">            CipherSuite.HashAlg hashAlg = session.getSuite().hashAlg;</span><a href="#l781"></a>
<span id="l782">            HKDF hkdf = new HKDF(hashAlg.name);</span><a href="#l782"></a>
<span id="l783">            byte[] zeros = new byte[hashAlg.hashLength];</span><a href="#l783"></a>
<span id="l784">            SecretKey earlySecret = hkdf.extract(zeros, psk, &quot;TlsEarlySecret&quot;);</span><a href="#l784"></a>
<span id="l785"></span><a href="#l785"></a>
<span id="l786">            byte[] label = (&quot;tls13 res binder&quot;).getBytes();</span><a href="#l786"></a>
<span id="l787">            MessageDigest md = MessageDigest.getInstance(hashAlg.name);</span><a href="#l787"></a>
<span id="l788">            byte[] hkdfInfo = SSLSecretDerivation.createHkdfInfo(</span><a href="#l788"></a>
<span id="l789">                    label, md.digest(new byte[0]), hashAlg.hashLength);</span><a href="#l789"></a>
<span id="l790">            return hkdf.expand(earlySecret,</span><a href="#l790"></a>
<span id="l791">                    hkdfInfo, hashAlg.hashLength, &quot;TlsBinderKey&quot;);</span><a href="#l791"></a>
<span id="l792">        } catch (GeneralSecurityException ex) {</span><a href="#l792"></a>
<span id="l793">            throw context.conContext.fatal(Alert.INTERNAL_ERROR, ex);</span><a href="#l793"></a>
<span id="l794">        }</span><a href="#l794"></a>
<span id="l795">    }</span><a href="#l795"></a>
<span id="l796"></span><a href="#l796"></a>
<span id="l797">    private static final</span><a href="#l797"></a>
<span id="l798">            class CHPreSharedKeyAbsence implements HandshakeAbsence {</span><a href="#l798"></a>
<span id="l799">        @Override</span><a href="#l799"></a>
<span id="l800">        public void absent(ConnectionContext context,</span><a href="#l800"></a>
<span id="l801">                           HandshakeMessage message) throws IOException {</span><a href="#l801"></a>
<span id="l802"></span><a href="#l802"></a>
<span id="l803">            if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l803"></a>
<span id="l804">                SSLLogger.fine(</span><a href="#l804"></a>
<span id="l805">                &quot;Handling pre_shared_key absence.&quot;);</span><a href="#l805"></a>
<span id="l806">            }</span><a href="#l806"></a>
<span id="l807"></span><a href="#l807"></a>
<span id="l808">            ServerHandshakeContext shc = (ServerHandshakeContext)context;</span><a href="#l808"></a>
<span id="l809"></span><a href="#l809"></a>
<span id="l810">            // Resumption is only determined by PSK, when enabled</span><a href="#l810"></a>
<span id="l811">            shc.resumingSession = null;</span><a href="#l811"></a>
<span id="l812">            shc.isResumption = false;</span><a href="#l812"></a>
<span id="l813">        }</span><a href="#l813"></a>
<span id="l814">    }</span><a href="#l814"></a>
<span id="l815"></span><a href="#l815"></a>
<span id="l816">    private static final</span><a href="#l816"></a>
<span id="l817">            class SHPreSharedKeyConsumer implements ExtensionConsumer {</span><a href="#l817"></a>
<span id="l818">        // Prevent instantiation of this class.</span><a href="#l818"></a>
<span id="l819">        private SHPreSharedKeyConsumer() {</span><a href="#l819"></a>
<span id="l820">            // blank</span><a href="#l820"></a>
<span id="l821">        }</span><a href="#l821"></a>
<span id="l822"></span><a href="#l822"></a>
<span id="l823">        @Override</span><a href="#l823"></a>
<span id="l824">        public void consume(ConnectionContext context,</span><a href="#l824"></a>
<span id="l825">            HandshakeMessage message, ByteBuffer buffer) throws IOException {</span><a href="#l825"></a>
<span id="l826">            // The consuming happens in client side only.</span><a href="#l826"></a>
<span id="l827">            ClientHandshakeContext chc = (ClientHandshakeContext)context;</span><a href="#l827"></a>
<span id="l828"></span><a href="#l828"></a>
<span id="l829">            // Is it a response of the specific request?</span><a href="#l829"></a>
<span id="l830">            if (!chc.handshakeExtensions.containsKey(</span><a href="#l830"></a>
<span id="l831">                    SSLExtension.CH_PRE_SHARED_KEY)) {</span><a href="#l831"></a>
<span id="l832">                throw chc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,</span><a href="#l832"></a>
<span id="l833">                    &quot;Server sent unexpected pre_shared_key extension&quot;);</span><a href="#l833"></a>
<span id="l834">            }</span><a href="#l834"></a>
<span id="l835"></span><a href="#l835"></a>
<span id="l836">            SHPreSharedKeySpec shPsk = new SHPreSharedKeySpec(chc, buffer);</span><a href="#l836"></a>
<span id="l837">            if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l837"></a>
<span id="l838">                SSLLogger.fine(</span><a href="#l838"></a>
<span id="l839">                    &quot;Received pre_shared_key extension: &quot;, shPsk);</span><a href="#l839"></a>
<span id="l840">            }</span><a href="#l840"></a>
<span id="l841"></span><a href="#l841"></a>
<span id="l842">            if (shPsk.selectedIdentity != 0) {</span><a href="#l842"></a>
<span id="l843">                throw chc.conContext.fatal(Alert.ILLEGAL_PARAMETER,</span><a href="#l843"></a>
<span id="l844">                    &quot;Selected identity index is not in correct range.&quot;);</span><a href="#l844"></a>
<span id="l845">            }</span><a href="#l845"></a>
<span id="l846"></span><a href="#l846"></a>
<span id="l847">            if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l847"></a>
<span id="l848">                SSLLogger.fine(</span><a href="#l848"></a>
<span id="l849">                        &quot;Resuming session: &quot;, chc.resumingSession);</span><a href="#l849"></a>
<span id="l850">            }</span><a href="#l850"></a>
<span id="l851">        }</span><a href="#l851"></a>
<span id="l852">    }</span><a href="#l852"></a>
<span id="l853"></span><a href="#l853"></a>
<span id="l854">    private static final</span><a href="#l854"></a>
<span id="l855">            class SHPreSharedKeyAbsence implements HandshakeAbsence {</span><a href="#l855"></a>
<span id="l856">        @Override</span><a href="#l856"></a>
<span id="l857">        public void absent(ConnectionContext context,</span><a href="#l857"></a>
<span id="l858">                HandshakeMessage message) throws IOException {</span><a href="#l858"></a>
<span id="l859">            ClientHandshakeContext chc = (ClientHandshakeContext)context;</span><a href="#l859"></a>
<span id="l860"></span><a href="#l860"></a>
<span id="l861">            if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl,handshake&quot;)) {</span><a href="#l861"></a>
<span id="l862">                SSLLogger.fine(&quot;Handling pre_shared_key absence.&quot;);</span><a href="#l862"></a>
<span id="l863">            }</span><a href="#l863"></a>
<span id="l864"></span><a href="#l864"></a>
<span id="l865">            // The server refused to resume, or the client did not</span><a href="#l865"></a>
<span id="l866">            // request 1.3 resumption.</span><a href="#l866"></a>
<span id="l867">            chc.resumingSession = null;</span><a href="#l867"></a>
<span id="l868">            chc.isResumption = false;</span><a href="#l868"></a>
<span id="l869">        }</span><a href="#l869"></a>
<span id="l870">    }</span><a href="#l870"></a>
<span id="l871"></span><a href="#l871"></a>
<span id="l872">    private static final</span><a href="#l872"></a>
<span id="l873">            class SHPreSharedKeyProducer implements HandshakeProducer {</span><a href="#l873"></a>
<span id="l874">        // Prevent instantiation of this class.</span><a href="#l874"></a>
<span id="l875">        private SHPreSharedKeyProducer() {</span><a href="#l875"></a>
<span id="l876">            // blank</span><a href="#l876"></a>
<span id="l877">        }</span><a href="#l877"></a>
<span id="l878"></span><a href="#l878"></a>
<span id="l879">        @Override</span><a href="#l879"></a>
<span id="l880">        public byte[] produce(ConnectionContext context,</span><a href="#l880"></a>
<span id="l881">                HandshakeMessage message) throws IOException {</span><a href="#l881"></a>
<span id="l882">            ServerHandshakeContext shc = (ServerHandshakeContext)context;</span><a href="#l882"></a>
<span id="l883">            SHPreSharedKeySpec psk = (SHPreSharedKeySpec)</span><a href="#l883"></a>
<span id="l884">                    shc.handshakeExtensions.get(SH_PRE_SHARED_KEY);</span><a href="#l884"></a>
<span id="l885">            if (psk == null) {</span><a href="#l885"></a>
<span id="l886">                return null;</span><a href="#l886"></a>
<span id="l887">            }</span><a href="#l887"></a>
<span id="l888"></span><a href="#l888"></a>
<span id="l889">            return psk.getEncoded();</span><a href="#l889"></a>
<span id="l890">        }</span><a href="#l890"></a>
<span id="l891">    }</span><a href="#l891"></a>
<span id="l892">}</span><a href="#l892"></a></pre>
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

