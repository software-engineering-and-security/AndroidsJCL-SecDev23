<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/monojdk8u/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/monojdk8u/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/monojdk8u/static/mercurial.js"></script>

<title>jdk8u/monojdk8u: 22fe1e492c16 jdk/test/java/util/Hashtable/DeserializedLength.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk8u/monojdk8u/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk8u/monojdk8u/shortlog/22fe1e492c16">log</a></li>
<li><a href="/jdk8u/monojdk8u/graph/22fe1e492c16">graph</a></li>
<li><a href="/jdk8u/monojdk8u/tags">tags</a></li>
<li><a href="/jdk8u/monojdk8u/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk8u/monojdk8u/rev/22fe1e492c16">changeset</a></li>
<li><a href="/jdk8u/monojdk8u/file/22fe1e492c16/jdk/test/java/util/Hashtable/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/monojdk8u/file/tip/jdk/test/java/util/Hashtable/DeserializedLength.java">latest</a></li>
<li><a href="/jdk8u/monojdk8u/diff/22fe1e492c16/jdk/test/java/util/Hashtable/DeserializedLength.java">diff</a></li>
<li><a href="/jdk8u/monojdk8u/comparison/22fe1e492c16/jdk/test/java/util/Hashtable/DeserializedLength.java">comparison</a></li>
<li><a href="/jdk8u/monojdk8u/annotate/22fe1e492c16/jdk/test/java/util/Hashtable/DeserializedLength.java">annotate</a></li>
<li><a href="/jdk8u/monojdk8u/log/22fe1e492c16/jdk/test/java/util/Hashtable/DeserializedLength.java">file log</a></li>
<li><a href="/jdk8u/monojdk8u/raw-file/22fe1e492c16/jdk/test/java/util/Hashtable/DeserializedLength.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/monojdk8u/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/monojdk8u">monojdk8u</a> </h2>
<h3>view jdk/test/java/util/Hashtable/DeserializedLength.java @ 48810:22fe1e492c16</h3>

<form class="search" action="/jdk8u/monojdk8u/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk8u/monojdk8u/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8270416: Enhance construction of Identity maps
Reviewed-by: andrew</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#97;&#118;&#111;&#105;&#116;&#121;&#108;&#111;&#118;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Wed, 05 Jan 2022 00:54:23 +0300</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk8u/monojdk8u/file/f2daa31d878b/jdk/test/java/util/Hashtable/DeserializedLength.java">f2daa31d878b</a> </td>
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
<span id="l2"> * Copyright (c) 2014, 2021, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l24">import java.io.*;</span><a href="#l24"></a>
<span id="l25">import java.lang.reflect.Field;</span><a href="#l25"></a>
<span id="l26">import java.util.Hashtable;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">/**</span><a href="#l28"></a>
<span id="l29"> * @test</span><a href="#l29"></a>
<span id="l30"> * @bug 8068427</span><a href="#l30"></a>
<span id="l31"> * @summary Hashtable deserialization reconstitutes table with wrong capacity</span><a href="#l31"></a>
<span id="l32"> */</span><a href="#l32"></a>
<span id="l33">public class DeserializedLength {</span><a href="#l33"></a>
<span id="l34"></span><a href="#l34"></a>
<span id="l35">    static boolean testDeserializedLength(int elements, float loadFactor) throws Exception {</span><a href="#l35"></a>
<span id="l36"></span><a href="#l36"></a>
<span id="l37">        // construct Hashtable with minimal initial capacity and given loadFactor</span><a href="#l37"></a>
<span id="l38">        Hashtable&lt;Integer, Integer&gt; ht1 = new Hashtable&lt;&gt;(1, loadFactor);</span><a href="#l38"></a>
<span id="l39"></span><a href="#l39"></a>
<span id="l40">        // add given number of unique elements</span><a href="#l40"></a>
<span id="l41">        for (int i = 0; i &lt; elements; i++) {</span><a href="#l41"></a>
<span id="l42">            ht1.put(i, i);</span><a href="#l42"></a>
<span id="l43">        }</span><a href="#l43"></a>
<span id="l44"></span><a href="#l44"></a>
<span id="l45">        // serialize and deserialize into a deep clone</span><a href="#l45"></a>
<span id="l46">        Hashtable&lt;Integer, Integer&gt; ht2 = serialClone(ht1);</span><a href="#l46"></a>
<span id="l47"></span><a href="#l47"></a>
<span id="l48">        // compare lengths of internal tables</span><a href="#l48"></a>
<span id="l49">        Object[] table1 = (Object[]) hashtableTableField.get(ht1);</span><a href="#l49"></a>
<span id="l50">        Object[] table2 = (Object[]) hashtableTableField.get(ht2);</span><a href="#l50"></a>
<span id="l51">        assert table1 != null;</span><a href="#l51"></a>
<span id="l52">        assert table2 != null;</span><a href="#l52"></a>
<span id="l53"></span><a href="#l53"></a>
<span id="l54">        int minLength = (int) (ht1.size() / loadFactor) + 1;</span><a href="#l54"></a>
<span id="l55">        int maxLength = minLength * 2;</span><a href="#l55"></a>
<span id="l56"></span><a href="#l56"></a>
<span id="l57">        boolean ok = (table2.length &gt;= minLength &amp;&amp; table2.length &lt;= maxLength);</span><a href="#l57"></a>
<span id="l58"></span><a href="#l58"></a>
<span id="l59">        System.out.printf(</span><a href="#l59"></a>
<span id="l60">            &quot;%7d %5.2f %7d %7d %7d...%7d %s\n&quot;,</span><a href="#l60"></a>
<span id="l61">            ht1.size(), loadFactor,</span><a href="#l61"></a>
<span id="l62">            table1.length, table2.length,</span><a href="#l62"></a>
<span id="l63">            minLength, maxLength,</span><a href="#l63"></a>
<span id="l64">            (ok ? &quot;OK&quot; : &quot;NOT-OK&quot;)</span><a href="#l64"></a>
<span id="l65">        );</span><a href="#l65"></a>
<span id="l66"></span><a href="#l66"></a>
<span id="l67">        return ok;</span><a href="#l67"></a>
<span id="l68">    }</span><a href="#l68"></a>
<span id="l69"></span><a href="#l69"></a>
<span id="l70">    static &lt;T&gt; T serialClone(T o) throws IOException, ClassNotFoundException {</span><a href="#l70"></a>
<span id="l71">        ByteArrayOutputStream bos = new ByteArrayOutputStream();</span><a href="#l71"></a>
<span id="l72">        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {</span><a href="#l72"></a>
<span id="l73">            oos.writeObject(o);</span><a href="#l73"></a>
<span id="l74">        }</span><a href="#l74"></a>
<span id="l75">        @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l75"></a>
<span id="l76">        T clone = (T) new ObjectInputStream(</span><a href="#l76"></a>
<span id="l77">            new ByteArrayInputStream(bos.toByteArray())).readObject();</span><a href="#l77"></a>
<span id="l78">        return clone;</span><a href="#l78"></a>
<span id="l79">    }</span><a href="#l79"></a>
<span id="l80"></span><a href="#l80"></a>
<span id="l81">    private static final Field hashtableTableField;</span><a href="#l81"></a>
<span id="l82"></span><a href="#l82"></a>
<span id="l83">    static {</span><a href="#l83"></a>
<span id="l84">        try {</span><a href="#l84"></a>
<span id="l85">            hashtableTableField = Hashtable.class.getDeclaredField(&quot;table&quot;);</span><a href="#l85"></a>
<span id="l86">            hashtableTableField.setAccessible(true);</span><a href="#l86"></a>
<span id="l87">        } catch (NoSuchFieldException e) {</span><a href="#l87"></a>
<span id="l88">            throw new Error(e);</span><a href="#l88"></a>
<span id="l89">        }</span><a href="#l89"></a>
<span id="l90">    }</span><a href="#l90"></a>
<span id="l91"></span><a href="#l91"></a>
<span id="l92">    public static void main(String[] args) throws Exception {</span><a href="#l92"></a>
<span id="l93">        boolean ok = true;</span><a href="#l93"></a>
<span id="l94"></span><a href="#l94"></a>
<span id="l95">        System.out.printf(&quot;Results:\n&quot; +</span><a href="#l95"></a>
<span id="l96">                &quot;                 ser.  deser.\n&quot; +</span><a href="#l96"></a>
<span id="l97">                &quot;   size  load  lentgh  length       valid range ok?\n&quot; +</span><a href="#l97"></a>
<span id="l98">                &quot;------- ----- ------- ------- ----------------- ------\n&quot;</span><a href="#l98"></a>
<span id="l99">        );</span><a href="#l99"></a>
<span id="l100"></span><a href="#l100"></a>
<span id="l101">        for (int elements : new int[]{10, 50, 500, 5000}) {</span><a href="#l101"></a>
<span id="l102">            for (float loadFactor : new float[]{0.25f, 0.5f, 0.75f, 1.0f, 2.5f}) {</span><a href="#l102"></a>
<span id="l103">                ok &amp;= testDeserializedLength(elements, loadFactor);</span><a href="#l103"></a>
<span id="l104">            }</span><a href="#l104"></a>
<span id="l105">        }</span><a href="#l105"></a>
<span id="l106">        if (!ok) {</span><a href="#l106"></a>
<span id="l107">            throw new AssertionError(&quot;Test failed.&quot;);</span><a href="#l107"></a>
<span id="l108">        }</span><a href="#l108"></a>
<span id="l109">    }</span><a href="#l109"></a>
<span id="l110">}</span><a href="#l110"></a></pre>
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

