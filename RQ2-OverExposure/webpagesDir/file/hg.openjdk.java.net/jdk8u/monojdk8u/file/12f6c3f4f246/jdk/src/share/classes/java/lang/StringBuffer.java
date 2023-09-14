<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/monojdk8u/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/monojdk8u/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/monojdk8u/static/mercurial.js"></script>

<title>jdk8u/monojdk8u: 12f6c3f4f246 jdk/src/share/classes/java/lang/StringBuffer.java</title>
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
<li><a href="/jdk8u/monojdk8u/file/tip/jdk/src/share/classes/java/lang/StringBuffer.java">latest</a></li>
<li><a href="/jdk8u/monojdk8u/diff/12f6c3f4f246/jdk/src/share/classes/java/lang/StringBuffer.java">diff</a></li>
<li><a href="/jdk8u/monojdk8u/comparison/12f6c3f4f246/jdk/src/share/classes/java/lang/StringBuffer.java">comparison</a></li>
<li><a href="/jdk8u/monojdk8u/annotate/12f6c3f4f246/jdk/src/share/classes/java/lang/StringBuffer.java">annotate</a></li>
<li><a href="/jdk8u/monojdk8u/log/12f6c3f4f246/jdk/src/share/classes/java/lang/StringBuffer.java">file log</a></li>
<li><a href="/jdk8u/monojdk8u/raw-file/12f6c3f4f246/jdk/src/share/classes/java/lang/StringBuffer.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/monojdk8u/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/monojdk8u">monojdk8u</a> </h2>
<h3>view jdk/src/share/classes/java/lang/StringBuffer.java @ 48809:12f6c3f4f246</h3>

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
 <td class="author"><a href="/jdk8u/monojdk8u/file/865ba171224d/jdk/src/share/classes/java/lang/StringBuffer.java">865ba171224d</a> </td>
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
<span id="l2"> * Copyright (c) 1994, 2021, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l31">import java.io.ObjectStreamField;</span><a href="#l31"></a>
<span id="l32">import java.io.Serializable;</span><a href="#l32"></a>
<span id="l33">import java.io.StreamCorruptedException;</span><a href="#l33"></a>
<span id="l34">import java.util.Arrays;</span><a href="#l34"></a>
<span id="l35"></span><a href="#l35"></a>
<span id="l36">/**</span><a href="#l36"></a>
<span id="l37"> * A thread-safe, mutable sequence of characters.</span><a href="#l37"></a>
<span id="l38"> * A string buffer is like a {@link String}, but can be modified. At any</span><a href="#l38"></a>
<span id="l39"> * point in time it contains some particular sequence of characters, but</span><a href="#l39"></a>
<span id="l40"> * the length and content of the sequence can be changed through certain</span><a href="#l40"></a>
<span id="l41"> * method calls.</span><a href="#l41"></a>
<span id="l42"> * &lt;p&gt;</span><a href="#l42"></a>
<span id="l43"> * String buffers are safe for use by multiple threads. The methods</span><a href="#l43"></a>
<span id="l44"> * are synchronized where necessary so that all the operations on any</span><a href="#l44"></a>
<span id="l45"> * particular instance behave as if they occur in some serial order</span><a href="#l45"></a>
<span id="l46"> * that is consistent with the order of the method calls made by each of</span><a href="#l46"></a>
<span id="l47"> * the individual threads involved.</span><a href="#l47"></a>
<span id="l48"> * &lt;p&gt;</span><a href="#l48"></a>
<span id="l49"> * The principal operations on a {@code StringBuffer} are the</span><a href="#l49"></a>
<span id="l50"> * {@code append} and {@code insert} methods, which are</span><a href="#l50"></a>
<span id="l51"> * overloaded so as to accept data of any type. Each effectively</span><a href="#l51"></a>
<span id="l52"> * converts a given datum to a string and then appends or inserts the</span><a href="#l52"></a>
<span id="l53"> * characters of that string to the string buffer. The</span><a href="#l53"></a>
<span id="l54"> * {@code append} method always adds these characters at the end</span><a href="#l54"></a>
<span id="l55"> * of the buffer; the {@code insert} method adds the characters at</span><a href="#l55"></a>
<span id="l56"> * a specified point.</span><a href="#l56"></a>
<span id="l57"> * &lt;p&gt;</span><a href="#l57"></a>
<span id="l58"> * For example, if {@code z} refers to a string buffer object</span><a href="#l58"></a>
<span id="l59"> * whose current contents are {@code &quot;start&quot;}, then</span><a href="#l59"></a>
<span id="l60"> * the method call {@code z.append(&quot;le&quot;)} would cause the string</span><a href="#l60"></a>
<span id="l61"> * buffer to contain {@code &quot;startle&quot;}, whereas</span><a href="#l61"></a>
<span id="l62"> * {@code z.insert(4, &quot;le&quot;)} would alter the string buffer to</span><a href="#l62"></a>
<span id="l63"> * contain {@code &quot;starlet&quot;}.</span><a href="#l63"></a>
<span id="l64"> * &lt;p&gt;</span><a href="#l64"></a>
<span id="l65"> * In general, if sb refers to an instance of a {@code StringBuffer},</span><a href="#l65"></a>
<span id="l66"> * then {@code sb.append(x)} has the same effect as</span><a href="#l66"></a>
<span id="l67"> * {@code sb.insert(sb.length(), x)}.</span><a href="#l67"></a>
<span id="l68"> * &lt;p&gt;</span><a href="#l68"></a>
<span id="l69"> * Whenever an operation occurs involving a source sequence (such as</span><a href="#l69"></a>
<span id="l70"> * appending or inserting from a source sequence), this class synchronizes</span><a href="#l70"></a>
<span id="l71"> * only on the string buffer performing the operation, not on the source.</span><a href="#l71"></a>
<span id="l72"> * Note that while {@code StringBuffer} is designed to be safe to use</span><a href="#l72"></a>
<span id="l73"> * concurrently from multiple threads, if the constructor or the</span><a href="#l73"></a>
<span id="l74"> * {@code append} or {@code insert} operation is passed a source sequence</span><a href="#l74"></a>
<span id="l75"> * that is shared across threads, the calling code must ensure</span><a href="#l75"></a>
<span id="l76"> * that the operation has a consistent and unchanging view of the source</span><a href="#l76"></a>
<span id="l77"> * sequence for the duration of the operation.</span><a href="#l77"></a>
<span id="l78"> * This could be satisfied by the caller holding a lock during the</span><a href="#l78"></a>
<span id="l79"> * operation's call, by using an immutable source sequence, or by not</span><a href="#l79"></a>
<span id="l80"> * sharing the source sequence across threads.</span><a href="#l80"></a>
<span id="l81"> * &lt;p&gt;</span><a href="#l81"></a>
<span id="l82"> * Every string buffer has a capacity. As long as the length of the</span><a href="#l82"></a>
<span id="l83"> * character sequence contained in the string buffer does not exceed</span><a href="#l83"></a>
<span id="l84"> * the capacity, it is not necessary to allocate a new internal</span><a href="#l84"></a>
<span id="l85"> * buffer array. If the internal buffer overflows, it is</span><a href="#l85"></a>
<span id="l86"> * automatically made larger.</span><a href="#l86"></a>
<span id="l87"> * &lt;p&gt;</span><a href="#l87"></a>
<span id="l88"> * Unless otherwise noted, passing a {@code null} argument to a constructor</span><a href="#l88"></a>
<span id="l89"> * or method in this class will cause a {@link NullPointerException} to be</span><a href="#l89"></a>
<span id="l90"> * thrown.</span><a href="#l90"></a>
<span id="l91"> * &lt;p&gt;</span><a href="#l91"></a>
<span id="l92"> * As of  release JDK 5, this class has been supplemented with an equivalent</span><a href="#l92"></a>
<span id="l93"> * class designed for use by a single thread, {@link StringBuilder}.  The</span><a href="#l93"></a>
<span id="l94"> * {@code StringBuilder} class should generally be used in preference to</span><a href="#l94"></a>
<span id="l95"> * this one, as it supports all of the same operations but it is faster, as</span><a href="#l95"></a>
<span id="l96"> * it performs no synchronization.</span><a href="#l96"></a>
<span id="l97"> *</span><a href="#l97"></a>
<span id="l98"> * @author      Arthur van Hoff</span><a href="#l98"></a>
<span id="l99"> * @see     java.lang.StringBuilder</span><a href="#l99"></a>
<span id="l100"> * @see     java.lang.String</span><a href="#l100"></a>
<span id="l101"> * @since   JDK1.0</span><a href="#l101"></a>
<span id="l102"> */</span><a href="#l102"></a>
<span id="l103"> public final class StringBuffer</span><a href="#l103"></a>
<span id="l104">    extends AbstractStringBuilder</span><a href="#l104"></a>
<span id="l105">    implements Serializable, CharSequence</span><a href="#l105"></a>
<span id="l106">{</span><a href="#l106"></a>
<span id="l107"></span><a href="#l107"></a>
<span id="l108">    /**</span><a href="#l108"></a>
<span id="l109">     * A cache of the last value returned by toString. Cleared</span><a href="#l109"></a>
<span id="l110">     * whenever the StringBuffer is modified.</span><a href="#l110"></a>
<span id="l111">     */</span><a href="#l111"></a>
<span id="l112">    private transient char[] toStringCache;</span><a href="#l112"></a>
<span id="l113"></span><a href="#l113"></a>
<span id="l114">    /** use serialVersionUID from JDK 1.0.2 for interoperability */</span><a href="#l114"></a>
<span id="l115">    static final long serialVersionUID = 3388685877147921107L;</span><a href="#l115"></a>
<span id="l116"></span><a href="#l116"></a>
<span id="l117">    /**</span><a href="#l117"></a>
<span id="l118">     * Constructs a string buffer with no characters in it and an</span><a href="#l118"></a>
<span id="l119">     * initial capacity of 16 characters.</span><a href="#l119"></a>
<span id="l120">     */</span><a href="#l120"></a>
<span id="l121">    public StringBuffer() {</span><a href="#l121"></a>
<span id="l122">        super(16);</span><a href="#l122"></a>
<span id="l123">    }</span><a href="#l123"></a>
<span id="l124"></span><a href="#l124"></a>
<span id="l125">    /**</span><a href="#l125"></a>
<span id="l126">     * Constructs a string buffer with no characters in it and</span><a href="#l126"></a>
<span id="l127">     * the specified initial capacity.</span><a href="#l127"></a>
<span id="l128">     *</span><a href="#l128"></a>
<span id="l129">     * @param      capacity  the initial capacity.</span><a href="#l129"></a>
<span id="l130">     * @exception  NegativeArraySizeException  if the {@code capacity}</span><a href="#l130"></a>
<span id="l131">     *               argument is less than {@code 0}.</span><a href="#l131"></a>
<span id="l132">     */</span><a href="#l132"></a>
<span id="l133">    public StringBuffer(int capacity) {</span><a href="#l133"></a>
<span id="l134">        super(capacity);</span><a href="#l134"></a>
<span id="l135">    }</span><a href="#l135"></a>
<span id="l136"></span><a href="#l136"></a>
<span id="l137">    /**</span><a href="#l137"></a>
<span id="l138">     * Constructs a string buffer initialized to the contents of the</span><a href="#l138"></a>
<span id="l139">     * specified string. The initial capacity of the string buffer is</span><a href="#l139"></a>
<span id="l140">     * {@code 16} plus the length of the string argument.</span><a href="#l140"></a>
<span id="l141">     *</span><a href="#l141"></a>
<span id="l142">     * @param   str   the initial contents of the buffer.</span><a href="#l142"></a>
<span id="l143">     */</span><a href="#l143"></a>
<span id="l144">    public StringBuffer(String str) {</span><a href="#l144"></a>
<span id="l145">        super(str.length() + 16);</span><a href="#l145"></a>
<span id="l146">        append(str);</span><a href="#l146"></a>
<span id="l147">    }</span><a href="#l147"></a>
<span id="l148"></span><a href="#l148"></a>
<span id="l149">    /**</span><a href="#l149"></a>
<span id="l150">     * Constructs a string buffer that contains the same characters</span><a href="#l150"></a>
<span id="l151">     * as the specified {@code CharSequence}. The initial capacity of</span><a href="#l151"></a>
<span id="l152">     * the string buffer is {@code 16} plus the length of the</span><a href="#l152"></a>
<span id="l153">     * {@code CharSequence} argument.</span><a href="#l153"></a>
<span id="l154">     * &lt;p&gt;</span><a href="#l154"></a>
<span id="l155">     * If the length of the specified {@code CharSequence} is</span><a href="#l155"></a>
<span id="l156">     * less than or equal to zero, then an empty buffer of capacity</span><a href="#l156"></a>
<span id="l157">     * {@code 16} is returned.</span><a href="#l157"></a>
<span id="l158">     *</span><a href="#l158"></a>
<span id="l159">     * @param      seq   the sequence to copy.</span><a href="#l159"></a>
<span id="l160">     * @since 1.5</span><a href="#l160"></a>
<span id="l161">     */</span><a href="#l161"></a>
<span id="l162">    public StringBuffer(CharSequence seq) {</span><a href="#l162"></a>
<span id="l163">        this(seq.length() + 16);</span><a href="#l163"></a>
<span id="l164">        append(seq);</span><a href="#l164"></a>
<span id="l165">    }</span><a href="#l165"></a>
<span id="l166"></span><a href="#l166"></a>
<span id="l167">    @Override</span><a href="#l167"></a>
<span id="l168">    public synchronized int length() {</span><a href="#l168"></a>
<span id="l169">        return count;</span><a href="#l169"></a>
<span id="l170">    }</span><a href="#l170"></a>
<span id="l171"></span><a href="#l171"></a>
<span id="l172">    @Override</span><a href="#l172"></a>
<span id="l173">    public synchronized int capacity() {</span><a href="#l173"></a>
<span id="l174">        return value.length;</span><a href="#l174"></a>
<span id="l175">    }</span><a href="#l175"></a>
<span id="l176"></span><a href="#l176"></a>
<span id="l177"></span><a href="#l177"></a>
<span id="l178">    @Override</span><a href="#l178"></a>
<span id="l179">    public synchronized void ensureCapacity(int minimumCapacity) {</span><a href="#l179"></a>
<span id="l180">        super.ensureCapacity(minimumCapacity);</span><a href="#l180"></a>
<span id="l181">    }</span><a href="#l181"></a>
<span id="l182"></span><a href="#l182"></a>
<span id="l183">    /**</span><a href="#l183"></a>
<span id="l184">     * @since      1.5</span><a href="#l184"></a>
<span id="l185">     */</span><a href="#l185"></a>
<span id="l186">    @Override</span><a href="#l186"></a>
<span id="l187">    public synchronized void trimToSize() {</span><a href="#l187"></a>
<span id="l188">        super.trimToSize();</span><a href="#l188"></a>
<span id="l189">    }</span><a href="#l189"></a>
<span id="l190"></span><a href="#l190"></a>
<span id="l191">    /**</span><a href="#l191"></a>
<span id="l192">     * @throws IndexOutOfBoundsException {@inheritDoc}</span><a href="#l192"></a>
<span id="l193">     * @see        #length()</span><a href="#l193"></a>
<span id="l194">     */</span><a href="#l194"></a>
<span id="l195">    @Override</span><a href="#l195"></a>
<span id="l196">    public synchronized void setLength(int newLength) {</span><a href="#l196"></a>
<span id="l197">        toStringCache = null;</span><a href="#l197"></a>
<span id="l198">        super.setLength(newLength);</span><a href="#l198"></a>
<span id="l199">    }</span><a href="#l199"></a>
<span id="l200"></span><a href="#l200"></a>
<span id="l201">    /**</span><a href="#l201"></a>
<span id="l202">     * @throws IndexOutOfBoundsException {@inheritDoc}</span><a href="#l202"></a>
<span id="l203">     * @see        #length()</span><a href="#l203"></a>
<span id="l204">     */</span><a href="#l204"></a>
<span id="l205">    @Override</span><a href="#l205"></a>
<span id="l206">    public synchronized char charAt(int index) {</span><a href="#l206"></a>
<span id="l207">        if ((index &lt; 0) || (index &gt;= count))</span><a href="#l207"></a>
<span id="l208">            throw new StringIndexOutOfBoundsException(index);</span><a href="#l208"></a>
<span id="l209">        return value[index];</span><a href="#l209"></a>
<span id="l210">    }</span><a href="#l210"></a>
<span id="l211"></span><a href="#l211"></a>
<span id="l212">    /**</span><a href="#l212"></a>
<span id="l213">     * @since      1.5</span><a href="#l213"></a>
<span id="l214">     */</span><a href="#l214"></a>
<span id="l215">    @Override</span><a href="#l215"></a>
<span id="l216">    public synchronized int codePointAt(int index) {</span><a href="#l216"></a>
<span id="l217">        return super.codePointAt(index);</span><a href="#l217"></a>
<span id="l218">    }</span><a href="#l218"></a>
<span id="l219"></span><a href="#l219"></a>
<span id="l220">    /**</span><a href="#l220"></a>
<span id="l221">     * @since     1.5</span><a href="#l221"></a>
<span id="l222">     */</span><a href="#l222"></a>
<span id="l223">    @Override</span><a href="#l223"></a>
<span id="l224">    public synchronized int codePointBefore(int index) {</span><a href="#l224"></a>
<span id="l225">        return super.codePointBefore(index);</span><a href="#l225"></a>
<span id="l226">    }</span><a href="#l226"></a>
<span id="l227"></span><a href="#l227"></a>
<span id="l228">    /**</span><a href="#l228"></a>
<span id="l229">     * @since     1.5</span><a href="#l229"></a>
<span id="l230">     */</span><a href="#l230"></a>
<span id="l231">    @Override</span><a href="#l231"></a>
<span id="l232">    public synchronized int codePointCount(int beginIndex, int endIndex) {</span><a href="#l232"></a>
<span id="l233">        return super.codePointCount(beginIndex, endIndex);</span><a href="#l233"></a>
<span id="l234">    }</span><a href="#l234"></a>
<span id="l235"></span><a href="#l235"></a>
<span id="l236">    /**</span><a href="#l236"></a>
<span id="l237">     * @since     1.5</span><a href="#l237"></a>
<span id="l238">     */</span><a href="#l238"></a>
<span id="l239">    @Override</span><a href="#l239"></a>
<span id="l240">    public synchronized int offsetByCodePoints(int index, int codePointOffset) {</span><a href="#l240"></a>
<span id="l241">        return super.offsetByCodePoints(index, codePointOffset);</span><a href="#l241"></a>
<span id="l242">    }</span><a href="#l242"></a>
<span id="l243"></span><a href="#l243"></a>
<span id="l244">    /**</span><a href="#l244"></a>
<span id="l245">     * @throws IndexOutOfBoundsException {@inheritDoc}</span><a href="#l245"></a>
<span id="l246">     */</span><a href="#l246"></a>
<span id="l247">    @Override</span><a href="#l247"></a>
<span id="l248">    public synchronized void getChars(int srcBegin, int srcEnd, char[] dst,</span><a href="#l248"></a>
<span id="l249">                                      int dstBegin)</span><a href="#l249"></a>
<span id="l250">    {</span><a href="#l250"></a>
<span id="l251">        super.getChars(srcBegin, srcEnd, dst, dstBegin);</span><a href="#l251"></a>
<span id="l252">    }</span><a href="#l252"></a>
<span id="l253"></span><a href="#l253"></a>
<span id="l254">    /**</span><a href="#l254"></a>
<span id="l255">     * @throws IndexOutOfBoundsException {@inheritDoc}</span><a href="#l255"></a>
<span id="l256">     * @see        #length()</span><a href="#l256"></a>
<span id="l257">     */</span><a href="#l257"></a>
<span id="l258">    @Override</span><a href="#l258"></a>
<span id="l259">    public synchronized void setCharAt(int index, char ch) {</span><a href="#l259"></a>
<span id="l260">        if ((index &lt; 0) || (index &gt;= count))</span><a href="#l260"></a>
<span id="l261">            throw new StringIndexOutOfBoundsException(index);</span><a href="#l261"></a>
<span id="l262">        toStringCache = null;</span><a href="#l262"></a>
<span id="l263">        value[index] = ch;</span><a href="#l263"></a>
<span id="l264">    }</span><a href="#l264"></a>
<span id="l265"></span><a href="#l265"></a>
<span id="l266">    @Override</span><a href="#l266"></a>
<span id="l267">    public synchronized StringBuffer append(Object obj) {</span><a href="#l267"></a>
<span id="l268">        toStringCache = null;</span><a href="#l268"></a>
<span id="l269">        super.append(String.valueOf(obj));</span><a href="#l269"></a>
<span id="l270">        return this;</span><a href="#l270"></a>
<span id="l271">    }</span><a href="#l271"></a>
<span id="l272"></span><a href="#l272"></a>
<span id="l273">    @Override</span><a href="#l273"></a>
<span id="l274">    public synchronized StringBuffer append(String str) {</span><a href="#l274"></a>
<span id="l275">        toStringCache = null;</span><a href="#l275"></a>
<span id="l276">        super.append(str);</span><a href="#l276"></a>
<span id="l277">        return this;</span><a href="#l277"></a>
<span id="l278">    }</span><a href="#l278"></a>
<span id="l279"></span><a href="#l279"></a>
<span id="l280">    /**</span><a href="#l280"></a>
<span id="l281">     * Appends the specified {@code StringBuffer} to this sequence.</span><a href="#l281"></a>
<span id="l282">     * &lt;p&gt;</span><a href="#l282"></a>
<span id="l283">     * The characters of the {@code StringBuffer} argument are appended,</span><a href="#l283"></a>
<span id="l284">     * in order, to the contents of this {@code StringBuffer}, increasing the</span><a href="#l284"></a>
<span id="l285">     * length of this {@code StringBuffer} by the length of the argument.</span><a href="#l285"></a>
<span id="l286">     * If {@code sb} is {@code null}, then the four characters</span><a href="#l286"></a>
<span id="l287">     * {@code &quot;null&quot;} are appended to this {@code StringBuffer}.</span><a href="#l287"></a>
<span id="l288">     * &lt;p&gt;</span><a href="#l288"></a>
<span id="l289">     * Let &lt;i&gt;n&lt;/i&gt; be the length of the old character sequence, the one</span><a href="#l289"></a>
<span id="l290">     * contained in the {@code StringBuffer} just prior to execution of the</span><a href="#l290"></a>
<span id="l291">     * {@code append} method. Then the character at index &lt;i&gt;k&lt;/i&gt; in</span><a href="#l291"></a>
<span id="l292">     * the new character sequence is equal to the character at index &lt;i&gt;k&lt;/i&gt;</span><a href="#l292"></a>
<span id="l293">     * in the old character sequence, if &lt;i&gt;k&lt;/i&gt; is less than &lt;i&gt;n&lt;/i&gt;;</span><a href="#l293"></a>
<span id="l294">     * otherwise, it is equal to the character at index &lt;i&gt;k-n&lt;/i&gt; in the</span><a href="#l294"></a>
<span id="l295">     * argument {@code sb}.</span><a href="#l295"></a>
<span id="l296">     * &lt;p&gt;</span><a href="#l296"></a>
<span id="l297">     * This method synchronizes on {@code this}, the destination</span><a href="#l297"></a>
<span id="l298">     * object, but does not synchronize on the source ({@code sb}).</span><a href="#l298"></a>
<span id="l299">     *</span><a href="#l299"></a>
<span id="l300">     * @param   sb   the {@code StringBuffer} to append.</span><a href="#l300"></a>
<span id="l301">     * @return  a reference to this object.</span><a href="#l301"></a>
<span id="l302">     * @since 1.4</span><a href="#l302"></a>
<span id="l303">     */</span><a href="#l303"></a>
<span id="l304">    public synchronized StringBuffer append(StringBuffer sb) {</span><a href="#l304"></a>
<span id="l305">        toStringCache = null;</span><a href="#l305"></a>
<span id="l306">        super.append(sb);</span><a href="#l306"></a>
<span id="l307">        return this;</span><a href="#l307"></a>
<span id="l308">    }</span><a href="#l308"></a>
<span id="l309"></span><a href="#l309"></a>
<span id="l310">    /**</span><a href="#l310"></a>
<span id="l311">     * @since 1.8</span><a href="#l311"></a>
<span id="l312">     */</span><a href="#l312"></a>
<span id="l313">    @Override</span><a href="#l313"></a>
<span id="l314">    synchronized StringBuffer append(AbstractStringBuilder asb) {</span><a href="#l314"></a>
<span id="l315">        toStringCache = null;</span><a href="#l315"></a>
<span id="l316">        super.append(asb);</span><a href="#l316"></a>
<span id="l317">        return this;</span><a href="#l317"></a>
<span id="l318">    }</span><a href="#l318"></a>
<span id="l319"></span><a href="#l319"></a>
<span id="l320">    /**</span><a href="#l320"></a>
<span id="l321">     * Appends the specified {@code CharSequence} to this</span><a href="#l321"></a>
<span id="l322">     * sequence.</span><a href="#l322"></a>
<span id="l323">     * &lt;p&gt;</span><a href="#l323"></a>
<span id="l324">     * The characters of the {@code CharSequence} argument are appended,</span><a href="#l324"></a>
<span id="l325">     * in order, increasing the length of this sequence by the length of the</span><a href="#l325"></a>
<span id="l326">     * argument.</span><a href="#l326"></a>
<span id="l327">     *</span><a href="#l327"></a>
<span id="l328">     * &lt;p&gt;The result of this method is exactly the same as if it were an</span><a href="#l328"></a>
<span id="l329">     * invocation of this.append(s, 0, s.length());</span><a href="#l329"></a>
<span id="l330">     *</span><a href="#l330"></a>
<span id="l331">     * &lt;p&gt;This method synchronizes on {@code this}, the destination</span><a href="#l331"></a>
<span id="l332">     * object, but does not synchronize on the source ({@code s}).</span><a href="#l332"></a>
<span id="l333">     *</span><a href="#l333"></a>
<span id="l334">     * &lt;p&gt;If {@code s} is {@code null}, then the four characters</span><a href="#l334"></a>
<span id="l335">     * {@code &quot;null&quot;} are appended.</span><a href="#l335"></a>
<span id="l336">     *</span><a href="#l336"></a>
<span id="l337">     * @param   s the {@code CharSequence} to append.</span><a href="#l337"></a>
<span id="l338">     * @return  a reference to this object.</span><a href="#l338"></a>
<span id="l339">     * @since 1.5</span><a href="#l339"></a>
<span id="l340">     */</span><a href="#l340"></a>
<span id="l341">    @Override</span><a href="#l341"></a>
<span id="l342">    public synchronized StringBuffer append(CharSequence s) {</span><a href="#l342"></a>
<span id="l343">        toStringCache = null;</span><a href="#l343"></a>
<span id="l344">        super.append(s);</span><a href="#l344"></a>
<span id="l345">        return this;</span><a href="#l345"></a>
<span id="l346">    }</span><a href="#l346"></a>
<span id="l347"></span><a href="#l347"></a>
<span id="l348">    /**</span><a href="#l348"></a>
<span id="l349">     * @throws IndexOutOfBoundsException {@inheritDoc}</span><a href="#l349"></a>
<span id="l350">     * @since      1.5</span><a href="#l350"></a>
<span id="l351">     */</span><a href="#l351"></a>
<span id="l352">    @Override</span><a href="#l352"></a>
<span id="l353">    public synchronized StringBuffer append(CharSequence s, int start, int end)</span><a href="#l353"></a>
<span id="l354">    {</span><a href="#l354"></a>
<span id="l355">        toStringCache = null;</span><a href="#l355"></a>
<span id="l356">        super.append(s, start, end);</span><a href="#l356"></a>
<span id="l357">        return this;</span><a href="#l357"></a>
<span id="l358">    }</span><a href="#l358"></a>
<span id="l359"></span><a href="#l359"></a>
<span id="l360">    @Override</span><a href="#l360"></a>
<span id="l361">    public synchronized StringBuffer append(char[] str) {</span><a href="#l361"></a>
<span id="l362">        toStringCache = null;</span><a href="#l362"></a>
<span id="l363">        super.append(str);</span><a href="#l363"></a>
<span id="l364">        return this;</span><a href="#l364"></a>
<span id="l365">    }</span><a href="#l365"></a>
<span id="l366"></span><a href="#l366"></a>
<span id="l367">    /**</span><a href="#l367"></a>
<span id="l368">     * @throws IndexOutOfBoundsException {@inheritDoc}</span><a href="#l368"></a>
<span id="l369">     */</span><a href="#l369"></a>
<span id="l370">    @Override</span><a href="#l370"></a>
<span id="l371">    public synchronized StringBuffer append(char[] str, int offset, int len) {</span><a href="#l371"></a>
<span id="l372">        toStringCache = null;</span><a href="#l372"></a>
<span id="l373">        super.append(str, offset, len);</span><a href="#l373"></a>
<span id="l374">        return this;</span><a href="#l374"></a>
<span id="l375">    }</span><a href="#l375"></a>
<span id="l376"></span><a href="#l376"></a>
<span id="l377">    @Override</span><a href="#l377"></a>
<span id="l378">    public synchronized StringBuffer append(boolean b) {</span><a href="#l378"></a>
<span id="l379">        toStringCache = null;</span><a href="#l379"></a>
<span id="l380">        super.append(b);</span><a href="#l380"></a>
<span id="l381">        return this;</span><a href="#l381"></a>
<span id="l382">    }</span><a href="#l382"></a>
<span id="l383"></span><a href="#l383"></a>
<span id="l384">    @Override</span><a href="#l384"></a>
<span id="l385">    public synchronized StringBuffer append(char c) {</span><a href="#l385"></a>
<span id="l386">        toStringCache = null;</span><a href="#l386"></a>
<span id="l387">        super.append(c);</span><a href="#l387"></a>
<span id="l388">        return this;</span><a href="#l388"></a>
<span id="l389">    }</span><a href="#l389"></a>
<span id="l390"></span><a href="#l390"></a>
<span id="l391">    @Override</span><a href="#l391"></a>
<span id="l392">    public synchronized StringBuffer append(int i) {</span><a href="#l392"></a>
<span id="l393">        toStringCache = null;</span><a href="#l393"></a>
<span id="l394">        super.append(i);</span><a href="#l394"></a>
<span id="l395">        return this;</span><a href="#l395"></a>
<span id="l396">    }</span><a href="#l396"></a>
<span id="l397"></span><a href="#l397"></a>
<span id="l398">    /**</span><a href="#l398"></a>
<span id="l399">     * @since 1.5</span><a href="#l399"></a>
<span id="l400">     */</span><a href="#l400"></a>
<span id="l401">    @Override</span><a href="#l401"></a>
<span id="l402">    public synchronized StringBuffer appendCodePoint(int codePoint) {</span><a href="#l402"></a>
<span id="l403">        toStringCache = null;</span><a href="#l403"></a>
<span id="l404">        super.appendCodePoint(codePoint);</span><a href="#l404"></a>
<span id="l405">        return this;</span><a href="#l405"></a>
<span id="l406">    }</span><a href="#l406"></a>
<span id="l407"></span><a href="#l407"></a>
<span id="l408">    @Override</span><a href="#l408"></a>
<span id="l409">    public synchronized StringBuffer append(long lng) {</span><a href="#l409"></a>
<span id="l410">        toStringCache = null;</span><a href="#l410"></a>
<span id="l411">        super.append(lng);</span><a href="#l411"></a>
<span id="l412">        return this;</span><a href="#l412"></a>
<span id="l413">    }</span><a href="#l413"></a>
<span id="l414"></span><a href="#l414"></a>
<span id="l415">    @Override</span><a href="#l415"></a>
<span id="l416">    public synchronized StringBuffer append(float f) {</span><a href="#l416"></a>
<span id="l417">        toStringCache = null;</span><a href="#l417"></a>
<span id="l418">        super.append(f);</span><a href="#l418"></a>
<span id="l419">        return this;</span><a href="#l419"></a>
<span id="l420">    }</span><a href="#l420"></a>
<span id="l421"></span><a href="#l421"></a>
<span id="l422">    @Override</span><a href="#l422"></a>
<span id="l423">    public synchronized StringBuffer append(double d) {</span><a href="#l423"></a>
<span id="l424">        toStringCache = null;</span><a href="#l424"></a>
<span id="l425">        super.append(d);</span><a href="#l425"></a>
<span id="l426">        return this;</span><a href="#l426"></a>
<span id="l427">    }</span><a href="#l427"></a>
<span id="l428"></span><a href="#l428"></a>
<span id="l429">    /**</span><a href="#l429"></a>
<span id="l430">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l430"></a>
<span id="l431">     * @since      1.2</span><a href="#l431"></a>
<span id="l432">     */</span><a href="#l432"></a>
<span id="l433">    @Override</span><a href="#l433"></a>
<span id="l434">    public synchronized StringBuffer delete(int start, int end) {</span><a href="#l434"></a>
<span id="l435">        toStringCache = null;</span><a href="#l435"></a>
<span id="l436">        super.delete(start, end);</span><a href="#l436"></a>
<span id="l437">        return this;</span><a href="#l437"></a>
<span id="l438">    }</span><a href="#l438"></a>
<span id="l439"></span><a href="#l439"></a>
<span id="l440">    /**</span><a href="#l440"></a>
<span id="l441">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l441"></a>
<span id="l442">     * @since      1.2</span><a href="#l442"></a>
<span id="l443">     */</span><a href="#l443"></a>
<span id="l444">    @Override</span><a href="#l444"></a>
<span id="l445">    public synchronized StringBuffer deleteCharAt(int index) {</span><a href="#l445"></a>
<span id="l446">        toStringCache = null;</span><a href="#l446"></a>
<span id="l447">        super.deleteCharAt(index);</span><a href="#l447"></a>
<span id="l448">        return this;</span><a href="#l448"></a>
<span id="l449">    }</span><a href="#l449"></a>
<span id="l450"></span><a href="#l450"></a>
<span id="l451">    /**</span><a href="#l451"></a>
<span id="l452">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l452"></a>
<span id="l453">     * @since      1.2</span><a href="#l453"></a>
<span id="l454">     */</span><a href="#l454"></a>
<span id="l455">    @Override</span><a href="#l455"></a>
<span id="l456">    public synchronized StringBuffer replace(int start, int end, String str) {</span><a href="#l456"></a>
<span id="l457">        toStringCache = null;</span><a href="#l457"></a>
<span id="l458">        super.replace(start, end, str);</span><a href="#l458"></a>
<span id="l459">        return this;</span><a href="#l459"></a>
<span id="l460">    }</span><a href="#l460"></a>
<span id="l461"></span><a href="#l461"></a>
<span id="l462">    /**</span><a href="#l462"></a>
<span id="l463">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l463"></a>
<span id="l464">     * @since      1.2</span><a href="#l464"></a>
<span id="l465">     */</span><a href="#l465"></a>
<span id="l466">    @Override</span><a href="#l466"></a>
<span id="l467">    public synchronized String substring(int start) {</span><a href="#l467"></a>
<span id="l468">        return substring(start, count);</span><a href="#l468"></a>
<span id="l469">    }</span><a href="#l469"></a>
<span id="l470"></span><a href="#l470"></a>
<span id="l471">    /**</span><a href="#l471"></a>
<span id="l472">     * @throws IndexOutOfBoundsException {@inheritDoc}</span><a href="#l472"></a>
<span id="l473">     * @since      1.4</span><a href="#l473"></a>
<span id="l474">     */</span><a href="#l474"></a>
<span id="l475">    @Override</span><a href="#l475"></a>
<span id="l476">    public synchronized CharSequence subSequence(int start, int end) {</span><a href="#l476"></a>
<span id="l477">        return super.substring(start, end);</span><a href="#l477"></a>
<span id="l478">    }</span><a href="#l478"></a>
<span id="l479"></span><a href="#l479"></a>
<span id="l480">    /**</span><a href="#l480"></a>
<span id="l481">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l481"></a>
<span id="l482">     * @since      1.2</span><a href="#l482"></a>
<span id="l483">     */</span><a href="#l483"></a>
<span id="l484">    @Override</span><a href="#l484"></a>
<span id="l485">    public synchronized String substring(int start, int end) {</span><a href="#l485"></a>
<span id="l486">        return super.substring(start, end);</span><a href="#l486"></a>
<span id="l487">    }</span><a href="#l487"></a>
<span id="l488"></span><a href="#l488"></a>
<span id="l489">    /**</span><a href="#l489"></a>
<span id="l490">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l490"></a>
<span id="l491">     * @since      1.2</span><a href="#l491"></a>
<span id="l492">     */</span><a href="#l492"></a>
<span id="l493">    @Override</span><a href="#l493"></a>
<span id="l494">    public synchronized StringBuffer insert(int index, char[] str, int offset,</span><a href="#l494"></a>
<span id="l495">                                            int len)</span><a href="#l495"></a>
<span id="l496">    {</span><a href="#l496"></a>
<span id="l497">        toStringCache = null;</span><a href="#l497"></a>
<span id="l498">        super.insert(index, str, offset, len);</span><a href="#l498"></a>
<span id="l499">        return this;</span><a href="#l499"></a>
<span id="l500">    }</span><a href="#l500"></a>
<span id="l501"></span><a href="#l501"></a>
<span id="l502">    /**</span><a href="#l502"></a>
<span id="l503">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l503"></a>
<span id="l504">     */</span><a href="#l504"></a>
<span id="l505">    @Override</span><a href="#l505"></a>
<span id="l506">    public synchronized StringBuffer insert(int offset, Object obj) {</span><a href="#l506"></a>
<span id="l507">        toStringCache = null;</span><a href="#l507"></a>
<span id="l508">        super.insert(offset, String.valueOf(obj));</span><a href="#l508"></a>
<span id="l509">        return this;</span><a href="#l509"></a>
<span id="l510">    }</span><a href="#l510"></a>
<span id="l511"></span><a href="#l511"></a>
<span id="l512">    /**</span><a href="#l512"></a>
<span id="l513">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l513"></a>
<span id="l514">     */</span><a href="#l514"></a>
<span id="l515">    @Override</span><a href="#l515"></a>
<span id="l516">    public synchronized StringBuffer insert(int offset, String str) {</span><a href="#l516"></a>
<span id="l517">        toStringCache = null;</span><a href="#l517"></a>
<span id="l518">        super.insert(offset, str);</span><a href="#l518"></a>
<span id="l519">        return this;</span><a href="#l519"></a>
<span id="l520">    }</span><a href="#l520"></a>
<span id="l521"></span><a href="#l521"></a>
<span id="l522">    /**</span><a href="#l522"></a>
<span id="l523">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l523"></a>
<span id="l524">     */</span><a href="#l524"></a>
<span id="l525">    @Override</span><a href="#l525"></a>
<span id="l526">    public synchronized StringBuffer insert(int offset, char[] str) {</span><a href="#l526"></a>
<span id="l527">        toStringCache = null;</span><a href="#l527"></a>
<span id="l528">        super.insert(offset, str);</span><a href="#l528"></a>
<span id="l529">        return this;</span><a href="#l529"></a>
<span id="l530">    }</span><a href="#l530"></a>
<span id="l531"></span><a href="#l531"></a>
<span id="l532">    /**</span><a href="#l532"></a>
<span id="l533">     * @throws IndexOutOfBoundsException {@inheritDoc}</span><a href="#l533"></a>
<span id="l534">     * @since      1.5</span><a href="#l534"></a>
<span id="l535">     */</span><a href="#l535"></a>
<span id="l536">    @Override</span><a href="#l536"></a>
<span id="l537">    public StringBuffer insert(int dstOffset, CharSequence s) {</span><a href="#l537"></a>
<span id="l538">        // Note, synchronization achieved via invocations of other StringBuffer methods</span><a href="#l538"></a>
<span id="l539">        // after narrowing of s to specific type</span><a href="#l539"></a>
<span id="l540">        // Ditto for toStringCache clearing</span><a href="#l540"></a>
<span id="l541">        super.insert(dstOffset, s);</span><a href="#l541"></a>
<span id="l542">        return this;</span><a href="#l542"></a>
<span id="l543">    }</span><a href="#l543"></a>
<span id="l544"></span><a href="#l544"></a>
<span id="l545">    /**</span><a href="#l545"></a>
<span id="l546">     * @throws IndexOutOfBoundsException {@inheritDoc}</span><a href="#l546"></a>
<span id="l547">     * @since      1.5</span><a href="#l547"></a>
<span id="l548">     */</span><a href="#l548"></a>
<span id="l549">    @Override</span><a href="#l549"></a>
<span id="l550">    public synchronized StringBuffer insert(int dstOffset, CharSequence s,</span><a href="#l550"></a>
<span id="l551">            int start, int end)</span><a href="#l551"></a>
<span id="l552">    {</span><a href="#l552"></a>
<span id="l553">        toStringCache = null;</span><a href="#l553"></a>
<span id="l554">        super.insert(dstOffset, s, start, end);</span><a href="#l554"></a>
<span id="l555">        return this;</span><a href="#l555"></a>
<span id="l556">    }</span><a href="#l556"></a>
<span id="l557"></span><a href="#l557"></a>
<span id="l558">    /**</span><a href="#l558"></a>
<span id="l559">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l559"></a>
<span id="l560">     */</span><a href="#l560"></a>
<span id="l561">    @Override</span><a href="#l561"></a>
<span id="l562">    public  StringBuffer insert(int offset, boolean b) {</span><a href="#l562"></a>
<span id="l563">        // Note, synchronization achieved via invocation of StringBuffer insert(int, String)</span><a href="#l563"></a>
<span id="l564">        // after conversion of b to String by super class method</span><a href="#l564"></a>
<span id="l565">        // Ditto for toStringCache clearing</span><a href="#l565"></a>
<span id="l566">        super.insert(offset, b);</span><a href="#l566"></a>
<span id="l567">        return this;</span><a href="#l567"></a>
<span id="l568">    }</span><a href="#l568"></a>
<span id="l569"></span><a href="#l569"></a>
<span id="l570">    /**</span><a href="#l570"></a>
<span id="l571">     * @throws IndexOutOfBoundsException {@inheritDoc}</span><a href="#l571"></a>
<span id="l572">     */</span><a href="#l572"></a>
<span id="l573">    @Override</span><a href="#l573"></a>
<span id="l574">    public synchronized StringBuffer insert(int offset, char c) {</span><a href="#l574"></a>
<span id="l575">        toStringCache = null;</span><a href="#l575"></a>
<span id="l576">        super.insert(offset, c);</span><a href="#l576"></a>
<span id="l577">        return this;</span><a href="#l577"></a>
<span id="l578">    }</span><a href="#l578"></a>
<span id="l579"></span><a href="#l579"></a>
<span id="l580">    /**</span><a href="#l580"></a>
<span id="l581">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l581"></a>
<span id="l582">     */</span><a href="#l582"></a>
<span id="l583">    @Override</span><a href="#l583"></a>
<span id="l584">    public StringBuffer insert(int offset, int i) {</span><a href="#l584"></a>
<span id="l585">        // Note, synchronization achieved via invocation of StringBuffer insert(int, String)</span><a href="#l585"></a>
<span id="l586">        // after conversion of i to String by super class method</span><a href="#l586"></a>
<span id="l587">        // Ditto for toStringCache clearing</span><a href="#l587"></a>
<span id="l588">        super.insert(offset, i);</span><a href="#l588"></a>
<span id="l589">        return this;</span><a href="#l589"></a>
<span id="l590">    }</span><a href="#l590"></a>
<span id="l591"></span><a href="#l591"></a>
<span id="l592">    /**</span><a href="#l592"></a>
<span id="l593">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l593"></a>
<span id="l594">     */</span><a href="#l594"></a>
<span id="l595">    @Override</span><a href="#l595"></a>
<span id="l596">    public StringBuffer insert(int offset, long l) {</span><a href="#l596"></a>
<span id="l597">        // Note, synchronization achieved via invocation of StringBuffer insert(int, String)</span><a href="#l597"></a>
<span id="l598">        // after conversion of l to String by super class method</span><a href="#l598"></a>
<span id="l599">        // Ditto for toStringCache clearing</span><a href="#l599"></a>
<span id="l600">        super.insert(offset, l);</span><a href="#l600"></a>
<span id="l601">        return this;</span><a href="#l601"></a>
<span id="l602">    }</span><a href="#l602"></a>
<span id="l603"></span><a href="#l603"></a>
<span id="l604">    /**</span><a href="#l604"></a>
<span id="l605">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l605"></a>
<span id="l606">     */</span><a href="#l606"></a>
<span id="l607">    @Override</span><a href="#l607"></a>
<span id="l608">    public StringBuffer insert(int offset, float f) {</span><a href="#l608"></a>
<span id="l609">        // Note, synchronization achieved via invocation of StringBuffer insert(int, String)</span><a href="#l609"></a>
<span id="l610">        // after conversion of f to String by super class method</span><a href="#l610"></a>
<span id="l611">        // Ditto for toStringCache clearing</span><a href="#l611"></a>
<span id="l612">        super.insert(offset, f);</span><a href="#l612"></a>
<span id="l613">        return this;</span><a href="#l613"></a>
<span id="l614">    }</span><a href="#l614"></a>
<span id="l615"></span><a href="#l615"></a>
<span id="l616">    /**</span><a href="#l616"></a>
<span id="l617">     * @throws StringIndexOutOfBoundsException {@inheritDoc}</span><a href="#l617"></a>
<span id="l618">     */</span><a href="#l618"></a>
<span id="l619">    @Override</span><a href="#l619"></a>
<span id="l620">    public StringBuffer insert(int offset, double d) {</span><a href="#l620"></a>
<span id="l621">        // Note, synchronization achieved via invocation of StringBuffer insert(int, String)</span><a href="#l621"></a>
<span id="l622">        // after conversion of d to String by super class method</span><a href="#l622"></a>
<span id="l623">        // Ditto for toStringCache clearing</span><a href="#l623"></a>
<span id="l624">        super.insert(offset, d);</span><a href="#l624"></a>
<span id="l625">        return this;</span><a href="#l625"></a>
<span id="l626">    }</span><a href="#l626"></a>
<span id="l627"></span><a href="#l627"></a>
<span id="l628">    /**</span><a href="#l628"></a>
<span id="l629">     * @since      1.4</span><a href="#l629"></a>
<span id="l630">     */</span><a href="#l630"></a>
<span id="l631">    @Override</span><a href="#l631"></a>
<span id="l632">    public int indexOf(String str) {</span><a href="#l632"></a>
<span id="l633">        // Note, synchronization achieved via invocations of other StringBuffer methods</span><a href="#l633"></a>
<span id="l634">        return super.indexOf(str);</span><a href="#l634"></a>
<span id="l635">    }</span><a href="#l635"></a>
<span id="l636"></span><a href="#l636"></a>
<span id="l637">    /**</span><a href="#l637"></a>
<span id="l638">     * @since      1.4</span><a href="#l638"></a>
<span id="l639">     */</span><a href="#l639"></a>
<span id="l640">    @Override</span><a href="#l640"></a>
<span id="l641">    public synchronized int indexOf(String str, int fromIndex) {</span><a href="#l641"></a>
<span id="l642">        return super.indexOf(str, fromIndex);</span><a href="#l642"></a>
<span id="l643">    }</span><a href="#l643"></a>
<span id="l644"></span><a href="#l644"></a>
<span id="l645">    /**</span><a href="#l645"></a>
<span id="l646">     * @since      1.4</span><a href="#l646"></a>
<span id="l647">     */</span><a href="#l647"></a>
<span id="l648">    @Override</span><a href="#l648"></a>
<span id="l649">    public int lastIndexOf(String str) {</span><a href="#l649"></a>
<span id="l650">        // Note, synchronization achieved via invocations of other StringBuffer methods</span><a href="#l650"></a>
<span id="l651">        return lastIndexOf(str, count);</span><a href="#l651"></a>
<span id="l652">    }</span><a href="#l652"></a>
<span id="l653"></span><a href="#l653"></a>
<span id="l654">    /**</span><a href="#l654"></a>
<span id="l655">     * @since      1.4</span><a href="#l655"></a>
<span id="l656">     */</span><a href="#l656"></a>
<span id="l657">    @Override</span><a href="#l657"></a>
<span id="l658">    public synchronized int lastIndexOf(String str, int fromIndex) {</span><a href="#l658"></a>
<span id="l659">        return super.lastIndexOf(str, fromIndex);</span><a href="#l659"></a>
<span id="l660">    }</span><a href="#l660"></a>
<span id="l661"></span><a href="#l661"></a>
<span id="l662">    /**</span><a href="#l662"></a>
<span id="l663">     * @since   JDK1.0.2</span><a href="#l663"></a>
<span id="l664">     */</span><a href="#l664"></a>
<span id="l665">    @Override</span><a href="#l665"></a>
<span id="l666">    public synchronized StringBuffer reverse() {</span><a href="#l666"></a>
<span id="l667">        toStringCache = null;</span><a href="#l667"></a>
<span id="l668">        super.reverse();</span><a href="#l668"></a>
<span id="l669">        return this;</span><a href="#l669"></a>
<span id="l670">    }</span><a href="#l670"></a>
<span id="l671"></span><a href="#l671"></a>
<span id="l672">    @Override</span><a href="#l672"></a>
<span id="l673">    public synchronized String toString() {</span><a href="#l673"></a>
<span id="l674">        if (toStringCache == null) {</span><a href="#l674"></a>
<span id="l675">            toStringCache = Arrays.copyOfRange(value, 0, count);</span><a href="#l675"></a>
<span id="l676">        }</span><a href="#l676"></a>
<span id="l677">        return new String(toStringCache, true);</span><a href="#l677"></a>
<span id="l678">    }</span><a href="#l678"></a>
<span id="l679"></span><a href="#l679"></a>
<span id="l680">    /**</span><a href="#l680"></a>
<span id="l681">     * Serializable fields for StringBuffer.</span><a href="#l681"></a>
<span id="l682">     *</span><a href="#l682"></a>
<span id="l683">     * @serialField value  char[]</span><a href="#l683"></a>
<span id="l684">     *              The backing character array of this StringBuffer.</span><a href="#l684"></a>
<span id="l685">     * @serialField count int</span><a href="#l685"></a>
<span id="l686">     *              The number of characters in this StringBuffer.</span><a href="#l686"></a>
<span id="l687">     * @serialField shared  boolean</span><a href="#l687"></a>
<span id="l688">     *              A flag indicating whether the backing array is shared.</span><a href="#l688"></a>
<span id="l689">     *              The value is ignored upon deserialization.</span><a href="#l689"></a>
<span id="l690">     */</span><a href="#l690"></a>
<span id="l691">    private static final ObjectStreamField[] serialPersistentFields =</span><a href="#l691"></a>
<span id="l692">    {</span><a href="#l692"></a>
<span id="l693">        new ObjectStreamField(&quot;value&quot;, char[].class),</span><a href="#l693"></a>
<span id="l694">        new ObjectStreamField(&quot;count&quot;, Integer.TYPE),</span><a href="#l694"></a>
<span id="l695">        new ObjectStreamField(&quot;shared&quot;, Boolean.TYPE),</span><a href="#l695"></a>
<span id="l696">    };</span><a href="#l696"></a>
<span id="l697"></span><a href="#l697"></a>
<span id="l698">    /**</span><a href="#l698"></a>
<span id="l699">     * The {@code writeObject} method is called to write the state of the</span><a href="#l699"></a>
<span id="l700">     * {@code StringBuffer} to a stream.</span><a href="#l700"></a>
<span id="l701">     */</span><a href="#l701"></a>
<span id="l702">    private synchronized void writeObject(ObjectOutputStream s)</span><a href="#l702"></a>
<span id="l703">        throws IOException {</span><a href="#l703"></a>
<span id="l704">        ObjectOutputStream.PutField fields = s.putFields();</span><a href="#l704"></a>
<span id="l705">        fields.put(&quot;value&quot;, value);</span><a href="#l705"></a>
<span id="l706">        fields.put(&quot;count&quot;, count);</span><a href="#l706"></a>
<span id="l707">        fields.put(&quot;shared&quot;, false);</span><a href="#l707"></a>
<span id="l708">        s.writeFields();</span><a href="#l708"></a>
<span id="l709">    }</span><a href="#l709"></a>
<span id="l710"></span><a href="#l710"></a>
<span id="l711">    /**</span><a href="#l711"></a>
<span id="l712">     * The {@code readObject} method is called to restore the state of the</span><a href="#l712"></a>
<span id="l713">     * {@code StringBuffer} from a stream.</span><a href="#l713"></a>
<span id="l714">     */</span><a href="#l714"></a>
<span id="l715">    private void readObject(ObjectInputStream s)</span><a href="#l715"></a>
<span id="l716">        throws IOException, ClassNotFoundException {</span><a href="#l716"></a>
<span id="l717">        ObjectInputStream.GetField fields = s.readFields();</span><a href="#l717"></a>
<span id="l718">        value = (char[])fields.get(&quot;value&quot;, null);</span><a href="#l718"></a>
<span id="l719">        int c = fields.get(&quot;count&quot;, 0);</span><a href="#l719"></a>
<span id="l720">        if (c &lt; 0 || c &gt; value.length) {</span><a href="#l720"></a>
<span id="l721">            throw new StreamCorruptedException(&quot;count value invalid&quot;);</span><a href="#l721"></a>
<span id="l722">        }</span><a href="#l722"></a>
<span id="l723">        count = c;</span><a href="#l723"></a>
<span id="l724">    }</span><a href="#l724"></a>
<span id="l725">}</span><a href="#l725"></a></pre>
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

