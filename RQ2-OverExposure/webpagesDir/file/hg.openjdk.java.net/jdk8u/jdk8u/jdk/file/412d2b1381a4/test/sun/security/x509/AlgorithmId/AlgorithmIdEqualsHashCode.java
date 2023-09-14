<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 412d2b1381a4 test/sun/security/x509/AlgorithmId/AlgorithmIdEqualsHashCode.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/412d2b1381a4/test/sun/security/x509/AlgorithmId/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/test/sun/security/x509/AlgorithmId/AlgorithmIdEqualsHashCode.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/412d2b1381a4/test/sun/security/x509/AlgorithmId/AlgorithmIdEqualsHashCode.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/412d2b1381a4/test/sun/security/x509/AlgorithmId/AlgorithmIdEqualsHashCode.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/412d2b1381a4/test/sun/security/x509/AlgorithmId/AlgorithmIdEqualsHashCode.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/412d2b1381a4/test/sun/security/x509/AlgorithmId/AlgorithmIdEqualsHashCode.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/412d2b1381a4/test/sun/security/x509/AlgorithmId/AlgorithmIdEqualsHashCode.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view test/sun/security/x509/AlgorithmId/AlgorithmIdEqualsHashCode.java @ 14391:412d2b1381a4</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/00cd9dc3c2b5/test/sun/security/x509/AlgorithmId/AlgorithmIdEqualsHashCode.java">00cd9dc3c2b5</a> </td>
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
<span id="l2"> * Copyright (c) 1999, 2021, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l26"> * @author Gary Ellison</span><a href="#l26"></a>
<span id="l27"> * @bug 4170635 8258247</span><a href="#l27"></a>
<span id="l28"> * @summary Verify equals()/hashCode() contract honored</span><a href="#l28"></a>
<span id="l29"> */</span><a href="#l29"></a>
<span id="l30"></span><a href="#l30"></a>
<span id="l31">import java.io.*;</span><a href="#l31"></a>
<span id="l32">import java.security.AlgorithmParameters;</span><a href="#l32"></a>
<span id="l33">import java.security.spec.MGF1ParameterSpec;</span><a href="#l33"></a>
<span id="l34">import java.security.spec.PSSParameterSpec;</span><a href="#l34"></a>
<span id="l35"></span><a href="#l35"></a>
<span id="l36">import sun.security.util.DerValue;</span><a href="#l36"></a>
<span id="l37">import sun.security.x509.*;</span><a href="#l37"></a>
<span id="l38"></span><a href="#l38"></a>
<span id="l39">public class AlgorithmIdEqualsHashCode {</span><a href="#l39"></a>
<span id="l40"></span><a href="#l40"></a>
<span id="l41">    public static void main(String[] args) throws Exception {</span><a href="#l41"></a>
<span id="l42"></span><a href="#l42"></a>
<span id="l43">        AlgorithmId ai1 = AlgorithmId.get(&quot;DH&quot;);</span><a href="#l43"></a>
<span id="l44">        AlgorithmId ai2 = AlgorithmId.get(&quot;DH&quot;);</span><a href="#l44"></a>
<span id="l45">        AlgorithmId ai3 = AlgorithmId.get(&quot;DH&quot;);</span><a href="#l45"></a>
<span id="l46"></span><a href="#l46"></a>
<span id="l47">        // supposedly transitivity is broken</span><a href="#l47"></a>
<span id="l48">        // System.out.println(ai1.equals(ai2));</span><a href="#l48"></a>
<span id="l49">        // System.out.println(ai2.equals(ai3));</span><a href="#l49"></a>
<span id="l50">        // System.out.println(ai1.equals(ai3));</span><a href="#l50"></a>
<span id="l51"></span><a href="#l51"></a>
<span id="l52">        if ( (ai1.equals(ai2)) == (ai2.equals(ai3)) == (ai1.equals(ai3)))</span><a href="#l52"></a>
<span id="l53">            System.out.println(&quot;PASSED transitivity test&quot;);</span><a href="#l53"></a>
<span id="l54">        else</span><a href="#l54"></a>
<span id="l55">            throw new Exception(&quot;Failed equals transitivity() contract&quot;);</span><a href="#l55"></a>
<span id="l56"></span><a href="#l56"></a>
<span id="l57">        if ( (ai1.equals(ai2)) == (ai1.hashCode()==ai2.hashCode()) )</span><a href="#l57"></a>
<span id="l58">            System.out.println(&quot;PASSED equals()/hashCode() test&quot;);</span><a href="#l58"></a>
<span id="l59">        else</span><a href="#l59"></a>
<span id="l60">            throw new Exception(&quot;Failed equals()/hashCode() contract&quot;);</span><a href="#l60"></a>
<span id="l61"></span><a href="#l61"></a>
<span id="l62">        // check that AlgorithmIds with same name but different params</span><a href="#l62"></a>
<span id="l63">        // are not equal</span><a href="#l63"></a>
<span id="l64">        AlgorithmParameters algParams1 =</span><a href="#l64"></a>
<span id="l65">            AlgorithmParameters.getInstance(&quot;RSASSA-PSS&quot;);</span><a href="#l65"></a>
<span id="l66">        AlgorithmParameters algParams2 =</span><a href="#l66"></a>
<span id="l67">            AlgorithmParameters.getInstance(&quot;RSASSA-PSS&quot;);</span><a href="#l67"></a>
<span id="l68">        algParams1.init(new PSSParameterSpec(&quot;SHA-1&quot;, &quot;MGF1&quot;,</span><a href="#l68"></a>
<span id="l69">            MGF1ParameterSpec.SHA1, 20, PSSParameterSpec.TRAILER_FIELD_BC));</span><a href="#l69"></a>
<span id="l70">        algParams2.init(new PSSParameterSpec(&quot;SHA-256&quot;, &quot;MGF1&quot;,</span><a href="#l70"></a>
<span id="l71">            MGF1ParameterSpec.SHA1, 20, PSSParameterSpec.TRAILER_FIELD_BC));</span><a href="#l71"></a>
<span id="l72">        ai1 = new AlgorithmId(AlgorithmId.RSASSA_PSS_oid, algParams1);</span><a href="#l72"></a>
<span id="l73">        ai2 = new AlgorithmId(AlgorithmId.RSASSA_PSS_oid, algParams2);</span><a href="#l73"></a>
<span id="l74">        if (ai1.equals(ai2)) {</span><a href="#l74"></a>
<span id="l75">            throw new Exception(&quot;Failed equals() contract&quot;);</span><a href="#l75"></a>
<span id="l76">        } else {</span><a href="#l76"></a>
<span id="l77">            System.out.println(&quot;PASSED equals() test&quot;);</span><a href="#l77"></a>
<span id="l78">        }</span><a href="#l78"></a>
<span id="l79"></span><a href="#l79"></a>
<span id="l80">        // check that two AlgorithmIds created with the same parameters but</span><a href="#l80"></a>
<span id="l81">        // one with DER encoded parameters and the other with</span><a href="#l81"></a>
<span id="l82">        // AlgorithmParameters are equal</span><a href="#l82"></a>
<span id="l83">        byte[] encoded = ai1.encode();</span><a href="#l83"></a>
<span id="l84">        ai3 = AlgorithmId.parse(new DerValue(encoded));</span><a href="#l84"></a>
<span id="l85">        if (!ai1.equals(ai3)) {</span><a href="#l85"></a>
<span id="l86">            throw new Exception(&quot;Failed equals() contract&quot;);</span><a href="#l86"></a>
<span id="l87">        } else {</span><a href="#l87"></a>
<span id="l88">            System.out.println(&quot;PASSED equals() test&quot;);</span><a href="#l88"></a>
<span id="l89">        }</span><a href="#l89"></a>
<span id="l90"></span><a href="#l90"></a>
<span id="l91">        // check that two AlgorithmIds created with different parameters but</span><a href="#l91"></a>
<span id="l92">        // one with DER encoded parameters and the other with</span><a href="#l92"></a>
<span id="l93">        // AlgorithmParameters are not equal</span><a href="#l93"></a>
<span id="l94">        if (ai2.equals(ai3)) {</span><a href="#l94"></a>
<span id="l95">            throw new Exception(&quot;Failed equals() contract&quot;);</span><a href="#l95"></a>
<span id="l96">        } else {</span><a href="#l96"></a>
<span id="l97">            System.out.println(&quot;PASSED equals() test&quot;);</span><a href="#l97"></a>
<span id="l98">        }</span><a href="#l98"></a>
<span id="l99">    }</span><a href="#l99"></a>
<span id="l100">}</span><a href="#l100"></a></pre>
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

