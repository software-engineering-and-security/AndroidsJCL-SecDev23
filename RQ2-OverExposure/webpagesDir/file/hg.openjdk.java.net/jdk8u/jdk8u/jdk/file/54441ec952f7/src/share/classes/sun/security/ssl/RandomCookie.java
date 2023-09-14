<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 54441ec952f7 src/share/classes/sun/security/ssl/RandomCookie.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/ssl/RandomCookie.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/54441ec952f7/src/share/classes/sun/security/ssl/RandomCookie.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/54441ec952f7/src/share/classes/sun/security/ssl/RandomCookie.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/54441ec952f7/src/share/classes/sun/security/ssl/RandomCookie.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/54441ec952f7/src/share/classes/sun/security/ssl/RandomCookie.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/54441ec952f7/src/share/classes/sun/security/ssl/RandomCookie.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/ssl/RandomCookie.java @ 14574:54441ec952f7</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/4546aa3faf37/src/share/classes/sun/security/ssl/RandomCookie.java">4546aa3faf37</a> </td>
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
<span id="l2"> * Copyright (c) 1996, 2018, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l28">import sun.security.util.ByteArrays;</span><a href="#l28"></a>
<span id="l29"></span><a href="#l29"></a>
<span id="l30">import java.io.*;</span><a href="#l30"></a>
<span id="l31">import java.nio.ByteBuffer;</span><a href="#l31"></a>
<span id="l32">import java.security.MessageDigest;</span><a href="#l32"></a>
<span id="l33">import java.security.SecureRandom;</span><a href="#l33"></a>
<span id="l34"></span><a href="#l34"></a>
<span id="l35">/*</span><a href="#l35"></a>
<span id="l36"> * RandomCookie ... SSL hands standard format random cookies (nonces)</span><a href="#l36"></a>
<span id="l37"> * around.  These know how to encode/decode themselves on SSL streams,</span><a href="#l37"></a>
<span id="l38"> * and can be created and printed.</span><a href="#l38"></a>
<span id="l39"> *</span><a href="#l39"></a>
<span id="l40"> * @author David Brownell</span><a href="#l40"></a>
<span id="l41"> */</span><a href="#l41"></a>
<span id="l42">final class RandomCookie {</span><a href="#l42"></a>
<span id="l43">    final byte[] randomBytes = new byte[32];   // exactly 32 bytes</span><a href="#l43"></a>
<span id="l44"></span><a href="#l44"></a>
<span id="l45">    private static final byte[] hrrRandomBytes = new byte[] {</span><a href="#l45"></a>
<span id="l46">            (byte)0xCF, (byte)0x21, (byte)0xAD, (byte)0x74,</span><a href="#l46"></a>
<span id="l47">            (byte)0xE5, (byte)0x9A, (byte)0x61, (byte)0x11,</span><a href="#l47"></a>
<span id="l48">            (byte)0xBE, (byte)0x1D, (byte)0x8C, (byte)0x02,</span><a href="#l48"></a>
<span id="l49">            (byte)0x1E, (byte)0x65, (byte)0xB8, (byte)0x91,</span><a href="#l49"></a>
<span id="l50">            (byte)0xC2, (byte)0xA2, (byte)0x11, (byte)0x16,</span><a href="#l50"></a>
<span id="l51">            (byte)0x7A, (byte)0xBB, (byte)0x8C, (byte)0x5E,</span><a href="#l51"></a>
<span id="l52">            (byte)0x07, (byte)0x9E, (byte)0x09, (byte)0xE2,</span><a href="#l52"></a>
<span id="l53">            (byte)0xC8, (byte)0xA8, (byte)0x33, (byte)0x9C</span><a href="#l53"></a>
<span id="l54">        };</span><a href="#l54"></a>
<span id="l55"></span><a href="#l55"></a>
<span id="l56">    private static final byte[] t12Protection = new byte[] {</span><a href="#l56"></a>
<span id="l57">            (byte)0x44, (byte)0x4F, (byte)0x57, (byte)0x4E,</span><a href="#l57"></a>
<span id="l58">            (byte)0x47, (byte)0x52, (byte)0x44, (byte)0x01</span><a href="#l58"></a>
<span id="l59">        };</span><a href="#l59"></a>
<span id="l60"></span><a href="#l60"></a>
<span id="l61">    private static final byte[] t11Protection = new byte[] {</span><a href="#l61"></a>
<span id="l62">            (byte)0x44, (byte)0x4F, (byte)0x57, (byte)0x4E,</span><a href="#l62"></a>
<span id="l63">            (byte)0x47, (byte)0x52, (byte)0x44, (byte)0x00</span><a href="#l63"></a>
<span id="l64">        };</span><a href="#l64"></a>
<span id="l65"></span><a href="#l65"></a>
<span id="l66">    static final RandomCookie hrrRandom = new RandomCookie(hrrRandomBytes);</span><a href="#l66"></a>
<span id="l67"></span><a href="#l67"></a>
<span id="l68">    RandomCookie(SecureRandom generator) {</span><a href="#l68"></a>
<span id="l69">        generator.nextBytes(randomBytes);</span><a href="#l69"></a>
<span id="l70">    }</span><a href="#l70"></a>
<span id="l71"></span><a href="#l71"></a>
<span id="l72">    // Used for server random generation with version downgrade protection.</span><a href="#l72"></a>
<span id="l73">    RandomCookie(HandshakeContext context) {</span><a href="#l73"></a>
<span id="l74">        SecureRandom generator = context.sslContext.getSecureRandom();</span><a href="#l74"></a>
<span id="l75">        generator.nextBytes(randomBytes);</span><a href="#l75"></a>
<span id="l76"></span><a href="#l76"></a>
<span id="l77">        // TLS 1.3 has a downgrade protection mechanism embedded in the</span><a href="#l77"></a>
<span id="l78">        // server's random value.  TLS 1.3 servers which negotiate TLS 1.2</span><a href="#l78"></a>
<span id="l79">        // or below in response to a ClientHello MUST set the last eight</span><a href="#l79"></a>
<span id="l80">        // bytes of their Random value specially.</span><a href="#l80"></a>
<span id="l81">        byte[] protection = null;</span><a href="#l81"></a>
<span id="l82">        if (context.maximumActiveProtocol.useTLS13PlusSpec()) {</span><a href="#l82"></a>
<span id="l83">            if (!context.negotiatedProtocol.useTLS13PlusSpec()) {</span><a href="#l83"></a>
<span id="l84">                if (context.negotiatedProtocol.useTLS12PlusSpec()) {</span><a href="#l84"></a>
<span id="l85">                    protection = t12Protection;</span><a href="#l85"></a>
<span id="l86">                } else {</span><a href="#l86"></a>
<span id="l87">                    protection = t11Protection;</span><a href="#l87"></a>
<span id="l88">                }</span><a href="#l88"></a>
<span id="l89">            }</span><a href="#l89"></a>
<span id="l90">        } else if (context.maximumActiveProtocol.useTLS12PlusSpec()) {</span><a href="#l90"></a>
<span id="l91">            if (!context.negotiatedProtocol.useTLS12PlusSpec()) {</span><a href="#l91"></a>
<span id="l92">                protection = t11Protection;</span><a href="#l92"></a>
<span id="l93">            }</span><a href="#l93"></a>
<span id="l94">        }</span><a href="#l94"></a>
<span id="l95"></span><a href="#l95"></a>
<span id="l96">        if (protection != null) {</span><a href="#l96"></a>
<span id="l97">            System.arraycopy(protection, 0, randomBytes,</span><a href="#l97"></a>
<span id="l98">                    randomBytes.length - protection.length, protection.length);</span><a href="#l98"></a>
<span id="l99">        }</span><a href="#l99"></a>
<span id="l100">    }</span><a href="#l100"></a>
<span id="l101"></span><a href="#l101"></a>
<span id="l102">    RandomCookie(ByteBuffer m) throws IOException {</span><a href="#l102"></a>
<span id="l103">        m.get(randomBytes);</span><a href="#l103"></a>
<span id="l104">    }</span><a href="#l104"></a>
<span id="l105"></span><a href="#l105"></a>
<span id="l106">    private RandomCookie(byte[] randomBytes) {</span><a href="#l106"></a>
<span id="l107">        System.arraycopy(randomBytes, 0, this.randomBytes, 0, 32);</span><a href="#l107"></a>
<span id="l108">    }</span><a href="#l108"></a>
<span id="l109"></span><a href="#l109"></a>
<span id="l110">    @Override</span><a href="#l110"></a>
<span id="l111">    public String toString() {</span><a href="#l111"></a>
<span id="l112">        return &quot;random_bytes = {&quot; + Utilities.toHexString(randomBytes) + &quot;}&quot;;</span><a href="#l112"></a>
<span id="l113">    }</span><a href="#l113"></a>
<span id="l114"></span><a href="#l114"></a>
<span id="l115">    boolean isHelloRetryRequest() {</span><a href="#l115"></a>
<span id="l116">        return MessageDigest.isEqual(hrrRandomBytes, randomBytes);</span><a href="#l116"></a>
<span id="l117">    }</span><a href="#l117"></a>
<span id="l118"></span><a href="#l118"></a>
<span id="l119">    // Used for client random validation of version downgrade protection.</span><a href="#l119"></a>
<span id="l120">    boolean isVersionDowngrade(HandshakeContext context) {</span><a href="#l120"></a>
<span id="l121">        if (context.maximumActiveProtocol.useTLS13PlusSpec()) {</span><a href="#l121"></a>
<span id="l122">            if (!context.negotiatedProtocol.useTLS13PlusSpec()) {</span><a href="#l122"></a>
<span id="l123">                return isT12Downgrade() || isT11Downgrade();</span><a href="#l123"></a>
<span id="l124">            }</span><a href="#l124"></a>
<span id="l125">        } else if (context.maximumActiveProtocol.useTLS12PlusSpec()) {</span><a href="#l125"></a>
<span id="l126">            if (!context.negotiatedProtocol.useTLS12PlusSpec()) {</span><a href="#l126"></a>
<span id="l127">                return isT11Downgrade();</span><a href="#l127"></a>
<span id="l128">            }</span><a href="#l128"></a>
<span id="l129">        }</span><a href="#l129"></a>
<span id="l130"></span><a href="#l130"></a>
<span id="l131">        return false;</span><a href="#l131"></a>
<span id="l132">    }</span><a href="#l132"></a>
<span id="l133"></span><a href="#l133"></a>
<span id="l134">    private boolean isT12Downgrade() {</span><a href="#l134"></a>
<span id="l135">        return ByteArrays.isEqual(randomBytes, 24, 32, t12Protection, 0, 8);</span><a href="#l135"></a>
<span id="l136">    }</span><a href="#l136"></a>
<span id="l137"></span><a href="#l137"></a>
<span id="l138">    private boolean isT11Downgrade() {</span><a href="#l138"></a>
<span id="l139">        return ByteArrays.isEqual(randomBytes, 24, 32, t11Protection, 0, 8);</span><a href="#l139"></a>
<span id="l140">    }</span><a href="#l140"></a>
<span id="l141">}</span><a href="#l141"></a></pre>
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

