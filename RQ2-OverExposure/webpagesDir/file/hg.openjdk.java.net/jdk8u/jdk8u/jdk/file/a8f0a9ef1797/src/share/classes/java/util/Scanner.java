<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: a8f0a9ef1797 src/share/classes/java/util/Scanner.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk8u/jdk8u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/shortlog/a8f0a9ef1797">log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/graph/a8f0a9ef1797">graph</a></li>
<li><a href="/jdk8u/jdk8u/jdk/tags">tags</a></li>
<li><a href="/jdk8u/jdk8u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/rev/a8f0a9ef1797">changeset</a></li>
<li><a href="/jdk8u/jdk8u/jdk/file/a8f0a9ef1797/src/share/classes/java/util/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/java/util/Scanner.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/a8f0a9ef1797/src/share/classes/java/util/Scanner.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/a8f0a9ef1797/src/share/classes/java/util/Scanner.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/a8f0a9ef1797/src/share/classes/java/util/Scanner.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/a8f0a9ef1797/src/share/classes/java/util/Scanner.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/a8f0a9ef1797/src/share/classes/java/util/Scanner.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/java/util/Scanner.java @ 13897:a8f0a9ef1797</h3>

<form class="search" action="/jdk8u/jdk8u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk8u/jdk8u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8236201: Better Scanner conversions
Reviewed-by: ahgross, rhalade, rriggs, skoivu, smarks, andrew</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#105;&#103;&#101;&#114;&#97;&#115;&#105;&#109;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Thu, 30 Jan 2020 01:15:13 -0800</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/1a3de3cdc684/src/share/classes/java/util/Scanner.java">1a3de3cdc684</a> </td>
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
<span id="l26">package java.util;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.nio.file.Path;</span><a href="#l28"></a>
<span id="l29">import java.nio.file.Files;</span><a href="#l29"></a>
<span id="l30">import java.util.regex.*;</span><a href="#l30"></a>
<span id="l31">import java.io.*;</span><a href="#l31"></a>
<span id="l32">import java.math.*;</span><a href="#l32"></a>
<span id="l33">import java.nio.*;</span><a href="#l33"></a>
<span id="l34">import java.nio.channels.*;</span><a href="#l34"></a>
<span id="l35">import java.nio.charset.*;</span><a href="#l35"></a>
<span id="l36">import java.text.*;</span><a href="#l36"></a>
<span id="l37">import java.util.Locale;</span><a href="#l37"></a>
<span id="l38"></span><a href="#l38"></a>
<span id="l39">import sun.misc.LRUCache;</span><a href="#l39"></a>
<span id="l40"></span><a href="#l40"></a>
<span id="l41">/**</span><a href="#l41"></a>
<span id="l42"> * A simple text scanner which can parse primitive types and strings using</span><a href="#l42"></a>
<span id="l43"> * regular expressions.</span><a href="#l43"></a>
<span id="l44"> *</span><a href="#l44"></a>
<span id="l45"> * &lt;p&gt;A &lt;code&gt;Scanner&lt;/code&gt; breaks its input into tokens using a</span><a href="#l45"></a>
<span id="l46"> * delimiter pattern, which by default matches whitespace. The resulting</span><a href="#l46"></a>
<span id="l47"> * tokens may then be converted into values of different types using the</span><a href="#l47"></a>
<span id="l48"> * various &lt;tt&gt;next&lt;/tt&gt; methods.</span><a href="#l48"></a>
<span id="l49"> *</span><a href="#l49"></a>
<span id="l50"> * &lt;p&gt;For example, this code allows a user to read a number from</span><a href="#l50"></a>
<span id="l51"> * &lt;tt&gt;System.in&lt;/tt&gt;:</span><a href="#l51"></a>
<span id="l52"> * &lt;blockquote&gt;&lt;pre&gt;{@code</span><a href="#l52"></a>
<span id="l53"> *     Scanner sc = new Scanner(System.in);</span><a href="#l53"></a>
<span id="l54"> *     int i = sc.nextInt();</span><a href="#l54"></a>
<span id="l55"> * }&lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l55"></a>
<span id="l56"> *</span><a href="#l56"></a>
<span id="l57"> * &lt;p&gt;As another example, this code allows &lt;code&gt;long&lt;/code&gt; types to be</span><a href="#l57"></a>
<span id="l58"> * assigned from entries in a file &lt;code&gt;myNumbers&lt;/code&gt;:</span><a href="#l58"></a>
<span id="l59"> * &lt;blockquote&gt;&lt;pre&gt;{@code</span><a href="#l59"></a>
<span id="l60"> *      Scanner sc = new Scanner(new File(&quot;myNumbers&quot;));</span><a href="#l60"></a>
<span id="l61"> *      while (sc.hasNextLong()) {</span><a href="#l61"></a>
<span id="l62"> *          long aLong = sc.nextLong();</span><a href="#l62"></a>
<span id="l63"> *      }</span><a href="#l63"></a>
<span id="l64"> * }&lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l64"></a>
<span id="l65"> *</span><a href="#l65"></a>
<span id="l66"> * &lt;p&gt;The scanner can also use delimiters other than whitespace. This</span><a href="#l66"></a>
<span id="l67"> * example reads several items in from a string:</span><a href="#l67"></a>
<span id="l68"> * &lt;blockquote&gt;&lt;pre&gt;{@code</span><a href="#l68"></a>
<span id="l69"> *     String input = &quot;1 fish 2 fish red fish blue fish&quot;;</span><a href="#l69"></a>
<span id="l70"> *     Scanner s = new Scanner(input).useDelimiter(&quot;\\s*fish\\s*&quot;);</span><a href="#l70"></a>
<span id="l71"> *     System.out.println(s.nextInt());</span><a href="#l71"></a>
<span id="l72"> *     System.out.println(s.nextInt());</span><a href="#l72"></a>
<span id="l73"> *     System.out.println(s.next());</span><a href="#l73"></a>
<span id="l74"> *     System.out.println(s.next());</span><a href="#l74"></a>
<span id="l75"> *     s.close();</span><a href="#l75"></a>
<span id="l76"> * }&lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l76"></a>
<span id="l77"> * &lt;p&gt;</span><a href="#l77"></a>
<span id="l78"> * prints the following output:</span><a href="#l78"></a>
<span id="l79"> * &lt;blockquote&gt;&lt;pre&gt;{@code</span><a href="#l79"></a>
<span id="l80"> *     1</span><a href="#l80"></a>
<span id="l81"> *     2</span><a href="#l81"></a>
<span id="l82"> *     red</span><a href="#l82"></a>
<span id="l83"> *     blue</span><a href="#l83"></a>
<span id="l84"> * }&lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l84"></a>
<span id="l85"> *</span><a href="#l85"></a>
<span id="l86"> * &lt;p&gt;The same output can be generated with this code, which uses a regular</span><a href="#l86"></a>
<span id="l87"> * expression to parse all four tokens at once:</span><a href="#l87"></a>
<span id="l88"> * &lt;blockquote&gt;&lt;pre&gt;{@code</span><a href="#l88"></a>
<span id="l89"> *     String input = &quot;1 fish 2 fish red fish blue fish&quot;;</span><a href="#l89"></a>
<span id="l90"> *     Scanner s = new Scanner(input);</span><a href="#l90"></a>
<span id="l91"> *     s.findInLine(&quot;(\\d+) fish (\\d+) fish (\\w+) fish (\\w+)&quot;);</span><a href="#l91"></a>
<span id="l92"> *     MatchResult result = s.match();</span><a href="#l92"></a>
<span id="l93"> *     for (int i=1; i&lt;=result.groupCount(); i++)</span><a href="#l93"></a>
<span id="l94"> *         System.out.println(result.group(i));</span><a href="#l94"></a>
<span id="l95"> *     s.close();</span><a href="#l95"></a>
<span id="l96"> * }&lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l96"></a>
<span id="l97"> *</span><a href="#l97"></a>
<span id="l98"> * &lt;p&gt;The &lt;a name=&quot;default-delimiter&quot;&gt;default whitespace delimiter&lt;/a&gt; used</span><a href="#l98"></a>
<span id="l99"> * by a scanner is as recognized by {@link java.lang.Character}.{@link</span><a href="#l99"></a>
<span id="l100"> * java.lang.Character#isWhitespace(char) isWhitespace}. The {@link #reset}</span><a href="#l100"></a>
<span id="l101"> * method will reset the value of the scanner's delimiter to the default</span><a href="#l101"></a>
<span id="l102"> * whitespace delimiter regardless of whether it was previously changed.</span><a href="#l102"></a>
<span id="l103"> *</span><a href="#l103"></a>
<span id="l104"> * &lt;p&gt;A scanning operation may block waiting for input.</span><a href="#l104"></a>
<span id="l105"> *</span><a href="#l105"></a>
<span id="l106"> * &lt;p&gt;The {@link #next} and {@link #hasNext} methods and their</span><a href="#l106"></a>
<span id="l107"> * primitive-type companion methods (such as {@link #nextInt} and</span><a href="#l107"></a>
<span id="l108"> * {@link #hasNextInt}) first skip any input that matches the delimiter</span><a href="#l108"></a>
<span id="l109"> * pattern, and then attempt to return the next token. Both &lt;tt&gt;hasNext&lt;/tt&gt;</span><a href="#l109"></a>
<span id="l110"> * and &lt;tt&gt;next&lt;/tt&gt; methods may block waiting for further input.  Whether a</span><a href="#l110"></a>
<span id="l111"> * &lt;tt&gt;hasNext&lt;/tt&gt; method blocks has no connection to whether or not its</span><a href="#l111"></a>
<span id="l112"> * associated &lt;tt&gt;next&lt;/tt&gt; method will block.</span><a href="#l112"></a>
<span id="l113"> *</span><a href="#l113"></a>
<span id="l114"> * &lt;p&gt; The {@link #findInLine}, {@link #findWithinHorizon}, and {@link #skip}</span><a href="#l114"></a>
<span id="l115"> * methods operate independently of the delimiter pattern. These methods will</span><a href="#l115"></a>
<span id="l116"> * attempt to match the specified pattern with no regard to delimiters in the</span><a href="#l116"></a>
<span id="l117"> * input and thus can be used in special circumstances where delimiters are</span><a href="#l117"></a>
<span id="l118"> * not relevant. These methods may block waiting for more input.</span><a href="#l118"></a>
<span id="l119"> *</span><a href="#l119"></a>
<span id="l120"> * &lt;p&gt;When a scanner throws an {@link InputMismatchException}, the scanner</span><a href="#l120"></a>
<span id="l121"> * will not pass the token that caused the exception, so that it may be</span><a href="#l121"></a>
<span id="l122"> * retrieved or skipped via some other method.</span><a href="#l122"></a>
<span id="l123"> *</span><a href="#l123"></a>
<span id="l124"> * &lt;p&gt;Depending upon the type of delimiting pattern, empty tokens may be</span><a href="#l124"></a>
<span id="l125"> * returned. For example, the pattern &lt;tt&gt;&quot;\\s+&quot;&lt;/tt&gt; will return no empty</span><a href="#l125"></a>
<span id="l126"> * tokens since it matches multiple instances of the delimiter. The delimiting</span><a href="#l126"></a>
<span id="l127"> * pattern &lt;tt&gt;&quot;\\s&quot;&lt;/tt&gt; could return empty tokens since it only passes one</span><a href="#l127"></a>
<span id="l128"> * space at a time.</span><a href="#l128"></a>
<span id="l129"> *</span><a href="#l129"></a>
<span id="l130"> * &lt;p&gt; A scanner can read text from any object which implements the {@link</span><a href="#l130"></a>
<span id="l131"> * java.lang.Readable} interface.  If an invocation of the underlying</span><a href="#l131"></a>
<span id="l132"> * readable's {@link java.lang.Readable#read} method throws an {@link</span><a href="#l132"></a>
<span id="l133"> * java.io.IOException} then the scanner assumes that the end of the input</span><a href="#l133"></a>
<span id="l134"> * has been reached.  The most recent &lt;tt&gt;IOException&lt;/tt&gt; thrown by the</span><a href="#l134"></a>
<span id="l135"> * underlying readable can be retrieved via the {@link #ioException} method.</span><a href="#l135"></a>
<span id="l136"> *</span><a href="#l136"></a>
<span id="l137"> * &lt;p&gt;When a &lt;code&gt;Scanner&lt;/code&gt; is closed, it will close its input source</span><a href="#l137"></a>
<span id="l138"> * if the source implements the {@link java.io.Closeable} interface.</span><a href="#l138"></a>
<span id="l139"> *</span><a href="#l139"></a>
<span id="l140"> * &lt;p&gt;A &lt;code&gt;Scanner&lt;/code&gt; is not safe for multithreaded use without</span><a href="#l140"></a>
<span id="l141"> * external synchronization.</span><a href="#l141"></a>
<span id="l142"> *</span><a href="#l142"></a>
<span id="l143"> * &lt;p&gt;Unless otherwise mentioned, passing a &lt;code&gt;null&lt;/code&gt; parameter into</span><a href="#l143"></a>
<span id="l144"> * any method of a &lt;code&gt;Scanner&lt;/code&gt; will cause a</span><a href="#l144"></a>
<span id="l145"> * &lt;code&gt;NullPointerException&lt;/code&gt; to be thrown.</span><a href="#l145"></a>
<span id="l146"> *</span><a href="#l146"></a>
<span id="l147"> * &lt;p&gt;A scanner will default to interpreting numbers as decimal unless a</span><a href="#l147"></a>
<span id="l148"> * different radix has been set by using the {@link #useRadix} method. The</span><a href="#l148"></a>
<span id="l149"> * {@link #reset} method will reset the value of the scanner's radix to</span><a href="#l149"></a>
<span id="l150"> * &lt;code&gt;10&lt;/code&gt; regardless of whether it was previously changed.</span><a href="#l150"></a>
<span id="l151"> *</span><a href="#l151"></a>
<span id="l152"> * &lt;h3&gt; &lt;a name=&quot;localized-numbers&quot;&gt;Localized numbers&lt;/a&gt; &lt;/h3&gt;</span><a href="#l152"></a>
<span id="l153"> *</span><a href="#l153"></a>
<span id="l154"> * &lt;p&gt; An instance of this class is capable of scanning numbers in the standard</span><a href="#l154"></a>
<span id="l155"> * formats as well as in the formats of the scanner's locale. A scanner's</span><a href="#l155"></a>
<span id="l156"> * &lt;a name=&quot;initial-locale&quot;&gt;initial locale &lt;/a&gt;is the value returned by the {@link</span><a href="#l156"></a>
<span id="l157"> * java.util.Locale#getDefault(Locale.Category)</span><a href="#l157"></a>
<span id="l158"> * Locale.getDefault(Locale.Category.FORMAT)} method; it may be changed via the {@link</span><a href="#l158"></a>
<span id="l159"> * #useLocale} method. The {@link #reset} method will reset the value of the</span><a href="#l159"></a>
<span id="l160"> * scanner's locale to the initial locale regardless of whether it was</span><a href="#l160"></a>
<span id="l161"> * previously changed.</span><a href="#l161"></a>
<span id="l162"> *</span><a href="#l162"></a>
<span id="l163"> * &lt;p&gt;The localized formats are defined in terms of the following parameters,</span><a href="#l163"></a>
<span id="l164"> * which for a particular locale are taken from that locale's {@link</span><a href="#l164"></a>
<span id="l165"> * java.text.DecimalFormat DecimalFormat} object, &lt;tt&gt;df&lt;/tt&gt;, and its and</span><a href="#l165"></a>
<span id="l166"> * {@link java.text.DecimalFormatSymbols DecimalFormatSymbols} object,</span><a href="#l166"></a>
<span id="l167"> * &lt;tt&gt;dfs&lt;/tt&gt;.</span><a href="#l167"></a>
<span id="l168"> *</span><a href="#l168"></a>
<span id="l169"> * &lt;blockquote&gt;&lt;dl&gt;</span><a href="#l169"></a>
<span id="l170"> *     &lt;dt&gt;&lt;i&gt;LocalGroupSeparator&amp;nbsp;&amp;nbsp;&lt;/i&gt;</span><a href="#l170"></a>
<span id="l171"> *         &lt;dd&gt;The character used to separate thousands groups,</span><a href="#l171"></a>
<span id="l172"> *         &lt;i&gt;i.e.,&lt;/i&gt;&amp;nbsp;&lt;tt&gt;dfs.&lt;/tt&gt;{@link</span><a href="#l172"></a>
<span id="l173"> *         java.text.DecimalFormatSymbols#getGroupingSeparator</span><a href="#l173"></a>
<span id="l174"> *         getGroupingSeparator()}</span><a href="#l174"></a>
<span id="l175"> *     &lt;dt&gt;&lt;i&gt;LocalDecimalSeparator&amp;nbsp;&amp;nbsp;&lt;/i&gt;</span><a href="#l175"></a>
<span id="l176"> *         &lt;dd&gt;The character used for the decimal point,</span><a href="#l176"></a>
<span id="l177"> *     &lt;i&gt;i.e.,&lt;/i&gt;&amp;nbsp;&lt;tt&gt;dfs.&lt;/tt&gt;{@link</span><a href="#l177"></a>
<span id="l178"> *     java.text.DecimalFormatSymbols#getDecimalSeparator</span><a href="#l178"></a>
<span id="l179"> *     getDecimalSeparator()}</span><a href="#l179"></a>
<span id="l180"> *     &lt;dt&gt;&lt;i&gt;LocalPositivePrefix&amp;nbsp;&amp;nbsp;&lt;/i&gt;</span><a href="#l180"></a>
<span id="l181"> *         &lt;dd&gt;The string that appears before a positive number (may</span><a href="#l181"></a>
<span id="l182"> *         be empty), &lt;i&gt;i.e.,&lt;/i&gt;&amp;nbsp;&lt;tt&gt;df.&lt;/tt&gt;{@link</span><a href="#l182"></a>
<span id="l183"> *         java.text.DecimalFormat#getPositivePrefix</span><a href="#l183"></a>
<span id="l184"> *         getPositivePrefix()}</span><a href="#l184"></a>
<span id="l185"> *     &lt;dt&gt;&lt;i&gt;LocalPositiveSuffix&amp;nbsp;&amp;nbsp;&lt;/i&gt;</span><a href="#l185"></a>
<span id="l186"> *         &lt;dd&gt;The string that appears after a positive number (may be</span><a href="#l186"></a>
<span id="l187"> *         empty), &lt;i&gt;i.e.,&lt;/i&gt;&amp;nbsp;&lt;tt&gt;df.&lt;/tt&gt;{@link</span><a href="#l187"></a>
<span id="l188"> *         java.text.DecimalFormat#getPositiveSuffix</span><a href="#l188"></a>
<span id="l189"> *         getPositiveSuffix()}</span><a href="#l189"></a>
<span id="l190"> *     &lt;dt&gt;&lt;i&gt;LocalNegativePrefix&amp;nbsp;&amp;nbsp;&lt;/i&gt;</span><a href="#l190"></a>
<span id="l191"> *         &lt;dd&gt;The string that appears before a negative number (may</span><a href="#l191"></a>
<span id="l192"> *         be empty), &lt;i&gt;i.e.,&lt;/i&gt;&amp;nbsp;&lt;tt&gt;df.&lt;/tt&gt;{@link</span><a href="#l192"></a>
<span id="l193"> *         java.text.DecimalFormat#getNegativePrefix</span><a href="#l193"></a>
<span id="l194"> *         getNegativePrefix()}</span><a href="#l194"></a>
<span id="l195"> *     &lt;dt&gt;&lt;i&gt;LocalNegativeSuffix&amp;nbsp;&amp;nbsp;&lt;/i&gt;</span><a href="#l195"></a>
<span id="l196"> *         &lt;dd&gt;The string that appears after a negative number (may be</span><a href="#l196"></a>
<span id="l197"> *         empty), &lt;i&gt;i.e.,&lt;/i&gt;&amp;nbsp;&lt;tt&gt;df.&lt;/tt&gt;{@link</span><a href="#l197"></a>
<span id="l198"> *     java.text.DecimalFormat#getNegativeSuffix</span><a href="#l198"></a>
<span id="l199"> *     getNegativeSuffix()}</span><a href="#l199"></a>
<span id="l200"> *     &lt;dt&gt;&lt;i&gt;LocalNaN&amp;nbsp;&amp;nbsp;&lt;/i&gt;</span><a href="#l200"></a>
<span id="l201"> *         &lt;dd&gt;The string that represents not-a-number for</span><a href="#l201"></a>
<span id="l202"> *         floating-point values,</span><a href="#l202"></a>
<span id="l203"> *         &lt;i&gt;i.e.,&lt;/i&gt;&amp;nbsp;&lt;tt&gt;dfs.&lt;/tt&gt;{@link</span><a href="#l203"></a>
<span id="l204"> *         java.text.DecimalFormatSymbols#getNaN</span><a href="#l204"></a>
<span id="l205"> *         getNaN()}</span><a href="#l205"></a>
<span id="l206"> *     &lt;dt&gt;&lt;i&gt;LocalInfinity&amp;nbsp;&amp;nbsp;&lt;/i&gt;</span><a href="#l206"></a>
<span id="l207"> *         &lt;dd&gt;The string that represents infinity for floating-point</span><a href="#l207"></a>
<span id="l208"> *         values, &lt;i&gt;i.e.,&lt;/i&gt;&amp;nbsp;&lt;tt&gt;dfs.&lt;/tt&gt;{@link</span><a href="#l208"></a>
<span id="l209"> *         java.text.DecimalFormatSymbols#getInfinity</span><a href="#l209"></a>
<span id="l210"> *         getInfinity()}</span><a href="#l210"></a>
<span id="l211"> * &lt;/dl&gt;&lt;/blockquote&gt;</span><a href="#l211"></a>
<span id="l212"> *</span><a href="#l212"></a>
<span id="l213"> * &lt;h4&gt; &lt;a name=&quot;number-syntax&quot;&gt;Number syntax&lt;/a&gt; &lt;/h4&gt;</span><a href="#l213"></a>
<span id="l214"> *</span><a href="#l214"></a>
<span id="l215"> * &lt;p&gt; The strings that can be parsed as numbers by an instance of this class</span><a href="#l215"></a>
<span id="l216"> * are specified in terms of the following regular-expression grammar, where</span><a href="#l216"></a>
<span id="l217"> * Rmax is the highest digit in the radix being used (for example, Rmax is 9 in base 10).</span><a href="#l217"></a>
<span id="l218"> *</span><a href="#l218"></a>
<span id="l219"> * &lt;dl&gt;</span><a href="#l219"></a>
<span id="l220"> *   &lt;dt&gt;&lt;i&gt;NonAsciiDigit&lt;/i&gt;:</span><a href="#l220"></a>
<span id="l221"> *       &lt;dd&gt;A non-ASCII character c for which</span><a href="#l221"></a>
<span id="l222"> *            {@link java.lang.Character#isDigit Character.isDigit}&lt;tt&gt;(c)&lt;/tt&gt;</span><a href="#l222"></a>
<span id="l223"> *                        returns&amp;nbsp;true</span><a href="#l223"></a>
<span id="l224"> *</span><a href="#l224"></a>
<span id="l225"> *   &lt;dt&gt;&lt;i&gt;Non0Digit&lt;/i&gt;:</span><a href="#l225"></a>
<span id="l226"> *       &lt;dd&gt;&lt;tt&gt;[1-&lt;/tt&gt;&lt;i&gt;Rmax&lt;/i&gt;&lt;tt&gt;] | &lt;/tt&gt;&lt;i&gt;NonASCIIDigit&lt;/i&gt;</span><a href="#l226"></a>
<span id="l227"> *</span><a href="#l227"></a>
<span id="l228"> *   &lt;dt&gt;&lt;i&gt;Digit&lt;/i&gt;:</span><a href="#l228"></a>
<span id="l229"> *       &lt;dd&gt;&lt;tt&gt;[0-&lt;/tt&gt;&lt;i&gt;Rmax&lt;/i&gt;&lt;tt&gt;] | &lt;/tt&gt;&lt;i&gt;NonASCIIDigit&lt;/i&gt;</span><a href="#l229"></a>
<span id="l230"> *</span><a href="#l230"></a>
<span id="l231"> *   &lt;dt&gt;&lt;i&gt;GroupedNumeral&lt;/i&gt;:</span><a href="#l231"></a>
<span id="l232"> *       &lt;dd&gt;&lt;tt&gt;(&amp;nbsp;&lt;/tt&gt;&lt;i&gt;Non0Digit&lt;/i&gt;</span><a href="#l232"></a>
<span id="l233"> *                   &lt;i&gt;Digit&lt;/i&gt;&lt;tt&gt;?</span><a href="#l233"></a>
<span id="l234"> *                   &lt;/tt&gt;&lt;i&gt;Digit&lt;/i&gt;&lt;tt&gt;?&lt;/tt&gt;</span><a href="#l234"></a>
<span id="l235"> *       &lt;dd&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;tt&gt;(&amp;nbsp;&lt;/tt&gt;&lt;i&gt;LocalGroupSeparator&lt;/i&gt;</span><a href="#l235"></a>
<span id="l236"> *                         &lt;i&gt;Digit&lt;/i&gt;</span><a href="#l236"></a>
<span id="l237"> *                         &lt;i&gt;Digit&lt;/i&gt;</span><a href="#l237"></a>
<span id="l238"> *                         &lt;i&gt;Digit&lt;/i&gt;&lt;tt&gt; )+ )&lt;/tt&gt;</span><a href="#l238"></a>
<span id="l239"> *</span><a href="#l239"></a>
<span id="l240"> *   &lt;dt&gt;&lt;i&gt;Numeral&lt;/i&gt;:</span><a href="#l240"></a>
<span id="l241"> *       &lt;dd&gt;&lt;tt&gt;( ( &lt;/tt&gt;&lt;i&gt;Digit&lt;/i&gt;&lt;tt&gt;+ )</span><a href="#l241"></a>
<span id="l242"> *               | &lt;/tt&gt;&lt;i&gt;GroupedNumeral&lt;/i&gt;&lt;tt&gt; )&lt;/tt&gt;</span><a href="#l242"></a>
<span id="l243"> *</span><a href="#l243"></a>
<span id="l244"> *   &lt;dt&gt;&lt;a name=&quot;Integer-regex&quot;&gt;&lt;i&gt;Integer&lt;/i&gt;:&lt;/a&gt;</span><a href="#l244"></a>
<span id="l245"> *       &lt;dd&gt;&lt;tt&gt;( [-+]? ( &lt;/tt&gt;&lt;i&gt;Numeral&lt;/i&gt;&lt;tt&gt;</span><a href="#l245"></a>
<span id="l246"> *                               ) )&lt;/tt&gt;</span><a href="#l246"></a>
<span id="l247"> *       &lt;dd&gt;&lt;tt&gt;| &lt;/tt&gt;&lt;i&gt;LocalPositivePrefix&lt;/i&gt; &lt;i&gt;Numeral&lt;/i&gt;</span><a href="#l247"></a>
<span id="l248"> *                      &lt;i&gt;LocalPositiveSuffix&lt;/i&gt;</span><a href="#l248"></a>
<span id="l249"> *       &lt;dd&gt;&lt;tt&gt;| &lt;/tt&gt;&lt;i&gt;LocalNegativePrefix&lt;/i&gt; &lt;i&gt;Numeral&lt;/i&gt;</span><a href="#l249"></a>
<span id="l250"> *                 &lt;i&gt;LocalNegativeSuffix&lt;/i&gt;</span><a href="#l250"></a>
<span id="l251"> *</span><a href="#l251"></a>
<span id="l252"> *   &lt;dt&gt;&lt;i&gt;DecimalNumeral&lt;/i&gt;:</span><a href="#l252"></a>
<span id="l253"> *       &lt;dd&gt;&lt;i&gt;Numeral&lt;/i&gt;</span><a href="#l253"></a>
<span id="l254"> *       &lt;dd&gt;&lt;tt&gt;| &lt;/tt&gt;&lt;i&gt;Numeral&lt;/i&gt;</span><a href="#l254"></a>
<span id="l255"> *                 &lt;i&gt;LocalDecimalSeparator&lt;/i&gt;</span><a href="#l255"></a>
<span id="l256"> *                 &lt;i&gt;Digit&lt;/i&gt;&lt;tt&gt;*&lt;/tt&gt;</span><a href="#l256"></a>
<span id="l257"> *       &lt;dd&gt;&lt;tt&gt;| &lt;/tt&gt;&lt;i&gt;LocalDecimalSeparator&lt;/i&gt;</span><a href="#l257"></a>
<span id="l258"> *                 &lt;i&gt;Digit&lt;/i&gt;&lt;tt&gt;+&lt;/tt&gt;</span><a href="#l258"></a>
<span id="l259"> *</span><a href="#l259"></a>
<span id="l260"> *   &lt;dt&gt;&lt;i&gt;Exponent&lt;/i&gt;:</span><a href="#l260"></a>
<span id="l261"> *       &lt;dd&gt;&lt;tt&gt;( [eE] [+-]? &lt;/tt&gt;&lt;i&gt;Digit&lt;/i&gt;&lt;tt&gt;+ )&lt;/tt&gt;</span><a href="#l261"></a>
<span id="l262"> *</span><a href="#l262"></a>
<span id="l263"> *   &lt;dt&gt;&lt;a name=&quot;Decimal-regex&quot;&gt;&lt;i&gt;Decimal&lt;/i&gt;:&lt;/a&gt;</span><a href="#l263"></a>
<span id="l264"> *       &lt;dd&gt;&lt;tt&gt;( [-+]? &lt;/tt&gt;&lt;i&gt;DecimalNumeral&lt;/i&gt;</span><a href="#l264"></a>
<span id="l265"> *                         &lt;i&gt;Exponent&lt;/i&gt;&lt;tt&gt;? )&lt;/tt&gt;</span><a href="#l265"></a>
<span id="l266"> *       &lt;dd&gt;&lt;tt&gt;| &lt;/tt&gt;&lt;i&gt;LocalPositivePrefix&lt;/i&gt;</span><a href="#l266"></a>
<span id="l267"> *                 &lt;i&gt;DecimalNumeral&lt;/i&gt;</span><a href="#l267"></a>
<span id="l268"> *                 &lt;i&gt;LocalPositiveSuffix&lt;/i&gt;</span><a href="#l268"></a>
<span id="l269"> *                 &lt;i&gt;Exponent&lt;/i&gt;&lt;tt&gt;?&lt;/tt&gt;</span><a href="#l269"></a>
<span id="l270"> *       &lt;dd&gt;&lt;tt&gt;| &lt;/tt&gt;&lt;i&gt;LocalNegativePrefix&lt;/i&gt;</span><a href="#l270"></a>
<span id="l271"> *                 &lt;i&gt;DecimalNumeral&lt;/i&gt;</span><a href="#l271"></a>
<span id="l272"> *                 &lt;i&gt;LocalNegativeSuffix&lt;/i&gt;</span><a href="#l272"></a>
<span id="l273"> *                 &lt;i&gt;Exponent&lt;/i&gt;&lt;tt&gt;?&lt;/tt&gt;</span><a href="#l273"></a>
<span id="l274"> *</span><a href="#l274"></a>
<span id="l275"> *   &lt;dt&gt;&lt;i&gt;HexFloat&lt;/i&gt;:</span><a href="#l275"></a>
<span id="l276"> *       &lt;dd&gt;&lt;tt&gt;[-+]? 0[xX][0-9a-fA-F]*\.[0-9a-fA-F]+</span><a href="#l276"></a>
<span id="l277"> *                 ([pP][-+]?[0-9]+)?&lt;/tt&gt;</span><a href="#l277"></a>
<span id="l278"> *</span><a href="#l278"></a>
<span id="l279"> *   &lt;dt&gt;&lt;i&gt;NonNumber&lt;/i&gt;:</span><a href="#l279"></a>
<span id="l280"> *       &lt;dd&gt;&lt;tt&gt;NaN</span><a href="#l280"></a>
<span id="l281"> *                          | &lt;/tt&gt;&lt;i&gt;LocalNan&lt;/i&gt;&lt;tt&gt;</span><a href="#l281"></a>
<span id="l282"> *                          | Infinity</span><a href="#l282"></a>
<span id="l283"> *                          | &lt;/tt&gt;&lt;i&gt;LocalInfinity&lt;/i&gt;</span><a href="#l283"></a>
<span id="l284"> *</span><a href="#l284"></a>
<span id="l285"> *   &lt;dt&gt;&lt;i&gt;SignedNonNumber&lt;/i&gt;:</span><a href="#l285"></a>
<span id="l286"> *       &lt;dd&gt;&lt;tt&gt;( [-+]? &lt;/tt&gt;&lt;i&gt;NonNumber&lt;/i&gt;&lt;tt&gt; )&lt;/tt&gt;</span><a href="#l286"></a>
<span id="l287"> *       &lt;dd&gt;&lt;tt&gt;| &lt;/tt&gt;&lt;i&gt;LocalPositivePrefix&lt;/i&gt;</span><a href="#l287"></a>
<span id="l288"> *                 &lt;i&gt;NonNumber&lt;/i&gt;</span><a href="#l288"></a>
<span id="l289"> *                 &lt;i&gt;LocalPositiveSuffix&lt;/i&gt;</span><a href="#l289"></a>
<span id="l290"> *       &lt;dd&gt;&lt;tt&gt;| &lt;/tt&gt;&lt;i&gt;LocalNegativePrefix&lt;/i&gt;</span><a href="#l290"></a>
<span id="l291"> *                 &lt;i&gt;NonNumber&lt;/i&gt;</span><a href="#l291"></a>
<span id="l292"> *                 &lt;i&gt;LocalNegativeSuffix&lt;/i&gt;</span><a href="#l292"></a>
<span id="l293"> *</span><a href="#l293"></a>
<span id="l294"> *   &lt;dt&gt;&lt;a name=&quot;Float-regex&quot;&gt;&lt;i&gt;Float&lt;/i&gt;&lt;/a&gt;:</span><a href="#l294"></a>
<span id="l295"> *       &lt;dd&gt;&lt;i&gt;Decimal&lt;/i&gt;</span><a href="#l295"></a>
<span id="l296"> *           &lt;tt&gt;| &lt;/tt&gt;&lt;i&gt;HexFloat&lt;/i&gt;</span><a href="#l296"></a>
<span id="l297"> *           &lt;tt&gt;| &lt;/tt&gt;&lt;i&gt;SignedNonNumber&lt;/i&gt;</span><a href="#l297"></a>
<span id="l298"> *</span><a href="#l298"></a>
<span id="l299"> * &lt;/dl&gt;</span><a href="#l299"></a>
<span id="l300"> * &lt;p&gt;Whitespace is not significant in the above regular expressions.</span><a href="#l300"></a>
<span id="l301"> *</span><a href="#l301"></a>
<span id="l302"> * @since   1.5</span><a href="#l302"></a>
<span id="l303"> */</span><a href="#l303"></a>
<span id="l304">public final class Scanner implements Iterator&lt;String&gt;, Closeable {</span><a href="#l304"></a>
<span id="l305"></span><a href="#l305"></a>
<span id="l306">    // Internal buffer used to hold input</span><a href="#l306"></a>
<span id="l307">    private CharBuffer buf;</span><a href="#l307"></a>
<span id="l308"></span><a href="#l308"></a>
<span id="l309">    // Size of internal character buffer</span><a href="#l309"></a>
<span id="l310">    private static final int BUFFER_SIZE = 1024; // change to 1024;</span><a href="#l310"></a>
<span id="l311"></span><a href="#l311"></a>
<span id="l312">    // The index into the buffer currently held by the Scanner</span><a href="#l312"></a>
<span id="l313">    private int position;</span><a href="#l313"></a>
<span id="l314"></span><a href="#l314"></a>
<span id="l315">    // Internal matcher used for finding delimiters</span><a href="#l315"></a>
<span id="l316">    private Matcher matcher;</span><a href="#l316"></a>
<span id="l317"></span><a href="#l317"></a>
<span id="l318">    // Pattern used to delimit tokens</span><a href="#l318"></a>
<span id="l319">    private Pattern delimPattern;</span><a href="#l319"></a>
<span id="l320"></span><a href="#l320"></a>
<span id="l321">    // Pattern found in last hasNext operation</span><a href="#l321"></a>
<span id="l322">    private Pattern hasNextPattern;</span><a href="#l322"></a>
<span id="l323"></span><a href="#l323"></a>
<span id="l324">    // Position after last hasNext operation</span><a href="#l324"></a>
<span id="l325">    private int hasNextPosition;</span><a href="#l325"></a>
<span id="l326"></span><a href="#l326"></a>
<span id="l327">    // Result after last hasNext operation</span><a href="#l327"></a>
<span id="l328">    private String hasNextResult;</span><a href="#l328"></a>
<span id="l329"></span><a href="#l329"></a>
<span id="l330">    // The input source</span><a href="#l330"></a>
<span id="l331">    private Readable source;</span><a href="#l331"></a>
<span id="l332"></span><a href="#l332"></a>
<span id="l333">    // Boolean is true if source is done</span><a href="#l333"></a>
<span id="l334">    private boolean sourceClosed = false;</span><a href="#l334"></a>
<span id="l335"></span><a href="#l335"></a>
<span id="l336">    // Boolean indicating more input is required</span><a href="#l336"></a>
<span id="l337">    private boolean needInput = false;</span><a href="#l337"></a>
<span id="l338"></span><a href="#l338"></a>
<span id="l339">    // Boolean indicating if a delim has been skipped this operation</span><a href="#l339"></a>
<span id="l340">    private boolean skipped = false;</span><a href="#l340"></a>
<span id="l341"></span><a href="#l341"></a>
<span id="l342">    // A store of a position that the scanner may fall back to</span><a href="#l342"></a>
<span id="l343">    private int savedScannerPosition = -1;</span><a href="#l343"></a>
<span id="l344"></span><a href="#l344"></a>
<span id="l345">    // A cache of the last primitive type scanned</span><a href="#l345"></a>
<span id="l346">    private Object typeCache = null;</span><a href="#l346"></a>
<span id="l347"></span><a href="#l347"></a>
<span id="l348">    // Boolean indicating if a match result is available</span><a href="#l348"></a>
<span id="l349">    private boolean matchValid = false;</span><a href="#l349"></a>
<span id="l350"></span><a href="#l350"></a>
<span id="l351">    // Boolean indicating if this scanner has been closed</span><a href="#l351"></a>
<span id="l352">    private boolean closed = false;</span><a href="#l352"></a>
<span id="l353"></span><a href="#l353"></a>
<span id="l354">    // The current radix used by this scanner</span><a href="#l354"></a>
<span id="l355">    private int radix = 10;</span><a href="#l355"></a>
<span id="l356"></span><a href="#l356"></a>
<span id="l357">    // The default radix for this scanner</span><a href="#l357"></a>
<span id="l358">    private int defaultRadix = 10;</span><a href="#l358"></a>
<span id="l359"></span><a href="#l359"></a>
<span id="l360">    // The locale used by this scanner</span><a href="#l360"></a>
<span id="l361">    private Locale locale = null;</span><a href="#l361"></a>
<span id="l362"></span><a href="#l362"></a>
<span id="l363">    // A cache of the last few recently used Patterns</span><a href="#l363"></a>
<span id="l364">    private LRUCache&lt;String,Pattern&gt; patternCache =</span><a href="#l364"></a>
<span id="l365">    new LRUCache&lt;String,Pattern&gt;(7) {</span><a href="#l365"></a>
<span id="l366">        protected Pattern create(String s) {</span><a href="#l366"></a>
<span id="l367">            return Pattern.compile(s);</span><a href="#l367"></a>
<span id="l368">        }</span><a href="#l368"></a>
<span id="l369">        protected boolean hasName(Pattern p, String s) {</span><a href="#l369"></a>
<span id="l370">            return p.pattern().equals(s);</span><a href="#l370"></a>
<span id="l371">        }</span><a href="#l371"></a>
<span id="l372">    };</span><a href="#l372"></a>
<span id="l373"></span><a href="#l373"></a>
<span id="l374">    // A holder of the last IOException encountered</span><a href="#l374"></a>
<span id="l375">    private IOException lastException;</span><a href="#l375"></a>
<span id="l376"></span><a href="#l376"></a>
<span id="l377">    // A pattern for java whitespace</span><a href="#l377"></a>
<span id="l378">    private static Pattern WHITESPACE_PATTERN = Pattern.compile(</span><a href="#l378"></a>
<span id="l379">                                                &quot;\\p{javaWhitespace}+&quot;);</span><a href="#l379"></a>
<span id="l380"></span><a href="#l380"></a>
<span id="l381">    // A pattern for any token</span><a href="#l381"></a>
<span id="l382">    private static Pattern FIND_ANY_PATTERN = Pattern.compile(&quot;(?s).*&quot;);</span><a href="#l382"></a>
<span id="l383"></span><a href="#l383"></a>
<span id="l384">    // A pattern for non-ASCII digits</span><a href="#l384"></a>
<span id="l385">    private static Pattern NON_ASCII_DIGIT = Pattern.compile(</span><a href="#l385"></a>
<span id="l386">        &quot;[\\p{javaDigit}&amp;&amp;[^0-9]]&quot;);</span><a href="#l386"></a>
<span id="l387"></span><a href="#l387"></a>
<span id="l388">    // Fields and methods to support scanning primitive types</span><a href="#l388"></a>
<span id="l389"></span><a href="#l389"></a>
<span id="l390">    /**</span><a href="#l390"></a>
<span id="l391">     * Locale dependent values used to scan numbers</span><a href="#l391"></a>
<span id="l392">     */</span><a href="#l392"></a>
<span id="l393">    private String groupSeparator = &quot;\\,&quot;;</span><a href="#l393"></a>
<span id="l394">    private String decimalSeparator = &quot;\\.&quot;;</span><a href="#l394"></a>
<span id="l395">    private String nanString = &quot;NaN&quot;;</span><a href="#l395"></a>
<span id="l396">    private String infinityString = &quot;Infinity&quot;;</span><a href="#l396"></a>
<span id="l397">    private String positivePrefix = &quot;&quot;;</span><a href="#l397"></a>
<span id="l398">    private String negativePrefix = &quot;\\-&quot;;</span><a href="#l398"></a>
<span id="l399">    private String positiveSuffix = &quot;&quot;;</span><a href="#l399"></a>
<span id="l400">    private String negativeSuffix = &quot;&quot;;</span><a href="#l400"></a>
<span id="l401"></span><a href="#l401"></a>
<span id="l402">    /**</span><a href="#l402"></a>
<span id="l403">     * Fields and an accessor method to match booleans</span><a href="#l403"></a>
<span id="l404">     */</span><a href="#l404"></a>
<span id="l405">    private static volatile Pattern boolPattern;</span><a href="#l405"></a>
<span id="l406">    private static final String BOOLEAN_PATTERN = &quot;true|false&quot;;</span><a href="#l406"></a>
<span id="l407">    private static Pattern boolPattern() {</span><a href="#l407"></a>
<span id="l408">        Pattern bp = boolPattern;</span><a href="#l408"></a>
<span id="l409">        if (bp == null)</span><a href="#l409"></a>
<span id="l410">            boolPattern = bp = Pattern.compile(BOOLEAN_PATTERN,</span><a href="#l410"></a>
<span id="l411">                                          Pattern.CASE_INSENSITIVE);</span><a href="#l411"></a>
<span id="l412">        return bp;</span><a href="#l412"></a>
<span id="l413">    }</span><a href="#l413"></a>
<span id="l414"></span><a href="#l414"></a>
<span id="l415">    /**</span><a href="#l415"></a>
<span id="l416">     * Fields and methods to match bytes, shorts, ints, and longs</span><a href="#l416"></a>
<span id="l417">     */</span><a href="#l417"></a>
<span id="l418">    private Pattern integerPattern;</span><a href="#l418"></a>
<span id="l419">    private String digits = &quot;0123456789abcdefghijklmnopqrstuvwxyz&quot;;</span><a href="#l419"></a>
<span id="l420">    private String non0Digit = &quot;[\\p{javaDigit}&amp;&amp;[^0]]&quot;;</span><a href="#l420"></a>
<span id="l421">    private int SIMPLE_GROUP_INDEX = 5;</span><a href="#l421"></a>
<span id="l422">    private String buildIntegerPatternString() {</span><a href="#l422"></a>
<span id="l423">        String radixDigits = digits.substring(0, radix);</span><a href="#l423"></a>
<span id="l424">        // \\p{javaDigit} is not guaranteed to be appropriate</span><a href="#l424"></a>
<span id="l425">        // here but what can we do? The final authority will be</span><a href="#l425"></a>
<span id="l426">        // whatever parse method is invoked, so ultimately the</span><a href="#l426"></a>
<span id="l427">        // Scanner will do the right thing</span><a href="#l427"></a>
<span id="l428">        String digit = &quot;((?i)[&quot;+radixDigits+&quot;\\p{javaDigit}])&quot;;</span><a href="#l428"></a>
<span id="l429">        String groupedNumeral = &quot;(&quot;+non0Digit+digit+&quot;?&quot;+digit+&quot;?(&quot;+</span><a href="#l429"></a>
<span id="l430">                                groupSeparator+digit+digit+digit+&quot;)+)&quot;;</span><a href="#l430"></a>
<span id="l431">        // digit++ is the possessive form which is necessary for reducing</span><a href="#l431"></a>
<span id="l432">        // backtracking that would otherwise cause unacceptable performance</span><a href="#l432"></a>
<span id="l433">        String numeral = &quot;((&quot;+ digit+&quot;++)|&quot;+groupedNumeral+&quot;)&quot;;</span><a href="#l433"></a>
<span id="l434">        String javaStyleInteger = &quot;([-+]?(&quot; + numeral + &quot;))&quot;;</span><a href="#l434"></a>
<span id="l435">        String negativeInteger = negativePrefix + numeral + negativeSuffix;</span><a href="#l435"></a>
<span id="l436">        String positiveInteger = positivePrefix + numeral + positiveSuffix;</span><a href="#l436"></a>
<span id="l437">        return &quot;(&quot;+ javaStyleInteger + &quot;)|(&quot; +</span><a href="#l437"></a>
<span id="l438">            positiveInteger + &quot;)|(&quot; +</span><a href="#l438"></a>
<span id="l439">            negativeInteger + &quot;)&quot;;</span><a href="#l439"></a>
<span id="l440">    }</span><a href="#l440"></a>
<span id="l441">    private Pattern integerPattern() {</span><a href="#l441"></a>
<span id="l442">        if (integerPattern == null) {</span><a href="#l442"></a>
<span id="l443">            integerPattern = patternCache.forName(buildIntegerPatternString());</span><a href="#l443"></a>
<span id="l444">        }</span><a href="#l444"></a>
<span id="l445">        return integerPattern;</span><a href="#l445"></a>
<span id="l446">    }</span><a href="#l446"></a>
<span id="l447"></span><a href="#l447"></a>
<span id="l448">    /**</span><a href="#l448"></a>
<span id="l449">     * Fields and an accessor method to match line separators</span><a href="#l449"></a>
<span id="l450">     */</span><a href="#l450"></a>
<span id="l451">    private static volatile Pattern separatorPattern;</span><a href="#l451"></a>
<span id="l452">    private static volatile Pattern linePattern;</span><a href="#l452"></a>
<span id="l453">    private static final String LINE_SEPARATOR_PATTERN =</span><a href="#l453"></a>
<span id="l454">                                           &quot;\r\n|[\n\r\u2028\u2029\u0085]&quot;;</span><a href="#l454"></a>
<span id="l455">    private static final String LINE_PATTERN = &quot;.*(&quot;+LINE_SEPARATOR_PATTERN+&quot;)|.+$&quot;;</span><a href="#l455"></a>
<span id="l456"></span><a href="#l456"></a>
<span id="l457">    private static Pattern separatorPattern() {</span><a href="#l457"></a>
<span id="l458">        Pattern sp = separatorPattern;</span><a href="#l458"></a>
<span id="l459">        if (sp == null)</span><a href="#l459"></a>
<span id="l460">            separatorPattern = sp = Pattern.compile(LINE_SEPARATOR_PATTERN);</span><a href="#l460"></a>
<span id="l461">        return sp;</span><a href="#l461"></a>
<span id="l462">    }</span><a href="#l462"></a>
<span id="l463"></span><a href="#l463"></a>
<span id="l464">    private static Pattern linePattern() {</span><a href="#l464"></a>
<span id="l465">        Pattern lp = linePattern;</span><a href="#l465"></a>
<span id="l466">        if (lp == null)</span><a href="#l466"></a>
<span id="l467">            linePattern = lp = Pattern.compile(LINE_PATTERN);</span><a href="#l467"></a>
<span id="l468">        return lp;</span><a href="#l468"></a>
<span id="l469">    }</span><a href="#l469"></a>
<span id="l470"></span><a href="#l470"></a>
<span id="l471">    /**</span><a href="#l471"></a>
<span id="l472">     * Fields and methods to match floats and doubles</span><a href="#l472"></a>
<span id="l473">     */</span><a href="#l473"></a>
<span id="l474">    private Pattern floatPattern;</span><a href="#l474"></a>
<span id="l475">    private Pattern decimalPattern;</span><a href="#l475"></a>
<span id="l476">    private void buildFloatAndDecimalPattern() {</span><a href="#l476"></a>
<span id="l477">        // \\p{javaDigit} may not be perfect, see above</span><a href="#l477"></a>
<span id="l478">        String digit = &quot;(([0-9\\p{javaDigit}]))&quot;;</span><a href="#l478"></a>
<span id="l479">        String exponent = &quot;([eE][+-]?&quot;+digit+&quot;+)?&quot;;</span><a href="#l479"></a>
<span id="l480">        String groupedNumeral = &quot;(&quot;+non0Digit+digit+&quot;?&quot;+digit+&quot;?(&quot;+</span><a href="#l480"></a>
<span id="l481">                                groupSeparator+digit+digit+digit+&quot;)+)&quot;;</span><a href="#l481"></a>
<span id="l482">        // Once again digit++ is used for performance, as above</span><a href="#l482"></a>
<span id="l483">        String numeral = &quot;((&quot;+digit+&quot;++)|&quot;+groupedNumeral+&quot;)&quot;;</span><a href="#l483"></a>
<span id="l484">        String decimalNumeral = &quot;(&quot;+numeral+&quot;|&quot;+numeral +</span><a href="#l484"></a>
<span id="l485">            decimalSeparator + digit + &quot;*+|&quot;+ decimalSeparator +</span><a href="#l485"></a>
<span id="l486">            digit + &quot;++)&quot;;</span><a href="#l486"></a>
<span id="l487">        String nonNumber = &quot;(NaN|&quot;+nanString+&quot;|Infinity|&quot;+</span><a href="#l487"></a>
<span id="l488">                               infinityString+&quot;)&quot;;</span><a href="#l488"></a>
<span id="l489">        String positiveFloat = &quot;(&quot; + positivePrefix + decimalNumeral +</span><a href="#l489"></a>
<span id="l490">                            positiveSuffix + exponent + &quot;)&quot;;</span><a href="#l490"></a>
<span id="l491">        String negativeFloat = &quot;(&quot; + negativePrefix + decimalNumeral +</span><a href="#l491"></a>
<span id="l492">                            negativeSuffix + exponent + &quot;)&quot;;</span><a href="#l492"></a>
<span id="l493">        String decimal = &quot;(([-+]?&quot; + decimalNumeral + exponent + &quot;)|&quot;+</span><a href="#l493"></a>
<span id="l494">            positiveFloat + &quot;|&quot; + negativeFloat + &quot;)&quot;;</span><a href="#l494"></a>
<span id="l495">        String hexFloat =</span><a href="#l495"></a>
<span id="l496">            &quot;[-+]?0[xX][0-9a-fA-F]*\\.[0-9a-fA-F]+([pP][-+]?[0-9]+)?&quot;;</span><a href="#l496"></a>
<span id="l497">        String positiveNonNumber = &quot;(&quot; + positivePrefix + nonNumber +</span><a href="#l497"></a>
<span id="l498">                            positiveSuffix + &quot;)&quot;;</span><a href="#l498"></a>
<span id="l499">        String negativeNonNumber = &quot;(&quot; + negativePrefix + nonNumber +</span><a href="#l499"></a>
<span id="l500">                            negativeSuffix + &quot;)&quot;;</span><a href="#l500"></a>
<span id="l501">        String signedNonNumber = &quot;(([-+]?&quot;+nonNumber+&quot;)|&quot; +</span><a href="#l501"></a>
<span id="l502">                                 positiveNonNumber + &quot;|&quot; +</span><a href="#l502"></a>
<span id="l503">                                 negativeNonNumber + &quot;)&quot;;</span><a href="#l503"></a>
<span id="l504">        floatPattern = Pattern.compile(decimal + &quot;|&quot; + hexFloat + &quot;|&quot; +</span><a href="#l504"></a>
<span id="l505">                                       signedNonNumber);</span><a href="#l505"></a>
<span id="l506">        decimalPattern = Pattern.compile(decimal);</span><a href="#l506"></a>
<span id="l507">    }</span><a href="#l507"></a>
<span id="l508">    private Pattern floatPattern() {</span><a href="#l508"></a>
<span id="l509">        if (floatPattern == null) {</span><a href="#l509"></a>
<span id="l510">            buildFloatAndDecimalPattern();</span><a href="#l510"></a>
<span id="l511">        }</span><a href="#l511"></a>
<span id="l512">        return floatPattern;</span><a href="#l512"></a>
<span id="l513">    }</span><a href="#l513"></a>
<span id="l514">    private Pattern decimalPattern() {</span><a href="#l514"></a>
<span id="l515">        if (decimalPattern == null) {</span><a href="#l515"></a>
<span id="l516">            buildFloatAndDecimalPattern();</span><a href="#l516"></a>
<span id="l517">        }</span><a href="#l517"></a>
<span id="l518">        return decimalPattern;</span><a href="#l518"></a>
<span id="l519">    }</span><a href="#l519"></a>
<span id="l520"></span><a href="#l520"></a>
<span id="l521">    // Constructors</span><a href="#l521"></a>
<span id="l522"></span><a href="#l522"></a>
<span id="l523">    /**</span><a href="#l523"></a>
<span id="l524">     * Constructs a &lt;code&gt;Scanner&lt;/code&gt; that returns values scanned</span><a href="#l524"></a>
<span id="l525">     * from the specified source delimited by the specified pattern.</span><a href="#l525"></a>
<span id="l526">     *</span><a href="#l526"></a>
<span id="l527">     * @param source A character source implementing the Readable interface</span><a href="#l527"></a>
<span id="l528">     * @param pattern A delimiting pattern</span><a href="#l528"></a>
<span id="l529">     */</span><a href="#l529"></a>
<span id="l530">    private Scanner(Readable source, Pattern pattern) {</span><a href="#l530"></a>
<span id="l531">        assert source != null : &quot;source should not be null&quot;;</span><a href="#l531"></a>
<span id="l532">        assert pattern != null : &quot;pattern should not be null&quot;;</span><a href="#l532"></a>
<span id="l533">        this.source = source;</span><a href="#l533"></a>
<span id="l534">        delimPattern = pattern;</span><a href="#l534"></a>
<span id="l535">        buf = CharBuffer.allocate(BUFFER_SIZE);</span><a href="#l535"></a>
<span id="l536">        buf.limit(0);</span><a href="#l536"></a>
<span id="l537">        matcher = delimPattern.matcher(buf);</span><a href="#l537"></a>
<span id="l538">        matcher.useTransparentBounds(true);</span><a href="#l538"></a>
<span id="l539">        matcher.useAnchoringBounds(false);</span><a href="#l539"></a>
<span id="l540">        useLocale(Locale.getDefault(Locale.Category.FORMAT));</span><a href="#l540"></a>
<span id="l541">    }</span><a href="#l541"></a>
<span id="l542"></span><a href="#l542"></a>
<span id="l543">    /**</span><a href="#l543"></a>
<span id="l544">     * Constructs a new &lt;code&gt;Scanner&lt;/code&gt; that produces values scanned</span><a href="#l544"></a>
<span id="l545">     * from the specified source.</span><a href="#l545"></a>
<span id="l546">     *</span><a href="#l546"></a>
<span id="l547">     * @param  source A character source implementing the {@link Readable}</span><a href="#l547"></a>
<span id="l548">     *         interface</span><a href="#l548"></a>
<span id="l549">     */</span><a href="#l549"></a>
<span id="l550">    public Scanner(Readable source) {</span><a href="#l550"></a>
<span id="l551">        this(Objects.requireNonNull(source, &quot;source&quot;), WHITESPACE_PATTERN);</span><a href="#l551"></a>
<span id="l552">    }</span><a href="#l552"></a>
<span id="l553"></span><a href="#l553"></a>
<span id="l554">    /**</span><a href="#l554"></a>
<span id="l555">     * Constructs a new &lt;code&gt;Scanner&lt;/code&gt; that produces values scanned</span><a href="#l555"></a>
<span id="l556">     * from the specified input stream. Bytes from the stream are converted</span><a href="#l556"></a>
<span id="l557">     * into characters using the underlying platform's</span><a href="#l557"></a>
<span id="l558">     * {@linkplain java.nio.charset.Charset#defaultCharset() default charset}.</span><a href="#l558"></a>
<span id="l559">     *</span><a href="#l559"></a>
<span id="l560">     * @param  source An input stream to be scanned</span><a href="#l560"></a>
<span id="l561">     */</span><a href="#l561"></a>
<span id="l562">    public Scanner(InputStream source) {</span><a href="#l562"></a>
<span id="l563">        this(new InputStreamReader(source), WHITESPACE_PATTERN);</span><a href="#l563"></a>
<span id="l564">    }</span><a href="#l564"></a>
<span id="l565"></span><a href="#l565"></a>
<span id="l566">    /**</span><a href="#l566"></a>
<span id="l567">     * Constructs a new &lt;code&gt;Scanner&lt;/code&gt; that produces values scanned</span><a href="#l567"></a>
<span id="l568">     * from the specified input stream. Bytes from the stream are converted</span><a href="#l568"></a>
<span id="l569">     * into characters using the specified charset.</span><a href="#l569"></a>
<span id="l570">     *</span><a href="#l570"></a>
<span id="l571">     * @param  source An input stream to be scanned</span><a href="#l571"></a>
<span id="l572">     * @param charsetName The encoding type used to convert bytes from the</span><a href="#l572"></a>
<span id="l573">     *        stream into characters to be scanned</span><a href="#l573"></a>
<span id="l574">     * @throws IllegalArgumentException if the specified character set</span><a href="#l574"></a>
<span id="l575">     *         does not exist</span><a href="#l575"></a>
<span id="l576">     */</span><a href="#l576"></a>
<span id="l577">    public Scanner(InputStream source, String charsetName) {</span><a href="#l577"></a>
<span id="l578">        this(makeReadable(Objects.requireNonNull(source, &quot;source&quot;), toCharset(charsetName)),</span><a href="#l578"></a>
<span id="l579">             WHITESPACE_PATTERN);</span><a href="#l579"></a>
<span id="l580">    }</span><a href="#l580"></a>
<span id="l581"></span><a href="#l581"></a>
<span id="l582">    /**</span><a href="#l582"></a>
<span id="l583">     * Returns a charset object for the given charset name.</span><a href="#l583"></a>
<span id="l584">     * @throws NullPointerException          is csn is null</span><a href="#l584"></a>
<span id="l585">     * @throws IllegalArgumentException      if the charset is not supported</span><a href="#l585"></a>
<span id="l586">     */</span><a href="#l586"></a>
<span id="l587">    private static Charset toCharset(String csn) {</span><a href="#l587"></a>
<span id="l588">        Objects.requireNonNull(csn, &quot;charsetName&quot;);</span><a href="#l588"></a>
<span id="l589">        try {</span><a href="#l589"></a>
<span id="l590">            return Charset.forName(csn);</span><a href="#l590"></a>
<span id="l591">        } catch (IllegalCharsetNameException|UnsupportedCharsetException e) {</span><a href="#l591"></a>
<span id="l592">            // IllegalArgumentException should be thrown</span><a href="#l592"></a>
<span id="l593">            throw new IllegalArgumentException(e);</span><a href="#l593"></a>
<span id="l594">        }</span><a href="#l594"></a>
<span id="l595">    }</span><a href="#l595"></a>
<span id="l596"></span><a href="#l596"></a>
<span id="l597">    private static Readable makeReadable(InputStream source, Charset charset) {</span><a href="#l597"></a>
<span id="l598">        return new InputStreamReader(source, charset);</span><a href="#l598"></a>
<span id="l599">    }</span><a href="#l599"></a>
<span id="l600"></span><a href="#l600"></a>
<span id="l601">    /**</span><a href="#l601"></a>
<span id="l602">     * Constructs a new &lt;code&gt;Scanner&lt;/code&gt; that produces values scanned</span><a href="#l602"></a>
<span id="l603">     * from the specified file. Bytes from the file are converted into</span><a href="#l603"></a>
<span id="l604">     * characters using the underlying platform's</span><a href="#l604"></a>
<span id="l605">     * {@linkplain java.nio.charset.Charset#defaultCharset() default charset}.</span><a href="#l605"></a>
<span id="l606">     *</span><a href="#l606"></a>
<span id="l607">     * @param  source A file to be scanned</span><a href="#l607"></a>
<span id="l608">     * @throws FileNotFoundException if source is not found</span><a href="#l608"></a>
<span id="l609">     */</span><a href="#l609"></a>
<span id="l610">    public Scanner(File source) throws FileNotFoundException {</span><a href="#l610"></a>
<span id="l611">        this((ReadableByteChannel)(new FileInputStream(source).getChannel()));</span><a href="#l611"></a>
<span id="l612">    }</span><a href="#l612"></a>
<span id="l613"></span><a href="#l613"></a>
<span id="l614">    /**</span><a href="#l614"></a>
<span id="l615">     * Constructs a new &lt;code&gt;Scanner&lt;/code&gt; that produces values scanned</span><a href="#l615"></a>
<span id="l616">     * from the specified file. Bytes from the file are converted into</span><a href="#l616"></a>
<span id="l617">     * characters using the specified charset.</span><a href="#l617"></a>
<span id="l618">     *</span><a href="#l618"></a>
<span id="l619">     * @param  source A file to be scanned</span><a href="#l619"></a>
<span id="l620">     * @param charsetName The encoding type used to convert bytes from the file</span><a href="#l620"></a>
<span id="l621">     *        into characters to be scanned</span><a href="#l621"></a>
<span id="l622">     * @throws FileNotFoundException if source is not found</span><a href="#l622"></a>
<span id="l623">     * @throws IllegalArgumentException if the specified encoding is</span><a href="#l623"></a>
<span id="l624">     *         not found</span><a href="#l624"></a>
<span id="l625">     */</span><a href="#l625"></a>
<span id="l626">    public Scanner(File source, String charsetName)</span><a href="#l626"></a>
<span id="l627">        throws FileNotFoundException</span><a href="#l627"></a>
<span id="l628">    {</span><a href="#l628"></a>
<span id="l629">        this(Objects.requireNonNull(source), toDecoder(charsetName));</span><a href="#l629"></a>
<span id="l630">    }</span><a href="#l630"></a>
<span id="l631"></span><a href="#l631"></a>
<span id="l632">    private Scanner(File source, CharsetDecoder dec)</span><a href="#l632"></a>
<span id="l633">        throws FileNotFoundException</span><a href="#l633"></a>
<span id="l634">    {</span><a href="#l634"></a>
<span id="l635">        this(makeReadable((ReadableByteChannel)(new FileInputStream(source).getChannel()), dec));</span><a href="#l635"></a>
<span id="l636">    }</span><a href="#l636"></a>
<span id="l637"></span><a href="#l637"></a>
<span id="l638">    private static CharsetDecoder toDecoder(String charsetName) {</span><a href="#l638"></a>
<span id="l639">        Objects.requireNonNull(charsetName, &quot;charsetName&quot;);</span><a href="#l639"></a>
<span id="l640">        try {</span><a href="#l640"></a>
<span id="l641">            return Charset.forName(charsetName).newDecoder();</span><a href="#l641"></a>
<span id="l642">        } catch (IllegalCharsetNameException|UnsupportedCharsetException unused) {</span><a href="#l642"></a>
<span id="l643">            throw new IllegalArgumentException(charsetName);</span><a href="#l643"></a>
<span id="l644">        }</span><a href="#l644"></a>
<span id="l645">    }</span><a href="#l645"></a>
<span id="l646"></span><a href="#l646"></a>
<span id="l647">    private static Readable makeReadable(ReadableByteChannel source,</span><a href="#l647"></a>
<span id="l648">                                         CharsetDecoder dec) {</span><a href="#l648"></a>
<span id="l649">        return Channels.newReader(source, dec, -1);</span><a href="#l649"></a>
<span id="l650">    }</span><a href="#l650"></a>
<span id="l651"></span><a href="#l651"></a>
<span id="l652">    /**</span><a href="#l652"></a>
<span id="l653">     * Constructs a new &lt;code&gt;Scanner&lt;/code&gt; that produces values scanned</span><a href="#l653"></a>
<span id="l654">     * from the specified file. Bytes from the file are converted into</span><a href="#l654"></a>
<span id="l655">     * characters using the underlying platform's</span><a href="#l655"></a>
<span id="l656">     * {@linkplain java.nio.charset.Charset#defaultCharset() default charset}.</span><a href="#l656"></a>
<span id="l657">     *</span><a href="#l657"></a>
<span id="l658">     * @param   source</span><a href="#l658"></a>
<span id="l659">     *          the path to the file to be scanned</span><a href="#l659"></a>
<span id="l660">     * @throws  IOException</span><a href="#l660"></a>
<span id="l661">     *          if an I/O error occurs opening source</span><a href="#l661"></a>
<span id="l662">     *</span><a href="#l662"></a>
<span id="l663">     * @since   1.7</span><a href="#l663"></a>
<span id="l664">     */</span><a href="#l664"></a>
<span id="l665">    public Scanner(Path source)</span><a href="#l665"></a>
<span id="l666">        throws IOException</span><a href="#l666"></a>
<span id="l667">    {</span><a href="#l667"></a>
<span id="l668">        this(Files.newInputStream(source));</span><a href="#l668"></a>
<span id="l669">    }</span><a href="#l669"></a>
<span id="l670"></span><a href="#l670"></a>
<span id="l671">    /**</span><a href="#l671"></a>
<span id="l672">     * Constructs a new &lt;code&gt;Scanner&lt;/code&gt; that produces values scanned</span><a href="#l672"></a>
<span id="l673">     * from the specified file. Bytes from the file are converted into</span><a href="#l673"></a>
<span id="l674">     * characters using the specified charset.</span><a href="#l674"></a>
<span id="l675">     *</span><a href="#l675"></a>
<span id="l676">     * @param   source</span><a href="#l676"></a>
<span id="l677">     *          the path to the file to be scanned</span><a href="#l677"></a>
<span id="l678">     * @param   charsetName</span><a href="#l678"></a>
<span id="l679">     *          The encoding type used to convert bytes from the file</span><a href="#l679"></a>
<span id="l680">     *          into characters to be scanned</span><a href="#l680"></a>
<span id="l681">     * @throws  IOException</span><a href="#l681"></a>
<span id="l682">     *          if an I/O error occurs opening source</span><a href="#l682"></a>
<span id="l683">     * @throws  IllegalArgumentException</span><a href="#l683"></a>
<span id="l684">     *          if the specified encoding is not found</span><a href="#l684"></a>
<span id="l685">     * @since   1.7</span><a href="#l685"></a>
<span id="l686">     */</span><a href="#l686"></a>
<span id="l687">    public Scanner(Path source, String charsetName) throws IOException {</span><a href="#l687"></a>
<span id="l688">        this(Objects.requireNonNull(source), toCharset(charsetName));</span><a href="#l688"></a>
<span id="l689">    }</span><a href="#l689"></a>
<span id="l690"></span><a href="#l690"></a>
<span id="l691">    private Scanner(Path source, Charset charset)  throws IOException {</span><a href="#l691"></a>
<span id="l692">        this(makeReadable(Files.newInputStream(source), charset));</span><a href="#l692"></a>
<span id="l693">    }</span><a href="#l693"></a>
<span id="l694"></span><a href="#l694"></a>
<span id="l695">    /**</span><a href="#l695"></a>
<span id="l696">     * Constructs a new &lt;code&gt;Scanner&lt;/code&gt; that produces values scanned</span><a href="#l696"></a>
<span id="l697">     * from the specified string.</span><a href="#l697"></a>
<span id="l698">     *</span><a href="#l698"></a>
<span id="l699">     * @param  source A string to scan</span><a href="#l699"></a>
<span id="l700">     */</span><a href="#l700"></a>
<span id="l701">    public Scanner(String source) {</span><a href="#l701"></a>
<span id="l702">        this(new StringReader(source), WHITESPACE_PATTERN);</span><a href="#l702"></a>
<span id="l703">    }</span><a href="#l703"></a>
<span id="l704"></span><a href="#l704"></a>
<span id="l705">    /**</span><a href="#l705"></a>
<span id="l706">     * Constructs a new &lt;code&gt;Scanner&lt;/code&gt; that produces values scanned</span><a href="#l706"></a>
<span id="l707">     * from the specified channel. Bytes from the source are converted into</span><a href="#l707"></a>
<span id="l708">     * characters using the underlying platform's</span><a href="#l708"></a>
<span id="l709">     * {@linkplain java.nio.charset.Charset#defaultCharset() default charset}.</span><a href="#l709"></a>
<span id="l710">     *</span><a href="#l710"></a>
<span id="l711">     * @param  source A channel to scan</span><a href="#l711"></a>
<span id="l712">     */</span><a href="#l712"></a>
<span id="l713">    public Scanner(ReadableByteChannel source) {</span><a href="#l713"></a>
<span id="l714">        this(makeReadable(Objects.requireNonNull(source, &quot;source&quot;)),</span><a href="#l714"></a>
<span id="l715">             WHITESPACE_PATTERN);</span><a href="#l715"></a>
<span id="l716">    }</span><a href="#l716"></a>
<span id="l717"></span><a href="#l717"></a>
<span id="l718">    private static Readable makeReadable(ReadableByteChannel source) {</span><a href="#l718"></a>
<span id="l719">        return makeReadable(source, Charset.defaultCharset().newDecoder());</span><a href="#l719"></a>
<span id="l720">    }</span><a href="#l720"></a>
<span id="l721"></span><a href="#l721"></a>
<span id="l722">    /**</span><a href="#l722"></a>
<span id="l723">     * Constructs a new &lt;code&gt;Scanner&lt;/code&gt; that produces values scanned</span><a href="#l723"></a>
<span id="l724">     * from the specified channel. Bytes from the source are converted into</span><a href="#l724"></a>
<span id="l725">     * characters using the specified charset.</span><a href="#l725"></a>
<span id="l726">     *</span><a href="#l726"></a>
<span id="l727">     * @param  source A channel to scan</span><a href="#l727"></a>
<span id="l728">     * @param charsetName The encoding type used to convert bytes from the</span><a href="#l728"></a>
<span id="l729">     *        channel into characters to be scanned</span><a href="#l729"></a>
<span id="l730">     * @throws IllegalArgumentException if the specified character set</span><a href="#l730"></a>
<span id="l731">     *         does not exist</span><a href="#l731"></a>
<span id="l732">     */</span><a href="#l732"></a>
<span id="l733">    public Scanner(ReadableByteChannel source, String charsetName) {</span><a href="#l733"></a>
<span id="l734">        this(makeReadable(Objects.requireNonNull(source, &quot;source&quot;), toDecoder(charsetName)),</span><a href="#l734"></a>
<span id="l735">             WHITESPACE_PATTERN);</span><a href="#l735"></a>
<span id="l736">    }</span><a href="#l736"></a>
<span id="l737"></span><a href="#l737"></a>
<span id="l738">    // Private primitives used to support scanning</span><a href="#l738"></a>
<span id="l739"></span><a href="#l739"></a>
<span id="l740">    private void saveState() {</span><a href="#l740"></a>
<span id="l741">        savedScannerPosition = position;</span><a href="#l741"></a>
<span id="l742">    }</span><a href="#l742"></a>
<span id="l743"></span><a href="#l743"></a>
<span id="l744">    private void revertState() {</span><a href="#l744"></a>
<span id="l745">        this.position = savedScannerPosition;</span><a href="#l745"></a>
<span id="l746">        savedScannerPosition = -1;</span><a href="#l746"></a>
<span id="l747">        skipped = false;</span><a href="#l747"></a>
<span id="l748">    }</span><a href="#l748"></a>
<span id="l749"></span><a href="#l749"></a>
<span id="l750">    private boolean revertState(boolean b) {</span><a href="#l750"></a>
<span id="l751">        this.position = savedScannerPosition;</span><a href="#l751"></a>
<span id="l752">        savedScannerPosition = -1;</span><a href="#l752"></a>
<span id="l753">        skipped = false;</span><a href="#l753"></a>
<span id="l754">        return b;</span><a href="#l754"></a>
<span id="l755">    }</span><a href="#l755"></a>
<span id="l756"></span><a href="#l756"></a>
<span id="l757">    private void cacheResult() {</span><a href="#l757"></a>
<span id="l758">        hasNextResult = matcher.group();</span><a href="#l758"></a>
<span id="l759">        hasNextPosition = matcher.end();</span><a href="#l759"></a>
<span id="l760">        hasNextPattern = matcher.pattern();</span><a href="#l760"></a>
<span id="l761">    }</span><a href="#l761"></a>
<span id="l762"></span><a href="#l762"></a>
<span id="l763">    private void cacheResult(String result) {</span><a href="#l763"></a>
<span id="l764">        hasNextResult = result;</span><a href="#l764"></a>
<span id="l765">        hasNextPosition = matcher.end();</span><a href="#l765"></a>
<span id="l766">        hasNextPattern = matcher.pattern();</span><a href="#l766"></a>
<span id="l767">    }</span><a href="#l767"></a>
<span id="l768"></span><a href="#l768"></a>
<span id="l769">    // Clears both regular cache and type cache</span><a href="#l769"></a>
<span id="l770">    private void clearCaches() {</span><a href="#l770"></a>
<span id="l771">        hasNextPattern = null;</span><a href="#l771"></a>
<span id="l772">        typeCache = null;</span><a href="#l772"></a>
<span id="l773">    }</span><a href="#l773"></a>
<span id="l774"></span><a href="#l774"></a>
<span id="l775">    // Also clears both the regular cache and the type cache</span><a href="#l775"></a>
<span id="l776">    private String getCachedResult() {</span><a href="#l776"></a>
<span id="l777">        position = hasNextPosition;</span><a href="#l777"></a>
<span id="l778">        hasNextPattern = null;</span><a href="#l778"></a>
<span id="l779">        typeCache = null;</span><a href="#l779"></a>
<span id="l780">        return hasNextResult;</span><a href="#l780"></a>
<span id="l781">    }</span><a href="#l781"></a>
<span id="l782"></span><a href="#l782"></a>
<span id="l783">    // Also clears both the regular cache and the type cache</span><a href="#l783"></a>
<span id="l784">    private void useTypeCache() {</span><a href="#l784"></a>
<span id="l785">        if (closed)</span><a href="#l785"></a>
<span id="l786">            throw new IllegalStateException(&quot;Scanner closed&quot;);</span><a href="#l786"></a>
<span id="l787">        position = hasNextPosition;</span><a href="#l787"></a>
<span id="l788">        hasNextPattern = null;</span><a href="#l788"></a>
<span id="l789">        typeCache = null;</span><a href="#l789"></a>
<span id="l790">    }</span><a href="#l790"></a>
<span id="l791"></span><a href="#l791"></a>
<span id="l792">    // Tries to read more input. May block.</span><a href="#l792"></a>
<span id="l793">    private void readInput() {</span><a href="#l793"></a>
<span id="l794">        if (buf.limit() == buf.capacity())</span><a href="#l794"></a>
<span id="l795">            makeSpace();</span><a href="#l795"></a>
<span id="l796"></span><a href="#l796"></a>
<span id="l797">        // Prepare to receive data</span><a href="#l797"></a>
<span id="l798">        int p = buf.position();</span><a href="#l798"></a>
<span id="l799">        buf.position(buf.limit());</span><a href="#l799"></a>
<span id="l800">        buf.limit(buf.capacity());</span><a href="#l800"></a>
<span id="l801"></span><a href="#l801"></a>
<span id="l802">        int n = 0;</span><a href="#l802"></a>
<span id="l803">        try {</span><a href="#l803"></a>
<span id="l804">            n = source.read(buf);</span><a href="#l804"></a>
<span id="l805">        } catch (IOException ioe) {</span><a href="#l805"></a>
<span id="l806">            lastException = ioe;</span><a href="#l806"></a>
<span id="l807">            n = -1;</span><a href="#l807"></a>
<span id="l808">        }</span><a href="#l808"></a>
<span id="l809"></span><a href="#l809"></a>
<span id="l810">        if (n == -1) {</span><a href="#l810"></a>
<span id="l811">            sourceClosed = true;</span><a href="#l811"></a>
<span id="l812">            needInput = false;</span><a href="#l812"></a>
<span id="l813">        }</span><a href="#l813"></a>
<span id="l814"></span><a href="#l814"></a>
<span id="l815">        if (n &gt; 0)</span><a href="#l815"></a>
<span id="l816">            needInput = false;</span><a href="#l816"></a>
<span id="l817"></span><a href="#l817"></a>
<span id="l818">        // Restore current position and limit for reading</span><a href="#l818"></a>
<span id="l819">        buf.limit(buf.position());</span><a href="#l819"></a>
<span id="l820">        buf.position(p);</span><a href="#l820"></a>
<span id="l821">    }</span><a href="#l821"></a>
<span id="l822"></span><a href="#l822"></a>
<span id="l823">    // After this method is called there will either be an exception</span><a href="#l823"></a>
<span id="l824">    // or else there will be space in the buffer</span><a href="#l824"></a>
<span id="l825">    private boolean makeSpace() {</span><a href="#l825"></a>
<span id="l826">        clearCaches();</span><a href="#l826"></a>
<span id="l827">        int offset = savedScannerPosition == -1 ?</span><a href="#l827"></a>
<span id="l828">            position : savedScannerPosition;</span><a href="#l828"></a>
<span id="l829">        buf.position(offset);</span><a href="#l829"></a>
<span id="l830">        // Gain space by compacting buffer</span><a href="#l830"></a>
<span id="l831">        if (offset &gt; 0) {</span><a href="#l831"></a>
<span id="l832">            buf.compact();</span><a href="#l832"></a>
<span id="l833">            translateSavedIndexes(offset);</span><a href="#l833"></a>
<span id="l834">            position -= offset;</span><a href="#l834"></a>
<span id="l835">            buf.flip();</span><a href="#l835"></a>
<span id="l836">            return true;</span><a href="#l836"></a>
<span id="l837">        }</span><a href="#l837"></a>
<span id="l838">        // Gain space by growing buffer</span><a href="#l838"></a>
<span id="l839">        int newSize = buf.capacity() * 2;</span><a href="#l839"></a>
<span id="l840">        CharBuffer newBuf = CharBuffer.allocate(newSize);</span><a href="#l840"></a>
<span id="l841">        newBuf.put(buf);</span><a href="#l841"></a>
<span id="l842">        newBuf.flip();</span><a href="#l842"></a>
<span id="l843">        translateSavedIndexes(offset);</span><a href="#l843"></a>
<span id="l844">        position -= offset;</span><a href="#l844"></a>
<span id="l845">        buf = newBuf;</span><a href="#l845"></a>
<span id="l846">        matcher.reset(buf);</span><a href="#l846"></a>
<span id="l847">        return true;</span><a href="#l847"></a>
<span id="l848">    }</span><a href="#l848"></a>
<span id="l849"></span><a href="#l849"></a>
<span id="l850">    // When a buffer compaction/reallocation occurs the saved indexes must</span><a href="#l850"></a>
<span id="l851">    // be modified appropriately</span><a href="#l851"></a>
<span id="l852">    private void translateSavedIndexes(int offset) {</span><a href="#l852"></a>
<span id="l853">        if (savedScannerPosition != -1)</span><a href="#l853"></a>
<span id="l854">            savedScannerPosition -= offset;</span><a href="#l854"></a>
<span id="l855">    }</span><a href="#l855"></a>
<span id="l856"></span><a href="#l856"></a>
<span id="l857">    // If we are at the end of input then NoSuchElement;</span><a href="#l857"></a>
<span id="l858">    // If there is still input left then InputMismatch</span><a href="#l858"></a>
<span id="l859">    private void throwFor() {</span><a href="#l859"></a>
<span id="l860">        skipped = false;</span><a href="#l860"></a>
<span id="l861">        if ((sourceClosed) &amp;&amp; (position == buf.limit()))</span><a href="#l861"></a>
<span id="l862">            throw new NoSuchElementException();</span><a href="#l862"></a>
<span id="l863">        else</span><a href="#l863"></a>
<span id="l864">            throw new InputMismatchException();</span><a href="#l864"></a>
<span id="l865">    }</span><a href="#l865"></a>
<span id="l866"></span><a href="#l866"></a>
<span id="l867">    // Returns true if a complete token or partial token is in the buffer.</span><a href="#l867"></a>
<span id="l868">    // It is not necessary to find a complete token since a partial token</span><a href="#l868"></a>
<span id="l869">    // means that there will be another token with or without more input.</span><a href="#l869"></a>
<span id="l870">    private boolean hasTokenInBuffer() {</span><a href="#l870"></a>
<span id="l871">        matchValid = false;</span><a href="#l871"></a>
<span id="l872">        matcher.usePattern(delimPattern);</span><a href="#l872"></a>
<span id="l873">        matcher.region(position, buf.limit());</span><a href="#l873"></a>
<span id="l874"></span><a href="#l874"></a>
<span id="l875">        // Skip delims first</span><a href="#l875"></a>
<span id="l876">        if (matcher.lookingAt())</span><a href="#l876"></a>
<span id="l877">            position = matcher.end();</span><a href="#l877"></a>
<span id="l878"></span><a href="#l878"></a>
<span id="l879">        // If we are sitting at the end, no more tokens in buffer</span><a href="#l879"></a>
<span id="l880">        if (position == buf.limit())</span><a href="#l880"></a>
<span id="l881">            return false;</span><a href="#l881"></a>
<span id="l882"></span><a href="#l882"></a>
<span id="l883">        return true;</span><a href="#l883"></a>
<span id="l884">    }</span><a href="#l884"></a>
<span id="l885"></span><a href="#l885"></a>
<span id="l886">    /*</span><a href="#l886"></a>
<span id="l887">     * Returns a &quot;complete token&quot; that matches the specified pattern</span><a href="#l887"></a>
<span id="l888">     *</span><a href="#l888"></a>
<span id="l889">     * A token is complete if surrounded by delims; a partial token</span><a href="#l889"></a>
<span id="l890">     * is prefixed by delims but not postfixed by them</span><a href="#l890"></a>
<span id="l891">     *</span><a href="#l891"></a>
<span id="l892">     * The position is advanced to the end of that complete token</span><a href="#l892"></a>
<span id="l893">     *</span><a href="#l893"></a>
<span id="l894">     * Pattern == null means accept any token at all</span><a href="#l894"></a>
<span id="l895">     *</span><a href="#l895"></a>
<span id="l896">     * Triple return:</span><a href="#l896"></a>
<span id="l897">     * 1. valid string means it was found</span><a href="#l897"></a>
<span id="l898">     * 2. null with needInput=false means we won't ever find it</span><a href="#l898"></a>
<span id="l899">     * 3. null with needInput=true means try again after readInput</span><a href="#l899"></a>
<span id="l900">     */</span><a href="#l900"></a>
<span id="l901">    private String getCompleteTokenInBuffer(Pattern pattern) {</span><a href="#l901"></a>
<span id="l902">        matchValid = false;</span><a href="#l902"></a>
<span id="l903"></span><a href="#l903"></a>
<span id="l904">        // Skip delims first</span><a href="#l904"></a>
<span id="l905">        matcher.usePattern(delimPattern);</span><a href="#l905"></a>
<span id="l906">        if (!skipped) { // Enforcing only one skip of leading delims</span><a href="#l906"></a>
<span id="l907">            matcher.region(position, buf.limit());</span><a href="#l907"></a>
<span id="l908">            if (matcher.lookingAt()) {</span><a href="#l908"></a>
<span id="l909">                // If more input could extend the delimiters then we must wait</span><a href="#l909"></a>
<span id="l910">                // for more input</span><a href="#l910"></a>
<span id="l911">                if (matcher.hitEnd() &amp;&amp; !sourceClosed) {</span><a href="#l911"></a>
<span id="l912">                    needInput = true;</span><a href="#l912"></a>
<span id="l913">                    return null;</span><a href="#l913"></a>
<span id="l914">                }</span><a href="#l914"></a>
<span id="l915">                // The delims were whole and the matcher should skip them</span><a href="#l915"></a>
<span id="l916">                skipped = true;</span><a href="#l916"></a>
<span id="l917">                position = matcher.end();</span><a href="#l917"></a>
<span id="l918">            }</span><a href="#l918"></a>
<span id="l919">        }</span><a href="#l919"></a>
<span id="l920"></span><a href="#l920"></a>
<span id="l921">        // If we are sitting at the end, no more tokens in buffer</span><a href="#l921"></a>
<span id="l922">        if (position == buf.limit()) {</span><a href="#l922"></a>
<span id="l923">            if (sourceClosed)</span><a href="#l923"></a>
<span id="l924">                return null;</span><a href="#l924"></a>
<span id="l925">            needInput = true;</span><a href="#l925"></a>
<span id="l926">            return null;</span><a href="#l926"></a>
<span id="l927">        }</span><a href="#l927"></a>
<span id="l928"></span><a href="#l928"></a>
<span id="l929">        // Must look for next delims. Simply attempting to match the</span><a href="#l929"></a>
<span id="l930">        // pattern at this point may find a match but it might not be</span><a href="#l930"></a>
<span id="l931">        // the first longest match because of missing input, or it might</span><a href="#l931"></a>
<span id="l932">        // match a partial token instead of the whole thing.</span><a href="#l932"></a>
<span id="l933"></span><a href="#l933"></a>
<span id="l934">        // Then look for next delims</span><a href="#l934"></a>
<span id="l935">        matcher.region(position, buf.limit());</span><a href="#l935"></a>
<span id="l936">        boolean foundNextDelim = matcher.find();</span><a href="#l936"></a>
<span id="l937">        if (foundNextDelim &amp;&amp; (matcher.end() == position)) {</span><a href="#l937"></a>
<span id="l938">            // Zero length delimiter match; we should find the next one</span><a href="#l938"></a>
<span id="l939">            // using the automatic advance past a zero length match;</span><a href="#l939"></a>
<span id="l940">            // Otherwise we have just found the same one we just skipped</span><a href="#l940"></a>
<span id="l941">            foundNextDelim = matcher.find();</span><a href="#l941"></a>
<span id="l942">        }</span><a href="#l942"></a>
<span id="l943">        if (foundNextDelim) {</span><a href="#l943"></a>
<span id="l944">            // In the rare case that more input could cause the match</span><a href="#l944"></a>
<span id="l945">            // to be lost and there is more input coming we must wait</span><a href="#l945"></a>
<span id="l946">            // for more input. Note that hitting the end is okay as long</span><a href="#l946"></a>
<span id="l947">            // as the match cannot go away. It is the beginning of the</span><a href="#l947"></a>
<span id="l948">            // next delims we want to be sure about, we don't care if</span><a href="#l948"></a>
<span id="l949">            // they potentially extend further.</span><a href="#l949"></a>
<span id="l950">            if (matcher.requireEnd() &amp;&amp; !sourceClosed) {</span><a href="#l950"></a>
<span id="l951">                needInput = true;</span><a href="#l951"></a>
<span id="l952">                return null;</span><a href="#l952"></a>
<span id="l953">            }</span><a href="#l953"></a>
<span id="l954">            int tokenEnd = matcher.start();</span><a href="#l954"></a>
<span id="l955">            // There is a complete token.</span><a href="#l955"></a>
<span id="l956">            if (pattern == null) {</span><a href="#l956"></a>
<span id="l957">                // Must continue with match to provide valid MatchResult</span><a href="#l957"></a>
<span id="l958">                pattern = FIND_ANY_PATTERN;</span><a href="#l958"></a>
<span id="l959">            }</span><a href="#l959"></a>
<span id="l960">            //  Attempt to match against the desired pattern</span><a href="#l960"></a>
<span id="l961">            matcher.usePattern(pattern);</span><a href="#l961"></a>
<span id="l962">            matcher.region(position, tokenEnd);</span><a href="#l962"></a>
<span id="l963">            if (matcher.matches()) {</span><a href="#l963"></a>
<span id="l964">                String s = matcher.group();</span><a href="#l964"></a>
<span id="l965">                position = matcher.end();</span><a href="#l965"></a>
<span id="l966">                return s;</span><a href="#l966"></a>
<span id="l967">            } else { // Complete token but it does not match</span><a href="#l967"></a>
<span id="l968">                return null;</span><a href="#l968"></a>
<span id="l969">            }</span><a href="#l969"></a>
<span id="l970">        }</span><a href="#l970"></a>
<span id="l971"></span><a href="#l971"></a>
<span id="l972">        // If we can't find the next delims but no more input is coming,</span><a href="#l972"></a>
<span id="l973">        // then we can treat the remainder as a whole token</span><a href="#l973"></a>
<span id="l974">        if (sourceClosed) {</span><a href="#l974"></a>
<span id="l975">            if (pattern == null) {</span><a href="#l975"></a>
<span id="l976">                // Must continue with match to provide valid MatchResult</span><a href="#l976"></a>
<span id="l977">                pattern = FIND_ANY_PATTERN;</span><a href="#l977"></a>
<span id="l978">            }</span><a href="#l978"></a>
<span id="l979">            // Last token; Match the pattern here or throw</span><a href="#l979"></a>
<span id="l980">            matcher.usePattern(pattern);</span><a href="#l980"></a>
<span id="l981">            matcher.region(position, buf.limit());</span><a href="#l981"></a>
<span id="l982">            if (matcher.matches()) {</span><a href="#l982"></a>
<span id="l983">                String s = matcher.group();</span><a href="#l983"></a>
<span id="l984">                position = matcher.end();</span><a href="#l984"></a>
<span id="l985">                return s;</span><a href="#l985"></a>
<span id="l986">            }</span><a href="#l986"></a>
<span id="l987">            // Last piece does not match</span><a href="#l987"></a>
<span id="l988">            return null;</span><a href="#l988"></a>
<span id="l989">        }</span><a href="#l989"></a>
<span id="l990"></span><a href="#l990"></a>
<span id="l991">        // There is a partial token in the buffer; must read more</span><a href="#l991"></a>
<span id="l992">        // to complete it</span><a href="#l992"></a>
<span id="l993">        needInput = true;</span><a href="#l993"></a>
<span id="l994">        return null;</span><a href="#l994"></a>
<span id="l995">    }</span><a href="#l995"></a>
<span id="l996"></span><a href="#l996"></a>
<span id="l997">    // Finds the specified pattern in the buffer up to horizon.</span><a href="#l997"></a>
<span id="l998">    // Returns a match for the specified input pattern.</span><a href="#l998"></a>
<span id="l999">    private String findPatternInBuffer(Pattern pattern, int horizon) {</span><a href="#l999"></a>
<span id="l1000">        matchValid = false;</span><a href="#l1000"></a>
<span id="l1001">        matcher.usePattern(pattern);</span><a href="#l1001"></a>
<span id="l1002">        int bufferLimit = buf.limit();</span><a href="#l1002"></a>
<span id="l1003">        int horizonLimit = -1;</span><a href="#l1003"></a>
<span id="l1004">        int searchLimit = bufferLimit;</span><a href="#l1004"></a>
<span id="l1005">        if (horizon &gt; 0) {</span><a href="#l1005"></a>
<span id="l1006">            horizonLimit = position + horizon;</span><a href="#l1006"></a>
<span id="l1007">            if (horizonLimit &lt; bufferLimit)</span><a href="#l1007"></a>
<span id="l1008">                searchLimit = horizonLimit;</span><a href="#l1008"></a>
<span id="l1009">        }</span><a href="#l1009"></a>
<span id="l1010">        matcher.region(position, searchLimit);</span><a href="#l1010"></a>
<span id="l1011">        if (matcher.find()) {</span><a href="#l1011"></a>
<span id="l1012">            if (matcher.hitEnd() &amp;&amp; (!sourceClosed)) {</span><a href="#l1012"></a>
<span id="l1013">                // The match may be longer if didn't hit horizon or real end</span><a href="#l1013"></a>
<span id="l1014">                if (searchLimit != horizonLimit) {</span><a href="#l1014"></a>
<span id="l1015">                     // Hit an artificial end; try to extend the match</span><a href="#l1015"></a>
<span id="l1016">                    needInput = true;</span><a href="#l1016"></a>
<span id="l1017">                    return null;</span><a href="#l1017"></a>
<span id="l1018">                }</span><a href="#l1018"></a>
<span id="l1019">                // The match could go away depending on what is next</span><a href="#l1019"></a>
<span id="l1020">                if ((searchLimit == horizonLimit) &amp;&amp; matcher.requireEnd()) {</span><a href="#l1020"></a>
<span id="l1021">                    // Rare case: we hit the end of input and it happens</span><a href="#l1021"></a>
<span id="l1022">                    // that it is at the horizon and the end of input is</span><a href="#l1022"></a>
<span id="l1023">                    // required for the match.</span><a href="#l1023"></a>
<span id="l1024">                    needInput = true;</span><a href="#l1024"></a>
<span id="l1025">                    return null;</span><a href="#l1025"></a>
<span id="l1026">                }</span><a href="#l1026"></a>
<span id="l1027">            }</span><a href="#l1027"></a>
<span id="l1028">            // Did not hit end, or hit real end, or hit horizon</span><a href="#l1028"></a>
<span id="l1029">            position = matcher.end();</span><a href="#l1029"></a>
<span id="l1030">            return matcher.group();</span><a href="#l1030"></a>
<span id="l1031">        }</span><a href="#l1031"></a>
<span id="l1032"></span><a href="#l1032"></a>
<span id="l1033">        if (sourceClosed)</span><a href="#l1033"></a>
<span id="l1034">            return null;</span><a href="#l1034"></a>
<span id="l1035"></span><a href="#l1035"></a>
<span id="l1036">        // If there is no specified horizon, or if we have not searched</span><a href="#l1036"></a>
<span id="l1037">        // to the specified horizon yet, get more input</span><a href="#l1037"></a>
<span id="l1038">        if ((horizon == 0) || (searchLimit != horizonLimit))</span><a href="#l1038"></a>
<span id="l1039">            needInput = true;</span><a href="#l1039"></a>
<span id="l1040">        return null;</span><a href="#l1040"></a>
<span id="l1041">    }</span><a href="#l1041"></a>
<span id="l1042"></span><a href="#l1042"></a>
<span id="l1043">    // Returns a match for the specified input pattern anchored at</span><a href="#l1043"></a>
<span id="l1044">    // the current position</span><a href="#l1044"></a>
<span id="l1045">    private String matchPatternInBuffer(Pattern pattern) {</span><a href="#l1045"></a>
<span id="l1046">        matchValid = false;</span><a href="#l1046"></a>
<span id="l1047">        matcher.usePattern(pattern);</span><a href="#l1047"></a>
<span id="l1048">        matcher.region(position, buf.limit());</span><a href="#l1048"></a>
<span id="l1049">        if (matcher.lookingAt()) {</span><a href="#l1049"></a>
<span id="l1050">            if (matcher.hitEnd() &amp;&amp; (!sourceClosed)) {</span><a href="#l1050"></a>
<span id="l1051">                // Get more input and try again</span><a href="#l1051"></a>
<span id="l1052">                needInput = true;</span><a href="#l1052"></a>
<span id="l1053">                return null;</span><a href="#l1053"></a>
<span id="l1054">            }</span><a href="#l1054"></a>
<span id="l1055">            position = matcher.end();</span><a href="#l1055"></a>
<span id="l1056">            return matcher.group();</span><a href="#l1056"></a>
<span id="l1057">        }</span><a href="#l1057"></a>
<span id="l1058"></span><a href="#l1058"></a>
<span id="l1059">        if (sourceClosed)</span><a href="#l1059"></a>
<span id="l1060">            return null;</span><a href="#l1060"></a>
<span id="l1061"></span><a href="#l1061"></a>
<span id="l1062">        // Read more to find pattern</span><a href="#l1062"></a>
<span id="l1063">        needInput = true;</span><a href="#l1063"></a>
<span id="l1064">        return null;</span><a href="#l1064"></a>
<span id="l1065">    }</span><a href="#l1065"></a>
<span id="l1066"></span><a href="#l1066"></a>
<span id="l1067">    // Throws if the scanner is closed</span><a href="#l1067"></a>
<span id="l1068">    private void ensureOpen() {</span><a href="#l1068"></a>
<span id="l1069">        if (closed)</span><a href="#l1069"></a>
<span id="l1070">            throw new IllegalStateException(&quot;Scanner closed&quot;);</span><a href="#l1070"></a>
<span id="l1071">    }</span><a href="#l1071"></a>
<span id="l1072"></span><a href="#l1072"></a>
<span id="l1073">    // Public methods</span><a href="#l1073"></a>
<span id="l1074"></span><a href="#l1074"></a>
<span id="l1075">    /**</span><a href="#l1075"></a>
<span id="l1076">     * Closes this scanner.</span><a href="#l1076"></a>
<span id="l1077">     *</span><a href="#l1077"></a>
<span id="l1078">     * &lt;p&gt; If this scanner has not yet been closed then if its underlying</span><a href="#l1078"></a>
<span id="l1079">     * {@linkplain java.lang.Readable readable} also implements the {@link</span><a href="#l1079"></a>
<span id="l1080">     * java.io.Closeable} interface then the readable's &lt;tt&gt;close&lt;/tt&gt; method</span><a href="#l1080"></a>
<span id="l1081">     * will be invoked.  If this scanner is already closed then invoking this</span><a href="#l1081"></a>
<span id="l1082">     * method will have no effect.</span><a href="#l1082"></a>
<span id="l1083">     *</span><a href="#l1083"></a>
<span id="l1084">     * &lt;p&gt;Attempting to perform search operations after a scanner has</span><a href="#l1084"></a>
<span id="l1085">     * been closed will result in an {@link IllegalStateException}.</span><a href="#l1085"></a>
<span id="l1086">     *</span><a href="#l1086"></a>
<span id="l1087">     */</span><a href="#l1087"></a>
<span id="l1088">    public void close() {</span><a href="#l1088"></a>
<span id="l1089">        if (closed)</span><a href="#l1089"></a>
<span id="l1090">            return;</span><a href="#l1090"></a>
<span id="l1091">        if (source instanceof Closeable) {</span><a href="#l1091"></a>
<span id="l1092">            try {</span><a href="#l1092"></a>
<span id="l1093">                ((Closeable)source).close();</span><a href="#l1093"></a>
<span id="l1094">            } catch (IOException ioe) {</span><a href="#l1094"></a>
<span id="l1095">                lastException = ioe;</span><a href="#l1095"></a>
<span id="l1096">            }</span><a href="#l1096"></a>
<span id="l1097">        }</span><a href="#l1097"></a>
<span id="l1098">        sourceClosed = true;</span><a href="#l1098"></a>
<span id="l1099">        source = null;</span><a href="#l1099"></a>
<span id="l1100">        closed = true;</span><a href="#l1100"></a>
<span id="l1101">    }</span><a href="#l1101"></a>
<span id="l1102"></span><a href="#l1102"></a>
<span id="l1103">    /**</span><a href="#l1103"></a>
<span id="l1104">     * Returns the &lt;code&gt;IOException&lt;/code&gt; last thrown by this</span><a href="#l1104"></a>
<span id="l1105">     * &lt;code&gt;Scanner&lt;/code&gt;'s underlying &lt;code&gt;Readable&lt;/code&gt;. This method</span><a href="#l1105"></a>
<span id="l1106">     * returns &lt;code&gt;null&lt;/code&gt; if no such exception exists.</span><a href="#l1106"></a>
<span id="l1107">     *</span><a href="#l1107"></a>
<span id="l1108">     * @return the last exception thrown by this scanner's readable</span><a href="#l1108"></a>
<span id="l1109">     */</span><a href="#l1109"></a>
<span id="l1110">    public IOException ioException() {</span><a href="#l1110"></a>
<span id="l1111">        return lastException;</span><a href="#l1111"></a>
<span id="l1112">    }</span><a href="#l1112"></a>
<span id="l1113"></span><a href="#l1113"></a>
<span id="l1114">    /**</span><a href="#l1114"></a>
<span id="l1115">     * Returns the &lt;code&gt;Pattern&lt;/code&gt; this &lt;code&gt;Scanner&lt;/code&gt; is currently</span><a href="#l1115"></a>
<span id="l1116">     * using to match delimiters.</span><a href="#l1116"></a>
<span id="l1117">     *</span><a href="#l1117"></a>
<span id="l1118">     * @return this scanner's delimiting pattern.</span><a href="#l1118"></a>
<span id="l1119">     */</span><a href="#l1119"></a>
<span id="l1120">    public Pattern delimiter() {</span><a href="#l1120"></a>
<span id="l1121">        return delimPattern;</span><a href="#l1121"></a>
<span id="l1122">    }</span><a href="#l1122"></a>
<span id="l1123"></span><a href="#l1123"></a>
<span id="l1124">    /**</span><a href="#l1124"></a>
<span id="l1125">     * Sets this scanner's delimiting pattern to the specified pattern.</span><a href="#l1125"></a>
<span id="l1126">     *</span><a href="#l1126"></a>
<span id="l1127">     * @param pattern A delimiting pattern</span><a href="#l1127"></a>
<span id="l1128">     * @return this scanner</span><a href="#l1128"></a>
<span id="l1129">     */</span><a href="#l1129"></a>
<span id="l1130">    public Scanner useDelimiter(Pattern pattern) {</span><a href="#l1130"></a>
<span id="l1131">        delimPattern = pattern;</span><a href="#l1131"></a>
<span id="l1132">        return this;</span><a href="#l1132"></a>
<span id="l1133">    }</span><a href="#l1133"></a>
<span id="l1134"></span><a href="#l1134"></a>
<span id="l1135">    /**</span><a href="#l1135"></a>
<span id="l1136">     * Sets this scanner's delimiting pattern to a pattern constructed from</span><a href="#l1136"></a>
<span id="l1137">     * the specified &lt;code&gt;String&lt;/code&gt;.</span><a href="#l1137"></a>
<span id="l1138">     *</span><a href="#l1138"></a>
<span id="l1139">     * &lt;p&gt; An invocation of this method of the form</span><a href="#l1139"></a>
<span id="l1140">     * &lt;tt&gt;useDelimiter(pattern)&lt;/tt&gt; behaves in exactly the same way as the</span><a href="#l1140"></a>
<span id="l1141">     * invocation &lt;tt&gt;useDelimiter(Pattern.compile(pattern))&lt;/tt&gt;.</span><a href="#l1141"></a>
<span id="l1142">     *</span><a href="#l1142"></a>
<span id="l1143">     * &lt;p&gt; Invoking the {@link #reset} method will set the scanner's delimiter</span><a href="#l1143"></a>
<span id="l1144">     * to the &lt;a href= &quot;#default-delimiter&quot;&gt;default&lt;/a&gt;.</span><a href="#l1144"></a>
<span id="l1145">     *</span><a href="#l1145"></a>
<span id="l1146">     * @param pattern A string specifying a delimiting pattern</span><a href="#l1146"></a>
<span id="l1147">     * @return this scanner</span><a href="#l1147"></a>
<span id="l1148">     */</span><a href="#l1148"></a>
<span id="l1149">    public Scanner useDelimiter(String pattern) {</span><a href="#l1149"></a>
<span id="l1150">        delimPattern = patternCache.forName(pattern);</span><a href="#l1150"></a>
<span id="l1151">        return this;</span><a href="#l1151"></a>
<span id="l1152">    }</span><a href="#l1152"></a>
<span id="l1153"></span><a href="#l1153"></a>
<span id="l1154">    /**</span><a href="#l1154"></a>
<span id="l1155">     * Returns this scanner's locale.</span><a href="#l1155"></a>
<span id="l1156">     *</span><a href="#l1156"></a>
<span id="l1157">     * &lt;p&gt;A scanner's locale affects many elements of its default</span><a href="#l1157"></a>
<span id="l1158">     * primitive matching regular expressions; see</span><a href="#l1158"></a>
<span id="l1159">     * &lt;a href= &quot;#localized-numbers&quot;&gt;localized numbers&lt;/a&gt; above.</span><a href="#l1159"></a>
<span id="l1160">     *</span><a href="#l1160"></a>
<span id="l1161">     * @return this scanner's locale</span><a href="#l1161"></a>
<span id="l1162">     */</span><a href="#l1162"></a>
<span id="l1163">    public Locale locale() {</span><a href="#l1163"></a>
<span id="l1164">        return this.locale;</span><a href="#l1164"></a>
<span id="l1165">    }</span><a href="#l1165"></a>
<span id="l1166"></span><a href="#l1166"></a>
<span id="l1167">    /**</span><a href="#l1167"></a>
<span id="l1168">     * Sets this scanner's locale to the specified locale.</span><a href="#l1168"></a>
<span id="l1169">     *</span><a href="#l1169"></a>
<span id="l1170">     * &lt;p&gt;A scanner's locale affects many elements of its default</span><a href="#l1170"></a>
<span id="l1171">     * primitive matching regular expressions; see</span><a href="#l1171"></a>
<span id="l1172">     * &lt;a href= &quot;#localized-numbers&quot;&gt;localized numbers&lt;/a&gt; above.</span><a href="#l1172"></a>
<span id="l1173">     *</span><a href="#l1173"></a>
<span id="l1174">     * &lt;p&gt;Invoking the {@link #reset} method will set the scanner's locale to</span><a href="#l1174"></a>
<span id="l1175">     * the &lt;a href= &quot;#initial-locale&quot;&gt;initial locale&lt;/a&gt;.</span><a href="#l1175"></a>
<span id="l1176">     *</span><a href="#l1176"></a>
<span id="l1177">     * @param locale A string specifying the locale to use</span><a href="#l1177"></a>
<span id="l1178">     * @return this scanner</span><a href="#l1178"></a>
<span id="l1179">     */</span><a href="#l1179"></a>
<span id="l1180">    public Scanner useLocale(Locale locale) {</span><a href="#l1180"></a>
<span id="l1181">        if (locale.equals(this.locale))</span><a href="#l1181"></a>
<span id="l1182">            return this;</span><a href="#l1182"></a>
<span id="l1183"></span><a href="#l1183"></a>
<span id="l1184">        this.locale = locale;</span><a href="#l1184"></a>
<span id="l1185">        DecimalFormat df =</span><a href="#l1185"></a>
<span id="l1186">            (DecimalFormat)NumberFormat.getNumberInstance(locale);</span><a href="#l1186"></a>
<span id="l1187">        DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance(locale);</span><a href="#l1187"></a>
<span id="l1188"></span><a href="#l1188"></a>
<span id="l1189">        // These must be literalized to avoid collision with regex</span><a href="#l1189"></a>
<span id="l1190">        // metacharacters such as dot or parenthesis</span><a href="#l1190"></a>
<span id="l1191">        groupSeparator =   &quot;\\x{&quot; + Integer.toHexString(dfs.getGroupingSeparator()) + &quot;}&quot;;</span><a href="#l1191"></a>
<span id="l1192">        decimalSeparator = &quot;\\x{&quot; + Integer.toHexString(dfs.getDecimalSeparator()) + &quot;}&quot;;</span><a href="#l1192"></a>
<span id="l1193"></span><a href="#l1193"></a>
<span id="l1194">        // Quoting the nonzero length locale-specific things</span><a href="#l1194"></a>
<span id="l1195">        // to avoid potential conflict with metacharacters</span><a href="#l1195"></a>
<span id="l1196">        nanString = Pattern.quote(dfs.getNaN());</span><a href="#l1196"></a>
<span id="l1197">        infinityString = Pattern.quote(dfs.getInfinity());</span><a href="#l1197"></a>
<span id="l1198">        positivePrefix = df.getPositivePrefix();</span><a href="#l1198"></a>
<span id="l1199">        if (positivePrefix.length() &gt; 0)</span><a href="#l1199"></a>
<span id="l1200">            positivePrefix = Pattern.quote(positivePrefix);</span><a href="#l1200"></a>
<span id="l1201">        negativePrefix = df.getNegativePrefix();</span><a href="#l1201"></a>
<span id="l1202">        if (negativePrefix.length() &gt; 0)</span><a href="#l1202"></a>
<span id="l1203">            negativePrefix = Pattern.quote(negativePrefix);</span><a href="#l1203"></a>
<span id="l1204">        positiveSuffix = df.getPositiveSuffix();</span><a href="#l1204"></a>
<span id="l1205">        if (positiveSuffix.length() &gt; 0)</span><a href="#l1205"></a>
<span id="l1206">            positiveSuffix = Pattern.quote(positiveSuffix);</span><a href="#l1206"></a>
<span id="l1207">        negativeSuffix = df.getNegativeSuffix();</span><a href="#l1207"></a>
<span id="l1208">        if (negativeSuffix.length() &gt; 0)</span><a href="#l1208"></a>
<span id="l1209">            negativeSuffix = Pattern.quote(negativeSuffix);</span><a href="#l1209"></a>
<span id="l1210"></span><a href="#l1210"></a>
<span id="l1211">        // Force rebuilding and recompilation of locale dependent</span><a href="#l1211"></a>
<span id="l1212">        // primitive patterns</span><a href="#l1212"></a>
<span id="l1213">        integerPattern = null;</span><a href="#l1213"></a>
<span id="l1214">        floatPattern = null;</span><a href="#l1214"></a>
<span id="l1215"></span><a href="#l1215"></a>
<span id="l1216">        return this;</span><a href="#l1216"></a>
<span id="l1217">    }</span><a href="#l1217"></a>
<span id="l1218"></span><a href="#l1218"></a>
<span id="l1219">    /**</span><a href="#l1219"></a>
<span id="l1220">     * Returns this scanner's default radix.</span><a href="#l1220"></a>
<span id="l1221">     *</span><a href="#l1221"></a>
<span id="l1222">     * &lt;p&gt;A scanner's radix affects elements of its default</span><a href="#l1222"></a>
<span id="l1223">     * number matching regular expressions; see</span><a href="#l1223"></a>
<span id="l1224">     * &lt;a href= &quot;#localized-numbers&quot;&gt;localized numbers&lt;/a&gt; above.</span><a href="#l1224"></a>
<span id="l1225">     *</span><a href="#l1225"></a>
<span id="l1226">     * @return the default radix of this scanner</span><a href="#l1226"></a>
<span id="l1227">     */</span><a href="#l1227"></a>
<span id="l1228">    public int radix() {</span><a href="#l1228"></a>
<span id="l1229">        return this.defaultRadix;</span><a href="#l1229"></a>
<span id="l1230">    }</span><a href="#l1230"></a>
<span id="l1231"></span><a href="#l1231"></a>
<span id="l1232">    /**</span><a href="#l1232"></a>
<span id="l1233">     * Sets this scanner's default radix to the specified radix.</span><a href="#l1233"></a>
<span id="l1234">     *</span><a href="#l1234"></a>
<span id="l1235">     * &lt;p&gt;A scanner's radix affects elements of its default</span><a href="#l1235"></a>
<span id="l1236">     * number matching regular expressions; see</span><a href="#l1236"></a>
<span id="l1237">     * &lt;a href= &quot;#localized-numbers&quot;&gt;localized numbers&lt;/a&gt; above.</span><a href="#l1237"></a>
<span id="l1238">     *</span><a href="#l1238"></a>
<span id="l1239">     * &lt;p&gt;If the radix is less than &lt;code&gt;Character.MIN_RADIX&lt;/code&gt;</span><a href="#l1239"></a>
<span id="l1240">     * or greater than &lt;code&gt;Character.MAX_RADIX&lt;/code&gt;, then an</span><a href="#l1240"></a>
<span id="l1241">     * &lt;code&gt;IllegalArgumentException&lt;/code&gt; is thrown.</span><a href="#l1241"></a>
<span id="l1242">     *</span><a href="#l1242"></a>
<span id="l1243">     * &lt;p&gt;Invoking the {@link #reset} method will set the scanner's radix to</span><a href="#l1243"></a>
<span id="l1244">     * &lt;code&gt;10&lt;/code&gt;.</span><a href="#l1244"></a>
<span id="l1245">     *</span><a href="#l1245"></a>
<span id="l1246">     * @param radix The radix to use when scanning numbers</span><a href="#l1246"></a>
<span id="l1247">     * @return this scanner</span><a href="#l1247"></a>
<span id="l1248">     * @throws IllegalArgumentException if radix is out of range</span><a href="#l1248"></a>
<span id="l1249">     */</span><a href="#l1249"></a>
<span id="l1250">    public Scanner useRadix(int radix) {</span><a href="#l1250"></a>
<span id="l1251">        if ((radix &lt; Character.MIN_RADIX) || (radix &gt; Character.MAX_RADIX))</span><a href="#l1251"></a>
<span id="l1252">            throw new IllegalArgumentException(&quot;radix:&quot;+radix);</span><a href="#l1252"></a>
<span id="l1253"></span><a href="#l1253"></a>
<span id="l1254">        if (this.defaultRadix == radix)</span><a href="#l1254"></a>
<span id="l1255">            return this;</span><a href="#l1255"></a>
<span id="l1256">        this.defaultRadix = radix;</span><a href="#l1256"></a>
<span id="l1257">        // Force rebuilding and recompilation of radix dependent patterns</span><a href="#l1257"></a>
<span id="l1258">        integerPattern = null;</span><a href="#l1258"></a>
<span id="l1259">        return this;</span><a href="#l1259"></a>
<span id="l1260">    }</span><a href="#l1260"></a>
<span id="l1261"></span><a href="#l1261"></a>
<span id="l1262">    // The next operation should occur in the specified radix but</span><a href="#l1262"></a>
<span id="l1263">    // the default is left untouched.</span><a href="#l1263"></a>
<span id="l1264">    private void setRadix(int radix) {</span><a href="#l1264"></a>
<span id="l1265">        if (this.radix != radix) {</span><a href="#l1265"></a>
<span id="l1266">            // Force rebuilding and recompilation of radix dependent patterns</span><a href="#l1266"></a>
<span id="l1267">            integerPattern = null;</span><a href="#l1267"></a>
<span id="l1268">            this.radix = radix;</span><a href="#l1268"></a>
<span id="l1269">        }</span><a href="#l1269"></a>
<span id="l1270">    }</span><a href="#l1270"></a>
<span id="l1271"></span><a href="#l1271"></a>
<span id="l1272">    /**</span><a href="#l1272"></a>
<span id="l1273">     * Returns the match result of the last scanning operation performed</span><a href="#l1273"></a>
<span id="l1274">     * by this scanner. This method throws &lt;code&gt;IllegalStateException&lt;/code&gt;</span><a href="#l1274"></a>
<span id="l1275">     * if no match has been performed, or if the last match was</span><a href="#l1275"></a>
<span id="l1276">     * not successful.</span><a href="#l1276"></a>
<span id="l1277">     *</span><a href="#l1277"></a>
<span id="l1278">     * &lt;p&gt;The various &lt;code&gt;next&lt;/code&gt;methods of &lt;code&gt;Scanner&lt;/code&gt;</span><a href="#l1278"></a>
<span id="l1279">     * make a match result available if they complete without throwing an</span><a href="#l1279"></a>
<span id="l1280">     * exception. For instance, after an invocation of the {@link #nextInt}</span><a href="#l1280"></a>
<span id="l1281">     * method that returned an int, this method returns a</span><a href="#l1281"></a>
<span id="l1282">     * &lt;code&gt;MatchResult&lt;/code&gt; for the search of the</span><a href="#l1282"></a>
<span id="l1283">     * &lt;a href=&quot;#Integer-regex&quot;&gt;&lt;i&gt;Integer&lt;/i&gt;&lt;/a&gt; regular expression</span><a href="#l1283"></a>
<span id="l1284">     * defined above. Similarly the {@link #findInLine},</span><a href="#l1284"></a>
<span id="l1285">     * {@link #findWithinHorizon}, and {@link #skip} methods will make a</span><a href="#l1285"></a>
<span id="l1286">     * match available if they succeed.</span><a href="#l1286"></a>
<span id="l1287">     *</span><a href="#l1287"></a>
<span id="l1288">     * @return a match result for the last match operation</span><a href="#l1288"></a>
<span id="l1289">     * @throws IllegalStateException  If no match result is available</span><a href="#l1289"></a>
<span id="l1290">     */</span><a href="#l1290"></a>
<span id="l1291">    public MatchResult match() {</span><a href="#l1291"></a>
<span id="l1292">        if (!matchValid)</span><a href="#l1292"></a>
<span id="l1293">            throw new IllegalStateException(&quot;No match result available&quot;);</span><a href="#l1293"></a>
<span id="l1294">        return matcher.toMatchResult();</span><a href="#l1294"></a>
<span id="l1295">    }</span><a href="#l1295"></a>
<span id="l1296"></span><a href="#l1296"></a>
<span id="l1297">    /**</span><a href="#l1297"></a>
<span id="l1298">     * &lt;p&gt;Returns the string representation of this &lt;code&gt;Scanner&lt;/code&gt;. The</span><a href="#l1298"></a>
<span id="l1299">     * string representation of a &lt;code&gt;Scanner&lt;/code&gt; contains information</span><a href="#l1299"></a>
<span id="l1300">     * that may be useful for debugging. The exact format is unspecified.</span><a href="#l1300"></a>
<span id="l1301">     *</span><a href="#l1301"></a>
<span id="l1302">     * @return  The string representation of this scanner</span><a href="#l1302"></a>
<span id="l1303">     */</span><a href="#l1303"></a>
<span id="l1304">    public String toString() {</span><a href="#l1304"></a>
<span id="l1305">        StringBuilder sb = new StringBuilder();</span><a href="#l1305"></a>
<span id="l1306">        sb.append(&quot;java.util.Scanner&quot;);</span><a href="#l1306"></a>
<span id="l1307">        sb.append(&quot;[delimiters=&quot; + delimPattern + &quot;]&quot;);</span><a href="#l1307"></a>
<span id="l1308">        sb.append(&quot;[position=&quot; + position + &quot;]&quot;);</span><a href="#l1308"></a>
<span id="l1309">        sb.append(&quot;[match valid=&quot; + matchValid + &quot;]&quot;);</span><a href="#l1309"></a>
<span id="l1310">        sb.append(&quot;[need input=&quot; + needInput + &quot;]&quot;);</span><a href="#l1310"></a>
<span id="l1311">        sb.append(&quot;[source closed=&quot; + sourceClosed + &quot;]&quot;);</span><a href="#l1311"></a>
<span id="l1312">        sb.append(&quot;[skipped=&quot; + skipped + &quot;]&quot;);</span><a href="#l1312"></a>
<span id="l1313">        sb.append(&quot;[group separator=&quot; + groupSeparator + &quot;]&quot;);</span><a href="#l1313"></a>
<span id="l1314">        sb.append(&quot;[decimal separator=&quot; + decimalSeparator + &quot;]&quot;);</span><a href="#l1314"></a>
<span id="l1315">        sb.append(&quot;[positive prefix=&quot; + positivePrefix + &quot;]&quot;);</span><a href="#l1315"></a>
<span id="l1316">        sb.append(&quot;[negative prefix=&quot; + negativePrefix + &quot;]&quot;);</span><a href="#l1316"></a>
<span id="l1317">        sb.append(&quot;[positive suffix=&quot; + positiveSuffix + &quot;]&quot;);</span><a href="#l1317"></a>
<span id="l1318">        sb.append(&quot;[negative suffix=&quot; + negativeSuffix + &quot;]&quot;);</span><a href="#l1318"></a>
<span id="l1319">        sb.append(&quot;[NaN string=&quot; + nanString + &quot;]&quot;);</span><a href="#l1319"></a>
<span id="l1320">        sb.append(&quot;[infinity string=&quot; + infinityString + &quot;]&quot;);</span><a href="#l1320"></a>
<span id="l1321">        return sb.toString();</span><a href="#l1321"></a>
<span id="l1322">    }</span><a href="#l1322"></a>
<span id="l1323"></span><a href="#l1323"></a>
<span id="l1324">    /**</span><a href="#l1324"></a>
<span id="l1325">     * Returns true if this scanner has another token in its input.</span><a href="#l1325"></a>
<span id="l1326">     * This method may block while waiting for input to scan.</span><a href="#l1326"></a>
<span id="l1327">     * The scanner does not advance past any input.</span><a href="#l1327"></a>
<span id="l1328">     *</span><a href="#l1328"></a>
<span id="l1329">     * @return true if and only if this scanner has another token</span><a href="#l1329"></a>
<span id="l1330">     * @throws IllegalStateException if this scanner is closed</span><a href="#l1330"></a>
<span id="l1331">     * @see java.util.Iterator</span><a href="#l1331"></a>
<span id="l1332">     */</span><a href="#l1332"></a>
<span id="l1333">    public boolean hasNext() {</span><a href="#l1333"></a>
<span id="l1334">        ensureOpen();</span><a href="#l1334"></a>
<span id="l1335">        saveState();</span><a href="#l1335"></a>
<span id="l1336">        while (!sourceClosed) {</span><a href="#l1336"></a>
<span id="l1337">            if (hasTokenInBuffer())</span><a href="#l1337"></a>
<span id="l1338">                return revertState(true);</span><a href="#l1338"></a>
<span id="l1339">            readInput();</span><a href="#l1339"></a>
<span id="l1340">        }</span><a href="#l1340"></a>
<span id="l1341">        boolean result = hasTokenInBuffer();</span><a href="#l1341"></a>
<span id="l1342">        return revertState(result);</span><a href="#l1342"></a>
<span id="l1343">    }</span><a href="#l1343"></a>
<span id="l1344"></span><a href="#l1344"></a>
<span id="l1345">    /**</span><a href="#l1345"></a>
<span id="l1346">     * Finds and returns the next complete token from this scanner.</span><a href="#l1346"></a>
<span id="l1347">     * A complete token is preceded and followed by input that matches</span><a href="#l1347"></a>
<span id="l1348">     * the delimiter pattern. This method may block while waiting for input</span><a href="#l1348"></a>
<span id="l1349">     * to scan, even if a previous invocation of {@link #hasNext} returned</span><a href="#l1349"></a>
<span id="l1350">     * &lt;code&gt;true&lt;/code&gt;.</span><a href="#l1350"></a>
<span id="l1351">     *</span><a href="#l1351"></a>
<span id="l1352">     * @return the next token</span><a href="#l1352"></a>
<span id="l1353">     * @throws NoSuchElementException if no more tokens are available</span><a href="#l1353"></a>
<span id="l1354">     * @throws IllegalStateException if this scanner is closed</span><a href="#l1354"></a>
<span id="l1355">     * @see java.util.Iterator</span><a href="#l1355"></a>
<span id="l1356">     */</span><a href="#l1356"></a>
<span id="l1357">    public String next() {</span><a href="#l1357"></a>
<span id="l1358">        ensureOpen();</span><a href="#l1358"></a>
<span id="l1359">        clearCaches();</span><a href="#l1359"></a>
<span id="l1360"></span><a href="#l1360"></a>
<span id="l1361">        while (true) {</span><a href="#l1361"></a>
<span id="l1362">            String token = getCompleteTokenInBuffer(null);</span><a href="#l1362"></a>
<span id="l1363">            if (token != null) {</span><a href="#l1363"></a>
<span id="l1364">                matchValid = true;</span><a href="#l1364"></a>
<span id="l1365">                skipped = false;</span><a href="#l1365"></a>
<span id="l1366">                return token;</span><a href="#l1366"></a>
<span id="l1367">            }</span><a href="#l1367"></a>
<span id="l1368">            if (needInput)</span><a href="#l1368"></a>
<span id="l1369">                readInput();</span><a href="#l1369"></a>
<span id="l1370">            else</span><a href="#l1370"></a>
<span id="l1371">                throwFor();</span><a href="#l1371"></a>
<span id="l1372">        }</span><a href="#l1372"></a>
<span id="l1373">    }</span><a href="#l1373"></a>
<span id="l1374"></span><a href="#l1374"></a>
<span id="l1375">    /**</span><a href="#l1375"></a>
<span id="l1376">     * The remove operation is not supported by this implementation of</span><a href="#l1376"></a>
<span id="l1377">     * &lt;code&gt;Iterator&lt;/code&gt;.</span><a href="#l1377"></a>
<span id="l1378">     *</span><a href="#l1378"></a>
<span id="l1379">     * @throws UnsupportedOperationException if this method is invoked.</span><a href="#l1379"></a>
<span id="l1380">     * @see java.util.Iterator</span><a href="#l1380"></a>
<span id="l1381">     */</span><a href="#l1381"></a>
<span id="l1382">    public void remove() {</span><a href="#l1382"></a>
<span id="l1383">        throw new UnsupportedOperationException();</span><a href="#l1383"></a>
<span id="l1384">    }</span><a href="#l1384"></a>
<span id="l1385"></span><a href="#l1385"></a>
<span id="l1386">    /**</span><a href="#l1386"></a>
<span id="l1387">     * Returns true if the next token matches the pattern constructed from the</span><a href="#l1387"></a>
<span id="l1388">     * specified string. The scanner does not advance past any input.</span><a href="#l1388"></a>
<span id="l1389">     *</span><a href="#l1389"></a>
<span id="l1390">     * &lt;p&gt; An invocation of this method of the form &lt;tt&gt;hasNext(pattern)&lt;/tt&gt;</span><a href="#l1390"></a>
<span id="l1391">     * behaves in exactly the same way as the invocation</span><a href="#l1391"></a>
<span id="l1392">     * &lt;tt&gt;hasNext(Pattern.compile(pattern))&lt;/tt&gt;.</span><a href="#l1392"></a>
<span id="l1393">     *</span><a href="#l1393"></a>
<span id="l1394">     * @param pattern a string specifying the pattern to scan</span><a href="#l1394"></a>
<span id="l1395">     * @return true if and only if this scanner has another token matching</span><a href="#l1395"></a>
<span id="l1396">     *         the specified pattern</span><a href="#l1396"></a>
<span id="l1397">     * @throws IllegalStateException if this scanner is closed</span><a href="#l1397"></a>
<span id="l1398">     */</span><a href="#l1398"></a>
<span id="l1399">    public boolean hasNext(String pattern)  {</span><a href="#l1399"></a>
<span id="l1400">        return hasNext(patternCache.forName(pattern));</span><a href="#l1400"></a>
<span id="l1401">    }</span><a href="#l1401"></a>
<span id="l1402"></span><a href="#l1402"></a>
<span id="l1403">    /**</span><a href="#l1403"></a>
<span id="l1404">     * Returns the next token if it matches the pattern constructed from the</span><a href="#l1404"></a>
<span id="l1405">     * specified string.  If the match is successful, the scanner advances</span><a href="#l1405"></a>
<span id="l1406">     * past the input that matched the pattern.</span><a href="#l1406"></a>
<span id="l1407">     *</span><a href="#l1407"></a>
<span id="l1408">     * &lt;p&gt; An invocation of this method of the form &lt;tt&gt;next(pattern)&lt;/tt&gt;</span><a href="#l1408"></a>
<span id="l1409">     * behaves in exactly the same way as the invocation</span><a href="#l1409"></a>
<span id="l1410">     * &lt;tt&gt;next(Pattern.compile(pattern))&lt;/tt&gt;.</span><a href="#l1410"></a>
<span id="l1411">     *</span><a href="#l1411"></a>
<span id="l1412">     * @param pattern a string specifying the pattern to scan</span><a href="#l1412"></a>
<span id="l1413">     * @return the next token</span><a href="#l1413"></a>
<span id="l1414">     * @throws NoSuchElementException if no such tokens are available</span><a href="#l1414"></a>
<span id="l1415">     * @throws IllegalStateException if this scanner is closed</span><a href="#l1415"></a>
<span id="l1416">     */</span><a href="#l1416"></a>
<span id="l1417">    public String next(String pattern)  {</span><a href="#l1417"></a>
<span id="l1418">        return next(patternCache.forName(pattern));</span><a href="#l1418"></a>
<span id="l1419">    }</span><a href="#l1419"></a>
<span id="l1420"></span><a href="#l1420"></a>
<span id="l1421">    /**</span><a href="#l1421"></a>
<span id="l1422">     * Returns true if the next complete token matches the specified pattern.</span><a href="#l1422"></a>
<span id="l1423">     * A complete token is prefixed and postfixed by input that matches</span><a href="#l1423"></a>
<span id="l1424">     * the delimiter pattern. This method may block while waiting for input.</span><a href="#l1424"></a>
<span id="l1425">     * The scanner does not advance past any input.</span><a href="#l1425"></a>
<span id="l1426">     *</span><a href="#l1426"></a>
<span id="l1427">     * @param pattern the pattern to scan for</span><a href="#l1427"></a>
<span id="l1428">     * @return true if and only if this scanner has another token matching</span><a href="#l1428"></a>
<span id="l1429">     *         the specified pattern</span><a href="#l1429"></a>
<span id="l1430">     * @throws IllegalStateException if this scanner is closed</span><a href="#l1430"></a>
<span id="l1431">     */</span><a href="#l1431"></a>
<span id="l1432">    public boolean hasNext(Pattern pattern) {</span><a href="#l1432"></a>
<span id="l1433">        ensureOpen();</span><a href="#l1433"></a>
<span id="l1434">        if (pattern == null)</span><a href="#l1434"></a>
<span id="l1435">            throw new NullPointerException();</span><a href="#l1435"></a>
<span id="l1436">        hasNextPattern = null;</span><a href="#l1436"></a>
<span id="l1437">        saveState();</span><a href="#l1437"></a>
<span id="l1438"></span><a href="#l1438"></a>
<span id="l1439">        while (true) {</span><a href="#l1439"></a>
<span id="l1440">            if (getCompleteTokenInBuffer(pattern) != null) {</span><a href="#l1440"></a>
<span id="l1441">                matchValid = true;</span><a href="#l1441"></a>
<span id="l1442">                cacheResult();</span><a href="#l1442"></a>
<span id="l1443">                return revertState(true);</span><a href="#l1443"></a>
<span id="l1444">            }</span><a href="#l1444"></a>
<span id="l1445">            if (needInput)</span><a href="#l1445"></a>
<span id="l1446">                readInput();</span><a href="#l1446"></a>
<span id="l1447">            else</span><a href="#l1447"></a>
<span id="l1448">                return revertState(false);</span><a href="#l1448"></a>
<span id="l1449">        }</span><a href="#l1449"></a>
<span id="l1450">    }</span><a href="#l1450"></a>
<span id="l1451"></span><a href="#l1451"></a>
<span id="l1452">    /**</span><a href="#l1452"></a>
<span id="l1453">     * Returns the next token if it matches the specified pattern. This</span><a href="#l1453"></a>
<span id="l1454">     * method may block while waiting for input to scan, even if a previous</span><a href="#l1454"></a>
<span id="l1455">     * invocation of {@link #hasNext(Pattern)} returned &lt;code&gt;true&lt;/code&gt;.</span><a href="#l1455"></a>
<span id="l1456">     * If the match is successful, the scanner advances past the input that</span><a href="#l1456"></a>
<span id="l1457">     * matched the pattern.</span><a href="#l1457"></a>
<span id="l1458">     *</span><a href="#l1458"></a>
<span id="l1459">     * @param pattern the pattern to scan for</span><a href="#l1459"></a>
<span id="l1460">     * @return the next token</span><a href="#l1460"></a>
<span id="l1461">     * @throws NoSuchElementException if no more tokens are available</span><a href="#l1461"></a>
<span id="l1462">     * @throws IllegalStateException if this scanner is closed</span><a href="#l1462"></a>
<span id="l1463">     */</span><a href="#l1463"></a>
<span id="l1464">    public String next(Pattern pattern) {</span><a href="#l1464"></a>
<span id="l1465">        ensureOpen();</span><a href="#l1465"></a>
<span id="l1466">        if (pattern == null)</span><a href="#l1466"></a>
<span id="l1467">            throw new NullPointerException();</span><a href="#l1467"></a>
<span id="l1468"></span><a href="#l1468"></a>
<span id="l1469">        // Did we already find this pattern?</span><a href="#l1469"></a>
<span id="l1470">        if (hasNextPattern == pattern)</span><a href="#l1470"></a>
<span id="l1471">            return getCachedResult();</span><a href="#l1471"></a>
<span id="l1472">        clearCaches();</span><a href="#l1472"></a>
<span id="l1473"></span><a href="#l1473"></a>
<span id="l1474">        // Search for the pattern</span><a href="#l1474"></a>
<span id="l1475">        while (true) {</span><a href="#l1475"></a>
<span id="l1476">            String token = getCompleteTokenInBuffer(pattern);</span><a href="#l1476"></a>
<span id="l1477">            if (token != null) {</span><a href="#l1477"></a>
<span id="l1478">                matchValid = true;</span><a href="#l1478"></a>
<span id="l1479">                skipped = false;</span><a href="#l1479"></a>
<span id="l1480">                return token;</span><a href="#l1480"></a>
<span id="l1481">            }</span><a href="#l1481"></a>
<span id="l1482">            if (needInput)</span><a href="#l1482"></a>
<span id="l1483">                readInput();</span><a href="#l1483"></a>
<span id="l1484">            else</span><a href="#l1484"></a>
<span id="l1485">                throwFor();</span><a href="#l1485"></a>
<span id="l1486">        }</span><a href="#l1486"></a>
<span id="l1487">    }</span><a href="#l1487"></a>
<span id="l1488"></span><a href="#l1488"></a>
<span id="l1489">    /**</span><a href="#l1489"></a>
<span id="l1490">     * Returns true if there is another line in the input of this scanner.</span><a href="#l1490"></a>
<span id="l1491">     * This method may block while waiting for input. The scanner does not</span><a href="#l1491"></a>
<span id="l1492">     * advance past any input.</span><a href="#l1492"></a>
<span id="l1493">     *</span><a href="#l1493"></a>
<span id="l1494">     * @return true if and only if this scanner has another line of input</span><a href="#l1494"></a>
<span id="l1495">     * @throws IllegalStateException if this scanner is closed</span><a href="#l1495"></a>
<span id="l1496">     */</span><a href="#l1496"></a>
<span id="l1497">    public boolean hasNextLine() {</span><a href="#l1497"></a>
<span id="l1498">        saveState();</span><a href="#l1498"></a>
<span id="l1499"></span><a href="#l1499"></a>
<span id="l1500">        String result = findWithinHorizon(linePattern(), 0);</span><a href="#l1500"></a>
<span id="l1501">        if (result != null) {</span><a href="#l1501"></a>
<span id="l1502">            MatchResult mr = this.match();</span><a href="#l1502"></a>
<span id="l1503">            String lineSep = mr.group(1);</span><a href="#l1503"></a>
<span id="l1504">            if (lineSep != null) {</span><a href="#l1504"></a>
<span id="l1505">                result = result.substring(0, result.length() -</span><a href="#l1505"></a>
<span id="l1506">                                          lineSep.length());</span><a href="#l1506"></a>
<span id="l1507">                cacheResult(result);</span><a href="#l1507"></a>
<span id="l1508"></span><a href="#l1508"></a>
<span id="l1509">            } else {</span><a href="#l1509"></a>
<span id="l1510">                cacheResult();</span><a href="#l1510"></a>
<span id="l1511">            }</span><a href="#l1511"></a>
<span id="l1512">        }</span><a href="#l1512"></a>
<span id="l1513">        revertState();</span><a href="#l1513"></a>
<span id="l1514">        return (result != null);</span><a href="#l1514"></a>
<span id="l1515">    }</span><a href="#l1515"></a>
<span id="l1516"></span><a href="#l1516"></a>
<span id="l1517">    /**</span><a href="#l1517"></a>
<span id="l1518">     * Advances this scanner past the current line and returns the input</span><a href="#l1518"></a>
<span id="l1519">     * that was skipped.</span><a href="#l1519"></a>
<span id="l1520">     *</span><a href="#l1520"></a>
<span id="l1521">     * This method returns the rest of the current line, excluding any line</span><a href="#l1521"></a>
<span id="l1522">     * separator at the end. The position is set to the beginning of the next</span><a href="#l1522"></a>
<span id="l1523">     * line.</span><a href="#l1523"></a>
<span id="l1524">     *</span><a href="#l1524"></a>
<span id="l1525">     * &lt;p&gt;Since this method continues to search through the input looking</span><a href="#l1525"></a>
<span id="l1526">     * for a line separator, it may buffer all of the input searching for</span><a href="#l1526"></a>
<span id="l1527">     * the line to skip if no line separators are present.</span><a href="#l1527"></a>
<span id="l1528">     *</span><a href="#l1528"></a>
<span id="l1529">     * @return the line that was skipped</span><a href="#l1529"></a>
<span id="l1530">     * @throws NoSuchElementException if no line was found</span><a href="#l1530"></a>
<span id="l1531">     * @throws IllegalStateException if this scanner is closed</span><a href="#l1531"></a>
<span id="l1532">     */</span><a href="#l1532"></a>
<span id="l1533">    public String nextLine() {</span><a href="#l1533"></a>
<span id="l1534">        if (hasNextPattern == linePattern())</span><a href="#l1534"></a>
<span id="l1535">            return getCachedResult();</span><a href="#l1535"></a>
<span id="l1536">        clearCaches();</span><a href="#l1536"></a>
<span id="l1537"></span><a href="#l1537"></a>
<span id="l1538">        String result = findWithinHorizon(linePattern, 0);</span><a href="#l1538"></a>
<span id="l1539">        if (result == null)</span><a href="#l1539"></a>
<span id="l1540">            throw new NoSuchElementException(&quot;No line found&quot;);</span><a href="#l1540"></a>
<span id="l1541">        MatchResult mr = this.match();</span><a href="#l1541"></a>
<span id="l1542">        String lineSep = mr.group(1);</span><a href="#l1542"></a>
<span id="l1543">        if (lineSep != null)</span><a href="#l1543"></a>
<span id="l1544">            result = result.substring(0, result.length() - lineSep.length());</span><a href="#l1544"></a>
<span id="l1545">        if (result == null)</span><a href="#l1545"></a>
<span id="l1546">            throw new NoSuchElementException();</span><a href="#l1546"></a>
<span id="l1547">        else</span><a href="#l1547"></a>
<span id="l1548">            return result;</span><a href="#l1548"></a>
<span id="l1549">    }</span><a href="#l1549"></a>
<span id="l1550"></span><a href="#l1550"></a>
<span id="l1551">    // Public methods that ignore delimiters</span><a href="#l1551"></a>
<span id="l1552"></span><a href="#l1552"></a>
<span id="l1553">    /**</span><a href="#l1553"></a>
<span id="l1554">     * Attempts to find the next occurrence of a pattern constructed from the</span><a href="#l1554"></a>
<span id="l1555">     * specified string, ignoring delimiters.</span><a href="#l1555"></a>
<span id="l1556">     *</span><a href="#l1556"></a>
<span id="l1557">     * &lt;p&gt;An invocation of this method of the form &lt;tt&gt;findInLine(pattern)&lt;/tt&gt;</span><a href="#l1557"></a>
<span id="l1558">     * behaves in exactly the same way as the invocation</span><a href="#l1558"></a>
<span id="l1559">     * &lt;tt&gt;findInLine(Pattern.compile(pattern))&lt;/tt&gt;.</span><a href="#l1559"></a>
<span id="l1560">     *</span><a href="#l1560"></a>
<span id="l1561">     * @param pattern a string specifying the pattern to search for</span><a href="#l1561"></a>
<span id="l1562">     * @return the text that matched the specified pattern</span><a href="#l1562"></a>
<span id="l1563">     * @throws IllegalStateException if this scanner is closed</span><a href="#l1563"></a>
<span id="l1564">     */</span><a href="#l1564"></a>
<span id="l1565">    public String findInLine(String pattern) {</span><a href="#l1565"></a>
<span id="l1566">        return findInLine(patternCache.forName(pattern));</span><a href="#l1566"></a>
<span id="l1567">    }</span><a href="#l1567"></a>
<span id="l1568"></span><a href="#l1568"></a>
<span id="l1569">    /**</span><a href="#l1569"></a>
<span id="l1570">     * Attempts to find the next occurrence of the specified pattern ignoring</span><a href="#l1570"></a>
<span id="l1571">     * delimiters. If the pattern is found before the next line separator, the</span><a href="#l1571"></a>
<span id="l1572">     * scanner advances past the input that matched and returns the string that</span><a href="#l1572"></a>
<span id="l1573">     * matched the pattern.</span><a href="#l1573"></a>
<span id="l1574">     * If no such pattern is detected in the input up to the next line</span><a href="#l1574"></a>
<span id="l1575">     * separator, then &lt;code&gt;null&lt;/code&gt; is returned and the scanner's</span><a href="#l1575"></a>
<span id="l1576">     * position is unchanged. This method may block waiting for input that</span><a href="#l1576"></a>
<span id="l1577">     * matches the pattern.</span><a href="#l1577"></a>
<span id="l1578">     *</span><a href="#l1578"></a>
<span id="l1579">     * &lt;p&gt;Since this method continues to search through the input looking</span><a href="#l1579"></a>
<span id="l1580">     * for the specified pattern, it may buffer all of the input searching for</span><a href="#l1580"></a>
<span id="l1581">     * the desired token if no line separators are present.</span><a href="#l1581"></a>
<span id="l1582">     *</span><a href="#l1582"></a>
<span id="l1583">     * @param pattern the pattern to scan for</span><a href="#l1583"></a>
<span id="l1584">     * @return the text that matched the specified pattern</span><a href="#l1584"></a>
<span id="l1585">     * @throws IllegalStateException if this scanner is closed</span><a href="#l1585"></a>
<span id="l1586">     */</span><a href="#l1586"></a>
<span id="l1587">    public String findInLine(Pattern pattern) {</span><a href="#l1587"></a>
<span id="l1588">        ensureOpen();</span><a href="#l1588"></a>
<span id="l1589">        if (pattern == null)</span><a href="#l1589"></a>
<span id="l1590">            throw new NullPointerException();</span><a href="#l1590"></a>
<span id="l1591">        clearCaches();</span><a href="#l1591"></a>
<span id="l1592">        // Expand buffer to include the next newline or end of input</span><a href="#l1592"></a>
<span id="l1593">        int endPosition = 0;</span><a href="#l1593"></a>
<span id="l1594">        saveState();</span><a href="#l1594"></a>
<span id="l1595">        while (true) {</span><a href="#l1595"></a>
<span id="l1596">            String token = findPatternInBuffer(separatorPattern(), 0);</span><a href="#l1596"></a>
<span id="l1597">            if (token != null) {</span><a href="#l1597"></a>
<span id="l1598">                endPosition = matcher.start();</span><a href="#l1598"></a>
<span id="l1599">                break; // up to next newline</span><a href="#l1599"></a>
<span id="l1600">            }</span><a href="#l1600"></a>
<span id="l1601">            if (needInput) {</span><a href="#l1601"></a>
<span id="l1602">                readInput();</span><a href="#l1602"></a>
<span id="l1603">            } else {</span><a href="#l1603"></a>
<span id="l1604">                endPosition = buf.limit();</span><a href="#l1604"></a>
<span id="l1605">                break; // up to end of input</span><a href="#l1605"></a>
<span id="l1606">            }</span><a href="#l1606"></a>
<span id="l1607">        }</span><a href="#l1607"></a>
<span id="l1608">        revertState();</span><a href="#l1608"></a>
<span id="l1609">        int horizonForLine = endPosition - position;</span><a href="#l1609"></a>
<span id="l1610">        // If there is nothing between the current pos and the next</span><a href="#l1610"></a>
<span id="l1611">        // newline simply return null, invoking findWithinHorizon</span><a href="#l1611"></a>
<span id="l1612">        // with &quot;horizon=0&quot; will scan beyond the line bound.</span><a href="#l1612"></a>
<span id="l1613">        if (horizonForLine == 0)</span><a href="#l1613"></a>
<span id="l1614">            return null;</span><a href="#l1614"></a>
<span id="l1615">        // Search for the pattern</span><a href="#l1615"></a>
<span id="l1616">        return findWithinHorizon(pattern, horizonForLine);</span><a href="#l1616"></a>
<span id="l1617">    }</span><a href="#l1617"></a>
<span id="l1618"></span><a href="#l1618"></a>
<span id="l1619">    /**</span><a href="#l1619"></a>
<span id="l1620">     * Attempts to find the next occurrence of a pattern constructed from the</span><a href="#l1620"></a>
<span id="l1621">     * specified string, ignoring delimiters.</span><a href="#l1621"></a>
<span id="l1622">     *</span><a href="#l1622"></a>
<span id="l1623">     * &lt;p&gt;An invocation of this method of the form</span><a href="#l1623"></a>
<span id="l1624">     * &lt;tt&gt;findWithinHorizon(pattern)&lt;/tt&gt; behaves in exactly the same way as</span><a href="#l1624"></a>
<span id="l1625">     * the invocation</span><a href="#l1625"></a>
<span id="l1626">     * &lt;tt&gt;findWithinHorizon(Pattern.compile(pattern, horizon))&lt;/tt&gt;.</span><a href="#l1626"></a>
<span id="l1627">     *</span><a href="#l1627"></a>
<span id="l1628">     * @param pattern a string specifying the pattern to search for</span><a href="#l1628"></a>
<span id="l1629">     * @param horizon the search horizon</span><a href="#l1629"></a>
<span id="l1630">     * @return the text that matched the specified pattern</span><a href="#l1630"></a>
<span id="l1631">     * @throws IllegalStateException if this scanner is closed</span><a href="#l1631"></a>
<span id="l1632">     * @throws IllegalArgumentException if horizon is negative</span><a href="#l1632"></a>
<span id="l1633">     */</span><a href="#l1633"></a>
<span id="l1634">    public String findWithinHorizon(String pattern, int horizon) {</span><a href="#l1634"></a>
<span id="l1635">        return findWithinHorizon(patternCache.forName(pattern), horizon);</span><a href="#l1635"></a>
<span id="l1636">    }</span><a href="#l1636"></a>
<span id="l1637"></span><a href="#l1637"></a>
<span id="l1638">    /**</span><a href="#l1638"></a>
<span id="l1639">     * Attempts to find the next occurrence of the specified pattern.</span><a href="#l1639"></a>
<span id="l1640">     *</span><a href="#l1640"></a>
<span id="l1641">     * &lt;p&gt;This method searches through the input up to the specified</span><a href="#l1641"></a>
<span id="l1642">     * search horizon, ignoring delimiters. If the pattern is found the</span><a href="#l1642"></a>
<span id="l1643">     * scanner advances past the input that matched and returns the string</span><a href="#l1643"></a>
<span id="l1644">     * that matched the pattern. If no such pattern is detected then the</span><a href="#l1644"></a>
<span id="l1645">     * null is returned and the scanner's position remains unchanged. This</span><a href="#l1645"></a>
<span id="l1646">     * method may block waiting for input that matches the pattern.</span><a href="#l1646"></a>
<span id="l1647">     *</span><a href="#l1647"></a>
<span id="l1648">     * &lt;p&gt;A scanner will never search more than &lt;code&gt;horizon&lt;/code&gt; code</span><a href="#l1648"></a>
<span id="l1649">     * points beyond its current position. Note that a match may be clipped</span><a href="#l1649"></a>
<span id="l1650">     * by the horizon; that is, an arbitrary match result may have been</span><a href="#l1650"></a>
<span id="l1651">     * different if the horizon had been larger. The scanner treats the</span><a href="#l1651"></a>
<span id="l1652">     * horizon as a transparent, non-anchoring bound (see {@link</span><a href="#l1652"></a>
<span id="l1653">     * Matcher#useTransparentBounds} and {@link Matcher#useAnchoringBounds}).</span><a href="#l1653"></a>
<span id="l1654">     *</span><a href="#l1654"></a>
<span id="l1655">     * &lt;p&gt;If horizon is &lt;code&gt;0&lt;/code&gt;, then the horizon is ignored and</span><a href="#l1655"></a>
<span id="l1656">     * this method continues to search through the input looking for the</span><a href="#l1656"></a>
<span id="l1657">     * specified pattern without bound. In this case it may buffer all of</span><a href="#l1657"></a>
<span id="l1658">     * the input searching for the pattern.</span><a href="#l1658"></a>
<span id="l1659">     *</span><a href="#l1659"></a>
<span id="l1660">     * &lt;p&gt;If horizon is negative, then an IllegalArgumentException is</span><a href="#l1660"></a>
<span id="l1661">     * thrown.</span><a href="#l1661"></a>
<span id="l1662">     *</span><a href="#l1662"></a>
<span id="l1663">     * @param pattern the pattern to scan for</span><a href="#l1663"></a>
<span id="l1664">     * @param horizon the search horizon</span><a href="#l1664"></a>
<span id="l1665">     * @return the text that matched the specified pattern</span><a href="#l1665"></a>
<span id="l1666">     * @throws IllegalStateException if this scanner is closed</span><a href="#l1666"></a>
<span id="l1667">     * @throws IllegalArgumentException if horizon is negative</span><a href="#l1667"></a>
<span id="l1668">     */</span><a href="#l1668"></a>
<span id="l1669">    public String findWithinHorizon(Pattern pattern, int horizon) {</span><a href="#l1669"></a>
<span id="l1670">        ensureOpen();</span><a href="#l1670"></a>
<span id="l1671">        if (pattern == null)</span><a href="#l1671"></a>
<span id="l1672">            throw new NullPointerException();</span><a href="#l1672"></a>
<span id="l1673">        if (horizon &lt; 0)</span><a href="#l1673"></a>
<span id="l1674">            throw new IllegalArgumentException(&quot;horizon &lt; 0&quot;);</span><a href="#l1674"></a>
<span id="l1675">        clearCaches();</span><a href="#l1675"></a>
<span id="l1676"></span><a href="#l1676"></a>
<span id="l1677">        // Search for the pattern</span><a href="#l1677"></a>
<span id="l1678">        while (true) {</span><a href="#l1678"></a>
<span id="l1679">            String token = findPatternInBuffer(pattern, horizon);</span><a href="#l1679"></a>
<span id="l1680">            if (token != null) {</span><a href="#l1680"></a>
<span id="l1681">                matchValid = true;</span><a href="#l1681"></a>
<span id="l1682">                return token;</span><a href="#l1682"></a>
<span id="l1683">            }</span><a href="#l1683"></a>
<span id="l1684">            if (needInput)</span><a href="#l1684"></a>
<span id="l1685">                readInput();</span><a href="#l1685"></a>
<span id="l1686">            else</span><a href="#l1686"></a>
<span id="l1687">                break; // up to end of input</span><a href="#l1687"></a>
<span id="l1688">        }</span><a href="#l1688"></a>
<span id="l1689">        return null;</span><a href="#l1689"></a>
<span id="l1690">    }</span><a href="#l1690"></a>
<span id="l1691"></span><a href="#l1691"></a>
<span id="l1692">    /**</span><a href="#l1692"></a>
<span id="l1693">     * Skips input that matches the specified pattern, ignoring delimiters.</span><a href="#l1693"></a>
<span id="l1694">     * This method will skip input if an anchored match of the specified</span><a href="#l1694"></a>
<span id="l1695">     * pattern succeeds.</span><a href="#l1695"></a>
<span id="l1696">     *</span><a href="#l1696"></a>
<span id="l1697">     * &lt;p&gt;If a match to the specified pattern is not found at the</span><a href="#l1697"></a>
<span id="l1698">     * current position, then no input is skipped and a</span><a href="#l1698"></a>
<span id="l1699">     * &lt;tt&gt;NoSuchElementException&lt;/tt&gt; is thrown.</span><a href="#l1699"></a>
<span id="l1700">     *</span><a href="#l1700"></a>
<span id="l1701">     * &lt;p&gt;Since this method seeks to match the specified pattern starting at</span><a href="#l1701"></a>
<span id="l1702">     * the scanner's current position, patterns that can match a lot of</span><a href="#l1702"></a>
<span id="l1703">     * input (&quot;.*&quot;, for example) may cause the scanner to buffer a large</span><a href="#l1703"></a>
<span id="l1704">     * amount of input.</span><a href="#l1704"></a>
<span id="l1705">     *</span><a href="#l1705"></a>
<span id="l1706">     * &lt;p&gt;Note that it is possible to skip something without risking a</span><a href="#l1706"></a>
<span id="l1707">     * &lt;code&gt;NoSuchElementException&lt;/code&gt; by using a pattern that can</span><a href="#l1707"></a>
<span id="l1708">     * match nothing, e.g., &lt;code&gt;sc.skip(&quot;[ \t]*&quot;)&lt;/code&gt;.</span><a href="#l1708"></a>
<span id="l1709">     *</span><a href="#l1709"></a>
<span id="l1710">     * @param pattern a string specifying the pattern to skip over</span><a href="#l1710"></a>
<span id="l1711">     * @return this scanner</span><a href="#l1711"></a>
<span id="l1712">     * @throws NoSuchElementException if the specified pattern is not found</span><a href="#l1712"></a>
<span id="l1713">     * @throws IllegalStateException if this scanner is closed</span><a href="#l1713"></a>
<span id="l1714">     */</span><a href="#l1714"></a>
<span id="l1715">    public Scanner skip(Pattern pattern) {</span><a href="#l1715"></a>
<span id="l1716">        ensureOpen();</span><a href="#l1716"></a>
<span id="l1717">        if (pattern == null)</span><a href="#l1717"></a>
<span id="l1718">            throw new NullPointerException();</span><a href="#l1718"></a>
<span id="l1719">        clearCaches();</span><a href="#l1719"></a>
<span id="l1720"></span><a href="#l1720"></a>
<span id="l1721">        // Search for the pattern</span><a href="#l1721"></a>
<span id="l1722">        while (true) {</span><a href="#l1722"></a>
<span id="l1723">            String token = matchPatternInBuffer(pattern);</span><a href="#l1723"></a>
<span id="l1724">            if (token != null) {</span><a href="#l1724"></a>
<span id="l1725">                matchValid = true;</span><a href="#l1725"></a>
<span id="l1726">                position = matcher.end();</span><a href="#l1726"></a>
<span id="l1727">                return this;</span><a href="#l1727"></a>
<span id="l1728">            }</span><a href="#l1728"></a>
<span id="l1729">            if (needInput)</span><a href="#l1729"></a>
<span id="l1730">                readInput();</span><a href="#l1730"></a>
<span id="l1731">            else</span><a href="#l1731"></a>
<span id="l1732">                throw new NoSuchElementException();</span><a href="#l1732"></a>
<span id="l1733">        }</span><a href="#l1733"></a>
<span id="l1734">    }</span><a href="#l1734"></a>
<span id="l1735"></span><a href="#l1735"></a>
<span id="l1736">    /**</span><a href="#l1736"></a>
<span id="l1737">     * Skips input that matches a pattern constructed from the specified</span><a href="#l1737"></a>
<span id="l1738">     * string.</span><a href="#l1738"></a>
<span id="l1739">     *</span><a href="#l1739"></a>
<span id="l1740">     * &lt;p&gt; An invocation of this method of the form &lt;tt&gt;skip(pattern)&lt;/tt&gt;</span><a href="#l1740"></a>
<span id="l1741">     * behaves in exactly the same way as the invocation</span><a href="#l1741"></a>
<span id="l1742">     * &lt;tt&gt;skip(Pattern.compile(pattern))&lt;/tt&gt;.</span><a href="#l1742"></a>
<span id="l1743">     *</span><a href="#l1743"></a>
<span id="l1744">     * @param pattern a string specifying the pattern to skip over</span><a href="#l1744"></a>
<span id="l1745">     * @return this scanner</span><a href="#l1745"></a>
<span id="l1746">     * @throws IllegalStateException if this scanner is closed</span><a href="#l1746"></a>
<span id="l1747">     */</span><a href="#l1747"></a>
<span id="l1748">    public Scanner skip(String pattern) {</span><a href="#l1748"></a>
<span id="l1749">        return skip(patternCache.forName(pattern));</span><a href="#l1749"></a>
<span id="l1750">    }</span><a href="#l1750"></a>
<span id="l1751"></span><a href="#l1751"></a>
<span id="l1752">    // Convenience methods for scanning primitives</span><a href="#l1752"></a>
<span id="l1753"></span><a href="#l1753"></a>
<span id="l1754">    /**</span><a href="#l1754"></a>
<span id="l1755">     * Returns true if the next token in this scanner's input can be</span><a href="#l1755"></a>
<span id="l1756">     * interpreted as a boolean value using a case insensitive pattern</span><a href="#l1756"></a>
<span id="l1757">     * created from the string &quot;true|false&quot;.  The scanner does not</span><a href="#l1757"></a>
<span id="l1758">     * advance past the input that matched.</span><a href="#l1758"></a>
<span id="l1759">     *</span><a href="#l1759"></a>
<span id="l1760">     * @return true if and only if this scanner's next token is a valid</span><a href="#l1760"></a>
<span id="l1761">     *         boolean value</span><a href="#l1761"></a>
<span id="l1762">     * @throws IllegalStateException if this scanner is closed</span><a href="#l1762"></a>
<span id="l1763">     */</span><a href="#l1763"></a>
<span id="l1764">    public boolean hasNextBoolean()  {</span><a href="#l1764"></a>
<span id="l1765">        return hasNext(boolPattern());</span><a href="#l1765"></a>
<span id="l1766">    }</span><a href="#l1766"></a>
<span id="l1767"></span><a href="#l1767"></a>
<span id="l1768">    /**</span><a href="#l1768"></a>
<span id="l1769">     * Scans the next token of the input into a boolean value and returns</span><a href="#l1769"></a>
<span id="l1770">     * that value. This method will throw &lt;code&gt;InputMismatchException&lt;/code&gt;</span><a href="#l1770"></a>
<span id="l1771">     * if the next token cannot be translated into a valid boolean value.</span><a href="#l1771"></a>
<span id="l1772">     * If the match is successful, the scanner advances past the input that</span><a href="#l1772"></a>
<span id="l1773">     * matched.</span><a href="#l1773"></a>
<span id="l1774">     *</span><a href="#l1774"></a>
<span id="l1775">     * @return the boolean scanned from the input</span><a href="#l1775"></a>
<span id="l1776">     * @throws InputMismatchException if the next token is not a valid boolean</span><a href="#l1776"></a>
<span id="l1777">     * @throws NoSuchElementException if input is exhausted</span><a href="#l1777"></a>
<span id="l1778">     * @throws IllegalStateException if this scanner is closed</span><a href="#l1778"></a>
<span id="l1779">     */</span><a href="#l1779"></a>
<span id="l1780">    public boolean nextBoolean()  {</span><a href="#l1780"></a>
<span id="l1781">        clearCaches();</span><a href="#l1781"></a>
<span id="l1782">        return Boolean.parseBoolean(next(boolPattern()));</span><a href="#l1782"></a>
<span id="l1783">    }</span><a href="#l1783"></a>
<span id="l1784"></span><a href="#l1784"></a>
<span id="l1785">    /**</span><a href="#l1785"></a>
<span id="l1786">     * Returns true if the next token in this scanner's input can be</span><a href="#l1786"></a>
<span id="l1787">     * interpreted as a byte value in the default radix using the</span><a href="#l1787"></a>
<span id="l1788">     * {@link #nextByte} method. The scanner does not advance past any input.</span><a href="#l1788"></a>
<span id="l1789">     *</span><a href="#l1789"></a>
<span id="l1790">     * @return true if and only if this scanner's next token is a valid</span><a href="#l1790"></a>
<span id="l1791">     *         byte value</span><a href="#l1791"></a>
<span id="l1792">     * @throws IllegalStateException if this scanner is closed</span><a href="#l1792"></a>
<span id="l1793">     */</span><a href="#l1793"></a>
<span id="l1794">    public boolean hasNextByte() {</span><a href="#l1794"></a>
<span id="l1795">        return hasNextByte(defaultRadix);</span><a href="#l1795"></a>
<span id="l1796">    }</span><a href="#l1796"></a>
<span id="l1797"></span><a href="#l1797"></a>
<span id="l1798">    /**</span><a href="#l1798"></a>
<span id="l1799">     * Returns true if the next token in this scanner's input can be</span><a href="#l1799"></a>
<span id="l1800">     * interpreted as a byte value in the specified radix using the</span><a href="#l1800"></a>
<span id="l1801">     * {@link #nextByte} method. The scanner does not advance past any input.</span><a href="#l1801"></a>
<span id="l1802">     *</span><a href="#l1802"></a>
<span id="l1803">     * @param radix the radix used to interpret the token as a byte value</span><a href="#l1803"></a>
<span id="l1804">     * @return true if and only if this scanner's next token is a valid</span><a href="#l1804"></a>
<span id="l1805">     *         byte value</span><a href="#l1805"></a>
<span id="l1806">     * @throws IllegalStateException if this scanner is closed</span><a href="#l1806"></a>
<span id="l1807">     */</span><a href="#l1807"></a>
<span id="l1808">    public boolean hasNextByte(int radix) {</span><a href="#l1808"></a>
<span id="l1809">        setRadix(radix);</span><a href="#l1809"></a>
<span id="l1810">        boolean result = hasNext(integerPattern());</span><a href="#l1810"></a>
<span id="l1811">        if (result) { // Cache it</span><a href="#l1811"></a>
<span id="l1812">            try {</span><a href="#l1812"></a>
<span id="l1813">                String s = (matcher.group(SIMPLE_GROUP_INDEX) == null) ?</span><a href="#l1813"></a>
<span id="l1814">                    processIntegerToken(hasNextResult) :</span><a href="#l1814"></a>
<span id="l1815">                    hasNextResult;</span><a href="#l1815"></a>
<span id="l1816">                typeCache = Byte.parseByte(s, radix);</span><a href="#l1816"></a>
<span id="l1817">            } catch (NumberFormatException nfe) {</span><a href="#l1817"></a>
<span id="l1818">                result = false;</span><a href="#l1818"></a>
<span id="l1819">            }</span><a href="#l1819"></a>
<span id="l1820">        }</span><a href="#l1820"></a>
<span id="l1821">        return result;</span><a href="#l1821"></a>
<span id="l1822">    }</span><a href="#l1822"></a>
<span id="l1823"></span><a href="#l1823"></a>
<span id="l1824">    /**</span><a href="#l1824"></a>
<span id="l1825">     * Scans the next token of the input as a &lt;tt&gt;byte&lt;/tt&gt;.</span><a href="#l1825"></a>
<span id="l1826">     *</span><a href="#l1826"></a>
<span id="l1827">     * &lt;p&gt; An invocation of this method of the form</span><a href="#l1827"></a>
<span id="l1828">     * &lt;tt&gt;nextByte()&lt;/tt&gt; behaves in exactly the same way as the</span><a href="#l1828"></a>
<span id="l1829">     * invocation &lt;tt&gt;nextByte(radix)&lt;/tt&gt;, where &lt;code&gt;radix&lt;/code&gt;</span><a href="#l1829"></a>
<span id="l1830">     * is the default radix of this scanner.</span><a href="#l1830"></a>
<span id="l1831">     *</span><a href="#l1831"></a>
<span id="l1832">     * @return the &lt;tt&gt;byte&lt;/tt&gt; scanned from the input</span><a href="#l1832"></a>
<span id="l1833">     * @throws InputMismatchException</span><a href="#l1833"></a>
<span id="l1834">     *         if the next token does not match the &lt;i&gt;Integer&lt;/i&gt;</span><a href="#l1834"></a>
<span id="l1835">     *         regular expression, or is out of range</span><a href="#l1835"></a>
<span id="l1836">     * @throws NoSuchElementException if input is exhausted</span><a href="#l1836"></a>
<span id="l1837">     * @throws IllegalStateException if this scanner is closed</span><a href="#l1837"></a>
<span id="l1838">     */</span><a href="#l1838"></a>
<span id="l1839">    public byte nextByte() {</span><a href="#l1839"></a>
<span id="l1840">         return nextByte(defaultRadix);</span><a href="#l1840"></a>
<span id="l1841">    }</span><a href="#l1841"></a>
<span id="l1842"></span><a href="#l1842"></a>
<span id="l1843">    /**</span><a href="#l1843"></a>
<span id="l1844">     * Scans the next token of the input as a &lt;tt&gt;byte&lt;/tt&gt;.</span><a href="#l1844"></a>
<span id="l1845">     * This method will throw &lt;code&gt;InputMismatchException&lt;/code&gt;</span><a href="#l1845"></a>
<span id="l1846">     * if the next token cannot be translated into a valid byte value as</span><a href="#l1846"></a>
<span id="l1847">     * described below. If the translation is successful, the scanner advances</span><a href="#l1847"></a>
<span id="l1848">     * past the input that matched.</span><a href="#l1848"></a>
<span id="l1849">     *</span><a href="#l1849"></a>
<span id="l1850">     * &lt;p&gt; If the next token matches the &lt;a</span><a href="#l1850"></a>
<span id="l1851">     * href=&quot;#Integer-regex&quot;&gt;&lt;i&gt;Integer&lt;/i&gt;&lt;/a&gt; regular expression defined</span><a href="#l1851"></a>
<span id="l1852">     * above then the token is converted into a &lt;tt&gt;byte&lt;/tt&gt; value as if by</span><a href="#l1852"></a>
<span id="l1853">     * removing all locale specific prefixes, group separators, and locale</span><a href="#l1853"></a>
<span id="l1854">     * specific suffixes, then mapping non-ASCII digits into ASCII</span><a href="#l1854"></a>
<span id="l1855">     * digits via {@link Character#digit Character.digit}, prepending a</span><a href="#l1855"></a>
<span id="l1856">     * negative sign (-) if the locale specific negative prefixes and suffixes</span><a href="#l1856"></a>
<span id="l1857">     * were present, and passing the resulting string to</span><a href="#l1857"></a>
<span id="l1858">     * {@link Byte#parseByte(String, int) Byte.parseByte} with the</span><a href="#l1858"></a>
<span id="l1859">     * specified radix.</span><a href="#l1859"></a>
<span id="l1860">     *</span><a href="#l1860"></a>
<span id="l1861">     * @param radix the radix used to interpret the token as a byte value</span><a href="#l1861"></a>
<span id="l1862">     * @return the &lt;tt&gt;byte&lt;/tt&gt; scanned from the input</span><a href="#l1862"></a>
<span id="l1863">     * @throws InputMismatchException</span><a href="#l1863"></a>
<span id="l1864">     *         if the next token does not match the &lt;i&gt;Integer&lt;/i&gt;</span><a href="#l1864"></a>
<span id="l1865">     *         regular expression, or is out of range</span><a href="#l1865"></a>
<span id="l1866">     * @throws NoSuchElementException if input is exhausted</span><a href="#l1866"></a>
<span id="l1867">     * @throws IllegalStateException if this scanner is closed</span><a href="#l1867"></a>
<span id="l1868">     */</span><a href="#l1868"></a>
<span id="l1869">    public byte nextByte(int radix) {</span><a href="#l1869"></a>
<span id="l1870">        // Check cached result</span><a href="#l1870"></a>
<span id="l1871">        if ((typeCache != null) &amp;&amp; (typeCache instanceof Byte)</span><a href="#l1871"></a>
<span id="l1872">            &amp;&amp; this.radix == radix) {</span><a href="#l1872"></a>
<span id="l1873">            byte val = ((Byte)typeCache).byteValue();</span><a href="#l1873"></a>
<span id="l1874">            useTypeCache();</span><a href="#l1874"></a>
<span id="l1875">            return val;</span><a href="#l1875"></a>
<span id="l1876">        }</span><a href="#l1876"></a>
<span id="l1877">        setRadix(radix);</span><a href="#l1877"></a>
<span id="l1878">        clearCaches();</span><a href="#l1878"></a>
<span id="l1879">        // Search for next byte</span><a href="#l1879"></a>
<span id="l1880">        try {</span><a href="#l1880"></a>
<span id="l1881">            String s = next(integerPattern());</span><a href="#l1881"></a>
<span id="l1882">            if (matcher.group(SIMPLE_GROUP_INDEX) == null)</span><a href="#l1882"></a>
<span id="l1883">                s = processIntegerToken(s);</span><a href="#l1883"></a>
<span id="l1884">            return Byte.parseByte(s, radix);</span><a href="#l1884"></a>
<span id="l1885">        } catch (NumberFormatException nfe) {</span><a href="#l1885"></a>
<span id="l1886">            position = matcher.start(); // don't skip bad token</span><a href="#l1886"></a>
<span id="l1887">            throw new InputMismatchException(nfe.getMessage());</span><a href="#l1887"></a>
<span id="l1888">        }</span><a href="#l1888"></a>
<span id="l1889">    }</span><a href="#l1889"></a>
<span id="l1890"></span><a href="#l1890"></a>
<span id="l1891">    /**</span><a href="#l1891"></a>
<span id="l1892">     * Returns true if the next token in this scanner's input can be</span><a href="#l1892"></a>
<span id="l1893">     * interpreted as a short value in the default radix using the</span><a href="#l1893"></a>
<span id="l1894">     * {@link #nextShort} method. The scanner does not advance past any input.</span><a href="#l1894"></a>
<span id="l1895">     *</span><a href="#l1895"></a>
<span id="l1896">     * @return true if and only if this scanner's next token is a valid</span><a href="#l1896"></a>
<span id="l1897">     *         short value in the default radix</span><a href="#l1897"></a>
<span id="l1898">     * @throws IllegalStateException if this scanner is closed</span><a href="#l1898"></a>
<span id="l1899">     */</span><a href="#l1899"></a>
<span id="l1900">    public boolean hasNextShort() {</span><a href="#l1900"></a>
<span id="l1901">        return hasNextShort(defaultRadix);</span><a href="#l1901"></a>
<span id="l1902">    }</span><a href="#l1902"></a>
<span id="l1903"></span><a href="#l1903"></a>
<span id="l1904">    /**</span><a href="#l1904"></a>
<span id="l1905">     * Returns true if the next token in this scanner's input can be</span><a href="#l1905"></a>
<span id="l1906">     * interpreted as a short value in the specified radix using the</span><a href="#l1906"></a>
<span id="l1907">     * {@link #nextShort} method. The scanner does not advance past any input.</span><a href="#l1907"></a>
<span id="l1908">     *</span><a href="#l1908"></a>
<span id="l1909">     * @param radix the radix used to interpret the token as a short value</span><a href="#l1909"></a>
<span id="l1910">     * @return true if and only if this scanner's next token is a valid</span><a href="#l1910"></a>
<span id="l1911">     *         short value in the specified radix</span><a href="#l1911"></a>
<span id="l1912">     * @throws IllegalStateException if this scanner is closed</span><a href="#l1912"></a>
<span id="l1913">     */</span><a href="#l1913"></a>
<span id="l1914">    public boolean hasNextShort(int radix) {</span><a href="#l1914"></a>
<span id="l1915">        setRadix(radix);</span><a href="#l1915"></a>
<span id="l1916">        boolean result = hasNext(integerPattern());</span><a href="#l1916"></a>
<span id="l1917">        if (result) { // Cache it</span><a href="#l1917"></a>
<span id="l1918">            try {</span><a href="#l1918"></a>
<span id="l1919">                String s = (matcher.group(SIMPLE_GROUP_INDEX) == null) ?</span><a href="#l1919"></a>
<span id="l1920">                    processIntegerToken(hasNextResult) :</span><a href="#l1920"></a>
<span id="l1921">                    hasNextResult;</span><a href="#l1921"></a>
<span id="l1922">                typeCache = Short.parseShort(s, radix);</span><a href="#l1922"></a>
<span id="l1923">            } catch (NumberFormatException nfe) {</span><a href="#l1923"></a>
<span id="l1924">                result = false;</span><a href="#l1924"></a>
<span id="l1925">            }</span><a href="#l1925"></a>
<span id="l1926">        }</span><a href="#l1926"></a>
<span id="l1927">        return result;</span><a href="#l1927"></a>
<span id="l1928">    }</span><a href="#l1928"></a>
<span id="l1929"></span><a href="#l1929"></a>
<span id="l1930">    /**</span><a href="#l1930"></a>
<span id="l1931">     * Scans the next token of the input as a &lt;tt&gt;short&lt;/tt&gt;.</span><a href="#l1931"></a>
<span id="l1932">     *</span><a href="#l1932"></a>
<span id="l1933">     * &lt;p&gt; An invocation of this method of the form</span><a href="#l1933"></a>
<span id="l1934">     * &lt;tt&gt;nextShort()&lt;/tt&gt; behaves in exactly the same way as the</span><a href="#l1934"></a>
<span id="l1935">     * invocation &lt;tt&gt;nextShort(radix)&lt;/tt&gt;, where &lt;code&gt;radix&lt;/code&gt;</span><a href="#l1935"></a>
<span id="l1936">     * is the default radix of this scanner.</span><a href="#l1936"></a>
<span id="l1937">     *</span><a href="#l1937"></a>
<span id="l1938">     * @return the &lt;tt&gt;short&lt;/tt&gt; scanned from the input</span><a href="#l1938"></a>
<span id="l1939">     * @throws InputMismatchException</span><a href="#l1939"></a>
<span id="l1940">     *         if the next token does not match the &lt;i&gt;Integer&lt;/i&gt;</span><a href="#l1940"></a>
<span id="l1941">     *         regular expression, or is out of range</span><a href="#l1941"></a>
<span id="l1942">     * @throws NoSuchElementException if input is exhausted</span><a href="#l1942"></a>
<span id="l1943">     * @throws IllegalStateException if this scanner is closed</span><a href="#l1943"></a>
<span id="l1944">     */</span><a href="#l1944"></a>
<span id="l1945">    public short nextShort() {</span><a href="#l1945"></a>
<span id="l1946">        return nextShort(defaultRadix);</span><a href="#l1946"></a>
<span id="l1947">    }</span><a href="#l1947"></a>
<span id="l1948"></span><a href="#l1948"></a>
<span id="l1949">    /**</span><a href="#l1949"></a>
<span id="l1950">     * Scans the next token of the input as a &lt;tt&gt;short&lt;/tt&gt;.</span><a href="#l1950"></a>
<span id="l1951">     * This method will throw &lt;code&gt;InputMismatchException&lt;/code&gt;</span><a href="#l1951"></a>
<span id="l1952">     * if the next token cannot be translated into a valid short value as</span><a href="#l1952"></a>
<span id="l1953">     * described below. If the translation is successful, the scanner advances</span><a href="#l1953"></a>
<span id="l1954">     * past the input that matched.</span><a href="#l1954"></a>
<span id="l1955">     *</span><a href="#l1955"></a>
<span id="l1956">     * &lt;p&gt; If the next token matches the &lt;a</span><a href="#l1956"></a>
<span id="l1957">     * href=&quot;#Integer-regex&quot;&gt;&lt;i&gt;Integer&lt;/i&gt;&lt;/a&gt; regular expression defined</span><a href="#l1957"></a>
<span id="l1958">     * above then the token is converted into a &lt;tt&gt;short&lt;/tt&gt; value as if by</span><a href="#l1958"></a>
<span id="l1959">     * removing all locale specific prefixes, group separators, and locale</span><a href="#l1959"></a>
<span id="l1960">     * specific suffixes, then mapping non-ASCII digits into ASCII</span><a href="#l1960"></a>
<span id="l1961">     * digits via {@link Character#digit Character.digit}, prepending a</span><a href="#l1961"></a>
<span id="l1962">     * negative sign (-) if the locale specific negative prefixes and suffixes</span><a href="#l1962"></a>
<span id="l1963">     * were present, and passing the resulting string to</span><a href="#l1963"></a>
<span id="l1964">     * {@link Short#parseShort(String, int) Short.parseShort} with the</span><a href="#l1964"></a>
<span id="l1965">     * specified radix.</span><a href="#l1965"></a>
<span id="l1966">     *</span><a href="#l1966"></a>
<span id="l1967">     * @param radix the radix used to interpret the token as a short value</span><a href="#l1967"></a>
<span id="l1968">     * @return the &lt;tt&gt;short&lt;/tt&gt; scanned from the input</span><a href="#l1968"></a>
<span id="l1969">     * @throws InputMismatchException</span><a href="#l1969"></a>
<span id="l1970">     *         if the next token does not match the &lt;i&gt;Integer&lt;/i&gt;</span><a href="#l1970"></a>
<span id="l1971">     *         regular expression, or is out of range</span><a href="#l1971"></a>
<span id="l1972">     * @throws NoSuchElementException if input is exhausted</span><a href="#l1972"></a>
<span id="l1973">     * @throws IllegalStateException if this scanner is closed</span><a href="#l1973"></a>
<span id="l1974">     */</span><a href="#l1974"></a>
<span id="l1975">    public short nextShort(int radix) {</span><a href="#l1975"></a>
<span id="l1976">        // Check cached result</span><a href="#l1976"></a>
<span id="l1977">        if ((typeCache != null) &amp;&amp; (typeCache instanceof Short)</span><a href="#l1977"></a>
<span id="l1978">            &amp;&amp; this.radix == radix) {</span><a href="#l1978"></a>
<span id="l1979">            short val = ((Short)typeCache).shortValue();</span><a href="#l1979"></a>
<span id="l1980">            useTypeCache();</span><a href="#l1980"></a>
<span id="l1981">            return val;</span><a href="#l1981"></a>
<span id="l1982">        }</span><a href="#l1982"></a>
<span id="l1983">        setRadix(radix);</span><a href="#l1983"></a>
<span id="l1984">        clearCaches();</span><a href="#l1984"></a>
<span id="l1985">        // Search for next short</span><a href="#l1985"></a>
<span id="l1986">        try {</span><a href="#l1986"></a>
<span id="l1987">            String s = next(integerPattern());</span><a href="#l1987"></a>
<span id="l1988">            if (matcher.group(SIMPLE_GROUP_INDEX) == null)</span><a href="#l1988"></a>
<span id="l1989">                s = processIntegerToken(s);</span><a href="#l1989"></a>
<span id="l1990">            return Short.parseShort(s, radix);</span><a href="#l1990"></a>
<span id="l1991">        } catch (NumberFormatException nfe) {</span><a href="#l1991"></a>
<span id="l1992">            position = matcher.start(); // don't skip bad token</span><a href="#l1992"></a>
<span id="l1993">            throw new InputMismatchException(nfe.getMessage());</span><a href="#l1993"></a>
<span id="l1994">        }</span><a href="#l1994"></a>
<span id="l1995">    }</span><a href="#l1995"></a>
<span id="l1996"></span><a href="#l1996"></a>
<span id="l1997">    /**</span><a href="#l1997"></a>
<span id="l1998">     * Returns true if the next token in this scanner's input can be</span><a href="#l1998"></a>
<span id="l1999">     * interpreted as an int value in the default radix using the</span><a href="#l1999"></a>
<span id="l2000">     * {@link #nextInt} method. The scanner does not advance past any input.</span><a href="#l2000"></a>
<span id="l2001">     *</span><a href="#l2001"></a>
<span id="l2002">     * @return true if and only if this scanner's next token is a valid</span><a href="#l2002"></a>
<span id="l2003">     *         int value</span><a href="#l2003"></a>
<span id="l2004">     * @throws IllegalStateException if this scanner is closed</span><a href="#l2004"></a>
<span id="l2005">     */</span><a href="#l2005"></a>
<span id="l2006">    public boolean hasNextInt() {</span><a href="#l2006"></a>
<span id="l2007">        return hasNextInt(defaultRadix);</span><a href="#l2007"></a>
<span id="l2008">    }</span><a href="#l2008"></a>
<span id="l2009"></span><a href="#l2009"></a>
<span id="l2010">    /**</span><a href="#l2010"></a>
<span id="l2011">     * Returns true if the next token in this scanner's input can be</span><a href="#l2011"></a>
<span id="l2012">     * interpreted as an int value in the specified radix using the</span><a href="#l2012"></a>
<span id="l2013">     * {@link #nextInt} method. The scanner does not advance past any input.</span><a href="#l2013"></a>
<span id="l2014">     *</span><a href="#l2014"></a>
<span id="l2015">     * @param radix the radix used to interpret the token as an int value</span><a href="#l2015"></a>
<span id="l2016">     * @return true if and only if this scanner's next token is a valid</span><a href="#l2016"></a>
<span id="l2017">     *         int value</span><a href="#l2017"></a>
<span id="l2018">     * @throws IllegalStateException if this scanner is closed</span><a href="#l2018"></a>
<span id="l2019">     */</span><a href="#l2019"></a>
<span id="l2020">    public boolean hasNextInt(int radix) {</span><a href="#l2020"></a>
<span id="l2021">        setRadix(radix);</span><a href="#l2021"></a>
<span id="l2022">        boolean result = hasNext(integerPattern());</span><a href="#l2022"></a>
<span id="l2023">        if (result) { // Cache it</span><a href="#l2023"></a>
<span id="l2024">            try {</span><a href="#l2024"></a>
<span id="l2025">                String s = (matcher.group(SIMPLE_GROUP_INDEX) == null) ?</span><a href="#l2025"></a>
<span id="l2026">                    processIntegerToken(hasNextResult) :</span><a href="#l2026"></a>
<span id="l2027">                    hasNextResult;</span><a href="#l2027"></a>
<span id="l2028">                typeCache = Integer.parseInt(s, radix);</span><a href="#l2028"></a>
<span id="l2029">            } catch (NumberFormatException nfe) {</span><a href="#l2029"></a>
<span id="l2030">                result = false;</span><a href="#l2030"></a>
<span id="l2031">            }</span><a href="#l2031"></a>
<span id="l2032">        }</span><a href="#l2032"></a>
<span id="l2033">        return result;</span><a href="#l2033"></a>
<span id="l2034">    }</span><a href="#l2034"></a>
<span id="l2035"></span><a href="#l2035"></a>
<span id="l2036">    /**</span><a href="#l2036"></a>
<span id="l2037">     * The integer token must be stripped of prefixes, group separators,</span><a href="#l2037"></a>
<span id="l2038">     * and suffixes, non ascii digits must be converted into ascii digits</span><a href="#l2038"></a>
<span id="l2039">     * before parse will accept it.</span><a href="#l2039"></a>
<span id="l2040">     */</span><a href="#l2040"></a>
<span id="l2041">    private String processIntegerToken(String token) {</span><a href="#l2041"></a>
<span id="l2042">        String result = token.replaceAll(&quot;&quot;+groupSeparator, &quot;&quot;);</span><a href="#l2042"></a>
<span id="l2043">        boolean isNegative = false;</span><a href="#l2043"></a>
<span id="l2044">        int preLen = negativePrefix.length();</span><a href="#l2044"></a>
<span id="l2045">        if ((preLen &gt; 0) &amp;&amp; result.startsWith(negativePrefix)) {</span><a href="#l2045"></a>
<span id="l2046">            isNegative = true;</span><a href="#l2046"></a>
<span id="l2047">            result = result.substring(preLen);</span><a href="#l2047"></a>
<span id="l2048">        }</span><a href="#l2048"></a>
<span id="l2049">        int sufLen = negativeSuffix.length();</span><a href="#l2049"></a>
<span id="l2050">        if ((sufLen &gt; 0) &amp;&amp; result.endsWith(negativeSuffix)) {</span><a href="#l2050"></a>
<span id="l2051">            isNegative = true;</span><a href="#l2051"></a>
<span id="l2052">            result = result.substring(result.length() - sufLen,</span><a href="#l2052"></a>
<span id="l2053">                                      result.length());</span><a href="#l2053"></a>
<span id="l2054">        }</span><a href="#l2054"></a>
<span id="l2055">        if (isNegative)</span><a href="#l2055"></a>
<span id="l2056">            result = &quot;-&quot; + result;</span><a href="#l2056"></a>
<span id="l2057">        return result;</span><a href="#l2057"></a>
<span id="l2058">    }</span><a href="#l2058"></a>
<span id="l2059"></span><a href="#l2059"></a>
<span id="l2060">    /**</span><a href="#l2060"></a>
<span id="l2061">     * Scans the next token of the input as an &lt;tt&gt;int&lt;/tt&gt;.</span><a href="#l2061"></a>
<span id="l2062">     *</span><a href="#l2062"></a>
<span id="l2063">     * &lt;p&gt; An invocation of this method of the form</span><a href="#l2063"></a>
<span id="l2064">     * &lt;tt&gt;nextInt()&lt;/tt&gt; behaves in exactly the same way as the</span><a href="#l2064"></a>
<span id="l2065">     * invocation &lt;tt&gt;nextInt(radix)&lt;/tt&gt;, where &lt;code&gt;radix&lt;/code&gt;</span><a href="#l2065"></a>
<span id="l2066">     * is the default radix of this scanner.</span><a href="#l2066"></a>
<span id="l2067">     *</span><a href="#l2067"></a>
<span id="l2068">     * @return the &lt;tt&gt;int&lt;/tt&gt; scanned from the input</span><a href="#l2068"></a>
<span id="l2069">     * @throws InputMismatchException</span><a href="#l2069"></a>
<span id="l2070">     *         if the next token does not match the &lt;i&gt;Integer&lt;/i&gt;</span><a href="#l2070"></a>
<span id="l2071">     *         regular expression, or is out of range</span><a href="#l2071"></a>
<span id="l2072">     * @throws NoSuchElementException if input is exhausted</span><a href="#l2072"></a>
<span id="l2073">     * @throws IllegalStateException if this scanner is closed</span><a href="#l2073"></a>
<span id="l2074">     */</span><a href="#l2074"></a>
<span id="l2075">    public int nextInt() {</span><a href="#l2075"></a>
<span id="l2076">        return nextInt(defaultRadix);</span><a href="#l2076"></a>
<span id="l2077">    }</span><a href="#l2077"></a>
<span id="l2078"></span><a href="#l2078"></a>
<span id="l2079">    /**</span><a href="#l2079"></a>
<span id="l2080">     * Scans the next token of the input as an &lt;tt&gt;int&lt;/tt&gt;.</span><a href="#l2080"></a>
<span id="l2081">     * This method will throw &lt;code&gt;InputMismatchException&lt;/code&gt;</span><a href="#l2081"></a>
<span id="l2082">     * if the next token cannot be translated into a valid int value as</span><a href="#l2082"></a>
<span id="l2083">     * described below. If the translation is successful, the scanner advances</span><a href="#l2083"></a>
<span id="l2084">     * past the input that matched.</span><a href="#l2084"></a>
<span id="l2085">     *</span><a href="#l2085"></a>
<span id="l2086">     * &lt;p&gt; If the next token matches the &lt;a</span><a href="#l2086"></a>
<span id="l2087">     * href=&quot;#Integer-regex&quot;&gt;&lt;i&gt;Integer&lt;/i&gt;&lt;/a&gt; regular expression defined</span><a href="#l2087"></a>
<span id="l2088">     * above then the token is converted into an &lt;tt&gt;int&lt;/tt&gt; value as if by</span><a href="#l2088"></a>
<span id="l2089">     * removing all locale specific prefixes, group separators, and locale</span><a href="#l2089"></a>
<span id="l2090">     * specific suffixes, then mapping non-ASCII digits into ASCII</span><a href="#l2090"></a>
<span id="l2091">     * digits via {@link Character#digit Character.digit}, prepending a</span><a href="#l2091"></a>
<span id="l2092">     * negative sign (-) if the locale specific negative prefixes and suffixes</span><a href="#l2092"></a>
<span id="l2093">     * were present, and passing the resulting string to</span><a href="#l2093"></a>
<span id="l2094">     * {@link Integer#parseInt(String, int) Integer.parseInt} with the</span><a href="#l2094"></a>
<span id="l2095">     * specified radix.</span><a href="#l2095"></a>
<span id="l2096">     *</span><a href="#l2096"></a>
<span id="l2097">     * @param radix the radix used to interpret the token as an int value</span><a href="#l2097"></a>
<span id="l2098">     * @return the &lt;tt&gt;int&lt;/tt&gt; scanned from the input</span><a href="#l2098"></a>
<span id="l2099">     * @throws InputMismatchException</span><a href="#l2099"></a>
<span id="l2100">     *         if the next token does not match the &lt;i&gt;Integer&lt;/i&gt;</span><a href="#l2100"></a>
<span id="l2101">     *         regular expression, or is out of range</span><a href="#l2101"></a>
<span id="l2102">     * @throws NoSuchElementException if input is exhausted</span><a href="#l2102"></a>
<span id="l2103">     * @throws IllegalStateException if this scanner is closed</span><a href="#l2103"></a>
<span id="l2104">     */</span><a href="#l2104"></a>
<span id="l2105">    public int nextInt(int radix) {</span><a href="#l2105"></a>
<span id="l2106">        // Check cached result</span><a href="#l2106"></a>
<span id="l2107">        if ((typeCache != null) &amp;&amp; (typeCache instanceof Integer)</span><a href="#l2107"></a>
<span id="l2108">            &amp;&amp; this.radix == radix) {</span><a href="#l2108"></a>
<span id="l2109">            int val = ((Integer)typeCache).intValue();</span><a href="#l2109"></a>
<span id="l2110">            useTypeCache();</span><a href="#l2110"></a>
<span id="l2111">            return val;</span><a href="#l2111"></a>
<span id="l2112">        }</span><a href="#l2112"></a>
<span id="l2113">        setRadix(radix);</span><a href="#l2113"></a>
<span id="l2114">        clearCaches();</span><a href="#l2114"></a>
<span id="l2115">        // Search for next int</span><a href="#l2115"></a>
<span id="l2116">        try {</span><a href="#l2116"></a>
<span id="l2117">            String s = next(integerPattern());</span><a href="#l2117"></a>
<span id="l2118">            if (matcher.group(SIMPLE_GROUP_INDEX) == null)</span><a href="#l2118"></a>
<span id="l2119">                s = processIntegerToken(s);</span><a href="#l2119"></a>
<span id="l2120">            return Integer.parseInt(s, radix);</span><a href="#l2120"></a>
<span id="l2121">        } catch (NumberFormatException nfe) {</span><a href="#l2121"></a>
<span id="l2122">            position = matcher.start(); // don't skip bad token</span><a href="#l2122"></a>
<span id="l2123">            throw new InputMismatchException(nfe.getMessage());</span><a href="#l2123"></a>
<span id="l2124">        }</span><a href="#l2124"></a>
<span id="l2125">    }</span><a href="#l2125"></a>
<span id="l2126"></span><a href="#l2126"></a>
<span id="l2127">    /**</span><a href="#l2127"></a>
<span id="l2128">     * Returns true if the next token in this scanner's input can be</span><a href="#l2128"></a>
<span id="l2129">     * interpreted as a long value in the default radix using the</span><a href="#l2129"></a>
<span id="l2130">     * {@link #nextLong} method. The scanner does not advance past any input.</span><a href="#l2130"></a>
<span id="l2131">     *</span><a href="#l2131"></a>
<span id="l2132">     * @return true if and only if this scanner's next token is a valid</span><a href="#l2132"></a>
<span id="l2133">     *         long value</span><a href="#l2133"></a>
<span id="l2134">     * @throws IllegalStateException if this scanner is closed</span><a href="#l2134"></a>
<span id="l2135">     */</span><a href="#l2135"></a>
<span id="l2136">    public boolean hasNextLong() {</span><a href="#l2136"></a>
<span id="l2137">        return hasNextLong(defaultRadix);</span><a href="#l2137"></a>
<span id="l2138">    }</span><a href="#l2138"></a>
<span id="l2139"></span><a href="#l2139"></a>
<span id="l2140">    /**</span><a href="#l2140"></a>
<span id="l2141">     * Returns true if the next token in this scanner's input can be</span><a href="#l2141"></a>
<span id="l2142">     * interpreted as a long value in the specified radix using the</span><a href="#l2142"></a>
<span id="l2143">     * {@link #nextLong} method. The scanner does not advance past any input.</span><a href="#l2143"></a>
<span id="l2144">     *</span><a href="#l2144"></a>
<span id="l2145">     * @param radix the radix used to interpret the token as a long value</span><a href="#l2145"></a>
<span id="l2146">     * @return true if and only if this scanner's next token is a valid</span><a href="#l2146"></a>
<span id="l2147">     *         long value</span><a href="#l2147"></a>
<span id="l2148">     * @throws IllegalStateException if this scanner is closed</span><a href="#l2148"></a>
<span id="l2149">     */</span><a href="#l2149"></a>
<span id="l2150">    public boolean hasNextLong(int radix) {</span><a href="#l2150"></a>
<span id="l2151">        setRadix(radix);</span><a href="#l2151"></a>
<span id="l2152">        boolean result = hasNext(integerPattern());</span><a href="#l2152"></a>
<span id="l2153">        if (result) { // Cache it</span><a href="#l2153"></a>
<span id="l2154">            try {</span><a href="#l2154"></a>
<span id="l2155">                String s = (matcher.group(SIMPLE_GROUP_INDEX) == null) ?</span><a href="#l2155"></a>
<span id="l2156">                    processIntegerToken(hasNextResult) :</span><a href="#l2156"></a>
<span id="l2157">                    hasNextResult;</span><a href="#l2157"></a>
<span id="l2158">                typeCache = Long.parseLong(s, radix);</span><a href="#l2158"></a>
<span id="l2159">            } catch (NumberFormatException nfe) {</span><a href="#l2159"></a>
<span id="l2160">                result = false;</span><a href="#l2160"></a>
<span id="l2161">            }</span><a href="#l2161"></a>
<span id="l2162">        }</span><a href="#l2162"></a>
<span id="l2163">        return result;</span><a href="#l2163"></a>
<span id="l2164">    }</span><a href="#l2164"></a>
<span id="l2165"></span><a href="#l2165"></a>
<span id="l2166">    /**</span><a href="#l2166"></a>
<span id="l2167">     * Scans the next token of the input as a &lt;tt&gt;long&lt;/tt&gt;.</span><a href="#l2167"></a>
<span id="l2168">     *</span><a href="#l2168"></a>
<span id="l2169">     * &lt;p&gt; An invocation of this method of the form</span><a href="#l2169"></a>
<span id="l2170">     * &lt;tt&gt;nextLong()&lt;/tt&gt; behaves in exactly the same way as the</span><a href="#l2170"></a>
<span id="l2171">     * invocation &lt;tt&gt;nextLong(radix)&lt;/tt&gt;, where &lt;code&gt;radix&lt;/code&gt;</span><a href="#l2171"></a>
<span id="l2172">     * is the default radix of this scanner.</span><a href="#l2172"></a>
<span id="l2173">     *</span><a href="#l2173"></a>
<span id="l2174">     * @return the &lt;tt&gt;long&lt;/tt&gt; scanned from the input</span><a href="#l2174"></a>
<span id="l2175">     * @throws InputMismatchException</span><a href="#l2175"></a>
<span id="l2176">     *         if the next token does not match the &lt;i&gt;Integer&lt;/i&gt;</span><a href="#l2176"></a>
<span id="l2177">     *         regular expression, or is out of range</span><a href="#l2177"></a>
<span id="l2178">     * @throws NoSuchElementException if input is exhausted</span><a href="#l2178"></a>
<span id="l2179">     * @throws IllegalStateException if this scanner is closed</span><a href="#l2179"></a>
<span id="l2180">     */</span><a href="#l2180"></a>
<span id="l2181">    public long nextLong() {</span><a href="#l2181"></a>
<span id="l2182">        return nextLong(defaultRadix);</span><a href="#l2182"></a>
<span id="l2183">    }</span><a href="#l2183"></a>
<span id="l2184"></span><a href="#l2184"></a>
<span id="l2185">    /**</span><a href="#l2185"></a>
<span id="l2186">     * Scans the next token of the input as a &lt;tt&gt;long&lt;/tt&gt;.</span><a href="#l2186"></a>
<span id="l2187">     * This method will throw &lt;code&gt;InputMismatchException&lt;/code&gt;</span><a href="#l2187"></a>
<span id="l2188">     * if the next token cannot be translated into a valid long value as</span><a href="#l2188"></a>
<span id="l2189">     * described below. If the translation is successful, the scanner advances</span><a href="#l2189"></a>
<span id="l2190">     * past the input that matched.</span><a href="#l2190"></a>
<span id="l2191">     *</span><a href="#l2191"></a>
<span id="l2192">     * &lt;p&gt; If the next token matches the &lt;a</span><a href="#l2192"></a>
<span id="l2193">     * href=&quot;#Integer-regex&quot;&gt;&lt;i&gt;Integer&lt;/i&gt;&lt;/a&gt; regular expression defined</span><a href="#l2193"></a>
<span id="l2194">     * above then the token is converted into a &lt;tt&gt;long&lt;/tt&gt; value as if by</span><a href="#l2194"></a>
<span id="l2195">     * removing all locale specific prefixes, group separators, and locale</span><a href="#l2195"></a>
<span id="l2196">     * specific suffixes, then mapping non-ASCII digits into ASCII</span><a href="#l2196"></a>
<span id="l2197">     * digits via {@link Character#digit Character.digit}, prepending a</span><a href="#l2197"></a>
<span id="l2198">     * negative sign (-) if the locale specific negative prefixes and suffixes</span><a href="#l2198"></a>
<span id="l2199">     * were present, and passing the resulting string to</span><a href="#l2199"></a>
<span id="l2200">     * {@link Long#parseLong(String, int) Long.parseLong} with the</span><a href="#l2200"></a>
<span id="l2201">     * specified radix.</span><a href="#l2201"></a>
<span id="l2202">     *</span><a href="#l2202"></a>
<span id="l2203">     * @param radix the radix used to interpret the token as an int value</span><a href="#l2203"></a>
<span id="l2204">     * @return the &lt;tt&gt;long&lt;/tt&gt; scanned from the input</span><a href="#l2204"></a>
<span id="l2205">     * @throws InputMismatchException</span><a href="#l2205"></a>
<span id="l2206">     *         if the next token does not match the &lt;i&gt;Integer&lt;/i&gt;</span><a href="#l2206"></a>
<span id="l2207">     *         regular expression, or is out of range</span><a href="#l2207"></a>
<span id="l2208">     * @throws NoSuchElementException if input is exhausted</span><a href="#l2208"></a>
<span id="l2209">     * @throws IllegalStateException if this scanner is closed</span><a href="#l2209"></a>
<span id="l2210">     */</span><a href="#l2210"></a>
<span id="l2211">    public long nextLong(int radix) {</span><a href="#l2211"></a>
<span id="l2212">        // Check cached result</span><a href="#l2212"></a>
<span id="l2213">        if ((typeCache != null) &amp;&amp; (typeCache instanceof Long)</span><a href="#l2213"></a>
<span id="l2214">            &amp;&amp; this.radix == radix) {</span><a href="#l2214"></a>
<span id="l2215">            long val = ((Long)typeCache).longValue();</span><a href="#l2215"></a>
<span id="l2216">            useTypeCache();</span><a href="#l2216"></a>
<span id="l2217">            return val;</span><a href="#l2217"></a>
<span id="l2218">        }</span><a href="#l2218"></a>
<span id="l2219">        setRadix(radix);</span><a href="#l2219"></a>
<span id="l2220">        clearCaches();</span><a href="#l2220"></a>
<span id="l2221">        try {</span><a href="#l2221"></a>
<span id="l2222">            String s = next(integerPattern());</span><a href="#l2222"></a>
<span id="l2223">            if (matcher.group(SIMPLE_GROUP_INDEX) == null)</span><a href="#l2223"></a>
<span id="l2224">                s = processIntegerToken(s);</span><a href="#l2224"></a>
<span id="l2225">            return Long.parseLong(s, radix);</span><a href="#l2225"></a>
<span id="l2226">        } catch (NumberFormatException nfe) {</span><a href="#l2226"></a>
<span id="l2227">            position = matcher.start(); // don't skip bad token</span><a href="#l2227"></a>
<span id="l2228">            throw new InputMismatchException(nfe.getMessage());</span><a href="#l2228"></a>
<span id="l2229">        }</span><a href="#l2229"></a>
<span id="l2230">    }</span><a href="#l2230"></a>
<span id="l2231"></span><a href="#l2231"></a>
<span id="l2232">    /**</span><a href="#l2232"></a>
<span id="l2233">     * The float token must be stripped of prefixes, group separators,</span><a href="#l2233"></a>
<span id="l2234">     * and suffixes, non ascii digits must be converted into ascii digits</span><a href="#l2234"></a>
<span id="l2235">     * before parseFloat will accept it.</span><a href="#l2235"></a>
<span id="l2236">     *</span><a href="#l2236"></a>
<span id="l2237">     * If there are non-ascii digits in the token these digits must</span><a href="#l2237"></a>
<span id="l2238">     * be processed before the token is passed to parseFloat.</span><a href="#l2238"></a>
<span id="l2239">     */</span><a href="#l2239"></a>
<span id="l2240">    private String processFloatToken(String token) {</span><a href="#l2240"></a>
<span id="l2241">        String result = token.replaceAll(groupSeparator, &quot;&quot;);</span><a href="#l2241"></a>
<span id="l2242">        if (!decimalSeparator.equals(&quot;\\.&quot;))</span><a href="#l2242"></a>
<span id="l2243">            result = result.replaceAll(decimalSeparator, &quot;.&quot;);</span><a href="#l2243"></a>
<span id="l2244">        boolean isNegative = false;</span><a href="#l2244"></a>
<span id="l2245">        int preLen = negativePrefix.length();</span><a href="#l2245"></a>
<span id="l2246">        if ((preLen &gt; 0) &amp;&amp; result.startsWith(negativePrefix)) {</span><a href="#l2246"></a>
<span id="l2247">            isNegative = true;</span><a href="#l2247"></a>
<span id="l2248">            result = result.substring(preLen);</span><a href="#l2248"></a>
<span id="l2249">        }</span><a href="#l2249"></a>
<span id="l2250">        int sufLen = negativeSuffix.length();</span><a href="#l2250"></a>
<span id="l2251">        if ((sufLen &gt; 0) &amp;&amp; result.endsWith(negativeSuffix)) {</span><a href="#l2251"></a>
<span id="l2252">            isNegative = true;</span><a href="#l2252"></a>
<span id="l2253">            result = result.substring(result.length() - sufLen,</span><a href="#l2253"></a>
<span id="l2254">                                      result.length());</span><a href="#l2254"></a>
<span id="l2255">        }</span><a href="#l2255"></a>
<span id="l2256">        if (result.equals(nanString))</span><a href="#l2256"></a>
<span id="l2257">            result = &quot;NaN&quot;;</span><a href="#l2257"></a>
<span id="l2258">        if (result.equals(infinityString))</span><a href="#l2258"></a>
<span id="l2259">            result = &quot;Infinity&quot;;</span><a href="#l2259"></a>
<span id="l2260">        if (isNegative)</span><a href="#l2260"></a>
<span id="l2261">            result = &quot;-&quot; + result;</span><a href="#l2261"></a>
<span id="l2262"></span><a href="#l2262"></a>
<span id="l2263">        // Translate non-ASCII digits</span><a href="#l2263"></a>
<span id="l2264">        Matcher m = NON_ASCII_DIGIT.matcher(result);</span><a href="#l2264"></a>
<span id="l2265">        if (m.find()) {</span><a href="#l2265"></a>
<span id="l2266">            StringBuilder inASCII = new StringBuilder();</span><a href="#l2266"></a>
<span id="l2267">            for (int i=0; i&lt;result.length(); i++) {</span><a href="#l2267"></a>
<span id="l2268">                char nextChar = result.charAt(i);</span><a href="#l2268"></a>
<span id="l2269">                if (Character.isDigit(nextChar)) {</span><a href="#l2269"></a>
<span id="l2270">                    int d = Character.digit(nextChar, 10);</span><a href="#l2270"></a>
<span id="l2271">                    if (d != -1)</span><a href="#l2271"></a>
<span id="l2272">                        inASCII.append(d);</span><a href="#l2272"></a>
<span id="l2273">                    else</span><a href="#l2273"></a>
<span id="l2274">                        inASCII.append(nextChar);</span><a href="#l2274"></a>
<span id="l2275">                } else {</span><a href="#l2275"></a>
<span id="l2276">                    inASCII.append(nextChar);</span><a href="#l2276"></a>
<span id="l2277">                }</span><a href="#l2277"></a>
<span id="l2278">            }</span><a href="#l2278"></a>
<span id="l2279">            result = inASCII.toString();</span><a href="#l2279"></a>
<span id="l2280">        }</span><a href="#l2280"></a>
<span id="l2281"></span><a href="#l2281"></a>
<span id="l2282">        return result;</span><a href="#l2282"></a>
<span id="l2283">    }</span><a href="#l2283"></a>
<span id="l2284"></span><a href="#l2284"></a>
<span id="l2285">    /**</span><a href="#l2285"></a>
<span id="l2286">     * Returns true if the next token in this scanner's input can be</span><a href="#l2286"></a>
<span id="l2287">     * interpreted as a float value using the {@link #nextFloat}</span><a href="#l2287"></a>
<span id="l2288">     * method. The scanner does not advance past any input.</span><a href="#l2288"></a>
<span id="l2289">     *</span><a href="#l2289"></a>
<span id="l2290">     * @return true if and only if this scanner's next token is a valid</span><a href="#l2290"></a>
<span id="l2291">     *         float value</span><a href="#l2291"></a>
<span id="l2292">     * @throws IllegalStateException if this scanner is closed</span><a href="#l2292"></a>
<span id="l2293">     */</span><a href="#l2293"></a>
<span id="l2294">    public boolean hasNextFloat() {</span><a href="#l2294"></a>
<span id="l2295">        setRadix(10);</span><a href="#l2295"></a>
<span id="l2296">        boolean result = hasNext(floatPattern());</span><a href="#l2296"></a>
<span id="l2297">        if (result) { // Cache it</span><a href="#l2297"></a>
<span id="l2298">            try {</span><a href="#l2298"></a>
<span id="l2299">                String s = processFloatToken(hasNextResult);</span><a href="#l2299"></a>
<span id="l2300">                typeCache = Float.valueOf(Float.parseFloat(s));</span><a href="#l2300"></a>
<span id="l2301">            } catch (NumberFormatException nfe) {</span><a href="#l2301"></a>
<span id="l2302">                result = false;</span><a href="#l2302"></a>
<span id="l2303">            }</span><a href="#l2303"></a>
<span id="l2304">        }</span><a href="#l2304"></a>
<span id="l2305">        return result;</span><a href="#l2305"></a>
<span id="l2306">    }</span><a href="#l2306"></a>
<span id="l2307"></span><a href="#l2307"></a>
<span id="l2308">    /**</span><a href="#l2308"></a>
<span id="l2309">     * Scans the next token of the input as a &lt;tt&gt;float&lt;/tt&gt;.</span><a href="#l2309"></a>
<span id="l2310">     * This method will throw &lt;code&gt;InputMismatchException&lt;/code&gt;</span><a href="#l2310"></a>
<span id="l2311">     * if the next token cannot be translated into a valid float value as</span><a href="#l2311"></a>
<span id="l2312">     * described below. If the translation is successful, the scanner advances</span><a href="#l2312"></a>
<span id="l2313">     * past the input that matched.</span><a href="#l2313"></a>
<span id="l2314">     *</span><a href="#l2314"></a>
<span id="l2315">     * &lt;p&gt; If the next token matches the &lt;a</span><a href="#l2315"></a>
<span id="l2316">     * href=&quot;#Float-regex&quot;&gt;&lt;i&gt;Float&lt;/i&gt;&lt;/a&gt; regular expression defined above</span><a href="#l2316"></a>
<span id="l2317">     * then the token is converted into a &lt;tt&gt;float&lt;/tt&gt; value as if by</span><a href="#l2317"></a>
<span id="l2318">     * removing all locale specific prefixes, group separators, and locale</span><a href="#l2318"></a>
<span id="l2319">     * specific suffixes, then mapping non-ASCII digits into ASCII</span><a href="#l2319"></a>
<span id="l2320">     * digits via {@link Character#digit Character.digit}, prepending a</span><a href="#l2320"></a>
<span id="l2321">     * negative sign (-) if the locale specific negative prefixes and suffixes</span><a href="#l2321"></a>
<span id="l2322">     * were present, and passing the resulting string to</span><a href="#l2322"></a>
<span id="l2323">     * {@link Float#parseFloat Float.parseFloat}. If the token matches</span><a href="#l2323"></a>
<span id="l2324">     * the localized NaN or infinity strings, then either &quot;Nan&quot; or &quot;Infinity&quot;</span><a href="#l2324"></a>
<span id="l2325">     * is passed to {@link Float#parseFloat(String) Float.parseFloat} as</span><a href="#l2325"></a>
<span id="l2326">     * appropriate.</span><a href="#l2326"></a>
<span id="l2327">     *</span><a href="#l2327"></a>
<span id="l2328">     * @return the &lt;tt&gt;float&lt;/tt&gt; scanned from the input</span><a href="#l2328"></a>
<span id="l2329">     * @throws InputMismatchException</span><a href="#l2329"></a>
<span id="l2330">     *         if the next token does not match the &lt;i&gt;Float&lt;/i&gt;</span><a href="#l2330"></a>
<span id="l2331">     *         regular expression, or is out of range</span><a href="#l2331"></a>
<span id="l2332">     * @throws NoSuchElementException if input is exhausted</span><a href="#l2332"></a>
<span id="l2333">     * @throws IllegalStateException if this scanner is closed</span><a href="#l2333"></a>
<span id="l2334">     */</span><a href="#l2334"></a>
<span id="l2335">    public float nextFloat() {</span><a href="#l2335"></a>
<span id="l2336">        // Check cached result</span><a href="#l2336"></a>
<span id="l2337">        if ((typeCache != null) &amp;&amp; (typeCache instanceof Float)) {</span><a href="#l2337"></a>
<span id="l2338">            float val = ((Float)typeCache).floatValue();</span><a href="#l2338"></a>
<span id="l2339">            useTypeCache();</span><a href="#l2339"></a>
<span id="l2340">            return val;</span><a href="#l2340"></a>
<span id="l2341">        }</span><a href="#l2341"></a>
<span id="l2342">        setRadix(10);</span><a href="#l2342"></a>
<span id="l2343">        clearCaches();</span><a href="#l2343"></a>
<span id="l2344">        try {</span><a href="#l2344"></a>
<span id="l2345">            return Float.parseFloat(processFloatToken(next(floatPattern())));</span><a href="#l2345"></a>
<span id="l2346">        } catch (NumberFormatException nfe) {</span><a href="#l2346"></a>
<span id="l2347">            position = matcher.start(); // don't skip bad token</span><a href="#l2347"></a>
<span id="l2348">            throw new InputMismatchException(nfe.getMessage());</span><a href="#l2348"></a>
<span id="l2349">        }</span><a href="#l2349"></a>
<span id="l2350">    }</span><a href="#l2350"></a>
<span id="l2351"></span><a href="#l2351"></a>
<span id="l2352">    /**</span><a href="#l2352"></a>
<span id="l2353">     * Returns true if the next token in this scanner's input can be</span><a href="#l2353"></a>
<span id="l2354">     * interpreted as a double value using the {@link #nextDouble}</span><a href="#l2354"></a>
<span id="l2355">     * method. The scanner does not advance past any input.</span><a href="#l2355"></a>
<span id="l2356">     *</span><a href="#l2356"></a>
<span id="l2357">     * @return true if and only if this scanner's next token is a valid</span><a href="#l2357"></a>
<span id="l2358">     *         double value</span><a href="#l2358"></a>
<span id="l2359">     * @throws IllegalStateException if this scanner is closed</span><a href="#l2359"></a>
<span id="l2360">     */</span><a href="#l2360"></a>
<span id="l2361">    public boolean hasNextDouble() {</span><a href="#l2361"></a>
<span id="l2362">        setRadix(10);</span><a href="#l2362"></a>
<span id="l2363">        boolean result = hasNext(floatPattern());</span><a href="#l2363"></a>
<span id="l2364">        if (result) { // Cache it</span><a href="#l2364"></a>
<span id="l2365">            try {</span><a href="#l2365"></a>
<span id="l2366">                String s = processFloatToken(hasNextResult);</span><a href="#l2366"></a>
<span id="l2367">                typeCache = Double.valueOf(Double.parseDouble(s));</span><a href="#l2367"></a>
<span id="l2368">            } catch (NumberFormatException nfe) {</span><a href="#l2368"></a>
<span id="l2369">                result = false;</span><a href="#l2369"></a>
<span id="l2370">            }</span><a href="#l2370"></a>
<span id="l2371">        }</span><a href="#l2371"></a>
<span id="l2372">        return result;</span><a href="#l2372"></a>
<span id="l2373">    }</span><a href="#l2373"></a>
<span id="l2374"></span><a href="#l2374"></a>
<span id="l2375">    /**</span><a href="#l2375"></a>
<span id="l2376">     * Scans the next token of the input as a &lt;tt&gt;double&lt;/tt&gt;.</span><a href="#l2376"></a>
<span id="l2377">     * This method will throw &lt;code&gt;InputMismatchException&lt;/code&gt;</span><a href="#l2377"></a>
<span id="l2378">     * if the next token cannot be translated into a valid double value.</span><a href="#l2378"></a>
<span id="l2379">     * If the translation is successful, the scanner advances past the input</span><a href="#l2379"></a>
<span id="l2380">     * that matched.</span><a href="#l2380"></a>
<span id="l2381">     *</span><a href="#l2381"></a>
<span id="l2382">     * &lt;p&gt; If the next token matches the &lt;a</span><a href="#l2382"></a>
<span id="l2383">     * href=&quot;#Float-regex&quot;&gt;&lt;i&gt;Float&lt;/i&gt;&lt;/a&gt; regular expression defined above</span><a href="#l2383"></a>
<span id="l2384">     * then the token is converted into a &lt;tt&gt;double&lt;/tt&gt; value as if by</span><a href="#l2384"></a>
<span id="l2385">     * removing all locale specific prefixes, group separators, and locale</span><a href="#l2385"></a>
<span id="l2386">     * specific suffixes, then mapping non-ASCII digits into ASCII</span><a href="#l2386"></a>
<span id="l2387">     * digits via {@link Character#digit Character.digit}, prepending a</span><a href="#l2387"></a>
<span id="l2388">     * negative sign (-) if the locale specific negative prefixes and suffixes</span><a href="#l2388"></a>
<span id="l2389">     * were present, and passing the resulting string to</span><a href="#l2389"></a>
<span id="l2390">     * {@link Double#parseDouble Double.parseDouble}. If the token matches</span><a href="#l2390"></a>
<span id="l2391">     * the localized NaN or infinity strings, then either &quot;Nan&quot; or &quot;Infinity&quot;</span><a href="#l2391"></a>
<span id="l2392">     * is passed to {@link Double#parseDouble(String) Double.parseDouble} as</span><a href="#l2392"></a>
<span id="l2393">     * appropriate.</span><a href="#l2393"></a>
<span id="l2394">     *</span><a href="#l2394"></a>
<span id="l2395">     * @return the &lt;tt&gt;double&lt;/tt&gt; scanned from the input</span><a href="#l2395"></a>
<span id="l2396">     * @throws InputMismatchException</span><a href="#l2396"></a>
<span id="l2397">     *         if the next token does not match the &lt;i&gt;Float&lt;/i&gt;</span><a href="#l2397"></a>
<span id="l2398">     *         regular expression, or is out of range</span><a href="#l2398"></a>
<span id="l2399">     * @throws NoSuchElementException if the input is exhausted</span><a href="#l2399"></a>
<span id="l2400">     * @throws IllegalStateException if this scanner is closed</span><a href="#l2400"></a>
<span id="l2401">     */</span><a href="#l2401"></a>
<span id="l2402">    public double nextDouble() {</span><a href="#l2402"></a>
<span id="l2403">        // Check cached result</span><a href="#l2403"></a>
<span id="l2404">        if ((typeCache != null) &amp;&amp; (typeCache instanceof Double)) {</span><a href="#l2404"></a>
<span id="l2405">            double val = ((Double)typeCache).doubleValue();</span><a href="#l2405"></a>
<span id="l2406">            useTypeCache();</span><a href="#l2406"></a>
<span id="l2407">            return val;</span><a href="#l2407"></a>
<span id="l2408">        }</span><a href="#l2408"></a>
<span id="l2409">        setRadix(10);</span><a href="#l2409"></a>
<span id="l2410">        clearCaches();</span><a href="#l2410"></a>
<span id="l2411">        // Search for next float</span><a href="#l2411"></a>
<span id="l2412">        try {</span><a href="#l2412"></a>
<span id="l2413">            return Double.parseDouble(processFloatToken(next(floatPattern())));</span><a href="#l2413"></a>
<span id="l2414">        } catch (NumberFormatException nfe) {</span><a href="#l2414"></a>
<span id="l2415">            position = matcher.start(); // don't skip bad token</span><a href="#l2415"></a>
<span id="l2416">            throw new InputMismatchException(nfe.getMessage());</span><a href="#l2416"></a>
<span id="l2417">        }</span><a href="#l2417"></a>
<span id="l2418">    }</span><a href="#l2418"></a>
<span id="l2419"></span><a href="#l2419"></a>
<span id="l2420">    // Convenience methods for scanning multi precision numbers</span><a href="#l2420"></a>
<span id="l2421"></span><a href="#l2421"></a>
<span id="l2422">    /**</span><a href="#l2422"></a>
<span id="l2423">     * Returns true if the next token in this scanner's input can be</span><a href="#l2423"></a>
<span id="l2424">     * interpreted as a &lt;code&gt;BigInteger&lt;/code&gt; in the default radix using the</span><a href="#l2424"></a>
<span id="l2425">     * {@link #nextBigInteger} method. The scanner does not advance past any</span><a href="#l2425"></a>
<span id="l2426">     * input.</span><a href="#l2426"></a>
<span id="l2427">     *</span><a href="#l2427"></a>
<span id="l2428">     * @return true if and only if this scanner's next token is a valid</span><a href="#l2428"></a>
<span id="l2429">     *         &lt;code&gt;BigInteger&lt;/code&gt;</span><a href="#l2429"></a>
<span id="l2430">     * @throws IllegalStateException if this scanner is closed</span><a href="#l2430"></a>
<span id="l2431">     */</span><a href="#l2431"></a>
<span id="l2432">    public boolean hasNextBigInteger() {</span><a href="#l2432"></a>
<span id="l2433">        return hasNextBigInteger(defaultRadix);</span><a href="#l2433"></a>
<span id="l2434">    }</span><a href="#l2434"></a>
<span id="l2435"></span><a href="#l2435"></a>
<span id="l2436">    /**</span><a href="#l2436"></a>
<span id="l2437">     * Returns true if the next token in this scanner's input can be</span><a href="#l2437"></a>
<span id="l2438">     * interpreted as a &lt;code&gt;BigInteger&lt;/code&gt; in the specified radix using</span><a href="#l2438"></a>
<span id="l2439">     * the {@link #nextBigInteger} method. The scanner does not advance past</span><a href="#l2439"></a>
<span id="l2440">     * any input.</span><a href="#l2440"></a>
<span id="l2441">     *</span><a href="#l2441"></a>
<span id="l2442">     * @param radix the radix used to interpret the token as an integer</span><a href="#l2442"></a>
<span id="l2443">     * @return true if and only if this scanner's next token is a valid</span><a href="#l2443"></a>
<span id="l2444">     *         &lt;code&gt;BigInteger&lt;/code&gt;</span><a href="#l2444"></a>
<span id="l2445">     * @throws IllegalStateException if this scanner is closed</span><a href="#l2445"></a>
<span id="l2446">     */</span><a href="#l2446"></a>
<span id="l2447">    public boolean hasNextBigInteger(int radix) {</span><a href="#l2447"></a>
<span id="l2448">        setRadix(radix);</span><a href="#l2448"></a>
<span id="l2449">        boolean result = hasNext(integerPattern());</span><a href="#l2449"></a>
<span id="l2450">        if (result) { // Cache it</span><a href="#l2450"></a>
<span id="l2451">            try {</span><a href="#l2451"></a>
<span id="l2452">                String s = (matcher.group(SIMPLE_GROUP_INDEX) == null) ?</span><a href="#l2452"></a>
<span id="l2453">                    processIntegerToken(hasNextResult) :</span><a href="#l2453"></a>
<span id="l2454">                    hasNextResult;</span><a href="#l2454"></a>
<span id="l2455">                typeCache = new BigInteger(s, radix);</span><a href="#l2455"></a>
<span id="l2456">            } catch (NumberFormatException nfe) {</span><a href="#l2456"></a>
<span id="l2457">                result = false;</span><a href="#l2457"></a>
<span id="l2458">            }</span><a href="#l2458"></a>
<span id="l2459">        }</span><a href="#l2459"></a>
<span id="l2460">        return result;</span><a href="#l2460"></a>
<span id="l2461">    }</span><a href="#l2461"></a>
<span id="l2462"></span><a href="#l2462"></a>
<span id="l2463">    /**</span><a href="#l2463"></a>
<span id="l2464">     * Scans the next token of the input as a {@link java.math.BigInteger</span><a href="#l2464"></a>
<span id="l2465">     * BigInteger}.</span><a href="#l2465"></a>
<span id="l2466">     *</span><a href="#l2466"></a>
<span id="l2467">     * &lt;p&gt; An invocation of this method of the form</span><a href="#l2467"></a>
<span id="l2468">     * &lt;tt&gt;nextBigInteger()&lt;/tt&gt; behaves in exactly the same way as the</span><a href="#l2468"></a>
<span id="l2469">     * invocation &lt;tt&gt;nextBigInteger(radix)&lt;/tt&gt;, where &lt;code&gt;radix&lt;/code&gt;</span><a href="#l2469"></a>
<span id="l2470">     * is the default radix of this scanner.</span><a href="#l2470"></a>
<span id="l2471">     *</span><a href="#l2471"></a>
<span id="l2472">     * @return the &lt;tt&gt;BigInteger&lt;/tt&gt; scanned from the input</span><a href="#l2472"></a>
<span id="l2473">     * @throws InputMismatchException</span><a href="#l2473"></a>
<span id="l2474">     *         if the next token does not match the &lt;i&gt;Integer&lt;/i&gt;</span><a href="#l2474"></a>
<span id="l2475">     *         regular expression, or is out of range</span><a href="#l2475"></a>
<span id="l2476">     * @throws NoSuchElementException if the input is exhausted</span><a href="#l2476"></a>
<span id="l2477">     * @throws IllegalStateException if this scanner is closed</span><a href="#l2477"></a>
<span id="l2478">     */</span><a href="#l2478"></a>
<span id="l2479">    public BigInteger nextBigInteger() {</span><a href="#l2479"></a>
<span id="l2480">        return nextBigInteger(defaultRadix);</span><a href="#l2480"></a>
<span id="l2481">    }</span><a href="#l2481"></a>
<span id="l2482"></span><a href="#l2482"></a>
<span id="l2483">    /**</span><a href="#l2483"></a>
<span id="l2484">     * Scans the next token of the input as a {@link java.math.BigInteger</span><a href="#l2484"></a>
<span id="l2485">     * BigInteger}.</span><a href="#l2485"></a>
<span id="l2486">     *</span><a href="#l2486"></a>
<span id="l2487">     * &lt;p&gt; If the next token matches the &lt;a</span><a href="#l2487"></a>
<span id="l2488">     * href=&quot;#Integer-regex&quot;&gt;&lt;i&gt;Integer&lt;/i&gt;&lt;/a&gt; regular expression defined</span><a href="#l2488"></a>
<span id="l2489">     * above then the token is converted into a &lt;tt&gt;BigInteger&lt;/tt&gt; value as if</span><a href="#l2489"></a>
<span id="l2490">     * by removing all group separators, mapping non-ASCII digits into ASCII</span><a href="#l2490"></a>
<span id="l2491">     * digits via the {@link Character#digit Character.digit}, and passing the</span><a href="#l2491"></a>
<span id="l2492">     * resulting string to the {@link</span><a href="#l2492"></a>
<span id="l2493">     * java.math.BigInteger#BigInteger(java.lang.String)</span><a href="#l2493"></a>
<span id="l2494">     * BigInteger(String, int)} constructor with the specified radix.</span><a href="#l2494"></a>
<span id="l2495">     *</span><a href="#l2495"></a>
<span id="l2496">     * @param radix the radix used to interpret the token</span><a href="#l2496"></a>
<span id="l2497">     * @return the &lt;tt&gt;BigInteger&lt;/tt&gt; scanned from the input</span><a href="#l2497"></a>
<span id="l2498">     * @throws InputMismatchException</span><a href="#l2498"></a>
<span id="l2499">     *         if the next token does not match the &lt;i&gt;Integer&lt;/i&gt;</span><a href="#l2499"></a>
<span id="l2500">     *         regular expression, or is out of range</span><a href="#l2500"></a>
<span id="l2501">     * @throws NoSuchElementException if the input is exhausted</span><a href="#l2501"></a>
<span id="l2502">     * @throws IllegalStateException if this scanner is closed</span><a href="#l2502"></a>
<span id="l2503">     */</span><a href="#l2503"></a>
<span id="l2504">    public BigInteger nextBigInteger(int radix) {</span><a href="#l2504"></a>
<span id="l2505">        // Check cached result</span><a href="#l2505"></a>
<span id="l2506">        if ((typeCache != null) &amp;&amp; (typeCache instanceof BigInteger)</span><a href="#l2506"></a>
<span id="l2507">            &amp;&amp; this.radix == radix) {</span><a href="#l2507"></a>
<span id="l2508">            BigInteger val = (BigInteger)typeCache;</span><a href="#l2508"></a>
<span id="l2509">            useTypeCache();</span><a href="#l2509"></a>
<span id="l2510">            return val;</span><a href="#l2510"></a>
<span id="l2511">        }</span><a href="#l2511"></a>
<span id="l2512">        setRadix(radix);</span><a href="#l2512"></a>
<span id="l2513">        clearCaches();</span><a href="#l2513"></a>
<span id="l2514">        // Search for next int</span><a href="#l2514"></a>
<span id="l2515">        try {</span><a href="#l2515"></a>
<span id="l2516">            String s = next(integerPattern());</span><a href="#l2516"></a>
<span id="l2517">            if (matcher.group(SIMPLE_GROUP_INDEX) == null)</span><a href="#l2517"></a>
<span id="l2518">                s = processIntegerToken(s);</span><a href="#l2518"></a>
<span id="l2519">            return new BigInteger(s, radix);</span><a href="#l2519"></a>
<span id="l2520">        } catch (NumberFormatException nfe) {</span><a href="#l2520"></a>
<span id="l2521">            position = matcher.start(); // don't skip bad token</span><a href="#l2521"></a>
<span id="l2522">            throw new InputMismatchException(nfe.getMessage());</span><a href="#l2522"></a>
<span id="l2523">        }</span><a href="#l2523"></a>
<span id="l2524">    }</span><a href="#l2524"></a>
<span id="l2525"></span><a href="#l2525"></a>
<span id="l2526">    /**</span><a href="#l2526"></a>
<span id="l2527">     * Returns true if the next token in this scanner's input can be</span><a href="#l2527"></a>
<span id="l2528">     * interpreted as a &lt;code&gt;BigDecimal&lt;/code&gt; using the</span><a href="#l2528"></a>
<span id="l2529">     * {@link #nextBigDecimal} method. The scanner does not advance past any</span><a href="#l2529"></a>
<span id="l2530">     * input.</span><a href="#l2530"></a>
<span id="l2531">     *</span><a href="#l2531"></a>
<span id="l2532">     * @return true if and only if this scanner's next token is a valid</span><a href="#l2532"></a>
<span id="l2533">     *         &lt;code&gt;BigDecimal&lt;/code&gt;</span><a href="#l2533"></a>
<span id="l2534">     * @throws IllegalStateException if this scanner is closed</span><a href="#l2534"></a>
<span id="l2535">     */</span><a href="#l2535"></a>
<span id="l2536">    public boolean hasNextBigDecimal() {</span><a href="#l2536"></a>
<span id="l2537">        setRadix(10);</span><a href="#l2537"></a>
<span id="l2538">        boolean result = hasNext(decimalPattern());</span><a href="#l2538"></a>
<span id="l2539">        if (result) { // Cache it</span><a href="#l2539"></a>
<span id="l2540">            try {</span><a href="#l2540"></a>
<span id="l2541">                String s = processFloatToken(hasNextResult);</span><a href="#l2541"></a>
<span id="l2542">                typeCache = new BigDecimal(s);</span><a href="#l2542"></a>
<span id="l2543">            } catch (NumberFormatException nfe) {</span><a href="#l2543"></a>
<span id="l2544">                result = false;</span><a href="#l2544"></a>
<span id="l2545">            }</span><a href="#l2545"></a>
<span id="l2546">        }</span><a href="#l2546"></a>
<span id="l2547">        return result;</span><a href="#l2547"></a>
<span id="l2548">    }</span><a href="#l2548"></a>
<span id="l2549"></span><a href="#l2549"></a>
<span id="l2550">    /**</span><a href="#l2550"></a>
<span id="l2551">     * Scans the next token of the input as a {@link java.math.BigDecimal</span><a href="#l2551"></a>
<span id="l2552">     * BigDecimal}.</span><a href="#l2552"></a>
<span id="l2553">     *</span><a href="#l2553"></a>
<span id="l2554">     * &lt;p&gt; If the next token matches the &lt;a</span><a href="#l2554"></a>
<span id="l2555">     * href=&quot;#Decimal-regex&quot;&gt;&lt;i&gt;Decimal&lt;/i&gt;&lt;/a&gt; regular expression defined</span><a href="#l2555"></a>
<span id="l2556">     * above then the token is converted into a &lt;tt&gt;BigDecimal&lt;/tt&gt; value as if</span><a href="#l2556"></a>
<span id="l2557">     * by removing all group separators, mapping non-ASCII digits into ASCII</span><a href="#l2557"></a>
<span id="l2558">     * digits via the {@link Character#digit Character.digit}, and passing the</span><a href="#l2558"></a>
<span id="l2559">     * resulting string to the {@link</span><a href="#l2559"></a>
<span id="l2560">     * java.math.BigDecimal#BigDecimal(java.lang.String) BigDecimal(String)}</span><a href="#l2560"></a>
<span id="l2561">     * constructor.</span><a href="#l2561"></a>
<span id="l2562">     *</span><a href="#l2562"></a>
<span id="l2563">     * @return the &lt;tt&gt;BigDecimal&lt;/tt&gt; scanned from the input</span><a href="#l2563"></a>
<span id="l2564">     * @throws InputMismatchException</span><a href="#l2564"></a>
<span id="l2565">     *         if the next token does not match the &lt;i&gt;Decimal&lt;/i&gt;</span><a href="#l2565"></a>
<span id="l2566">     *         regular expression, or is out of range</span><a href="#l2566"></a>
<span id="l2567">     * @throws NoSuchElementException if the input is exhausted</span><a href="#l2567"></a>
<span id="l2568">     * @throws IllegalStateException if this scanner is closed</span><a href="#l2568"></a>
<span id="l2569">     */</span><a href="#l2569"></a>
<span id="l2570">    public BigDecimal nextBigDecimal() {</span><a href="#l2570"></a>
<span id="l2571">        // Check cached result</span><a href="#l2571"></a>
<span id="l2572">        if ((typeCache != null) &amp;&amp; (typeCache instanceof BigDecimal)) {</span><a href="#l2572"></a>
<span id="l2573">            BigDecimal val = (BigDecimal)typeCache;</span><a href="#l2573"></a>
<span id="l2574">            useTypeCache();</span><a href="#l2574"></a>
<span id="l2575">            return val;</span><a href="#l2575"></a>
<span id="l2576">        }</span><a href="#l2576"></a>
<span id="l2577">        setRadix(10);</span><a href="#l2577"></a>
<span id="l2578">        clearCaches();</span><a href="#l2578"></a>
<span id="l2579">        // Search for next float</span><a href="#l2579"></a>
<span id="l2580">        try {</span><a href="#l2580"></a>
<span id="l2581">            String s = processFloatToken(next(decimalPattern()));</span><a href="#l2581"></a>
<span id="l2582">            return new BigDecimal(s);</span><a href="#l2582"></a>
<span id="l2583">        } catch (NumberFormatException nfe) {</span><a href="#l2583"></a>
<span id="l2584">            position = matcher.start(); // don't skip bad token</span><a href="#l2584"></a>
<span id="l2585">            throw new InputMismatchException(nfe.getMessage());</span><a href="#l2585"></a>
<span id="l2586">        }</span><a href="#l2586"></a>
<span id="l2587">    }</span><a href="#l2587"></a>
<span id="l2588"></span><a href="#l2588"></a>
<span id="l2589">    /**</span><a href="#l2589"></a>
<span id="l2590">     * Resets this scanner.</span><a href="#l2590"></a>
<span id="l2591">     *</span><a href="#l2591"></a>
<span id="l2592">     * &lt;p&gt; Resetting a scanner discards all of its explicit state</span><a href="#l2592"></a>
<span id="l2593">     * information which may have been changed by invocations of {@link</span><a href="#l2593"></a>
<span id="l2594">     * #useDelimiter}, {@link #useLocale}, or {@link #useRadix}.</span><a href="#l2594"></a>
<span id="l2595">     *</span><a href="#l2595"></a>
<span id="l2596">     * &lt;p&gt; An invocation of this method of the form</span><a href="#l2596"></a>
<span id="l2597">     * &lt;tt&gt;scanner.reset()&lt;/tt&gt; behaves in exactly the same way as the</span><a href="#l2597"></a>
<span id="l2598">     * invocation</span><a href="#l2598"></a>
<span id="l2599">     *</span><a href="#l2599"></a>
<span id="l2600">     * &lt;blockquote&gt;&lt;pre&gt;{@code</span><a href="#l2600"></a>
<span id="l2601">     *   scanner.useDelimiter(&quot;\\p{javaWhitespace}+&quot;)</span><a href="#l2601"></a>
<span id="l2602">     *          .useLocale(Locale.getDefault(Locale.Category.FORMAT))</span><a href="#l2602"></a>
<span id="l2603">     *          .useRadix(10);</span><a href="#l2603"></a>
<span id="l2604">     * }&lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l2604"></a>
<span id="l2605">     *</span><a href="#l2605"></a>
<span id="l2606">     * @return this scanner</span><a href="#l2606"></a>
<span id="l2607">     *</span><a href="#l2607"></a>
<span id="l2608">     * @since 1.6</span><a href="#l2608"></a>
<span id="l2609">     */</span><a href="#l2609"></a>
<span id="l2610">    public Scanner reset() {</span><a href="#l2610"></a>
<span id="l2611">        delimPattern = WHITESPACE_PATTERN;</span><a href="#l2611"></a>
<span id="l2612">        useLocale(Locale.getDefault(Locale.Category.FORMAT));</span><a href="#l2612"></a>
<span id="l2613">        useRadix(10);</span><a href="#l2613"></a>
<span id="l2614">        clearCaches();</span><a href="#l2614"></a>
<span id="l2615">        return this;</span><a href="#l2615"></a>
<span id="l2616">    }</span><a href="#l2616"></a>
<span id="l2617">}</span><a href="#l2617"></a></pre>
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

