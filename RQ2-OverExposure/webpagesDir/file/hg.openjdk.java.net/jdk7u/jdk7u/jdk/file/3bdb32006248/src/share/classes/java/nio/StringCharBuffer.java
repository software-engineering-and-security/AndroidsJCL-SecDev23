<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk7u/jdk7u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk7u/jdk7u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk7u/jdk7u/jdk/static/mercurial.js"></script>

<title>jdk7u/jdk7u/jdk: 3bdb32006248 src/share/classes/java/nio/StringCharBuffer.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk7u/jdk7u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/shortlog/3bdb32006248">log</a></li>
<li><a href="/jdk7u/jdk7u/jdk/graph/3bdb32006248">graph</a></li>
<li><a href="/jdk7u/jdk7u/jdk/tags">tags</a></li>
<li><a href="/jdk7u/jdk7u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/rev/3bdb32006248">changeset</a></li>
<li><a href="/jdk7u/jdk7u/jdk/file/3bdb32006248/src/share/classes/java/nio/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk7u/jdk7u/jdk/file/tip/src/share/classes/java/nio/StringCharBuffer.java">latest</a></li>
<li><a href="/jdk7u/jdk7u/jdk/diff/3bdb32006248/src/share/classes/java/nio/StringCharBuffer.java">diff</a></li>
<li><a href="/jdk7u/jdk7u/jdk/comparison/3bdb32006248/src/share/classes/java/nio/StringCharBuffer.java">comparison</a></li>
<li><a href="/jdk7u/jdk7u/jdk/annotate/3bdb32006248/src/share/classes/java/nio/StringCharBuffer.java">annotate</a></li>
<li><a href="/jdk7u/jdk7u/jdk/log/3bdb32006248/src/share/classes/java/nio/StringCharBuffer.java">file log</a></li>
<li><a href="/jdk7u/jdk7u/jdk/raw-file/3bdb32006248/src/share/classes/java/nio/StringCharBuffer.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u/jdk">jdk</a> </h2>
<h3>view src/share/classes/java/nio/StringCharBuffer.java @ 9004:3bdb32006248</h3>

<form class="search" action="/jdk7u/jdk7u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk7u/jdk7u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8234841: Enhance buffering of byte buffers
Reviewed-by: alanb, ahgross, rhalade, psandoz</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#114;&#111;&#98;&#109;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Mon, 30 Mar 2020 05:13:42 +0100</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk7u/jdk7u/jdk/file/a06412e13bf7/src/share/classes/java/nio/StringCharBuffer.java">a06412e13bf7</a> </td>
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
<span id="l2"> * Copyright (c) 2000, 2010, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l26">package java.nio;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28"></span><a href="#l28"></a>
<span id="l29">// ## If the sequence is a string, use reflection to share its array</span><a href="#l29"></a>
<span id="l30"></span><a href="#l30"></a>
<span id="l31">class StringCharBuffer                                  // package-private</span><a href="#l31"></a>
<span id="l32">    extends CharBuffer</span><a href="#l32"></a>
<span id="l33">{</span><a href="#l33"></a>
<span id="l34">    CharSequence str;</span><a href="#l34"></a>
<span id="l35"></span><a href="#l35"></a>
<span id="l36">    StringCharBuffer(CharSequence s, int start, int end) { // package-private</span><a href="#l36"></a>
<span id="l37">        super(-1, start, end, s.length());</span><a href="#l37"></a>
<span id="l38">        int n = s.length();</span><a href="#l38"></a>
<span id="l39">        if ((start &lt; 0) || (start &gt; n) || (end &lt; start) || (end &gt; n))</span><a href="#l39"></a>
<span id="l40">            throw new IndexOutOfBoundsException();</span><a href="#l40"></a>
<span id="l41">        str = s;</span><a href="#l41"></a>
<span id="l42">    }</span><a href="#l42"></a>
<span id="l43"></span><a href="#l43"></a>
<span id="l44">    public CharBuffer slice() {</span><a href="#l44"></a>
<span id="l45">        int pos = this.position();</span><a href="#l45"></a>
<span id="l46">        int lim = this.limit();</span><a href="#l46"></a>
<span id="l47">        int rem = (pos &lt;= lim ? lim - pos : 0);</span><a href="#l47"></a>
<span id="l48">        return new StringCharBuffer(str,</span><a href="#l48"></a>
<span id="l49">                                    -1,</span><a href="#l49"></a>
<span id="l50">                                    0,</span><a href="#l50"></a>
<span id="l51">                                    rem,</span><a href="#l51"></a>
<span id="l52">                                    rem,</span><a href="#l52"></a>
<span id="l53">                                    offset + pos);</span><a href="#l53"></a>
<span id="l54">    }</span><a href="#l54"></a>
<span id="l55"></span><a href="#l55"></a>
<span id="l56">    private StringCharBuffer(CharSequence s,</span><a href="#l56"></a>
<span id="l57">                             int mark,</span><a href="#l57"></a>
<span id="l58">                             int pos,</span><a href="#l58"></a>
<span id="l59">                             int limit,</span><a href="#l59"></a>
<span id="l60">                             int cap,</span><a href="#l60"></a>
<span id="l61">                             int offset) {</span><a href="#l61"></a>
<span id="l62">        super(mark, pos, limit, cap, null, offset);</span><a href="#l62"></a>
<span id="l63">        str = s;</span><a href="#l63"></a>
<span id="l64">    }</span><a href="#l64"></a>
<span id="l65"></span><a href="#l65"></a>
<span id="l66">    public CharBuffer duplicate() {</span><a href="#l66"></a>
<span id="l67">        return new StringCharBuffer(str, markValue(),</span><a href="#l67"></a>
<span id="l68">                                    position(), limit(), capacity(), offset);</span><a href="#l68"></a>
<span id="l69">    }</span><a href="#l69"></a>
<span id="l70"></span><a href="#l70"></a>
<span id="l71">    public CharBuffer asReadOnlyBuffer() {</span><a href="#l71"></a>
<span id="l72">        return duplicate();</span><a href="#l72"></a>
<span id="l73">    }</span><a href="#l73"></a>
<span id="l74"></span><a href="#l74"></a>
<span id="l75">    public final char get() {</span><a href="#l75"></a>
<span id="l76">        return str.charAt(nextGetIndex() + offset);</span><a href="#l76"></a>
<span id="l77">    }</span><a href="#l77"></a>
<span id="l78"></span><a href="#l78"></a>
<span id="l79">    public final char get(int index) {</span><a href="#l79"></a>
<span id="l80">        return str.charAt(checkIndex(index) + offset);</span><a href="#l80"></a>
<span id="l81">    }</span><a href="#l81"></a>
<span id="l82"></span><a href="#l82"></a>
<span id="l83">    // ## Override bulk get methods for better performance</span><a href="#l83"></a>
<span id="l84"></span><a href="#l84"></a>
<span id="l85">    public final CharBuffer put(char c) {</span><a href="#l85"></a>
<span id="l86">        throw new ReadOnlyBufferException();</span><a href="#l86"></a>
<span id="l87">    }</span><a href="#l87"></a>
<span id="l88"></span><a href="#l88"></a>
<span id="l89">    public final CharBuffer put(int index, char c) {</span><a href="#l89"></a>
<span id="l90">        throw new ReadOnlyBufferException();</span><a href="#l90"></a>
<span id="l91">    }</span><a href="#l91"></a>
<span id="l92"></span><a href="#l92"></a>
<span id="l93">    public final CharBuffer compact() {</span><a href="#l93"></a>
<span id="l94">        throw new ReadOnlyBufferException();</span><a href="#l94"></a>
<span id="l95">    }</span><a href="#l95"></a>
<span id="l96"></span><a href="#l96"></a>
<span id="l97">    public final boolean isReadOnly() {</span><a href="#l97"></a>
<span id="l98">        return true;</span><a href="#l98"></a>
<span id="l99">    }</span><a href="#l99"></a>
<span id="l100"></span><a href="#l100"></a>
<span id="l101">    final String toString(int start, int end) {</span><a href="#l101"></a>
<span id="l102">        return str.toString().substring(start + offset, end + offset);</span><a href="#l102"></a>
<span id="l103">    }</span><a href="#l103"></a>
<span id="l104"></span><a href="#l104"></a>
<span id="l105">    public final CharBuffer subSequence(int start, int end) {</span><a href="#l105"></a>
<span id="l106">        try {</span><a href="#l106"></a>
<span id="l107">            int pos = position();</span><a href="#l107"></a>
<span id="l108">            return new StringCharBuffer(str,</span><a href="#l108"></a>
<span id="l109">                                        -1,</span><a href="#l109"></a>
<span id="l110">                                        pos + checkIndex(start, pos),</span><a href="#l110"></a>
<span id="l111">                                        pos + checkIndex(end, pos),</span><a href="#l111"></a>
<span id="l112">                                        capacity(),</span><a href="#l112"></a>
<span id="l113">                                        offset);</span><a href="#l113"></a>
<span id="l114">        } catch (IllegalArgumentException x) {</span><a href="#l114"></a>
<span id="l115">            throw new IndexOutOfBoundsException();</span><a href="#l115"></a>
<span id="l116">        }</span><a href="#l116"></a>
<span id="l117">    }</span><a href="#l117"></a>
<span id="l118"></span><a href="#l118"></a>
<span id="l119">    public boolean isDirect() {</span><a href="#l119"></a>
<span id="l120">        return false;</span><a href="#l120"></a>
<span id="l121">    }</span><a href="#l121"></a>
<span id="l122"></span><a href="#l122"></a>
<span id="l123">    public ByteOrder order() {</span><a href="#l123"></a>
<span id="l124">        return ByteOrder.nativeOrder();</span><a href="#l124"></a>
<span id="l125">    }</span><a href="#l125"></a>
<span id="l126"></span><a href="#l126"></a>
<span id="l127">}</span><a href="#l127"></a></pre>
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

