<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: f143814b41fb src/share/classes/java/util/HashSet.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk8u/jdk8u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/shortlog/f143814b41fb">log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/graph/f143814b41fb">graph</a></li>
<li><a href="/jdk8u/jdk8u/jdk/tags">tags</a></li>
<li><a href="/jdk8u/jdk8u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/rev/f143814b41fb">changeset</a></li>
<li><a href="/jdk8u/jdk8u/jdk/file/f143814b41fb/src/share/classes/java/util/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/java/util/HashSet.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/f143814b41fb/src/share/classes/java/util/HashSet.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/f143814b41fb/src/share/classes/java/util/HashSet.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/f143814b41fb/src/share/classes/java/util/HashSet.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/f143814b41fb/src/share/classes/java/util/HashSet.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/f143814b41fb/src/share/classes/java/util/HashSet.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/java/util/HashSet.java @ 14560:f143814b41fb</h3>

<form class="search" action="/jdk8u/jdk8u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk8u/jdk8u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8266097: Better hashing support
Reviewed-by: mbalao, andrew</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#121;&#97;&#110;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Thu, 26 Aug 2021 14:33:15 +0300</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/1612ce07eb9b/src/share/classes/java/util/HashSet.java">1612ce07eb9b</a> </td>
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
<span id="l2"> * Copyright (c) 1997, 2021, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l28">import java.io.InvalidObjectException;</span><a href="#l28"></a>
<span id="l29">import sun.misc.SharedSecrets;</span><a href="#l29"></a>
<span id="l30"></span><a href="#l30"></a>
<span id="l31">/**</span><a href="#l31"></a>
<span id="l32"> * This class implements the &lt;tt&gt;Set&lt;/tt&gt; interface, backed by a hash table</span><a href="#l32"></a>
<span id="l33"> * (actually a &lt;tt&gt;HashMap&lt;/tt&gt; instance).  It makes no guarantees as to the</span><a href="#l33"></a>
<span id="l34"> * iteration order of the set; in particular, it does not guarantee that the</span><a href="#l34"></a>
<span id="l35"> * order will remain constant over time.  This class permits the &lt;tt&gt;null&lt;/tt&gt;</span><a href="#l35"></a>
<span id="l36"> * element.</span><a href="#l36"></a>
<span id="l37"> *</span><a href="#l37"></a>
<span id="l38"> * &lt;p&gt;This class offers constant time performance for the basic operations</span><a href="#l38"></a>
<span id="l39"> * (&lt;tt&gt;add&lt;/tt&gt;, &lt;tt&gt;remove&lt;/tt&gt;, &lt;tt&gt;contains&lt;/tt&gt; and &lt;tt&gt;size&lt;/tt&gt;),</span><a href="#l39"></a>
<span id="l40"> * assuming the hash function disperses the elements properly among the</span><a href="#l40"></a>
<span id="l41"> * buckets.  Iterating over this set requires time proportional to the sum of</span><a href="#l41"></a>
<span id="l42"> * the &lt;tt&gt;HashSet&lt;/tt&gt; instance's size (the number of elements) plus the</span><a href="#l42"></a>
<span id="l43"> * &quot;capacity&quot; of the backing &lt;tt&gt;HashMap&lt;/tt&gt; instance (the number of</span><a href="#l43"></a>
<span id="l44"> * buckets).  Thus, it's very important not to set the initial capacity too</span><a href="#l44"></a>
<span id="l45"> * high (or the load factor too low) if iteration performance is important.</span><a href="#l45"></a>
<span id="l46"> *</span><a href="#l46"></a>
<span id="l47"> * &lt;p&gt;&lt;strong&gt;Note that this implementation is not synchronized.&lt;/strong&gt;</span><a href="#l47"></a>
<span id="l48"> * If multiple threads access a hash set concurrently, and at least one of</span><a href="#l48"></a>
<span id="l49"> * the threads modifies the set, it &lt;i&gt;must&lt;/i&gt; be synchronized externally.</span><a href="#l49"></a>
<span id="l50"> * This is typically accomplished by synchronizing on some object that</span><a href="#l50"></a>
<span id="l51"> * naturally encapsulates the set.</span><a href="#l51"></a>
<span id="l52"> *</span><a href="#l52"></a>
<span id="l53"> * If no such object exists, the set should be &quot;wrapped&quot; using the</span><a href="#l53"></a>
<span id="l54"> * {@link Collections#synchronizedSet Collections.synchronizedSet}</span><a href="#l54"></a>
<span id="l55"> * method.  This is best done at creation time, to prevent accidental</span><a href="#l55"></a>
<span id="l56"> * unsynchronized access to the set:&lt;pre&gt;</span><a href="#l56"></a>
<span id="l57"> *   Set s = Collections.synchronizedSet(new HashSet(...));&lt;/pre&gt;</span><a href="#l57"></a>
<span id="l58"> *</span><a href="#l58"></a>
<span id="l59"> * &lt;p&gt;The iterators returned by this class's &lt;tt&gt;iterator&lt;/tt&gt; method are</span><a href="#l59"></a>
<span id="l60"> * &lt;i&gt;fail-fast&lt;/i&gt;: if the set is modified at any time after the iterator is</span><a href="#l60"></a>
<span id="l61"> * created, in any way except through the iterator's own &lt;tt&gt;remove&lt;/tt&gt;</span><a href="#l61"></a>
<span id="l62"> * method, the Iterator throws a {@link ConcurrentModificationException}.</span><a href="#l62"></a>
<span id="l63"> * Thus, in the face of concurrent modification, the iterator fails quickly</span><a href="#l63"></a>
<span id="l64"> * and cleanly, rather than risking arbitrary, non-deterministic behavior at</span><a href="#l64"></a>
<span id="l65"> * an undetermined time in the future.</span><a href="#l65"></a>
<span id="l66"> *</span><a href="#l66"></a>
<span id="l67"> * &lt;p&gt;Note that the fail-fast behavior of an iterator cannot be guaranteed</span><a href="#l67"></a>
<span id="l68"> * as it is, generally speaking, impossible to make any hard guarantees in the</span><a href="#l68"></a>
<span id="l69"> * presence of unsynchronized concurrent modification.  Fail-fast iterators</span><a href="#l69"></a>
<span id="l70"> * throw &lt;tt&gt;ConcurrentModificationException&lt;/tt&gt; on a best-effort basis.</span><a href="#l70"></a>
<span id="l71"> * Therefore, it would be wrong to write a program that depended on this</span><a href="#l71"></a>
<span id="l72"> * exception for its correctness: &lt;i&gt;the fail-fast behavior of iterators</span><a href="#l72"></a>
<span id="l73"> * should be used only to detect bugs.&lt;/i&gt;</span><a href="#l73"></a>
<span id="l74"> *</span><a href="#l74"></a>
<span id="l75"> * &lt;p&gt;This class is a member of the</span><a href="#l75"></a>
<span id="l76"> * &lt;a href=&quot;{@docRoot}/../technotes/guides/collections/index.html&quot;&gt;</span><a href="#l76"></a>
<span id="l77"> * Java Collections Framework&lt;/a&gt;.</span><a href="#l77"></a>
<span id="l78"> *</span><a href="#l78"></a>
<span id="l79"> * @param &lt;E&gt; the type of elements maintained by this set</span><a href="#l79"></a>
<span id="l80"> *</span><a href="#l80"></a>
<span id="l81"> * @author  Josh Bloch</span><a href="#l81"></a>
<span id="l82"> * @author  Neal Gafter</span><a href="#l82"></a>
<span id="l83"> * @see     Collection</span><a href="#l83"></a>
<span id="l84"> * @see     Set</span><a href="#l84"></a>
<span id="l85"> * @see     TreeSet</span><a href="#l85"></a>
<span id="l86"> * @see     HashMap</span><a href="#l86"></a>
<span id="l87"> * @since   1.2</span><a href="#l87"></a>
<span id="l88"> */</span><a href="#l88"></a>
<span id="l89"></span><a href="#l89"></a>
<span id="l90">public class HashSet&lt;E&gt;</span><a href="#l90"></a>
<span id="l91">    extends AbstractSet&lt;E&gt;</span><a href="#l91"></a>
<span id="l92">    implements Set&lt;E&gt;, Cloneable, java.io.Serializable</span><a href="#l92"></a>
<span id="l93">{</span><a href="#l93"></a>
<span id="l94">    static final long serialVersionUID = -5024744406713321676L;</span><a href="#l94"></a>
<span id="l95"></span><a href="#l95"></a>
<span id="l96">    private transient HashMap&lt;E,Object&gt; map;</span><a href="#l96"></a>
<span id="l97"></span><a href="#l97"></a>
<span id="l98">    // Dummy value to associate with an Object in the backing Map</span><a href="#l98"></a>
<span id="l99">    private static final Object PRESENT = new Object();</span><a href="#l99"></a>
<span id="l100"></span><a href="#l100"></a>
<span id="l101">    /**</span><a href="#l101"></a>
<span id="l102">     * Constructs a new, empty set; the backing &lt;tt&gt;HashMap&lt;/tt&gt; instance has</span><a href="#l102"></a>
<span id="l103">     * default initial capacity (16) and load factor (0.75).</span><a href="#l103"></a>
<span id="l104">     */</span><a href="#l104"></a>
<span id="l105">    public HashSet() {</span><a href="#l105"></a>
<span id="l106">        map = new HashMap&lt;&gt;();</span><a href="#l106"></a>
<span id="l107">    }</span><a href="#l107"></a>
<span id="l108"></span><a href="#l108"></a>
<span id="l109">    /**</span><a href="#l109"></a>
<span id="l110">     * Constructs a new set containing the elements in the specified</span><a href="#l110"></a>
<span id="l111">     * collection.  The &lt;tt&gt;HashMap&lt;/tt&gt; is created with default load factor</span><a href="#l111"></a>
<span id="l112">     * (0.75) and an initial capacity sufficient to contain the elements in</span><a href="#l112"></a>
<span id="l113">     * the specified collection.</span><a href="#l113"></a>
<span id="l114">     *</span><a href="#l114"></a>
<span id="l115">     * @param c the collection whose elements are to be placed into this set</span><a href="#l115"></a>
<span id="l116">     * @throws NullPointerException if the specified collection is null</span><a href="#l116"></a>
<span id="l117">     */</span><a href="#l117"></a>
<span id="l118">    public HashSet(Collection&lt;? extends E&gt; c) {</span><a href="#l118"></a>
<span id="l119">        map = new HashMap&lt;&gt;(Math.max((int) (c.size()/.75f) + 1, 16));</span><a href="#l119"></a>
<span id="l120">        addAll(c);</span><a href="#l120"></a>
<span id="l121">    }</span><a href="#l121"></a>
<span id="l122"></span><a href="#l122"></a>
<span id="l123">    /**</span><a href="#l123"></a>
<span id="l124">     * Constructs a new, empty set; the backing &lt;tt&gt;HashMap&lt;/tt&gt; instance has</span><a href="#l124"></a>
<span id="l125">     * the specified initial capacity and the specified load factor.</span><a href="#l125"></a>
<span id="l126">     *</span><a href="#l126"></a>
<span id="l127">     * @param      initialCapacity   the initial capacity of the hash map</span><a href="#l127"></a>
<span id="l128">     * @param      loadFactor        the load factor of the hash map</span><a href="#l128"></a>
<span id="l129">     * @throws     IllegalArgumentException if the initial capacity is less</span><a href="#l129"></a>
<span id="l130">     *             than zero, or if the load factor is nonpositive</span><a href="#l130"></a>
<span id="l131">     */</span><a href="#l131"></a>
<span id="l132">    public HashSet(int initialCapacity, float loadFactor) {</span><a href="#l132"></a>
<span id="l133">        map = new HashMap&lt;&gt;(initialCapacity, loadFactor);</span><a href="#l133"></a>
<span id="l134">    }</span><a href="#l134"></a>
<span id="l135"></span><a href="#l135"></a>
<span id="l136">    /**</span><a href="#l136"></a>
<span id="l137">     * Constructs a new, empty set; the backing &lt;tt&gt;HashMap&lt;/tt&gt; instance has</span><a href="#l137"></a>
<span id="l138">     * the specified initial capacity and default load factor (0.75).</span><a href="#l138"></a>
<span id="l139">     *</span><a href="#l139"></a>
<span id="l140">     * @param      initialCapacity   the initial capacity of the hash table</span><a href="#l140"></a>
<span id="l141">     * @throws     IllegalArgumentException if the initial capacity is less</span><a href="#l141"></a>
<span id="l142">     *             than zero</span><a href="#l142"></a>
<span id="l143">     */</span><a href="#l143"></a>
<span id="l144">    public HashSet(int initialCapacity) {</span><a href="#l144"></a>
<span id="l145">        map = new HashMap&lt;&gt;(initialCapacity);</span><a href="#l145"></a>
<span id="l146">    }</span><a href="#l146"></a>
<span id="l147"></span><a href="#l147"></a>
<span id="l148">    /**</span><a href="#l148"></a>
<span id="l149">     * Constructs a new, empty linked hash set.  (This package private</span><a href="#l149"></a>
<span id="l150">     * constructor is only used by LinkedHashSet.) The backing</span><a href="#l150"></a>
<span id="l151">     * HashMap instance is a LinkedHashMap with the specified initial</span><a href="#l151"></a>
<span id="l152">     * capacity and the specified load factor.</span><a href="#l152"></a>
<span id="l153">     *</span><a href="#l153"></a>
<span id="l154">     * @param      initialCapacity   the initial capacity of the hash map</span><a href="#l154"></a>
<span id="l155">     * @param      loadFactor        the load factor of the hash map</span><a href="#l155"></a>
<span id="l156">     * @param      dummy             ignored (distinguishes this</span><a href="#l156"></a>
<span id="l157">     *             constructor from other int, float constructor.)</span><a href="#l157"></a>
<span id="l158">     * @throws     IllegalArgumentException if the initial capacity is less</span><a href="#l158"></a>
<span id="l159">     *             than zero, or if the load factor is nonpositive</span><a href="#l159"></a>
<span id="l160">     */</span><a href="#l160"></a>
<span id="l161">    HashSet(int initialCapacity, float loadFactor, boolean dummy) {</span><a href="#l161"></a>
<span id="l162">        map = new LinkedHashMap&lt;&gt;(initialCapacity, loadFactor);</span><a href="#l162"></a>
<span id="l163">    }</span><a href="#l163"></a>
<span id="l164"></span><a href="#l164"></a>
<span id="l165">    /**</span><a href="#l165"></a>
<span id="l166">     * Returns an iterator over the elements in this set.  The elements</span><a href="#l166"></a>
<span id="l167">     * are returned in no particular order.</span><a href="#l167"></a>
<span id="l168">     *</span><a href="#l168"></a>
<span id="l169">     * @return an Iterator over the elements in this set</span><a href="#l169"></a>
<span id="l170">     * @see ConcurrentModificationException</span><a href="#l170"></a>
<span id="l171">     */</span><a href="#l171"></a>
<span id="l172">    public Iterator&lt;E&gt; iterator() {</span><a href="#l172"></a>
<span id="l173">        return map.keySet().iterator();</span><a href="#l173"></a>
<span id="l174">    }</span><a href="#l174"></a>
<span id="l175"></span><a href="#l175"></a>
<span id="l176">    /**</span><a href="#l176"></a>
<span id="l177">     * Returns the number of elements in this set (its cardinality).</span><a href="#l177"></a>
<span id="l178">     *</span><a href="#l178"></a>
<span id="l179">     * @return the number of elements in this set (its cardinality)</span><a href="#l179"></a>
<span id="l180">     */</span><a href="#l180"></a>
<span id="l181">    public int size() {</span><a href="#l181"></a>
<span id="l182">        return map.size();</span><a href="#l182"></a>
<span id="l183">    }</span><a href="#l183"></a>
<span id="l184"></span><a href="#l184"></a>
<span id="l185">    /**</span><a href="#l185"></a>
<span id="l186">     * Returns &lt;tt&gt;true&lt;/tt&gt; if this set contains no elements.</span><a href="#l186"></a>
<span id="l187">     *</span><a href="#l187"></a>
<span id="l188">     * @return &lt;tt&gt;true&lt;/tt&gt; if this set contains no elements</span><a href="#l188"></a>
<span id="l189">     */</span><a href="#l189"></a>
<span id="l190">    public boolean isEmpty() {</span><a href="#l190"></a>
<span id="l191">        return map.isEmpty();</span><a href="#l191"></a>
<span id="l192">    }</span><a href="#l192"></a>
<span id="l193"></span><a href="#l193"></a>
<span id="l194">    /**</span><a href="#l194"></a>
<span id="l195">     * Returns &lt;tt&gt;true&lt;/tt&gt; if this set contains the specified element.</span><a href="#l195"></a>
<span id="l196">     * More formally, returns &lt;tt&gt;true&lt;/tt&gt; if and only if this set</span><a href="#l196"></a>
<span id="l197">     * contains an element &lt;tt&gt;e&lt;/tt&gt; such that</span><a href="#l197"></a>
<span id="l198">     * &lt;tt&gt;(o==null&amp;nbsp;?&amp;nbsp;e==null&amp;nbsp;:&amp;nbsp;o.equals(e))&lt;/tt&gt;.</span><a href="#l198"></a>
<span id="l199">     *</span><a href="#l199"></a>
<span id="l200">     * @param o element whose presence in this set is to be tested</span><a href="#l200"></a>
<span id="l201">     * @return &lt;tt&gt;true&lt;/tt&gt; if this set contains the specified element</span><a href="#l201"></a>
<span id="l202">     */</span><a href="#l202"></a>
<span id="l203">    public boolean contains(Object o) {</span><a href="#l203"></a>
<span id="l204">        return map.containsKey(o);</span><a href="#l204"></a>
<span id="l205">    }</span><a href="#l205"></a>
<span id="l206"></span><a href="#l206"></a>
<span id="l207">    /**</span><a href="#l207"></a>
<span id="l208">     * Adds the specified element to this set if it is not already present.</span><a href="#l208"></a>
<span id="l209">     * More formally, adds the specified element &lt;tt&gt;e&lt;/tt&gt; to this set if</span><a href="#l209"></a>
<span id="l210">     * this set contains no element &lt;tt&gt;e2&lt;/tt&gt; such that</span><a href="#l210"></a>
<span id="l211">     * &lt;tt&gt;(e==null&amp;nbsp;?&amp;nbsp;e2==null&amp;nbsp;:&amp;nbsp;e.equals(e2))&lt;/tt&gt;.</span><a href="#l211"></a>
<span id="l212">     * If this set already contains the element, the call leaves the set</span><a href="#l212"></a>
<span id="l213">     * unchanged and returns &lt;tt&gt;false&lt;/tt&gt;.</span><a href="#l213"></a>
<span id="l214">     *</span><a href="#l214"></a>
<span id="l215">     * @param e element to be added to this set</span><a href="#l215"></a>
<span id="l216">     * @return &lt;tt&gt;true&lt;/tt&gt; if this set did not already contain the specified</span><a href="#l216"></a>
<span id="l217">     * element</span><a href="#l217"></a>
<span id="l218">     */</span><a href="#l218"></a>
<span id="l219">    public boolean add(E e) {</span><a href="#l219"></a>
<span id="l220">        return map.put(e, PRESENT)==null;</span><a href="#l220"></a>
<span id="l221">    }</span><a href="#l221"></a>
<span id="l222"></span><a href="#l222"></a>
<span id="l223">    /**</span><a href="#l223"></a>
<span id="l224">     * Removes the specified element from this set if it is present.</span><a href="#l224"></a>
<span id="l225">     * More formally, removes an element &lt;tt&gt;e&lt;/tt&gt; such that</span><a href="#l225"></a>
<span id="l226">     * &lt;tt&gt;(o==null&amp;nbsp;?&amp;nbsp;e==null&amp;nbsp;:&amp;nbsp;o.equals(e))&lt;/tt&gt;,</span><a href="#l226"></a>
<span id="l227">     * if this set contains such an element.  Returns &lt;tt&gt;true&lt;/tt&gt; if</span><a href="#l227"></a>
<span id="l228">     * this set contained the element (or equivalently, if this set</span><a href="#l228"></a>
<span id="l229">     * changed as a result of the call).  (This set will not contain the</span><a href="#l229"></a>
<span id="l230">     * element once the call returns.)</span><a href="#l230"></a>
<span id="l231">     *</span><a href="#l231"></a>
<span id="l232">     * @param o object to be removed from this set, if present</span><a href="#l232"></a>
<span id="l233">     * @return &lt;tt&gt;true&lt;/tt&gt; if the set contained the specified element</span><a href="#l233"></a>
<span id="l234">     */</span><a href="#l234"></a>
<span id="l235">    public boolean remove(Object o) {</span><a href="#l235"></a>
<span id="l236">        return map.remove(o)==PRESENT;</span><a href="#l236"></a>
<span id="l237">    }</span><a href="#l237"></a>
<span id="l238"></span><a href="#l238"></a>
<span id="l239">    /**</span><a href="#l239"></a>
<span id="l240">     * Removes all of the elements from this set.</span><a href="#l240"></a>
<span id="l241">     * The set will be empty after this call returns.</span><a href="#l241"></a>
<span id="l242">     */</span><a href="#l242"></a>
<span id="l243">    public void clear() {</span><a href="#l243"></a>
<span id="l244">        map.clear();</span><a href="#l244"></a>
<span id="l245">    }</span><a href="#l245"></a>
<span id="l246"></span><a href="#l246"></a>
<span id="l247">    /**</span><a href="#l247"></a>
<span id="l248">     * Returns a shallow copy of this &lt;tt&gt;HashSet&lt;/tt&gt; instance: the elements</span><a href="#l248"></a>
<span id="l249">     * themselves are not cloned.</span><a href="#l249"></a>
<span id="l250">     *</span><a href="#l250"></a>
<span id="l251">     * @return a shallow copy of this set</span><a href="#l251"></a>
<span id="l252">     */</span><a href="#l252"></a>
<span id="l253">    @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l253"></a>
<span id="l254">    public Object clone() {</span><a href="#l254"></a>
<span id="l255">        try {</span><a href="#l255"></a>
<span id="l256">            HashSet&lt;E&gt; newSet = (HashSet&lt;E&gt;) super.clone();</span><a href="#l256"></a>
<span id="l257">            newSet.map = (HashMap&lt;E, Object&gt;) map.clone();</span><a href="#l257"></a>
<span id="l258">            return newSet;</span><a href="#l258"></a>
<span id="l259">        } catch (CloneNotSupportedException e) {</span><a href="#l259"></a>
<span id="l260">            throw new InternalError(e);</span><a href="#l260"></a>
<span id="l261">        }</span><a href="#l261"></a>
<span id="l262">    }</span><a href="#l262"></a>
<span id="l263"></span><a href="#l263"></a>
<span id="l264">    /**</span><a href="#l264"></a>
<span id="l265">     * Save the state of this &lt;tt&gt;HashSet&lt;/tt&gt; instance to a stream (that is,</span><a href="#l265"></a>
<span id="l266">     * serialize it).</span><a href="#l266"></a>
<span id="l267">     *</span><a href="#l267"></a>
<span id="l268">     * @serialData The capacity of the backing &lt;tt&gt;HashMap&lt;/tt&gt; instance</span><a href="#l268"></a>
<span id="l269">     *             (int), and its load factor (float) are emitted, followed by</span><a href="#l269"></a>
<span id="l270">     *             the size of the set (the number of elements it contains)</span><a href="#l270"></a>
<span id="l271">     *             (int), followed by all of its elements (each an Object) in</span><a href="#l271"></a>
<span id="l272">     *             no particular order.</span><a href="#l272"></a>
<span id="l273">     */</span><a href="#l273"></a>
<span id="l274">    private void writeObject(java.io.ObjectOutputStream s)</span><a href="#l274"></a>
<span id="l275">        throws java.io.IOException {</span><a href="#l275"></a>
<span id="l276">        // Write out any hidden serialization magic</span><a href="#l276"></a>
<span id="l277">        s.defaultWriteObject();</span><a href="#l277"></a>
<span id="l278"></span><a href="#l278"></a>
<span id="l279">        // Write out HashMap capacity and load factor</span><a href="#l279"></a>
<span id="l280">        s.writeInt(map.capacity());</span><a href="#l280"></a>
<span id="l281">        s.writeFloat(map.loadFactor());</span><a href="#l281"></a>
<span id="l282"></span><a href="#l282"></a>
<span id="l283">        // Write out size</span><a href="#l283"></a>
<span id="l284">        s.writeInt(map.size());</span><a href="#l284"></a>
<span id="l285"></span><a href="#l285"></a>
<span id="l286">        // Write out all elements in the proper order.</span><a href="#l286"></a>
<span id="l287">        for (E e : map.keySet())</span><a href="#l287"></a>
<span id="l288">            s.writeObject(e);</span><a href="#l288"></a>
<span id="l289">    }</span><a href="#l289"></a>
<span id="l290"></span><a href="#l290"></a>
<span id="l291">    /**</span><a href="#l291"></a>
<span id="l292">     * Reconstitute the &lt;tt&gt;HashSet&lt;/tt&gt; instance from a stream (that is,</span><a href="#l292"></a>
<span id="l293">     * deserialize it).</span><a href="#l293"></a>
<span id="l294">     */</span><a href="#l294"></a>
<span id="l295">    private void readObject(java.io.ObjectInputStream s)</span><a href="#l295"></a>
<span id="l296">        throws java.io.IOException, ClassNotFoundException {</span><a href="#l296"></a>
<span id="l297">        // Consume and ignore stream fields (currently zero).</span><a href="#l297"></a>
<span id="l298">        s.readFields();</span><a href="#l298"></a>
<span id="l299"></span><a href="#l299"></a>
<span id="l300">        // Read capacity and verify non-negative.</span><a href="#l300"></a>
<span id="l301">        int capacity = s.readInt();</span><a href="#l301"></a>
<span id="l302">        if (capacity &lt; 0) {</span><a href="#l302"></a>
<span id="l303">            throw new InvalidObjectException(&quot;Illegal capacity: &quot; +</span><a href="#l303"></a>
<span id="l304">                                             capacity);</span><a href="#l304"></a>
<span id="l305">        }</span><a href="#l305"></a>
<span id="l306"></span><a href="#l306"></a>
<span id="l307">        // Read load factor and verify positive and non NaN.</span><a href="#l307"></a>
<span id="l308">        float loadFactor = s.readFloat();</span><a href="#l308"></a>
<span id="l309">        if (loadFactor &lt;= 0 || Float.isNaN(loadFactor)) {</span><a href="#l309"></a>
<span id="l310">            throw new InvalidObjectException(&quot;Illegal load factor: &quot; +</span><a href="#l310"></a>
<span id="l311">                                             loadFactor);</span><a href="#l311"></a>
<span id="l312">        }</span><a href="#l312"></a>
<span id="l313">        // Clamp load factor to range of 0.25...4.0.</span><a href="#l313"></a>
<span id="l314">        loadFactor = Math.min(Math.max(0.25f, loadFactor), 4.0f);</span><a href="#l314"></a>
<span id="l315"></span><a href="#l315"></a>
<span id="l316">        // Read size and verify non-negative.</span><a href="#l316"></a>
<span id="l317">        int size = s.readInt();</span><a href="#l317"></a>
<span id="l318">        if (size &lt; 0) {</span><a href="#l318"></a>
<span id="l319">            throw new InvalidObjectException(&quot;Illegal size: &quot; + size);</span><a href="#l319"></a>
<span id="l320">        }</span><a href="#l320"></a>
<span id="l321">        // Set the capacity according to the size and load factor ensuring that</span><a href="#l321"></a>
<span id="l322">        // the HashMap is at least 25% full but clamping to maximum capacity.</span><a href="#l322"></a>
<span id="l323">        capacity = (int) Math.min(size * Math.min(1 / loadFactor, 4.0f),</span><a href="#l323"></a>
<span id="l324">                HashMap.MAXIMUM_CAPACITY);</span><a href="#l324"></a>
<span id="l325"></span><a href="#l325"></a>
<span id="l326">        // Constructing the backing map will lazily create an array when the first element is</span><a href="#l326"></a>
<span id="l327">        // added, so check it before construction. Call HashMap.tableSizeFor to compute the</span><a href="#l327"></a>
<span id="l328">        // actual allocation size. Check Map.Entry[].class since it's the nearest public type to</span><a href="#l328"></a>
<span id="l329">        // what is actually created.</span><a href="#l329"></a>
<span id="l330"></span><a href="#l330"></a>
<span id="l331">        SharedSecrets.getJavaOISAccess()</span><a href="#l331"></a>
<span id="l332">                     .checkArray(s, Map.Entry[].class, HashMap.tableSizeFor(capacity));</span><a href="#l332"></a>
<span id="l333"></span><a href="#l333"></a>
<span id="l334">        // Create backing HashMap</span><a href="#l334"></a>
<span id="l335">        map = (((HashSet&lt;?&gt;)this) instanceof LinkedHashSet ?</span><a href="#l335"></a>
<span id="l336">               new LinkedHashMap&lt;E,Object&gt;(capacity, loadFactor) :</span><a href="#l336"></a>
<span id="l337">               new HashMap&lt;E,Object&gt;(capacity, loadFactor));</span><a href="#l337"></a>
<span id="l338"></span><a href="#l338"></a>
<span id="l339">        // Read in all elements in the proper order.</span><a href="#l339"></a>
<span id="l340">        for (int i=0; i&lt;size; i++) {</span><a href="#l340"></a>
<span id="l341">            @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l341"></a>
<span id="l342">                E e = (E) s.readObject();</span><a href="#l342"></a>
<span id="l343">            map.put(e, PRESENT);</span><a href="#l343"></a>
<span id="l344">        }</span><a href="#l344"></a>
<span id="l345">    }</span><a href="#l345"></a>
<span id="l346"></span><a href="#l346"></a>
<span id="l347">    /**</span><a href="#l347"></a>
<span id="l348">     * Creates a &lt;em&gt;&lt;a href=&quot;Spliterator.html#binding&quot;&gt;late-binding&lt;/a&gt;&lt;/em&gt;</span><a href="#l348"></a>
<span id="l349">     * and &lt;em&gt;fail-fast&lt;/em&gt; {@link Spliterator} over the elements in this</span><a href="#l349"></a>
<span id="l350">     * set.</span><a href="#l350"></a>
<span id="l351">     *</span><a href="#l351"></a>
<span id="l352">     * &lt;p&gt;The {@code Spliterator} reports {@link Spliterator#SIZED} and</span><a href="#l352"></a>
<span id="l353">     * {@link Spliterator#DISTINCT}.  Overriding implementations should document</span><a href="#l353"></a>
<span id="l354">     * the reporting of additional characteristic values.</span><a href="#l354"></a>
<span id="l355">     *</span><a href="#l355"></a>
<span id="l356">     * @return a {@code Spliterator} over the elements in this set</span><a href="#l356"></a>
<span id="l357">     * @since 1.8</span><a href="#l357"></a>
<span id="l358">     */</span><a href="#l358"></a>
<span id="l359">    public Spliterator&lt;E&gt; spliterator() {</span><a href="#l359"></a>
<span id="l360">        return new HashMap.KeySpliterator&lt;E,Object&gt;(map, 0, -1, 0, 0);</span><a href="#l360"></a>
<span id="l361">    }</span><a href="#l361"></a>
<span id="l362">}</span><a href="#l362"></a></pre>
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

