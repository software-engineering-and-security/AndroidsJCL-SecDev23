<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: f60ef263eb92 test/javax/net/ssl/sanity/ciphersuites/CheckCipherSuites.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/f60ef263eb92/test/javax/net/ssl/sanity/ciphersuites/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/test/javax/net/ssl/sanity/ciphersuites/CheckCipherSuites.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/f60ef263eb92/test/javax/net/ssl/sanity/ciphersuites/CheckCipherSuites.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/f60ef263eb92/test/javax/net/ssl/sanity/ciphersuites/CheckCipherSuites.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/f60ef263eb92/test/javax/net/ssl/sanity/ciphersuites/CheckCipherSuites.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/f60ef263eb92/test/javax/net/ssl/sanity/ciphersuites/CheckCipherSuites.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/f60ef263eb92/test/javax/net/ssl/sanity/ciphersuites/CheckCipherSuites.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view test/javax/net/ssl/sanity/ciphersuites/CheckCipherSuites.java @ 14554:f60ef263eb92</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/ed0d638da09f/test/javax/net/ssl/sanity/ciphersuites/CheckCipherSuites.java">ed0d638da09f</a> </td>
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
<span id="l2"> * Copyright (c) 2002, 2019, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l26"> * @bug 4750141 4895631 8217579 8163326</span><a href="#l26"></a>
<span id="l27"> * @summary Check enabled and supported ciphersuites are correct</span><a href="#l27"></a>
<span id="l28"> * @run main/othervm -Djdk.tls.client.protocols=&quot;TLSv1.3,TLSv1.2,TLSv1.1,TLSv1,SSLv3&quot; CheckCipherSuites default</span><a href="#l28"></a>
<span id="l29"> * @run main/othervm -Djdk.tls.client.protocols=&quot;TLSv1.3,TLSv1.2,TLSv1.1,TLSv1,SSLv3&quot; CheckCipherSuites limited</span><a href="#l29"></a>
<span id="l30"> */</span><a href="#l30"></a>
<span id="l31"></span><a href="#l31"></a>
<span id="l32">import java.util.*;</span><a href="#l32"></a>
<span id="l33">import java.security.Security;</span><a href="#l33"></a>
<span id="l34">import javax.net.ssl.*;</span><a href="#l34"></a>
<span id="l35"></span><a href="#l35"></a>
<span id="l36">public class CheckCipherSuites {</span><a href="#l36"></a>
<span id="l37"></span><a href="#l37"></a>
<span id="l38">    // List of enabled cipher suites when the &quot;crypto.policy&quot; security</span><a href="#l38"></a>
<span id="l39">    // property is set to &quot;unlimited&quot; (the default value).</span><a href="#l39"></a>
<span id="l40">    private final static String[] ENABLED_DEFAULT = {</span><a href="#l40"></a>
<span id="l41">        // TLS 1.3 cipher suites</span><a href="#l41"></a>
<span id="l42">        &quot;TLS_AES_256_GCM_SHA384&quot;,</span><a href="#l42"></a>
<span id="l43">        &quot;TLS_AES_128_GCM_SHA256&quot;,</span><a href="#l43"></a>
<span id="l44"></span><a href="#l44"></a>
<span id="l45">        // Suite B compliant cipher suites</span><a href="#l45"></a>
<span id="l46">        &quot;TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384&quot;,</span><a href="#l46"></a>
<span id="l47">        &quot;TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l47"></a>
<span id="l48"></span><a href="#l48"></a>
<span id="l49">        // AES_256(GCM) - ECDHE - forward screcy</span><a href="#l49"></a>
<span id="l50">        &quot;TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384&quot;,</span><a href="#l50"></a>
<span id="l51"></span><a href="#l51"></a>
<span id="l52">        // AES_128(GCM) - ECDHE - forward screcy</span><a href="#l52"></a>
<span id="l53">        &quot;TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l53"></a>
<span id="l54"></span><a href="#l54"></a>
<span id="l55">        // AES_256(GCM) - DHE - forward screcy</span><a href="#l55"></a>
<span id="l56">        &quot;TLS_DHE_RSA_WITH_AES_256_GCM_SHA384&quot;,</span><a href="#l56"></a>
<span id="l57">        &quot;TLS_DHE_DSS_WITH_AES_256_GCM_SHA384&quot;,</span><a href="#l57"></a>
<span id="l58"></span><a href="#l58"></a>
<span id="l59">        // AES_128(GCM) - DHE - forward screcy</span><a href="#l59"></a>
<span id="l60">        &quot;TLS_DHE_RSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l60"></a>
<span id="l61">        &quot;TLS_DHE_DSS_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l61"></a>
<span id="l62"></span><a href="#l62"></a>
<span id="l63">        // AES_256(CBC) - ECDHE - forward screcy</span><a href="#l63"></a>
<span id="l64">        &quot;TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384&quot;,</span><a href="#l64"></a>
<span id="l65">        &quot;TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384&quot;,</span><a href="#l65"></a>
<span id="l66"></span><a href="#l66"></a>
<span id="l67">        // AES_256(CBC) - ECDHE - forward screcy</span><a href="#l67"></a>
<span id="l68">        &quot;TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l68"></a>
<span id="l69">        &quot;TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l69"></a>
<span id="l70"></span><a href="#l70"></a>
<span id="l71">        // AES_256(CBC) - DHE - forward screcy</span><a href="#l71"></a>
<span id="l72">        &quot;TLS_DHE_RSA_WITH_AES_256_CBC_SHA256&quot;,</span><a href="#l72"></a>
<span id="l73">        &quot;TLS_DHE_DSS_WITH_AES_256_CBC_SHA256&quot;,</span><a href="#l73"></a>
<span id="l74"></span><a href="#l74"></a>
<span id="l75">        // AES_128(CBC) - DHE - forward screcy</span><a href="#l75"></a>
<span id="l76">        &quot;TLS_DHE_RSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l76"></a>
<span id="l77">        &quot;TLS_DHE_DSS_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l77"></a>
<span id="l78"></span><a href="#l78"></a>
<span id="l79">        // AES_256(GCM) - not forward screcy</span><a href="#l79"></a>
<span id="l80">        &quot;TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384&quot;,</span><a href="#l80"></a>
<span id="l81">        &quot;TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384&quot;,</span><a href="#l81"></a>
<span id="l82"></span><a href="#l82"></a>
<span id="l83">        // AES_128(GCM) - not forward screcy</span><a href="#l83"></a>
<span id="l84">        &quot;TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l84"></a>
<span id="l85">        &quot;TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l85"></a>
<span id="l86"></span><a href="#l86"></a>
<span id="l87">        // AES_256(CBC) - not forward screcy</span><a href="#l87"></a>
<span id="l88">        &quot;TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384&quot;,</span><a href="#l88"></a>
<span id="l89">        &quot;TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384&quot;,</span><a href="#l89"></a>
<span id="l90"></span><a href="#l90"></a>
<span id="l91">        // AES_128(CBC) - not forward screcy</span><a href="#l91"></a>
<span id="l92">        &quot;TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l92"></a>
<span id="l93">        &quot;TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l93"></a>
<span id="l94"></span><a href="#l94"></a>
<span id="l95">        // AES_256(CBC) - ECDHE - using SHA</span><a href="#l95"></a>
<span id="l96">        &quot;TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA&quot;,</span><a href="#l96"></a>
<span id="l97">        &quot;TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA&quot;,</span><a href="#l97"></a>
<span id="l98"></span><a href="#l98"></a>
<span id="l99">        // AES_128(CBC) - ECDHE - using SHA</span><a href="#l99"></a>
<span id="l100">        &quot;TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l100"></a>
<span id="l101">        &quot;TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l101"></a>
<span id="l102"></span><a href="#l102"></a>
<span id="l103">        // AES_256(CBC) - DHE - using SHA</span><a href="#l103"></a>
<span id="l104">        &quot;TLS_DHE_RSA_WITH_AES_256_CBC_SHA&quot;,</span><a href="#l104"></a>
<span id="l105">        &quot;TLS_DHE_DSS_WITH_AES_256_CBC_SHA&quot;,</span><a href="#l105"></a>
<span id="l106"></span><a href="#l106"></a>
<span id="l107">        // AES_128(CBC) - DHE - using SHA</span><a href="#l107"></a>
<span id="l108">        &quot;TLS_DHE_RSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l108"></a>
<span id="l109">        &quot;TLS_DHE_DSS_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l109"></a>
<span id="l110"></span><a href="#l110"></a>
<span id="l111">        // AES_256(CBC) - using SHA, not forward screcy</span><a href="#l111"></a>
<span id="l112">        &quot;TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA&quot;,</span><a href="#l112"></a>
<span id="l113">        &quot;TLS_ECDH_RSA_WITH_AES_256_CBC_SHA&quot;,</span><a href="#l113"></a>
<span id="l114"></span><a href="#l114"></a>
<span id="l115">        // AES_128(CBC) - using SHA, not forward screcy</span><a href="#l115"></a>
<span id="l116">        &quot;TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l116"></a>
<span id="l117">        &quot;TLS_ECDH_RSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l117"></a>
<span id="l118"></span><a href="#l118"></a>
<span id="l119">        // deprecated</span><a href="#l119"></a>
<span id="l120">        &quot;TLS_RSA_WITH_AES_256_GCM_SHA384&quot;,</span><a href="#l120"></a>
<span id="l121">        &quot;TLS_RSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l121"></a>
<span id="l122">        &quot;TLS_RSA_WITH_AES_256_CBC_SHA256&quot;,</span><a href="#l122"></a>
<span id="l123">        &quot;TLS_RSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l123"></a>
<span id="l124">        &quot;TLS_RSA_WITH_AES_256_CBC_SHA&quot;,</span><a href="#l124"></a>
<span id="l125">        &quot;TLS_RSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l125"></a>
<span id="l126">        &quot;TLS_EMPTY_RENEGOTIATION_INFO_SCSV&quot;</span><a href="#l126"></a>
<span id="l127">    };</span><a href="#l127"></a>
<span id="l128"></span><a href="#l128"></a>
<span id="l129">    // List of enabled cipher suites when the &quot;crypto.policy&quot; security</span><a href="#l129"></a>
<span id="l130">    // property is set to &quot;limited&quot;.</span><a href="#l130"></a>
<span id="l131">    private final static String[] ENABLED_LIMITED = {</span><a href="#l131"></a>
<span id="l132">        &quot;TLS_AES_128_GCM_SHA256&quot;,</span><a href="#l132"></a>
<span id="l133">        &quot;TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l133"></a>
<span id="l134">        &quot;TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l134"></a>
<span id="l135">        &quot;TLS_DHE_RSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l135"></a>
<span id="l136">        &quot;TLS_DHE_DSS_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l136"></a>
<span id="l137">        &quot;TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l137"></a>
<span id="l138">        &quot;TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l138"></a>
<span id="l139">        &quot;TLS_DHE_RSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l139"></a>
<span id="l140">        &quot;TLS_DHE_DSS_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l140"></a>
<span id="l141">        &quot;TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l141"></a>
<span id="l142">        &quot;TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l142"></a>
<span id="l143">        &quot;TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l143"></a>
<span id="l144">        &quot;TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l144"></a>
<span id="l145">        &quot;TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l145"></a>
<span id="l146">        &quot;TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l146"></a>
<span id="l147">        &quot;TLS_DHE_RSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l147"></a>
<span id="l148">        &quot;TLS_DHE_DSS_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l148"></a>
<span id="l149">        &quot;TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l149"></a>
<span id="l150">        &quot;TLS_ECDH_RSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l150"></a>
<span id="l151">        &quot;TLS_RSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l151"></a>
<span id="l152">        &quot;TLS_RSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l152"></a>
<span id="l153">        &quot;TLS_RSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l153"></a>
<span id="l154">        &quot;TLS_EMPTY_RENEGOTIATION_INFO_SCSV&quot;</span><a href="#l154"></a>
<span id="l155">    };</span><a href="#l155"></a>
<span id="l156"></span><a href="#l156"></a>
<span id="l157">    // List of supported cipher suites when the &quot;crypto.policy&quot; security</span><a href="#l157"></a>
<span id="l158">    // property is set to &quot;unlimited&quot; (the default value).</span><a href="#l158"></a>
<span id="l159">    private final static String[] SUPPORTED_DEFAULT = {</span><a href="#l159"></a>
<span id="l160">         // TLS 1.3 cipher suites</span><a href="#l160"></a>
<span id="l161">        &quot;TLS_AES_256_GCM_SHA384&quot;,</span><a href="#l161"></a>
<span id="l162">        &quot;TLS_AES_128_GCM_SHA256&quot;,</span><a href="#l162"></a>
<span id="l163"></span><a href="#l163"></a>
<span id="l164">        // Suite B compliant cipher suites</span><a href="#l164"></a>
<span id="l165">        &quot;TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384&quot;,</span><a href="#l165"></a>
<span id="l166">        &quot;TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l166"></a>
<span id="l167"></span><a href="#l167"></a>
<span id="l168">        // AES_256(GCM) - ECDHE - forward screcy</span><a href="#l168"></a>
<span id="l169">        &quot;TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384&quot;,</span><a href="#l169"></a>
<span id="l170"></span><a href="#l170"></a>
<span id="l171">        // AES_128(GCM) - ECDHE - forward screcy</span><a href="#l171"></a>
<span id="l172">        &quot;TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l172"></a>
<span id="l173"></span><a href="#l173"></a>
<span id="l174">        // AES_256(GCM) - DHE - forward screcy</span><a href="#l174"></a>
<span id="l175">        &quot;TLS_DHE_RSA_WITH_AES_256_GCM_SHA384&quot;,</span><a href="#l175"></a>
<span id="l176">        &quot;TLS_DHE_DSS_WITH_AES_256_GCM_SHA384&quot;,</span><a href="#l176"></a>
<span id="l177"></span><a href="#l177"></a>
<span id="l178">        // AES_128(GCM) - DHE - forward screcy</span><a href="#l178"></a>
<span id="l179">        &quot;TLS_DHE_RSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l179"></a>
<span id="l180">        &quot;TLS_DHE_DSS_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l180"></a>
<span id="l181"></span><a href="#l181"></a>
<span id="l182">        // AES_256(CBC) - ECDHE - forward screcy</span><a href="#l182"></a>
<span id="l183">        &quot;TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384&quot;,</span><a href="#l183"></a>
<span id="l184">        &quot;TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384&quot;,</span><a href="#l184"></a>
<span id="l185"></span><a href="#l185"></a>
<span id="l186">        // AES_256(CBC) - ECDHE - forward screcy</span><a href="#l186"></a>
<span id="l187">        &quot;TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l187"></a>
<span id="l188">        &quot;TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l188"></a>
<span id="l189"></span><a href="#l189"></a>
<span id="l190">        // AES_256(CBC) - DHE - forward screcy</span><a href="#l190"></a>
<span id="l191">        &quot;TLS_DHE_RSA_WITH_AES_256_CBC_SHA256&quot;,</span><a href="#l191"></a>
<span id="l192">        &quot;TLS_DHE_DSS_WITH_AES_256_CBC_SHA256&quot;,</span><a href="#l192"></a>
<span id="l193"></span><a href="#l193"></a>
<span id="l194">        // AES_128(CBC) - DHE - forward screcy</span><a href="#l194"></a>
<span id="l195">        &quot;TLS_DHE_RSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l195"></a>
<span id="l196">        &quot;TLS_DHE_DSS_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l196"></a>
<span id="l197"></span><a href="#l197"></a>
<span id="l198">        // AES_256(GCM) - not forward screcy</span><a href="#l198"></a>
<span id="l199">        &quot;TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384&quot;,</span><a href="#l199"></a>
<span id="l200">        &quot;TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384&quot;,</span><a href="#l200"></a>
<span id="l201"></span><a href="#l201"></a>
<span id="l202">        // AES_128(GCM) - not forward screcy</span><a href="#l202"></a>
<span id="l203">        &quot;TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l203"></a>
<span id="l204">        &quot;TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l204"></a>
<span id="l205"></span><a href="#l205"></a>
<span id="l206">        // AES_256(CBC) - not forward screcy</span><a href="#l206"></a>
<span id="l207">        &quot;TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384&quot;,</span><a href="#l207"></a>
<span id="l208">        &quot;TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384&quot;,</span><a href="#l208"></a>
<span id="l209"></span><a href="#l209"></a>
<span id="l210">        // AES_128(CBC) - not forward screcy</span><a href="#l210"></a>
<span id="l211">        &quot;TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l211"></a>
<span id="l212">        &quot;TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l212"></a>
<span id="l213"></span><a href="#l213"></a>
<span id="l214">        // AES_256(CBC) - ECDHE - using SHA</span><a href="#l214"></a>
<span id="l215">        &quot;TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA&quot;,</span><a href="#l215"></a>
<span id="l216">        &quot;TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA&quot;,</span><a href="#l216"></a>
<span id="l217"></span><a href="#l217"></a>
<span id="l218">        // AES_128(CBC) - ECDHE - using SHA</span><a href="#l218"></a>
<span id="l219">        &quot;TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l219"></a>
<span id="l220">        &quot;TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l220"></a>
<span id="l221"></span><a href="#l221"></a>
<span id="l222">        // AES_256(CBC) - DHE - using SHA</span><a href="#l222"></a>
<span id="l223">        &quot;TLS_DHE_RSA_WITH_AES_256_CBC_SHA&quot;,</span><a href="#l223"></a>
<span id="l224">        &quot;TLS_DHE_DSS_WITH_AES_256_CBC_SHA&quot;,</span><a href="#l224"></a>
<span id="l225"></span><a href="#l225"></a>
<span id="l226">        // AES_128(CBC) - DHE - using SHA</span><a href="#l226"></a>
<span id="l227">        &quot;TLS_DHE_RSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l227"></a>
<span id="l228">        &quot;TLS_DHE_DSS_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l228"></a>
<span id="l229"></span><a href="#l229"></a>
<span id="l230">        // AES_256(CBC) - using SHA, not forward screcy</span><a href="#l230"></a>
<span id="l231">        &quot;TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA&quot;,</span><a href="#l231"></a>
<span id="l232">        &quot;TLS_ECDH_RSA_WITH_AES_256_CBC_SHA&quot;,</span><a href="#l232"></a>
<span id="l233"></span><a href="#l233"></a>
<span id="l234">        // AES_128(CBC) - using SHA, not forward screcy</span><a href="#l234"></a>
<span id="l235">        &quot;TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l235"></a>
<span id="l236">        &quot;TLS_ECDH_RSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l236"></a>
<span id="l237"></span><a href="#l237"></a>
<span id="l238">        // deprecated</span><a href="#l238"></a>
<span id="l239">        &quot;TLS_RSA_WITH_AES_256_GCM_SHA384&quot;,</span><a href="#l239"></a>
<span id="l240">        &quot;TLS_RSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l240"></a>
<span id="l241">        &quot;TLS_RSA_WITH_AES_256_CBC_SHA256&quot;,</span><a href="#l241"></a>
<span id="l242">        &quot;TLS_RSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l242"></a>
<span id="l243">        &quot;TLS_RSA_WITH_AES_256_CBC_SHA&quot;,</span><a href="#l243"></a>
<span id="l244">        &quot;TLS_RSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l244"></a>
<span id="l245">        &quot;TLS_EMPTY_RENEGOTIATION_INFO_SCSV&quot;</span><a href="#l245"></a>
<span id="l246">    };</span><a href="#l246"></a>
<span id="l247"></span><a href="#l247"></a>
<span id="l248">    // List of supported cipher suites when the &quot;crypto.policy&quot; security</span><a href="#l248"></a>
<span id="l249">    // property is set to &quot;limited&quot;.</span><a href="#l249"></a>
<span id="l250">    private final static String[] SUPPORTED_LIMITED = {</span><a href="#l250"></a>
<span id="l251">        &quot;TLS_AES_128_GCM_SHA256&quot;,</span><a href="#l251"></a>
<span id="l252">        &quot;TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l252"></a>
<span id="l253">        &quot;TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l253"></a>
<span id="l254">        &quot;TLS_DHE_RSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l254"></a>
<span id="l255">        &quot;TLS_DHE_DSS_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l255"></a>
<span id="l256">        &quot;TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l256"></a>
<span id="l257">        &quot;TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l257"></a>
<span id="l258">        &quot;TLS_DHE_RSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l258"></a>
<span id="l259">        &quot;TLS_DHE_DSS_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l259"></a>
<span id="l260">        &quot;TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l260"></a>
<span id="l261">        &quot;TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l261"></a>
<span id="l262">        &quot;TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l262"></a>
<span id="l263">        &quot;TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l263"></a>
<span id="l264">        &quot;TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l264"></a>
<span id="l265">        &quot;TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l265"></a>
<span id="l266">        &quot;TLS_DHE_RSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l266"></a>
<span id="l267">        &quot;TLS_DHE_DSS_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l267"></a>
<span id="l268">        &quot;TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l268"></a>
<span id="l269">        &quot;TLS_ECDH_RSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l269"></a>
<span id="l270">        &quot;TLS_RSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l270"></a>
<span id="l271">        &quot;TLS_RSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l271"></a>
<span id="l272">        &quot;TLS_RSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l272"></a>
<span id="l273">        &quot;TLS_EMPTY_RENEGOTIATION_INFO_SCSV&quot;</span><a href="#l273"></a>
<span id="l274">    };</span><a href="#l274"></a>
<span id="l275"></span><a href="#l275"></a>
<span id="l276">    private static void showSuites(String[] suites) {</span><a href="#l276"></a>
<span id="l277">        if ((suites == null) || (suites.length == 0)) {</span><a href="#l277"></a>
<span id="l278">            System.out.println(&quot;&lt;none&gt;&quot;);</span><a href="#l278"></a>
<span id="l279">        }</span><a href="#l279"></a>
<span id="l280">        for (int i = 0; i &lt; suites.length; i++) {</span><a href="#l280"></a>
<span id="l281">            System.out.println(&quot;  &quot; + suites[i]);</span><a href="#l281"></a>
<span id="l282">        }</span><a href="#l282"></a>
<span id="l283">    }</span><a href="#l283"></a>
<span id="l284"></span><a href="#l284"></a>
<span id="l285">    public static void main(String[] args) throws Exception {</span><a href="#l285"></a>
<span id="l286">        long start = System.currentTimeMillis();</span><a href="#l286"></a>
<span id="l287"></span><a href="#l287"></a>
<span id="l288">        if (args.length != 1) {</span><a href="#l288"></a>
<span id="l289">            throw new Exception(&quot;One arg required&quot;);</span><a href="#l289"></a>
<span id="l290">        }</span><a href="#l290"></a>
<span id="l291"></span><a href="#l291"></a>
<span id="l292">        String[] ENABLED;</span><a href="#l292"></a>
<span id="l293">        String[] SUPPORTED;</span><a href="#l293"></a>
<span id="l294">        if (args[0].equals(&quot;default&quot;)) {</span><a href="#l294"></a>
<span id="l295">            ENABLED = ENABLED_DEFAULT;</span><a href="#l295"></a>
<span id="l296">            SUPPORTED = SUPPORTED_DEFAULT;</span><a href="#l296"></a>
<span id="l297">        } else if (args[0].equals(&quot;limited&quot;)) {</span><a href="#l297"></a>
<span id="l298">            Security.setProperty(&quot;crypto.policy&quot;, &quot;limited&quot;);</span><a href="#l298"></a>
<span id="l299">            ENABLED = ENABLED_LIMITED;</span><a href="#l299"></a>
<span id="l300">            SUPPORTED = SUPPORTED_LIMITED;</span><a href="#l300"></a>
<span id="l301">        } else {</span><a href="#l301"></a>
<span id="l302">            throw new Exception(&quot;Illegal argument&quot;);</span><a href="#l302"></a>
<span id="l303">        }</span><a href="#l303"></a>
<span id="l304"></span><a href="#l304"></a>
<span id="l305">        SSLSocketFactory factory =</span><a href="#l305"></a>
<span id="l306">                (SSLSocketFactory)SSLSocketFactory.getDefault();</span><a href="#l306"></a>
<span id="l307">        SSLSocket socket = (SSLSocket)factory.createSocket();</span><a href="#l307"></a>
<span id="l308">        String[] enabled = socket.getEnabledCipherSuites();</span><a href="#l308"></a>
<span id="l309"></span><a href="#l309"></a>
<span id="l310">        System.out.println(&quot;Default enabled ciphersuites:&quot;);</span><a href="#l310"></a>
<span id="l311">        showSuites(enabled);</span><a href="#l311"></a>
<span id="l312"></span><a href="#l312"></a>
<span id="l313">        if (Arrays.equals(ENABLED, enabled) == false) {</span><a href="#l313"></a>
<span id="l314">            System.out.println(&quot;*** MISMATCH, should be ***&quot;);</span><a href="#l314"></a>
<span id="l315">            showSuites(ENABLED);</span><a href="#l315"></a>
<span id="l316">            throw new Exception(&quot;Enabled ciphersuite mismatch&quot;);</span><a href="#l316"></a>
<span id="l317">        }</span><a href="#l317"></a>
<span id="l318">        System.out.println(&quot;OK&quot;);</span><a href="#l318"></a>
<span id="l319">        System.out.println();</span><a href="#l319"></a>
<span id="l320"></span><a href="#l320"></a>
<span id="l321">        String[] supported = socket.getSupportedCipherSuites();</span><a href="#l321"></a>
<span id="l322">        System.out.println(&quot;Supported ciphersuites:&quot;);</span><a href="#l322"></a>
<span id="l323">        showSuites(supported);</span><a href="#l323"></a>
<span id="l324"></span><a href="#l324"></a>
<span id="l325">        if (Arrays.equals(SUPPORTED, supported) == false) {</span><a href="#l325"></a>
<span id="l326">            System.out.println(&quot;*** MISMATCH, should be ***&quot;);</span><a href="#l326"></a>
<span id="l327">            showSuites(SUPPORTED);</span><a href="#l327"></a>
<span id="l328">            throw new Exception(&quot;Supported ciphersuite mismatch&quot;);</span><a href="#l328"></a>
<span id="l329">        }</span><a href="#l329"></a>
<span id="l330">        System.out.println(&quot;OK&quot;);</span><a href="#l330"></a>
<span id="l331"></span><a href="#l331"></a>
<span id="l332">        long end = System.currentTimeMillis();</span><a href="#l332"></a>
<span id="l333">        System.out.println(&quot;Done (&quot; + (end - start) + &quot; ms).&quot;);</span><a href="#l333"></a>
<span id="l334">    }</span><a href="#l334"></a>
<span id="l335">}</span><a href="#l335"></a></pre>
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

