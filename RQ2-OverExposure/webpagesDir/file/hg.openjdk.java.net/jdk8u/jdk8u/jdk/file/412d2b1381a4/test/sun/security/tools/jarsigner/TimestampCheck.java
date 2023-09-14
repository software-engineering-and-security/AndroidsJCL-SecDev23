<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 412d2b1381a4 test/sun/security/tools/jarsigner/TimestampCheck.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/412d2b1381a4/test/sun/security/tools/jarsigner/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/test/sun/security/tools/jarsigner/TimestampCheck.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/412d2b1381a4/test/sun/security/tools/jarsigner/TimestampCheck.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/412d2b1381a4/test/sun/security/tools/jarsigner/TimestampCheck.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/412d2b1381a4/test/sun/security/tools/jarsigner/TimestampCheck.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/412d2b1381a4/test/sun/security/tools/jarsigner/TimestampCheck.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/412d2b1381a4/test/sun/security/tools/jarsigner/TimestampCheck.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view test/sun/security/tools/jarsigner/TimestampCheck.java @ 14391:412d2b1381a4</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/78875da14d05/test/sun/security/tools/jarsigner/TimestampCheck.java">78875da14d05</a> </td>
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
<span id="l2"> * Copyright (c) 2003, 2020, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
<span id="l3"> * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.</span><a href="#l3"></a>
<span id="l4"> *</span><a href="#l4"></a>
<span id="l5"> * This code is free software; you can redistribute it and/or modify it</span><a href="#l5"></a>
<span id="l6"> * under the terms of the GNU General Public License version 2 only, as</span><a href="#l6"></a>
<span id="l7"> * published by the Free Software Foundation.</span><a href="#l7"></a>
<span id="l8"> *</span><a href="#l8"></a>
<span id="l9"> * This code is distributed in the hope that it will be useful, but WITHOUT</span><a href="#l9"></a>
<span id="l10"> * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or</span><a href="#l10"></a>
<span id="l11"> * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License</span><a href="#l11"></a>
<span id="l12"> * version 2 for more details (a copy is included in the LICENSE file that</span><a href="#l12"></a>
<span id="l13"> * accompanied this code).</span><a href="#l13"></a>
<span id="l14"> *</span><a href="#l14"></a>
<span id="l15"> * You should have received a copy of the GNU General Public License version</span><a href="#l15"></a>
<span id="l16"> * 2 along with this work; if not, write to the Free Software Foundation,</span><a href="#l16"></a>
<span id="l17"> * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.</span><a href="#l17"></a>
<span id="l18"> *</span><a href="#l18"></a>
<span id="l19"> * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA</span><a href="#l19"></a>
<span id="l20"> * or visit www.oracle.com if you need additional information or have any</span><a href="#l20"></a>
<span id="l21"> * questions.</span><a href="#l21"></a>
<span id="l22"> */</span><a href="#l22"></a>
<span id="l23"></span><a href="#l23"></a>
<span id="l24">import com.sun.net.httpserver.*;</span><a href="#l24"></a>
<span id="l25"></span><a href="#l25"></a>
<span id="l26">import java.io.ByteArrayInputStream;</span><a href="#l26"></a>
<span id="l27">import java.io.ByteArrayOutputStream;</span><a href="#l27"></a>
<span id="l28">import java.io.File;</span><a href="#l28"></a>
<span id="l29">import java.io.FileInputStream;</span><a href="#l29"></a>
<span id="l30">import java.io.IOException;</span><a href="#l30"></a>
<span id="l31">import java.io.InputStream;</span><a href="#l31"></a>
<span id="l32">import java.io.OutputStream;</span><a href="#l32"></a>
<span id="l33">import java.math.BigInteger;</span><a href="#l33"></a>
<span id="l34">import java.net.InetSocketAddress;</span><a href="#l34"></a>
<span id="l35">import java.nio.file.Files;</span><a href="#l35"></a>
<span id="l36">import java.nio.file.Paths;</span><a href="#l36"></a>
<span id="l37">import java.security.KeyStore;</span><a href="#l37"></a>
<span id="l38">import java.security.PrivateKey;</span><a href="#l38"></a>
<span id="l39">import java.security.Signature;</span><a href="#l39"></a>
<span id="l40">import java.security.cert.Certificate;</span><a href="#l40"></a>
<span id="l41">import java.security.cert.CertificateException;</span><a href="#l41"></a>
<span id="l42">import java.security.cert.CertificateFactory;</span><a href="#l42"></a>
<span id="l43">import java.security.cert.X509Certificate;</span><a href="#l43"></a>
<span id="l44">import java.time.Instant;</span><a href="#l44"></a>
<span id="l45">import java.time.temporal.ChronoUnit;</span><a href="#l45"></a>
<span id="l46">import java.util.*;</span><a href="#l46"></a>
<span id="l47">import java.util.jar.JarEntry;</span><a href="#l47"></a>
<span id="l48">import java.util.jar.JarFile;</span><a href="#l48"></a>
<span id="l49"></span><a href="#l49"></a>
<span id="l50">import sun.misc.IOUtils;</span><a href="#l50"></a>
<span id="l51">import jdk.testlibrary.SecurityTools;</span><a href="#l51"></a>
<span id="l52">import jdk.testlibrary.OutputAnalyzer;</span><a href="#l52"></a>
<span id="l53">import jdk.testlibrary.JarUtils;</span><a href="#l53"></a>
<span id="l54">import sun.security.pkcs.ContentInfo;</span><a href="#l54"></a>
<span id="l55">import sun.security.pkcs.PKCS7;</span><a href="#l55"></a>
<span id="l56">import sun.security.pkcs.PKCS9Attribute;</span><a href="#l56"></a>
<span id="l57">import sun.security.pkcs.SignerInfo;</span><a href="#l57"></a>
<span id="l58">import sun.security.timestamp.TimestampToken;</span><a href="#l58"></a>
<span id="l59">import sun.security.util.DerOutputStream;</span><a href="#l59"></a>
<span id="l60">import sun.security.util.DerValue;</span><a href="#l60"></a>
<span id="l61">import sun.security.util.ObjectIdentifier;</span><a href="#l61"></a>
<span id="l62">import sun.security.x509.AlgorithmId;</span><a href="#l62"></a>
<span id="l63">import sun.security.x509.X500Name;</span><a href="#l63"></a>
<span id="l64"></span><a href="#l64"></a>
<span id="l65">import jdk.testlibrary.Utils;</span><a href="#l65"></a>
<span id="l66"></span><a href="#l66"></a>
<span id="l67">/*</span><a href="#l67"></a>
<span id="l68"> * @test</span><a href="#l68"></a>
<span id="l69"> * @bug 6543842 6543440 6939248 8009636 8024302 8163304 8169911 8169688 8171121</span><a href="#l69"></a>
<span id="l70"> *      8180289 8172404</span><a href="#l70"></a>
<span id="l71"> * @summary checking response of timestamp</span><a href="#l71"></a>
<span id="l72"> * @modules java.base/sun.security.pkcs</span><a href="#l72"></a>
<span id="l73"> *          java.base/sun.security.timestamp</span><a href="#l73"></a>
<span id="l74"> *          java.base/sun.security.x509</span><a href="#l74"></a>
<span id="l75"> *          java.base/sun.security.util</span><a href="#l75"></a>
<span id="l76"> *          java.base/sun.security.tools.keytool</span><a href="#l76"></a>
<span id="l77"> * @library /lib/testlibrary</span><a href="#l77"></a>
<span id="l78"> * @compile -XDignore.symbol.file TimestampCheck.java</span><a href="#l78"></a>
<span id="l79"> * @run main/othervm/timeout=600 TimestampCheck</span><a href="#l79"></a>
<span id="l80"> */</span><a href="#l80"></a>
<span id="l81">public class TimestampCheck {</span><a href="#l81"></a>
<span id="l82"></span><a href="#l82"></a>
<span id="l83">    static final String defaultPolicyId = &quot;2.3.4&quot;;</span><a href="#l83"></a>
<span id="l84">    static String host = null;</span><a href="#l84"></a>
<span id="l85"></span><a href="#l85"></a>
<span id="l86">    static class Handler implements HttpHandler, AutoCloseable {</span><a href="#l86"></a>
<span id="l87"></span><a href="#l87"></a>
<span id="l88">        private final HttpServer httpServer;</span><a href="#l88"></a>
<span id="l89">        private final String keystore;</span><a href="#l89"></a>
<span id="l90"></span><a href="#l90"></a>
<span id="l91">        @Override</span><a href="#l91"></a>
<span id="l92">        public void handle(HttpExchange t) throws IOException {</span><a href="#l92"></a>
<span id="l93">            int len = 0;</span><a href="#l93"></a>
<span id="l94">            for (String h: t.getRequestHeaders().keySet()) {</span><a href="#l94"></a>
<span id="l95">                if (h.equalsIgnoreCase(&quot;Content-length&quot;)) {</span><a href="#l95"></a>
<span id="l96">                    len = Integer.valueOf(t.getRequestHeaders().get(h).get(0));</span><a href="#l96"></a>
<span id="l97">                }</span><a href="#l97"></a>
<span id="l98">            }</span><a href="#l98"></a>
<span id="l99">            byte[] input = new byte[len];</span><a href="#l99"></a>
<span id="l100">            t.getRequestBody().read(input);</span><a href="#l100"></a>
<span id="l101"></span><a href="#l101"></a>
<span id="l102">            try {</span><a href="#l102"></a>
<span id="l103">                String path = t.getRequestURI().getPath().substring(1);</span><a href="#l103"></a>
<span id="l104">                byte[] output = sign(input, path);</span><a href="#l104"></a>
<span id="l105">                Headers out = t.getResponseHeaders();</span><a href="#l105"></a>
<span id="l106">                out.set(&quot;Content-Type&quot;, &quot;application/timestamp-reply&quot;);</span><a href="#l106"></a>
<span id="l107"></span><a href="#l107"></a>
<span id="l108">                t.sendResponseHeaders(200, output.length);</span><a href="#l108"></a>
<span id="l109">                OutputStream os = t.getResponseBody();</span><a href="#l109"></a>
<span id="l110">                os.write(output);</span><a href="#l110"></a>
<span id="l111">            } catch (Exception e) {</span><a href="#l111"></a>
<span id="l112">                e.printStackTrace();</span><a href="#l112"></a>
<span id="l113">                t.sendResponseHeaders(500, 0);</span><a href="#l113"></a>
<span id="l114">            }</span><a href="#l114"></a>
<span id="l115">            t.close();</span><a href="#l115"></a>
<span id="l116">        }</span><a href="#l116"></a>
<span id="l117"></span><a href="#l117"></a>
<span id="l118">        /**</span><a href="#l118"></a>
<span id="l119">         * @param input The data to sign</span><a href="#l119"></a>
<span id="l120">         * @param path different cases to simulate, impl on URL path</span><a href="#l120"></a>
<span id="l121">         * @returns the signed</span><a href="#l121"></a>
<span id="l122">         */</span><a href="#l122"></a>
<span id="l123">        byte[] sign(byte[] input, String path) throws Exception {</span><a href="#l123"></a>
<span id="l124">            DerValue value = new DerValue(input);</span><a href="#l124"></a>
<span id="l125">            System.out.println(&quot;#\n# Incoming Request\n===================&quot;);</span><a href="#l125"></a>
<span id="l126">            System.out.println(&quot;# Version: &quot; + value.data.getInteger());</span><a href="#l126"></a>
<span id="l127">            DerValue messageImprint = value.data.getDerValue();</span><a href="#l127"></a>
<span id="l128">            AlgorithmId aid = AlgorithmId.parse(</span><a href="#l128"></a>
<span id="l129">                    messageImprint.data.getDerValue());</span><a href="#l129"></a>
<span id="l130">            System.out.println(&quot;# AlgorithmId: &quot; + aid);</span><a href="#l130"></a>
<span id="l131"></span><a href="#l131"></a>
<span id="l132">            ObjectIdentifier policyId = new ObjectIdentifier(defaultPolicyId);</span><a href="#l132"></a>
<span id="l133">            BigInteger nonce = null;</span><a href="#l133"></a>
<span id="l134">            while (value.data.available() &gt; 0) {</span><a href="#l134"></a>
<span id="l135">                DerValue v = value.data.getDerValue();</span><a href="#l135"></a>
<span id="l136">                if (v.tag == DerValue.tag_Integer) {</span><a href="#l136"></a>
<span id="l137">                    nonce = v.getBigInteger();</span><a href="#l137"></a>
<span id="l138">                    System.out.println(&quot;# nonce: &quot; + nonce);</span><a href="#l138"></a>
<span id="l139">                } else if (v.tag == DerValue.tag_Boolean) {</span><a href="#l139"></a>
<span id="l140">                    System.out.println(&quot;# certReq: &quot; + v.getBoolean());</span><a href="#l140"></a>
<span id="l141">                } else if (v.tag == DerValue.tag_ObjectId) {</span><a href="#l141"></a>
<span id="l142">                    policyId = v.getOID();</span><a href="#l142"></a>
<span id="l143">                    System.out.println(&quot;# PolicyID: &quot; + policyId);</span><a href="#l143"></a>
<span id="l144">                }</span><a href="#l144"></a>
<span id="l145">            }</span><a href="#l145"></a>
<span id="l146"></span><a href="#l146"></a>
<span id="l147">            System.out.println(&quot;#\n# Response\n===================&quot;);</span><a href="#l147"></a>
<span id="l148">            FileInputStream is = new FileInputStream(keystore);</span><a href="#l148"></a>
<span id="l149">            KeyStore ks = KeyStore.getInstance(&quot;JCEKS&quot;);</span><a href="#l149"></a>
<span id="l150">            ks.load(is, &quot;changeit&quot;.toCharArray());</span><a href="#l150"></a>
<span id="l151">            is.close();</span><a href="#l151"></a>
<span id="l152"></span><a href="#l152"></a>
<span id="l153">            // If path starts with &quot;ts&quot;, use the TSA it points to.</span><a href="#l153"></a>
<span id="l154">            // Otherwise, always use &quot;ts&quot;.</span><a href="#l154"></a>
<span id="l155">            String alias = path.startsWith(&quot;ts&quot;) ? path : &quot;ts&quot;;</span><a href="#l155"></a>
<span id="l156"></span><a href="#l156"></a>
<span id="l157">            if (path.equals(&quot;diffpolicy&quot;)) {</span><a href="#l157"></a>
<span id="l158">                policyId = new ObjectIdentifier(defaultPolicyId);</span><a href="#l158"></a>
<span id="l159">            }</span><a href="#l159"></a>
<span id="l160"></span><a href="#l160"></a>
<span id="l161">            DerOutputStream statusInfo = new DerOutputStream();</span><a href="#l161"></a>
<span id="l162">            statusInfo.putInteger(0);</span><a href="#l162"></a>
<span id="l163"></span><a href="#l163"></a>
<span id="l164">            AlgorithmId[] algorithms = {aid};</span><a href="#l164"></a>
<span id="l165">            Certificate[] chain = ks.getCertificateChain(alias);</span><a href="#l165"></a>
<span id="l166">            X509Certificate[] signerCertificateChain;</span><a href="#l166"></a>
<span id="l167">            X509Certificate signer = (X509Certificate)chain[0];</span><a href="#l167"></a>
<span id="l168"></span><a href="#l168"></a>
<span id="l169">            if (path.equals(&quot;fullchain&quot;)) {   // Only case 5 uses full chain</span><a href="#l169"></a>
<span id="l170">                signerCertificateChain = new X509Certificate[chain.length];</span><a href="#l170"></a>
<span id="l171">                for (int i=0; i&lt;chain.length; i++) {</span><a href="#l171"></a>
<span id="l172">                    signerCertificateChain[i] = (X509Certificate)chain[i];</span><a href="#l172"></a>
<span id="l173">                }</span><a href="#l173"></a>
<span id="l174">            } else if (path.equals(&quot;nocert&quot;)) {</span><a href="#l174"></a>
<span id="l175">                signerCertificateChain = new X509Certificate[0];</span><a href="#l175"></a>
<span id="l176">            } else {</span><a href="#l176"></a>
<span id="l177">                signerCertificateChain = new X509Certificate[1];</span><a href="#l177"></a>
<span id="l178">                signerCertificateChain[0] = (X509Certificate)chain[0];</span><a href="#l178"></a>
<span id="l179">            }</span><a href="#l179"></a>
<span id="l180"></span><a href="#l180"></a>
<span id="l181">            DerOutputStream tst = new DerOutputStream();</span><a href="#l181"></a>
<span id="l182"></span><a href="#l182"></a>
<span id="l183">            tst.putInteger(1);</span><a href="#l183"></a>
<span id="l184">            tst.putOID(policyId);</span><a href="#l184"></a>
<span id="l185"></span><a href="#l185"></a>
<span id="l186">            if (!path.equals(&quot;baddigest&quot;) &amp;&amp; !path.equals(&quot;diffalg&quot;)) {</span><a href="#l186"></a>
<span id="l187">                tst.putDerValue(messageImprint);</span><a href="#l187"></a>
<span id="l188">            } else {</span><a href="#l188"></a>
<span id="l189">                byte[] data = messageImprint.toByteArray();</span><a href="#l189"></a>
<span id="l190">                if (path.equals(&quot;diffalg&quot;)) {</span><a href="#l190"></a>
<span id="l191">                    data[6] = (byte)0x01;</span><a href="#l191"></a>
<span id="l192">                } else {</span><a href="#l192"></a>
<span id="l193">                    data[data.length-1] = (byte)0x01;</span><a href="#l193"></a>
<span id="l194">                    data[data.length-2] = (byte)0x02;</span><a href="#l194"></a>
<span id="l195">                    data[data.length-3] = (byte)0x03;</span><a href="#l195"></a>
<span id="l196">                }</span><a href="#l196"></a>
<span id="l197">                tst.write(data);</span><a href="#l197"></a>
<span id="l198">            }</span><a href="#l198"></a>
<span id="l199"></span><a href="#l199"></a>
<span id="l200">            tst.putInteger(1);</span><a href="#l200"></a>
<span id="l201"></span><a href="#l201"></a>
<span id="l202">            Instant instant = Instant.now();</span><a href="#l202"></a>
<span id="l203">            if (path.equals(&quot;tsold&quot;)) {</span><a href="#l203"></a>
<span id="l204">                instant = instant.minus(20, ChronoUnit.DAYS);</span><a href="#l204"></a>
<span id="l205">            }</span><a href="#l205"></a>
<span id="l206">            tst.putGeneralizedTime(Date.from(instant));</span><a href="#l206"></a>
<span id="l207"></span><a href="#l207"></a>
<span id="l208">            if (path.equals(&quot;diffnonce&quot;)) {</span><a href="#l208"></a>
<span id="l209">                tst.putInteger(1234);</span><a href="#l209"></a>
<span id="l210">            } else if (path.equals(&quot;nononce&quot;)) {</span><a href="#l210"></a>
<span id="l211">                // no noce</span><a href="#l211"></a>
<span id="l212">            } else {</span><a href="#l212"></a>
<span id="l213">                tst.putInteger(nonce);</span><a href="#l213"></a>
<span id="l214">            }</span><a href="#l214"></a>
<span id="l215"></span><a href="#l215"></a>
<span id="l216">            DerOutputStream tstInfo = new DerOutputStream();</span><a href="#l216"></a>
<span id="l217">            tstInfo.write(DerValue.tag_Sequence, tst);</span><a href="#l217"></a>
<span id="l218"></span><a href="#l218"></a>
<span id="l219">            DerOutputStream tstInfo2 = new DerOutputStream();</span><a href="#l219"></a>
<span id="l220">            tstInfo2.putOctetString(tstInfo.toByteArray());</span><a href="#l220"></a>
<span id="l221"></span><a href="#l221"></a>
<span id="l222">            // Always use the same algorithm at timestamp signing</span><a href="#l222"></a>
<span id="l223">            // so it is different from the hash algorithm.</span><a href="#l223"></a>
<span id="l224">            String sigAlg = &quot;SHA256withRSA&quot;;</span><a href="#l224"></a>
<span id="l225">            Signature sig = Signature.getInstance(sigAlg);</span><a href="#l225"></a>
<span id="l226">            sig.initSign((PrivateKey)(ks.getKey(</span><a href="#l226"></a>
<span id="l227">                    alias, &quot;changeit&quot;.toCharArray())));</span><a href="#l227"></a>
<span id="l228">            sig.update(tstInfo.toByteArray());</span><a href="#l228"></a>
<span id="l229"></span><a href="#l229"></a>
<span id="l230">            ContentInfo contentInfo = new ContentInfo(new ObjectIdentifier(</span><a href="#l230"></a>
<span id="l231">                    &quot;1.2.840.113549.1.9.16.1.4&quot;),</span><a href="#l231"></a>
<span id="l232">                    new DerValue(tstInfo2.toByteArray()));</span><a href="#l232"></a>
<span id="l233"></span><a href="#l233"></a>
<span id="l234">            System.out.println(&quot;# Signing...&quot;);</span><a href="#l234"></a>
<span id="l235">            System.out.println(&quot;# &quot; + new X500Name(signer</span><a href="#l235"></a>
<span id="l236">                    .getIssuerX500Principal().getName()));</span><a href="#l236"></a>
<span id="l237">            System.out.println(&quot;# &quot; + signer.getSerialNumber());</span><a href="#l237"></a>
<span id="l238"></span><a href="#l238"></a>
<span id="l239">            SignerInfo signerInfo = new SignerInfo(</span><a href="#l239"></a>
<span id="l240">                    new X500Name(signer.getIssuerX500Principal().getName()),</span><a href="#l240"></a>
<span id="l241">                    signer.getSerialNumber(),</span><a href="#l241"></a>
<span id="l242">                    AlgorithmId.get(AlgorithmId.getDigAlgFromSigAlg(sigAlg)),</span><a href="#l242"></a>
<span id="l243">                    AlgorithmId.get(AlgorithmId.getEncAlgFromSigAlg(sigAlg)),</span><a href="#l243"></a>
<span id="l244">                    sig.sign());</span><a href="#l244"></a>
<span id="l245"></span><a href="#l245"></a>
<span id="l246">            SignerInfo[] signerInfos = {signerInfo};</span><a href="#l246"></a>
<span id="l247">            PKCS7 p7 = new PKCS7(algorithms, contentInfo,</span><a href="#l247"></a>
<span id="l248">                    signerCertificateChain, signerInfos);</span><a href="#l248"></a>
<span id="l249">            ByteArrayOutputStream p7out = new ByteArrayOutputStream();</span><a href="#l249"></a>
<span id="l250">            p7.encodeSignedData(p7out);</span><a href="#l250"></a>
<span id="l251"></span><a href="#l251"></a>
<span id="l252">            DerOutputStream response = new DerOutputStream();</span><a href="#l252"></a>
<span id="l253">            response.write(DerValue.tag_Sequence, statusInfo);</span><a href="#l253"></a>
<span id="l254">            response.putDerValue(new DerValue(p7out.toByteArray()));</span><a href="#l254"></a>
<span id="l255"></span><a href="#l255"></a>
<span id="l256">            DerOutputStream out = new DerOutputStream();</span><a href="#l256"></a>
<span id="l257">            out.write(DerValue.tag_Sequence, response);</span><a href="#l257"></a>
<span id="l258"></span><a href="#l258"></a>
<span id="l259">            return out.toByteArray();</span><a href="#l259"></a>
<span id="l260">        }</span><a href="#l260"></a>
<span id="l261"></span><a href="#l261"></a>
<span id="l262">        private Handler(HttpServer httpServer, String keystore) {</span><a href="#l262"></a>
<span id="l263">            this.httpServer = httpServer;</span><a href="#l263"></a>
<span id="l264">            this.keystore = keystore;</span><a href="#l264"></a>
<span id="l265">        }</span><a href="#l265"></a>
<span id="l266"></span><a href="#l266"></a>
<span id="l267">        /**</span><a href="#l267"></a>
<span id="l268">         * Initialize TSA instance.</span><a href="#l268"></a>
<span id="l269">         *</span><a href="#l269"></a>
<span id="l270">         * Extended Key Info extension of certificate that is used for</span><a href="#l270"></a>
<span id="l271">         * signing TSA responses should contain timeStamping value.</span><a href="#l271"></a>
<span id="l272">         */</span><a href="#l272"></a>
<span id="l273">        static Handler init(int port, String keystore) throws IOException {</span><a href="#l273"></a>
<span id="l274">            HttpServer httpServer = HttpServer.create(</span><a href="#l274"></a>
<span id="l275">                    new InetSocketAddress(port), 0);</span><a href="#l275"></a>
<span id="l276">            Handler tsa = new Handler(httpServer, keystore);</span><a href="#l276"></a>
<span id="l277">            httpServer.createContext(&quot;/&quot;, tsa);</span><a href="#l277"></a>
<span id="l278">            return tsa;</span><a href="#l278"></a>
<span id="l279">        }</span><a href="#l279"></a>
<span id="l280"></span><a href="#l280"></a>
<span id="l281">        /**</span><a href="#l281"></a>
<span id="l282">         * Start TSA service.</span><a href="#l282"></a>
<span id="l283">         */</span><a href="#l283"></a>
<span id="l284">        void start() {</span><a href="#l284"></a>
<span id="l285">            httpServer.start();</span><a href="#l285"></a>
<span id="l286">        }</span><a href="#l286"></a>
<span id="l287"></span><a href="#l287"></a>
<span id="l288">        /**</span><a href="#l288"></a>
<span id="l289">         * Stop TSA service.</span><a href="#l289"></a>
<span id="l290">         */</span><a href="#l290"></a>
<span id="l291">        void stop() {</span><a href="#l291"></a>
<span id="l292">            httpServer.stop(0);</span><a href="#l292"></a>
<span id="l293">        }</span><a href="#l293"></a>
<span id="l294"></span><a href="#l294"></a>
<span id="l295">        /**</span><a href="#l295"></a>
<span id="l296">         * Return server port number.</span><a href="#l296"></a>
<span id="l297">         */</span><a href="#l297"></a>
<span id="l298">        int getPort() {</span><a href="#l298"></a>
<span id="l299">            return httpServer.getAddress().getPort();</span><a href="#l299"></a>
<span id="l300">        }</span><a href="#l300"></a>
<span id="l301"></span><a href="#l301"></a>
<span id="l302">        @Override</span><a href="#l302"></a>
<span id="l303">        public void close() throws Exception {</span><a href="#l303"></a>
<span id="l304">            stop();</span><a href="#l304"></a>
<span id="l305">        }</span><a href="#l305"></a>
<span id="l306">    }</span><a href="#l306"></a>
<span id="l307"></span><a href="#l307"></a>
<span id="l308">    public static void main(String[] args) throws Throwable {</span><a href="#l308"></a>
<span id="l309"></span><a href="#l309"></a>
<span id="l310">        try (Handler tsa = Handler.init(0, &quot;ks&quot;);) {</span><a href="#l310"></a>
<span id="l311">            tsa.start();</span><a href="#l311"></a>
<span id="l312">            int port = tsa.getPort();</span><a href="#l312"></a>
<span id="l313"></span><a href="#l313"></a>
<span id="l314">            host = &quot;http://localhost:&quot; + port + &quot;/&quot;;</span><a href="#l314"></a>
<span id="l315"></span><a href="#l315"></a>
<span id="l316">            if (args.length == 0) {         // Run this test</span><a href="#l316"></a>
<span id="l317"></span><a href="#l317"></a>
<span id="l318">                prepare();</span><a href="#l318"></a>
<span id="l319"></span><a href="#l319"></a>
<span id="l320">                sign(&quot;normal&quot;)</span><a href="#l320"></a>
<span id="l321">                        .shouldNotContain(&quot;Warning&quot;)</span><a href="#l321"></a>
<span id="l322">                        .shouldContain(&quot;The signer certificate will expire on&quot;)</span><a href="#l322"></a>
<span id="l323">                        .shouldContain(&quot;The timestamp will expire on&quot;)</span><a href="#l323"></a>
<span id="l324">                        .shouldHaveExitValue(0);</span><a href="#l324"></a>
<span id="l325"></span><a href="#l325"></a>
<span id="l326">                verify(&quot;normal.jar&quot;)</span><a href="#l326"></a>
<span id="l327">                        .shouldNotContain(&quot;Warning&quot;)</span><a href="#l327"></a>
<span id="l328">                        .shouldHaveExitValue(0);</span><a href="#l328"></a>
<span id="l329"></span><a href="#l329"></a>
<span id="l330">                verify(&quot;normal.jar&quot;, &quot;-verbose&quot;)</span><a href="#l330"></a>
<span id="l331">                        .shouldNotContain(&quot;Warning&quot;)</span><a href="#l331"></a>
<span id="l332">                        .shouldContain(&quot;The signer certificate will expire on&quot;)</span><a href="#l332"></a>
<span id="l333">                        .shouldContain(&quot;The timestamp will expire on&quot;)</span><a href="#l333"></a>
<span id="l334">                        .shouldHaveExitValue(0);</span><a href="#l334"></a>
<span id="l335"></span><a href="#l335"></a>
<span id="l336">                // Simulate signing at a previous date:</span><a href="#l336"></a>
<span id="l337">                // 1. tsold will create a timestamp of 20 days ago.</span><a href="#l337"></a>
<span id="l338">                // 2. oldsigner expired 10 days ago.</span><a href="#l338"></a>
<span id="l339">                signVerbose(&quot;tsold&quot;, &quot;unsigned.jar&quot;, &quot;tsold.jar&quot;, &quot;oldsigner&quot;)</span><a href="#l339"></a>
<span id="l340">                        .shouldNotContain(&quot;Warning&quot;)</span><a href="#l340"></a>
<span id="l341">                        .shouldMatch(&quot;signer certificate expired on .*. &quot;</span><a href="#l341"></a>
<span id="l342">                                + &quot;However, the JAR will be valid&quot;)</span><a href="#l342"></a>
<span id="l343">                        .shouldHaveExitValue(0);</span><a href="#l343"></a>
<span id="l344"></span><a href="#l344"></a>
<span id="l345">                // It verifies perfectly.</span><a href="#l345"></a>
<span id="l346">                verify(&quot;tsold.jar&quot;, &quot;-verbose&quot;, &quot;-certs&quot;)</span><a href="#l346"></a>
<span id="l347">                        .shouldNotContain(&quot;Warning&quot;)</span><a href="#l347"></a>
<span id="l348">                        .shouldMatch(&quot;signer certificate expired on .*. &quot;</span><a href="#l348"></a>
<span id="l349">                                + &quot;However, the JAR will be valid&quot;)</span><a href="#l349"></a>
<span id="l350">                        .shouldHaveExitValue(0);</span><a href="#l350"></a>
<span id="l351"></span><a href="#l351"></a>
<span id="l352">                // No timestamp</span><a href="#l352"></a>
<span id="l353">                signVerbose(null, &quot;unsigned.jar&quot;, &quot;none.jar&quot;, &quot;signer&quot;)</span><a href="#l353"></a>
<span id="l354">                        .shouldContain(&quot;is not timestamped&quot;)</span><a href="#l354"></a>
<span id="l355">                        .shouldContain(&quot;The signer certificate will expire on&quot;)</span><a href="#l355"></a>
<span id="l356">                        .shouldHaveExitValue(0);</span><a href="#l356"></a>
<span id="l357"></span><a href="#l357"></a>
<span id="l358">                verify(&quot;none.jar&quot;, &quot;-verbose&quot;)</span><a href="#l358"></a>
<span id="l359">                        .shouldContain(&quot;do not include a timestamp&quot;)</span><a href="#l359"></a>
<span id="l360">                        .shouldContain(&quot;The signer certificate will expire on&quot;)</span><a href="#l360"></a>
<span id="l361">                        .shouldHaveExitValue(0);</span><a href="#l361"></a>
<span id="l362"></span><a href="#l362"></a>
<span id="l363">                // Error cases</span><a href="#l363"></a>
<span id="l364"></span><a href="#l364"></a>
<span id="l365">                signVerbose(null, &quot;unsigned.jar&quot;, &quot;badku.jar&quot;, &quot;badku&quot;)</span><a href="#l365"></a>
<span id="l366">                        .shouldContain(&quot;KeyUsage extension doesn't allow code signing&quot;)</span><a href="#l366"></a>
<span id="l367">                        .shouldHaveExitValue(8);</span><a href="#l367"></a>
<span id="l368">                checkBadKU(&quot;badku.jar&quot;);</span><a href="#l368"></a>
<span id="l369"></span><a href="#l369"></a>
<span id="l370">                // 8180289: unvalidated TSA cert chain</span><a href="#l370"></a>
<span id="l371">                sign(&quot;tsnoca&quot;)</span><a href="#l371"></a>
<span id="l372">                        .shouldContain(&quot;The TSA certificate chain is invalid. &quot;</span><a href="#l372"></a>
<span id="l373">                                + &quot;Reason: Path does not chain with any of the trust anchors&quot;)</span><a href="#l373"></a>
<span id="l374">                        .shouldHaveExitValue(64);</span><a href="#l374"></a>
<span id="l375"></span><a href="#l375"></a>
<span id="l376">                verify(&quot;tsnoca.jar&quot;, &quot;-verbose&quot;, &quot;-certs&quot;)</span><a href="#l376"></a>
<span id="l377">                        .shouldHaveExitValue(64)</span><a href="#l377"></a>
<span id="l378">                        .shouldContain(&quot;jar verified&quot;)</span><a href="#l378"></a>
<span id="l379">                        .shouldContain(&quot;Invalid TSA certificate chain: &quot;</span><a href="#l379"></a>
<span id="l380">                                + &quot;Path does not chain with any of the trust anchors&quot;)</span><a href="#l380"></a>
<span id="l381">                        .shouldContain(&quot;TSA certificate chain is invalid.&quot;</span><a href="#l381"></a>
<span id="l382">                                + &quot; Reason: Path does not chain with any of the trust anchors&quot;);</span><a href="#l382"></a>
<span id="l383"></span><a href="#l383"></a>
<span id="l384">                sign(&quot;nononce&quot;)</span><a href="#l384"></a>
<span id="l385">                        .shouldContain(&quot;Nonce missing in timestamp token&quot;)</span><a href="#l385"></a>
<span id="l386">                        .shouldHaveExitValue(1);</span><a href="#l386"></a>
<span id="l387">                sign(&quot;diffnonce&quot;)</span><a href="#l387"></a>
<span id="l388">                        .shouldContain(&quot;Nonce changed in timestamp token&quot;)</span><a href="#l388"></a>
<span id="l389">                        .shouldHaveExitValue(1);</span><a href="#l389"></a>
<span id="l390">                sign(&quot;baddigest&quot;)</span><a href="#l390"></a>
<span id="l391">                        .shouldContain(&quot;Digest octets changed in timestamp token&quot;)</span><a href="#l391"></a>
<span id="l392">                        .shouldHaveExitValue(1);</span><a href="#l392"></a>
<span id="l393">                sign(&quot;diffalg&quot;)</span><a href="#l393"></a>
<span id="l394">                        .shouldContain(&quot;Digest algorithm not&quot;)</span><a href="#l394"></a>
<span id="l395">                        .shouldHaveExitValue(1);</span><a href="#l395"></a>
<span id="l396"></span><a href="#l396"></a>
<span id="l397">                sign(&quot;fullchain&quot;)</span><a href="#l397"></a>
<span id="l398">                        .shouldHaveExitValue(0);   // Success, 6543440 solved.</span><a href="#l398"></a>
<span id="l399"></span><a href="#l399"></a>
<span id="l400">                sign(&quot;tsbad1&quot;)</span><a href="#l400"></a>
<span id="l401">                        .shouldContain(&quot;Certificate is not valid for timestamping&quot;)</span><a href="#l401"></a>
<span id="l402">                        .shouldHaveExitValue(1);</span><a href="#l402"></a>
<span id="l403">                sign(&quot;tsbad2&quot;)</span><a href="#l403"></a>
<span id="l404">                        .shouldContain(&quot;Certificate is not valid for timestamping&quot;)</span><a href="#l404"></a>
<span id="l405">                        .shouldHaveExitValue(1);</span><a href="#l405"></a>
<span id="l406">                sign(&quot;tsbad3&quot;)</span><a href="#l406"></a>
<span id="l407">                        .shouldContain(&quot;Certificate is not valid for timestamping&quot;)</span><a href="#l407"></a>
<span id="l408">                        .shouldHaveExitValue(1);</span><a href="#l408"></a>
<span id="l409">                sign(&quot;nocert&quot;)</span><a href="#l409"></a>
<span id="l410">                        .shouldContain(&quot;Certificate not included in timestamp token&quot;)</span><a href="#l410"></a>
<span id="l411">                        .shouldHaveExitValue(1);</span><a href="#l411"></a>
<span id="l412"></span><a href="#l412"></a>
<span id="l413">                sign(&quot;policy&quot;, &quot;-tsapolicyid&quot;,  &quot;1.2.3&quot;)</span><a href="#l413"></a>
<span id="l414">                        .shouldHaveExitValue(0);</span><a href="#l414"></a>
<span id="l415">                checkTimestamp(&quot;policy.jar&quot;, &quot;1.2.3&quot;, &quot;SHA-256&quot;);</span><a href="#l415"></a>
<span id="l416"></span><a href="#l416"></a>
<span id="l417">                sign(&quot;diffpolicy&quot;, &quot;-tsapolicyid&quot;, &quot;1.2.3&quot;)</span><a href="#l417"></a>
<span id="l418">                        .shouldContain(&quot;TSAPolicyID changed in timestamp token&quot;)</span><a href="#l418"></a>
<span id="l419">                        .shouldHaveExitValue(1);</span><a href="#l419"></a>
<span id="l420"></span><a href="#l420"></a>
<span id="l421">                sign(&quot;sha384alg&quot;, &quot;-tsadigestalg&quot;, &quot;SHA-384&quot;)</span><a href="#l421"></a>
<span id="l422">                        .shouldHaveExitValue(0);</span><a href="#l422"></a>
<span id="l423">                checkTimestamp(&quot;sha384alg.jar&quot;, defaultPolicyId, &quot;SHA-384&quot;);</span><a href="#l423"></a>
<span id="l424"></span><a href="#l424"></a>
<span id="l425">                // Legacy algorithms</span><a href="#l425"></a>
<span id="l426">                signVerbose(null, &quot;unsigned.jar&quot;, &quot;sha1alg.jar&quot;, &quot;signer&quot;,</span><a href="#l426"></a>
<span id="l427">                        &quot;-strict&quot;, &quot;-digestalg&quot;, &quot;SHA-1&quot;)</span><a href="#l427"></a>
<span id="l428">                        .shouldHaveExitValue(0)</span><a href="#l428"></a>
<span id="l429">                        .shouldContain(&quot;jar signed, with signer errors&quot;)</span><a href="#l429"></a>
<span id="l430">                        .shouldMatch(&quot;SHA-1.*-digestalg.*will be disabled&quot;);</span><a href="#l430"></a>
<span id="l431">                verify(&quot;sha1alg.jar&quot;, &quot;-strict&quot;)</span><a href="#l431"></a>
<span id="l432">                        .shouldHaveExitValue(0)</span><a href="#l432"></a>
<span id="l433">                        .shouldContain(&quot;jar verified, with signer errors&quot;)</span><a href="#l433"></a>
<span id="l434">                        .shouldContain(&quot;SHA-1 digest algorithm is considered a security risk&quot;)</span><a href="#l434"></a>
<span id="l435">                        .shouldContain(&quot;This algorithm will be disabled in a future update&quot;)</span><a href="#l435"></a>
<span id="l436">                        .shouldNotContain(&quot;is disabled&quot;);</span><a href="#l436"></a>
<span id="l437"></span><a href="#l437"></a>
<span id="l438">                sign(&quot;sha1tsaalg&quot;, &quot;-tsadigestalg&quot;, &quot;SHA-1&quot;, &quot;-strict&quot;)</span><a href="#l438"></a>
<span id="l439">                        .shouldHaveExitValue(0)</span><a href="#l439"></a>
<span id="l440">                        .shouldContain(&quot;jar signed, with signer errors&quot;)</span><a href="#l440"></a>
<span id="l441">                        .shouldMatch(&quot;SHA-1.*-tsadigestalg.*will be disabled&quot;)</span><a href="#l441"></a>
<span id="l442">                        .shouldNotContain(&quot;is disabled&quot;);</span><a href="#l442"></a>
<span id="l443">                verify(&quot;sha1tsaalg.jar&quot;, &quot;-strict&quot;)</span><a href="#l443"></a>
<span id="l444">                        .shouldHaveExitValue(0)</span><a href="#l444"></a>
<span id="l445">                        .shouldContain(&quot;jar verified, with signer errors&quot;)</span><a href="#l445"></a>
<span id="l446">                        .shouldContain(&quot;SHA-1 digest algorithm is considered a security risk&quot;)</span><a href="#l446"></a>
<span id="l447">                        .shouldNotContain(&quot;is disabled&quot;);</span><a href="#l447"></a>
<span id="l448"></span><a href="#l448"></a>
<span id="l449">                // Disabled algorithms</span><a href="#l449"></a>
<span id="l450">                sign(&quot;tsdisabled&quot;, &quot;-digestalg&quot;, &quot;MD5&quot;,</span><a href="#l450"></a>
<span id="l451">                                &quot;-sigalg&quot;, &quot;MD5withRSA&quot;, &quot;-tsadigestalg&quot;, &quot;MD5&quot;)</span><a href="#l451"></a>
<span id="l452">                        .shouldHaveExitValue(68)</span><a href="#l452"></a>
<span id="l453">                        .shouldContain(&quot;TSA certificate chain is invalid&quot;)</span><a href="#l453"></a>
<span id="l454">                        .shouldMatch(&quot;MD5.*-digestalg.*is disabled&quot;)</span><a href="#l454"></a>
<span id="l455">                        .shouldMatch(&quot;MD5.*-tsadigestalg.*is disabled&quot;)</span><a href="#l455"></a>
<span id="l456">                        .shouldMatch(&quot;MD5withRSA.*-sigalg.*is disabled&quot;);</span><a href="#l456"></a>
<span id="l457">                checkDisabled(&quot;tsdisabled.jar&quot;);</span><a href="#l457"></a>
<span id="l458"></span><a href="#l458"></a>
<span id="l459">                signVerbose(&quot;tsdisabled&quot;, &quot;unsigned.jar&quot;, &quot;tsdisabled2.jar&quot;, &quot;signer&quot;)</span><a href="#l459"></a>
<span id="l460">                        .shouldHaveExitValue(64)</span><a href="#l460"></a>
<span id="l461">                        .shouldContain(&quot;TSA certificate chain is invalid&quot;);</span><a href="#l461"></a>
<span id="l462"></span><a href="#l462"></a>
<span id="l463">                // Disabled timestamp is an error and jar treated unsigned</span><a href="#l463"></a>
<span id="l464">                verify(&quot;tsdisabled2.jar&quot;, &quot;-verbose&quot;)</span><a href="#l464"></a>
<span id="l465">                        .shouldHaveExitValue(16)</span><a href="#l465"></a>
<span id="l466">                        .shouldContain(&quot;treated as unsigned&quot;)</span><a href="#l466"></a>
<span id="l467">                        .shouldMatch(&quot;Timestamp.*512.*(disabled)&quot;);</span><a href="#l467"></a>
<span id="l468"></span><a href="#l468"></a>
<span id="l469">                // Algorithm used in signing is disabled</span><a href="#l469"></a>
<span id="l470">                signVerbose(&quot;normal&quot;, &quot;unsigned.jar&quot;, &quot;halfDisabled.jar&quot;, &quot;signer&quot;,</span><a href="#l470"></a>
<span id="l471">                        &quot;-digestalg&quot;, &quot;MD5&quot;)</span><a href="#l471"></a>
<span id="l472">                        .shouldContain(&quot;-digestalg option is considered a security risk and is disabled&quot;)</span><a href="#l472"></a>
<span id="l473">                        .shouldHaveExitValue(4);</span><a href="#l473"></a>
<span id="l474">                checkHalfDisabled(&quot;halfDisabled.jar&quot;);</span><a href="#l474"></a>
<span id="l475"></span><a href="#l475"></a>
<span id="l476">                // sign with DSA key</span><a href="#l476"></a>
<span id="l477">                signVerbose(&quot;normal&quot;, &quot;unsigned.jar&quot;, &quot;sign1.jar&quot;, &quot;dsakey&quot;)</span><a href="#l477"></a>
<span id="l478">                        .shouldHaveExitValue(0);</span><a href="#l478"></a>
<span id="l479"></span><a href="#l479"></a>
<span id="l480">                // sign with RSAkeysize &lt; 1024</span><a href="#l480"></a>
<span id="l481">                signVerbose(&quot;normal&quot;, &quot;sign1.jar&quot;, &quot;sign2.jar&quot;, &quot;disabledkeysize&quot;)</span><a href="#l481"></a>
<span id="l482">                        .shouldContain(&quot;Algorithm constraints check failed on keysize&quot;)</span><a href="#l482"></a>
<span id="l483">                        .shouldHaveExitValue(4);</span><a href="#l483"></a>
<span id="l484">                checkMultiple(&quot;sign2.jar&quot;);</span><a href="#l484"></a>
<span id="l485"></span><a href="#l485"></a>
<span id="l486">                // Legacy algorithms</span><a href="#l486"></a>
<span id="l487">                sign(&quot;tsweak&quot;, &quot;-digestalg&quot;, &quot;SHA1&quot;,</span><a href="#l487"></a>
<span id="l488">                                &quot;-sigalg&quot;, &quot;SHA1withRSA&quot;, &quot;-tsadigestalg&quot;, &quot;SHA1&quot;)</span><a href="#l488"></a>
<span id="l489">                        .shouldHaveExitValue(0)</span><a href="#l489"></a>
<span id="l490">                        .shouldMatch(&quot;SHA1.*-digestalg.*will be disabled&quot;)</span><a href="#l490"></a>
<span id="l491">                        .shouldMatch(&quot;SHA1.*-tsadigestalg.*will be disabled&quot;)</span><a href="#l491"></a>
<span id="l492">                        .shouldMatch(&quot;SHA1withRSA.*-sigalg.*will be disabled&quot;);</span><a href="#l492"></a>
<span id="l493">                checkWeak(&quot;tsweak.jar&quot;);</span><a href="#l493"></a>
<span id="l494"></span><a href="#l494"></a>
<span id="l495">                signVerbose(&quot;tsweak&quot;, &quot;unsigned.jar&quot;, &quot;tsweak2.jar&quot;, &quot;signer&quot;)</span><a href="#l495"></a>
<span id="l496">                        .shouldHaveExitValue(0);</span><a href="#l496"></a>
<span id="l497"></span><a href="#l497"></a>
<span id="l498">                verify(&quot;tsweak2.jar&quot;, &quot;-verbose&quot;)</span><a href="#l498"></a>
<span id="l499">                        .shouldHaveExitValue(0)</span><a href="#l499"></a>
<span id="l500">                        .shouldContain(&quot;jar verified&quot;)</span><a href="#l500"></a>
<span id="l501">                        .shouldMatch(&quot;Timestamp.*1024.*(weak)&quot;);</span><a href="#l501"></a>
<span id="l502"></span><a href="#l502"></a>
<span id="l503">                // Algorithm used in signing is weak</span><a href="#l503"></a>
<span id="l504">                signVerbose(&quot;normal&quot;, &quot;unsigned.jar&quot;, &quot;halfWeak.jar&quot;, &quot;signer&quot;,</span><a href="#l504"></a>
<span id="l505">                        &quot;-digestalg&quot;, &quot;SHA1&quot;)</span><a href="#l505"></a>
<span id="l506">                        .shouldContain(&quot;-digestalg option is considered a security risk.&quot;)</span><a href="#l506"></a>
<span id="l507">                        .shouldContain(&quot;This algorithm will be disabled in a future update.&quot;)</span><a href="#l507"></a>
<span id="l508">                        .shouldHaveExitValue(0);</span><a href="#l508"></a>
<span id="l509">                checkHalfWeak(&quot;halfWeak.jar&quot;);</span><a href="#l509"></a>
<span id="l510"></span><a href="#l510"></a>
<span id="l511">                // sign with DSA key</span><a href="#l511"></a>
<span id="l512">                signVerbose(&quot;normal&quot;, &quot;unsigned.jar&quot;, &quot;sign1.jar&quot;, &quot;dsakey&quot;)</span><a href="#l512"></a>
<span id="l513">                        .shouldHaveExitValue(0);</span><a href="#l513"></a>
<span id="l514"></span><a href="#l514"></a>
<span id="l515">                // sign with RSAkeysize &lt; 2048</span><a href="#l515"></a>
<span id="l516">                signVerbose(&quot;normal&quot;, &quot;sign1.jar&quot;, &quot;sign2.jar&quot;, &quot;weakkeysize&quot;)</span><a href="#l516"></a>
<span id="l517">                        .shouldNotContain(&quot;Algorithm constraints check failed on keysize&quot;)</span><a href="#l517"></a>
<span id="l518">                        .shouldHaveExitValue(0);</span><a href="#l518"></a>
<span id="l519">                checkMultipleWeak(&quot;sign2.jar&quot;);</span><a href="#l519"></a>
<span id="l520"></span><a href="#l520"></a>
<span id="l521"></span><a href="#l521"></a>
<span id="l522">                // 8191438: jarsigner should print when a timestamp will expire</span><a href="#l522"></a>
<span id="l523">                checkExpiration();</span><a href="#l523"></a>
<span id="l524"></span><a href="#l524"></a>
<span id="l525">                // When .SF or .RSA is missing or invalid</span><a href="#l525"></a>
<span id="l526">                checkMissingOrInvalidFiles(&quot;normal.jar&quot;);</span><a href="#l526"></a>
<span id="l527"></span><a href="#l527"></a>
<span id="l528">                if (Files.exists(Paths.get(&quot;ts2.cert&quot;))) {</span><a href="#l528"></a>
<span id="l529">                    checkInvalidTsaCertKeyUsage();</span><a href="#l529"></a>
<span id="l530">                }</span><a href="#l530"></a>
<span id="l531">            } else {                        // Run as a standalone server</span><a href="#l531"></a>
<span id="l532">                System.out.println(&quot;TSA started at &quot; + host</span><a href="#l532"></a>
<span id="l533">                        + &quot;. Press Enter to quit server&quot;);</span><a href="#l533"></a>
<span id="l534">                System.in.read();</span><a href="#l534"></a>
<span id="l535">            }</span><a href="#l535"></a>
<span id="l536">        }</span><a href="#l536"></a>
<span id="l537">    }</span><a href="#l537"></a>
<span id="l538"></span><a href="#l538"></a>
<span id="l539">    private static void checkExpiration() throws Exception {</span><a href="#l539"></a>
<span id="l540"></span><a href="#l540"></a>
<span id="l541">        // Warning when expired or expiring</span><a href="#l541"></a>
<span id="l542">        signVerbose(null, &quot;unsigned.jar&quot;, &quot;expired.jar&quot;, &quot;expired&quot;)</span><a href="#l542"></a>
<span id="l543">                .shouldContain(&quot;signer certificate has expired&quot;)</span><a href="#l543"></a>
<span id="l544">                .shouldHaveExitValue(4);</span><a href="#l544"></a>
<span id="l545">        verify(&quot;expired.jar&quot;)</span><a href="#l545"></a>
<span id="l546">                .shouldContain(&quot;signer certificate has expired&quot;)</span><a href="#l546"></a>
<span id="l547">                .shouldHaveExitValue(4);</span><a href="#l547"></a>
<span id="l548">        signVerbose(null, &quot;unsigned.jar&quot;, &quot;expiring.jar&quot;, &quot;expiring&quot;)</span><a href="#l548"></a>
<span id="l549">                .shouldContain(&quot;signer certificate will expire within&quot;)</span><a href="#l549"></a>
<span id="l550">                .shouldHaveExitValue(0);</span><a href="#l550"></a>
<span id="l551">        verify(&quot;expiring.jar&quot;)</span><a href="#l551"></a>
<span id="l552">                .shouldContain(&quot;signer certificate will expire within&quot;)</span><a href="#l552"></a>
<span id="l553">                .shouldHaveExitValue(0);</span><a href="#l553"></a>
<span id="l554">        // Info for long</span><a href="#l554"></a>
<span id="l555">        signVerbose(null, &quot;unsigned.jar&quot;, &quot;long.jar&quot;, &quot;long&quot;)</span><a href="#l555"></a>
<span id="l556">                .shouldNotContain(&quot;signer certificate has expired&quot;)</span><a href="#l556"></a>
<span id="l557">                .shouldNotContain(&quot;signer certificate will expire within&quot;)</span><a href="#l557"></a>
<span id="l558">                .shouldContain(&quot;signer certificate will expire on&quot;)</span><a href="#l558"></a>
<span id="l559">                .shouldHaveExitValue(0);</span><a href="#l559"></a>
<span id="l560">        verify(&quot;long.jar&quot;)</span><a href="#l560"></a>
<span id="l561">                .shouldNotContain(&quot;signer certificate has expired&quot;)</span><a href="#l561"></a>
<span id="l562">                .shouldNotContain(&quot;signer certificate will expire within&quot;)</span><a href="#l562"></a>
<span id="l563">                .shouldNotContain(&quot;The signer certificate will expire&quot;)</span><a href="#l563"></a>
<span id="l564">                .shouldHaveExitValue(0);</span><a href="#l564"></a>
<span id="l565">        verify(&quot;long.jar&quot;, &quot;-verbose&quot;)</span><a href="#l565"></a>
<span id="l566">                .shouldContain(&quot;The signer certificate will expire&quot;)</span><a href="#l566"></a>
<span id="l567">                .shouldHaveExitValue(0);</span><a href="#l567"></a>
<span id="l568"></span><a href="#l568"></a>
<span id="l569">        // Both expired</span><a href="#l569"></a>
<span id="l570">        signVerbose(&quot;tsexpired&quot;, &quot;unsigned.jar&quot;,</span><a href="#l570"></a>
<span id="l571">                &quot;tsexpired-expired.jar&quot;, &quot;expired&quot;)</span><a href="#l571"></a>
<span id="l572">                .shouldContain(&quot;The signer certificate has expired.&quot;)</span><a href="#l572"></a>
<span id="l573">                .shouldContain(&quot;The timestamp has expired.&quot;)</span><a href="#l573"></a>
<span id="l574">                .shouldHaveExitValue(4);</span><a href="#l574"></a>
<span id="l575">        verify(&quot;tsexpired-expired.jar&quot;)</span><a href="#l575"></a>
<span id="l576">                .shouldContain(&quot;signer certificate has expired&quot;)</span><a href="#l576"></a>
<span id="l577">                .shouldContain(&quot;timestamp has expired.&quot;)</span><a href="#l577"></a>
<span id="l578">                .shouldHaveExitValue(4);</span><a href="#l578"></a>
<span id="l579"></span><a href="#l579"></a>
<span id="l580">        // TS expired but signer still good</span><a href="#l580"></a>
<span id="l581">        signVerbose(&quot;tsexpired&quot;, &quot;unsigned.jar&quot;,</span><a href="#l581"></a>
<span id="l582">                &quot;tsexpired-long.jar&quot;, &quot;long&quot;)</span><a href="#l582"></a>
<span id="l583">                .shouldContain(&quot;The timestamp expired on&quot;)</span><a href="#l583"></a>
<span id="l584">                .shouldHaveExitValue(0);</span><a href="#l584"></a>
<span id="l585">        verify(&quot;tsexpired-long.jar&quot;)</span><a href="#l585"></a>
<span id="l586">                .shouldMatch(&quot;timestamp expired on.*However, the JAR will be valid&quot;)</span><a href="#l586"></a>
<span id="l587">                .shouldNotContain(&quot;Error&quot;)</span><a href="#l587"></a>
<span id="l588">                .shouldHaveExitValue(0);</span><a href="#l588"></a>
<span id="l589"></span><a href="#l589"></a>
<span id="l590">        signVerbose(&quot;tsexpired&quot;, &quot;unsigned.jar&quot;,</span><a href="#l590"></a>
<span id="l591">                &quot;tsexpired-ca.jar&quot;, &quot;ca&quot;)</span><a href="#l591"></a>
<span id="l592">                .shouldContain(&quot;The timestamp has expired.&quot;)</span><a href="#l592"></a>
<span id="l593">                .shouldHaveExitValue(4);</span><a href="#l593"></a>
<span id="l594">        verify(&quot;tsexpired-ca.jar&quot;)</span><a href="#l594"></a>
<span id="l595">                .shouldNotContain(&quot;timestamp has expired&quot;)</span><a href="#l595"></a>
<span id="l596">                .shouldNotContain(&quot;Error&quot;)</span><a href="#l596"></a>
<span id="l597">                .shouldHaveExitValue(0);</span><a href="#l597"></a>
<span id="l598"></span><a href="#l598"></a>
<span id="l599">        // Warning when expiring</span><a href="#l599"></a>
<span id="l600">        sign(&quot;tsexpiring&quot;)</span><a href="#l600"></a>
<span id="l601">                .shouldContain(&quot;timestamp will expire within&quot;)</span><a href="#l601"></a>
<span id="l602">                .shouldHaveExitValue(0);</span><a href="#l602"></a>
<span id="l603">        verify(&quot;tsexpiring.jar&quot;)</span><a href="#l603"></a>
<span id="l604">                .shouldContain(&quot;timestamp will expire within&quot;)</span><a href="#l604"></a>
<span id="l605">                .shouldNotContain(&quot;still valid&quot;)</span><a href="#l605"></a>
<span id="l606">                .shouldHaveExitValue(0);</span><a href="#l606"></a>
<span id="l607"></span><a href="#l607"></a>
<span id="l608">        signVerbose(&quot;tsexpiring&quot;, &quot;unsigned.jar&quot;,</span><a href="#l608"></a>
<span id="l609">                &quot;tsexpiring-ca.jar&quot;, &quot;ca&quot;)</span><a href="#l609"></a>
<span id="l610">                .shouldContain(&quot;self-signed&quot;)</span><a href="#l610"></a>
<span id="l611">                .stderrShouldNotMatch(&quot;The.*expir&quot;)</span><a href="#l611"></a>
<span id="l612">                .shouldHaveExitValue(4); // self-signed</span><a href="#l612"></a>
<span id="l613">        verify(&quot;tsexpiring-ca.jar&quot;)</span><a href="#l613"></a>
<span id="l614">                .stderrShouldNotMatch(&quot;The.*expir&quot;)</span><a href="#l614"></a>
<span id="l615">                .shouldHaveExitValue(0);</span><a href="#l615"></a>
<span id="l616"></span><a href="#l616"></a>
<span id="l617">        signVerbose(&quot;tsexpiringsoon&quot;, &quot;unsigned.jar&quot;,</span><a href="#l617"></a>
<span id="l618">                &quot;tsexpiringsoon-long.jar&quot;, &quot;long&quot;)</span><a href="#l618"></a>
<span id="l619">                .shouldContain(&quot;The timestamp will expire&quot;)</span><a href="#l619"></a>
<span id="l620">                .shouldHaveExitValue(0);</span><a href="#l620"></a>
<span id="l621">        verify(&quot;tsexpiringsoon-long.jar&quot;)</span><a href="#l621"></a>
<span id="l622">                .shouldMatch(&quot;timestamp will expire.*However, the JAR will be valid until&quot;)</span><a href="#l622"></a>
<span id="l623">                .shouldHaveExitValue(0);</span><a href="#l623"></a>
<span id="l624"></span><a href="#l624"></a>
<span id="l625">        // Info for long</span><a href="#l625"></a>
<span id="l626">        sign(&quot;tslong&quot;)</span><a href="#l626"></a>
<span id="l627">                .shouldNotContain(&quot;timestamp has expired&quot;)</span><a href="#l627"></a>
<span id="l628">                .shouldNotContain(&quot;timestamp will expire within&quot;)</span><a href="#l628"></a>
<span id="l629">                .shouldContain(&quot;timestamp will expire on&quot;)</span><a href="#l629"></a>
<span id="l630">                .shouldContain(&quot;signer certificate will expire on&quot;)</span><a href="#l630"></a>
<span id="l631">                .shouldHaveExitValue(0);</span><a href="#l631"></a>
<span id="l632">        verify(&quot;tslong.jar&quot;)</span><a href="#l632"></a>
<span id="l633">                .shouldNotContain(&quot;timestamp has expired&quot;)</span><a href="#l633"></a>
<span id="l634">                .shouldNotContain(&quot;timestamp will expire within&quot;)</span><a href="#l634"></a>
<span id="l635">                .shouldNotContain(&quot;timestamp will expire on&quot;)</span><a href="#l635"></a>
<span id="l636">                .shouldNotContain(&quot;signer certificate will expire on&quot;)</span><a href="#l636"></a>
<span id="l637">                .shouldHaveExitValue(0);</span><a href="#l637"></a>
<span id="l638">        verify(&quot;tslong.jar&quot;, &quot;-verbose&quot;)</span><a href="#l638"></a>
<span id="l639">                .shouldContain(&quot;timestamp will expire on&quot;)</span><a href="#l639"></a>
<span id="l640">                .shouldContain(&quot;signer certificate will expire on&quot;)</span><a href="#l640"></a>
<span id="l641">                .shouldHaveExitValue(0);</span><a href="#l641"></a>
<span id="l642">    }</span><a href="#l642"></a>
<span id="l643"></span><a href="#l643"></a>
<span id="l644">    private static void checkInvalidTsaCertKeyUsage() throws Exception {</span><a href="#l644"></a>
<span id="l645"></span><a href="#l645"></a>
<span id="l646">        // Hack: Rewrite the TSA cert inside normal.jar into ts2.jar.</span><a href="#l646"></a>
<span id="l647"></span><a href="#l647"></a>
<span id="l648">        // Both the cert and the serial number must be rewritten.</span><a href="#l648"></a>
<span id="l649">        byte[] tsCert = Files.readAllBytes(Paths.get(&quot;ts.cert&quot;));</span><a href="#l649"></a>
<span id="l650">        byte[] ts2Cert = Files.readAllBytes(Paths.get(&quot;ts2.cert&quot;));</span><a href="#l650"></a>
<span id="l651">        byte[] tsSerial = getCert(tsCert)</span><a href="#l651"></a>
<span id="l652">                .getSerialNumber().toByteArray();</span><a href="#l652"></a>
<span id="l653">        byte[] ts2Serial = getCert(ts2Cert)</span><a href="#l653"></a>
<span id="l654">                .getSerialNumber().toByteArray();</span><a href="#l654"></a>
<span id="l655"></span><a href="#l655"></a>
<span id="l656">        byte[] oldBlock;</span><a href="#l656"></a>
<span id="l657">        try (JarFile normal = new JarFile(&quot;normal.jar&quot;)) {</span><a href="#l657"></a>
<span id="l658">            oldBlock = Utils.readAllBytes(normal.getInputStream(</span><a href="#l658"></a>
<span id="l659">                    normal.getJarEntry(&quot;META-INF/SIGNER.RSA&quot;)));</span><a href="#l659"></a>
<span id="l660">        }</span><a href="#l660"></a>
<span id="l661"></span><a href="#l661"></a>
<span id="l662">        JarUtils.updateJar(&quot;normal.jar&quot;, &quot;ts2.jar&quot;,</span><a href="#l662"></a>
<span id="l663">                mapOf(&quot;META-INF/SIGNER.RSA&quot;,</span><a href="#l663"></a>
<span id="l664">                        updateBytes(updateBytes(oldBlock, tsCert, ts2Cert),</span><a href="#l664"></a>
<span id="l665">                                tsSerial, ts2Serial)));</span><a href="#l665"></a>
<span id="l666"></span><a href="#l666"></a>
<span id="l667">        verify(&quot;ts2.jar&quot;, &quot;-verbose&quot;, &quot;-certs&quot;)</span><a href="#l667"></a>
<span id="l668">                .shouldHaveExitValue(64)</span><a href="#l668"></a>
<span id="l669">                .shouldContain(&quot;jar verified&quot;)</span><a href="#l669"></a>
<span id="l670">                .shouldContain(&quot;Invalid TSA certificate chain: Extended key usage does not permit use for TSA server&quot;);</span><a href="#l670"></a>
<span id="l671">    }</span><a href="#l671"></a>
<span id="l672"></span><a href="#l672"></a>
<span id="l673">    public static X509Certificate getCert(byte[] data)</span><a href="#l673"></a>
<span id="l674">            throws CertificateException, IOException {</span><a href="#l674"></a>
<span id="l675">        return (X509Certificate)</span><a href="#l675"></a>
<span id="l676">                CertificateFactory.getInstance(&quot;X.509&quot;)</span><a href="#l676"></a>
<span id="l677">                        .generateCertificate(new ByteArrayInputStream(data));</span><a href="#l677"></a>
<span id="l678">    }</span><a href="#l678"></a>
<span id="l679"></span><a href="#l679"></a>
<span id="l680">    private static byte[] updateBytes(byte[] old, byte[] from, byte[] to) {</span><a href="#l680"></a>
<span id="l681">        int pos = 0;</span><a href="#l681"></a>
<span id="l682">        while (true) {</span><a href="#l682"></a>
<span id="l683">            if (pos + from.length &gt; old.length) {</span><a href="#l683"></a>
<span id="l684">                return null;</span><a href="#l684"></a>
<span id="l685">            }</span><a href="#l685"></a>
<span id="l686">            if (Arrays.equals(Arrays.copyOfRange(old, pos, pos+from.length), from)) {</span><a href="#l686"></a>
<span id="l687">                byte[] result = old.clone();</span><a href="#l687"></a>
<span id="l688">                System.arraycopy(to, 0, result, pos, from.length);</span><a href="#l688"></a>
<span id="l689">                return result;</span><a href="#l689"></a>
<span id="l690">            }</span><a href="#l690"></a>
<span id="l691">            pos++;</span><a href="#l691"></a>
<span id="l692">        }</span><a href="#l692"></a>
<span id="l693">    }</span><a href="#l693"></a>
<span id="l694"></span><a href="#l694"></a>
<span id="l695">    private static void checkMissingOrInvalidFiles(String s)</span><a href="#l695"></a>
<span id="l696">            throws Throwable {</span><a href="#l696"></a>
<span id="l697"></span><a href="#l697"></a>
<span id="l698">        JarUtils.updateJar(s, &quot;1.jar&quot;, mapOf(&quot;META-INF/SIGNER.SF&quot;, Boolean.FALSE));</span><a href="#l698"></a>
<span id="l699">        verify(&quot;1.jar&quot;, &quot;-verbose&quot;)</span><a href="#l699"></a>
<span id="l700">                .shouldHaveExitValue(16)</span><a href="#l700"></a>
<span id="l701">                .shouldContain(&quot;treated as unsigned&quot;)</span><a href="#l701"></a>
<span id="l702">                .shouldContain(&quot;Missing signature-related file META-INF/SIGNER.SF&quot;);</span><a href="#l702"></a>
<span id="l703">        JarUtils.updateJar(s, &quot;2.jar&quot;, mapOf(&quot;META-INF/SIGNER.RSA&quot;, Boolean.FALSE));</span><a href="#l703"></a>
<span id="l704">        verify(&quot;2.jar&quot;, &quot;-verbose&quot;)</span><a href="#l704"></a>
<span id="l705">                .shouldHaveExitValue(16)</span><a href="#l705"></a>
<span id="l706">                .shouldContain(&quot;treated as unsigned&quot;)</span><a href="#l706"></a>
<span id="l707">                .shouldContain(&quot;Missing block file for signature-related file META-INF/SIGNER.SF&quot;);</span><a href="#l707"></a>
<span id="l708">        JarUtils.updateJar(s, &quot;3.jar&quot;, mapOf(&quot;META-INF/SIGNER.SF&quot;, &quot;dummy&quot;));</span><a href="#l708"></a>
<span id="l709">        verify(&quot;3.jar&quot;, &quot;-verbose&quot;)</span><a href="#l709"></a>
<span id="l710">                .shouldHaveExitValue(16)</span><a href="#l710"></a>
<span id="l711">                .shouldContain(&quot;treated as unsigned&quot;)</span><a href="#l711"></a>
<span id="l712">                .shouldContain(&quot;Unparsable signature-related file META-INF/SIGNER.SF&quot;);</span><a href="#l712"></a>
<span id="l713">        JarUtils.updateJar(s, &quot;4.jar&quot;, mapOf(&quot;META-INF/SIGNER.RSA&quot;, &quot;dummy&quot;));</span><a href="#l713"></a>
<span id="l714">        verify(&quot;4.jar&quot;, &quot;-verbose&quot;)</span><a href="#l714"></a>
<span id="l715">                .shouldHaveExitValue(16)</span><a href="#l715"></a>
<span id="l716">                .shouldContain(&quot;treated as unsigned&quot;)</span><a href="#l716"></a>
<span id="l717">                .shouldContain(&quot;Unparsable signature-related file META-INF/SIGNER.RSA&quot;);</span><a href="#l717"></a>
<span id="l718">    }</span><a href="#l718"></a>
<span id="l719"></span><a href="#l719"></a>
<span id="l720">    static OutputAnalyzer jarsigner(List&lt;String&gt; extra)</span><a href="#l720"></a>
<span id="l721">            throws Exception {</span><a href="#l721"></a>
<span id="l722">        List&lt;String&gt; args = new ArrayList&lt;&gt;(</span><a href="#l722"></a>
<span id="l723">                listOf(&quot;-keystore&quot;, &quot;ks&quot;, &quot;-storepass&quot;, &quot;changeit&quot;));</span><a href="#l723"></a>
<span id="l724">        args.addAll(extra);</span><a href="#l724"></a>
<span id="l725">        return SecurityTools.jarsigner(args);</span><a href="#l725"></a>
<span id="l726">    }</span><a href="#l726"></a>
<span id="l727"></span><a href="#l727"></a>
<span id="l728">    static OutputAnalyzer verify(String file, String... extra)</span><a href="#l728"></a>
<span id="l729">            throws Exception {</span><a href="#l729"></a>
<span id="l730">        List&lt;String&gt; args = new ArrayList&lt;&gt;();</span><a href="#l730"></a>
<span id="l731">        args.add(&quot;-verify&quot;);</span><a href="#l731"></a>
<span id="l732">        args.add(&quot;-strict&quot;);</span><a href="#l732"></a>
<span id="l733">        args.add(file);</span><a href="#l733"></a>
<span id="l734">        args.addAll(Arrays.asList(extra));</span><a href="#l734"></a>
<span id="l735">        return jarsigner(args);</span><a href="#l735"></a>
<span id="l736">    }</span><a href="#l736"></a>
<span id="l737"></span><a href="#l737"></a>
<span id="l738">    static void checkBadKU(String file) throws Exception {</span><a href="#l738"></a>
<span id="l739">        System.err.println(&quot;BadKU: &quot; + file);</span><a href="#l739"></a>
<span id="l740">        verify(file)</span><a href="#l740"></a>
<span id="l741">                .shouldHaveExitValue(16)</span><a href="#l741"></a>
<span id="l742">                .shouldContain(&quot;treated as unsigned&quot;)</span><a href="#l742"></a>
<span id="l743">                .shouldContain(&quot;re-run jarsigner with debug enabled&quot;);</span><a href="#l743"></a>
<span id="l744">        verify(file, &quot;-verbose&quot;)</span><a href="#l744"></a>
<span id="l745">                .shouldHaveExitValue(16)</span><a href="#l745"></a>
<span id="l746">                .shouldContain(&quot;Signed by&quot;)</span><a href="#l746"></a>
<span id="l747">                .shouldContain(&quot;treated as unsigned&quot;)</span><a href="#l747"></a>
<span id="l748">                .shouldContain(&quot;re-run jarsigner with debug enabled&quot;);</span><a href="#l748"></a>
<span id="l749">        verify(file, &quot;-J-Djava.security.debug=jar&quot;)</span><a href="#l749"></a>
<span id="l750">                .shouldHaveExitValue(16)</span><a href="#l750"></a>
<span id="l751">                .shouldContain(&quot;SignatureException: Key usage restricted&quot;)</span><a href="#l751"></a>
<span id="l752">                .shouldContain(&quot;treated as unsigned&quot;)</span><a href="#l752"></a>
<span id="l753">                .shouldContain(&quot;re-run jarsigner with debug enabled&quot;);</span><a href="#l753"></a>
<span id="l754">    }</span><a href="#l754"></a>
<span id="l755"></span><a href="#l755"></a>
<span id="l756">    static void checkDisabled(String file) throws Exception {</span><a href="#l756"></a>
<span id="l757">        verify(file)</span><a href="#l757"></a>
<span id="l758">                .shouldHaveExitValue(16)</span><a href="#l758"></a>
<span id="l759">                .shouldContain(&quot;treated as unsigned&quot;)</span><a href="#l759"></a>
<span id="l760">                .shouldMatch(&quot;weak algorithm that is now disabled.&quot;)</span><a href="#l760"></a>
<span id="l761">                .shouldMatch(&quot;Re-run jarsigner with the -verbose option for more details&quot;);</span><a href="#l761"></a>
<span id="l762">        verify(file, &quot;-verbose&quot;)</span><a href="#l762"></a>
<span id="l763">                .shouldHaveExitValue(16)</span><a href="#l763"></a>
<span id="l764">                .shouldContain(&quot;treated as unsigned&quot;)</span><a href="#l764"></a>
<span id="l765">                .shouldMatch(&quot;weak algorithm that is now disabled by&quot;)</span><a href="#l765"></a>
<span id="l766">                .shouldMatch(&quot;Digest algorithm: .*(disabled)&quot;)</span><a href="#l766"></a>
<span id="l767">                .shouldMatch(&quot;Signature algorithm: .*(disabled)&quot;)</span><a href="#l767"></a>
<span id="l768">                .shouldMatch(&quot;Timestamp digest algorithm: .*(disabled)&quot;)</span><a href="#l768"></a>
<span id="l769">                .shouldNotMatch(&quot;Timestamp signature algorithm: .*(weak).*(weak)&quot;)</span><a href="#l769"></a>
<span id="l770">                .shouldMatch(&quot;Timestamp signature algorithm: .*key.*(disabled)&quot;);</span><a href="#l770"></a>
<span id="l771">        verify(file, &quot;-J-Djava.security.debug=jar&quot;)</span><a href="#l771"></a>
<span id="l772">                .shouldHaveExitValue(16)</span><a href="#l772"></a>
<span id="l773">                .shouldMatch(&quot;SignatureException:.*keysize&quot;);</span><a href="#l773"></a>
<span id="l774">    }</span><a href="#l774"></a>
<span id="l775"></span><a href="#l775"></a>
<span id="l776">    static void checkHalfWeak(String file) throws Exception {</span><a href="#l776"></a>
<span id="l777">        verify(file)</span><a href="#l777"></a>
<span id="l778">                .shouldHaveExitValue(0)</span><a href="#l778"></a>
<span id="l779">                .shouldNotContain(&quot;treated as unsigned&quot;);</span><a href="#l779"></a>
<span id="l780">        verify(file, &quot;-verbose&quot;)</span><a href="#l780"></a>
<span id="l781">                .shouldHaveExitValue(0)</span><a href="#l781"></a>
<span id="l782">                .shouldNotContain(&quot;treated as unsigned&quot;)</span><a href="#l782"></a>
<span id="l783">                .shouldMatch(&quot;Digest algorithm: .*(weak)&quot;)</span><a href="#l783"></a>
<span id="l784">                .shouldNotMatch(&quot;Signature algorithm: .*(weak)&quot;)</span><a href="#l784"></a>
<span id="l785">                .shouldNotMatch(&quot;Signature algorithm: .*(disabled)&quot;)</span><a href="#l785"></a>
<span id="l786">                .shouldNotMatch(&quot;Timestamp digest algorithm: .*(weak)&quot;)</span><a href="#l786"></a>
<span id="l787">                .shouldNotMatch(&quot;Timestamp signature algorithm: .*(weak).*(weak)&quot;)</span><a href="#l787"></a>
<span id="l788">                .shouldNotMatch(&quot;Timestamp signature algorithm: .*(disabled).*(disabled)&quot;)</span><a href="#l788"></a>
<span id="l789">                .shouldNotMatch(&quot;Timestamp signature algorithm: .*key.*(weak)&quot;)</span><a href="#l789"></a>
<span id="l790">                .shouldNotMatch(&quot;Timestamp signature algorithm: .*key.*(disabled)&quot;);</span><a href="#l790"></a>
<span id="l791">     }</span><a href="#l791"></a>
<span id="l792"></span><a href="#l792"></a>
<span id="l793">    static void checkMultiple(String file) throws Exception {</span><a href="#l793"></a>
<span id="l794">        verify(file)</span><a href="#l794"></a>
<span id="l795">                .shouldHaveExitValue(0)</span><a href="#l795"></a>
<span id="l796">                .shouldContain(&quot;jar verified&quot;);</span><a href="#l796"></a>
<span id="l797">        verify(file, &quot;-verbose&quot;, &quot;-certs&quot;)</span><a href="#l797"></a>
<span id="l798">                .shouldHaveExitValue(0)</span><a href="#l798"></a>
<span id="l799">                .shouldContain(&quot;jar verified&quot;)</span><a href="#l799"></a>
<span id="l800">                .shouldMatch(&quot;X.509.*CN=dsakey&quot;)</span><a href="#l800"></a>
<span id="l801">                .shouldNotMatch(&quot;X.509.*CN=disabledkeysize&quot;)</span><a href="#l801"></a>
<span id="l802">                .shouldMatch(&quot;Signed by .*CN=dsakey&quot;)</span><a href="#l802"></a>
<span id="l803">                .shouldMatch(&quot;Signed by .*CN=disabledkeysize&quot;)</span><a href="#l803"></a>
<span id="l804">                .shouldMatch(&quot;Signature algorithm: .*key.*(disabled)&quot;);</span><a href="#l804"></a>
<span id="l805">    }</span><a href="#l805"></a>
<span id="l806"></span><a href="#l806"></a>
<span id="l807">    static void checkWeak(String file) throws Exception {</span><a href="#l807"></a>
<span id="l808">        verify(file)</span><a href="#l808"></a>
<span id="l809">                .shouldHaveExitValue(0)</span><a href="#l809"></a>
<span id="l810">                .shouldNotContain(&quot;treated as unsigned&quot;);</span><a href="#l810"></a>
<span id="l811">        verify(file, &quot;-verbose&quot;)</span><a href="#l811"></a>
<span id="l812">                .shouldHaveExitValue(0)</span><a href="#l812"></a>
<span id="l813">                .shouldNotContain(&quot;treated as unsigned&quot;)</span><a href="#l813"></a>
<span id="l814">                .shouldMatch(&quot;Digest algorithm: .*(weak)&quot;)</span><a href="#l814"></a>
<span id="l815">                .shouldMatch(&quot;Signature algorithm: .*(weak)&quot;)</span><a href="#l815"></a>
<span id="l816">                .shouldMatch(&quot;Timestamp digest algorithm: .*(weak)&quot;)</span><a href="#l816"></a>
<span id="l817">                .shouldNotMatch(&quot;Timestamp signature algorithm: .*(weak).*(weak)&quot;)</span><a href="#l817"></a>
<span id="l818">                .shouldMatch(&quot;Timestamp signature algorithm: .*key.*(weak)&quot;);</span><a href="#l818"></a>
<span id="l819">        verify(file, &quot;-J-Djava.security.debug=jar&quot;)</span><a href="#l819"></a>
<span id="l820">                .shouldHaveExitValue(0)</span><a href="#l820"></a>
<span id="l821">                .shouldNotMatch(&quot;SignatureException:.*disabled&quot;);</span><a href="#l821"></a>
<span id="l822"></span><a href="#l822"></a>
<span id="l823">        // keytool should print out warnings when reading or</span><a href="#l823"></a>
<span id="l824">        // generating cert/cert req using legacy algorithms.</span><a href="#l824"></a>
<span id="l825">        String sout = SecurityTools.keytool(&quot;-printcert -jarfile &quot; + file)</span><a href="#l825"></a>
<span id="l826">                .stderrShouldContain(&quot;The TSA certificate uses a 1024-bit RSA key&quot; +</span><a href="#l826"></a>
<span id="l827">                        &quot; which is considered a security risk.&quot; +</span><a href="#l827"></a>
<span id="l828">                        &quot; This key size will be disabled in a future update.&quot;)</span><a href="#l828"></a>
<span id="l829">                .getStdout();</span><a href="#l829"></a>
<span id="l830">        if (sout.indexOf(&quot;weak&quot;, sout.indexOf(&quot;Timestamp:&quot;)) &lt; 0) {</span><a href="#l830"></a>
<span id="l831">            throw new RuntimeException(&quot;timestamp not weak: &quot; + sout);</span><a href="#l831"></a>
<span id="l832">        }</span><a href="#l832"></a>
<span id="l833">    }</span><a href="#l833"></a>
<span id="l834"></span><a href="#l834"></a>
<span id="l835">    static void checkHalfDisabled(String file) throws Exception {</span><a href="#l835"></a>
<span id="l836">        verify(file)</span><a href="#l836"></a>
<span id="l837">                .shouldHaveExitValue(16)</span><a href="#l837"></a>
<span id="l838">                .shouldContain(&quot;treated as unsigned&quot;)</span><a href="#l838"></a>
<span id="l839">                .shouldMatch(&quot;weak algorithm that is now disabled.&quot;)</span><a href="#l839"></a>
<span id="l840">                .shouldMatch(&quot;Re-run jarsigner with the -verbose option for more details&quot;);</span><a href="#l840"></a>
<span id="l841">        verify(file, &quot;-verbose&quot;)</span><a href="#l841"></a>
<span id="l842">                .shouldHaveExitValue(16)</span><a href="#l842"></a>
<span id="l843">                .shouldContain(&quot;treated as unsigned&quot;)</span><a href="#l843"></a>
<span id="l844">                .shouldMatch(&quot;weak algorithm that is now disabled by&quot;)</span><a href="#l844"></a>
<span id="l845">                .shouldMatch(&quot;Digest algorithm: .*(disabled)&quot;)</span><a href="#l845"></a>
<span id="l846">                .shouldNotMatch(&quot;Signature algorithm: .*(weak)&quot;)</span><a href="#l846"></a>
<span id="l847">                .shouldNotMatch(&quot;Signature algorithm: .*(disabled)&quot;)</span><a href="#l847"></a>
<span id="l848">                .shouldNotMatch(&quot;Timestamp digest algorithm: .*(disabled)&quot;)</span><a href="#l848"></a>
<span id="l849">                .shouldNotMatch(&quot;Timestamp signature algorithm: .*(weak).*(weak)&quot;)</span><a href="#l849"></a>
<span id="l850">                .shouldNotMatch(&quot;Timestamp signature algorithm: .*(disabled).*(disabled)&quot;)</span><a href="#l850"></a>
<span id="l851">                .shouldNotMatch(&quot;Timestamp signature algorithm: .*key.*(weak)&quot;)</span><a href="#l851"></a>
<span id="l852">                .shouldNotMatch(&quot;Timestamp signature algorithm: .*key.*(disabled)&quot;);</span><a href="#l852"></a>
<span id="l853">    }</span><a href="#l853"></a>
<span id="l854"></span><a href="#l854"></a>
<span id="l855">    static void checkMultipleWeak(String file) throws Exception {</span><a href="#l855"></a>
<span id="l856">        verify(file)</span><a href="#l856"></a>
<span id="l857">                .shouldHaveExitValue(0)</span><a href="#l857"></a>
<span id="l858">                .shouldContain(&quot;jar verified&quot;);</span><a href="#l858"></a>
<span id="l859">        verify(file, &quot;-verbose&quot;, &quot;-certs&quot;)</span><a href="#l859"></a>
<span id="l860">                .shouldHaveExitValue(0)</span><a href="#l860"></a>
<span id="l861">                .shouldContain(&quot;jar verified&quot;)</span><a href="#l861"></a>
<span id="l862">                .shouldMatch(&quot;X.509.*CN=dsakey&quot;)</span><a href="#l862"></a>
<span id="l863">                .shouldMatch(&quot;X.509.*CN=weakkeysize&quot;)</span><a href="#l863"></a>
<span id="l864">                .shouldMatch(&quot;Signed by .*CN=dsakey&quot;)</span><a href="#l864"></a>
<span id="l865">                .shouldMatch(&quot;Signed by .*CN=weakkeysize&quot;)</span><a href="#l865"></a>
<span id="l866">                .shouldMatch(&quot;Signature algorithm: .*key.*(weak)&quot;);</span><a href="#l866"></a>
<span id="l867">    }</span><a href="#l867"></a>
<span id="l868"></span><a href="#l868"></a>
<span id="l869">    static void checkTimestamp(String file, String policyId, String digestAlg)</span><a href="#l869"></a>
<span id="l870">            throws Exception {</span><a href="#l870"></a>
<span id="l871">        try (JarFile jf = new JarFile(file)) {</span><a href="#l871"></a>
<span id="l872">            JarEntry je = jf.getJarEntry(&quot;META-INF/SIGNER.RSA&quot;);</span><a href="#l872"></a>
<span id="l873">            try (InputStream is = jf.getInputStream(je)) {</span><a href="#l873"></a>
<span id="l874">                byte[] content = IOUtils.readAllBytes(is);</span><a href="#l874"></a>
<span id="l875">                PKCS7 p7 = new PKCS7(content);</span><a href="#l875"></a>
<span id="l876">                SignerInfo[] si = p7.getSignerInfos();</span><a href="#l876"></a>
<span id="l877">                if (si == null || si.length == 0) {</span><a href="#l877"></a>
<span id="l878">                    throw new Exception(&quot;Not signed&quot;);</span><a href="#l878"></a>
<span id="l879">                }</span><a href="#l879"></a>
<span id="l880">                PKCS9Attribute p9 = si[0].getUnauthenticatedAttributes()</span><a href="#l880"></a>
<span id="l881">                        .getAttribute(PKCS9Attribute.SIGNATURE_TIMESTAMP_TOKEN_OID);</span><a href="#l881"></a>
<span id="l882">                PKCS7 tsToken = new PKCS7((byte[]) p9.getValue());</span><a href="#l882"></a>
<span id="l883">                TimestampToken tt =</span><a href="#l883"></a>
<span id="l884">                        new TimestampToken(tsToken.getContentInfo().getData());</span><a href="#l884"></a>
<span id="l885">                if (!tt.getHashAlgorithm().toString().equals(digestAlg)) {</span><a href="#l885"></a>
<span id="l886">                    throw new Exception(&quot;Digest alg different&quot;);</span><a href="#l886"></a>
<span id="l887">                }</span><a href="#l887"></a>
<span id="l888">                if (!tt.getPolicyID().equals(policyId)) {</span><a href="#l888"></a>
<span id="l889">                    throw new Exception(&quot;policyId different&quot;);</span><a href="#l889"></a>
<span id="l890">                }</span><a href="#l890"></a>
<span id="l891">            }</span><a href="#l891"></a>
<span id="l892">        }</span><a href="#l892"></a>
<span id="l893">    }</span><a href="#l893"></a>
<span id="l894"></span><a href="#l894"></a>
<span id="l895">    static int which = 0;</span><a href="#l895"></a>
<span id="l896"></span><a href="#l896"></a>
<span id="l897">    /**</span><a href="#l897"></a>
<span id="l898">     * Sign with a TSA path. Always use alias &quot;signer&quot; to sign &quot;unsigned.jar&quot;.</span><a href="#l898"></a>
<span id="l899">     * The signed jar name is always path.jar.</span><a href="#l899"></a>
<span id="l900">     *</span><a href="#l900"></a>
<span id="l901">     * @param extra more args given to jarsigner</span><a href="#l901"></a>
<span id="l902">     */</span><a href="#l902"></a>
<span id="l903">    static OutputAnalyzer sign(String path, String... extra)</span><a href="#l903"></a>
<span id="l904">            throws Exception {</span><a href="#l904"></a>
<span id="l905">        return signVerbose(</span><a href="#l905"></a>
<span id="l906">                path,</span><a href="#l906"></a>
<span id="l907">                &quot;unsigned.jar&quot;,</span><a href="#l907"></a>
<span id="l908">                path + &quot;.jar&quot;,</span><a href="#l908"></a>
<span id="l909">                &quot;signer&quot;,</span><a href="#l909"></a>
<span id="l910">                extra);</span><a href="#l910"></a>
<span id="l911">    }</span><a href="#l911"></a>
<span id="l912"></span><a href="#l912"></a>
<span id="l913">    static OutputAnalyzer signVerbose(</span><a href="#l913"></a>
<span id="l914">            String path,    // TSA URL path</span><a href="#l914"></a>
<span id="l915">            String oldJar,</span><a href="#l915"></a>
<span id="l916">            String newJar,</span><a href="#l916"></a>
<span id="l917">            String alias,   // signer</span><a href="#l917"></a>
<span id="l918">            String...extra) throws Exception {</span><a href="#l918"></a>
<span id="l919">        which++;</span><a href="#l919"></a>
<span id="l920">        System.out.println(&quot;\n&gt;&gt; Test #&quot; + which);</span><a href="#l920"></a>
<span id="l921">        List&lt;String&gt; args = new ArrayList&lt;&gt;();</span><a href="#l921"></a>
<span id="l922">        args.add(&quot;-strict&quot;);</span><a href="#l922"></a>
<span id="l923">        args.add(&quot;-verbose&quot;);</span><a href="#l923"></a>
<span id="l924">        args.add(&quot;-debug&quot;);</span><a href="#l924"></a>
<span id="l925">        args.add(&quot;-signedjar&quot;);</span><a href="#l925"></a>
<span id="l926">        args.add(newJar);</span><a href="#l926"></a>
<span id="l927">        args.add(oldJar);</span><a href="#l927"></a>
<span id="l928">        args.add(alias);</span><a href="#l928"></a>
<span id="l929">        if (path != null) {</span><a href="#l929"></a>
<span id="l930">            args.add(&quot;-tsa&quot;);</span><a href="#l930"></a>
<span id="l931">            args.add(host + path);</span><a href="#l931"></a>
<span id="l932">         }</span><a href="#l932"></a>
<span id="l933">        args.addAll(Arrays.asList(extra));</span><a href="#l933"></a>
<span id="l934">        return jarsigner(args);</span><a href="#l934"></a>
<span id="l935">    }</span><a href="#l935"></a>
<span id="l936"></span><a href="#l936"></a>
<span id="l937">    static void prepare() throws Exception {</span><a href="#l937"></a>
<span id="l938">        JarUtils.createJar(&quot;unsigned.jar&quot;, &quot;A&quot;);</span><a href="#l938"></a>
<span id="l939">        Files.deleteIfExists(Paths.get(&quot;ks&quot;));</span><a href="#l939"></a>
<span id="l940">        keytool(&quot;-alias signer -genkeypair -ext bc -dname CN=signer&quot;);</span><a href="#l940"></a>
<span id="l941">        keytool(&quot;-alias oldsigner -genkeypair -dname CN=oldsigner&quot;);</span><a href="#l941"></a>
<span id="l942">        keytool(&quot;-alias dsakey -genkeypair -keyalg DSA -dname CN=dsakey&quot;);</span><a href="#l942"></a>
<span id="l943">        keytool(&quot;-alias weakkeysize -genkeypair -keysize 1024 -dname CN=weakkeysize&quot;);</span><a href="#l943"></a>
<span id="l944">        keytool(&quot;-alias disabledkeysize -genkeypair -keysize 512 -dname CN=disabledkeysize&quot;);</span><a href="#l944"></a>
<span id="l945">        keytool(&quot;-alias badku -genkeypair -dname CN=badku&quot;);</span><a href="#l945"></a>
<span id="l946">        keytool(&quot;-alias ts -genkeypair -dname CN=ts&quot;);</span><a href="#l946"></a>
<span id="l947">        keytool(&quot;-alias tsold -genkeypair -dname CN=tsold&quot;);</span><a href="#l947"></a>
<span id="l948">        keytool(&quot;-alias tsweak -genkeypair -keysize 1024 -dname CN=tsweak&quot;);</span><a href="#l948"></a>
<span id="l949">        keytool(&quot;-alias tsdisabled -genkeypair -keysize 512 -dname CN=tsdisabled&quot;);</span><a href="#l949"></a>
<span id="l950">        keytool(&quot;-alias tsbad1 -genkeypair -dname CN=tsbad1&quot;);</span><a href="#l950"></a>
<span id="l951">        keytool(&quot;-alias tsbad2 -genkeypair -dname CN=tsbad2&quot;);</span><a href="#l951"></a>
<span id="l952">        keytool(&quot;-alias tsbad3 -genkeypair -dname CN=tsbad3&quot;);</span><a href="#l952"></a>
<span id="l953">        keytool(&quot;-alias tsnoca -genkeypair -dname CN=tsnoca&quot;);</span><a href="#l953"></a>
<span id="l954"></span><a href="#l954"></a>
<span id="l955">        keytool(&quot;-alias expired -genkeypair -dname CN=expired&quot;);</span><a href="#l955"></a>
<span id="l956">        keytool(&quot;-alias expiring -genkeypair -dname CN=expiring&quot;);</span><a href="#l956"></a>
<span id="l957">        keytool(&quot;-alias long -genkeypair -dname CN=long&quot;);</span><a href="#l957"></a>
<span id="l958">        keytool(&quot;-alias tsexpired -genkeypair -dname CN=tsexpired&quot;);</span><a href="#l958"></a>
<span id="l959">        keytool(&quot;-alias tsexpiring -genkeypair -dname CN=tsexpiring&quot;);</span><a href="#l959"></a>
<span id="l960">        keytool(&quot;-alias tsexpiringsoon -genkeypair -dname CN=tsexpiringsoon&quot;);</span><a href="#l960"></a>
<span id="l961">        keytool(&quot;-alias tslong -genkeypair -dname CN=tslong&quot;);</span><a href="#l961"></a>
<span id="l962"></span><a href="#l962"></a>
<span id="l963">        // tsnoca's issuer will be removed from keystore later</span><a href="#l963"></a>
<span id="l964">        keytool(&quot;-alias ca -genkeypair -ext bc -dname CN=CA&quot;);</span><a href="#l964"></a>
<span id="l965">        gencert(&quot;tsnoca&quot;, &quot;-ext eku:critical=ts&quot;);</span><a href="#l965"></a>
<span id="l966">        keytool(&quot;-delete -alias ca&quot;);</span><a href="#l966"></a>
<span id="l967">        keytool(&quot;-alias ca -genkeypair -ext bc -dname CN=CA -startdate -40d&quot;);</span><a href="#l967"></a>
<span id="l968"></span><a href="#l968"></a>
<span id="l969">        gencert(&quot;signer&quot;);</span><a href="#l969"></a>
<span id="l970">        gencert(&quot;oldsigner&quot;, &quot;-startdate -30d -validity 20&quot;);</span><a href="#l970"></a>
<span id="l971">        gencert(&quot;dsakey&quot;);</span><a href="#l971"></a>
<span id="l972">        gencert(&quot;weakkeysize&quot;);</span><a href="#l972"></a>
<span id="l973">        gencert(&quot;disabledkeysize&quot;);</span><a href="#l973"></a>
<span id="l974">        gencert(&quot;badku&quot;, &quot;-ext ku:critical=keyAgreement&quot;);</span><a href="#l974"></a>
<span id="l975">        gencert(&quot;ts&quot;, &quot;-ext eku:critical=ts -validity 500&quot;);</span><a href="#l975"></a>
<span id="l976"></span><a href="#l976"></a>
<span id="l977">        gencert(&quot;expired&quot;, &quot;-validity 10 -startdate -12d&quot;);</span><a href="#l977"></a>
<span id="l978">        gencert(&quot;expiring&quot;, &quot;-validity 178&quot;);</span><a href="#l978"></a>
<span id="l979">        gencert(&quot;long&quot;, &quot;-validity 182&quot;);</span><a href="#l979"></a>
<span id="l980">        gencert(&quot;tsexpired&quot;, &quot;-ext eku:critical=ts -validity 10 -startdate -12d&quot;);</span><a href="#l980"></a>
<span id="l981">        gencert(&quot;tsexpiring&quot;, &quot;-ext eku:critical=ts -validity 364&quot;);</span><a href="#l981"></a>
<span id="l982">        gencert(&quot;tsexpiringsoon&quot;, &quot;-ext eku:critical=ts -validity 170&quot;); // earlier than expiring</span><a href="#l982"></a>
<span id="l983">        gencert(&quot;tslong&quot;, &quot;-ext eku:critical=ts -validity 367&quot;);</span><a href="#l983"></a>
<span id="l984"></span><a href="#l984"></a>
<span id="l985"></span><a href="#l985"></a>
<span id="l986">        for (int i = 0; i &lt; 5; i++) {</span><a href="#l986"></a>
<span id="l987">            // Issue another cert for &quot;ts&quot; with a different EKU.</span><a href="#l987"></a>
<span id="l988">            // Length might be different because serial number is</span><a href="#l988"></a>
<span id="l989">            // random. Try several times until a cert with the same</span><a href="#l989"></a>
<span id="l990">            // length is generated so we can substitute ts.cert</span><a href="#l990"></a>
<span id="l991">            // embedded in the PKCS7 block with ts2.cert.</span><a href="#l991"></a>
<span id="l992">            // If cannot create one, related test will be ignored.</span><a href="#l992"></a>
<span id="l993">            keytool(&quot;-gencert -alias ca -infile ts.req -outfile ts2.cert &quot; +</span><a href="#l993"></a>
<span id="l994">                    &quot;-ext eku:critical=1.3.6.1.5.5.7.3.9&quot;);</span><a href="#l994"></a>
<span id="l995">            if (Files.size(Paths.get(&quot;ts.cert&quot;)) != Files.size(Paths.get(&quot;ts2.cert&quot;))) {</span><a href="#l995"></a>
<span id="l996">                Files.delete(Paths.get(&quot;ts2.cert&quot;));</span><a href="#l996"></a>
<span id="l997">                System.out.println(&quot;Warning: cannot create same length&quot;);</span><a href="#l997"></a>
<span id="l998">            } else {</span><a href="#l998"></a>
<span id="l999">                break;</span><a href="#l999"></a>
<span id="l1000">            }</span><a href="#l1000"></a>
<span id="l1001">        }</span><a href="#l1001"></a>
<span id="l1002"></span><a href="#l1002"></a>
<span id="l1003">        gencert(&quot;tsold&quot;, &quot;-ext eku:critical=ts -startdate -40d -validity 500&quot;);</span><a href="#l1003"></a>
<span id="l1004"></span><a href="#l1004"></a>
<span id="l1005">        gencert(&quot;tsweak&quot;, &quot;-ext eku:critical=ts&quot;);</span><a href="#l1005"></a>
<span id="l1006">        gencert(&quot;tsdisabled&quot;, &quot;-ext eku:critical=ts&quot;);</span><a href="#l1006"></a>
<span id="l1007">        gencert(&quot;tsbad1&quot;);</span><a href="#l1007"></a>
<span id="l1008">        gencert(&quot;tsbad2&quot;, &quot;-ext eku=ts&quot;);</span><a href="#l1008"></a>
<span id="l1009">        gencert(&quot;tsbad3&quot;, &quot;-ext eku:critical=cs&quot;);</span><a href="#l1009"></a>
<span id="l1010">    }</span><a href="#l1010"></a>
<span id="l1011"></span><a href="#l1011"></a>
<span id="l1012">    static void gencert(String alias, String... extra) throws Exception {</span><a href="#l1012"></a>
<span id="l1013">        keytool(&quot;-alias &quot; + alias + &quot; -certreq -file &quot; + alias + &quot;.req&quot;);</span><a href="#l1013"></a>
<span id="l1014">        String genCmd = &quot;-gencert -alias ca -infile &quot; +</span><a href="#l1014"></a>
<span id="l1015">                alias + &quot;.req -outfile &quot; + alias + &quot;.cert&quot;;</span><a href="#l1015"></a>
<span id="l1016">        for (String s : extra) {</span><a href="#l1016"></a>
<span id="l1017">            genCmd += &quot; &quot; + s;</span><a href="#l1017"></a>
<span id="l1018">        }</span><a href="#l1018"></a>
<span id="l1019">        keytool(genCmd);</span><a href="#l1019"></a>
<span id="l1020">        keytool(&quot;-alias &quot; + alias + &quot; -importcert -file &quot; + alias + &quot;.cert&quot;);</span><a href="#l1020"></a>
<span id="l1021">    }</span><a href="#l1021"></a>
<span id="l1022"></span><a href="#l1022"></a>
<span id="l1023">    static void keytool(String cmd) throws Exception {</span><a href="#l1023"></a>
<span id="l1024">        cmd = &quot;-keystore ks -storepass changeit -keypass changeit &quot; +</span><a href="#l1024"></a>
<span id="l1025">                &quot;-keyalg rsa -validity 200 &quot; + cmd;</span><a href="#l1025"></a>
<span id="l1026">        sun.security.tools.keytool.Main.main(cmd.split(&quot; &quot;));</span><a href="#l1026"></a>
<span id="l1027">    }</span><a href="#l1027"></a>
<span id="l1028"></span><a href="#l1028"></a>
<span id="l1029">    static &lt;K,V&gt; Map&lt;K,V&gt; mapOf(K k1, V v1) {</span><a href="#l1029"></a>
<span id="l1030">        return Collections.singletonMap(k1, v1);</span><a href="#l1030"></a>
<span id="l1031">    }</span><a href="#l1031"></a>
<span id="l1032"></span><a href="#l1032"></a>
<span id="l1033">    static &lt;E&gt; List&lt;E&gt; listOf(E... elements) {</span><a href="#l1033"></a>
<span id="l1034">        return Arrays.asList(elements);</span><a href="#l1034"></a>
<span id="l1035">    }</span><a href="#l1035"></a>
<span id="l1036">}</span><a href="#l1036"></a></pre>
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

