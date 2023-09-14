<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk6/jdk6/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk6/jdk6/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk6/jdk6/jdk/static/mercurial.js"></script>

<title>jdk6/jdk6/jdk: 9b95de10b74e test/java/lang/ClassLoader/UninitializedParent.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk6/jdk6/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk6/jdk6/jdk/shortlog/9b95de10b74e">log</a></li>
<li><a href="/jdk6/jdk6/jdk/graph/9b95de10b74e">graph</a></li>
<li><a href="/jdk6/jdk6/jdk/tags">tags</a></li>
<li><a href="/jdk6/jdk6/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk6/jdk6/jdk/rev/9b95de10b74e">changeset</a></li>
<li><a href="/jdk6/jdk6/jdk/file/9b95de10b74e/test/java/lang/ClassLoader/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk6/jdk6/jdk/file/tip/test/java/lang/ClassLoader/UninitializedParent.java">latest</a></li>
<li><a href="/jdk6/jdk6/jdk/diff/9b95de10b74e/test/java/lang/ClassLoader/UninitializedParent.java">diff</a></li>
<li><a href="/jdk6/jdk6/jdk/comparison/9b95de10b74e/test/java/lang/ClassLoader/UninitializedParent.java">comparison</a></li>
<li><a href="/jdk6/jdk6/jdk/annotate/9b95de10b74e/test/java/lang/ClassLoader/UninitializedParent.java">annotate</a></li>
<li><a href="/jdk6/jdk6/jdk/log/9b95de10b74e/test/java/lang/ClassLoader/UninitializedParent.java">file log</a></li>
<li><a href="/jdk6/jdk6/jdk/raw-file/9b95de10b74e/test/java/lang/ClassLoader/UninitializedParent.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk6/jdk6/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk6">jdk6</a> / <a href="/jdk6/jdk6">jdk6</a> / <a href="/jdk6/jdk6/jdk">jdk</a> </h2>
<h3>view test/java/lang/ClassLoader/UninitializedParent.java @ 220:9b95de10b74e</h3>

<form class="search" action="/jdk6/jdk6/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk6/jdk6/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">6636650: (cl) Resurrected ClassLoaders can still have children
Summary: Prevent classloader from resurrection
Reviewed-by: hawtin</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#118;&#97;&#108;&#101;&#114;&#105;&#101;&#112;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Mon, 24 Aug 2009 17:09:10 -0700</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"></td>
</tr>
<tr>
 <th class="author">children</th>
 <td class="author"><a href="/jdk6/jdk6/jdk/file/ffa98eed5766/test/java/lang/ClassLoader/UninitializedParent.java">ffa98eed5766</a> </td>
</tr>
</table>

<div class="overflow">
<div class="sourcefirst linewraptoggle">line wrap: <a class="linewraplink" href="javascript:toggleLinewrap()">on</a></div>
<div class="sourcefirst"> line source</div>
<pre class="sourcelines stripes4 wrap">
<span id="l1">/*</span><a href="#l1"></a>
<span id="l2"> * Copyright 2009 Sun Microsystems, Inc.  All Rights Reserved.</span><a href="#l2"></a>
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
<span id="l19"> * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,</span><a href="#l19"></a>
<span id="l20"> * CA 95054 USA or visit www.sun.com if you need additional information or</span><a href="#l20"></a>
<span id="l21"> * have any questions.</span><a href="#l21"></a>
<span id="l22"> */</span><a href="#l22"></a>
<span id="l23"></span><a href="#l23"></a>
<span id="l24">/*</span><a href="#l24"></a>
<span id="l25"> * @test</span><a href="#l25"></a>
<span id="l26"> * @bug 6636650</span><a href="#l26"></a>
<span id="l27"> * @summary Uninitialized class loaders should not be a parent of other</span><a href="#l27"></a>
<span id="l28"> *          class loaders.</span><a href="#l28"></a>
<span id="l29"> */</span><a href="#l29"></a>
<span id="l30"></span><a href="#l30"></a>
<span id="l31"></span><a href="#l31"></a>
<span id="l32">import java.net.*;</span><a href="#l32"></a>
<span id="l33"></span><a href="#l33"></a>
<span id="l34">public class UninitializedParent {</span><a href="#l34"></a>
<span id="l35">    private static ClassLoader loader;</span><a href="#l35"></a>
<span id="l36">    public static void main(String[] args) throws Exception {</span><a href="#l36"></a>
<span id="l37">        System.setSecurityManager(new SecurityManager());</span><a href="#l37"></a>
<span id="l38"></span><a href="#l38"></a>
<span id="l39">        // Create an uninitialized class loader</span><a href="#l39"></a>
<span id="l40">        try {</span><a href="#l40"></a>
<span id="l41">            new ClassLoader(null) {</span><a href="#l41"></a>
<span id="l42">                @Override</span><a href="#l42"></a>
<span id="l43">                protected void finalize() {</span><a href="#l43"></a>
<span id="l44">                    loader = this;</span><a href="#l44"></a>
<span id="l45">                }</span><a href="#l45"></a>
<span id="l46">            };</span><a href="#l46"></a>
<span id="l47">        } catch (SecurityException exc) {</span><a href="#l47"></a>
<span id="l48">            // Expected</span><a href="#l48"></a>
<span id="l49">        }</span><a href="#l49"></a>
<span id="l50">        System.gc();</span><a href="#l50"></a>
<span id="l51">        System.runFinalization();</span><a href="#l51"></a>
<span id="l52"></span><a href="#l52"></a>
<span id="l53">        // if 'loader' isn't null, need to ensure that it can't be used as</span><a href="#l53"></a>
<span id="l54">        // parent</span><a href="#l54"></a>
<span id="l55">        if (loader != null) {</span><a href="#l55"></a>
<span id="l56">            try {</span><a href="#l56"></a>
<span id="l57">                // Create a class loader with 'loader' being the parent</span><a href="#l57"></a>
<span id="l58">                URLClassLoader child = URLClassLoader.newInstance</span><a href="#l58"></a>
<span id="l59">                    (new URL[0], loader);</span><a href="#l59"></a>
<span id="l60">                throw new RuntimeException(&quot;Test Failed!&quot;);</span><a href="#l60"></a>
<span id="l61">            } catch (SecurityException se) {</span><a href="#l61"></a>
<span id="l62">                System.out.println(&quot;Test Passed: Exception thrown&quot;); </span><a href="#l62"></a>
<span id="l63">            }</span><a href="#l63"></a>
<span id="l64">        } else {</span><a href="#l64"></a>
<span id="l65">            System.out.println(&quot;Test Passed: Loader is null&quot;);</span><a href="#l65"></a>
<span id="l66">        }</span><a href="#l66"></a>
<span id="l67">    }</span><a href="#l67"></a>
<span id="l68">}</span><a href="#l68"></a></pre>
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

