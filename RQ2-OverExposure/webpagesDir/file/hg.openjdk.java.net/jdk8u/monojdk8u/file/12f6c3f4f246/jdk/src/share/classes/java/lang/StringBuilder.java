<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/monojdk8u/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/monojdk8u/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/monojdk8u/static/mercurial.js"></script>

<title>jdk8u/monojdk8u: 12f6c3f4f246 jdk/src/share/classes/java/lang/StringBuilder.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk8u/monojdk8u/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk8u/monojdk8u/shortlog/12f6c3f4f246">log</a></li>
<li><a href="/jdk8u/monojdk8u/graph/12f6c3f4f246">graph</a></li>
<li><a href="/jdk8u/monojdk8u/tags">tags</a></li>
<li><a href="/jdk8u/monojdk8u/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk8u/monojdk8u/rev/12f6c3f4f246">changeset</a></li>
<li><a href="/jdk8u/monojdk8u/file/12f6c3f4f246/jdk/src/share/classes/java/lang/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/monojdk8u/file/tip/jdk/src/share/classes/java/lang/StringBuilder.java">latest</a></li>
<li><a href="/jdk8u/monojdk8u/diff/12f6c3f4f246/jdk/src/share/classes/java/lang/StringBuilder.java">diff</a></li>
<li><a href="/jdk8u/monojdk8u/comparison/12f6c3f4f246/jdk/src/share/classes/java/lang/StringBuilder.java">comparison</a></li>
<li><a href="/jdk8u/monojdk8u/annotate/12f6c3f4f246/jdk/src/share/classes/java/lang/StringBuilder.java">annotate</a></li>
<li><a href="/jdk8u/monojdk8u/log/12f6c3f4f246/jdk/src/share/classes/java/lang/StringBuilder.java">file log</a></li>
<li><a href="/jdk8u/monojdk8u/raw-file/12f6c3f4f246/jdk/src/share/classes/java/lang/StringBuilder.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/monojdk8u/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/monojdk8u">monojdk8u</a> </h2>
<h3>view jdk/src/share/classes/java/lang/StringBuilder.java @ 48809:12f6c3f4f246</h3>

<form class="search" action="/jdk8u/monojdk8u/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk8u/monojdk8u/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8270392: Improve String constructions
Reviewed-by: yan, andrew
Contributed-by: Tianmin Shi &lt;tianshi@amazon.com&gt;</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#112;&#104;&#104;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Tue, 18 Jan 2022 22:12:32 +0000</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk8u/monojdk8u/file/7f14411bf16d/jdk/src/share/classes/java/lang/StringBuilder.java">7f14411bf16d</a> </td>
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
<span id="l2"> * Copyright (c) 2003, 2021, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l26">package java.lang;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.io.IOException;</span><a href="#l28"></a>
<span id="l29">import java.io.ObjectInputStream;</span><a href="#l29"></a>
<span id="l30">import java.io.ObjectOutputStream;</span><a href="#l30"></a>
<span id="l31">import java.io.Serializable;</span><a href="#l31"></a>
<span id="l32">import java.io.StreamCorruptedException;</span><a href="#l32"></a>
<span id="l33"></span><a href="#l33"></a>
<span id="l34">/**</span><a href="#l34"></a>
<span id="l35"> * A mutable sequence of characters.  This class provides an API compatible</span><a href="#l35"></a>
<span id="l36"> * with {@code StringBuffer}, but with no guarantee of synchronization.</span><a href="#l36"></a>
<span id="l37"> * This class is designed for use as a drop-in replacement for</span><a href="#l37"></a>
<span id="l38"> * {@code StringBuffer} in places where the string buffer was being</span><a href="#l38"></a>
<span id="l39"> * used by a single thread (as is generally the case).   Where possible,</span><a href="#l39"></a>
<span id="l40"> * it is recommended that this class be used in preference to</span><a href="#l40"></a>
<span id="l41"> * {@code StringBuffer} as it will be faster under most implementations.</span><a href="#l41"></a>
<span id="l42"> *</span><a href="#l42"></a>
<span id="l43"> * &lt;p&gt;The principal operations on a {@code StringBuilder} are the</span><a href="#l43"></a>
<span id="l44"> * {@code append} and {@code insert} methods, which are</span><a href="#l44"></a>
<span id="l45"> * overloaded so as to accept data of any type. Each effectively</span><a href="#l45"></a>
<span id="l46"> * converts a given datum to a string and then appends or inserts the</span><a href="#l46"></a>
<span id="l47"> * characters of that string to the string builder. The</span><a href="#l47"></a>
<span id="l48"> * {@code append} method always adds these characters at the end</span><a href="#l48"></a>
<span id="l49"> * of the builder; the {@code insert} method adds the characters at</span><a href="#l49"></a>
<span id="l50"> * a specified point.</span><a href="#l50"></a>
<span id="l51"> * &lt;p&gt;</span><a href="#l51"></a>
<span id="l52"> * For example, if {@code z} refers to a string builder object</span><a href="#l52"></a>
<span id="l53"> * whose current contents are &quot;{@code start}&quot;, then</span><a href="#l53"></a>
<span id="l54"> * the method call {@code z.append(&quot;le&quot;)} would cause the string</span><a href="#l54"></a>
<span id="l55"> * builder to contain &quot;{@code startle}&quot;, whereas</span><a href="#l55"></a>
<span id="l56"> * {@code z.insert(4, &quot;le&quot;)} would alter the string builder to</span><a href="#l56"></a>
<span id="l57"> * contain &quot;{@code starlet}&quot;.</span><a href="#l57"></a>
<span id="l58"> * &lt;p&gt;</span><a href="#l58"></a>
<span id="l59"> * In general, if sb refers to an instance of a {@code StringBuilder},</span><a href="#l59"></a>
<span id="l60"> * then {@code sb.append(x)} has the same effect as</span><a href="#l60"></a>
<span id="l61"> * {@code sb.insert(sb.length(), x)}.</span><a href="#l61"></a>
<span id="l62"> * &lt;p&gt;</span><a href="#l62"></a>
<span id="l63"> * Every string builder has a capacity. As long as the length of the</span><a href="#l63"></a>
<span id="l64"> * character sequence contained in the string builder does not exceed</span><a href="#l64"></a>
<span id="l65"> * the capacity, it is not necessary to allocate a new internal</span><a href="#l65"></a>
<span id="l66"> * buffer. If the internal buffer overflows, it is automatically made larger.</span><a href="#l66"></a>
<span id="l67"> *</span><a href="#l67"></a>
<span id="l68"> * &lt;p&gt;Instances of {@code StringBuilder} are not safe for</span><a href="#l68"></a>
<span id="l69"> * use by multiple threads. If such synchronization is required then it is</span><a href="#l69"></a>
<span id="l70"> * recommended that {@link java.lang.StringBuffer} be used.</span><a href="#l70"></a>
<span id="l71"> *</span><a href="#l71"></a>
<span id="l72"> * &lt;p&gt;Unless otherwise noted, passing a {@code null} argument to a constructor</span><a href="#l72"></a>
<span id="l73"> * or method in this class will cause a {@link NullPointerException} to be</span><a href="#l73"></a>
<span id="l74"> * thrown.</span><a href="#l74"></a>
<span id="l75"> *</span><a href="#l75"></a>
<span id="l76"> * @author      Michael McCloskey</span><a href="#l76"></a>
<span id="l77"> * @see         java.lang.StringBuffer</span><a href="#l77"></a>
<span id="l78"> * @see         java.lang.String</span><a href="#l78"></a>
<span id="l79"> * @since       1.5</span><a href="#l79"></a>
<span id="l80"> */</span><a href="#l80"></a>
<span id="l81">public final class StringBuilder</span><a href="#l81"></a>
<span id="l82">    extends AbstractStringBuilder</span><a href="#l82"></a>
<span id="l83">    implements Serializable, CharSequence</span><a href="#l83"></a>
<span id="l84">{</span><a href="#l84"></a>
<span id="l85"></span><a href="#l85"></a>
<span id="l86">    /** use serialVersionUID for interoperability */</span><a href="#l86"></a>
<span id="l87">    static final long serialVersionUID = 4383685877147921099L;</span><a href="#l87"></a>
<span id="l88"></span><a href="#l88"></a>
<span id="l89">    /**</span><a href="#l89"></a>
<span id="l90">     * Constructs a string builder with no characters in it and an</span><a href="#l90"></a>
<span id="l91">     * initial capacity of 16 characters.</span><a href="#l91"></a>
<span id="l92">     */</span><a href="#l92"></a>
<span id="l93">    public StringBuilder() {</span><a href="#l93"></a>
<span id="l94">        super(16);</span><a href="#l94"></a>
<span id="l95">    }</span><a href="#l95"></a>
<span id="l96"></span><a href="#l96"></a>
<span id="l97">    /**</span><a href="#l97"></a>
<span id="l98">     * Constructs a string builder with no characters in it and an</span><a href="#l98"></a>
<span id="l99">     * initial capacity specified by the {@code capacity} argument.</span><a href="#l99"></a>
<span id="l100">     *</span><a href="#l100"></a>
<span id="l101">     * @param      capacity  the initial capacity.</span><a href="#l101"></a>
<span id="l102">     * @throws     NegativeArraySizeException  if the {@code capacity}</span><a href="#l102"></a>
<span id="l103">     *               argument is less than {@code 0}.</span><a href="#l103"></a>
<span id="l104">     */</span><a href="#l104"></a>
<span id="l105">    public StringBuilder(int capacity) {</span><a href="#l105"></a>
<span id="l106">        super(capacity);</span><a href="#l106"></a>
<span id="l107">    }</span><a href="#l107"></a>
<span id="l108"></span><a href="#l108"></a>
<span id="l109">    /**</span><a href="#l109"></a>
<span id="l110">     * Constructs a string builder initialized to the contents of the</span><a href="#l110"></a>
<span id="l111">     * specified string. The initial capacity of the string builder is</span><a href="#l111"></a>
<span id="l112">     * {@code 16} plus the length of the string argument.</span><a href="#l112"></a>
<span id="l113">     *</span><a href="#l113"></a>
<span id="l114">     * @param   str   the initial contents of the buffer.</span><a href="#l114"></a>
<span id="l115">     */</span><a href="#l115"></a>
<span id="l116">    public StringBuilder(String str) {</span><a href="#l116"></a>
<span id="l117">        super(str.length() + 16);</span><a href="#l117"></a>
<span id="l118">        append(str);</span><a href="#l118"></a>
<span id="l119">    }</span><a href="#l119"></a>
<span id="l120"></span><a href="#l120"></a>
<span id="l121">    /**</span><a href="#l121"></a>
<span id="l122">     * Constructs a string builder that contains the same characters</span><a href="#l122"></a>
<span id="l123">     * as the specified {@code CharSequence}. The initial capacity of</span><a href="#l123"></a>
<span id="l124">     * the string builder is {@code 16} plus the length of the</span><a href="#l124"></a>
<span id="l125">     * {@code CharSequence} argument.</span><a href="#l125"></a>
<span id="l126">     *</span><a href="#l126"></a>
<span id="l127">     * @param      seq   the sequence to copy.</span><a href="#l127"></a>
<span id="l128">     */</span><a href="#l128"></a>
<span id="l129">    public StringBuilder(CharSequence seq) {</span><a href="#l129"></a>
<span id="l130">        this(seq.length() + 16);</span><a href="#l130"></a>
<span id="l131">        append(seq);</span><a href="#l131"></a>
<span id="l132">    }</span><a href="#l132"></a>
<span id="l133"></span><a href="#l133"></a>
<span id="l134">    @Override</span><a href="#l134"></a>
<span id="l135">    public StringBuilder append(Object obj) {</span><a href="#l135"></a>
<span id="l136">        return append(String.valueOf(obj));</span><a href="#l136"></a>
<span id="l137">    }</span><a href="#l137"></a>
<span id="l138"></span><a href="#l138"></a>
<span id="l139">    @Override</span><a href="#l139"></a>
<span id="l140">    public StringBuilder append(String str) {</span><a href="#l140"></a>
<span id="l141">        super.append(str);</span><a href="#l141"></a>
<span id="l142">        return this;</span><a href="#l142"></a>
<span id="l143">    }</span><a href="#l143"></a>
<span id="l144"></span><a href="#l144"></a>
<span id="l145">    /**</span><a href="#l145"></a>
<span id="l146">     * Appends the specified {@code StringBuffer} to this sequence.</span><a href="#l146"></a>
<span id="l147">     * &lt;p&gt;</span><a href="#l147"></a>
<span id="l148">     * The characters of the {@code StringBuffer} argument are appended,</span><a href="#l148"></a>
<span id="l149">     * in order, to this sequence, increasing the</span><a href="#l149"></a>
<span id="l150">     * length of this sequence by the length of the argument.</span><a href="#l150"></a>
<span id="l151">     * If {@code sb} is {@code null}, then the four characters</span><a href="#l151"></a>
<span id="l152">     * {@code &quot;null&quot;} are appended to this sequence.</span><a href="#l152"></a>
<span id="l153">     * &lt;p&gt;</span><a href="#l153"></a>
<span id="l154">     * Let &lt;i&gt;n&lt;/i&gt; be the length of this character sequence just prior to</span><a href="#l154"></a>
<span id="l155">     * execution of the {@code append} method. Then the character at index</span><a href="#l155"></a>
<span id="l156">     * &lt;i&gt;k&lt;/i&gt; in the new character sequence is equal to the character at</span><a href="#l156"></a>
<span id="l157">     * index &lt;i&gt;k&lt;/i&gt; in the old character sequence, if &lt;i&gt;k&lt;/i&gt; is less than</span><a href="#l157"></a>
<span id="l158">     * &lt;i&gt;n&lt;/i&gt;; otherwise, it is equal to the character at index &lt;i&gt;k-n&lt;/i&gt;</span><a href="#l158"></a>
<span id="l159">     * in the argument {@code sb}.</span><a href="#l159"></a>
<span id="l160">     *</span><a href="#l160"></a>
<span id="l161">     * @param   sb   the {@code StringBuffer} to append.</span><a href="#l161"></a>
<span id="l162">     * @return  a reference to this object.</span><a href="#l162"></a>
<span id="l163">     */</span><a href="#l163"></a>
<span id="l164">    public StringBuilder append(StringBuffer sb) {</span><a href="#l164"></a>
<span id="l165">        super.append(sb);</span><a href="#l165"></a>
<span id="l166">        return this;</span><a href="#l166"></a>
<span id="l167">    }</span><a href="#l167"></a>
<span id="l168"></span><a href="#l168"></a>
<span id="l169">    @Override</span><a href="#l169"></a>
<span id="l170">    public StringBuilder append(CharSequence s) {</span><a href="#l170"></a>
<span id="l171">        super.append(s);</span><a href="#l171"></a>
<span id="l172">        return this;</span><a href="#l172"></a>
<span id="l173">    }</span><a href="#l173"></a>
<span id="l174"></span><a href="#l174"></a>
<span id="l175">    /**</span><a href="#l175"></a>
<span id="l176">     * @throws     IndexOutOfBoundsException {@inheritDoc}</span><a href="#l176"></a>
<span id="l177">     */</span><a href="#l177"></a>
<span id="l178">    @Override</span><a href="#l178"></a>
<span id="l179">    public StringBuilder append(CharSequence s, int start, int end) {</span><a href="#l179"></a>
<span id="l180">        super.append(s, start, end);</span><a href="#l180"></a>
<span id="l181">        return this;</span><a href="#l181"></a>
<span id="l182">    }</span><a href="#l182"></a>
<span id="l183"></span><a href="#l183"></a>
<span id="l184">    @Override</span><a href="#l184"></a>
<span id="l185">    public StringBuilder append(char[] str) {</span><a href="#l185"></a>
<span id="l186">        super.append(str);</span><a href="#l186"></a>
<span id="l187">        return this;</span><a href="#l187"></a>
<span id="l188">    }</span><a href="#l188"></a>
<span id="l189"></span><a href="#l189"></a>
<span id="l190">    /**</span><a href="#l190"></a>
<span id="l191">     * @throws IndexOutOfBoundsException {@inheritDoc}</span><a href="#l191"></a>
<span id="l192">     */</span><a href="#l192"></a>
<span id="l193">    @Override</span><a href="#l193"></a>
<span id="l194">    public StringBuilder append(char[] str, int offset, int len) {</span><a href="#l194"></a>
<span id="l195">        super.append(str, offset, len);</span><a href="#l195"></a>
<span id="l196">        return this;</span><a href="#l196"></a>
<span id="l197">    }</span><a href="#l197"></a>
<span id="l198"></span><a href="#l198"></a>
<span id="l199">    @Override</span><a href="#l199"></a>
<span id="l200">    public StringBuilder append(boolean b) {</span><a href="#l200"></a>
<span id="l201">        super.append(b);</span><a href="#l201"></a>
<span id="l202">        return this;</span><a href="#l202"></a>
<span id="l203">    }</span><a href="#l203"></a>
<span id="l204"></span><a href="#l204"></a>
<span id="l205">    @Override</span><a href="#l205"></a>
<span id="l206">    public StringBuilder append(char c) {</span><a href="#l206"></a>
<span id="l207">        super.append(c);</span><a href="#l207"></a>
<span id="l208">        return this;</span><a href="#l208"></a>
<span id="l209">    }</span><a href="#l209"></a>
<span id="l210"></span><a href="#l210"></a>
<span id="l211">    @Override</span><a href="#l211"></a>
<span id="l212">    public StringBuilder append(int i) {</span><a href="#l212"></a>
<span id="l213">        super.append(i);</span><a href="#l213"></a>
<span id="l214">        return this;</span><a href="#l214"></a>
<span id="l215">    }</span><a href="#l215"></a>
<span id="l216"></span><a href="#l216"></a>
<span id="l217">    @Override</span><a href="#l217"></a>
<span id="l218">    public StringBuilder append(long lng) {</span><a href="#l218"></a>
<span id="l219">        super.append(lng);</span><a href="#l219"></a>
<span id="l220">        return this;</span><a href="#l220"></a>
<span id="l221">    }</span><a href="#l221"></a>
<span id="l222"></span><a href="#l222"></a>
<span id="l223">    @Override</span><a href="#l223"></a>
<span id="l224">    public StringBuilder append(float f) {</span><a href="#l224"></a>
<span id="l225">        super.append(f);</span><a href="#l225"></a>
<span id="l226">        return this;</span><a href="#l226"></a>
<span id="l227">    }</span><a href="#l227"></a>
<span id="l228"></span><a href="#l228"></a>
<span id="l229">    @Override</span><a href="#l229"></a>
<span id="l230">    public StringBuilder append(double d) {</span><a href="#l230"></a>
<span id="l231">        super.append(d);</span><a href="#l231"></a>
<span id="l232">        return this;</span><a href="#l232"></a>
<span id="l233">    }</span><a href="#l233"></a>
<span id="l234"></span><a href="#l234"></a>
<span id="l235">    /**</span><a href="#l235"></a>
<span id="l236">     * @since 1.5</span><a href="#l236"></a>
<span id="l237">     */</span><a href="#l237"></a>
<span id="l238">    @Override</span><a href="#l238"></a>
<span id="l239">    public StringBuilder appendCodePoint(int codePoint) {</span><a href="#l239"></a>
<span id="l240">        super.appendCodePoint(codePoint);</span><a href="#l240"></a>
<span id="l241">        return this;</span><a href="#l241"></a>
<span id="l242">    }</span><a href="#l242"></a>
<span id="l243"></span><a href="#l243"></a>
<span id="l244">    /**</span><a href="#l244"></a>
<span id="l245">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l245"></a>
<span id="l246">     */</span><a href="#l246"></a>
<span id="l247">    @Override</span><a href="#l247"></a>
<span id="l248">    public StringBuilder delete(int start, int end) {</span><a href="#l248"></a>
<span id="l249">        super.delete(start, end);</span><a href="#l249"></a>
<span id="l250">        return this;</span><a href="#l250"></a>
<span id="l251">    }</span><a href="#l251"></a>
<span id="l252"></span><a href="#l252"></a>
<span id="l253">    /**</span><a href="#l253"></a>
<span id="l254">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l254"></a>
<span id="l255">     */</span><a href="#l255"></a>
<span id="l256">    @Override</span><a href="#l256"></a>
<span id="l257">    public StringBuilder deleteCharAt(int index) {</span><a href="#l257"></a>
<span id="l258">        super.deleteCharAt(index);</span><a href="#l258"></a>
<span id="l259">        return this;</span><a href="#l259"></a>
<span id="l260">    }</span><a href="#l260"></a>
<span id="l261"></span><a href="#l261"></a>
<span id="l262">    /**</span><a href="#l262"></a>
<span id="l263">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l263"></a>
<span id="l264">     */</span><a href="#l264"></a>
<span id="l265">    @Override</span><a href="#l265"></a>
<span id="l266">    public StringBuilder replace(int start, int end, String str) {</span><a href="#l266"></a>
<span id="l267">        super.replace(start, end, str);</span><a href="#l267"></a>
<span id="l268">        return this;</span><a href="#l268"></a>
<span id="l269">    }</span><a href="#l269"></a>
<span id="l270"></span><a href="#l270"></a>
<span id="l271">    /**</span><a href="#l271"></a>
<span id="l272">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l272"></a>
<span id="l273">     */</span><a href="#l273"></a>
<span id="l274">    @Override</span><a href="#l274"></a>
<span id="l275">    public StringBuilder insert(int index, char[] str, int offset,</span><a href="#l275"></a>
<span id="l276">                                int len)</span><a href="#l276"></a>
<span id="l277">    {</span><a href="#l277"></a>
<span id="l278">        super.insert(index, str, offset, len);</span><a href="#l278"></a>
<span id="l279">        return this;</span><a href="#l279"></a>
<span id="l280">    }</span><a href="#l280"></a>
<span id="l281"></span><a href="#l281"></a>
<span id="l282">    /**</span><a href="#l282"></a>
<span id="l283">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l283"></a>
<span id="l284">     */</span><a href="#l284"></a>
<span id="l285">    @Override</span><a href="#l285"></a>
<span id="l286">    public StringBuilder insert(int offset, Object obj) {</span><a href="#l286"></a>
<span id="l287">            super.insert(offset, obj);</span><a href="#l287"></a>
<span id="l288">            return this;</span><a href="#l288"></a>
<span id="l289">    }</span><a href="#l289"></a>
<span id="l290"></span><a href="#l290"></a>
<span id="l291">    /**</span><a href="#l291"></a>
<span id="l292">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l292"></a>
<span id="l293">     */</span><a href="#l293"></a>
<span id="l294">    @Override</span><a href="#l294"></a>
<span id="l295">    public StringBuilder insert(int offset, String str) {</span><a href="#l295"></a>
<span id="l296">        super.insert(offset, str);</span><a href="#l296"></a>
<span id="l297">        return this;</span><a href="#l297"></a>
<span id="l298">    }</span><a href="#l298"></a>
<span id="l299"></span><a href="#l299"></a>
<span id="l300">    /**</span><a href="#l300"></a>
<span id="l301">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l301"></a>
<span id="l302">     */</span><a href="#l302"></a>
<span id="l303">    @Override</span><a href="#l303"></a>
<span id="l304">    public StringBuilder insert(int offset, char[] str) {</span><a href="#l304"></a>
<span id="l305">        super.insert(offset, str);</span><a href="#l305"></a>
<span id="l306">        return this;</span><a href="#l306"></a>
<span id="l307">    }</span><a href="#l307"></a>
<span id="l308"></span><a href="#l308"></a>
<span id="l309">    /**</span><a href="#l309"></a>
<span id="l310">     * @throws IndexOutOfBoundsException {@inheritDoc}</span><a href="#l310"></a>
<span id="l311">     */</span><a href="#l311"></a>
<span id="l312">    @Override</span><a href="#l312"></a>
<span id="l313">    public StringBuilder insert(int dstOffset, CharSequence s) {</span><a href="#l313"></a>
<span id="l314">            super.insert(dstOffset, s);</span><a href="#l314"></a>
<span id="l315">            return this;</span><a href="#l315"></a>
<span id="l316">    }</span><a href="#l316"></a>
<span id="l317"></span><a href="#l317"></a>
<span id="l318">    /**</span><a href="#l318"></a>
<span id="l319">     * @throws IndexOutOfBoundsException {@inheritDoc}</span><a href="#l319"></a>
<span id="l320">     */</span><a href="#l320"></a>
<span id="l321">    @Override</span><a href="#l321"></a>
<span id="l322">    public StringBuilder insert(int dstOffset, CharSequence s,</span><a href="#l322"></a>
<span id="l323">                                int start, int end)</span><a href="#l323"></a>
<span id="l324">    {</span><a href="#l324"></a>
<span id="l325">        super.insert(dstOffset, s, start, end);</span><a href="#l325"></a>
<span id="l326">        return this;</span><a href="#l326"></a>
<span id="l327">    }</span><a href="#l327"></a>
<span id="l328"></span><a href="#l328"></a>
<span id="l329">    /**</span><a href="#l329"></a>
<span id="l330">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l330"></a>
<span id="l331">     */</span><a href="#l331"></a>
<span id="l332">    @Override</span><a href="#l332"></a>
<span id="l333">    public StringBuilder insert(int offset, boolean b) {</span><a href="#l333"></a>
<span id="l334">        super.insert(offset, b);</span><a href="#l334"></a>
<span id="l335">        return this;</span><a href="#l335"></a>
<span id="l336">    }</span><a href="#l336"></a>
<span id="l337"></span><a href="#l337"></a>
<span id="l338">    /**</span><a href="#l338"></a>
<span id="l339">     * @throws IndexOutOfBoundsException {@inheritDoc}</span><a href="#l339"></a>
<span id="l340">     */</span><a href="#l340"></a>
<span id="l341">    @Override</span><a href="#l341"></a>
<span id="l342">    public StringBuilder insert(int offset, char c) {</span><a href="#l342"></a>
<span id="l343">        super.insert(offset, c);</span><a href="#l343"></a>
<span id="l344">        return this;</span><a href="#l344"></a>
<span id="l345">    }</span><a href="#l345"></a>
<span id="l346"></span><a href="#l346"></a>
<span id="l347">    /**</span><a href="#l347"></a>
<span id="l348">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l348"></a>
<span id="l349">     */</span><a href="#l349"></a>
<span id="l350">    @Override</span><a href="#l350"></a>
<span id="l351">    public StringBuilder insert(int offset, int i) {</span><a href="#l351"></a>
<span id="l352">        super.insert(offset, i);</span><a href="#l352"></a>
<span id="l353">        return this;</span><a href="#l353"></a>
<span id="l354">    }</span><a href="#l354"></a>
<span id="l355"></span><a href="#l355"></a>
<span id="l356">    /**</span><a href="#l356"></a>
<span id="l357">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l357"></a>
<span id="l358">     */</span><a href="#l358"></a>
<span id="l359">    @Override</span><a href="#l359"></a>
<span id="l360">    public StringBuilder insert(int offset, long l) {</span><a href="#l360"></a>
<span id="l361">        super.insert(offset, l);</span><a href="#l361"></a>
<span id="l362">        return this;</span><a href="#l362"></a>
<span id="l363">    }</span><a href="#l363"></a>
<span id="l364"></span><a href="#l364"></a>
<span id="l365">    /**</span><a href="#l365"></a>
<span id="l366">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l366"></a>
<span id="l367">     */</span><a href="#l367"></a>
<span id="l368">    @Override</span><a href="#l368"></a>
<span id="l369">    public StringBuilder insert(int offset, float f) {</span><a href="#l369"></a>
<span id="l370">        super.insert(offset, f);</span><a href="#l370"></a>
<span id="l371">        return this;</span><a href="#l371"></a>
<span id="l372">    }</span><a href="#l372"></a>
<span id="l373"></span><a href="#l373"></a>
<span id="l374">    /**</span><a href="#l374"></a>
<span id="l375">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l375"></a>
<span id="l376">     */</span><a href="#l376"></a>
<span id="l377">    @Override</span><a href="#l377"></a>
<span id="l378">    public StringBuilder insert(int offset, double d) {</span><a href="#l378"></a>
<span id="l379">        super.insert(offset, d);</span><a href="#l379"></a>
<span id="l380">        return this;</span><a href="#l380"></a>
<span id="l381">    }</span><a href="#l381"></a>
<span id="l382"></span><a href="#l382"></a>
<span id="l383">    @Override</span><a href="#l383"></a>
<span id="l384">    public int indexOf(String str) {</span><a href="#l384"></a>
<span id="l385">        return super.indexOf(str);</span><a href="#l385"></a>
<span id="l386">    }</span><a href="#l386"></a>
<span id="l387"></span><a href="#l387"></a>
<span id="l388">    @Override</span><a href="#l388"></a>
<span id="l389">    public int indexOf(String str, int fromIndex) {</span><a href="#l389"></a>
<span id="l390">        return super.indexOf(str, fromIndex);</span><a href="#l390"></a>
<span id="l391">    }</span><a href="#l391"></a>
<span id="l392"></span><a href="#l392"></a>
<span id="l393">    @Override</span><a href="#l393"></a>
<span id="l394">    public int lastIndexOf(String str) {</span><a href="#l394"></a>
<span id="l395">        return super.lastIndexOf(str);</span><a href="#l395"></a>
<span id="l396">    }</span><a href="#l396"></a>
<span id="l397"></span><a href="#l397"></a>
<span id="l398">    @Override</span><a href="#l398"></a>
<span id="l399">    public int lastIndexOf(String str, int fromIndex) {</span><a href="#l399"></a>
<span id="l400">        return super.lastIndexOf(str, fromIndex);</span><a href="#l400"></a>
<span id="l401">    }</span><a href="#l401"></a>
<span id="l402"></span><a href="#l402"></a>
<span id="l403">    @Override</span><a href="#l403"></a>
<span id="l404">    public StringBuilder reverse() {</span><a href="#l404"></a>
<span id="l405">        super.reverse();</span><a href="#l405"></a>
<span id="l406">        return this;</span><a href="#l406"></a>
<span id="l407">    }</span><a href="#l407"></a>
<span id="l408"></span><a href="#l408"></a>
<span id="l409">    @Override</span><a href="#l409"></a>
<span id="l410">    public String toString() {</span><a href="#l410"></a>
<span id="l411">        // Create a copy, don't share the array</span><a href="#l411"></a>
<span id="l412">        return new String(value, 0, count);</span><a href="#l412"></a>
<span id="l413">    }</span><a href="#l413"></a>
<span id="l414"></span><a href="#l414"></a>
<span id="l415">    /**</span><a href="#l415"></a>
<span id="l416">     * Save the state of the {@code StringBuilder} instance to a stream</span><a href="#l416"></a>
<span id="l417">     * (that is, serialize it).</span><a href="#l417"></a>
<span id="l418">     *</span><a href="#l418"></a>
<span id="l419">     * @serialData the number of characters currently stored in the string</span><a href="#l419"></a>
<span id="l420">     *             builder ({@code int}), followed by the characters in the</span><a href="#l420"></a>
<span id="l421">     *             string builder ({@code char[]}).   The length of the</span><a href="#l421"></a>
<span id="l422">     *             {@code char} array may be greater than the number of</span><a href="#l422"></a>
<span id="l423">     *             characters currently stored in the string builder, in which</span><a href="#l423"></a>
<span id="l424">     *             case extra characters are ignored.</span><a href="#l424"></a>
<span id="l425">     */</span><a href="#l425"></a>
<span id="l426">    private void writeObject(ObjectOutputStream s)</span><a href="#l426"></a>
<span id="l427">        throws IOException {</span><a href="#l427"></a>
<span id="l428">        s.defaultWriteObject();</span><a href="#l428"></a>
<span id="l429">        s.writeInt(count);</span><a href="#l429"></a>
<span id="l430">        s.writeObject(value);</span><a href="#l430"></a>
<span id="l431">    }</span><a href="#l431"></a>
<span id="l432"></span><a href="#l432"></a>
<span id="l433">    /**</span><a href="#l433"></a>
<span id="l434">     * readObject is called to restore the state of the StringBuilder from</span><a href="#l434"></a>
<span id="l435">     * a stream.</span><a href="#l435"></a>
<span id="l436">     */</span><a href="#l436"></a>
<span id="l437">    private void readObject(ObjectInputStream s)</span><a href="#l437"></a>
<span id="l438">        throws IOException, ClassNotFoundException {</span><a href="#l438"></a>
<span id="l439">        s.defaultReadObject();</span><a href="#l439"></a>
<span id="l440">        int c = s.readInt();</span><a href="#l440"></a>
<span id="l441">        value = (char[]) s.readObject();</span><a href="#l441"></a>
<span id="l442">        if (c &lt; 0 || c &gt; value.length) {</span><a href="#l442"></a>
<span id="l443">            throw new StreamCorruptedException(&quot;count value invalid&quot;);</span><a href="#l443"></a>
<span id="l444">        }</span><a href="#l444"></a>
<span id="l445">        count = c;</span><a href="#l445"></a>
<span id="l446">    }</span><a href="#l446"></a>
<span id="l447"></span><a href="#l447"></a>
<span id="l448">}</span><a href="#l448"></a></pre>
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

