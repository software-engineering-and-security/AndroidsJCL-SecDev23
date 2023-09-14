<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 412d2b1381a4 test/sun/security/pkcs/pkcs8/PKCS8Test.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/412d2b1381a4/test/sun/security/pkcs/pkcs8/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/test/sun/security/pkcs/pkcs8/PKCS8Test.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/412d2b1381a4/test/sun/security/pkcs/pkcs8/PKCS8Test.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/412d2b1381a4/test/sun/security/pkcs/pkcs8/PKCS8Test.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/412d2b1381a4/test/sun/security/pkcs/pkcs8/PKCS8Test.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/412d2b1381a4/test/sun/security/pkcs/pkcs8/PKCS8Test.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/412d2b1381a4/test/sun/security/pkcs/pkcs8/PKCS8Test.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view test/sun/security/pkcs/pkcs8/PKCS8Test.java @ 14391:412d2b1381a4</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/cd671a817324/test/sun/security/pkcs/pkcs8/PKCS8Test.java">cd671a817324</a> </td>
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
<span id="l2"> * Copyright (c) 2015, 2021, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l24">/*</span><a href="#l24"></a>
<span id="l25"> * @test</span><a href="#l25"></a>
<span id="l26"> * @bug 8048357</span><a href="#l26"></a>
<span id="l27"> * @summary PKCS8 Standards Conformance Tests</span><a href="#l27"></a>
<span id="l28"> * @requires (os.family != &quot;solaris&quot;)</span><a href="#l28"></a>
<span id="l29"> * @compile -XDignore.symbol.file PKCS8Test.java</span><a href="#l29"></a>
<span id="l30"> * @run main PKCS8Test</span><a href="#l30"></a>
<span id="l31"> */</span><a href="#l31"></a>
<span id="l32"></span><a href="#l32"></a>
<span id="l33">/*</span><a href="#l33"></a>
<span id="l34"> * Skip Solaris since the DSAPrivateKeys returned by</span><a href="#l34"></a>
<span id="l35"> * SunPKCS11 Provider are not subclasses of PKCS8Key</span><a href="#l35"></a>
<span id="l36"> */</span><a href="#l36"></a>
<span id="l37">import java.io.IOException;</span><a href="#l37"></a>
<span id="l38">import java.math.BigInteger;</span><a href="#l38"></a>
<span id="l39">import java.security.InvalidKeyException;</span><a href="#l39"></a>
<span id="l40">import java.util.Arrays;</span><a href="#l40"></a>
<span id="l41">import sun.misc.HexDumpEncoder;</span><a href="#l41"></a>
<span id="l42">import sun.security.pkcs.PKCS8Key;</span><a href="#l42"></a>
<span id="l43">import sun.security.provider.DSAPrivateKey;</span><a href="#l43"></a>
<span id="l44">import sun.security.util.DerOutputStream;</span><a href="#l44"></a>
<span id="l45">import sun.security.util.DerValue;</span><a href="#l45"></a>
<span id="l46">import sun.security.x509.AlgorithmId;</span><a href="#l46"></a>
<span id="l47">import static java.lang.System.out;</span><a href="#l47"></a>
<span id="l48"></span><a href="#l48"></a>
<span id="l49">public class PKCS8Test {</span><a href="#l49"></a>
<span id="l50"></span><a href="#l50"></a>
<span id="l51">    static final HexDumpEncoder hexDump = new HexDumpEncoder();</span><a href="#l51"></a>
<span id="l52"></span><a href="#l52"></a>
<span id="l53">    static final DerOutputStream derOutput = new DerOutputStream();</span><a href="#l53"></a>
<span id="l54"></span><a href="#l54"></a>
<span id="l55">    static final String FORMAT = &quot;PKCS#8&quot;;</span><a href="#l55"></a>
<span id="l56">    static final String EXPECTED_ALG_ID_CHRS = &quot;DSA, \n\tp:     02\n\tq:     03\n&quot;</span><a href="#l56"></a>
<span id="l57">            + &quot;\tg:     04\n&quot;;</span><a href="#l57"></a>
<span id="l58">    static final String ALGORITHM = &quot;DSA&quot;;</span><a href="#l58"></a>
<span id="l59">    static final String EXCEPTION_MESSAGE = &quot;version mismatch: (supported:     &quot;</span><a href="#l59"></a>
<span id="l60">            + &quot;00, parsed:     01&quot;;</span><a href="#l60"></a>
<span id="l61"></span><a href="#l61"></a>
<span id="l62">    // test second branch in byte[] encode()</span><a href="#l62"></a>
<span id="l63">    // DER encoding,include (empty) set of attributes</span><a href="#l63"></a>
<span id="l64">    static final int[] NEW_ENCODED_KEY_INTS = { 0x30,</span><a href="#l64"></a>
<span id="l65">            // length 30 = 0x1e</span><a href="#l65"></a>
<span id="l66">            0x1e,</span><a href="#l66"></a>
<span id="l67">            // first element</span><a href="#l67"></a>
<span id="l68">            // version Version (= INTEGER)</span><a href="#l68"></a>
<span id="l69">            0x02,</span><a href="#l69"></a>
<span id="l70">            // length 1</span><a href="#l70"></a>
<span id="l71">            0x01,</span><a href="#l71"></a>
<span id="l72">            // value 0</span><a href="#l72"></a>
<span id="l73">            0x00,</span><a href="#l73"></a>
<span id="l74">            // second element</span><a href="#l74"></a>
<span id="l75">            // privateKeyAlgorithmIdentifier PrivateKeyAlgorithmIdentifier</span><a href="#l75"></a>
<span id="l76">            // (sequence)</span><a href="#l76"></a>
<span id="l77">            // (an object identifier?)</span><a href="#l77"></a>
<span id="l78">            0x30,</span><a href="#l78"></a>
<span id="l79">            // length 18</span><a href="#l79"></a>
<span id="l80">            0x12,</span><a href="#l80"></a>
<span id="l81">            // contents</span><a href="#l81"></a>
<span id="l82">            // object identifier, 5 bytes</span><a href="#l82"></a>
<span id="l83">            0x06, 0x05,</span><a href="#l83"></a>
<span id="l84">            // { 1 3 14 3 2 12 }</span><a href="#l84"></a>
<span id="l85">            0x2b, 0x0e, 0x03, 0x02, 0x0c,</span><a href="#l85"></a>
<span id="l86">            // sequence, 9 bytes</span><a href="#l86"></a>
<span id="l87">            0x30, 0x09,</span><a href="#l87"></a>
<span id="l88">            // integer 2</span><a href="#l88"></a>
<span id="l89">            0x02, 0x01, 0x02,</span><a href="#l89"></a>
<span id="l90">            // integer 3</span><a href="#l90"></a>
<span id="l91">            0x02, 0x01, 0x03,</span><a href="#l91"></a>
<span id="l92">            // integer 4</span><a href="#l92"></a>
<span id="l93">            0x02, 0x01, 0x04,</span><a href="#l93"></a>
<span id="l94">            // third element</span><a href="#l94"></a>
<span id="l95">            // privateKey PrivateKey (= OCTET STRING)</span><a href="#l95"></a>
<span id="l96">            0x04,</span><a href="#l96"></a>
<span id="l97">            // length</span><a href="#l97"></a>
<span id="l98">            0x03,</span><a href="#l98"></a>
<span id="l99">            // privateKey contents</span><a href="#l99"></a>
<span id="l100">            0x02, 0x01, 0x01,</span><a href="#l100"></a>
<span id="l101">            // 4th (optional) element -- attributes [0] IMPLICIT Attributes</span><a href="#l101"></a>
<span id="l102">            // OPTIONAL</span><a href="#l102"></a>
<span id="l103">            // (Attributes = SET OF Attribute) Here, it will be empty.</span><a href="#l103"></a>
<span id="l104">            0xA0,</span><a href="#l104"></a>
<span id="l105">            // length</span><a href="#l105"></a>
<span id="l106">            0x00 };</span><a href="#l106"></a>
<span id="l107"></span><a href="#l107"></a>
<span id="l108">    // encoding originally created, but with the version changed</span><a href="#l108"></a>
<span id="l109">    static final int[] NEW_ENCODED_KEY_INTS_2 = {</span><a href="#l109"></a>
<span id="l110">            // sequence</span><a href="#l110"></a>
<span id="l111">            0x30,</span><a href="#l111"></a>
<span id="l112">            // length 28 = 0x1c</span><a href="#l112"></a>
<span id="l113">            0x1c,</span><a href="#l113"></a>
<span id="l114">            // first element</span><a href="#l114"></a>
<span id="l115">            // version Version (= INTEGER)</span><a href="#l115"></a>
<span id="l116">            0x02,</span><a href="#l116"></a>
<span id="l117">            // length 1</span><a href="#l117"></a>
<span id="l118">            0x01,</span><a href="#l118"></a>
<span id="l119">            // value 1 (illegal)</span><a href="#l119"></a>
<span id="l120">            0x01,</span><a href="#l120"></a>
<span id="l121">            // second element</span><a href="#l121"></a>
<span id="l122">            // privateKeyAlgorithmIdentifier PrivateKeyAlgorithmIdentifier</span><a href="#l122"></a>
<span id="l123">            // (sequence)</span><a href="#l123"></a>
<span id="l124">            // (an object identifier?)</span><a href="#l124"></a>
<span id="l125">            0x30,</span><a href="#l125"></a>
<span id="l126">            // length 18</span><a href="#l126"></a>
<span id="l127">            0x12,</span><a href="#l127"></a>
<span id="l128">            // contents</span><a href="#l128"></a>
<span id="l129">            // object identifier, 5 bytes</span><a href="#l129"></a>
<span id="l130">            0x06, 0x05,</span><a href="#l130"></a>
<span id="l131">            // { 1 3 14 3 2 12 }</span><a href="#l131"></a>
<span id="l132">            0x2b, 0x0e, 0x03, 0x02, 0x0c,</span><a href="#l132"></a>
<span id="l133">            // sequence, 9 bytes</span><a href="#l133"></a>
<span id="l134">            0x30, 0x09,</span><a href="#l134"></a>
<span id="l135">            // integer 2</span><a href="#l135"></a>
<span id="l136">            0x02, 0x01, 0x02,</span><a href="#l136"></a>
<span id="l137">            // integer 3</span><a href="#l137"></a>
<span id="l138">            0x02, 0x01, 0x03,</span><a href="#l138"></a>
<span id="l139">            // integer 4</span><a href="#l139"></a>
<span id="l140">            0x02, 0x01, 0x04,</span><a href="#l140"></a>
<span id="l141">            // third element</span><a href="#l141"></a>
<span id="l142">            // privateKey PrivateKey (= OCTET STRING)</span><a href="#l142"></a>
<span id="l143">            0x04,</span><a href="#l143"></a>
<span id="l144">            // length</span><a href="#l144"></a>
<span id="l145">            0x03,</span><a href="#l145"></a>
<span id="l146">            // privateKey contents</span><a href="#l146"></a>
<span id="l147">            0x02, 0x01, 0x01 };</span><a href="#l147"></a>
<span id="l148"></span><a href="#l148"></a>
<span id="l149">    // 0000: 30 1E 02 01 00 30 14 06 07 2A 86 48 CE 38 04 01 0....0...*.H.8..</span><a href="#l149"></a>
<span id="l150">    // 0010: 30 09 02 01 02 02 01 03 02 01 04 04 03 02 01 01 0...............</span><a href="#l150"></a>
<span id="l151">    static final int[] EXPECTED = { 0x30,</span><a href="#l151"></a>
<span id="l152">            // length 30 = 0x1e</span><a href="#l152"></a>
<span id="l153">            0x1e,</span><a href="#l153"></a>
<span id="l154">            // first element</span><a href="#l154"></a>
<span id="l155">            // version Version (= INTEGER)</span><a href="#l155"></a>
<span id="l156">            0x02,</span><a href="#l156"></a>
<span id="l157">            // length 1</span><a href="#l157"></a>
<span id="l158">            0x01,</span><a href="#l158"></a>
<span id="l159">            // value 0</span><a href="#l159"></a>
<span id="l160">            0x00,</span><a href="#l160"></a>
<span id="l161">            // second element</span><a href="#l161"></a>
<span id="l162">            // privateKeyAlgorithmIdentifier PrivateKeyAlgorithmIdentifier</span><a href="#l162"></a>
<span id="l163">            // (sequence)</span><a href="#l163"></a>
<span id="l164">            // (an object identifier?)</span><a href="#l164"></a>
<span id="l165">            0x30, 0x14, 0x06, 0x07, 0x2a, 0x86, 0x48, 0xce, 0x38, 0x04, 0x01,</span><a href="#l165"></a>
<span id="l166">            // integer 2</span><a href="#l166"></a>
<span id="l167">            0x30, 0x09, 0x02,</span><a href="#l167"></a>
<span id="l168">            // integer 3</span><a href="#l168"></a>
<span id="l169">            0x01, 0x02, 0x02,</span><a href="#l169"></a>
<span id="l170">            // integer 4</span><a href="#l170"></a>
<span id="l171">            0x01, 0x03, 0x02,</span><a href="#l171"></a>
<span id="l172">            // third element</span><a href="#l172"></a>
<span id="l173">            // privateKey PrivateKey (= OCTET STRING)</span><a href="#l173"></a>
<span id="l174">            0x01,</span><a href="#l174"></a>
<span id="l175">            // length</span><a href="#l175"></a>
<span id="l176">            0x04,</span><a href="#l176"></a>
<span id="l177">            // privateKey contents</span><a href="#l177"></a>
<span id="l178">            0x04, 0x03, 0x02,</span><a href="#l178"></a>
<span id="l179">            // 4th (optional) element -- attributes [0] IMPLICIT Attributes</span><a href="#l179"></a>
<span id="l180">            // OPTIONAL</span><a href="#l180"></a>
<span id="l181">            // (Attributes = SET OF Attribute) Here, it will be empty.</span><a href="#l181"></a>
<span id="l182">            0x01,</span><a href="#l182"></a>
<span id="l183">            // length</span><a href="#l183"></a>
<span id="l184">            0x01 };</span><a href="#l184"></a>
<span id="l185"></span><a href="#l185"></a>
<span id="l186">    static void raiseException(String expected, String received) {</span><a href="#l186"></a>
<span id="l187">        throw new RuntimeException(</span><a href="#l187"></a>
<span id="l188">                &quot;Expected &quot; + expected + &quot;; Received &quot; + received);</span><a href="#l188"></a>
<span id="l189">    }</span><a href="#l189"></a>
<span id="l190"></span><a href="#l190"></a>
<span id="l191">    public static void main(String[] args)</span><a href="#l191"></a>
<span id="l192">            throws IOException, InvalidKeyException {</span><a href="#l192"></a>
<span id="l193"></span><a href="#l193"></a>
<span id="l194">        BigInteger x = BigInteger.valueOf(1);</span><a href="#l194"></a>
<span id="l195">        BigInteger p = BigInteger.valueOf(2);</span><a href="#l195"></a>
<span id="l196">        BigInteger q = BigInteger.valueOf(3);</span><a href="#l196"></a>
<span id="l197">        BigInteger g = BigInteger.valueOf(4);</span><a href="#l197"></a>
<span id="l198"></span><a href="#l198"></a>
<span id="l199">        DSAPrivateKey priv = new DSAPrivateKey(x, p, q, g);</span><a href="#l199"></a>
<span id="l200"></span><a href="#l200"></a>
<span id="l201">        byte[] encodedKey = priv.getEncoded();</span><a href="#l201"></a>
<span id="l202">        byte[] expectedBytes = new byte[EXPECTED.length];</span><a href="#l202"></a>
<span id="l203">        for (int i = 0; i &lt; EXPECTED.length; i++) {</span><a href="#l203"></a>
<span id="l204">            expectedBytes[i] = (byte) EXPECTED[i];</span><a href="#l204"></a>
<span id="l205">        }</span><a href="#l205"></a>
<span id="l206"></span><a href="#l206"></a>
<span id="l207">        dumpByteArray(&quot;encodedKey :&quot;, encodedKey);</span><a href="#l207"></a>
<span id="l208">        if (!Arrays.equals(encodedKey, expectedBytes)) {</span><a href="#l208"></a>
<span id="l209">            raiseException(new String(expectedBytes), new String(encodedKey));</span><a href="#l209"></a>
<span id="l210">        }</span><a href="#l210"></a>
<span id="l211"></span><a href="#l211"></a>
<span id="l212">        PKCS8Key decodedKey = PKCS8Key.parse(new DerValue(encodedKey));</span><a href="#l212"></a>
<span id="l213"></span><a href="#l213"></a>
<span id="l214">        String alg = decodedKey.getAlgorithm();</span><a href="#l214"></a>
<span id="l215">        AlgorithmId algId = decodedKey.getAlgorithmId();</span><a href="#l215"></a>
<span id="l216">        out.println(&quot;Algorithm :&quot; + alg);</span><a href="#l216"></a>
<span id="l217">        out.println(&quot;AlgorithmId: &quot; + algId);</span><a href="#l217"></a>
<span id="l218"></span><a href="#l218"></a>
<span id="l219">        if (!ALGORITHM.equals(alg)) {</span><a href="#l219"></a>
<span id="l220">            raiseException(ALGORITHM, alg);</span><a href="#l220"></a>
<span id="l221">        }</span><a href="#l221"></a>
<span id="l222">        if (!EXPECTED_ALG_ID_CHRS.equalsIgnoreCase(algId.toString())) {</span><a href="#l222"></a>
<span id="l223">            raiseException(EXPECTED_ALG_ID_CHRS, algId.toString());</span><a href="#l223"></a>
<span id="l224">        }</span><a href="#l224"></a>
<span id="l225"></span><a href="#l225"></a>
<span id="l226">        decodedKey.encode(derOutput);</span><a href="#l226"></a>
<span id="l227">        dumpByteArray(&quot;Stream encode: &quot;, derOutput.toByteArray());</span><a href="#l227"></a>
<span id="l228">        if (!Arrays.equals(derOutput.toByteArray(), expectedBytes)) {</span><a href="#l228"></a>
<span id="l229">            raiseException(new String(expectedBytes), derOutput.toString());</span><a href="#l229"></a>
<span id="l230">        }</span><a href="#l230"></a>
<span id="l231"></span><a href="#l231"></a>
<span id="l232">        dumpByteArray(&quot;byte[] encoding: &quot;, decodedKey.getEncoded());</span><a href="#l232"></a>
<span id="l233">        if (!Arrays.equals(decodedKey.getEncoded(), expectedBytes)) {</span><a href="#l233"></a>
<span id="l234">            raiseException(new String(expectedBytes),</span><a href="#l234"></a>
<span id="l235">                    new String(decodedKey.getEncoded()));</span><a href="#l235"></a>
<span id="l236">        }</span><a href="#l236"></a>
<span id="l237"></span><a href="#l237"></a>
<span id="l238">        if (!FORMAT.equals(decodedKey.getFormat())) {</span><a href="#l238"></a>
<span id="l239">            raiseException(FORMAT, decodedKey.getFormat());</span><a href="#l239"></a>
<span id="l240">        }</span><a href="#l240"></a>
<span id="l241"></span><a href="#l241"></a>
<span id="l242">        try {</span><a href="#l242"></a>
<span id="l243">            byte[] newEncodedKey = new byte[NEW_ENCODED_KEY_INTS.length];</span><a href="#l243"></a>
<span id="l244">            for (int i = 0; i &lt; newEncodedKey.length; i++) {</span><a href="#l244"></a>
<span id="l245">                newEncodedKey[i] = (byte) NEW_ENCODED_KEY_INTS[i];</span><a href="#l245"></a>
<span id="l246">            }</span><a href="#l246"></a>
<span id="l247">            PKCS8Key newDecodedKey = PKCS8Key</span><a href="#l247"></a>
<span id="l248">                    .parse(new DerValue(newEncodedKey));</span><a href="#l248"></a>
<span id="l249"></span><a href="#l249"></a>
<span id="l250">            throw new RuntimeException(</span><a href="#l250"></a>
<span id="l251">                    &quot;key1: Expected an IOException during &quot; + &quot;parsing&quot;);</span><a href="#l251"></a>
<span id="l252">        } catch (IOException e) {</span><a href="#l252"></a>
<span id="l253">            System.out.println(&quot;newEncodedKey: should have excess data due to &quot;</span><a href="#l253"></a>
<span id="l254">                    + &quot;attributes, which are not supported&quot;);</span><a href="#l254"></a>
<span id="l255">        }</span><a href="#l255"></a>
<span id="l256"></span><a href="#l256"></a>
<span id="l257">        try {</span><a href="#l257"></a>
<span id="l258">            byte[] newEncodedKey2 = new byte[NEW_ENCODED_KEY_INTS_2.length];</span><a href="#l258"></a>
<span id="l259">            for (int i = 0; i &lt; newEncodedKey2.length; i++) {</span><a href="#l259"></a>
<span id="l260">                newEncodedKey2[i] = (byte) NEW_ENCODED_KEY_INTS_2[i];</span><a href="#l260"></a>
<span id="l261">            }</span><a href="#l261"></a>
<span id="l262"></span><a href="#l262"></a>
<span id="l263">            PKCS8Key newDecodedKey2 = PKCS8Key</span><a href="#l263"></a>
<span id="l264">                    .parse(new DerValue(newEncodedKey2));</span><a href="#l264"></a>
<span id="l265"></span><a href="#l265"></a>
<span id="l266">            throw new RuntimeException(</span><a href="#l266"></a>
<span id="l267">                    &quot;key2: Expected an IOException during &quot; + &quot;parsing&quot;);</span><a href="#l267"></a>
<span id="l268">        } catch (IOException e) {</span><a href="#l268"></a>
<span id="l269">            out.println(&quot;Key 2: should be illegal version&quot;);</span><a href="#l269"></a>
<span id="l270">            out.println(e.getMessage());</span><a href="#l270"></a>
<span id="l271">            if (!EXCEPTION_MESSAGE.equals(e.getMessage())) {</span><a href="#l271"></a>
<span id="l272">                throw new RuntimeException(&quot;Key2: expected: &quot;</span><a href="#l272"></a>
<span id="l273">                        + EXCEPTION_MESSAGE + &quot; get: &quot; + e.getMessage());</span><a href="#l273"></a>
<span id="l274">            }</span><a href="#l274"></a>
<span id="l275">        }</span><a href="#l275"></a>
<span id="l276">    }</span><a href="#l276"></a>
<span id="l277"></span><a href="#l277"></a>
<span id="l278">    static void dumpByteArray(String nm, byte[] bytes) throws IOException {</span><a href="#l278"></a>
<span id="l279">        out.println(nm + &quot; length: &quot; + bytes.length);</span><a href="#l279"></a>
<span id="l280">        hexDump.encodeBuffer(bytes, out);</span><a href="#l280"></a>
<span id="l281">    }</span><a href="#l281"></a>
<span id="l282">}</span><a href="#l282"></a></pre>
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

