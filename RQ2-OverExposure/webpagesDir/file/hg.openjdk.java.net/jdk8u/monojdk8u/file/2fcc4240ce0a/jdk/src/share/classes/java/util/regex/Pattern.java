<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/monojdk8u/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/monojdk8u/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/monojdk8u/static/mercurial.js"></script>

<title>jdk8u/monojdk8u: 2fcc4240ce0a jdk/src/share/classes/java/util/regex/Pattern.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk8u/monojdk8u/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk8u/monojdk8u/shortlog/2fcc4240ce0a">log</a></li>
<li><a href="/jdk8u/monojdk8u/graph/2fcc4240ce0a">graph</a></li>
<li><a href="/jdk8u/monojdk8u/tags">tags</a></li>
<li><a href="/jdk8u/monojdk8u/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk8u/monojdk8u/rev/2fcc4240ce0a">changeset</a></li>
<li><a href="/jdk8u/monojdk8u/file/2fcc4240ce0a/jdk/src/share/classes/java/util/regex/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/monojdk8u/file/tip/jdk/src/share/classes/java/util/regex/Pattern.java">latest</a></li>
<li><a href="/jdk8u/monojdk8u/diff/2fcc4240ce0a/jdk/src/share/classes/java/util/regex/Pattern.java">diff</a></li>
<li><a href="/jdk8u/monojdk8u/comparison/2fcc4240ce0a/jdk/src/share/classes/java/util/regex/Pattern.java">comparison</a></li>
<li><a href="/jdk8u/monojdk8u/annotate/2fcc4240ce0a/jdk/src/share/classes/java/util/regex/Pattern.java">annotate</a></li>
<li><a href="/jdk8u/monojdk8u/log/2fcc4240ce0a/jdk/src/share/classes/java/util/regex/Pattern.java">file log</a></li>
<li><a href="/jdk8u/monojdk8u/raw-file/2fcc4240ce0a/jdk/src/share/classes/java/util/regex/Pattern.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/monojdk8u/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/monojdk8u">monojdk8u</a> </h2>
<h3>view jdk/src/share/classes/java/util/regex/Pattern.java @ 48805:2fcc4240ce0a</h3>

<form class="search" action="/jdk8u/monojdk8u/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk8u/monojdk8u/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8268813: Better String matching
Reviewed-by: yan, andrew
Contributed-by: Oli Gillespie &lt;ogillesp@amazon.com&gt;</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#97;&#108;&#118;&#100;&#97;&#118;&#105;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Tue, 07 Dec 2021 12:31:54 +0000</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk8u/monojdk8u/file/dd77f158dabb/jdk/src/share/classes/java/util/regex/Pattern.java">dd77f158dabb</a> </td>
</tr>
<tr>
 <th class="author">children</th>
 <td class="author"><a href="/jdk8u/monojdk8u/file/695ebff2b080/jdk/src/share/classes/java/util/regex/Pattern.java">695ebff2b080</a> </td>
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
<span id="l26">package java.util.regex;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.text.Normalizer;</span><a href="#l28"></a>
<span id="l29">import java.util.Locale;</span><a href="#l29"></a>
<span id="l30">import java.util.Iterator;</span><a href="#l30"></a>
<span id="l31">import java.util.Map;</span><a href="#l31"></a>
<span id="l32">import java.util.ArrayList;</span><a href="#l32"></a>
<span id="l33">import java.util.HashMap;</span><a href="#l33"></a>
<span id="l34">import java.util.Arrays;</span><a href="#l34"></a>
<span id="l35">import java.util.NoSuchElementException;</span><a href="#l35"></a>
<span id="l36">import java.util.Spliterator;</span><a href="#l36"></a>
<span id="l37">import java.util.Spliterators;</span><a href="#l37"></a>
<span id="l38">import java.util.function.Predicate;</span><a href="#l38"></a>
<span id="l39">import java.util.stream.Stream;</span><a href="#l39"></a>
<span id="l40">import java.util.stream.StreamSupport;</span><a href="#l40"></a>
<span id="l41"></span><a href="#l41"></a>
<span id="l42"></span><a href="#l42"></a>
<span id="l43">/**</span><a href="#l43"></a>
<span id="l44"> * A compiled representation of a regular expression.</span><a href="#l44"></a>
<span id="l45"> *</span><a href="#l45"></a>
<span id="l46"> * &lt;p&gt; A regular expression, specified as a string, must first be compiled into</span><a href="#l46"></a>
<span id="l47"> * an instance of this class.  The resulting pattern can then be used to create</span><a href="#l47"></a>
<span id="l48"> * a {@link Matcher} object that can match arbitrary {@linkplain</span><a href="#l48"></a>
<span id="l49"> * java.lang.CharSequence character sequences} against the regular</span><a href="#l49"></a>
<span id="l50"> * expression.  All of the state involved in performing a match resides in the</span><a href="#l50"></a>
<span id="l51"> * matcher, so many matchers can share the same pattern.</span><a href="#l51"></a>
<span id="l52"> *</span><a href="#l52"></a>
<span id="l53"> * &lt;p&gt; A typical invocation sequence is thus</span><a href="#l53"></a>
<span id="l54"> *</span><a href="#l54"></a>
<span id="l55"> * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l55"></a>
<span id="l56"> * Pattern p = Pattern.{@link #compile compile}(&quot;a*b&quot;);</span><a href="#l56"></a>
<span id="l57"> * Matcher m = p.{@link #matcher matcher}(&quot;aaaaab&quot;);</span><a href="#l57"></a>
<span id="l58"> * boolean b = m.{@link Matcher#matches matches}();&lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l58"></a>
<span id="l59"> *</span><a href="#l59"></a>
<span id="l60"> * &lt;p&gt; A {@link #matches matches} method is defined by this class as a</span><a href="#l60"></a>
<span id="l61"> * convenience for when a regular expression is used just once.  This method</span><a href="#l61"></a>
<span id="l62"> * compiles an expression and matches an input sequence against it in a single</span><a href="#l62"></a>
<span id="l63"> * invocation.  The statement</span><a href="#l63"></a>
<span id="l64"> *</span><a href="#l64"></a>
<span id="l65"> * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l65"></a>
<span id="l66"> * boolean b = Pattern.matches(&quot;a*b&quot;, &quot;aaaaab&quot;);&lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l66"></a>
<span id="l67"> *</span><a href="#l67"></a>
<span id="l68"> * is equivalent to the three statements above, though for repeated matches it</span><a href="#l68"></a>
<span id="l69"> * is less efficient since it does not allow the compiled pattern to be reused.</span><a href="#l69"></a>
<span id="l70"> *</span><a href="#l70"></a>
<span id="l71"> * &lt;p&gt; Instances of this class are immutable and are safe for use by multiple</span><a href="#l71"></a>
<span id="l72"> * concurrent threads.  Instances of the {@link Matcher} class are not safe for</span><a href="#l72"></a>
<span id="l73"> * such use.</span><a href="#l73"></a>
<span id="l74"> *</span><a href="#l74"></a>
<span id="l75"> *</span><a href="#l75"></a>
<span id="l76"> * &lt;h3&gt;&lt;a name=&quot;sum&quot;&gt;Summary of regular-expression constructs&lt;/a&gt;&lt;/h3&gt;</span><a href="#l76"></a>
<span id="l77"> *</span><a href="#l77"></a>
<span id="l78"> * &lt;table border=&quot;0&quot; cellpadding=&quot;1&quot; cellspacing=&quot;0&quot;</span><a href="#l78"></a>
<span id="l79"> *  summary=&quot;Regular expression constructs, and what they match&quot;&gt;</span><a href="#l79"></a>
<span id="l80"> *</span><a href="#l80"></a>
<span id="l81"> * &lt;tr align=&quot;left&quot;&gt;</span><a href="#l81"></a>
<span id="l82"> * &lt;th align=&quot;left&quot; id=&quot;construct&quot;&gt;Construct&lt;/th&gt;</span><a href="#l82"></a>
<span id="l83"> * &lt;th align=&quot;left&quot; id=&quot;matches&quot;&gt;Matches&lt;/th&gt;</span><a href="#l83"></a>
<span id="l84"> * &lt;/tr&gt;</span><a href="#l84"></a>
<span id="l85"> *</span><a href="#l85"></a>
<span id="l86"> * &lt;tr&gt;&lt;th&gt;&amp;nbsp;&lt;/th&gt;&lt;/tr&gt;</span><a href="#l86"></a>
<span id="l87"> * &lt;tr align=&quot;left&quot;&gt;&lt;th colspan=&quot;2&quot; id=&quot;characters&quot;&gt;Characters&lt;/th&gt;&lt;/tr&gt;</span><a href="#l87"></a>
<span id="l88"> *</span><a href="#l88"></a>
<span id="l89"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct characters&quot;&gt;&lt;i&gt;x&lt;/i&gt;&lt;/td&gt;</span><a href="#l89"></a>
<span id="l90"> *     &lt;td headers=&quot;matches&quot;&gt;The character &lt;i&gt;x&lt;/i&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l90"></a>
<span id="l91"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct characters&quot;&gt;&lt;tt&gt;\\&lt;/tt&gt;&lt;/td&gt;</span><a href="#l91"></a>
<span id="l92"> *     &lt;td headers=&quot;matches&quot;&gt;The backslash character&lt;/td&gt;&lt;/tr&gt;</span><a href="#l92"></a>
<span id="l93"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct characters&quot;&gt;&lt;tt&gt;\0&lt;/tt&gt;&lt;i&gt;n&lt;/i&gt;&lt;/td&gt;</span><a href="#l93"></a>
<span id="l94"> *     &lt;td headers=&quot;matches&quot;&gt;The character with octal value &lt;tt&gt;0&lt;/tt&gt;&lt;i&gt;n&lt;/i&gt;</span><a href="#l94"></a>
<span id="l95"> *         (0&amp;nbsp;&lt;tt&gt;&amp;lt;=&lt;/tt&gt;&amp;nbsp;&lt;i&gt;n&lt;/i&gt;&amp;nbsp;&lt;tt&gt;&amp;lt;=&lt;/tt&gt;&amp;nbsp;7)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l95"></a>
<span id="l96"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct characters&quot;&gt;&lt;tt&gt;\0&lt;/tt&gt;&lt;i&gt;nn&lt;/i&gt;&lt;/td&gt;</span><a href="#l96"></a>
<span id="l97"> *     &lt;td headers=&quot;matches&quot;&gt;The character with octal value &lt;tt&gt;0&lt;/tt&gt;&lt;i&gt;nn&lt;/i&gt;</span><a href="#l97"></a>
<span id="l98"> *         (0&amp;nbsp;&lt;tt&gt;&amp;lt;=&lt;/tt&gt;&amp;nbsp;&lt;i&gt;n&lt;/i&gt;&amp;nbsp;&lt;tt&gt;&amp;lt;=&lt;/tt&gt;&amp;nbsp;7)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l98"></a>
<span id="l99"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct characters&quot;&gt;&lt;tt&gt;\0&lt;/tt&gt;&lt;i&gt;mnn&lt;/i&gt;&lt;/td&gt;</span><a href="#l99"></a>
<span id="l100"> *     &lt;td headers=&quot;matches&quot;&gt;The character with octal value &lt;tt&gt;0&lt;/tt&gt;&lt;i&gt;mnn&lt;/i&gt;</span><a href="#l100"></a>
<span id="l101"> *         (0&amp;nbsp;&lt;tt&gt;&amp;lt;=&lt;/tt&gt;&amp;nbsp;&lt;i&gt;m&lt;/i&gt;&amp;nbsp;&lt;tt&gt;&amp;lt;=&lt;/tt&gt;&amp;nbsp;3,</span><a href="#l101"></a>
<span id="l102"> *         0&amp;nbsp;&lt;tt&gt;&amp;lt;=&lt;/tt&gt;&amp;nbsp;&lt;i&gt;n&lt;/i&gt;&amp;nbsp;&lt;tt&gt;&amp;lt;=&lt;/tt&gt;&amp;nbsp;7)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l102"></a>
<span id="l103"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct characters&quot;&gt;&lt;tt&gt;\x&lt;/tt&gt;&lt;i&gt;hh&lt;/i&gt;&lt;/td&gt;</span><a href="#l103"></a>
<span id="l104"> *     &lt;td headers=&quot;matches&quot;&gt;The character with hexadecimal&amp;nbsp;value&amp;nbsp;&lt;tt&gt;0x&lt;/tt&gt;&lt;i&gt;hh&lt;/i&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l104"></a>
<span id="l105"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct characters&quot;&gt;&lt;tt&gt;&amp;#92;u&lt;/tt&gt;&lt;i&gt;hhhh&lt;/i&gt;&lt;/td&gt;</span><a href="#l105"></a>
<span id="l106"> *     &lt;td headers=&quot;matches&quot;&gt;The character with hexadecimal&amp;nbsp;value&amp;nbsp;&lt;tt&gt;0x&lt;/tt&gt;&lt;i&gt;hhhh&lt;/i&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l106"></a>
<span id="l107"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct characters&quot;&gt;&lt;tt&gt;&amp;#92;x&lt;/tt&gt;&lt;i&gt;{h...h}&lt;/i&gt;&lt;/td&gt;</span><a href="#l107"></a>
<span id="l108"> *     &lt;td headers=&quot;matches&quot;&gt;The character with hexadecimal&amp;nbsp;value&amp;nbsp;&lt;tt&gt;0x&lt;/tt&gt;&lt;i&gt;h...h&lt;/i&gt;</span><a href="#l108"></a>
<span id="l109"> *         ({@link java.lang.Character#MIN_CODE_POINT Character.MIN_CODE_POINT}</span><a href="#l109"></a>
<span id="l110"> *         &amp;nbsp;&amp;lt;=&amp;nbsp;&lt;tt&gt;0x&lt;/tt&gt;&lt;i&gt;h...h&lt;/i&gt;&amp;nbsp;&amp;lt;=&amp;nbsp;</span><a href="#l110"></a>
<span id="l111"> *          {@link java.lang.Character#MAX_CODE_POINT Character.MAX_CODE_POINT})&lt;/td&gt;&lt;/tr&gt;</span><a href="#l111"></a>
<span id="l112"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;matches&quot;&gt;&lt;tt&gt;\t&lt;/tt&gt;&lt;/td&gt;</span><a href="#l112"></a>
<span id="l113"> *     &lt;td headers=&quot;matches&quot;&gt;The tab character (&lt;tt&gt;'&amp;#92;u0009'&lt;/tt&gt;)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l113"></a>
<span id="l114"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct characters&quot;&gt;&lt;tt&gt;\n&lt;/tt&gt;&lt;/td&gt;</span><a href="#l114"></a>
<span id="l115"> *     &lt;td headers=&quot;matches&quot;&gt;The newline (line feed) character (&lt;tt&gt;'&amp;#92;u000A'&lt;/tt&gt;)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l115"></a>
<span id="l116"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct characters&quot;&gt;&lt;tt&gt;\r&lt;/tt&gt;&lt;/td&gt;</span><a href="#l116"></a>
<span id="l117"> *     &lt;td headers=&quot;matches&quot;&gt;The carriage-return character (&lt;tt&gt;'&amp;#92;u000D'&lt;/tt&gt;)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l117"></a>
<span id="l118"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct characters&quot;&gt;&lt;tt&gt;\f&lt;/tt&gt;&lt;/td&gt;</span><a href="#l118"></a>
<span id="l119"> *     &lt;td headers=&quot;matches&quot;&gt;The form-feed character (&lt;tt&gt;'&amp;#92;u000C'&lt;/tt&gt;)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l119"></a>
<span id="l120"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct characters&quot;&gt;&lt;tt&gt;\a&lt;/tt&gt;&lt;/td&gt;</span><a href="#l120"></a>
<span id="l121"> *     &lt;td headers=&quot;matches&quot;&gt;The alert (bell) character (&lt;tt&gt;'&amp;#92;u0007'&lt;/tt&gt;)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l121"></a>
<span id="l122"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct characters&quot;&gt;&lt;tt&gt;\e&lt;/tt&gt;&lt;/td&gt;</span><a href="#l122"></a>
<span id="l123"> *     &lt;td headers=&quot;matches&quot;&gt;The escape character (&lt;tt&gt;'&amp;#92;u001B'&lt;/tt&gt;)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l123"></a>
<span id="l124"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct characters&quot;&gt;&lt;tt&gt;\c&lt;/tt&gt;&lt;i&gt;x&lt;/i&gt;&lt;/td&gt;</span><a href="#l124"></a>
<span id="l125"> *     &lt;td headers=&quot;matches&quot;&gt;The control character corresponding to &lt;i&gt;x&lt;/i&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l125"></a>
<span id="l126"> *</span><a href="#l126"></a>
<span id="l127"> * &lt;tr&gt;&lt;th&gt;&amp;nbsp;&lt;/th&gt;&lt;/tr&gt;</span><a href="#l127"></a>
<span id="l128"> * &lt;tr align=&quot;left&quot;&gt;&lt;th colspan=&quot;2&quot; id=&quot;classes&quot;&gt;Character classes&lt;/th&gt;&lt;/tr&gt;</span><a href="#l128"></a>
<span id="l129"> *</span><a href="#l129"></a>
<span id="l130"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct classes&quot;&gt;{@code [abc]}&lt;/td&gt;</span><a href="#l130"></a>
<span id="l131"> *     &lt;td headers=&quot;matches&quot;&gt;{@code a}, {@code b}, or {@code c} (simple class)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l131"></a>
<span id="l132"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct classes&quot;&gt;{@code [^abc]}&lt;/td&gt;</span><a href="#l132"></a>
<span id="l133"> *     &lt;td headers=&quot;matches&quot;&gt;Any character except {@code a}, {@code b}, or {@code c} (negation)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l133"></a>
<span id="l134"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct classes&quot;&gt;{@code [a-zA-Z]}&lt;/td&gt;</span><a href="#l134"></a>
<span id="l135"> *     &lt;td headers=&quot;matches&quot;&gt;{@code a} through {@code z}</span><a href="#l135"></a>
<span id="l136"> *         or {@code A} through {@code Z}, inclusive (range)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l136"></a>
<span id="l137"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct classes&quot;&gt;{@code [a-d[m-p]]}&lt;/td&gt;</span><a href="#l137"></a>
<span id="l138"> *     &lt;td headers=&quot;matches&quot;&gt;{@code a} through {@code d},</span><a href="#l138"></a>
<span id="l139"> *      or {@code m} through {@code p}: {@code [a-dm-p]} (union)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l139"></a>
<span id="l140"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct classes&quot;&gt;{@code [a-z&amp;&amp;[def]]}&lt;/td&gt;</span><a href="#l140"></a>
<span id="l141"> *     &lt;td headers=&quot;matches&quot;&gt;{@code d}, {@code e}, or {@code f} (intersection)&lt;/tr&gt;</span><a href="#l141"></a>
<span id="l142"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct classes&quot;&gt;{@code [a-z&amp;&amp;[^bc]]}&lt;/td&gt;</span><a href="#l142"></a>
<span id="l143"> *     &lt;td headers=&quot;matches&quot;&gt;{@code a} through {@code z},</span><a href="#l143"></a>
<span id="l144"> *         except for {@code b} and {@code c}: {@code [ad-z]} (subtraction)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l144"></a>
<span id="l145"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct classes&quot;&gt;{@code [a-z&amp;&amp;[^m-p]]}&lt;/td&gt;</span><a href="#l145"></a>
<span id="l146"> *     &lt;td headers=&quot;matches&quot;&gt;{@code a} through {@code z},</span><a href="#l146"></a>
<span id="l147"> *          and not {@code m} through {@code p}: {@code [a-lq-z]}(subtraction)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l147"></a>
<span id="l148"> * &lt;tr&gt;&lt;th&gt;&amp;nbsp;&lt;/th&gt;&lt;/tr&gt;</span><a href="#l148"></a>
<span id="l149"> *</span><a href="#l149"></a>
<span id="l150"> * &lt;tr align=&quot;left&quot;&gt;&lt;th colspan=&quot;2&quot; id=&quot;predef&quot;&gt;Predefined character classes&lt;/th&gt;&lt;/tr&gt;</span><a href="#l150"></a>
<span id="l151"> *</span><a href="#l151"></a>
<span id="l152"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct predef&quot;&gt;&lt;tt&gt;.&lt;/tt&gt;&lt;/td&gt;</span><a href="#l152"></a>
<span id="l153"> *     &lt;td headers=&quot;matches&quot;&gt;Any character (may or may not match &lt;a href=&quot;#lt&quot;&gt;line terminators&lt;/a&gt;)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l153"></a>
<span id="l154"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct predef&quot;&gt;&lt;tt&gt;\d&lt;/tt&gt;&lt;/td&gt;</span><a href="#l154"></a>
<span id="l155"> *     &lt;td headers=&quot;matches&quot;&gt;A digit: &lt;tt&gt;[0-9]&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l155"></a>
<span id="l156"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct predef&quot;&gt;&lt;tt&gt;\D&lt;/tt&gt;&lt;/td&gt;</span><a href="#l156"></a>
<span id="l157"> *     &lt;td headers=&quot;matches&quot;&gt;A non-digit: &lt;tt&gt;[^0-9]&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l157"></a>
<span id="l158"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct predef&quot;&gt;&lt;tt&gt;\h&lt;/tt&gt;&lt;/td&gt;</span><a href="#l158"></a>
<span id="l159"> *     &lt;td headers=&quot;matches&quot;&gt;A horizontal whitespace character:</span><a href="#l159"></a>
<span id="l160"> *     &lt;tt&gt;[ \t\xA0&amp;#92;u1680&amp;#92;u180e&amp;#92;u2000-&amp;#92;u200a&amp;#92;u202f&amp;#92;u205f&amp;#92;u3000]&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l160"></a>
<span id="l161"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct predef&quot;&gt;&lt;tt&gt;\H&lt;/tt&gt;&lt;/td&gt;</span><a href="#l161"></a>
<span id="l162"> *     &lt;td headers=&quot;matches&quot;&gt;A non-horizontal whitespace character: &lt;tt&gt;[^\h]&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l162"></a>
<span id="l163"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct predef&quot;&gt;&lt;tt&gt;\s&lt;/tt&gt;&lt;/td&gt;</span><a href="#l163"></a>
<span id="l164"> *     &lt;td headers=&quot;matches&quot;&gt;A whitespace character: &lt;tt&gt;[ \t\n\x0B\f\r]&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l164"></a>
<span id="l165"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct predef&quot;&gt;&lt;tt&gt;\S&lt;/tt&gt;&lt;/td&gt;</span><a href="#l165"></a>
<span id="l166"> *     &lt;td headers=&quot;matches&quot;&gt;A non-whitespace character: &lt;tt&gt;[^\s]&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l166"></a>
<span id="l167"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct predef&quot;&gt;&lt;tt&gt;\v&lt;/tt&gt;&lt;/td&gt;</span><a href="#l167"></a>
<span id="l168"> *     &lt;td headers=&quot;matches&quot;&gt;A vertical whitespace character: &lt;tt&gt;[\n\x0B\f\r\x85&amp;#92;u2028&amp;#92;u2029]&lt;/tt&gt;</span><a href="#l168"></a>
<span id="l169"> *     &lt;/td&gt;&lt;/tr&gt;</span><a href="#l169"></a>
<span id="l170"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct predef&quot;&gt;&lt;tt&gt;\V&lt;/tt&gt;&lt;/td&gt;</span><a href="#l170"></a>
<span id="l171"> *     &lt;td headers=&quot;matches&quot;&gt;A non-vertical whitespace character: &lt;tt&gt;[^\v]&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l171"></a>
<span id="l172"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct predef&quot;&gt;&lt;tt&gt;\w&lt;/tt&gt;&lt;/td&gt;</span><a href="#l172"></a>
<span id="l173"> *     &lt;td headers=&quot;matches&quot;&gt;A word character: &lt;tt&gt;[a-zA-Z_0-9]&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l173"></a>
<span id="l174"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct predef&quot;&gt;&lt;tt&gt;\W&lt;/tt&gt;&lt;/td&gt;</span><a href="#l174"></a>
<span id="l175"> *     &lt;td headers=&quot;matches&quot;&gt;A non-word character: &lt;tt&gt;[^\w]&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l175"></a>
<span id="l176"> * &lt;tr&gt;&lt;th&gt;&amp;nbsp;&lt;/th&gt;&lt;/tr&gt;</span><a href="#l176"></a>
<span id="l177"> * &lt;tr align=&quot;left&quot;&gt;&lt;th colspan=&quot;2&quot; id=&quot;posix&quot;&gt;&lt;b&gt;POSIX character classes (US-ASCII only)&lt;/b&gt;&lt;/th&gt;&lt;/tr&gt;</span><a href="#l177"></a>
<span id="l178"> *</span><a href="#l178"></a>
<span id="l179"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct posix&quot;&gt;{@code \p{Lower}}&lt;/td&gt;</span><a href="#l179"></a>
<span id="l180"> *     &lt;td headers=&quot;matches&quot;&gt;A lower-case alphabetic character: {@code [a-z]}&lt;/td&gt;&lt;/tr&gt;</span><a href="#l180"></a>
<span id="l181"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct posix&quot;&gt;{@code \p{Upper}}&lt;/td&gt;</span><a href="#l181"></a>
<span id="l182"> *     &lt;td headers=&quot;matches&quot;&gt;An upper-case alphabetic character:{@code [A-Z]}&lt;/td&gt;&lt;/tr&gt;</span><a href="#l182"></a>
<span id="l183"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct posix&quot;&gt;{@code \p{ASCII}}&lt;/td&gt;</span><a href="#l183"></a>
<span id="l184"> *     &lt;td headers=&quot;matches&quot;&gt;All ASCII:{@code [\x00-\x7F]}&lt;/td&gt;&lt;/tr&gt;</span><a href="#l184"></a>
<span id="l185"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct posix&quot;&gt;{@code \p{Alpha}}&lt;/td&gt;</span><a href="#l185"></a>
<span id="l186"> *     &lt;td headers=&quot;matches&quot;&gt;An alphabetic character:{@code [\p{Lower}\p{Upper}]}&lt;/td&gt;&lt;/tr&gt;</span><a href="#l186"></a>
<span id="l187"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct posix&quot;&gt;{@code \p{Digit}}&lt;/td&gt;</span><a href="#l187"></a>
<span id="l188"> *     &lt;td headers=&quot;matches&quot;&gt;A decimal digit: {@code [0-9]}&lt;/td&gt;&lt;/tr&gt;</span><a href="#l188"></a>
<span id="l189"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct posix&quot;&gt;{@code \p{Alnum}}&lt;/td&gt;</span><a href="#l189"></a>
<span id="l190"> *     &lt;td headers=&quot;matches&quot;&gt;An alphanumeric character:{@code [\p{Alpha}\p{Digit}]}&lt;/td&gt;&lt;/tr&gt;</span><a href="#l190"></a>
<span id="l191"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct posix&quot;&gt;{@code \p{Punct}}&lt;/td&gt;</span><a href="#l191"></a>
<span id="l192"> *     &lt;td headers=&quot;matches&quot;&gt;Punctuation: One of {@code !&quot;#$%&amp;'()*+,-./:;&lt;=&gt;?@[\]^_`{|}~}&lt;/td&gt;&lt;/tr&gt;</span><a href="#l192"></a>
<span id="l193"> *     &lt;!-- {@code [\!&quot;#\$%&amp;'\(\)\*\+,\-\./:;\&lt;=\&gt;\?@\[\\\]\^_`\{\|\}~]}</span><a href="#l193"></a>
<span id="l194"> *          {@code [\X21-\X2F\X31-\X40\X5B-\X60\X7B-\X7E]} --&gt;</span><a href="#l194"></a>
<span id="l195"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct posix&quot;&gt;{@code \p{Graph}}&lt;/td&gt;</span><a href="#l195"></a>
<span id="l196"> *     &lt;td headers=&quot;matches&quot;&gt;A visible character: {@code [\p{Alnum}\p{Punct}]}&lt;/td&gt;&lt;/tr&gt;</span><a href="#l196"></a>
<span id="l197"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct posix&quot;&gt;{@code \p{Print}}&lt;/td&gt;</span><a href="#l197"></a>
<span id="l198"> *     &lt;td headers=&quot;matches&quot;&gt;A printable character: {@code [\p{Graph}\x20]}&lt;/td&gt;&lt;/tr&gt;</span><a href="#l198"></a>
<span id="l199"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct posix&quot;&gt;{@code \p{Blank}}&lt;/td&gt;</span><a href="#l199"></a>
<span id="l200"> *     &lt;td headers=&quot;matches&quot;&gt;A space or a tab: {@code [ \t]}&lt;/td&gt;&lt;/tr&gt;</span><a href="#l200"></a>
<span id="l201"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct posix&quot;&gt;{@code \p{Cntrl}}&lt;/td&gt;</span><a href="#l201"></a>
<span id="l202"> *     &lt;td headers=&quot;matches&quot;&gt;A control character: {@code [\x00-\x1F\x7F]}&lt;/td&gt;&lt;/tr&gt;</span><a href="#l202"></a>
<span id="l203"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct posix&quot;&gt;{@code \p{XDigit}}&lt;/td&gt;</span><a href="#l203"></a>
<span id="l204"> *     &lt;td headers=&quot;matches&quot;&gt;A hexadecimal digit: {@code [0-9a-fA-F]}&lt;/td&gt;&lt;/tr&gt;</span><a href="#l204"></a>
<span id="l205"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct posix&quot;&gt;{@code \p{Space}}&lt;/td&gt;</span><a href="#l205"></a>
<span id="l206"> *     &lt;td headers=&quot;matches&quot;&gt;A whitespace character: {@code [ \t\n\x0B\f\r]}&lt;/td&gt;&lt;/tr&gt;</span><a href="#l206"></a>
<span id="l207"> *</span><a href="#l207"></a>
<span id="l208"> * &lt;tr&gt;&lt;th&gt;&amp;nbsp;&lt;/th&gt;&lt;/tr&gt;</span><a href="#l208"></a>
<span id="l209"> * &lt;tr align=&quot;left&quot;&gt;&lt;th colspan=&quot;2&quot;&gt;java.lang.Character classes (simple &lt;a href=&quot;#jcc&quot;&gt;java character type&lt;/a&gt;)&lt;/th&gt;&lt;/tr&gt;</span><a href="#l209"></a>
<span id="l210"> *</span><a href="#l210"></a>
<span id="l211"> * &lt;tr&gt;&lt;td valign=&quot;top&quot;&gt;&lt;tt&gt;\p{javaLowerCase}&lt;/tt&gt;&lt;/td&gt;</span><a href="#l211"></a>
<span id="l212"> *     &lt;td&gt;Equivalent to java.lang.Character.isLowerCase()&lt;/td&gt;&lt;/tr&gt;</span><a href="#l212"></a>
<span id="l213"> * &lt;tr&gt;&lt;td valign=&quot;top&quot;&gt;&lt;tt&gt;\p{javaUpperCase}&lt;/tt&gt;&lt;/td&gt;</span><a href="#l213"></a>
<span id="l214"> *     &lt;td&gt;Equivalent to java.lang.Character.isUpperCase()&lt;/td&gt;&lt;/tr&gt;</span><a href="#l214"></a>
<span id="l215"> * &lt;tr&gt;&lt;td valign=&quot;top&quot;&gt;&lt;tt&gt;\p{javaWhitespace}&lt;/tt&gt;&lt;/td&gt;</span><a href="#l215"></a>
<span id="l216"> *     &lt;td&gt;Equivalent to java.lang.Character.isWhitespace()&lt;/td&gt;&lt;/tr&gt;</span><a href="#l216"></a>
<span id="l217"> * &lt;tr&gt;&lt;td valign=&quot;top&quot;&gt;&lt;tt&gt;\p{javaMirrored}&lt;/tt&gt;&lt;/td&gt;</span><a href="#l217"></a>
<span id="l218"> *     &lt;td&gt;Equivalent to java.lang.Character.isMirrored()&lt;/td&gt;&lt;/tr&gt;</span><a href="#l218"></a>
<span id="l219"> *</span><a href="#l219"></a>
<span id="l220"> * &lt;tr&gt;&lt;th&gt;&amp;nbsp;&lt;/th&gt;&lt;/tr&gt;</span><a href="#l220"></a>
<span id="l221"> * &lt;tr align=&quot;left&quot;&gt;&lt;th colspan=&quot;2&quot; id=&quot;unicode&quot;&gt;Classes for Unicode scripts, blocks, categories and binary properties&lt;/th&gt;&lt;/tr&gt;</span><a href="#l221"></a>
<span id="l222"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct unicode&quot;&gt;{@code \p{IsLatin}}&lt;/td&gt;</span><a href="#l222"></a>
<span id="l223"> *     &lt;td headers=&quot;matches&quot;&gt;A Latin&amp;nbsp;script character (&lt;a href=&quot;#usc&quot;&gt;script&lt;/a&gt;)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l223"></a>
<span id="l224"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct unicode&quot;&gt;{@code \p{InGreek}}&lt;/td&gt;</span><a href="#l224"></a>
<span id="l225"> *     &lt;td headers=&quot;matches&quot;&gt;A character in the Greek&amp;nbsp;block (&lt;a href=&quot;#ubc&quot;&gt;block&lt;/a&gt;)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l225"></a>
<span id="l226"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct unicode&quot;&gt;{@code \p{Lu}}&lt;/td&gt;</span><a href="#l226"></a>
<span id="l227"> *     &lt;td headers=&quot;matches&quot;&gt;An uppercase letter (&lt;a href=&quot;#ucc&quot;&gt;category&lt;/a&gt;)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l227"></a>
<span id="l228"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct unicode&quot;&gt;{@code \p{IsAlphabetic}}&lt;/td&gt;</span><a href="#l228"></a>
<span id="l229"> *     &lt;td headers=&quot;matches&quot;&gt;An alphabetic character (&lt;a href=&quot;#ubpc&quot;&gt;binary property&lt;/a&gt;)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l229"></a>
<span id="l230"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct unicode&quot;&gt;{@code \p{Sc}}&lt;/td&gt;</span><a href="#l230"></a>
<span id="l231"> *     &lt;td headers=&quot;matches&quot;&gt;A currency symbol&lt;/td&gt;&lt;/tr&gt;</span><a href="#l231"></a>
<span id="l232"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct unicode&quot;&gt;{@code \P{InGreek}}&lt;/td&gt;</span><a href="#l232"></a>
<span id="l233"> *     &lt;td headers=&quot;matches&quot;&gt;Any character except one in the Greek block (negation)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l233"></a>
<span id="l234"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct unicode&quot;&gt;{@code [\p{L}&amp;&amp;[^\p{Lu}]]}&lt;/td&gt;</span><a href="#l234"></a>
<span id="l235"> *     &lt;td headers=&quot;matches&quot;&gt;Any letter except an uppercase letter (subtraction)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l235"></a>
<span id="l236"> *</span><a href="#l236"></a>
<span id="l237"> * &lt;tr&gt;&lt;th&gt;&amp;nbsp;&lt;/th&gt;&lt;/tr&gt;</span><a href="#l237"></a>
<span id="l238"> * &lt;tr align=&quot;left&quot;&gt;&lt;th colspan=&quot;2&quot; id=&quot;bounds&quot;&gt;Boundary matchers&lt;/th&gt;&lt;/tr&gt;</span><a href="#l238"></a>
<span id="l239"> *</span><a href="#l239"></a>
<span id="l240"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct bounds&quot;&gt;&lt;tt&gt;^&lt;/tt&gt;&lt;/td&gt;</span><a href="#l240"></a>
<span id="l241"> *     &lt;td headers=&quot;matches&quot;&gt;The beginning of a line&lt;/td&gt;&lt;/tr&gt;</span><a href="#l241"></a>
<span id="l242"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct bounds&quot;&gt;&lt;tt&gt;$&lt;/tt&gt;&lt;/td&gt;</span><a href="#l242"></a>
<span id="l243"> *     &lt;td headers=&quot;matches&quot;&gt;The end of a line&lt;/td&gt;&lt;/tr&gt;</span><a href="#l243"></a>
<span id="l244"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct bounds&quot;&gt;&lt;tt&gt;\b&lt;/tt&gt;&lt;/td&gt;</span><a href="#l244"></a>
<span id="l245"> *     &lt;td headers=&quot;matches&quot;&gt;A word boundary&lt;/td&gt;&lt;/tr&gt;</span><a href="#l245"></a>
<span id="l246"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct bounds&quot;&gt;&lt;tt&gt;\B&lt;/tt&gt;&lt;/td&gt;</span><a href="#l246"></a>
<span id="l247"> *     &lt;td headers=&quot;matches&quot;&gt;A non-word boundary&lt;/td&gt;&lt;/tr&gt;</span><a href="#l247"></a>
<span id="l248"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct bounds&quot;&gt;&lt;tt&gt;\A&lt;/tt&gt;&lt;/td&gt;</span><a href="#l248"></a>
<span id="l249"> *     &lt;td headers=&quot;matches&quot;&gt;The beginning of the input&lt;/td&gt;&lt;/tr&gt;</span><a href="#l249"></a>
<span id="l250"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct bounds&quot;&gt;&lt;tt&gt;\G&lt;/tt&gt;&lt;/td&gt;</span><a href="#l250"></a>
<span id="l251"> *     &lt;td headers=&quot;matches&quot;&gt;The end of the previous match&lt;/td&gt;&lt;/tr&gt;</span><a href="#l251"></a>
<span id="l252"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct bounds&quot;&gt;&lt;tt&gt;\Z&lt;/tt&gt;&lt;/td&gt;</span><a href="#l252"></a>
<span id="l253"> *     &lt;td headers=&quot;matches&quot;&gt;The end of the input but for the final</span><a href="#l253"></a>
<span id="l254"> *         &lt;a href=&quot;#lt&quot;&gt;terminator&lt;/a&gt;, if&amp;nbsp;any&lt;/td&gt;&lt;/tr&gt;</span><a href="#l254"></a>
<span id="l255"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct bounds&quot;&gt;&lt;tt&gt;\z&lt;/tt&gt;&lt;/td&gt;</span><a href="#l255"></a>
<span id="l256"> *     &lt;td headers=&quot;matches&quot;&gt;The end of the input&lt;/td&gt;&lt;/tr&gt;</span><a href="#l256"></a>
<span id="l257"> *</span><a href="#l257"></a>
<span id="l258"> * &lt;tr&gt;&lt;th&gt;&amp;nbsp;&lt;/th&gt;&lt;/tr&gt;</span><a href="#l258"></a>
<span id="l259"> * &lt;tr align=&quot;left&quot;&gt;&lt;th colspan=&quot;2&quot; id=&quot;lineending&quot;&gt;Linebreak matcher&lt;/th&gt;&lt;/tr&gt;</span><a href="#l259"></a>
<span id="l260"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct lineending&quot;&gt;&lt;tt&gt;\R&lt;/tt&gt;&lt;/td&gt;</span><a href="#l260"></a>
<span id="l261"> *     &lt;td headers=&quot;matches&quot;&gt;Any Unicode linebreak sequence, is equivalent to</span><a href="#l261"></a>
<span id="l262"> *     &lt;tt&gt;&amp;#92;u000D&amp;#92;u000A|[&amp;#92;u000A&amp;#92;u000B&amp;#92;u000C&amp;#92;u000D&amp;#92;u0085&amp;#92;u2028&amp;#92;u2029]</span><a href="#l262"></a>
<span id="l263"> *     &lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l263"></a>
<span id="l264"> *</span><a href="#l264"></a>
<span id="l265"> * &lt;tr&gt;&lt;th&gt;&amp;nbsp;&lt;/th&gt;&lt;/tr&gt;</span><a href="#l265"></a>
<span id="l266"> * &lt;tr align=&quot;left&quot;&gt;&lt;th colspan=&quot;2&quot; id=&quot;greedy&quot;&gt;Greedy quantifiers&lt;/th&gt;&lt;/tr&gt;</span><a href="#l266"></a>
<span id="l267"> *</span><a href="#l267"></a>
<span id="l268"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct greedy&quot;&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;?&lt;/tt&gt;&lt;/td&gt;</span><a href="#l268"></a>
<span id="l269"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, once or not at all&lt;/td&gt;&lt;/tr&gt;</span><a href="#l269"></a>
<span id="l270"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct greedy&quot;&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;*&lt;/tt&gt;&lt;/td&gt;</span><a href="#l270"></a>
<span id="l271"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, zero or more times&lt;/td&gt;&lt;/tr&gt;</span><a href="#l271"></a>
<span id="l272"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct greedy&quot;&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;+&lt;/tt&gt;&lt;/td&gt;</span><a href="#l272"></a>
<span id="l273"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, one or more times&lt;/td&gt;&lt;/tr&gt;</span><a href="#l273"></a>
<span id="l274"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct greedy&quot;&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;{&lt;/tt&gt;&lt;i&gt;n&lt;/i&gt;&lt;tt&gt;}&lt;/tt&gt;&lt;/td&gt;</span><a href="#l274"></a>
<span id="l275"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, exactly &lt;i&gt;n&lt;/i&gt; times&lt;/td&gt;&lt;/tr&gt;</span><a href="#l275"></a>
<span id="l276"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct greedy&quot;&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;{&lt;/tt&gt;&lt;i&gt;n&lt;/i&gt;&lt;tt&gt;,}&lt;/tt&gt;&lt;/td&gt;</span><a href="#l276"></a>
<span id="l277"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, at least &lt;i&gt;n&lt;/i&gt; times&lt;/td&gt;&lt;/tr&gt;</span><a href="#l277"></a>
<span id="l278"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct greedy&quot;&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;{&lt;/tt&gt;&lt;i&gt;n&lt;/i&gt;&lt;tt&gt;,&lt;/tt&gt;&lt;i&gt;m&lt;/i&gt;&lt;tt&gt;}&lt;/tt&gt;&lt;/td&gt;</span><a href="#l278"></a>
<span id="l279"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, at least &lt;i&gt;n&lt;/i&gt; but not more than &lt;i&gt;m&lt;/i&gt; times&lt;/td&gt;&lt;/tr&gt;</span><a href="#l279"></a>
<span id="l280"> *</span><a href="#l280"></a>
<span id="l281"> * &lt;tr&gt;&lt;th&gt;&amp;nbsp;&lt;/th&gt;&lt;/tr&gt;</span><a href="#l281"></a>
<span id="l282"> * &lt;tr align=&quot;left&quot;&gt;&lt;th colspan=&quot;2&quot; id=&quot;reluc&quot;&gt;Reluctant quantifiers&lt;/th&gt;&lt;/tr&gt;</span><a href="#l282"></a>
<span id="l283"> *</span><a href="#l283"></a>
<span id="l284"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct reluc&quot;&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;??&lt;/tt&gt;&lt;/td&gt;</span><a href="#l284"></a>
<span id="l285"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, once or not at all&lt;/td&gt;&lt;/tr&gt;</span><a href="#l285"></a>
<span id="l286"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct reluc&quot;&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;*?&lt;/tt&gt;&lt;/td&gt;</span><a href="#l286"></a>
<span id="l287"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, zero or more times&lt;/td&gt;&lt;/tr&gt;</span><a href="#l287"></a>
<span id="l288"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct reluc&quot;&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;+?&lt;/tt&gt;&lt;/td&gt;</span><a href="#l288"></a>
<span id="l289"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, one or more times&lt;/td&gt;&lt;/tr&gt;</span><a href="#l289"></a>
<span id="l290"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct reluc&quot;&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;{&lt;/tt&gt;&lt;i&gt;n&lt;/i&gt;&lt;tt&gt;}?&lt;/tt&gt;&lt;/td&gt;</span><a href="#l290"></a>
<span id="l291"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, exactly &lt;i&gt;n&lt;/i&gt; times&lt;/td&gt;&lt;/tr&gt;</span><a href="#l291"></a>
<span id="l292"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct reluc&quot;&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;{&lt;/tt&gt;&lt;i&gt;n&lt;/i&gt;&lt;tt&gt;,}?&lt;/tt&gt;&lt;/td&gt;</span><a href="#l292"></a>
<span id="l293"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, at least &lt;i&gt;n&lt;/i&gt; times&lt;/td&gt;&lt;/tr&gt;</span><a href="#l293"></a>
<span id="l294"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct reluc&quot;&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;{&lt;/tt&gt;&lt;i&gt;n&lt;/i&gt;&lt;tt&gt;,&lt;/tt&gt;&lt;i&gt;m&lt;/i&gt;&lt;tt&gt;}?&lt;/tt&gt;&lt;/td&gt;</span><a href="#l294"></a>
<span id="l295"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, at least &lt;i&gt;n&lt;/i&gt; but not more than &lt;i&gt;m&lt;/i&gt; times&lt;/td&gt;&lt;/tr&gt;</span><a href="#l295"></a>
<span id="l296"> *</span><a href="#l296"></a>
<span id="l297"> * &lt;tr&gt;&lt;th&gt;&amp;nbsp;&lt;/th&gt;&lt;/tr&gt;</span><a href="#l297"></a>
<span id="l298"> * &lt;tr align=&quot;left&quot;&gt;&lt;th colspan=&quot;2&quot; id=&quot;poss&quot;&gt;Possessive quantifiers&lt;/th&gt;&lt;/tr&gt;</span><a href="#l298"></a>
<span id="l299"> *</span><a href="#l299"></a>
<span id="l300"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct poss&quot;&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;?+&lt;/tt&gt;&lt;/td&gt;</span><a href="#l300"></a>
<span id="l301"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, once or not at all&lt;/td&gt;&lt;/tr&gt;</span><a href="#l301"></a>
<span id="l302"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct poss&quot;&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;*+&lt;/tt&gt;&lt;/td&gt;</span><a href="#l302"></a>
<span id="l303"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, zero or more times&lt;/td&gt;&lt;/tr&gt;</span><a href="#l303"></a>
<span id="l304"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct poss&quot;&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;++&lt;/tt&gt;&lt;/td&gt;</span><a href="#l304"></a>
<span id="l305"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, one or more times&lt;/td&gt;&lt;/tr&gt;</span><a href="#l305"></a>
<span id="l306"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct poss&quot;&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;{&lt;/tt&gt;&lt;i&gt;n&lt;/i&gt;&lt;tt&gt;}+&lt;/tt&gt;&lt;/td&gt;</span><a href="#l306"></a>
<span id="l307"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, exactly &lt;i&gt;n&lt;/i&gt; times&lt;/td&gt;&lt;/tr&gt;</span><a href="#l307"></a>
<span id="l308"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct poss&quot;&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;{&lt;/tt&gt;&lt;i&gt;n&lt;/i&gt;&lt;tt&gt;,}+&lt;/tt&gt;&lt;/td&gt;</span><a href="#l308"></a>
<span id="l309"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, at least &lt;i&gt;n&lt;/i&gt; times&lt;/td&gt;&lt;/tr&gt;</span><a href="#l309"></a>
<span id="l310"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct poss&quot;&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;{&lt;/tt&gt;&lt;i&gt;n&lt;/i&gt;&lt;tt&gt;,&lt;/tt&gt;&lt;i&gt;m&lt;/i&gt;&lt;tt&gt;}+&lt;/tt&gt;&lt;/td&gt;</span><a href="#l310"></a>
<span id="l311"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, at least &lt;i&gt;n&lt;/i&gt; but not more than &lt;i&gt;m&lt;/i&gt; times&lt;/td&gt;&lt;/tr&gt;</span><a href="#l311"></a>
<span id="l312"> *</span><a href="#l312"></a>
<span id="l313"> * &lt;tr&gt;&lt;th&gt;&amp;nbsp;&lt;/th&gt;&lt;/tr&gt;</span><a href="#l313"></a>
<span id="l314"> * &lt;tr align=&quot;left&quot;&gt;&lt;th colspan=&quot;2&quot; id=&quot;logical&quot;&gt;Logical operators&lt;/th&gt;&lt;/tr&gt;</span><a href="#l314"></a>
<span id="l315"> *</span><a href="#l315"></a>
<span id="l316"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct logical&quot;&gt;&lt;i&gt;XY&lt;/i&gt;&lt;/td&gt;</span><a href="#l316"></a>
<span id="l317"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt; followed by &lt;i&gt;Y&lt;/i&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l317"></a>
<span id="l318"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct logical&quot;&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;|&lt;/tt&gt;&lt;i&gt;Y&lt;/i&gt;&lt;/td&gt;</span><a href="#l318"></a>
<span id="l319"> *     &lt;td headers=&quot;matches&quot;&gt;Either &lt;i&gt;X&lt;/i&gt; or &lt;i&gt;Y&lt;/i&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l319"></a>
<span id="l320"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct logical&quot;&gt;&lt;tt&gt;(&lt;/tt&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;)&lt;/tt&gt;&lt;/td&gt;</span><a href="#l320"></a>
<span id="l321"> *     &lt;td headers=&quot;matches&quot;&gt;X, as a &lt;a href=&quot;#cg&quot;&gt;capturing group&lt;/a&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l321"></a>
<span id="l322"> *</span><a href="#l322"></a>
<span id="l323"> * &lt;tr&gt;&lt;th&gt;&amp;nbsp;&lt;/th&gt;&lt;/tr&gt;</span><a href="#l323"></a>
<span id="l324"> * &lt;tr align=&quot;left&quot;&gt;&lt;th colspan=&quot;2&quot; id=&quot;backref&quot;&gt;Back references&lt;/th&gt;&lt;/tr&gt;</span><a href="#l324"></a>
<span id="l325"> *</span><a href="#l325"></a>
<span id="l326"> * &lt;tr&gt;&lt;td valign=&quot;bottom&quot; headers=&quot;construct backref&quot;&gt;&lt;tt&gt;\&lt;/tt&gt;&lt;i&gt;n&lt;/i&gt;&lt;/td&gt;</span><a href="#l326"></a>
<span id="l327"> *     &lt;td valign=&quot;bottom&quot; headers=&quot;matches&quot;&gt;Whatever the &lt;i&gt;n&lt;/i&gt;&lt;sup&gt;th&lt;/sup&gt;</span><a href="#l327"></a>
<span id="l328"> *     &lt;a href=&quot;#cg&quot;&gt;capturing group&lt;/a&gt; matched&lt;/td&gt;&lt;/tr&gt;</span><a href="#l328"></a>
<span id="l329"> *</span><a href="#l329"></a>
<span id="l330"> * &lt;tr&gt;&lt;td valign=&quot;bottom&quot; headers=&quot;construct backref&quot;&gt;&lt;tt&gt;\&lt;/tt&gt;&lt;i&gt;k&lt;/i&gt;&amp;lt;&lt;i&gt;name&lt;/i&gt;&amp;gt;&lt;/td&gt;</span><a href="#l330"></a>
<span id="l331"> *     &lt;td valign=&quot;bottom&quot; headers=&quot;matches&quot;&gt;Whatever the</span><a href="#l331"></a>
<span id="l332"> *     &lt;a href=&quot;#groupname&quot;&gt;named-capturing group&lt;/a&gt; &quot;name&quot; matched&lt;/td&gt;&lt;/tr&gt;</span><a href="#l332"></a>
<span id="l333"> *</span><a href="#l333"></a>
<span id="l334"> * &lt;tr&gt;&lt;th&gt;&amp;nbsp;&lt;/th&gt;&lt;/tr&gt;</span><a href="#l334"></a>
<span id="l335"> * &lt;tr align=&quot;left&quot;&gt;&lt;th colspan=&quot;2&quot; id=&quot;quot&quot;&gt;Quotation&lt;/th&gt;&lt;/tr&gt;</span><a href="#l335"></a>
<span id="l336"> *</span><a href="#l336"></a>
<span id="l337"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct quot&quot;&gt;&lt;tt&gt;\&lt;/tt&gt;&lt;/td&gt;</span><a href="#l337"></a>
<span id="l338"> *     &lt;td headers=&quot;matches&quot;&gt;Nothing, but quotes the following character&lt;/td&gt;&lt;/tr&gt;</span><a href="#l338"></a>
<span id="l339"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct quot&quot;&gt;&lt;tt&gt;\Q&lt;/tt&gt;&lt;/td&gt;</span><a href="#l339"></a>
<span id="l340"> *     &lt;td headers=&quot;matches&quot;&gt;Nothing, but quotes all characters until &lt;tt&gt;\E&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l340"></a>
<span id="l341"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct quot&quot;&gt;&lt;tt&gt;\E&lt;/tt&gt;&lt;/td&gt;</span><a href="#l341"></a>
<span id="l342"> *     &lt;td headers=&quot;matches&quot;&gt;Nothing, but ends quoting started by &lt;tt&gt;\Q&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l342"></a>
<span id="l343"> *     &lt;!-- Metachars: !$()*+.&lt;&gt;?[\]^{|} --&gt;</span><a href="#l343"></a>
<span id="l344"> *</span><a href="#l344"></a>
<span id="l345"> * &lt;tr&gt;&lt;th&gt;&amp;nbsp;&lt;/th&gt;&lt;/tr&gt;</span><a href="#l345"></a>
<span id="l346"> * &lt;tr align=&quot;left&quot;&gt;&lt;th colspan=&quot;2&quot; id=&quot;special&quot;&gt;Special constructs (named-capturing and non-capturing)&lt;/th&gt;&lt;/tr&gt;</span><a href="#l346"></a>
<span id="l347"> *</span><a href="#l347"></a>
<span id="l348"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct special&quot;&gt;&lt;tt&gt;(?&amp;lt;&lt;a href=&quot;#groupname&quot;&gt;name&lt;/a&gt;&amp;gt;&lt;/tt&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;)&lt;/tt&gt;&lt;/td&gt;</span><a href="#l348"></a>
<span id="l349"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, as a named-capturing group&lt;/td&gt;&lt;/tr&gt;</span><a href="#l349"></a>
<span id="l350"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct special&quot;&gt;&lt;tt&gt;(?:&lt;/tt&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;)&lt;/tt&gt;&lt;/td&gt;</span><a href="#l350"></a>
<span id="l351"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, as a non-capturing group&lt;/td&gt;&lt;/tr&gt;</span><a href="#l351"></a>
<span id="l352"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct special&quot;&gt;&lt;tt&gt;(?idmsuxU-idmsuxU)&amp;nbsp;&lt;/tt&gt;&lt;/td&gt;</span><a href="#l352"></a>
<span id="l353"> *     &lt;td headers=&quot;matches&quot;&gt;Nothing, but turns match flags &lt;a href=&quot;#CASE_INSENSITIVE&quot;&gt;i&lt;/a&gt;</span><a href="#l353"></a>
<span id="l354"> * &lt;a href=&quot;#UNIX_LINES&quot;&gt;d&lt;/a&gt; &lt;a href=&quot;#MULTILINE&quot;&gt;m&lt;/a&gt; &lt;a href=&quot;#DOTALL&quot;&gt;s&lt;/a&gt;</span><a href="#l354"></a>
<span id="l355"> * &lt;a href=&quot;#UNICODE_CASE&quot;&gt;u&lt;/a&gt; &lt;a href=&quot;#COMMENTS&quot;&gt;x&lt;/a&gt; &lt;a href=&quot;#UNICODE_CHARACTER_CLASS&quot;&gt;U&lt;/a&gt;</span><a href="#l355"></a>
<span id="l356"> * on - off&lt;/td&gt;&lt;/tr&gt;</span><a href="#l356"></a>
<span id="l357"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct special&quot;&gt;&lt;tt&gt;(?idmsux-idmsux:&lt;/tt&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;)&lt;/tt&gt;&amp;nbsp;&amp;nbsp;&lt;/td&gt;</span><a href="#l357"></a>
<span id="l358"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, as a &lt;a href=&quot;#cg&quot;&gt;non-capturing group&lt;/a&gt; with the</span><a href="#l358"></a>
<span id="l359"> *         given flags &lt;a href=&quot;#CASE_INSENSITIVE&quot;&gt;i&lt;/a&gt; &lt;a href=&quot;#UNIX_LINES&quot;&gt;d&lt;/a&gt;</span><a href="#l359"></a>
<span id="l360"> * &lt;a href=&quot;#MULTILINE&quot;&gt;m&lt;/a&gt; &lt;a href=&quot;#DOTALL&quot;&gt;s&lt;/a&gt; &lt;a href=&quot;#UNICODE_CASE&quot;&gt;u&lt;/a &gt;</span><a href="#l360"></a>
<span id="l361"> * &lt;a href=&quot;#COMMENTS&quot;&gt;x&lt;/a&gt; on - off&lt;/td&gt;&lt;/tr&gt;</span><a href="#l361"></a>
<span id="l362"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct special&quot;&gt;&lt;tt&gt;(?=&lt;/tt&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;)&lt;/tt&gt;&lt;/td&gt;</span><a href="#l362"></a>
<span id="l363"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, via zero-width positive lookahead&lt;/td&gt;&lt;/tr&gt;</span><a href="#l363"></a>
<span id="l364"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct special&quot;&gt;&lt;tt&gt;(?!&lt;/tt&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;)&lt;/tt&gt;&lt;/td&gt;</span><a href="#l364"></a>
<span id="l365"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, via zero-width negative lookahead&lt;/td&gt;&lt;/tr&gt;</span><a href="#l365"></a>
<span id="l366"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct special&quot;&gt;&lt;tt&gt;(?&amp;lt;=&lt;/tt&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;)&lt;/tt&gt;&lt;/td&gt;</span><a href="#l366"></a>
<span id="l367"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, via zero-width positive lookbehind&lt;/td&gt;&lt;/tr&gt;</span><a href="#l367"></a>
<span id="l368"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct special&quot;&gt;&lt;tt&gt;(?&amp;lt;!&lt;/tt&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;)&lt;/tt&gt;&lt;/td&gt;</span><a href="#l368"></a>
<span id="l369"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, via zero-width negative lookbehind&lt;/td&gt;&lt;/tr&gt;</span><a href="#l369"></a>
<span id="l370"> * &lt;tr&gt;&lt;td valign=&quot;top&quot; headers=&quot;construct special&quot;&gt;&lt;tt&gt;(?&amp;gt;&lt;/tt&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;)&lt;/tt&gt;&lt;/td&gt;</span><a href="#l370"></a>
<span id="l371"> *     &lt;td headers=&quot;matches&quot;&gt;&lt;i&gt;X&lt;/i&gt;, as an independent, non-capturing group&lt;/td&gt;&lt;/tr&gt;</span><a href="#l371"></a>
<span id="l372"> *</span><a href="#l372"></a>
<span id="l373"> * &lt;/table&gt;</span><a href="#l373"></a>
<span id="l374"> *</span><a href="#l374"></a>
<span id="l375"> * &lt;hr&gt;</span><a href="#l375"></a>
<span id="l376"> *</span><a href="#l376"></a>
<span id="l377"> *</span><a href="#l377"></a>
<span id="l378"> * &lt;h3&gt;&lt;a name=&quot;bs&quot;&gt;Backslashes, escapes, and quoting&lt;/a&gt;&lt;/h3&gt;</span><a href="#l378"></a>
<span id="l379"> *</span><a href="#l379"></a>
<span id="l380"> * &lt;p&gt; The backslash character (&lt;tt&gt;'\'&lt;/tt&gt;) serves to introduce escaped</span><a href="#l380"></a>
<span id="l381"> * constructs, as defined in the table above, as well as to quote characters</span><a href="#l381"></a>
<span id="l382"> * that otherwise would be interpreted as unescaped constructs.  Thus the</span><a href="#l382"></a>
<span id="l383"> * expression &lt;tt&gt;\\&lt;/tt&gt; matches a single backslash and &lt;tt&gt;\{&lt;/tt&gt; matches a</span><a href="#l383"></a>
<span id="l384"> * left brace.</span><a href="#l384"></a>
<span id="l385"> *</span><a href="#l385"></a>
<span id="l386"> * &lt;p&gt; It is an error to use a backslash prior to any alphabetic character that</span><a href="#l386"></a>
<span id="l387"> * does not denote an escaped construct; these are reserved for future</span><a href="#l387"></a>
<span id="l388"> * extensions to the regular-expression language.  A backslash may be used</span><a href="#l388"></a>
<span id="l389"> * prior to a non-alphabetic character regardless of whether that character is</span><a href="#l389"></a>
<span id="l390"> * part of an unescaped construct.</span><a href="#l390"></a>
<span id="l391"> *</span><a href="#l391"></a>
<span id="l392"> * &lt;p&gt; Backslashes within string literals in Java source code are interpreted</span><a href="#l392"></a>
<span id="l393"> * as required by</span><a href="#l393"></a>
<span id="l394"> * &lt;cite&gt;The Java&amp;trade; Language Specification&lt;/cite&gt;</span><a href="#l394"></a>
<span id="l395"> * as either Unicode escapes (section 3.3) or other character escapes (section 3.10.6)</span><a href="#l395"></a>
<span id="l396"> * It is therefore necessary to double backslashes in string</span><a href="#l396"></a>
<span id="l397"> * literals that represent regular expressions to protect them from</span><a href="#l397"></a>
<span id="l398"> * interpretation by the Java bytecode compiler.  The string literal</span><a href="#l398"></a>
<span id="l399"> * &lt;tt&gt;&quot;&amp;#92;b&quot;&lt;/tt&gt;, for example, matches a single backspace character when</span><a href="#l399"></a>
<span id="l400"> * interpreted as a regular expression, while &lt;tt&gt;&quot;&amp;#92;&amp;#92;b&quot;&lt;/tt&gt; matches a</span><a href="#l400"></a>
<span id="l401"> * word boundary.  The string literal &lt;tt&gt;&quot;&amp;#92;(hello&amp;#92;)&quot;&lt;/tt&gt; is illegal</span><a href="#l401"></a>
<span id="l402"> * and leads to a compile-time error; in order to match the string</span><a href="#l402"></a>
<span id="l403"> * &lt;tt&gt;(hello)&lt;/tt&gt; the string literal &lt;tt&gt;&quot;&amp;#92;&amp;#92;(hello&amp;#92;&amp;#92;)&quot;&lt;/tt&gt;</span><a href="#l403"></a>
<span id="l404"> * must be used.</span><a href="#l404"></a>
<span id="l405"> *</span><a href="#l405"></a>
<span id="l406"> * &lt;h3&gt;&lt;a name=&quot;cc&quot;&gt;Character Classes&lt;/a&gt;&lt;/h3&gt;</span><a href="#l406"></a>
<span id="l407"> *</span><a href="#l407"></a>
<span id="l408"> *    &lt;p&gt; Character classes may appear within other character classes, and</span><a href="#l408"></a>
<span id="l409"> *    may be composed by the union operator (implicit) and the intersection</span><a href="#l409"></a>
<span id="l410"> *    operator (&lt;tt&gt;&amp;amp;&amp;amp;&lt;/tt&gt;).</span><a href="#l410"></a>
<span id="l411"> *    The union operator denotes a class that contains every character that is</span><a href="#l411"></a>
<span id="l412"> *    in at least one of its operand classes.  The intersection operator</span><a href="#l412"></a>
<span id="l413"> *    denotes a class that contains every character that is in both of its</span><a href="#l413"></a>
<span id="l414"> *    operand classes.</span><a href="#l414"></a>
<span id="l415"> *</span><a href="#l415"></a>
<span id="l416"> *    &lt;p&gt; The precedence of character-class operators is as follows, from</span><a href="#l416"></a>
<span id="l417"> *    highest to lowest:</span><a href="#l417"></a>
<span id="l418"> *</span><a href="#l418"></a>
<span id="l419"> *    &lt;blockquote&gt;&lt;table border=&quot;0&quot; cellpadding=&quot;1&quot; cellspacing=&quot;0&quot;</span><a href="#l419"></a>
<span id="l420"> *                 summary=&quot;Precedence of character class operators.&quot;&gt;</span><a href="#l420"></a>
<span id="l421"> *      &lt;tr&gt;&lt;th&gt;1&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/th&gt;</span><a href="#l421"></a>
<span id="l422"> *        &lt;td&gt;Literal escape&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/td&gt;</span><a href="#l422"></a>
<span id="l423"> *        &lt;td&gt;&lt;tt&gt;\x&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l423"></a>
<span id="l424"> *     &lt;tr&gt;&lt;th&gt;2&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/th&gt;</span><a href="#l424"></a>
<span id="l425"> *        &lt;td&gt;Grouping&lt;/td&gt;</span><a href="#l425"></a>
<span id="l426"> *        &lt;td&gt;&lt;tt&gt;[...]&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l426"></a>
<span id="l427"> *     &lt;tr&gt;&lt;th&gt;3&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/th&gt;</span><a href="#l427"></a>
<span id="l428"> *        &lt;td&gt;Range&lt;/td&gt;</span><a href="#l428"></a>
<span id="l429"> *        &lt;td&gt;&lt;tt&gt;a-z&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l429"></a>
<span id="l430"> *      &lt;tr&gt;&lt;th&gt;4&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/th&gt;</span><a href="#l430"></a>
<span id="l431"> *        &lt;td&gt;Union&lt;/td&gt;</span><a href="#l431"></a>
<span id="l432"> *        &lt;td&gt;&lt;tt&gt;[a-e][i-u]&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l432"></a>
<span id="l433"> *      &lt;tr&gt;&lt;th&gt;5&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/th&gt;</span><a href="#l433"></a>
<span id="l434"> *        &lt;td&gt;Intersection&lt;/td&gt;</span><a href="#l434"></a>
<span id="l435"> *        &lt;td&gt;{@code [a-z&amp;&amp;[aeiou]]}&lt;/td&gt;&lt;/tr&gt;</span><a href="#l435"></a>
<span id="l436"> *    &lt;/table&gt;&lt;/blockquote&gt;</span><a href="#l436"></a>
<span id="l437"> *</span><a href="#l437"></a>
<span id="l438"> *    &lt;p&gt; Note that a different set of metacharacters are in effect inside</span><a href="#l438"></a>
<span id="l439"> *    a character class than outside a character class. For instance, the</span><a href="#l439"></a>
<span id="l440"> *    regular expression &lt;tt&gt;.&lt;/tt&gt; loses its special meaning inside a</span><a href="#l440"></a>
<span id="l441"> *    character class, while the expression &lt;tt&gt;-&lt;/tt&gt; becomes a range</span><a href="#l441"></a>
<span id="l442"> *    forming metacharacter.</span><a href="#l442"></a>
<span id="l443"> *</span><a href="#l443"></a>
<span id="l444"> * &lt;h3&gt;&lt;a name=&quot;lt&quot;&gt;Line terminators&lt;/a&gt;&lt;/h3&gt;</span><a href="#l444"></a>
<span id="l445"> *</span><a href="#l445"></a>
<span id="l446"> * &lt;p&gt; A &lt;i&gt;line terminator&lt;/i&gt; is a one- or two-character sequence that marks</span><a href="#l446"></a>
<span id="l447"> * the end of a line of the input character sequence.  The following are</span><a href="#l447"></a>
<span id="l448"> * recognized as line terminators:</span><a href="#l448"></a>
<span id="l449"> *</span><a href="#l449"></a>
<span id="l450"> * &lt;ul&gt;</span><a href="#l450"></a>
<span id="l451"> *</span><a href="#l451"></a>
<span id="l452"> *   &lt;li&gt; A newline (line feed) character&amp;nbsp;(&lt;tt&gt;'\n'&lt;/tt&gt;),</span><a href="#l452"></a>
<span id="l453"> *</span><a href="#l453"></a>
<span id="l454"> *   &lt;li&gt; A carriage-return character followed immediately by a newline</span><a href="#l454"></a>
<span id="l455"> *   character&amp;nbsp;(&lt;tt&gt;&quot;\r\n&quot;&lt;/tt&gt;),</span><a href="#l455"></a>
<span id="l456"> *</span><a href="#l456"></a>
<span id="l457"> *   &lt;li&gt; A standalone carriage-return character&amp;nbsp;(&lt;tt&gt;'\r'&lt;/tt&gt;),</span><a href="#l457"></a>
<span id="l458"> *</span><a href="#l458"></a>
<span id="l459"> *   &lt;li&gt; A next-line character&amp;nbsp;(&lt;tt&gt;'&amp;#92;u0085'&lt;/tt&gt;),</span><a href="#l459"></a>
<span id="l460"> *</span><a href="#l460"></a>
<span id="l461"> *   &lt;li&gt; A line-separator character&amp;nbsp;(&lt;tt&gt;'&amp;#92;u2028'&lt;/tt&gt;), or</span><a href="#l461"></a>
<span id="l462"> *</span><a href="#l462"></a>
<span id="l463"> *   &lt;li&gt; A paragraph-separator character&amp;nbsp;(&lt;tt&gt;'&amp;#92;u2029&lt;/tt&gt;).</span><a href="#l463"></a>
<span id="l464"> *</span><a href="#l464"></a>
<span id="l465"> * &lt;/ul&gt;</span><a href="#l465"></a>
<span id="l466"> * &lt;p&gt;If {@link #UNIX_LINES} mode is activated, then the only line terminators</span><a href="#l466"></a>
<span id="l467"> * recognized are newline characters.</span><a href="#l467"></a>
<span id="l468"> *</span><a href="#l468"></a>
<span id="l469"> * &lt;p&gt; The regular expression &lt;tt&gt;.&lt;/tt&gt; matches any character except a line</span><a href="#l469"></a>
<span id="l470"> * terminator unless the {@link #DOTALL} flag is specified.</span><a href="#l470"></a>
<span id="l471"> *</span><a href="#l471"></a>
<span id="l472"> * &lt;p&gt; By default, the regular expressions &lt;tt&gt;^&lt;/tt&gt; and &lt;tt&gt;$&lt;/tt&gt; ignore</span><a href="#l472"></a>
<span id="l473"> * line terminators and only match at the beginning and the end, respectively,</span><a href="#l473"></a>
<span id="l474"> * of the entire input sequence. If {@link #MULTILINE} mode is activated then</span><a href="#l474"></a>
<span id="l475"> * &lt;tt&gt;^&lt;/tt&gt; matches at the beginning of input and after any line terminator</span><a href="#l475"></a>
<span id="l476"> * except at the end of input. When in {@link #MULTILINE} mode &lt;tt&gt;$&lt;/tt&gt;</span><a href="#l476"></a>
<span id="l477"> * matches just before a line terminator or the end of the input sequence.</span><a href="#l477"></a>
<span id="l478"> *</span><a href="#l478"></a>
<span id="l479"> * &lt;h3&gt;&lt;a name=&quot;cg&quot;&gt;Groups and capturing&lt;/a&gt;&lt;/h3&gt;</span><a href="#l479"></a>
<span id="l480"> *</span><a href="#l480"></a>
<span id="l481"> * &lt;h4&gt;&lt;a name=&quot;gnumber&quot;&gt;Group number&lt;/a&gt;&lt;/h4&gt;</span><a href="#l481"></a>
<span id="l482"> * &lt;p&gt; Capturing groups are numbered by counting their opening parentheses from</span><a href="#l482"></a>
<span id="l483"> * left to right.  In the expression &lt;tt&gt;((A)(B(C)))&lt;/tt&gt;, for example, there</span><a href="#l483"></a>
<span id="l484"> * are four such groups: &lt;/p&gt;</span><a href="#l484"></a>
<span id="l485"> *</span><a href="#l485"></a>
<span id="l486"> * &lt;blockquote&gt;&lt;table cellpadding=1 cellspacing=0 summary=&quot;Capturing group numberings&quot;&gt;</span><a href="#l486"></a>
<span id="l487"> * &lt;tr&gt;&lt;th&gt;1&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/th&gt;</span><a href="#l487"></a>
<span id="l488"> *     &lt;td&gt;&lt;tt&gt;((A)(B(C)))&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l488"></a>
<span id="l489"> * &lt;tr&gt;&lt;th&gt;2&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/th&gt;</span><a href="#l489"></a>
<span id="l490"> *     &lt;td&gt;&lt;tt&gt;(A)&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l490"></a>
<span id="l491"> * &lt;tr&gt;&lt;th&gt;3&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/th&gt;</span><a href="#l491"></a>
<span id="l492"> *     &lt;td&gt;&lt;tt&gt;(B(C))&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l492"></a>
<span id="l493"> * &lt;tr&gt;&lt;th&gt;4&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/th&gt;</span><a href="#l493"></a>
<span id="l494"> *     &lt;td&gt;&lt;tt&gt;(C)&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l494"></a>
<span id="l495"> * &lt;/table&gt;&lt;/blockquote&gt;</span><a href="#l495"></a>
<span id="l496"> *</span><a href="#l496"></a>
<span id="l497"> * &lt;p&gt; Group zero always stands for the entire expression.</span><a href="#l497"></a>
<span id="l498"> *</span><a href="#l498"></a>
<span id="l499"> * &lt;p&gt; Capturing groups are so named because, during a match, each subsequence</span><a href="#l499"></a>
<span id="l500"> * of the input sequence that matches such a group is saved.  The captured</span><a href="#l500"></a>
<span id="l501"> * subsequence may be used later in the expression, via a back reference, and</span><a href="#l501"></a>
<span id="l502"> * may also be retrieved from the matcher once the match operation is complete.</span><a href="#l502"></a>
<span id="l503"> *</span><a href="#l503"></a>
<span id="l504"> * &lt;h4&gt;&lt;a name=&quot;groupname&quot;&gt;Group name&lt;/a&gt;&lt;/h4&gt;</span><a href="#l504"></a>
<span id="l505"> * &lt;p&gt;A capturing group can also be assigned a &quot;name&quot;, a &lt;tt&gt;named-capturing group&lt;/tt&gt;,</span><a href="#l505"></a>
<span id="l506"> * and then be back-referenced later by the &quot;name&quot;. Group names are composed of</span><a href="#l506"></a>
<span id="l507"> * the following characters. The first character must be a &lt;tt&gt;letter&lt;/tt&gt;.</span><a href="#l507"></a>
<span id="l508"> *</span><a href="#l508"></a>
<span id="l509"> * &lt;ul&gt;</span><a href="#l509"></a>
<span id="l510"> *   &lt;li&gt; The uppercase letters &lt;tt&gt;'A'&lt;/tt&gt; through &lt;tt&gt;'Z'&lt;/tt&gt;</span><a href="#l510"></a>
<span id="l511"> *        (&lt;tt&gt;'&amp;#92;u0041'&lt;/tt&gt;&amp;nbsp;through&amp;nbsp;&lt;tt&gt;'&amp;#92;u005a'&lt;/tt&gt;),</span><a href="#l511"></a>
<span id="l512"> *   &lt;li&gt; The lowercase letters &lt;tt&gt;'a'&lt;/tt&gt; through &lt;tt&gt;'z'&lt;/tt&gt;</span><a href="#l512"></a>
<span id="l513"> *        (&lt;tt&gt;'&amp;#92;u0061'&lt;/tt&gt;&amp;nbsp;through&amp;nbsp;&lt;tt&gt;'&amp;#92;u007a'&lt;/tt&gt;),</span><a href="#l513"></a>
<span id="l514"> *   &lt;li&gt; The digits &lt;tt&gt;'0'&lt;/tt&gt; through &lt;tt&gt;'9'&lt;/tt&gt;</span><a href="#l514"></a>
<span id="l515"> *        (&lt;tt&gt;'&amp;#92;u0030'&lt;/tt&gt;&amp;nbsp;through&amp;nbsp;&lt;tt&gt;'&amp;#92;u0039'&lt;/tt&gt;),</span><a href="#l515"></a>
<span id="l516"> * &lt;/ul&gt;</span><a href="#l516"></a>
<span id="l517"> *</span><a href="#l517"></a>
<span id="l518"> * &lt;p&gt; A &lt;tt&gt;named-capturing group&lt;/tt&gt; is still numbered as described in</span><a href="#l518"></a>
<span id="l519"> * &lt;a href=&quot;#gnumber&quot;&gt;Group number&lt;/a&gt;.</span><a href="#l519"></a>
<span id="l520"> *</span><a href="#l520"></a>
<span id="l521"> * &lt;p&gt; The captured input associated with a group is always the subsequence</span><a href="#l521"></a>
<span id="l522"> * that the group most recently matched.  If a group is evaluated a second time</span><a href="#l522"></a>
<span id="l523"> * because of quantification then its previously-captured value, if any, will</span><a href="#l523"></a>
<span id="l524"> * be retained if the second evaluation fails.  Matching the string</span><a href="#l524"></a>
<span id="l525"> * &lt;tt&gt;&quot;aba&quot;&lt;/tt&gt; against the expression &lt;tt&gt;(a(b)?)+&lt;/tt&gt;, for example, leaves</span><a href="#l525"></a>
<span id="l526"> * group two set to &lt;tt&gt;&quot;b&quot;&lt;/tt&gt;.  All captured input is discarded at the</span><a href="#l526"></a>
<span id="l527"> * beginning of each match.</span><a href="#l527"></a>
<span id="l528"> *</span><a href="#l528"></a>
<span id="l529"> * &lt;p&gt; Groups beginning with &lt;tt&gt;(?&lt;/tt&gt; are either pure, &lt;i&gt;non-capturing&lt;/i&gt; groups</span><a href="#l529"></a>
<span id="l530"> * that do not capture text and do not count towards the group total, or</span><a href="#l530"></a>
<span id="l531"> * &lt;i&gt;named-capturing&lt;/i&gt; group.</span><a href="#l531"></a>
<span id="l532"> *</span><a href="#l532"></a>
<span id="l533"> * &lt;h3&gt; Unicode support &lt;/h3&gt;</span><a href="#l533"></a>
<span id="l534"> *</span><a href="#l534"></a>
<span id="l535"> * &lt;p&gt; This class is in conformance with Level 1 of &lt;a</span><a href="#l535"></a>
<span id="l536"> * href=&quot;http://www.unicode.org/reports/tr18/&quot;&gt;&lt;i&gt;Unicode Technical</span><a href="#l536"></a>
<span id="l537"> * Standard #18: Unicode Regular Expression&lt;/i&gt;&lt;/a&gt;, plus RL2.1</span><a href="#l537"></a>
<span id="l538"> * Canonical Equivalents.</span><a href="#l538"></a>
<span id="l539"> * &lt;p&gt;</span><a href="#l539"></a>
<span id="l540"> * &lt;b&gt;Unicode escape sequences&lt;/b&gt; such as &lt;tt&gt;&amp;#92;u2014&lt;/tt&gt; in Java source code</span><a href="#l540"></a>
<span id="l541"> * are processed as described in section 3.3 of</span><a href="#l541"></a>
<span id="l542"> * &lt;cite&gt;The Java&amp;trade; Language Specification&lt;/cite&gt;.</span><a href="#l542"></a>
<span id="l543"> * Such escape sequences are also implemented directly by the regular-expression</span><a href="#l543"></a>
<span id="l544"> * parser so that Unicode escapes can be used in expressions that are read from</span><a href="#l544"></a>
<span id="l545"> * files or from the keyboard.  Thus the strings &lt;tt&gt;&quot;&amp;#92;u2014&quot;&lt;/tt&gt; and</span><a href="#l545"></a>
<span id="l546"> * &lt;tt&gt;&quot;\\u2014&quot;&lt;/tt&gt;, while not equal, compile into the same pattern, which</span><a href="#l546"></a>
<span id="l547"> * matches the character with hexadecimal value &lt;tt&gt;0x2014&lt;/tt&gt;.</span><a href="#l547"></a>
<span id="l548"> * &lt;p&gt;</span><a href="#l548"></a>
<span id="l549"> * A Unicode character can also be represented in a regular-expression by</span><a href="#l549"></a>
<span id="l550"> * using its &lt;b&gt;Hex notation&lt;/b&gt;(hexadecimal code point value) directly as described in construct</span><a href="#l550"></a>
<span id="l551"> * &lt;tt&gt;&amp;#92;x{...}&lt;/tt&gt;, for example a supplementary character U+2011F</span><a href="#l551"></a>
<span id="l552"> * can be specified as &lt;tt&gt;&amp;#92;x{2011F}&lt;/tt&gt;, instead of two consecutive</span><a href="#l552"></a>
<span id="l553"> * Unicode escape sequences of the surrogate pair</span><a href="#l553"></a>
<span id="l554"> * &lt;tt&gt;&amp;#92;uD840&lt;/tt&gt;&lt;tt&gt;&amp;#92;uDD1F&lt;/tt&gt;.</span><a href="#l554"></a>
<span id="l555"> * &lt;p&gt;</span><a href="#l555"></a>
<span id="l556"> * Unicode scripts, blocks, categories and binary properties are written with</span><a href="#l556"></a>
<span id="l557"> * the &lt;tt&gt;\p&lt;/tt&gt; and &lt;tt&gt;\P&lt;/tt&gt; constructs as in Perl.</span><a href="#l557"></a>
<span id="l558"> * &lt;tt&gt;\p{&lt;/tt&gt;&lt;i&gt;prop&lt;/i&gt;&lt;tt&gt;}&lt;/tt&gt; matches if</span><a href="#l558"></a>
<span id="l559"> * the input has the property &lt;i&gt;prop&lt;/i&gt;, while &lt;tt&gt;\P{&lt;/tt&gt;&lt;i&gt;prop&lt;/i&gt;&lt;tt&gt;}&lt;/tt&gt;</span><a href="#l559"></a>
<span id="l560"> * does not match if the input has that property.</span><a href="#l560"></a>
<span id="l561"> * &lt;p&gt;</span><a href="#l561"></a>
<span id="l562"> * Scripts, blocks, categories and binary properties can be used both inside</span><a href="#l562"></a>
<span id="l563"> * and outside of a character class.</span><a href="#l563"></a>
<span id="l564"> *</span><a href="#l564"></a>
<span id="l565"> * &lt;p&gt;</span><a href="#l565"></a>
<span id="l566"> * &lt;b&gt;&lt;a name=&quot;usc&quot;&gt;Scripts&lt;/a&gt;&lt;/b&gt; are specified either with the prefix {@code Is}, as in</span><a href="#l566"></a>
<span id="l567"> * {@code IsHiragana}, or by using  the {@code script} keyword (or its short</span><a href="#l567"></a>
<span id="l568"> * form {@code sc})as in {@code script=Hiragana} or {@code sc=Hiragana}.</span><a href="#l568"></a>
<span id="l569"> * &lt;p&gt;</span><a href="#l569"></a>
<span id="l570"> * The script names supported by &lt;code&gt;Pattern&lt;/code&gt; are the valid script names</span><a href="#l570"></a>
<span id="l571"> * accepted and defined by</span><a href="#l571"></a>
<span id="l572"> * {@link java.lang.Character.UnicodeScript#forName(String) UnicodeScript.forName}.</span><a href="#l572"></a>
<span id="l573"> *</span><a href="#l573"></a>
<span id="l574"> * &lt;p&gt;</span><a href="#l574"></a>
<span id="l575"> * &lt;b&gt;&lt;a name=&quot;ubc&quot;&gt;Blocks&lt;/a&gt;&lt;/b&gt; are specified with the prefix {@code In}, as in</span><a href="#l575"></a>
<span id="l576"> * {@code InMongolian}, or by using the keyword {@code block} (or its short</span><a href="#l576"></a>
<span id="l577"> * form {@code blk}) as in {@code block=Mongolian} or {@code blk=Mongolian}.</span><a href="#l577"></a>
<span id="l578"> * &lt;p&gt;</span><a href="#l578"></a>
<span id="l579"> * The block names supported by &lt;code&gt;Pattern&lt;/code&gt; are the valid block names</span><a href="#l579"></a>
<span id="l580"> * accepted and defined by</span><a href="#l580"></a>
<span id="l581"> * {@link java.lang.Character.UnicodeBlock#forName(String) UnicodeBlock.forName}.</span><a href="#l581"></a>
<span id="l582"> * &lt;p&gt;</span><a href="#l582"></a>
<span id="l583"> *</span><a href="#l583"></a>
<span id="l584"> * &lt;b&gt;&lt;a name=&quot;ucc&quot;&gt;Categories&lt;/a&gt;&lt;/b&gt; may be specified with the optional prefix {@code Is}:</span><a href="#l584"></a>
<span id="l585"> * Both {@code \p{L}} and {@code \p{IsL}} denote the category of Unicode</span><a href="#l585"></a>
<span id="l586"> * letters. Same as scripts and blocks, categories can also be specified</span><a href="#l586"></a>
<span id="l587"> * by using the keyword {@code general_category} (or its short form</span><a href="#l587"></a>
<span id="l588"> * {@code gc}) as in {@code general_category=Lu} or {@code gc=Lu}.</span><a href="#l588"></a>
<span id="l589"> * &lt;p&gt;</span><a href="#l589"></a>
<span id="l590"> * The supported categories are those of</span><a href="#l590"></a>
<span id="l591"> * &lt;a href=&quot;http://www.unicode.org/unicode/standard/standard.html&quot;&gt;</span><a href="#l591"></a>
<span id="l592"> * &lt;i&gt;The Unicode Standard&lt;/i&gt;&lt;/a&gt; in the version specified by the</span><a href="#l592"></a>
<span id="l593"> * {@link java.lang.Character Character} class. The category names are those</span><a href="#l593"></a>
<span id="l594"> * defined in the Standard, both normative and informative.</span><a href="#l594"></a>
<span id="l595"> * &lt;p&gt;</span><a href="#l595"></a>
<span id="l596"> *</span><a href="#l596"></a>
<span id="l597"> * &lt;b&gt;&lt;a name=&quot;ubpc&quot;&gt;Binary properties&lt;/a&gt;&lt;/b&gt; are specified with the prefix {@code Is}, as in</span><a href="#l597"></a>
<span id="l598"> * {@code IsAlphabetic}. The supported binary properties by &lt;code&gt;Pattern&lt;/code&gt;</span><a href="#l598"></a>
<span id="l599"> * are</span><a href="#l599"></a>
<span id="l600"> * &lt;ul&gt;</span><a href="#l600"></a>
<span id="l601"> *   &lt;li&gt; Alphabetic</span><a href="#l601"></a>
<span id="l602"> *   &lt;li&gt; Ideographic</span><a href="#l602"></a>
<span id="l603"> *   &lt;li&gt; Letter</span><a href="#l603"></a>
<span id="l604"> *   &lt;li&gt; Lowercase</span><a href="#l604"></a>
<span id="l605"> *   &lt;li&gt; Uppercase</span><a href="#l605"></a>
<span id="l606"> *   &lt;li&gt; Titlecase</span><a href="#l606"></a>
<span id="l607"> *   &lt;li&gt; Punctuation</span><a href="#l607"></a>
<span id="l608"> *   &lt;Li&gt; Control</span><a href="#l608"></a>
<span id="l609"> *   &lt;li&gt; White_Space</span><a href="#l609"></a>
<span id="l610"> *   &lt;li&gt; Digit</span><a href="#l610"></a>
<span id="l611"> *   &lt;li&gt; Hex_Digit</span><a href="#l611"></a>
<span id="l612"> *   &lt;li&gt; Join_Control</span><a href="#l612"></a>
<span id="l613"> *   &lt;li&gt; Noncharacter_Code_Point</span><a href="#l613"></a>
<span id="l614"> *   &lt;li&gt; Assigned</span><a href="#l614"></a>
<span id="l615"> * &lt;/ul&gt;</span><a href="#l615"></a>
<span id="l616"> * &lt;p&gt;</span><a href="#l616"></a>
<span id="l617"> * The following &lt;b&gt;Predefined Character classes&lt;/b&gt; and &lt;b&gt;POSIX character classes&lt;/b&gt;</span><a href="#l617"></a>
<span id="l618"> * are in conformance with the recommendation of &lt;i&gt;Annex C: Compatibility Properties&lt;/i&gt;</span><a href="#l618"></a>
<span id="l619"> * of &lt;a href=&quot;http://www.unicode.org/reports/tr18/&quot;&gt;&lt;i&gt;Unicode Regular Expression</span><a href="#l619"></a>
<span id="l620"> * &lt;/i&gt;&lt;/a&gt;, when {@link #UNICODE_CHARACTER_CLASS} flag is specified.</span><a href="#l620"></a>
<span id="l621"> *</span><a href="#l621"></a>
<span id="l622"> * &lt;table border=&quot;0&quot; cellpadding=&quot;1&quot; cellspacing=&quot;0&quot;</span><a href="#l622"></a>
<span id="l623"> *  summary=&quot;predefined and posix character classes in Unicode mode&quot;&gt;</span><a href="#l623"></a>
<span id="l624"> * &lt;tr align=&quot;left&quot;&gt;</span><a href="#l624"></a>
<span id="l625"> * &lt;th align=&quot;left&quot; id=&quot;predef_classes&quot;&gt;Classes&lt;/th&gt;</span><a href="#l625"></a>
<span id="l626"> * &lt;th align=&quot;left&quot; id=&quot;predef_matches&quot;&gt;Matches&lt;/th&gt;</span><a href="#l626"></a>
<span id="l627"> *&lt;/tr&gt;</span><a href="#l627"></a>
<span id="l628"> * &lt;tr&gt;&lt;td&gt;&lt;tt&gt;\p{Lower}&lt;/tt&gt;&lt;/td&gt;</span><a href="#l628"></a>
<span id="l629"> *     &lt;td&gt;A lowercase character:&lt;tt&gt;\p{IsLowercase}&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l629"></a>
<span id="l630"> * &lt;tr&gt;&lt;td&gt;&lt;tt&gt;\p{Upper}&lt;/tt&gt;&lt;/td&gt;</span><a href="#l630"></a>
<span id="l631"> *     &lt;td&gt;An uppercase character:&lt;tt&gt;\p{IsUppercase}&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l631"></a>
<span id="l632"> * &lt;tr&gt;&lt;td&gt;&lt;tt&gt;\p{ASCII}&lt;/tt&gt;&lt;/td&gt;</span><a href="#l632"></a>
<span id="l633"> *     &lt;td&gt;All ASCII:&lt;tt&gt;[\x00-\x7F]&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l633"></a>
<span id="l634"> * &lt;tr&gt;&lt;td&gt;&lt;tt&gt;\p{Alpha}&lt;/tt&gt;&lt;/td&gt;</span><a href="#l634"></a>
<span id="l635"> *     &lt;td&gt;An alphabetic character:&lt;tt&gt;\p{IsAlphabetic}&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l635"></a>
<span id="l636"> * &lt;tr&gt;&lt;td&gt;&lt;tt&gt;\p{Digit}&lt;/tt&gt;&lt;/td&gt;</span><a href="#l636"></a>
<span id="l637"> *     &lt;td&gt;A decimal digit character:&lt;tt&gt;p{IsDigit}&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l637"></a>
<span id="l638"> * &lt;tr&gt;&lt;td&gt;&lt;tt&gt;\p{Alnum}&lt;/tt&gt;&lt;/td&gt;</span><a href="#l638"></a>
<span id="l639"> *     &lt;td&gt;An alphanumeric character:&lt;tt&gt;[\p{IsAlphabetic}\p{IsDigit}]&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l639"></a>
<span id="l640"> * &lt;tr&gt;&lt;td&gt;&lt;tt&gt;\p{Punct}&lt;/tt&gt;&lt;/td&gt;</span><a href="#l640"></a>
<span id="l641"> *     &lt;td&gt;A punctuation character:&lt;tt&gt;p{IsPunctuation}&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l641"></a>
<span id="l642"> * &lt;tr&gt;&lt;td&gt;&lt;tt&gt;\p{Graph}&lt;/tt&gt;&lt;/td&gt;</span><a href="#l642"></a>
<span id="l643"> *     &lt;td&gt;A visible character: &lt;tt&gt;[^\p{IsWhite_Space}\p{gc=Cc}\p{gc=Cs}\p{gc=Cn}]&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l643"></a>
<span id="l644"> * &lt;tr&gt;&lt;td&gt;&lt;tt&gt;\p{Print}&lt;/tt&gt;&lt;/td&gt;</span><a href="#l644"></a>
<span id="l645"> *     &lt;td&gt;A printable character: {@code [\p{Graph}\p{Blank}&amp;&amp;[^\p{Cntrl}]]}&lt;/td&gt;&lt;/tr&gt;</span><a href="#l645"></a>
<span id="l646"> * &lt;tr&gt;&lt;td&gt;&lt;tt&gt;\p{Blank}&lt;/tt&gt;&lt;/td&gt;</span><a href="#l646"></a>
<span id="l647"> *     &lt;td&gt;A space or a tab: {@code [\p{IsWhite_Space}&amp;&amp;[^\p{gc=Zl}\p{gc=Zp}\x0a\x0b\x0c\x0d\x85]]}&lt;/td&gt;&lt;/tr&gt;</span><a href="#l647"></a>
<span id="l648"> * &lt;tr&gt;&lt;td&gt;&lt;tt&gt;\p{Cntrl}&lt;/tt&gt;&lt;/td&gt;</span><a href="#l648"></a>
<span id="l649"> *     &lt;td&gt;A control character: &lt;tt&gt;\p{gc=Cc}&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l649"></a>
<span id="l650"> * &lt;tr&gt;&lt;td&gt;&lt;tt&gt;\p{XDigit}&lt;/tt&gt;&lt;/td&gt;</span><a href="#l650"></a>
<span id="l651"> *     &lt;td&gt;A hexadecimal digit: &lt;tt&gt;[\p{gc=Nd}\p{IsHex_Digit}]&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l651"></a>
<span id="l652"> * &lt;tr&gt;&lt;td&gt;&lt;tt&gt;\p{Space}&lt;/tt&gt;&lt;/td&gt;</span><a href="#l652"></a>
<span id="l653"> *     &lt;td&gt;A whitespace character:&lt;tt&gt;\p{IsWhite_Space}&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l653"></a>
<span id="l654"> * &lt;tr&gt;&lt;td&gt;&lt;tt&gt;\d&lt;/tt&gt;&lt;/td&gt;</span><a href="#l654"></a>
<span id="l655"> *     &lt;td&gt;A digit: &lt;tt&gt;\p{IsDigit}&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l655"></a>
<span id="l656"> * &lt;tr&gt;&lt;td&gt;&lt;tt&gt;\D&lt;/tt&gt;&lt;/td&gt;</span><a href="#l656"></a>
<span id="l657"> *     &lt;td&gt;A non-digit: &lt;tt&gt;[^\d]&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l657"></a>
<span id="l658"> * &lt;tr&gt;&lt;td&gt;&lt;tt&gt;\s&lt;/tt&gt;&lt;/td&gt;</span><a href="#l658"></a>
<span id="l659"> *     &lt;td&gt;A whitespace character: &lt;tt&gt;\p{IsWhite_Space}&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l659"></a>
<span id="l660"> * &lt;tr&gt;&lt;td&gt;&lt;tt&gt;\S&lt;/tt&gt;&lt;/td&gt;</span><a href="#l660"></a>
<span id="l661"> *     &lt;td&gt;A non-whitespace character: &lt;tt&gt;[^\s]&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l661"></a>
<span id="l662"> * &lt;tr&gt;&lt;td&gt;&lt;tt&gt;\w&lt;/tt&gt;&lt;/td&gt;</span><a href="#l662"></a>
<span id="l663"> *     &lt;td&gt;A word character: &lt;tt&gt;[\p{Alpha}\p{gc=Mn}\p{gc=Me}\p{gc=Mc}\p{Digit}\p{gc=Pc}\p{IsJoin_Control}]&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l663"></a>
<span id="l664"> * &lt;tr&gt;&lt;td&gt;&lt;tt&gt;\W&lt;/tt&gt;&lt;/td&gt;</span><a href="#l664"></a>
<span id="l665"> *     &lt;td&gt;A non-word character: &lt;tt&gt;[^\w]&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l665"></a>
<span id="l666"> * &lt;/table&gt;</span><a href="#l666"></a>
<span id="l667"> * &lt;p&gt;</span><a href="#l667"></a>
<span id="l668"> * &lt;a name=&quot;jcc&quot;&gt;</span><a href="#l668"></a>
<span id="l669"> * Categories that behave like the java.lang.Character</span><a href="#l669"></a>
<span id="l670"> * boolean is&lt;i&gt;methodname&lt;/i&gt; methods (except for the deprecated ones) are</span><a href="#l670"></a>
<span id="l671"> * available through the same &lt;tt&gt;\p{&lt;/tt&gt;&lt;i&gt;prop&lt;/i&gt;&lt;tt&gt;}&lt;/tt&gt; syntax where</span><a href="#l671"></a>
<span id="l672"> * the specified property has the name &lt;tt&gt;java&lt;i&gt;methodname&lt;/i&gt;&lt;/tt&gt;&lt;/a&gt;.</span><a href="#l672"></a>
<span id="l673"> *</span><a href="#l673"></a>
<span id="l674"> * &lt;h3&gt; Comparison to Perl 5 &lt;/h3&gt;</span><a href="#l674"></a>
<span id="l675"> *</span><a href="#l675"></a>
<span id="l676"> * &lt;p&gt;The &lt;code&gt;Pattern&lt;/code&gt; engine performs traditional NFA-based matching</span><a href="#l676"></a>
<span id="l677"> * with ordered alternation as occurs in Perl 5.</span><a href="#l677"></a>
<span id="l678"> *</span><a href="#l678"></a>
<span id="l679"> * &lt;p&gt; Perl constructs not supported by this class: &lt;/p&gt;</span><a href="#l679"></a>
<span id="l680"> *</span><a href="#l680"></a>
<span id="l681"> * &lt;ul&gt;</span><a href="#l681"></a>
<span id="l682"> *    &lt;li&gt;&lt;p&gt; Predefined character classes (Unicode character)</span><a href="#l682"></a>
<span id="l683"> *    &lt;p&gt;&lt;tt&gt;\X&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/tt&gt;Match Unicode</span><a href="#l683"></a>
<span id="l684"> *    &lt;a href=&quot;http://www.unicode.org/reports/tr18/#Default_Grapheme_Clusters&quot;&gt;</span><a href="#l684"></a>
<span id="l685"> *    &lt;i&gt;extended grapheme cluster&lt;/i&gt;&lt;/a&gt;</span><a href="#l685"></a>
<span id="l686"> *    &lt;/p&gt;&lt;/li&gt;</span><a href="#l686"></a>
<span id="l687"> *</span><a href="#l687"></a>
<span id="l688"> *    &lt;li&gt;&lt;p&gt; The backreference constructs, &lt;tt&gt;\g{&lt;/tt&gt;&lt;i&gt;n&lt;/i&gt;&lt;tt&gt;}&lt;/tt&gt; for</span><a href="#l688"></a>
<span id="l689"> *    the &lt;i&gt;n&lt;/i&gt;&lt;sup&gt;th&lt;/sup&gt;&lt;a href=&quot;#cg&quot;&gt;capturing group&lt;/a&gt; and</span><a href="#l689"></a>
<span id="l690"> *    &lt;tt&gt;\g{&lt;/tt&gt;&lt;i&gt;name&lt;/i&gt;&lt;tt&gt;}&lt;/tt&gt; for</span><a href="#l690"></a>
<span id="l691"> *    &lt;a href=&quot;#groupname&quot;&gt;named-capturing group&lt;/a&gt;.</span><a href="#l691"></a>
<span id="l692"> *    &lt;/p&gt;&lt;/li&gt;</span><a href="#l692"></a>
<span id="l693"> *</span><a href="#l693"></a>
<span id="l694"> *    &lt;li&gt;&lt;p&gt; The named character construct, &lt;tt&gt;\N{&lt;/tt&gt;&lt;i&gt;name&lt;/i&gt;&lt;tt&gt;}&lt;/tt&gt;</span><a href="#l694"></a>
<span id="l695"> *    for a Unicode character by its name.</span><a href="#l695"></a>
<span id="l696"> *    &lt;/p&gt;&lt;/li&gt;</span><a href="#l696"></a>
<span id="l697"> *</span><a href="#l697"></a>
<span id="l698"> *    &lt;li&gt;&lt;p&gt; The conditional constructs</span><a href="#l698"></a>
<span id="l699"> *    &lt;tt&gt;(?(&lt;/tt&gt;&lt;i&gt;condition&lt;/i&gt;&lt;tt&gt;)&lt;/tt&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;)&lt;/tt&gt; and</span><a href="#l699"></a>
<span id="l700"> *    &lt;tt&gt;(?(&lt;/tt&gt;&lt;i&gt;condition&lt;/i&gt;&lt;tt&gt;)&lt;/tt&gt;&lt;i&gt;X&lt;/i&gt;&lt;tt&gt;|&lt;/tt&gt;&lt;i&gt;Y&lt;/i&gt;&lt;tt&gt;)&lt;/tt&gt;,</span><a href="#l700"></a>
<span id="l701"> *    &lt;/p&gt;&lt;/li&gt;</span><a href="#l701"></a>
<span id="l702"> *</span><a href="#l702"></a>
<span id="l703"> *    &lt;li&gt;&lt;p&gt; The embedded code constructs &lt;tt&gt;(?{&lt;/tt&gt;&lt;i&gt;code&lt;/i&gt;&lt;tt&gt;})&lt;/tt&gt;</span><a href="#l703"></a>
<span id="l704"> *    and &lt;tt&gt;(??{&lt;/tt&gt;&lt;i&gt;code&lt;/i&gt;&lt;tt&gt;})&lt;/tt&gt;,&lt;/p&gt;&lt;/li&gt;</span><a href="#l704"></a>
<span id="l705"> *</span><a href="#l705"></a>
<span id="l706"> *    &lt;li&gt;&lt;p&gt; The embedded comment syntax &lt;tt&gt;(?#comment)&lt;/tt&gt;, and &lt;/p&gt;&lt;/li&gt;</span><a href="#l706"></a>
<span id="l707"> *</span><a href="#l707"></a>
<span id="l708"> *    &lt;li&gt;&lt;p&gt; The preprocessing operations &lt;tt&gt;\l&lt;/tt&gt; &lt;tt&gt;&amp;#92;u&lt;/tt&gt;,</span><a href="#l708"></a>
<span id="l709"> *    &lt;tt&gt;\L&lt;/tt&gt;, and &lt;tt&gt;\U&lt;/tt&gt;.  &lt;/p&gt;&lt;/li&gt;</span><a href="#l709"></a>
<span id="l710"> *</span><a href="#l710"></a>
<span id="l711"> * &lt;/ul&gt;</span><a href="#l711"></a>
<span id="l712"> *</span><a href="#l712"></a>
<span id="l713"> * &lt;p&gt; Constructs supported by this class but not by Perl: &lt;/p&gt;</span><a href="#l713"></a>
<span id="l714"> *</span><a href="#l714"></a>
<span id="l715"> * &lt;ul&gt;</span><a href="#l715"></a>
<span id="l716"> *</span><a href="#l716"></a>
<span id="l717"> *    &lt;li&gt;&lt;p&gt; Character-class union and intersection as described</span><a href="#l717"></a>
<span id="l718"> *    &lt;a href=&quot;#cc&quot;&gt;above&lt;/a&gt;.&lt;/p&gt;&lt;/li&gt;</span><a href="#l718"></a>
<span id="l719"> *</span><a href="#l719"></a>
<span id="l720"> * &lt;/ul&gt;</span><a href="#l720"></a>
<span id="l721"> *</span><a href="#l721"></a>
<span id="l722"> * &lt;p&gt; Notable differences from Perl: &lt;/p&gt;</span><a href="#l722"></a>
<span id="l723"> *</span><a href="#l723"></a>
<span id="l724"> * &lt;ul&gt;</span><a href="#l724"></a>
<span id="l725"> *</span><a href="#l725"></a>
<span id="l726"> *    &lt;li&gt;&lt;p&gt; In Perl, &lt;tt&gt;\1&lt;/tt&gt; through &lt;tt&gt;\9&lt;/tt&gt; are always interpreted</span><a href="#l726"></a>
<span id="l727"> *    as back references; a backslash-escaped number greater than &lt;tt&gt;9&lt;/tt&gt; is</span><a href="#l727"></a>
<span id="l728"> *    treated as a back reference if at least that many subexpressions exist,</span><a href="#l728"></a>
<span id="l729"> *    otherwise it is interpreted, if possible, as an octal escape.  In this</span><a href="#l729"></a>
<span id="l730"> *    class octal escapes must always begin with a zero. In this class,</span><a href="#l730"></a>
<span id="l731"> *    &lt;tt&gt;\1&lt;/tt&gt; through &lt;tt&gt;\9&lt;/tt&gt; are always interpreted as back</span><a href="#l731"></a>
<span id="l732"> *    references, and a larger number is accepted as a back reference if at</span><a href="#l732"></a>
<span id="l733"> *    least that many subexpressions exist at that point in the regular</span><a href="#l733"></a>
<span id="l734"> *    expression, otherwise the parser will drop digits until the number is</span><a href="#l734"></a>
<span id="l735"> *    smaller or equal to the existing number of groups or it is one digit.</span><a href="#l735"></a>
<span id="l736"> *    &lt;/p&gt;&lt;/li&gt;</span><a href="#l736"></a>
<span id="l737"> *</span><a href="#l737"></a>
<span id="l738"> *    &lt;li&gt;&lt;p&gt; Perl uses the &lt;tt&gt;g&lt;/tt&gt; flag to request a match that resumes</span><a href="#l738"></a>
<span id="l739"> *    where the last match left off.  This functionality is provided implicitly</span><a href="#l739"></a>
<span id="l740"> *    by the {@link Matcher} class: Repeated invocations of the {@link</span><a href="#l740"></a>
<span id="l741"> *    Matcher#find find} method will resume where the last match left off,</span><a href="#l741"></a>
<span id="l742"> *    unless the matcher is reset.  &lt;/p&gt;&lt;/li&gt;</span><a href="#l742"></a>
<span id="l743"> *</span><a href="#l743"></a>
<span id="l744"> *    &lt;li&gt;&lt;p&gt; In Perl, embedded flags at the top level of an expression affect</span><a href="#l744"></a>
<span id="l745"> *    the whole expression.  In this class, embedded flags always take effect</span><a href="#l745"></a>
<span id="l746"> *    at the point at which they appear, whether they are at the top level or</span><a href="#l746"></a>
<span id="l747"> *    within a group; in the latter case, flags are restored at the end of the</span><a href="#l747"></a>
<span id="l748"> *    group just as in Perl.  &lt;/p&gt;&lt;/li&gt;</span><a href="#l748"></a>
<span id="l749"> *</span><a href="#l749"></a>
<span id="l750"> * &lt;/ul&gt;</span><a href="#l750"></a>
<span id="l751"> *</span><a href="#l751"></a>
<span id="l752"> *</span><a href="#l752"></a>
<span id="l753"> * &lt;p&gt; For a more precise description of the behavior of regular expression</span><a href="#l753"></a>
<span id="l754"> * constructs, please see &lt;a href=&quot;http://www.oreilly.com/catalog/regex3/&quot;&gt;</span><a href="#l754"></a>
<span id="l755"> * &lt;i&gt;Mastering Regular Expressions, 3nd Edition&lt;/i&gt;, Jeffrey E. F. Friedl,</span><a href="#l755"></a>
<span id="l756"> * O'Reilly and Associates, 2006.&lt;/a&gt;</span><a href="#l756"></a>
<span id="l757"> * &lt;/p&gt;</span><a href="#l757"></a>
<span id="l758"> *</span><a href="#l758"></a>
<span id="l759"> * @see java.lang.String#split(String, int)</span><a href="#l759"></a>
<span id="l760"> * @see java.lang.String#split(String)</span><a href="#l760"></a>
<span id="l761"> *</span><a href="#l761"></a>
<span id="l762"> * @author      Mike McCloskey</span><a href="#l762"></a>
<span id="l763"> * @author      Mark Reinhold</span><a href="#l763"></a>
<span id="l764"> * @author      JSR-51 Expert Group</span><a href="#l764"></a>
<span id="l765"> * @since       1.4</span><a href="#l765"></a>
<span id="l766"> * @spec        JSR-51</span><a href="#l766"></a>
<span id="l767"> */</span><a href="#l767"></a>
<span id="l768"></span><a href="#l768"></a>
<span id="l769">public final class Pattern</span><a href="#l769"></a>
<span id="l770">    implements java.io.Serializable</span><a href="#l770"></a>
<span id="l771">{</span><a href="#l771"></a>
<span id="l772"></span><a href="#l772"></a>
<span id="l773">    /**</span><a href="#l773"></a>
<span id="l774">     * Regular expression modifier values.  Instead of being passed as</span><a href="#l774"></a>
<span id="l775">     * arguments, they can also be passed as inline modifiers.</span><a href="#l775"></a>
<span id="l776">     * For example, the following statements have the same effect.</span><a href="#l776"></a>
<span id="l777">     * &lt;pre&gt;</span><a href="#l777"></a>
<span id="l778">     * RegExp r1 = RegExp.compile(&quot;abc&quot;, Pattern.I|Pattern.M);</span><a href="#l778"></a>
<span id="l779">     * RegExp r2 = RegExp.compile(&quot;(?im)abc&quot;, 0);</span><a href="#l779"></a>
<span id="l780">     * &lt;/pre&gt;</span><a href="#l780"></a>
<span id="l781">     *</span><a href="#l781"></a>
<span id="l782">     * The flags are duplicated so that the familiar Perl match flag</span><a href="#l782"></a>
<span id="l783">     * names are available.</span><a href="#l783"></a>
<span id="l784">     */</span><a href="#l784"></a>
<span id="l785"></span><a href="#l785"></a>
<span id="l786">    /**</span><a href="#l786"></a>
<span id="l787">     * Enables Unix lines mode.</span><a href="#l787"></a>
<span id="l788">     *</span><a href="#l788"></a>
<span id="l789">     * &lt;p&gt; In this mode, only the &lt;tt&gt;'\n'&lt;/tt&gt; line terminator is recognized</span><a href="#l789"></a>
<span id="l790">     * in the behavior of &lt;tt&gt;.&lt;/tt&gt;, &lt;tt&gt;^&lt;/tt&gt;, and &lt;tt&gt;$&lt;/tt&gt;.</span><a href="#l790"></a>
<span id="l791">     *</span><a href="#l791"></a>
<span id="l792">     * &lt;p&gt; Unix lines mode can also be enabled via the embedded flag</span><a href="#l792"></a>
<span id="l793">     * expression&amp;nbsp;&lt;tt&gt;(?d)&lt;/tt&gt;.</span><a href="#l793"></a>
<span id="l794">     */</span><a href="#l794"></a>
<span id="l795">    public static final int UNIX_LINES = 0x01;</span><a href="#l795"></a>
<span id="l796"></span><a href="#l796"></a>
<span id="l797">    /**</span><a href="#l797"></a>
<span id="l798">     * Enables case-insensitive matching.</span><a href="#l798"></a>
<span id="l799">     *</span><a href="#l799"></a>
<span id="l800">     * &lt;p&gt; By default, case-insensitive matching assumes that only characters</span><a href="#l800"></a>
<span id="l801">     * in the US-ASCII charset are being matched.  Unicode-aware</span><a href="#l801"></a>
<span id="l802">     * case-insensitive matching can be enabled by specifying the {@link</span><a href="#l802"></a>
<span id="l803">     * #UNICODE_CASE} flag in conjunction with this flag.</span><a href="#l803"></a>
<span id="l804">     *</span><a href="#l804"></a>
<span id="l805">     * &lt;p&gt; Case-insensitive matching can also be enabled via the embedded flag</span><a href="#l805"></a>
<span id="l806">     * expression&amp;nbsp;&lt;tt&gt;(?i)&lt;/tt&gt;.</span><a href="#l806"></a>
<span id="l807">     *</span><a href="#l807"></a>
<span id="l808">     * &lt;p&gt; Specifying this flag may impose a slight performance penalty.  &lt;/p&gt;</span><a href="#l808"></a>
<span id="l809">     */</span><a href="#l809"></a>
<span id="l810">    public static final int CASE_INSENSITIVE = 0x02;</span><a href="#l810"></a>
<span id="l811"></span><a href="#l811"></a>
<span id="l812">    /**</span><a href="#l812"></a>
<span id="l813">     * Permits whitespace and comments in pattern.</span><a href="#l813"></a>
<span id="l814">     *</span><a href="#l814"></a>
<span id="l815">     * &lt;p&gt; In this mode, whitespace is ignored, and embedded comments starting</span><a href="#l815"></a>
<span id="l816">     * with &lt;tt&gt;#&lt;/tt&gt; are ignored until the end of a line.</span><a href="#l816"></a>
<span id="l817">     *</span><a href="#l817"></a>
<span id="l818">     * &lt;p&gt; Comments mode can also be enabled via the embedded flag</span><a href="#l818"></a>
<span id="l819">     * expression&amp;nbsp;&lt;tt&gt;(?x)&lt;/tt&gt;.</span><a href="#l819"></a>
<span id="l820">     */</span><a href="#l820"></a>
<span id="l821">    public static final int COMMENTS = 0x04;</span><a href="#l821"></a>
<span id="l822"></span><a href="#l822"></a>
<span id="l823">    /**</span><a href="#l823"></a>
<span id="l824">     * Enables multiline mode.</span><a href="#l824"></a>
<span id="l825">     *</span><a href="#l825"></a>
<span id="l826">     * &lt;p&gt; In multiline mode the expressions &lt;tt&gt;^&lt;/tt&gt; and &lt;tt&gt;$&lt;/tt&gt; match</span><a href="#l826"></a>
<span id="l827">     * just after or just before, respectively, a line terminator or the end of</span><a href="#l827"></a>
<span id="l828">     * the input sequence.  By default these expressions only match at the</span><a href="#l828"></a>
<span id="l829">     * beginning and the end of the entire input sequence.</span><a href="#l829"></a>
<span id="l830">     *</span><a href="#l830"></a>
<span id="l831">     * &lt;p&gt; Multiline mode can also be enabled via the embedded flag</span><a href="#l831"></a>
<span id="l832">     * expression&amp;nbsp;&lt;tt&gt;(?m)&lt;/tt&gt;.  &lt;/p&gt;</span><a href="#l832"></a>
<span id="l833">     */</span><a href="#l833"></a>
<span id="l834">    public static final int MULTILINE = 0x08;</span><a href="#l834"></a>
<span id="l835"></span><a href="#l835"></a>
<span id="l836">    /**</span><a href="#l836"></a>
<span id="l837">     * Enables literal parsing of the pattern.</span><a href="#l837"></a>
<span id="l838">     *</span><a href="#l838"></a>
<span id="l839">     * &lt;p&gt; When this flag is specified then the input string that specifies</span><a href="#l839"></a>
<span id="l840">     * the pattern is treated as a sequence of literal characters.</span><a href="#l840"></a>
<span id="l841">     * Metacharacters or escape sequences in the input sequence will be</span><a href="#l841"></a>
<span id="l842">     * given no special meaning.</span><a href="#l842"></a>
<span id="l843">     *</span><a href="#l843"></a>
<span id="l844">     * &lt;p&gt;The flags CASE_INSENSITIVE and UNICODE_CASE retain their impact on</span><a href="#l844"></a>
<span id="l845">     * matching when used in conjunction with this flag. The other flags</span><a href="#l845"></a>
<span id="l846">     * become superfluous.</span><a href="#l846"></a>
<span id="l847">     *</span><a href="#l847"></a>
<span id="l848">     * &lt;p&gt; There is no embedded flag character for enabling literal parsing.</span><a href="#l848"></a>
<span id="l849">     * @since 1.5</span><a href="#l849"></a>
<span id="l850">     */</span><a href="#l850"></a>
<span id="l851">    public static final int LITERAL = 0x10;</span><a href="#l851"></a>
<span id="l852"></span><a href="#l852"></a>
<span id="l853">    /**</span><a href="#l853"></a>
<span id="l854">     * Enables dotall mode.</span><a href="#l854"></a>
<span id="l855">     *</span><a href="#l855"></a>
<span id="l856">     * &lt;p&gt; In dotall mode, the expression &lt;tt&gt;.&lt;/tt&gt; matches any character,</span><a href="#l856"></a>
<span id="l857">     * including a line terminator.  By default this expression does not match</span><a href="#l857"></a>
<span id="l858">     * line terminators.</span><a href="#l858"></a>
<span id="l859">     *</span><a href="#l859"></a>
<span id="l860">     * &lt;p&gt; Dotall mode can also be enabled via the embedded flag</span><a href="#l860"></a>
<span id="l861">     * expression&amp;nbsp;&lt;tt&gt;(?s)&lt;/tt&gt;.  (The &lt;tt&gt;s&lt;/tt&gt; is a mnemonic for</span><a href="#l861"></a>
<span id="l862">     * &quot;single-line&quot; mode, which is what this is called in Perl.)  &lt;/p&gt;</span><a href="#l862"></a>
<span id="l863">     */</span><a href="#l863"></a>
<span id="l864">    public static final int DOTALL = 0x20;</span><a href="#l864"></a>
<span id="l865"></span><a href="#l865"></a>
<span id="l866">    /**</span><a href="#l866"></a>
<span id="l867">     * Enables Unicode-aware case folding.</span><a href="#l867"></a>
<span id="l868">     *</span><a href="#l868"></a>
<span id="l869">     * &lt;p&gt; When this flag is specified then case-insensitive matching, when</span><a href="#l869"></a>
<span id="l870">     * enabled by the {@link #CASE_INSENSITIVE} flag, is done in a manner</span><a href="#l870"></a>
<span id="l871">     * consistent with the Unicode Standard.  By default, case-insensitive</span><a href="#l871"></a>
<span id="l872">     * matching assumes that only characters in the US-ASCII charset are being</span><a href="#l872"></a>
<span id="l873">     * matched.</span><a href="#l873"></a>
<span id="l874">     *</span><a href="#l874"></a>
<span id="l875">     * &lt;p&gt; Unicode-aware case folding can also be enabled via the embedded flag</span><a href="#l875"></a>
<span id="l876">     * expression&amp;nbsp;&lt;tt&gt;(?u)&lt;/tt&gt;.</span><a href="#l876"></a>
<span id="l877">     *</span><a href="#l877"></a>
<span id="l878">     * &lt;p&gt; Specifying this flag may impose a performance penalty.  &lt;/p&gt;</span><a href="#l878"></a>
<span id="l879">     */</span><a href="#l879"></a>
<span id="l880">    public static final int UNICODE_CASE = 0x40;</span><a href="#l880"></a>
<span id="l881"></span><a href="#l881"></a>
<span id="l882">    /**</span><a href="#l882"></a>
<span id="l883">     * Enables canonical equivalence.</span><a href="#l883"></a>
<span id="l884">     *</span><a href="#l884"></a>
<span id="l885">     * &lt;p&gt; When this flag is specified then two characters will be considered</span><a href="#l885"></a>
<span id="l886">     * to match if, and only if, their full canonical decompositions match.</span><a href="#l886"></a>
<span id="l887">     * The expression &lt;tt&gt;&quot;a&amp;#92;u030A&quot;&lt;/tt&gt;, for example, will match the</span><a href="#l887"></a>
<span id="l888">     * string &lt;tt&gt;&quot;&amp;#92;u00E5&quot;&lt;/tt&gt; when this flag is specified.  By default,</span><a href="#l888"></a>
<span id="l889">     * matching does not take canonical equivalence into account.</span><a href="#l889"></a>
<span id="l890">     *</span><a href="#l890"></a>
<span id="l891">     * &lt;p&gt; There is no embedded flag character for enabling canonical</span><a href="#l891"></a>
<span id="l892">     * equivalence.</span><a href="#l892"></a>
<span id="l893">     *</span><a href="#l893"></a>
<span id="l894">     * &lt;p&gt; Specifying this flag may impose a performance penalty.  &lt;/p&gt;</span><a href="#l894"></a>
<span id="l895">     */</span><a href="#l895"></a>
<span id="l896">    public static final int CANON_EQ = 0x80;</span><a href="#l896"></a>
<span id="l897"></span><a href="#l897"></a>
<span id="l898">    /**</span><a href="#l898"></a>
<span id="l899">     * Enables the Unicode version of &lt;i&gt;Predefined character classes&lt;/i&gt; and</span><a href="#l899"></a>
<span id="l900">     * &lt;i&gt;POSIX character classes&lt;/i&gt;.</span><a href="#l900"></a>
<span id="l901">     *</span><a href="#l901"></a>
<span id="l902">     * &lt;p&gt; When this flag is specified then the (US-ASCII only)</span><a href="#l902"></a>
<span id="l903">     * &lt;i&gt;Predefined character classes&lt;/i&gt; and &lt;i&gt;POSIX character classes&lt;/i&gt;</span><a href="#l903"></a>
<span id="l904">     * are in conformance with</span><a href="#l904"></a>
<span id="l905">     * &lt;a href=&quot;http://www.unicode.org/reports/tr18/&quot;&gt;&lt;i&gt;Unicode Technical</span><a href="#l905"></a>
<span id="l906">     * Standard #18: Unicode Regular Expression&lt;/i&gt;&lt;/a&gt;</span><a href="#l906"></a>
<span id="l907">     * &lt;i&gt;Annex C: Compatibility Properties&lt;/i&gt;.</span><a href="#l907"></a>
<span id="l908">     * &lt;p&gt;</span><a href="#l908"></a>
<span id="l909">     * The UNICODE_CHARACTER_CLASS mode can also be enabled via the embedded</span><a href="#l909"></a>
<span id="l910">     * flag expression&amp;nbsp;&lt;tt&gt;(?U)&lt;/tt&gt;.</span><a href="#l910"></a>
<span id="l911">     * &lt;p&gt;</span><a href="#l911"></a>
<span id="l912">     * The flag implies UNICODE_CASE, that is, it enables Unicode-aware case</span><a href="#l912"></a>
<span id="l913">     * folding.</span><a href="#l913"></a>
<span id="l914">     * &lt;p&gt;</span><a href="#l914"></a>
<span id="l915">     * Specifying this flag may impose a performance penalty.  &lt;/p&gt;</span><a href="#l915"></a>
<span id="l916">     * @since 1.7</span><a href="#l916"></a>
<span id="l917">     */</span><a href="#l917"></a>
<span id="l918">    public static final int UNICODE_CHARACTER_CLASS = 0x100;</span><a href="#l918"></a>
<span id="l919"></span><a href="#l919"></a>
<span id="l920">    /* Pattern has only two serialized components: The pattern string</span><a href="#l920"></a>
<span id="l921">     * and the flags, which are all that is needed to recompile the pattern</span><a href="#l921"></a>
<span id="l922">     * when it is deserialized.</span><a href="#l922"></a>
<span id="l923">     */</span><a href="#l923"></a>
<span id="l924"></span><a href="#l924"></a>
<span id="l925">    /** use serialVersionUID from Merlin b59 for interoperability */</span><a href="#l925"></a>
<span id="l926">    private static final long serialVersionUID = 5073258162644648461L;</span><a href="#l926"></a>
<span id="l927"></span><a href="#l927"></a>
<span id="l928">    /**</span><a href="#l928"></a>
<span id="l929">     * The original regular-expression pattern string.</span><a href="#l929"></a>
<span id="l930">     *</span><a href="#l930"></a>
<span id="l931">     * @serial</span><a href="#l931"></a>
<span id="l932">     */</span><a href="#l932"></a>
<span id="l933">    private String pattern;</span><a href="#l933"></a>
<span id="l934"></span><a href="#l934"></a>
<span id="l935">    /**</span><a href="#l935"></a>
<span id="l936">     * The original pattern flags.</span><a href="#l936"></a>
<span id="l937">     *</span><a href="#l937"></a>
<span id="l938">     * @serial</span><a href="#l938"></a>
<span id="l939">     */</span><a href="#l939"></a>
<span id="l940">    private int flags;</span><a href="#l940"></a>
<span id="l941"></span><a href="#l941"></a>
<span id="l942">    /**</span><a href="#l942"></a>
<span id="l943">     * Boolean indicating this Pattern is compiled; this is necessary in order</span><a href="#l943"></a>
<span id="l944">     * to lazily compile deserialized Patterns.</span><a href="#l944"></a>
<span id="l945">     */</span><a href="#l945"></a>
<span id="l946">    private transient volatile boolean compiled = false;</span><a href="#l946"></a>
<span id="l947"></span><a href="#l947"></a>
<span id="l948">    /**</span><a href="#l948"></a>
<span id="l949">     * The normalized pattern string.</span><a href="#l949"></a>
<span id="l950">     */</span><a href="#l950"></a>
<span id="l951">    private transient String normalizedPattern;</span><a href="#l951"></a>
<span id="l952"></span><a href="#l952"></a>
<span id="l953">    /**</span><a href="#l953"></a>
<span id="l954">     * The starting point of state machine for the find operation.  This allows</span><a href="#l954"></a>
<span id="l955">     * a match to start anywhere in the input.</span><a href="#l955"></a>
<span id="l956">     */</span><a href="#l956"></a>
<span id="l957">    transient Node root;</span><a href="#l957"></a>
<span id="l958"></span><a href="#l958"></a>
<span id="l959">    /**</span><a href="#l959"></a>
<span id="l960">     * The root of object tree for a match operation.  The pattern is matched</span><a href="#l960"></a>
<span id="l961">     * at the beginning.  This may include a find that uses BnM or a First</span><a href="#l961"></a>
<span id="l962">     * node.</span><a href="#l962"></a>
<span id="l963">     */</span><a href="#l963"></a>
<span id="l964">    transient Node matchRoot;</span><a href="#l964"></a>
<span id="l965"></span><a href="#l965"></a>
<span id="l966">    /**</span><a href="#l966"></a>
<span id="l967">     * Temporary storage used by parsing pattern slice.</span><a href="#l967"></a>
<span id="l968">     */</span><a href="#l968"></a>
<span id="l969">    transient int[] buffer;</span><a href="#l969"></a>
<span id="l970"></span><a href="#l970"></a>
<span id="l971">    /**</span><a href="#l971"></a>
<span id="l972">     * Map the &quot;name&quot; of the &quot;named capturing group&quot; to its group id</span><a href="#l972"></a>
<span id="l973">     * node.</span><a href="#l973"></a>
<span id="l974">     */</span><a href="#l974"></a>
<span id="l975">    transient volatile Map&lt;String, Integer&gt; namedGroups;</span><a href="#l975"></a>
<span id="l976"></span><a href="#l976"></a>
<span id="l977">    /**</span><a href="#l977"></a>
<span id="l978">     * Temporary storage used while parsing group references.</span><a href="#l978"></a>
<span id="l979">     */</span><a href="#l979"></a>
<span id="l980">    transient GroupHead[] groupNodes;</span><a href="#l980"></a>
<span id="l981"></span><a href="#l981"></a>
<span id="l982">    /**</span><a href="#l982"></a>
<span id="l983">     * Temporary null terminated code point array used by pattern compiling.</span><a href="#l983"></a>
<span id="l984">     */</span><a href="#l984"></a>
<span id="l985">    private transient int[] temp;</span><a href="#l985"></a>
<span id="l986"></span><a href="#l986"></a>
<span id="l987">    /**</span><a href="#l987"></a>
<span id="l988">     * The number of capturing groups in this Pattern. Used by matchers to</span><a href="#l988"></a>
<span id="l989">     * allocate storage needed to perform a match.</span><a href="#l989"></a>
<span id="l990">     */</span><a href="#l990"></a>
<span id="l991">    transient int capturingGroupCount;</span><a href="#l991"></a>
<span id="l992"></span><a href="#l992"></a>
<span id="l993">    /**</span><a href="#l993"></a>
<span id="l994">     * The local variable count used by parsing tree. Used by matchers to</span><a href="#l994"></a>
<span id="l995">     * allocate storage needed to perform a match.</span><a href="#l995"></a>
<span id="l996">     */</span><a href="#l996"></a>
<span id="l997">    transient int localCount;</span><a href="#l997"></a>
<span id="l998"></span><a href="#l998"></a>
<span id="l999">    /**</span><a href="#l999"></a>
<span id="l1000">     * Index into the pattern string that keeps track of how much has been</span><a href="#l1000"></a>
<span id="l1001">     * parsed.</span><a href="#l1001"></a>
<span id="l1002">     */</span><a href="#l1002"></a>
<span id="l1003">    private transient int cursor;</span><a href="#l1003"></a>
<span id="l1004"></span><a href="#l1004"></a>
<span id="l1005">    /**</span><a href="#l1005"></a>
<span id="l1006">     * Holds the length of the pattern string.</span><a href="#l1006"></a>
<span id="l1007">     */</span><a href="#l1007"></a>
<span id="l1008">    private transient int patternLength;</span><a href="#l1008"></a>
<span id="l1009"></span><a href="#l1009"></a>
<span id="l1010">    /**</span><a href="#l1010"></a>
<span id="l1011">     * If the Start node might possibly match supplementary characters.</span><a href="#l1011"></a>
<span id="l1012">     * It is set to true during compiling if</span><a href="#l1012"></a>
<span id="l1013">     * (1) There is supplementary char in pattern, or</span><a href="#l1013"></a>
<span id="l1014">     * (2) There is complement node of Category or Block</span><a href="#l1014"></a>
<span id="l1015">     */</span><a href="#l1015"></a>
<span id="l1016">    private transient boolean hasSupplementary;</span><a href="#l1016"></a>
<span id="l1017"></span><a href="#l1017"></a>
<span id="l1018">    /**</span><a href="#l1018"></a>
<span id="l1019">     * Compiles the given regular expression into a pattern.</span><a href="#l1019"></a>
<span id="l1020">     *</span><a href="#l1020"></a>
<span id="l1021">     * @param  regex</span><a href="#l1021"></a>
<span id="l1022">     *         The expression to be compiled</span><a href="#l1022"></a>
<span id="l1023">     * @return the given regular expression compiled into a pattern</span><a href="#l1023"></a>
<span id="l1024">     * @throws  PatternSyntaxException</span><a href="#l1024"></a>
<span id="l1025">     *          If the expression's syntax is invalid</span><a href="#l1025"></a>
<span id="l1026">     */</span><a href="#l1026"></a>
<span id="l1027">    public static Pattern compile(String regex) {</span><a href="#l1027"></a>
<span id="l1028">        return new Pattern(regex, 0);</span><a href="#l1028"></a>
<span id="l1029">    }</span><a href="#l1029"></a>
<span id="l1030"></span><a href="#l1030"></a>
<span id="l1031">    /**</span><a href="#l1031"></a>
<span id="l1032">     * Compiles the given regular expression into a pattern with the given</span><a href="#l1032"></a>
<span id="l1033">     * flags.</span><a href="#l1033"></a>
<span id="l1034">     *</span><a href="#l1034"></a>
<span id="l1035">     * @param  regex</span><a href="#l1035"></a>
<span id="l1036">     *         The expression to be compiled</span><a href="#l1036"></a>
<span id="l1037">     *</span><a href="#l1037"></a>
<span id="l1038">     * @param  flags</span><a href="#l1038"></a>
<span id="l1039">     *         Match flags, a bit mask that may include</span><a href="#l1039"></a>
<span id="l1040">     *         {@link #CASE_INSENSITIVE}, {@link #MULTILINE}, {@link #DOTALL},</span><a href="#l1040"></a>
<span id="l1041">     *         {@link #UNICODE_CASE}, {@link #CANON_EQ}, {@link #UNIX_LINES},</span><a href="#l1041"></a>
<span id="l1042">     *         {@link #LITERAL}, {@link #UNICODE_CHARACTER_CLASS}</span><a href="#l1042"></a>
<span id="l1043">     *         and {@link #COMMENTS}</span><a href="#l1043"></a>
<span id="l1044">     *</span><a href="#l1044"></a>
<span id="l1045">     * @return the given regular expression compiled into a pattern with the given flags</span><a href="#l1045"></a>
<span id="l1046">     * @throws  IllegalArgumentException</span><a href="#l1046"></a>
<span id="l1047">     *          If bit values other than those corresponding to the defined</span><a href="#l1047"></a>
<span id="l1048">     *          match flags are set in &lt;tt&gt;flags&lt;/tt&gt;</span><a href="#l1048"></a>
<span id="l1049">     *</span><a href="#l1049"></a>
<span id="l1050">     * @throws  PatternSyntaxException</span><a href="#l1050"></a>
<span id="l1051">     *          If the expression's syntax is invalid</span><a href="#l1051"></a>
<span id="l1052">     */</span><a href="#l1052"></a>
<span id="l1053">    public static Pattern compile(String regex, int flags) {</span><a href="#l1053"></a>
<span id="l1054">        return new Pattern(regex, flags);</span><a href="#l1054"></a>
<span id="l1055">    }</span><a href="#l1055"></a>
<span id="l1056"></span><a href="#l1056"></a>
<span id="l1057">    /**</span><a href="#l1057"></a>
<span id="l1058">     * Returns the regular expression from which this pattern was compiled.</span><a href="#l1058"></a>
<span id="l1059">     *</span><a href="#l1059"></a>
<span id="l1060">     * @return  The source of this pattern</span><a href="#l1060"></a>
<span id="l1061">     */</span><a href="#l1061"></a>
<span id="l1062">    public String pattern() {</span><a href="#l1062"></a>
<span id="l1063">        return pattern;</span><a href="#l1063"></a>
<span id="l1064">    }</span><a href="#l1064"></a>
<span id="l1065"></span><a href="#l1065"></a>
<span id="l1066">    /**</span><a href="#l1066"></a>
<span id="l1067">     * &lt;p&gt;Returns the string representation of this pattern. This</span><a href="#l1067"></a>
<span id="l1068">     * is the regular expression from which this pattern was</span><a href="#l1068"></a>
<span id="l1069">     * compiled.&lt;/p&gt;</span><a href="#l1069"></a>
<span id="l1070">     *</span><a href="#l1070"></a>
<span id="l1071">     * @return  The string representation of this pattern</span><a href="#l1071"></a>
<span id="l1072">     * @since 1.5</span><a href="#l1072"></a>
<span id="l1073">     */</span><a href="#l1073"></a>
<span id="l1074">    public String toString() {</span><a href="#l1074"></a>
<span id="l1075">        return pattern;</span><a href="#l1075"></a>
<span id="l1076">    }</span><a href="#l1076"></a>
<span id="l1077"></span><a href="#l1077"></a>
<span id="l1078">    /**</span><a href="#l1078"></a>
<span id="l1079">     * Creates a matcher that will match the given input against this pattern.</span><a href="#l1079"></a>
<span id="l1080">     *</span><a href="#l1080"></a>
<span id="l1081">     * @param  input</span><a href="#l1081"></a>
<span id="l1082">     *         The character sequence to be matched</span><a href="#l1082"></a>
<span id="l1083">     *</span><a href="#l1083"></a>
<span id="l1084">     * @return  A new matcher for this pattern</span><a href="#l1084"></a>
<span id="l1085">     */</span><a href="#l1085"></a>
<span id="l1086">    public Matcher matcher(CharSequence input) {</span><a href="#l1086"></a>
<span id="l1087">        if (!compiled) {</span><a href="#l1087"></a>
<span id="l1088">            synchronized(this) {</span><a href="#l1088"></a>
<span id="l1089">                if (!compiled)</span><a href="#l1089"></a>
<span id="l1090">                    compile();</span><a href="#l1090"></a>
<span id="l1091">            }</span><a href="#l1091"></a>
<span id="l1092">        }</span><a href="#l1092"></a>
<span id="l1093">        Matcher m = new Matcher(this, input);</span><a href="#l1093"></a>
<span id="l1094">        return m;</span><a href="#l1094"></a>
<span id="l1095">    }</span><a href="#l1095"></a>
<span id="l1096"></span><a href="#l1096"></a>
<span id="l1097">    /**</span><a href="#l1097"></a>
<span id="l1098">     * Returns this pattern's match flags.</span><a href="#l1098"></a>
<span id="l1099">     *</span><a href="#l1099"></a>
<span id="l1100">     * @return  The match flags specified when this pattern was compiled</span><a href="#l1100"></a>
<span id="l1101">     */</span><a href="#l1101"></a>
<span id="l1102">    public int flags() {</span><a href="#l1102"></a>
<span id="l1103">        return flags;</span><a href="#l1103"></a>
<span id="l1104">    }</span><a href="#l1104"></a>
<span id="l1105"></span><a href="#l1105"></a>
<span id="l1106">    /**</span><a href="#l1106"></a>
<span id="l1107">     * Compiles the given regular expression and attempts to match the given</span><a href="#l1107"></a>
<span id="l1108">     * input against it.</span><a href="#l1108"></a>
<span id="l1109">     *</span><a href="#l1109"></a>
<span id="l1110">     * &lt;p&gt; An invocation of this convenience method of the form</span><a href="#l1110"></a>
<span id="l1111">     *</span><a href="#l1111"></a>
<span id="l1112">     * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l1112"></a>
<span id="l1113">     * Pattern.matches(regex, input);&lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l1113"></a>
<span id="l1114">     *</span><a href="#l1114"></a>
<span id="l1115">     * behaves in exactly the same way as the expression</span><a href="#l1115"></a>
<span id="l1116">     *</span><a href="#l1116"></a>
<span id="l1117">     * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l1117"></a>
<span id="l1118">     * Pattern.compile(regex).matcher(input).matches()&lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l1118"></a>
<span id="l1119">     *</span><a href="#l1119"></a>
<span id="l1120">     * &lt;p&gt; If a pattern is to be used multiple times, compiling it once and reusing</span><a href="#l1120"></a>
<span id="l1121">     * it will be more efficient than invoking this method each time.  &lt;/p&gt;</span><a href="#l1121"></a>
<span id="l1122">     *</span><a href="#l1122"></a>
<span id="l1123">     * @param  regex</span><a href="#l1123"></a>
<span id="l1124">     *         The expression to be compiled</span><a href="#l1124"></a>
<span id="l1125">     *</span><a href="#l1125"></a>
<span id="l1126">     * @param  input</span><a href="#l1126"></a>
<span id="l1127">     *         The character sequence to be matched</span><a href="#l1127"></a>
<span id="l1128">     * @return whether or not the regular expression matches on the input</span><a href="#l1128"></a>
<span id="l1129">     * @throws  PatternSyntaxException</span><a href="#l1129"></a>
<span id="l1130">     *          If the expression's syntax is invalid</span><a href="#l1130"></a>
<span id="l1131">     */</span><a href="#l1131"></a>
<span id="l1132">    public static boolean matches(String regex, CharSequence input) {</span><a href="#l1132"></a>
<span id="l1133">        Pattern p = Pattern.compile(regex);</span><a href="#l1133"></a>
<span id="l1134">        Matcher m = p.matcher(input);</span><a href="#l1134"></a>
<span id="l1135">        return m.matches();</span><a href="#l1135"></a>
<span id="l1136">    }</span><a href="#l1136"></a>
<span id="l1137"></span><a href="#l1137"></a>
<span id="l1138">    /**</span><a href="#l1138"></a>
<span id="l1139">     * Splits the given input sequence around matches of this pattern.</span><a href="#l1139"></a>
<span id="l1140">     *</span><a href="#l1140"></a>
<span id="l1141">     * &lt;p&gt; The array returned by this method contains each substring of the</span><a href="#l1141"></a>
<span id="l1142">     * input sequence that is terminated by another subsequence that matches</span><a href="#l1142"></a>
<span id="l1143">     * this pattern or is terminated by the end of the input sequence.  The</span><a href="#l1143"></a>
<span id="l1144">     * substrings in the array are in the order in which they occur in the</span><a href="#l1144"></a>
<span id="l1145">     * input. If this pattern does not match any subsequence of the input then</span><a href="#l1145"></a>
<span id="l1146">     * the resulting array has just one element, namely the input sequence in</span><a href="#l1146"></a>
<span id="l1147">     * string form.</span><a href="#l1147"></a>
<span id="l1148">     *</span><a href="#l1148"></a>
<span id="l1149">     * &lt;p&gt; When there is a positive-width match at the beginning of the input</span><a href="#l1149"></a>
<span id="l1150">     * sequence then an empty leading substring is included at the beginning</span><a href="#l1150"></a>
<span id="l1151">     * of the resulting array. A zero-width match at the beginning however</span><a href="#l1151"></a>
<span id="l1152">     * never produces such empty leading substring.</span><a href="#l1152"></a>
<span id="l1153">     *</span><a href="#l1153"></a>
<span id="l1154">     * &lt;p&gt; The &lt;tt&gt;limit&lt;/tt&gt; parameter controls the number of times the</span><a href="#l1154"></a>
<span id="l1155">     * pattern is applied and therefore affects the length of the resulting</span><a href="#l1155"></a>
<span id="l1156">     * array.  If the limit &lt;i&gt;n&lt;/i&gt; is greater than zero then the pattern</span><a href="#l1156"></a>
<span id="l1157">     * will be applied at most &lt;i&gt;n&lt;/i&gt;&amp;nbsp;-&amp;nbsp;1 times, the array's</span><a href="#l1157"></a>
<span id="l1158">     * length will be no greater than &lt;i&gt;n&lt;/i&gt;, and the array's last entry</span><a href="#l1158"></a>
<span id="l1159">     * will contain all input beyond the last matched delimiter.  If &lt;i&gt;n&lt;/i&gt;</span><a href="#l1159"></a>
<span id="l1160">     * is non-positive then the pattern will be applied as many times as</span><a href="#l1160"></a>
<span id="l1161">     * possible and the array can have any length.  If &lt;i&gt;n&lt;/i&gt; is zero then</span><a href="#l1161"></a>
<span id="l1162">     * the pattern will be applied as many times as possible, the array can</span><a href="#l1162"></a>
<span id="l1163">     * have any length, and trailing empty strings will be discarded.</span><a href="#l1163"></a>
<span id="l1164">     *</span><a href="#l1164"></a>
<span id="l1165">     * &lt;p&gt; The input &lt;tt&gt;&quot;boo:and:foo&quot;&lt;/tt&gt;, for example, yields the following</span><a href="#l1165"></a>
<span id="l1166">     * results with these parameters:</span><a href="#l1166"></a>
<span id="l1167">     *</span><a href="#l1167"></a>
<span id="l1168">     * &lt;blockquote&gt;&lt;table cellpadding=1 cellspacing=0</span><a href="#l1168"></a>
<span id="l1169">     *              summary=&quot;Split examples showing regex, limit, and result&quot;&gt;</span><a href="#l1169"></a>
<span id="l1170">     * &lt;tr&gt;&lt;th align=&quot;left&quot;&gt;&lt;i&gt;Regex&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/i&gt;&lt;/th&gt;</span><a href="#l1170"></a>
<span id="l1171">     *     &lt;th align=&quot;left&quot;&gt;&lt;i&gt;Limit&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/i&gt;&lt;/th&gt;</span><a href="#l1171"></a>
<span id="l1172">     *     &lt;th align=&quot;left&quot;&gt;&lt;i&gt;Result&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/i&gt;&lt;/th&gt;&lt;/tr&gt;</span><a href="#l1172"></a>
<span id="l1173">     * &lt;tr&gt;&lt;td align=center&gt;:&lt;/td&gt;</span><a href="#l1173"></a>
<span id="l1174">     *     &lt;td align=center&gt;2&lt;/td&gt;</span><a href="#l1174"></a>
<span id="l1175">     *     &lt;td&gt;&lt;tt&gt;{ &quot;boo&quot;, &quot;and:foo&quot; }&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l1175"></a>
<span id="l1176">     * &lt;tr&gt;&lt;td align=center&gt;:&lt;/td&gt;</span><a href="#l1176"></a>
<span id="l1177">     *     &lt;td align=center&gt;5&lt;/td&gt;</span><a href="#l1177"></a>
<span id="l1178">     *     &lt;td&gt;&lt;tt&gt;{ &quot;boo&quot;, &quot;and&quot;, &quot;foo&quot; }&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l1178"></a>
<span id="l1179">     * &lt;tr&gt;&lt;td align=center&gt;:&lt;/td&gt;</span><a href="#l1179"></a>
<span id="l1180">     *     &lt;td align=center&gt;-2&lt;/td&gt;</span><a href="#l1180"></a>
<span id="l1181">     *     &lt;td&gt;&lt;tt&gt;{ &quot;boo&quot;, &quot;and&quot;, &quot;foo&quot; }&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l1181"></a>
<span id="l1182">     * &lt;tr&gt;&lt;td align=center&gt;o&lt;/td&gt;</span><a href="#l1182"></a>
<span id="l1183">     *     &lt;td align=center&gt;5&lt;/td&gt;</span><a href="#l1183"></a>
<span id="l1184">     *     &lt;td&gt;&lt;tt&gt;{ &quot;b&quot;, &quot;&quot;, &quot;:and:f&quot;, &quot;&quot;, &quot;&quot; }&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l1184"></a>
<span id="l1185">     * &lt;tr&gt;&lt;td align=center&gt;o&lt;/td&gt;</span><a href="#l1185"></a>
<span id="l1186">     *     &lt;td align=center&gt;-2&lt;/td&gt;</span><a href="#l1186"></a>
<span id="l1187">     *     &lt;td&gt;&lt;tt&gt;{ &quot;b&quot;, &quot;&quot;, &quot;:and:f&quot;, &quot;&quot;, &quot;&quot; }&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l1187"></a>
<span id="l1188">     * &lt;tr&gt;&lt;td align=center&gt;o&lt;/td&gt;</span><a href="#l1188"></a>
<span id="l1189">     *     &lt;td align=center&gt;0&lt;/td&gt;</span><a href="#l1189"></a>
<span id="l1190">     *     &lt;td&gt;&lt;tt&gt;{ &quot;b&quot;, &quot;&quot;, &quot;:and:f&quot; }&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l1190"></a>
<span id="l1191">     * &lt;/table&gt;&lt;/blockquote&gt;</span><a href="#l1191"></a>
<span id="l1192">     *</span><a href="#l1192"></a>
<span id="l1193">     * @param  input</span><a href="#l1193"></a>
<span id="l1194">     *         The character sequence to be split</span><a href="#l1194"></a>
<span id="l1195">     *</span><a href="#l1195"></a>
<span id="l1196">     * @param  limit</span><a href="#l1196"></a>
<span id="l1197">     *         The result threshold, as described above</span><a href="#l1197"></a>
<span id="l1198">     *</span><a href="#l1198"></a>
<span id="l1199">     * @return  The array of strings computed by splitting the input</span><a href="#l1199"></a>
<span id="l1200">     *          around matches of this pattern</span><a href="#l1200"></a>
<span id="l1201">     */</span><a href="#l1201"></a>
<span id="l1202">    public String[] split(CharSequence input, int limit) {</span><a href="#l1202"></a>
<span id="l1203">        int index = 0;</span><a href="#l1203"></a>
<span id="l1204">        boolean matchLimited = limit &gt; 0;</span><a href="#l1204"></a>
<span id="l1205">        ArrayList&lt;String&gt; matchList = new ArrayList&lt;&gt;();</span><a href="#l1205"></a>
<span id="l1206">        Matcher m = matcher(input);</span><a href="#l1206"></a>
<span id="l1207"></span><a href="#l1207"></a>
<span id="l1208">        // Add segments before each match found</span><a href="#l1208"></a>
<span id="l1209">        while(m.find()) {</span><a href="#l1209"></a>
<span id="l1210">            if (!matchLimited || matchList.size() &lt; limit - 1) {</span><a href="#l1210"></a>
<span id="l1211">                if (index == 0 &amp;&amp; index == m.start() &amp;&amp; m.start() == m.end()) {</span><a href="#l1211"></a>
<span id="l1212">                    // no empty leading substring included for zero-width match</span><a href="#l1212"></a>
<span id="l1213">                    // at the beginning of the input char sequence.</span><a href="#l1213"></a>
<span id="l1214">                    continue;</span><a href="#l1214"></a>
<span id="l1215">                }</span><a href="#l1215"></a>
<span id="l1216">                String match = input.subSequence(index, m.start()).toString();</span><a href="#l1216"></a>
<span id="l1217">                matchList.add(match);</span><a href="#l1217"></a>
<span id="l1218">                index = m.end();</span><a href="#l1218"></a>
<span id="l1219">            } else if (matchList.size() == limit - 1) { // last one</span><a href="#l1219"></a>
<span id="l1220">                String match = input.subSequence(index,</span><a href="#l1220"></a>
<span id="l1221">                                                 input.length()).toString();</span><a href="#l1221"></a>
<span id="l1222">                matchList.add(match);</span><a href="#l1222"></a>
<span id="l1223">                index = m.end();</span><a href="#l1223"></a>
<span id="l1224">            }</span><a href="#l1224"></a>
<span id="l1225">        }</span><a href="#l1225"></a>
<span id="l1226"></span><a href="#l1226"></a>
<span id="l1227">        // If no match was found, return this</span><a href="#l1227"></a>
<span id="l1228">        if (index == 0)</span><a href="#l1228"></a>
<span id="l1229">            return new String[] {input.toString()};</span><a href="#l1229"></a>
<span id="l1230"></span><a href="#l1230"></a>
<span id="l1231">        // Add remaining segment</span><a href="#l1231"></a>
<span id="l1232">        if (!matchLimited || matchList.size() &lt; limit)</span><a href="#l1232"></a>
<span id="l1233">            matchList.add(input.subSequence(index, input.length()).toString());</span><a href="#l1233"></a>
<span id="l1234"></span><a href="#l1234"></a>
<span id="l1235">        // Construct result</span><a href="#l1235"></a>
<span id="l1236">        int resultSize = matchList.size();</span><a href="#l1236"></a>
<span id="l1237">        if (limit == 0)</span><a href="#l1237"></a>
<span id="l1238">            while (resultSize &gt; 0 &amp;&amp; matchList.get(resultSize-1).equals(&quot;&quot;))</span><a href="#l1238"></a>
<span id="l1239">                resultSize--;</span><a href="#l1239"></a>
<span id="l1240">        String[] result = new String[resultSize];</span><a href="#l1240"></a>
<span id="l1241">        return matchList.subList(0, resultSize).toArray(result);</span><a href="#l1241"></a>
<span id="l1242">    }</span><a href="#l1242"></a>
<span id="l1243"></span><a href="#l1243"></a>
<span id="l1244">    /**</span><a href="#l1244"></a>
<span id="l1245">     * Splits the given input sequence around matches of this pattern.</span><a href="#l1245"></a>
<span id="l1246">     *</span><a href="#l1246"></a>
<span id="l1247">     * &lt;p&gt; This method works as if by invoking the two-argument {@link</span><a href="#l1247"></a>
<span id="l1248">     * #split(java.lang.CharSequence, int) split} method with the given input</span><a href="#l1248"></a>
<span id="l1249">     * sequence and a limit argument of zero.  Trailing empty strings are</span><a href="#l1249"></a>
<span id="l1250">     * therefore not included in the resulting array. &lt;/p&gt;</span><a href="#l1250"></a>
<span id="l1251">     *</span><a href="#l1251"></a>
<span id="l1252">     * &lt;p&gt; The input &lt;tt&gt;&quot;boo:and:foo&quot;&lt;/tt&gt;, for example, yields the following</span><a href="#l1252"></a>
<span id="l1253">     * results with these expressions:</span><a href="#l1253"></a>
<span id="l1254">     *</span><a href="#l1254"></a>
<span id="l1255">     * &lt;blockquote&gt;&lt;table cellpadding=1 cellspacing=0</span><a href="#l1255"></a>
<span id="l1256">     *              summary=&quot;Split examples showing regex and result&quot;&gt;</span><a href="#l1256"></a>
<span id="l1257">     * &lt;tr&gt;&lt;th align=&quot;left&quot;&gt;&lt;i&gt;Regex&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/i&gt;&lt;/th&gt;</span><a href="#l1257"></a>
<span id="l1258">     *     &lt;th align=&quot;left&quot;&gt;&lt;i&gt;Result&lt;/i&gt;&lt;/th&gt;&lt;/tr&gt;</span><a href="#l1258"></a>
<span id="l1259">     * &lt;tr&gt;&lt;td align=center&gt;:&lt;/td&gt;</span><a href="#l1259"></a>
<span id="l1260">     *     &lt;td&gt;&lt;tt&gt;{ &quot;boo&quot;, &quot;and&quot;, &quot;foo&quot; }&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l1260"></a>
<span id="l1261">     * &lt;tr&gt;&lt;td align=center&gt;o&lt;/td&gt;</span><a href="#l1261"></a>
<span id="l1262">     *     &lt;td&gt;&lt;tt&gt;{ &quot;b&quot;, &quot;&quot;, &quot;:and:f&quot; }&lt;/tt&gt;&lt;/td&gt;&lt;/tr&gt;</span><a href="#l1262"></a>
<span id="l1263">     * &lt;/table&gt;&lt;/blockquote&gt;</span><a href="#l1263"></a>
<span id="l1264">     *</span><a href="#l1264"></a>
<span id="l1265">     *</span><a href="#l1265"></a>
<span id="l1266">     * @param  input</span><a href="#l1266"></a>
<span id="l1267">     *         The character sequence to be split</span><a href="#l1267"></a>
<span id="l1268">     *</span><a href="#l1268"></a>
<span id="l1269">     * @return  The array of strings computed by splitting the input</span><a href="#l1269"></a>
<span id="l1270">     *          around matches of this pattern</span><a href="#l1270"></a>
<span id="l1271">     */</span><a href="#l1271"></a>
<span id="l1272">    public String[] split(CharSequence input) {</span><a href="#l1272"></a>
<span id="l1273">        return split(input, 0);</span><a href="#l1273"></a>
<span id="l1274">    }</span><a href="#l1274"></a>
<span id="l1275"></span><a href="#l1275"></a>
<span id="l1276">    /**</span><a href="#l1276"></a>
<span id="l1277">     * Returns a literal pattern &lt;code&gt;String&lt;/code&gt; for the specified</span><a href="#l1277"></a>
<span id="l1278">     * &lt;code&gt;String&lt;/code&gt;.</span><a href="#l1278"></a>
<span id="l1279">     *</span><a href="#l1279"></a>
<span id="l1280">     * &lt;p&gt;This method produces a &lt;code&gt;String&lt;/code&gt; that can be used to</span><a href="#l1280"></a>
<span id="l1281">     * create a &lt;code&gt;Pattern&lt;/code&gt; that would match the string</span><a href="#l1281"></a>
<span id="l1282">     * &lt;code&gt;s&lt;/code&gt; as if it were a literal pattern.&lt;/p&gt; Metacharacters</span><a href="#l1282"></a>
<span id="l1283">     * or escape sequences in the input sequence will be given no special</span><a href="#l1283"></a>
<span id="l1284">     * meaning.</span><a href="#l1284"></a>
<span id="l1285">     *</span><a href="#l1285"></a>
<span id="l1286">     * @param  s The string to be literalized</span><a href="#l1286"></a>
<span id="l1287">     * @return  A literal string replacement</span><a href="#l1287"></a>
<span id="l1288">     * @since 1.5</span><a href="#l1288"></a>
<span id="l1289">     */</span><a href="#l1289"></a>
<span id="l1290">    public static String quote(String s) {</span><a href="#l1290"></a>
<span id="l1291">        int slashEIndex = s.indexOf(&quot;\\E&quot;);</span><a href="#l1291"></a>
<span id="l1292">        if (slashEIndex == -1)</span><a href="#l1292"></a>
<span id="l1293">            return &quot;\\Q&quot; + s + &quot;\\E&quot;;</span><a href="#l1293"></a>
<span id="l1294"></span><a href="#l1294"></a>
<span id="l1295">        StringBuilder sb = new StringBuilder(s.length() * 2);</span><a href="#l1295"></a>
<span id="l1296">        sb.append(&quot;\\Q&quot;);</span><a href="#l1296"></a>
<span id="l1297">        slashEIndex = 0;</span><a href="#l1297"></a>
<span id="l1298">        int current = 0;</span><a href="#l1298"></a>
<span id="l1299">        while ((slashEIndex = s.indexOf(&quot;\\E&quot;, current)) != -1) {</span><a href="#l1299"></a>
<span id="l1300">            sb.append(s.substring(current, slashEIndex));</span><a href="#l1300"></a>
<span id="l1301">            current = slashEIndex + 2;</span><a href="#l1301"></a>
<span id="l1302">            sb.append(&quot;\\E\\\\E\\Q&quot;);</span><a href="#l1302"></a>
<span id="l1303">        }</span><a href="#l1303"></a>
<span id="l1304">        sb.append(s.substring(current, s.length()));</span><a href="#l1304"></a>
<span id="l1305">        sb.append(&quot;\\E&quot;);</span><a href="#l1305"></a>
<span id="l1306">        return sb.toString();</span><a href="#l1306"></a>
<span id="l1307">    }</span><a href="#l1307"></a>
<span id="l1308"></span><a href="#l1308"></a>
<span id="l1309">    /**</span><a href="#l1309"></a>
<span id="l1310">     * Recompile the Pattern instance from a stream.  The original pattern</span><a href="#l1310"></a>
<span id="l1311">     * string is read in and the object tree is recompiled from it.</span><a href="#l1311"></a>
<span id="l1312">     */</span><a href="#l1312"></a>
<span id="l1313">    private void readObject(java.io.ObjectInputStream s)</span><a href="#l1313"></a>
<span id="l1314">        throws java.io.IOException, ClassNotFoundException {</span><a href="#l1314"></a>
<span id="l1315"></span><a href="#l1315"></a>
<span id="l1316">        // Read in all fields</span><a href="#l1316"></a>
<span id="l1317">        s.defaultReadObject();</span><a href="#l1317"></a>
<span id="l1318"></span><a href="#l1318"></a>
<span id="l1319">        // Initialize counts</span><a href="#l1319"></a>
<span id="l1320">        capturingGroupCount = 1;</span><a href="#l1320"></a>
<span id="l1321">        localCount = 0;</span><a href="#l1321"></a>
<span id="l1322"></span><a href="#l1322"></a>
<span id="l1323">        // if length &gt; 0, the Pattern is lazily compiled</span><a href="#l1323"></a>
<span id="l1324">        compiled = false;</span><a href="#l1324"></a>
<span id="l1325">        if (pattern.length() == 0) {</span><a href="#l1325"></a>
<span id="l1326">            root = new Start(lastAccept);</span><a href="#l1326"></a>
<span id="l1327">            matchRoot = lastAccept;</span><a href="#l1327"></a>
<span id="l1328">            compiled = true;</span><a href="#l1328"></a>
<span id="l1329">        }</span><a href="#l1329"></a>
<span id="l1330">    }</span><a href="#l1330"></a>
<span id="l1331"></span><a href="#l1331"></a>
<span id="l1332">    /**</span><a href="#l1332"></a>
<span id="l1333">     * This private constructor is used to create all Patterns. The pattern</span><a href="#l1333"></a>
<span id="l1334">     * string and match flags are all that is needed to completely describe</span><a href="#l1334"></a>
<span id="l1335">     * a Pattern. An empty pattern string results in an object tree with</span><a href="#l1335"></a>
<span id="l1336">     * only a Start node and a LastNode node.</span><a href="#l1336"></a>
<span id="l1337">     */</span><a href="#l1337"></a>
<span id="l1338">    private Pattern(String p, int f) {</span><a href="#l1338"></a>
<span id="l1339">        pattern = p;</span><a href="#l1339"></a>
<span id="l1340">        flags = f;</span><a href="#l1340"></a>
<span id="l1341"></span><a href="#l1341"></a>
<span id="l1342">        // to use UNICODE_CASE if UNICODE_CHARACTER_CLASS present</span><a href="#l1342"></a>
<span id="l1343">        if ((flags &amp; UNICODE_CHARACTER_CLASS) != 0)</span><a href="#l1343"></a>
<span id="l1344">            flags |= UNICODE_CASE;</span><a href="#l1344"></a>
<span id="l1345"></span><a href="#l1345"></a>
<span id="l1346">        // Reset group index count</span><a href="#l1346"></a>
<span id="l1347">        capturingGroupCount = 1;</span><a href="#l1347"></a>
<span id="l1348">        localCount = 0;</span><a href="#l1348"></a>
<span id="l1349"></span><a href="#l1349"></a>
<span id="l1350">        if (pattern.length() &gt; 0) {</span><a href="#l1350"></a>
<span id="l1351">            try {</span><a href="#l1351"></a>
<span id="l1352">                compile();</span><a href="#l1352"></a>
<span id="l1353">            } catch (StackOverflowError soe) {</span><a href="#l1353"></a>
<span id="l1354">                throw error(&quot;Stack overflow during pattern compilation&quot;);</span><a href="#l1354"></a>
<span id="l1355">            }</span><a href="#l1355"></a>
<span id="l1356">        } else {</span><a href="#l1356"></a>
<span id="l1357">            root = new Start(lastAccept);</span><a href="#l1357"></a>
<span id="l1358">            matchRoot = lastAccept;</span><a href="#l1358"></a>
<span id="l1359">        }</span><a href="#l1359"></a>
<span id="l1360">    }</span><a href="#l1360"></a>
<span id="l1361"></span><a href="#l1361"></a>
<span id="l1362">    /**</span><a href="#l1362"></a>
<span id="l1363">     * The pattern is converted to normalizedD form and then a pure group</span><a href="#l1363"></a>
<span id="l1364">     * is constructed to match canonical equivalences of the characters.</span><a href="#l1364"></a>
<span id="l1365">     */</span><a href="#l1365"></a>
<span id="l1366">    private void normalize() {</span><a href="#l1366"></a>
<span id="l1367">        boolean inCharClass = false;</span><a href="#l1367"></a>
<span id="l1368">        int lastCodePoint = -1;</span><a href="#l1368"></a>
<span id="l1369"></span><a href="#l1369"></a>
<span id="l1370">        // Convert pattern into normalizedD form</span><a href="#l1370"></a>
<span id="l1371">        normalizedPattern = Normalizer.normalize(pattern, Normalizer.Form.NFD);</span><a href="#l1371"></a>
<span id="l1372">        patternLength = normalizedPattern.length();</span><a href="#l1372"></a>
<span id="l1373"></span><a href="#l1373"></a>
<span id="l1374">        // Modify pattern to match canonical equivalences</span><a href="#l1374"></a>
<span id="l1375">        StringBuilder newPattern = new StringBuilder(patternLength);</span><a href="#l1375"></a>
<span id="l1376">        for(int i=0; i&lt;patternLength; ) {</span><a href="#l1376"></a>
<span id="l1377">            int c = normalizedPattern.codePointAt(i);</span><a href="#l1377"></a>
<span id="l1378">            StringBuilder sequenceBuffer;</span><a href="#l1378"></a>
<span id="l1379">            if ((Character.getType(c) == Character.NON_SPACING_MARK)</span><a href="#l1379"></a>
<span id="l1380">                &amp;&amp; (lastCodePoint != -1)) {</span><a href="#l1380"></a>
<span id="l1381">                sequenceBuffer = new StringBuilder();</span><a href="#l1381"></a>
<span id="l1382">                sequenceBuffer.appendCodePoint(lastCodePoint);</span><a href="#l1382"></a>
<span id="l1383">                sequenceBuffer.appendCodePoint(c);</span><a href="#l1383"></a>
<span id="l1384">                while(Character.getType(c) == Character.NON_SPACING_MARK) {</span><a href="#l1384"></a>
<span id="l1385">                    i += Character.charCount(c);</span><a href="#l1385"></a>
<span id="l1386">                    if (i &gt;= patternLength)</span><a href="#l1386"></a>
<span id="l1387">                        break;</span><a href="#l1387"></a>
<span id="l1388">                    c = normalizedPattern.codePointAt(i);</span><a href="#l1388"></a>
<span id="l1389">                    sequenceBuffer.appendCodePoint(c);</span><a href="#l1389"></a>
<span id="l1390">                }</span><a href="#l1390"></a>
<span id="l1391">                String ea = produceEquivalentAlternation(</span><a href="#l1391"></a>
<span id="l1392">                                               sequenceBuffer.toString());</span><a href="#l1392"></a>
<span id="l1393">                newPattern.setLength(newPattern.length()-Character.charCount(lastCodePoint));</span><a href="#l1393"></a>
<span id="l1394">                newPattern.append(&quot;(?:&quot;).append(ea).append(&quot;)&quot;);</span><a href="#l1394"></a>
<span id="l1395">            } else if (c == '[' &amp;&amp; lastCodePoint != '\\') {</span><a href="#l1395"></a>
<span id="l1396">                i = normalizeCharClass(newPattern, i);</span><a href="#l1396"></a>
<span id="l1397">            } else {</span><a href="#l1397"></a>
<span id="l1398">                newPattern.appendCodePoint(c);</span><a href="#l1398"></a>
<span id="l1399">            }</span><a href="#l1399"></a>
<span id="l1400">            lastCodePoint = c;</span><a href="#l1400"></a>
<span id="l1401">            i += Character.charCount(c);</span><a href="#l1401"></a>
<span id="l1402">        }</span><a href="#l1402"></a>
<span id="l1403">        normalizedPattern = newPattern.toString();</span><a href="#l1403"></a>
<span id="l1404">    }</span><a href="#l1404"></a>
<span id="l1405"></span><a href="#l1405"></a>
<span id="l1406">    /**</span><a href="#l1406"></a>
<span id="l1407">     * Complete the character class being parsed and add a set</span><a href="#l1407"></a>
<span id="l1408">     * of alternations to it that will match the canonical equivalences</span><a href="#l1408"></a>
<span id="l1409">     * of the characters within the class.</span><a href="#l1409"></a>
<span id="l1410">     */</span><a href="#l1410"></a>
<span id="l1411">    private int normalizeCharClass(StringBuilder newPattern, int i) {</span><a href="#l1411"></a>
<span id="l1412">        StringBuilder charClass = new StringBuilder();</span><a href="#l1412"></a>
<span id="l1413">        StringBuilder eq = null;</span><a href="#l1413"></a>
<span id="l1414">        int lastCodePoint = -1;</span><a href="#l1414"></a>
<span id="l1415">        String result;</span><a href="#l1415"></a>
<span id="l1416"></span><a href="#l1416"></a>
<span id="l1417">        i++;</span><a href="#l1417"></a>
<span id="l1418">        if (i == normalizedPattern.length())</span><a href="#l1418"></a>
<span id="l1419">            throw error(&quot;Unclosed character class&quot;);</span><a href="#l1419"></a>
<span id="l1420">        charClass.append(&quot;[&quot;);</span><a href="#l1420"></a>
<span id="l1421">        while(true) {</span><a href="#l1421"></a>
<span id="l1422">            int c = normalizedPattern.codePointAt(i);</span><a href="#l1422"></a>
<span id="l1423">            StringBuilder sequenceBuffer;</span><a href="#l1423"></a>
<span id="l1424"></span><a href="#l1424"></a>
<span id="l1425">            if (c == ']' &amp;&amp; lastCodePoint != '\\') {</span><a href="#l1425"></a>
<span id="l1426">                charClass.append((char)c);</span><a href="#l1426"></a>
<span id="l1427">                break;</span><a href="#l1427"></a>
<span id="l1428">            } else if (Character.getType(c) == Character.NON_SPACING_MARK) {</span><a href="#l1428"></a>
<span id="l1429">                sequenceBuffer = new StringBuilder();</span><a href="#l1429"></a>
<span id="l1430">                sequenceBuffer.appendCodePoint(lastCodePoint);</span><a href="#l1430"></a>
<span id="l1431">                while(Character.getType(c) == Character.NON_SPACING_MARK) {</span><a href="#l1431"></a>
<span id="l1432">                    sequenceBuffer.appendCodePoint(c);</span><a href="#l1432"></a>
<span id="l1433">                    i += Character.charCount(c);</span><a href="#l1433"></a>
<span id="l1434">                    if (i &gt;= normalizedPattern.length())</span><a href="#l1434"></a>
<span id="l1435">                        break;</span><a href="#l1435"></a>
<span id="l1436">                    c = normalizedPattern.codePointAt(i);</span><a href="#l1436"></a>
<span id="l1437">                }</span><a href="#l1437"></a>
<span id="l1438">                String ea = produceEquivalentAlternation(</span><a href="#l1438"></a>
<span id="l1439">                                                  sequenceBuffer.toString());</span><a href="#l1439"></a>
<span id="l1440"></span><a href="#l1440"></a>
<span id="l1441">                charClass.setLength(charClass.length()-Character.charCount(lastCodePoint));</span><a href="#l1441"></a>
<span id="l1442">                if (eq == null)</span><a href="#l1442"></a>
<span id="l1443">                    eq = new StringBuilder();</span><a href="#l1443"></a>
<span id="l1444">                eq.append('|');</span><a href="#l1444"></a>
<span id="l1445">                eq.append(ea);</span><a href="#l1445"></a>
<span id="l1446">            } else {</span><a href="#l1446"></a>
<span id="l1447">                charClass.appendCodePoint(c);</span><a href="#l1447"></a>
<span id="l1448">                i++;</span><a href="#l1448"></a>
<span id="l1449">            }</span><a href="#l1449"></a>
<span id="l1450">            if (i == normalizedPattern.length())</span><a href="#l1450"></a>
<span id="l1451">                throw error(&quot;Unclosed character class&quot;);</span><a href="#l1451"></a>
<span id="l1452">            lastCodePoint = c;</span><a href="#l1452"></a>
<span id="l1453">        }</span><a href="#l1453"></a>
<span id="l1454"></span><a href="#l1454"></a>
<span id="l1455">        if (eq != null) {</span><a href="#l1455"></a>
<span id="l1456">            result = &quot;(?:&quot;+charClass.toString()+eq.toString()+&quot;)&quot;;</span><a href="#l1456"></a>
<span id="l1457">        } else {</span><a href="#l1457"></a>
<span id="l1458">            result = charClass.toString();</span><a href="#l1458"></a>
<span id="l1459">        }</span><a href="#l1459"></a>
<span id="l1460"></span><a href="#l1460"></a>
<span id="l1461">        newPattern.append(result);</span><a href="#l1461"></a>
<span id="l1462">        return i;</span><a href="#l1462"></a>
<span id="l1463">    }</span><a href="#l1463"></a>
<span id="l1464"></span><a href="#l1464"></a>
<span id="l1465">    /**</span><a href="#l1465"></a>
<span id="l1466">     * Given a specific sequence composed of a regular character and</span><a href="#l1466"></a>
<span id="l1467">     * combining marks that follow it, produce the alternation that will</span><a href="#l1467"></a>
<span id="l1468">     * match all canonical equivalences of that sequence.</span><a href="#l1468"></a>
<span id="l1469">     */</span><a href="#l1469"></a>
<span id="l1470">    private String produceEquivalentAlternation(String source) {</span><a href="#l1470"></a>
<span id="l1471">        int len = countChars(source, 0, 1);</span><a href="#l1471"></a>
<span id="l1472">        if (source.length() == len)</span><a href="#l1472"></a>
<span id="l1473">            // source has one character.</span><a href="#l1473"></a>
<span id="l1474">            return source;</span><a href="#l1474"></a>
<span id="l1475"></span><a href="#l1475"></a>
<span id="l1476">        String base = source.substring(0,len);</span><a href="#l1476"></a>
<span id="l1477">        String combiningMarks = source.substring(len);</span><a href="#l1477"></a>
<span id="l1478"></span><a href="#l1478"></a>
<span id="l1479">        String[] perms = producePermutations(combiningMarks);</span><a href="#l1479"></a>
<span id="l1480">        StringBuilder result = new StringBuilder(source);</span><a href="#l1480"></a>
<span id="l1481"></span><a href="#l1481"></a>
<span id="l1482">        // Add combined permutations</span><a href="#l1482"></a>
<span id="l1483">        for(int x=0; x&lt;perms.length; x++) {</span><a href="#l1483"></a>
<span id="l1484">            String next = base + perms[x];</span><a href="#l1484"></a>
<span id="l1485">            if (x&gt;0)</span><a href="#l1485"></a>
<span id="l1486">                result.append(&quot;|&quot;+next);</span><a href="#l1486"></a>
<span id="l1487">            next = composeOneStep(next);</span><a href="#l1487"></a>
<span id="l1488">            if (next != null)</span><a href="#l1488"></a>
<span id="l1489">                result.append(&quot;|&quot;+produceEquivalentAlternation(next));</span><a href="#l1489"></a>
<span id="l1490">        }</span><a href="#l1490"></a>
<span id="l1491">        return result.toString();</span><a href="#l1491"></a>
<span id="l1492">    }</span><a href="#l1492"></a>
<span id="l1493"></span><a href="#l1493"></a>
<span id="l1494">    /**</span><a href="#l1494"></a>
<span id="l1495">     * Returns an array of strings that have all the possible</span><a href="#l1495"></a>
<span id="l1496">     * permutations of the characters in the input string.</span><a href="#l1496"></a>
<span id="l1497">     * This is used to get a list of all possible orderings</span><a href="#l1497"></a>
<span id="l1498">     * of a set of combining marks. Note that some of the permutations</span><a href="#l1498"></a>
<span id="l1499">     * are invalid because of combining class collisions, and these</span><a href="#l1499"></a>
<span id="l1500">     * possibilities must be removed because they are not canonically</span><a href="#l1500"></a>
<span id="l1501">     * equivalent.</span><a href="#l1501"></a>
<span id="l1502">     */</span><a href="#l1502"></a>
<span id="l1503">    private String[] producePermutations(String input) {</span><a href="#l1503"></a>
<span id="l1504">        if (input.length() == countChars(input, 0, 1))</span><a href="#l1504"></a>
<span id="l1505">            return new String[] {input};</span><a href="#l1505"></a>
<span id="l1506"></span><a href="#l1506"></a>
<span id="l1507">        if (input.length() == countChars(input, 0, 2)) {</span><a href="#l1507"></a>
<span id="l1508">            int c0 = Character.codePointAt(input, 0);</span><a href="#l1508"></a>
<span id="l1509">            int c1 = Character.codePointAt(input, Character.charCount(c0));</span><a href="#l1509"></a>
<span id="l1510">            if (getClass(c1) == getClass(c0)) {</span><a href="#l1510"></a>
<span id="l1511">                return new String[] {input};</span><a href="#l1511"></a>
<span id="l1512">            }</span><a href="#l1512"></a>
<span id="l1513">            String[] result = new String[2];</span><a href="#l1513"></a>
<span id="l1514">            result[0] = input;</span><a href="#l1514"></a>
<span id="l1515">            StringBuilder sb = new StringBuilder(2);</span><a href="#l1515"></a>
<span id="l1516">            sb.appendCodePoint(c1);</span><a href="#l1516"></a>
<span id="l1517">            sb.appendCodePoint(c0);</span><a href="#l1517"></a>
<span id="l1518">            result[1] = sb.toString();</span><a href="#l1518"></a>
<span id="l1519">            return result;</span><a href="#l1519"></a>
<span id="l1520">        }</span><a href="#l1520"></a>
<span id="l1521"></span><a href="#l1521"></a>
<span id="l1522">        int length = 1;</span><a href="#l1522"></a>
<span id="l1523">        int nCodePoints = countCodePoints(input);</span><a href="#l1523"></a>
<span id="l1524">        for(int x=1; x&lt;nCodePoints; x++)</span><a href="#l1524"></a>
<span id="l1525">            length = length * (x+1);</span><a href="#l1525"></a>
<span id="l1526"></span><a href="#l1526"></a>
<span id="l1527">        String[] temp = new String[length];</span><a href="#l1527"></a>
<span id="l1528"></span><a href="#l1528"></a>
<span id="l1529">        int combClass[] = new int[nCodePoints];</span><a href="#l1529"></a>
<span id="l1530">        for(int x=0, i=0; x&lt;nCodePoints; x++) {</span><a href="#l1530"></a>
<span id="l1531">            int c = Character.codePointAt(input, i);</span><a href="#l1531"></a>
<span id="l1532">            combClass[x] = getClass(c);</span><a href="#l1532"></a>
<span id="l1533">            i +=  Character.charCount(c);</span><a href="#l1533"></a>
<span id="l1534">        }</span><a href="#l1534"></a>
<span id="l1535"></span><a href="#l1535"></a>
<span id="l1536">        // For each char, take it out and add the permutations</span><a href="#l1536"></a>
<span id="l1537">        // of the remaining chars</span><a href="#l1537"></a>
<span id="l1538">        int index = 0;</span><a href="#l1538"></a>
<span id="l1539">        int len;</span><a href="#l1539"></a>
<span id="l1540">        // offset maintains the index in code units.</span><a href="#l1540"></a>
<span id="l1541">loop:   for(int x=0, offset=0; x&lt;nCodePoints; x++, offset+=len) {</span><a href="#l1541"></a>
<span id="l1542">            len = countChars(input, offset, 1);</span><a href="#l1542"></a>
<span id="l1543">            boolean skip = false;</span><a href="#l1543"></a>
<span id="l1544">            for(int y=x-1; y&gt;=0; y--) {</span><a href="#l1544"></a>
<span id="l1545">                if (combClass[y] == combClass[x]) {</span><a href="#l1545"></a>
<span id="l1546">                    continue loop;</span><a href="#l1546"></a>
<span id="l1547">                }</span><a href="#l1547"></a>
<span id="l1548">            }</span><a href="#l1548"></a>
<span id="l1549">            StringBuilder sb = new StringBuilder(input);</span><a href="#l1549"></a>
<span id="l1550">            String otherChars = sb.delete(offset, offset+len).toString();</span><a href="#l1550"></a>
<span id="l1551">            String[] subResult = producePermutations(otherChars);</span><a href="#l1551"></a>
<span id="l1552"></span><a href="#l1552"></a>
<span id="l1553">            String prefix = input.substring(offset, offset+len);</span><a href="#l1553"></a>
<span id="l1554">            for(int y=0; y&lt;subResult.length; y++)</span><a href="#l1554"></a>
<span id="l1555">                temp[index++] =  prefix + subResult[y];</span><a href="#l1555"></a>
<span id="l1556">        }</span><a href="#l1556"></a>
<span id="l1557">        String[] result = new String[index];</span><a href="#l1557"></a>
<span id="l1558">        for (int x=0; x&lt;index; x++)</span><a href="#l1558"></a>
<span id="l1559">            result[x] = temp[x];</span><a href="#l1559"></a>
<span id="l1560">        return result;</span><a href="#l1560"></a>
<span id="l1561">    }</span><a href="#l1561"></a>
<span id="l1562"></span><a href="#l1562"></a>
<span id="l1563">    private int getClass(int c) {</span><a href="#l1563"></a>
<span id="l1564">        return sun.text.Normalizer.getCombiningClass(c);</span><a href="#l1564"></a>
<span id="l1565">    }</span><a href="#l1565"></a>
<span id="l1566"></span><a href="#l1566"></a>
<span id="l1567">    /**</span><a href="#l1567"></a>
<span id="l1568">     * Attempts to compose input by combining the first character</span><a href="#l1568"></a>
<span id="l1569">     * with the first combining mark following it. Returns a String</span><a href="#l1569"></a>
<span id="l1570">     * that is the composition of the leading character with its first</span><a href="#l1570"></a>
<span id="l1571">     * combining mark followed by the remaining combining marks. Returns</span><a href="#l1571"></a>
<span id="l1572">     * null if the first two characters cannot be further composed.</span><a href="#l1572"></a>
<span id="l1573">     */</span><a href="#l1573"></a>
<span id="l1574">    private String composeOneStep(String input) {</span><a href="#l1574"></a>
<span id="l1575">        int len = countChars(input, 0, 2);</span><a href="#l1575"></a>
<span id="l1576">        String firstTwoCharacters = input.substring(0, len);</span><a href="#l1576"></a>
<span id="l1577">        String result = Normalizer.normalize(firstTwoCharacters, Normalizer.Form.NFC);</span><a href="#l1577"></a>
<span id="l1578"></span><a href="#l1578"></a>
<span id="l1579">        if (result.equals(firstTwoCharacters))</span><a href="#l1579"></a>
<span id="l1580">            return null;</span><a href="#l1580"></a>
<span id="l1581">        else {</span><a href="#l1581"></a>
<span id="l1582">            String remainder = input.substring(len);</span><a href="#l1582"></a>
<span id="l1583">            return result + remainder;</span><a href="#l1583"></a>
<span id="l1584">        }</span><a href="#l1584"></a>
<span id="l1585">    }</span><a href="#l1585"></a>
<span id="l1586"></span><a href="#l1586"></a>
<span id="l1587">    /**</span><a href="#l1587"></a>
<span id="l1588">     * Preprocess any \Q...\E sequences in `temp', meta-quoting them.</span><a href="#l1588"></a>
<span id="l1589">     * See the description of `quotemeta' in perlfunc(1).</span><a href="#l1589"></a>
<span id="l1590">     */</span><a href="#l1590"></a>
<span id="l1591">    private void RemoveQEQuoting() {</span><a href="#l1591"></a>
<span id="l1592">        final int pLen = patternLength;</span><a href="#l1592"></a>
<span id="l1593">        int i = 0;</span><a href="#l1593"></a>
<span id="l1594">        while (i &lt; pLen-1) {</span><a href="#l1594"></a>
<span id="l1595">            if (temp[i] != '\\')</span><a href="#l1595"></a>
<span id="l1596">                i += 1;</span><a href="#l1596"></a>
<span id="l1597">            else if (temp[i + 1] != 'Q')</span><a href="#l1597"></a>
<span id="l1598">                i += 2;</span><a href="#l1598"></a>
<span id="l1599">            else</span><a href="#l1599"></a>
<span id="l1600">                break;</span><a href="#l1600"></a>
<span id="l1601">        }</span><a href="#l1601"></a>
<span id="l1602">        if (i &gt;= pLen - 1)    // No \Q sequence found</span><a href="#l1602"></a>
<span id="l1603">            return;</span><a href="#l1603"></a>
<span id="l1604">        int j = i;</span><a href="#l1604"></a>
<span id="l1605">        i += 2;</span><a href="#l1605"></a>
<span id="l1606">        int[] newtemp = new int[j + 3*(pLen-i) + 2];</span><a href="#l1606"></a>
<span id="l1607">        System.arraycopy(temp, 0, newtemp, 0, j);</span><a href="#l1607"></a>
<span id="l1608"></span><a href="#l1608"></a>
<span id="l1609">        boolean inQuote = true;</span><a href="#l1609"></a>
<span id="l1610">        boolean beginQuote = true;</span><a href="#l1610"></a>
<span id="l1611">        while (i &lt; pLen) {</span><a href="#l1611"></a>
<span id="l1612">            int c = temp[i++];</span><a href="#l1612"></a>
<span id="l1613">            if (!ASCII.isAscii(c) || ASCII.isAlpha(c)) {</span><a href="#l1613"></a>
<span id="l1614">                newtemp[j++] = c;</span><a href="#l1614"></a>
<span id="l1615">            } else if (ASCII.isDigit(c)) {</span><a href="#l1615"></a>
<span id="l1616">                if (beginQuote) {</span><a href="#l1616"></a>
<span id="l1617">                    /*</span><a href="#l1617"></a>
<span id="l1618">                     * A unicode escape \[0xu] could be before this quote,</span><a href="#l1618"></a>
<span id="l1619">                     * and we don't want this numeric char to processed as</span><a href="#l1619"></a>
<span id="l1620">                     * part of the escape.</span><a href="#l1620"></a>
<span id="l1621">                     */</span><a href="#l1621"></a>
<span id="l1622">                    newtemp[j++] = '\\';</span><a href="#l1622"></a>
<span id="l1623">                    newtemp[j++] = 'x';</span><a href="#l1623"></a>
<span id="l1624">                    newtemp[j++] = '3';</span><a href="#l1624"></a>
<span id="l1625">                }</span><a href="#l1625"></a>
<span id="l1626">                newtemp[j++] = c;</span><a href="#l1626"></a>
<span id="l1627">            } else if (c != '\\') {</span><a href="#l1627"></a>
<span id="l1628">                if (inQuote) newtemp[j++] = '\\';</span><a href="#l1628"></a>
<span id="l1629">                newtemp[j++] = c;</span><a href="#l1629"></a>
<span id="l1630">            } else if (inQuote) {</span><a href="#l1630"></a>
<span id="l1631">                if (temp[i] == 'E') {</span><a href="#l1631"></a>
<span id="l1632">                    i++;</span><a href="#l1632"></a>
<span id="l1633">                    inQuote = false;</span><a href="#l1633"></a>
<span id="l1634">                } else {</span><a href="#l1634"></a>
<span id="l1635">                    newtemp[j++] = '\\';</span><a href="#l1635"></a>
<span id="l1636">                    newtemp[j++] = '\\';</span><a href="#l1636"></a>
<span id="l1637">                }</span><a href="#l1637"></a>
<span id="l1638">            } else {</span><a href="#l1638"></a>
<span id="l1639">                if (temp[i] == 'Q') {</span><a href="#l1639"></a>
<span id="l1640">                    i++;</span><a href="#l1640"></a>
<span id="l1641">                    inQuote = true;</span><a href="#l1641"></a>
<span id="l1642">                    beginQuote = true;</span><a href="#l1642"></a>
<span id="l1643">                    continue;</span><a href="#l1643"></a>
<span id="l1644">                } else {</span><a href="#l1644"></a>
<span id="l1645">                    newtemp[j++] = c;</span><a href="#l1645"></a>
<span id="l1646">                    if (i != pLen)</span><a href="#l1646"></a>
<span id="l1647">                        newtemp[j++] = temp[i++];</span><a href="#l1647"></a>
<span id="l1648">                }</span><a href="#l1648"></a>
<span id="l1649">            }</span><a href="#l1649"></a>
<span id="l1650"></span><a href="#l1650"></a>
<span id="l1651">            beginQuote = false;</span><a href="#l1651"></a>
<span id="l1652">        }</span><a href="#l1652"></a>
<span id="l1653"></span><a href="#l1653"></a>
<span id="l1654">        patternLength = j;</span><a href="#l1654"></a>
<span id="l1655">        temp = Arrays.copyOf(newtemp, j + 2); // double zero termination</span><a href="#l1655"></a>
<span id="l1656">    }</span><a href="#l1656"></a>
<span id="l1657"></span><a href="#l1657"></a>
<span id="l1658">    /**</span><a href="#l1658"></a>
<span id="l1659">     * Copies regular expression to an int array and invokes the parsing</span><a href="#l1659"></a>
<span id="l1660">     * of the expression which will create the object tree.</span><a href="#l1660"></a>
<span id="l1661">     */</span><a href="#l1661"></a>
<span id="l1662">    private void compile() {</span><a href="#l1662"></a>
<span id="l1663">        // Handle canonical equivalences</span><a href="#l1663"></a>
<span id="l1664">        if (has(CANON_EQ) &amp;&amp; !has(LITERAL)) {</span><a href="#l1664"></a>
<span id="l1665">            normalize();</span><a href="#l1665"></a>
<span id="l1666">        } else {</span><a href="#l1666"></a>
<span id="l1667">            normalizedPattern = pattern;</span><a href="#l1667"></a>
<span id="l1668">        }</span><a href="#l1668"></a>
<span id="l1669">        patternLength = normalizedPattern.length();</span><a href="#l1669"></a>
<span id="l1670"></span><a href="#l1670"></a>
<span id="l1671">        // Copy pattern to int array for convenience</span><a href="#l1671"></a>
<span id="l1672">        // Use double zero to terminate pattern</span><a href="#l1672"></a>
<span id="l1673">        temp = new int[patternLength + 2];</span><a href="#l1673"></a>
<span id="l1674"></span><a href="#l1674"></a>
<span id="l1675">        hasSupplementary = false;</span><a href="#l1675"></a>
<span id="l1676">        int c, count = 0;</span><a href="#l1676"></a>
<span id="l1677">        // Convert all chars into code points</span><a href="#l1677"></a>
<span id="l1678">        for (int x = 0; x &lt; patternLength; x += Character.charCount(c)) {</span><a href="#l1678"></a>
<span id="l1679">            c = normalizedPattern.codePointAt(x);</span><a href="#l1679"></a>
<span id="l1680">            if (isSupplementary(c)) {</span><a href="#l1680"></a>
<span id="l1681">                hasSupplementary = true;</span><a href="#l1681"></a>
<span id="l1682">            }</span><a href="#l1682"></a>
<span id="l1683">            temp[count++] = c;</span><a href="#l1683"></a>
<span id="l1684">        }</span><a href="#l1684"></a>
<span id="l1685"></span><a href="#l1685"></a>
<span id="l1686">        patternLength = count;   // patternLength now in code points</span><a href="#l1686"></a>
<span id="l1687"></span><a href="#l1687"></a>
<span id="l1688">        if (! has(LITERAL))</span><a href="#l1688"></a>
<span id="l1689">            RemoveQEQuoting();</span><a href="#l1689"></a>
<span id="l1690"></span><a href="#l1690"></a>
<span id="l1691">        // Allocate all temporary objects here.</span><a href="#l1691"></a>
<span id="l1692">        buffer = new int[32];</span><a href="#l1692"></a>
<span id="l1693">        groupNodes = new GroupHead[10];</span><a href="#l1693"></a>
<span id="l1694">        namedGroups = null;</span><a href="#l1694"></a>
<span id="l1695"></span><a href="#l1695"></a>
<span id="l1696">        if (has(LITERAL)) {</span><a href="#l1696"></a>
<span id="l1697">            // Literal pattern handling</span><a href="#l1697"></a>
<span id="l1698">            matchRoot = newSlice(temp, patternLength, hasSupplementary);</span><a href="#l1698"></a>
<span id="l1699">            matchRoot.next = lastAccept;</span><a href="#l1699"></a>
<span id="l1700">        } else {</span><a href="#l1700"></a>
<span id="l1701">            // Start recursive descent parsing</span><a href="#l1701"></a>
<span id="l1702">            matchRoot = expr(lastAccept);</span><a href="#l1702"></a>
<span id="l1703">            // Check extra pattern characters</span><a href="#l1703"></a>
<span id="l1704">            if (patternLength != cursor) {</span><a href="#l1704"></a>
<span id="l1705">                if (peek() == ')') {</span><a href="#l1705"></a>
<span id="l1706">                    throw error(&quot;Unmatched closing ')'&quot;);</span><a href="#l1706"></a>
<span id="l1707">                } else {</span><a href="#l1707"></a>
<span id="l1708">                    throw error(&quot;Unexpected internal error&quot;);</span><a href="#l1708"></a>
<span id="l1709">                }</span><a href="#l1709"></a>
<span id="l1710">            }</span><a href="#l1710"></a>
<span id="l1711">        }</span><a href="#l1711"></a>
<span id="l1712"></span><a href="#l1712"></a>
<span id="l1713">        // Peephole optimization</span><a href="#l1713"></a>
<span id="l1714">        if (matchRoot instanceof Slice) {</span><a href="#l1714"></a>
<span id="l1715">            root = BnM.optimize(matchRoot);</span><a href="#l1715"></a>
<span id="l1716">            if (root == matchRoot) {</span><a href="#l1716"></a>
<span id="l1717">                root = hasSupplementary ? new StartS(matchRoot) : new Start(matchRoot);</span><a href="#l1717"></a>
<span id="l1718">            }</span><a href="#l1718"></a>
<span id="l1719">        } else if (matchRoot instanceof Begin || matchRoot instanceof First) {</span><a href="#l1719"></a>
<span id="l1720">            root = matchRoot;</span><a href="#l1720"></a>
<span id="l1721">        } else {</span><a href="#l1721"></a>
<span id="l1722">            root = hasSupplementary ? new StartS(matchRoot) : new Start(matchRoot);</span><a href="#l1722"></a>
<span id="l1723">        }</span><a href="#l1723"></a>
<span id="l1724"></span><a href="#l1724"></a>
<span id="l1725">        // Release temporary storage</span><a href="#l1725"></a>
<span id="l1726">        temp = null;</span><a href="#l1726"></a>
<span id="l1727">        buffer = null;</span><a href="#l1727"></a>
<span id="l1728">        groupNodes = null;</span><a href="#l1728"></a>
<span id="l1729">        patternLength = 0;</span><a href="#l1729"></a>
<span id="l1730">        compiled = true;</span><a href="#l1730"></a>
<span id="l1731">    }</span><a href="#l1731"></a>
<span id="l1732"></span><a href="#l1732"></a>
<span id="l1733">    Map&lt;String, Integer&gt; namedGroups() {</span><a href="#l1733"></a>
<span id="l1734">        if (namedGroups == null)</span><a href="#l1734"></a>
<span id="l1735">            namedGroups = new HashMap&lt;&gt;(2);</span><a href="#l1735"></a>
<span id="l1736">        return namedGroups;</span><a href="#l1736"></a>
<span id="l1737">    }</span><a href="#l1737"></a>
<span id="l1738"></span><a href="#l1738"></a>
<span id="l1739">    /**</span><a href="#l1739"></a>
<span id="l1740">     * Used to print out a subtree of the Pattern to help with debugging.</span><a href="#l1740"></a>
<span id="l1741">     */</span><a href="#l1741"></a>
<span id="l1742">    private static void printObjectTree(Node node) {</span><a href="#l1742"></a>
<span id="l1743">        while(node != null) {</span><a href="#l1743"></a>
<span id="l1744">            if (node instanceof Prolog) {</span><a href="#l1744"></a>
<span id="l1745">                System.out.println(node);</span><a href="#l1745"></a>
<span id="l1746">                printObjectTree(((Prolog)node).loop);</span><a href="#l1746"></a>
<span id="l1747">                System.out.println(&quot;**** end contents prolog loop&quot;);</span><a href="#l1747"></a>
<span id="l1748">            } else if (node instanceof Loop) {</span><a href="#l1748"></a>
<span id="l1749">                System.out.println(node);</span><a href="#l1749"></a>
<span id="l1750">                printObjectTree(((Loop)node).body);</span><a href="#l1750"></a>
<span id="l1751">                System.out.println(&quot;**** end contents Loop body&quot;);</span><a href="#l1751"></a>
<span id="l1752">            } else if (node instanceof Curly) {</span><a href="#l1752"></a>
<span id="l1753">                System.out.println(node);</span><a href="#l1753"></a>
<span id="l1754">                printObjectTree(((Curly)node).atom);</span><a href="#l1754"></a>
<span id="l1755">                System.out.println(&quot;**** end contents Curly body&quot;);</span><a href="#l1755"></a>
<span id="l1756">            } else if (node instanceof GroupCurly) {</span><a href="#l1756"></a>
<span id="l1757">                System.out.println(node);</span><a href="#l1757"></a>
<span id="l1758">                printObjectTree(((GroupCurly)node).atom);</span><a href="#l1758"></a>
<span id="l1759">                System.out.println(&quot;**** end contents GroupCurly body&quot;);</span><a href="#l1759"></a>
<span id="l1760">            } else if (node instanceof GroupTail) {</span><a href="#l1760"></a>
<span id="l1761">                System.out.println(node);</span><a href="#l1761"></a>
<span id="l1762">                System.out.println(&quot;Tail next is &quot;+node.next);</span><a href="#l1762"></a>
<span id="l1763">                return;</span><a href="#l1763"></a>
<span id="l1764">            } else {</span><a href="#l1764"></a>
<span id="l1765">                System.out.println(node);</span><a href="#l1765"></a>
<span id="l1766">            }</span><a href="#l1766"></a>
<span id="l1767">            node = node.next;</span><a href="#l1767"></a>
<span id="l1768">            if (node != null)</span><a href="#l1768"></a>
<span id="l1769">                System.out.println(&quot;-&gt;next:&quot;);</span><a href="#l1769"></a>
<span id="l1770">            if (node == Pattern.accept) {</span><a href="#l1770"></a>
<span id="l1771">                System.out.println(&quot;Accept Node&quot;);</span><a href="#l1771"></a>
<span id="l1772">                node = null;</span><a href="#l1772"></a>
<span id="l1773">            }</span><a href="#l1773"></a>
<span id="l1774">       }</span><a href="#l1774"></a>
<span id="l1775">    }</span><a href="#l1775"></a>
<span id="l1776"></span><a href="#l1776"></a>
<span id="l1777">    /**</span><a href="#l1777"></a>
<span id="l1778">     * Used to accumulate information about a subtree of the object graph</span><a href="#l1778"></a>
<span id="l1779">     * so that optimizations can be applied to the subtree.</span><a href="#l1779"></a>
<span id="l1780">     */</span><a href="#l1780"></a>
<span id="l1781">    static final class TreeInfo {</span><a href="#l1781"></a>
<span id="l1782">        int minLength;</span><a href="#l1782"></a>
<span id="l1783">        int maxLength;</span><a href="#l1783"></a>
<span id="l1784">        boolean maxValid;</span><a href="#l1784"></a>
<span id="l1785">        boolean deterministic;</span><a href="#l1785"></a>
<span id="l1786"></span><a href="#l1786"></a>
<span id="l1787">        TreeInfo() {</span><a href="#l1787"></a>
<span id="l1788">            reset();</span><a href="#l1788"></a>
<span id="l1789">        }</span><a href="#l1789"></a>
<span id="l1790">        void reset() {</span><a href="#l1790"></a>
<span id="l1791">            minLength = 0;</span><a href="#l1791"></a>
<span id="l1792">            maxLength = 0;</span><a href="#l1792"></a>
<span id="l1793">            maxValid = true;</span><a href="#l1793"></a>
<span id="l1794">            deterministic = true;</span><a href="#l1794"></a>
<span id="l1795">        }</span><a href="#l1795"></a>
<span id="l1796">    }</span><a href="#l1796"></a>
<span id="l1797"></span><a href="#l1797"></a>
<span id="l1798">    /*</span><a href="#l1798"></a>
<span id="l1799">     * The following private methods are mainly used to improve the</span><a href="#l1799"></a>
<span id="l1800">     * readability of the code. In order to let the Java compiler easily</span><a href="#l1800"></a>
<span id="l1801">     * inline them, we should not put many assertions or error checks in them.</span><a href="#l1801"></a>
<span id="l1802">     */</span><a href="#l1802"></a>
<span id="l1803"></span><a href="#l1803"></a>
<span id="l1804">    /**</span><a href="#l1804"></a>
<span id="l1805">     * Indicates whether a particular flag is set or not.</span><a href="#l1805"></a>
<span id="l1806">     */</span><a href="#l1806"></a>
<span id="l1807">    private boolean has(int f) {</span><a href="#l1807"></a>
<span id="l1808">        return (flags &amp; f) != 0;</span><a href="#l1808"></a>
<span id="l1809">    }</span><a href="#l1809"></a>
<span id="l1810"></span><a href="#l1810"></a>
<span id="l1811">    /**</span><a href="#l1811"></a>
<span id="l1812">     * Match next character, signal error if failed.</span><a href="#l1812"></a>
<span id="l1813">     */</span><a href="#l1813"></a>
<span id="l1814">    private void accept(int ch, String s) {</span><a href="#l1814"></a>
<span id="l1815">        int testChar = temp[cursor++];</span><a href="#l1815"></a>
<span id="l1816">        if (has(COMMENTS))</span><a href="#l1816"></a>
<span id="l1817">            testChar = parsePastWhitespace(testChar);</span><a href="#l1817"></a>
<span id="l1818">        if (ch != testChar) {</span><a href="#l1818"></a>
<span id="l1819">            throw error(s);</span><a href="#l1819"></a>
<span id="l1820">        }</span><a href="#l1820"></a>
<span id="l1821">    }</span><a href="#l1821"></a>
<span id="l1822"></span><a href="#l1822"></a>
<span id="l1823">    /**</span><a href="#l1823"></a>
<span id="l1824">     * Mark the end of pattern with a specific character.</span><a href="#l1824"></a>
<span id="l1825">     */</span><a href="#l1825"></a>
<span id="l1826">    private void mark(int c) {</span><a href="#l1826"></a>
<span id="l1827">        temp[patternLength] = c;</span><a href="#l1827"></a>
<span id="l1828">    }</span><a href="#l1828"></a>
<span id="l1829"></span><a href="#l1829"></a>
<span id="l1830">    /**</span><a href="#l1830"></a>
<span id="l1831">     * Peek the next character, and do not advance the cursor.</span><a href="#l1831"></a>
<span id="l1832">     */</span><a href="#l1832"></a>
<span id="l1833">    private int peek() {</span><a href="#l1833"></a>
<span id="l1834">        int ch = temp[cursor];</span><a href="#l1834"></a>
<span id="l1835">        if (has(COMMENTS))</span><a href="#l1835"></a>
<span id="l1836">            ch = peekPastWhitespace(ch);</span><a href="#l1836"></a>
<span id="l1837">        return ch;</span><a href="#l1837"></a>
<span id="l1838">    }</span><a href="#l1838"></a>
<span id="l1839"></span><a href="#l1839"></a>
<span id="l1840">    /**</span><a href="#l1840"></a>
<span id="l1841">     * Read the next character, and advance the cursor by one.</span><a href="#l1841"></a>
<span id="l1842">     */</span><a href="#l1842"></a>
<span id="l1843">    private int read() {</span><a href="#l1843"></a>
<span id="l1844">        int ch = temp[cursor++];</span><a href="#l1844"></a>
<span id="l1845">        if (has(COMMENTS))</span><a href="#l1845"></a>
<span id="l1846">            ch = parsePastWhitespace(ch);</span><a href="#l1846"></a>
<span id="l1847">        return ch;</span><a href="#l1847"></a>
<span id="l1848">    }</span><a href="#l1848"></a>
<span id="l1849"></span><a href="#l1849"></a>
<span id="l1850">    /**</span><a href="#l1850"></a>
<span id="l1851">     * Read the next character, and advance the cursor by one,</span><a href="#l1851"></a>
<span id="l1852">     * ignoring the COMMENTS setting</span><a href="#l1852"></a>
<span id="l1853">     */</span><a href="#l1853"></a>
<span id="l1854">    private int readEscaped() {</span><a href="#l1854"></a>
<span id="l1855">        int ch = temp[cursor++];</span><a href="#l1855"></a>
<span id="l1856">        return ch;</span><a href="#l1856"></a>
<span id="l1857">    }</span><a href="#l1857"></a>
<span id="l1858"></span><a href="#l1858"></a>
<span id="l1859">    /**</span><a href="#l1859"></a>
<span id="l1860">     * Advance the cursor by one, and peek the next character.</span><a href="#l1860"></a>
<span id="l1861">     */</span><a href="#l1861"></a>
<span id="l1862">    private int next() {</span><a href="#l1862"></a>
<span id="l1863">        int ch = temp[++cursor];</span><a href="#l1863"></a>
<span id="l1864">        if (has(COMMENTS))</span><a href="#l1864"></a>
<span id="l1865">            ch = peekPastWhitespace(ch);</span><a href="#l1865"></a>
<span id="l1866">        return ch;</span><a href="#l1866"></a>
<span id="l1867">    }</span><a href="#l1867"></a>
<span id="l1868"></span><a href="#l1868"></a>
<span id="l1869">    /**</span><a href="#l1869"></a>
<span id="l1870">     * Advance the cursor by one, and peek the next character,</span><a href="#l1870"></a>
<span id="l1871">     * ignoring the COMMENTS setting</span><a href="#l1871"></a>
<span id="l1872">     */</span><a href="#l1872"></a>
<span id="l1873">    private int nextEscaped() {</span><a href="#l1873"></a>
<span id="l1874">        int ch = temp[++cursor];</span><a href="#l1874"></a>
<span id="l1875">        return ch;</span><a href="#l1875"></a>
<span id="l1876">    }</span><a href="#l1876"></a>
<span id="l1877"></span><a href="#l1877"></a>
<span id="l1878">    /**</span><a href="#l1878"></a>
<span id="l1879">     * If in xmode peek past whitespace and comments.</span><a href="#l1879"></a>
<span id="l1880">     */</span><a href="#l1880"></a>
<span id="l1881">    private int peekPastWhitespace(int ch) {</span><a href="#l1881"></a>
<span id="l1882">        while (ASCII.isSpace(ch) || ch == '#') {</span><a href="#l1882"></a>
<span id="l1883">            while (ASCII.isSpace(ch))</span><a href="#l1883"></a>
<span id="l1884">                ch = temp[++cursor];</span><a href="#l1884"></a>
<span id="l1885">            if (ch == '#') {</span><a href="#l1885"></a>
<span id="l1886">                ch = peekPastLine();</span><a href="#l1886"></a>
<span id="l1887">            }</span><a href="#l1887"></a>
<span id="l1888">        }</span><a href="#l1888"></a>
<span id="l1889">        return ch;</span><a href="#l1889"></a>
<span id="l1890">    }</span><a href="#l1890"></a>
<span id="l1891"></span><a href="#l1891"></a>
<span id="l1892">    /**</span><a href="#l1892"></a>
<span id="l1893">     * If in xmode parse past whitespace and comments.</span><a href="#l1893"></a>
<span id="l1894">     */</span><a href="#l1894"></a>
<span id="l1895">    private int parsePastWhitespace(int ch) {</span><a href="#l1895"></a>
<span id="l1896">        while (ASCII.isSpace(ch) || ch == '#') {</span><a href="#l1896"></a>
<span id="l1897">            while (ASCII.isSpace(ch))</span><a href="#l1897"></a>
<span id="l1898">                ch = temp[cursor++];</span><a href="#l1898"></a>
<span id="l1899">            if (ch == '#')</span><a href="#l1899"></a>
<span id="l1900">                ch = parsePastLine();</span><a href="#l1900"></a>
<span id="l1901">        }</span><a href="#l1901"></a>
<span id="l1902">        return ch;</span><a href="#l1902"></a>
<span id="l1903">    }</span><a href="#l1903"></a>
<span id="l1904"></span><a href="#l1904"></a>
<span id="l1905">    /**</span><a href="#l1905"></a>
<span id="l1906">     * xmode parse past comment to end of line.</span><a href="#l1906"></a>
<span id="l1907">     */</span><a href="#l1907"></a>
<span id="l1908">    private int parsePastLine() {</span><a href="#l1908"></a>
<span id="l1909">        int ch = temp[cursor++];</span><a href="#l1909"></a>
<span id="l1910">        while (ch != 0 &amp;&amp; !isLineSeparator(ch))</span><a href="#l1910"></a>
<span id="l1911">            ch = temp[cursor++];</span><a href="#l1911"></a>
<span id="l1912">        if (ch == 0 &amp;&amp; cursor &gt; patternLength) {</span><a href="#l1912"></a>
<span id="l1913">            cursor = patternLength;</span><a href="#l1913"></a>
<span id="l1914">            ch = temp[cursor++];</span><a href="#l1914"></a>
<span id="l1915">        }</span><a href="#l1915"></a>
<span id="l1916">        return ch;</span><a href="#l1916"></a>
<span id="l1917">    }</span><a href="#l1917"></a>
<span id="l1918"></span><a href="#l1918"></a>
<span id="l1919">    /**</span><a href="#l1919"></a>
<span id="l1920">     * xmode peek past comment to end of line.</span><a href="#l1920"></a>
<span id="l1921">     */</span><a href="#l1921"></a>
<span id="l1922">    private int peekPastLine() {</span><a href="#l1922"></a>
<span id="l1923">        int ch = temp[++cursor];</span><a href="#l1923"></a>
<span id="l1924">        while (ch != 0 &amp;&amp; !isLineSeparator(ch))</span><a href="#l1924"></a>
<span id="l1925">            ch = temp[++cursor];</span><a href="#l1925"></a>
<span id="l1926">        if (ch == 0 &amp;&amp; cursor &gt; patternLength) {</span><a href="#l1926"></a>
<span id="l1927">            cursor = patternLength;</span><a href="#l1927"></a>
<span id="l1928">            ch = temp[cursor];</span><a href="#l1928"></a>
<span id="l1929">        }</span><a href="#l1929"></a>
<span id="l1930">        return ch;</span><a href="#l1930"></a>
<span id="l1931">    }</span><a href="#l1931"></a>
<span id="l1932"></span><a href="#l1932"></a>
<span id="l1933">    /**</span><a href="#l1933"></a>
<span id="l1934">     * Determines if character is a line separator in the current mode</span><a href="#l1934"></a>
<span id="l1935">     */</span><a href="#l1935"></a>
<span id="l1936">    private boolean isLineSeparator(int ch) {</span><a href="#l1936"></a>
<span id="l1937">        if (has(UNIX_LINES)) {</span><a href="#l1937"></a>
<span id="l1938">            return ch == '\n';</span><a href="#l1938"></a>
<span id="l1939">        } else {</span><a href="#l1939"></a>
<span id="l1940">            return (ch == '\n' ||</span><a href="#l1940"></a>
<span id="l1941">                    ch == '\r' ||</span><a href="#l1941"></a>
<span id="l1942">                    (ch|1) == '\u2029' ||</span><a href="#l1942"></a>
<span id="l1943">                    ch == '\u0085');</span><a href="#l1943"></a>
<span id="l1944">        }</span><a href="#l1944"></a>
<span id="l1945">    }</span><a href="#l1945"></a>
<span id="l1946"></span><a href="#l1946"></a>
<span id="l1947">    /**</span><a href="#l1947"></a>
<span id="l1948">     * Read the character after the next one, and advance the cursor by two.</span><a href="#l1948"></a>
<span id="l1949">     */</span><a href="#l1949"></a>
<span id="l1950">    private int skip() {</span><a href="#l1950"></a>
<span id="l1951">        int i = cursor;</span><a href="#l1951"></a>
<span id="l1952">        int ch = temp[i+1];</span><a href="#l1952"></a>
<span id="l1953">        cursor = i + 2;</span><a href="#l1953"></a>
<span id="l1954">        return ch;</span><a href="#l1954"></a>
<span id="l1955">    }</span><a href="#l1955"></a>
<span id="l1956"></span><a href="#l1956"></a>
<span id="l1957">    /**</span><a href="#l1957"></a>
<span id="l1958">     * Unread one next character, and retreat cursor by one.</span><a href="#l1958"></a>
<span id="l1959">     */</span><a href="#l1959"></a>
<span id="l1960">    private void unread() {</span><a href="#l1960"></a>
<span id="l1961">        cursor--;</span><a href="#l1961"></a>
<span id="l1962">    }</span><a href="#l1962"></a>
<span id="l1963"></span><a href="#l1963"></a>
<span id="l1964">    /**</span><a href="#l1964"></a>
<span id="l1965">     * Internal method used for handling all syntax errors. The pattern is</span><a href="#l1965"></a>
<span id="l1966">     * displayed with a pointer to aid in locating the syntax error.</span><a href="#l1966"></a>
<span id="l1967">     */</span><a href="#l1967"></a>
<span id="l1968">    private PatternSyntaxException error(String s) {</span><a href="#l1968"></a>
<span id="l1969">        return new PatternSyntaxException(s, normalizedPattern,  cursor - 1);</span><a href="#l1969"></a>
<span id="l1970">    }</span><a href="#l1970"></a>
<span id="l1971"></span><a href="#l1971"></a>
<span id="l1972">    /**</span><a href="#l1972"></a>
<span id="l1973">     * Determines if there is any supplementary character or unpaired</span><a href="#l1973"></a>
<span id="l1974">     * surrogate in the specified range.</span><a href="#l1974"></a>
<span id="l1975">     */</span><a href="#l1975"></a>
<span id="l1976">    private boolean findSupplementary(int start, int end) {</span><a href="#l1976"></a>
<span id="l1977">        for (int i = start; i &lt; end; i++) {</span><a href="#l1977"></a>
<span id="l1978">            if (isSupplementary(temp[i]))</span><a href="#l1978"></a>
<span id="l1979">                return true;</span><a href="#l1979"></a>
<span id="l1980">        }</span><a href="#l1980"></a>
<span id="l1981">        return false;</span><a href="#l1981"></a>
<span id="l1982">    }</span><a href="#l1982"></a>
<span id="l1983"></span><a href="#l1983"></a>
<span id="l1984">    /**</span><a href="#l1984"></a>
<span id="l1985">     * Determines if the specified code point is a supplementary</span><a href="#l1985"></a>
<span id="l1986">     * character or unpaired surrogate.</span><a href="#l1986"></a>
<span id="l1987">     */</span><a href="#l1987"></a>
<span id="l1988">    private static final boolean isSupplementary(int ch) {</span><a href="#l1988"></a>
<span id="l1989">        return ch &gt;= Character.MIN_SUPPLEMENTARY_CODE_POINT ||</span><a href="#l1989"></a>
<span id="l1990">               Character.isSurrogate((char)ch);</span><a href="#l1990"></a>
<span id="l1991">    }</span><a href="#l1991"></a>
<span id="l1992"></span><a href="#l1992"></a>
<span id="l1993">    /**</span><a href="#l1993"></a>
<span id="l1994">     *  The following methods handle the main parsing. They are sorted</span><a href="#l1994"></a>
<span id="l1995">     *  according to their precedence order, the lowest one first.</span><a href="#l1995"></a>
<span id="l1996">     */</span><a href="#l1996"></a>
<span id="l1997"></span><a href="#l1997"></a>
<span id="l1998">    /**</span><a href="#l1998"></a>
<span id="l1999">     * The expression is parsed with branch nodes added for alternations.</span><a href="#l1999"></a>
<span id="l2000">     * This may be called recursively to parse sub expressions that may</span><a href="#l2000"></a>
<span id="l2001">     * contain alternations.</span><a href="#l2001"></a>
<span id="l2002">     */</span><a href="#l2002"></a>
<span id="l2003">    private Node expr(Node end) {</span><a href="#l2003"></a>
<span id="l2004">        Node prev = null;</span><a href="#l2004"></a>
<span id="l2005">        Node firstTail = null;</span><a href="#l2005"></a>
<span id="l2006">        Branch branch = null;</span><a href="#l2006"></a>
<span id="l2007">        Node branchConn = null;</span><a href="#l2007"></a>
<span id="l2008"></span><a href="#l2008"></a>
<span id="l2009">        for (;;) {</span><a href="#l2009"></a>
<span id="l2010">            Node node = sequence(end);</span><a href="#l2010"></a>
<span id="l2011">            Node nodeTail = root;      //double return</span><a href="#l2011"></a>
<span id="l2012">            if (prev == null) {</span><a href="#l2012"></a>
<span id="l2013">                prev = node;</span><a href="#l2013"></a>
<span id="l2014">                firstTail = nodeTail;</span><a href="#l2014"></a>
<span id="l2015">            } else {</span><a href="#l2015"></a>
<span id="l2016">                // Branch</span><a href="#l2016"></a>
<span id="l2017">                if (branchConn == null) {</span><a href="#l2017"></a>
<span id="l2018">                    branchConn = new BranchConn();</span><a href="#l2018"></a>
<span id="l2019">                    branchConn.next = end;</span><a href="#l2019"></a>
<span id="l2020">                }</span><a href="#l2020"></a>
<span id="l2021">                if (node == end) {</span><a href="#l2021"></a>
<span id="l2022">                    // if the node returned from sequence() is &quot;end&quot;</span><a href="#l2022"></a>
<span id="l2023">                    // we have an empty expr, set a null atom into</span><a href="#l2023"></a>
<span id="l2024">                    // the branch to indicate to go &quot;next&quot; directly.</span><a href="#l2024"></a>
<span id="l2025">                    node = null;</span><a href="#l2025"></a>
<span id="l2026">                } else {</span><a href="#l2026"></a>
<span id="l2027">                    // the &quot;tail.next&quot; of each atom goes to branchConn</span><a href="#l2027"></a>
<span id="l2028">                    nodeTail.next = branchConn;</span><a href="#l2028"></a>
<span id="l2029">                }</span><a href="#l2029"></a>
<span id="l2030">                if (prev == branch) {</span><a href="#l2030"></a>
<span id="l2031">                    branch.add(node);</span><a href="#l2031"></a>
<span id="l2032">                } else {</span><a href="#l2032"></a>
<span id="l2033">                    if (prev == end) {</span><a href="#l2033"></a>
<span id="l2034">                        prev = null;</span><a href="#l2034"></a>
<span id="l2035">                    } else {</span><a href="#l2035"></a>
<span id="l2036">                        // replace the &quot;end&quot; with &quot;branchConn&quot; at its tail.next</span><a href="#l2036"></a>
<span id="l2037">                        // when put the &quot;prev&quot; into the branch as the first atom.</span><a href="#l2037"></a>
<span id="l2038">                        firstTail.next = branchConn;</span><a href="#l2038"></a>
<span id="l2039">                    }</span><a href="#l2039"></a>
<span id="l2040">                    prev = branch = new Branch(prev, node, branchConn);</span><a href="#l2040"></a>
<span id="l2041">                }</span><a href="#l2041"></a>
<span id="l2042">            }</span><a href="#l2042"></a>
<span id="l2043">            if (peek() != '|') {</span><a href="#l2043"></a>
<span id="l2044">                return prev;</span><a href="#l2044"></a>
<span id="l2045">            }</span><a href="#l2045"></a>
<span id="l2046">            next();</span><a href="#l2046"></a>
<span id="l2047">        }</span><a href="#l2047"></a>
<span id="l2048">    }</span><a href="#l2048"></a>
<span id="l2049"></span><a href="#l2049"></a>
<span id="l2050">    @SuppressWarnings(&quot;fallthrough&quot;)</span><a href="#l2050"></a>
<span id="l2051">    /**</span><a href="#l2051"></a>
<span id="l2052">     * Parsing of sequences between alternations.</span><a href="#l2052"></a>
<span id="l2053">     */</span><a href="#l2053"></a>
<span id="l2054">    private Node sequence(Node end) {</span><a href="#l2054"></a>
<span id="l2055">        Node head = null;</span><a href="#l2055"></a>
<span id="l2056">        Node tail = null;</span><a href="#l2056"></a>
<span id="l2057">        Node node = null;</span><a href="#l2057"></a>
<span id="l2058">    LOOP:</span><a href="#l2058"></a>
<span id="l2059">        for (;;) {</span><a href="#l2059"></a>
<span id="l2060">            int ch = peek();</span><a href="#l2060"></a>
<span id="l2061">            switch (ch) {</span><a href="#l2061"></a>
<span id="l2062">            case '(':</span><a href="#l2062"></a>
<span id="l2063">                // Because group handles its own closure,</span><a href="#l2063"></a>
<span id="l2064">                // we need to treat it differently</span><a href="#l2064"></a>
<span id="l2065">                node = group0();</span><a href="#l2065"></a>
<span id="l2066">                // Check for comment or flag group</span><a href="#l2066"></a>
<span id="l2067">                if (node == null)</span><a href="#l2067"></a>
<span id="l2068">                    continue;</span><a href="#l2068"></a>
<span id="l2069">                if (head == null)</span><a href="#l2069"></a>
<span id="l2070">                    head = node;</span><a href="#l2070"></a>
<span id="l2071">                else</span><a href="#l2071"></a>
<span id="l2072">                    tail.next = node;</span><a href="#l2072"></a>
<span id="l2073">                // Double return: Tail was returned in root</span><a href="#l2073"></a>
<span id="l2074">                tail = root;</span><a href="#l2074"></a>
<span id="l2075">                continue;</span><a href="#l2075"></a>
<span id="l2076">            case '[':</span><a href="#l2076"></a>
<span id="l2077">                node = clazz(true);</span><a href="#l2077"></a>
<span id="l2078">                break;</span><a href="#l2078"></a>
<span id="l2079">            case '\\':</span><a href="#l2079"></a>
<span id="l2080">                ch = nextEscaped();</span><a href="#l2080"></a>
<span id="l2081">                if (ch == 'p' || ch == 'P') {</span><a href="#l2081"></a>
<span id="l2082">                    boolean oneLetter = true;</span><a href="#l2082"></a>
<span id="l2083">                    boolean comp = (ch == 'P');</span><a href="#l2083"></a>
<span id="l2084">                    ch = next(); // Consume { if present</span><a href="#l2084"></a>
<span id="l2085">                    if (ch != '{') {</span><a href="#l2085"></a>
<span id="l2086">                        unread();</span><a href="#l2086"></a>
<span id="l2087">                    } else {</span><a href="#l2087"></a>
<span id="l2088">                        oneLetter = false;</span><a href="#l2088"></a>
<span id="l2089">                    }</span><a href="#l2089"></a>
<span id="l2090">                    node = family(oneLetter, comp);</span><a href="#l2090"></a>
<span id="l2091">                } else {</span><a href="#l2091"></a>
<span id="l2092">                    unread();</span><a href="#l2092"></a>
<span id="l2093">                    node = atom();</span><a href="#l2093"></a>
<span id="l2094">                }</span><a href="#l2094"></a>
<span id="l2095">                break;</span><a href="#l2095"></a>
<span id="l2096">            case '^':</span><a href="#l2096"></a>
<span id="l2097">                next();</span><a href="#l2097"></a>
<span id="l2098">                if (has(MULTILINE)) {</span><a href="#l2098"></a>
<span id="l2099">                    if (has(UNIX_LINES))</span><a href="#l2099"></a>
<span id="l2100">                        node = new UnixCaret();</span><a href="#l2100"></a>
<span id="l2101">                    else</span><a href="#l2101"></a>
<span id="l2102">                        node = new Caret();</span><a href="#l2102"></a>
<span id="l2103">                } else {</span><a href="#l2103"></a>
<span id="l2104">                    node = new Begin();</span><a href="#l2104"></a>
<span id="l2105">                }</span><a href="#l2105"></a>
<span id="l2106">                break;</span><a href="#l2106"></a>
<span id="l2107">            case '$':</span><a href="#l2107"></a>
<span id="l2108">                next();</span><a href="#l2108"></a>
<span id="l2109">                if (has(UNIX_LINES))</span><a href="#l2109"></a>
<span id="l2110">                    node = new UnixDollar(has(MULTILINE));</span><a href="#l2110"></a>
<span id="l2111">                else</span><a href="#l2111"></a>
<span id="l2112">                    node = new Dollar(has(MULTILINE));</span><a href="#l2112"></a>
<span id="l2113">                break;</span><a href="#l2113"></a>
<span id="l2114">            case '.':</span><a href="#l2114"></a>
<span id="l2115">                next();</span><a href="#l2115"></a>
<span id="l2116">                if (has(DOTALL)) {</span><a href="#l2116"></a>
<span id="l2117">                    node = new All();</span><a href="#l2117"></a>
<span id="l2118">                } else {</span><a href="#l2118"></a>
<span id="l2119">                    if (has(UNIX_LINES))</span><a href="#l2119"></a>
<span id="l2120">                        node = new UnixDot();</span><a href="#l2120"></a>
<span id="l2121">                    else {</span><a href="#l2121"></a>
<span id="l2122">                        node = new Dot();</span><a href="#l2122"></a>
<span id="l2123">                    }</span><a href="#l2123"></a>
<span id="l2124">                }</span><a href="#l2124"></a>
<span id="l2125">                break;</span><a href="#l2125"></a>
<span id="l2126">            case '|':</span><a href="#l2126"></a>
<span id="l2127">            case ')':</span><a href="#l2127"></a>
<span id="l2128">                break LOOP;</span><a href="#l2128"></a>
<span id="l2129">            case ']': // Now interpreting dangling ] and } as literals</span><a href="#l2129"></a>
<span id="l2130">            case '}':</span><a href="#l2130"></a>
<span id="l2131">                node = atom();</span><a href="#l2131"></a>
<span id="l2132">                break;</span><a href="#l2132"></a>
<span id="l2133">            case '?':</span><a href="#l2133"></a>
<span id="l2134">            case '*':</span><a href="#l2134"></a>
<span id="l2135">            case '+':</span><a href="#l2135"></a>
<span id="l2136">                next();</span><a href="#l2136"></a>
<span id="l2137">                throw error(&quot;Dangling meta character '&quot; + ((char)ch) + &quot;'&quot;);</span><a href="#l2137"></a>
<span id="l2138">            case 0:</span><a href="#l2138"></a>
<span id="l2139">                if (cursor &gt;= patternLength) {</span><a href="#l2139"></a>
<span id="l2140">                    break LOOP;</span><a href="#l2140"></a>
<span id="l2141">                }</span><a href="#l2141"></a>
<span id="l2142">                // Fall through</span><a href="#l2142"></a>
<span id="l2143">            default:</span><a href="#l2143"></a>
<span id="l2144">                node = atom();</span><a href="#l2144"></a>
<span id="l2145">                break;</span><a href="#l2145"></a>
<span id="l2146">            }</span><a href="#l2146"></a>
<span id="l2147"></span><a href="#l2147"></a>
<span id="l2148">            node = closure(node);</span><a href="#l2148"></a>
<span id="l2149"></span><a href="#l2149"></a>
<span id="l2150">            if (head == null) {</span><a href="#l2150"></a>
<span id="l2151">                head = tail = node;</span><a href="#l2151"></a>
<span id="l2152">            } else {</span><a href="#l2152"></a>
<span id="l2153">                tail.next = node;</span><a href="#l2153"></a>
<span id="l2154">                tail = node;</span><a href="#l2154"></a>
<span id="l2155">            }</span><a href="#l2155"></a>
<span id="l2156">        }</span><a href="#l2156"></a>
<span id="l2157">        if (head == null) {</span><a href="#l2157"></a>
<span id="l2158">            return end;</span><a href="#l2158"></a>
<span id="l2159">        }</span><a href="#l2159"></a>
<span id="l2160">        tail.next = end;</span><a href="#l2160"></a>
<span id="l2161">        root = tail;      //double return</span><a href="#l2161"></a>
<span id="l2162">        return head;</span><a href="#l2162"></a>
<span id="l2163">    }</span><a href="#l2163"></a>
<span id="l2164"></span><a href="#l2164"></a>
<span id="l2165">    @SuppressWarnings(&quot;fallthrough&quot;)</span><a href="#l2165"></a>
<span id="l2166">    /**</span><a href="#l2166"></a>
<span id="l2167">     * Parse and add a new Single or Slice.</span><a href="#l2167"></a>
<span id="l2168">     */</span><a href="#l2168"></a>
<span id="l2169">    private Node atom() {</span><a href="#l2169"></a>
<span id="l2170">        int first = 0;</span><a href="#l2170"></a>
<span id="l2171">        int prev = -1;</span><a href="#l2171"></a>
<span id="l2172">        boolean hasSupplementary = false;</span><a href="#l2172"></a>
<span id="l2173">        int ch = peek();</span><a href="#l2173"></a>
<span id="l2174">        for (;;) {</span><a href="#l2174"></a>
<span id="l2175">            switch (ch) {</span><a href="#l2175"></a>
<span id="l2176">            case '*':</span><a href="#l2176"></a>
<span id="l2177">            case '+':</span><a href="#l2177"></a>
<span id="l2178">            case '?':</span><a href="#l2178"></a>
<span id="l2179">            case '{':</span><a href="#l2179"></a>
<span id="l2180">                if (first &gt; 1) {</span><a href="#l2180"></a>
<span id="l2181">                    cursor = prev;    // Unwind one character</span><a href="#l2181"></a>
<span id="l2182">                    first--;</span><a href="#l2182"></a>
<span id="l2183">                }</span><a href="#l2183"></a>
<span id="l2184">                break;</span><a href="#l2184"></a>
<span id="l2185">            case '$':</span><a href="#l2185"></a>
<span id="l2186">            case '.':</span><a href="#l2186"></a>
<span id="l2187">            case '^':</span><a href="#l2187"></a>
<span id="l2188">            case '(':</span><a href="#l2188"></a>
<span id="l2189">            case '[':</span><a href="#l2189"></a>
<span id="l2190">            case '|':</span><a href="#l2190"></a>
<span id="l2191">            case ')':</span><a href="#l2191"></a>
<span id="l2192">                break;</span><a href="#l2192"></a>
<span id="l2193">            case '\\':</span><a href="#l2193"></a>
<span id="l2194">                ch = nextEscaped();</span><a href="#l2194"></a>
<span id="l2195">                if (ch == 'p' || ch == 'P') { // Property</span><a href="#l2195"></a>
<span id="l2196">                    if (first &gt; 0) { // Slice is waiting; handle it first</span><a href="#l2196"></a>
<span id="l2197">                        unread();</span><a href="#l2197"></a>
<span id="l2198">                        break;</span><a href="#l2198"></a>
<span id="l2199">                    } else { // No slice; just return the family node</span><a href="#l2199"></a>
<span id="l2200">                        boolean comp = (ch == 'P');</span><a href="#l2200"></a>
<span id="l2201">                        boolean oneLetter = true;</span><a href="#l2201"></a>
<span id="l2202">                        ch = next(); // Consume { if present</span><a href="#l2202"></a>
<span id="l2203">                        if (ch != '{')</span><a href="#l2203"></a>
<span id="l2204">                            unread();</span><a href="#l2204"></a>
<span id="l2205">                        else</span><a href="#l2205"></a>
<span id="l2206">                            oneLetter = false;</span><a href="#l2206"></a>
<span id="l2207">                        return family(oneLetter, comp);</span><a href="#l2207"></a>
<span id="l2208">                    }</span><a href="#l2208"></a>
<span id="l2209">                }</span><a href="#l2209"></a>
<span id="l2210">                unread();</span><a href="#l2210"></a>
<span id="l2211">                prev = cursor;</span><a href="#l2211"></a>
<span id="l2212">                ch = escape(false, first == 0, false);</span><a href="#l2212"></a>
<span id="l2213">                if (ch &gt;= 0) {</span><a href="#l2213"></a>
<span id="l2214">                    append(ch, first);</span><a href="#l2214"></a>
<span id="l2215">                    first++;</span><a href="#l2215"></a>
<span id="l2216">                    if (isSupplementary(ch)) {</span><a href="#l2216"></a>
<span id="l2217">                        hasSupplementary = true;</span><a href="#l2217"></a>
<span id="l2218">                    }</span><a href="#l2218"></a>
<span id="l2219">                    ch = peek();</span><a href="#l2219"></a>
<span id="l2220">                    continue;</span><a href="#l2220"></a>
<span id="l2221">                } else if (first == 0) {</span><a href="#l2221"></a>
<span id="l2222">                    return root;</span><a href="#l2222"></a>
<span id="l2223">                }</span><a href="#l2223"></a>
<span id="l2224">                // Unwind meta escape sequence</span><a href="#l2224"></a>
<span id="l2225">                cursor = prev;</span><a href="#l2225"></a>
<span id="l2226">                break;</span><a href="#l2226"></a>
<span id="l2227">            case 0:</span><a href="#l2227"></a>
<span id="l2228">                if (cursor &gt;= patternLength) {</span><a href="#l2228"></a>
<span id="l2229">                    break;</span><a href="#l2229"></a>
<span id="l2230">                }</span><a href="#l2230"></a>
<span id="l2231">                // Fall through</span><a href="#l2231"></a>
<span id="l2232">            default:</span><a href="#l2232"></a>
<span id="l2233">                prev = cursor;</span><a href="#l2233"></a>
<span id="l2234">                append(ch, first);</span><a href="#l2234"></a>
<span id="l2235">                first++;</span><a href="#l2235"></a>
<span id="l2236">                if (isSupplementary(ch)) {</span><a href="#l2236"></a>
<span id="l2237">                    hasSupplementary = true;</span><a href="#l2237"></a>
<span id="l2238">                }</span><a href="#l2238"></a>
<span id="l2239">                ch = next();</span><a href="#l2239"></a>
<span id="l2240">                continue;</span><a href="#l2240"></a>
<span id="l2241">            }</span><a href="#l2241"></a>
<span id="l2242">            break;</span><a href="#l2242"></a>
<span id="l2243">        }</span><a href="#l2243"></a>
<span id="l2244">        if (first == 1) {</span><a href="#l2244"></a>
<span id="l2245">            return newSingle(buffer[0]);</span><a href="#l2245"></a>
<span id="l2246">        } else {</span><a href="#l2246"></a>
<span id="l2247">            return newSlice(buffer, first, hasSupplementary);</span><a href="#l2247"></a>
<span id="l2248">        }</span><a href="#l2248"></a>
<span id="l2249">    }</span><a href="#l2249"></a>
<span id="l2250"></span><a href="#l2250"></a>
<span id="l2251">    private void append(int ch, int len) {</span><a href="#l2251"></a>
<span id="l2252">        if (len &gt;= buffer.length) {</span><a href="#l2252"></a>
<span id="l2253">            int[] tmp = new int[len+len];</span><a href="#l2253"></a>
<span id="l2254">            System.arraycopy(buffer, 0, tmp, 0, len);</span><a href="#l2254"></a>
<span id="l2255">            buffer = tmp;</span><a href="#l2255"></a>
<span id="l2256">        }</span><a href="#l2256"></a>
<span id="l2257">        buffer[len] = ch;</span><a href="#l2257"></a>
<span id="l2258">    }</span><a href="#l2258"></a>
<span id="l2259"></span><a href="#l2259"></a>
<span id="l2260">    /**</span><a href="#l2260"></a>
<span id="l2261">     * Parses a backref greedily, taking as many numbers as it</span><a href="#l2261"></a>
<span id="l2262">     * can. The first digit is always treated as a backref, but</span><a href="#l2262"></a>
<span id="l2263">     * multi digit numbers are only treated as a backref if at</span><a href="#l2263"></a>
<span id="l2264">     * least that many backrefs exist at this point in the regex.</span><a href="#l2264"></a>
<span id="l2265">     */</span><a href="#l2265"></a>
<span id="l2266">    private Node ref(int refNum) {</span><a href="#l2266"></a>
<span id="l2267">        boolean done = false;</span><a href="#l2267"></a>
<span id="l2268">        while(!done) {</span><a href="#l2268"></a>
<span id="l2269">            int ch = peek();</span><a href="#l2269"></a>
<span id="l2270">            switch(ch) {</span><a href="#l2270"></a>
<span id="l2271">            case '0':</span><a href="#l2271"></a>
<span id="l2272">            case '1':</span><a href="#l2272"></a>
<span id="l2273">            case '2':</span><a href="#l2273"></a>
<span id="l2274">            case '3':</span><a href="#l2274"></a>
<span id="l2275">            case '4':</span><a href="#l2275"></a>
<span id="l2276">            case '5':</span><a href="#l2276"></a>
<span id="l2277">            case '6':</span><a href="#l2277"></a>
<span id="l2278">            case '7':</span><a href="#l2278"></a>
<span id="l2279">            case '8':</span><a href="#l2279"></a>
<span id="l2280">            case '9':</span><a href="#l2280"></a>
<span id="l2281">                int newRefNum = (refNum * 10) + (ch - '0');</span><a href="#l2281"></a>
<span id="l2282">                // Add another number if it doesn't make a group</span><a href="#l2282"></a>
<span id="l2283">                // that doesn't exist</span><a href="#l2283"></a>
<span id="l2284">                if (capturingGroupCount - 1 &lt; newRefNum) {</span><a href="#l2284"></a>
<span id="l2285">                    done = true;</span><a href="#l2285"></a>
<span id="l2286">                    break;</span><a href="#l2286"></a>
<span id="l2287">                }</span><a href="#l2287"></a>
<span id="l2288">                refNum = newRefNum;</span><a href="#l2288"></a>
<span id="l2289">                read();</span><a href="#l2289"></a>
<span id="l2290">                break;</span><a href="#l2290"></a>
<span id="l2291">            default:</span><a href="#l2291"></a>
<span id="l2292">                done = true;</span><a href="#l2292"></a>
<span id="l2293">                break;</span><a href="#l2293"></a>
<span id="l2294">            }</span><a href="#l2294"></a>
<span id="l2295">        }</span><a href="#l2295"></a>
<span id="l2296">        if (has(CASE_INSENSITIVE))</span><a href="#l2296"></a>
<span id="l2297">            return new CIBackRef(refNum, has(UNICODE_CASE));</span><a href="#l2297"></a>
<span id="l2298">        else</span><a href="#l2298"></a>
<span id="l2299">            return new BackRef(refNum);</span><a href="#l2299"></a>
<span id="l2300">    }</span><a href="#l2300"></a>
<span id="l2301"></span><a href="#l2301"></a>
<span id="l2302">    /**</span><a href="#l2302"></a>
<span id="l2303">     * Parses an escape sequence to determine the actual value that needs</span><a href="#l2303"></a>
<span id="l2304">     * to be matched.</span><a href="#l2304"></a>
<span id="l2305">     * If -1 is returned and create was true a new object was added to the tree</span><a href="#l2305"></a>
<span id="l2306">     * to handle the escape sequence.</span><a href="#l2306"></a>
<span id="l2307">     * If the returned value is greater than zero, it is the value that</span><a href="#l2307"></a>
<span id="l2308">     * matches the escape sequence.</span><a href="#l2308"></a>
<span id="l2309">     */</span><a href="#l2309"></a>
<span id="l2310">    private int escape(boolean inclass, boolean create, boolean isrange) {</span><a href="#l2310"></a>
<span id="l2311">        int ch = skip();</span><a href="#l2311"></a>
<span id="l2312">        switch (ch) {</span><a href="#l2312"></a>
<span id="l2313">        case '0':</span><a href="#l2313"></a>
<span id="l2314">            return o();</span><a href="#l2314"></a>
<span id="l2315">        case '1':</span><a href="#l2315"></a>
<span id="l2316">        case '2':</span><a href="#l2316"></a>
<span id="l2317">        case '3':</span><a href="#l2317"></a>
<span id="l2318">        case '4':</span><a href="#l2318"></a>
<span id="l2319">        case '5':</span><a href="#l2319"></a>
<span id="l2320">        case '6':</span><a href="#l2320"></a>
<span id="l2321">        case '7':</span><a href="#l2321"></a>
<span id="l2322">        case '8':</span><a href="#l2322"></a>
<span id="l2323">        case '9':</span><a href="#l2323"></a>
<span id="l2324">            if (inclass) break;</span><a href="#l2324"></a>
<span id="l2325">            if (create) {</span><a href="#l2325"></a>
<span id="l2326">                root = ref((ch - '0'));</span><a href="#l2326"></a>
<span id="l2327">            }</span><a href="#l2327"></a>
<span id="l2328">            return -1;</span><a href="#l2328"></a>
<span id="l2329">        case 'A':</span><a href="#l2329"></a>
<span id="l2330">            if (inclass) break;</span><a href="#l2330"></a>
<span id="l2331">            if (create) root = new Begin();</span><a href="#l2331"></a>
<span id="l2332">            return -1;</span><a href="#l2332"></a>
<span id="l2333">        case 'B':</span><a href="#l2333"></a>
<span id="l2334">            if (inclass) break;</span><a href="#l2334"></a>
<span id="l2335">            if (create) root = new Bound(Bound.NONE, has(UNICODE_CHARACTER_CLASS));</span><a href="#l2335"></a>
<span id="l2336">            return -1;</span><a href="#l2336"></a>
<span id="l2337">        case 'C':</span><a href="#l2337"></a>
<span id="l2338">            break;</span><a href="#l2338"></a>
<span id="l2339">        case 'D':</span><a href="#l2339"></a>
<span id="l2340">            if (create) root = has(UNICODE_CHARACTER_CLASS)</span><a href="#l2340"></a>
<span id="l2341">                               ? new Utype(UnicodeProp.DIGIT).complement()</span><a href="#l2341"></a>
<span id="l2342">                               : new Ctype(ASCII.DIGIT).complement();</span><a href="#l2342"></a>
<span id="l2343">            return -1;</span><a href="#l2343"></a>
<span id="l2344">        case 'E':</span><a href="#l2344"></a>
<span id="l2345">        case 'F':</span><a href="#l2345"></a>
<span id="l2346">            break;</span><a href="#l2346"></a>
<span id="l2347">        case 'G':</span><a href="#l2347"></a>
<span id="l2348">            if (inclass) break;</span><a href="#l2348"></a>
<span id="l2349">            if (create) root = new LastMatch();</span><a href="#l2349"></a>
<span id="l2350">            return -1;</span><a href="#l2350"></a>
<span id="l2351">        case 'H':</span><a href="#l2351"></a>
<span id="l2352">            if (create) root = new HorizWS().complement();</span><a href="#l2352"></a>
<span id="l2353">            return -1;</span><a href="#l2353"></a>
<span id="l2354">        case 'I':</span><a href="#l2354"></a>
<span id="l2355">        case 'J':</span><a href="#l2355"></a>
<span id="l2356">        case 'K':</span><a href="#l2356"></a>
<span id="l2357">        case 'L':</span><a href="#l2357"></a>
<span id="l2358">        case 'M':</span><a href="#l2358"></a>
<span id="l2359">        case 'N':</span><a href="#l2359"></a>
<span id="l2360">        case 'O':</span><a href="#l2360"></a>
<span id="l2361">        case 'P':</span><a href="#l2361"></a>
<span id="l2362">        case 'Q':</span><a href="#l2362"></a>
<span id="l2363">            break;</span><a href="#l2363"></a>
<span id="l2364">        case 'R':</span><a href="#l2364"></a>
<span id="l2365">            if (inclass) break;</span><a href="#l2365"></a>
<span id="l2366">            if (create) root = new LineEnding();</span><a href="#l2366"></a>
<span id="l2367">            return -1;</span><a href="#l2367"></a>
<span id="l2368">        case 'S':</span><a href="#l2368"></a>
<span id="l2369">            if (create) root = has(UNICODE_CHARACTER_CLASS)</span><a href="#l2369"></a>
<span id="l2370">                               ? new Utype(UnicodeProp.WHITE_SPACE).complement()</span><a href="#l2370"></a>
<span id="l2371">                               : new Ctype(ASCII.SPACE).complement();</span><a href="#l2371"></a>
<span id="l2372">            return -1;</span><a href="#l2372"></a>
<span id="l2373">        case 'T':</span><a href="#l2373"></a>
<span id="l2374">        case 'U':</span><a href="#l2374"></a>
<span id="l2375">            break;</span><a href="#l2375"></a>
<span id="l2376">        case 'V':</span><a href="#l2376"></a>
<span id="l2377">            if (create) root = new VertWS().complement();</span><a href="#l2377"></a>
<span id="l2378">            return -1;</span><a href="#l2378"></a>
<span id="l2379">        case 'W':</span><a href="#l2379"></a>
<span id="l2380">            if (create) root = has(UNICODE_CHARACTER_CLASS)</span><a href="#l2380"></a>
<span id="l2381">                               ? new Utype(UnicodeProp.WORD).complement()</span><a href="#l2381"></a>
<span id="l2382">                               : new Ctype(ASCII.WORD).complement();</span><a href="#l2382"></a>
<span id="l2383">            return -1;</span><a href="#l2383"></a>
<span id="l2384">        case 'X':</span><a href="#l2384"></a>
<span id="l2385">        case 'Y':</span><a href="#l2385"></a>
<span id="l2386">            break;</span><a href="#l2386"></a>
<span id="l2387">        case 'Z':</span><a href="#l2387"></a>
<span id="l2388">            if (inclass) break;</span><a href="#l2388"></a>
<span id="l2389">            if (create) {</span><a href="#l2389"></a>
<span id="l2390">                if (has(UNIX_LINES))</span><a href="#l2390"></a>
<span id="l2391">                    root = new UnixDollar(false);</span><a href="#l2391"></a>
<span id="l2392">                else</span><a href="#l2392"></a>
<span id="l2393">                    root = new Dollar(false);</span><a href="#l2393"></a>
<span id="l2394">            }</span><a href="#l2394"></a>
<span id="l2395">            return -1;</span><a href="#l2395"></a>
<span id="l2396">        case 'a':</span><a href="#l2396"></a>
<span id="l2397">            return '\007';</span><a href="#l2397"></a>
<span id="l2398">        case 'b':</span><a href="#l2398"></a>
<span id="l2399">            if (inclass) break;</span><a href="#l2399"></a>
<span id="l2400">            if (create) root = new Bound(Bound.BOTH, has(UNICODE_CHARACTER_CLASS));</span><a href="#l2400"></a>
<span id="l2401">            return -1;</span><a href="#l2401"></a>
<span id="l2402">        case 'c':</span><a href="#l2402"></a>
<span id="l2403">            return c();</span><a href="#l2403"></a>
<span id="l2404">        case 'd':</span><a href="#l2404"></a>
<span id="l2405">            if (create) root = has(UNICODE_CHARACTER_CLASS)</span><a href="#l2405"></a>
<span id="l2406">                               ? new Utype(UnicodeProp.DIGIT)</span><a href="#l2406"></a>
<span id="l2407">                               : new Ctype(ASCII.DIGIT);</span><a href="#l2407"></a>
<span id="l2408">            return -1;</span><a href="#l2408"></a>
<span id="l2409">        case 'e':</span><a href="#l2409"></a>
<span id="l2410">            return '\033';</span><a href="#l2410"></a>
<span id="l2411">        case 'f':</span><a href="#l2411"></a>
<span id="l2412">            return '\f';</span><a href="#l2412"></a>
<span id="l2413">        case 'g':</span><a href="#l2413"></a>
<span id="l2414">            break;</span><a href="#l2414"></a>
<span id="l2415">        case 'h':</span><a href="#l2415"></a>
<span id="l2416">            if (create) root = new HorizWS();</span><a href="#l2416"></a>
<span id="l2417">            return -1;</span><a href="#l2417"></a>
<span id="l2418">        case 'i':</span><a href="#l2418"></a>
<span id="l2419">        case 'j':</span><a href="#l2419"></a>
<span id="l2420">            break;</span><a href="#l2420"></a>
<span id="l2421">        case 'k':</span><a href="#l2421"></a>
<span id="l2422">            if (inclass)</span><a href="#l2422"></a>
<span id="l2423">                break;</span><a href="#l2423"></a>
<span id="l2424">            if (read() != '&lt;')</span><a href="#l2424"></a>
<span id="l2425">                throw error(&quot;\\k is not followed by '&lt;' for named capturing group&quot;);</span><a href="#l2425"></a>
<span id="l2426">            String name = groupname(read());</span><a href="#l2426"></a>
<span id="l2427">            if (!namedGroups().containsKey(name))</span><a href="#l2427"></a>
<span id="l2428">                throw error(&quot;(named capturing group &lt;&quot;+ name+&quot;&gt; does not exit&quot;);</span><a href="#l2428"></a>
<span id="l2429">            if (create) {</span><a href="#l2429"></a>
<span id="l2430">                if (has(CASE_INSENSITIVE))</span><a href="#l2430"></a>
<span id="l2431">                    root = new CIBackRef(namedGroups().get(name), has(UNICODE_CASE));</span><a href="#l2431"></a>
<span id="l2432">                else</span><a href="#l2432"></a>
<span id="l2433">                    root = new BackRef(namedGroups().get(name));</span><a href="#l2433"></a>
<span id="l2434">            }</span><a href="#l2434"></a>
<span id="l2435">            return -1;</span><a href="#l2435"></a>
<span id="l2436">        case 'l':</span><a href="#l2436"></a>
<span id="l2437">        case 'm':</span><a href="#l2437"></a>
<span id="l2438">            break;</span><a href="#l2438"></a>
<span id="l2439">        case 'n':</span><a href="#l2439"></a>
<span id="l2440">            return '\n';</span><a href="#l2440"></a>
<span id="l2441">        case 'o':</span><a href="#l2441"></a>
<span id="l2442">        case 'p':</span><a href="#l2442"></a>
<span id="l2443">        case 'q':</span><a href="#l2443"></a>
<span id="l2444">            break;</span><a href="#l2444"></a>
<span id="l2445">        case 'r':</span><a href="#l2445"></a>
<span id="l2446">            return '\r';</span><a href="#l2446"></a>
<span id="l2447">        case 's':</span><a href="#l2447"></a>
<span id="l2448">            if (create) root = has(UNICODE_CHARACTER_CLASS)</span><a href="#l2448"></a>
<span id="l2449">                               ? new Utype(UnicodeProp.WHITE_SPACE)</span><a href="#l2449"></a>
<span id="l2450">                               : new Ctype(ASCII.SPACE);</span><a href="#l2450"></a>
<span id="l2451">            return -1;</span><a href="#l2451"></a>
<span id="l2452">        case 't':</span><a href="#l2452"></a>
<span id="l2453">            return '\t';</span><a href="#l2453"></a>
<span id="l2454">        case 'u':</span><a href="#l2454"></a>
<span id="l2455">            return u();</span><a href="#l2455"></a>
<span id="l2456">        case 'v':</span><a href="#l2456"></a>
<span id="l2457">            // '\v' was implemented as VT/0x0B in releases &lt; 1.8 (though</span><a href="#l2457"></a>
<span id="l2458">            // undocumented). In JDK8 '\v' is specified as a predefined</span><a href="#l2458"></a>
<span id="l2459">            // character class for all vertical whitespace characters.</span><a href="#l2459"></a>
<span id="l2460">            // So [-1, root=VertWS node] pair is returned (instead of a</span><a href="#l2460"></a>
<span id="l2461">            // single 0x0B). This breaks the range if '\v' is used as</span><a href="#l2461"></a>
<span id="l2462">            // the start or end value, such as [\v-...] or [...-\v], in</span><a href="#l2462"></a>
<span id="l2463">            // which a single definite value (0x0B) is expected. For</span><a href="#l2463"></a>
<span id="l2464">            // compatibility concern '\013'/0x0B is returned if isrange.</span><a href="#l2464"></a>
<span id="l2465">            if (isrange)</span><a href="#l2465"></a>
<span id="l2466">                return '\013';</span><a href="#l2466"></a>
<span id="l2467">            if (create) root = new VertWS();</span><a href="#l2467"></a>
<span id="l2468">            return -1;</span><a href="#l2468"></a>
<span id="l2469">        case 'w':</span><a href="#l2469"></a>
<span id="l2470">            if (create) root = has(UNICODE_CHARACTER_CLASS)</span><a href="#l2470"></a>
<span id="l2471">                               ? new Utype(UnicodeProp.WORD)</span><a href="#l2471"></a>
<span id="l2472">                               : new Ctype(ASCII.WORD);</span><a href="#l2472"></a>
<span id="l2473">            return -1;</span><a href="#l2473"></a>
<span id="l2474">        case 'x':</span><a href="#l2474"></a>
<span id="l2475">            return x();</span><a href="#l2475"></a>
<span id="l2476">        case 'y':</span><a href="#l2476"></a>
<span id="l2477">            break;</span><a href="#l2477"></a>
<span id="l2478">        case 'z':</span><a href="#l2478"></a>
<span id="l2479">            if (inclass) break;</span><a href="#l2479"></a>
<span id="l2480">            if (create) root = new End();</span><a href="#l2480"></a>
<span id="l2481">            return -1;</span><a href="#l2481"></a>
<span id="l2482">        default:</span><a href="#l2482"></a>
<span id="l2483">            return ch;</span><a href="#l2483"></a>
<span id="l2484">        }</span><a href="#l2484"></a>
<span id="l2485">        throw error(&quot;Illegal/unsupported escape sequence&quot;);</span><a href="#l2485"></a>
<span id="l2486">    }</span><a href="#l2486"></a>
<span id="l2487"></span><a href="#l2487"></a>
<span id="l2488">    /**</span><a href="#l2488"></a>
<span id="l2489">     * Parse a character class, and return the node that matches it.</span><a href="#l2489"></a>
<span id="l2490">     *</span><a href="#l2490"></a>
<span id="l2491">     * Consumes a ] on the way out if consume is true. Usually consume</span><a href="#l2491"></a>
<span id="l2492">     * is true except for the case of [abc&amp;&amp;def] where def is a separate</span><a href="#l2492"></a>
<span id="l2493">     * right hand node with &quot;understood&quot; brackets.</span><a href="#l2493"></a>
<span id="l2494">     */</span><a href="#l2494"></a>
<span id="l2495">    private CharProperty clazz(boolean consume) {</span><a href="#l2495"></a>
<span id="l2496">        CharProperty prev = null;</span><a href="#l2496"></a>
<span id="l2497">        CharProperty node = null;</span><a href="#l2497"></a>
<span id="l2498">        BitClass bits = new BitClass();</span><a href="#l2498"></a>
<span id="l2499">        boolean include = true;</span><a href="#l2499"></a>
<span id="l2500">        boolean firstInClass = true;</span><a href="#l2500"></a>
<span id="l2501">        int ch = next();</span><a href="#l2501"></a>
<span id="l2502">        for (;;) {</span><a href="#l2502"></a>
<span id="l2503">            switch (ch) {</span><a href="#l2503"></a>
<span id="l2504">                case '^':</span><a href="#l2504"></a>
<span id="l2505">                    // Negates if first char in a class, otherwise literal</span><a href="#l2505"></a>
<span id="l2506">                    if (firstInClass) {</span><a href="#l2506"></a>
<span id="l2507">                        if (temp[cursor-1] != '[')</span><a href="#l2507"></a>
<span id="l2508">                            break;</span><a href="#l2508"></a>
<span id="l2509">                        ch = next();</span><a href="#l2509"></a>
<span id="l2510">                        include = !include;</span><a href="#l2510"></a>
<span id="l2511">                        continue;</span><a href="#l2511"></a>
<span id="l2512">                    } else {</span><a href="#l2512"></a>
<span id="l2513">                        // ^ not first in class, treat as literal</span><a href="#l2513"></a>
<span id="l2514">                        break;</span><a href="#l2514"></a>
<span id="l2515">                    }</span><a href="#l2515"></a>
<span id="l2516">                case '[':</span><a href="#l2516"></a>
<span id="l2517">                    firstInClass = false;</span><a href="#l2517"></a>
<span id="l2518">                    node = clazz(true);</span><a href="#l2518"></a>
<span id="l2519">                    if (prev == null)</span><a href="#l2519"></a>
<span id="l2520">                        prev = node;</span><a href="#l2520"></a>
<span id="l2521">                    else</span><a href="#l2521"></a>
<span id="l2522">                        prev = union(prev, node);</span><a href="#l2522"></a>
<span id="l2523">                    ch = peek();</span><a href="#l2523"></a>
<span id="l2524">                    continue;</span><a href="#l2524"></a>
<span id="l2525">                case '&amp;':</span><a href="#l2525"></a>
<span id="l2526">                    firstInClass = false;</span><a href="#l2526"></a>
<span id="l2527">                    ch = next();</span><a href="#l2527"></a>
<span id="l2528">                    if (ch == '&amp;') {</span><a href="#l2528"></a>
<span id="l2529">                        ch = next();</span><a href="#l2529"></a>
<span id="l2530">                        CharProperty rightNode = null;</span><a href="#l2530"></a>
<span id="l2531">                        while (ch != ']' &amp;&amp; ch != '&amp;') {</span><a href="#l2531"></a>
<span id="l2532">                            if (ch == '[') {</span><a href="#l2532"></a>
<span id="l2533">                                if (rightNode == null)</span><a href="#l2533"></a>
<span id="l2534">                                    rightNode = clazz(true);</span><a href="#l2534"></a>
<span id="l2535">                                else</span><a href="#l2535"></a>
<span id="l2536">                                    rightNode = union(rightNode, clazz(true));</span><a href="#l2536"></a>
<span id="l2537">                            } else { // abc&amp;&amp;def</span><a href="#l2537"></a>
<span id="l2538">                                unread();</span><a href="#l2538"></a>
<span id="l2539">                                rightNode = clazz(false);</span><a href="#l2539"></a>
<span id="l2540">                            }</span><a href="#l2540"></a>
<span id="l2541">                            ch = peek();</span><a href="#l2541"></a>
<span id="l2542">                        }</span><a href="#l2542"></a>
<span id="l2543">                        if (rightNode != null)</span><a href="#l2543"></a>
<span id="l2544">                            node = rightNode;</span><a href="#l2544"></a>
<span id="l2545">                        if (prev == null) {</span><a href="#l2545"></a>
<span id="l2546">                            if (rightNode == null)</span><a href="#l2546"></a>
<span id="l2547">                                throw error(&quot;Bad class syntax&quot;);</span><a href="#l2547"></a>
<span id="l2548">                            else</span><a href="#l2548"></a>
<span id="l2549">                                prev = rightNode;</span><a href="#l2549"></a>
<span id="l2550">                        } else {</span><a href="#l2550"></a>
<span id="l2551">                            prev = intersection(prev, node);</span><a href="#l2551"></a>
<span id="l2552">                        }</span><a href="#l2552"></a>
<span id="l2553">                    } else {</span><a href="#l2553"></a>
<span id="l2554">                        // treat as a literal &amp;</span><a href="#l2554"></a>
<span id="l2555">                        unread();</span><a href="#l2555"></a>
<span id="l2556">                        break;</span><a href="#l2556"></a>
<span id="l2557">                    }</span><a href="#l2557"></a>
<span id="l2558">                    continue;</span><a href="#l2558"></a>
<span id="l2559">                case 0:</span><a href="#l2559"></a>
<span id="l2560">                    firstInClass = false;</span><a href="#l2560"></a>
<span id="l2561">                    if (cursor &gt;= patternLength)</span><a href="#l2561"></a>
<span id="l2562">                        throw error(&quot;Unclosed character class&quot;);</span><a href="#l2562"></a>
<span id="l2563">                    break;</span><a href="#l2563"></a>
<span id="l2564">                case ']':</span><a href="#l2564"></a>
<span id="l2565">                    firstInClass = false;</span><a href="#l2565"></a>
<span id="l2566">                    if (prev != null) {</span><a href="#l2566"></a>
<span id="l2567">                        if (consume)</span><a href="#l2567"></a>
<span id="l2568">                            next();</span><a href="#l2568"></a>
<span id="l2569">                        return prev;</span><a href="#l2569"></a>
<span id="l2570">                    }</span><a href="#l2570"></a>
<span id="l2571">                    break;</span><a href="#l2571"></a>
<span id="l2572">                default:</span><a href="#l2572"></a>
<span id="l2573">                    firstInClass = false;</span><a href="#l2573"></a>
<span id="l2574">                    break;</span><a href="#l2574"></a>
<span id="l2575">            }</span><a href="#l2575"></a>
<span id="l2576">            node = range(bits);</span><a href="#l2576"></a>
<span id="l2577">            if (include) {</span><a href="#l2577"></a>
<span id="l2578">                if (prev == null) {</span><a href="#l2578"></a>
<span id="l2579">                    prev = node;</span><a href="#l2579"></a>
<span id="l2580">                } else {</span><a href="#l2580"></a>
<span id="l2581">                    if (prev != node)</span><a href="#l2581"></a>
<span id="l2582">                        prev = union(prev, node);</span><a href="#l2582"></a>
<span id="l2583">                }</span><a href="#l2583"></a>
<span id="l2584">            } else {</span><a href="#l2584"></a>
<span id="l2585">                if (prev == null) {</span><a href="#l2585"></a>
<span id="l2586">                    prev = node.complement();</span><a href="#l2586"></a>
<span id="l2587">                } else {</span><a href="#l2587"></a>
<span id="l2588">                    if (prev != node)</span><a href="#l2588"></a>
<span id="l2589">                        prev = setDifference(prev, node);</span><a href="#l2589"></a>
<span id="l2590">                }</span><a href="#l2590"></a>
<span id="l2591">            }</span><a href="#l2591"></a>
<span id="l2592">            ch = peek();</span><a href="#l2592"></a>
<span id="l2593">        }</span><a href="#l2593"></a>
<span id="l2594">    }</span><a href="#l2594"></a>
<span id="l2595"></span><a href="#l2595"></a>
<span id="l2596">    private CharProperty bitsOrSingle(BitClass bits, int ch) {</span><a href="#l2596"></a>
<span id="l2597">        /* Bits can only handle codepoints in [u+0000-u+00ff] range.</span><a href="#l2597"></a>
<span id="l2598">           Use &quot;single&quot; node instead of bits when dealing with unicode</span><a href="#l2598"></a>
<span id="l2599">           case folding for codepoints listed below.</span><a href="#l2599"></a>
<span id="l2600">           (1)Uppercase out of range: u+00ff, u+00b5</span><a href="#l2600"></a>
<span id="l2601">              toUpperCase(u+00ff) -&gt; u+0178</span><a href="#l2601"></a>
<span id="l2602">              toUpperCase(u+00b5) -&gt; u+039c</span><a href="#l2602"></a>
<span id="l2603">           (2)LatinSmallLetterLongS u+17f</span><a href="#l2603"></a>
<span id="l2604">              toUpperCase(u+017f) -&gt; u+0053</span><a href="#l2604"></a>
<span id="l2605">           (3)LatinSmallLetterDotlessI u+131</span><a href="#l2605"></a>
<span id="l2606">              toUpperCase(u+0131) -&gt; u+0049</span><a href="#l2606"></a>
<span id="l2607">           (4)LatinCapitalLetterIWithDotAbove u+0130</span><a href="#l2607"></a>
<span id="l2608">              toLowerCase(u+0130) -&gt; u+0069</span><a href="#l2608"></a>
<span id="l2609">           (5)KelvinSign u+212a</span><a href="#l2609"></a>
<span id="l2610">              toLowerCase(u+212a) ==&gt; u+006B</span><a href="#l2610"></a>
<span id="l2611">           (6)AngstromSign u+212b</span><a href="#l2611"></a>
<span id="l2612">              toLowerCase(u+212b) ==&gt; u+00e5</span><a href="#l2612"></a>
<span id="l2613">        */</span><a href="#l2613"></a>
<span id="l2614">        int d;</span><a href="#l2614"></a>
<span id="l2615">        if (ch &lt; 256 &amp;&amp;</span><a href="#l2615"></a>
<span id="l2616">            !(has(CASE_INSENSITIVE) &amp;&amp; has(UNICODE_CASE) &amp;&amp;</span><a href="#l2616"></a>
<span id="l2617">              (ch == 0xff || ch == 0xb5 ||</span><a href="#l2617"></a>
<span id="l2618">               ch == 0x49 || ch == 0x69 ||  //I and i</span><a href="#l2618"></a>
<span id="l2619">               ch == 0x53 || ch == 0x73 ||  //S and s</span><a href="#l2619"></a>
<span id="l2620">               ch == 0x4b || ch == 0x6b ||  //K and k</span><a href="#l2620"></a>
<span id="l2621">               ch == 0xc5 || ch == 0xe5)))  //A+ring</span><a href="#l2621"></a>
<span id="l2622">            return bits.add(ch, flags());</span><a href="#l2622"></a>
<span id="l2623">        return newSingle(ch);</span><a href="#l2623"></a>
<span id="l2624">    }</span><a href="#l2624"></a>
<span id="l2625"></span><a href="#l2625"></a>
<span id="l2626">    /**</span><a href="#l2626"></a>
<span id="l2627">     * Parse a single character or a character range in a character class</span><a href="#l2627"></a>
<span id="l2628">     * and return its representative node.</span><a href="#l2628"></a>
<span id="l2629">     */</span><a href="#l2629"></a>
<span id="l2630">    private CharProperty range(BitClass bits) {</span><a href="#l2630"></a>
<span id="l2631">        int ch = peek();</span><a href="#l2631"></a>
<span id="l2632">        if (ch == '\\') {</span><a href="#l2632"></a>
<span id="l2633">            ch = nextEscaped();</span><a href="#l2633"></a>
<span id="l2634">            if (ch == 'p' || ch == 'P') { // A property</span><a href="#l2634"></a>
<span id="l2635">                boolean comp = (ch == 'P');</span><a href="#l2635"></a>
<span id="l2636">                boolean oneLetter = true;</span><a href="#l2636"></a>
<span id="l2637">                // Consume { if present</span><a href="#l2637"></a>
<span id="l2638">                ch = next();</span><a href="#l2638"></a>
<span id="l2639">                if (ch != '{')</span><a href="#l2639"></a>
<span id="l2640">                    unread();</span><a href="#l2640"></a>
<span id="l2641">                else</span><a href="#l2641"></a>
<span id="l2642">                    oneLetter = false;</span><a href="#l2642"></a>
<span id="l2643">                return family(oneLetter, comp);</span><a href="#l2643"></a>
<span id="l2644">            } else { // ordinary escape</span><a href="#l2644"></a>
<span id="l2645">                boolean isrange = temp[cursor+1] == '-';</span><a href="#l2645"></a>
<span id="l2646">                unread();</span><a href="#l2646"></a>
<span id="l2647">                ch = escape(true, true, isrange);</span><a href="#l2647"></a>
<span id="l2648">                if (ch == -1)</span><a href="#l2648"></a>
<span id="l2649">                    return (CharProperty) root;</span><a href="#l2649"></a>
<span id="l2650">            }</span><a href="#l2650"></a>
<span id="l2651">        } else {</span><a href="#l2651"></a>
<span id="l2652">            next();</span><a href="#l2652"></a>
<span id="l2653">        }</span><a href="#l2653"></a>
<span id="l2654">        if (ch &gt;= 0) {</span><a href="#l2654"></a>
<span id="l2655">            if (peek() == '-') {</span><a href="#l2655"></a>
<span id="l2656">                int endRange = temp[cursor+1];</span><a href="#l2656"></a>
<span id="l2657">                if (endRange == '[') {</span><a href="#l2657"></a>
<span id="l2658">                    return bitsOrSingle(bits, ch);</span><a href="#l2658"></a>
<span id="l2659">                }</span><a href="#l2659"></a>
<span id="l2660">                if (endRange != ']') {</span><a href="#l2660"></a>
<span id="l2661">                    next();</span><a href="#l2661"></a>
<span id="l2662">                    int m = peek();</span><a href="#l2662"></a>
<span id="l2663">                    if (m == '\\') {</span><a href="#l2663"></a>
<span id="l2664">                        m = escape(true, false, true);</span><a href="#l2664"></a>
<span id="l2665">                    } else {</span><a href="#l2665"></a>
<span id="l2666">                        next();</span><a href="#l2666"></a>
<span id="l2667">                    }</span><a href="#l2667"></a>
<span id="l2668">                    if (m &lt; ch) {</span><a href="#l2668"></a>
<span id="l2669">                        throw error(&quot;Illegal character range&quot;);</span><a href="#l2669"></a>
<span id="l2670">                    }</span><a href="#l2670"></a>
<span id="l2671">                    if (has(CASE_INSENSITIVE))</span><a href="#l2671"></a>
<span id="l2672">                        return caseInsensitiveRangeFor(ch, m);</span><a href="#l2672"></a>
<span id="l2673">                    else</span><a href="#l2673"></a>
<span id="l2674">                        return rangeFor(ch, m);</span><a href="#l2674"></a>
<span id="l2675">                }</span><a href="#l2675"></a>
<span id="l2676">            }</span><a href="#l2676"></a>
<span id="l2677">            return bitsOrSingle(bits, ch);</span><a href="#l2677"></a>
<span id="l2678">        }</span><a href="#l2678"></a>
<span id="l2679">        throw error(&quot;Unexpected character '&quot;+((char)ch)+&quot;'&quot;);</span><a href="#l2679"></a>
<span id="l2680">    }</span><a href="#l2680"></a>
<span id="l2681"></span><a href="#l2681"></a>
<span id="l2682">    /**</span><a href="#l2682"></a>
<span id="l2683">     * Parses a Unicode character family and returns its representative node.</span><a href="#l2683"></a>
<span id="l2684">     */</span><a href="#l2684"></a>
<span id="l2685">    private CharProperty family(boolean singleLetter,</span><a href="#l2685"></a>
<span id="l2686">                                boolean maybeComplement)</span><a href="#l2686"></a>
<span id="l2687">    {</span><a href="#l2687"></a>
<span id="l2688">        next();</span><a href="#l2688"></a>
<span id="l2689">        String name;</span><a href="#l2689"></a>
<span id="l2690">        CharProperty node = null;</span><a href="#l2690"></a>
<span id="l2691"></span><a href="#l2691"></a>
<span id="l2692">        if (singleLetter) {</span><a href="#l2692"></a>
<span id="l2693">            int c = temp[cursor];</span><a href="#l2693"></a>
<span id="l2694">            if (!Character.isSupplementaryCodePoint(c)) {</span><a href="#l2694"></a>
<span id="l2695">                name = String.valueOf((char)c);</span><a href="#l2695"></a>
<span id="l2696">            } else {</span><a href="#l2696"></a>
<span id="l2697">                name = new String(temp, cursor, 1);</span><a href="#l2697"></a>
<span id="l2698">            }</span><a href="#l2698"></a>
<span id="l2699">            read();</span><a href="#l2699"></a>
<span id="l2700">        } else {</span><a href="#l2700"></a>
<span id="l2701">            int i = cursor;</span><a href="#l2701"></a>
<span id="l2702">            mark('}');</span><a href="#l2702"></a>
<span id="l2703">            while(read() != '}') {</span><a href="#l2703"></a>
<span id="l2704">            }</span><a href="#l2704"></a>
<span id="l2705">            mark('\000');</span><a href="#l2705"></a>
<span id="l2706">            int j = cursor;</span><a href="#l2706"></a>
<span id="l2707">            if (j &gt; patternLength)</span><a href="#l2707"></a>
<span id="l2708">                throw error(&quot;Unclosed character family&quot;);</span><a href="#l2708"></a>
<span id="l2709">            if (i + 1 &gt;= j)</span><a href="#l2709"></a>
<span id="l2710">                throw error(&quot;Empty character family&quot;);</span><a href="#l2710"></a>
<span id="l2711">            name = new String(temp, i, j-i-1);</span><a href="#l2711"></a>
<span id="l2712">        }</span><a href="#l2712"></a>
<span id="l2713"></span><a href="#l2713"></a>
<span id="l2714">        int i = name.indexOf('=');</span><a href="#l2714"></a>
<span id="l2715">        if (i != -1) {</span><a href="#l2715"></a>
<span id="l2716">            // property construct \p{name=value}</span><a href="#l2716"></a>
<span id="l2717">            String value = name.substring(i + 1);</span><a href="#l2717"></a>
<span id="l2718">            name = name.substring(0, i).toLowerCase(Locale.ENGLISH);</span><a href="#l2718"></a>
<span id="l2719">            if (&quot;sc&quot;.equals(name) || &quot;script&quot;.equals(name)) {</span><a href="#l2719"></a>
<span id="l2720">                node = unicodeScriptPropertyFor(value);</span><a href="#l2720"></a>
<span id="l2721">            } else if (&quot;blk&quot;.equals(name) || &quot;block&quot;.equals(name)) {</span><a href="#l2721"></a>
<span id="l2722">                node = unicodeBlockPropertyFor(value);</span><a href="#l2722"></a>
<span id="l2723">            } else if (&quot;gc&quot;.equals(name) || &quot;general_category&quot;.equals(name)) {</span><a href="#l2723"></a>
<span id="l2724">                node = charPropertyNodeFor(value);</span><a href="#l2724"></a>
<span id="l2725">            } else {</span><a href="#l2725"></a>
<span id="l2726">                throw error(&quot;Unknown Unicode property {name=&lt;&quot; + name + &quot;&gt;, &quot;</span><a href="#l2726"></a>
<span id="l2727">                             + &quot;value=&lt;&quot; + value + &quot;&gt;}&quot;);</span><a href="#l2727"></a>
<span id="l2728">            }</span><a href="#l2728"></a>
<span id="l2729">        } else {</span><a href="#l2729"></a>
<span id="l2730">            if (name.startsWith(&quot;In&quot;)) {</span><a href="#l2730"></a>
<span id="l2731">                // \p{inBlockName}</span><a href="#l2731"></a>
<span id="l2732">                node = unicodeBlockPropertyFor(name.substring(2));</span><a href="#l2732"></a>
<span id="l2733">            } else if (name.startsWith(&quot;Is&quot;)) {</span><a href="#l2733"></a>
<span id="l2734">                // \p{isGeneralCategory} and \p{isScriptName}</span><a href="#l2734"></a>
<span id="l2735">                name = name.substring(2);</span><a href="#l2735"></a>
<span id="l2736">                UnicodeProp uprop = UnicodeProp.forName(name);</span><a href="#l2736"></a>
<span id="l2737">                if (uprop != null)</span><a href="#l2737"></a>
<span id="l2738">                    node = new Utype(uprop);</span><a href="#l2738"></a>
<span id="l2739">                if (node == null)</span><a href="#l2739"></a>
<span id="l2740">                    node = CharPropertyNames.charPropertyFor(name);</span><a href="#l2740"></a>
<span id="l2741">                if (node == null)</span><a href="#l2741"></a>
<span id="l2742">                    node = unicodeScriptPropertyFor(name);</span><a href="#l2742"></a>
<span id="l2743">            } else {</span><a href="#l2743"></a>
<span id="l2744">                if (has(UNICODE_CHARACTER_CLASS)) {</span><a href="#l2744"></a>
<span id="l2745">                    UnicodeProp uprop = UnicodeProp.forPOSIXName(name);</span><a href="#l2745"></a>
<span id="l2746">                    if (uprop != null)</span><a href="#l2746"></a>
<span id="l2747">                        node = new Utype(uprop);</span><a href="#l2747"></a>
<span id="l2748">                }</span><a href="#l2748"></a>
<span id="l2749">                if (node == null)</span><a href="#l2749"></a>
<span id="l2750">                    node = charPropertyNodeFor(name);</span><a href="#l2750"></a>
<span id="l2751">            }</span><a href="#l2751"></a>
<span id="l2752">        }</span><a href="#l2752"></a>
<span id="l2753">        if (maybeComplement) {</span><a href="#l2753"></a>
<span id="l2754">            if (node instanceof Category || node instanceof Block)</span><a href="#l2754"></a>
<span id="l2755">                hasSupplementary = true;</span><a href="#l2755"></a>
<span id="l2756">            node = node.complement();</span><a href="#l2756"></a>
<span id="l2757">        }</span><a href="#l2757"></a>
<span id="l2758">        return node;</span><a href="#l2758"></a>
<span id="l2759">    }</span><a href="#l2759"></a>
<span id="l2760"></span><a href="#l2760"></a>
<span id="l2761"></span><a href="#l2761"></a>
<span id="l2762">    /**</span><a href="#l2762"></a>
<span id="l2763">     * Returns a CharProperty matching all characters belong to</span><a href="#l2763"></a>
<span id="l2764">     * a UnicodeScript.</span><a href="#l2764"></a>
<span id="l2765">     */</span><a href="#l2765"></a>
<span id="l2766">    private CharProperty unicodeScriptPropertyFor(String name) {</span><a href="#l2766"></a>
<span id="l2767">        final Character.UnicodeScript script;</span><a href="#l2767"></a>
<span id="l2768">        try {</span><a href="#l2768"></a>
<span id="l2769">            script = Character.UnicodeScript.forName(name);</span><a href="#l2769"></a>
<span id="l2770">        } catch (IllegalArgumentException iae) {</span><a href="#l2770"></a>
<span id="l2771">            throw error(&quot;Unknown character script name {&quot; + name + &quot;}&quot;);</span><a href="#l2771"></a>
<span id="l2772">        }</span><a href="#l2772"></a>
<span id="l2773">        return new Script(script);</span><a href="#l2773"></a>
<span id="l2774">    }</span><a href="#l2774"></a>
<span id="l2775"></span><a href="#l2775"></a>
<span id="l2776">    /**</span><a href="#l2776"></a>
<span id="l2777">     * Returns a CharProperty matching all characters in a UnicodeBlock.</span><a href="#l2777"></a>
<span id="l2778">     */</span><a href="#l2778"></a>
<span id="l2779">    private CharProperty unicodeBlockPropertyFor(String name) {</span><a href="#l2779"></a>
<span id="l2780">        final Character.UnicodeBlock block;</span><a href="#l2780"></a>
<span id="l2781">        try {</span><a href="#l2781"></a>
<span id="l2782">            block = Character.UnicodeBlock.forName(name);</span><a href="#l2782"></a>
<span id="l2783">        } catch (IllegalArgumentException iae) {</span><a href="#l2783"></a>
<span id="l2784">            throw error(&quot;Unknown character block name {&quot; + name + &quot;}&quot;);</span><a href="#l2784"></a>
<span id="l2785">        }</span><a href="#l2785"></a>
<span id="l2786">        return new Block(block);</span><a href="#l2786"></a>
<span id="l2787">    }</span><a href="#l2787"></a>
<span id="l2788"></span><a href="#l2788"></a>
<span id="l2789">    /**</span><a href="#l2789"></a>
<span id="l2790">     * Returns a CharProperty matching all characters in a named property.</span><a href="#l2790"></a>
<span id="l2791">     */</span><a href="#l2791"></a>
<span id="l2792">    private CharProperty charPropertyNodeFor(String name) {</span><a href="#l2792"></a>
<span id="l2793">        CharProperty p = CharPropertyNames.charPropertyFor(name);</span><a href="#l2793"></a>
<span id="l2794">        if (p == null)</span><a href="#l2794"></a>
<span id="l2795">            throw error(&quot;Unknown character property name {&quot; + name + &quot;}&quot;);</span><a href="#l2795"></a>
<span id="l2796">        return p;</span><a href="#l2796"></a>
<span id="l2797">    }</span><a href="#l2797"></a>
<span id="l2798"></span><a href="#l2798"></a>
<span id="l2799">    /**</span><a href="#l2799"></a>
<span id="l2800">     * Parses and returns the name of a &quot;named capturing group&quot;, the trailing</span><a href="#l2800"></a>
<span id="l2801">     * &quot;&gt;&quot; is consumed after parsing.</span><a href="#l2801"></a>
<span id="l2802">     */</span><a href="#l2802"></a>
<span id="l2803">    private String groupname(int ch) {</span><a href="#l2803"></a>
<span id="l2804">        StringBuilder sb = new StringBuilder();</span><a href="#l2804"></a>
<span id="l2805">        sb.append(Character.toChars(ch));</span><a href="#l2805"></a>
<span id="l2806">        while (ASCII.isLower(ch=read()) || ASCII.isUpper(ch) ||</span><a href="#l2806"></a>
<span id="l2807">               ASCII.isDigit(ch)) {</span><a href="#l2807"></a>
<span id="l2808">            sb.append(Character.toChars(ch));</span><a href="#l2808"></a>
<span id="l2809">        }</span><a href="#l2809"></a>
<span id="l2810">        if (sb.length() == 0)</span><a href="#l2810"></a>
<span id="l2811">            throw error(&quot;named capturing group has 0 length name&quot;);</span><a href="#l2811"></a>
<span id="l2812">        if (ch != '&gt;')</span><a href="#l2812"></a>
<span id="l2813">            throw error(&quot;named capturing group is missing trailing '&gt;'&quot;);</span><a href="#l2813"></a>
<span id="l2814">        return sb.toString();</span><a href="#l2814"></a>
<span id="l2815">    }</span><a href="#l2815"></a>
<span id="l2816"></span><a href="#l2816"></a>
<span id="l2817">    /**</span><a href="#l2817"></a>
<span id="l2818">     * Parses a group and returns the head node of a set of nodes that process</span><a href="#l2818"></a>
<span id="l2819">     * the group. Sometimes a double return system is used where the tail is</span><a href="#l2819"></a>
<span id="l2820">     * returned in root.</span><a href="#l2820"></a>
<span id="l2821">     */</span><a href="#l2821"></a>
<span id="l2822">    private Node group0() {</span><a href="#l2822"></a>
<span id="l2823">        boolean capturingGroup = false;</span><a href="#l2823"></a>
<span id="l2824">        Node head = null;</span><a href="#l2824"></a>
<span id="l2825">        Node tail = null;</span><a href="#l2825"></a>
<span id="l2826">        int save = flags;</span><a href="#l2826"></a>
<span id="l2827">        root = null;</span><a href="#l2827"></a>
<span id="l2828">        int ch = next();</span><a href="#l2828"></a>
<span id="l2829">        if (ch == '?') {</span><a href="#l2829"></a>
<span id="l2830">            ch = skip();</span><a href="#l2830"></a>
<span id="l2831">            switch (ch) {</span><a href="#l2831"></a>
<span id="l2832">            case ':':   //  (?:xxx) pure group</span><a href="#l2832"></a>
<span id="l2833">                head = createGroup(true);</span><a href="#l2833"></a>
<span id="l2834">                tail = root;</span><a href="#l2834"></a>
<span id="l2835">                head.next = expr(tail);</span><a href="#l2835"></a>
<span id="l2836">                break;</span><a href="#l2836"></a>
<span id="l2837">            case '=':   // (?=xxx) and (?!xxx) lookahead</span><a href="#l2837"></a>
<span id="l2838">            case '!':</span><a href="#l2838"></a>
<span id="l2839">                head = createGroup(true);</span><a href="#l2839"></a>
<span id="l2840">                tail = root;</span><a href="#l2840"></a>
<span id="l2841">                head.next = expr(tail);</span><a href="#l2841"></a>
<span id="l2842">                if (ch == '=') {</span><a href="#l2842"></a>
<span id="l2843">                    head = tail = new Pos(head);</span><a href="#l2843"></a>
<span id="l2844">                } else {</span><a href="#l2844"></a>
<span id="l2845">                    head = tail = new Neg(head);</span><a href="#l2845"></a>
<span id="l2846">                }</span><a href="#l2846"></a>
<span id="l2847">                break;</span><a href="#l2847"></a>
<span id="l2848">            case '&gt;':   // (?&gt;xxx)  independent group</span><a href="#l2848"></a>
<span id="l2849">                head = createGroup(true);</span><a href="#l2849"></a>
<span id="l2850">                tail = root;</span><a href="#l2850"></a>
<span id="l2851">                head.next = expr(tail);</span><a href="#l2851"></a>
<span id="l2852">                head = tail = new Ques(head, INDEPENDENT);</span><a href="#l2852"></a>
<span id="l2853">                break;</span><a href="#l2853"></a>
<span id="l2854">            case '&lt;':   // (?&lt;xxx)  look behind</span><a href="#l2854"></a>
<span id="l2855">                ch = read();</span><a href="#l2855"></a>
<span id="l2856">                if (ASCII.isLower(ch) || ASCII.isUpper(ch)) {</span><a href="#l2856"></a>
<span id="l2857">                    // named captured group</span><a href="#l2857"></a>
<span id="l2858">                    String name = groupname(ch);</span><a href="#l2858"></a>
<span id="l2859">                    if (namedGroups().containsKey(name))</span><a href="#l2859"></a>
<span id="l2860">                        throw error(&quot;Named capturing group &lt;&quot; + name</span><a href="#l2860"></a>
<span id="l2861">                                    + &quot;&gt; is already defined&quot;);</span><a href="#l2861"></a>
<span id="l2862">                    capturingGroup = true;</span><a href="#l2862"></a>
<span id="l2863">                    head = createGroup(false);</span><a href="#l2863"></a>
<span id="l2864">                    tail = root;</span><a href="#l2864"></a>
<span id="l2865">                    namedGroups().put(name, capturingGroupCount-1);</span><a href="#l2865"></a>
<span id="l2866">                    head.next = expr(tail);</span><a href="#l2866"></a>
<span id="l2867">                    break;</span><a href="#l2867"></a>
<span id="l2868">                }</span><a href="#l2868"></a>
<span id="l2869">                int start = cursor;</span><a href="#l2869"></a>
<span id="l2870">                head = createGroup(true);</span><a href="#l2870"></a>
<span id="l2871">                tail = root;</span><a href="#l2871"></a>
<span id="l2872">                head.next = expr(tail);</span><a href="#l2872"></a>
<span id="l2873">                tail.next = lookbehindEnd;</span><a href="#l2873"></a>
<span id="l2874">                TreeInfo info = new TreeInfo();</span><a href="#l2874"></a>
<span id="l2875">                head.study(info);</span><a href="#l2875"></a>
<span id="l2876">                if (info.maxValid == false) {</span><a href="#l2876"></a>
<span id="l2877">                    throw error(&quot;Look-behind group does not have &quot;</span><a href="#l2877"></a>
<span id="l2878">                                + &quot;an obvious maximum length&quot;);</span><a href="#l2878"></a>
<span id="l2879">                }</span><a href="#l2879"></a>
<span id="l2880">                boolean hasSupplementary = findSupplementary(start, patternLength);</span><a href="#l2880"></a>
<span id="l2881">                if (ch == '=') {</span><a href="#l2881"></a>
<span id="l2882">                    head = tail = (hasSupplementary ?</span><a href="#l2882"></a>
<span id="l2883">                                   new BehindS(head, info.maxLength,</span><a href="#l2883"></a>
<span id="l2884">                                               info.minLength) :</span><a href="#l2884"></a>
<span id="l2885">                                   new Behind(head, info.maxLength,</span><a href="#l2885"></a>
<span id="l2886">                                              info.minLength));</span><a href="#l2886"></a>
<span id="l2887">                } else if (ch == '!') {</span><a href="#l2887"></a>
<span id="l2888">                    head = tail = (hasSupplementary ?</span><a href="#l2888"></a>
<span id="l2889">                                   new NotBehindS(head, info.maxLength,</span><a href="#l2889"></a>
<span id="l2890">                                                  info.minLength) :</span><a href="#l2890"></a>
<span id="l2891">                                   new NotBehind(head, info.maxLength,</span><a href="#l2891"></a>
<span id="l2892">                                                 info.minLength));</span><a href="#l2892"></a>
<span id="l2893">                } else {</span><a href="#l2893"></a>
<span id="l2894">                    throw error(&quot;Unknown look-behind group&quot;);</span><a href="#l2894"></a>
<span id="l2895">                }</span><a href="#l2895"></a>
<span id="l2896">                break;</span><a href="#l2896"></a>
<span id="l2897">            case '$':</span><a href="#l2897"></a>
<span id="l2898">            case '@':</span><a href="#l2898"></a>
<span id="l2899">                throw error(&quot;Unknown group type&quot;);</span><a href="#l2899"></a>
<span id="l2900">            default:    // (?xxx:) inlined match flags</span><a href="#l2900"></a>
<span id="l2901">                unread();</span><a href="#l2901"></a>
<span id="l2902">                addFlag();</span><a href="#l2902"></a>
<span id="l2903">                ch = read();</span><a href="#l2903"></a>
<span id="l2904">                if (ch == ')') {</span><a href="#l2904"></a>
<span id="l2905">                    return null;    // Inline modifier only</span><a href="#l2905"></a>
<span id="l2906">                }</span><a href="#l2906"></a>
<span id="l2907">                if (ch != ':') {</span><a href="#l2907"></a>
<span id="l2908">                    throw error(&quot;Unknown inline modifier&quot;);</span><a href="#l2908"></a>
<span id="l2909">                }</span><a href="#l2909"></a>
<span id="l2910">                head = createGroup(true);</span><a href="#l2910"></a>
<span id="l2911">                tail = root;</span><a href="#l2911"></a>
<span id="l2912">                head.next = expr(tail);</span><a href="#l2912"></a>
<span id="l2913">                break;</span><a href="#l2913"></a>
<span id="l2914">            }</span><a href="#l2914"></a>
<span id="l2915">        } else { // (xxx) a regular group</span><a href="#l2915"></a>
<span id="l2916">            capturingGroup = true;</span><a href="#l2916"></a>
<span id="l2917">            head = createGroup(false);</span><a href="#l2917"></a>
<span id="l2918">            tail = root;</span><a href="#l2918"></a>
<span id="l2919">            head.next = expr(tail);</span><a href="#l2919"></a>
<span id="l2920">        }</span><a href="#l2920"></a>
<span id="l2921"></span><a href="#l2921"></a>
<span id="l2922">        accept(')', &quot;Unclosed group&quot;);</span><a href="#l2922"></a>
<span id="l2923">        flags = save;</span><a href="#l2923"></a>
<span id="l2924"></span><a href="#l2924"></a>
<span id="l2925">        // Check for quantifiers</span><a href="#l2925"></a>
<span id="l2926">        Node node = closure(head);</span><a href="#l2926"></a>
<span id="l2927">        if (node == head) { // No closure</span><a href="#l2927"></a>
<span id="l2928">            root = tail;</span><a href="#l2928"></a>
<span id="l2929">            return node;    // Dual return</span><a href="#l2929"></a>
<span id="l2930">        }</span><a href="#l2930"></a>
<span id="l2931">        if (head == tail) { // Zero length assertion</span><a href="#l2931"></a>
<span id="l2932">            root = node;</span><a href="#l2932"></a>
<span id="l2933">            return node;    // Dual return</span><a href="#l2933"></a>
<span id="l2934">        }</span><a href="#l2934"></a>
<span id="l2935"></span><a href="#l2935"></a>
<span id="l2936">        if (node instanceof Ques) {</span><a href="#l2936"></a>
<span id="l2937">            Ques ques = (Ques) node;</span><a href="#l2937"></a>
<span id="l2938">            if (ques.type == POSSESSIVE) {</span><a href="#l2938"></a>
<span id="l2939">                root = node;</span><a href="#l2939"></a>
<span id="l2940">                return node;</span><a href="#l2940"></a>
<span id="l2941">            }</span><a href="#l2941"></a>
<span id="l2942">            tail.next = new BranchConn();</span><a href="#l2942"></a>
<span id="l2943">            tail = tail.next;</span><a href="#l2943"></a>
<span id="l2944">            if (ques.type == GREEDY) {</span><a href="#l2944"></a>
<span id="l2945">                head = new Branch(head, null, tail);</span><a href="#l2945"></a>
<span id="l2946">            } else { // Reluctant quantifier</span><a href="#l2946"></a>
<span id="l2947">                head = new Branch(null, head, tail);</span><a href="#l2947"></a>
<span id="l2948">            }</span><a href="#l2948"></a>
<span id="l2949">            root = tail;</span><a href="#l2949"></a>
<span id="l2950">            return head;</span><a href="#l2950"></a>
<span id="l2951">        } else if (node instanceof Curly) {</span><a href="#l2951"></a>
<span id="l2952">            Curly curly = (Curly) node;</span><a href="#l2952"></a>
<span id="l2953">            if (curly.type == POSSESSIVE) {</span><a href="#l2953"></a>
<span id="l2954">                root = node;</span><a href="#l2954"></a>
<span id="l2955">                return node;</span><a href="#l2955"></a>
<span id="l2956">            }</span><a href="#l2956"></a>
<span id="l2957">            // Discover if the group is deterministic</span><a href="#l2957"></a>
<span id="l2958">            TreeInfo info = new TreeInfo();</span><a href="#l2958"></a>
<span id="l2959">            if (head.study(info)) { // Deterministic</span><a href="#l2959"></a>
<span id="l2960">                GroupTail temp = (GroupTail) tail;</span><a href="#l2960"></a>
<span id="l2961">                head = root = new GroupCurly(head.next, curly.cmin,</span><a href="#l2961"></a>
<span id="l2962">                                   curly.cmax, curly.type,</span><a href="#l2962"></a>
<span id="l2963">                                   ((GroupTail)tail).localIndex,</span><a href="#l2963"></a>
<span id="l2964">                                   ((GroupTail)tail).groupIndex,</span><a href="#l2964"></a>
<span id="l2965">                                             capturingGroup);</span><a href="#l2965"></a>
<span id="l2966">                return head;</span><a href="#l2966"></a>
<span id="l2967">            } else { // Non-deterministic</span><a href="#l2967"></a>
<span id="l2968">                int temp = ((GroupHead) head).localIndex;</span><a href="#l2968"></a>
<span id="l2969">                Loop loop;</span><a href="#l2969"></a>
<span id="l2970">                if (curly.type == GREEDY)</span><a href="#l2970"></a>
<span id="l2971">                    loop = new Loop(this.localCount, temp);</span><a href="#l2971"></a>
<span id="l2972">                else  // Reluctant Curly</span><a href="#l2972"></a>
<span id="l2973">                    loop = new LazyLoop(this.localCount, temp);</span><a href="#l2973"></a>
<span id="l2974">                Prolog prolog = new Prolog(loop);</span><a href="#l2974"></a>
<span id="l2975">                this.localCount += 1;</span><a href="#l2975"></a>
<span id="l2976">                loop.cmin = curly.cmin;</span><a href="#l2976"></a>
<span id="l2977">                loop.cmax = curly.cmax;</span><a href="#l2977"></a>
<span id="l2978">                loop.body = head;</span><a href="#l2978"></a>
<span id="l2979">                tail.next = loop;</span><a href="#l2979"></a>
<span id="l2980">                root = loop;</span><a href="#l2980"></a>
<span id="l2981">                return prolog; // Dual return</span><a href="#l2981"></a>
<span id="l2982">            }</span><a href="#l2982"></a>
<span id="l2983">        }</span><a href="#l2983"></a>
<span id="l2984">        throw error(&quot;Internal logic error&quot;);</span><a href="#l2984"></a>
<span id="l2985">    }</span><a href="#l2985"></a>
<span id="l2986"></span><a href="#l2986"></a>
<span id="l2987">    /**</span><a href="#l2987"></a>
<span id="l2988">     * Create group head and tail nodes using double return. If the group is</span><a href="#l2988"></a>
<span id="l2989">     * created with anonymous true then it is a pure group and should not</span><a href="#l2989"></a>
<span id="l2990">     * affect group counting.</span><a href="#l2990"></a>
<span id="l2991">     */</span><a href="#l2991"></a>
<span id="l2992">    private Node createGroup(boolean anonymous) {</span><a href="#l2992"></a>
<span id="l2993">        int localIndex = localCount++;</span><a href="#l2993"></a>
<span id="l2994">        int groupIndex = 0;</span><a href="#l2994"></a>
<span id="l2995">        if (!anonymous)</span><a href="#l2995"></a>
<span id="l2996">            groupIndex = capturingGroupCount++;</span><a href="#l2996"></a>
<span id="l2997">        GroupHead head = new GroupHead(localIndex);</span><a href="#l2997"></a>
<span id="l2998">        root = new GroupTail(localIndex, groupIndex);</span><a href="#l2998"></a>
<span id="l2999">        if (!anonymous &amp;&amp; groupIndex &lt; 10)</span><a href="#l2999"></a>
<span id="l3000">            groupNodes[groupIndex] = head;</span><a href="#l3000"></a>
<span id="l3001">        return head;</span><a href="#l3001"></a>
<span id="l3002">    }</span><a href="#l3002"></a>
<span id="l3003"></span><a href="#l3003"></a>
<span id="l3004">    @SuppressWarnings(&quot;fallthrough&quot;)</span><a href="#l3004"></a>
<span id="l3005">    /**</span><a href="#l3005"></a>
<span id="l3006">     * Parses inlined match flags and set them appropriately.</span><a href="#l3006"></a>
<span id="l3007">     */</span><a href="#l3007"></a>
<span id="l3008">    private void addFlag() {</span><a href="#l3008"></a>
<span id="l3009">        int ch = peek();</span><a href="#l3009"></a>
<span id="l3010">        for (;;) {</span><a href="#l3010"></a>
<span id="l3011">            switch (ch) {</span><a href="#l3011"></a>
<span id="l3012">            case 'i':</span><a href="#l3012"></a>
<span id="l3013">                flags |= CASE_INSENSITIVE;</span><a href="#l3013"></a>
<span id="l3014">                break;</span><a href="#l3014"></a>
<span id="l3015">            case 'm':</span><a href="#l3015"></a>
<span id="l3016">                flags |= MULTILINE;</span><a href="#l3016"></a>
<span id="l3017">                break;</span><a href="#l3017"></a>
<span id="l3018">            case 's':</span><a href="#l3018"></a>
<span id="l3019">                flags |= DOTALL;</span><a href="#l3019"></a>
<span id="l3020">                break;</span><a href="#l3020"></a>
<span id="l3021">            case 'd':</span><a href="#l3021"></a>
<span id="l3022">                flags |= UNIX_LINES;</span><a href="#l3022"></a>
<span id="l3023">                break;</span><a href="#l3023"></a>
<span id="l3024">            case 'u':</span><a href="#l3024"></a>
<span id="l3025">                flags |= UNICODE_CASE;</span><a href="#l3025"></a>
<span id="l3026">                break;</span><a href="#l3026"></a>
<span id="l3027">            case 'c':</span><a href="#l3027"></a>
<span id="l3028">                flags |= CANON_EQ;</span><a href="#l3028"></a>
<span id="l3029">                break;</span><a href="#l3029"></a>
<span id="l3030">            case 'x':</span><a href="#l3030"></a>
<span id="l3031">                flags |= COMMENTS;</span><a href="#l3031"></a>
<span id="l3032">                break;</span><a href="#l3032"></a>
<span id="l3033">            case 'U':</span><a href="#l3033"></a>
<span id="l3034">                flags |= (UNICODE_CHARACTER_CLASS | UNICODE_CASE);</span><a href="#l3034"></a>
<span id="l3035">                break;</span><a href="#l3035"></a>
<span id="l3036">            case '-': // subFlag then fall through</span><a href="#l3036"></a>
<span id="l3037">                ch = next();</span><a href="#l3037"></a>
<span id="l3038">                subFlag();</span><a href="#l3038"></a>
<span id="l3039">            default:</span><a href="#l3039"></a>
<span id="l3040">                return;</span><a href="#l3040"></a>
<span id="l3041">            }</span><a href="#l3041"></a>
<span id="l3042">            ch = next();</span><a href="#l3042"></a>
<span id="l3043">        }</span><a href="#l3043"></a>
<span id="l3044">    }</span><a href="#l3044"></a>
<span id="l3045"></span><a href="#l3045"></a>
<span id="l3046">    @SuppressWarnings(&quot;fallthrough&quot;)</span><a href="#l3046"></a>
<span id="l3047">    /**</span><a href="#l3047"></a>
<span id="l3048">     * Parses the second part of inlined match flags and turns off</span><a href="#l3048"></a>
<span id="l3049">     * flags appropriately.</span><a href="#l3049"></a>
<span id="l3050">     */</span><a href="#l3050"></a>
<span id="l3051">    private void subFlag() {</span><a href="#l3051"></a>
<span id="l3052">        int ch = peek();</span><a href="#l3052"></a>
<span id="l3053">        for (;;) {</span><a href="#l3053"></a>
<span id="l3054">            switch (ch) {</span><a href="#l3054"></a>
<span id="l3055">            case 'i':</span><a href="#l3055"></a>
<span id="l3056">                flags &amp;= ~CASE_INSENSITIVE;</span><a href="#l3056"></a>
<span id="l3057">                break;</span><a href="#l3057"></a>
<span id="l3058">            case 'm':</span><a href="#l3058"></a>
<span id="l3059">                flags &amp;= ~MULTILINE;</span><a href="#l3059"></a>
<span id="l3060">                break;</span><a href="#l3060"></a>
<span id="l3061">            case 's':</span><a href="#l3061"></a>
<span id="l3062">                flags &amp;= ~DOTALL;</span><a href="#l3062"></a>
<span id="l3063">                break;</span><a href="#l3063"></a>
<span id="l3064">            case 'd':</span><a href="#l3064"></a>
<span id="l3065">                flags &amp;= ~UNIX_LINES;</span><a href="#l3065"></a>
<span id="l3066">                break;</span><a href="#l3066"></a>
<span id="l3067">            case 'u':</span><a href="#l3067"></a>
<span id="l3068">                flags &amp;= ~UNICODE_CASE;</span><a href="#l3068"></a>
<span id="l3069">                break;</span><a href="#l3069"></a>
<span id="l3070">            case 'c':</span><a href="#l3070"></a>
<span id="l3071">                flags &amp;= ~CANON_EQ;</span><a href="#l3071"></a>
<span id="l3072">                break;</span><a href="#l3072"></a>
<span id="l3073">            case 'x':</span><a href="#l3073"></a>
<span id="l3074">                flags &amp;= ~COMMENTS;</span><a href="#l3074"></a>
<span id="l3075">                break;</span><a href="#l3075"></a>
<span id="l3076">            case 'U':</span><a href="#l3076"></a>
<span id="l3077">                flags &amp;= ~(UNICODE_CHARACTER_CLASS | UNICODE_CASE);</span><a href="#l3077"></a>
<span id="l3078">            default:</span><a href="#l3078"></a>
<span id="l3079">                return;</span><a href="#l3079"></a>
<span id="l3080">            }</span><a href="#l3080"></a>
<span id="l3081">            ch = next();</span><a href="#l3081"></a>
<span id="l3082">        }</span><a href="#l3082"></a>
<span id="l3083">    }</span><a href="#l3083"></a>
<span id="l3084"></span><a href="#l3084"></a>
<span id="l3085">    static final int MAX_REPS   = 0x7FFFFFFF;</span><a href="#l3085"></a>
<span id="l3086"></span><a href="#l3086"></a>
<span id="l3087">    static final int GREEDY     = 0;</span><a href="#l3087"></a>
<span id="l3088"></span><a href="#l3088"></a>
<span id="l3089">    static final int LAZY       = 1;</span><a href="#l3089"></a>
<span id="l3090"></span><a href="#l3090"></a>
<span id="l3091">    static final int POSSESSIVE = 2;</span><a href="#l3091"></a>
<span id="l3092"></span><a href="#l3092"></a>
<span id="l3093">    static final int INDEPENDENT = 3;</span><a href="#l3093"></a>
<span id="l3094"></span><a href="#l3094"></a>
<span id="l3095">    /**</span><a href="#l3095"></a>
<span id="l3096">     * Processes repetition. If the next character peeked is a quantifier</span><a href="#l3096"></a>
<span id="l3097">     * then new nodes must be appended to handle the repetition.</span><a href="#l3097"></a>
<span id="l3098">     * Prev could be a single or a group, so it could be a chain of nodes.</span><a href="#l3098"></a>
<span id="l3099">     */</span><a href="#l3099"></a>
<span id="l3100">    private Node closure(Node prev) {</span><a href="#l3100"></a>
<span id="l3101">        Node atom;</span><a href="#l3101"></a>
<span id="l3102">        int ch = peek();</span><a href="#l3102"></a>
<span id="l3103">        switch (ch) {</span><a href="#l3103"></a>
<span id="l3104">        case '?':</span><a href="#l3104"></a>
<span id="l3105">            ch = next();</span><a href="#l3105"></a>
<span id="l3106">            if (ch == '?') {</span><a href="#l3106"></a>
<span id="l3107">                next();</span><a href="#l3107"></a>
<span id="l3108">                return new Ques(prev, LAZY);</span><a href="#l3108"></a>
<span id="l3109">            } else if (ch == '+') {</span><a href="#l3109"></a>
<span id="l3110">                next();</span><a href="#l3110"></a>
<span id="l3111">                return new Ques(prev, POSSESSIVE);</span><a href="#l3111"></a>
<span id="l3112">            }</span><a href="#l3112"></a>
<span id="l3113">            return new Ques(prev, GREEDY);</span><a href="#l3113"></a>
<span id="l3114">        case '*':</span><a href="#l3114"></a>
<span id="l3115">            ch = next();</span><a href="#l3115"></a>
<span id="l3116">            if (ch == '?') {</span><a href="#l3116"></a>
<span id="l3117">                next();</span><a href="#l3117"></a>
<span id="l3118">                return new Curly(prev, 0, MAX_REPS, LAZY);</span><a href="#l3118"></a>
<span id="l3119">            } else if (ch == '+') {</span><a href="#l3119"></a>
<span id="l3120">                next();</span><a href="#l3120"></a>
<span id="l3121">                return new Curly(prev, 0, MAX_REPS, POSSESSIVE);</span><a href="#l3121"></a>
<span id="l3122">            }</span><a href="#l3122"></a>
<span id="l3123">            return new Curly(prev, 0, MAX_REPS, GREEDY);</span><a href="#l3123"></a>
<span id="l3124">        case '+':</span><a href="#l3124"></a>
<span id="l3125">            ch = next();</span><a href="#l3125"></a>
<span id="l3126">            if (ch == '?') {</span><a href="#l3126"></a>
<span id="l3127">                next();</span><a href="#l3127"></a>
<span id="l3128">                return new Curly(prev, 1, MAX_REPS, LAZY);</span><a href="#l3128"></a>
<span id="l3129">            } else if (ch == '+') {</span><a href="#l3129"></a>
<span id="l3130">                next();</span><a href="#l3130"></a>
<span id="l3131">                return new Curly(prev, 1, MAX_REPS, POSSESSIVE);</span><a href="#l3131"></a>
<span id="l3132">            }</span><a href="#l3132"></a>
<span id="l3133">            return new Curly(prev, 1, MAX_REPS, GREEDY);</span><a href="#l3133"></a>
<span id="l3134">        case '{':</span><a href="#l3134"></a>
<span id="l3135">            ch = temp[cursor+1];</span><a href="#l3135"></a>
<span id="l3136">            if (ASCII.isDigit(ch)) {</span><a href="#l3136"></a>
<span id="l3137">                skip();</span><a href="#l3137"></a>
<span id="l3138">                int cmin = 0;</span><a href="#l3138"></a>
<span id="l3139">                do {</span><a href="#l3139"></a>
<span id="l3140">                    cmin = cmin * 10 + (ch - '0');</span><a href="#l3140"></a>
<span id="l3141">                } while (ASCII.isDigit(ch = read()));</span><a href="#l3141"></a>
<span id="l3142">                int cmax = cmin;</span><a href="#l3142"></a>
<span id="l3143">                if (ch == ',') {</span><a href="#l3143"></a>
<span id="l3144">                    ch = read();</span><a href="#l3144"></a>
<span id="l3145">                    cmax = MAX_REPS;</span><a href="#l3145"></a>
<span id="l3146">                    if (ch != '}') {</span><a href="#l3146"></a>
<span id="l3147">                        cmax = 0;</span><a href="#l3147"></a>
<span id="l3148">                        while (ASCII.isDigit(ch)) {</span><a href="#l3148"></a>
<span id="l3149">                            cmax = cmax * 10 + (ch - '0');</span><a href="#l3149"></a>
<span id="l3150">                            ch = read();</span><a href="#l3150"></a>
<span id="l3151">                        }</span><a href="#l3151"></a>
<span id="l3152">                    }</span><a href="#l3152"></a>
<span id="l3153">                }</span><a href="#l3153"></a>
<span id="l3154">                if (ch != '}')</span><a href="#l3154"></a>
<span id="l3155">                    throw error(&quot;Unclosed counted closure&quot;);</span><a href="#l3155"></a>
<span id="l3156">                if (((cmin) | (cmax) | (cmax - cmin)) &lt; 0)</span><a href="#l3156"></a>
<span id="l3157">                    throw error(&quot;Illegal repetition range&quot;);</span><a href="#l3157"></a>
<span id="l3158">                Curly curly;</span><a href="#l3158"></a>
<span id="l3159">                ch = peek();</span><a href="#l3159"></a>
<span id="l3160">                if (ch == '?') {</span><a href="#l3160"></a>
<span id="l3161">                    next();</span><a href="#l3161"></a>
<span id="l3162">                    curly = new Curly(prev, cmin, cmax, LAZY);</span><a href="#l3162"></a>
<span id="l3163">                } else if (ch == '+') {</span><a href="#l3163"></a>
<span id="l3164">                    next();</span><a href="#l3164"></a>
<span id="l3165">                    curly = new Curly(prev, cmin, cmax, POSSESSIVE);</span><a href="#l3165"></a>
<span id="l3166">                } else {</span><a href="#l3166"></a>
<span id="l3167">                    curly = new Curly(prev, cmin, cmax, GREEDY);</span><a href="#l3167"></a>
<span id="l3168">                }</span><a href="#l3168"></a>
<span id="l3169">                return curly;</span><a href="#l3169"></a>
<span id="l3170">            } else {</span><a href="#l3170"></a>
<span id="l3171">                throw error(&quot;Illegal repetition&quot;);</span><a href="#l3171"></a>
<span id="l3172">            }</span><a href="#l3172"></a>
<span id="l3173">        default:</span><a href="#l3173"></a>
<span id="l3174">            return prev;</span><a href="#l3174"></a>
<span id="l3175">        }</span><a href="#l3175"></a>
<span id="l3176">    }</span><a href="#l3176"></a>
<span id="l3177"></span><a href="#l3177"></a>
<span id="l3178">    /**</span><a href="#l3178"></a>
<span id="l3179">     *  Utility method for parsing control escape sequences.</span><a href="#l3179"></a>
<span id="l3180">     */</span><a href="#l3180"></a>
<span id="l3181">    private int c() {</span><a href="#l3181"></a>
<span id="l3182">        if (cursor &lt; patternLength) {</span><a href="#l3182"></a>
<span id="l3183">            return read() ^ 64;</span><a href="#l3183"></a>
<span id="l3184">        }</span><a href="#l3184"></a>
<span id="l3185">        throw error(&quot;Illegal control escape sequence&quot;);</span><a href="#l3185"></a>
<span id="l3186">    }</span><a href="#l3186"></a>
<span id="l3187"></span><a href="#l3187"></a>
<span id="l3188">    /**</span><a href="#l3188"></a>
<span id="l3189">     *  Utility method for parsing octal escape sequences.</span><a href="#l3189"></a>
<span id="l3190">     */</span><a href="#l3190"></a>
<span id="l3191">    private int o() {</span><a href="#l3191"></a>
<span id="l3192">        int n = read();</span><a href="#l3192"></a>
<span id="l3193">        if (((n-'0')|('7'-n)) &gt;= 0) {</span><a href="#l3193"></a>
<span id="l3194">            int m = read();</span><a href="#l3194"></a>
<span id="l3195">            if (((m-'0')|('7'-m)) &gt;= 0) {</span><a href="#l3195"></a>
<span id="l3196">                int o = read();</span><a href="#l3196"></a>
<span id="l3197">                if ((((o-'0')|('7'-o)) &gt;= 0) &amp;&amp; (((n-'0')|('3'-n)) &gt;= 0)) {</span><a href="#l3197"></a>
<span id="l3198">                    return (n - '0') * 64 + (m - '0') * 8 + (o - '0');</span><a href="#l3198"></a>
<span id="l3199">                }</span><a href="#l3199"></a>
<span id="l3200">                unread();</span><a href="#l3200"></a>
<span id="l3201">                return (n - '0') * 8 + (m - '0');</span><a href="#l3201"></a>
<span id="l3202">            }</span><a href="#l3202"></a>
<span id="l3203">            unread();</span><a href="#l3203"></a>
<span id="l3204">            return (n - '0');</span><a href="#l3204"></a>
<span id="l3205">        }</span><a href="#l3205"></a>
<span id="l3206">        throw error(&quot;Illegal octal escape sequence&quot;);</span><a href="#l3206"></a>
<span id="l3207">    }</span><a href="#l3207"></a>
<span id="l3208"></span><a href="#l3208"></a>
<span id="l3209">    /**</span><a href="#l3209"></a>
<span id="l3210">     *  Utility method for parsing hexadecimal escape sequences.</span><a href="#l3210"></a>
<span id="l3211">     */</span><a href="#l3211"></a>
<span id="l3212">    private int x() {</span><a href="#l3212"></a>
<span id="l3213">        int n = read();</span><a href="#l3213"></a>
<span id="l3214">        if (ASCII.isHexDigit(n)) {</span><a href="#l3214"></a>
<span id="l3215">            int m = read();</span><a href="#l3215"></a>
<span id="l3216">            if (ASCII.isHexDigit(m)) {</span><a href="#l3216"></a>
<span id="l3217">                return ASCII.toDigit(n) * 16 + ASCII.toDigit(m);</span><a href="#l3217"></a>
<span id="l3218">            }</span><a href="#l3218"></a>
<span id="l3219">        } else if (n == '{' &amp;&amp; ASCII.isHexDigit(peek())) {</span><a href="#l3219"></a>
<span id="l3220">            int ch = 0;</span><a href="#l3220"></a>
<span id="l3221">            while (ASCII.isHexDigit(n = read())) {</span><a href="#l3221"></a>
<span id="l3222">                ch = (ch &lt;&lt; 4) + ASCII.toDigit(n);</span><a href="#l3222"></a>
<span id="l3223">                if (ch &gt; Character.MAX_CODE_POINT)</span><a href="#l3223"></a>
<span id="l3224">                    throw error(&quot;Hexadecimal codepoint is too big&quot;);</span><a href="#l3224"></a>
<span id="l3225">            }</span><a href="#l3225"></a>
<span id="l3226">            if (n != '}')</span><a href="#l3226"></a>
<span id="l3227">                throw error(&quot;Unclosed hexadecimal escape sequence&quot;);</span><a href="#l3227"></a>
<span id="l3228">            return ch;</span><a href="#l3228"></a>
<span id="l3229">        }</span><a href="#l3229"></a>
<span id="l3230">        throw error(&quot;Illegal hexadecimal escape sequence&quot;);</span><a href="#l3230"></a>
<span id="l3231">    }</span><a href="#l3231"></a>
<span id="l3232"></span><a href="#l3232"></a>
<span id="l3233">    /**</span><a href="#l3233"></a>
<span id="l3234">     *  Utility method for parsing unicode escape sequences.</span><a href="#l3234"></a>
<span id="l3235">     */</span><a href="#l3235"></a>
<span id="l3236">    private int cursor() {</span><a href="#l3236"></a>
<span id="l3237">        return cursor;</span><a href="#l3237"></a>
<span id="l3238">    }</span><a href="#l3238"></a>
<span id="l3239"></span><a href="#l3239"></a>
<span id="l3240">    private void setcursor(int pos) {</span><a href="#l3240"></a>
<span id="l3241">        cursor = pos;</span><a href="#l3241"></a>
<span id="l3242">    }</span><a href="#l3242"></a>
<span id="l3243"></span><a href="#l3243"></a>
<span id="l3244">    private int uxxxx() {</span><a href="#l3244"></a>
<span id="l3245">        int n = 0;</span><a href="#l3245"></a>
<span id="l3246">        for (int i = 0; i &lt; 4; i++) {</span><a href="#l3246"></a>
<span id="l3247">            int ch = read();</span><a href="#l3247"></a>
<span id="l3248">            if (!ASCII.isHexDigit(ch)) {</span><a href="#l3248"></a>
<span id="l3249">                throw error(&quot;Illegal Unicode escape sequence&quot;);</span><a href="#l3249"></a>
<span id="l3250">            }</span><a href="#l3250"></a>
<span id="l3251">            n = n * 16 + ASCII.toDigit(ch);</span><a href="#l3251"></a>
<span id="l3252">        }</span><a href="#l3252"></a>
<span id="l3253">        return n;</span><a href="#l3253"></a>
<span id="l3254">    }</span><a href="#l3254"></a>
<span id="l3255"></span><a href="#l3255"></a>
<span id="l3256">    private int u() {</span><a href="#l3256"></a>
<span id="l3257">        int n = uxxxx();</span><a href="#l3257"></a>
<span id="l3258">        if (Character.isHighSurrogate((char)n)) {</span><a href="#l3258"></a>
<span id="l3259">            int cur = cursor();</span><a href="#l3259"></a>
<span id="l3260">            if (read() == '\\' &amp;&amp; read() == 'u') {</span><a href="#l3260"></a>
<span id="l3261">                int n2 = uxxxx();</span><a href="#l3261"></a>
<span id="l3262">                if (Character.isLowSurrogate((char)n2))</span><a href="#l3262"></a>
<span id="l3263">                    return Character.toCodePoint((char)n, (char)n2);</span><a href="#l3263"></a>
<span id="l3264">            }</span><a href="#l3264"></a>
<span id="l3265">            setcursor(cur);</span><a href="#l3265"></a>
<span id="l3266">        }</span><a href="#l3266"></a>
<span id="l3267">        return n;</span><a href="#l3267"></a>
<span id="l3268">    }</span><a href="#l3268"></a>
<span id="l3269"></span><a href="#l3269"></a>
<span id="l3270">    //</span><a href="#l3270"></a>
<span id="l3271">    // Utility methods for code point support</span><a href="#l3271"></a>
<span id="l3272">    //</span><a href="#l3272"></a>
<span id="l3273"></span><a href="#l3273"></a>
<span id="l3274">    private static final int countChars(CharSequence seq, int index,</span><a href="#l3274"></a>
<span id="l3275">                                        int lengthInCodePoints) {</span><a href="#l3275"></a>
<span id="l3276">        // optimization</span><a href="#l3276"></a>
<span id="l3277">        if (lengthInCodePoints == 1 &amp;&amp; index &gt;= 0 &amp;&amp; index &lt; seq.length() &amp;&amp;</span><a href="#l3277"></a>
<span id="l3278">            !Character.isHighSurrogate(seq.charAt(index))) {</span><a href="#l3278"></a>
<span id="l3279">            return 1;</span><a href="#l3279"></a>
<span id="l3280">        }</span><a href="#l3280"></a>
<span id="l3281">        int length = seq.length();</span><a href="#l3281"></a>
<span id="l3282">        int x = index;</span><a href="#l3282"></a>
<span id="l3283">        if (lengthInCodePoints &gt;= 0) {</span><a href="#l3283"></a>
<span id="l3284">            assert (index &gt;= 0 &amp;&amp; index &lt; length);</span><a href="#l3284"></a>
<span id="l3285">            for (int i = 0; x &lt; length &amp;&amp; i &lt; lengthInCodePoints; i++) {</span><a href="#l3285"></a>
<span id="l3286">                if (Character.isHighSurrogate(seq.charAt(x++))) {</span><a href="#l3286"></a>
<span id="l3287">                    if (x &lt; length &amp;&amp; Character.isLowSurrogate(seq.charAt(x))) {</span><a href="#l3287"></a>
<span id="l3288">                        x++;</span><a href="#l3288"></a>
<span id="l3289">                    }</span><a href="#l3289"></a>
<span id="l3290">                }</span><a href="#l3290"></a>
<span id="l3291">            }</span><a href="#l3291"></a>
<span id="l3292">            return x - index;</span><a href="#l3292"></a>
<span id="l3293">        }</span><a href="#l3293"></a>
<span id="l3294"></span><a href="#l3294"></a>
<span id="l3295">        assert (index &gt;= 0 &amp;&amp; index &lt;= length);</span><a href="#l3295"></a>
<span id="l3296">        if (index == 0) {</span><a href="#l3296"></a>
<span id="l3297">            return 0;</span><a href="#l3297"></a>
<span id="l3298">        }</span><a href="#l3298"></a>
<span id="l3299">        int len = -lengthInCodePoints;</span><a href="#l3299"></a>
<span id="l3300">        for (int i = 0; x &gt; 0 &amp;&amp; i &lt; len; i++) {</span><a href="#l3300"></a>
<span id="l3301">            if (Character.isLowSurrogate(seq.charAt(--x))) {</span><a href="#l3301"></a>
<span id="l3302">                if (x &gt; 0 &amp;&amp; Character.isHighSurrogate(seq.charAt(x-1))) {</span><a href="#l3302"></a>
<span id="l3303">                    x--;</span><a href="#l3303"></a>
<span id="l3304">                }</span><a href="#l3304"></a>
<span id="l3305">            }</span><a href="#l3305"></a>
<span id="l3306">        }</span><a href="#l3306"></a>
<span id="l3307">        return index - x;</span><a href="#l3307"></a>
<span id="l3308">    }</span><a href="#l3308"></a>
<span id="l3309"></span><a href="#l3309"></a>
<span id="l3310">    private static final int countCodePoints(CharSequence seq) {</span><a href="#l3310"></a>
<span id="l3311">        int length = seq.length();</span><a href="#l3311"></a>
<span id="l3312">        int n = 0;</span><a href="#l3312"></a>
<span id="l3313">        for (int i = 0; i &lt; length; ) {</span><a href="#l3313"></a>
<span id="l3314">            n++;</span><a href="#l3314"></a>
<span id="l3315">            if (Character.isHighSurrogate(seq.charAt(i++))) {</span><a href="#l3315"></a>
<span id="l3316">                if (i &lt; length &amp;&amp; Character.isLowSurrogate(seq.charAt(i))) {</span><a href="#l3316"></a>
<span id="l3317">                    i++;</span><a href="#l3317"></a>
<span id="l3318">                }</span><a href="#l3318"></a>
<span id="l3319">            }</span><a href="#l3319"></a>
<span id="l3320">        }</span><a href="#l3320"></a>
<span id="l3321">        return n;</span><a href="#l3321"></a>
<span id="l3322">    }</span><a href="#l3322"></a>
<span id="l3323"></span><a href="#l3323"></a>
<span id="l3324">    /**</span><a href="#l3324"></a>
<span id="l3325">     *  Creates a bit vector for matching Latin-1 values. A normal BitClass</span><a href="#l3325"></a>
<span id="l3326">     *  never matches values above Latin-1, and a complemented BitClass always</span><a href="#l3326"></a>
<span id="l3327">     *  matches values above Latin-1.</span><a href="#l3327"></a>
<span id="l3328">     */</span><a href="#l3328"></a>
<span id="l3329">    private static final class BitClass extends BmpCharProperty {</span><a href="#l3329"></a>
<span id="l3330">        final boolean[] bits;</span><a href="#l3330"></a>
<span id="l3331">        BitClass() { bits = new boolean[256]; }</span><a href="#l3331"></a>
<span id="l3332">        private BitClass(boolean[] bits) { this.bits = bits; }</span><a href="#l3332"></a>
<span id="l3333">        BitClass add(int c, int flags) {</span><a href="#l3333"></a>
<span id="l3334">            assert c &gt;= 0 &amp;&amp; c &lt;= 255;</span><a href="#l3334"></a>
<span id="l3335">            if ((flags &amp; CASE_INSENSITIVE) != 0) {</span><a href="#l3335"></a>
<span id="l3336">                if (ASCII.isAscii(c)) {</span><a href="#l3336"></a>
<span id="l3337">                    bits[ASCII.toUpper(c)] = true;</span><a href="#l3337"></a>
<span id="l3338">                    bits[ASCII.toLower(c)] = true;</span><a href="#l3338"></a>
<span id="l3339">                } else if ((flags &amp; UNICODE_CASE) != 0) {</span><a href="#l3339"></a>
<span id="l3340">                    bits[Character.toLowerCase(c)] = true;</span><a href="#l3340"></a>
<span id="l3341">                    bits[Character.toUpperCase(c)] = true;</span><a href="#l3341"></a>
<span id="l3342">                }</span><a href="#l3342"></a>
<span id="l3343">            }</span><a href="#l3343"></a>
<span id="l3344">            bits[c] = true;</span><a href="#l3344"></a>
<span id="l3345">            return this;</span><a href="#l3345"></a>
<span id="l3346">        }</span><a href="#l3346"></a>
<span id="l3347">        boolean isSatisfiedBy(int ch) {</span><a href="#l3347"></a>
<span id="l3348">            return ch &lt; 256 &amp;&amp; bits[ch];</span><a href="#l3348"></a>
<span id="l3349">        }</span><a href="#l3349"></a>
<span id="l3350">    }</span><a href="#l3350"></a>
<span id="l3351"></span><a href="#l3351"></a>
<span id="l3352">    /**</span><a href="#l3352"></a>
<span id="l3353">     *  Returns a suitably optimized, single character matcher.</span><a href="#l3353"></a>
<span id="l3354">     */</span><a href="#l3354"></a>
<span id="l3355">    private CharProperty newSingle(final int ch) {</span><a href="#l3355"></a>
<span id="l3356">        if (has(CASE_INSENSITIVE)) {</span><a href="#l3356"></a>
<span id="l3357">            int lower, upper;</span><a href="#l3357"></a>
<span id="l3358">            if (has(UNICODE_CASE)) {</span><a href="#l3358"></a>
<span id="l3359">                upper = Character.toUpperCase(ch);</span><a href="#l3359"></a>
<span id="l3360">                lower = Character.toLowerCase(upper);</span><a href="#l3360"></a>
<span id="l3361">                if (upper != lower)</span><a href="#l3361"></a>
<span id="l3362">                    return new SingleU(lower);</span><a href="#l3362"></a>
<span id="l3363">            } else if (ASCII.isAscii(ch)) {</span><a href="#l3363"></a>
<span id="l3364">                lower = ASCII.toLower(ch);</span><a href="#l3364"></a>
<span id="l3365">                upper = ASCII.toUpper(ch);</span><a href="#l3365"></a>
<span id="l3366">                if (lower != upper)</span><a href="#l3366"></a>
<span id="l3367">                    return new SingleI(lower, upper);</span><a href="#l3367"></a>
<span id="l3368">            }</span><a href="#l3368"></a>
<span id="l3369">        }</span><a href="#l3369"></a>
<span id="l3370">        if (isSupplementary(ch))</span><a href="#l3370"></a>
<span id="l3371">            return new SingleS(ch);    // Match a given Unicode character</span><a href="#l3371"></a>
<span id="l3372">        return new Single(ch);         // Match a given BMP character</span><a href="#l3372"></a>
<span id="l3373">    }</span><a href="#l3373"></a>
<span id="l3374"></span><a href="#l3374"></a>
<span id="l3375">    /**</span><a href="#l3375"></a>
<span id="l3376">     *  Utility method for creating a string slice matcher.</span><a href="#l3376"></a>
<span id="l3377">     */</span><a href="#l3377"></a>
<span id="l3378">    private Node newSlice(int[] buf, int count, boolean hasSupplementary) {</span><a href="#l3378"></a>
<span id="l3379">        int[] tmp = new int[count];</span><a href="#l3379"></a>
<span id="l3380">        if (has(CASE_INSENSITIVE)) {</span><a href="#l3380"></a>
<span id="l3381">            if (has(UNICODE_CASE)) {</span><a href="#l3381"></a>
<span id="l3382">                for (int i = 0; i &lt; count; i++) {</span><a href="#l3382"></a>
<span id="l3383">                    tmp[i] = Character.toLowerCase(</span><a href="#l3383"></a>
<span id="l3384">                                 Character.toUpperCase(buf[i]));</span><a href="#l3384"></a>
<span id="l3385">                }</span><a href="#l3385"></a>
<span id="l3386">                return hasSupplementary? new SliceUS(tmp) : new SliceU(tmp);</span><a href="#l3386"></a>
<span id="l3387">            }</span><a href="#l3387"></a>
<span id="l3388">            for (int i = 0; i &lt; count; i++) {</span><a href="#l3388"></a>
<span id="l3389">                tmp[i] = ASCII.toLower(buf[i]);</span><a href="#l3389"></a>
<span id="l3390">            }</span><a href="#l3390"></a>
<span id="l3391">            return hasSupplementary? new SliceIS(tmp) : new SliceI(tmp);</span><a href="#l3391"></a>
<span id="l3392">        }</span><a href="#l3392"></a>
<span id="l3393">        for (int i = 0; i &lt; count; i++) {</span><a href="#l3393"></a>
<span id="l3394">            tmp[i] = buf[i];</span><a href="#l3394"></a>
<span id="l3395">        }</span><a href="#l3395"></a>
<span id="l3396">        return hasSupplementary ? new SliceS(tmp) : new Slice(tmp);</span><a href="#l3396"></a>
<span id="l3397">    }</span><a href="#l3397"></a>
<span id="l3398"></span><a href="#l3398"></a>
<span id="l3399">    /**</span><a href="#l3399"></a>
<span id="l3400">     * The following classes are the building components of the object</span><a href="#l3400"></a>
<span id="l3401">     * tree that represents a compiled regular expression. The object tree</span><a href="#l3401"></a>
<span id="l3402">     * is made of individual elements that handle constructs in the Pattern.</span><a href="#l3402"></a>
<span id="l3403">     * Each type of object knows how to match its equivalent construct with</span><a href="#l3403"></a>
<span id="l3404">     * the match() method.</span><a href="#l3404"></a>
<span id="l3405">     */</span><a href="#l3405"></a>
<span id="l3406"></span><a href="#l3406"></a>
<span id="l3407">    /**</span><a href="#l3407"></a>
<span id="l3408">     * Base class for all node classes. Subclasses should override the match()</span><a href="#l3408"></a>
<span id="l3409">     * method as appropriate. This class is an accepting node, so its match()</span><a href="#l3409"></a>
<span id="l3410">     * always returns true.</span><a href="#l3410"></a>
<span id="l3411">     */</span><a href="#l3411"></a>
<span id="l3412">    static class Node extends Object {</span><a href="#l3412"></a>
<span id="l3413">        Node next;</span><a href="#l3413"></a>
<span id="l3414">        Node() {</span><a href="#l3414"></a>
<span id="l3415">            next = Pattern.accept;</span><a href="#l3415"></a>
<span id="l3416">        }</span><a href="#l3416"></a>
<span id="l3417">        /**</span><a href="#l3417"></a>
<span id="l3418">         * This method implements the classic accept node.</span><a href="#l3418"></a>
<span id="l3419">         */</span><a href="#l3419"></a>
<span id="l3420">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l3420"></a>
<span id="l3421">            matcher.last = i;</span><a href="#l3421"></a>
<span id="l3422">            matcher.groups[0] = matcher.first;</span><a href="#l3422"></a>
<span id="l3423">            matcher.groups[1] = matcher.last;</span><a href="#l3423"></a>
<span id="l3424">            return true;</span><a href="#l3424"></a>
<span id="l3425">        }</span><a href="#l3425"></a>
<span id="l3426">        /**</span><a href="#l3426"></a>
<span id="l3427">         * This method is good for all zero length assertions.</span><a href="#l3427"></a>
<span id="l3428">         */</span><a href="#l3428"></a>
<span id="l3429">        boolean study(TreeInfo info) {</span><a href="#l3429"></a>
<span id="l3430">            if (next != null) {</span><a href="#l3430"></a>
<span id="l3431">                return next.study(info);</span><a href="#l3431"></a>
<span id="l3432">            } else {</span><a href="#l3432"></a>
<span id="l3433">                return info.deterministic;</span><a href="#l3433"></a>
<span id="l3434">            }</span><a href="#l3434"></a>
<span id="l3435">        }</span><a href="#l3435"></a>
<span id="l3436">    }</span><a href="#l3436"></a>
<span id="l3437"></span><a href="#l3437"></a>
<span id="l3438">    static class LastNode extends Node {</span><a href="#l3438"></a>
<span id="l3439">        /**</span><a href="#l3439"></a>
<span id="l3440">         * This method implements the classic accept node with</span><a href="#l3440"></a>
<span id="l3441">         * the addition of a check to see if the match occurred</span><a href="#l3441"></a>
<span id="l3442">         * using all of the input.</span><a href="#l3442"></a>
<span id="l3443">         */</span><a href="#l3443"></a>
<span id="l3444">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l3444"></a>
<span id="l3445">            if (matcher.acceptMode == Matcher.ENDANCHOR &amp;&amp; i != matcher.to)</span><a href="#l3445"></a>
<span id="l3446">                return false;</span><a href="#l3446"></a>
<span id="l3447">            matcher.last = i;</span><a href="#l3447"></a>
<span id="l3448">            matcher.groups[0] = matcher.first;</span><a href="#l3448"></a>
<span id="l3449">            matcher.groups[1] = matcher.last;</span><a href="#l3449"></a>
<span id="l3450">            return true;</span><a href="#l3450"></a>
<span id="l3451">        }</span><a href="#l3451"></a>
<span id="l3452">    }</span><a href="#l3452"></a>
<span id="l3453"></span><a href="#l3453"></a>
<span id="l3454">    /**</span><a href="#l3454"></a>
<span id="l3455">     * Used for REs that can start anywhere within the input string.</span><a href="#l3455"></a>
<span id="l3456">     * This basically tries to match repeatedly at each spot in the</span><a href="#l3456"></a>
<span id="l3457">     * input string, moving forward after each try. An anchored search</span><a href="#l3457"></a>
<span id="l3458">     * or a BnM will bypass this node completely.</span><a href="#l3458"></a>
<span id="l3459">     */</span><a href="#l3459"></a>
<span id="l3460">    static class Start extends Node {</span><a href="#l3460"></a>
<span id="l3461">        int minLength;</span><a href="#l3461"></a>
<span id="l3462">        Start(Node node) {</span><a href="#l3462"></a>
<span id="l3463">            this.next = node;</span><a href="#l3463"></a>
<span id="l3464">            TreeInfo info = new TreeInfo();</span><a href="#l3464"></a>
<span id="l3465">            next.study(info);</span><a href="#l3465"></a>
<span id="l3466">            minLength = info.minLength;</span><a href="#l3466"></a>
<span id="l3467">        }</span><a href="#l3467"></a>
<span id="l3468">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l3468"></a>
<span id="l3469">            if (i &gt; matcher.to - minLength) {</span><a href="#l3469"></a>
<span id="l3470">                matcher.hitEnd = true;</span><a href="#l3470"></a>
<span id="l3471">                return false;</span><a href="#l3471"></a>
<span id="l3472">            }</span><a href="#l3472"></a>
<span id="l3473">            int guard = matcher.to - minLength;</span><a href="#l3473"></a>
<span id="l3474">            for (; i &lt;= guard; i++) {</span><a href="#l3474"></a>
<span id="l3475">                if (next.match(matcher, i, seq)) {</span><a href="#l3475"></a>
<span id="l3476">                    matcher.first = i;</span><a href="#l3476"></a>
<span id="l3477">                    matcher.groups[0] = matcher.first;</span><a href="#l3477"></a>
<span id="l3478">                    matcher.groups[1] = matcher.last;</span><a href="#l3478"></a>
<span id="l3479">                    return true;</span><a href="#l3479"></a>
<span id="l3480">                }</span><a href="#l3480"></a>
<span id="l3481">            }</span><a href="#l3481"></a>
<span id="l3482">            matcher.hitEnd = true;</span><a href="#l3482"></a>
<span id="l3483">            return false;</span><a href="#l3483"></a>
<span id="l3484">        }</span><a href="#l3484"></a>
<span id="l3485">        boolean study(TreeInfo info) {</span><a href="#l3485"></a>
<span id="l3486">            next.study(info);</span><a href="#l3486"></a>
<span id="l3487">            info.maxValid = false;</span><a href="#l3487"></a>
<span id="l3488">            info.deterministic = false;</span><a href="#l3488"></a>
<span id="l3489">            return false;</span><a href="#l3489"></a>
<span id="l3490">        }</span><a href="#l3490"></a>
<span id="l3491">    }</span><a href="#l3491"></a>
<span id="l3492"></span><a href="#l3492"></a>
<span id="l3493">    /*</span><a href="#l3493"></a>
<span id="l3494">     * StartS supports supplementary characters, including unpaired surrogates.</span><a href="#l3494"></a>
<span id="l3495">     */</span><a href="#l3495"></a>
<span id="l3496">    static final class StartS extends Start {</span><a href="#l3496"></a>
<span id="l3497">        StartS(Node node) {</span><a href="#l3497"></a>
<span id="l3498">            super(node);</span><a href="#l3498"></a>
<span id="l3499">        }</span><a href="#l3499"></a>
<span id="l3500">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l3500"></a>
<span id="l3501">            if (i &gt; matcher.to - minLength) {</span><a href="#l3501"></a>
<span id="l3502">                matcher.hitEnd = true;</span><a href="#l3502"></a>
<span id="l3503">                return false;</span><a href="#l3503"></a>
<span id="l3504">            }</span><a href="#l3504"></a>
<span id="l3505">            int guard = matcher.to - minLength;</span><a href="#l3505"></a>
<span id="l3506">            while (i &lt;= guard) {</span><a href="#l3506"></a>
<span id="l3507">                //if ((ret = next.match(matcher, i, seq)) || i == guard)</span><a href="#l3507"></a>
<span id="l3508">                if (next.match(matcher, i, seq)) {</span><a href="#l3508"></a>
<span id="l3509">                    matcher.first = i;</span><a href="#l3509"></a>
<span id="l3510">                    matcher.groups[0] = matcher.first;</span><a href="#l3510"></a>
<span id="l3511">                    matcher.groups[1] = matcher.last;</span><a href="#l3511"></a>
<span id="l3512">                    return true;</span><a href="#l3512"></a>
<span id="l3513">                }</span><a href="#l3513"></a>
<span id="l3514">                if (i == guard)</span><a href="#l3514"></a>
<span id="l3515">                    break;</span><a href="#l3515"></a>
<span id="l3516">                // Optimization to move to the next character. This is</span><a href="#l3516"></a>
<span id="l3517">                // faster than countChars(seq, i, 1).</span><a href="#l3517"></a>
<span id="l3518">                if (Character.isHighSurrogate(seq.charAt(i++))) {</span><a href="#l3518"></a>
<span id="l3519">                    if (i &lt; seq.length() &amp;&amp;</span><a href="#l3519"></a>
<span id="l3520">                        Character.isLowSurrogate(seq.charAt(i))) {</span><a href="#l3520"></a>
<span id="l3521">                        i++;</span><a href="#l3521"></a>
<span id="l3522">                    }</span><a href="#l3522"></a>
<span id="l3523">                }</span><a href="#l3523"></a>
<span id="l3524">            }</span><a href="#l3524"></a>
<span id="l3525">            matcher.hitEnd = true;</span><a href="#l3525"></a>
<span id="l3526">            return false;</span><a href="#l3526"></a>
<span id="l3527">        }</span><a href="#l3527"></a>
<span id="l3528">    }</span><a href="#l3528"></a>
<span id="l3529"></span><a href="#l3529"></a>
<span id="l3530">    /**</span><a href="#l3530"></a>
<span id="l3531">     * Node to anchor at the beginning of input. This object implements the</span><a href="#l3531"></a>
<span id="l3532">     * match for a \A sequence, and the caret anchor will use this if not in</span><a href="#l3532"></a>
<span id="l3533">     * multiline mode.</span><a href="#l3533"></a>
<span id="l3534">     */</span><a href="#l3534"></a>
<span id="l3535">    static final class Begin extends Node {</span><a href="#l3535"></a>
<span id="l3536">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l3536"></a>
<span id="l3537">            int fromIndex = (matcher.anchoringBounds) ?</span><a href="#l3537"></a>
<span id="l3538">                matcher.from : 0;</span><a href="#l3538"></a>
<span id="l3539">            if (i == fromIndex &amp;&amp; next.match(matcher, i, seq)) {</span><a href="#l3539"></a>
<span id="l3540">                matcher.first = i;</span><a href="#l3540"></a>
<span id="l3541">                matcher.groups[0] = i;</span><a href="#l3541"></a>
<span id="l3542">                matcher.groups[1] = matcher.last;</span><a href="#l3542"></a>
<span id="l3543">                return true;</span><a href="#l3543"></a>
<span id="l3544">            } else {</span><a href="#l3544"></a>
<span id="l3545">                return false;</span><a href="#l3545"></a>
<span id="l3546">            }</span><a href="#l3546"></a>
<span id="l3547">        }</span><a href="#l3547"></a>
<span id="l3548">    }</span><a href="#l3548"></a>
<span id="l3549"></span><a href="#l3549"></a>
<span id="l3550">    /**</span><a href="#l3550"></a>
<span id="l3551">     * Node to anchor at the end of input. This is the absolute end, so this</span><a href="#l3551"></a>
<span id="l3552">     * should not match at the last newline before the end as $ will.</span><a href="#l3552"></a>
<span id="l3553">     */</span><a href="#l3553"></a>
<span id="l3554">    static final class End extends Node {</span><a href="#l3554"></a>
<span id="l3555">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l3555"></a>
<span id="l3556">            int endIndex = (matcher.anchoringBounds) ?</span><a href="#l3556"></a>
<span id="l3557">                matcher.to : matcher.getTextLength();</span><a href="#l3557"></a>
<span id="l3558">            if (i == endIndex) {</span><a href="#l3558"></a>
<span id="l3559">                matcher.hitEnd = true;</span><a href="#l3559"></a>
<span id="l3560">                return next.match(matcher, i, seq);</span><a href="#l3560"></a>
<span id="l3561">            }</span><a href="#l3561"></a>
<span id="l3562">            return false;</span><a href="#l3562"></a>
<span id="l3563">        }</span><a href="#l3563"></a>
<span id="l3564">    }</span><a href="#l3564"></a>
<span id="l3565"></span><a href="#l3565"></a>
<span id="l3566">    /**</span><a href="#l3566"></a>
<span id="l3567">     * Node to anchor at the beginning of a line. This is essentially the</span><a href="#l3567"></a>
<span id="l3568">     * object to match for the multiline ^.</span><a href="#l3568"></a>
<span id="l3569">     */</span><a href="#l3569"></a>
<span id="l3570">    static final class Caret extends Node {</span><a href="#l3570"></a>
<span id="l3571">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l3571"></a>
<span id="l3572">            int startIndex = matcher.from;</span><a href="#l3572"></a>
<span id="l3573">            int endIndex = matcher.to;</span><a href="#l3573"></a>
<span id="l3574">            if (!matcher.anchoringBounds) {</span><a href="#l3574"></a>
<span id="l3575">                startIndex = 0;</span><a href="#l3575"></a>
<span id="l3576">                endIndex = matcher.getTextLength();</span><a href="#l3576"></a>
<span id="l3577">            }</span><a href="#l3577"></a>
<span id="l3578">            // Perl does not match ^ at end of input even after newline</span><a href="#l3578"></a>
<span id="l3579">            if (i == endIndex) {</span><a href="#l3579"></a>
<span id="l3580">                matcher.hitEnd = true;</span><a href="#l3580"></a>
<span id="l3581">                return false;</span><a href="#l3581"></a>
<span id="l3582">            }</span><a href="#l3582"></a>
<span id="l3583">            if (i &gt; startIndex) {</span><a href="#l3583"></a>
<span id="l3584">                char ch = seq.charAt(i-1);</span><a href="#l3584"></a>
<span id="l3585">                if (ch != '\n' &amp;&amp; ch != '\r'</span><a href="#l3585"></a>
<span id="l3586">                    &amp;&amp; (ch|1) != '\u2029'</span><a href="#l3586"></a>
<span id="l3587">                    &amp;&amp; ch != '\u0085' ) {</span><a href="#l3587"></a>
<span id="l3588">                    return false;</span><a href="#l3588"></a>
<span id="l3589">                }</span><a href="#l3589"></a>
<span id="l3590">                // Should treat /r/n as one newline</span><a href="#l3590"></a>
<span id="l3591">                if (ch == '\r' &amp;&amp; seq.charAt(i) == '\n')</span><a href="#l3591"></a>
<span id="l3592">                    return false;</span><a href="#l3592"></a>
<span id="l3593">            }</span><a href="#l3593"></a>
<span id="l3594">            return next.match(matcher, i, seq);</span><a href="#l3594"></a>
<span id="l3595">        }</span><a href="#l3595"></a>
<span id="l3596">    }</span><a href="#l3596"></a>
<span id="l3597"></span><a href="#l3597"></a>
<span id="l3598">    /**</span><a href="#l3598"></a>
<span id="l3599">     * Node to anchor at the beginning of a line when in unixdot mode.</span><a href="#l3599"></a>
<span id="l3600">     */</span><a href="#l3600"></a>
<span id="l3601">    static final class UnixCaret extends Node {</span><a href="#l3601"></a>
<span id="l3602">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l3602"></a>
<span id="l3603">            int startIndex = matcher.from;</span><a href="#l3603"></a>
<span id="l3604">            int endIndex = matcher.to;</span><a href="#l3604"></a>
<span id="l3605">            if (!matcher.anchoringBounds) {</span><a href="#l3605"></a>
<span id="l3606">                startIndex = 0;</span><a href="#l3606"></a>
<span id="l3607">                endIndex = matcher.getTextLength();</span><a href="#l3607"></a>
<span id="l3608">            }</span><a href="#l3608"></a>
<span id="l3609">            // Perl does not match ^ at end of input even after newline</span><a href="#l3609"></a>
<span id="l3610">            if (i == endIndex) {</span><a href="#l3610"></a>
<span id="l3611">                matcher.hitEnd = true;</span><a href="#l3611"></a>
<span id="l3612">                return false;</span><a href="#l3612"></a>
<span id="l3613">            }</span><a href="#l3613"></a>
<span id="l3614">            if (i &gt; startIndex) {</span><a href="#l3614"></a>
<span id="l3615">                char ch = seq.charAt(i-1);</span><a href="#l3615"></a>
<span id="l3616">                if (ch != '\n') {</span><a href="#l3616"></a>
<span id="l3617">                    return false;</span><a href="#l3617"></a>
<span id="l3618">                }</span><a href="#l3618"></a>
<span id="l3619">            }</span><a href="#l3619"></a>
<span id="l3620">            return next.match(matcher, i, seq);</span><a href="#l3620"></a>
<span id="l3621">        }</span><a href="#l3621"></a>
<span id="l3622">    }</span><a href="#l3622"></a>
<span id="l3623"></span><a href="#l3623"></a>
<span id="l3624">    /**</span><a href="#l3624"></a>
<span id="l3625">     * Node to match the location where the last match ended.</span><a href="#l3625"></a>
<span id="l3626">     * This is used for the \G construct.</span><a href="#l3626"></a>
<span id="l3627">     */</span><a href="#l3627"></a>
<span id="l3628">    static final class LastMatch extends Node {</span><a href="#l3628"></a>
<span id="l3629">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l3629"></a>
<span id="l3630">            if (i != matcher.oldLast)</span><a href="#l3630"></a>
<span id="l3631">                return false;</span><a href="#l3631"></a>
<span id="l3632">            return next.match(matcher, i, seq);</span><a href="#l3632"></a>
<span id="l3633">        }</span><a href="#l3633"></a>
<span id="l3634">    }</span><a href="#l3634"></a>
<span id="l3635"></span><a href="#l3635"></a>
<span id="l3636">    /**</span><a href="#l3636"></a>
<span id="l3637">     * Node to anchor at the end of a line or the end of input based on the</span><a href="#l3637"></a>
<span id="l3638">     * multiline mode.</span><a href="#l3638"></a>
<span id="l3639">     *</span><a href="#l3639"></a>
<span id="l3640">     * When not in multiline mode, the $ can only match at the very end</span><a href="#l3640"></a>
<span id="l3641">     * of the input, unless the input ends in a line terminator in which</span><a href="#l3641"></a>
<span id="l3642">     * it matches right before the last line terminator.</span><a href="#l3642"></a>
<span id="l3643">     *</span><a href="#l3643"></a>
<span id="l3644">     * Note that \r\n is considered an atomic line terminator.</span><a href="#l3644"></a>
<span id="l3645">     *</span><a href="#l3645"></a>
<span id="l3646">     * Like ^ the $ operator matches at a position, it does not match the</span><a href="#l3646"></a>
<span id="l3647">     * line terminators themselves.</span><a href="#l3647"></a>
<span id="l3648">     */</span><a href="#l3648"></a>
<span id="l3649">    static final class Dollar extends Node {</span><a href="#l3649"></a>
<span id="l3650">        boolean multiline;</span><a href="#l3650"></a>
<span id="l3651">        Dollar(boolean mul) {</span><a href="#l3651"></a>
<span id="l3652">            multiline = mul;</span><a href="#l3652"></a>
<span id="l3653">        }</span><a href="#l3653"></a>
<span id="l3654">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l3654"></a>
<span id="l3655">            int endIndex = (matcher.anchoringBounds) ?</span><a href="#l3655"></a>
<span id="l3656">                matcher.to : matcher.getTextLength();</span><a href="#l3656"></a>
<span id="l3657">            if (!multiline) {</span><a href="#l3657"></a>
<span id="l3658">                if (i &lt; endIndex - 2)</span><a href="#l3658"></a>
<span id="l3659">                    return false;</span><a href="#l3659"></a>
<span id="l3660">                if (i == endIndex - 2) {</span><a href="#l3660"></a>
<span id="l3661">                    char ch = seq.charAt(i);</span><a href="#l3661"></a>
<span id="l3662">                    if (ch != '\r')</span><a href="#l3662"></a>
<span id="l3663">                        return false;</span><a href="#l3663"></a>
<span id="l3664">                    ch = seq.charAt(i + 1);</span><a href="#l3664"></a>
<span id="l3665">                    if (ch != '\n')</span><a href="#l3665"></a>
<span id="l3666">                        return false;</span><a href="#l3666"></a>
<span id="l3667">                }</span><a href="#l3667"></a>
<span id="l3668">            }</span><a href="#l3668"></a>
<span id="l3669">            // Matches before any line terminator; also matches at the</span><a href="#l3669"></a>
<span id="l3670">            // end of input</span><a href="#l3670"></a>
<span id="l3671">            // Before line terminator:</span><a href="#l3671"></a>
<span id="l3672">            // If multiline, we match here no matter what</span><a href="#l3672"></a>
<span id="l3673">            // If not multiline, fall through so that the end</span><a href="#l3673"></a>
<span id="l3674">            // is marked as hit; this must be a /r/n or a /n</span><a href="#l3674"></a>
<span id="l3675">            // at the very end so the end was hit; more input</span><a href="#l3675"></a>
<span id="l3676">            // could make this not match here</span><a href="#l3676"></a>
<span id="l3677">            if (i &lt; endIndex) {</span><a href="#l3677"></a>
<span id="l3678">                char ch = seq.charAt(i);</span><a href="#l3678"></a>
<span id="l3679">                 if (ch == '\n') {</span><a href="#l3679"></a>
<span id="l3680">                     // No match between \r\n</span><a href="#l3680"></a>
<span id="l3681">                     if (i &gt; 0 &amp;&amp; seq.charAt(i-1) == '\r')</span><a href="#l3681"></a>
<span id="l3682">                         return false;</span><a href="#l3682"></a>
<span id="l3683">                     if (multiline)</span><a href="#l3683"></a>
<span id="l3684">                         return next.match(matcher, i, seq);</span><a href="#l3684"></a>
<span id="l3685">                 } else if (ch == '\r' || ch == '\u0085' ||</span><a href="#l3685"></a>
<span id="l3686">                            (ch|1) == '\u2029') {</span><a href="#l3686"></a>
<span id="l3687">                     if (multiline)</span><a href="#l3687"></a>
<span id="l3688">                         return next.match(matcher, i, seq);</span><a href="#l3688"></a>
<span id="l3689">                 } else { // No line terminator, no match</span><a href="#l3689"></a>
<span id="l3690">                     return false;</span><a href="#l3690"></a>
<span id="l3691">                 }</span><a href="#l3691"></a>
<span id="l3692">            }</span><a href="#l3692"></a>
<span id="l3693">            // Matched at current end so hit end</span><a href="#l3693"></a>
<span id="l3694">            matcher.hitEnd = true;</span><a href="#l3694"></a>
<span id="l3695">            // If a $ matches because of end of input, then more input</span><a href="#l3695"></a>
<span id="l3696">            // could cause it to fail!</span><a href="#l3696"></a>
<span id="l3697">            matcher.requireEnd = true;</span><a href="#l3697"></a>
<span id="l3698">            return next.match(matcher, i, seq);</span><a href="#l3698"></a>
<span id="l3699">        }</span><a href="#l3699"></a>
<span id="l3700">        boolean study(TreeInfo info) {</span><a href="#l3700"></a>
<span id="l3701">            next.study(info);</span><a href="#l3701"></a>
<span id="l3702">            return info.deterministic;</span><a href="#l3702"></a>
<span id="l3703">        }</span><a href="#l3703"></a>
<span id="l3704">    }</span><a href="#l3704"></a>
<span id="l3705"></span><a href="#l3705"></a>
<span id="l3706">    /**</span><a href="#l3706"></a>
<span id="l3707">     * Node to anchor at the end of a line or the end of input based on the</span><a href="#l3707"></a>
<span id="l3708">     * multiline mode when in unix lines mode.</span><a href="#l3708"></a>
<span id="l3709">     */</span><a href="#l3709"></a>
<span id="l3710">    static final class UnixDollar extends Node {</span><a href="#l3710"></a>
<span id="l3711">        boolean multiline;</span><a href="#l3711"></a>
<span id="l3712">        UnixDollar(boolean mul) {</span><a href="#l3712"></a>
<span id="l3713">            multiline = mul;</span><a href="#l3713"></a>
<span id="l3714">        }</span><a href="#l3714"></a>
<span id="l3715">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l3715"></a>
<span id="l3716">            int endIndex = (matcher.anchoringBounds) ?</span><a href="#l3716"></a>
<span id="l3717">                matcher.to : matcher.getTextLength();</span><a href="#l3717"></a>
<span id="l3718">            if (i &lt; endIndex) {</span><a href="#l3718"></a>
<span id="l3719">                char ch = seq.charAt(i);</span><a href="#l3719"></a>
<span id="l3720">                if (ch == '\n') {</span><a href="#l3720"></a>
<span id="l3721">                    // If not multiline, then only possible to</span><a href="#l3721"></a>
<span id="l3722">                    // match at very end or one before end</span><a href="#l3722"></a>
<span id="l3723">                    if (multiline == false &amp;&amp; i != endIndex - 1)</span><a href="#l3723"></a>
<span id="l3724">                        return false;</span><a href="#l3724"></a>
<span id="l3725">                    // If multiline return next.match without setting</span><a href="#l3725"></a>
<span id="l3726">                    // matcher.hitEnd</span><a href="#l3726"></a>
<span id="l3727">                    if (multiline)</span><a href="#l3727"></a>
<span id="l3728">                        return next.match(matcher, i, seq);</span><a href="#l3728"></a>
<span id="l3729">                } else {</span><a href="#l3729"></a>
<span id="l3730">                    return false;</span><a href="#l3730"></a>
<span id="l3731">                }</span><a href="#l3731"></a>
<span id="l3732">            }</span><a href="#l3732"></a>
<span id="l3733">            // Matching because at the end or 1 before the end;</span><a href="#l3733"></a>
<span id="l3734">            // more input could change this so set hitEnd</span><a href="#l3734"></a>
<span id="l3735">            matcher.hitEnd = true;</span><a href="#l3735"></a>
<span id="l3736">            // If a $ matches because of end of input, then more input</span><a href="#l3736"></a>
<span id="l3737">            // could cause it to fail!</span><a href="#l3737"></a>
<span id="l3738">            matcher.requireEnd = true;</span><a href="#l3738"></a>
<span id="l3739">            return next.match(matcher, i, seq);</span><a href="#l3739"></a>
<span id="l3740">        }</span><a href="#l3740"></a>
<span id="l3741">        boolean study(TreeInfo info) {</span><a href="#l3741"></a>
<span id="l3742">            next.study(info);</span><a href="#l3742"></a>
<span id="l3743">            return info.deterministic;</span><a href="#l3743"></a>
<span id="l3744">        }</span><a href="#l3744"></a>
<span id="l3745">    }</span><a href="#l3745"></a>
<span id="l3746"></span><a href="#l3746"></a>
<span id="l3747">    /**</span><a href="#l3747"></a>
<span id="l3748">     * Node class that matches a Unicode line ending '\R'</span><a href="#l3748"></a>
<span id="l3749">     */</span><a href="#l3749"></a>
<span id="l3750">    static final class LineEnding extends Node {</span><a href="#l3750"></a>
<span id="l3751">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l3751"></a>
<span id="l3752">            // (u+000Du+000A|[u+000Au+000Bu+000Cu+000Du+0085u+2028u+2029])</span><a href="#l3752"></a>
<span id="l3753">            if (i &lt; matcher.to) {</span><a href="#l3753"></a>
<span id="l3754">                int ch = seq.charAt(i);</span><a href="#l3754"></a>
<span id="l3755">                if (ch == 0x0A || ch == 0x0B || ch == 0x0C ||</span><a href="#l3755"></a>
<span id="l3756">                    ch == 0x85 || ch == 0x2028 || ch == 0x2029)</span><a href="#l3756"></a>
<span id="l3757">                    return next.match(matcher, i + 1, seq);</span><a href="#l3757"></a>
<span id="l3758">                if (ch == 0x0D) {</span><a href="#l3758"></a>
<span id="l3759">                    i++;</span><a href="#l3759"></a>
<span id="l3760">                    if (i &lt; matcher.to &amp;&amp; seq.charAt(i) == 0x0A)</span><a href="#l3760"></a>
<span id="l3761">                        i++;</span><a href="#l3761"></a>
<span id="l3762">                    return next.match(matcher, i, seq);</span><a href="#l3762"></a>
<span id="l3763">                }</span><a href="#l3763"></a>
<span id="l3764">            } else {</span><a href="#l3764"></a>
<span id="l3765">                matcher.hitEnd = true;</span><a href="#l3765"></a>
<span id="l3766">            }</span><a href="#l3766"></a>
<span id="l3767">            return false;</span><a href="#l3767"></a>
<span id="l3768">        }</span><a href="#l3768"></a>
<span id="l3769">        boolean study(TreeInfo info) {</span><a href="#l3769"></a>
<span id="l3770">            info.minLength++;</span><a href="#l3770"></a>
<span id="l3771">            info.maxLength += 2;</span><a href="#l3771"></a>
<span id="l3772">            return next.study(info);</span><a href="#l3772"></a>
<span id="l3773">        }</span><a href="#l3773"></a>
<span id="l3774">    }</span><a href="#l3774"></a>
<span id="l3775"></span><a href="#l3775"></a>
<span id="l3776">    /**</span><a href="#l3776"></a>
<span id="l3777">     * Abstract node class to match one character satisfying some</span><a href="#l3777"></a>
<span id="l3778">     * boolean property.</span><a href="#l3778"></a>
<span id="l3779">     */</span><a href="#l3779"></a>
<span id="l3780">    private static abstract class CharProperty extends Node {</span><a href="#l3780"></a>
<span id="l3781">        abstract boolean isSatisfiedBy(int ch);</span><a href="#l3781"></a>
<span id="l3782">        CharProperty complement() {</span><a href="#l3782"></a>
<span id="l3783">            return new CharProperty() {</span><a href="#l3783"></a>
<span id="l3784">                    boolean isSatisfiedBy(int ch) {</span><a href="#l3784"></a>
<span id="l3785">                        return ! CharProperty.this.isSatisfiedBy(ch);}};</span><a href="#l3785"></a>
<span id="l3786">        }</span><a href="#l3786"></a>
<span id="l3787">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l3787"></a>
<span id="l3788">            if (i &lt; matcher.to) {</span><a href="#l3788"></a>
<span id="l3789">                int ch = Character.codePointAt(seq, i);</span><a href="#l3789"></a>
<span id="l3790">                return isSatisfiedBy(ch)</span><a href="#l3790"></a>
<span id="l3791">                    &amp;&amp; next.match(matcher, i+Character.charCount(ch), seq);</span><a href="#l3791"></a>
<span id="l3792">            } else {</span><a href="#l3792"></a>
<span id="l3793">                matcher.hitEnd = true;</span><a href="#l3793"></a>
<span id="l3794">                return false;</span><a href="#l3794"></a>
<span id="l3795">            }</span><a href="#l3795"></a>
<span id="l3796">        }</span><a href="#l3796"></a>
<span id="l3797">        boolean study(TreeInfo info) {</span><a href="#l3797"></a>
<span id="l3798">            info.minLength++;</span><a href="#l3798"></a>
<span id="l3799">            info.maxLength++;</span><a href="#l3799"></a>
<span id="l3800">            return next.study(info);</span><a href="#l3800"></a>
<span id="l3801">        }</span><a href="#l3801"></a>
<span id="l3802">    }</span><a href="#l3802"></a>
<span id="l3803"></span><a href="#l3803"></a>
<span id="l3804">    /**</span><a href="#l3804"></a>
<span id="l3805">     * Optimized version of CharProperty that works only for</span><a href="#l3805"></a>
<span id="l3806">     * properties never satisfied by Supplementary characters.</span><a href="#l3806"></a>
<span id="l3807">     */</span><a href="#l3807"></a>
<span id="l3808">    private static abstract class BmpCharProperty extends CharProperty {</span><a href="#l3808"></a>
<span id="l3809">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l3809"></a>
<span id="l3810">            if (i &lt; matcher.to) {</span><a href="#l3810"></a>
<span id="l3811">                return isSatisfiedBy(seq.charAt(i))</span><a href="#l3811"></a>
<span id="l3812">                    &amp;&amp; next.match(matcher, i+1, seq);</span><a href="#l3812"></a>
<span id="l3813">            } else {</span><a href="#l3813"></a>
<span id="l3814">                matcher.hitEnd = true;</span><a href="#l3814"></a>
<span id="l3815">                return false;</span><a href="#l3815"></a>
<span id="l3816">            }</span><a href="#l3816"></a>
<span id="l3817">        }</span><a href="#l3817"></a>
<span id="l3818">    }</span><a href="#l3818"></a>
<span id="l3819"></span><a href="#l3819"></a>
<span id="l3820">    /**</span><a href="#l3820"></a>
<span id="l3821">     * Node class that matches a Supplementary Unicode character</span><a href="#l3821"></a>
<span id="l3822">     */</span><a href="#l3822"></a>
<span id="l3823">    static final class SingleS extends CharProperty {</span><a href="#l3823"></a>
<span id="l3824">        final int c;</span><a href="#l3824"></a>
<span id="l3825">        SingleS(int c) { this.c = c; }</span><a href="#l3825"></a>
<span id="l3826">        boolean isSatisfiedBy(int ch) {</span><a href="#l3826"></a>
<span id="l3827">            return ch == c;</span><a href="#l3827"></a>
<span id="l3828">        }</span><a href="#l3828"></a>
<span id="l3829">    }</span><a href="#l3829"></a>
<span id="l3830"></span><a href="#l3830"></a>
<span id="l3831">    /**</span><a href="#l3831"></a>
<span id="l3832">     * Optimization -- matches a given BMP character</span><a href="#l3832"></a>
<span id="l3833">     */</span><a href="#l3833"></a>
<span id="l3834">    static final class Single extends BmpCharProperty {</span><a href="#l3834"></a>
<span id="l3835">        final int c;</span><a href="#l3835"></a>
<span id="l3836">        Single(int c) { this.c = c; }</span><a href="#l3836"></a>
<span id="l3837">        boolean isSatisfiedBy(int ch) {</span><a href="#l3837"></a>
<span id="l3838">            return ch == c;</span><a href="#l3838"></a>
<span id="l3839">        }</span><a href="#l3839"></a>
<span id="l3840">    }</span><a href="#l3840"></a>
<span id="l3841"></span><a href="#l3841"></a>
<span id="l3842">    /**</span><a href="#l3842"></a>
<span id="l3843">     * Case insensitive matches a given BMP character</span><a href="#l3843"></a>
<span id="l3844">     */</span><a href="#l3844"></a>
<span id="l3845">    static final class SingleI extends BmpCharProperty {</span><a href="#l3845"></a>
<span id="l3846">        final int lower;</span><a href="#l3846"></a>
<span id="l3847">        final int upper;</span><a href="#l3847"></a>
<span id="l3848">        SingleI(int lower, int upper) {</span><a href="#l3848"></a>
<span id="l3849">            this.lower = lower;</span><a href="#l3849"></a>
<span id="l3850">            this.upper = upper;</span><a href="#l3850"></a>
<span id="l3851">        }</span><a href="#l3851"></a>
<span id="l3852">        boolean isSatisfiedBy(int ch) {</span><a href="#l3852"></a>
<span id="l3853">            return ch == lower || ch == upper;</span><a href="#l3853"></a>
<span id="l3854">        }</span><a href="#l3854"></a>
<span id="l3855">    }</span><a href="#l3855"></a>
<span id="l3856"></span><a href="#l3856"></a>
<span id="l3857">    /**</span><a href="#l3857"></a>
<span id="l3858">     * Unicode case insensitive matches a given Unicode character</span><a href="#l3858"></a>
<span id="l3859">     */</span><a href="#l3859"></a>
<span id="l3860">    static final class SingleU extends CharProperty {</span><a href="#l3860"></a>
<span id="l3861">        final int lower;</span><a href="#l3861"></a>
<span id="l3862">        SingleU(int lower) {</span><a href="#l3862"></a>
<span id="l3863">            this.lower = lower;</span><a href="#l3863"></a>
<span id="l3864">        }</span><a href="#l3864"></a>
<span id="l3865">        boolean isSatisfiedBy(int ch) {</span><a href="#l3865"></a>
<span id="l3866">            return lower == ch ||</span><a href="#l3866"></a>
<span id="l3867">                lower == Character.toLowerCase(Character.toUpperCase(ch));</span><a href="#l3867"></a>
<span id="l3868">        }</span><a href="#l3868"></a>
<span id="l3869">    }</span><a href="#l3869"></a>
<span id="l3870"></span><a href="#l3870"></a>
<span id="l3871">    /**</span><a href="#l3871"></a>
<span id="l3872">     * Node class that matches a Unicode block.</span><a href="#l3872"></a>
<span id="l3873">     */</span><a href="#l3873"></a>
<span id="l3874">    static final class Block extends CharProperty {</span><a href="#l3874"></a>
<span id="l3875">        final Character.UnicodeBlock block;</span><a href="#l3875"></a>
<span id="l3876">        Block(Character.UnicodeBlock block) {</span><a href="#l3876"></a>
<span id="l3877">            this.block = block;</span><a href="#l3877"></a>
<span id="l3878">        }</span><a href="#l3878"></a>
<span id="l3879">        boolean isSatisfiedBy(int ch) {</span><a href="#l3879"></a>
<span id="l3880">            return block == Character.UnicodeBlock.of(ch);</span><a href="#l3880"></a>
<span id="l3881">        }</span><a href="#l3881"></a>
<span id="l3882">    }</span><a href="#l3882"></a>
<span id="l3883"></span><a href="#l3883"></a>
<span id="l3884">    /**</span><a href="#l3884"></a>
<span id="l3885">     * Node class that matches a Unicode script</span><a href="#l3885"></a>
<span id="l3886">     */</span><a href="#l3886"></a>
<span id="l3887">    static final class Script extends CharProperty {</span><a href="#l3887"></a>
<span id="l3888">        final Character.UnicodeScript script;</span><a href="#l3888"></a>
<span id="l3889">        Script(Character.UnicodeScript script) {</span><a href="#l3889"></a>
<span id="l3890">            this.script = script;</span><a href="#l3890"></a>
<span id="l3891">        }</span><a href="#l3891"></a>
<span id="l3892">        boolean isSatisfiedBy(int ch) {</span><a href="#l3892"></a>
<span id="l3893">            return script == Character.UnicodeScript.of(ch);</span><a href="#l3893"></a>
<span id="l3894">        }</span><a href="#l3894"></a>
<span id="l3895">    }</span><a href="#l3895"></a>
<span id="l3896"></span><a href="#l3896"></a>
<span id="l3897">    /**</span><a href="#l3897"></a>
<span id="l3898">     * Node class that matches a Unicode category.</span><a href="#l3898"></a>
<span id="l3899">     */</span><a href="#l3899"></a>
<span id="l3900">    static final class Category extends CharProperty {</span><a href="#l3900"></a>
<span id="l3901">        final int typeMask;</span><a href="#l3901"></a>
<span id="l3902">        Category(int typeMask) { this.typeMask = typeMask; }</span><a href="#l3902"></a>
<span id="l3903">        boolean isSatisfiedBy(int ch) {</span><a href="#l3903"></a>
<span id="l3904">            return (typeMask &amp; (1 &lt;&lt; Character.getType(ch))) != 0;</span><a href="#l3904"></a>
<span id="l3905">        }</span><a href="#l3905"></a>
<span id="l3906">    }</span><a href="#l3906"></a>
<span id="l3907"></span><a href="#l3907"></a>
<span id="l3908">    /**</span><a href="#l3908"></a>
<span id="l3909">     * Node class that matches a Unicode &quot;type&quot;</span><a href="#l3909"></a>
<span id="l3910">     */</span><a href="#l3910"></a>
<span id="l3911">    static final class Utype extends CharProperty {</span><a href="#l3911"></a>
<span id="l3912">        final UnicodeProp uprop;</span><a href="#l3912"></a>
<span id="l3913">        Utype(UnicodeProp uprop) { this.uprop = uprop; }</span><a href="#l3913"></a>
<span id="l3914">        boolean isSatisfiedBy(int ch) {</span><a href="#l3914"></a>
<span id="l3915">            return uprop.is(ch);</span><a href="#l3915"></a>
<span id="l3916">        }</span><a href="#l3916"></a>
<span id="l3917">    }</span><a href="#l3917"></a>
<span id="l3918"></span><a href="#l3918"></a>
<span id="l3919">    /**</span><a href="#l3919"></a>
<span id="l3920">     * Node class that matches a POSIX type.</span><a href="#l3920"></a>
<span id="l3921">     */</span><a href="#l3921"></a>
<span id="l3922">    static final class Ctype extends BmpCharProperty {</span><a href="#l3922"></a>
<span id="l3923">        final int ctype;</span><a href="#l3923"></a>
<span id="l3924">        Ctype(int ctype) { this.ctype = ctype; }</span><a href="#l3924"></a>
<span id="l3925">        boolean isSatisfiedBy(int ch) {</span><a href="#l3925"></a>
<span id="l3926">            return ch &lt; 128 &amp;&amp; ASCII.isType(ch, ctype);</span><a href="#l3926"></a>
<span id="l3927">        }</span><a href="#l3927"></a>
<span id="l3928">    }</span><a href="#l3928"></a>
<span id="l3929"></span><a href="#l3929"></a>
<span id="l3930">    /**</span><a href="#l3930"></a>
<span id="l3931">     * Node class that matches a Perl vertical whitespace</span><a href="#l3931"></a>
<span id="l3932">     */</span><a href="#l3932"></a>
<span id="l3933">    static final class VertWS extends BmpCharProperty {</span><a href="#l3933"></a>
<span id="l3934">        boolean isSatisfiedBy(int cp) {</span><a href="#l3934"></a>
<span id="l3935">            return (cp &gt;= 0x0A &amp;&amp; cp &lt;= 0x0D) ||</span><a href="#l3935"></a>
<span id="l3936">                   cp == 0x85 || cp == 0x2028 || cp == 0x2029;</span><a href="#l3936"></a>
<span id="l3937">        }</span><a href="#l3937"></a>
<span id="l3938">    }</span><a href="#l3938"></a>
<span id="l3939"></span><a href="#l3939"></a>
<span id="l3940">    /**</span><a href="#l3940"></a>
<span id="l3941">     * Node class that matches a Perl horizontal whitespace</span><a href="#l3941"></a>
<span id="l3942">     */</span><a href="#l3942"></a>
<span id="l3943">    static final class HorizWS extends BmpCharProperty {</span><a href="#l3943"></a>
<span id="l3944">        boolean isSatisfiedBy(int cp) {</span><a href="#l3944"></a>
<span id="l3945">            return cp == 0x09 || cp == 0x20 || cp == 0xa0 ||</span><a href="#l3945"></a>
<span id="l3946">                   cp == 0x1680 || cp == 0x180e ||</span><a href="#l3946"></a>
<span id="l3947">                   cp &gt;= 0x2000 &amp;&amp; cp &lt;= 0x200a ||</span><a href="#l3947"></a>
<span id="l3948">                   cp == 0x202f || cp == 0x205f || cp == 0x3000;</span><a href="#l3948"></a>
<span id="l3949">        }</span><a href="#l3949"></a>
<span id="l3950">    }</span><a href="#l3950"></a>
<span id="l3951"></span><a href="#l3951"></a>
<span id="l3952">    /**</span><a href="#l3952"></a>
<span id="l3953">     * Base class for all Slice nodes</span><a href="#l3953"></a>
<span id="l3954">     */</span><a href="#l3954"></a>
<span id="l3955">    static class SliceNode extends Node {</span><a href="#l3955"></a>
<span id="l3956">        int[] buffer;</span><a href="#l3956"></a>
<span id="l3957">        SliceNode(int[] buf) {</span><a href="#l3957"></a>
<span id="l3958">            buffer = buf;</span><a href="#l3958"></a>
<span id="l3959">        }</span><a href="#l3959"></a>
<span id="l3960">        boolean study(TreeInfo info) {</span><a href="#l3960"></a>
<span id="l3961">            info.minLength += buffer.length;</span><a href="#l3961"></a>
<span id="l3962">            info.maxLength += buffer.length;</span><a href="#l3962"></a>
<span id="l3963">            return next.study(info);</span><a href="#l3963"></a>
<span id="l3964">        }</span><a href="#l3964"></a>
<span id="l3965">    }</span><a href="#l3965"></a>
<span id="l3966"></span><a href="#l3966"></a>
<span id="l3967">    /**</span><a href="#l3967"></a>
<span id="l3968">     * Node class for a case sensitive/BMP-only sequence of literal</span><a href="#l3968"></a>
<span id="l3969">     * characters.</span><a href="#l3969"></a>
<span id="l3970">     */</span><a href="#l3970"></a>
<span id="l3971">    static final class Slice extends SliceNode {</span><a href="#l3971"></a>
<span id="l3972">        Slice(int[] buf) {</span><a href="#l3972"></a>
<span id="l3973">            super(buf);</span><a href="#l3973"></a>
<span id="l3974">        }</span><a href="#l3974"></a>
<span id="l3975">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l3975"></a>
<span id="l3976">            int[] buf = buffer;</span><a href="#l3976"></a>
<span id="l3977">            int len = buf.length;</span><a href="#l3977"></a>
<span id="l3978">            for (int j=0; j&lt;len; j++) {</span><a href="#l3978"></a>
<span id="l3979">                if ((i+j) &gt;= matcher.to) {</span><a href="#l3979"></a>
<span id="l3980">                    matcher.hitEnd = true;</span><a href="#l3980"></a>
<span id="l3981">                    return false;</span><a href="#l3981"></a>
<span id="l3982">                }</span><a href="#l3982"></a>
<span id="l3983">                if (buf[j] != seq.charAt(i+j))</span><a href="#l3983"></a>
<span id="l3984">                    return false;</span><a href="#l3984"></a>
<span id="l3985">            }</span><a href="#l3985"></a>
<span id="l3986">            return next.match(matcher, i+len, seq);</span><a href="#l3986"></a>
<span id="l3987">        }</span><a href="#l3987"></a>
<span id="l3988">    }</span><a href="#l3988"></a>
<span id="l3989"></span><a href="#l3989"></a>
<span id="l3990">    /**</span><a href="#l3990"></a>
<span id="l3991">     * Node class for a case_insensitive/BMP-only sequence of literal</span><a href="#l3991"></a>
<span id="l3992">     * characters.</span><a href="#l3992"></a>
<span id="l3993">     */</span><a href="#l3993"></a>
<span id="l3994">    static class SliceI extends SliceNode {</span><a href="#l3994"></a>
<span id="l3995">        SliceI(int[] buf) {</span><a href="#l3995"></a>
<span id="l3996">            super(buf);</span><a href="#l3996"></a>
<span id="l3997">        }</span><a href="#l3997"></a>
<span id="l3998">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l3998"></a>
<span id="l3999">            int[] buf = buffer;</span><a href="#l3999"></a>
<span id="l4000">            int len = buf.length;</span><a href="#l4000"></a>
<span id="l4001">            for (int j=0; j&lt;len; j++) {</span><a href="#l4001"></a>
<span id="l4002">                if ((i+j) &gt;= matcher.to) {</span><a href="#l4002"></a>
<span id="l4003">                    matcher.hitEnd = true;</span><a href="#l4003"></a>
<span id="l4004">                    return false;</span><a href="#l4004"></a>
<span id="l4005">                }</span><a href="#l4005"></a>
<span id="l4006">                int c = seq.charAt(i+j);</span><a href="#l4006"></a>
<span id="l4007">                if (buf[j] != c &amp;&amp;</span><a href="#l4007"></a>
<span id="l4008">                    buf[j] != ASCII.toLower(c))</span><a href="#l4008"></a>
<span id="l4009">                    return false;</span><a href="#l4009"></a>
<span id="l4010">            }</span><a href="#l4010"></a>
<span id="l4011">            return next.match(matcher, i+len, seq);</span><a href="#l4011"></a>
<span id="l4012">        }</span><a href="#l4012"></a>
<span id="l4013">    }</span><a href="#l4013"></a>
<span id="l4014"></span><a href="#l4014"></a>
<span id="l4015">    /**</span><a href="#l4015"></a>
<span id="l4016">     * Node class for a unicode_case_insensitive/BMP-only sequence of</span><a href="#l4016"></a>
<span id="l4017">     * literal characters. Uses unicode case folding.</span><a href="#l4017"></a>
<span id="l4018">     */</span><a href="#l4018"></a>
<span id="l4019">    static final class SliceU extends SliceNode {</span><a href="#l4019"></a>
<span id="l4020">        SliceU(int[] buf) {</span><a href="#l4020"></a>
<span id="l4021">            super(buf);</span><a href="#l4021"></a>
<span id="l4022">        }</span><a href="#l4022"></a>
<span id="l4023">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l4023"></a>
<span id="l4024">            int[] buf = buffer;</span><a href="#l4024"></a>
<span id="l4025">            int len = buf.length;</span><a href="#l4025"></a>
<span id="l4026">            for (int j=0; j&lt;len; j++) {</span><a href="#l4026"></a>
<span id="l4027">                if ((i+j) &gt;= matcher.to) {</span><a href="#l4027"></a>
<span id="l4028">                    matcher.hitEnd = true;</span><a href="#l4028"></a>
<span id="l4029">                    return false;</span><a href="#l4029"></a>
<span id="l4030">                }</span><a href="#l4030"></a>
<span id="l4031">                int c = seq.charAt(i+j);</span><a href="#l4031"></a>
<span id="l4032">                if (buf[j] != c &amp;&amp;</span><a href="#l4032"></a>
<span id="l4033">                    buf[j] != Character.toLowerCase(Character.toUpperCase(c)))</span><a href="#l4033"></a>
<span id="l4034">                    return false;</span><a href="#l4034"></a>
<span id="l4035">            }</span><a href="#l4035"></a>
<span id="l4036">            return next.match(matcher, i+len, seq);</span><a href="#l4036"></a>
<span id="l4037">        }</span><a href="#l4037"></a>
<span id="l4038">    }</span><a href="#l4038"></a>
<span id="l4039"></span><a href="#l4039"></a>
<span id="l4040">    /**</span><a href="#l4040"></a>
<span id="l4041">     * Node class for a case sensitive sequence of literal characters</span><a href="#l4041"></a>
<span id="l4042">     * including supplementary characters.</span><a href="#l4042"></a>
<span id="l4043">     */</span><a href="#l4043"></a>
<span id="l4044">    static final class SliceS extends SliceNode {</span><a href="#l4044"></a>
<span id="l4045">        SliceS(int[] buf) {</span><a href="#l4045"></a>
<span id="l4046">            super(buf);</span><a href="#l4046"></a>
<span id="l4047">        }</span><a href="#l4047"></a>
<span id="l4048">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l4048"></a>
<span id="l4049">            int[] buf = buffer;</span><a href="#l4049"></a>
<span id="l4050">            int x = i;</span><a href="#l4050"></a>
<span id="l4051">            for (int j = 0; j &lt; buf.length; j++) {</span><a href="#l4051"></a>
<span id="l4052">                if (x &gt;= matcher.to) {</span><a href="#l4052"></a>
<span id="l4053">                    matcher.hitEnd = true;</span><a href="#l4053"></a>
<span id="l4054">                    return false;</span><a href="#l4054"></a>
<span id="l4055">                }</span><a href="#l4055"></a>
<span id="l4056">                int c = Character.codePointAt(seq, x);</span><a href="#l4056"></a>
<span id="l4057">                if (buf[j] != c)</span><a href="#l4057"></a>
<span id="l4058">                    return false;</span><a href="#l4058"></a>
<span id="l4059">                x += Character.charCount(c);</span><a href="#l4059"></a>
<span id="l4060">                if (x &gt; matcher.to) {</span><a href="#l4060"></a>
<span id="l4061">                    matcher.hitEnd = true;</span><a href="#l4061"></a>
<span id="l4062">                    return false;</span><a href="#l4062"></a>
<span id="l4063">                }</span><a href="#l4063"></a>
<span id="l4064">            }</span><a href="#l4064"></a>
<span id="l4065">            return next.match(matcher, x, seq);</span><a href="#l4065"></a>
<span id="l4066">        }</span><a href="#l4066"></a>
<span id="l4067">    }</span><a href="#l4067"></a>
<span id="l4068"></span><a href="#l4068"></a>
<span id="l4069">    /**</span><a href="#l4069"></a>
<span id="l4070">     * Node class for a case insensitive sequence of literal characters</span><a href="#l4070"></a>
<span id="l4071">     * including supplementary characters.</span><a href="#l4071"></a>
<span id="l4072">     */</span><a href="#l4072"></a>
<span id="l4073">    static class SliceIS extends SliceNode {</span><a href="#l4073"></a>
<span id="l4074">        SliceIS(int[] buf) {</span><a href="#l4074"></a>
<span id="l4075">            super(buf);</span><a href="#l4075"></a>
<span id="l4076">        }</span><a href="#l4076"></a>
<span id="l4077">        int toLower(int c) {</span><a href="#l4077"></a>
<span id="l4078">            return ASCII.toLower(c);</span><a href="#l4078"></a>
<span id="l4079">        }</span><a href="#l4079"></a>
<span id="l4080">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l4080"></a>
<span id="l4081">            int[] buf = buffer;</span><a href="#l4081"></a>
<span id="l4082">            int x = i;</span><a href="#l4082"></a>
<span id="l4083">            for (int j = 0; j &lt; buf.length; j++) {</span><a href="#l4083"></a>
<span id="l4084">                if (x &gt;= matcher.to) {</span><a href="#l4084"></a>
<span id="l4085">                    matcher.hitEnd = true;</span><a href="#l4085"></a>
<span id="l4086">                    return false;</span><a href="#l4086"></a>
<span id="l4087">                }</span><a href="#l4087"></a>
<span id="l4088">                int c = Character.codePointAt(seq, x);</span><a href="#l4088"></a>
<span id="l4089">                if (buf[j] != c &amp;&amp; buf[j] != toLower(c))</span><a href="#l4089"></a>
<span id="l4090">                    return false;</span><a href="#l4090"></a>
<span id="l4091">                x += Character.charCount(c);</span><a href="#l4091"></a>
<span id="l4092">                if (x &gt; matcher.to) {</span><a href="#l4092"></a>
<span id="l4093">                    matcher.hitEnd = true;</span><a href="#l4093"></a>
<span id="l4094">                    return false;</span><a href="#l4094"></a>
<span id="l4095">                }</span><a href="#l4095"></a>
<span id="l4096">            }</span><a href="#l4096"></a>
<span id="l4097">            return next.match(matcher, x, seq);</span><a href="#l4097"></a>
<span id="l4098">        }</span><a href="#l4098"></a>
<span id="l4099">    }</span><a href="#l4099"></a>
<span id="l4100"></span><a href="#l4100"></a>
<span id="l4101">    /**</span><a href="#l4101"></a>
<span id="l4102">     * Node class for a case insensitive sequence of literal characters.</span><a href="#l4102"></a>
<span id="l4103">     * Uses unicode case folding.</span><a href="#l4103"></a>
<span id="l4104">     */</span><a href="#l4104"></a>
<span id="l4105">    static final class SliceUS extends SliceIS {</span><a href="#l4105"></a>
<span id="l4106">        SliceUS(int[] buf) {</span><a href="#l4106"></a>
<span id="l4107">            super(buf);</span><a href="#l4107"></a>
<span id="l4108">        }</span><a href="#l4108"></a>
<span id="l4109">        int toLower(int c) {</span><a href="#l4109"></a>
<span id="l4110">            return Character.toLowerCase(Character.toUpperCase(c));</span><a href="#l4110"></a>
<span id="l4111">        }</span><a href="#l4111"></a>
<span id="l4112">    }</span><a href="#l4112"></a>
<span id="l4113"></span><a href="#l4113"></a>
<span id="l4114">    private static boolean inRange(int lower, int ch, int upper) {</span><a href="#l4114"></a>
<span id="l4115">        return lower &lt;= ch &amp;&amp; ch &lt;= upper;</span><a href="#l4115"></a>
<span id="l4116">    }</span><a href="#l4116"></a>
<span id="l4117"></span><a href="#l4117"></a>
<span id="l4118">    /**</span><a href="#l4118"></a>
<span id="l4119">     * Returns node for matching characters within an explicit value range.</span><a href="#l4119"></a>
<span id="l4120">     */</span><a href="#l4120"></a>
<span id="l4121">    private static CharProperty rangeFor(final int lower,</span><a href="#l4121"></a>
<span id="l4122">                                         final int upper) {</span><a href="#l4122"></a>
<span id="l4123">        return new CharProperty() {</span><a href="#l4123"></a>
<span id="l4124">                boolean isSatisfiedBy(int ch) {</span><a href="#l4124"></a>
<span id="l4125">                    return inRange(lower, ch, upper);}};</span><a href="#l4125"></a>
<span id="l4126">    }</span><a href="#l4126"></a>
<span id="l4127"></span><a href="#l4127"></a>
<span id="l4128">    /**</span><a href="#l4128"></a>
<span id="l4129">     * Returns node for matching characters within an explicit value</span><a href="#l4129"></a>
<span id="l4130">     * range in a case insensitive manner.</span><a href="#l4130"></a>
<span id="l4131">     */</span><a href="#l4131"></a>
<span id="l4132">    private CharProperty caseInsensitiveRangeFor(final int lower,</span><a href="#l4132"></a>
<span id="l4133">                                                 final int upper) {</span><a href="#l4133"></a>
<span id="l4134">        if (has(UNICODE_CASE))</span><a href="#l4134"></a>
<span id="l4135">            return new CharProperty() {</span><a href="#l4135"></a>
<span id="l4136">                boolean isSatisfiedBy(int ch) {</span><a href="#l4136"></a>
<span id="l4137">                    if (inRange(lower, ch, upper))</span><a href="#l4137"></a>
<span id="l4138">                        return true;</span><a href="#l4138"></a>
<span id="l4139">                    int up = Character.toUpperCase(ch);</span><a href="#l4139"></a>
<span id="l4140">                    return inRange(lower, up, upper) ||</span><a href="#l4140"></a>
<span id="l4141">                           inRange(lower, Character.toLowerCase(up), upper);}};</span><a href="#l4141"></a>
<span id="l4142">        return new CharProperty() {</span><a href="#l4142"></a>
<span id="l4143">            boolean isSatisfiedBy(int ch) {</span><a href="#l4143"></a>
<span id="l4144">                return inRange(lower, ch, upper) ||</span><a href="#l4144"></a>
<span id="l4145">                    ASCII.isAscii(ch) &amp;&amp;</span><a href="#l4145"></a>
<span id="l4146">                        (inRange(lower, ASCII.toUpper(ch), upper) ||</span><a href="#l4146"></a>
<span id="l4147">                         inRange(lower, ASCII.toLower(ch), upper));</span><a href="#l4147"></a>
<span id="l4148">            }};</span><a href="#l4148"></a>
<span id="l4149">    }</span><a href="#l4149"></a>
<span id="l4150"></span><a href="#l4150"></a>
<span id="l4151">    /**</span><a href="#l4151"></a>
<span id="l4152">     * Implements the Unicode category ALL and the dot metacharacter when</span><a href="#l4152"></a>
<span id="l4153">     * in dotall mode.</span><a href="#l4153"></a>
<span id="l4154">     */</span><a href="#l4154"></a>
<span id="l4155">    static final class All extends CharProperty {</span><a href="#l4155"></a>
<span id="l4156">        boolean isSatisfiedBy(int ch) {</span><a href="#l4156"></a>
<span id="l4157">            return true;</span><a href="#l4157"></a>
<span id="l4158">        }</span><a href="#l4158"></a>
<span id="l4159">    }</span><a href="#l4159"></a>
<span id="l4160"></span><a href="#l4160"></a>
<span id="l4161">    /**</span><a href="#l4161"></a>
<span id="l4162">     * Node class for the dot metacharacter when dotall is not enabled.</span><a href="#l4162"></a>
<span id="l4163">     */</span><a href="#l4163"></a>
<span id="l4164">    static final class Dot extends CharProperty {</span><a href="#l4164"></a>
<span id="l4165">        boolean isSatisfiedBy(int ch) {</span><a href="#l4165"></a>
<span id="l4166">            return (ch != '\n' &amp;&amp; ch != '\r'</span><a href="#l4166"></a>
<span id="l4167">                    &amp;&amp; (ch|1) != '\u2029'</span><a href="#l4167"></a>
<span id="l4168">                    &amp;&amp; ch != '\u0085');</span><a href="#l4168"></a>
<span id="l4169">        }</span><a href="#l4169"></a>
<span id="l4170">    }</span><a href="#l4170"></a>
<span id="l4171"></span><a href="#l4171"></a>
<span id="l4172">    /**</span><a href="#l4172"></a>
<span id="l4173">     * Node class for the dot metacharacter when dotall is not enabled</span><a href="#l4173"></a>
<span id="l4174">     * but UNIX_LINES is enabled.</span><a href="#l4174"></a>
<span id="l4175">     */</span><a href="#l4175"></a>
<span id="l4176">    static final class UnixDot extends CharProperty {</span><a href="#l4176"></a>
<span id="l4177">        boolean isSatisfiedBy(int ch) {</span><a href="#l4177"></a>
<span id="l4178">            return ch != '\n';</span><a href="#l4178"></a>
<span id="l4179">        }</span><a href="#l4179"></a>
<span id="l4180">    }</span><a href="#l4180"></a>
<span id="l4181"></span><a href="#l4181"></a>
<span id="l4182">    /**</span><a href="#l4182"></a>
<span id="l4183">     * The 0 or 1 quantifier. This one class implements all three types.</span><a href="#l4183"></a>
<span id="l4184">     */</span><a href="#l4184"></a>
<span id="l4185">    static final class Ques extends Node {</span><a href="#l4185"></a>
<span id="l4186">        Node atom;</span><a href="#l4186"></a>
<span id="l4187">        int type;</span><a href="#l4187"></a>
<span id="l4188">        Ques(Node node, int type) {</span><a href="#l4188"></a>
<span id="l4189">            this.atom = node;</span><a href="#l4189"></a>
<span id="l4190">            this.type = type;</span><a href="#l4190"></a>
<span id="l4191">        }</span><a href="#l4191"></a>
<span id="l4192">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l4192"></a>
<span id="l4193">            switch (type) {</span><a href="#l4193"></a>
<span id="l4194">            case GREEDY:</span><a href="#l4194"></a>
<span id="l4195">                return (atom.match(matcher, i, seq) &amp;&amp; next.match(matcher, matcher.last, seq))</span><a href="#l4195"></a>
<span id="l4196">                    || next.match(matcher, i, seq);</span><a href="#l4196"></a>
<span id="l4197">            case LAZY:</span><a href="#l4197"></a>
<span id="l4198">                return next.match(matcher, i, seq)</span><a href="#l4198"></a>
<span id="l4199">                    || (atom.match(matcher, i, seq) &amp;&amp; next.match(matcher, matcher.last, seq));</span><a href="#l4199"></a>
<span id="l4200">            case POSSESSIVE:</span><a href="#l4200"></a>
<span id="l4201">                if (atom.match(matcher, i, seq)) i = matcher.last;</span><a href="#l4201"></a>
<span id="l4202">                return next.match(matcher, i, seq);</span><a href="#l4202"></a>
<span id="l4203">            default:</span><a href="#l4203"></a>
<span id="l4204">                return atom.match(matcher, i, seq) &amp;&amp; next.match(matcher, matcher.last, seq);</span><a href="#l4204"></a>
<span id="l4205">            }</span><a href="#l4205"></a>
<span id="l4206">        }</span><a href="#l4206"></a>
<span id="l4207">        boolean study(TreeInfo info) {</span><a href="#l4207"></a>
<span id="l4208">            if (type != INDEPENDENT) {</span><a href="#l4208"></a>
<span id="l4209">                int minL = info.minLength;</span><a href="#l4209"></a>
<span id="l4210">                atom.study(info);</span><a href="#l4210"></a>
<span id="l4211">                info.minLength = minL;</span><a href="#l4211"></a>
<span id="l4212">                info.deterministic = false;</span><a href="#l4212"></a>
<span id="l4213">                return next.study(info);</span><a href="#l4213"></a>
<span id="l4214">            } else {</span><a href="#l4214"></a>
<span id="l4215">                atom.study(info);</span><a href="#l4215"></a>
<span id="l4216">                return next.study(info);</span><a href="#l4216"></a>
<span id="l4217">            }</span><a href="#l4217"></a>
<span id="l4218">        }</span><a href="#l4218"></a>
<span id="l4219">    }</span><a href="#l4219"></a>
<span id="l4220"></span><a href="#l4220"></a>
<span id="l4221">    /**</span><a href="#l4221"></a>
<span id="l4222">     * Handles the curly-brace style repetition with a specified minimum and</span><a href="#l4222"></a>
<span id="l4223">     * maximum occurrences. The * quantifier is handled as a special case.</span><a href="#l4223"></a>
<span id="l4224">     * This class handles the three types.</span><a href="#l4224"></a>
<span id="l4225">     */</span><a href="#l4225"></a>
<span id="l4226">    static final class Curly extends Node {</span><a href="#l4226"></a>
<span id="l4227">        Node atom;</span><a href="#l4227"></a>
<span id="l4228">        int type;</span><a href="#l4228"></a>
<span id="l4229">        int cmin;</span><a href="#l4229"></a>
<span id="l4230">        int cmax;</span><a href="#l4230"></a>
<span id="l4231"></span><a href="#l4231"></a>
<span id="l4232">        Curly(Node node, int cmin, int cmax, int type) {</span><a href="#l4232"></a>
<span id="l4233">            this.atom = node;</span><a href="#l4233"></a>
<span id="l4234">            this.type = type;</span><a href="#l4234"></a>
<span id="l4235">            this.cmin = cmin;</span><a href="#l4235"></a>
<span id="l4236">            this.cmax = cmax;</span><a href="#l4236"></a>
<span id="l4237">        }</span><a href="#l4237"></a>
<span id="l4238">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l4238"></a>
<span id="l4239">            int j;</span><a href="#l4239"></a>
<span id="l4240">            for (j = 0; j &lt; cmin; j++) {</span><a href="#l4240"></a>
<span id="l4241">                if (atom.match(matcher, i, seq)) {</span><a href="#l4241"></a>
<span id="l4242">                    i = matcher.last;</span><a href="#l4242"></a>
<span id="l4243">                    continue;</span><a href="#l4243"></a>
<span id="l4244">                }</span><a href="#l4244"></a>
<span id="l4245">                return false;</span><a href="#l4245"></a>
<span id="l4246">            }</span><a href="#l4246"></a>
<span id="l4247">            if (type == GREEDY)</span><a href="#l4247"></a>
<span id="l4248">                return match0(matcher, i, j, seq);</span><a href="#l4248"></a>
<span id="l4249">            else if (type == LAZY)</span><a href="#l4249"></a>
<span id="l4250">                return match1(matcher, i, j, seq);</span><a href="#l4250"></a>
<span id="l4251">            else</span><a href="#l4251"></a>
<span id="l4252">                return match2(matcher, i, j, seq);</span><a href="#l4252"></a>
<span id="l4253">        }</span><a href="#l4253"></a>
<span id="l4254">        // Greedy match.</span><a href="#l4254"></a>
<span id="l4255">        // i is the index to start matching at</span><a href="#l4255"></a>
<span id="l4256">        // j is the number of atoms that have matched</span><a href="#l4256"></a>
<span id="l4257">        boolean match0(Matcher matcher, int i, int j, CharSequence seq) {</span><a href="#l4257"></a>
<span id="l4258">            if (j &gt;= cmax) {</span><a href="#l4258"></a>
<span id="l4259">                // We have matched the maximum... continue with the rest of</span><a href="#l4259"></a>
<span id="l4260">                // the regular expression</span><a href="#l4260"></a>
<span id="l4261">                return next.match(matcher, i, seq);</span><a href="#l4261"></a>
<span id="l4262">            }</span><a href="#l4262"></a>
<span id="l4263">            int backLimit = j;</span><a href="#l4263"></a>
<span id="l4264">            while (atom.match(matcher, i, seq)) {</span><a href="#l4264"></a>
<span id="l4265">                // k is the length of this match</span><a href="#l4265"></a>
<span id="l4266">                int k = matcher.last - i;</span><a href="#l4266"></a>
<span id="l4267">                if (k == 0) // Zero length match</span><a href="#l4267"></a>
<span id="l4268">                    break;</span><a href="#l4268"></a>
<span id="l4269">                // Move up index and number matched</span><a href="#l4269"></a>
<span id="l4270">                i = matcher.last;</span><a href="#l4270"></a>
<span id="l4271">                j++;</span><a href="#l4271"></a>
<span id="l4272">                // We are greedy so match as many as we can</span><a href="#l4272"></a>
<span id="l4273">                while (j &lt; cmax) {</span><a href="#l4273"></a>
<span id="l4274">                    if (!atom.match(matcher, i, seq))</span><a href="#l4274"></a>
<span id="l4275">                        break;</span><a href="#l4275"></a>
<span id="l4276">                    if (i + k != matcher.last) {</span><a href="#l4276"></a>
<span id="l4277">                        if (match0(matcher, matcher.last, j+1, seq))</span><a href="#l4277"></a>
<span id="l4278">                            return true;</span><a href="#l4278"></a>
<span id="l4279">                        break;</span><a href="#l4279"></a>
<span id="l4280">                    }</span><a href="#l4280"></a>
<span id="l4281">                    i += k;</span><a href="#l4281"></a>
<span id="l4282">                    j++;</span><a href="#l4282"></a>
<span id="l4283">                }</span><a href="#l4283"></a>
<span id="l4284">                // Handle backing off if match fails</span><a href="#l4284"></a>
<span id="l4285">                while (j &gt;= backLimit) {</span><a href="#l4285"></a>
<span id="l4286">                   if (next.match(matcher, i, seq))</span><a href="#l4286"></a>
<span id="l4287">                        return true;</span><a href="#l4287"></a>
<span id="l4288">                    i -= k;</span><a href="#l4288"></a>
<span id="l4289">                    j--;</span><a href="#l4289"></a>
<span id="l4290">                }</span><a href="#l4290"></a>
<span id="l4291">                return false;</span><a href="#l4291"></a>
<span id="l4292">            }</span><a href="#l4292"></a>
<span id="l4293">            return next.match(matcher, i, seq);</span><a href="#l4293"></a>
<span id="l4294">        }</span><a href="#l4294"></a>
<span id="l4295">        // Reluctant match. At this point, the minimum has been satisfied.</span><a href="#l4295"></a>
<span id="l4296">        // i is the index to start matching at</span><a href="#l4296"></a>
<span id="l4297">        // j is the number of atoms that have matched</span><a href="#l4297"></a>
<span id="l4298">        boolean match1(Matcher matcher, int i, int j, CharSequence seq) {</span><a href="#l4298"></a>
<span id="l4299">            for (;;) {</span><a href="#l4299"></a>
<span id="l4300">                // Try finishing match without consuming any more</span><a href="#l4300"></a>
<span id="l4301">                if (next.match(matcher, i, seq))</span><a href="#l4301"></a>
<span id="l4302">                    return true;</span><a href="#l4302"></a>
<span id="l4303">                // At the maximum, no match found</span><a href="#l4303"></a>
<span id="l4304">                if (j &gt;= cmax)</span><a href="#l4304"></a>
<span id="l4305">                    return false;</span><a href="#l4305"></a>
<span id="l4306">                // Okay, must try one more atom</span><a href="#l4306"></a>
<span id="l4307">                if (!atom.match(matcher, i, seq))</span><a href="#l4307"></a>
<span id="l4308">                    return false;</span><a href="#l4308"></a>
<span id="l4309">                // If we haven't moved forward then must break out</span><a href="#l4309"></a>
<span id="l4310">                if (i == matcher.last)</span><a href="#l4310"></a>
<span id="l4311">                    return false;</span><a href="#l4311"></a>
<span id="l4312">                // Move up index and number matched</span><a href="#l4312"></a>
<span id="l4313">                i = matcher.last;</span><a href="#l4313"></a>
<span id="l4314">                j++;</span><a href="#l4314"></a>
<span id="l4315">            }</span><a href="#l4315"></a>
<span id="l4316">        }</span><a href="#l4316"></a>
<span id="l4317">        boolean match2(Matcher matcher, int i, int j, CharSequence seq) {</span><a href="#l4317"></a>
<span id="l4318">            for (; j &lt; cmax; j++) {</span><a href="#l4318"></a>
<span id="l4319">                if (!atom.match(matcher, i, seq))</span><a href="#l4319"></a>
<span id="l4320">                    break;</span><a href="#l4320"></a>
<span id="l4321">                if (i == matcher.last)</span><a href="#l4321"></a>
<span id="l4322">                    break;</span><a href="#l4322"></a>
<span id="l4323">                i = matcher.last;</span><a href="#l4323"></a>
<span id="l4324">            }</span><a href="#l4324"></a>
<span id="l4325">            return next.match(matcher, i, seq);</span><a href="#l4325"></a>
<span id="l4326">        }</span><a href="#l4326"></a>
<span id="l4327">        boolean study(TreeInfo info) {</span><a href="#l4327"></a>
<span id="l4328">            // Save original info</span><a href="#l4328"></a>
<span id="l4329">            int minL = info.minLength;</span><a href="#l4329"></a>
<span id="l4330">            int maxL = info.maxLength;</span><a href="#l4330"></a>
<span id="l4331">            boolean maxV = info.maxValid;</span><a href="#l4331"></a>
<span id="l4332">            boolean detm = info.deterministic;</span><a href="#l4332"></a>
<span id="l4333">            info.reset();</span><a href="#l4333"></a>
<span id="l4334"></span><a href="#l4334"></a>
<span id="l4335">            atom.study(info);</span><a href="#l4335"></a>
<span id="l4336"></span><a href="#l4336"></a>
<span id="l4337">            int temp = info.minLength * cmin + minL;</span><a href="#l4337"></a>
<span id="l4338">            if (temp &lt; minL) {</span><a href="#l4338"></a>
<span id="l4339">                temp = 0xFFFFFFF; // arbitrary large number</span><a href="#l4339"></a>
<span id="l4340">            }</span><a href="#l4340"></a>
<span id="l4341">            info.minLength = temp;</span><a href="#l4341"></a>
<span id="l4342"></span><a href="#l4342"></a>
<span id="l4343">            if (maxV &amp; info.maxValid) {</span><a href="#l4343"></a>
<span id="l4344">                temp = info.maxLength * cmax + maxL;</span><a href="#l4344"></a>
<span id="l4345">                info.maxLength = temp;</span><a href="#l4345"></a>
<span id="l4346">                if (temp &lt; maxL) {</span><a href="#l4346"></a>
<span id="l4347">                    info.maxValid = false;</span><a href="#l4347"></a>
<span id="l4348">                }</span><a href="#l4348"></a>
<span id="l4349">            } else {</span><a href="#l4349"></a>
<span id="l4350">                info.maxValid = false;</span><a href="#l4350"></a>
<span id="l4351">            }</span><a href="#l4351"></a>
<span id="l4352"></span><a href="#l4352"></a>
<span id="l4353">            if (info.deterministic &amp;&amp; cmin == cmax)</span><a href="#l4353"></a>
<span id="l4354">                info.deterministic = detm;</span><a href="#l4354"></a>
<span id="l4355">            else</span><a href="#l4355"></a>
<span id="l4356">                info.deterministic = false;</span><a href="#l4356"></a>
<span id="l4357">            return next.study(info);</span><a href="#l4357"></a>
<span id="l4358">        }</span><a href="#l4358"></a>
<span id="l4359">    }</span><a href="#l4359"></a>
<span id="l4360"></span><a href="#l4360"></a>
<span id="l4361">    /**</span><a href="#l4361"></a>
<span id="l4362">     * Handles the curly-brace style repetition with a specified minimum and</span><a href="#l4362"></a>
<span id="l4363">     * maximum occurrences in deterministic cases. This is an iterative</span><a href="#l4363"></a>
<span id="l4364">     * optimization over the Prolog and Loop system which would handle this</span><a href="#l4364"></a>
<span id="l4365">     * in a recursive way. The * quantifier is handled as a special case.</span><a href="#l4365"></a>
<span id="l4366">     * If capture is true then this class saves group settings and ensures</span><a href="#l4366"></a>
<span id="l4367">     * that groups are unset when backing off of a group match.</span><a href="#l4367"></a>
<span id="l4368">     */</span><a href="#l4368"></a>
<span id="l4369">    static final class GroupCurly extends Node {</span><a href="#l4369"></a>
<span id="l4370">        Node atom;</span><a href="#l4370"></a>
<span id="l4371">        int type;</span><a href="#l4371"></a>
<span id="l4372">        int cmin;</span><a href="#l4372"></a>
<span id="l4373">        int cmax;</span><a href="#l4373"></a>
<span id="l4374">        int localIndex;</span><a href="#l4374"></a>
<span id="l4375">        int groupIndex;</span><a href="#l4375"></a>
<span id="l4376">        boolean capture;</span><a href="#l4376"></a>
<span id="l4377"></span><a href="#l4377"></a>
<span id="l4378">        GroupCurly(Node node, int cmin, int cmax, int type, int local,</span><a href="#l4378"></a>
<span id="l4379">                   int group, boolean capture) {</span><a href="#l4379"></a>
<span id="l4380">            this.atom = node;</span><a href="#l4380"></a>
<span id="l4381">            this.type = type;</span><a href="#l4381"></a>
<span id="l4382">            this.cmin = cmin;</span><a href="#l4382"></a>
<span id="l4383">            this.cmax = cmax;</span><a href="#l4383"></a>
<span id="l4384">            this.localIndex = local;</span><a href="#l4384"></a>
<span id="l4385">            this.groupIndex = group;</span><a href="#l4385"></a>
<span id="l4386">            this.capture = capture;</span><a href="#l4386"></a>
<span id="l4387">        }</span><a href="#l4387"></a>
<span id="l4388">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l4388"></a>
<span id="l4389">            int[] groups = matcher.groups;</span><a href="#l4389"></a>
<span id="l4390">            int[] locals = matcher.locals;</span><a href="#l4390"></a>
<span id="l4391">            int save0 = locals[localIndex];</span><a href="#l4391"></a>
<span id="l4392">            int save1 = 0;</span><a href="#l4392"></a>
<span id="l4393">            int save2 = 0;</span><a href="#l4393"></a>
<span id="l4394"></span><a href="#l4394"></a>
<span id="l4395">            if (capture) {</span><a href="#l4395"></a>
<span id="l4396">                save1 = groups[groupIndex];</span><a href="#l4396"></a>
<span id="l4397">                save2 = groups[groupIndex+1];</span><a href="#l4397"></a>
<span id="l4398">            }</span><a href="#l4398"></a>
<span id="l4399"></span><a href="#l4399"></a>
<span id="l4400">            // Notify GroupTail there is no need to setup group info</span><a href="#l4400"></a>
<span id="l4401">            // because it will be set here</span><a href="#l4401"></a>
<span id="l4402">            locals[localIndex] = -1;</span><a href="#l4402"></a>
<span id="l4403"></span><a href="#l4403"></a>
<span id="l4404">            boolean ret = true;</span><a href="#l4404"></a>
<span id="l4405">            for (int j = 0; j &lt; cmin; j++) {</span><a href="#l4405"></a>
<span id="l4406">                if (atom.match(matcher, i, seq)) {</span><a href="#l4406"></a>
<span id="l4407">                    if (capture) {</span><a href="#l4407"></a>
<span id="l4408">                        groups[groupIndex] = i;</span><a href="#l4408"></a>
<span id="l4409">                        groups[groupIndex+1] = matcher.last;</span><a href="#l4409"></a>
<span id="l4410">                    }</span><a href="#l4410"></a>
<span id="l4411">                    i = matcher.last;</span><a href="#l4411"></a>
<span id="l4412">                } else {</span><a href="#l4412"></a>
<span id="l4413">                    ret = false;</span><a href="#l4413"></a>
<span id="l4414">                    break;</span><a href="#l4414"></a>
<span id="l4415">                }</span><a href="#l4415"></a>
<span id="l4416">            }</span><a href="#l4416"></a>
<span id="l4417">            if (ret) {</span><a href="#l4417"></a>
<span id="l4418">                if (type == GREEDY) {</span><a href="#l4418"></a>
<span id="l4419">                    ret = match0(matcher, i, cmin, seq);</span><a href="#l4419"></a>
<span id="l4420">                } else if (type == LAZY) {</span><a href="#l4420"></a>
<span id="l4421">                    ret = match1(matcher, i, cmin, seq);</span><a href="#l4421"></a>
<span id="l4422">                } else {</span><a href="#l4422"></a>
<span id="l4423">                    ret = match2(matcher, i, cmin, seq);</span><a href="#l4423"></a>
<span id="l4424">                }</span><a href="#l4424"></a>
<span id="l4425">            }</span><a href="#l4425"></a>
<span id="l4426">            if (!ret) {</span><a href="#l4426"></a>
<span id="l4427">                locals[localIndex] = save0;</span><a href="#l4427"></a>
<span id="l4428">                if (capture) {</span><a href="#l4428"></a>
<span id="l4429">                    groups[groupIndex] = save1;</span><a href="#l4429"></a>
<span id="l4430">                    groups[groupIndex+1] = save2;</span><a href="#l4430"></a>
<span id="l4431">                }</span><a href="#l4431"></a>
<span id="l4432">            }</span><a href="#l4432"></a>
<span id="l4433">            return ret;</span><a href="#l4433"></a>
<span id="l4434">        }</span><a href="#l4434"></a>
<span id="l4435">        // Aggressive group match</span><a href="#l4435"></a>
<span id="l4436">        boolean match0(Matcher matcher, int i, int j, CharSequence seq) {</span><a href="#l4436"></a>
<span id="l4437">            // don't back off passing the starting &quot;j&quot;</span><a href="#l4437"></a>
<span id="l4438">            int min = j;</span><a href="#l4438"></a>
<span id="l4439">            int[] groups = matcher.groups;</span><a href="#l4439"></a>
<span id="l4440">            int save0 = 0;</span><a href="#l4440"></a>
<span id="l4441">            int save1 = 0;</span><a href="#l4441"></a>
<span id="l4442">            if (capture) {</span><a href="#l4442"></a>
<span id="l4443">                save0 = groups[groupIndex];</span><a href="#l4443"></a>
<span id="l4444">                save1 = groups[groupIndex+1];</span><a href="#l4444"></a>
<span id="l4445">            }</span><a href="#l4445"></a>
<span id="l4446">            for (;;) {</span><a href="#l4446"></a>
<span id="l4447">                if (j &gt;= cmax)</span><a href="#l4447"></a>
<span id="l4448">                    break;</span><a href="#l4448"></a>
<span id="l4449">                if (!atom.match(matcher, i, seq))</span><a href="#l4449"></a>
<span id="l4450">                    break;</span><a href="#l4450"></a>
<span id="l4451">                int k = matcher.last - i;</span><a href="#l4451"></a>
<span id="l4452">                if (k &lt;= 0) {</span><a href="#l4452"></a>
<span id="l4453">                    if (capture) {</span><a href="#l4453"></a>
<span id="l4454">                        groups[groupIndex] = i;</span><a href="#l4454"></a>
<span id="l4455">                        groups[groupIndex+1] = i + k;</span><a href="#l4455"></a>
<span id="l4456">                    }</span><a href="#l4456"></a>
<span id="l4457">                    i = i + k;</span><a href="#l4457"></a>
<span id="l4458">                    break;</span><a href="#l4458"></a>
<span id="l4459">                }</span><a href="#l4459"></a>
<span id="l4460">                for (;;) {</span><a href="#l4460"></a>
<span id="l4461">                    if (capture) {</span><a href="#l4461"></a>
<span id="l4462">                        groups[groupIndex] = i;</span><a href="#l4462"></a>
<span id="l4463">                        groups[groupIndex+1] = i + k;</span><a href="#l4463"></a>
<span id="l4464">                    }</span><a href="#l4464"></a>
<span id="l4465">                    i = i + k;</span><a href="#l4465"></a>
<span id="l4466">                    if (++j &gt;= cmax)</span><a href="#l4466"></a>
<span id="l4467">                        break;</span><a href="#l4467"></a>
<span id="l4468">                    if (!atom.match(matcher, i, seq))</span><a href="#l4468"></a>
<span id="l4469">                        break;</span><a href="#l4469"></a>
<span id="l4470">                    if (i + k != matcher.last) {</span><a href="#l4470"></a>
<span id="l4471">                        if (match0(matcher, i, j, seq))</span><a href="#l4471"></a>
<span id="l4472">                            return true;</span><a href="#l4472"></a>
<span id="l4473">                        break;</span><a href="#l4473"></a>
<span id="l4474">                    }</span><a href="#l4474"></a>
<span id="l4475">                }</span><a href="#l4475"></a>
<span id="l4476">                while (j &gt; min) {</span><a href="#l4476"></a>
<span id="l4477">                    if (next.match(matcher, i, seq)) {</span><a href="#l4477"></a>
<span id="l4478">                        if (capture) {</span><a href="#l4478"></a>
<span id="l4479">                            groups[groupIndex+1] = i;</span><a href="#l4479"></a>
<span id="l4480">                            groups[groupIndex] = i - k;</span><a href="#l4480"></a>
<span id="l4481">                        }</span><a href="#l4481"></a>
<span id="l4482">                        return true;</span><a href="#l4482"></a>
<span id="l4483">                    }</span><a href="#l4483"></a>
<span id="l4484">                    // backing off</span><a href="#l4484"></a>
<span id="l4485">                    i = i - k;</span><a href="#l4485"></a>
<span id="l4486">                    if (capture) {</span><a href="#l4486"></a>
<span id="l4487">                        groups[groupIndex+1] = i;</span><a href="#l4487"></a>
<span id="l4488">                        groups[groupIndex] = i - k;</span><a href="#l4488"></a>
<span id="l4489">                    }</span><a href="#l4489"></a>
<span id="l4490">                    j--;</span><a href="#l4490"></a>
<span id="l4491"></span><a href="#l4491"></a>
<span id="l4492">                }</span><a href="#l4492"></a>
<span id="l4493">                break;</span><a href="#l4493"></a>
<span id="l4494">            }</span><a href="#l4494"></a>
<span id="l4495">            if (capture) {</span><a href="#l4495"></a>
<span id="l4496">                groups[groupIndex] = save0;</span><a href="#l4496"></a>
<span id="l4497">                groups[groupIndex+1] = save1;</span><a href="#l4497"></a>
<span id="l4498">            }</span><a href="#l4498"></a>
<span id="l4499">            return next.match(matcher, i, seq);</span><a href="#l4499"></a>
<span id="l4500">        }</span><a href="#l4500"></a>
<span id="l4501">        // Reluctant matching</span><a href="#l4501"></a>
<span id="l4502">        boolean match1(Matcher matcher, int i, int j, CharSequence seq) {</span><a href="#l4502"></a>
<span id="l4503">            for (;;) {</span><a href="#l4503"></a>
<span id="l4504">                if (next.match(matcher, i, seq))</span><a href="#l4504"></a>
<span id="l4505">                    return true;</span><a href="#l4505"></a>
<span id="l4506">                if (j &gt;= cmax)</span><a href="#l4506"></a>
<span id="l4507">                    return false;</span><a href="#l4507"></a>
<span id="l4508">                if (!atom.match(matcher, i, seq))</span><a href="#l4508"></a>
<span id="l4509">                    return false;</span><a href="#l4509"></a>
<span id="l4510">                if (i == matcher.last)</span><a href="#l4510"></a>
<span id="l4511">                    return false;</span><a href="#l4511"></a>
<span id="l4512">                if (capture) {</span><a href="#l4512"></a>
<span id="l4513">                    matcher.groups[groupIndex] = i;</span><a href="#l4513"></a>
<span id="l4514">                    matcher.groups[groupIndex+1] = matcher.last;</span><a href="#l4514"></a>
<span id="l4515">                }</span><a href="#l4515"></a>
<span id="l4516">                i = matcher.last;</span><a href="#l4516"></a>
<span id="l4517">                j++;</span><a href="#l4517"></a>
<span id="l4518">            }</span><a href="#l4518"></a>
<span id="l4519">        }</span><a href="#l4519"></a>
<span id="l4520">        // Possessive matching</span><a href="#l4520"></a>
<span id="l4521">        boolean match2(Matcher matcher, int i, int j, CharSequence seq) {</span><a href="#l4521"></a>
<span id="l4522">            for (; j &lt; cmax; j++) {</span><a href="#l4522"></a>
<span id="l4523">                if (!atom.match(matcher, i, seq)) {</span><a href="#l4523"></a>
<span id="l4524">                    break;</span><a href="#l4524"></a>
<span id="l4525">                }</span><a href="#l4525"></a>
<span id="l4526">                if (capture) {</span><a href="#l4526"></a>
<span id="l4527">                    matcher.groups[groupIndex] = i;</span><a href="#l4527"></a>
<span id="l4528">                    matcher.groups[groupIndex+1] = matcher.last;</span><a href="#l4528"></a>
<span id="l4529">                }</span><a href="#l4529"></a>
<span id="l4530">                if (i == matcher.last) {</span><a href="#l4530"></a>
<span id="l4531">                    break;</span><a href="#l4531"></a>
<span id="l4532">                }</span><a href="#l4532"></a>
<span id="l4533">                i = matcher.last;</span><a href="#l4533"></a>
<span id="l4534">            }</span><a href="#l4534"></a>
<span id="l4535">            return next.match(matcher, i, seq);</span><a href="#l4535"></a>
<span id="l4536">        }</span><a href="#l4536"></a>
<span id="l4537">        boolean study(TreeInfo info) {</span><a href="#l4537"></a>
<span id="l4538">            // Save original info</span><a href="#l4538"></a>
<span id="l4539">            int minL = info.minLength;</span><a href="#l4539"></a>
<span id="l4540">            int maxL = info.maxLength;</span><a href="#l4540"></a>
<span id="l4541">            boolean maxV = info.maxValid;</span><a href="#l4541"></a>
<span id="l4542">            boolean detm = info.deterministic;</span><a href="#l4542"></a>
<span id="l4543">            info.reset();</span><a href="#l4543"></a>
<span id="l4544"></span><a href="#l4544"></a>
<span id="l4545">            atom.study(info);</span><a href="#l4545"></a>
<span id="l4546"></span><a href="#l4546"></a>
<span id="l4547">            int temp = info.minLength * cmin + minL;</span><a href="#l4547"></a>
<span id="l4548">            if (temp &lt; minL) {</span><a href="#l4548"></a>
<span id="l4549">                temp = 0xFFFFFFF; // Arbitrary large number</span><a href="#l4549"></a>
<span id="l4550">            }</span><a href="#l4550"></a>
<span id="l4551">            info.minLength = temp;</span><a href="#l4551"></a>
<span id="l4552"></span><a href="#l4552"></a>
<span id="l4553">            if (maxV &amp; info.maxValid) {</span><a href="#l4553"></a>
<span id="l4554">                temp = info.maxLength * cmax + maxL;</span><a href="#l4554"></a>
<span id="l4555">                info.maxLength = temp;</span><a href="#l4555"></a>
<span id="l4556">                if (temp &lt; maxL) {</span><a href="#l4556"></a>
<span id="l4557">                    info.maxValid = false;</span><a href="#l4557"></a>
<span id="l4558">                }</span><a href="#l4558"></a>
<span id="l4559">            } else {</span><a href="#l4559"></a>
<span id="l4560">                info.maxValid = false;</span><a href="#l4560"></a>
<span id="l4561">            }</span><a href="#l4561"></a>
<span id="l4562"></span><a href="#l4562"></a>
<span id="l4563">            if (info.deterministic &amp;&amp; cmin == cmax) {</span><a href="#l4563"></a>
<span id="l4564">                info.deterministic = detm;</span><a href="#l4564"></a>
<span id="l4565">            } else {</span><a href="#l4565"></a>
<span id="l4566">                info.deterministic = false;</span><a href="#l4566"></a>
<span id="l4567">            }</span><a href="#l4567"></a>
<span id="l4568">            return next.study(info);</span><a href="#l4568"></a>
<span id="l4569">        }</span><a href="#l4569"></a>
<span id="l4570">    }</span><a href="#l4570"></a>
<span id="l4571"></span><a href="#l4571"></a>
<span id="l4572">    /**</span><a href="#l4572"></a>
<span id="l4573">     * A Guard node at the end of each atom node in a Branch. It</span><a href="#l4573"></a>
<span id="l4574">     * serves the purpose of chaining the &quot;match&quot; operation to</span><a href="#l4574"></a>
<span id="l4575">     * &quot;next&quot; but not the &quot;study&quot;, so we can collect the TreeInfo</span><a href="#l4575"></a>
<span id="l4576">     * of each atom node without including the TreeInfo of the</span><a href="#l4576"></a>
<span id="l4577">     * &quot;next&quot;.</span><a href="#l4577"></a>
<span id="l4578">     */</span><a href="#l4578"></a>
<span id="l4579">    static final class BranchConn extends Node {</span><a href="#l4579"></a>
<span id="l4580">        BranchConn() {};</span><a href="#l4580"></a>
<span id="l4581">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l4581"></a>
<span id="l4582">            return next.match(matcher, i, seq);</span><a href="#l4582"></a>
<span id="l4583">        }</span><a href="#l4583"></a>
<span id="l4584">        boolean study(TreeInfo info) {</span><a href="#l4584"></a>
<span id="l4585">            return info.deterministic;</span><a href="#l4585"></a>
<span id="l4586">        }</span><a href="#l4586"></a>
<span id="l4587">    }</span><a href="#l4587"></a>
<span id="l4588"></span><a href="#l4588"></a>
<span id="l4589">    /**</span><a href="#l4589"></a>
<span id="l4590">     * Handles the branching of alternations. Note this is also used for</span><a href="#l4590"></a>
<span id="l4591">     * the ? quantifier to branch between the case where it matches once</span><a href="#l4591"></a>
<span id="l4592">     * and where it does not occur.</span><a href="#l4592"></a>
<span id="l4593">     */</span><a href="#l4593"></a>
<span id="l4594">    static final class Branch extends Node {</span><a href="#l4594"></a>
<span id="l4595">        Node[] atoms = new Node[2];</span><a href="#l4595"></a>
<span id="l4596">        int size = 2;</span><a href="#l4596"></a>
<span id="l4597">        Node conn;</span><a href="#l4597"></a>
<span id="l4598">        Branch(Node first, Node second, Node branchConn) {</span><a href="#l4598"></a>
<span id="l4599">            conn = branchConn;</span><a href="#l4599"></a>
<span id="l4600">            atoms[0] = first;</span><a href="#l4600"></a>
<span id="l4601">            atoms[1] = second;</span><a href="#l4601"></a>
<span id="l4602">        }</span><a href="#l4602"></a>
<span id="l4603"></span><a href="#l4603"></a>
<span id="l4604">        void add(Node node) {</span><a href="#l4604"></a>
<span id="l4605">            if (size &gt;= atoms.length) {</span><a href="#l4605"></a>
<span id="l4606">                Node[] tmp = new Node[atoms.length*2];</span><a href="#l4606"></a>
<span id="l4607">                System.arraycopy(atoms, 0, tmp, 0, atoms.length);</span><a href="#l4607"></a>
<span id="l4608">                atoms = tmp;</span><a href="#l4608"></a>
<span id="l4609">            }</span><a href="#l4609"></a>
<span id="l4610">            atoms[size++] = node;</span><a href="#l4610"></a>
<span id="l4611">        }</span><a href="#l4611"></a>
<span id="l4612"></span><a href="#l4612"></a>
<span id="l4613">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l4613"></a>
<span id="l4614">            for (int n = 0; n &lt; size; n++) {</span><a href="#l4614"></a>
<span id="l4615">                if (atoms[n] == null) {</span><a href="#l4615"></a>
<span id="l4616">                    if (conn.next.match(matcher, i, seq))</span><a href="#l4616"></a>
<span id="l4617">                        return true;</span><a href="#l4617"></a>
<span id="l4618">                } else if (atoms[n].match(matcher, i, seq)) {</span><a href="#l4618"></a>
<span id="l4619">                    return true;</span><a href="#l4619"></a>
<span id="l4620">                }</span><a href="#l4620"></a>
<span id="l4621">            }</span><a href="#l4621"></a>
<span id="l4622">            return false;</span><a href="#l4622"></a>
<span id="l4623">        }</span><a href="#l4623"></a>
<span id="l4624"></span><a href="#l4624"></a>
<span id="l4625">        boolean study(TreeInfo info) {</span><a href="#l4625"></a>
<span id="l4626">            int minL = info.minLength;</span><a href="#l4626"></a>
<span id="l4627">            int maxL = info.maxLength;</span><a href="#l4627"></a>
<span id="l4628">            boolean maxV = info.maxValid;</span><a href="#l4628"></a>
<span id="l4629"></span><a href="#l4629"></a>
<span id="l4630">            int minL2 = Integer.MAX_VALUE; //arbitrary large enough num</span><a href="#l4630"></a>
<span id="l4631">            int maxL2 = -1;</span><a href="#l4631"></a>
<span id="l4632">            for (int n = 0; n &lt; size; n++) {</span><a href="#l4632"></a>
<span id="l4633">                info.reset();</span><a href="#l4633"></a>
<span id="l4634">                if (atoms[n] != null)</span><a href="#l4634"></a>
<span id="l4635">                    atoms[n].study(info);</span><a href="#l4635"></a>
<span id="l4636">                minL2 = Math.min(minL2, info.minLength);</span><a href="#l4636"></a>
<span id="l4637">                maxL2 = Math.max(maxL2, info.maxLength);</span><a href="#l4637"></a>
<span id="l4638">                maxV = (maxV &amp; info.maxValid);</span><a href="#l4638"></a>
<span id="l4639">            }</span><a href="#l4639"></a>
<span id="l4640"></span><a href="#l4640"></a>
<span id="l4641">            minL += minL2;</span><a href="#l4641"></a>
<span id="l4642">            maxL += maxL2;</span><a href="#l4642"></a>
<span id="l4643"></span><a href="#l4643"></a>
<span id="l4644">            info.reset();</span><a href="#l4644"></a>
<span id="l4645">            conn.next.study(info);</span><a href="#l4645"></a>
<span id="l4646"></span><a href="#l4646"></a>
<span id="l4647">            info.minLength += minL;</span><a href="#l4647"></a>
<span id="l4648">            info.maxLength += maxL;</span><a href="#l4648"></a>
<span id="l4649">            info.maxValid &amp;= maxV;</span><a href="#l4649"></a>
<span id="l4650">            info.deterministic = false;</span><a href="#l4650"></a>
<span id="l4651">            return false;</span><a href="#l4651"></a>
<span id="l4652">        }</span><a href="#l4652"></a>
<span id="l4653">    }</span><a href="#l4653"></a>
<span id="l4654"></span><a href="#l4654"></a>
<span id="l4655">    /**</span><a href="#l4655"></a>
<span id="l4656">     * The GroupHead saves the location where the group begins in the locals</span><a href="#l4656"></a>
<span id="l4657">     * and restores them when the match is done.</span><a href="#l4657"></a>
<span id="l4658">     *</span><a href="#l4658"></a>
<span id="l4659">     * The matchRef is used when a reference to this group is accessed later</span><a href="#l4659"></a>
<span id="l4660">     * in the expression. The locals will have a negative value in them to</span><a href="#l4660"></a>
<span id="l4661">     * indicate that we do not want to unset the group if the reference</span><a href="#l4661"></a>
<span id="l4662">     * doesn't match.</span><a href="#l4662"></a>
<span id="l4663">     */</span><a href="#l4663"></a>
<span id="l4664">    static final class GroupHead extends Node {</span><a href="#l4664"></a>
<span id="l4665">        int localIndex;</span><a href="#l4665"></a>
<span id="l4666">        GroupHead(int localCount) {</span><a href="#l4666"></a>
<span id="l4667">            localIndex = localCount;</span><a href="#l4667"></a>
<span id="l4668">        }</span><a href="#l4668"></a>
<span id="l4669">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l4669"></a>
<span id="l4670">            int save = matcher.locals[localIndex];</span><a href="#l4670"></a>
<span id="l4671">            matcher.locals[localIndex] = i;</span><a href="#l4671"></a>
<span id="l4672">            boolean ret = next.match(matcher, i, seq);</span><a href="#l4672"></a>
<span id="l4673">            matcher.locals[localIndex] = save;</span><a href="#l4673"></a>
<span id="l4674">            return ret;</span><a href="#l4674"></a>
<span id="l4675">        }</span><a href="#l4675"></a>
<span id="l4676">        boolean matchRef(Matcher matcher, int i, CharSequence seq) {</span><a href="#l4676"></a>
<span id="l4677">            int save = matcher.locals[localIndex];</span><a href="#l4677"></a>
<span id="l4678">            matcher.locals[localIndex] = ~i; // HACK</span><a href="#l4678"></a>
<span id="l4679">            boolean ret = next.match(matcher, i, seq);</span><a href="#l4679"></a>
<span id="l4680">            matcher.locals[localIndex] = save;</span><a href="#l4680"></a>
<span id="l4681">            return ret;</span><a href="#l4681"></a>
<span id="l4682">        }</span><a href="#l4682"></a>
<span id="l4683">    }</span><a href="#l4683"></a>
<span id="l4684"></span><a href="#l4684"></a>
<span id="l4685">    /**</span><a href="#l4685"></a>
<span id="l4686">     * Recursive reference to a group in the regular expression. It calls</span><a href="#l4686"></a>
<span id="l4687">     * matchRef because if the reference fails to match we would not unset</span><a href="#l4687"></a>
<span id="l4688">     * the group.</span><a href="#l4688"></a>
<span id="l4689">     */</span><a href="#l4689"></a>
<span id="l4690">    static final class GroupRef extends Node {</span><a href="#l4690"></a>
<span id="l4691">        GroupHead head;</span><a href="#l4691"></a>
<span id="l4692">        GroupRef(GroupHead head) {</span><a href="#l4692"></a>
<span id="l4693">            this.head = head;</span><a href="#l4693"></a>
<span id="l4694">        }</span><a href="#l4694"></a>
<span id="l4695">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l4695"></a>
<span id="l4696">            return head.matchRef(matcher, i, seq)</span><a href="#l4696"></a>
<span id="l4697">                &amp;&amp; next.match(matcher, matcher.last, seq);</span><a href="#l4697"></a>
<span id="l4698">        }</span><a href="#l4698"></a>
<span id="l4699">        boolean study(TreeInfo info) {</span><a href="#l4699"></a>
<span id="l4700">            info.maxValid = false;</span><a href="#l4700"></a>
<span id="l4701">            info.deterministic = false;</span><a href="#l4701"></a>
<span id="l4702">            return next.study(info);</span><a href="#l4702"></a>
<span id="l4703">        }</span><a href="#l4703"></a>
<span id="l4704">    }</span><a href="#l4704"></a>
<span id="l4705"></span><a href="#l4705"></a>
<span id="l4706">    /**</span><a href="#l4706"></a>
<span id="l4707">     * The GroupTail handles the setting of group beginning and ending</span><a href="#l4707"></a>
<span id="l4708">     * locations when groups are successfully matched. It must also be able to</span><a href="#l4708"></a>
<span id="l4709">     * unset groups that have to be backed off of.</span><a href="#l4709"></a>
<span id="l4710">     *</span><a href="#l4710"></a>
<span id="l4711">     * The GroupTail node is also used when a previous group is referenced,</span><a href="#l4711"></a>
<span id="l4712">     * and in that case no group information needs to be set.</span><a href="#l4712"></a>
<span id="l4713">     */</span><a href="#l4713"></a>
<span id="l4714">    static final class GroupTail extends Node {</span><a href="#l4714"></a>
<span id="l4715">        int localIndex;</span><a href="#l4715"></a>
<span id="l4716">        int groupIndex;</span><a href="#l4716"></a>
<span id="l4717">        GroupTail(int localCount, int groupCount) {</span><a href="#l4717"></a>
<span id="l4718">            localIndex = localCount;</span><a href="#l4718"></a>
<span id="l4719">            groupIndex = groupCount + groupCount;</span><a href="#l4719"></a>
<span id="l4720">        }</span><a href="#l4720"></a>
<span id="l4721">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l4721"></a>
<span id="l4722">            int tmp = matcher.locals[localIndex];</span><a href="#l4722"></a>
<span id="l4723">            if (tmp &gt;= 0) { // This is the normal group case.</span><a href="#l4723"></a>
<span id="l4724">                // Save the group so we can unset it if it</span><a href="#l4724"></a>
<span id="l4725">                // backs off of a match.</span><a href="#l4725"></a>
<span id="l4726">                int groupStart = matcher.groups[groupIndex];</span><a href="#l4726"></a>
<span id="l4727">                int groupEnd = matcher.groups[groupIndex+1];</span><a href="#l4727"></a>
<span id="l4728"></span><a href="#l4728"></a>
<span id="l4729">                matcher.groups[groupIndex] = tmp;</span><a href="#l4729"></a>
<span id="l4730">                matcher.groups[groupIndex+1] = i;</span><a href="#l4730"></a>
<span id="l4731">                if (next.match(matcher, i, seq)) {</span><a href="#l4731"></a>
<span id="l4732">                    return true;</span><a href="#l4732"></a>
<span id="l4733">                }</span><a href="#l4733"></a>
<span id="l4734">                matcher.groups[groupIndex] = groupStart;</span><a href="#l4734"></a>
<span id="l4735">                matcher.groups[groupIndex+1] = groupEnd;</span><a href="#l4735"></a>
<span id="l4736">                return false;</span><a href="#l4736"></a>
<span id="l4737">            } else {</span><a href="#l4737"></a>
<span id="l4738">                // This is a group reference case. We don't need to save any</span><a href="#l4738"></a>
<span id="l4739">                // group info because it isn't really a group.</span><a href="#l4739"></a>
<span id="l4740">                matcher.last = i;</span><a href="#l4740"></a>
<span id="l4741">                return true;</span><a href="#l4741"></a>
<span id="l4742">            }</span><a href="#l4742"></a>
<span id="l4743">        }</span><a href="#l4743"></a>
<span id="l4744">    }</span><a href="#l4744"></a>
<span id="l4745"></span><a href="#l4745"></a>
<span id="l4746">    /**</span><a href="#l4746"></a>
<span id="l4747">     * This sets up a loop to handle a recursive quantifier structure.</span><a href="#l4747"></a>
<span id="l4748">     */</span><a href="#l4748"></a>
<span id="l4749">    static final class Prolog extends Node {</span><a href="#l4749"></a>
<span id="l4750">        Loop loop;</span><a href="#l4750"></a>
<span id="l4751">        Prolog(Loop loop) {</span><a href="#l4751"></a>
<span id="l4752">            this.loop = loop;</span><a href="#l4752"></a>
<span id="l4753">        }</span><a href="#l4753"></a>
<span id="l4754">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l4754"></a>
<span id="l4755">            return loop.matchInit(matcher, i, seq);</span><a href="#l4755"></a>
<span id="l4756">        }</span><a href="#l4756"></a>
<span id="l4757">        boolean study(TreeInfo info) {</span><a href="#l4757"></a>
<span id="l4758">            return loop.study(info);</span><a href="#l4758"></a>
<span id="l4759">        }</span><a href="#l4759"></a>
<span id="l4760">    }</span><a href="#l4760"></a>
<span id="l4761"></span><a href="#l4761"></a>
<span id="l4762">    /**</span><a href="#l4762"></a>
<span id="l4763">     * Handles the repetition count for a greedy Curly. The matchInit</span><a href="#l4763"></a>
<span id="l4764">     * is called from the Prolog to save the index of where the group</span><a href="#l4764"></a>
<span id="l4765">     * beginning is stored. A zero length group check occurs in the</span><a href="#l4765"></a>
<span id="l4766">     * normal match but is skipped in the matchInit.</span><a href="#l4766"></a>
<span id="l4767">     */</span><a href="#l4767"></a>
<span id="l4768">    static class Loop extends Node {</span><a href="#l4768"></a>
<span id="l4769">        Node body;</span><a href="#l4769"></a>
<span id="l4770">        int countIndex; // local count index in matcher locals</span><a href="#l4770"></a>
<span id="l4771">        int beginIndex; // group beginning index</span><a href="#l4771"></a>
<span id="l4772">        int cmin, cmax;</span><a href="#l4772"></a>
<span id="l4773">        Loop(int countIndex, int beginIndex) {</span><a href="#l4773"></a>
<span id="l4774">            this.countIndex = countIndex;</span><a href="#l4774"></a>
<span id="l4775">            this.beginIndex = beginIndex;</span><a href="#l4775"></a>
<span id="l4776">        }</span><a href="#l4776"></a>
<span id="l4777">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l4777"></a>
<span id="l4778">            // Avoid infinite loop in zero-length case.</span><a href="#l4778"></a>
<span id="l4779">            if (i &gt; matcher.locals[beginIndex]) {</span><a href="#l4779"></a>
<span id="l4780">                int count = matcher.locals[countIndex];</span><a href="#l4780"></a>
<span id="l4781"></span><a href="#l4781"></a>
<span id="l4782">                // This block is for before we reach the minimum</span><a href="#l4782"></a>
<span id="l4783">                // iterations required for the loop to match</span><a href="#l4783"></a>
<span id="l4784">                if (count &lt; cmin) {</span><a href="#l4784"></a>
<span id="l4785">                    matcher.locals[countIndex] = count + 1;</span><a href="#l4785"></a>
<span id="l4786">                    boolean b = body.match(matcher, i, seq);</span><a href="#l4786"></a>
<span id="l4787">                    // If match failed we must backtrack, so</span><a href="#l4787"></a>
<span id="l4788">                    // the loop count should NOT be incremented</span><a href="#l4788"></a>
<span id="l4789">                    if (!b)</span><a href="#l4789"></a>
<span id="l4790">                        matcher.locals[countIndex] = count;</span><a href="#l4790"></a>
<span id="l4791">                    // Return success or failure since we are under</span><a href="#l4791"></a>
<span id="l4792">                    // minimum</span><a href="#l4792"></a>
<span id="l4793">                    return b;</span><a href="#l4793"></a>
<span id="l4794">                }</span><a href="#l4794"></a>
<span id="l4795">                // This block is for after we have the minimum</span><a href="#l4795"></a>
<span id="l4796">                // iterations required for the loop to match</span><a href="#l4796"></a>
<span id="l4797">                if (count &lt; cmax) {</span><a href="#l4797"></a>
<span id="l4798">                    matcher.locals[countIndex] = count + 1;</span><a href="#l4798"></a>
<span id="l4799">                    boolean b = body.match(matcher, i, seq);</span><a href="#l4799"></a>
<span id="l4800">                    // If match failed we must backtrack, so</span><a href="#l4800"></a>
<span id="l4801">                    // the loop count should NOT be incremented</span><a href="#l4801"></a>
<span id="l4802">                    if (!b)</span><a href="#l4802"></a>
<span id="l4803">                        matcher.locals[countIndex] = count;</span><a href="#l4803"></a>
<span id="l4804">                    else</span><a href="#l4804"></a>
<span id="l4805">                        return true;</span><a href="#l4805"></a>
<span id="l4806">                }</span><a href="#l4806"></a>
<span id="l4807">            }</span><a href="#l4807"></a>
<span id="l4808">            return next.match(matcher, i, seq);</span><a href="#l4808"></a>
<span id="l4809">        }</span><a href="#l4809"></a>
<span id="l4810">        boolean matchInit(Matcher matcher, int i, CharSequence seq) {</span><a href="#l4810"></a>
<span id="l4811">            int save = matcher.locals[countIndex];</span><a href="#l4811"></a>
<span id="l4812">            boolean ret = false;</span><a href="#l4812"></a>
<span id="l4813">            if (0 &lt; cmin) {</span><a href="#l4813"></a>
<span id="l4814">                matcher.locals[countIndex] = 1;</span><a href="#l4814"></a>
<span id="l4815">                ret = body.match(matcher, i, seq);</span><a href="#l4815"></a>
<span id="l4816">            } else if (0 &lt; cmax) {</span><a href="#l4816"></a>
<span id="l4817">                matcher.locals[countIndex] = 1;</span><a href="#l4817"></a>
<span id="l4818">                ret = body.match(matcher, i, seq);</span><a href="#l4818"></a>
<span id="l4819">                if (ret == false)</span><a href="#l4819"></a>
<span id="l4820">                    ret = next.match(matcher, i, seq);</span><a href="#l4820"></a>
<span id="l4821">            } else {</span><a href="#l4821"></a>
<span id="l4822">                ret = next.match(matcher, i, seq);</span><a href="#l4822"></a>
<span id="l4823">            }</span><a href="#l4823"></a>
<span id="l4824">            matcher.locals[countIndex] = save;</span><a href="#l4824"></a>
<span id="l4825">            return ret;</span><a href="#l4825"></a>
<span id="l4826">        }</span><a href="#l4826"></a>
<span id="l4827">        boolean study(TreeInfo info) {</span><a href="#l4827"></a>
<span id="l4828">            info.maxValid = false;</span><a href="#l4828"></a>
<span id="l4829">            info.deterministic = false;</span><a href="#l4829"></a>
<span id="l4830">            return false;</span><a href="#l4830"></a>
<span id="l4831">        }</span><a href="#l4831"></a>
<span id="l4832">    }</span><a href="#l4832"></a>
<span id="l4833"></span><a href="#l4833"></a>
<span id="l4834">    /**</span><a href="#l4834"></a>
<span id="l4835">     * Handles the repetition count for a reluctant Curly. The matchInit</span><a href="#l4835"></a>
<span id="l4836">     * is called from the Prolog to save the index of where the group</span><a href="#l4836"></a>
<span id="l4837">     * beginning is stored. A zero length group check occurs in the</span><a href="#l4837"></a>
<span id="l4838">     * normal match but is skipped in the matchInit.</span><a href="#l4838"></a>
<span id="l4839">     */</span><a href="#l4839"></a>
<span id="l4840">    static final class LazyLoop extends Loop {</span><a href="#l4840"></a>
<span id="l4841">        LazyLoop(int countIndex, int beginIndex) {</span><a href="#l4841"></a>
<span id="l4842">            super(countIndex, beginIndex);</span><a href="#l4842"></a>
<span id="l4843">        }</span><a href="#l4843"></a>
<span id="l4844">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l4844"></a>
<span id="l4845">            // Check for zero length group</span><a href="#l4845"></a>
<span id="l4846">            if (i &gt; matcher.locals[beginIndex]) {</span><a href="#l4846"></a>
<span id="l4847">                int count = matcher.locals[countIndex];</span><a href="#l4847"></a>
<span id="l4848">                if (count &lt; cmin) {</span><a href="#l4848"></a>
<span id="l4849">                    matcher.locals[countIndex] = count + 1;</span><a href="#l4849"></a>
<span id="l4850">                    boolean result = body.match(matcher, i, seq);</span><a href="#l4850"></a>
<span id="l4851">                    // If match failed we must backtrack, so</span><a href="#l4851"></a>
<span id="l4852">                    // the loop count should NOT be incremented</span><a href="#l4852"></a>
<span id="l4853">                    if (!result)</span><a href="#l4853"></a>
<span id="l4854">                        matcher.locals[countIndex] = count;</span><a href="#l4854"></a>
<span id="l4855">                    return result;</span><a href="#l4855"></a>
<span id="l4856">                }</span><a href="#l4856"></a>
<span id="l4857">                if (next.match(matcher, i, seq))</span><a href="#l4857"></a>
<span id="l4858">                    return true;</span><a href="#l4858"></a>
<span id="l4859">                if (count &lt; cmax) {</span><a href="#l4859"></a>
<span id="l4860">                    matcher.locals[countIndex] = count + 1;</span><a href="#l4860"></a>
<span id="l4861">                    boolean result = body.match(matcher, i, seq);</span><a href="#l4861"></a>
<span id="l4862">                    // If match failed we must backtrack, so</span><a href="#l4862"></a>
<span id="l4863">                    // the loop count should NOT be incremented</span><a href="#l4863"></a>
<span id="l4864">                    if (!result)</span><a href="#l4864"></a>
<span id="l4865">                        matcher.locals[countIndex] = count;</span><a href="#l4865"></a>
<span id="l4866">                    return result;</span><a href="#l4866"></a>
<span id="l4867">                }</span><a href="#l4867"></a>
<span id="l4868">                return false;</span><a href="#l4868"></a>
<span id="l4869">            }</span><a href="#l4869"></a>
<span id="l4870">            return next.match(matcher, i, seq);</span><a href="#l4870"></a>
<span id="l4871">        }</span><a href="#l4871"></a>
<span id="l4872">        boolean matchInit(Matcher matcher, int i, CharSequence seq) {</span><a href="#l4872"></a>
<span id="l4873">            int save = matcher.locals[countIndex];</span><a href="#l4873"></a>
<span id="l4874">            boolean ret = false;</span><a href="#l4874"></a>
<span id="l4875">            if (0 &lt; cmin) {</span><a href="#l4875"></a>
<span id="l4876">                matcher.locals[countIndex] = 1;</span><a href="#l4876"></a>
<span id="l4877">                ret = body.match(matcher, i, seq);</span><a href="#l4877"></a>
<span id="l4878">            } else if (next.match(matcher, i, seq)) {</span><a href="#l4878"></a>
<span id="l4879">                ret = true;</span><a href="#l4879"></a>
<span id="l4880">            } else if (0 &lt; cmax) {</span><a href="#l4880"></a>
<span id="l4881">                matcher.locals[countIndex] = 1;</span><a href="#l4881"></a>
<span id="l4882">                ret = body.match(matcher, i, seq);</span><a href="#l4882"></a>
<span id="l4883">            }</span><a href="#l4883"></a>
<span id="l4884">            matcher.locals[countIndex] = save;</span><a href="#l4884"></a>
<span id="l4885">            return ret;</span><a href="#l4885"></a>
<span id="l4886">        }</span><a href="#l4886"></a>
<span id="l4887">        boolean study(TreeInfo info) {</span><a href="#l4887"></a>
<span id="l4888">            info.maxValid = false;</span><a href="#l4888"></a>
<span id="l4889">            info.deterministic = false;</span><a href="#l4889"></a>
<span id="l4890">            return false;</span><a href="#l4890"></a>
<span id="l4891">        }</span><a href="#l4891"></a>
<span id="l4892">    }</span><a href="#l4892"></a>
<span id="l4893"></span><a href="#l4893"></a>
<span id="l4894">    /**</span><a href="#l4894"></a>
<span id="l4895">     * Refers to a group in the regular expression. Attempts to match</span><a href="#l4895"></a>
<span id="l4896">     * whatever the group referred to last matched.</span><a href="#l4896"></a>
<span id="l4897">     */</span><a href="#l4897"></a>
<span id="l4898">    static class BackRef extends Node {</span><a href="#l4898"></a>
<span id="l4899">        int groupIndex;</span><a href="#l4899"></a>
<span id="l4900">        BackRef(int groupCount) {</span><a href="#l4900"></a>
<span id="l4901">            super();</span><a href="#l4901"></a>
<span id="l4902">            groupIndex = groupCount + groupCount;</span><a href="#l4902"></a>
<span id="l4903">        }</span><a href="#l4903"></a>
<span id="l4904">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l4904"></a>
<span id="l4905">            int j = matcher.groups[groupIndex];</span><a href="#l4905"></a>
<span id="l4906">            int k = matcher.groups[groupIndex+1];</span><a href="#l4906"></a>
<span id="l4907"></span><a href="#l4907"></a>
<span id="l4908">            int groupSize = k - j;</span><a href="#l4908"></a>
<span id="l4909">            // If the referenced group didn't match, neither can this</span><a href="#l4909"></a>
<span id="l4910">            if (j &lt; 0)</span><a href="#l4910"></a>
<span id="l4911">                return false;</span><a href="#l4911"></a>
<span id="l4912"></span><a href="#l4912"></a>
<span id="l4913">            // If there isn't enough input left no match</span><a href="#l4913"></a>
<span id="l4914">            if (i + groupSize &gt; matcher.to) {</span><a href="#l4914"></a>
<span id="l4915">                matcher.hitEnd = true;</span><a href="#l4915"></a>
<span id="l4916">                return false;</span><a href="#l4916"></a>
<span id="l4917">            }</span><a href="#l4917"></a>
<span id="l4918">            // Check each new char to make sure it matches what the group</span><a href="#l4918"></a>
<span id="l4919">            // referenced matched last time around</span><a href="#l4919"></a>
<span id="l4920">            for (int index=0; index&lt;groupSize; index++)</span><a href="#l4920"></a>
<span id="l4921">                if (seq.charAt(i+index) != seq.charAt(j+index))</span><a href="#l4921"></a>
<span id="l4922">                    return false;</span><a href="#l4922"></a>
<span id="l4923"></span><a href="#l4923"></a>
<span id="l4924">            return next.match(matcher, i+groupSize, seq);</span><a href="#l4924"></a>
<span id="l4925">        }</span><a href="#l4925"></a>
<span id="l4926">        boolean study(TreeInfo info) {</span><a href="#l4926"></a>
<span id="l4927">            info.maxValid = false;</span><a href="#l4927"></a>
<span id="l4928">            return next.study(info);</span><a href="#l4928"></a>
<span id="l4929">        }</span><a href="#l4929"></a>
<span id="l4930">    }</span><a href="#l4930"></a>
<span id="l4931"></span><a href="#l4931"></a>
<span id="l4932">    static class CIBackRef extends Node {</span><a href="#l4932"></a>
<span id="l4933">        int groupIndex;</span><a href="#l4933"></a>
<span id="l4934">        boolean doUnicodeCase;</span><a href="#l4934"></a>
<span id="l4935">        CIBackRef(int groupCount, boolean doUnicodeCase) {</span><a href="#l4935"></a>
<span id="l4936">            super();</span><a href="#l4936"></a>
<span id="l4937">            groupIndex = groupCount + groupCount;</span><a href="#l4937"></a>
<span id="l4938">            this.doUnicodeCase = doUnicodeCase;</span><a href="#l4938"></a>
<span id="l4939">        }</span><a href="#l4939"></a>
<span id="l4940">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l4940"></a>
<span id="l4941">            int j = matcher.groups[groupIndex];</span><a href="#l4941"></a>
<span id="l4942">            int k = matcher.groups[groupIndex+1];</span><a href="#l4942"></a>
<span id="l4943"></span><a href="#l4943"></a>
<span id="l4944">            int groupSize = k - j;</span><a href="#l4944"></a>
<span id="l4945"></span><a href="#l4945"></a>
<span id="l4946">            // If the referenced group didn't match, neither can this</span><a href="#l4946"></a>
<span id="l4947">            if (j &lt; 0)</span><a href="#l4947"></a>
<span id="l4948">                return false;</span><a href="#l4948"></a>
<span id="l4949"></span><a href="#l4949"></a>
<span id="l4950">            // If there isn't enough input left no match</span><a href="#l4950"></a>
<span id="l4951">            if (i + groupSize &gt; matcher.to) {</span><a href="#l4951"></a>
<span id="l4952">                matcher.hitEnd = true;</span><a href="#l4952"></a>
<span id="l4953">                return false;</span><a href="#l4953"></a>
<span id="l4954">            }</span><a href="#l4954"></a>
<span id="l4955"></span><a href="#l4955"></a>
<span id="l4956">            // Check each new char to make sure it matches what the group</span><a href="#l4956"></a>
<span id="l4957">            // referenced matched last time around</span><a href="#l4957"></a>
<span id="l4958">            int x = i;</span><a href="#l4958"></a>
<span id="l4959">            for (int index=0; index&lt;groupSize; index++) {</span><a href="#l4959"></a>
<span id="l4960">                int c1 = Character.codePointAt(seq, x);</span><a href="#l4960"></a>
<span id="l4961">                int c2 = Character.codePointAt(seq, j);</span><a href="#l4961"></a>
<span id="l4962">                if (c1 != c2) {</span><a href="#l4962"></a>
<span id="l4963">                    if (doUnicodeCase) {</span><a href="#l4963"></a>
<span id="l4964">                        int cc1 = Character.toUpperCase(c1);</span><a href="#l4964"></a>
<span id="l4965">                        int cc2 = Character.toUpperCase(c2);</span><a href="#l4965"></a>
<span id="l4966">                        if (cc1 != cc2 &amp;&amp;</span><a href="#l4966"></a>
<span id="l4967">                            Character.toLowerCase(cc1) !=</span><a href="#l4967"></a>
<span id="l4968">                            Character.toLowerCase(cc2))</span><a href="#l4968"></a>
<span id="l4969">                            return false;</span><a href="#l4969"></a>
<span id="l4970">                    } else {</span><a href="#l4970"></a>
<span id="l4971">                        if (ASCII.toLower(c1) != ASCII.toLower(c2))</span><a href="#l4971"></a>
<span id="l4972">                            return false;</span><a href="#l4972"></a>
<span id="l4973">                    }</span><a href="#l4973"></a>
<span id="l4974">                }</span><a href="#l4974"></a>
<span id="l4975">                x += Character.charCount(c1);</span><a href="#l4975"></a>
<span id="l4976">                j += Character.charCount(c2);</span><a href="#l4976"></a>
<span id="l4977">            }</span><a href="#l4977"></a>
<span id="l4978"></span><a href="#l4978"></a>
<span id="l4979">            return next.match(matcher, i+groupSize, seq);</span><a href="#l4979"></a>
<span id="l4980">        }</span><a href="#l4980"></a>
<span id="l4981">        boolean study(TreeInfo info) {</span><a href="#l4981"></a>
<span id="l4982">            info.maxValid = false;</span><a href="#l4982"></a>
<span id="l4983">            return next.study(info);</span><a href="#l4983"></a>
<span id="l4984">        }</span><a href="#l4984"></a>
<span id="l4985">    }</span><a href="#l4985"></a>
<span id="l4986"></span><a href="#l4986"></a>
<span id="l4987">    /**</span><a href="#l4987"></a>
<span id="l4988">     * Searches until the next instance of its atom. This is useful for</span><a href="#l4988"></a>
<span id="l4989">     * finding the atom efficiently without passing an instance of it</span><a href="#l4989"></a>
<span id="l4990">     * (greedy problem) and without a lot of wasted search time (reluctant</span><a href="#l4990"></a>
<span id="l4991">     * problem).</span><a href="#l4991"></a>
<span id="l4992">     */</span><a href="#l4992"></a>
<span id="l4993">    static final class First extends Node {</span><a href="#l4993"></a>
<span id="l4994">        Node atom;</span><a href="#l4994"></a>
<span id="l4995">        First(Node node) {</span><a href="#l4995"></a>
<span id="l4996">            this.atom = BnM.optimize(node);</span><a href="#l4996"></a>
<span id="l4997">        }</span><a href="#l4997"></a>
<span id="l4998">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l4998"></a>
<span id="l4999">            if (atom instanceof BnM) {</span><a href="#l4999"></a>
<span id="l5000">                return atom.match(matcher, i, seq)</span><a href="#l5000"></a>
<span id="l5001">                    &amp;&amp; next.match(matcher, matcher.last, seq);</span><a href="#l5001"></a>
<span id="l5002">            }</span><a href="#l5002"></a>
<span id="l5003">            for (;;) {</span><a href="#l5003"></a>
<span id="l5004">                if (i &gt; matcher.to) {</span><a href="#l5004"></a>
<span id="l5005">                    matcher.hitEnd = true;</span><a href="#l5005"></a>
<span id="l5006">                    return false;</span><a href="#l5006"></a>
<span id="l5007">                }</span><a href="#l5007"></a>
<span id="l5008">                if (atom.match(matcher, i, seq)) {</span><a href="#l5008"></a>
<span id="l5009">                    return next.match(matcher, matcher.last, seq);</span><a href="#l5009"></a>
<span id="l5010">                }</span><a href="#l5010"></a>
<span id="l5011">                i += countChars(seq, i, 1);</span><a href="#l5011"></a>
<span id="l5012">                matcher.first++;</span><a href="#l5012"></a>
<span id="l5013">            }</span><a href="#l5013"></a>
<span id="l5014">        }</span><a href="#l5014"></a>
<span id="l5015">        boolean study(TreeInfo info) {</span><a href="#l5015"></a>
<span id="l5016">            atom.study(info);</span><a href="#l5016"></a>
<span id="l5017">            info.maxValid = false;</span><a href="#l5017"></a>
<span id="l5018">            info.deterministic = false;</span><a href="#l5018"></a>
<span id="l5019">            return next.study(info);</span><a href="#l5019"></a>
<span id="l5020">        }</span><a href="#l5020"></a>
<span id="l5021">    }</span><a href="#l5021"></a>
<span id="l5022"></span><a href="#l5022"></a>
<span id="l5023">    static final class Conditional extends Node {</span><a href="#l5023"></a>
<span id="l5024">        Node cond, yes, not;</span><a href="#l5024"></a>
<span id="l5025">        Conditional(Node cond, Node yes, Node not) {</span><a href="#l5025"></a>
<span id="l5026">            this.cond = cond;</span><a href="#l5026"></a>
<span id="l5027">            this.yes = yes;</span><a href="#l5027"></a>
<span id="l5028">            this.not = not;</span><a href="#l5028"></a>
<span id="l5029">        }</span><a href="#l5029"></a>
<span id="l5030">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l5030"></a>
<span id="l5031">            if (cond.match(matcher, i, seq)) {</span><a href="#l5031"></a>
<span id="l5032">                return yes.match(matcher, i, seq);</span><a href="#l5032"></a>
<span id="l5033">            } else {</span><a href="#l5033"></a>
<span id="l5034">                return not.match(matcher, i, seq);</span><a href="#l5034"></a>
<span id="l5035">            }</span><a href="#l5035"></a>
<span id="l5036">        }</span><a href="#l5036"></a>
<span id="l5037">        boolean study(TreeInfo info) {</span><a href="#l5037"></a>
<span id="l5038">            int minL = info.minLength;</span><a href="#l5038"></a>
<span id="l5039">            int maxL = info.maxLength;</span><a href="#l5039"></a>
<span id="l5040">            boolean maxV = info.maxValid;</span><a href="#l5040"></a>
<span id="l5041">            info.reset();</span><a href="#l5041"></a>
<span id="l5042">            yes.study(info);</span><a href="#l5042"></a>
<span id="l5043"></span><a href="#l5043"></a>
<span id="l5044">            int minL2 = info.minLength;</span><a href="#l5044"></a>
<span id="l5045">            int maxL2 = info.maxLength;</span><a href="#l5045"></a>
<span id="l5046">            boolean maxV2 = info.maxValid;</span><a href="#l5046"></a>
<span id="l5047">            info.reset();</span><a href="#l5047"></a>
<span id="l5048">            not.study(info);</span><a href="#l5048"></a>
<span id="l5049"></span><a href="#l5049"></a>
<span id="l5050">            info.minLength = minL + Math.min(minL2, info.minLength);</span><a href="#l5050"></a>
<span id="l5051">            info.maxLength = maxL + Math.max(maxL2, info.maxLength);</span><a href="#l5051"></a>
<span id="l5052">            info.maxValid = (maxV &amp; maxV2 &amp; info.maxValid);</span><a href="#l5052"></a>
<span id="l5053">            info.deterministic = false;</span><a href="#l5053"></a>
<span id="l5054">            return next.study(info);</span><a href="#l5054"></a>
<span id="l5055">        }</span><a href="#l5055"></a>
<span id="l5056">    }</span><a href="#l5056"></a>
<span id="l5057"></span><a href="#l5057"></a>
<span id="l5058">    /**</span><a href="#l5058"></a>
<span id="l5059">     * Zero width positive lookahead.</span><a href="#l5059"></a>
<span id="l5060">     */</span><a href="#l5060"></a>
<span id="l5061">    static final class Pos extends Node {</span><a href="#l5061"></a>
<span id="l5062">        Node cond;</span><a href="#l5062"></a>
<span id="l5063">        Pos(Node cond) {</span><a href="#l5063"></a>
<span id="l5064">            this.cond = cond;</span><a href="#l5064"></a>
<span id="l5065">        }</span><a href="#l5065"></a>
<span id="l5066">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l5066"></a>
<span id="l5067">            int savedTo = matcher.to;</span><a href="#l5067"></a>
<span id="l5068">            boolean conditionMatched = false;</span><a href="#l5068"></a>
<span id="l5069"></span><a href="#l5069"></a>
<span id="l5070">            // Relax transparent region boundaries for lookahead</span><a href="#l5070"></a>
<span id="l5071">            if (matcher.transparentBounds)</span><a href="#l5071"></a>
<span id="l5072">                matcher.to = matcher.getTextLength();</span><a href="#l5072"></a>
<span id="l5073">            try {</span><a href="#l5073"></a>
<span id="l5074">                conditionMatched = cond.match(matcher, i, seq);</span><a href="#l5074"></a>
<span id="l5075">            } finally {</span><a href="#l5075"></a>
<span id="l5076">                // Reinstate region boundaries</span><a href="#l5076"></a>
<span id="l5077">                matcher.to = savedTo;</span><a href="#l5077"></a>
<span id="l5078">            }</span><a href="#l5078"></a>
<span id="l5079">            return conditionMatched &amp;&amp; next.match(matcher, i, seq);</span><a href="#l5079"></a>
<span id="l5080">        }</span><a href="#l5080"></a>
<span id="l5081">    }</span><a href="#l5081"></a>
<span id="l5082"></span><a href="#l5082"></a>
<span id="l5083">    /**</span><a href="#l5083"></a>
<span id="l5084">     * Zero width negative lookahead.</span><a href="#l5084"></a>
<span id="l5085">     */</span><a href="#l5085"></a>
<span id="l5086">    static final class Neg extends Node {</span><a href="#l5086"></a>
<span id="l5087">        Node cond;</span><a href="#l5087"></a>
<span id="l5088">        Neg(Node cond) {</span><a href="#l5088"></a>
<span id="l5089">            this.cond = cond;</span><a href="#l5089"></a>
<span id="l5090">        }</span><a href="#l5090"></a>
<span id="l5091">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l5091"></a>
<span id="l5092">            int savedTo = matcher.to;</span><a href="#l5092"></a>
<span id="l5093">            boolean conditionMatched = false;</span><a href="#l5093"></a>
<span id="l5094"></span><a href="#l5094"></a>
<span id="l5095">            // Relax transparent region boundaries for lookahead</span><a href="#l5095"></a>
<span id="l5096">            if (matcher.transparentBounds)</span><a href="#l5096"></a>
<span id="l5097">                matcher.to = matcher.getTextLength();</span><a href="#l5097"></a>
<span id="l5098">            try {</span><a href="#l5098"></a>
<span id="l5099">                if (i &lt; matcher.to) {</span><a href="#l5099"></a>
<span id="l5100">                    conditionMatched = !cond.match(matcher, i, seq);</span><a href="#l5100"></a>
<span id="l5101">                } else {</span><a href="#l5101"></a>
<span id="l5102">                    // If a negative lookahead succeeds then more input</span><a href="#l5102"></a>
<span id="l5103">                    // could cause it to fail!</span><a href="#l5103"></a>
<span id="l5104">                    matcher.requireEnd = true;</span><a href="#l5104"></a>
<span id="l5105">                    conditionMatched = !cond.match(matcher, i, seq);</span><a href="#l5105"></a>
<span id="l5106">                }</span><a href="#l5106"></a>
<span id="l5107">            } finally {</span><a href="#l5107"></a>
<span id="l5108">                // Reinstate region boundaries</span><a href="#l5108"></a>
<span id="l5109">                matcher.to = savedTo;</span><a href="#l5109"></a>
<span id="l5110">            }</span><a href="#l5110"></a>
<span id="l5111">            return conditionMatched &amp;&amp; next.match(matcher, i, seq);</span><a href="#l5111"></a>
<span id="l5112">        }</span><a href="#l5112"></a>
<span id="l5113">    }</span><a href="#l5113"></a>
<span id="l5114"></span><a href="#l5114"></a>
<span id="l5115">    /**</span><a href="#l5115"></a>
<span id="l5116">     * For use with lookbehinds; matches the position where the lookbehind</span><a href="#l5116"></a>
<span id="l5117">     * was encountered.</span><a href="#l5117"></a>
<span id="l5118">     */</span><a href="#l5118"></a>
<span id="l5119">    static Node lookbehindEnd = new Node() {</span><a href="#l5119"></a>
<span id="l5120">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l5120"></a>
<span id="l5121">            return i == matcher.lookbehindTo;</span><a href="#l5121"></a>
<span id="l5122">        }</span><a href="#l5122"></a>
<span id="l5123">    };</span><a href="#l5123"></a>
<span id="l5124"></span><a href="#l5124"></a>
<span id="l5125">    /**</span><a href="#l5125"></a>
<span id="l5126">     * Zero width positive lookbehind.</span><a href="#l5126"></a>
<span id="l5127">     */</span><a href="#l5127"></a>
<span id="l5128">    static class Behind extends Node {</span><a href="#l5128"></a>
<span id="l5129">        Node cond;</span><a href="#l5129"></a>
<span id="l5130">        int rmax, rmin;</span><a href="#l5130"></a>
<span id="l5131">        Behind(Node cond, int rmax, int rmin) {</span><a href="#l5131"></a>
<span id="l5132">            this.cond = cond;</span><a href="#l5132"></a>
<span id="l5133">            this.rmax = rmax;</span><a href="#l5133"></a>
<span id="l5134">            this.rmin = rmin;</span><a href="#l5134"></a>
<span id="l5135">        }</span><a href="#l5135"></a>
<span id="l5136"></span><a href="#l5136"></a>
<span id="l5137">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l5137"></a>
<span id="l5138">            int savedFrom = matcher.from;</span><a href="#l5138"></a>
<span id="l5139">            boolean conditionMatched = false;</span><a href="#l5139"></a>
<span id="l5140">            int startIndex = (!matcher.transparentBounds) ?</span><a href="#l5140"></a>
<span id="l5141">                             matcher.from : 0;</span><a href="#l5141"></a>
<span id="l5142">            int from = Math.max(i - rmax, startIndex);</span><a href="#l5142"></a>
<span id="l5143">            // Set end boundary</span><a href="#l5143"></a>
<span id="l5144">            int savedLBT = matcher.lookbehindTo;</span><a href="#l5144"></a>
<span id="l5145">            matcher.lookbehindTo = i;</span><a href="#l5145"></a>
<span id="l5146">            // Relax transparent region boundaries for lookbehind</span><a href="#l5146"></a>
<span id="l5147">            if (matcher.transparentBounds)</span><a href="#l5147"></a>
<span id="l5148">                matcher.from = 0;</span><a href="#l5148"></a>
<span id="l5149">            for (int j = i - rmin; !conditionMatched &amp;&amp; j &gt;= from; j--) {</span><a href="#l5149"></a>
<span id="l5150">                conditionMatched = cond.match(matcher, j, seq);</span><a href="#l5150"></a>
<span id="l5151">            }</span><a href="#l5151"></a>
<span id="l5152">            matcher.from = savedFrom;</span><a href="#l5152"></a>
<span id="l5153">            matcher.lookbehindTo = savedLBT;</span><a href="#l5153"></a>
<span id="l5154">            return conditionMatched &amp;&amp; next.match(matcher, i, seq);</span><a href="#l5154"></a>
<span id="l5155">        }</span><a href="#l5155"></a>
<span id="l5156">    }</span><a href="#l5156"></a>
<span id="l5157"></span><a href="#l5157"></a>
<span id="l5158">    /**</span><a href="#l5158"></a>
<span id="l5159">     * Zero width positive lookbehind, including supplementary</span><a href="#l5159"></a>
<span id="l5160">     * characters or unpaired surrogates.</span><a href="#l5160"></a>
<span id="l5161">     */</span><a href="#l5161"></a>
<span id="l5162">    static final class BehindS extends Behind {</span><a href="#l5162"></a>
<span id="l5163">        BehindS(Node cond, int rmax, int rmin) {</span><a href="#l5163"></a>
<span id="l5164">            super(cond, rmax, rmin);</span><a href="#l5164"></a>
<span id="l5165">        }</span><a href="#l5165"></a>
<span id="l5166">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l5166"></a>
<span id="l5167">            int rmaxChars = countChars(seq, i, -rmax);</span><a href="#l5167"></a>
<span id="l5168">            int rminChars = countChars(seq, i, -rmin);</span><a href="#l5168"></a>
<span id="l5169">            int savedFrom = matcher.from;</span><a href="#l5169"></a>
<span id="l5170">            int startIndex = (!matcher.transparentBounds) ?</span><a href="#l5170"></a>
<span id="l5171">                             matcher.from : 0;</span><a href="#l5171"></a>
<span id="l5172">            boolean conditionMatched = false;</span><a href="#l5172"></a>
<span id="l5173">            int from = Math.max(i - rmaxChars, startIndex);</span><a href="#l5173"></a>
<span id="l5174">            // Set end boundary</span><a href="#l5174"></a>
<span id="l5175">            int savedLBT = matcher.lookbehindTo;</span><a href="#l5175"></a>
<span id="l5176">            matcher.lookbehindTo = i;</span><a href="#l5176"></a>
<span id="l5177">            // Relax transparent region boundaries for lookbehind</span><a href="#l5177"></a>
<span id="l5178">            if (matcher.transparentBounds)</span><a href="#l5178"></a>
<span id="l5179">                matcher.from = 0;</span><a href="#l5179"></a>
<span id="l5180"></span><a href="#l5180"></a>
<span id="l5181">            for (int j = i - rminChars;</span><a href="#l5181"></a>
<span id="l5182">                 !conditionMatched &amp;&amp; j &gt;= from;</span><a href="#l5182"></a>
<span id="l5183">                 j -= j&gt;from ? countChars(seq, j, -1) : 1) {</span><a href="#l5183"></a>
<span id="l5184">                conditionMatched = cond.match(matcher, j, seq);</span><a href="#l5184"></a>
<span id="l5185">            }</span><a href="#l5185"></a>
<span id="l5186">            matcher.from = savedFrom;</span><a href="#l5186"></a>
<span id="l5187">            matcher.lookbehindTo = savedLBT;</span><a href="#l5187"></a>
<span id="l5188">            return conditionMatched &amp;&amp; next.match(matcher, i, seq);</span><a href="#l5188"></a>
<span id="l5189">        }</span><a href="#l5189"></a>
<span id="l5190">    }</span><a href="#l5190"></a>
<span id="l5191"></span><a href="#l5191"></a>
<span id="l5192">    /**</span><a href="#l5192"></a>
<span id="l5193">     * Zero width negative lookbehind.</span><a href="#l5193"></a>
<span id="l5194">     */</span><a href="#l5194"></a>
<span id="l5195">    static class NotBehind extends Node {</span><a href="#l5195"></a>
<span id="l5196">        Node cond;</span><a href="#l5196"></a>
<span id="l5197">        int rmax, rmin;</span><a href="#l5197"></a>
<span id="l5198">        NotBehind(Node cond, int rmax, int rmin) {</span><a href="#l5198"></a>
<span id="l5199">            this.cond = cond;</span><a href="#l5199"></a>
<span id="l5200">            this.rmax = rmax;</span><a href="#l5200"></a>
<span id="l5201">            this.rmin = rmin;</span><a href="#l5201"></a>
<span id="l5202">        }</span><a href="#l5202"></a>
<span id="l5203"></span><a href="#l5203"></a>
<span id="l5204">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l5204"></a>
<span id="l5205">            int savedLBT = matcher.lookbehindTo;</span><a href="#l5205"></a>
<span id="l5206">            int savedFrom = matcher.from;</span><a href="#l5206"></a>
<span id="l5207">            boolean conditionMatched = false;</span><a href="#l5207"></a>
<span id="l5208">            int startIndex = (!matcher.transparentBounds) ?</span><a href="#l5208"></a>
<span id="l5209">                             matcher.from : 0;</span><a href="#l5209"></a>
<span id="l5210">            int from = Math.max(i - rmax, startIndex);</span><a href="#l5210"></a>
<span id="l5211">            matcher.lookbehindTo = i;</span><a href="#l5211"></a>
<span id="l5212">            // Relax transparent region boundaries for lookbehind</span><a href="#l5212"></a>
<span id="l5213">            if (matcher.transparentBounds)</span><a href="#l5213"></a>
<span id="l5214">                matcher.from = 0;</span><a href="#l5214"></a>
<span id="l5215">            for (int j = i - rmin; !conditionMatched &amp;&amp; j &gt;= from; j--) {</span><a href="#l5215"></a>
<span id="l5216">                conditionMatched = cond.match(matcher, j, seq);</span><a href="#l5216"></a>
<span id="l5217">            }</span><a href="#l5217"></a>
<span id="l5218">            // Reinstate region boundaries</span><a href="#l5218"></a>
<span id="l5219">            matcher.from = savedFrom;</span><a href="#l5219"></a>
<span id="l5220">            matcher.lookbehindTo = savedLBT;</span><a href="#l5220"></a>
<span id="l5221">            return !conditionMatched &amp;&amp; next.match(matcher, i, seq);</span><a href="#l5221"></a>
<span id="l5222">        }</span><a href="#l5222"></a>
<span id="l5223">    }</span><a href="#l5223"></a>
<span id="l5224"></span><a href="#l5224"></a>
<span id="l5225">    /**</span><a href="#l5225"></a>
<span id="l5226">     * Zero width negative lookbehind, including supplementary</span><a href="#l5226"></a>
<span id="l5227">     * characters or unpaired surrogates.</span><a href="#l5227"></a>
<span id="l5228">     */</span><a href="#l5228"></a>
<span id="l5229">    static final class NotBehindS extends NotBehind {</span><a href="#l5229"></a>
<span id="l5230">        NotBehindS(Node cond, int rmax, int rmin) {</span><a href="#l5230"></a>
<span id="l5231">            super(cond, rmax, rmin);</span><a href="#l5231"></a>
<span id="l5232">        }</span><a href="#l5232"></a>
<span id="l5233">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l5233"></a>
<span id="l5234">            int rmaxChars = countChars(seq, i, -rmax);</span><a href="#l5234"></a>
<span id="l5235">            int rminChars = countChars(seq, i, -rmin);</span><a href="#l5235"></a>
<span id="l5236">            int savedFrom = matcher.from;</span><a href="#l5236"></a>
<span id="l5237">            int savedLBT = matcher.lookbehindTo;</span><a href="#l5237"></a>
<span id="l5238">            boolean conditionMatched = false;</span><a href="#l5238"></a>
<span id="l5239">            int startIndex = (!matcher.transparentBounds) ?</span><a href="#l5239"></a>
<span id="l5240">                             matcher.from : 0;</span><a href="#l5240"></a>
<span id="l5241">            int from = Math.max(i - rmaxChars, startIndex);</span><a href="#l5241"></a>
<span id="l5242">            matcher.lookbehindTo = i;</span><a href="#l5242"></a>
<span id="l5243">            // Relax transparent region boundaries for lookbehind</span><a href="#l5243"></a>
<span id="l5244">            if (matcher.transparentBounds)</span><a href="#l5244"></a>
<span id="l5245">                matcher.from = 0;</span><a href="#l5245"></a>
<span id="l5246">            for (int j = i - rminChars;</span><a href="#l5246"></a>
<span id="l5247">                 !conditionMatched &amp;&amp; j &gt;= from;</span><a href="#l5247"></a>
<span id="l5248">                 j -= j&gt;from ? countChars(seq, j, -1) : 1) {</span><a href="#l5248"></a>
<span id="l5249">                conditionMatched = cond.match(matcher, j, seq);</span><a href="#l5249"></a>
<span id="l5250">            }</span><a href="#l5250"></a>
<span id="l5251">            //Reinstate region boundaries</span><a href="#l5251"></a>
<span id="l5252">            matcher.from = savedFrom;</span><a href="#l5252"></a>
<span id="l5253">            matcher.lookbehindTo = savedLBT;</span><a href="#l5253"></a>
<span id="l5254">            return !conditionMatched &amp;&amp; next.match(matcher, i, seq);</span><a href="#l5254"></a>
<span id="l5255">        }</span><a href="#l5255"></a>
<span id="l5256">    }</span><a href="#l5256"></a>
<span id="l5257"></span><a href="#l5257"></a>
<span id="l5258">    /**</span><a href="#l5258"></a>
<span id="l5259">     * Returns the set union of two CharProperty nodes.</span><a href="#l5259"></a>
<span id="l5260">     */</span><a href="#l5260"></a>
<span id="l5261">    private static CharProperty union(final CharProperty lhs,</span><a href="#l5261"></a>
<span id="l5262">                                      final CharProperty rhs) {</span><a href="#l5262"></a>
<span id="l5263">        return new CharProperty() {</span><a href="#l5263"></a>
<span id="l5264">                boolean isSatisfiedBy(int ch) {</span><a href="#l5264"></a>
<span id="l5265">                    return lhs.isSatisfiedBy(ch) || rhs.isSatisfiedBy(ch);}};</span><a href="#l5265"></a>
<span id="l5266">    }</span><a href="#l5266"></a>
<span id="l5267"></span><a href="#l5267"></a>
<span id="l5268">    /**</span><a href="#l5268"></a>
<span id="l5269">     * Returns the set intersection of two CharProperty nodes.</span><a href="#l5269"></a>
<span id="l5270">     */</span><a href="#l5270"></a>
<span id="l5271">    private static CharProperty intersection(final CharProperty lhs,</span><a href="#l5271"></a>
<span id="l5272">                                             final CharProperty rhs) {</span><a href="#l5272"></a>
<span id="l5273">        return new CharProperty() {</span><a href="#l5273"></a>
<span id="l5274">                boolean isSatisfiedBy(int ch) {</span><a href="#l5274"></a>
<span id="l5275">                    return lhs.isSatisfiedBy(ch) &amp;&amp; rhs.isSatisfiedBy(ch);}};</span><a href="#l5275"></a>
<span id="l5276">    }</span><a href="#l5276"></a>
<span id="l5277"></span><a href="#l5277"></a>
<span id="l5278">    /**</span><a href="#l5278"></a>
<span id="l5279">     * Returns the set difference of two CharProperty nodes.</span><a href="#l5279"></a>
<span id="l5280">     */</span><a href="#l5280"></a>
<span id="l5281">    private static CharProperty setDifference(final CharProperty lhs,</span><a href="#l5281"></a>
<span id="l5282">                                              final CharProperty rhs) {</span><a href="#l5282"></a>
<span id="l5283">        return new CharProperty() {</span><a href="#l5283"></a>
<span id="l5284">                boolean isSatisfiedBy(int ch) {</span><a href="#l5284"></a>
<span id="l5285">                    return ! rhs.isSatisfiedBy(ch) &amp;&amp; lhs.isSatisfiedBy(ch);}};</span><a href="#l5285"></a>
<span id="l5286">    }</span><a href="#l5286"></a>
<span id="l5287"></span><a href="#l5287"></a>
<span id="l5288">    /**</span><a href="#l5288"></a>
<span id="l5289">     * Handles word boundaries. Includes a field to allow this one class to</span><a href="#l5289"></a>
<span id="l5290">     * deal with the different types of word boundaries we can match. The word</span><a href="#l5290"></a>
<span id="l5291">     * characters include underscores, letters, and digits. Non spacing marks</span><a href="#l5291"></a>
<span id="l5292">     * can are also part of a word if they have a base character, otherwise</span><a href="#l5292"></a>
<span id="l5293">     * they are ignored for purposes of finding word boundaries.</span><a href="#l5293"></a>
<span id="l5294">     */</span><a href="#l5294"></a>
<span id="l5295">    static final class Bound extends Node {</span><a href="#l5295"></a>
<span id="l5296">        static int LEFT = 0x1;</span><a href="#l5296"></a>
<span id="l5297">        static int RIGHT= 0x2;</span><a href="#l5297"></a>
<span id="l5298">        static int BOTH = 0x3;</span><a href="#l5298"></a>
<span id="l5299">        static int NONE = 0x4;</span><a href="#l5299"></a>
<span id="l5300">        int type;</span><a href="#l5300"></a>
<span id="l5301">        boolean useUWORD;</span><a href="#l5301"></a>
<span id="l5302">        Bound(int n, boolean useUWORD) {</span><a href="#l5302"></a>
<span id="l5303">            type = n;</span><a href="#l5303"></a>
<span id="l5304">            this.useUWORD = useUWORD;</span><a href="#l5304"></a>
<span id="l5305">        }</span><a href="#l5305"></a>
<span id="l5306"></span><a href="#l5306"></a>
<span id="l5307">        boolean isWord(int ch) {</span><a href="#l5307"></a>
<span id="l5308">            return useUWORD ? UnicodeProp.WORD.is(ch)</span><a href="#l5308"></a>
<span id="l5309">                            : (ch == '_' || Character.isLetterOrDigit(ch));</span><a href="#l5309"></a>
<span id="l5310">        }</span><a href="#l5310"></a>
<span id="l5311"></span><a href="#l5311"></a>
<span id="l5312">        int check(Matcher matcher, int i, CharSequence seq) {</span><a href="#l5312"></a>
<span id="l5313">            int ch;</span><a href="#l5313"></a>
<span id="l5314">            boolean left = false;</span><a href="#l5314"></a>
<span id="l5315">            int startIndex = matcher.from;</span><a href="#l5315"></a>
<span id="l5316">            int endIndex = matcher.to;</span><a href="#l5316"></a>
<span id="l5317">            if (matcher.transparentBounds) {</span><a href="#l5317"></a>
<span id="l5318">                startIndex = 0;</span><a href="#l5318"></a>
<span id="l5319">                endIndex = matcher.getTextLength();</span><a href="#l5319"></a>
<span id="l5320">            }</span><a href="#l5320"></a>
<span id="l5321">            if (i &gt; startIndex) {</span><a href="#l5321"></a>
<span id="l5322">                ch = Character.codePointBefore(seq, i);</span><a href="#l5322"></a>
<span id="l5323">                left = (isWord(ch) ||</span><a href="#l5323"></a>
<span id="l5324">                    ((Character.getType(ch) == Character.NON_SPACING_MARK)</span><a href="#l5324"></a>
<span id="l5325">                     &amp;&amp; hasBaseCharacter(matcher, i-1, seq)));</span><a href="#l5325"></a>
<span id="l5326">            }</span><a href="#l5326"></a>
<span id="l5327">            boolean right = false;</span><a href="#l5327"></a>
<span id="l5328">            if (i &lt; endIndex) {</span><a href="#l5328"></a>
<span id="l5329">                ch = Character.codePointAt(seq, i);</span><a href="#l5329"></a>
<span id="l5330">                right = (isWord(ch) ||</span><a href="#l5330"></a>
<span id="l5331">                    ((Character.getType(ch) == Character.NON_SPACING_MARK)</span><a href="#l5331"></a>
<span id="l5332">                     &amp;&amp; hasBaseCharacter(matcher, i, seq)));</span><a href="#l5332"></a>
<span id="l5333">            } else {</span><a href="#l5333"></a>
<span id="l5334">                // Tried to access char past the end</span><a href="#l5334"></a>
<span id="l5335">                matcher.hitEnd = true;</span><a href="#l5335"></a>
<span id="l5336">                // The addition of another char could wreck a boundary</span><a href="#l5336"></a>
<span id="l5337">                matcher.requireEnd = true;</span><a href="#l5337"></a>
<span id="l5338">            }</span><a href="#l5338"></a>
<span id="l5339">            return ((left ^ right) ? (right ? LEFT : RIGHT) : NONE);</span><a href="#l5339"></a>
<span id="l5340">        }</span><a href="#l5340"></a>
<span id="l5341">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l5341"></a>
<span id="l5342">            return (check(matcher, i, seq) &amp; type) &gt; 0</span><a href="#l5342"></a>
<span id="l5343">                &amp;&amp; next.match(matcher, i, seq);</span><a href="#l5343"></a>
<span id="l5344">        }</span><a href="#l5344"></a>
<span id="l5345">    }</span><a href="#l5345"></a>
<span id="l5346"></span><a href="#l5346"></a>
<span id="l5347">    /**</span><a href="#l5347"></a>
<span id="l5348">     * Non spacing marks only count as word characters in bounds calculations</span><a href="#l5348"></a>
<span id="l5349">     * if they have a base character.</span><a href="#l5349"></a>
<span id="l5350">     */</span><a href="#l5350"></a>
<span id="l5351">    private static boolean hasBaseCharacter(Matcher matcher, int i,</span><a href="#l5351"></a>
<span id="l5352">                                            CharSequence seq)</span><a href="#l5352"></a>
<span id="l5353">    {</span><a href="#l5353"></a>
<span id="l5354">        int start = (!matcher.transparentBounds) ?</span><a href="#l5354"></a>
<span id="l5355">            matcher.from : 0;</span><a href="#l5355"></a>
<span id="l5356">        for (int x=i; x &gt;= start; x--) {</span><a href="#l5356"></a>
<span id="l5357">            int ch = Character.codePointAt(seq, x);</span><a href="#l5357"></a>
<span id="l5358">            if (Character.isLetterOrDigit(ch))</span><a href="#l5358"></a>
<span id="l5359">                return true;</span><a href="#l5359"></a>
<span id="l5360">            if (Character.getType(ch) == Character.NON_SPACING_MARK)</span><a href="#l5360"></a>
<span id="l5361">                continue;</span><a href="#l5361"></a>
<span id="l5362">            return false;</span><a href="#l5362"></a>
<span id="l5363">        }</span><a href="#l5363"></a>
<span id="l5364">        return false;</span><a href="#l5364"></a>
<span id="l5365">    }</span><a href="#l5365"></a>
<span id="l5366"></span><a href="#l5366"></a>
<span id="l5367">    /**</span><a href="#l5367"></a>
<span id="l5368">     * Attempts to match a slice in the input using the Boyer-Moore string</span><a href="#l5368"></a>
<span id="l5369">     * matching algorithm. The algorithm is based on the idea that the</span><a href="#l5369"></a>
<span id="l5370">     * pattern can be shifted farther ahead in the search text if it is</span><a href="#l5370"></a>
<span id="l5371">     * matched right to left.</span><a href="#l5371"></a>
<span id="l5372">     * &lt;p&gt;</span><a href="#l5372"></a>
<span id="l5373">     * The pattern is compared to the input one character at a time, from</span><a href="#l5373"></a>
<span id="l5374">     * the rightmost character in the pattern to the left. If the characters</span><a href="#l5374"></a>
<span id="l5375">     * all match the pattern has been found. If a character does not match,</span><a href="#l5375"></a>
<span id="l5376">     * the pattern is shifted right a distance that is the maximum of two</span><a href="#l5376"></a>
<span id="l5377">     * functions, the bad character shift and the good suffix shift. This</span><a href="#l5377"></a>
<span id="l5378">     * shift moves the attempted match position through the input more</span><a href="#l5378"></a>
<span id="l5379">     * quickly than a naive one position at a time check.</span><a href="#l5379"></a>
<span id="l5380">     * &lt;p&gt;</span><a href="#l5380"></a>
<span id="l5381">     * The bad character shift is based on the character from the text that</span><a href="#l5381"></a>
<span id="l5382">     * did not match. If the character does not appear in the pattern, the</span><a href="#l5382"></a>
<span id="l5383">     * pattern can be shifted completely beyond the bad character. If the</span><a href="#l5383"></a>
<span id="l5384">     * character does occur in the pattern, the pattern can be shifted to</span><a href="#l5384"></a>
<span id="l5385">     * line the pattern up with the next occurrence of that character.</span><a href="#l5385"></a>
<span id="l5386">     * &lt;p&gt;</span><a href="#l5386"></a>
<span id="l5387">     * The good suffix shift is based on the idea that some subset on the right</span><a href="#l5387"></a>
<span id="l5388">     * side of the pattern has matched. When a bad character is found, the</span><a href="#l5388"></a>
<span id="l5389">     * pattern can be shifted right by the pattern length if the subset does</span><a href="#l5389"></a>
<span id="l5390">     * not occur again in pattern, or by the amount of distance to the</span><a href="#l5390"></a>
<span id="l5391">     * next occurrence of the subset in the pattern.</span><a href="#l5391"></a>
<span id="l5392">     *</span><a href="#l5392"></a>
<span id="l5393">     * Boyer-Moore search methods adapted from code by Amy Yu.</span><a href="#l5393"></a>
<span id="l5394">     */</span><a href="#l5394"></a>
<span id="l5395">    static class BnM extends Node {</span><a href="#l5395"></a>
<span id="l5396">        int[] buffer;</span><a href="#l5396"></a>
<span id="l5397">        int[] lastOcc;</span><a href="#l5397"></a>
<span id="l5398">        int[] optoSft;</span><a href="#l5398"></a>
<span id="l5399"></span><a href="#l5399"></a>
<span id="l5400">        /**</span><a href="#l5400"></a>
<span id="l5401">         * Pre calculates arrays needed to generate the bad character</span><a href="#l5401"></a>
<span id="l5402">         * shift and the good suffix shift. Only the last seven bits</span><a href="#l5402"></a>
<span id="l5403">         * are used to see if chars match; This keeps the tables small</span><a href="#l5403"></a>
<span id="l5404">         * and covers the heavily used ASCII range, but occasionally</span><a href="#l5404"></a>
<span id="l5405">         * results in an aliased match for the bad character shift.</span><a href="#l5405"></a>
<span id="l5406">         */</span><a href="#l5406"></a>
<span id="l5407">        static Node optimize(Node node) {</span><a href="#l5407"></a>
<span id="l5408">            if (!(node instanceof Slice)) {</span><a href="#l5408"></a>
<span id="l5409">                return node;</span><a href="#l5409"></a>
<span id="l5410">            }</span><a href="#l5410"></a>
<span id="l5411"></span><a href="#l5411"></a>
<span id="l5412">            int[] src = ((Slice) node).buffer;</span><a href="#l5412"></a>
<span id="l5413">            int patternLength = src.length;</span><a href="#l5413"></a>
<span id="l5414">            // The BM algorithm requires a bit of overhead;</span><a href="#l5414"></a>
<span id="l5415">            // If the pattern is short don't use it, since</span><a href="#l5415"></a>
<span id="l5416">            // a shift larger than the pattern length cannot</span><a href="#l5416"></a>
<span id="l5417">            // be used anyway.</span><a href="#l5417"></a>
<span id="l5418">            if (patternLength &lt; 4) {</span><a href="#l5418"></a>
<span id="l5419">                return node;</span><a href="#l5419"></a>
<span id="l5420">            }</span><a href="#l5420"></a>
<span id="l5421">            int i, j, k;</span><a href="#l5421"></a>
<span id="l5422">            int[] lastOcc = new int[128];</span><a href="#l5422"></a>
<span id="l5423">            int[] optoSft = new int[patternLength];</span><a href="#l5423"></a>
<span id="l5424">            // Precalculate part of the bad character shift</span><a href="#l5424"></a>
<span id="l5425">            // It is a table for where in the pattern each</span><a href="#l5425"></a>
<span id="l5426">            // lower 7-bit value occurs</span><a href="#l5426"></a>
<span id="l5427">            for (i = 0; i &lt; patternLength; i++) {</span><a href="#l5427"></a>
<span id="l5428">                lastOcc[src[i]&amp;0x7F] = i + 1;</span><a href="#l5428"></a>
<span id="l5429">            }</span><a href="#l5429"></a>
<span id="l5430">            // Precalculate the good suffix shift</span><a href="#l5430"></a>
<span id="l5431">            // i is the shift amount being considered</span><a href="#l5431"></a>
<span id="l5432">NEXT:       for (i = patternLength; i &gt; 0; i--) {</span><a href="#l5432"></a>
<span id="l5433">                // j is the beginning index of suffix being considered</span><a href="#l5433"></a>
<span id="l5434">                for (j = patternLength - 1; j &gt;= i; j--) {</span><a href="#l5434"></a>
<span id="l5435">                    // Testing for good suffix</span><a href="#l5435"></a>
<span id="l5436">                    if (src[j] == src[j-i]) {</span><a href="#l5436"></a>
<span id="l5437">                        // src[j..len] is a good suffix</span><a href="#l5437"></a>
<span id="l5438">                        optoSft[j-1] = i;</span><a href="#l5438"></a>
<span id="l5439">                    } else {</span><a href="#l5439"></a>
<span id="l5440">                        // No match. The array has already been</span><a href="#l5440"></a>
<span id="l5441">                        // filled up with correct values before.</span><a href="#l5441"></a>
<span id="l5442">                        continue NEXT;</span><a href="#l5442"></a>
<span id="l5443">                    }</span><a href="#l5443"></a>
<span id="l5444">                }</span><a href="#l5444"></a>
<span id="l5445">                // This fills up the remaining of optoSft</span><a href="#l5445"></a>
<span id="l5446">                // any suffix can not have larger shift amount</span><a href="#l5446"></a>
<span id="l5447">                // then its sub-suffix. Why???</span><a href="#l5447"></a>
<span id="l5448">                while (j &gt; 0) {</span><a href="#l5448"></a>
<span id="l5449">                    optoSft[--j] = i;</span><a href="#l5449"></a>
<span id="l5450">                }</span><a href="#l5450"></a>
<span id="l5451">            }</span><a href="#l5451"></a>
<span id="l5452">            // Set the guard value because of unicode compression</span><a href="#l5452"></a>
<span id="l5453">            optoSft[patternLength-1] = 1;</span><a href="#l5453"></a>
<span id="l5454">            if (node instanceof SliceS)</span><a href="#l5454"></a>
<span id="l5455">                return new BnMS(src, lastOcc, optoSft, node.next);</span><a href="#l5455"></a>
<span id="l5456">            return new BnM(src, lastOcc, optoSft, node.next);</span><a href="#l5456"></a>
<span id="l5457">        }</span><a href="#l5457"></a>
<span id="l5458">        BnM(int[] src, int[] lastOcc, int[] optoSft, Node next) {</span><a href="#l5458"></a>
<span id="l5459">            this.buffer = src;</span><a href="#l5459"></a>
<span id="l5460">            this.lastOcc = lastOcc;</span><a href="#l5460"></a>
<span id="l5461">            this.optoSft = optoSft;</span><a href="#l5461"></a>
<span id="l5462">            this.next = next;</span><a href="#l5462"></a>
<span id="l5463">        }</span><a href="#l5463"></a>
<span id="l5464">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l5464"></a>
<span id="l5465">            int[] src = buffer;</span><a href="#l5465"></a>
<span id="l5466">            int patternLength = src.length;</span><a href="#l5466"></a>
<span id="l5467">            int last = matcher.to - patternLength;</span><a href="#l5467"></a>
<span id="l5468"></span><a href="#l5468"></a>
<span id="l5469">            // Loop over all possible match positions in text</span><a href="#l5469"></a>
<span id="l5470">NEXT:       while (i &lt;= last) {</span><a href="#l5470"></a>
<span id="l5471">                // Loop over pattern from right to left</span><a href="#l5471"></a>
<span id="l5472">                for (int j = patternLength - 1; j &gt;= 0; j--) {</span><a href="#l5472"></a>
<span id="l5473">                    int ch = seq.charAt(i+j);</span><a href="#l5473"></a>
<span id="l5474">                    if (ch != src[j]) {</span><a href="#l5474"></a>
<span id="l5475">                        // Shift search to the right by the maximum of the</span><a href="#l5475"></a>
<span id="l5476">                        // bad character shift and the good suffix shift</span><a href="#l5476"></a>
<span id="l5477">                        i += Math.max(j + 1 - lastOcc[ch&amp;0x7F], optoSft[j]);</span><a href="#l5477"></a>
<span id="l5478">                        continue NEXT;</span><a href="#l5478"></a>
<span id="l5479">                    }</span><a href="#l5479"></a>
<span id="l5480">                }</span><a href="#l5480"></a>
<span id="l5481">                // Entire pattern matched starting at i</span><a href="#l5481"></a>
<span id="l5482">                matcher.first = i;</span><a href="#l5482"></a>
<span id="l5483">                boolean ret = next.match(matcher, i + patternLength, seq);</span><a href="#l5483"></a>
<span id="l5484">                if (ret) {</span><a href="#l5484"></a>
<span id="l5485">                    matcher.first = i;</span><a href="#l5485"></a>
<span id="l5486">                    matcher.groups[0] = matcher.first;</span><a href="#l5486"></a>
<span id="l5487">                    matcher.groups[1] = matcher.last;</span><a href="#l5487"></a>
<span id="l5488">                    return true;</span><a href="#l5488"></a>
<span id="l5489">                }</span><a href="#l5489"></a>
<span id="l5490">                i++;</span><a href="#l5490"></a>
<span id="l5491">            }</span><a href="#l5491"></a>
<span id="l5492">            // BnM is only used as the leading node in the unanchored case,</span><a href="#l5492"></a>
<span id="l5493">            // and it replaced its Start() which always searches to the end</span><a href="#l5493"></a>
<span id="l5494">            // if it doesn't find what it's looking for, so hitEnd is true.</span><a href="#l5494"></a>
<span id="l5495">            matcher.hitEnd = true;</span><a href="#l5495"></a>
<span id="l5496">            return false;</span><a href="#l5496"></a>
<span id="l5497">        }</span><a href="#l5497"></a>
<span id="l5498">        boolean study(TreeInfo info) {</span><a href="#l5498"></a>
<span id="l5499">            info.minLength += buffer.length;</span><a href="#l5499"></a>
<span id="l5500">            info.maxValid = false;</span><a href="#l5500"></a>
<span id="l5501">            return next.study(info);</span><a href="#l5501"></a>
<span id="l5502">        }</span><a href="#l5502"></a>
<span id="l5503">    }</span><a href="#l5503"></a>
<span id="l5504"></span><a href="#l5504"></a>
<span id="l5505">    /**</span><a href="#l5505"></a>
<span id="l5506">     * Supplementary support version of BnM(). Unpaired surrogates are</span><a href="#l5506"></a>
<span id="l5507">     * also handled by this class.</span><a href="#l5507"></a>
<span id="l5508">     */</span><a href="#l5508"></a>
<span id="l5509">    static final class BnMS extends BnM {</span><a href="#l5509"></a>
<span id="l5510">        int lengthInChars;</span><a href="#l5510"></a>
<span id="l5511"></span><a href="#l5511"></a>
<span id="l5512">        BnMS(int[] src, int[] lastOcc, int[] optoSft, Node next) {</span><a href="#l5512"></a>
<span id="l5513">            super(src, lastOcc, optoSft, next);</span><a href="#l5513"></a>
<span id="l5514">            for (int x = 0; x &lt; buffer.length; x++) {</span><a href="#l5514"></a>
<span id="l5515">                lengthInChars += Character.charCount(buffer[x]);</span><a href="#l5515"></a>
<span id="l5516">            }</span><a href="#l5516"></a>
<span id="l5517">        }</span><a href="#l5517"></a>
<span id="l5518">        boolean match(Matcher matcher, int i, CharSequence seq) {</span><a href="#l5518"></a>
<span id="l5519">            int[] src = buffer;</span><a href="#l5519"></a>
<span id="l5520">            int patternLength = src.length;</span><a href="#l5520"></a>
<span id="l5521">            int last = matcher.to - lengthInChars;</span><a href="#l5521"></a>
<span id="l5522"></span><a href="#l5522"></a>
<span id="l5523">            // Loop over all possible match positions in text</span><a href="#l5523"></a>
<span id="l5524">NEXT:       while (i &lt;= last) {</span><a href="#l5524"></a>
<span id="l5525">                // Loop over pattern from right to left</span><a href="#l5525"></a>
<span id="l5526">                int ch;</span><a href="#l5526"></a>
<span id="l5527">                for (int j = countChars(seq, i, patternLength), x = patternLength - 1;</span><a href="#l5527"></a>
<span id="l5528">                     j &gt; 0; j -= Character.charCount(ch), x--) {</span><a href="#l5528"></a>
<span id="l5529">                    ch = Character.codePointBefore(seq, i+j);</span><a href="#l5529"></a>
<span id="l5530">                    if (ch != src[x]) {</span><a href="#l5530"></a>
<span id="l5531">                        // Shift search to the right by the maximum of the</span><a href="#l5531"></a>
<span id="l5532">                        // bad character shift and the good suffix shift</span><a href="#l5532"></a>
<span id="l5533">                        int n = Math.max(x + 1 - lastOcc[ch&amp;0x7F], optoSft[x]);</span><a href="#l5533"></a>
<span id="l5534">                        i += countChars(seq, i, n);</span><a href="#l5534"></a>
<span id="l5535">                        continue NEXT;</span><a href="#l5535"></a>
<span id="l5536">                    }</span><a href="#l5536"></a>
<span id="l5537">                }</span><a href="#l5537"></a>
<span id="l5538">                // Entire pattern matched starting at i</span><a href="#l5538"></a>
<span id="l5539">                matcher.first = i;</span><a href="#l5539"></a>
<span id="l5540">                boolean ret = next.match(matcher, i + lengthInChars, seq);</span><a href="#l5540"></a>
<span id="l5541">                if (ret) {</span><a href="#l5541"></a>
<span id="l5542">                    matcher.first = i;</span><a href="#l5542"></a>
<span id="l5543">                    matcher.groups[0] = matcher.first;</span><a href="#l5543"></a>
<span id="l5544">                    matcher.groups[1] = matcher.last;</span><a href="#l5544"></a>
<span id="l5545">                    return true;</span><a href="#l5545"></a>
<span id="l5546">                }</span><a href="#l5546"></a>
<span id="l5547">                i += countChars(seq, i, 1);</span><a href="#l5547"></a>
<span id="l5548">            }</span><a href="#l5548"></a>
<span id="l5549">            matcher.hitEnd = true;</span><a href="#l5549"></a>
<span id="l5550">            return false;</span><a href="#l5550"></a>
<span id="l5551">        }</span><a href="#l5551"></a>
<span id="l5552">    }</span><a href="#l5552"></a>
<span id="l5553"></span><a href="#l5553"></a>
<span id="l5554">///////////////////////////////////////////////////////////////////////////////</span><a href="#l5554"></a>
<span id="l5555">///////////////////////////////////////////////////////////////////////////////</span><a href="#l5555"></a>
<span id="l5556"></span><a href="#l5556"></a>
<span id="l5557">    /**</span><a href="#l5557"></a>
<span id="l5558">     *  This must be the very first initializer.</span><a href="#l5558"></a>
<span id="l5559">     */</span><a href="#l5559"></a>
<span id="l5560">    static Node accept = new Node();</span><a href="#l5560"></a>
<span id="l5561"></span><a href="#l5561"></a>
<span id="l5562">    static Node lastAccept = new LastNode();</span><a href="#l5562"></a>
<span id="l5563"></span><a href="#l5563"></a>
<span id="l5564">    private static class CharPropertyNames {</span><a href="#l5564"></a>
<span id="l5565"></span><a href="#l5565"></a>
<span id="l5566">        static CharProperty charPropertyFor(String name) {</span><a href="#l5566"></a>
<span id="l5567">            CharPropertyFactory m = map.get(name);</span><a href="#l5567"></a>
<span id="l5568">            return m == null ? null : m.make();</span><a href="#l5568"></a>
<span id="l5569">        }</span><a href="#l5569"></a>
<span id="l5570"></span><a href="#l5570"></a>
<span id="l5571">        private static abstract class CharPropertyFactory {</span><a href="#l5571"></a>
<span id="l5572">            abstract CharProperty make();</span><a href="#l5572"></a>
<span id="l5573">        }</span><a href="#l5573"></a>
<span id="l5574"></span><a href="#l5574"></a>
<span id="l5575">        private static void defCategory(String name,</span><a href="#l5575"></a>
<span id="l5576">                                        final int typeMask) {</span><a href="#l5576"></a>
<span id="l5577">            map.put(name, new CharPropertyFactory() {</span><a href="#l5577"></a>
<span id="l5578">                    CharProperty make() { return new Category(typeMask);}});</span><a href="#l5578"></a>
<span id="l5579">        }</span><a href="#l5579"></a>
<span id="l5580"></span><a href="#l5580"></a>
<span id="l5581">        private static void defRange(String name,</span><a href="#l5581"></a>
<span id="l5582">                                     final int lower, final int upper) {</span><a href="#l5582"></a>
<span id="l5583">            map.put(name, new CharPropertyFactory() {</span><a href="#l5583"></a>
<span id="l5584">                    CharProperty make() { return rangeFor(lower, upper);}});</span><a href="#l5584"></a>
<span id="l5585">        }</span><a href="#l5585"></a>
<span id="l5586"></span><a href="#l5586"></a>
<span id="l5587">        private static void defCtype(String name,</span><a href="#l5587"></a>
<span id="l5588">                                     final int ctype) {</span><a href="#l5588"></a>
<span id="l5589">            map.put(name, new CharPropertyFactory() {</span><a href="#l5589"></a>
<span id="l5590">                    CharProperty make() { return new Ctype(ctype);}});</span><a href="#l5590"></a>
<span id="l5591">        }</span><a href="#l5591"></a>
<span id="l5592"></span><a href="#l5592"></a>
<span id="l5593">        private static abstract class CloneableProperty</span><a href="#l5593"></a>
<span id="l5594">            extends CharProperty implements Cloneable</span><a href="#l5594"></a>
<span id="l5595">        {</span><a href="#l5595"></a>
<span id="l5596">            public CloneableProperty clone() {</span><a href="#l5596"></a>
<span id="l5597">                try {</span><a href="#l5597"></a>
<span id="l5598">                    return (CloneableProperty) super.clone();</span><a href="#l5598"></a>
<span id="l5599">                } catch (CloneNotSupportedException e) {</span><a href="#l5599"></a>
<span id="l5600">                    throw new AssertionError(e);</span><a href="#l5600"></a>
<span id="l5601">                }</span><a href="#l5601"></a>
<span id="l5602">            }</span><a href="#l5602"></a>
<span id="l5603">        }</span><a href="#l5603"></a>
<span id="l5604"></span><a href="#l5604"></a>
<span id="l5605">        private static void defClone(String name,</span><a href="#l5605"></a>
<span id="l5606">                                     final CloneableProperty p) {</span><a href="#l5606"></a>
<span id="l5607">            map.put(name, new CharPropertyFactory() {</span><a href="#l5607"></a>
<span id="l5608">                    CharProperty make() { return p.clone();}});</span><a href="#l5608"></a>
<span id="l5609">        }</span><a href="#l5609"></a>
<span id="l5610"></span><a href="#l5610"></a>
<span id="l5611">        private static final HashMap&lt;String, CharPropertyFactory&gt; map</span><a href="#l5611"></a>
<span id="l5612">            = new HashMap&lt;&gt;();</span><a href="#l5612"></a>
<span id="l5613"></span><a href="#l5613"></a>
<span id="l5614">        static {</span><a href="#l5614"></a>
<span id="l5615">            // Unicode character property aliases, defined in</span><a href="#l5615"></a>
<span id="l5616">            // http://www.unicode.org/Public/UNIDATA/PropertyValueAliases.txt</span><a href="#l5616"></a>
<span id="l5617">            defCategory(&quot;Cn&quot;, 1&lt;&lt;Character.UNASSIGNED);</span><a href="#l5617"></a>
<span id="l5618">            defCategory(&quot;Lu&quot;, 1&lt;&lt;Character.UPPERCASE_LETTER);</span><a href="#l5618"></a>
<span id="l5619">            defCategory(&quot;Ll&quot;, 1&lt;&lt;Character.LOWERCASE_LETTER);</span><a href="#l5619"></a>
<span id="l5620">            defCategory(&quot;Lt&quot;, 1&lt;&lt;Character.TITLECASE_LETTER);</span><a href="#l5620"></a>
<span id="l5621">            defCategory(&quot;Lm&quot;, 1&lt;&lt;Character.MODIFIER_LETTER);</span><a href="#l5621"></a>
<span id="l5622">            defCategory(&quot;Lo&quot;, 1&lt;&lt;Character.OTHER_LETTER);</span><a href="#l5622"></a>
<span id="l5623">            defCategory(&quot;Mn&quot;, 1&lt;&lt;Character.NON_SPACING_MARK);</span><a href="#l5623"></a>
<span id="l5624">            defCategory(&quot;Me&quot;, 1&lt;&lt;Character.ENCLOSING_MARK);</span><a href="#l5624"></a>
<span id="l5625">            defCategory(&quot;Mc&quot;, 1&lt;&lt;Character.COMBINING_SPACING_MARK);</span><a href="#l5625"></a>
<span id="l5626">            defCategory(&quot;Nd&quot;, 1&lt;&lt;Character.DECIMAL_DIGIT_NUMBER);</span><a href="#l5626"></a>
<span id="l5627">            defCategory(&quot;Nl&quot;, 1&lt;&lt;Character.LETTER_NUMBER);</span><a href="#l5627"></a>
<span id="l5628">            defCategory(&quot;No&quot;, 1&lt;&lt;Character.OTHER_NUMBER);</span><a href="#l5628"></a>
<span id="l5629">            defCategory(&quot;Zs&quot;, 1&lt;&lt;Character.SPACE_SEPARATOR);</span><a href="#l5629"></a>
<span id="l5630">            defCategory(&quot;Zl&quot;, 1&lt;&lt;Character.LINE_SEPARATOR);</span><a href="#l5630"></a>
<span id="l5631">            defCategory(&quot;Zp&quot;, 1&lt;&lt;Character.PARAGRAPH_SEPARATOR);</span><a href="#l5631"></a>
<span id="l5632">            defCategory(&quot;Cc&quot;, 1&lt;&lt;Character.CONTROL);</span><a href="#l5632"></a>
<span id="l5633">            defCategory(&quot;Cf&quot;, 1&lt;&lt;Character.FORMAT);</span><a href="#l5633"></a>
<span id="l5634">            defCategory(&quot;Co&quot;, 1&lt;&lt;Character.PRIVATE_USE);</span><a href="#l5634"></a>
<span id="l5635">            defCategory(&quot;Cs&quot;, 1&lt;&lt;Character.SURROGATE);</span><a href="#l5635"></a>
<span id="l5636">            defCategory(&quot;Pd&quot;, 1&lt;&lt;Character.DASH_PUNCTUATION);</span><a href="#l5636"></a>
<span id="l5637">            defCategory(&quot;Ps&quot;, 1&lt;&lt;Character.START_PUNCTUATION);</span><a href="#l5637"></a>
<span id="l5638">            defCategory(&quot;Pe&quot;, 1&lt;&lt;Character.END_PUNCTUATION);</span><a href="#l5638"></a>
<span id="l5639">            defCategory(&quot;Pc&quot;, 1&lt;&lt;Character.CONNECTOR_PUNCTUATION);</span><a href="#l5639"></a>
<span id="l5640">            defCategory(&quot;Po&quot;, 1&lt;&lt;Character.OTHER_PUNCTUATION);</span><a href="#l5640"></a>
<span id="l5641">            defCategory(&quot;Sm&quot;, 1&lt;&lt;Character.MATH_SYMBOL);</span><a href="#l5641"></a>
<span id="l5642">            defCategory(&quot;Sc&quot;, 1&lt;&lt;Character.CURRENCY_SYMBOL);</span><a href="#l5642"></a>
<span id="l5643">            defCategory(&quot;Sk&quot;, 1&lt;&lt;Character.MODIFIER_SYMBOL);</span><a href="#l5643"></a>
<span id="l5644">            defCategory(&quot;So&quot;, 1&lt;&lt;Character.OTHER_SYMBOL);</span><a href="#l5644"></a>
<span id="l5645">            defCategory(&quot;Pi&quot;, 1&lt;&lt;Character.INITIAL_QUOTE_PUNCTUATION);</span><a href="#l5645"></a>
<span id="l5646">            defCategory(&quot;Pf&quot;, 1&lt;&lt;Character.FINAL_QUOTE_PUNCTUATION);</span><a href="#l5646"></a>
<span id="l5647">            defCategory(&quot;L&quot;, ((1&lt;&lt;Character.UPPERCASE_LETTER) |</span><a href="#l5647"></a>
<span id="l5648">                              (1&lt;&lt;Character.LOWERCASE_LETTER) |</span><a href="#l5648"></a>
<span id="l5649">                              (1&lt;&lt;Character.TITLECASE_LETTER) |</span><a href="#l5649"></a>
<span id="l5650">                              (1&lt;&lt;Character.MODIFIER_LETTER)  |</span><a href="#l5650"></a>
<span id="l5651">                              (1&lt;&lt;Character.OTHER_LETTER)));</span><a href="#l5651"></a>
<span id="l5652">            defCategory(&quot;M&quot;, ((1&lt;&lt;Character.NON_SPACING_MARK) |</span><a href="#l5652"></a>
<span id="l5653">                              (1&lt;&lt;Character.ENCLOSING_MARK)   |</span><a href="#l5653"></a>
<span id="l5654">                              (1&lt;&lt;Character.COMBINING_SPACING_MARK)));</span><a href="#l5654"></a>
<span id="l5655">            defCategory(&quot;N&quot;, ((1&lt;&lt;Character.DECIMAL_DIGIT_NUMBER) |</span><a href="#l5655"></a>
<span id="l5656">                              (1&lt;&lt;Character.LETTER_NUMBER)        |</span><a href="#l5656"></a>
<span id="l5657">                              (1&lt;&lt;Character.OTHER_NUMBER)));</span><a href="#l5657"></a>
<span id="l5658">            defCategory(&quot;Z&quot;, ((1&lt;&lt;Character.SPACE_SEPARATOR) |</span><a href="#l5658"></a>
<span id="l5659">                              (1&lt;&lt;Character.LINE_SEPARATOR)  |</span><a href="#l5659"></a>
<span id="l5660">                              (1&lt;&lt;Character.PARAGRAPH_SEPARATOR)));</span><a href="#l5660"></a>
<span id="l5661">            defCategory(&quot;C&quot;, ((1&lt;&lt;Character.CONTROL)     |</span><a href="#l5661"></a>
<span id="l5662">                              (1&lt;&lt;Character.FORMAT)      |</span><a href="#l5662"></a>
<span id="l5663">                              (1&lt;&lt;Character.PRIVATE_USE) |</span><a href="#l5663"></a>
<span id="l5664">                              (1&lt;&lt;Character.SURROGATE))); // Other</span><a href="#l5664"></a>
<span id="l5665">            defCategory(&quot;P&quot;, ((1&lt;&lt;Character.DASH_PUNCTUATION)      |</span><a href="#l5665"></a>
<span id="l5666">                              (1&lt;&lt;Character.START_PUNCTUATION)     |</span><a href="#l5666"></a>
<span id="l5667">                              (1&lt;&lt;Character.END_PUNCTUATION)       |</span><a href="#l5667"></a>
<span id="l5668">                              (1&lt;&lt;Character.CONNECTOR_PUNCTUATION) |</span><a href="#l5668"></a>
<span id="l5669">                              (1&lt;&lt;Character.OTHER_PUNCTUATION)     |</span><a href="#l5669"></a>
<span id="l5670">                              (1&lt;&lt;Character.INITIAL_QUOTE_PUNCTUATION) |</span><a href="#l5670"></a>
<span id="l5671">                              (1&lt;&lt;Character.FINAL_QUOTE_PUNCTUATION)));</span><a href="#l5671"></a>
<span id="l5672">            defCategory(&quot;S&quot;, ((1&lt;&lt;Character.MATH_SYMBOL)     |</span><a href="#l5672"></a>
<span id="l5673">                              (1&lt;&lt;Character.CURRENCY_SYMBOL) |</span><a href="#l5673"></a>
<span id="l5674">                              (1&lt;&lt;Character.MODIFIER_SYMBOL) |</span><a href="#l5674"></a>
<span id="l5675">                              (1&lt;&lt;Character.OTHER_SYMBOL)));</span><a href="#l5675"></a>
<span id="l5676">            defCategory(&quot;LC&quot;, ((1&lt;&lt;Character.UPPERCASE_LETTER) |</span><a href="#l5676"></a>
<span id="l5677">                               (1&lt;&lt;Character.LOWERCASE_LETTER) |</span><a href="#l5677"></a>
<span id="l5678">                               (1&lt;&lt;Character.TITLECASE_LETTER)));</span><a href="#l5678"></a>
<span id="l5679">            defCategory(&quot;LD&quot;, ((1&lt;&lt;Character.UPPERCASE_LETTER) |</span><a href="#l5679"></a>
<span id="l5680">                               (1&lt;&lt;Character.LOWERCASE_LETTER) |</span><a href="#l5680"></a>
<span id="l5681">                               (1&lt;&lt;Character.TITLECASE_LETTER) |</span><a href="#l5681"></a>
<span id="l5682">                               (1&lt;&lt;Character.MODIFIER_LETTER)  |</span><a href="#l5682"></a>
<span id="l5683">                               (1&lt;&lt;Character.OTHER_LETTER)     |</span><a href="#l5683"></a>
<span id="l5684">                               (1&lt;&lt;Character.DECIMAL_DIGIT_NUMBER)));</span><a href="#l5684"></a>
<span id="l5685">            defRange(&quot;L1&quot;, 0x00, 0xFF); // Latin-1</span><a href="#l5685"></a>
<span id="l5686">            map.put(&quot;all&quot;, new CharPropertyFactory() {</span><a href="#l5686"></a>
<span id="l5687">                    CharProperty make() { return new All(); }});</span><a href="#l5687"></a>
<span id="l5688"></span><a href="#l5688"></a>
<span id="l5689">            // Posix regular expression character classes, defined in</span><a href="#l5689"></a>
<span id="l5690">            // http://www.unix.org/onlinepubs/009695399/basedefs/xbd_chap09.html</span><a href="#l5690"></a>
<span id="l5691">            defRange(&quot;ASCII&quot;, 0x00, 0x7F);   // ASCII</span><a href="#l5691"></a>
<span id="l5692">            defCtype(&quot;Alnum&quot;, ASCII.ALNUM);  // Alphanumeric characters</span><a href="#l5692"></a>
<span id="l5693">            defCtype(&quot;Alpha&quot;, ASCII.ALPHA);  // Alphabetic characters</span><a href="#l5693"></a>
<span id="l5694">            defCtype(&quot;Blank&quot;, ASCII.BLANK);  // Space and tab characters</span><a href="#l5694"></a>
<span id="l5695">            defCtype(&quot;Cntrl&quot;, ASCII.CNTRL);  // Control characters</span><a href="#l5695"></a>
<span id="l5696">            defRange(&quot;Digit&quot;, '0', '9');     // Numeric characters</span><a href="#l5696"></a>
<span id="l5697">            defCtype(&quot;Graph&quot;, ASCII.GRAPH);  // printable and visible</span><a href="#l5697"></a>
<span id="l5698">            defRange(&quot;Lower&quot;, 'a', 'z');     // Lower-case alphabetic</span><a href="#l5698"></a>
<span id="l5699">            defRange(&quot;Print&quot;, 0x20, 0x7E);   // Printable characters</span><a href="#l5699"></a>
<span id="l5700">            defCtype(&quot;Punct&quot;, ASCII.PUNCT);  // Punctuation characters</span><a href="#l5700"></a>
<span id="l5701">            defCtype(&quot;Space&quot;, ASCII.SPACE);  // Space characters</span><a href="#l5701"></a>
<span id="l5702">            defRange(&quot;Upper&quot;, 'A', 'Z');     // Upper-case alphabetic</span><a href="#l5702"></a>
<span id="l5703">            defCtype(&quot;XDigit&quot;,ASCII.XDIGIT); // hexadecimal digits</span><a href="#l5703"></a>
<span id="l5704"></span><a href="#l5704"></a>
<span id="l5705">            // Java character properties, defined by methods in Character.java</span><a href="#l5705"></a>
<span id="l5706">            defClone(&quot;javaLowerCase&quot;, new CloneableProperty() {</span><a href="#l5706"></a>
<span id="l5707">                boolean isSatisfiedBy(int ch) {</span><a href="#l5707"></a>
<span id="l5708">                    return Character.isLowerCase(ch);}});</span><a href="#l5708"></a>
<span id="l5709">            defClone(&quot;javaUpperCase&quot;, new CloneableProperty() {</span><a href="#l5709"></a>
<span id="l5710">                boolean isSatisfiedBy(int ch) {</span><a href="#l5710"></a>
<span id="l5711">                    return Character.isUpperCase(ch);}});</span><a href="#l5711"></a>
<span id="l5712">            defClone(&quot;javaAlphabetic&quot;, new CloneableProperty() {</span><a href="#l5712"></a>
<span id="l5713">                boolean isSatisfiedBy(int ch) {</span><a href="#l5713"></a>
<span id="l5714">                    return Character.isAlphabetic(ch);}});</span><a href="#l5714"></a>
<span id="l5715">            defClone(&quot;javaIdeographic&quot;, new CloneableProperty() {</span><a href="#l5715"></a>
<span id="l5716">                boolean isSatisfiedBy(int ch) {</span><a href="#l5716"></a>
<span id="l5717">                    return Character.isIdeographic(ch);}});</span><a href="#l5717"></a>
<span id="l5718">            defClone(&quot;javaTitleCase&quot;, new CloneableProperty() {</span><a href="#l5718"></a>
<span id="l5719">                boolean isSatisfiedBy(int ch) {</span><a href="#l5719"></a>
<span id="l5720">                    return Character.isTitleCase(ch);}});</span><a href="#l5720"></a>
<span id="l5721">            defClone(&quot;javaDigit&quot;, new CloneableProperty() {</span><a href="#l5721"></a>
<span id="l5722">                boolean isSatisfiedBy(int ch) {</span><a href="#l5722"></a>
<span id="l5723">                    return Character.isDigit(ch);}});</span><a href="#l5723"></a>
<span id="l5724">            defClone(&quot;javaDefined&quot;, new CloneableProperty() {</span><a href="#l5724"></a>
<span id="l5725">                boolean isSatisfiedBy(int ch) {</span><a href="#l5725"></a>
<span id="l5726">                    return Character.isDefined(ch);}});</span><a href="#l5726"></a>
<span id="l5727">            defClone(&quot;javaLetter&quot;, new CloneableProperty() {</span><a href="#l5727"></a>
<span id="l5728">                boolean isSatisfiedBy(int ch) {</span><a href="#l5728"></a>
<span id="l5729">                    return Character.isLetter(ch);}});</span><a href="#l5729"></a>
<span id="l5730">            defClone(&quot;javaLetterOrDigit&quot;, new CloneableProperty() {</span><a href="#l5730"></a>
<span id="l5731">                boolean isSatisfiedBy(int ch) {</span><a href="#l5731"></a>
<span id="l5732">                    return Character.isLetterOrDigit(ch);}});</span><a href="#l5732"></a>
<span id="l5733">            defClone(&quot;javaJavaIdentifierStart&quot;, new CloneableProperty() {</span><a href="#l5733"></a>
<span id="l5734">                boolean isSatisfiedBy(int ch) {</span><a href="#l5734"></a>
<span id="l5735">                    return Character.isJavaIdentifierStart(ch);}});</span><a href="#l5735"></a>
<span id="l5736">            defClone(&quot;javaJavaIdentifierPart&quot;, new CloneableProperty() {</span><a href="#l5736"></a>
<span id="l5737">                boolean isSatisfiedBy(int ch) {</span><a href="#l5737"></a>
<span id="l5738">                    return Character.isJavaIdentifierPart(ch);}});</span><a href="#l5738"></a>
<span id="l5739">            defClone(&quot;javaUnicodeIdentifierStart&quot;, new CloneableProperty() {</span><a href="#l5739"></a>
<span id="l5740">                boolean isSatisfiedBy(int ch) {</span><a href="#l5740"></a>
<span id="l5741">                    return Character.isUnicodeIdentifierStart(ch);}});</span><a href="#l5741"></a>
<span id="l5742">            defClone(&quot;javaUnicodeIdentifierPart&quot;, new CloneableProperty() {</span><a href="#l5742"></a>
<span id="l5743">                boolean isSatisfiedBy(int ch) {</span><a href="#l5743"></a>
<span id="l5744">                    return Character.isUnicodeIdentifierPart(ch);}});</span><a href="#l5744"></a>
<span id="l5745">            defClone(&quot;javaIdentifierIgnorable&quot;, new CloneableProperty() {</span><a href="#l5745"></a>
<span id="l5746">                boolean isSatisfiedBy(int ch) {</span><a href="#l5746"></a>
<span id="l5747">                    return Character.isIdentifierIgnorable(ch);}});</span><a href="#l5747"></a>
<span id="l5748">            defClone(&quot;javaSpaceChar&quot;, new CloneableProperty() {</span><a href="#l5748"></a>
<span id="l5749">                boolean isSatisfiedBy(int ch) {</span><a href="#l5749"></a>
<span id="l5750">                    return Character.isSpaceChar(ch);}});</span><a href="#l5750"></a>
<span id="l5751">            defClone(&quot;javaWhitespace&quot;, new CloneableProperty() {</span><a href="#l5751"></a>
<span id="l5752">                boolean isSatisfiedBy(int ch) {</span><a href="#l5752"></a>
<span id="l5753">                    return Character.isWhitespace(ch);}});</span><a href="#l5753"></a>
<span id="l5754">            defClone(&quot;javaISOControl&quot;, new CloneableProperty() {</span><a href="#l5754"></a>
<span id="l5755">                boolean isSatisfiedBy(int ch) {</span><a href="#l5755"></a>
<span id="l5756">                    return Character.isISOControl(ch);}});</span><a href="#l5756"></a>
<span id="l5757">            defClone(&quot;javaMirrored&quot;, new CloneableProperty() {</span><a href="#l5757"></a>
<span id="l5758">                boolean isSatisfiedBy(int ch) {</span><a href="#l5758"></a>
<span id="l5759">                    return Character.isMirrored(ch);}});</span><a href="#l5759"></a>
<span id="l5760">        }</span><a href="#l5760"></a>
<span id="l5761">    }</span><a href="#l5761"></a>
<span id="l5762"></span><a href="#l5762"></a>
<span id="l5763">    /**</span><a href="#l5763"></a>
<span id="l5764">     * Creates a predicate which can be used to match a string.</span><a href="#l5764"></a>
<span id="l5765">     *</span><a href="#l5765"></a>
<span id="l5766">     * @return  The predicate which can be used for matching on a string</span><a href="#l5766"></a>
<span id="l5767">     * @since   1.8</span><a href="#l5767"></a>
<span id="l5768">     */</span><a href="#l5768"></a>
<span id="l5769">    public Predicate&lt;String&gt; asPredicate() {</span><a href="#l5769"></a>
<span id="l5770">        return s -&gt; matcher(s).find();</span><a href="#l5770"></a>
<span id="l5771">    }</span><a href="#l5771"></a>
<span id="l5772"></span><a href="#l5772"></a>
<span id="l5773">    /**</span><a href="#l5773"></a>
<span id="l5774">     * Creates a stream from the given input sequence around matches of this</span><a href="#l5774"></a>
<span id="l5775">     * pattern.</span><a href="#l5775"></a>
<span id="l5776">     *</span><a href="#l5776"></a>
<span id="l5777">     * &lt;p&gt; The stream returned by this method contains each substring of the</span><a href="#l5777"></a>
<span id="l5778">     * input sequence that is terminated by another subsequence that matches</span><a href="#l5778"></a>
<span id="l5779">     * this pattern or is terminated by the end of the input sequence.  The</span><a href="#l5779"></a>
<span id="l5780">     * substrings in the stream are in the order in which they occur in the</span><a href="#l5780"></a>
<span id="l5781">     * input. Trailing empty strings will be discarded and not encountered in</span><a href="#l5781"></a>
<span id="l5782">     * the stream.</span><a href="#l5782"></a>
<span id="l5783">     *</span><a href="#l5783"></a>
<span id="l5784">     * &lt;p&gt; If this pattern does not match any subsequence of the input then</span><a href="#l5784"></a>
<span id="l5785">     * the resulting stream has just one element, namely the input sequence in</span><a href="#l5785"></a>
<span id="l5786">     * string form.</span><a href="#l5786"></a>
<span id="l5787">     *</span><a href="#l5787"></a>
<span id="l5788">     * &lt;p&gt; When there is a positive-width match at the beginning of the input</span><a href="#l5788"></a>
<span id="l5789">     * sequence then an empty leading substring is included at the beginning</span><a href="#l5789"></a>
<span id="l5790">     * of the stream. A zero-width match at the beginning however never produces</span><a href="#l5790"></a>
<span id="l5791">     * such empty leading substring.</span><a href="#l5791"></a>
<span id="l5792">     *</span><a href="#l5792"></a>
<span id="l5793">     * &lt;p&gt; If the input sequence is mutable, it must remain constant during the</span><a href="#l5793"></a>
<span id="l5794">     * execution of the terminal stream operation.  Otherwise, the result of the</span><a href="#l5794"></a>
<span id="l5795">     * terminal stream operation is undefined.</span><a href="#l5795"></a>
<span id="l5796">     *</span><a href="#l5796"></a>
<span id="l5797">     * @param   input</span><a href="#l5797"></a>
<span id="l5798">     *          The character sequence to be split</span><a href="#l5798"></a>
<span id="l5799">     *</span><a href="#l5799"></a>
<span id="l5800">     * @return  The stream of strings computed by splitting the input</span><a href="#l5800"></a>
<span id="l5801">     *          around matches of this pattern</span><a href="#l5801"></a>
<span id="l5802">     * @see     #split(CharSequence)</span><a href="#l5802"></a>
<span id="l5803">     * @since   1.8</span><a href="#l5803"></a>
<span id="l5804">     */</span><a href="#l5804"></a>
<span id="l5805">    public Stream&lt;String&gt; splitAsStream(final CharSequence input) {</span><a href="#l5805"></a>
<span id="l5806">        class MatcherIterator implements Iterator&lt;String&gt; {</span><a href="#l5806"></a>
<span id="l5807">            private final Matcher matcher;</span><a href="#l5807"></a>
<span id="l5808">            // The start position of the next sub-sequence of input</span><a href="#l5808"></a>
<span id="l5809">            // when current == input.length there are no more elements</span><a href="#l5809"></a>
<span id="l5810">            private int current;</span><a href="#l5810"></a>
<span id="l5811">            // null if the next element, if any, needs to obtained</span><a href="#l5811"></a>
<span id="l5812">            private String nextElement;</span><a href="#l5812"></a>
<span id="l5813">            // &gt; 0 if there are N next empty elements</span><a href="#l5813"></a>
<span id="l5814">            private int emptyElementCount;</span><a href="#l5814"></a>
<span id="l5815"></span><a href="#l5815"></a>
<span id="l5816">            MatcherIterator() {</span><a href="#l5816"></a>
<span id="l5817">                this.matcher = matcher(input);</span><a href="#l5817"></a>
<span id="l5818">            }</span><a href="#l5818"></a>
<span id="l5819"></span><a href="#l5819"></a>
<span id="l5820">            public String next() {</span><a href="#l5820"></a>
<span id="l5821">                if (!hasNext())</span><a href="#l5821"></a>
<span id="l5822">                    throw new NoSuchElementException();</span><a href="#l5822"></a>
<span id="l5823"></span><a href="#l5823"></a>
<span id="l5824">                if (emptyElementCount == 0) {</span><a href="#l5824"></a>
<span id="l5825">                    String n = nextElement;</span><a href="#l5825"></a>
<span id="l5826">                    nextElement = null;</span><a href="#l5826"></a>
<span id="l5827">                    return n;</span><a href="#l5827"></a>
<span id="l5828">                } else {</span><a href="#l5828"></a>
<span id="l5829">                    emptyElementCount--;</span><a href="#l5829"></a>
<span id="l5830">                    return &quot;&quot;;</span><a href="#l5830"></a>
<span id="l5831">                }</span><a href="#l5831"></a>
<span id="l5832">            }</span><a href="#l5832"></a>
<span id="l5833"></span><a href="#l5833"></a>
<span id="l5834">            public boolean hasNext() {</span><a href="#l5834"></a>
<span id="l5835">                if (nextElement != null || emptyElementCount &gt; 0)</span><a href="#l5835"></a>
<span id="l5836">                    return true;</span><a href="#l5836"></a>
<span id="l5837"></span><a href="#l5837"></a>
<span id="l5838">                if (current == input.length())</span><a href="#l5838"></a>
<span id="l5839">                    return false;</span><a href="#l5839"></a>
<span id="l5840"></span><a href="#l5840"></a>
<span id="l5841">                // Consume the next matching element</span><a href="#l5841"></a>
<span id="l5842">                // Count sequence of matching empty elements</span><a href="#l5842"></a>
<span id="l5843">                while (matcher.find()) {</span><a href="#l5843"></a>
<span id="l5844">                    nextElement = input.subSequence(current, matcher.start()).toString();</span><a href="#l5844"></a>
<span id="l5845">                    current = matcher.end();</span><a href="#l5845"></a>
<span id="l5846">                    if (!nextElement.isEmpty()) {</span><a href="#l5846"></a>
<span id="l5847">                        return true;</span><a href="#l5847"></a>
<span id="l5848">                    } else if (current &gt; 0) { // no empty leading substring for zero-width</span><a href="#l5848"></a>
<span id="l5849">                                              // match at the beginning of the input</span><a href="#l5849"></a>
<span id="l5850">                        emptyElementCount++;</span><a href="#l5850"></a>
<span id="l5851">                    }</span><a href="#l5851"></a>
<span id="l5852">                }</span><a href="#l5852"></a>
<span id="l5853"></span><a href="#l5853"></a>
<span id="l5854">                // Consume last matching element</span><a href="#l5854"></a>
<span id="l5855">                nextElement = input.subSequence(current, input.length()).toString();</span><a href="#l5855"></a>
<span id="l5856">                current = input.length();</span><a href="#l5856"></a>
<span id="l5857">                if (!nextElement.isEmpty()) {</span><a href="#l5857"></a>
<span id="l5858">                    return true;</span><a href="#l5858"></a>
<span id="l5859">                } else {</span><a href="#l5859"></a>
<span id="l5860">                    // Ignore a terminal sequence of matching empty elements</span><a href="#l5860"></a>
<span id="l5861">                    emptyElementCount = 0;</span><a href="#l5861"></a>
<span id="l5862">                    nextElement = null;</span><a href="#l5862"></a>
<span id="l5863">                    return false;</span><a href="#l5863"></a>
<span id="l5864">                }</span><a href="#l5864"></a>
<span id="l5865">            }</span><a href="#l5865"></a>
<span id="l5866">        }</span><a href="#l5866"></a>
<span id="l5867">        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(</span><a href="#l5867"></a>
<span id="l5868">                new MatcherIterator(), Spliterator.ORDERED | Spliterator.NONNULL), false);</span><a href="#l5868"></a>
<span id="l5869">    }</span><a href="#l5869"></a>
<span id="l5870">}</span><a href="#l5870"></a></pre>
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

