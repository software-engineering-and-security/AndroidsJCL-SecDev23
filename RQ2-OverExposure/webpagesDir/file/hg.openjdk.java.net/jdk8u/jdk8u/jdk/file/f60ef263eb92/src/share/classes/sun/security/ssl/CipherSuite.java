<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: f60ef263eb92 src/share/classes/sun/security/ssl/CipherSuite.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk8u/jdk8u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/shortlog/f60ef263eb92">log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/graph/f60ef263eb92">graph</a></li>
<li><a href="/jdk8u/jdk8u/jdk/tags">tags</a></li>
<li><a href="/jdk8u/jdk8u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/rev/f60ef263eb92">changeset</a></li>
<li><a href="/jdk8u/jdk8u/jdk/file/f60ef263eb92/src/share/classes/sun/security/ssl/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/ssl/CipherSuite.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/f60ef263eb92/src/share/classes/sun/security/ssl/CipherSuite.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/f60ef263eb92/src/share/classes/sun/security/ssl/CipherSuite.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/f60ef263eb92/src/share/classes/sun/security/ssl/CipherSuite.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/f60ef263eb92/src/share/classes/sun/security/ssl/CipherSuite.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/f60ef263eb92/src/share/classes/sun/security/ssl/CipherSuite.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/ssl/CipherSuite.java @ 14554:f60ef263eb92</h3>

<form class="search" action="/jdk8u/jdk8u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk8u/jdk8u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8163326: Update the default enabled cipher suites preference
Reviewed-by: avoitylov, andrew</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#109;&#98;&#97;&#108;&#97;&#111;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Thu, 01 Jul 2021 07:04:53 +0000</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/466b007eedbb/src/share/classes/sun/security/ssl/CipherSuite.java">466b007eedbb</a> </td>
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
<span id="l2"> * Copyright (c) 2002, 2020, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l28">import java.util.ArrayList;</span><a href="#l28"></a>
<span id="l29">import java.util.Arrays;</span><a href="#l29"></a>
<span id="l30">import java.util.Collection;</span><a href="#l30"></a>
<span id="l31">import java.util.Collections;</span><a href="#l31"></a>
<span id="l32">import java.util.LinkedList;</span><a href="#l32"></a>
<span id="l33">import java.util.List;</span><a href="#l33"></a>
<span id="l34">import static sun.security.ssl.CipherSuite.HashAlg.*;</span><a href="#l34"></a>
<span id="l35">import static sun.security.ssl.CipherSuite.KeyExchange.*;</span><a href="#l35"></a>
<span id="l36">import static sun.security.ssl.CipherSuite.MacAlg.*;</span><a href="#l36"></a>
<span id="l37">import static sun.security.ssl.SSLCipher.*;</span><a href="#l37"></a>
<span id="l38">import sun.security.ssl.SupportedGroupsExtension.NamedGroupType;</span><a href="#l38"></a>
<span id="l39">import static sun.security.ssl.SupportedGroupsExtension.NamedGroupType.*;</span><a href="#l39"></a>
<span id="l40"></span><a href="#l40"></a>
<span id="l41">/**</span><a href="#l41"></a>
<span id="l42"> * Enum for SSL/TLS cipher suites.</span><a href="#l42"></a>
<span id="l43"> *</span><a href="#l43"></a>
<span id="l44"> * Please refer to the &quot;TLS Cipher Suite Registry&quot; section for more details</span><a href="#l44"></a>
<span id="l45"> * about each cipher suite:</span><a href="#l45"></a>
<span id="l46"> *     https://www.iana.org/assignments/tls-parameters/tls-parameters.xml</span><a href="#l46"></a>
<span id="l47"> */</span><a href="#l47"></a>
<span id="l48">enum CipherSuite {</span><a href="#l48"></a>
<span id="l49">    //</span><a href="#l49"></a>
<span id="l50">    // in preference order</span><a href="#l50"></a>
<span id="l51">    //</span><a href="#l51"></a>
<span id="l52"></span><a href="#l52"></a>
<span id="l53">    // Definition of the CipherSuites that are enabled by default.</span><a href="#l53"></a>
<span id="l54">    //</span><a href="#l54"></a>
<span id="l55">    // They are listed in preference order, most preferred first, using</span><a href="#l55"></a>
<span id="l56">    // the following criteria:</span><a href="#l56"></a>
<span id="l57">    // 1. Prefer Suite B compliant cipher suites, see RFC6460 (To be</span><a href="#l57"></a>
<span id="l58">    //    changed later, see below).</span><a href="#l58"></a>
<span id="l59">    // 2. Prefer forward secrecy cipher suites.</span><a href="#l59"></a>
<span id="l60">    // 3. Prefer the stronger bulk cipher, in the order of AES_256(GCM),</span><a href="#l60"></a>
<span id="l61">    //    AES_128(GCM), AES_256, AES_128, 3DES-EDE.</span><a href="#l61"></a>
<span id="l62">    // 4. Prefer the stronger MAC algorithm, in the order of SHA384,</span><a href="#l62"></a>
<span id="l63">    //    SHA256, SHA, MD5.</span><a href="#l63"></a>
<span id="l64">    // 5. Prefer the better performance of key exchange and digital</span><a href="#l64"></a>
<span id="l65">    //    signature algorithm, in the order of ECDHE-ECDSA, ECDHE-RSA,</span><a href="#l65"></a>
<span id="l66">    //    DHE-RSA, DHE-DSS, ECDH-ECDSA, ECDH-RSA, RSA.</span><a href="#l66"></a>
<span id="l67"></span><a href="#l67"></a>
<span id="l68">    // TLS 1.3 cipher suites.</span><a href="#l68"></a>
<span id="l69">    TLS_AES_256_GCM_SHA384(</span><a href="#l69"></a>
<span id="l70">            0x1302, true, &quot;TLS_AES_256_GCM_SHA384&quot;,</span><a href="#l70"></a>
<span id="l71">            ProtocolVersion.PROTOCOLS_OF_13, B_AES_256_GCM_IV, H_SHA384),</span><a href="#l71"></a>
<span id="l72">    TLS_AES_128_GCM_SHA256(</span><a href="#l72"></a>
<span id="l73">            0x1301, true, &quot;TLS_AES_128_GCM_SHA256&quot;,</span><a href="#l73"></a>
<span id="l74">            ProtocolVersion.PROTOCOLS_OF_13, B_AES_128_GCM_IV, H_SHA256),</span><a href="#l74"></a>
<span id="l75"></span><a href="#l75"></a>
<span id="l76">    // Suite B compliant cipher suites, see RFC 6460.</span><a href="#l76"></a>
<span id="l77">    //</span><a href="#l77"></a>
<span id="l78">    // Note that, at present this provider is not Suite B compliant. The</span><a href="#l78"></a>
<span id="l79">    // preference order of the GCM cipher suites does not follow the spec</span><a href="#l79"></a>
<span id="l80">    // of RFC 6460.  In this section, only two cipher suites are listed</span><a href="#l80"></a>
<span id="l81">    // so that applications can make use of Suite-B compliant cipher</span><a href="#l81"></a>
<span id="l82">    // suite firstly.</span><a href="#l82"></a>
<span id="l83">    TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384(</span><a href="#l83"></a>
<span id="l84">            0xC02C, true, &quot;TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384&quot;, &quot;&quot;,</span><a href="#l84"></a>
<span id="l85">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l85"></a>
<span id="l86">            K_ECDHE_ECDSA, B_AES_256_GCM, M_NULL, H_SHA384),</span><a href="#l86"></a>
<span id="l87">    TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256(</span><a href="#l87"></a>
<span id="l88">            0xC02B, true, &quot;TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256&quot;, &quot;&quot;,</span><a href="#l88"></a>
<span id="l89">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l89"></a>
<span id="l90">            K_ECDHE_ECDSA, B_AES_128_GCM, M_NULL, H_SHA256),</span><a href="#l90"></a>
<span id="l91"></span><a href="#l91"></a>
<span id="l92">    //</span><a href="#l92"></a>
<span id="l93">    // Forward screcy cipher suites.</span><a href="#l93"></a>
<span id="l94">    //</span><a href="#l94"></a>
<span id="l95"></span><a href="#l95"></a>
<span id="l96">    // AES_256(GCM) - ECDHE</span><a href="#l96"></a>
<span id="l97">    TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384(</span><a href="#l97"></a>
<span id="l98">            0xC030, true, &quot;TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384&quot;, &quot;&quot;,</span><a href="#l98"></a>
<span id="l99">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l99"></a>
<span id="l100">            K_ECDHE_RSA, B_AES_256_GCM, M_NULL, H_SHA384),</span><a href="#l100"></a>
<span id="l101"></span><a href="#l101"></a>
<span id="l102">    // AES_128(GCM) - ECDHE</span><a href="#l102"></a>
<span id="l103">    TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256(</span><a href="#l103"></a>
<span id="l104">            0xC02F, true, &quot;TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256&quot;, &quot;&quot;,</span><a href="#l104"></a>
<span id="l105">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l105"></a>
<span id="l106">            K_ECDHE_RSA, B_AES_128_GCM, M_NULL, H_SHA256),</span><a href="#l106"></a>
<span id="l107"></span><a href="#l107"></a>
<span id="l108">    // AES_256(GCM) - DHE</span><a href="#l108"></a>
<span id="l109">    TLS_DHE_RSA_WITH_AES_256_GCM_SHA384(</span><a href="#l109"></a>
<span id="l110">            0x009F, true, &quot;TLS_DHE_RSA_WITH_AES_256_GCM_SHA384&quot;, &quot;&quot;,</span><a href="#l110"></a>
<span id="l111">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l111"></a>
<span id="l112">            K_DHE_RSA, B_AES_256_GCM, M_NULL, H_SHA384),</span><a href="#l112"></a>
<span id="l113">    TLS_DHE_DSS_WITH_AES_256_GCM_SHA384(</span><a href="#l113"></a>
<span id="l114">            0x00A3, true, &quot;TLS_DHE_DSS_WITH_AES_256_GCM_SHA384&quot;, &quot;&quot;,</span><a href="#l114"></a>
<span id="l115">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l115"></a>
<span id="l116">            K_DHE_DSS, B_AES_256_GCM, M_NULL, H_SHA384),</span><a href="#l116"></a>
<span id="l117"></span><a href="#l117"></a>
<span id="l118">    // AES_128(GCM) - DHE</span><a href="#l118"></a>
<span id="l119">    TLS_DHE_RSA_WITH_AES_128_GCM_SHA256(</span><a href="#l119"></a>
<span id="l120">            0x009E, true, &quot;TLS_DHE_RSA_WITH_AES_128_GCM_SHA256&quot;, &quot;&quot;,</span><a href="#l120"></a>
<span id="l121">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l121"></a>
<span id="l122">            K_DHE_RSA, B_AES_128_GCM, M_NULL, H_SHA256),</span><a href="#l122"></a>
<span id="l123">    TLS_DHE_DSS_WITH_AES_128_GCM_SHA256(</span><a href="#l123"></a>
<span id="l124">            0x00A2, true, &quot;TLS_DHE_DSS_WITH_AES_128_GCM_SHA256&quot;, &quot;&quot;,</span><a href="#l124"></a>
<span id="l125">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l125"></a>
<span id="l126">            K_DHE_DSS, B_AES_128_GCM, M_NULL, H_SHA256),</span><a href="#l126"></a>
<span id="l127"></span><a href="#l127"></a>
<span id="l128">    // AES_256(CBC) - ECDHE</span><a href="#l128"></a>
<span id="l129">    TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384(</span><a href="#l129"></a>
<span id="l130">            0xC024, true, &quot;TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384&quot;, &quot;&quot;,</span><a href="#l130"></a>
<span id="l131">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l131"></a>
<span id="l132">            K_ECDHE_ECDSA, B_AES_256, M_SHA384, H_SHA384),</span><a href="#l132"></a>
<span id="l133">    TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384(</span><a href="#l133"></a>
<span id="l134">            0xC028, true, &quot;TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384&quot;, &quot;&quot;,</span><a href="#l134"></a>
<span id="l135">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l135"></a>
<span id="l136">            K_ECDHE_RSA, B_AES_256, M_SHA384, H_SHA384),</span><a href="#l136"></a>
<span id="l137"></span><a href="#l137"></a>
<span id="l138">    // AES_128(CBC) - ECDHE</span><a href="#l138"></a>
<span id="l139">    TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256(</span><a href="#l139"></a>
<span id="l140">            0xC023, true, &quot;TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256&quot;, &quot;&quot;,</span><a href="#l140"></a>
<span id="l141">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l141"></a>
<span id="l142">            K_ECDHE_ECDSA, B_AES_128, M_SHA256, H_SHA256),</span><a href="#l142"></a>
<span id="l143">    TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256(</span><a href="#l143"></a>
<span id="l144">            0xC027, true, &quot;TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256&quot;, &quot;&quot;,</span><a href="#l144"></a>
<span id="l145">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l145"></a>
<span id="l146">            K_ECDHE_RSA, B_AES_128, M_SHA256, H_SHA256),</span><a href="#l146"></a>
<span id="l147"></span><a href="#l147"></a>
<span id="l148">    // AES_256(CBC) - DHE</span><a href="#l148"></a>
<span id="l149">    TLS_DHE_RSA_WITH_AES_256_CBC_SHA256(</span><a href="#l149"></a>
<span id="l150">            0x006B, true, &quot;TLS_DHE_RSA_WITH_AES_256_CBC_SHA256&quot;, &quot;&quot;,</span><a href="#l150"></a>
<span id="l151">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l151"></a>
<span id="l152">            K_DHE_RSA, B_AES_256, M_SHA256, H_SHA256),</span><a href="#l152"></a>
<span id="l153">    TLS_DHE_DSS_WITH_AES_256_CBC_SHA256(</span><a href="#l153"></a>
<span id="l154">            0x006A, true, &quot;TLS_DHE_DSS_WITH_AES_256_CBC_SHA256&quot;, &quot;&quot;,</span><a href="#l154"></a>
<span id="l155">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l155"></a>
<span id="l156">            K_DHE_DSS, B_AES_256, M_SHA256, H_SHA256),</span><a href="#l156"></a>
<span id="l157"></span><a href="#l157"></a>
<span id="l158">    // AES_128(CBC) - DHE</span><a href="#l158"></a>
<span id="l159">    TLS_DHE_RSA_WITH_AES_128_CBC_SHA256(</span><a href="#l159"></a>
<span id="l160">            0x0067, true, &quot;TLS_DHE_RSA_WITH_AES_128_CBC_SHA256&quot;, &quot;&quot;,</span><a href="#l160"></a>
<span id="l161">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l161"></a>
<span id="l162">            K_DHE_RSA, B_AES_128, M_SHA256, H_SHA256),</span><a href="#l162"></a>
<span id="l163">    TLS_DHE_DSS_WITH_AES_128_CBC_SHA256(</span><a href="#l163"></a>
<span id="l164">            0x0040, true, &quot;TLS_DHE_DSS_WITH_AES_128_CBC_SHA256&quot;, &quot;&quot;,</span><a href="#l164"></a>
<span id="l165">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l165"></a>
<span id="l166">            K_DHE_DSS, B_AES_128, M_SHA256, H_SHA256),</span><a href="#l166"></a>
<span id="l167"></span><a href="#l167"></a>
<span id="l168">    //</span><a href="#l168"></a>
<span id="l169">    // not forward screcy cipher suites.</span><a href="#l169"></a>
<span id="l170">    //</span><a href="#l170"></a>
<span id="l171"></span><a href="#l171"></a>
<span id="l172">    // AES_256(GCM)</span><a href="#l172"></a>
<span id="l173">    TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384(</span><a href="#l173"></a>
<span id="l174">            0xC02E, true, &quot;TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384&quot;, &quot;&quot;,</span><a href="#l174"></a>
<span id="l175">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l175"></a>
<span id="l176">            K_ECDH_ECDSA, B_AES_256_GCM, M_NULL, H_SHA384),</span><a href="#l176"></a>
<span id="l177">    TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384(</span><a href="#l177"></a>
<span id="l178">            0xC032, true, &quot;TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384&quot;, &quot;&quot;,</span><a href="#l178"></a>
<span id="l179">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l179"></a>
<span id="l180">            K_ECDH_RSA, B_AES_256_GCM, M_NULL, H_SHA384),</span><a href="#l180"></a>
<span id="l181"></span><a href="#l181"></a>
<span id="l182">    // AES_128(GCM)</span><a href="#l182"></a>
<span id="l183">    TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256(</span><a href="#l183"></a>
<span id="l184">            0xC02D, true, &quot;TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256&quot;, &quot;&quot;,</span><a href="#l184"></a>
<span id="l185">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l185"></a>
<span id="l186">            K_ECDH_ECDSA, B_AES_128_GCM, M_NULL, H_SHA256),</span><a href="#l186"></a>
<span id="l187">    TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256(</span><a href="#l187"></a>
<span id="l188">            0xC031, true, &quot;TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256&quot;, &quot;&quot;,</span><a href="#l188"></a>
<span id="l189">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l189"></a>
<span id="l190">            K_ECDH_RSA, B_AES_128_GCM, M_NULL, H_SHA256),</span><a href="#l190"></a>
<span id="l191"></span><a href="#l191"></a>
<span id="l192">    // AES_256(CBC)</span><a href="#l192"></a>
<span id="l193">    TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384(</span><a href="#l193"></a>
<span id="l194">            0xC026, true, &quot;TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384&quot;, &quot;&quot;,</span><a href="#l194"></a>
<span id="l195">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l195"></a>
<span id="l196">            K_ECDH_ECDSA, B_AES_256, M_SHA384, H_SHA384),</span><a href="#l196"></a>
<span id="l197">    TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384(</span><a href="#l197"></a>
<span id="l198">            0xC02A, true, &quot;TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384&quot;, &quot;&quot;,</span><a href="#l198"></a>
<span id="l199">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l199"></a>
<span id="l200">            K_ECDH_RSA, B_AES_256, M_SHA384, H_SHA384),</span><a href="#l200"></a>
<span id="l201"></span><a href="#l201"></a>
<span id="l202">    // AES_128(CBC)</span><a href="#l202"></a>
<span id="l203">    TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256(</span><a href="#l203"></a>
<span id="l204">            0xC025, true, &quot;TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256&quot;, &quot;&quot;,</span><a href="#l204"></a>
<span id="l205">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l205"></a>
<span id="l206">            K_ECDH_ECDSA, B_AES_128, M_SHA256, H_SHA256),</span><a href="#l206"></a>
<span id="l207">    TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256(</span><a href="#l207"></a>
<span id="l208">            0xC029, true, &quot;TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256&quot;, &quot;&quot;,</span><a href="#l208"></a>
<span id="l209">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l209"></a>
<span id="l210">            K_ECDH_RSA, B_AES_128, M_SHA256, H_SHA256),</span><a href="#l210"></a>
<span id="l211"></span><a href="#l211"></a>
<span id="l212">    //</span><a href="#l212"></a>
<span id="l213">    // Legacy, used for compatibility</span><a href="#l213"></a>
<span id="l214">    //</span><a href="#l214"></a>
<span id="l215"></span><a href="#l215"></a>
<span id="l216">    // AES_256(CBC) - ECDHE - Using SHA</span><a href="#l216"></a>
<span id="l217">    TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA(</span><a href="#l217"></a>
<span id="l218">            0xC00A, true, &quot;TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l218"></a>
<span id="l219">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l219"></a>
<span id="l220">            K_ECDHE_ECDSA, B_AES_256, M_SHA, H_SHA256),</span><a href="#l220"></a>
<span id="l221">    TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA(</span><a href="#l221"></a>
<span id="l222">            0xC014, true, &quot;TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l222"></a>
<span id="l223">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l223"></a>
<span id="l224">            K_ECDHE_RSA, B_AES_256, M_SHA, H_SHA256),</span><a href="#l224"></a>
<span id="l225"></span><a href="#l225"></a>
<span id="l226">    // AES_128(CBC) - ECDHE - using SHA</span><a href="#l226"></a>
<span id="l227">    TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA(</span><a href="#l227"></a>
<span id="l228">            0xC009, true, &quot;TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l228"></a>
<span id="l229">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l229"></a>
<span id="l230">            K_ECDHE_ECDSA, B_AES_128, M_SHA, H_SHA256),</span><a href="#l230"></a>
<span id="l231">    TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA(</span><a href="#l231"></a>
<span id="l232">            0xC013, true, &quot;TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l232"></a>
<span id="l233">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l233"></a>
<span id="l234">            K_ECDHE_RSA, B_AES_128, M_SHA, H_SHA256),</span><a href="#l234"></a>
<span id="l235"></span><a href="#l235"></a>
<span id="l236">    // AES_256(CBC) - DHE - Using SHA</span><a href="#l236"></a>
<span id="l237">    TLS_DHE_RSA_WITH_AES_256_CBC_SHA(</span><a href="#l237"></a>
<span id="l238">            0x0039, true, &quot;TLS_DHE_RSA_WITH_AES_256_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l238"></a>
<span id="l239">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l239"></a>
<span id="l240">            K_DHE_RSA, B_AES_256, M_SHA, H_SHA256),</span><a href="#l240"></a>
<span id="l241">    TLS_DHE_DSS_WITH_AES_256_CBC_SHA(</span><a href="#l241"></a>
<span id="l242">            0x0038, true, &quot;TLS_DHE_DSS_WITH_AES_256_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l242"></a>
<span id="l243">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l243"></a>
<span id="l244">            K_DHE_DSS, B_AES_256, M_SHA, H_SHA256),</span><a href="#l244"></a>
<span id="l245"></span><a href="#l245"></a>
<span id="l246">    // AES_128(CBC) - DHE - using SHA</span><a href="#l246"></a>
<span id="l247">    TLS_DHE_RSA_WITH_AES_128_CBC_SHA(</span><a href="#l247"></a>
<span id="l248">            0x0033, true, &quot;TLS_DHE_RSA_WITH_AES_128_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l248"></a>
<span id="l249">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l249"></a>
<span id="l250">            K_DHE_RSA, B_AES_128, M_SHA, H_SHA256),</span><a href="#l250"></a>
<span id="l251">    TLS_DHE_DSS_WITH_AES_128_CBC_SHA(</span><a href="#l251"></a>
<span id="l252">            0x0032, true, &quot;TLS_DHE_DSS_WITH_AES_128_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l252"></a>
<span id="l253">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l253"></a>
<span id="l254">            K_DHE_DSS, B_AES_128, M_SHA, H_SHA256),</span><a href="#l254"></a>
<span id="l255"></span><a href="#l255"></a>
<span id="l256">    // AES_256(CBC) - using SHA, not forward screcy</span><a href="#l256"></a>
<span id="l257">    TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA(</span><a href="#l257"></a>
<span id="l258">            0xC005, true, &quot;TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l258"></a>
<span id="l259">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l259"></a>
<span id="l260">            K_ECDH_ECDSA, B_AES_256, M_SHA, H_SHA256),</span><a href="#l260"></a>
<span id="l261">    TLS_ECDH_RSA_WITH_AES_256_CBC_SHA(</span><a href="#l261"></a>
<span id="l262">            0xC00F, true, &quot;TLS_ECDH_RSA_WITH_AES_256_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l262"></a>
<span id="l263">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l263"></a>
<span id="l264">            K_ECDH_RSA, B_AES_256, M_SHA, H_SHA256),</span><a href="#l264"></a>
<span id="l265"></span><a href="#l265"></a>
<span id="l266">    // AES_128(CBC) - using SHA, not forward screcy</span><a href="#l266"></a>
<span id="l267">    TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA(</span><a href="#l267"></a>
<span id="l268">            0xC004, true, &quot;TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l268"></a>
<span id="l269">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l269"></a>
<span id="l270">            K_ECDH_ECDSA, B_AES_128, M_SHA, H_SHA256),</span><a href="#l270"></a>
<span id="l271">    TLS_ECDH_RSA_WITH_AES_128_CBC_SHA(</span><a href="#l271"></a>
<span id="l272">            0xC00E, true, &quot;TLS_ECDH_RSA_WITH_AES_128_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l272"></a>
<span id="l273">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l273"></a>
<span id="l274">            K_ECDH_RSA, B_AES_128, M_SHA, H_SHA256),</span><a href="#l274"></a>
<span id="l275"></span><a href="#l275"></a>
<span id="l276">    //</span><a href="#l276"></a>
<span id="l277">    // deprecated, used for compatibility</span><a href="#l277"></a>
<span id="l278">    //</span><a href="#l278"></a>
<span id="l279"></span><a href="#l279"></a>
<span id="l280">    // RSA, AES_256(GCM)</span><a href="#l280"></a>
<span id="l281">    TLS_RSA_WITH_AES_256_GCM_SHA384(</span><a href="#l281"></a>
<span id="l282">            0x009D, true, &quot;TLS_RSA_WITH_AES_256_GCM_SHA384&quot;, &quot;&quot;,</span><a href="#l282"></a>
<span id="l283">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l283"></a>
<span id="l284">            K_RSA, B_AES_256_GCM, M_NULL, H_SHA384),</span><a href="#l284"></a>
<span id="l285"></span><a href="#l285"></a>
<span id="l286">    // RSA, AES_128(GCM)</span><a href="#l286"></a>
<span id="l287">    TLS_RSA_WITH_AES_128_GCM_SHA256(</span><a href="#l287"></a>
<span id="l288">            0x009C, true, &quot;TLS_RSA_WITH_AES_128_GCM_SHA256&quot;, &quot;&quot;,</span><a href="#l288"></a>
<span id="l289">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l289"></a>
<span id="l290">            K_RSA, B_AES_128_GCM, M_NULL, H_SHA256),</span><a href="#l290"></a>
<span id="l291"></span><a href="#l291"></a>
<span id="l292">    // RSA, AES_256(CBC)</span><a href="#l292"></a>
<span id="l293">    TLS_RSA_WITH_AES_256_CBC_SHA256(</span><a href="#l293"></a>
<span id="l294">            0x003D, true, &quot;TLS_RSA_WITH_AES_256_CBC_SHA256&quot;, &quot;&quot;,</span><a href="#l294"></a>
<span id="l295">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l295"></a>
<span id="l296">            K_RSA, B_AES_256, M_SHA256, H_SHA256),</span><a href="#l296"></a>
<span id="l297"></span><a href="#l297"></a>
<span id="l298">    // RSA, AES_128(CBC)</span><a href="#l298"></a>
<span id="l299">    TLS_RSA_WITH_AES_128_CBC_SHA256(</span><a href="#l299"></a>
<span id="l300">            0x003C, true, &quot;TLS_RSA_WITH_AES_128_CBC_SHA256&quot;, &quot;&quot;,</span><a href="#l300"></a>
<span id="l301">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l301"></a>
<span id="l302">            K_RSA, B_AES_128, M_SHA256, H_SHA256),</span><a href="#l302"></a>
<span id="l303"></span><a href="#l303"></a>
<span id="l304">    // RSA, AES_256(CBC) - using SHA, not forward screcy</span><a href="#l304"></a>
<span id="l305">    TLS_RSA_WITH_AES_256_CBC_SHA(</span><a href="#l305"></a>
<span id="l306">            0x0035, true, &quot;TLS_RSA_WITH_AES_256_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l306"></a>
<span id="l307">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l307"></a>
<span id="l308">            K_RSA, B_AES_256, M_SHA, H_SHA256),</span><a href="#l308"></a>
<span id="l309"></span><a href="#l309"></a>
<span id="l310">    // RSA, AES_128(CBC) - using SHA, not forward screcy</span><a href="#l310"></a>
<span id="l311">    TLS_RSA_WITH_AES_128_CBC_SHA(</span><a href="#l311"></a>
<span id="l312">            0x002F, true, &quot;TLS_RSA_WITH_AES_128_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l312"></a>
<span id="l313">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l313"></a>
<span id="l314">            K_RSA, B_AES_128, M_SHA, H_SHA256),</span><a href="#l314"></a>
<span id="l315"></span><a href="#l315"></a>
<span id="l316">    // 3DES_EDE, forward secrecy.</span><a href="#l316"></a>
<span id="l317">    TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA(</span><a href="#l317"></a>
<span id="l318">            0xC008, true, &quot;TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l318"></a>
<span id="l319">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l319"></a>
<span id="l320">            K_ECDHE_ECDSA, B_3DES, M_SHA, H_SHA256),</span><a href="#l320"></a>
<span id="l321">    TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA(</span><a href="#l321"></a>
<span id="l322">            0xC012, true, &quot;TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l322"></a>
<span id="l323">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l323"></a>
<span id="l324">            K_ECDHE_RSA, B_3DES, M_SHA, H_SHA256),</span><a href="#l324"></a>
<span id="l325">    SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA(</span><a href="#l325"></a>
<span id="l326">            0x0016, true, &quot;SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA&quot;,</span><a href="#l326"></a>
<span id="l327">                          &quot;TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA&quot;,</span><a href="#l327"></a>
<span id="l328">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l328"></a>
<span id="l329">            K_DHE_RSA, B_3DES, M_SHA, H_SHA256),</span><a href="#l329"></a>
<span id="l330">    SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA(</span><a href="#l330"></a>
<span id="l331">            0x0013, true, &quot;SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA&quot;,</span><a href="#l331"></a>
<span id="l332">                          &quot;TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA&quot;,</span><a href="#l332"></a>
<span id="l333">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l333"></a>
<span id="l334">            K_DHE_DSS, B_3DES, M_SHA, H_SHA256),</span><a href="#l334"></a>
<span id="l335"></span><a href="#l335"></a>
<span id="l336">    // 3DES_EDE, not forward secrecy.</span><a href="#l336"></a>
<span id="l337">    TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA(</span><a href="#l337"></a>
<span id="l338">            0xC003, true, &quot;TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l338"></a>
<span id="l339">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l339"></a>
<span id="l340">            K_ECDH_ECDSA, B_3DES, M_SHA, H_SHA256),</span><a href="#l340"></a>
<span id="l341">    TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA(</span><a href="#l341"></a>
<span id="l342">            0xC00D, true, &quot;TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l342"></a>
<span id="l343">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l343"></a>
<span id="l344">            K_ECDH_RSA, B_3DES, M_SHA, H_SHA256),</span><a href="#l344"></a>
<span id="l345">    SSL_RSA_WITH_3DES_EDE_CBC_SHA(</span><a href="#l345"></a>
<span id="l346">            0x000A, true, &quot;SSL_RSA_WITH_3DES_EDE_CBC_SHA&quot;,</span><a href="#l346"></a>
<span id="l347">                          &quot;TLS_RSA_WITH_3DES_EDE_CBC_SHA&quot;,</span><a href="#l347"></a>
<span id="l348">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l348"></a>
<span id="l349">            K_RSA, B_3DES, M_SHA, H_SHA256),</span><a href="#l349"></a>
<span id="l350"></span><a href="#l350"></a>
<span id="l351">    // Renegotiation protection request Signalling Cipher Suite Value (SCSV).</span><a href="#l351"></a>
<span id="l352">    TLS_EMPTY_RENEGOTIATION_INFO_SCSV(        //  RFC 5746, TLS 1.2 and prior</span><a href="#l352"></a>
<span id="l353">            0x00FF, true, &quot;TLS_EMPTY_RENEGOTIATION_INFO_SCSV&quot;, &quot;&quot;,</span><a href="#l353"></a>
<span id="l354">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l354"></a>
<span id="l355">            K_SCSV, B_NULL, M_NULL, H_NONE),</span><a href="#l355"></a>
<span id="l356"></span><a href="#l356"></a>
<span id="l357">    // Definition of the CipherSuites that are supported but not enabled</span><a href="#l357"></a>
<span id="l358">    // by default.</span><a href="#l358"></a>
<span id="l359">    // They are listed in preference order, preferred first, using the</span><a href="#l359"></a>
<span id="l360">    // following criteria:</span><a href="#l360"></a>
<span id="l361">    // 1. If a cipher suite has been obsoleted, we put it at the end of</span><a href="#l361"></a>
<span id="l362">    //    the list.</span><a href="#l362"></a>
<span id="l363">    // 2. Prefer the stronger bulk cipher, in the order of AES_256,</span><a href="#l363"></a>
<span id="l364">    //    AES_128, 3DES-EDE, RC-4, DES, DES40, RC4_40, NULL.</span><a href="#l364"></a>
<span id="l365">    // 3. Prefer the stronger MAC algorithm, in the order of SHA384,</span><a href="#l365"></a>
<span id="l366">    //    SHA256, SHA, MD5.</span><a href="#l366"></a>
<span id="l367">    // 4. Prefer the better performance of key exchange and digital</span><a href="#l367"></a>
<span id="l368">    //    signature algorithm, in the order of ECDHE-ECDSA, ECDHE-RSA,</span><a href="#l368"></a>
<span id="l369">    //    RSA, ECDH-ECDSA, ECDH-RSA, DHE-RSA, DHE-DSS, anonymous.</span><a href="#l369"></a>
<span id="l370">    TLS_DH_anon_WITH_AES_256_GCM_SHA384(</span><a href="#l370"></a>
<span id="l371">            0x00A7, false, &quot;TLS_DH_anon_WITH_AES_256_GCM_SHA384&quot;, &quot;&quot;,</span><a href="#l371"></a>
<span id="l372">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l372"></a>
<span id="l373">            K_DH_ANON, B_AES_256_GCM, M_NULL, H_SHA384),</span><a href="#l373"></a>
<span id="l374">    TLS_DH_anon_WITH_AES_128_GCM_SHA256(</span><a href="#l374"></a>
<span id="l375">            0x00A6, false, &quot;TLS_DH_anon_WITH_AES_128_GCM_SHA256&quot;, &quot;&quot;,</span><a href="#l375"></a>
<span id="l376">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l376"></a>
<span id="l377">            K_DH_ANON, B_AES_128_GCM, M_NULL, H_SHA256),</span><a href="#l377"></a>
<span id="l378">    TLS_DH_anon_WITH_AES_256_CBC_SHA256(</span><a href="#l378"></a>
<span id="l379">            0x006D, false, &quot;TLS_DH_anon_WITH_AES_256_CBC_SHA256&quot;, &quot;&quot;,</span><a href="#l379"></a>
<span id="l380">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l380"></a>
<span id="l381">            K_DH_ANON, B_AES_256, M_SHA256, H_SHA256),</span><a href="#l381"></a>
<span id="l382">    TLS_ECDH_anon_WITH_AES_256_CBC_SHA(</span><a href="#l382"></a>
<span id="l383">            0xC019, false, &quot;TLS_ECDH_anon_WITH_AES_256_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l383"></a>
<span id="l384">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l384"></a>
<span id="l385">            K_ECDH_ANON, B_AES_256, M_SHA, H_SHA256),</span><a href="#l385"></a>
<span id="l386">    TLS_DH_anon_WITH_AES_256_CBC_SHA(</span><a href="#l386"></a>
<span id="l387">            0x003A, false, &quot;TLS_DH_anon_WITH_AES_256_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l387"></a>
<span id="l388">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l388"></a>
<span id="l389">            K_DH_ANON, B_AES_256, M_SHA, H_SHA256),</span><a href="#l389"></a>
<span id="l390">    TLS_DH_anon_WITH_AES_128_CBC_SHA256(</span><a href="#l390"></a>
<span id="l391">            0x006C, false, &quot;TLS_DH_anon_WITH_AES_128_CBC_SHA256&quot;, &quot;&quot;,</span><a href="#l391"></a>
<span id="l392">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l392"></a>
<span id="l393">            K_DH_ANON, B_AES_128, M_SHA256, H_SHA256),</span><a href="#l393"></a>
<span id="l394">    TLS_ECDH_anon_WITH_AES_128_CBC_SHA(</span><a href="#l394"></a>
<span id="l395">            0xC018, false, &quot;TLS_ECDH_anon_WITH_AES_128_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l395"></a>
<span id="l396">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l396"></a>
<span id="l397">            K_ECDH_ANON, B_AES_128, M_SHA, H_SHA256),</span><a href="#l397"></a>
<span id="l398">    TLS_DH_anon_WITH_AES_128_CBC_SHA(</span><a href="#l398"></a>
<span id="l399">            0x0034, false, &quot;TLS_DH_anon_WITH_AES_128_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l399"></a>
<span id="l400">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l400"></a>
<span id="l401">            K_DH_ANON, B_AES_128, M_SHA, H_SHA256),</span><a href="#l401"></a>
<span id="l402">    TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA(</span><a href="#l402"></a>
<span id="l403">            0xC017, false, &quot;TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l403"></a>
<span id="l404">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l404"></a>
<span id="l405">            K_ECDH_ANON, B_3DES, M_SHA, H_SHA256),</span><a href="#l405"></a>
<span id="l406">    SSL_DH_anon_WITH_3DES_EDE_CBC_SHA(</span><a href="#l406"></a>
<span id="l407">            0x001B, false, &quot;SSL_DH_anon_WITH_3DES_EDE_CBC_SHA&quot;,</span><a href="#l407"></a>
<span id="l408">                           &quot;TLS_DH_anon_WITH_3DES_EDE_CBC_SHA&quot;,</span><a href="#l408"></a>
<span id="l409">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l409"></a>
<span id="l410">            K_DH_ANON, B_3DES, M_SHA, H_SHA256),</span><a href="#l410"></a>
<span id="l411"></span><a href="#l411"></a>
<span id="l412">    // RC4</span><a href="#l412"></a>
<span id="l413">    TLS_ECDHE_ECDSA_WITH_RC4_128_SHA(</span><a href="#l413"></a>
<span id="l414">            0xC007, false, &quot;TLS_ECDHE_ECDSA_WITH_RC4_128_SHA&quot;, &quot;&quot;,</span><a href="#l414"></a>
<span id="l415">            ProtocolVersion.PROTOCOLS_TO_TLS12,</span><a href="#l415"></a>
<span id="l416">            K_ECDHE_ECDSA, B_RC4_128, M_SHA, H_SHA256),</span><a href="#l416"></a>
<span id="l417">    TLS_ECDHE_RSA_WITH_RC4_128_SHA(</span><a href="#l417"></a>
<span id="l418">            0xC011, false, &quot;TLS_ECDHE_RSA_WITH_RC4_128_SHA&quot;, &quot;&quot;,</span><a href="#l418"></a>
<span id="l419">            ProtocolVersion.PROTOCOLS_TO_TLS12,</span><a href="#l419"></a>
<span id="l420">            K_ECDHE_RSA, B_RC4_128, M_SHA, H_SHA256),</span><a href="#l420"></a>
<span id="l421">    SSL_RSA_WITH_RC4_128_SHA(</span><a href="#l421"></a>
<span id="l422">            0x0005, false, &quot;SSL_RSA_WITH_RC4_128_SHA&quot;,</span><a href="#l422"></a>
<span id="l423">                           &quot;TLS_RSA_WITH_RC4_128_SHA&quot;,</span><a href="#l423"></a>
<span id="l424">            ProtocolVersion.PROTOCOLS_TO_TLS12,</span><a href="#l424"></a>
<span id="l425">            K_RSA, B_RC4_128, M_SHA, H_SHA256),</span><a href="#l425"></a>
<span id="l426">    TLS_ECDH_ECDSA_WITH_RC4_128_SHA(</span><a href="#l426"></a>
<span id="l427">            0xC002, false, &quot;TLS_ECDH_ECDSA_WITH_RC4_128_SHA&quot;, &quot;&quot;,</span><a href="#l427"></a>
<span id="l428">            ProtocolVersion.PROTOCOLS_TO_TLS12,</span><a href="#l428"></a>
<span id="l429">            K_ECDH_ECDSA, B_RC4_128, M_SHA, H_SHA256),</span><a href="#l429"></a>
<span id="l430">    TLS_ECDH_RSA_WITH_RC4_128_SHA(</span><a href="#l430"></a>
<span id="l431">            0xC00C, false, &quot;TLS_ECDH_RSA_WITH_RC4_128_SHA&quot;, &quot;&quot;,</span><a href="#l431"></a>
<span id="l432">            ProtocolVersion.PROTOCOLS_TO_TLS12,</span><a href="#l432"></a>
<span id="l433">            K_ECDH_RSA, B_RC4_128, M_SHA, H_SHA256),</span><a href="#l433"></a>
<span id="l434">    SSL_RSA_WITH_RC4_128_MD5(</span><a href="#l434"></a>
<span id="l435">            0x0004, false, &quot;SSL_RSA_WITH_RC4_128_MD5&quot;,</span><a href="#l435"></a>
<span id="l436">                           &quot;TLS_RSA_WITH_RC4_128_MD5&quot;,</span><a href="#l436"></a>
<span id="l437">            ProtocolVersion.PROTOCOLS_TO_TLS12,</span><a href="#l437"></a>
<span id="l438">            K_RSA, B_RC4_128, M_MD5, H_SHA256),</span><a href="#l438"></a>
<span id="l439">    TLS_ECDH_anon_WITH_RC4_128_SHA(</span><a href="#l439"></a>
<span id="l440">            0xC016, false, &quot;TLS_ECDH_anon_WITH_RC4_128_SHA&quot;, &quot;&quot;,</span><a href="#l440"></a>
<span id="l441">            ProtocolVersion.PROTOCOLS_TO_TLS12,</span><a href="#l441"></a>
<span id="l442">            K_ECDH_ANON, B_RC4_128, M_SHA, H_SHA256),</span><a href="#l442"></a>
<span id="l443">    SSL_DH_anon_WITH_RC4_128_MD5(</span><a href="#l443"></a>
<span id="l444">            0x0018, false, &quot;SSL_DH_anon_WITH_RC4_128_MD5&quot;,</span><a href="#l444"></a>
<span id="l445">                           &quot;TLS_DH_anon_WITH_RC4_128_MD5&quot;,</span><a href="#l445"></a>
<span id="l446">            ProtocolVersion.PROTOCOLS_TO_TLS12,</span><a href="#l446"></a>
<span id="l447">            K_DH_ANON, B_RC4_128, M_MD5, H_SHA256),</span><a href="#l447"></a>
<span id="l448"></span><a href="#l448"></a>
<span id="l449">    // Weak cipher suites obsoleted in TLS 1.2 [RFC 5246]</span><a href="#l449"></a>
<span id="l450">    SSL_RSA_WITH_DES_CBC_SHA(</span><a href="#l450"></a>
<span id="l451">            0x0009, false, &quot;SSL_RSA_WITH_DES_CBC_SHA&quot;,</span><a href="#l451"></a>
<span id="l452">                           &quot;TLS_RSA_WITH_DES_CBC_SHA&quot;,</span><a href="#l452"></a>
<span id="l453">            ProtocolVersion.PROTOCOLS_TO_11,</span><a href="#l453"></a>
<span id="l454">            K_RSA, B_DES, M_SHA, H_NONE),</span><a href="#l454"></a>
<span id="l455">    SSL_DHE_RSA_WITH_DES_CBC_SHA(</span><a href="#l455"></a>
<span id="l456">            0x0015, false, &quot;SSL_DHE_RSA_WITH_DES_CBC_SHA&quot;,</span><a href="#l456"></a>
<span id="l457">                           &quot;TLS_DHE_RSA_WITH_DES_CBC_SHA&quot;,</span><a href="#l457"></a>
<span id="l458">            ProtocolVersion.PROTOCOLS_TO_11,</span><a href="#l458"></a>
<span id="l459">            K_DHE_RSA, B_DES, M_SHA, H_NONE),</span><a href="#l459"></a>
<span id="l460">    SSL_DHE_DSS_WITH_DES_CBC_SHA(</span><a href="#l460"></a>
<span id="l461">            0x0012, false, &quot;SSL_DHE_DSS_WITH_DES_CBC_SHA&quot;,</span><a href="#l461"></a>
<span id="l462">                           &quot;TLS_DHE_DSS_WITH_DES_CBC_SHA&quot;,</span><a href="#l462"></a>
<span id="l463">            ProtocolVersion.PROTOCOLS_TO_11,</span><a href="#l463"></a>
<span id="l464">            K_DHE_DSS, B_DES, M_SHA, H_NONE),</span><a href="#l464"></a>
<span id="l465">    SSL_DH_anon_WITH_DES_CBC_SHA(</span><a href="#l465"></a>
<span id="l466">            0x001A, false, &quot;SSL_DH_anon_WITH_DES_CBC_SHA&quot;,</span><a href="#l466"></a>
<span id="l467">                           &quot;TLS_DH_anon_WITH_DES_CBC_SHA&quot;,</span><a href="#l467"></a>
<span id="l468">            ProtocolVersion.PROTOCOLS_TO_11,</span><a href="#l468"></a>
<span id="l469">            K_DH_ANON, B_DES, M_SHA, H_NONE),</span><a href="#l469"></a>
<span id="l470"></span><a href="#l470"></a>
<span id="l471">    // Weak cipher suites obsoleted in TLS 1.1  [RFC 4346]</span><a href="#l471"></a>
<span id="l472">    SSL_RSA_EXPORT_WITH_DES40_CBC_SHA(</span><a href="#l472"></a>
<span id="l473">            0x0008, false, &quot;SSL_RSA_EXPORT_WITH_DES40_CBC_SHA&quot;,</span><a href="#l473"></a>
<span id="l474">                           &quot;TLS_RSA_EXPORT_WITH_DES40_CBC_SHA&quot;,</span><a href="#l474"></a>
<span id="l475">            ProtocolVersion.PROTOCOLS_TO_10,</span><a href="#l475"></a>
<span id="l476">            K_RSA_EXPORT, B_DES_40, M_SHA, H_NONE),</span><a href="#l476"></a>
<span id="l477">    SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA(</span><a href="#l477"></a>
<span id="l478">            0x0014, false, &quot;SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA&quot;,</span><a href="#l478"></a>
<span id="l479">                           &quot;TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA&quot;,</span><a href="#l479"></a>
<span id="l480">            ProtocolVersion.PROTOCOLS_TO_10,</span><a href="#l480"></a>
<span id="l481">            K_DHE_RSA_EXPORT, B_DES_40, M_SHA, H_NONE),</span><a href="#l481"></a>
<span id="l482">    SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA(</span><a href="#l482"></a>
<span id="l483">            0x0011, false, &quot;SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA&quot;,</span><a href="#l483"></a>
<span id="l484">                           &quot;TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA&quot;,</span><a href="#l484"></a>
<span id="l485">            ProtocolVersion.PROTOCOLS_TO_10,</span><a href="#l485"></a>
<span id="l486">            K_DHE_DSS_EXPORT, B_DES_40, M_SHA, H_NONE),</span><a href="#l486"></a>
<span id="l487">    SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA(</span><a href="#l487"></a>
<span id="l488">            0x0019, false, &quot;SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA&quot;,</span><a href="#l488"></a>
<span id="l489">                           &quot;TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA&quot;,</span><a href="#l489"></a>
<span id="l490">            ProtocolVersion.PROTOCOLS_TO_10,</span><a href="#l490"></a>
<span id="l491">            K_DH_ANON_EXPORT, B_DES_40, M_SHA, H_NONE),</span><a href="#l491"></a>
<span id="l492">    SSL_RSA_EXPORT_WITH_RC4_40_MD5(</span><a href="#l492"></a>
<span id="l493">            0x0003, false, &quot;SSL_RSA_EXPORT_WITH_RC4_40_MD5&quot;,</span><a href="#l493"></a>
<span id="l494">                           &quot;TLS_RSA_EXPORT_WITH_RC4_40_MD5&quot;,</span><a href="#l494"></a>
<span id="l495">            ProtocolVersion.PROTOCOLS_TO_10,</span><a href="#l495"></a>
<span id="l496">            K_RSA_EXPORT, B_RC4_40, M_MD5, H_NONE),</span><a href="#l496"></a>
<span id="l497">    SSL_DH_anon_EXPORT_WITH_RC4_40_MD5(</span><a href="#l497"></a>
<span id="l498">            0x0017, false, &quot;SSL_DH_anon_EXPORT_WITH_RC4_40_MD5&quot;,</span><a href="#l498"></a>
<span id="l499">                           &quot;TLS_DH_anon_EXPORT_WITH_RC4_40_MD5&quot;,</span><a href="#l499"></a>
<span id="l500">            ProtocolVersion.PROTOCOLS_TO_10,</span><a href="#l500"></a>
<span id="l501">            K_DH_ANON, B_RC4_40, M_MD5, H_NONE),</span><a href="#l501"></a>
<span id="l502"></span><a href="#l502"></a>
<span id="l503">    // No traffic encryption cipher suites</span><a href="#l503"></a>
<span id="l504">    TLS_RSA_WITH_NULL_SHA256(</span><a href="#l504"></a>
<span id="l505">            0x003B, false, &quot;TLS_RSA_WITH_NULL_SHA256&quot;, &quot;&quot;,</span><a href="#l505"></a>
<span id="l506">            ProtocolVersion.PROTOCOLS_OF_12,</span><a href="#l506"></a>
<span id="l507">            K_RSA, B_NULL, M_SHA256, H_SHA256),</span><a href="#l507"></a>
<span id="l508">    TLS_ECDHE_ECDSA_WITH_NULL_SHA(</span><a href="#l508"></a>
<span id="l509">            0xC006, false, &quot;TLS_ECDHE_ECDSA_WITH_NULL_SHA&quot;, &quot;&quot;,</span><a href="#l509"></a>
<span id="l510">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l510"></a>
<span id="l511">            K_ECDHE_ECDSA, B_NULL, M_SHA, H_SHA256),</span><a href="#l511"></a>
<span id="l512">    TLS_ECDHE_RSA_WITH_NULL_SHA(</span><a href="#l512"></a>
<span id="l513">            0xC010, false, &quot;TLS_ECDHE_RSA_WITH_NULL_SHA&quot;, &quot;&quot;,</span><a href="#l513"></a>
<span id="l514">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l514"></a>
<span id="l515">            K_ECDHE_RSA, B_NULL, M_SHA, H_SHA256),</span><a href="#l515"></a>
<span id="l516">    SSL_RSA_WITH_NULL_SHA(</span><a href="#l516"></a>
<span id="l517">            0x0002, false, &quot;SSL_RSA_WITH_NULL_SHA&quot;,</span><a href="#l517"></a>
<span id="l518">                           &quot;TLS_RSA_WITH_NULL_SHA&quot;,</span><a href="#l518"></a>
<span id="l519">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l519"></a>
<span id="l520">            K_RSA, B_NULL, M_SHA, H_SHA256),</span><a href="#l520"></a>
<span id="l521">    TLS_ECDH_ECDSA_WITH_NULL_SHA(</span><a href="#l521"></a>
<span id="l522">            0xC001, false, &quot;TLS_ECDH_ECDSA_WITH_NULL_SHA&quot;, &quot;&quot;,</span><a href="#l522"></a>
<span id="l523">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l523"></a>
<span id="l524">            K_ECDH_ECDSA, B_NULL, M_SHA, H_SHA256),</span><a href="#l524"></a>
<span id="l525">    TLS_ECDH_RSA_WITH_NULL_SHA(</span><a href="#l525"></a>
<span id="l526">            0xC00B, false, &quot;TLS_ECDH_RSA_WITH_NULL_SHA&quot;, &quot;&quot;,</span><a href="#l526"></a>
<span id="l527">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l527"></a>
<span id="l528">            K_ECDH_RSA, B_NULL, M_SHA, H_SHA256),</span><a href="#l528"></a>
<span id="l529">    TLS_ECDH_anon_WITH_NULL_SHA(</span><a href="#l529"></a>
<span id="l530">            0xC015, false, &quot;TLS_ECDH_anon_WITH_NULL_SHA&quot;, &quot;&quot;,</span><a href="#l530"></a>
<span id="l531">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l531"></a>
<span id="l532">            K_ECDH_ANON, B_NULL, M_SHA, H_SHA256),</span><a href="#l532"></a>
<span id="l533">    SSL_RSA_WITH_NULL_MD5(</span><a href="#l533"></a>
<span id="l534">            0x0001, false, &quot;SSL_RSA_WITH_NULL_MD5&quot;,</span><a href="#l534"></a>
<span id="l535">                           &quot;TLS_RSA_WITH_NULL_MD5&quot;,</span><a href="#l535"></a>
<span id="l536">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l536"></a>
<span id="l537">            K_RSA, B_NULL, M_MD5, H_SHA256),</span><a href="#l537"></a>
<span id="l538"></span><a href="#l538"></a>
<span id="l539"></span><a href="#l539"></a>
<span id="l540">    // Supported Kerberos ciphersuites from RFC2712</span><a href="#l540"></a>
<span id="l541">    TLS_KRB5_WITH_3DES_EDE_CBC_SHA(</span><a href="#l541"></a>
<span id="l542">            0x001f, false, &quot;TLS_KRB5_WITH_3DES_EDE_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l542"></a>
<span id="l543">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l543"></a>
<span id="l544">            K_KRB5, B_3DES, M_SHA, H_SHA256),</span><a href="#l544"></a>
<span id="l545">    TLS_KRB5_WITH_3DES_EDE_CBC_MD5(</span><a href="#l545"></a>
<span id="l546">            0x0023, false, &quot;TLS_KRB5_WITH_3DES_EDE_CBC_MD5&quot;, &quot;&quot;,</span><a href="#l546"></a>
<span id="l547">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l547"></a>
<span id="l548">            K_KRB5, B_3DES, M_MD5, H_SHA256),</span><a href="#l548"></a>
<span id="l549">    TLS_KRB5_WITH_RC4_128_SHA(</span><a href="#l549"></a>
<span id="l550">            0x0020, false, &quot;TLS_KRB5_WITH_RC4_128_SHA&quot;, &quot;&quot;,</span><a href="#l550"></a>
<span id="l551">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l551"></a>
<span id="l552">            K_KRB5, B_RC4_128, M_SHA, H_SHA256),</span><a href="#l552"></a>
<span id="l553">    TLS_KRB5_WITH_RC4_128_MD5(</span><a href="#l553"></a>
<span id="l554">            0x0024, false, &quot;TLS_KRB5_WITH_RC4_128_MD5&quot;, &quot;&quot;,</span><a href="#l554"></a>
<span id="l555">            ProtocolVersion.PROTOCOLS_TO_12,</span><a href="#l555"></a>
<span id="l556">            K_KRB5, B_RC4_128, M_MD5, H_SHA256),</span><a href="#l556"></a>
<span id="l557">    TLS_KRB5_WITH_DES_CBC_SHA(</span><a href="#l557"></a>
<span id="l558">            0x001e, false, &quot;TLS_KRB5_WITH_DES_CBC_SHA&quot;, &quot;&quot;,</span><a href="#l558"></a>
<span id="l559">            ProtocolVersion.PROTOCOLS_TO_11,</span><a href="#l559"></a>
<span id="l560">            K_KRB5, B_DES, M_SHA, H_SHA256),</span><a href="#l560"></a>
<span id="l561">    TLS_KRB5_WITH_DES_CBC_MD5(</span><a href="#l561"></a>
<span id="l562">            0x0022, false, &quot;TLS_KRB5_WITH_DES_CBC_MD5&quot;, &quot;&quot;,</span><a href="#l562"></a>
<span id="l563">            ProtocolVersion.PROTOCOLS_TO_11,</span><a href="#l563"></a>
<span id="l564">            K_KRB5, B_DES, M_MD5, H_SHA256),</span><a href="#l564"></a>
<span id="l565">    TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA(</span><a href="#l565"></a>
<span id="l566">            0x0026, false, &quot;TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA&quot;, &quot;&quot;,</span><a href="#l566"></a>
<span id="l567">            ProtocolVersion.PROTOCOLS_TO_10,</span><a href="#l567"></a>
<span id="l568">            K_KRB5_EXPORT, B_DES_40, M_SHA, H_SHA256),</span><a href="#l568"></a>
<span id="l569">    TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5(</span><a href="#l569"></a>
<span id="l570">            0x0029, false, &quot;TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5&quot;, &quot;&quot;,</span><a href="#l570"></a>
<span id="l571">            ProtocolVersion.PROTOCOLS_TO_10,</span><a href="#l571"></a>
<span id="l572">            K_KRB5_EXPORT, B_DES_40, M_MD5, H_SHA256),</span><a href="#l572"></a>
<span id="l573">    TLS_KRB5_EXPORT_WITH_RC4_40_SHA(</span><a href="#l573"></a>
<span id="l574">            0x0028, false, &quot;TLS_KRB5_EXPORT_WITH_RC4_40_SHA&quot;, &quot;&quot;,</span><a href="#l574"></a>
<span id="l575">            ProtocolVersion.PROTOCOLS_TO_10,</span><a href="#l575"></a>
<span id="l576">            K_KRB5_EXPORT, B_RC4_40, M_SHA, H_SHA256),</span><a href="#l576"></a>
<span id="l577">    TLS_KRB5_EXPORT_WITH_RC4_40_MD5(</span><a href="#l577"></a>
<span id="l578">            0x002B, false, &quot;TLS_KRB5_EXPORT_WITH_RC4_40_MD5&quot;, &quot;&quot;,</span><a href="#l578"></a>
<span id="l579">            ProtocolVersion.PROTOCOLS_TO_10,</span><a href="#l579"></a>
<span id="l580">            K_KRB5_EXPORT, B_RC4_40, M_MD5, H_SHA256),</span><a href="#l580"></a>
<span id="l581"></span><a href="#l581"></a>
<span id="l582">    // Definition of the cipher suites that are not supported but the names</span><a href="#l582"></a>
<span id="l583">    // are known.</span><a href="#l583"></a>
<span id="l584">    TLS_CHACHA20_POLY1305_SHA256(                    // TLS 1.3</span><a href="#l584"></a>
<span id="l585">            &quot;TLS_CHACHA20_POLY1305_SHA256&quot;, 0x1303),</span><a href="#l585"></a>
<span id="l586">    TLS_AES_128_CCM_SHA256(                          // TLS 1.3</span><a href="#l586"></a>
<span id="l587">            &quot;TLS_AES_128_CCM_SHA256&quot;, 0x1304),</span><a href="#l587"></a>
<span id="l588">    TLS_AES_128_CCM_8_SHA256(                        // TLS 1.3</span><a href="#l588"></a>
<span id="l589">            &quot;TLS_AES_128_CCM_8_SHA256&quot;, 0x1305),</span><a href="#l589"></a>
<span id="l590"></span><a href="#l590"></a>
<span id="l591">    // Remaining unsupported cipher suites defined in RFC2246.</span><a href="#l591"></a>
<span id="l592">    CS_0006(&quot;SSL_RSA_EXPORT_WITH_RC2_CBC_40_MD5&quot;,           0x0006),</span><a href="#l592"></a>
<span id="l593">    CS_0007(&quot;SSL_RSA_WITH_IDEA_CBC_SHA&quot;,                    0x0007),</span><a href="#l593"></a>
<span id="l594">    CS_000B(&quot;SSL_DH_DSS_EXPORT_WITH_DES40_CBC_SHA&quot;,         0x000b),</span><a href="#l594"></a>
<span id="l595">    CS_000C(&quot;SSL_DH_DSS_WITH_DES_CBC_SHA&quot;,                  0x000c),</span><a href="#l595"></a>
<span id="l596">    CS_000D(&quot;SSL_DH_DSS_WITH_3DES_EDE_CBC_SHA&quot;,             0x000d),</span><a href="#l596"></a>
<span id="l597">    CS_000E(&quot;SSL_DH_RSA_EXPORT_WITH_DES40_CBC_SHA&quot;,         0x000e),</span><a href="#l597"></a>
<span id="l598">    CS_000F(&quot;SSL_DH_RSA_WITH_DES_CBC_SHA&quot;,                  0x000f),</span><a href="#l598"></a>
<span id="l599">    CS_0010(&quot;SSL_DH_RSA_WITH_3DES_EDE_CBC_SHA&quot;,             0x0010),</span><a href="#l599"></a>
<span id="l600"></span><a href="#l600"></a>
<span id="l601">    // SSL 3.0 Fortezza cipher suites</span><a href="#l601"></a>
<span id="l602">    CS_001C(&quot;SSL_FORTEZZA_DMS_WITH_NULL_SHA&quot;,               0x001c),</span><a href="#l602"></a>
<span id="l603">    CS_001D(&quot;SSL_FORTEZZA_DMS_WITH_FORTEZZA_CBC_SHA&quot;,       0x001d),</span><a href="#l603"></a>
<span id="l604"></span><a href="#l604"></a>
<span id="l605">    // 1024/56 bit exportable cipher suites from expired internet draft</span><a href="#l605"></a>
<span id="l606">    CS_0062(&quot;SSL_RSA_EXPORT1024_WITH_DES_CBC_SHA&quot;,          0x0062),</span><a href="#l606"></a>
<span id="l607">    CS_0063(&quot;SSL_DHE_DSS_EXPORT1024_WITH_DES_CBC_SHA&quot;,      0x0063),</span><a href="#l607"></a>
<span id="l608">    CS_0064(&quot;SSL_RSA_EXPORT1024_WITH_RC4_56_SHA&quot;,           0x0064),</span><a href="#l608"></a>
<span id="l609">    CS_0065(&quot;SSL_DHE_DSS_EXPORT1024_WITH_RC4_56_SHA&quot;,       0x0065),</span><a href="#l609"></a>
<span id="l610">    CS_0066(&quot;SSL_DHE_DSS_WITH_RC4_128_SHA&quot;,                 0x0066),</span><a href="#l610"></a>
<span id="l611"></span><a href="#l611"></a>
<span id="l612">    // Netscape old and new SSL 3.0 FIPS cipher suites</span><a href="#l612"></a>
<span id="l613">    // see http://www.mozilla.org/projects/security/pki/nss/ssl/fips-ssl-ciphersuites.html</span><a href="#l613"></a>
<span id="l614">    CS_FFE0(&quot;NETSCAPE_RSA_FIPS_WITH_3DES_EDE_CBC_SHA&quot;,      0xffe0),</span><a href="#l614"></a>
<span id="l615">    CS_FFE1(&quot;NETSCAPE_RSA_FIPS_WITH_DES_CBC_SHA&quot;,           0xffe1),</span><a href="#l615"></a>
<span id="l616">    CS_FEFE(&quot;SSL_RSA_FIPS_WITH_DES_CBC_SHA&quot;,                0xfefe),</span><a href="#l616"></a>
<span id="l617">    CS_FEFF(&quot;SSL_RSA_FIPS_WITH_3DES_EDE_CBC_SHA&quot;,           0xfeff),</span><a href="#l617"></a>
<span id="l618"></span><a href="#l618"></a>
<span id="l619">    // Unsupported Kerberos cipher suites from RFC 2712</span><a href="#l619"></a>
<span id="l620">    CS_0021(&quot;TLS_KRB5_WITH_IDEA_CBC_SHA&quot;,                   0x0021),</span><a href="#l620"></a>
<span id="l621">    CS_0025(&quot;TLS_KRB5_WITH_IDEA_CBC_MD5&quot;,                   0x0025),</span><a href="#l621"></a>
<span id="l622">    CS_0027(&quot;TLS_KRB5_EXPORT_WITH_RC2_CBC_40_SHA&quot;,          0x0027),</span><a href="#l622"></a>
<span id="l623">    CS_002A(&quot;TLS_KRB5_EXPORT_WITH_RC2_CBC_40_MD5&quot;,          0x002a),</span><a href="#l623"></a>
<span id="l624"></span><a href="#l624"></a>
<span id="l625">    // Unsupported cipher suites from RFC 4162</span><a href="#l625"></a>
<span id="l626">    CS_0096(&quot;TLS_RSA_WITH_SEED_CBC_SHA&quot;,                    0x0096),</span><a href="#l626"></a>
<span id="l627">    CS_0097(&quot;TLS_DH_DSS_WITH_SEED_CBC_SHA&quot;,                 0x0097),</span><a href="#l627"></a>
<span id="l628">    CS_0098(&quot;TLS_DH_RSA_WITH_SEED_CBC_SHA&quot;,                 0x0098),</span><a href="#l628"></a>
<span id="l629">    CS_0099(&quot;TLS_DHE_DSS_WITH_SEED_CBC_SHA&quot;,                0x0099),</span><a href="#l629"></a>
<span id="l630">    CS_009A(&quot;TLS_DHE_RSA_WITH_SEED_CBC_SHA&quot;,                0x009a),</span><a href="#l630"></a>
<span id="l631">    CS_009B(&quot;TLS_DH_anon_WITH_SEED_CBC_SHA&quot;,                0x009b),</span><a href="#l631"></a>
<span id="l632"></span><a href="#l632"></a>
<span id="l633">    // Unsupported cipher suites from RFC 4279</span><a href="#l633"></a>
<span id="l634">    CS_008A(&quot;TLS_PSK_WITH_RC4_128_SHA&quot;,                     0x008a),</span><a href="#l634"></a>
<span id="l635">    CS_008B(&quot;TLS_PSK_WITH_3DES_EDE_CBC_SHA&quot;,                0x008b),</span><a href="#l635"></a>
<span id="l636">    CS_008C(&quot;TLS_PSK_WITH_AES_128_CBC_SHA&quot;,                 0x008c),</span><a href="#l636"></a>
<span id="l637">    CS_008D(&quot;TLS_PSK_WITH_AES_256_CBC_SHA&quot;,                 0x008d),</span><a href="#l637"></a>
<span id="l638">    CS_008E(&quot;TLS_DHE_PSK_WITH_RC4_128_SHA&quot;,                 0x008e),</span><a href="#l638"></a>
<span id="l639">    CS_008F(&quot;TLS_DHE_PSK_WITH_3DES_EDE_CBC_SHA&quot;,            0x008f),</span><a href="#l639"></a>
<span id="l640">    CS_0090(&quot;TLS_DHE_PSK_WITH_AES_128_CBC_SHA&quot;,             0x0090),</span><a href="#l640"></a>
<span id="l641">    CS_0091(&quot;TLS_DHE_PSK_WITH_AES_256_CBC_SHA&quot;,             0x0091),</span><a href="#l641"></a>
<span id="l642">    CS_0092(&quot;TLS_RSA_PSK_WITH_RC4_128_SHA&quot;,                 0x0092),</span><a href="#l642"></a>
<span id="l643">    CS_0093(&quot;TLS_RSA_PSK_WITH_3DES_EDE_CBC_SHA&quot;,            0x0093),</span><a href="#l643"></a>
<span id="l644">    CS_0094(&quot;TLS_RSA_PSK_WITH_AES_128_CBC_SHA&quot;,             0x0094),</span><a href="#l644"></a>
<span id="l645">    CS_0095(&quot;TLS_RSA_PSK_WITH_AES_256_CBC_SHA&quot;,             0x0095),</span><a href="#l645"></a>
<span id="l646"></span><a href="#l646"></a>
<span id="l647">    // Unsupported cipher suites from RFC 4785</span><a href="#l647"></a>
<span id="l648">    CS_002C(&quot;TLS_PSK_WITH_NULL_SHA&quot;,                        0x002c),</span><a href="#l648"></a>
<span id="l649">    CS_002D(&quot;TLS_DHE_PSK_WITH_NULL_SHA&quot;,                    0x002d),</span><a href="#l649"></a>
<span id="l650">    CS_002E(&quot;TLS_RSA_PSK_WITH_NULL_SHA&quot;,                    0x002e),</span><a href="#l650"></a>
<span id="l651"></span><a href="#l651"></a>
<span id="l652">    // Unsupported cipher suites from RFC 5246</span><a href="#l652"></a>
<span id="l653">    CS_0030(&quot;TLS_DH_DSS_WITH_AES_128_CBC_SHA&quot;,              0x0030),</span><a href="#l653"></a>
<span id="l654">    CS_0031(&quot;TLS_DH_RSA_WITH_AES_128_CBC_SHA&quot;,              0x0031),</span><a href="#l654"></a>
<span id="l655">    CS_0036(&quot;TLS_DH_DSS_WITH_AES_256_CBC_SHA&quot;,              0x0036),</span><a href="#l655"></a>
<span id="l656">    CS_0037(&quot;TLS_DH_RSA_WITH_AES_256_CBC_SHA&quot;,              0x0037),</span><a href="#l656"></a>
<span id="l657">    CS_003E(&quot;TLS_DH_DSS_WITH_AES_128_CBC_SHA256&quot;,           0x003e),</span><a href="#l657"></a>
<span id="l658">    CS_003F(&quot;TLS_DH_RSA_WITH_AES_128_CBC_SHA256&quot;,           0x003f),</span><a href="#l658"></a>
<span id="l659">    CS_0068(&quot;TLS_DH_DSS_WITH_AES_256_CBC_SHA256&quot;,           0x0068),</span><a href="#l659"></a>
<span id="l660">    CS_0069(&quot;TLS_DH_RSA_WITH_AES_256_CBC_SHA256&quot;,           0x0069),</span><a href="#l660"></a>
<span id="l661"></span><a href="#l661"></a>
<span id="l662">    // Unsupported cipher suites from RFC 5288</span><a href="#l662"></a>
<span id="l663">    CS_00A0(&quot;TLS_DH_RSA_WITH_AES_128_GCM_SHA256&quot;,           0x00a0),</span><a href="#l663"></a>
<span id="l664">    CS_00A1(&quot;TLS_DH_RSA_WITH_AES_256_GCM_SHA384&quot;,           0x00a1),</span><a href="#l664"></a>
<span id="l665">    CS_00A4(&quot;TLS_DH_DSS_WITH_AES_128_GCM_SHA256&quot;,           0x00a4),</span><a href="#l665"></a>
<span id="l666">    CS_00A5(&quot;TLS_DH_DSS_WITH_AES_256_GCM_SHA384&quot;,           0x00a5),</span><a href="#l666"></a>
<span id="l667"></span><a href="#l667"></a>
<span id="l668">    // Unsupported cipher suites from RFC 5487</span><a href="#l668"></a>
<span id="l669">    CS_00A8(&quot;TLS_PSK_WITH_AES_128_GCM_SHA256&quot;,              0x00a8),</span><a href="#l669"></a>
<span id="l670">    CS_00A9(&quot;TLS_PSK_WITH_AES_256_GCM_SHA384&quot;,              0x00a9),</span><a href="#l670"></a>
<span id="l671">    CS_00AA(&quot;TLS_DHE_PSK_WITH_AES_128_GCM_SHA256&quot;,          0x00aa),</span><a href="#l671"></a>
<span id="l672">    CS_00AB(&quot;TLS_DHE_PSK_WITH_AES_256_GCM_SHA384&quot;,          0x00ab),</span><a href="#l672"></a>
<span id="l673">    CS_00AC(&quot;TLS_RSA_PSK_WITH_AES_128_GCM_SHA256&quot;,          0x00ac),</span><a href="#l673"></a>
<span id="l674">    CS_00AD(&quot;TLS_RSA_PSK_WITH_AES_256_GCM_SHA384&quot;,          0x00ad),</span><a href="#l674"></a>
<span id="l675">    CS_00AE(&quot;TLS_PSK_WITH_AES_128_CBC_SHA256&quot;,              0x00ae),</span><a href="#l675"></a>
<span id="l676">    CS_00AF(&quot;TLS_PSK_WITH_AES_256_CBC_SHA384&quot;,              0x00af),</span><a href="#l676"></a>
<span id="l677">    CS_00B0(&quot;TLS_PSK_WITH_NULL_SHA256&quot;,                     0x00b0),</span><a href="#l677"></a>
<span id="l678">    CS_00B1(&quot;TLS_PSK_WITH_NULL_SHA384&quot;,                     0x00b1),</span><a href="#l678"></a>
<span id="l679">    CS_00B2(&quot;TLS_DHE_PSK_WITH_AES_128_CBC_SHA256&quot;,          0x00b2),</span><a href="#l679"></a>
<span id="l680">    CS_00B3(&quot;TLS_DHE_PSK_WITH_AES_256_CBC_SHA384&quot;,          0x00b3),</span><a href="#l680"></a>
<span id="l681">    CS_00B4(&quot;TLS_DHE_PSK_WITH_NULL_SHA256&quot;,                 0x00b4),</span><a href="#l681"></a>
<span id="l682">    CS_00B5(&quot;TLS_DHE_PSK_WITH_NULL_SHA384&quot;,                 0x00b5),</span><a href="#l682"></a>
<span id="l683">    CS_00B6(&quot;TLS_RSA_PSK_WITH_AES_128_CBC_SHA256&quot;,          0x00b6),</span><a href="#l683"></a>
<span id="l684">    CS_00B7(&quot;TLS_RSA_PSK_WITH_AES_256_CBC_SHA384&quot;,          0x00b7),</span><a href="#l684"></a>
<span id="l685">    CS_00B8(&quot;TLS_RSA_PSK_WITH_NULL_SHA256&quot;,                 0x00b8),</span><a href="#l685"></a>
<span id="l686">    CS_00B9(&quot;TLS_RSA_PSK_WITH_NULL_SHA384&quot;,                 0x00b9),</span><a href="#l686"></a>
<span id="l687"></span><a href="#l687"></a>
<span id="l688">    // Unsupported cipher suites from RFC 5932</span><a href="#l688"></a>
<span id="l689">    CS_0041(&quot;TLS_RSA_WITH_CAMELLIA_128_CBC_SHA&quot;,            0x0041),</span><a href="#l689"></a>
<span id="l690">    CS_0042(&quot;TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA&quot;,         0x0042),</span><a href="#l690"></a>
<span id="l691">    CS_0043(&quot;TLS_DH_RSA_WITH_CAMELLIA_128_CBC_SHA&quot;,         0x0043),</span><a href="#l691"></a>
<span id="l692">    CS_0044(&quot;TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA&quot;,        0x0044),</span><a href="#l692"></a>
<span id="l693">    CS_0045(&quot;TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA&quot;,        0x0045),</span><a href="#l693"></a>
<span id="l694">    CS_0046(&quot;TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA&quot;,        0x0046),</span><a href="#l694"></a>
<span id="l695">    CS_0084(&quot;TLS_RSA_WITH_CAMELLIA_256_CBC_SHA&quot;,            0x0084),</span><a href="#l695"></a>
<span id="l696">    CS_0085(&quot;TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA&quot;,         0x0085),</span><a href="#l696"></a>
<span id="l697">    CS_0086(&quot;TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA&quot;,         0x0086),</span><a href="#l697"></a>
<span id="l698">    CS_0087(&quot;TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA&quot;,        0x0087),</span><a href="#l698"></a>
<span id="l699">    CS_0088(&quot;TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA&quot;,        0x0088),</span><a href="#l699"></a>
<span id="l700">    CS_0089(&quot;TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA&quot;,        0x0089),</span><a href="#l700"></a>
<span id="l701">    CS_00BA(&quot;TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256&quot;,         0x00ba),</span><a href="#l701"></a>
<span id="l702">    CS_00BB(&quot;TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256&quot;,      0x00bb),</span><a href="#l702"></a>
<span id="l703">    CS_00BC(&quot;TLS_DH_RSA_WITH_CAMELLIA_128_CBC_SHA256&quot;,      0x00bc),</span><a href="#l703"></a>
<span id="l704">    CS_00BD(&quot;TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256&quot;,     0x00bd),</span><a href="#l704"></a>
<span id="l705">    CS_00BE(&quot;TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA256&quot;,     0x00be),</span><a href="#l705"></a>
<span id="l706">    CS_00BF(&quot;TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256&quot;,     0x00bf),</span><a href="#l706"></a>
<span id="l707">    CS_00C0(&quot;TLS_RSA_WITH_CAMELLIA_256_CBC_SHA256&quot;,         0x00c0),</span><a href="#l707"></a>
<span id="l708">    CS_00C1(&quot;TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256&quot;,      0x00c1),</span><a href="#l708"></a>
<span id="l709">    CS_00C2(&quot;TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256&quot;,      0x00c2),</span><a href="#l709"></a>
<span id="l710">    CS_00C3(&quot;TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256&quot;,     0x00c3),</span><a href="#l710"></a>
<span id="l711">    CS_00C4(&quot;TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256&quot;,     0x00c4),</span><a href="#l711"></a>
<span id="l712">    CS_00C5(&quot;TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256&quot;,     0x00c5),</span><a href="#l712"></a>
<span id="l713"></span><a href="#l713"></a>
<span id="l714">    // TLS Fallback Signaling Cipher Suite Value (SCSV) RFC 7507</span><a href="#l714"></a>
<span id="l715">    CS_5600(&quot;TLS_FALLBACK_SCSV&quot;,                            0x5600),</span><a href="#l715"></a>
<span id="l716"></span><a href="#l716"></a>
<span id="l717">    // Unsupported cipher suites from RFC 5054</span><a href="#l717"></a>
<span id="l718">    CS_C01A(&quot;TLS_SRP_SHA_WITH_3DES_EDE_CBC_SHA&quot;,            0xc01a),</span><a href="#l718"></a>
<span id="l719">    CS_C01B(&quot;TLS_SRP_SHA_RSA_WITH_3DES_EDE_CBC_SHA&quot;,        0xc01b),</span><a href="#l719"></a>
<span id="l720">    CS_C01C(&quot;TLS_SRP_SHA_DSS_WITH_3DES_EDE_CBC_SHA&quot;,        0xc01c),</span><a href="#l720"></a>
<span id="l721">    CS_C01D(&quot;TLS_SRP_SHA_WITH_AES_128_CBC_SHA&quot;,             0xc01d),</span><a href="#l721"></a>
<span id="l722">    CS_C01E(&quot;TLS_SRP_SHA_RSA_WITH_AES_128_CBC_SHA&quot;,         0xc01e),</span><a href="#l722"></a>
<span id="l723">    CS_C01F(&quot;TLS_SRP_SHA_DSS_WITH_AES_128_CBC_SHA&quot;,         0xc01f),</span><a href="#l723"></a>
<span id="l724">    CS_C020(&quot;TLS_SRP_SHA_WITH_AES_256_CBC_SHA&quot;,             0xc020),</span><a href="#l724"></a>
<span id="l725">    CS_C021(&quot;TLS_SRP_SHA_RSA_WITH_AES_256_CBC_SHA&quot;,         0xc021),</span><a href="#l725"></a>
<span id="l726">    CS_C022(&quot;TLS_SRP_SHA_DSS_WITH_AES_256_CBC_SHA&quot;,         0xc022),</span><a href="#l726"></a>
<span id="l727"></span><a href="#l727"></a>
<span id="l728">    // Unsupported cipher suites from RFC 5489</span><a href="#l728"></a>
<span id="l729">    CS_C033(&quot;TLS_ECDHE_PSK_WITH_RC4_128_SHA&quot;,               0xc033),</span><a href="#l729"></a>
<span id="l730">    CS_C034(&quot;TLS_ECDHE_PSK_WITH_3DES_EDE_CBC_SHA&quot;,          0xc034),</span><a href="#l730"></a>
<span id="l731">    CS_C035(&quot;TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA&quot;,           0xc035),</span><a href="#l731"></a>
<span id="l732">    CS_C036(&quot;TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA&quot;,           0xc036),</span><a href="#l732"></a>
<span id="l733">    CS_C037(&quot;TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256&quot;,        0xc037),</span><a href="#l733"></a>
<span id="l734">    CS_C038(&quot;TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA384&quot;,        0xc038),</span><a href="#l734"></a>
<span id="l735">    CS_C039(&quot;TLS_ECDHE_PSK_WITH_NULL_SHA&quot;,                  0xc039),</span><a href="#l735"></a>
<span id="l736">    CS_C03A(&quot;TLS_ECDHE_PSK_WITH_NULL_SHA256&quot;,               0xc03a),</span><a href="#l736"></a>
<span id="l737">    CS_C03B(&quot;TLS_ECDHE_PSK_WITH_NULL_SHA384&quot;,               0xc03b),</span><a href="#l737"></a>
<span id="l738"></span><a href="#l738"></a>
<span id="l739">    // Unsupported cipher suites from RFC 6209</span><a href="#l739"></a>
<span id="l740">    CS_C03C(&quot;TLS_RSA_WITH_ARIA_128_CBC_SHA256&quot;,             0xc03c),</span><a href="#l740"></a>
<span id="l741">    CS_C03D(&quot;TLS_RSA_WITH_ARIA_256_CBC_SHA384&quot;,             0xc03d),</span><a href="#l741"></a>
<span id="l742">    CS_C03E(&quot;TLS_DH_DSS_WITH_ARIA_128_CBC_SHA256&quot;,          0xc03e),</span><a href="#l742"></a>
<span id="l743">    CS_C03F(&quot;TLS_DH_DSS_WITH_ARIA_256_CBC_SHA384&quot;,          0xc03f),</span><a href="#l743"></a>
<span id="l744">    CS_C040(&quot;TLS_DH_RSA_WITH_ARIA_128_CBC_SHA256&quot;,          0xc040),</span><a href="#l744"></a>
<span id="l745">    CS_C041(&quot;TLS_DH_RSA_WITH_ARIA_256_CBC_SHA384&quot;,          0xc041),</span><a href="#l745"></a>
<span id="l746">    CS_C042(&quot;TLS_DHE_DSS_WITH_ARIA_128_CBC_SHA256&quot;,         0xc042),</span><a href="#l746"></a>
<span id="l747">    CS_C043(&quot;TLS_DHE_DSS_WITH_ARIA_256_CBC_SHA384&quot;,         0xc043),</span><a href="#l747"></a>
<span id="l748">    CS_C044(&quot;TLS_DHE_RSA_WITH_ARIA_128_CBC_SHA256&quot;,         0xc044),</span><a href="#l748"></a>
<span id="l749">    CS_C045(&quot;TLS_DHE_RSA_WITH_ARIA_256_CBC_SHA384&quot;,         0xc045),</span><a href="#l749"></a>
<span id="l750">    CS_C046(&quot;TLS_DH_anon_WITH_ARIA_128_CBC_SHA256&quot;,         0xc046),</span><a href="#l750"></a>
<span id="l751">    CS_C047(&quot;TLS_DH_anon_WITH_ARIA_256_CBC_SHA384&quot;,         0xc047),</span><a href="#l751"></a>
<span id="l752">    CS_C048(&quot;TLS_ECDHE_ECDSA_WITH_ARIA_128_CBC_SHA256&quot;,     0xc048),</span><a href="#l752"></a>
<span id="l753">    CS_C049(&quot;TLS_ECDHE_ECDSA_WITH_ARIA_256_CBC_SHA384&quot;,     0xc049),</span><a href="#l753"></a>
<span id="l754">    CS_C04A(&quot;TLS_ECDH_ECDSA_WITH_ARIA_128_CBC_SHA256&quot;,      0xc04a),</span><a href="#l754"></a>
<span id="l755">    CS_C04B(&quot;TLS_ECDH_ECDSA_WITH_ARIA_256_CBC_SHA384&quot;,      0xc04b),</span><a href="#l755"></a>
<span id="l756">    CS_C04C(&quot;TLS_ECDHE_RSA_WITH_ARIA_128_CBC_SHA256&quot;,       0xc04c),</span><a href="#l756"></a>
<span id="l757">    CS_C04D(&quot;TLS_ECDHE_RSA_WITH_ARIA_256_CBC_SHA384&quot;,       0xc04d),</span><a href="#l757"></a>
<span id="l758">    CS_C04E(&quot;TLS_ECDH_RSA_WITH_ARIA_128_CBC_SHA256&quot;,        0xc04e),</span><a href="#l758"></a>
<span id="l759">    CS_C04F(&quot;TLS_ECDH_RSA_WITH_ARIA_256_CBC_SHA384&quot;,        0xc04f),</span><a href="#l759"></a>
<span id="l760">    CS_C050(&quot;TLS_RSA_WITH_ARIA_128_GCM_SHA256&quot;,             0xc050),</span><a href="#l760"></a>
<span id="l761">    CS_C051(&quot;TLS_RSA_WITH_ARIA_256_GCM_SHA384&quot;,             0xc051),</span><a href="#l761"></a>
<span id="l762">    CS_C052(&quot;TLS_DHE_RSA_WITH_ARIA_128_GCM_SHA256&quot;,         0xc052),</span><a href="#l762"></a>
<span id="l763">    CS_C053(&quot;TLS_DHE_RSA_WITH_ARIA_256_GCM_SHA384&quot;,         0xc053),</span><a href="#l763"></a>
<span id="l764">    CS_C054(&quot;TLS_DH_RSA_WITH_ARIA_128_GCM_SHA256&quot;,          0xc054),</span><a href="#l764"></a>
<span id="l765">    CS_C055(&quot;TLS_DH_RSA_WITH_ARIA_256_GCM_SHA384&quot;,          0xc055),</span><a href="#l765"></a>
<span id="l766">    CS_C056(&quot;TLS_DHE_DSS_WITH_ARIA_128_GCM_SHA256&quot;,         0xc056),</span><a href="#l766"></a>
<span id="l767">    CS_C057(&quot;TLS_DHE_DSS_WITH_ARIA_256_GCM_SHA384&quot;,         0xc057),</span><a href="#l767"></a>
<span id="l768">    CS_C058(&quot;TLS_DH_DSS_WITH_ARIA_128_GCM_SHA256&quot;,          0xc058),</span><a href="#l768"></a>
<span id="l769">    CS_C059(&quot;TLS_DH_DSS_WITH_ARIA_256_GCM_SHA384&quot;,          0xc059),</span><a href="#l769"></a>
<span id="l770">    CS_C05A(&quot;TLS_DH_anon_WITH_ARIA_128_GCM_SHA256&quot;,         0xc05a),</span><a href="#l770"></a>
<span id="l771">    CS_C05B(&quot;TLS_DH_anon_WITH_ARIA_256_GCM_SHA384&quot;,         0xc05b),</span><a href="#l771"></a>
<span id="l772">    CS_C05C(&quot;TLS_ECDHE_ECDSA_WITH_ARIA_128_GCM_SHA256&quot;,     0xc05c),</span><a href="#l772"></a>
<span id="l773">    CS_C05D(&quot;TLS_ECDHE_ECDSA_WITH_ARIA_256_GCM_SHA384&quot;,     0xc05d),</span><a href="#l773"></a>
<span id="l774">    CS_C05E(&quot;TLS_ECDH_ECDSA_WITH_ARIA_128_GCM_SHA256&quot;,      0xc05e),</span><a href="#l774"></a>
<span id="l775">    CS_C05F(&quot;TLS_ECDH_ECDSA_WITH_ARIA_256_GCM_SHA384&quot;,      0xc05f),</span><a href="#l775"></a>
<span id="l776">    CS_C060(&quot;TLS_ECDHE_RSA_WITH_ARIA_128_GCM_SHA256&quot;,       0xc060),</span><a href="#l776"></a>
<span id="l777">    CS_C061(&quot;TLS_ECDHE_RSA_WITH_ARIA_256_GCM_SHA384&quot;,       0xc061),</span><a href="#l777"></a>
<span id="l778">    CS_C062(&quot;TLS_ECDH_RSA_WITH_ARIA_128_GCM_SHA256&quot;,        0xc062),</span><a href="#l778"></a>
<span id="l779">    CS_C063(&quot;TLS_ECDH_RSA_WITH_ARIA_256_GCM_SHA384&quot;,        0xc063),</span><a href="#l779"></a>
<span id="l780">    CS_C064(&quot;TLS_PSK_WITH_ARIA_128_CBC_SHA256&quot;,             0xc064),</span><a href="#l780"></a>
<span id="l781">    CS_C065(&quot;TLS_PSK_WITH_ARIA_256_CBC_SHA384&quot;,             0xc065),</span><a href="#l781"></a>
<span id="l782">    CS_C066(&quot;TLS_DHE_PSK_WITH_ARIA_128_CBC_SHA256&quot;,         0xc066),</span><a href="#l782"></a>
<span id="l783">    CS_C067(&quot;TLS_DHE_PSK_WITH_ARIA_256_CBC_SHA384&quot;,         0xc067),</span><a href="#l783"></a>
<span id="l784">    CS_C068(&quot;TLS_RSA_PSK_WITH_ARIA_128_CBC_SHA256&quot;,         0xc068),</span><a href="#l784"></a>
<span id="l785">    CS_C069(&quot;TLS_RSA_PSK_WITH_ARIA_256_CBC_SHA384&quot;,         0xc069),</span><a href="#l785"></a>
<span id="l786">    CS_C06A(&quot;TLS_PSK_WITH_ARIA_128_GCM_SHA256&quot;,             0xc06a),</span><a href="#l786"></a>
<span id="l787">    CS_C06B(&quot;TLS_PSK_WITH_ARIA_256_GCM_SHA384&quot;,             0xc06b),</span><a href="#l787"></a>
<span id="l788">    CS_C06C(&quot;TLS_DHE_PSK_WITH_ARIA_128_GCM_SHA256&quot;,         0xc06c),</span><a href="#l788"></a>
<span id="l789">    CS_C06D(&quot;TLS_DHE_PSK_WITH_ARIA_256_GCM_SHA384&quot;,         0xc06d),</span><a href="#l789"></a>
<span id="l790">    CS_C06E(&quot;TLS_RSA_PSK_WITH_ARIA_128_GCM_SHA256&quot;,         0xc06e),</span><a href="#l790"></a>
<span id="l791">    CS_C06F(&quot;TLS_RSA_PSK_WITH_ARIA_256_GCM_SHA384&quot;,         0xc06f),</span><a href="#l791"></a>
<span id="l792">    CS_C070(&quot;TLS_ECDHE_PSK_WITH_ARIA_128_CBC_SHA256&quot;,       0xc070),</span><a href="#l792"></a>
<span id="l793">    CS_C071(&quot;TLS_ECDHE_PSK_WITH_ARIA_256_CBC_SHA384&quot;,       0xc071),</span><a href="#l793"></a>
<span id="l794"></span><a href="#l794"></a>
<span id="l795">    // Unsupported cipher suites from RFC 6367</span><a href="#l795"></a>
<span id="l796">    CS_C072(&quot;TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256&quot;, 0xc072),</span><a href="#l796"></a>
<span id="l797">    CS_C073(&quot;TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384&quot;, 0xc073),</span><a href="#l797"></a>
<span id="l798">    CS_C074(&quot;TLS_ECDH_ECDSA_WITH_CAMELLIA_128_CBC_SHA256&quot;,  0xc074),</span><a href="#l798"></a>
<span id="l799">    CS_C075(&quot;TLS_ECDH_ECDSA_WITH_CAMELLIA_256_CBC_SHA384&quot;,  0xc075),</span><a href="#l799"></a>
<span id="l800">    CS_C076(&quot;TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256&quot;,   0xc076),</span><a href="#l800"></a>
<span id="l801">    CS_C077(&quot;TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384&quot;,   0xc077),</span><a href="#l801"></a>
<span id="l802">    CS_C078(&quot;TLS_ECDH_RSA_WITH_CAMELLIA_128_CBC_SHA256&quot;,    0xc078),</span><a href="#l802"></a>
<span id="l803">    CS_C079(&quot;TLS_ECDH_RSA_WITH_CAMELLIA_256_CBC_SHA384&quot;,    0xc079),</span><a href="#l803"></a>
<span id="l804">    CS_C07A(&quot;TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256&quot;,         0xc07a),</span><a href="#l804"></a>
<span id="l805">    CS_C07B(&quot;TLS_RSA_WITH_CAMELLIA_256_GCM_SHA384&quot;,         0xc07b),</span><a href="#l805"></a>
<span id="l806">    CS_C07C(&quot;TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256&quot;,     0xc07c),</span><a href="#l806"></a>
<span id="l807">    CS_C07D(&quot;TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384&quot;,     0xc07d),</span><a href="#l807"></a>
<span id="l808">    CS_C07E(&quot;TLS_DH_RSA_WITH_CAMELLIA_128_GCM_SHA256&quot;,      0xc07e),</span><a href="#l808"></a>
<span id="l809">    CS_C07F(&quot;TLS_DH_RSA_WITH_CAMELLIA_256_GCM_SHA384&quot;,      0xc07f),</span><a href="#l809"></a>
<span id="l810">    CS_C080(&quot;TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256&quot;,     0xc080),</span><a href="#l810"></a>
<span id="l811">    CS_C081(&quot;TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384&quot;,     0xc081),</span><a href="#l811"></a>
<span id="l812">    CS_C082(&quot;TLS_DH_DSS_WITH_CAMELLIA_128_GCM_SHA256&quot;,      0xc082),</span><a href="#l812"></a>
<span id="l813">    CS_C083(&quot;TLS_DH_DSS_WITH_CAMELLIA_256_GCM_SHA384&quot;,      0xc083),</span><a href="#l813"></a>
<span id="l814">    CS_C084(&quot;TLS_DH_anon_WITH_CAMELLIA_128_GCM_SHA256&quot;,     0xc084),</span><a href="#l814"></a>
<span id="l815">    CS_C085(&quot;TLS_DH_anon_WITH_CAMELLIA_256_GCM_SHA384&quot;,     0xc085),</span><a href="#l815"></a>
<span id="l816">    CS_C086(&quot;TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256&quot;, 0xc086),</span><a href="#l816"></a>
<span id="l817">    CS_C087(&quot;TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384&quot;, 0xc087),</span><a href="#l817"></a>
<span id="l818">    CS_C088(&quot;TLS_ECDH_ECDSA_WITH_CAMELLIA_128_GCM_SHA256&quot;,  0xc088),</span><a href="#l818"></a>
<span id="l819">    CS_C089(&quot;TLS_ECDH_ECDSA_WITH_CAMELLIA_256_GCM_SHA384&quot;,  0xc089),</span><a href="#l819"></a>
<span id="l820">    CS_C08A(&quot;TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256&quot;,   0xc08a),</span><a href="#l820"></a>
<span id="l821">    CS_C08B(&quot;TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384&quot;,   0xc08b),</span><a href="#l821"></a>
<span id="l822">    CS_C08C(&quot;TLS_ECDH_RSA_WITH_CAMELLIA_128_GCM_SHA256&quot;,    0xc08c),</span><a href="#l822"></a>
<span id="l823">    CS_C08D(&quot;TLS_ECDH_RSA_WITH_CAMELLIA_256_GCM_SHA384&quot;,    0xc08d),</span><a href="#l823"></a>
<span id="l824">    CS_C08E(&quot;TLS_PSK_WITH_CAMELLIA_128_GCM_SHA256&quot;,         0xc08e),</span><a href="#l824"></a>
<span id="l825">    CS_C08F(&quot;TLS_PSK_WITH_CAMELLIA_256_GCM_SHA384&quot;,         0xc08f),</span><a href="#l825"></a>
<span id="l826">    CS_C090(&quot;TLS_DHE_PSK_WITH_CAMELLIA_128_GCM_SHA256&quot;,     0xc090),</span><a href="#l826"></a>
<span id="l827">    CS_C091(&quot;TLS_DHE_PSK_WITH_CAMELLIA_256_GCM_SHA384&quot;,     0xc091),</span><a href="#l827"></a>
<span id="l828">    CS_C092(&quot;TLS_RSA_PSK_WITH_CAMELLIA_128_GCM_SHA256&quot;,     0xc092),</span><a href="#l828"></a>
<span id="l829">    CS_C093(&quot;TLS_RSA_PSK_WITH_CAMELLIA_256_GCM_SHA384&quot;,     0xc093),</span><a href="#l829"></a>
<span id="l830">    CS_C094(&quot;TLS_PSK_WITH_CAMELLIA_128_CBC_SHA256&quot;,         0xc094),</span><a href="#l830"></a>
<span id="l831">    CS_C095(&quot;TLS_PSK_WITH_CAMELLIA_256_CBC_SHA384&quot;,         0xc095),</span><a href="#l831"></a>
<span id="l832">    CS_C096(&quot;TLS_DHE_PSK_WITH_CAMELLIA_128_CBC_SHA256&quot;,     0xc096),</span><a href="#l832"></a>
<span id="l833">    CS_C097(&quot;TLS_DHE_PSK_WITH_CAMELLIA_256_CBC_SHA384&quot;,     0xc097),</span><a href="#l833"></a>
<span id="l834">    CS_C098(&quot;TLS_RSA_PSK_WITH_CAMELLIA_128_CBC_SHA256&quot;,     0xc098),</span><a href="#l834"></a>
<span id="l835">    CS_C099(&quot;TLS_RSA_PSK_WITH_CAMELLIA_256_CBC_SHA384&quot;,     0xc099),</span><a href="#l835"></a>
<span id="l836">    CS_C09A(&quot;TLS_ECDHE_PSK_WITH_CAMELLIA_128_CBC_SHA256&quot;,   0xc09a),</span><a href="#l836"></a>
<span id="l837">    CS_C09B(&quot;TLS_ECDHE_PSK_WITH_CAMELLIA_256_CBC_SHA384&quot;,   0xc09b),</span><a href="#l837"></a>
<span id="l838"></span><a href="#l838"></a>
<span id="l839">    // Unsupported cipher suites from RFC 6655</span><a href="#l839"></a>
<span id="l840">    CS_C09C(&quot;TLS_RSA_WITH_AES_128_CCM&quot;,                     0xc09c),</span><a href="#l840"></a>
<span id="l841">    CS_C09D(&quot;TLS_RSA_WITH_AES_256_CCM&quot;,                     0xc09d),</span><a href="#l841"></a>
<span id="l842">    CS_C09E(&quot;TLS_DHE_RSA_WITH_AES_128_CCM&quot;,                 0xc09e),</span><a href="#l842"></a>
<span id="l843">    CS_C09F(&quot;TLS_DHE_RSA_WITH_AES_256_CCM&quot;,                 0xc09f),</span><a href="#l843"></a>
<span id="l844">    CS_C0A0(&quot;TLS_RSA_WITH_AES_128_CCM_8&quot;,                   0xc0A0),</span><a href="#l844"></a>
<span id="l845">    CS_C0A1(&quot;TLS_RSA_WITH_AES_256_CCM_8&quot;,                   0xc0A1),</span><a href="#l845"></a>
<span id="l846">    CS_C0A2(&quot;TLS_DHE_RSA_WITH_AES_128_CCM_8&quot;,               0xc0A2),</span><a href="#l846"></a>
<span id="l847">    CS_C0A3(&quot;TLS_DHE_RSA_WITH_AES_256_CCM_8&quot;,               0xc0A3),</span><a href="#l847"></a>
<span id="l848">    CS_C0A4(&quot;TLS_PSK_WITH_AES_128_CCM&quot;,                     0xc0A4),</span><a href="#l848"></a>
<span id="l849">    CS_C0A5(&quot;TLS_PSK_WITH_AES_256_CCM&quot;,                     0xc0A5),</span><a href="#l849"></a>
<span id="l850">    CS_C0A6(&quot;TLS_DHE_PSK_WITH_AES_128_CCM&quot;,                 0xc0A6),</span><a href="#l850"></a>
<span id="l851">    CS_C0A7(&quot;TLS_DHE_PSK_WITH_AES_256_CCM&quot;,                 0xc0A7),</span><a href="#l851"></a>
<span id="l852">    CS_C0A8(&quot;TLS_PSK_WITH_AES_128_CCM_8&quot;,                   0xc0A8),</span><a href="#l852"></a>
<span id="l853">    CS_C0A9(&quot;TLS_PSK_WITH_AES_256_CCM_8&quot;,                   0xc0A9),</span><a href="#l853"></a>
<span id="l854">    CS_C0AA(&quot;TLS_PSK_DHE_WITH_AES_128_CCM_8&quot;,               0xc0Aa),</span><a href="#l854"></a>
<span id="l855">    CS_C0AB(&quot;TLS_PSK_DHE_WITH_AES_256_CCM_8&quot;,               0xc0Ab),</span><a href="#l855"></a>
<span id="l856"></span><a href="#l856"></a>
<span id="l857">    // Unsupported cipher suites from RFC 7251</span><a href="#l857"></a>
<span id="l858">    CS_C0AC(&quot;TLS_ECDHE_ECDSA_WITH_AES_128_CCM&quot;,             0xc0Ac),</span><a href="#l858"></a>
<span id="l859">    CS_C0AD(&quot;TLS_ECDHE_ECDSA_WITH_AES_256_CCM&quot;,             0xc0Ad),</span><a href="#l859"></a>
<span id="l860">    CS_C0AE(&quot;TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8&quot;,           0xc0Ae),</span><a href="#l860"></a>
<span id="l861">    CS_C0AF(&quot;TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8&quot;,           0xc0Af),</span><a href="#l861"></a>
<span id="l862"></span><a href="#l862"></a>
<span id="l863">    C_NULL(&quot;SSL_NULL_WITH_NULL_NULL&quot;, 0x0000);</span><a href="#l863"></a>
<span id="l864"></span><a href="#l864"></a>
<span id="l865">    final int id;</span><a href="#l865"></a>
<span id="l866">    final boolean isDefaultEnabled;</span><a href="#l866"></a>
<span id="l867">    final String name;</span><a href="#l867"></a>
<span id="l868">    final List&lt;String&gt; aliases;</span><a href="#l868"></a>
<span id="l869">    final List&lt;ProtocolVersion&gt; supportedProtocols;</span><a href="#l869"></a>
<span id="l870">    final KeyExchange keyExchange;</span><a href="#l870"></a>
<span id="l871">    final SSLCipher bulkCipher;</span><a href="#l871"></a>
<span id="l872">    final MacAlg macAlg;</span><a href="#l872"></a>
<span id="l873">    final HashAlg hashAlg;</span><a href="#l873"></a>
<span id="l874"></span><a href="#l874"></a>
<span id="l875">    final boolean exportable;</span><a href="#l875"></a>
<span id="l876"></span><a href="#l876"></a>
<span id="l877">    // known but unsupported cipher suite</span><a href="#l877"></a>
<span id="l878">    private CipherSuite(String name, int id) {</span><a href="#l878"></a>
<span id="l879">        this(id, false, name, &quot;&quot;,</span><a href="#l879"></a>
<span id="l880">                ProtocolVersion.PROTOCOLS_EMPTY, null, null, null, null);</span><a href="#l880"></a>
<span id="l881">    }</span><a href="#l881"></a>
<span id="l882"></span><a href="#l882"></a>
<span id="l883">    // TLS 1.3 cipher suite</span><a href="#l883"></a>
<span id="l884">    private CipherSuite(int id, boolean isDefaultEnabled,</span><a href="#l884"></a>
<span id="l885">            String name, ProtocolVersion[] supportedProtocols,</span><a href="#l885"></a>
<span id="l886">            SSLCipher bulkCipher, HashAlg hashAlg) {</span><a href="#l886"></a>
<span id="l887">        this(id, isDefaultEnabled, name, &quot;&quot;,</span><a href="#l887"></a>
<span id="l888">                supportedProtocols, null, bulkCipher, M_NULL, hashAlg);</span><a href="#l888"></a>
<span id="l889">    }</span><a href="#l889"></a>
<span id="l890"></span><a href="#l890"></a>
<span id="l891">    private CipherSuite(int id, boolean isDefaultEnabled,</span><a href="#l891"></a>
<span id="l892">            String name, String aliases,</span><a href="#l892"></a>
<span id="l893">            ProtocolVersion[] supportedProtocols,</span><a href="#l893"></a>
<span id="l894">            KeyExchange keyExchange, SSLCipher cipher,</span><a href="#l894"></a>
<span id="l895">            MacAlg macAlg, HashAlg hashAlg) {</span><a href="#l895"></a>
<span id="l896">        this.id = id;</span><a href="#l896"></a>
<span id="l897">        this.isDefaultEnabled = isDefaultEnabled;</span><a href="#l897"></a>
<span id="l898">        this.name = name;</span><a href="#l898"></a>
<span id="l899">        if (!aliases.isEmpty()) {</span><a href="#l899"></a>
<span id="l900">            this.aliases = Arrays.asList(aliases.split(&quot;,&quot;));</span><a href="#l900"></a>
<span id="l901">        } else {</span><a href="#l901"></a>
<span id="l902">            this.aliases = Collections.emptyList();</span><a href="#l902"></a>
<span id="l903">        }</span><a href="#l903"></a>
<span id="l904">        this.supportedProtocols = Arrays.asList(supportedProtocols);</span><a href="#l904"></a>
<span id="l905">        this.keyExchange = keyExchange;</span><a href="#l905"></a>
<span id="l906">        this.bulkCipher = cipher;</span><a href="#l906"></a>
<span id="l907">        this.macAlg = macAlg;</span><a href="#l907"></a>
<span id="l908">        this.hashAlg = hashAlg;</span><a href="#l908"></a>
<span id="l909"></span><a href="#l909"></a>
<span id="l910">        this.exportable = (cipher == null ? false : cipher.exportable);</span><a href="#l910"></a>
<span id="l911">    }</span><a href="#l911"></a>
<span id="l912"></span><a href="#l912"></a>
<span id="l913">    static CipherSuite nameOf(String ciperSuiteName) {</span><a href="#l913"></a>
<span id="l914">        for (CipherSuite cs : CipherSuite.values()) {</span><a href="#l914"></a>
<span id="l915">            if (cs.name.equals(ciperSuiteName) ||</span><a href="#l915"></a>
<span id="l916">                    cs.aliases.contains(ciperSuiteName)) {</span><a href="#l916"></a>
<span id="l917">                return cs;</span><a href="#l917"></a>
<span id="l918">            }</span><a href="#l918"></a>
<span id="l919">        }</span><a href="#l919"></a>
<span id="l920"></span><a href="#l920"></a>
<span id="l921">        return null;</span><a href="#l921"></a>
<span id="l922">    }</span><a href="#l922"></a>
<span id="l923"></span><a href="#l923"></a>
<span id="l924">    static CipherSuite valueOf(int id) {</span><a href="#l924"></a>
<span id="l925">        for (CipherSuite cs : CipherSuite.values()) {</span><a href="#l925"></a>
<span id="l926">            if (cs.id == id) {</span><a href="#l926"></a>
<span id="l927">                return cs;</span><a href="#l927"></a>
<span id="l928">            }</span><a href="#l928"></a>
<span id="l929">        }</span><a href="#l929"></a>
<span id="l930"></span><a href="#l930"></a>
<span id="l931">        return null;</span><a href="#l931"></a>
<span id="l932">    }</span><a href="#l932"></a>
<span id="l933"></span><a href="#l933"></a>
<span id="l934">    static String nameOf(int id) {</span><a href="#l934"></a>
<span id="l935">        for (CipherSuite cs : CipherSuite.values()) {</span><a href="#l935"></a>
<span id="l936">            if (cs.id == id) {</span><a href="#l936"></a>
<span id="l937">                return cs.name;</span><a href="#l937"></a>
<span id="l938">            }</span><a href="#l938"></a>
<span id="l939">        }</span><a href="#l939"></a>
<span id="l940"></span><a href="#l940"></a>
<span id="l941">        return &quot;UNKNOWN-CIPHER-SUITE(&quot; + Utilities.byte16HexString(id) + &quot;)&quot;;</span><a href="#l941"></a>
<span id="l942">    }</span><a href="#l942"></a>
<span id="l943"></span><a href="#l943"></a>
<span id="l944">    static Collection&lt;CipherSuite&gt; allowedCipherSuites() {</span><a href="#l944"></a>
<span id="l945">        Collection&lt;CipherSuite&gt; cipherSuites = new LinkedList&lt;&gt;();</span><a href="#l945"></a>
<span id="l946">        for (CipherSuite cs : CipherSuite.values()) {</span><a href="#l946"></a>
<span id="l947">            if (!cs.supportedProtocols.isEmpty()) {</span><a href="#l947"></a>
<span id="l948">                cipherSuites.add(cs);</span><a href="#l948"></a>
<span id="l949">            } else {</span><a href="#l949"></a>
<span id="l950">                // values() is ordered, remaining cipher suites are</span><a href="#l950"></a>
<span id="l951">                // not supported.</span><a href="#l951"></a>
<span id="l952">                break;</span><a href="#l952"></a>
<span id="l953">            }</span><a href="#l953"></a>
<span id="l954">        }</span><a href="#l954"></a>
<span id="l955">        return cipherSuites;</span><a href="#l955"></a>
<span id="l956">    }</span><a href="#l956"></a>
<span id="l957"></span><a href="#l957"></a>
<span id="l958">    static Collection&lt;CipherSuite&gt; defaultCipherSuites() {</span><a href="#l958"></a>
<span id="l959">        Collection&lt;CipherSuite&gt; cipherSuites = new LinkedList&lt;&gt;();</span><a href="#l959"></a>
<span id="l960">        for (CipherSuite cs : CipherSuite.values()) {</span><a href="#l960"></a>
<span id="l961">            if (cs.isDefaultEnabled) {</span><a href="#l961"></a>
<span id="l962">                cipherSuites.add(cs);</span><a href="#l962"></a>
<span id="l963">            } else {</span><a href="#l963"></a>
<span id="l964">                // values() is ordered, remaining cipher suites are</span><a href="#l964"></a>
<span id="l965">                // not enabled.</span><a href="#l965"></a>
<span id="l966">                break;</span><a href="#l966"></a>
<span id="l967">            }</span><a href="#l967"></a>
<span id="l968">        }</span><a href="#l968"></a>
<span id="l969">        return cipherSuites;</span><a href="#l969"></a>
<span id="l970">    }</span><a href="#l970"></a>
<span id="l971"></span><a href="#l971"></a>
<span id="l972">    /**</span><a href="#l972"></a>
<span id="l973">     * Validates and converts an array of cipher suite names.</span><a href="#l973"></a>
<span id="l974">     *</span><a href="#l974"></a>
<span id="l975">     * @throws IllegalArgumentException when one or more of the ciphers named</span><a href="#l975"></a>
<span id="l976">     *         by the parameter is not supported, or when the parameter is null.</span><a href="#l976"></a>
<span id="l977">     */</span><a href="#l977"></a>
<span id="l978">    static List&lt;CipherSuite&gt; validValuesOf(String[] names) {</span><a href="#l978"></a>
<span id="l979">        if (names == null) {</span><a href="#l979"></a>
<span id="l980">            throw new IllegalArgumentException(&quot;CipherSuites cannot be null&quot;);</span><a href="#l980"></a>
<span id="l981">        }</span><a href="#l981"></a>
<span id="l982"></span><a href="#l982"></a>
<span id="l983">        List&lt;CipherSuite&gt; cipherSuites = new ArrayList&lt;&gt;(names.length);</span><a href="#l983"></a>
<span id="l984">        for (String name : names) {</span><a href="#l984"></a>
<span id="l985">            if (name == null || name.isEmpty()) {</span><a href="#l985"></a>
<span id="l986">                throw new IllegalArgumentException(</span><a href="#l986"></a>
<span id="l987">                        &quot;The specified CipherSuites array contains &quot; +</span><a href="#l987"></a>
<span id="l988">                        &quot;invalid null or empty string elements&quot;);</span><a href="#l988"></a>
<span id="l989">            }</span><a href="#l989"></a>
<span id="l990"></span><a href="#l990"></a>
<span id="l991">            boolean found = false;</span><a href="#l991"></a>
<span id="l992">            for (CipherSuite cs : CipherSuite.values()) {</span><a href="#l992"></a>
<span id="l993">                if (!cs.supportedProtocols.isEmpty()) {</span><a href="#l993"></a>
<span id="l994">                    if (cs.name.equals(name) ||</span><a href="#l994"></a>
<span id="l995">                            cs.aliases.contains(name)) {</span><a href="#l995"></a>
<span id="l996">                        cipherSuites.add(cs);</span><a href="#l996"></a>
<span id="l997">                        found = true;</span><a href="#l997"></a>
<span id="l998">                        break;</span><a href="#l998"></a>
<span id="l999">                    }</span><a href="#l999"></a>
<span id="l1000">                } else {</span><a href="#l1000"></a>
<span id="l1001">                    // values() is ordered, remaining cipher suites are</span><a href="#l1001"></a>
<span id="l1002">                    // not supported.</span><a href="#l1002"></a>
<span id="l1003">                    break;</span><a href="#l1003"></a>
<span id="l1004">                }</span><a href="#l1004"></a>
<span id="l1005">            }</span><a href="#l1005"></a>
<span id="l1006">            if (!found) {</span><a href="#l1006"></a>
<span id="l1007">                throw new IllegalArgumentException(</span><a href="#l1007"></a>
<span id="l1008">                        &quot;Unsupported CipherSuite: &quot;  + name);</span><a href="#l1008"></a>
<span id="l1009">            }</span><a href="#l1009"></a>
<span id="l1010">        }</span><a href="#l1010"></a>
<span id="l1011"></span><a href="#l1011"></a>
<span id="l1012">        return Collections.unmodifiableList(cipherSuites);</span><a href="#l1012"></a>
<span id="l1013">    }</span><a href="#l1013"></a>
<span id="l1014"></span><a href="#l1014"></a>
<span id="l1015">    static String[] namesOf(List&lt;CipherSuite&gt; cipherSuites) {</span><a href="#l1015"></a>
<span id="l1016">        String[] names = new String[cipherSuites.size()];</span><a href="#l1016"></a>
<span id="l1017">        int i = 0;</span><a href="#l1017"></a>
<span id="l1018">        for (CipherSuite cipherSuite : cipherSuites) {</span><a href="#l1018"></a>
<span id="l1019">            names[i++] = cipherSuite.name;</span><a href="#l1019"></a>
<span id="l1020">        }</span><a href="#l1020"></a>
<span id="l1021"></span><a href="#l1021"></a>
<span id="l1022">        return names;</span><a href="#l1022"></a>
<span id="l1023">    }</span><a href="#l1023"></a>
<span id="l1024"></span><a href="#l1024"></a>
<span id="l1025">    boolean isAvailable() {</span><a href="#l1025"></a>
<span id="l1026">        // Note: keyExchange is null for TLS 1.3 CipherSuites.</span><a href="#l1026"></a>
<span id="l1027">        return !supportedProtocols.isEmpty() &amp;&amp;</span><a href="#l1027"></a>
<span id="l1028">                (keyExchange == null || keyExchange.isAvailable()) &amp;&amp;</span><a href="#l1028"></a>
<span id="l1029">                bulkCipher != null &amp;&amp; bulkCipher.isAvailable();</span><a href="#l1029"></a>
<span id="l1030">    }</span><a href="#l1030"></a>
<span id="l1031"></span><a href="#l1031"></a>
<span id="l1032">    public boolean supports(ProtocolVersion protocolVersion) {</span><a href="#l1032"></a>
<span id="l1033">        return supportedProtocols.contains(protocolVersion);</span><a href="#l1033"></a>
<span id="l1034">    }</span><a href="#l1034"></a>
<span id="l1035"></span><a href="#l1035"></a>
<span id="l1036">    boolean isNegotiable() {</span><a href="#l1036"></a>
<span id="l1037">        return this != TLS_EMPTY_RENEGOTIATION_INFO_SCSV &amp;&amp; isAvailable();</span><a href="#l1037"></a>
<span id="l1038">    }</span><a href="#l1038"></a>
<span id="l1039"></span><a href="#l1039"></a>
<span id="l1040">    boolean isAnonymous() {</span><a href="#l1040"></a>
<span id="l1041">        return (keyExchange != null &amp;&amp; keyExchange.isAnonymous);</span><a href="#l1041"></a>
<span id="l1042">    }</span><a href="#l1042"></a>
<span id="l1043"></span><a href="#l1043"></a>
<span id="l1044">    // See also SSLWriteCipher.calculatePacketSize().</span><a href="#l1044"></a>
<span id="l1045">    int calculatePacketSize(int fragmentSize,</span><a href="#l1045"></a>
<span id="l1046">            ProtocolVersion protocolVersion) {</span><a href="#l1046"></a>
<span id="l1047">        int packetSize = fragmentSize;</span><a href="#l1047"></a>
<span id="l1048">        if (bulkCipher != null &amp;&amp; bulkCipher != B_NULL) {</span><a href="#l1048"></a>
<span id="l1049">            int blockSize = bulkCipher.ivSize;</span><a href="#l1049"></a>
<span id="l1050">            switch (bulkCipher.cipherType) {</span><a href="#l1050"></a>
<span id="l1051">                case BLOCK_CIPHER:</span><a href="#l1051"></a>
<span id="l1052">                    packetSize += macAlg.size;</span><a href="#l1052"></a>
<span id="l1053">                    packetSize += 1;        // 1 byte padding length field</span><a href="#l1053"></a>
<span id="l1054">                    packetSize +=           // use the minimal padding</span><a href="#l1054"></a>
<span id="l1055">                            (blockSize - (packetSize % blockSize)) % blockSize;</span><a href="#l1055"></a>
<span id="l1056">                    if (protocolVersion.useTLS11PlusSpec()) {</span><a href="#l1056"></a>
<span id="l1057">                        packetSize += blockSize;        // explicit IV</span><a href="#l1057"></a>
<span id="l1058">                    }</span><a href="#l1058"></a>
<span id="l1059"></span><a href="#l1059"></a>
<span id="l1060">                    break;</span><a href="#l1060"></a>
<span id="l1061">                case AEAD_CIPHER:</span><a href="#l1061"></a>
<span id="l1062">                    if (protocolVersion == ProtocolVersion.TLS12) {</span><a href="#l1062"></a>
<span id="l1063">                        packetSize +=</span><a href="#l1063"></a>
<span id="l1064">                                bulkCipher.ivSize - bulkCipher.fixedIvSize;</span><a href="#l1064"></a>
<span id="l1065">                    }</span><a href="#l1065"></a>
<span id="l1066">                    packetSize += bulkCipher.tagSize;</span><a href="#l1066"></a>
<span id="l1067"></span><a href="#l1067"></a>
<span id="l1068">                    break;</span><a href="#l1068"></a>
<span id="l1069">                default:    // NULL_CIPHER or STREAM_CIPHER</span><a href="#l1069"></a>
<span id="l1070">                    packetSize += macAlg.size;</span><a href="#l1070"></a>
<span id="l1071">            }</span><a href="#l1071"></a>
<span id="l1072">        }</span><a href="#l1072"></a>
<span id="l1073"></span><a href="#l1073"></a>
<span id="l1074">        return packetSize + SSLRecord.headerSize;</span><a href="#l1074"></a>
<span id="l1075">    }</span><a href="#l1075"></a>
<span id="l1076"></span><a href="#l1076"></a>
<span id="l1077">    // See also CipherBox.calculateFragmentSize().</span><a href="#l1077"></a>
<span id="l1078">    int calculateFragSize(int packetLimit,</span><a href="#l1078"></a>
<span id="l1079">            ProtocolVersion protocolVersion) {</span><a href="#l1079"></a>
<span id="l1080">        int fragSize = packetLimit - SSLRecord.headerSize;</span><a href="#l1080"></a>
<span id="l1081">        if (bulkCipher != null &amp;&amp; bulkCipher != B_NULL) {</span><a href="#l1081"></a>
<span id="l1082">            int blockSize = bulkCipher.ivSize;</span><a href="#l1082"></a>
<span id="l1083">            switch (bulkCipher.cipherType) {</span><a href="#l1083"></a>
<span id="l1084">                case BLOCK_CIPHER:</span><a href="#l1084"></a>
<span id="l1085">                    if (protocolVersion.useTLS11PlusSpec()) {</span><a href="#l1085"></a>
<span id="l1086">                        fragSize -= blockSize;          // explicit IV</span><a href="#l1086"></a>
<span id="l1087">                    }</span><a href="#l1087"></a>
<span id="l1088">                    fragSize -= (fragSize % blockSize); // cannot hold a block</span><a href="#l1088"></a>
<span id="l1089">                    // No padding for a maximum fragment.</span><a href="#l1089"></a>
<span id="l1090">                    fragSize -= 1;        // 1 byte padding length field: 0x00</span><a href="#l1090"></a>
<span id="l1091">                    fragSize -= macAlg.size;</span><a href="#l1091"></a>
<span id="l1092"></span><a href="#l1092"></a>
<span id="l1093">                    break;</span><a href="#l1093"></a>
<span id="l1094">                case AEAD_CIPHER:</span><a href="#l1094"></a>
<span id="l1095">                    fragSize -= bulkCipher.tagSize;</span><a href="#l1095"></a>
<span id="l1096">                    fragSize -= bulkCipher.ivSize - bulkCipher.fixedIvSize;</span><a href="#l1096"></a>
<span id="l1097"></span><a href="#l1097"></a>
<span id="l1098">                    break;</span><a href="#l1098"></a>
<span id="l1099">                default:    // NULL_CIPHER or STREAM_CIPHER</span><a href="#l1099"></a>
<span id="l1100">                    fragSize -= macAlg.size;</span><a href="#l1100"></a>
<span id="l1101">            }</span><a href="#l1101"></a>
<span id="l1102">        }</span><a href="#l1102"></a>
<span id="l1103"></span><a href="#l1103"></a>
<span id="l1104">        return fragSize;</span><a href="#l1104"></a>
<span id="l1105">    }</span><a href="#l1105"></a>
<span id="l1106"></span><a href="#l1106"></a>
<span id="l1107">    /**</span><a href="#l1107"></a>
<span id="l1108">     * An SSL/TLS key exchange algorithm.</span><a href="#l1108"></a>
<span id="l1109">     */</span><a href="#l1109"></a>
<span id="l1110">    static enum KeyExchange {</span><a href="#l1110"></a>
<span id="l1111">        K_NULL          (&quot;NULL&quot;,           false, true,   NAMED_GROUP_NONE),</span><a href="#l1111"></a>
<span id="l1112">        K_RSA           (&quot;RSA&quot;,            true,  false,  NAMED_GROUP_NONE),</span><a href="#l1112"></a>
<span id="l1113">        K_RSA_EXPORT    (&quot;RSA_EXPORT&quot;,     true,  false,  NAMED_GROUP_NONE),</span><a href="#l1113"></a>
<span id="l1114">        K_DH_RSA        (&quot;DH_RSA&quot;,         false, false,  NAMED_GROUP_NONE),</span><a href="#l1114"></a>
<span id="l1115">        K_DH_DSS        (&quot;DH_DSS&quot;,         false, false,  NAMED_GROUP_NONE),</span><a href="#l1115"></a>
<span id="l1116">        K_DHE_DSS       (&quot;DHE_DSS&quot;,        true,  false,  NAMED_GROUP_FFDHE),</span><a href="#l1116"></a>
<span id="l1117">        K_DHE_DSS_EXPORT(&quot;DHE_DSS_EXPORT&quot;, true,  false,  NAMED_GROUP_NONE),</span><a href="#l1117"></a>
<span id="l1118">        K_DHE_RSA       (&quot;DHE_RSA&quot;,        true,  false,  NAMED_GROUP_FFDHE),</span><a href="#l1118"></a>
<span id="l1119">        K_DHE_RSA_EXPORT(&quot;DHE_RSA_EXPORT&quot;, true,  false,  NAMED_GROUP_NONE),</span><a href="#l1119"></a>
<span id="l1120">        K_DH_ANON       (&quot;DH_anon&quot;,        true,  true,   NAMED_GROUP_FFDHE),</span><a href="#l1120"></a>
<span id="l1121">        K_DH_ANON_EXPORT(&quot;DH_anon_EXPORT&quot;, true,  true,   NAMED_GROUP_NONE),</span><a href="#l1121"></a>
<span id="l1122"></span><a href="#l1122"></a>
<span id="l1123">        K_ECDH_ECDSA    (&quot;ECDH_ECDSA&quot;,     true,  false,  NAMED_GROUP_ECDHE),</span><a href="#l1123"></a>
<span id="l1124">        K_ECDH_RSA      (&quot;ECDH_RSA&quot;,       true,  false,  NAMED_GROUP_ECDHE),</span><a href="#l1124"></a>
<span id="l1125">        K_ECDHE_ECDSA   (&quot;ECDHE_ECDSA&quot;,    true,  false,  NAMED_GROUP_ECDHE),</span><a href="#l1125"></a>
<span id="l1126">        K_ECDHE_RSA     (&quot;ECDHE_RSA&quot;,      true,  false,  NAMED_GROUP_ECDHE),</span><a href="#l1126"></a>
<span id="l1127">        K_ECDH_ANON     (&quot;ECDH_anon&quot;,      true,  true,   NAMED_GROUP_ECDHE),</span><a href="#l1127"></a>
<span id="l1128"></span><a href="#l1128"></a>
<span id="l1129">        // Kerberos cipher suites</span><a href="#l1129"></a>
<span id="l1130">        K_KRB5          (&quot;KRB5&quot;,           true,  false,  NAMED_GROUP_NONE),</span><a href="#l1130"></a>
<span id="l1131">        K_KRB5_EXPORT   (&quot;KRB5_EXPORT&quot;,    true,  false,  NAMED_GROUP_NONE),</span><a href="#l1131"></a>
<span id="l1132"></span><a href="#l1132"></a>
<span id="l1133">        // renegotiation protection request signaling cipher suite</span><a href="#l1133"></a>
<span id="l1134">        K_SCSV          (&quot;SCSV&quot;,           true,  true,   NAMED_GROUP_NONE);</span><a href="#l1134"></a>
<span id="l1135"></span><a href="#l1135"></a>
<span id="l1136">        // name of the key exchange algorithm, e.g. DHE_DSS</span><a href="#l1136"></a>
<span id="l1137">        final String name;</span><a href="#l1137"></a>
<span id="l1138">        final boolean allowed;</span><a href="#l1138"></a>
<span id="l1139">        final NamedGroupType groupType;</span><a href="#l1139"></a>
<span id="l1140">        private final boolean alwaysAvailable;</span><a href="#l1140"></a>
<span id="l1141">        private final boolean isAnonymous;</span><a href="#l1141"></a>
<span id="l1142"></span><a href="#l1142"></a>
<span id="l1143">        KeyExchange(String name, boolean allowed,</span><a href="#l1143"></a>
<span id="l1144">                boolean isAnonymous, NamedGroupType groupType) {</span><a href="#l1144"></a>
<span id="l1145">            this.name = name;</span><a href="#l1145"></a>
<span id="l1146">            if (groupType == NAMED_GROUP_ECDHE) {</span><a href="#l1146"></a>
<span id="l1147">                this.allowed = JsseJce.ALLOW_ECC;</span><a href="#l1147"></a>
<span id="l1148">            } else {</span><a href="#l1148"></a>
<span id="l1149">                this.allowed = allowed;</span><a href="#l1149"></a>
<span id="l1150">            }</span><a href="#l1150"></a>
<span id="l1151">            this.groupType = groupType;</span><a href="#l1151"></a>
<span id="l1152">            this.alwaysAvailable = allowed &amp;&amp; (!name.startsWith(&quot;EC&quot;));</span><a href="#l1152"></a>
<span id="l1153">            this.isAnonymous = isAnonymous;</span><a href="#l1153"></a>
<span id="l1154">        }</span><a href="#l1154"></a>
<span id="l1155"></span><a href="#l1155"></a>
<span id="l1156">        boolean isAvailable() {</span><a href="#l1156"></a>
<span id="l1157">            if (alwaysAvailable) {</span><a href="#l1157"></a>
<span id="l1158">                return true;</span><a href="#l1158"></a>
<span id="l1159">            }</span><a href="#l1159"></a>
<span id="l1160"></span><a href="#l1160"></a>
<span id="l1161">            if (groupType == NAMED_GROUP_ECDHE) {</span><a href="#l1161"></a>
<span id="l1162">                return (allowed &amp;&amp; JsseJce.isEcAvailable());</span><a href="#l1162"></a>
<span id="l1163">            } else if (name.startsWith(&quot;KRB&quot;)) {</span><a href="#l1163"></a>
<span id="l1164">                return (allowed &amp;&amp; JsseJce.isKerberosAvailable());</span><a href="#l1164"></a>
<span id="l1165">            } else {</span><a href="#l1165"></a>
<span id="l1166">                return allowed;</span><a href="#l1166"></a>
<span id="l1167">            }</span><a href="#l1167"></a>
<span id="l1168">        }</span><a href="#l1168"></a>
<span id="l1169"></span><a href="#l1169"></a>
<span id="l1170">        @Override</span><a href="#l1170"></a>
<span id="l1171">        public String toString() {</span><a href="#l1171"></a>
<span id="l1172">            return name;</span><a href="#l1172"></a>
<span id="l1173">        }</span><a href="#l1173"></a>
<span id="l1174">    }</span><a href="#l1174"></a>
<span id="l1175"></span><a href="#l1175"></a>
<span id="l1176">    /**</span><a href="#l1176"></a>
<span id="l1177">     * An SSL/TLS key MAC algorithm.</span><a href="#l1177"></a>
<span id="l1178">     *</span><a href="#l1178"></a>
<span id="l1179">     * Also contains a factory method to obtain an initialized MAC</span><a href="#l1179"></a>
<span id="l1180">     * for this algorithm.</span><a href="#l1180"></a>
<span id="l1181">     */</span><a href="#l1181"></a>
<span id="l1182">    static enum MacAlg {</span><a href="#l1182"></a>
<span id="l1183">        M_NULL      (&quot;NULL&quot;,     0,   0,   0),</span><a href="#l1183"></a>
<span id="l1184">        M_MD5       (&quot;MD5&quot;,     16,  64,   9),</span><a href="#l1184"></a>
<span id="l1185">        M_SHA       (&quot;SHA&quot;,     20,  64,   9),</span><a href="#l1185"></a>
<span id="l1186">        M_SHA256    (&quot;SHA256&quot;,  32,  64,   9),</span><a href="#l1186"></a>
<span id="l1187">        M_SHA384    (&quot;SHA384&quot;,  48, 128,  17);</span><a href="#l1187"></a>
<span id="l1188"></span><a href="#l1188"></a>
<span id="l1189">        // descriptive name, e.g. MD5</span><a href="#l1189"></a>
<span id="l1190">        final String name;</span><a href="#l1190"></a>
<span id="l1191"></span><a href="#l1191"></a>
<span id="l1192">        // size of the MAC value (and MAC key) in bytes</span><a href="#l1192"></a>
<span id="l1193">        final int size;</span><a href="#l1193"></a>
<span id="l1194"></span><a href="#l1194"></a>
<span id="l1195">        // block size of the underlying hash algorithm</span><a href="#l1195"></a>
<span id="l1196">        final int hashBlockSize;</span><a href="#l1196"></a>
<span id="l1197"></span><a href="#l1197"></a>
<span id="l1198">        // minimal padding size of the underlying hash algorithm</span><a href="#l1198"></a>
<span id="l1199">        final int minimalPaddingSize;</span><a href="#l1199"></a>
<span id="l1200"></span><a href="#l1200"></a>
<span id="l1201">        MacAlg(String name, int size,</span><a href="#l1201"></a>
<span id="l1202">                int hashBlockSize, int minimalPaddingSize) {</span><a href="#l1202"></a>
<span id="l1203">            this.name = name;</span><a href="#l1203"></a>
<span id="l1204">            this.size = size;</span><a href="#l1204"></a>
<span id="l1205">            this.hashBlockSize = hashBlockSize;</span><a href="#l1205"></a>
<span id="l1206">            this.minimalPaddingSize = minimalPaddingSize;</span><a href="#l1206"></a>
<span id="l1207">        }</span><a href="#l1207"></a>
<span id="l1208"></span><a href="#l1208"></a>
<span id="l1209">        @Override</span><a href="#l1209"></a>
<span id="l1210">        public String toString() {</span><a href="#l1210"></a>
<span id="l1211">            return name;</span><a href="#l1211"></a>
<span id="l1212">        }</span><a href="#l1212"></a>
<span id="l1213">    }</span><a href="#l1213"></a>
<span id="l1214"></span><a href="#l1214"></a>
<span id="l1215">    /**</span><a href="#l1215"></a>
<span id="l1216">     * The hash algorithms used for PRF (PseudoRandom Function) or HKDF.</span><a href="#l1216"></a>
<span id="l1217">     *</span><a href="#l1217"></a>
<span id="l1218">     * Note that TLS 1.1- uses a single MD5/SHA1-based PRF algorithm for</span><a href="#l1218"></a>
<span id="l1219">     * generating the necessary material.</span><a href="#l1219"></a>
<span id="l1220">     */</span><a href="#l1220"></a>
<span id="l1221">    static enum HashAlg {</span><a href="#l1221"></a>
<span id="l1222">        H_NONE      (&quot;NONE&quot;,    0,    0),</span><a href="#l1222"></a>
<span id="l1223">        H_SHA256    (&quot;SHA-256&quot;, 32,  64),</span><a href="#l1223"></a>
<span id="l1224">        H_SHA384    (&quot;SHA-384&quot;, 48, 128);</span><a href="#l1224"></a>
<span id="l1225"></span><a href="#l1225"></a>
<span id="l1226">        final String name;</span><a href="#l1226"></a>
<span id="l1227">        final int hashLength;</span><a href="#l1227"></a>
<span id="l1228">        final int blockSize;</span><a href="#l1228"></a>
<span id="l1229"></span><a href="#l1229"></a>
<span id="l1230">        HashAlg(String hashAlg, int hashLength, int blockSize) {</span><a href="#l1230"></a>
<span id="l1231">            this.name = hashAlg;</span><a href="#l1231"></a>
<span id="l1232">            this.hashLength = hashLength;</span><a href="#l1232"></a>
<span id="l1233">            this.blockSize = blockSize;</span><a href="#l1233"></a>
<span id="l1234">        }</span><a href="#l1234"></a>
<span id="l1235"></span><a href="#l1235"></a>
<span id="l1236">        @Override</span><a href="#l1236"></a>
<span id="l1237">        public String toString() {</span><a href="#l1237"></a>
<span id="l1238">            return name;</span><a href="#l1238"></a>
<span id="l1239">        }</span><a href="#l1239"></a>
<span id="l1240">    }</span><a href="#l1240"></a>
<span id="l1241">}</span><a href="#l1241"></a></pre>
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

