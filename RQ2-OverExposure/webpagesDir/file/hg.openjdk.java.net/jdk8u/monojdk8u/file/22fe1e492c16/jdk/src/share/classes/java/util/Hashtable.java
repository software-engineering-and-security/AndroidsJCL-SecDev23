<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/monojdk8u/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/monojdk8u/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/monojdk8u/static/mercurial.js"></script>

<title>jdk8u/monojdk8u: 22fe1e492c16 jdk/src/share/classes/java/util/Hashtable.java</title>
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
<li><a href="/jdk8u/monojdk8u/file/22fe1e492c16/jdk/src/share/classes/java/util/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/monojdk8u/file/tip/jdk/src/share/classes/java/util/Hashtable.java">latest</a></li>
<li><a href="/jdk8u/monojdk8u/diff/22fe1e492c16/jdk/src/share/classes/java/util/Hashtable.java">diff</a></li>
<li><a href="/jdk8u/monojdk8u/comparison/22fe1e492c16/jdk/src/share/classes/java/util/Hashtable.java">comparison</a></li>
<li><a href="/jdk8u/monojdk8u/annotate/22fe1e492c16/jdk/src/share/classes/java/util/Hashtable.java">annotate</a></li>
<li><a href="/jdk8u/monojdk8u/log/22fe1e492c16/jdk/src/share/classes/java/util/Hashtable.java">file log</a></li>
<li><a href="/jdk8u/monojdk8u/raw-file/22fe1e492c16/jdk/src/share/classes/java/util/Hashtable.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/monojdk8u/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/monojdk8u">monojdk8u</a> </h2>
<h3>view jdk/src/share/classes/java/util/Hashtable.java @ 48810:22fe1e492c16</h3>

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
 <td class="author"><a href="/jdk8u/monojdk8u/file/683620104f23/jdk/src/share/classes/java/util/Hashtable.java">683620104f23</a> </td>
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
<span id="l26">package java.util;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.io.*;</span><a href="#l28"></a>
<span id="l29">import java.util.concurrent.ThreadLocalRandom;</span><a href="#l29"></a>
<span id="l30">import java.util.function.BiConsumer;</span><a href="#l30"></a>
<span id="l31">import java.util.function.Function;</span><a href="#l31"></a>
<span id="l32">import java.util.function.BiFunction;</span><a href="#l32"></a>
<span id="l33">import sun.misc.SharedSecrets;</span><a href="#l33"></a>
<span id="l34"></span><a href="#l34"></a>
<span id="l35">/**</span><a href="#l35"></a>
<span id="l36"> * This class implements a hash table, which maps keys to values. Any</span><a href="#l36"></a>
<span id="l37"> * non-&lt;code&gt;null&lt;/code&gt; object can be used as a key or as a value. &lt;p&gt;</span><a href="#l37"></a>
<span id="l38"> *</span><a href="#l38"></a>
<span id="l39"> * To successfully store and retrieve objects from a hashtable, the</span><a href="#l39"></a>
<span id="l40"> * objects used as keys must implement the &lt;code&gt;hashCode&lt;/code&gt;</span><a href="#l40"></a>
<span id="l41"> * method and the &lt;code&gt;equals&lt;/code&gt; method. &lt;p&gt;</span><a href="#l41"></a>
<span id="l42"> *</span><a href="#l42"></a>
<span id="l43"> * An instance of &lt;code&gt;Hashtable&lt;/code&gt; has two parameters that affect its</span><a href="#l43"></a>
<span id="l44"> * performance: &lt;i&gt;initial capacity&lt;/i&gt; and &lt;i&gt;load factor&lt;/i&gt;.  The</span><a href="#l44"></a>
<span id="l45"> * &lt;i&gt;capacity&lt;/i&gt; is the number of &lt;i&gt;buckets&lt;/i&gt; in the hash table, and the</span><a href="#l45"></a>
<span id="l46"> * &lt;i&gt;initial capacity&lt;/i&gt; is simply the capacity at the time the hash table</span><a href="#l46"></a>
<span id="l47"> * is created.  Note that the hash table is &lt;i&gt;open&lt;/i&gt;: in the case of a &quot;hash</span><a href="#l47"></a>
<span id="l48"> * collision&quot;, a single bucket stores multiple entries, which must be searched</span><a href="#l48"></a>
<span id="l49"> * sequentially.  The &lt;i&gt;load factor&lt;/i&gt; is a measure of how full the hash</span><a href="#l49"></a>
<span id="l50"> * table is allowed to get before its capacity is automatically increased.</span><a href="#l50"></a>
<span id="l51"> * The initial capacity and load factor parameters are merely hints to</span><a href="#l51"></a>
<span id="l52"> * the implementation.  The exact details as to when and whether the rehash</span><a href="#l52"></a>
<span id="l53"> * method is invoked are implementation-dependent.&lt;p&gt;</span><a href="#l53"></a>
<span id="l54"> *</span><a href="#l54"></a>
<span id="l55"> * Generally, the default load factor (.75) offers a good tradeoff between</span><a href="#l55"></a>
<span id="l56"> * time and space costs.  Higher values decrease the space overhead but</span><a href="#l56"></a>
<span id="l57"> * increase the time cost to look up an entry (which is reflected in most</span><a href="#l57"></a>
<span id="l58"> * &lt;tt&gt;Hashtable&lt;/tt&gt; operations, including &lt;tt&gt;get&lt;/tt&gt; and &lt;tt&gt;put&lt;/tt&gt;).&lt;p&gt;</span><a href="#l58"></a>
<span id="l59"> *</span><a href="#l59"></a>
<span id="l60"> * The initial capacity controls a tradeoff between wasted space and the</span><a href="#l60"></a>
<span id="l61"> * need for &lt;code&gt;rehash&lt;/code&gt; operations, which are time-consuming.</span><a href="#l61"></a>
<span id="l62"> * No &lt;code&gt;rehash&lt;/code&gt; operations will &lt;i&gt;ever&lt;/i&gt; occur if the initial</span><a href="#l62"></a>
<span id="l63"> * capacity is greater than the maximum number of entries the</span><a href="#l63"></a>
<span id="l64"> * &lt;tt&gt;Hashtable&lt;/tt&gt; will contain divided by its load factor.  However,</span><a href="#l64"></a>
<span id="l65"> * setting the initial capacity too high can waste space.&lt;p&gt;</span><a href="#l65"></a>
<span id="l66"> *</span><a href="#l66"></a>
<span id="l67"> * If many entries are to be made into a &lt;code&gt;Hashtable&lt;/code&gt;,</span><a href="#l67"></a>
<span id="l68"> * creating it with a sufficiently large capacity may allow the</span><a href="#l68"></a>
<span id="l69"> * entries to be inserted more efficiently than letting it perform</span><a href="#l69"></a>
<span id="l70"> * automatic rehashing as needed to grow the table. &lt;p&gt;</span><a href="#l70"></a>
<span id="l71"> *</span><a href="#l71"></a>
<span id="l72"> * This example creates a hashtable of numbers. It uses the names of</span><a href="#l72"></a>
<span id="l73"> * the numbers as keys:</span><a href="#l73"></a>
<span id="l74"> * &lt;pre&gt;   {@code</span><a href="#l74"></a>
<span id="l75"> *   Hashtable&lt;String, Integer&gt; numbers</span><a href="#l75"></a>
<span id="l76"> *     = new Hashtable&lt;String, Integer&gt;();</span><a href="#l76"></a>
<span id="l77"> *   numbers.put(&quot;one&quot;, 1);</span><a href="#l77"></a>
<span id="l78"> *   numbers.put(&quot;two&quot;, 2);</span><a href="#l78"></a>
<span id="l79"> *   numbers.put(&quot;three&quot;, 3);}&lt;/pre&gt;</span><a href="#l79"></a>
<span id="l80"> *</span><a href="#l80"></a>
<span id="l81"> * &lt;p&gt;To retrieve a number, use the following code:</span><a href="#l81"></a>
<span id="l82"> * &lt;pre&gt;   {@code</span><a href="#l82"></a>
<span id="l83"> *   Integer n = numbers.get(&quot;two&quot;);</span><a href="#l83"></a>
<span id="l84"> *   if (n != null) {</span><a href="#l84"></a>
<span id="l85"> *     System.out.println(&quot;two = &quot; + n);</span><a href="#l85"></a>
<span id="l86"> *   }}&lt;/pre&gt;</span><a href="#l86"></a>
<span id="l87"> *</span><a href="#l87"></a>
<span id="l88"> * &lt;p&gt;The iterators returned by the &lt;tt&gt;iterator&lt;/tt&gt; method of the collections</span><a href="#l88"></a>
<span id="l89"> * returned by all of this class's &quot;collection view methods&quot; are</span><a href="#l89"></a>
<span id="l90"> * &lt;em&gt;fail-fast&lt;/em&gt;: if the Hashtable is structurally modified at any time</span><a href="#l90"></a>
<span id="l91"> * after the iterator is created, in any way except through the iterator's own</span><a href="#l91"></a>
<span id="l92"> * &lt;tt&gt;remove&lt;/tt&gt; method, the iterator will throw a {@link</span><a href="#l92"></a>
<span id="l93"> * ConcurrentModificationException}.  Thus, in the face of concurrent</span><a href="#l93"></a>
<span id="l94"> * modification, the iterator fails quickly and cleanly, rather than risking</span><a href="#l94"></a>
<span id="l95"> * arbitrary, non-deterministic behavior at an undetermined time in the future.</span><a href="#l95"></a>
<span id="l96"> * The Enumerations returned by Hashtable's keys and elements methods are</span><a href="#l96"></a>
<span id="l97"> * &lt;em&gt;not&lt;/em&gt; fail-fast.</span><a href="#l97"></a>
<span id="l98"> *</span><a href="#l98"></a>
<span id="l99"> * &lt;p&gt;Note that the fail-fast behavior of an iterator cannot be guaranteed</span><a href="#l99"></a>
<span id="l100"> * as it is, generally speaking, impossible to make any hard guarantees in the</span><a href="#l100"></a>
<span id="l101"> * presence of unsynchronized concurrent modification.  Fail-fast iterators</span><a href="#l101"></a>
<span id="l102"> * throw &lt;tt&gt;ConcurrentModificationException&lt;/tt&gt; on a best-effort basis.</span><a href="#l102"></a>
<span id="l103"> * Therefore, it would be wrong to write a program that depended on this</span><a href="#l103"></a>
<span id="l104"> * exception for its correctness: &lt;i&gt;the fail-fast behavior of iterators</span><a href="#l104"></a>
<span id="l105"> * should be used only to detect bugs.&lt;/i&gt;</span><a href="#l105"></a>
<span id="l106"> *</span><a href="#l106"></a>
<span id="l107"> * &lt;p&gt;As of the Java 2 platform v1.2, this class was retrofitted to</span><a href="#l107"></a>
<span id="l108"> * implement the {@link Map} interface, making it a member of the</span><a href="#l108"></a>
<span id="l109"> * &lt;a href=&quot;{@docRoot}/../technotes/guides/collections/index.html&quot;&gt;</span><a href="#l109"></a>
<span id="l110"> *</span><a href="#l110"></a>
<span id="l111"> * Java Collections Framework&lt;/a&gt;.  Unlike the new collection</span><a href="#l111"></a>
<span id="l112"> * implementations, {@code Hashtable} is synchronized.  If a</span><a href="#l112"></a>
<span id="l113"> * thread-safe implementation is not needed, it is recommended to use</span><a href="#l113"></a>
<span id="l114"> * {@link HashMap} in place of {@code Hashtable}.  If a thread-safe</span><a href="#l114"></a>
<span id="l115"> * highly-concurrent implementation is desired, then it is recommended</span><a href="#l115"></a>
<span id="l116"> * to use {@link java.util.concurrent.ConcurrentHashMap} in place of</span><a href="#l116"></a>
<span id="l117"> * {@code Hashtable}.</span><a href="#l117"></a>
<span id="l118"> *</span><a href="#l118"></a>
<span id="l119"> * @author  Arthur van Hoff</span><a href="#l119"></a>
<span id="l120"> * @author  Josh Bloch</span><a href="#l120"></a>
<span id="l121"> * @author  Neal Gafter</span><a href="#l121"></a>
<span id="l122"> * @see     Object#equals(java.lang.Object)</span><a href="#l122"></a>
<span id="l123"> * @see     Object#hashCode()</span><a href="#l123"></a>
<span id="l124"> * @see     Hashtable#rehash()</span><a href="#l124"></a>
<span id="l125"> * @see     Collection</span><a href="#l125"></a>
<span id="l126"> * @see     Map</span><a href="#l126"></a>
<span id="l127"> * @see     HashMap</span><a href="#l127"></a>
<span id="l128"> * @see     TreeMap</span><a href="#l128"></a>
<span id="l129"> * @since JDK1.0</span><a href="#l129"></a>
<span id="l130"> */</span><a href="#l130"></a>
<span id="l131">public class Hashtable&lt;K,V&gt;</span><a href="#l131"></a>
<span id="l132">    extends Dictionary&lt;K,V&gt;</span><a href="#l132"></a>
<span id="l133">    implements Map&lt;K,V&gt;, Cloneable, java.io.Serializable {</span><a href="#l133"></a>
<span id="l134"></span><a href="#l134"></a>
<span id="l135">    /**</span><a href="#l135"></a>
<span id="l136">     * The hash table data.</span><a href="#l136"></a>
<span id="l137">     */</span><a href="#l137"></a>
<span id="l138">    private transient Entry&lt;?,?&gt;[] table;</span><a href="#l138"></a>
<span id="l139"></span><a href="#l139"></a>
<span id="l140">    /**</span><a href="#l140"></a>
<span id="l141">     * The total number of entries in the hash table.</span><a href="#l141"></a>
<span id="l142">     */</span><a href="#l142"></a>
<span id="l143">    private transient int count;</span><a href="#l143"></a>
<span id="l144"></span><a href="#l144"></a>
<span id="l145">    /**</span><a href="#l145"></a>
<span id="l146">     * The table is rehashed when its size exceeds this threshold.  (The</span><a href="#l146"></a>
<span id="l147">     * value of this field is (int)(capacity * loadFactor).)</span><a href="#l147"></a>
<span id="l148">     *</span><a href="#l148"></a>
<span id="l149">     * @serial</span><a href="#l149"></a>
<span id="l150">     */</span><a href="#l150"></a>
<span id="l151">    private int threshold;</span><a href="#l151"></a>
<span id="l152"></span><a href="#l152"></a>
<span id="l153">    /**</span><a href="#l153"></a>
<span id="l154">     * The load factor for the hashtable.</span><a href="#l154"></a>
<span id="l155">     *</span><a href="#l155"></a>
<span id="l156">     * @serial</span><a href="#l156"></a>
<span id="l157">     */</span><a href="#l157"></a>
<span id="l158">    private float loadFactor;</span><a href="#l158"></a>
<span id="l159"></span><a href="#l159"></a>
<span id="l160">    /**</span><a href="#l160"></a>
<span id="l161">     * The number of times this Hashtable has been structurally modified</span><a href="#l161"></a>
<span id="l162">     * Structural modifications are those that change the number of entries in</span><a href="#l162"></a>
<span id="l163">     * the Hashtable or otherwise modify its internal structure (e.g.,</span><a href="#l163"></a>
<span id="l164">     * rehash).  This field is used to make iterators on Collection-views of</span><a href="#l164"></a>
<span id="l165">     * the Hashtable fail-fast.  (See ConcurrentModificationException).</span><a href="#l165"></a>
<span id="l166">     */</span><a href="#l166"></a>
<span id="l167">    private transient int modCount = 0;</span><a href="#l167"></a>
<span id="l168"></span><a href="#l168"></a>
<span id="l169">    /** use serialVersionUID from JDK 1.0.2 for interoperability */</span><a href="#l169"></a>
<span id="l170">    private static final long serialVersionUID = 1421746759512286392L;</span><a href="#l170"></a>
<span id="l171"></span><a href="#l171"></a>
<span id="l172">    /**</span><a href="#l172"></a>
<span id="l173">     * Constructs a new, empty hashtable with the specified initial</span><a href="#l173"></a>
<span id="l174">     * capacity and the specified load factor.</span><a href="#l174"></a>
<span id="l175">     *</span><a href="#l175"></a>
<span id="l176">     * @param      initialCapacity   the initial capacity of the hashtable.</span><a href="#l176"></a>
<span id="l177">     * @param      loadFactor        the load factor of the hashtable.</span><a href="#l177"></a>
<span id="l178">     * @exception  IllegalArgumentException  if the initial capacity is less</span><a href="#l178"></a>
<span id="l179">     *             than zero, or if the load factor is nonpositive.</span><a href="#l179"></a>
<span id="l180">     */</span><a href="#l180"></a>
<span id="l181">    public Hashtable(int initialCapacity, float loadFactor) {</span><a href="#l181"></a>
<span id="l182">        if (initialCapacity &lt; 0)</span><a href="#l182"></a>
<span id="l183">            throw new IllegalArgumentException(&quot;Illegal Capacity: &quot;+</span><a href="#l183"></a>
<span id="l184">                                               initialCapacity);</span><a href="#l184"></a>
<span id="l185">        if (loadFactor &lt;= 0 || Float.isNaN(loadFactor))</span><a href="#l185"></a>
<span id="l186">            throw new IllegalArgumentException(&quot;Illegal Load: &quot;+loadFactor);</span><a href="#l186"></a>
<span id="l187"></span><a href="#l187"></a>
<span id="l188">        if (initialCapacity==0)</span><a href="#l188"></a>
<span id="l189">            initialCapacity = 1;</span><a href="#l189"></a>
<span id="l190">        this.loadFactor = loadFactor;</span><a href="#l190"></a>
<span id="l191">        table = new Entry&lt;?,?&gt;[initialCapacity];</span><a href="#l191"></a>
<span id="l192">        threshold = (int)Math.min(initialCapacity * loadFactor, MAX_ARRAY_SIZE + 1);</span><a href="#l192"></a>
<span id="l193">    }</span><a href="#l193"></a>
<span id="l194"></span><a href="#l194"></a>
<span id="l195">    /**</span><a href="#l195"></a>
<span id="l196">     * Constructs a new, empty hashtable with the specified initial capacity</span><a href="#l196"></a>
<span id="l197">     * and default load factor (0.75).</span><a href="#l197"></a>
<span id="l198">     *</span><a href="#l198"></a>
<span id="l199">     * @param     initialCapacity   the initial capacity of the hashtable.</span><a href="#l199"></a>
<span id="l200">     * @exception IllegalArgumentException if the initial capacity is less</span><a href="#l200"></a>
<span id="l201">     *              than zero.</span><a href="#l201"></a>
<span id="l202">     */</span><a href="#l202"></a>
<span id="l203">    public Hashtable(int initialCapacity) {</span><a href="#l203"></a>
<span id="l204">        this(initialCapacity, 0.75f);</span><a href="#l204"></a>
<span id="l205">    }</span><a href="#l205"></a>
<span id="l206"></span><a href="#l206"></a>
<span id="l207">    /**</span><a href="#l207"></a>
<span id="l208">     * Constructs a new, empty hashtable with a default initial capacity (11)</span><a href="#l208"></a>
<span id="l209">     * and load factor (0.75).</span><a href="#l209"></a>
<span id="l210">     */</span><a href="#l210"></a>
<span id="l211">    public Hashtable() {</span><a href="#l211"></a>
<span id="l212">        this(11, 0.75f);</span><a href="#l212"></a>
<span id="l213">    }</span><a href="#l213"></a>
<span id="l214"></span><a href="#l214"></a>
<span id="l215">    /**</span><a href="#l215"></a>
<span id="l216">     * Constructs a new hashtable with the same mappings as the given</span><a href="#l216"></a>
<span id="l217">     * Map.  The hashtable is created with an initial capacity sufficient to</span><a href="#l217"></a>
<span id="l218">     * hold the mappings in the given Map and a default load factor (0.75).</span><a href="#l218"></a>
<span id="l219">     *</span><a href="#l219"></a>
<span id="l220">     * @param t the map whose mappings are to be placed in this map.</span><a href="#l220"></a>
<span id="l221">     * @throws NullPointerException if the specified map is null.</span><a href="#l221"></a>
<span id="l222">     * @since   1.2</span><a href="#l222"></a>
<span id="l223">     */</span><a href="#l223"></a>
<span id="l224">    public Hashtable(Map&lt;? extends K, ? extends V&gt; t) {</span><a href="#l224"></a>
<span id="l225">        this(Math.max(2*t.size(), 11), 0.75f);</span><a href="#l225"></a>
<span id="l226">        putAll(t);</span><a href="#l226"></a>
<span id="l227">    }</span><a href="#l227"></a>
<span id="l228"></span><a href="#l228"></a>
<span id="l229">    /**</span><a href="#l229"></a>
<span id="l230">     * Returns the number of keys in this hashtable.</span><a href="#l230"></a>
<span id="l231">     *</span><a href="#l231"></a>
<span id="l232">     * @return  the number of keys in this hashtable.</span><a href="#l232"></a>
<span id="l233">     */</span><a href="#l233"></a>
<span id="l234">    public synchronized int size() {</span><a href="#l234"></a>
<span id="l235">        return count;</span><a href="#l235"></a>
<span id="l236">    }</span><a href="#l236"></a>
<span id="l237"></span><a href="#l237"></a>
<span id="l238">    /**</span><a href="#l238"></a>
<span id="l239">     * Tests if this hashtable maps no keys to values.</span><a href="#l239"></a>
<span id="l240">     *</span><a href="#l240"></a>
<span id="l241">     * @return  &lt;code&gt;true&lt;/code&gt; if this hashtable maps no keys to values;</span><a href="#l241"></a>
<span id="l242">     *          &lt;code&gt;false&lt;/code&gt; otherwise.</span><a href="#l242"></a>
<span id="l243">     */</span><a href="#l243"></a>
<span id="l244">    public synchronized boolean isEmpty() {</span><a href="#l244"></a>
<span id="l245">        return count == 0;</span><a href="#l245"></a>
<span id="l246">    }</span><a href="#l246"></a>
<span id="l247"></span><a href="#l247"></a>
<span id="l248">    /**</span><a href="#l248"></a>
<span id="l249">     * Returns an enumeration of the keys in this hashtable.</span><a href="#l249"></a>
<span id="l250">     *</span><a href="#l250"></a>
<span id="l251">     * @return  an enumeration of the keys in this hashtable.</span><a href="#l251"></a>
<span id="l252">     * @see     Enumeration</span><a href="#l252"></a>
<span id="l253">     * @see     #elements()</span><a href="#l253"></a>
<span id="l254">     * @see     #keySet()</span><a href="#l254"></a>
<span id="l255">     * @see     Map</span><a href="#l255"></a>
<span id="l256">     */</span><a href="#l256"></a>
<span id="l257">    public synchronized Enumeration&lt;K&gt; keys() {</span><a href="#l257"></a>
<span id="l258">        return this.&lt;K&gt;getEnumeration(KEYS);</span><a href="#l258"></a>
<span id="l259">    }</span><a href="#l259"></a>
<span id="l260"></span><a href="#l260"></a>
<span id="l261">    /**</span><a href="#l261"></a>
<span id="l262">     * Returns an enumeration of the values in this hashtable.</span><a href="#l262"></a>
<span id="l263">     * Use the Enumeration methods on the returned object to fetch the elements</span><a href="#l263"></a>
<span id="l264">     * sequentially.</span><a href="#l264"></a>
<span id="l265">     *</span><a href="#l265"></a>
<span id="l266">     * @return  an enumeration of the values in this hashtable.</span><a href="#l266"></a>
<span id="l267">     * @see     java.util.Enumeration</span><a href="#l267"></a>
<span id="l268">     * @see     #keys()</span><a href="#l268"></a>
<span id="l269">     * @see     #values()</span><a href="#l269"></a>
<span id="l270">     * @see     Map</span><a href="#l270"></a>
<span id="l271">     */</span><a href="#l271"></a>
<span id="l272">    public synchronized Enumeration&lt;V&gt; elements() {</span><a href="#l272"></a>
<span id="l273">        return this.&lt;V&gt;getEnumeration(VALUES);</span><a href="#l273"></a>
<span id="l274">    }</span><a href="#l274"></a>
<span id="l275"></span><a href="#l275"></a>
<span id="l276">    /**</span><a href="#l276"></a>
<span id="l277">     * Tests if some key maps into the specified value in this hashtable.</span><a href="#l277"></a>
<span id="l278">     * This operation is more expensive than the {@link #containsKey</span><a href="#l278"></a>
<span id="l279">     * containsKey} method.</span><a href="#l279"></a>
<span id="l280">     *</span><a href="#l280"></a>
<span id="l281">     * &lt;p&gt;Note that this method is identical in functionality to</span><a href="#l281"></a>
<span id="l282">     * {@link #containsValue containsValue}, (which is part of the</span><a href="#l282"></a>
<span id="l283">     * {@link Map} interface in the collections framework).</span><a href="#l283"></a>
<span id="l284">     *</span><a href="#l284"></a>
<span id="l285">     * @param      value   a value to search for</span><a href="#l285"></a>
<span id="l286">     * @return     &lt;code&gt;true&lt;/code&gt; if and only if some key maps to the</span><a href="#l286"></a>
<span id="l287">     *             &lt;code&gt;value&lt;/code&gt; argument in this hashtable as</span><a href="#l287"></a>
<span id="l288">     *             determined by the &lt;tt&gt;equals&lt;/tt&gt; method;</span><a href="#l288"></a>
<span id="l289">     *             &lt;code&gt;false&lt;/code&gt; otherwise.</span><a href="#l289"></a>
<span id="l290">     * @exception  NullPointerException  if the value is &lt;code&gt;null&lt;/code&gt;</span><a href="#l290"></a>
<span id="l291">     */</span><a href="#l291"></a>
<span id="l292">    public synchronized boolean contains(Object value) {</span><a href="#l292"></a>
<span id="l293">        if (value == null) {</span><a href="#l293"></a>
<span id="l294">            throw new NullPointerException();</span><a href="#l294"></a>
<span id="l295">        }</span><a href="#l295"></a>
<span id="l296"></span><a href="#l296"></a>
<span id="l297">        Entry&lt;?,?&gt; tab[] = table;</span><a href="#l297"></a>
<span id="l298">        for (int i = tab.length ; i-- &gt; 0 ;) {</span><a href="#l298"></a>
<span id="l299">            for (Entry&lt;?,?&gt; e = tab[i] ; e != null ; e = e.next) {</span><a href="#l299"></a>
<span id="l300">                if (e.value.equals(value)) {</span><a href="#l300"></a>
<span id="l301">                    return true;</span><a href="#l301"></a>
<span id="l302">                }</span><a href="#l302"></a>
<span id="l303">            }</span><a href="#l303"></a>
<span id="l304">        }</span><a href="#l304"></a>
<span id="l305">        return false;</span><a href="#l305"></a>
<span id="l306">    }</span><a href="#l306"></a>
<span id="l307"></span><a href="#l307"></a>
<span id="l308">    /**</span><a href="#l308"></a>
<span id="l309">     * Returns true if this hashtable maps one or more keys to this value.</span><a href="#l309"></a>
<span id="l310">     *</span><a href="#l310"></a>
<span id="l311">     * &lt;p&gt;Note that this method is identical in functionality to {@link</span><a href="#l311"></a>
<span id="l312">     * #contains contains} (which predates the {@link Map} interface).</span><a href="#l312"></a>
<span id="l313">     *</span><a href="#l313"></a>
<span id="l314">     * @param value value whose presence in this hashtable is to be tested</span><a href="#l314"></a>
<span id="l315">     * @return &lt;tt&gt;true&lt;/tt&gt; if this map maps one or more keys to the</span><a href="#l315"></a>
<span id="l316">     *         specified value</span><a href="#l316"></a>
<span id="l317">     * @throws NullPointerException  if the value is &lt;code&gt;null&lt;/code&gt;</span><a href="#l317"></a>
<span id="l318">     * @since 1.2</span><a href="#l318"></a>
<span id="l319">     */</span><a href="#l319"></a>
<span id="l320">    public boolean containsValue(Object value) {</span><a href="#l320"></a>
<span id="l321">        return contains(value);</span><a href="#l321"></a>
<span id="l322">    }</span><a href="#l322"></a>
<span id="l323"></span><a href="#l323"></a>
<span id="l324">    /**</span><a href="#l324"></a>
<span id="l325">     * Tests if the specified object is a key in this hashtable.</span><a href="#l325"></a>
<span id="l326">     *</span><a href="#l326"></a>
<span id="l327">     * @param   key   possible key</span><a href="#l327"></a>
<span id="l328">     * @return  &lt;code&gt;true&lt;/code&gt; if and only if the specified object</span><a href="#l328"></a>
<span id="l329">     *          is a key in this hashtable, as determined by the</span><a href="#l329"></a>
<span id="l330">     *          &lt;tt&gt;equals&lt;/tt&gt; method; &lt;code&gt;false&lt;/code&gt; otherwise.</span><a href="#l330"></a>
<span id="l331">     * @throws  NullPointerException  if the key is &lt;code&gt;null&lt;/code&gt;</span><a href="#l331"></a>
<span id="l332">     * @see     #contains(Object)</span><a href="#l332"></a>
<span id="l333">     */</span><a href="#l333"></a>
<span id="l334">    public synchronized boolean containsKey(Object key) {</span><a href="#l334"></a>
<span id="l335">        Entry&lt;?,?&gt; tab[] = table;</span><a href="#l335"></a>
<span id="l336">        int hash = key.hashCode();</span><a href="#l336"></a>
<span id="l337">        int index = (hash &amp; 0x7FFFFFFF) % tab.length;</span><a href="#l337"></a>
<span id="l338">        for (Entry&lt;?,?&gt; e = tab[index] ; e != null ; e = e.next) {</span><a href="#l338"></a>
<span id="l339">            if ((e.hash == hash) &amp;&amp; e.key.equals(key)) {</span><a href="#l339"></a>
<span id="l340">                return true;</span><a href="#l340"></a>
<span id="l341">            }</span><a href="#l341"></a>
<span id="l342">        }</span><a href="#l342"></a>
<span id="l343">        return false;</span><a href="#l343"></a>
<span id="l344">    }</span><a href="#l344"></a>
<span id="l345"></span><a href="#l345"></a>
<span id="l346">    /**</span><a href="#l346"></a>
<span id="l347">     * Returns the value to which the specified key is mapped,</span><a href="#l347"></a>
<span id="l348">     * or {@code null} if this map contains no mapping for the key.</span><a href="#l348"></a>
<span id="l349">     *</span><a href="#l349"></a>
<span id="l350">     * &lt;p&gt;More formally, if this map contains a mapping from a key</span><a href="#l350"></a>
<span id="l351">     * {@code k} to a value {@code v} such that {@code (key.equals(k))},</span><a href="#l351"></a>
<span id="l352">     * then this method returns {@code v}; otherwise it returns</span><a href="#l352"></a>
<span id="l353">     * {@code null}.  (There can be at most one such mapping.)</span><a href="#l353"></a>
<span id="l354">     *</span><a href="#l354"></a>
<span id="l355">     * @param key the key whose associated value is to be returned</span><a href="#l355"></a>
<span id="l356">     * @return the value to which the specified key is mapped, or</span><a href="#l356"></a>
<span id="l357">     *         {@code null} if this map contains no mapping for the key</span><a href="#l357"></a>
<span id="l358">     * @throws NullPointerException if the specified key is null</span><a href="#l358"></a>
<span id="l359">     * @see     #put(Object, Object)</span><a href="#l359"></a>
<span id="l360">     */</span><a href="#l360"></a>
<span id="l361">    @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l361"></a>
<span id="l362">    public synchronized V get(Object key) {</span><a href="#l362"></a>
<span id="l363">        Entry&lt;?,?&gt; tab[] = table;</span><a href="#l363"></a>
<span id="l364">        int hash = key.hashCode();</span><a href="#l364"></a>
<span id="l365">        int index = (hash &amp; 0x7FFFFFFF) % tab.length;</span><a href="#l365"></a>
<span id="l366">        for (Entry&lt;?,?&gt; e = tab[index] ; e != null ; e = e.next) {</span><a href="#l366"></a>
<span id="l367">            if ((e.hash == hash) &amp;&amp; e.key.equals(key)) {</span><a href="#l367"></a>
<span id="l368">                return (V)e.value;</span><a href="#l368"></a>
<span id="l369">            }</span><a href="#l369"></a>
<span id="l370">        }</span><a href="#l370"></a>
<span id="l371">        return null;</span><a href="#l371"></a>
<span id="l372">    }</span><a href="#l372"></a>
<span id="l373"></span><a href="#l373"></a>
<span id="l374">    /**</span><a href="#l374"></a>
<span id="l375">     * The maximum size of array to allocate.</span><a href="#l375"></a>
<span id="l376">     * Some VMs reserve some header words in an array.</span><a href="#l376"></a>
<span id="l377">     * Attempts to allocate larger arrays may result in</span><a href="#l377"></a>
<span id="l378">     * OutOfMemoryError: Requested array size exceeds VM limit</span><a href="#l378"></a>
<span id="l379">     */</span><a href="#l379"></a>
<span id="l380">    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;</span><a href="#l380"></a>
<span id="l381"></span><a href="#l381"></a>
<span id="l382">    /**</span><a href="#l382"></a>
<span id="l383">     * Increases the capacity of and internally reorganizes this</span><a href="#l383"></a>
<span id="l384">     * hashtable, in order to accommodate and access its entries more</span><a href="#l384"></a>
<span id="l385">     * efficiently.  This method is called automatically when the</span><a href="#l385"></a>
<span id="l386">     * number of keys in the hashtable exceeds this hashtable's capacity</span><a href="#l386"></a>
<span id="l387">     * and load factor.</span><a href="#l387"></a>
<span id="l388">     */</span><a href="#l388"></a>
<span id="l389">    @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l389"></a>
<span id="l390">    protected void rehash() {</span><a href="#l390"></a>
<span id="l391">        int oldCapacity = table.length;</span><a href="#l391"></a>
<span id="l392">        Entry&lt;?,?&gt;[] oldMap = table;</span><a href="#l392"></a>
<span id="l393"></span><a href="#l393"></a>
<span id="l394">        // overflow-conscious code</span><a href="#l394"></a>
<span id="l395">        int newCapacity = (oldCapacity &lt;&lt; 1) + 1;</span><a href="#l395"></a>
<span id="l396">        if (newCapacity - MAX_ARRAY_SIZE &gt; 0) {</span><a href="#l396"></a>
<span id="l397">            if (oldCapacity == MAX_ARRAY_SIZE)</span><a href="#l397"></a>
<span id="l398">                // Keep running with MAX_ARRAY_SIZE buckets</span><a href="#l398"></a>
<span id="l399">                return;</span><a href="#l399"></a>
<span id="l400">            newCapacity = MAX_ARRAY_SIZE;</span><a href="#l400"></a>
<span id="l401">        }</span><a href="#l401"></a>
<span id="l402">        Entry&lt;?,?&gt;[] newMap = new Entry&lt;?,?&gt;[newCapacity];</span><a href="#l402"></a>
<span id="l403"></span><a href="#l403"></a>
<span id="l404">        modCount++;</span><a href="#l404"></a>
<span id="l405">        threshold = (int)Math.min(newCapacity * loadFactor, MAX_ARRAY_SIZE + 1);</span><a href="#l405"></a>
<span id="l406">        table = newMap;</span><a href="#l406"></a>
<span id="l407"></span><a href="#l407"></a>
<span id="l408">        for (int i = oldCapacity ; i-- &gt; 0 ;) {</span><a href="#l408"></a>
<span id="l409">            for (Entry&lt;K,V&gt; old = (Entry&lt;K,V&gt;)oldMap[i] ; old != null ; ) {</span><a href="#l409"></a>
<span id="l410">                Entry&lt;K,V&gt; e = old;</span><a href="#l410"></a>
<span id="l411">                old = old.next;</span><a href="#l411"></a>
<span id="l412"></span><a href="#l412"></a>
<span id="l413">                int index = (e.hash &amp; 0x7FFFFFFF) % newCapacity;</span><a href="#l413"></a>
<span id="l414">                e.next = (Entry&lt;K,V&gt;)newMap[index];</span><a href="#l414"></a>
<span id="l415">                newMap[index] = e;</span><a href="#l415"></a>
<span id="l416">            }</span><a href="#l416"></a>
<span id="l417">        }</span><a href="#l417"></a>
<span id="l418">    }</span><a href="#l418"></a>
<span id="l419"></span><a href="#l419"></a>
<span id="l420">    private void addEntry(int hash, K key, V value, int index) {</span><a href="#l420"></a>
<span id="l421">        modCount++;</span><a href="#l421"></a>
<span id="l422"></span><a href="#l422"></a>
<span id="l423">        Entry&lt;?,?&gt; tab[] = table;</span><a href="#l423"></a>
<span id="l424">        if (count &gt;= threshold) {</span><a href="#l424"></a>
<span id="l425">            // Rehash the table if the threshold is exceeded</span><a href="#l425"></a>
<span id="l426">            rehash();</span><a href="#l426"></a>
<span id="l427"></span><a href="#l427"></a>
<span id="l428">            tab = table;</span><a href="#l428"></a>
<span id="l429">            hash = key.hashCode();</span><a href="#l429"></a>
<span id="l430">            index = (hash &amp; 0x7FFFFFFF) % tab.length;</span><a href="#l430"></a>
<span id="l431">        }</span><a href="#l431"></a>
<span id="l432"></span><a href="#l432"></a>
<span id="l433">        // Creates the new entry.</span><a href="#l433"></a>
<span id="l434">        @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l434"></a>
<span id="l435">        Entry&lt;K,V&gt; e = (Entry&lt;K,V&gt;) tab[index];</span><a href="#l435"></a>
<span id="l436">        tab[index] = new Entry&lt;&gt;(hash, key, value, e);</span><a href="#l436"></a>
<span id="l437">        count++;</span><a href="#l437"></a>
<span id="l438">    }</span><a href="#l438"></a>
<span id="l439"></span><a href="#l439"></a>
<span id="l440">    /**</span><a href="#l440"></a>
<span id="l441">     * Maps the specified &lt;code&gt;key&lt;/code&gt; to the specified</span><a href="#l441"></a>
<span id="l442">     * &lt;code&gt;value&lt;/code&gt; in this hashtable. Neither the key nor the</span><a href="#l442"></a>
<span id="l443">     * value can be &lt;code&gt;null&lt;/code&gt;. &lt;p&gt;</span><a href="#l443"></a>
<span id="l444">     *</span><a href="#l444"></a>
<span id="l445">     * The value can be retrieved by calling the &lt;code&gt;get&lt;/code&gt; method</span><a href="#l445"></a>
<span id="l446">     * with a key that is equal to the original key.</span><a href="#l446"></a>
<span id="l447">     *</span><a href="#l447"></a>
<span id="l448">     * @param      key     the hashtable key</span><a href="#l448"></a>
<span id="l449">     * @param      value   the value</span><a href="#l449"></a>
<span id="l450">     * @return     the previous value of the specified key in this hashtable,</span><a href="#l450"></a>
<span id="l451">     *             or &lt;code&gt;null&lt;/code&gt; if it did not have one</span><a href="#l451"></a>
<span id="l452">     * @exception  NullPointerException  if the key or value is</span><a href="#l452"></a>
<span id="l453">     *               &lt;code&gt;null&lt;/code&gt;</span><a href="#l453"></a>
<span id="l454">     * @see     Object#equals(Object)</span><a href="#l454"></a>
<span id="l455">     * @see     #get(Object)</span><a href="#l455"></a>
<span id="l456">     */</span><a href="#l456"></a>
<span id="l457">    public synchronized V put(K key, V value) {</span><a href="#l457"></a>
<span id="l458">        // Make sure the value is not null</span><a href="#l458"></a>
<span id="l459">        if (value == null) {</span><a href="#l459"></a>
<span id="l460">            throw new NullPointerException();</span><a href="#l460"></a>
<span id="l461">        }</span><a href="#l461"></a>
<span id="l462"></span><a href="#l462"></a>
<span id="l463">        // Makes sure the key is not already in the hashtable.</span><a href="#l463"></a>
<span id="l464">        Entry&lt;?,?&gt; tab[] = table;</span><a href="#l464"></a>
<span id="l465">        int hash = key.hashCode();</span><a href="#l465"></a>
<span id="l466">        int index = (hash &amp; 0x7FFFFFFF) % tab.length;</span><a href="#l466"></a>
<span id="l467">        @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l467"></a>
<span id="l468">        Entry&lt;K,V&gt; entry = (Entry&lt;K,V&gt;)tab[index];</span><a href="#l468"></a>
<span id="l469">        for(; entry != null ; entry = entry.next) {</span><a href="#l469"></a>
<span id="l470">            if ((entry.hash == hash) &amp;&amp; entry.key.equals(key)) {</span><a href="#l470"></a>
<span id="l471">                V old = entry.value;</span><a href="#l471"></a>
<span id="l472">                entry.value = value;</span><a href="#l472"></a>
<span id="l473">                return old;</span><a href="#l473"></a>
<span id="l474">            }</span><a href="#l474"></a>
<span id="l475">        }</span><a href="#l475"></a>
<span id="l476"></span><a href="#l476"></a>
<span id="l477">        addEntry(hash, key, value, index);</span><a href="#l477"></a>
<span id="l478">        return null;</span><a href="#l478"></a>
<span id="l479">    }</span><a href="#l479"></a>
<span id="l480"></span><a href="#l480"></a>
<span id="l481">    /**</span><a href="#l481"></a>
<span id="l482">     * Removes the key (and its corresponding value) from this</span><a href="#l482"></a>
<span id="l483">     * hashtable. This method does nothing if the key is not in the hashtable.</span><a href="#l483"></a>
<span id="l484">     *</span><a href="#l484"></a>
<span id="l485">     * @param   key   the key that needs to be removed</span><a href="#l485"></a>
<span id="l486">     * @return  the value to which the key had been mapped in this hashtable,</span><a href="#l486"></a>
<span id="l487">     *          or &lt;code&gt;null&lt;/code&gt; if the key did not have a mapping</span><a href="#l487"></a>
<span id="l488">     * @throws  NullPointerException  if the key is &lt;code&gt;null&lt;/code&gt;</span><a href="#l488"></a>
<span id="l489">     */</span><a href="#l489"></a>
<span id="l490">    public synchronized V remove(Object key) {</span><a href="#l490"></a>
<span id="l491">        Entry&lt;?,?&gt; tab[] = table;</span><a href="#l491"></a>
<span id="l492">        int hash = key.hashCode();</span><a href="#l492"></a>
<span id="l493">        int index = (hash &amp; 0x7FFFFFFF) % tab.length;</span><a href="#l493"></a>
<span id="l494">        @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l494"></a>
<span id="l495">        Entry&lt;K,V&gt; e = (Entry&lt;K,V&gt;)tab[index];</span><a href="#l495"></a>
<span id="l496">        for(Entry&lt;K,V&gt; prev = null ; e != null ; prev = e, e = e.next) {</span><a href="#l496"></a>
<span id="l497">            if ((e.hash == hash) &amp;&amp; e.key.equals(key)) {</span><a href="#l497"></a>
<span id="l498">                modCount++;</span><a href="#l498"></a>
<span id="l499">                if (prev != null) {</span><a href="#l499"></a>
<span id="l500">                    prev.next = e.next;</span><a href="#l500"></a>
<span id="l501">                } else {</span><a href="#l501"></a>
<span id="l502">                    tab[index] = e.next;</span><a href="#l502"></a>
<span id="l503">                }</span><a href="#l503"></a>
<span id="l504">                count--;</span><a href="#l504"></a>
<span id="l505">                V oldValue = e.value;</span><a href="#l505"></a>
<span id="l506">                e.value = null;</span><a href="#l506"></a>
<span id="l507">                return oldValue;</span><a href="#l507"></a>
<span id="l508">            }</span><a href="#l508"></a>
<span id="l509">        }</span><a href="#l509"></a>
<span id="l510">        return null;</span><a href="#l510"></a>
<span id="l511">    }</span><a href="#l511"></a>
<span id="l512"></span><a href="#l512"></a>
<span id="l513">    /**</span><a href="#l513"></a>
<span id="l514">     * Copies all of the mappings from the specified map to this hashtable.</span><a href="#l514"></a>
<span id="l515">     * These mappings will replace any mappings that this hashtable had for any</span><a href="#l515"></a>
<span id="l516">     * of the keys currently in the specified map.</span><a href="#l516"></a>
<span id="l517">     *</span><a href="#l517"></a>
<span id="l518">     * @param t mappings to be stored in this map</span><a href="#l518"></a>
<span id="l519">     * @throws NullPointerException if the specified map is null</span><a href="#l519"></a>
<span id="l520">     * @since 1.2</span><a href="#l520"></a>
<span id="l521">     */</span><a href="#l521"></a>
<span id="l522">    public synchronized void putAll(Map&lt;? extends K, ? extends V&gt; t) {</span><a href="#l522"></a>
<span id="l523">        for (Map.Entry&lt;? extends K, ? extends V&gt; e : t.entrySet())</span><a href="#l523"></a>
<span id="l524">            put(e.getKey(), e.getValue());</span><a href="#l524"></a>
<span id="l525">    }</span><a href="#l525"></a>
<span id="l526"></span><a href="#l526"></a>
<span id="l527">    /**</span><a href="#l527"></a>
<span id="l528">     * Clears this hashtable so that it contains no keys.</span><a href="#l528"></a>
<span id="l529">     */</span><a href="#l529"></a>
<span id="l530">    public synchronized void clear() {</span><a href="#l530"></a>
<span id="l531">        Entry&lt;?,?&gt; tab[] = table;</span><a href="#l531"></a>
<span id="l532">        modCount++;</span><a href="#l532"></a>
<span id="l533">        for (int index = tab.length; --index &gt;= 0; )</span><a href="#l533"></a>
<span id="l534">            tab[index] = null;</span><a href="#l534"></a>
<span id="l535">        count = 0;</span><a href="#l535"></a>
<span id="l536">    }</span><a href="#l536"></a>
<span id="l537"></span><a href="#l537"></a>
<span id="l538">    /**</span><a href="#l538"></a>
<span id="l539">     * Creates a shallow copy of this hashtable. All the structure of the</span><a href="#l539"></a>
<span id="l540">     * hashtable itself is copied, but the keys and values are not cloned.</span><a href="#l540"></a>
<span id="l541">     * This is a relatively expensive operation.</span><a href="#l541"></a>
<span id="l542">     *</span><a href="#l542"></a>
<span id="l543">     * @return  a clone of the hashtable</span><a href="#l543"></a>
<span id="l544">     */</span><a href="#l544"></a>
<span id="l545">    public synchronized Object clone() {</span><a href="#l545"></a>
<span id="l546">        try {</span><a href="#l546"></a>
<span id="l547">            Hashtable&lt;?,?&gt; t = (Hashtable&lt;?,?&gt;)super.clone();</span><a href="#l547"></a>
<span id="l548">            t.table = new Entry&lt;?,?&gt;[table.length];</span><a href="#l548"></a>
<span id="l549">            for (int i = table.length ; i-- &gt; 0 ; ) {</span><a href="#l549"></a>
<span id="l550">                t.table[i] = (table[i] != null)</span><a href="#l550"></a>
<span id="l551">                    ? (Entry&lt;?,?&gt;) table[i].clone() : null;</span><a href="#l551"></a>
<span id="l552">            }</span><a href="#l552"></a>
<span id="l553">            t.keySet = null;</span><a href="#l553"></a>
<span id="l554">            t.entrySet = null;</span><a href="#l554"></a>
<span id="l555">            t.values = null;</span><a href="#l555"></a>
<span id="l556">            t.modCount = 0;</span><a href="#l556"></a>
<span id="l557">            return t;</span><a href="#l557"></a>
<span id="l558">        } catch (CloneNotSupportedException e) {</span><a href="#l558"></a>
<span id="l559">            // this shouldn't happen, since we are Cloneable</span><a href="#l559"></a>
<span id="l560">            throw new InternalError(e);</span><a href="#l560"></a>
<span id="l561">        }</span><a href="#l561"></a>
<span id="l562">    }</span><a href="#l562"></a>
<span id="l563"></span><a href="#l563"></a>
<span id="l564">    /**</span><a href="#l564"></a>
<span id="l565">     * Returns a string representation of this &lt;tt&gt;Hashtable&lt;/tt&gt; object</span><a href="#l565"></a>
<span id="l566">     * in the form of a set of entries, enclosed in braces and separated</span><a href="#l566"></a>
<span id="l567">     * by the ASCII characters &quot;&lt;tt&gt;,&amp;nbsp;&lt;/tt&gt;&quot; (comma and space). Each</span><a href="#l567"></a>
<span id="l568">     * entry is rendered as the key, an equals sign &lt;tt&gt;=&lt;/tt&gt;, and the</span><a href="#l568"></a>
<span id="l569">     * associated element, where the &lt;tt&gt;toString&lt;/tt&gt; method is used to</span><a href="#l569"></a>
<span id="l570">     * convert the key and element to strings.</span><a href="#l570"></a>
<span id="l571">     *</span><a href="#l571"></a>
<span id="l572">     * @return  a string representation of this hashtable</span><a href="#l572"></a>
<span id="l573">     */</span><a href="#l573"></a>
<span id="l574">    public synchronized String toString() {</span><a href="#l574"></a>
<span id="l575">        int max = size() - 1;</span><a href="#l575"></a>
<span id="l576">        if (max == -1)</span><a href="#l576"></a>
<span id="l577">            return &quot;{}&quot;;</span><a href="#l577"></a>
<span id="l578"></span><a href="#l578"></a>
<span id="l579">        StringBuilder sb = new StringBuilder();</span><a href="#l579"></a>
<span id="l580">        Iterator&lt;Map.Entry&lt;K,V&gt;&gt; it = entrySet().iterator();</span><a href="#l580"></a>
<span id="l581"></span><a href="#l581"></a>
<span id="l582">        sb.append('{');</span><a href="#l582"></a>
<span id="l583">        for (int i = 0; ; i++) {</span><a href="#l583"></a>
<span id="l584">            Map.Entry&lt;K,V&gt; e = it.next();</span><a href="#l584"></a>
<span id="l585">            K key = e.getKey();</span><a href="#l585"></a>
<span id="l586">            V value = e.getValue();</span><a href="#l586"></a>
<span id="l587">            sb.append(key   == this ? &quot;(this Map)&quot; : key.toString());</span><a href="#l587"></a>
<span id="l588">            sb.append('=');</span><a href="#l588"></a>
<span id="l589">            sb.append(value == this ? &quot;(this Map)&quot; : value.toString());</span><a href="#l589"></a>
<span id="l590"></span><a href="#l590"></a>
<span id="l591">            if (i == max)</span><a href="#l591"></a>
<span id="l592">                return sb.append('}').toString();</span><a href="#l592"></a>
<span id="l593">            sb.append(&quot;, &quot;);</span><a href="#l593"></a>
<span id="l594">        }</span><a href="#l594"></a>
<span id="l595">    }</span><a href="#l595"></a>
<span id="l596"></span><a href="#l596"></a>
<span id="l597"></span><a href="#l597"></a>
<span id="l598">    private &lt;T&gt; Enumeration&lt;T&gt; getEnumeration(int type) {</span><a href="#l598"></a>
<span id="l599">        if (count == 0) {</span><a href="#l599"></a>
<span id="l600">            return Collections.emptyEnumeration();</span><a href="#l600"></a>
<span id="l601">        } else {</span><a href="#l601"></a>
<span id="l602">            return new Enumerator&lt;&gt;(type, false);</span><a href="#l602"></a>
<span id="l603">        }</span><a href="#l603"></a>
<span id="l604">    }</span><a href="#l604"></a>
<span id="l605"></span><a href="#l605"></a>
<span id="l606">    private &lt;T&gt; Iterator&lt;T&gt; getIterator(int type) {</span><a href="#l606"></a>
<span id="l607">        if (count == 0) {</span><a href="#l607"></a>
<span id="l608">            return Collections.emptyIterator();</span><a href="#l608"></a>
<span id="l609">        } else {</span><a href="#l609"></a>
<span id="l610">            return new Enumerator&lt;&gt;(type, true);</span><a href="#l610"></a>
<span id="l611">        }</span><a href="#l611"></a>
<span id="l612">    }</span><a href="#l612"></a>
<span id="l613"></span><a href="#l613"></a>
<span id="l614">    // Views</span><a href="#l614"></a>
<span id="l615"></span><a href="#l615"></a>
<span id="l616">    /**</span><a href="#l616"></a>
<span id="l617">     * Each of these fields are initialized to contain an instance of the</span><a href="#l617"></a>
<span id="l618">     * appropriate view the first time this view is requested.  The views are</span><a href="#l618"></a>
<span id="l619">     * stateless, so there's no reason to create more than one of each.</span><a href="#l619"></a>
<span id="l620">     */</span><a href="#l620"></a>
<span id="l621">    private transient volatile Set&lt;K&gt; keySet;</span><a href="#l621"></a>
<span id="l622">    private transient volatile Set&lt;Map.Entry&lt;K,V&gt;&gt; entrySet;</span><a href="#l622"></a>
<span id="l623">    private transient volatile Collection&lt;V&gt; values;</span><a href="#l623"></a>
<span id="l624"></span><a href="#l624"></a>
<span id="l625">    /**</span><a href="#l625"></a>
<span id="l626">     * Returns a {@link Set} view of the keys contained in this map.</span><a href="#l626"></a>
<span id="l627">     * The set is backed by the map, so changes to the map are</span><a href="#l627"></a>
<span id="l628">     * reflected in the set, and vice-versa.  If the map is modified</span><a href="#l628"></a>
<span id="l629">     * while an iteration over the set is in progress (except through</span><a href="#l629"></a>
<span id="l630">     * the iterator's own &lt;tt&gt;remove&lt;/tt&gt; operation), the results of</span><a href="#l630"></a>
<span id="l631">     * the iteration are undefined.  The set supports element removal,</span><a href="#l631"></a>
<span id="l632">     * which removes the corresponding mapping from the map, via the</span><a href="#l632"></a>
<span id="l633">     * &lt;tt&gt;Iterator.remove&lt;/tt&gt;, &lt;tt&gt;Set.remove&lt;/tt&gt;,</span><a href="#l633"></a>
<span id="l634">     * &lt;tt&gt;removeAll&lt;/tt&gt;, &lt;tt&gt;retainAll&lt;/tt&gt;, and &lt;tt&gt;clear&lt;/tt&gt;</span><a href="#l634"></a>
<span id="l635">     * operations.  It does not support the &lt;tt&gt;add&lt;/tt&gt; or &lt;tt&gt;addAll&lt;/tt&gt;</span><a href="#l635"></a>
<span id="l636">     * operations.</span><a href="#l636"></a>
<span id="l637">     *</span><a href="#l637"></a>
<span id="l638">     * @since 1.2</span><a href="#l638"></a>
<span id="l639">     */</span><a href="#l639"></a>
<span id="l640">    public Set&lt;K&gt; keySet() {</span><a href="#l640"></a>
<span id="l641">        if (keySet == null)</span><a href="#l641"></a>
<span id="l642">            keySet = Collections.synchronizedSet(new KeySet(), this);</span><a href="#l642"></a>
<span id="l643">        return keySet;</span><a href="#l643"></a>
<span id="l644">    }</span><a href="#l644"></a>
<span id="l645"></span><a href="#l645"></a>
<span id="l646">    private class KeySet extends AbstractSet&lt;K&gt; {</span><a href="#l646"></a>
<span id="l647">        public Iterator&lt;K&gt; iterator() {</span><a href="#l647"></a>
<span id="l648">            return getIterator(KEYS);</span><a href="#l648"></a>
<span id="l649">        }</span><a href="#l649"></a>
<span id="l650">        public int size() {</span><a href="#l650"></a>
<span id="l651">            return count;</span><a href="#l651"></a>
<span id="l652">        }</span><a href="#l652"></a>
<span id="l653">        public boolean contains(Object o) {</span><a href="#l653"></a>
<span id="l654">            return containsKey(o);</span><a href="#l654"></a>
<span id="l655">        }</span><a href="#l655"></a>
<span id="l656">        public boolean remove(Object o) {</span><a href="#l656"></a>
<span id="l657">            return Hashtable.this.remove(o) != null;</span><a href="#l657"></a>
<span id="l658">        }</span><a href="#l658"></a>
<span id="l659">        public void clear() {</span><a href="#l659"></a>
<span id="l660">            Hashtable.this.clear();</span><a href="#l660"></a>
<span id="l661">        }</span><a href="#l661"></a>
<span id="l662">    }</span><a href="#l662"></a>
<span id="l663"></span><a href="#l663"></a>
<span id="l664">    /**</span><a href="#l664"></a>
<span id="l665">     * Returns a {@link Set} view of the mappings contained in this map.</span><a href="#l665"></a>
<span id="l666">     * The set is backed by the map, so changes to the map are</span><a href="#l666"></a>
<span id="l667">     * reflected in the set, and vice-versa.  If the map is modified</span><a href="#l667"></a>
<span id="l668">     * while an iteration over the set is in progress (except through</span><a href="#l668"></a>
<span id="l669">     * the iterator's own &lt;tt&gt;remove&lt;/tt&gt; operation, or through the</span><a href="#l669"></a>
<span id="l670">     * &lt;tt&gt;setValue&lt;/tt&gt; operation on a map entry returned by the</span><a href="#l670"></a>
<span id="l671">     * iterator) the results of the iteration are undefined.  The set</span><a href="#l671"></a>
<span id="l672">     * supports element removal, which removes the corresponding</span><a href="#l672"></a>
<span id="l673">     * mapping from the map, via the &lt;tt&gt;Iterator.remove&lt;/tt&gt;,</span><a href="#l673"></a>
<span id="l674">     * &lt;tt&gt;Set.remove&lt;/tt&gt;, &lt;tt&gt;removeAll&lt;/tt&gt;, &lt;tt&gt;retainAll&lt;/tt&gt; and</span><a href="#l674"></a>
<span id="l675">     * &lt;tt&gt;clear&lt;/tt&gt; operations.  It does not support the</span><a href="#l675"></a>
<span id="l676">     * &lt;tt&gt;add&lt;/tt&gt; or &lt;tt&gt;addAll&lt;/tt&gt; operations.</span><a href="#l676"></a>
<span id="l677">     *</span><a href="#l677"></a>
<span id="l678">     * @since 1.2</span><a href="#l678"></a>
<span id="l679">     */</span><a href="#l679"></a>
<span id="l680">    public Set&lt;Map.Entry&lt;K,V&gt;&gt; entrySet() {</span><a href="#l680"></a>
<span id="l681">        if (entrySet==null)</span><a href="#l681"></a>
<span id="l682">            entrySet = Collections.synchronizedSet(new EntrySet(), this);</span><a href="#l682"></a>
<span id="l683">        return entrySet;</span><a href="#l683"></a>
<span id="l684">    }</span><a href="#l684"></a>
<span id="l685"></span><a href="#l685"></a>
<span id="l686">    private class EntrySet extends AbstractSet&lt;Map.Entry&lt;K,V&gt;&gt; {</span><a href="#l686"></a>
<span id="l687">        public Iterator&lt;Map.Entry&lt;K,V&gt;&gt; iterator() {</span><a href="#l687"></a>
<span id="l688">            return getIterator(ENTRIES);</span><a href="#l688"></a>
<span id="l689">        }</span><a href="#l689"></a>
<span id="l690"></span><a href="#l690"></a>
<span id="l691">        public boolean add(Map.Entry&lt;K,V&gt; o) {</span><a href="#l691"></a>
<span id="l692">            return super.add(o);</span><a href="#l692"></a>
<span id="l693">        }</span><a href="#l693"></a>
<span id="l694"></span><a href="#l694"></a>
<span id="l695">        public boolean contains(Object o) {</span><a href="#l695"></a>
<span id="l696">            if (!(o instanceof Map.Entry))</span><a href="#l696"></a>
<span id="l697">                return false;</span><a href="#l697"></a>
<span id="l698">            Map.Entry&lt;?,?&gt; entry = (Map.Entry&lt;?,?&gt;)o;</span><a href="#l698"></a>
<span id="l699">            Object key = entry.getKey();</span><a href="#l699"></a>
<span id="l700">            Entry&lt;?,?&gt;[] tab = table;</span><a href="#l700"></a>
<span id="l701">            int hash = key.hashCode();</span><a href="#l701"></a>
<span id="l702">            int index = (hash &amp; 0x7FFFFFFF) % tab.length;</span><a href="#l702"></a>
<span id="l703"></span><a href="#l703"></a>
<span id="l704">            for (Entry&lt;?,?&gt; e = tab[index]; e != null; e = e.next)</span><a href="#l704"></a>
<span id="l705">                if (e.hash==hash &amp;&amp; e.equals(entry))</span><a href="#l705"></a>
<span id="l706">                    return true;</span><a href="#l706"></a>
<span id="l707">            return false;</span><a href="#l707"></a>
<span id="l708">        }</span><a href="#l708"></a>
<span id="l709"></span><a href="#l709"></a>
<span id="l710">        public boolean remove(Object o) {</span><a href="#l710"></a>
<span id="l711">            if (!(o instanceof Map.Entry))</span><a href="#l711"></a>
<span id="l712">                return false;</span><a href="#l712"></a>
<span id="l713">            Map.Entry&lt;?,?&gt; entry = (Map.Entry&lt;?,?&gt;) o;</span><a href="#l713"></a>
<span id="l714">            Object key = entry.getKey();</span><a href="#l714"></a>
<span id="l715">            Entry&lt;?,?&gt;[] tab = table;</span><a href="#l715"></a>
<span id="l716">            int hash = key.hashCode();</span><a href="#l716"></a>
<span id="l717">            int index = (hash &amp; 0x7FFFFFFF) % tab.length;</span><a href="#l717"></a>
<span id="l718"></span><a href="#l718"></a>
<span id="l719">            @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l719"></a>
<span id="l720">            Entry&lt;K,V&gt; e = (Entry&lt;K,V&gt;)tab[index];</span><a href="#l720"></a>
<span id="l721">            for(Entry&lt;K,V&gt; prev = null; e != null; prev = e, e = e.next) {</span><a href="#l721"></a>
<span id="l722">                if (e.hash==hash &amp;&amp; e.equals(entry)) {</span><a href="#l722"></a>
<span id="l723">                    modCount++;</span><a href="#l723"></a>
<span id="l724">                    if (prev != null)</span><a href="#l724"></a>
<span id="l725">                        prev.next = e.next;</span><a href="#l725"></a>
<span id="l726">                    else</span><a href="#l726"></a>
<span id="l727">                        tab[index] = e.next;</span><a href="#l727"></a>
<span id="l728"></span><a href="#l728"></a>
<span id="l729">                    count--;</span><a href="#l729"></a>
<span id="l730">                    e.value = null;</span><a href="#l730"></a>
<span id="l731">                    return true;</span><a href="#l731"></a>
<span id="l732">                }</span><a href="#l732"></a>
<span id="l733">            }</span><a href="#l733"></a>
<span id="l734">            return false;</span><a href="#l734"></a>
<span id="l735">        }</span><a href="#l735"></a>
<span id="l736"></span><a href="#l736"></a>
<span id="l737">        public int size() {</span><a href="#l737"></a>
<span id="l738">            return count;</span><a href="#l738"></a>
<span id="l739">        }</span><a href="#l739"></a>
<span id="l740"></span><a href="#l740"></a>
<span id="l741">        public void clear() {</span><a href="#l741"></a>
<span id="l742">            Hashtable.this.clear();</span><a href="#l742"></a>
<span id="l743">        }</span><a href="#l743"></a>
<span id="l744">    }</span><a href="#l744"></a>
<span id="l745"></span><a href="#l745"></a>
<span id="l746">    /**</span><a href="#l746"></a>
<span id="l747">     * Returns a {@link Collection} view of the values contained in this map.</span><a href="#l747"></a>
<span id="l748">     * The collection is backed by the map, so changes to the map are</span><a href="#l748"></a>
<span id="l749">     * reflected in the collection, and vice-versa.  If the map is</span><a href="#l749"></a>
<span id="l750">     * modified while an iteration over the collection is in progress</span><a href="#l750"></a>
<span id="l751">     * (except through the iterator's own &lt;tt&gt;remove&lt;/tt&gt; operation),</span><a href="#l751"></a>
<span id="l752">     * the results of the iteration are undefined.  The collection</span><a href="#l752"></a>
<span id="l753">     * supports element removal, which removes the corresponding</span><a href="#l753"></a>
<span id="l754">     * mapping from the map, via the &lt;tt&gt;Iterator.remove&lt;/tt&gt;,</span><a href="#l754"></a>
<span id="l755">     * &lt;tt&gt;Collection.remove&lt;/tt&gt;, &lt;tt&gt;removeAll&lt;/tt&gt;,</span><a href="#l755"></a>
<span id="l756">     * &lt;tt&gt;retainAll&lt;/tt&gt; and &lt;tt&gt;clear&lt;/tt&gt; operations.  It does not</span><a href="#l756"></a>
<span id="l757">     * support the &lt;tt&gt;add&lt;/tt&gt; or &lt;tt&gt;addAll&lt;/tt&gt; operations.</span><a href="#l757"></a>
<span id="l758">     *</span><a href="#l758"></a>
<span id="l759">     * @since 1.2</span><a href="#l759"></a>
<span id="l760">     */</span><a href="#l760"></a>
<span id="l761">    public Collection&lt;V&gt; values() {</span><a href="#l761"></a>
<span id="l762">        if (values==null)</span><a href="#l762"></a>
<span id="l763">            values = Collections.synchronizedCollection(new ValueCollection(),</span><a href="#l763"></a>
<span id="l764">                                                        this);</span><a href="#l764"></a>
<span id="l765">        return values;</span><a href="#l765"></a>
<span id="l766">    }</span><a href="#l766"></a>
<span id="l767"></span><a href="#l767"></a>
<span id="l768">    private class ValueCollection extends AbstractCollection&lt;V&gt; {</span><a href="#l768"></a>
<span id="l769">        public Iterator&lt;V&gt; iterator() {</span><a href="#l769"></a>
<span id="l770">            return getIterator(VALUES);</span><a href="#l770"></a>
<span id="l771">        }</span><a href="#l771"></a>
<span id="l772">        public int size() {</span><a href="#l772"></a>
<span id="l773">            return count;</span><a href="#l773"></a>
<span id="l774">        }</span><a href="#l774"></a>
<span id="l775">        public boolean contains(Object o) {</span><a href="#l775"></a>
<span id="l776">            return containsValue(o);</span><a href="#l776"></a>
<span id="l777">        }</span><a href="#l777"></a>
<span id="l778">        public void clear() {</span><a href="#l778"></a>
<span id="l779">            Hashtable.this.clear();</span><a href="#l779"></a>
<span id="l780">        }</span><a href="#l780"></a>
<span id="l781">    }</span><a href="#l781"></a>
<span id="l782"></span><a href="#l782"></a>
<span id="l783">    // Comparison and hashing</span><a href="#l783"></a>
<span id="l784"></span><a href="#l784"></a>
<span id="l785">    /**</span><a href="#l785"></a>
<span id="l786">     * Compares the specified Object with this Map for equality,</span><a href="#l786"></a>
<span id="l787">     * as per the definition in the Map interface.</span><a href="#l787"></a>
<span id="l788">     *</span><a href="#l788"></a>
<span id="l789">     * @param  o object to be compared for equality with this hashtable</span><a href="#l789"></a>
<span id="l790">     * @return true if the specified Object is equal to this Map</span><a href="#l790"></a>
<span id="l791">     * @see Map#equals(Object)</span><a href="#l791"></a>
<span id="l792">     * @since 1.2</span><a href="#l792"></a>
<span id="l793">     */</span><a href="#l793"></a>
<span id="l794">    public synchronized boolean equals(Object o) {</span><a href="#l794"></a>
<span id="l795">        if (o == this)</span><a href="#l795"></a>
<span id="l796">            return true;</span><a href="#l796"></a>
<span id="l797"></span><a href="#l797"></a>
<span id="l798">        if (!(o instanceof Map))</span><a href="#l798"></a>
<span id="l799">            return false;</span><a href="#l799"></a>
<span id="l800">        Map&lt;?,?&gt; t = (Map&lt;?,?&gt;) o;</span><a href="#l800"></a>
<span id="l801">        if (t.size() != size())</span><a href="#l801"></a>
<span id="l802">            return false;</span><a href="#l802"></a>
<span id="l803"></span><a href="#l803"></a>
<span id="l804">        try {</span><a href="#l804"></a>
<span id="l805">            Iterator&lt;Map.Entry&lt;K,V&gt;&gt; i = entrySet().iterator();</span><a href="#l805"></a>
<span id="l806">            while (i.hasNext()) {</span><a href="#l806"></a>
<span id="l807">                Map.Entry&lt;K,V&gt; e = i.next();</span><a href="#l807"></a>
<span id="l808">                K key = e.getKey();</span><a href="#l808"></a>
<span id="l809">                V value = e.getValue();</span><a href="#l809"></a>
<span id="l810">                if (value == null) {</span><a href="#l810"></a>
<span id="l811">                    if (!(t.get(key)==null &amp;&amp; t.containsKey(key)))</span><a href="#l811"></a>
<span id="l812">                        return false;</span><a href="#l812"></a>
<span id="l813">                } else {</span><a href="#l813"></a>
<span id="l814">                    if (!value.equals(t.get(key)))</span><a href="#l814"></a>
<span id="l815">                        return false;</span><a href="#l815"></a>
<span id="l816">                }</span><a href="#l816"></a>
<span id="l817">            }</span><a href="#l817"></a>
<span id="l818">        } catch (ClassCastException unused)   {</span><a href="#l818"></a>
<span id="l819">            return false;</span><a href="#l819"></a>
<span id="l820">        } catch (NullPointerException unused) {</span><a href="#l820"></a>
<span id="l821">            return false;</span><a href="#l821"></a>
<span id="l822">        }</span><a href="#l822"></a>
<span id="l823"></span><a href="#l823"></a>
<span id="l824">        return true;</span><a href="#l824"></a>
<span id="l825">    }</span><a href="#l825"></a>
<span id="l826"></span><a href="#l826"></a>
<span id="l827">    /**</span><a href="#l827"></a>
<span id="l828">     * Returns the hash code value for this Map as per the definition in the</span><a href="#l828"></a>
<span id="l829">     * Map interface.</span><a href="#l829"></a>
<span id="l830">     *</span><a href="#l830"></a>
<span id="l831">     * @see Map#hashCode()</span><a href="#l831"></a>
<span id="l832">     * @since 1.2</span><a href="#l832"></a>
<span id="l833">     */</span><a href="#l833"></a>
<span id="l834">    public synchronized int hashCode() {</span><a href="#l834"></a>
<span id="l835">        /*</span><a href="#l835"></a>
<span id="l836">         * This code detects the recursion caused by computing the hash code</span><a href="#l836"></a>
<span id="l837">         * of a self-referential hash table and prevents the stack overflow</span><a href="#l837"></a>
<span id="l838">         * that would otherwise result.  This allows certain 1.1-era</span><a href="#l838"></a>
<span id="l839">         * applets with self-referential hash tables to work.  This code</span><a href="#l839"></a>
<span id="l840">         * abuses the loadFactor field to do double-duty as a hashCode</span><a href="#l840"></a>
<span id="l841">         * in progress flag, so as not to worsen the space performance.</span><a href="#l841"></a>
<span id="l842">         * A negative load factor indicates that hash code computation is</span><a href="#l842"></a>
<span id="l843">         * in progress.</span><a href="#l843"></a>
<span id="l844">         */</span><a href="#l844"></a>
<span id="l845">        int h = 0;</span><a href="#l845"></a>
<span id="l846">        if (count == 0 || loadFactor &lt; 0)</span><a href="#l846"></a>
<span id="l847">            return h;  // Returns zero</span><a href="#l847"></a>
<span id="l848"></span><a href="#l848"></a>
<span id="l849">        loadFactor = -loadFactor;  // Mark hashCode computation in progress</span><a href="#l849"></a>
<span id="l850">        Entry&lt;?,?&gt;[] tab = table;</span><a href="#l850"></a>
<span id="l851">        for (Entry&lt;?,?&gt; entry : tab) {</span><a href="#l851"></a>
<span id="l852">            while (entry != null) {</span><a href="#l852"></a>
<span id="l853">                h += entry.hashCode();</span><a href="#l853"></a>
<span id="l854">                entry = entry.next;</span><a href="#l854"></a>
<span id="l855">            }</span><a href="#l855"></a>
<span id="l856">        }</span><a href="#l856"></a>
<span id="l857"></span><a href="#l857"></a>
<span id="l858">        loadFactor = -loadFactor;  // Mark hashCode computation complete</span><a href="#l858"></a>
<span id="l859"></span><a href="#l859"></a>
<span id="l860">        return h;</span><a href="#l860"></a>
<span id="l861">    }</span><a href="#l861"></a>
<span id="l862"></span><a href="#l862"></a>
<span id="l863">    @Override</span><a href="#l863"></a>
<span id="l864">    public synchronized V getOrDefault(Object key, V defaultValue) {</span><a href="#l864"></a>
<span id="l865">        V result = get(key);</span><a href="#l865"></a>
<span id="l866">        return (null == result) ? defaultValue : result;</span><a href="#l866"></a>
<span id="l867">    }</span><a href="#l867"></a>
<span id="l868"></span><a href="#l868"></a>
<span id="l869">    @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l869"></a>
<span id="l870">    @Override</span><a href="#l870"></a>
<span id="l871">    public synchronized void forEach(BiConsumer&lt;? super K, ? super V&gt; action) {</span><a href="#l871"></a>
<span id="l872">        Objects.requireNonNull(action);     // explicit check required in case</span><a href="#l872"></a>
<span id="l873">                                            // table is empty.</span><a href="#l873"></a>
<span id="l874">        final int expectedModCount = modCount;</span><a href="#l874"></a>
<span id="l875"></span><a href="#l875"></a>
<span id="l876">        Entry&lt;?, ?&gt;[] tab = table;</span><a href="#l876"></a>
<span id="l877">        for (Entry&lt;?, ?&gt; entry : tab) {</span><a href="#l877"></a>
<span id="l878">            while (entry != null) {</span><a href="#l878"></a>
<span id="l879">                action.accept((K)entry.key, (V)entry.value);</span><a href="#l879"></a>
<span id="l880">                entry = entry.next;</span><a href="#l880"></a>
<span id="l881"></span><a href="#l881"></a>
<span id="l882">                if (expectedModCount != modCount) {</span><a href="#l882"></a>
<span id="l883">                    throw new ConcurrentModificationException();</span><a href="#l883"></a>
<span id="l884">                }</span><a href="#l884"></a>
<span id="l885">            }</span><a href="#l885"></a>
<span id="l886">        }</span><a href="#l886"></a>
<span id="l887">    }</span><a href="#l887"></a>
<span id="l888"></span><a href="#l888"></a>
<span id="l889">    @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l889"></a>
<span id="l890">    @Override</span><a href="#l890"></a>
<span id="l891">    public synchronized void replaceAll(BiFunction&lt;? super K, ? super V, ? extends V&gt; function) {</span><a href="#l891"></a>
<span id="l892">        Objects.requireNonNull(function);     // explicit check required in case</span><a href="#l892"></a>
<span id="l893">                                              // table is empty.</span><a href="#l893"></a>
<span id="l894">        final int expectedModCount = modCount;</span><a href="#l894"></a>
<span id="l895"></span><a href="#l895"></a>
<span id="l896">        Entry&lt;K, V&gt;[] tab = (Entry&lt;K, V&gt;[])table;</span><a href="#l896"></a>
<span id="l897">        for (Entry&lt;K, V&gt; entry : tab) {</span><a href="#l897"></a>
<span id="l898">            while (entry != null) {</span><a href="#l898"></a>
<span id="l899">                entry.value = Objects.requireNonNull(</span><a href="#l899"></a>
<span id="l900">                    function.apply(entry.key, entry.value));</span><a href="#l900"></a>
<span id="l901">                entry = entry.next;</span><a href="#l901"></a>
<span id="l902"></span><a href="#l902"></a>
<span id="l903">                if (expectedModCount != modCount) {</span><a href="#l903"></a>
<span id="l904">                    throw new ConcurrentModificationException();</span><a href="#l904"></a>
<span id="l905">                }</span><a href="#l905"></a>
<span id="l906">            }</span><a href="#l906"></a>
<span id="l907">        }</span><a href="#l907"></a>
<span id="l908">    }</span><a href="#l908"></a>
<span id="l909"></span><a href="#l909"></a>
<span id="l910">    @Override</span><a href="#l910"></a>
<span id="l911">    public synchronized V putIfAbsent(K key, V value) {</span><a href="#l911"></a>
<span id="l912">        Objects.requireNonNull(value);</span><a href="#l912"></a>
<span id="l913"></span><a href="#l913"></a>
<span id="l914">        // Makes sure the key is not already in the hashtable.</span><a href="#l914"></a>
<span id="l915">        Entry&lt;?,?&gt; tab[] = table;</span><a href="#l915"></a>
<span id="l916">        int hash = key.hashCode();</span><a href="#l916"></a>
<span id="l917">        int index = (hash &amp; 0x7FFFFFFF) % tab.length;</span><a href="#l917"></a>
<span id="l918">        @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l918"></a>
<span id="l919">        Entry&lt;K,V&gt; entry = (Entry&lt;K,V&gt;)tab[index];</span><a href="#l919"></a>
<span id="l920">        for (; entry != null; entry = entry.next) {</span><a href="#l920"></a>
<span id="l921">            if ((entry.hash == hash) &amp;&amp; entry.key.equals(key)) {</span><a href="#l921"></a>
<span id="l922">                V old = entry.value;</span><a href="#l922"></a>
<span id="l923">                if (old == null) {</span><a href="#l923"></a>
<span id="l924">                    entry.value = value;</span><a href="#l924"></a>
<span id="l925">                }</span><a href="#l925"></a>
<span id="l926">                return old;</span><a href="#l926"></a>
<span id="l927">            }</span><a href="#l927"></a>
<span id="l928">        }</span><a href="#l928"></a>
<span id="l929"></span><a href="#l929"></a>
<span id="l930">        addEntry(hash, key, value, index);</span><a href="#l930"></a>
<span id="l931">        return null;</span><a href="#l931"></a>
<span id="l932">    }</span><a href="#l932"></a>
<span id="l933"></span><a href="#l933"></a>
<span id="l934">    @Override</span><a href="#l934"></a>
<span id="l935">    public synchronized boolean remove(Object key, Object value) {</span><a href="#l935"></a>
<span id="l936">        Objects.requireNonNull(value);</span><a href="#l936"></a>
<span id="l937"></span><a href="#l937"></a>
<span id="l938">        Entry&lt;?,?&gt; tab[] = table;</span><a href="#l938"></a>
<span id="l939">        int hash = key.hashCode();</span><a href="#l939"></a>
<span id="l940">        int index = (hash &amp; 0x7FFFFFFF) % tab.length;</span><a href="#l940"></a>
<span id="l941">        @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l941"></a>
<span id="l942">        Entry&lt;K,V&gt; e = (Entry&lt;K,V&gt;)tab[index];</span><a href="#l942"></a>
<span id="l943">        for (Entry&lt;K,V&gt; prev = null; e != null; prev = e, e = e.next) {</span><a href="#l943"></a>
<span id="l944">            if ((e.hash == hash) &amp;&amp; e.key.equals(key) &amp;&amp; e.value.equals(value)) {</span><a href="#l944"></a>
<span id="l945">                modCount++;</span><a href="#l945"></a>
<span id="l946">                if (prev != null) {</span><a href="#l946"></a>
<span id="l947">                    prev.next = e.next;</span><a href="#l947"></a>
<span id="l948">                } else {</span><a href="#l948"></a>
<span id="l949">                    tab[index] = e.next;</span><a href="#l949"></a>
<span id="l950">                }</span><a href="#l950"></a>
<span id="l951">                count--;</span><a href="#l951"></a>
<span id="l952">                e.value = null;</span><a href="#l952"></a>
<span id="l953">                return true;</span><a href="#l953"></a>
<span id="l954">            }</span><a href="#l954"></a>
<span id="l955">        }</span><a href="#l955"></a>
<span id="l956">        return false;</span><a href="#l956"></a>
<span id="l957">    }</span><a href="#l957"></a>
<span id="l958"></span><a href="#l958"></a>
<span id="l959">    @Override</span><a href="#l959"></a>
<span id="l960">    public synchronized boolean replace(K key, V oldValue, V newValue) {</span><a href="#l960"></a>
<span id="l961">        Objects.requireNonNull(oldValue);</span><a href="#l961"></a>
<span id="l962">        Objects.requireNonNull(newValue);</span><a href="#l962"></a>
<span id="l963">        Entry&lt;?,?&gt; tab[] = table;</span><a href="#l963"></a>
<span id="l964">        int hash = key.hashCode();</span><a href="#l964"></a>
<span id="l965">        int index = (hash &amp; 0x7FFFFFFF) % tab.length;</span><a href="#l965"></a>
<span id="l966">        @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l966"></a>
<span id="l967">        Entry&lt;K,V&gt; e = (Entry&lt;K,V&gt;)tab[index];</span><a href="#l967"></a>
<span id="l968">        for (; e != null; e = e.next) {</span><a href="#l968"></a>
<span id="l969">            if ((e.hash == hash) &amp;&amp; e.key.equals(key)) {</span><a href="#l969"></a>
<span id="l970">                if (e.value.equals(oldValue)) {</span><a href="#l970"></a>
<span id="l971">                    e.value = newValue;</span><a href="#l971"></a>
<span id="l972">                    return true;</span><a href="#l972"></a>
<span id="l973">                } else {</span><a href="#l973"></a>
<span id="l974">                    return false;</span><a href="#l974"></a>
<span id="l975">                }</span><a href="#l975"></a>
<span id="l976">            }</span><a href="#l976"></a>
<span id="l977">        }</span><a href="#l977"></a>
<span id="l978">        return false;</span><a href="#l978"></a>
<span id="l979">    }</span><a href="#l979"></a>
<span id="l980"></span><a href="#l980"></a>
<span id="l981">    @Override</span><a href="#l981"></a>
<span id="l982">    public synchronized V replace(K key, V value) {</span><a href="#l982"></a>
<span id="l983">        Objects.requireNonNull(value);</span><a href="#l983"></a>
<span id="l984">        Entry&lt;?,?&gt; tab[] = table;</span><a href="#l984"></a>
<span id="l985">        int hash = key.hashCode();</span><a href="#l985"></a>
<span id="l986">        int index = (hash &amp; 0x7FFFFFFF) % tab.length;</span><a href="#l986"></a>
<span id="l987">        @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l987"></a>
<span id="l988">        Entry&lt;K,V&gt; e = (Entry&lt;K,V&gt;)tab[index];</span><a href="#l988"></a>
<span id="l989">        for (; e != null; e = e.next) {</span><a href="#l989"></a>
<span id="l990">            if ((e.hash == hash) &amp;&amp; e.key.equals(key)) {</span><a href="#l990"></a>
<span id="l991">                V oldValue = e.value;</span><a href="#l991"></a>
<span id="l992">                e.value = value;</span><a href="#l992"></a>
<span id="l993">                return oldValue;</span><a href="#l993"></a>
<span id="l994">            }</span><a href="#l994"></a>
<span id="l995">        }</span><a href="#l995"></a>
<span id="l996">        return null;</span><a href="#l996"></a>
<span id="l997">    }</span><a href="#l997"></a>
<span id="l998"></span><a href="#l998"></a>
<span id="l999">    @Override</span><a href="#l999"></a>
<span id="l1000">    public synchronized V computeIfAbsent(K key, Function&lt;? super K, ? extends V&gt; mappingFunction) {</span><a href="#l1000"></a>
<span id="l1001">        Objects.requireNonNull(mappingFunction);</span><a href="#l1001"></a>
<span id="l1002"></span><a href="#l1002"></a>
<span id="l1003">        Entry&lt;?,?&gt; tab[] = table;</span><a href="#l1003"></a>
<span id="l1004">        int hash = key.hashCode();</span><a href="#l1004"></a>
<span id="l1005">        int index = (hash &amp; 0x7FFFFFFF) % tab.length;</span><a href="#l1005"></a>
<span id="l1006">        @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l1006"></a>
<span id="l1007">        Entry&lt;K,V&gt; e = (Entry&lt;K,V&gt;)tab[index];</span><a href="#l1007"></a>
<span id="l1008">        for (; e != null; e = e.next) {</span><a href="#l1008"></a>
<span id="l1009">            if (e.hash == hash &amp;&amp; e.key.equals(key)) {</span><a href="#l1009"></a>
<span id="l1010">                // Hashtable not accept null value</span><a href="#l1010"></a>
<span id="l1011">                return e.value;</span><a href="#l1011"></a>
<span id="l1012">            }</span><a href="#l1012"></a>
<span id="l1013">        }</span><a href="#l1013"></a>
<span id="l1014"></span><a href="#l1014"></a>
<span id="l1015">        V newValue = mappingFunction.apply(key);</span><a href="#l1015"></a>
<span id="l1016">        if (newValue != null) {</span><a href="#l1016"></a>
<span id="l1017">            addEntry(hash, key, newValue, index);</span><a href="#l1017"></a>
<span id="l1018">        }</span><a href="#l1018"></a>
<span id="l1019"></span><a href="#l1019"></a>
<span id="l1020">        return newValue;</span><a href="#l1020"></a>
<span id="l1021">    }</span><a href="#l1021"></a>
<span id="l1022"></span><a href="#l1022"></a>
<span id="l1023">    @Override</span><a href="#l1023"></a>
<span id="l1024">    public synchronized V computeIfPresent(K key, BiFunction&lt;? super K, ? super V, ? extends V&gt; remappingFunction) {</span><a href="#l1024"></a>
<span id="l1025">        Objects.requireNonNull(remappingFunction);</span><a href="#l1025"></a>
<span id="l1026"></span><a href="#l1026"></a>
<span id="l1027">        Entry&lt;?,?&gt; tab[] = table;</span><a href="#l1027"></a>
<span id="l1028">        int hash = key.hashCode();</span><a href="#l1028"></a>
<span id="l1029">        int index = (hash &amp; 0x7FFFFFFF) % tab.length;</span><a href="#l1029"></a>
<span id="l1030">        @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l1030"></a>
<span id="l1031">        Entry&lt;K,V&gt; e = (Entry&lt;K,V&gt;)tab[index];</span><a href="#l1031"></a>
<span id="l1032">        for (Entry&lt;K,V&gt; prev = null; e != null; prev = e, e = e.next) {</span><a href="#l1032"></a>
<span id="l1033">            if (e.hash == hash &amp;&amp; e.key.equals(key)) {</span><a href="#l1033"></a>
<span id="l1034">                V newValue = remappingFunction.apply(key, e.value);</span><a href="#l1034"></a>
<span id="l1035">                if (newValue == null) {</span><a href="#l1035"></a>
<span id="l1036">                    modCount++;</span><a href="#l1036"></a>
<span id="l1037">                    if (prev != null) {</span><a href="#l1037"></a>
<span id="l1038">                        prev.next = e.next;</span><a href="#l1038"></a>
<span id="l1039">                    } else {</span><a href="#l1039"></a>
<span id="l1040">                        tab[index] = e.next;</span><a href="#l1040"></a>
<span id="l1041">                    }</span><a href="#l1041"></a>
<span id="l1042">                    count--;</span><a href="#l1042"></a>
<span id="l1043">                } else {</span><a href="#l1043"></a>
<span id="l1044">                    e.value = newValue;</span><a href="#l1044"></a>
<span id="l1045">                }</span><a href="#l1045"></a>
<span id="l1046">                return newValue;</span><a href="#l1046"></a>
<span id="l1047">            }</span><a href="#l1047"></a>
<span id="l1048">        }</span><a href="#l1048"></a>
<span id="l1049">        return null;</span><a href="#l1049"></a>
<span id="l1050">    }</span><a href="#l1050"></a>
<span id="l1051"></span><a href="#l1051"></a>
<span id="l1052">    @Override</span><a href="#l1052"></a>
<span id="l1053">    public synchronized V compute(K key, BiFunction&lt;? super K, ? super V, ? extends V&gt; remappingFunction) {</span><a href="#l1053"></a>
<span id="l1054">        Objects.requireNonNull(remappingFunction);</span><a href="#l1054"></a>
<span id="l1055"></span><a href="#l1055"></a>
<span id="l1056">        Entry&lt;?,?&gt; tab[] = table;</span><a href="#l1056"></a>
<span id="l1057">        int hash = key.hashCode();</span><a href="#l1057"></a>
<span id="l1058">        int index = (hash &amp; 0x7FFFFFFF) % tab.length;</span><a href="#l1058"></a>
<span id="l1059">        @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l1059"></a>
<span id="l1060">        Entry&lt;K,V&gt; e = (Entry&lt;K,V&gt;)tab[index];</span><a href="#l1060"></a>
<span id="l1061">        for (Entry&lt;K,V&gt; prev = null; e != null; prev = e, e = e.next) {</span><a href="#l1061"></a>
<span id="l1062">            if (e.hash == hash &amp;&amp; Objects.equals(e.key, key)) {</span><a href="#l1062"></a>
<span id="l1063">                V newValue = remappingFunction.apply(key, e.value);</span><a href="#l1063"></a>
<span id="l1064">                if (newValue == null) {</span><a href="#l1064"></a>
<span id="l1065">                    modCount++;</span><a href="#l1065"></a>
<span id="l1066">                    if (prev != null) {</span><a href="#l1066"></a>
<span id="l1067">                        prev.next = e.next;</span><a href="#l1067"></a>
<span id="l1068">                    } else {</span><a href="#l1068"></a>
<span id="l1069">                        tab[index] = e.next;</span><a href="#l1069"></a>
<span id="l1070">                    }</span><a href="#l1070"></a>
<span id="l1071">                    count--;</span><a href="#l1071"></a>
<span id="l1072">                } else {</span><a href="#l1072"></a>
<span id="l1073">                    e.value = newValue;</span><a href="#l1073"></a>
<span id="l1074">                }</span><a href="#l1074"></a>
<span id="l1075">                return newValue;</span><a href="#l1075"></a>
<span id="l1076">            }</span><a href="#l1076"></a>
<span id="l1077">        }</span><a href="#l1077"></a>
<span id="l1078"></span><a href="#l1078"></a>
<span id="l1079">        V newValue = remappingFunction.apply(key, null);</span><a href="#l1079"></a>
<span id="l1080">        if (newValue != null) {</span><a href="#l1080"></a>
<span id="l1081">            addEntry(hash, key, newValue, index);</span><a href="#l1081"></a>
<span id="l1082">        }</span><a href="#l1082"></a>
<span id="l1083"></span><a href="#l1083"></a>
<span id="l1084">        return newValue;</span><a href="#l1084"></a>
<span id="l1085">    }</span><a href="#l1085"></a>
<span id="l1086"></span><a href="#l1086"></a>
<span id="l1087">    @Override</span><a href="#l1087"></a>
<span id="l1088">    public synchronized V merge(K key, V value, BiFunction&lt;? super V, ? super V, ? extends V&gt; remappingFunction) {</span><a href="#l1088"></a>
<span id="l1089">        Objects.requireNonNull(remappingFunction);</span><a href="#l1089"></a>
<span id="l1090"></span><a href="#l1090"></a>
<span id="l1091">        Entry&lt;?,?&gt; tab[] = table;</span><a href="#l1091"></a>
<span id="l1092">        int hash = key.hashCode();</span><a href="#l1092"></a>
<span id="l1093">        int index = (hash &amp; 0x7FFFFFFF) % tab.length;</span><a href="#l1093"></a>
<span id="l1094">        @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l1094"></a>
<span id="l1095">        Entry&lt;K,V&gt; e = (Entry&lt;K,V&gt;)tab[index];</span><a href="#l1095"></a>
<span id="l1096">        for (Entry&lt;K,V&gt; prev = null; e != null; prev = e, e = e.next) {</span><a href="#l1096"></a>
<span id="l1097">            if (e.hash == hash &amp;&amp; e.key.equals(key)) {</span><a href="#l1097"></a>
<span id="l1098">                V newValue = remappingFunction.apply(e.value, value);</span><a href="#l1098"></a>
<span id="l1099">                if (newValue == null) {</span><a href="#l1099"></a>
<span id="l1100">                    modCount++;</span><a href="#l1100"></a>
<span id="l1101">                    if (prev != null) {</span><a href="#l1101"></a>
<span id="l1102">                        prev.next = e.next;</span><a href="#l1102"></a>
<span id="l1103">                    } else {</span><a href="#l1103"></a>
<span id="l1104">                        tab[index] = e.next;</span><a href="#l1104"></a>
<span id="l1105">                    }</span><a href="#l1105"></a>
<span id="l1106">                    count--;</span><a href="#l1106"></a>
<span id="l1107">                } else {</span><a href="#l1107"></a>
<span id="l1108">                    e.value = newValue;</span><a href="#l1108"></a>
<span id="l1109">                }</span><a href="#l1109"></a>
<span id="l1110">                return newValue;</span><a href="#l1110"></a>
<span id="l1111">            }</span><a href="#l1111"></a>
<span id="l1112">        }</span><a href="#l1112"></a>
<span id="l1113"></span><a href="#l1113"></a>
<span id="l1114">        if (value != null) {</span><a href="#l1114"></a>
<span id="l1115">            addEntry(hash, key, value, index);</span><a href="#l1115"></a>
<span id="l1116">        }</span><a href="#l1116"></a>
<span id="l1117"></span><a href="#l1117"></a>
<span id="l1118">        return value;</span><a href="#l1118"></a>
<span id="l1119">    }</span><a href="#l1119"></a>
<span id="l1120"></span><a href="#l1120"></a>
<span id="l1121">    /**</span><a href="#l1121"></a>
<span id="l1122">     * Save the state of the Hashtable to a stream (i.e., serialize it).</span><a href="#l1122"></a>
<span id="l1123">     *</span><a href="#l1123"></a>
<span id="l1124">     * @serialData The &lt;i&gt;capacity&lt;/i&gt; of the Hashtable (the length of the</span><a href="#l1124"></a>
<span id="l1125">     *             bucket array) is emitted (int), followed by the</span><a href="#l1125"></a>
<span id="l1126">     *             &lt;i&gt;size&lt;/i&gt; of the Hashtable (the number of key-value</span><a href="#l1126"></a>
<span id="l1127">     *             mappings), followed by the key (Object) and value (Object)</span><a href="#l1127"></a>
<span id="l1128">     *             for each key-value mapping represented by the Hashtable</span><a href="#l1128"></a>
<span id="l1129">     *             The key-value mappings are emitted in no particular order.</span><a href="#l1129"></a>
<span id="l1130">     */</span><a href="#l1130"></a>
<span id="l1131">    private void writeObject(java.io.ObjectOutputStream s)</span><a href="#l1131"></a>
<span id="l1132">            throws IOException {</span><a href="#l1132"></a>
<span id="l1133">        Entry&lt;Object, Object&gt; entryStack = null;</span><a href="#l1133"></a>
<span id="l1134"></span><a href="#l1134"></a>
<span id="l1135">        synchronized (this) {</span><a href="#l1135"></a>
<span id="l1136">            // Write out the threshold and loadFactor</span><a href="#l1136"></a>
<span id="l1137">            s.defaultWriteObject();</span><a href="#l1137"></a>
<span id="l1138"></span><a href="#l1138"></a>
<span id="l1139">            // Write out the length and count of elements</span><a href="#l1139"></a>
<span id="l1140">            s.writeInt(table.length);</span><a href="#l1140"></a>
<span id="l1141">            s.writeInt(count);</span><a href="#l1141"></a>
<span id="l1142"></span><a href="#l1142"></a>
<span id="l1143">            // Stack copies of the entries in the table</span><a href="#l1143"></a>
<span id="l1144">            for (int index = 0; index &lt; table.length; index++) {</span><a href="#l1144"></a>
<span id="l1145">                Entry&lt;?,?&gt; entry = table[index];</span><a href="#l1145"></a>
<span id="l1146"></span><a href="#l1146"></a>
<span id="l1147">                while (entry != null) {</span><a href="#l1147"></a>
<span id="l1148">                    entryStack =</span><a href="#l1148"></a>
<span id="l1149">                        new Entry&lt;&gt;(0, entry.key, entry.value, entryStack);</span><a href="#l1149"></a>
<span id="l1150">                    entry = entry.next;</span><a href="#l1150"></a>
<span id="l1151">                }</span><a href="#l1151"></a>
<span id="l1152">            }</span><a href="#l1152"></a>
<span id="l1153">        }</span><a href="#l1153"></a>
<span id="l1154"></span><a href="#l1154"></a>
<span id="l1155">        // Write out the key/value objects from the stacked entries</span><a href="#l1155"></a>
<span id="l1156">        while (entryStack != null) {</span><a href="#l1156"></a>
<span id="l1157">            s.writeObject(entryStack.key);</span><a href="#l1157"></a>
<span id="l1158">            s.writeObject(entryStack.value);</span><a href="#l1158"></a>
<span id="l1159">            entryStack = entryStack.next;</span><a href="#l1159"></a>
<span id="l1160">        }</span><a href="#l1160"></a>
<span id="l1161">    }</span><a href="#l1161"></a>
<span id="l1162"></span><a href="#l1162"></a>
<span id="l1163">    /**</span><a href="#l1163"></a>
<span id="l1164">     * Reconstitute the Hashtable from a stream (i.e., deserialize it).</span><a href="#l1164"></a>
<span id="l1165">     */</span><a href="#l1165"></a>
<span id="l1166">    private void readObject(ObjectInputStream s)</span><a href="#l1166"></a>
<span id="l1167">         throws IOException, ClassNotFoundException</span><a href="#l1167"></a>
<span id="l1168">    {</span><a href="#l1168"></a>
<span id="l1169"></span><a href="#l1169"></a>
<span id="l1170">        ObjectInputStream.GetField fields = s.readFields();</span><a href="#l1170"></a>
<span id="l1171"></span><a href="#l1171"></a>
<span id="l1172">        // Read and validate loadFactor (ignore threshold - it will be re-computed)</span><a href="#l1172"></a>
<span id="l1173">        float lf = fields.get(&quot;loadFactor&quot;, 0.75f);</span><a href="#l1173"></a>
<span id="l1174">        if (lf &lt;= 0 || Float.isNaN(lf))</span><a href="#l1174"></a>
<span id="l1175">            throw new StreamCorruptedException(&quot;Illegal load factor: &quot; + lf);</span><a href="#l1175"></a>
<span id="l1176">        lf = Math.min(Math.max(0.25f, lf), 4.0f);</span><a href="#l1176"></a>
<span id="l1177"></span><a href="#l1177"></a>
<span id="l1178">        // Read the original length of the array and number of elements</span><a href="#l1178"></a>
<span id="l1179">        int origlength = s.readInt();</span><a href="#l1179"></a>
<span id="l1180">        int elements = s.readInt();</span><a href="#l1180"></a>
<span id="l1181"></span><a href="#l1181"></a>
<span id="l1182">        // Validate # of elements</span><a href="#l1182"></a>
<span id="l1183">        if (elements &lt; 0)</span><a href="#l1183"></a>
<span id="l1184">            throw new StreamCorruptedException(&quot;Illegal # of Elements: &quot; + elements);</span><a href="#l1184"></a>
<span id="l1185"></span><a href="#l1185"></a>
<span id="l1186">        // Clamp original length to be more than elements / loadFactor</span><a href="#l1186"></a>
<span id="l1187">        // (this is the invariant enforced with auto-growth)</span><a href="#l1187"></a>
<span id="l1188">        origlength = Math.max(origlength, (int)(elements / lf) + 1);</span><a href="#l1188"></a>
<span id="l1189"></span><a href="#l1189"></a>
<span id="l1190">        // Compute new length with a bit of room 5% + 3 to grow but</span><a href="#l1190"></a>
<span id="l1191">        // no larger than the clamped original length.  Make the length</span><a href="#l1191"></a>
<span id="l1192">        // odd if it's large enough, this helps distribute the entries.</span><a href="#l1192"></a>
<span id="l1193">        // Guard against the length ending up zero, that's not valid.</span><a href="#l1193"></a>
<span id="l1194">        int length = (int)((elements + elements / 20) / lf) + 3;</span><a href="#l1194"></a>
<span id="l1195">        if (length &gt; elements &amp;&amp; (length &amp; 1) == 0)</span><a href="#l1195"></a>
<span id="l1196">            length--;</span><a href="#l1196"></a>
<span id="l1197">        length = Math.min(length, origlength);</span><a href="#l1197"></a>
<span id="l1198"></span><a href="#l1198"></a>
<span id="l1199">        if (length &lt; 0) { // overflow</span><a href="#l1199"></a>
<span id="l1200">            length = origlength;</span><a href="#l1200"></a>
<span id="l1201">        }</span><a href="#l1201"></a>
<span id="l1202"></span><a href="#l1202"></a>
<span id="l1203">        // Check Map.Entry[].class since it's the nearest public type to</span><a href="#l1203"></a>
<span id="l1204">        // what we're actually creating.</span><a href="#l1204"></a>
<span id="l1205">        SharedSecrets.getJavaOISAccess().checkArray(s, Map.Entry[].class, length);</span><a href="#l1205"></a>
<span id="l1206">        Hashtable.UnsafeHolder.putLoadFactor(this, lf);</span><a href="#l1206"></a>
<span id="l1207">        table = new Entry&lt;?,?&gt;[length];</span><a href="#l1207"></a>
<span id="l1208">        threshold = (int)Math.min(length * lf, MAX_ARRAY_SIZE + 1);</span><a href="#l1208"></a>
<span id="l1209">        count = 0;</span><a href="#l1209"></a>
<span id="l1210"></span><a href="#l1210"></a>
<span id="l1211">        // Read the number of elements and then all the key/value objects</span><a href="#l1211"></a>
<span id="l1212">        for (; elements &gt; 0; elements--) {</span><a href="#l1212"></a>
<span id="l1213">            @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l1213"></a>
<span id="l1214">                K key = (K)s.readObject();</span><a href="#l1214"></a>
<span id="l1215">            @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l1215"></a>
<span id="l1216">                V value = (V)s.readObject();</span><a href="#l1216"></a>
<span id="l1217">            // sync is eliminated for performance</span><a href="#l1217"></a>
<span id="l1218">            reconstitutionPut(table, key, value);</span><a href="#l1218"></a>
<span id="l1219">        }</span><a href="#l1219"></a>
<span id="l1220">    }</span><a href="#l1220"></a>
<span id="l1221"></span><a href="#l1221"></a>
<span id="l1222">    // Support for resetting final field during deserializing</span><a href="#l1222"></a>
<span id="l1223">    private static final class UnsafeHolder {</span><a href="#l1223"></a>
<span id="l1224">        private UnsafeHolder() { throw new InternalError(); }</span><a href="#l1224"></a>
<span id="l1225">        private static final sun.misc.Unsafe unsafe</span><a href="#l1225"></a>
<span id="l1226">                = sun.misc.Unsafe.getUnsafe();</span><a href="#l1226"></a>
<span id="l1227">        private static final long LF_OFFSET;</span><a href="#l1227"></a>
<span id="l1228">        static {</span><a href="#l1228"></a>
<span id="l1229">            try {</span><a href="#l1229"></a>
<span id="l1230">                LF_OFFSET = unsafe.objectFieldOffset(Hashtable.class.getDeclaredField(&quot;loadFactor&quot;));</span><a href="#l1230"></a>
<span id="l1231">            } catch (NoSuchFieldException nfe) {</span><a href="#l1231"></a>
<span id="l1232">                throw new InternalError();</span><a href="#l1232"></a>
<span id="l1233">            }</span><a href="#l1233"></a>
<span id="l1234">        }</span><a href="#l1234"></a>
<span id="l1235">        static void putLoadFactor(Hashtable&lt;?, ?&gt; table, float lf) {</span><a href="#l1235"></a>
<span id="l1236">            unsafe.putFloat(table, LF_OFFSET, lf);</span><a href="#l1236"></a>
<span id="l1237">        }</span><a href="#l1237"></a>
<span id="l1238">    }</span><a href="#l1238"></a>
<span id="l1239"></span><a href="#l1239"></a>
<span id="l1240">    /**</span><a href="#l1240"></a>
<span id="l1241">     * The put method used by readObject. This is provided because put</span><a href="#l1241"></a>
<span id="l1242">     * is overridable and should not be called in readObject since the</span><a href="#l1242"></a>
<span id="l1243">     * subclass will not yet be initialized.</span><a href="#l1243"></a>
<span id="l1244">     *</span><a href="#l1244"></a>
<span id="l1245">     * &lt;p&gt;This differs from the regular put method in several ways. No</span><a href="#l1245"></a>
<span id="l1246">     * checking for rehashing is necessary since the number of elements</span><a href="#l1246"></a>
<span id="l1247">     * initially in the table is known. The modCount is not incremented and</span><a href="#l1247"></a>
<span id="l1248">     * there's no synchronization because we are creating a new instance.</span><a href="#l1248"></a>
<span id="l1249">     * Also, no return value is needed.</span><a href="#l1249"></a>
<span id="l1250">     */</span><a href="#l1250"></a>
<span id="l1251">    private void reconstitutionPut(Entry&lt;?,?&gt;[] tab, K key, V value)</span><a href="#l1251"></a>
<span id="l1252">        throws StreamCorruptedException</span><a href="#l1252"></a>
<span id="l1253">    {</span><a href="#l1253"></a>
<span id="l1254">        if (value == null) {</span><a href="#l1254"></a>
<span id="l1255">            throw new java.io.StreamCorruptedException();</span><a href="#l1255"></a>
<span id="l1256">        }</span><a href="#l1256"></a>
<span id="l1257">        // Makes sure the key is not already in the hashtable.</span><a href="#l1257"></a>
<span id="l1258">        // This should not happen in deserialized version.</span><a href="#l1258"></a>
<span id="l1259">        int hash = key.hashCode();</span><a href="#l1259"></a>
<span id="l1260">        int index = (hash &amp; 0x7FFFFFFF) % tab.length;</span><a href="#l1260"></a>
<span id="l1261">        for (Entry&lt;?,?&gt; e = tab[index] ; e != null ; e = e.next) {</span><a href="#l1261"></a>
<span id="l1262">            if ((e.hash == hash) &amp;&amp; e.key.equals(key)) {</span><a href="#l1262"></a>
<span id="l1263">                throw new java.io.StreamCorruptedException();</span><a href="#l1263"></a>
<span id="l1264">            }</span><a href="#l1264"></a>
<span id="l1265">        }</span><a href="#l1265"></a>
<span id="l1266">        // Creates the new entry.</span><a href="#l1266"></a>
<span id="l1267">        @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l1267"></a>
<span id="l1268">            Entry&lt;K,V&gt; e = (Entry&lt;K,V&gt;)tab[index];</span><a href="#l1268"></a>
<span id="l1269">        tab[index] = new Entry&lt;&gt;(hash, key, value, e);</span><a href="#l1269"></a>
<span id="l1270">        count++;</span><a href="#l1270"></a>
<span id="l1271">    }</span><a href="#l1271"></a>
<span id="l1272"></span><a href="#l1272"></a>
<span id="l1273">    /**</span><a href="#l1273"></a>
<span id="l1274">     * Hashtable bucket collision list entry</span><a href="#l1274"></a>
<span id="l1275">     */</span><a href="#l1275"></a>
<span id="l1276">    private static class Entry&lt;K,V&gt; implements Map.Entry&lt;K,V&gt; {</span><a href="#l1276"></a>
<span id="l1277">        final int hash;</span><a href="#l1277"></a>
<span id="l1278">        final K key;</span><a href="#l1278"></a>
<span id="l1279">        V value;</span><a href="#l1279"></a>
<span id="l1280">        Entry&lt;K,V&gt; next;</span><a href="#l1280"></a>
<span id="l1281"></span><a href="#l1281"></a>
<span id="l1282">        protected Entry(int hash, K key, V value, Entry&lt;K,V&gt; next) {</span><a href="#l1282"></a>
<span id="l1283">            this.hash = hash;</span><a href="#l1283"></a>
<span id="l1284">            this.key =  key;</span><a href="#l1284"></a>
<span id="l1285">            this.value = value;</span><a href="#l1285"></a>
<span id="l1286">            this.next = next;</span><a href="#l1286"></a>
<span id="l1287">        }</span><a href="#l1287"></a>
<span id="l1288"></span><a href="#l1288"></a>
<span id="l1289">        @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l1289"></a>
<span id="l1290">        protected Object clone() {</span><a href="#l1290"></a>
<span id="l1291">            return new Entry&lt;&gt;(hash, key, value,</span><a href="#l1291"></a>
<span id="l1292">                                  (next==null ? null : (Entry&lt;K,V&gt;) next.clone()));</span><a href="#l1292"></a>
<span id="l1293">        }</span><a href="#l1293"></a>
<span id="l1294"></span><a href="#l1294"></a>
<span id="l1295">        // Map.Entry Ops</span><a href="#l1295"></a>
<span id="l1296"></span><a href="#l1296"></a>
<span id="l1297">        public K getKey() {</span><a href="#l1297"></a>
<span id="l1298">            return key;</span><a href="#l1298"></a>
<span id="l1299">        }</span><a href="#l1299"></a>
<span id="l1300"></span><a href="#l1300"></a>
<span id="l1301">        public V getValue() {</span><a href="#l1301"></a>
<span id="l1302">            return value;</span><a href="#l1302"></a>
<span id="l1303">        }</span><a href="#l1303"></a>
<span id="l1304"></span><a href="#l1304"></a>
<span id="l1305">        public V setValue(V value) {</span><a href="#l1305"></a>
<span id="l1306">            if (value == null)</span><a href="#l1306"></a>
<span id="l1307">                throw new NullPointerException();</span><a href="#l1307"></a>
<span id="l1308"></span><a href="#l1308"></a>
<span id="l1309">            V oldValue = this.value;</span><a href="#l1309"></a>
<span id="l1310">            this.value = value;</span><a href="#l1310"></a>
<span id="l1311">            return oldValue;</span><a href="#l1311"></a>
<span id="l1312">        }</span><a href="#l1312"></a>
<span id="l1313"></span><a href="#l1313"></a>
<span id="l1314">        public boolean equals(Object o) {</span><a href="#l1314"></a>
<span id="l1315">            if (!(o instanceof Map.Entry))</span><a href="#l1315"></a>
<span id="l1316">                return false;</span><a href="#l1316"></a>
<span id="l1317">            Map.Entry&lt;?,?&gt; e = (Map.Entry&lt;?,?&gt;)o;</span><a href="#l1317"></a>
<span id="l1318"></span><a href="#l1318"></a>
<span id="l1319">            return (key==null ? e.getKey()==null : key.equals(e.getKey())) &amp;&amp;</span><a href="#l1319"></a>
<span id="l1320">               (value==null ? e.getValue()==null : value.equals(e.getValue()));</span><a href="#l1320"></a>
<span id="l1321">        }</span><a href="#l1321"></a>
<span id="l1322"></span><a href="#l1322"></a>
<span id="l1323">        public int hashCode() {</span><a href="#l1323"></a>
<span id="l1324">            return hash ^ Objects.hashCode(value);</span><a href="#l1324"></a>
<span id="l1325">        }</span><a href="#l1325"></a>
<span id="l1326"></span><a href="#l1326"></a>
<span id="l1327">        public String toString() {</span><a href="#l1327"></a>
<span id="l1328">            return key.toString()+&quot;=&quot;+value.toString();</span><a href="#l1328"></a>
<span id="l1329">        }</span><a href="#l1329"></a>
<span id="l1330">    }</span><a href="#l1330"></a>
<span id="l1331"></span><a href="#l1331"></a>
<span id="l1332">    // Types of Enumerations/Iterations</span><a href="#l1332"></a>
<span id="l1333">    private static final int KEYS = 0;</span><a href="#l1333"></a>
<span id="l1334">    private static final int VALUES = 1;</span><a href="#l1334"></a>
<span id="l1335">    private static final int ENTRIES = 2;</span><a href="#l1335"></a>
<span id="l1336"></span><a href="#l1336"></a>
<span id="l1337">    /**</span><a href="#l1337"></a>
<span id="l1338">     * A hashtable enumerator class.  This class implements both the</span><a href="#l1338"></a>
<span id="l1339">     * Enumeration and Iterator interfaces, but individual instances</span><a href="#l1339"></a>
<span id="l1340">     * can be created with the Iterator methods disabled.  This is necessary</span><a href="#l1340"></a>
<span id="l1341">     * to avoid unintentionally increasing the capabilities granted a user</span><a href="#l1341"></a>
<span id="l1342">     * by passing an Enumeration.</span><a href="#l1342"></a>
<span id="l1343">     */</span><a href="#l1343"></a>
<span id="l1344">    private class Enumerator&lt;T&gt; implements Enumeration&lt;T&gt;, Iterator&lt;T&gt; {</span><a href="#l1344"></a>
<span id="l1345">        Entry&lt;?,?&gt;[] table = Hashtable.this.table;</span><a href="#l1345"></a>
<span id="l1346">        int index = table.length;</span><a href="#l1346"></a>
<span id="l1347">        Entry&lt;?,?&gt; entry;</span><a href="#l1347"></a>
<span id="l1348">        Entry&lt;?,?&gt; lastReturned;</span><a href="#l1348"></a>
<span id="l1349">        int type;</span><a href="#l1349"></a>
<span id="l1350"></span><a href="#l1350"></a>
<span id="l1351">        /**</span><a href="#l1351"></a>
<span id="l1352">         * Indicates whether this Enumerator is serving as an Iterator</span><a href="#l1352"></a>
<span id="l1353">         * or an Enumeration.  (true -&gt; Iterator).</span><a href="#l1353"></a>
<span id="l1354">         */</span><a href="#l1354"></a>
<span id="l1355">        boolean iterator;</span><a href="#l1355"></a>
<span id="l1356"></span><a href="#l1356"></a>
<span id="l1357">        /**</span><a href="#l1357"></a>
<span id="l1358">         * The modCount value that the iterator believes that the backing</span><a href="#l1358"></a>
<span id="l1359">         * Hashtable should have.  If this expectation is violated, the iterator</span><a href="#l1359"></a>
<span id="l1360">         * has detected concurrent modification.</span><a href="#l1360"></a>
<span id="l1361">         */</span><a href="#l1361"></a>
<span id="l1362">        protected int expectedModCount = modCount;</span><a href="#l1362"></a>
<span id="l1363"></span><a href="#l1363"></a>
<span id="l1364">        Enumerator(int type, boolean iterator) {</span><a href="#l1364"></a>
<span id="l1365">            this.type = type;</span><a href="#l1365"></a>
<span id="l1366">            this.iterator = iterator;</span><a href="#l1366"></a>
<span id="l1367">        }</span><a href="#l1367"></a>
<span id="l1368"></span><a href="#l1368"></a>
<span id="l1369">        public boolean hasMoreElements() {</span><a href="#l1369"></a>
<span id="l1370">            Entry&lt;?,?&gt; e = entry;</span><a href="#l1370"></a>
<span id="l1371">            int i = index;</span><a href="#l1371"></a>
<span id="l1372">            Entry&lt;?,?&gt;[] t = table;</span><a href="#l1372"></a>
<span id="l1373">            /* Use locals for faster loop iteration */</span><a href="#l1373"></a>
<span id="l1374">            while (e == null &amp;&amp; i &gt; 0) {</span><a href="#l1374"></a>
<span id="l1375">                e = t[--i];</span><a href="#l1375"></a>
<span id="l1376">            }</span><a href="#l1376"></a>
<span id="l1377">            entry = e;</span><a href="#l1377"></a>
<span id="l1378">            index = i;</span><a href="#l1378"></a>
<span id="l1379">            return e != null;</span><a href="#l1379"></a>
<span id="l1380">        }</span><a href="#l1380"></a>
<span id="l1381"></span><a href="#l1381"></a>
<span id="l1382">        @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l1382"></a>
<span id="l1383">        public T nextElement() {</span><a href="#l1383"></a>
<span id="l1384">            Entry&lt;?,?&gt; et = entry;</span><a href="#l1384"></a>
<span id="l1385">            int i = index;</span><a href="#l1385"></a>
<span id="l1386">            Entry&lt;?,?&gt;[] t = table;</span><a href="#l1386"></a>
<span id="l1387">            /* Use locals for faster loop iteration */</span><a href="#l1387"></a>
<span id="l1388">            while (et == null &amp;&amp; i &gt; 0) {</span><a href="#l1388"></a>
<span id="l1389">                et = t[--i];</span><a href="#l1389"></a>
<span id="l1390">            }</span><a href="#l1390"></a>
<span id="l1391">            entry = et;</span><a href="#l1391"></a>
<span id="l1392">            index = i;</span><a href="#l1392"></a>
<span id="l1393">            if (et != null) {</span><a href="#l1393"></a>
<span id="l1394">                Entry&lt;?,?&gt; e = lastReturned = entry;</span><a href="#l1394"></a>
<span id="l1395">                entry = e.next;</span><a href="#l1395"></a>
<span id="l1396">                return type == KEYS ? (T)e.key : (type == VALUES ? (T)e.value : (T)e);</span><a href="#l1396"></a>
<span id="l1397">            }</span><a href="#l1397"></a>
<span id="l1398">            throw new NoSuchElementException(&quot;Hashtable Enumerator&quot;);</span><a href="#l1398"></a>
<span id="l1399">        }</span><a href="#l1399"></a>
<span id="l1400"></span><a href="#l1400"></a>
<span id="l1401">        // Iterator methods</span><a href="#l1401"></a>
<span id="l1402">        public boolean hasNext() {</span><a href="#l1402"></a>
<span id="l1403">            return hasMoreElements();</span><a href="#l1403"></a>
<span id="l1404">        }</span><a href="#l1404"></a>
<span id="l1405"></span><a href="#l1405"></a>
<span id="l1406">        public T next() {</span><a href="#l1406"></a>
<span id="l1407">            if (modCount != expectedModCount)</span><a href="#l1407"></a>
<span id="l1408">                throw new ConcurrentModificationException();</span><a href="#l1408"></a>
<span id="l1409">            return nextElement();</span><a href="#l1409"></a>
<span id="l1410">        }</span><a href="#l1410"></a>
<span id="l1411"></span><a href="#l1411"></a>
<span id="l1412">        public void remove() {</span><a href="#l1412"></a>
<span id="l1413">            if (!iterator)</span><a href="#l1413"></a>
<span id="l1414">                throw new UnsupportedOperationException();</span><a href="#l1414"></a>
<span id="l1415">            if (lastReturned == null)</span><a href="#l1415"></a>
<span id="l1416">                throw new IllegalStateException(&quot;Hashtable Enumerator&quot;);</span><a href="#l1416"></a>
<span id="l1417">            if (modCount != expectedModCount)</span><a href="#l1417"></a>
<span id="l1418">                throw new ConcurrentModificationException();</span><a href="#l1418"></a>
<span id="l1419"></span><a href="#l1419"></a>
<span id="l1420">            synchronized(Hashtable.this) {</span><a href="#l1420"></a>
<span id="l1421">                Entry&lt;?,?&gt;[] tab = Hashtable.this.table;</span><a href="#l1421"></a>
<span id="l1422">                int index = (lastReturned.hash &amp; 0x7FFFFFFF) % tab.length;</span><a href="#l1422"></a>
<span id="l1423"></span><a href="#l1423"></a>
<span id="l1424">                @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l1424"></a>
<span id="l1425">                Entry&lt;K,V&gt; e = (Entry&lt;K,V&gt;)tab[index];</span><a href="#l1425"></a>
<span id="l1426">                for(Entry&lt;K,V&gt; prev = null; e != null; prev = e, e = e.next) {</span><a href="#l1426"></a>
<span id="l1427">                    if (e == lastReturned) {</span><a href="#l1427"></a>
<span id="l1428">                        modCount++;</span><a href="#l1428"></a>
<span id="l1429">                        expectedModCount++;</span><a href="#l1429"></a>
<span id="l1430">                        if (prev == null)</span><a href="#l1430"></a>
<span id="l1431">                            tab[index] = e.next;</span><a href="#l1431"></a>
<span id="l1432">                        else</span><a href="#l1432"></a>
<span id="l1433">                            prev.next = e.next;</span><a href="#l1433"></a>
<span id="l1434">                        count--;</span><a href="#l1434"></a>
<span id="l1435">                        lastReturned = null;</span><a href="#l1435"></a>
<span id="l1436">                        return;</span><a href="#l1436"></a>
<span id="l1437">                    }</span><a href="#l1437"></a>
<span id="l1438">                }</span><a href="#l1438"></a>
<span id="l1439">                throw new ConcurrentModificationException();</span><a href="#l1439"></a>
<span id="l1440">            }</span><a href="#l1440"></a>
<span id="l1441">        }</span><a href="#l1441"></a>
<span id="l1442">    }</span><a href="#l1442"></a>
<span id="l1443">}</span><a href="#l1443"></a></pre>
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

