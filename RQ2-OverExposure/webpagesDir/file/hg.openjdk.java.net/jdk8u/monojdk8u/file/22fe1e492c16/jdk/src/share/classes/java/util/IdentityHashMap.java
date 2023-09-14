<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/monojdk8u/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/monojdk8u/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/monojdk8u/static/mercurial.js"></script>

<title>jdk8u/monojdk8u: 22fe1e492c16 jdk/src/share/classes/java/util/IdentityHashMap.java</title>
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
<li><a href="/jdk8u/monojdk8u/file/tip/jdk/src/share/classes/java/util/IdentityHashMap.java">latest</a></li>
<li><a href="/jdk8u/monojdk8u/diff/22fe1e492c16/jdk/src/share/classes/java/util/IdentityHashMap.java">diff</a></li>
<li><a href="/jdk8u/monojdk8u/comparison/22fe1e492c16/jdk/src/share/classes/java/util/IdentityHashMap.java">comparison</a></li>
<li><a href="/jdk8u/monojdk8u/annotate/22fe1e492c16/jdk/src/share/classes/java/util/IdentityHashMap.java">annotate</a></li>
<li><a href="/jdk8u/monojdk8u/log/22fe1e492c16/jdk/src/share/classes/java/util/IdentityHashMap.java">file log</a></li>
<li><a href="/jdk8u/monojdk8u/raw-file/22fe1e492c16/jdk/src/share/classes/java/util/IdentityHashMap.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/monojdk8u/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/monojdk8u">monojdk8u</a> </h2>
<h3>view jdk/src/share/classes/java/util/IdentityHashMap.java @ 48810:22fe1e492c16</h3>

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
 <td class="author"><a href="/jdk8u/monojdk8u/file/663307395fc9/jdk/src/share/classes/java/util/IdentityHashMap.java">663307395fc9</a> </td>
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
<span id="l2"> * Copyright (c) 2000, 2021, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l28">import java.io.ObjectInputStream;</span><a href="#l28"></a>
<span id="l29">import java.io.ObjectOutputStream;</span><a href="#l29"></a>
<span id="l30">import java.lang.reflect.Array;</span><a href="#l30"></a>
<span id="l31">import java.util.function.BiConsumer;</span><a href="#l31"></a>
<span id="l32">import java.util.function.BiFunction;</span><a href="#l32"></a>
<span id="l33">import java.util.function.Consumer;</span><a href="#l33"></a>
<span id="l34">import sun.misc.SharedSecrets;</span><a href="#l34"></a>
<span id="l35"></span><a href="#l35"></a>
<span id="l36">/**</span><a href="#l36"></a>
<span id="l37"> * This class implements the &lt;tt&gt;Map&lt;/tt&gt; interface with a hash table, using</span><a href="#l37"></a>
<span id="l38"> * reference-equality in place of object-equality when comparing keys (and</span><a href="#l38"></a>
<span id="l39"> * values).  In other words, in an &lt;tt&gt;IdentityHashMap&lt;/tt&gt;, two keys</span><a href="#l39"></a>
<span id="l40"> * &lt;tt&gt;k1&lt;/tt&gt; and &lt;tt&gt;k2&lt;/tt&gt; are considered equal if and only if</span><a href="#l40"></a>
<span id="l41"> * &lt;tt&gt;(k1==k2)&lt;/tt&gt;.  (In normal &lt;tt&gt;Map&lt;/tt&gt; implementations (like</span><a href="#l41"></a>
<span id="l42"> * &lt;tt&gt;HashMap&lt;/tt&gt;) two keys &lt;tt&gt;k1&lt;/tt&gt; and &lt;tt&gt;k2&lt;/tt&gt; are considered equal</span><a href="#l42"></a>
<span id="l43"> * if and only if &lt;tt&gt;(k1==null ? k2==null : k1.equals(k2))&lt;/tt&gt;.)</span><a href="#l43"></a>
<span id="l44"> *</span><a href="#l44"></a>
<span id="l45"> * &lt;p&gt;&lt;b&gt;This class is &lt;i&gt;not&lt;/i&gt; a general-purpose &lt;tt&gt;Map&lt;/tt&gt;</span><a href="#l45"></a>
<span id="l46"> * implementation!  While this class implements the &lt;tt&gt;Map&lt;/tt&gt; interface, it</span><a href="#l46"></a>
<span id="l47"> * intentionally violates &lt;tt&gt;Map's&lt;/tt&gt; general contract, which mandates the</span><a href="#l47"></a>
<span id="l48"> * use of the &lt;tt&gt;equals&lt;/tt&gt; method when comparing objects.  This class is</span><a href="#l48"></a>
<span id="l49"> * designed for use only in the rare cases wherein reference-equality</span><a href="#l49"></a>
<span id="l50"> * semantics are required.&lt;/b&gt;</span><a href="#l50"></a>
<span id="l51"> *</span><a href="#l51"></a>
<span id="l52"> * &lt;p&gt;A typical use of this class is &lt;i&gt;topology-preserving object graph</span><a href="#l52"></a>
<span id="l53"> * transformations&lt;/i&gt;, such as serialization or deep-copying.  To perform such</span><a href="#l53"></a>
<span id="l54"> * a transformation, a program must maintain a &quot;node table&quot; that keeps track</span><a href="#l54"></a>
<span id="l55"> * of all the object references that have already been processed.  The node</span><a href="#l55"></a>
<span id="l56"> * table must not equate distinct objects even if they happen to be equal.</span><a href="#l56"></a>
<span id="l57"> * Another typical use of this class is to maintain &lt;i&gt;proxy objects&lt;/i&gt;.  For</span><a href="#l57"></a>
<span id="l58"> * example, a debugging facility might wish to maintain a proxy object for</span><a href="#l58"></a>
<span id="l59"> * each object in the program being debugged.</span><a href="#l59"></a>
<span id="l60"> *</span><a href="#l60"></a>
<span id="l61"> * &lt;p&gt;This class provides all of the optional map operations, and permits</span><a href="#l61"></a>
<span id="l62"> * &lt;tt&gt;null&lt;/tt&gt; values and the &lt;tt&gt;null&lt;/tt&gt; key.  This class makes no</span><a href="#l62"></a>
<span id="l63"> * guarantees as to the order of the map; in particular, it does not guarantee</span><a href="#l63"></a>
<span id="l64"> * that the order will remain constant over time.</span><a href="#l64"></a>
<span id="l65"> *</span><a href="#l65"></a>
<span id="l66"> * &lt;p&gt;This class provides constant-time performance for the basic</span><a href="#l66"></a>
<span id="l67"> * operations (&lt;tt&gt;get&lt;/tt&gt; and &lt;tt&gt;put&lt;/tt&gt;), assuming the system</span><a href="#l67"></a>
<span id="l68"> * identity hash function ({@link System#identityHashCode(Object)})</span><a href="#l68"></a>
<span id="l69"> * disperses elements properly among the buckets.</span><a href="#l69"></a>
<span id="l70"> *</span><a href="#l70"></a>
<span id="l71"> * &lt;p&gt;This class has one tuning parameter (which affects performance but not</span><a href="#l71"></a>
<span id="l72"> * semantics): &lt;i&gt;expected maximum size&lt;/i&gt;.  This parameter is the maximum</span><a href="#l72"></a>
<span id="l73"> * number of key-value mappings that the map is expected to hold.  Internally,</span><a href="#l73"></a>
<span id="l74"> * this parameter is used to determine the number of buckets initially</span><a href="#l74"></a>
<span id="l75"> * comprising the hash table.  The precise relationship between the expected</span><a href="#l75"></a>
<span id="l76"> * maximum size and the number of buckets is unspecified.</span><a href="#l76"></a>
<span id="l77"> *</span><a href="#l77"></a>
<span id="l78"> * &lt;p&gt;If the size of the map (the number of key-value mappings) sufficiently</span><a href="#l78"></a>
<span id="l79"> * exceeds the expected maximum size, the number of buckets is increased.</span><a href="#l79"></a>
<span id="l80"> * Increasing the number of buckets (&quot;rehashing&quot;) may be fairly expensive, so</span><a href="#l80"></a>
<span id="l81"> * it pays to create identity hash maps with a sufficiently large expected</span><a href="#l81"></a>
<span id="l82"> * maximum size.  On the other hand, iteration over collection views requires</span><a href="#l82"></a>
<span id="l83"> * time proportional to the number of buckets in the hash table, so it</span><a href="#l83"></a>
<span id="l84"> * pays not to set the expected maximum size too high if you are especially</span><a href="#l84"></a>
<span id="l85"> * concerned with iteration performance or memory usage.</span><a href="#l85"></a>
<span id="l86"> *</span><a href="#l86"></a>
<span id="l87"> * &lt;p&gt;&lt;strong&gt;Note that this implementation is not synchronized.&lt;/strong&gt;</span><a href="#l87"></a>
<span id="l88"> * If multiple threads access an identity hash map concurrently, and at</span><a href="#l88"></a>
<span id="l89"> * least one of the threads modifies the map structurally, it &lt;i&gt;must&lt;/i&gt;</span><a href="#l89"></a>
<span id="l90"> * be synchronized externally.  (A structural modification is any operation</span><a href="#l90"></a>
<span id="l91"> * that adds or deletes one or more mappings; merely changing the value</span><a href="#l91"></a>
<span id="l92"> * associated with a key that an instance already contains is not a</span><a href="#l92"></a>
<span id="l93"> * structural modification.)  This is typically accomplished by</span><a href="#l93"></a>
<span id="l94"> * synchronizing on some object that naturally encapsulates the map.</span><a href="#l94"></a>
<span id="l95"> *</span><a href="#l95"></a>
<span id="l96"> * If no such object exists, the map should be &quot;wrapped&quot; using the</span><a href="#l96"></a>
<span id="l97"> * {@link Collections#synchronizedMap Collections.synchronizedMap}</span><a href="#l97"></a>
<span id="l98"> * method.  This is best done at creation time, to prevent accidental</span><a href="#l98"></a>
<span id="l99"> * unsynchronized access to the map:&lt;pre&gt;</span><a href="#l99"></a>
<span id="l100"> *   Map m = Collections.synchronizedMap(new IdentityHashMap(...));&lt;/pre&gt;</span><a href="#l100"></a>
<span id="l101"> *</span><a href="#l101"></a>
<span id="l102"> * &lt;p&gt;The iterators returned by the &lt;tt&gt;iterator&lt;/tt&gt; method of the</span><a href="#l102"></a>
<span id="l103"> * collections returned by all of this class's &quot;collection view</span><a href="#l103"></a>
<span id="l104"> * methods&quot; are &lt;i&gt;fail-fast&lt;/i&gt;: if the map is structurally modified</span><a href="#l104"></a>
<span id="l105"> * at any time after the iterator is created, in any way except</span><a href="#l105"></a>
<span id="l106"> * through the iterator's own &lt;tt&gt;remove&lt;/tt&gt; method, the iterator</span><a href="#l106"></a>
<span id="l107"> * will throw a {@link ConcurrentModificationException}.  Thus, in the</span><a href="#l107"></a>
<span id="l108"> * face of concurrent modification, the iterator fails quickly and</span><a href="#l108"></a>
<span id="l109"> * cleanly, rather than risking arbitrary, non-deterministic behavior</span><a href="#l109"></a>
<span id="l110"> * at an undetermined time in the future.</span><a href="#l110"></a>
<span id="l111"> *</span><a href="#l111"></a>
<span id="l112"> * &lt;p&gt;Note that the fail-fast behavior of an iterator cannot be guaranteed</span><a href="#l112"></a>
<span id="l113"> * as it is, generally speaking, impossible to make any hard guarantees in the</span><a href="#l113"></a>
<span id="l114"> * presence of unsynchronized concurrent modification.  Fail-fast iterators</span><a href="#l114"></a>
<span id="l115"> * throw &lt;tt&gt;ConcurrentModificationException&lt;/tt&gt; on a best-effort basis.</span><a href="#l115"></a>
<span id="l116"> * Therefore, it would be wrong to write a program that depended on this</span><a href="#l116"></a>
<span id="l117"> * exception for its correctness: &lt;i&gt;fail-fast iterators should be used only</span><a href="#l117"></a>
<span id="l118"> * to detect bugs.&lt;/i&gt;</span><a href="#l118"></a>
<span id="l119"> *</span><a href="#l119"></a>
<span id="l120"> * &lt;p&gt;Implementation note: This is a simple &lt;i&gt;linear-probe&lt;/i&gt; hash table,</span><a href="#l120"></a>
<span id="l121"> * as described for example in texts by Sedgewick and Knuth.  The array</span><a href="#l121"></a>
<span id="l122"> * alternates holding keys and values.  (This has better locality for large</span><a href="#l122"></a>
<span id="l123"> * tables than does using separate arrays.)  For many JRE implementations</span><a href="#l123"></a>
<span id="l124"> * and operation mixes, this class will yield better performance than</span><a href="#l124"></a>
<span id="l125"> * {@link HashMap} (which uses &lt;i&gt;chaining&lt;/i&gt; rather than linear-probing).</span><a href="#l125"></a>
<span id="l126"> *</span><a href="#l126"></a>
<span id="l127"> * &lt;p&gt;This class is a member of the</span><a href="#l127"></a>
<span id="l128"> * &lt;a href=&quot;{@docRoot}/../technotes/guides/collections/index.html&quot;&gt;</span><a href="#l128"></a>
<span id="l129"> * Java Collections Framework&lt;/a&gt;.</span><a href="#l129"></a>
<span id="l130"> *</span><a href="#l130"></a>
<span id="l131"> * @see     System#identityHashCode(Object)</span><a href="#l131"></a>
<span id="l132"> * @see     Object#hashCode()</span><a href="#l132"></a>
<span id="l133"> * @see     Collection</span><a href="#l133"></a>
<span id="l134"> * @see     Map</span><a href="#l134"></a>
<span id="l135"> * @see     HashMap</span><a href="#l135"></a>
<span id="l136"> * @see     TreeMap</span><a href="#l136"></a>
<span id="l137"> * @author  Doug Lea and Josh Bloch</span><a href="#l137"></a>
<span id="l138"> * @since   1.4</span><a href="#l138"></a>
<span id="l139"> */</span><a href="#l139"></a>
<span id="l140"></span><a href="#l140"></a>
<span id="l141">public class IdentityHashMap&lt;K,V&gt;</span><a href="#l141"></a>
<span id="l142">    extends AbstractMap&lt;K,V&gt;</span><a href="#l142"></a>
<span id="l143">    implements Map&lt;K,V&gt;, java.io.Serializable, Cloneable</span><a href="#l143"></a>
<span id="l144">{</span><a href="#l144"></a>
<span id="l145">    /**</span><a href="#l145"></a>
<span id="l146">     * The initial capacity used by the no-args constructor.</span><a href="#l146"></a>
<span id="l147">     * MUST be a power of two.  The value 32 corresponds to the</span><a href="#l147"></a>
<span id="l148">     * (specified) expected maximum size of 21, given a load factor</span><a href="#l148"></a>
<span id="l149">     * of 2/3.</span><a href="#l149"></a>
<span id="l150">     */</span><a href="#l150"></a>
<span id="l151">    private static final int DEFAULT_CAPACITY = 32;</span><a href="#l151"></a>
<span id="l152"></span><a href="#l152"></a>
<span id="l153">    /**</span><a href="#l153"></a>
<span id="l154">     * The minimum capacity, used if a lower value is implicitly specified</span><a href="#l154"></a>
<span id="l155">     * by either of the constructors with arguments.  The value 4 corresponds</span><a href="#l155"></a>
<span id="l156">     * to an expected maximum size of 2, given a load factor of 2/3.</span><a href="#l156"></a>
<span id="l157">     * MUST be a power of two.</span><a href="#l157"></a>
<span id="l158">     */</span><a href="#l158"></a>
<span id="l159">    private static final int MINIMUM_CAPACITY = 4;</span><a href="#l159"></a>
<span id="l160"></span><a href="#l160"></a>
<span id="l161">    /**</span><a href="#l161"></a>
<span id="l162">     * The maximum capacity, used if a higher value is implicitly specified</span><a href="#l162"></a>
<span id="l163">     * by either of the constructors with arguments.</span><a href="#l163"></a>
<span id="l164">     * MUST be a power of two &lt;= 1&lt;&lt;29.</span><a href="#l164"></a>
<span id="l165">     *</span><a href="#l165"></a>
<span id="l166">     * In fact, the map can hold no more than MAXIMUM_CAPACITY-1 items</span><a href="#l166"></a>
<span id="l167">     * because it has to have at least one slot with the key == null</span><a href="#l167"></a>
<span id="l168">     * in order to avoid infinite loops in get(), put(), remove()</span><a href="#l168"></a>
<span id="l169">     */</span><a href="#l169"></a>
<span id="l170">    private static final int MAXIMUM_CAPACITY = 1 &lt;&lt; 29;</span><a href="#l170"></a>
<span id="l171"></span><a href="#l171"></a>
<span id="l172">    /**</span><a href="#l172"></a>
<span id="l173">     * The table, resized as necessary. Length MUST always be a power of two.</span><a href="#l173"></a>
<span id="l174">     */</span><a href="#l174"></a>
<span id="l175">    transient Object[] table; // non-private to simplify nested class access</span><a href="#l175"></a>
<span id="l176"></span><a href="#l176"></a>
<span id="l177">    /**</span><a href="#l177"></a>
<span id="l178">     * The number of key-value mappings contained in this identity hash map.</span><a href="#l178"></a>
<span id="l179">     *</span><a href="#l179"></a>
<span id="l180">     * @serial</span><a href="#l180"></a>
<span id="l181">     */</span><a href="#l181"></a>
<span id="l182">    int size;</span><a href="#l182"></a>
<span id="l183"></span><a href="#l183"></a>
<span id="l184">    /**</span><a href="#l184"></a>
<span id="l185">     * The number of modifications, to support fast-fail iterators</span><a href="#l185"></a>
<span id="l186">     */</span><a href="#l186"></a>
<span id="l187">    transient int modCount;</span><a href="#l187"></a>
<span id="l188"></span><a href="#l188"></a>
<span id="l189">    /**</span><a href="#l189"></a>
<span id="l190">     * Value representing null keys inside tables.</span><a href="#l190"></a>
<span id="l191">     */</span><a href="#l191"></a>
<span id="l192">    static final Object NULL_KEY = new Object();</span><a href="#l192"></a>
<span id="l193"></span><a href="#l193"></a>
<span id="l194">    /**</span><a href="#l194"></a>
<span id="l195">     * Use NULL_KEY for key if it is null.</span><a href="#l195"></a>
<span id="l196">     */</span><a href="#l196"></a>
<span id="l197">    private static Object maskNull(Object key) {</span><a href="#l197"></a>
<span id="l198">        return (key == null ? NULL_KEY : key);</span><a href="#l198"></a>
<span id="l199">    }</span><a href="#l199"></a>
<span id="l200"></span><a href="#l200"></a>
<span id="l201">    /**</span><a href="#l201"></a>
<span id="l202">     * Returns internal representation of null key back to caller as null.</span><a href="#l202"></a>
<span id="l203">     */</span><a href="#l203"></a>
<span id="l204">    static final Object unmaskNull(Object key) {</span><a href="#l204"></a>
<span id="l205">        return (key == NULL_KEY ? null : key);</span><a href="#l205"></a>
<span id="l206">    }</span><a href="#l206"></a>
<span id="l207"></span><a href="#l207"></a>
<span id="l208">    /**</span><a href="#l208"></a>
<span id="l209">     * Constructs a new, empty identity hash map with a default expected</span><a href="#l209"></a>
<span id="l210">     * maximum size (21).</span><a href="#l210"></a>
<span id="l211">     */</span><a href="#l211"></a>
<span id="l212">    public IdentityHashMap() {</span><a href="#l212"></a>
<span id="l213">        init(DEFAULT_CAPACITY);</span><a href="#l213"></a>
<span id="l214">    }</span><a href="#l214"></a>
<span id="l215"></span><a href="#l215"></a>
<span id="l216">    /**</span><a href="#l216"></a>
<span id="l217">     * Constructs a new, empty map with the specified expected maximum size.</span><a href="#l217"></a>
<span id="l218">     * Putting more than the expected number of key-value mappings into</span><a href="#l218"></a>
<span id="l219">     * the map may cause the internal data structure to grow, which may be</span><a href="#l219"></a>
<span id="l220">     * somewhat time-consuming.</span><a href="#l220"></a>
<span id="l221">     *</span><a href="#l221"></a>
<span id="l222">     * @param expectedMaxSize the expected maximum size of the map</span><a href="#l222"></a>
<span id="l223">     * @throws IllegalArgumentException if &lt;tt&gt;expectedMaxSize&lt;/tt&gt; is negative</span><a href="#l223"></a>
<span id="l224">     */</span><a href="#l224"></a>
<span id="l225">    public IdentityHashMap(int expectedMaxSize) {</span><a href="#l225"></a>
<span id="l226">        if (expectedMaxSize &lt; 0)</span><a href="#l226"></a>
<span id="l227">            throw new IllegalArgumentException(&quot;expectedMaxSize is negative: &quot;</span><a href="#l227"></a>
<span id="l228">                                               + expectedMaxSize);</span><a href="#l228"></a>
<span id="l229">        init(capacity(expectedMaxSize));</span><a href="#l229"></a>
<span id="l230">    }</span><a href="#l230"></a>
<span id="l231"></span><a href="#l231"></a>
<span id="l232">    /**</span><a href="#l232"></a>
<span id="l233">     * Returns the appropriate capacity for the given expected maximum size.</span><a href="#l233"></a>
<span id="l234">     * Returns the smallest power of two between MINIMUM_CAPACITY and</span><a href="#l234"></a>
<span id="l235">     * MAXIMUM_CAPACITY, inclusive, that is greater than (3 *</span><a href="#l235"></a>
<span id="l236">     * expectedMaxSize)/2, if such a number exists.  Otherwise returns</span><a href="#l236"></a>
<span id="l237">     * MAXIMUM_CAPACITY.</span><a href="#l237"></a>
<span id="l238">     */</span><a href="#l238"></a>
<span id="l239">    private static int capacity(int expectedMaxSize) {</span><a href="#l239"></a>
<span id="l240">        // assert expectedMaxSize &gt;= 0;</span><a href="#l240"></a>
<span id="l241">        return</span><a href="#l241"></a>
<span id="l242">            (expectedMaxSize &gt; MAXIMUM_CAPACITY / 3) ? MAXIMUM_CAPACITY :</span><a href="#l242"></a>
<span id="l243">            (expectedMaxSize &lt;= 2 * MINIMUM_CAPACITY / 3) ? MINIMUM_CAPACITY :</span><a href="#l243"></a>
<span id="l244">            Integer.highestOneBit(expectedMaxSize + (expectedMaxSize &lt;&lt; 1));</span><a href="#l244"></a>
<span id="l245">    }</span><a href="#l245"></a>
<span id="l246"></span><a href="#l246"></a>
<span id="l247">    /**</span><a href="#l247"></a>
<span id="l248">     * Initializes object to be an empty map with the specified initial</span><a href="#l248"></a>
<span id="l249">     * capacity, which is assumed to be a power of two between</span><a href="#l249"></a>
<span id="l250">     * MINIMUM_CAPACITY and MAXIMUM_CAPACITY inclusive.</span><a href="#l250"></a>
<span id="l251">     */</span><a href="#l251"></a>
<span id="l252">    private void init(int initCapacity) {</span><a href="#l252"></a>
<span id="l253">        // assert (initCapacity &amp; -initCapacity) == initCapacity; // power of 2</span><a href="#l253"></a>
<span id="l254">        // assert initCapacity &gt;= MINIMUM_CAPACITY;</span><a href="#l254"></a>
<span id="l255">        // assert initCapacity &lt;= MAXIMUM_CAPACITY;</span><a href="#l255"></a>
<span id="l256"></span><a href="#l256"></a>
<span id="l257">        table = new Object[2 * initCapacity];</span><a href="#l257"></a>
<span id="l258">    }</span><a href="#l258"></a>
<span id="l259"></span><a href="#l259"></a>
<span id="l260">    /**</span><a href="#l260"></a>
<span id="l261">     * Constructs a new identity hash map containing the keys-value mappings</span><a href="#l261"></a>
<span id="l262">     * in the specified map.</span><a href="#l262"></a>
<span id="l263">     *</span><a href="#l263"></a>
<span id="l264">     * @param m the map whose mappings are to be placed into this map</span><a href="#l264"></a>
<span id="l265">     * @throws NullPointerException if the specified map is null</span><a href="#l265"></a>
<span id="l266">     */</span><a href="#l266"></a>
<span id="l267">    public IdentityHashMap(Map&lt;? extends K, ? extends V&gt; m) {</span><a href="#l267"></a>
<span id="l268">        // Allow for a bit of growth</span><a href="#l268"></a>
<span id="l269">        this((int) ((1 + m.size()) * 1.1));</span><a href="#l269"></a>
<span id="l270">        putAll(m);</span><a href="#l270"></a>
<span id="l271">    }</span><a href="#l271"></a>
<span id="l272"></span><a href="#l272"></a>
<span id="l273">    /**</span><a href="#l273"></a>
<span id="l274">     * Returns the number of key-value mappings in this identity hash map.</span><a href="#l274"></a>
<span id="l275">     *</span><a href="#l275"></a>
<span id="l276">     * @return the number of key-value mappings in this map</span><a href="#l276"></a>
<span id="l277">     */</span><a href="#l277"></a>
<span id="l278">    public int size() {</span><a href="#l278"></a>
<span id="l279">        return size;</span><a href="#l279"></a>
<span id="l280">    }</span><a href="#l280"></a>
<span id="l281"></span><a href="#l281"></a>
<span id="l282">    /**</span><a href="#l282"></a>
<span id="l283">     * Returns &lt;tt&gt;true&lt;/tt&gt; if this identity hash map contains no key-value</span><a href="#l283"></a>
<span id="l284">     * mappings.</span><a href="#l284"></a>
<span id="l285">     *</span><a href="#l285"></a>
<span id="l286">     * @return &lt;tt&gt;true&lt;/tt&gt; if this identity hash map contains no key-value</span><a href="#l286"></a>
<span id="l287">     *         mappings</span><a href="#l287"></a>
<span id="l288">     */</span><a href="#l288"></a>
<span id="l289">    public boolean isEmpty() {</span><a href="#l289"></a>
<span id="l290">        return size == 0;</span><a href="#l290"></a>
<span id="l291">    }</span><a href="#l291"></a>
<span id="l292"></span><a href="#l292"></a>
<span id="l293">    /**</span><a href="#l293"></a>
<span id="l294">     * Returns index for Object x.</span><a href="#l294"></a>
<span id="l295">     */</span><a href="#l295"></a>
<span id="l296">    private static int hash(Object x, int length) {</span><a href="#l296"></a>
<span id="l297">        int h = System.identityHashCode(x);</span><a href="#l297"></a>
<span id="l298">        // Multiply by -127, and left-shift to use least bit as part of hash</span><a href="#l298"></a>
<span id="l299">        return ((h &lt;&lt; 1) - (h &lt;&lt; 8)) &amp; (length - 1);</span><a href="#l299"></a>
<span id="l300">    }</span><a href="#l300"></a>
<span id="l301"></span><a href="#l301"></a>
<span id="l302">    /**</span><a href="#l302"></a>
<span id="l303">     * Circularly traverses table of size len.</span><a href="#l303"></a>
<span id="l304">     */</span><a href="#l304"></a>
<span id="l305">    private static int nextKeyIndex(int i, int len) {</span><a href="#l305"></a>
<span id="l306">        return (i + 2 &lt; len ? i + 2 : 0);</span><a href="#l306"></a>
<span id="l307">    }</span><a href="#l307"></a>
<span id="l308"></span><a href="#l308"></a>
<span id="l309">    /**</span><a href="#l309"></a>
<span id="l310">     * Returns the value to which the specified key is mapped,</span><a href="#l310"></a>
<span id="l311">     * or {@code null} if this map contains no mapping for the key.</span><a href="#l311"></a>
<span id="l312">     *</span><a href="#l312"></a>
<span id="l313">     * &lt;p&gt;More formally, if this map contains a mapping from a key</span><a href="#l313"></a>
<span id="l314">     * {@code k} to a value {@code v} such that {@code (key == k)},</span><a href="#l314"></a>
<span id="l315">     * then this method returns {@code v}; otherwise it returns</span><a href="#l315"></a>
<span id="l316">     * {@code null}.  (There can be at most one such mapping.)</span><a href="#l316"></a>
<span id="l317">     *</span><a href="#l317"></a>
<span id="l318">     * &lt;p&gt;A return value of {@code null} does not &lt;i&gt;necessarily&lt;/i&gt;</span><a href="#l318"></a>
<span id="l319">     * indicate that the map contains no mapping for the key; it's also</span><a href="#l319"></a>
<span id="l320">     * possible that the map explicitly maps the key to {@code null}.</span><a href="#l320"></a>
<span id="l321">     * The {@link #containsKey containsKey} operation may be used to</span><a href="#l321"></a>
<span id="l322">     * distinguish these two cases.</span><a href="#l322"></a>
<span id="l323">     *</span><a href="#l323"></a>
<span id="l324">     * @see #put(Object, Object)</span><a href="#l324"></a>
<span id="l325">     */</span><a href="#l325"></a>
<span id="l326">    @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l326"></a>
<span id="l327">    public V get(Object key) {</span><a href="#l327"></a>
<span id="l328">        Object k = maskNull(key);</span><a href="#l328"></a>
<span id="l329">        Object[] tab = table;</span><a href="#l329"></a>
<span id="l330">        int len = tab.length;</span><a href="#l330"></a>
<span id="l331">        int i = hash(k, len);</span><a href="#l331"></a>
<span id="l332">        while (true) {</span><a href="#l332"></a>
<span id="l333">            Object item = tab[i];</span><a href="#l333"></a>
<span id="l334">            if (item == k)</span><a href="#l334"></a>
<span id="l335">                return (V) tab[i + 1];</span><a href="#l335"></a>
<span id="l336">            if (item == null)</span><a href="#l336"></a>
<span id="l337">                return null;</span><a href="#l337"></a>
<span id="l338">            i = nextKeyIndex(i, len);</span><a href="#l338"></a>
<span id="l339">        }</span><a href="#l339"></a>
<span id="l340">    }</span><a href="#l340"></a>
<span id="l341"></span><a href="#l341"></a>
<span id="l342">    /**</span><a href="#l342"></a>
<span id="l343">     * Tests whether the specified object reference is a key in this identity</span><a href="#l343"></a>
<span id="l344">     * hash map.</span><a href="#l344"></a>
<span id="l345">     *</span><a href="#l345"></a>
<span id="l346">     * @param   key   possible key</span><a href="#l346"></a>
<span id="l347">     * @return  &lt;code&gt;true&lt;/code&gt; if the specified object reference is a key</span><a href="#l347"></a>
<span id="l348">     *          in this map</span><a href="#l348"></a>
<span id="l349">     * @see     #containsValue(Object)</span><a href="#l349"></a>
<span id="l350">     */</span><a href="#l350"></a>
<span id="l351">    public boolean containsKey(Object key) {</span><a href="#l351"></a>
<span id="l352">        Object k = maskNull(key);</span><a href="#l352"></a>
<span id="l353">        Object[] tab = table;</span><a href="#l353"></a>
<span id="l354">        int len = tab.length;</span><a href="#l354"></a>
<span id="l355">        int i = hash(k, len);</span><a href="#l355"></a>
<span id="l356">        while (true) {</span><a href="#l356"></a>
<span id="l357">            Object item = tab[i];</span><a href="#l357"></a>
<span id="l358">            if (item == k)</span><a href="#l358"></a>
<span id="l359">                return true;</span><a href="#l359"></a>
<span id="l360">            if (item == null)</span><a href="#l360"></a>
<span id="l361">                return false;</span><a href="#l361"></a>
<span id="l362">            i = nextKeyIndex(i, len);</span><a href="#l362"></a>
<span id="l363">        }</span><a href="#l363"></a>
<span id="l364">    }</span><a href="#l364"></a>
<span id="l365"></span><a href="#l365"></a>
<span id="l366">    /**</span><a href="#l366"></a>
<span id="l367">     * Tests whether the specified object reference is a value in this identity</span><a href="#l367"></a>
<span id="l368">     * hash map.</span><a href="#l368"></a>
<span id="l369">     *</span><a href="#l369"></a>
<span id="l370">     * @param value value whose presence in this map is to be tested</span><a href="#l370"></a>
<span id="l371">     * @return &lt;tt&gt;true&lt;/tt&gt; if this map maps one or more keys to the</span><a href="#l371"></a>
<span id="l372">     *         specified object reference</span><a href="#l372"></a>
<span id="l373">     * @see     #containsKey(Object)</span><a href="#l373"></a>
<span id="l374">     */</span><a href="#l374"></a>
<span id="l375">    public boolean containsValue(Object value) {</span><a href="#l375"></a>
<span id="l376">        Object[] tab = table;</span><a href="#l376"></a>
<span id="l377">        for (int i = 1; i &lt; tab.length; i += 2)</span><a href="#l377"></a>
<span id="l378">            if (tab[i] == value &amp;&amp; tab[i - 1] != null)</span><a href="#l378"></a>
<span id="l379">                return true;</span><a href="#l379"></a>
<span id="l380"></span><a href="#l380"></a>
<span id="l381">        return false;</span><a href="#l381"></a>
<span id="l382">    }</span><a href="#l382"></a>
<span id="l383"></span><a href="#l383"></a>
<span id="l384">    /**</span><a href="#l384"></a>
<span id="l385">     * Tests if the specified key-value mapping is in the map.</span><a href="#l385"></a>
<span id="l386">     *</span><a href="#l386"></a>
<span id="l387">     * @param   key   possible key</span><a href="#l387"></a>
<span id="l388">     * @param   value possible value</span><a href="#l388"></a>
<span id="l389">     * @return  &lt;code&gt;true&lt;/code&gt; if and only if the specified key-value</span><a href="#l389"></a>
<span id="l390">     *          mapping is in the map</span><a href="#l390"></a>
<span id="l391">     */</span><a href="#l391"></a>
<span id="l392">    private boolean containsMapping(Object key, Object value) {</span><a href="#l392"></a>
<span id="l393">        Object k = maskNull(key);</span><a href="#l393"></a>
<span id="l394">        Object[] tab = table;</span><a href="#l394"></a>
<span id="l395">        int len = tab.length;</span><a href="#l395"></a>
<span id="l396">        int i = hash(k, len);</span><a href="#l396"></a>
<span id="l397">        while (true) {</span><a href="#l397"></a>
<span id="l398">            Object item = tab[i];</span><a href="#l398"></a>
<span id="l399">            if (item == k)</span><a href="#l399"></a>
<span id="l400">                return tab[i + 1] == value;</span><a href="#l400"></a>
<span id="l401">            if (item == null)</span><a href="#l401"></a>
<span id="l402">                return false;</span><a href="#l402"></a>
<span id="l403">            i = nextKeyIndex(i, len);</span><a href="#l403"></a>
<span id="l404">        }</span><a href="#l404"></a>
<span id="l405">    }</span><a href="#l405"></a>
<span id="l406"></span><a href="#l406"></a>
<span id="l407">    /**</span><a href="#l407"></a>
<span id="l408">     * Associates the specified value with the specified key in this identity</span><a href="#l408"></a>
<span id="l409">     * hash map.  If the map previously contained a mapping for the key, the</span><a href="#l409"></a>
<span id="l410">     * old value is replaced.</span><a href="#l410"></a>
<span id="l411">     *</span><a href="#l411"></a>
<span id="l412">     * @param key the key with which the specified value is to be associated</span><a href="#l412"></a>
<span id="l413">     * @param value the value to be associated with the specified key</span><a href="#l413"></a>
<span id="l414">     * @return the previous value associated with &lt;tt&gt;key&lt;/tt&gt;, or</span><a href="#l414"></a>
<span id="l415">     *         &lt;tt&gt;null&lt;/tt&gt; if there was no mapping for &lt;tt&gt;key&lt;/tt&gt;.</span><a href="#l415"></a>
<span id="l416">     *         (A &lt;tt&gt;null&lt;/tt&gt; return can also indicate that the map</span><a href="#l416"></a>
<span id="l417">     *         previously associated &lt;tt&gt;null&lt;/tt&gt; with &lt;tt&gt;key&lt;/tt&gt;.)</span><a href="#l417"></a>
<span id="l418">     * @see     Object#equals(Object)</span><a href="#l418"></a>
<span id="l419">     * @see     #get(Object)</span><a href="#l419"></a>
<span id="l420">     * @see     #containsKey(Object)</span><a href="#l420"></a>
<span id="l421">     */</span><a href="#l421"></a>
<span id="l422">    public V put(K key, V value) {</span><a href="#l422"></a>
<span id="l423">        final Object k = maskNull(key);</span><a href="#l423"></a>
<span id="l424"></span><a href="#l424"></a>
<span id="l425">        retryAfterResize: for (;;) {</span><a href="#l425"></a>
<span id="l426">            final Object[] tab = table;</span><a href="#l426"></a>
<span id="l427">            final int len = tab.length;</span><a href="#l427"></a>
<span id="l428">            int i = hash(k, len);</span><a href="#l428"></a>
<span id="l429"></span><a href="#l429"></a>
<span id="l430">            for (Object item; (item = tab[i]) != null;</span><a href="#l430"></a>
<span id="l431">                 i = nextKeyIndex(i, len)) {</span><a href="#l431"></a>
<span id="l432">                if (item == k) {</span><a href="#l432"></a>
<span id="l433">                    @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l433"></a>
<span id="l434">                        V oldValue = (V) tab[i + 1];</span><a href="#l434"></a>
<span id="l435">                    tab[i + 1] = value;</span><a href="#l435"></a>
<span id="l436">                    return oldValue;</span><a href="#l436"></a>
<span id="l437">                }</span><a href="#l437"></a>
<span id="l438">            }</span><a href="#l438"></a>
<span id="l439"></span><a href="#l439"></a>
<span id="l440">            final int s = size + 1;</span><a href="#l440"></a>
<span id="l441">            // Use optimized form of 3 * s.</span><a href="#l441"></a>
<span id="l442">            // Next capacity is len, 2 * current capacity.</span><a href="#l442"></a>
<span id="l443">            if (s + (s &lt;&lt; 1) &gt; len &amp;&amp; resize(len))</span><a href="#l443"></a>
<span id="l444">                continue retryAfterResize;</span><a href="#l444"></a>
<span id="l445"></span><a href="#l445"></a>
<span id="l446">            modCount++;</span><a href="#l446"></a>
<span id="l447">            tab[i] = k;</span><a href="#l447"></a>
<span id="l448">            tab[i + 1] = value;</span><a href="#l448"></a>
<span id="l449">            size = s;</span><a href="#l449"></a>
<span id="l450">            return null;</span><a href="#l450"></a>
<span id="l451">        }</span><a href="#l451"></a>
<span id="l452">    }</span><a href="#l452"></a>
<span id="l453"></span><a href="#l453"></a>
<span id="l454">    /**</span><a href="#l454"></a>
<span id="l455">     * Resizes the table if necessary to hold given capacity.</span><a href="#l455"></a>
<span id="l456">     *</span><a href="#l456"></a>
<span id="l457">     * @param newCapacity the new capacity, must be a power of two.</span><a href="#l457"></a>
<span id="l458">     * @return whether a resize did in fact take place</span><a href="#l458"></a>
<span id="l459">     */</span><a href="#l459"></a>
<span id="l460">    private boolean resize(int newCapacity) {</span><a href="#l460"></a>
<span id="l461">        // assert (newCapacity &amp; -newCapacity) == newCapacity; // power of 2</span><a href="#l461"></a>
<span id="l462">        int newLength = newCapacity * 2;</span><a href="#l462"></a>
<span id="l463"></span><a href="#l463"></a>
<span id="l464">        Object[] oldTable = table;</span><a href="#l464"></a>
<span id="l465">        int oldLength = oldTable.length;</span><a href="#l465"></a>
<span id="l466">        if (oldLength == 2 * MAXIMUM_CAPACITY) { // can't expand any further</span><a href="#l466"></a>
<span id="l467">            if (size == MAXIMUM_CAPACITY - 1)</span><a href="#l467"></a>
<span id="l468">                throw new IllegalStateException(&quot;Capacity exhausted.&quot;);</span><a href="#l468"></a>
<span id="l469">            return false;</span><a href="#l469"></a>
<span id="l470">        }</span><a href="#l470"></a>
<span id="l471">        if (oldLength &gt;= newLength)</span><a href="#l471"></a>
<span id="l472">            return false;</span><a href="#l472"></a>
<span id="l473"></span><a href="#l473"></a>
<span id="l474">        Object[] newTable = new Object[newLength];</span><a href="#l474"></a>
<span id="l475"></span><a href="#l475"></a>
<span id="l476">        for (int j = 0; j &lt; oldLength; j += 2) {</span><a href="#l476"></a>
<span id="l477">            Object key = oldTable[j];</span><a href="#l477"></a>
<span id="l478">            if (key != null) {</span><a href="#l478"></a>
<span id="l479">                Object value = oldTable[j+1];</span><a href="#l479"></a>
<span id="l480">                oldTable[j] = null;</span><a href="#l480"></a>
<span id="l481">                oldTable[j+1] = null;</span><a href="#l481"></a>
<span id="l482">                int i = hash(key, newLength);</span><a href="#l482"></a>
<span id="l483">                while (newTable[i] != null)</span><a href="#l483"></a>
<span id="l484">                    i = nextKeyIndex(i, newLength);</span><a href="#l484"></a>
<span id="l485">                newTable[i] = key;</span><a href="#l485"></a>
<span id="l486">                newTable[i + 1] = value;</span><a href="#l486"></a>
<span id="l487">            }</span><a href="#l487"></a>
<span id="l488">        }</span><a href="#l488"></a>
<span id="l489">        table = newTable;</span><a href="#l489"></a>
<span id="l490">        return true;</span><a href="#l490"></a>
<span id="l491">    }</span><a href="#l491"></a>
<span id="l492"></span><a href="#l492"></a>
<span id="l493">    /**</span><a href="#l493"></a>
<span id="l494">     * Copies all of the mappings from the specified map to this map.</span><a href="#l494"></a>
<span id="l495">     * These mappings will replace any mappings that this map had for</span><a href="#l495"></a>
<span id="l496">     * any of the keys currently in the specified map.</span><a href="#l496"></a>
<span id="l497">     *</span><a href="#l497"></a>
<span id="l498">     * @param m mappings to be stored in this map</span><a href="#l498"></a>
<span id="l499">     * @throws NullPointerException if the specified map is null</span><a href="#l499"></a>
<span id="l500">     */</span><a href="#l500"></a>
<span id="l501">    public void putAll(Map&lt;? extends K, ? extends V&gt; m) {</span><a href="#l501"></a>
<span id="l502">        int n = m.size();</span><a href="#l502"></a>
<span id="l503">        if (n == 0)</span><a href="#l503"></a>
<span id="l504">            return;</span><a href="#l504"></a>
<span id="l505">        if (n &gt; size)</span><a href="#l505"></a>
<span id="l506">            resize(capacity(n)); // conservatively pre-expand</span><a href="#l506"></a>
<span id="l507"></span><a href="#l507"></a>
<span id="l508">        for (Entry&lt;? extends K, ? extends V&gt; e : m.entrySet())</span><a href="#l508"></a>
<span id="l509">            put(e.getKey(), e.getValue());</span><a href="#l509"></a>
<span id="l510">    }</span><a href="#l510"></a>
<span id="l511"></span><a href="#l511"></a>
<span id="l512">    /**</span><a href="#l512"></a>
<span id="l513">     * Removes the mapping for this key from this map if present.</span><a href="#l513"></a>
<span id="l514">     *</span><a href="#l514"></a>
<span id="l515">     * @param key key whose mapping is to be removed from the map</span><a href="#l515"></a>
<span id="l516">     * @return the previous value associated with &lt;tt&gt;key&lt;/tt&gt;, or</span><a href="#l516"></a>
<span id="l517">     *         &lt;tt&gt;null&lt;/tt&gt; if there was no mapping for &lt;tt&gt;key&lt;/tt&gt;.</span><a href="#l517"></a>
<span id="l518">     *         (A &lt;tt&gt;null&lt;/tt&gt; return can also indicate that the map</span><a href="#l518"></a>
<span id="l519">     *         previously associated &lt;tt&gt;null&lt;/tt&gt; with &lt;tt&gt;key&lt;/tt&gt;.)</span><a href="#l519"></a>
<span id="l520">     */</span><a href="#l520"></a>
<span id="l521">    public V remove(Object key) {</span><a href="#l521"></a>
<span id="l522">        Object k = maskNull(key);</span><a href="#l522"></a>
<span id="l523">        Object[] tab = table;</span><a href="#l523"></a>
<span id="l524">        int len = tab.length;</span><a href="#l524"></a>
<span id="l525">        int i = hash(k, len);</span><a href="#l525"></a>
<span id="l526"></span><a href="#l526"></a>
<span id="l527">        while (true) {</span><a href="#l527"></a>
<span id="l528">            Object item = tab[i];</span><a href="#l528"></a>
<span id="l529">            if (item == k) {</span><a href="#l529"></a>
<span id="l530">                modCount++;</span><a href="#l530"></a>
<span id="l531">                size--;</span><a href="#l531"></a>
<span id="l532">                @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l532"></a>
<span id="l533">                    V oldValue = (V) tab[i + 1];</span><a href="#l533"></a>
<span id="l534">                tab[i + 1] = null;</span><a href="#l534"></a>
<span id="l535">                tab[i] = null;</span><a href="#l535"></a>
<span id="l536">                closeDeletion(i);</span><a href="#l536"></a>
<span id="l537">                return oldValue;</span><a href="#l537"></a>
<span id="l538">            }</span><a href="#l538"></a>
<span id="l539">            if (item == null)</span><a href="#l539"></a>
<span id="l540">                return null;</span><a href="#l540"></a>
<span id="l541">            i = nextKeyIndex(i, len);</span><a href="#l541"></a>
<span id="l542">        }</span><a href="#l542"></a>
<span id="l543">    }</span><a href="#l543"></a>
<span id="l544"></span><a href="#l544"></a>
<span id="l545">    /**</span><a href="#l545"></a>
<span id="l546">     * Removes the specified key-value mapping from the map if it is present.</span><a href="#l546"></a>
<span id="l547">     *</span><a href="#l547"></a>
<span id="l548">     * @param   key   possible key</span><a href="#l548"></a>
<span id="l549">     * @param   value possible value</span><a href="#l549"></a>
<span id="l550">     * @return  &lt;code&gt;true&lt;/code&gt; if and only if the specified key-value</span><a href="#l550"></a>
<span id="l551">     *          mapping was in the map</span><a href="#l551"></a>
<span id="l552">     */</span><a href="#l552"></a>
<span id="l553">    private boolean removeMapping(Object key, Object value) {</span><a href="#l553"></a>
<span id="l554">        Object k = maskNull(key);</span><a href="#l554"></a>
<span id="l555">        Object[] tab = table;</span><a href="#l555"></a>
<span id="l556">        int len = tab.length;</span><a href="#l556"></a>
<span id="l557">        int i = hash(k, len);</span><a href="#l557"></a>
<span id="l558"></span><a href="#l558"></a>
<span id="l559">        while (true) {</span><a href="#l559"></a>
<span id="l560">            Object item = tab[i];</span><a href="#l560"></a>
<span id="l561">            if (item == k) {</span><a href="#l561"></a>
<span id="l562">                if (tab[i + 1] != value)</span><a href="#l562"></a>
<span id="l563">                    return false;</span><a href="#l563"></a>
<span id="l564">                modCount++;</span><a href="#l564"></a>
<span id="l565">                size--;</span><a href="#l565"></a>
<span id="l566">                tab[i] = null;</span><a href="#l566"></a>
<span id="l567">                tab[i + 1] = null;</span><a href="#l567"></a>
<span id="l568">                closeDeletion(i);</span><a href="#l568"></a>
<span id="l569">                return true;</span><a href="#l569"></a>
<span id="l570">            }</span><a href="#l570"></a>
<span id="l571">            if (item == null)</span><a href="#l571"></a>
<span id="l572">                return false;</span><a href="#l572"></a>
<span id="l573">            i = nextKeyIndex(i, len);</span><a href="#l573"></a>
<span id="l574">        }</span><a href="#l574"></a>
<span id="l575">    }</span><a href="#l575"></a>
<span id="l576"></span><a href="#l576"></a>
<span id="l577">    /**</span><a href="#l577"></a>
<span id="l578">     * Rehash all possibly-colliding entries following a</span><a href="#l578"></a>
<span id="l579">     * deletion. This preserves the linear-probe</span><a href="#l579"></a>
<span id="l580">     * collision properties required by get, put, etc.</span><a href="#l580"></a>
<span id="l581">     *</span><a href="#l581"></a>
<span id="l582">     * @param d the index of a newly empty deleted slot</span><a href="#l582"></a>
<span id="l583">     */</span><a href="#l583"></a>
<span id="l584">    private void closeDeletion(int d) {</span><a href="#l584"></a>
<span id="l585">        // Adapted from Knuth Section 6.4 Algorithm R</span><a href="#l585"></a>
<span id="l586">        Object[] tab = table;</span><a href="#l586"></a>
<span id="l587">        int len = tab.length;</span><a href="#l587"></a>
<span id="l588"></span><a href="#l588"></a>
<span id="l589">        // Look for items to swap into newly vacated slot</span><a href="#l589"></a>
<span id="l590">        // starting at index immediately following deletion,</span><a href="#l590"></a>
<span id="l591">        // and continuing until a null slot is seen, indicating</span><a href="#l591"></a>
<span id="l592">        // the end of a run of possibly-colliding keys.</span><a href="#l592"></a>
<span id="l593">        Object item;</span><a href="#l593"></a>
<span id="l594">        for (int i = nextKeyIndex(d, len); (item = tab[i]) != null;</span><a href="#l594"></a>
<span id="l595">             i = nextKeyIndex(i, len) ) {</span><a href="#l595"></a>
<span id="l596">            // The following test triggers if the item at slot i (which</span><a href="#l596"></a>
<span id="l597">            // hashes to be at slot r) should take the spot vacated by d.</span><a href="#l597"></a>
<span id="l598">            // If so, we swap it in, and then continue with d now at the</span><a href="#l598"></a>
<span id="l599">            // newly vacated i.  This process will terminate when we hit</span><a href="#l599"></a>
<span id="l600">            // the null slot at the end of this run.</span><a href="#l600"></a>
<span id="l601">            // The test is messy because we are using a circular table.</span><a href="#l601"></a>
<span id="l602">            int r = hash(item, len);</span><a href="#l602"></a>
<span id="l603">            if ((i &lt; r &amp;&amp; (r &lt;= d || d &lt;= i)) || (r &lt;= d &amp;&amp; d &lt;= i)) {</span><a href="#l603"></a>
<span id="l604">                tab[d] = item;</span><a href="#l604"></a>
<span id="l605">                tab[d + 1] = tab[i + 1];</span><a href="#l605"></a>
<span id="l606">                tab[i] = null;</span><a href="#l606"></a>
<span id="l607">                tab[i + 1] = null;</span><a href="#l607"></a>
<span id="l608">                d = i;</span><a href="#l608"></a>
<span id="l609">            }</span><a href="#l609"></a>
<span id="l610">        }</span><a href="#l610"></a>
<span id="l611">    }</span><a href="#l611"></a>
<span id="l612"></span><a href="#l612"></a>
<span id="l613">    /**</span><a href="#l613"></a>
<span id="l614">     * Removes all of the mappings from this map.</span><a href="#l614"></a>
<span id="l615">     * The map will be empty after this call returns.</span><a href="#l615"></a>
<span id="l616">     */</span><a href="#l616"></a>
<span id="l617">    public void clear() {</span><a href="#l617"></a>
<span id="l618">        modCount++;</span><a href="#l618"></a>
<span id="l619">        Object[] tab = table;</span><a href="#l619"></a>
<span id="l620">        for (int i = 0; i &lt; tab.length; i++)</span><a href="#l620"></a>
<span id="l621">            tab[i] = null;</span><a href="#l621"></a>
<span id="l622">        size = 0;</span><a href="#l622"></a>
<span id="l623">    }</span><a href="#l623"></a>
<span id="l624"></span><a href="#l624"></a>
<span id="l625">    /**</span><a href="#l625"></a>
<span id="l626">     * Compares the specified object with this map for equality.  Returns</span><a href="#l626"></a>
<span id="l627">     * &lt;tt&gt;true&lt;/tt&gt; if the given object is also a map and the two maps</span><a href="#l627"></a>
<span id="l628">     * represent identical object-reference mappings.  More formally, this</span><a href="#l628"></a>
<span id="l629">     * map is equal to another map &lt;tt&gt;m&lt;/tt&gt; if and only if</span><a href="#l629"></a>
<span id="l630">     * &lt;tt&gt;this.entrySet().equals(m.entrySet())&lt;/tt&gt;.</span><a href="#l630"></a>
<span id="l631">     *</span><a href="#l631"></a>
<span id="l632">     * &lt;p&gt;&lt;b&gt;Owing to the reference-equality-based semantics of this map it is</span><a href="#l632"></a>
<span id="l633">     * possible that the symmetry and transitivity requirements of the</span><a href="#l633"></a>
<span id="l634">     * &lt;tt&gt;Object.equals&lt;/tt&gt; contract may be violated if this map is compared</span><a href="#l634"></a>
<span id="l635">     * to a normal map.  However, the &lt;tt&gt;Object.equals&lt;/tt&gt; contract is</span><a href="#l635"></a>
<span id="l636">     * guaranteed to hold among &lt;tt&gt;IdentityHashMap&lt;/tt&gt; instances.&lt;/b&gt;</span><a href="#l636"></a>
<span id="l637">     *</span><a href="#l637"></a>
<span id="l638">     * @param  o object to be compared for equality with this map</span><a href="#l638"></a>
<span id="l639">     * @return &lt;tt&gt;true&lt;/tt&gt; if the specified object is equal to this map</span><a href="#l639"></a>
<span id="l640">     * @see Object#equals(Object)</span><a href="#l640"></a>
<span id="l641">     */</span><a href="#l641"></a>
<span id="l642">    public boolean equals(Object o) {</span><a href="#l642"></a>
<span id="l643">        if (o == this) {</span><a href="#l643"></a>
<span id="l644">            return true;</span><a href="#l644"></a>
<span id="l645">        } else if (o instanceof IdentityHashMap) {</span><a href="#l645"></a>
<span id="l646">            IdentityHashMap&lt;?,?&gt; m = (IdentityHashMap&lt;?,?&gt;) o;</span><a href="#l646"></a>
<span id="l647">            if (m.size() != size)</span><a href="#l647"></a>
<span id="l648">                return false;</span><a href="#l648"></a>
<span id="l649"></span><a href="#l649"></a>
<span id="l650">            Object[] tab = m.table;</span><a href="#l650"></a>
<span id="l651">            for (int i = 0; i &lt; tab.length; i+=2) {</span><a href="#l651"></a>
<span id="l652">                Object k = tab[i];</span><a href="#l652"></a>
<span id="l653">                if (k != null &amp;&amp; !containsMapping(k, tab[i + 1]))</span><a href="#l653"></a>
<span id="l654">                    return false;</span><a href="#l654"></a>
<span id="l655">            }</span><a href="#l655"></a>
<span id="l656">            return true;</span><a href="#l656"></a>
<span id="l657">        } else if (o instanceof Map) {</span><a href="#l657"></a>
<span id="l658">            Map&lt;?,?&gt; m = (Map&lt;?,?&gt;)o;</span><a href="#l658"></a>
<span id="l659">            return entrySet().equals(m.entrySet());</span><a href="#l659"></a>
<span id="l660">        } else {</span><a href="#l660"></a>
<span id="l661">            return false;  // o is not a Map</span><a href="#l661"></a>
<span id="l662">        }</span><a href="#l662"></a>
<span id="l663">    }</span><a href="#l663"></a>
<span id="l664"></span><a href="#l664"></a>
<span id="l665">    /**</span><a href="#l665"></a>
<span id="l666">     * Returns the hash code value for this map.  The hash code of a map is</span><a href="#l666"></a>
<span id="l667">     * defined to be the sum of the hash codes of each entry in the map's</span><a href="#l667"></a>
<span id="l668">     * &lt;tt&gt;entrySet()&lt;/tt&gt; view.  This ensures that &lt;tt&gt;m1.equals(m2)&lt;/tt&gt;</span><a href="#l668"></a>
<span id="l669">     * implies that &lt;tt&gt;m1.hashCode()==m2.hashCode()&lt;/tt&gt; for any two</span><a href="#l669"></a>
<span id="l670">     * &lt;tt&gt;IdentityHashMap&lt;/tt&gt; instances &lt;tt&gt;m1&lt;/tt&gt; and &lt;tt&gt;m2&lt;/tt&gt;, as</span><a href="#l670"></a>
<span id="l671">     * required by the general contract of {@link Object#hashCode}.</span><a href="#l671"></a>
<span id="l672">     *</span><a href="#l672"></a>
<span id="l673">     * &lt;p&gt;&lt;b&gt;Owing to the reference-equality-based semantics of the</span><a href="#l673"></a>
<span id="l674">     * &lt;tt&gt;Map.Entry&lt;/tt&gt; instances in the set returned by this map's</span><a href="#l674"></a>
<span id="l675">     * &lt;tt&gt;entrySet&lt;/tt&gt; method, it is possible that the contractual</span><a href="#l675"></a>
<span id="l676">     * requirement of &lt;tt&gt;Object.hashCode&lt;/tt&gt; mentioned in the previous</span><a href="#l676"></a>
<span id="l677">     * paragraph will be violated if one of the two objects being compared is</span><a href="#l677"></a>
<span id="l678">     * an &lt;tt&gt;IdentityHashMap&lt;/tt&gt; instance and the other is a normal map.&lt;/b&gt;</span><a href="#l678"></a>
<span id="l679">     *</span><a href="#l679"></a>
<span id="l680">     * @return the hash code value for this map</span><a href="#l680"></a>
<span id="l681">     * @see Object#equals(Object)</span><a href="#l681"></a>
<span id="l682">     * @see #equals(Object)</span><a href="#l682"></a>
<span id="l683">     */</span><a href="#l683"></a>
<span id="l684">    public int hashCode() {</span><a href="#l684"></a>
<span id="l685">        int result = 0;</span><a href="#l685"></a>
<span id="l686">        Object[] tab = table;</span><a href="#l686"></a>
<span id="l687">        for (int i = 0; i &lt; tab.length; i +=2) {</span><a href="#l687"></a>
<span id="l688">            Object key = tab[i];</span><a href="#l688"></a>
<span id="l689">            if (key != null) {</span><a href="#l689"></a>
<span id="l690">                Object k = unmaskNull(key);</span><a href="#l690"></a>
<span id="l691">                result += System.identityHashCode(k) ^</span><a href="#l691"></a>
<span id="l692">                          System.identityHashCode(tab[i + 1]);</span><a href="#l692"></a>
<span id="l693">            }</span><a href="#l693"></a>
<span id="l694">        }</span><a href="#l694"></a>
<span id="l695">        return result;</span><a href="#l695"></a>
<span id="l696">    }</span><a href="#l696"></a>
<span id="l697"></span><a href="#l697"></a>
<span id="l698">    /**</span><a href="#l698"></a>
<span id="l699">     * Returns a shallow copy of this identity hash map: the keys and values</span><a href="#l699"></a>
<span id="l700">     * themselves are not cloned.</span><a href="#l700"></a>
<span id="l701">     *</span><a href="#l701"></a>
<span id="l702">     * @return a shallow copy of this map</span><a href="#l702"></a>
<span id="l703">     */</span><a href="#l703"></a>
<span id="l704">    public Object clone() {</span><a href="#l704"></a>
<span id="l705">        try {</span><a href="#l705"></a>
<span id="l706">            IdentityHashMap&lt;?,?&gt; m = (IdentityHashMap&lt;?,?&gt;) super.clone();</span><a href="#l706"></a>
<span id="l707">            m.entrySet = null;</span><a href="#l707"></a>
<span id="l708">            m.table = table.clone();</span><a href="#l708"></a>
<span id="l709">            return m;</span><a href="#l709"></a>
<span id="l710">        } catch (CloneNotSupportedException e) {</span><a href="#l710"></a>
<span id="l711">            throw new InternalError(e);</span><a href="#l711"></a>
<span id="l712">        }</span><a href="#l712"></a>
<span id="l713">    }</span><a href="#l713"></a>
<span id="l714"></span><a href="#l714"></a>
<span id="l715">    private abstract class IdentityHashMapIterator&lt;T&gt; implements Iterator&lt;T&gt; {</span><a href="#l715"></a>
<span id="l716">        int index = (size != 0 ? 0 : table.length); // current slot.</span><a href="#l716"></a>
<span id="l717">        int expectedModCount = modCount; // to support fast-fail</span><a href="#l717"></a>
<span id="l718">        int lastReturnedIndex = -1;      // to allow remove()</span><a href="#l718"></a>
<span id="l719">        boolean indexValid; // To avoid unnecessary next computation</span><a href="#l719"></a>
<span id="l720">        Object[] traversalTable = table; // reference to main table or copy</span><a href="#l720"></a>
<span id="l721"></span><a href="#l721"></a>
<span id="l722">        public boolean hasNext() {</span><a href="#l722"></a>
<span id="l723">            Object[] tab = traversalTable;</span><a href="#l723"></a>
<span id="l724">            for (int i = index; i &lt; tab.length; i+=2) {</span><a href="#l724"></a>
<span id="l725">                Object key = tab[i];</span><a href="#l725"></a>
<span id="l726">                if (key != null) {</span><a href="#l726"></a>
<span id="l727">                    index = i;</span><a href="#l727"></a>
<span id="l728">                    return indexValid = true;</span><a href="#l728"></a>
<span id="l729">                }</span><a href="#l729"></a>
<span id="l730">            }</span><a href="#l730"></a>
<span id="l731">            index = tab.length;</span><a href="#l731"></a>
<span id="l732">            return false;</span><a href="#l732"></a>
<span id="l733">        }</span><a href="#l733"></a>
<span id="l734"></span><a href="#l734"></a>
<span id="l735">        protected int nextIndex() {</span><a href="#l735"></a>
<span id="l736">            if (modCount != expectedModCount)</span><a href="#l736"></a>
<span id="l737">                throw new ConcurrentModificationException();</span><a href="#l737"></a>
<span id="l738">            if (!indexValid &amp;&amp; !hasNext())</span><a href="#l738"></a>
<span id="l739">                throw new NoSuchElementException();</span><a href="#l739"></a>
<span id="l740"></span><a href="#l740"></a>
<span id="l741">            indexValid = false;</span><a href="#l741"></a>
<span id="l742">            lastReturnedIndex = index;</span><a href="#l742"></a>
<span id="l743">            index += 2;</span><a href="#l743"></a>
<span id="l744">            return lastReturnedIndex;</span><a href="#l744"></a>
<span id="l745">        }</span><a href="#l745"></a>
<span id="l746"></span><a href="#l746"></a>
<span id="l747">        public void remove() {</span><a href="#l747"></a>
<span id="l748">            if (lastReturnedIndex == -1)</span><a href="#l748"></a>
<span id="l749">                throw new IllegalStateException();</span><a href="#l749"></a>
<span id="l750">            if (modCount != expectedModCount)</span><a href="#l750"></a>
<span id="l751">                throw new ConcurrentModificationException();</span><a href="#l751"></a>
<span id="l752"></span><a href="#l752"></a>
<span id="l753">            expectedModCount = ++modCount;</span><a href="#l753"></a>
<span id="l754">            int deletedSlot = lastReturnedIndex;</span><a href="#l754"></a>
<span id="l755">            lastReturnedIndex = -1;</span><a href="#l755"></a>
<span id="l756">            // back up index to revisit new contents after deletion</span><a href="#l756"></a>
<span id="l757">            index = deletedSlot;</span><a href="#l757"></a>
<span id="l758">            indexValid = false;</span><a href="#l758"></a>
<span id="l759"></span><a href="#l759"></a>
<span id="l760">            // Removal code proceeds as in closeDeletion except that</span><a href="#l760"></a>
<span id="l761">            // it must catch the rare case where an element already</span><a href="#l761"></a>
<span id="l762">            // seen is swapped into a vacant slot that will be later</span><a href="#l762"></a>
<span id="l763">            // traversed by this iterator. We cannot allow future</span><a href="#l763"></a>
<span id="l764">            // next() calls to return it again.  The likelihood of</span><a href="#l764"></a>
<span id="l765">            // this occurring under 2/3 load factor is very slim, but</span><a href="#l765"></a>
<span id="l766">            // when it does happen, we must make a copy of the rest of</span><a href="#l766"></a>
<span id="l767">            // the table to use for the rest of the traversal. Since</span><a href="#l767"></a>
<span id="l768">            // this can only happen when we are near the end of the table,</span><a href="#l768"></a>
<span id="l769">            // even in these rare cases, this is not very expensive in</span><a href="#l769"></a>
<span id="l770">            // time or space.</span><a href="#l770"></a>
<span id="l771"></span><a href="#l771"></a>
<span id="l772">            Object[] tab = traversalTable;</span><a href="#l772"></a>
<span id="l773">            int len = tab.length;</span><a href="#l773"></a>
<span id="l774"></span><a href="#l774"></a>
<span id="l775">            int d = deletedSlot;</span><a href="#l775"></a>
<span id="l776">            Object key = tab[d];</span><a href="#l776"></a>
<span id="l777">            tab[d] = null;        // vacate the slot</span><a href="#l777"></a>
<span id="l778">            tab[d + 1] = null;</span><a href="#l778"></a>
<span id="l779"></span><a href="#l779"></a>
<span id="l780">            // If traversing a copy, remove in real table.</span><a href="#l780"></a>
<span id="l781">            // We can skip gap-closure on copy.</span><a href="#l781"></a>
<span id="l782">            if (tab != IdentityHashMap.this.table) {</span><a href="#l782"></a>
<span id="l783">                IdentityHashMap.this.remove(key);</span><a href="#l783"></a>
<span id="l784">                expectedModCount = modCount;</span><a href="#l784"></a>
<span id="l785">                return;</span><a href="#l785"></a>
<span id="l786">            }</span><a href="#l786"></a>
<span id="l787"></span><a href="#l787"></a>
<span id="l788">            size--;</span><a href="#l788"></a>
<span id="l789"></span><a href="#l789"></a>
<span id="l790">            Object item;</span><a href="#l790"></a>
<span id="l791">            for (int i = nextKeyIndex(d, len); (item = tab[i]) != null;</span><a href="#l791"></a>
<span id="l792">                 i = nextKeyIndex(i, len)) {</span><a href="#l792"></a>
<span id="l793">                int r = hash(item, len);</span><a href="#l793"></a>
<span id="l794">                // See closeDeletion for explanation of this conditional</span><a href="#l794"></a>
<span id="l795">                if ((i &lt; r &amp;&amp; (r &lt;= d || d &lt;= i)) ||</span><a href="#l795"></a>
<span id="l796">                    (r &lt;= d &amp;&amp; d &lt;= i)) {</span><a href="#l796"></a>
<span id="l797"></span><a href="#l797"></a>
<span id="l798">                    // If we are about to swap an already-seen element</span><a href="#l798"></a>
<span id="l799">                    // into a slot that may later be returned by next(),</span><a href="#l799"></a>
<span id="l800">                    // then clone the rest of table for use in future</span><a href="#l800"></a>
<span id="l801">                    // next() calls. It is OK that our copy will have</span><a href="#l801"></a>
<span id="l802">                    // a gap in the &quot;wrong&quot; place, since it will never</span><a href="#l802"></a>
<span id="l803">                    // be used for searching anyway.</span><a href="#l803"></a>
<span id="l804"></span><a href="#l804"></a>
<span id="l805">                    if (i &lt; deletedSlot &amp;&amp; d &gt;= deletedSlot &amp;&amp;</span><a href="#l805"></a>
<span id="l806">                        traversalTable == IdentityHashMap.this.table) {</span><a href="#l806"></a>
<span id="l807">                        int remaining = len - deletedSlot;</span><a href="#l807"></a>
<span id="l808">                        Object[] newTable = new Object[remaining];</span><a href="#l808"></a>
<span id="l809">                        System.arraycopy(tab, deletedSlot,</span><a href="#l809"></a>
<span id="l810">                                         newTable, 0, remaining);</span><a href="#l810"></a>
<span id="l811">                        traversalTable = newTable;</span><a href="#l811"></a>
<span id="l812">                        index = 0;</span><a href="#l812"></a>
<span id="l813">                    }</span><a href="#l813"></a>
<span id="l814"></span><a href="#l814"></a>
<span id="l815">                    tab[d] = item;</span><a href="#l815"></a>
<span id="l816">                    tab[d + 1] = tab[i + 1];</span><a href="#l816"></a>
<span id="l817">                    tab[i] = null;</span><a href="#l817"></a>
<span id="l818">                    tab[i + 1] = null;</span><a href="#l818"></a>
<span id="l819">                    d = i;</span><a href="#l819"></a>
<span id="l820">                }</span><a href="#l820"></a>
<span id="l821">            }</span><a href="#l821"></a>
<span id="l822">        }</span><a href="#l822"></a>
<span id="l823">    }</span><a href="#l823"></a>
<span id="l824"></span><a href="#l824"></a>
<span id="l825">    private class KeyIterator extends IdentityHashMapIterator&lt;K&gt; {</span><a href="#l825"></a>
<span id="l826">        @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l826"></a>
<span id="l827">        public K next() {</span><a href="#l827"></a>
<span id="l828">            return (K) unmaskNull(traversalTable[nextIndex()]);</span><a href="#l828"></a>
<span id="l829">        }</span><a href="#l829"></a>
<span id="l830">    }</span><a href="#l830"></a>
<span id="l831"></span><a href="#l831"></a>
<span id="l832">    private class ValueIterator extends IdentityHashMapIterator&lt;V&gt; {</span><a href="#l832"></a>
<span id="l833">        @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l833"></a>
<span id="l834">        public V next() {</span><a href="#l834"></a>
<span id="l835">            return (V) traversalTable[nextIndex() + 1];</span><a href="#l835"></a>
<span id="l836">        }</span><a href="#l836"></a>
<span id="l837">    }</span><a href="#l837"></a>
<span id="l838"></span><a href="#l838"></a>
<span id="l839">    private class EntryIterator</span><a href="#l839"></a>
<span id="l840">        extends IdentityHashMapIterator&lt;Map.Entry&lt;K,V&gt;&gt;</span><a href="#l840"></a>
<span id="l841">    {</span><a href="#l841"></a>
<span id="l842">        private Entry lastReturnedEntry;</span><a href="#l842"></a>
<span id="l843"></span><a href="#l843"></a>
<span id="l844">        public Map.Entry&lt;K,V&gt; next() {</span><a href="#l844"></a>
<span id="l845">            lastReturnedEntry = new Entry(nextIndex());</span><a href="#l845"></a>
<span id="l846">            return lastReturnedEntry;</span><a href="#l846"></a>
<span id="l847">        }</span><a href="#l847"></a>
<span id="l848"></span><a href="#l848"></a>
<span id="l849">        public void remove() {</span><a href="#l849"></a>
<span id="l850">            lastReturnedIndex =</span><a href="#l850"></a>
<span id="l851">                ((null == lastReturnedEntry) ? -1 : lastReturnedEntry.index);</span><a href="#l851"></a>
<span id="l852">            super.remove();</span><a href="#l852"></a>
<span id="l853">            lastReturnedEntry.index = lastReturnedIndex;</span><a href="#l853"></a>
<span id="l854">            lastReturnedEntry = null;</span><a href="#l854"></a>
<span id="l855">        }</span><a href="#l855"></a>
<span id="l856"></span><a href="#l856"></a>
<span id="l857">        private class Entry implements Map.Entry&lt;K,V&gt; {</span><a href="#l857"></a>
<span id="l858">            private int index;</span><a href="#l858"></a>
<span id="l859"></span><a href="#l859"></a>
<span id="l860">            private Entry(int index) {</span><a href="#l860"></a>
<span id="l861">                this.index = index;</span><a href="#l861"></a>
<span id="l862">            }</span><a href="#l862"></a>
<span id="l863"></span><a href="#l863"></a>
<span id="l864">            @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l864"></a>
<span id="l865">            public K getKey() {</span><a href="#l865"></a>
<span id="l866">                checkIndexForEntryUse();</span><a href="#l866"></a>
<span id="l867">                return (K) unmaskNull(traversalTable[index]);</span><a href="#l867"></a>
<span id="l868">            }</span><a href="#l868"></a>
<span id="l869"></span><a href="#l869"></a>
<span id="l870">            @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l870"></a>
<span id="l871">            public V getValue() {</span><a href="#l871"></a>
<span id="l872">                checkIndexForEntryUse();</span><a href="#l872"></a>
<span id="l873">                return (V) traversalTable[index+1];</span><a href="#l873"></a>
<span id="l874">            }</span><a href="#l874"></a>
<span id="l875"></span><a href="#l875"></a>
<span id="l876">            @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l876"></a>
<span id="l877">            public V setValue(V value) {</span><a href="#l877"></a>
<span id="l878">                checkIndexForEntryUse();</span><a href="#l878"></a>
<span id="l879">                V oldValue = (V) traversalTable[index+1];</span><a href="#l879"></a>
<span id="l880">                traversalTable[index+1] = value;</span><a href="#l880"></a>
<span id="l881">                // if shadowing, force into main table</span><a href="#l881"></a>
<span id="l882">                if (traversalTable != IdentityHashMap.this.table)</span><a href="#l882"></a>
<span id="l883">                    put((K) traversalTable[index], value);</span><a href="#l883"></a>
<span id="l884">                return oldValue;</span><a href="#l884"></a>
<span id="l885">            }</span><a href="#l885"></a>
<span id="l886"></span><a href="#l886"></a>
<span id="l887">            public boolean equals(Object o) {</span><a href="#l887"></a>
<span id="l888">                if (index &lt; 0)</span><a href="#l888"></a>
<span id="l889">                    return super.equals(o);</span><a href="#l889"></a>
<span id="l890"></span><a href="#l890"></a>
<span id="l891">                if (!(o instanceof Map.Entry))</span><a href="#l891"></a>
<span id="l892">                    return false;</span><a href="#l892"></a>
<span id="l893">                Map.Entry&lt;?,?&gt; e = (Map.Entry&lt;?,?&gt;)o;</span><a href="#l893"></a>
<span id="l894">                return (e.getKey() == unmaskNull(traversalTable[index]) &amp;&amp;</span><a href="#l894"></a>
<span id="l895">                       e.getValue() == traversalTable[index+1]);</span><a href="#l895"></a>
<span id="l896">            }</span><a href="#l896"></a>
<span id="l897"></span><a href="#l897"></a>
<span id="l898">            public int hashCode() {</span><a href="#l898"></a>
<span id="l899">                if (lastReturnedIndex &lt; 0)</span><a href="#l899"></a>
<span id="l900">                    return super.hashCode();</span><a href="#l900"></a>
<span id="l901"></span><a href="#l901"></a>
<span id="l902">                return (System.identityHashCode(unmaskNull(traversalTable[index])) ^</span><a href="#l902"></a>
<span id="l903">                       System.identityHashCode(traversalTable[index+1]));</span><a href="#l903"></a>
<span id="l904">            }</span><a href="#l904"></a>
<span id="l905"></span><a href="#l905"></a>
<span id="l906">            public String toString() {</span><a href="#l906"></a>
<span id="l907">                if (index &lt; 0)</span><a href="#l907"></a>
<span id="l908">                    return super.toString();</span><a href="#l908"></a>
<span id="l909"></span><a href="#l909"></a>
<span id="l910">                return (unmaskNull(traversalTable[index]) + &quot;=&quot;</span><a href="#l910"></a>
<span id="l911">                        + traversalTable[index+1]);</span><a href="#l911"></a>
<span id="l912">            }</span><a href="#l912"></a>
<span id="l913"></span><a href="#l913"></a>
<span id="l914">            private void checkIndexForEntryUse() {</span><a href="#l914"></a>
<span id="l915">                if (index &lt; 0)</span><a href="#l915"></a>
<span id="l916">                    throw new IllegalStateException(&quot;Entry was removed&quot;);</span><a href="#l916"></a>
<span id="l917">            }</span><a href="#l917"></a>
<span id="l918">        }</span><a href="#l918"></a>
<span id="l919">    }</span><a href="#l919"></a>
<span id="l920"></span><a href="#l920"></a>
<span id="l921">    // Views</span><a href="#l921"></a>
<span id="l922"></span><a href="#l922"></a>
<span id="l923">    /**</span><a href="#l923"></a>
<span id="l924">     * This field is initialized to contain an instance of the entry set</span><a href="#l924"></a>
<span id="l925">     * view the first time this view is requested.  The view is stateless,</span><a href="#l925"></a>
<span id="l926">     * so there's no reason to create more than one.</span><a href="#l926"></a>
<span id="l927">     */</span><a href="#l927"></a>
<span id="l928">    private transient Set&lt;Map.Entry&lt;K,V&gt;&gt; entrySet;</span><a href="#l928"></a>
<span id="l929"></span><a href="#l929"></a>
<span id="l930">    /**</span><a href="#l930"></a>
<span id="l931">     * Returns an identity-based set view of the keys contained in this map.</span><a href="#l931"></a>
<span id="l932">     * The set is backed by the map, so changes to the map are reflected in</span><a href="#l932"></a>
<span id="l933">     * the set, and vice-versa.  If the map is modified while an iteration</span><a href="#l933"></a>
<span id="l934">     * over the set is in progress, the results of the iteration are</span><a href="#l934"></a>
<span id="l935">     * undefined.  The set supports element removal, which removes the</span><a href="#l935"></a>
<span id="l936">     * corresponding mapping from the map, via the &lt;tt&gt;Iterator.remove&lt;/tt&gt;,</span><a href="#l936"></a>
<span id="l937">     * &lt;tt&gt;Set.remove&lt;/tt&gt;, &lt;tt&gt;removeAll&lt;/tt&gt;, &lt;tt&gt;retainAll&lt;/tt&gt;, and</span><a href="#l937"></a>
<span id="l938">     * &lt;tt&gt;clear&lt;/tt&gt; methods.  It does not support the &lt;tt&gt;add&lt;/tt&gt; or</span><a href="#l938"></a>
<span id="l939">     * &lt;tt&gt;addAll&lt;/tt&gt; methods.</span><a href="#l939"></a>
<span id="l940">     *</span><a href="#l940"></a>
<span id="l941">     * &lt;p&gt;&lt;b&gt;While the object returned by this method implements the</span><a href="#l941"></a>
<span id="l942">     * &lt;tt&gt;Set&lt;/tt&gt; interface, it does &lt;i&gt;not&lt;/i&gt; obey &lt;tt&gt;Set's&lt;/tt&gt; general</span><a href="#l942"></a>
<span id="l943">     * contract.  Like its backing map, the set returned by this method</span><a href="#l943"></a>
<span id="l944">     * defines element equality as reference-equality rather than</span><a href="#l944"></a>
<span id="l945">     * object-equality.  This affects the behavior of its &lt;tt&gt;contains&lt;/tt&gt;,</span><a href="#l945"></a>
<span id="l946">     * &lt;tt&gt;remove&lt;/tt&gt;, &lt;tt&gt;containsAll&lt;/tt&gt;, &lt;tt&gt;equals&lt;/tt&gt;, and</span><a href="#l946"></a>
<span id="l947">     * &lt;tt&gt;hashCode&lt;/tt&gt; methods.&lt;/b&gt;</span><a href="#l947"></a>
<span id="l948">     *</span><a href="#l948"></a>
<span id="l949">     * &lt;p&gt;&lt;b&gt;The &lt;tt&gt;equals&lt;/tt&gt; method of the returned set returns &lt;tt&gt;true&lt;/tt&gt;</span><a href="#l949"></a>
<span id="l950">     * only if the specified object is a set containing exactly the same</span><a href="#l950"></a>
<span id="l951">     * object references as the returned set.  The symmetry and transitivity</span><a href="#l951"></a>
<span id="l952">     * requirements of the &lt;tt&gt;Object.equals&lt;/tt&gt; contract may be violated if</span><a href="#l952"></a>
<span id="l953">     * the set returned by this method is compared to a normal set.  However,</span><a href="#l953"></a>
<span id="l954">     * the &lt;tt&gt;Object.equals&lt;/tt&gt; contract is guaranteed to hold among sets</span><a href="#l954"></a>
<span id="l955">     * returned by this method.&lt;/b&gt;</span><a href="#l955"></a>
<span id="l956">     *</span><a href="#l956"></a>
<span id="l957">     * &lt;p&gt;The &lt;tt&gt;hashCode&lt;/tt&gt; method of the returned set returns the sum of</span><a href="#l957"></a>
<span id="l958">     * the &lt;i&gt;identity hashcodes&lt;/i&gt; of the elements in the set, rather than</span><a href="#l958"></a>
<span id="l959">     * the sum of their hashcodes.  This is mandated by the change in the</span><a href="#l959"></a>
<span id="l960">     * semantics of the &lt;tt&gt;equals&lt;/tt&gt; method, in order to enforce the</span><a href="#l960"></a>
<span id="l961">     * general contract of the &lt;tt&gt;Object.hashCode&lt;/tt&gt; method among sets</span><a href="#l961"></a>
<span id="l962">     * returned by this method.</span><a href="#l962"></a>
<span id="l963">     *</span><a href="#l963"></a>
<span id="l964">     * @return an identity-based set view of the keys contained in this map</span><a href="#l964"></a>
<span id="l965">     * @see Object#equals(Object)</span><a href="#l965"></a>
<span id="l966">     * @see System#identityHashCode(Object)</span><a href="#l966"></a>
<span id="l967">     */</span><a href="#l967"></a>
<span id="l968">    public Set&lt;K&gt; keySet() {</span><a href="#l968"></a>
<span id="l969">        Set&lt;K&gt; ks = keySet;</span><a href="#l969"></a>
<span id="l970">        if (ks == null) {</span><a href="#l970"></a>
<span id="l971">            ks = new KeySet();</span><a href="#l971"></a>
<span id="l972">            keySet = ks;</span><a href="#l972"></a>
<span id="l973">        }</span><a href="#l973"></a>
<span id="l974">        return ks;</span><a href="#l974"></a>
<span id="l975">    }</span><a href="#l975"></a>
<span id="l976"></span><a href="#l976"></a>
<span id="l977">    private class KeySet extends AbstractSet&lt;K&gt; {</span><a href="#l977"></a>
<span id="l978">        public Iterator&lt;K&gt; iterator() {</span><a href="#l978"></a>
<span id="l979">            return new KeyIterator();</span><a href="#l979"></a>
<span id="l980">        }</span><a href="#l980"></a>
<span id="l981">        public int size() {</span><a href="#l981"></a>
<span id="l982">            return size;</span><a href="#l982"></a>
<span id="l983">        }</span><a href="#l983"></a>
<span id="l984">        public boolean contains(Object o) {</span><a href="#l984"></a>
<span id="l985">            return containsKey(o);</span><a href="#l985"></a>
<span id="l986">        }</span><a href="#l986"></a>
<span id="l987">        public boolean remove(Object o) {</span><a href="#l987"></a>
<span id="l988">            int oldSize = size;</span><a href="#l988"></a>
<span id="l989">            IdentityHashMap.this.remove(o);</span><a href="#l989"></a>
<span id="l990">            return size != oldSize;</span><a href="#l990"></a>
<span id="l991">        }</span><a href="#l991"></a>
<span id="l992">        /*</span><a href="#l992"></a>
<span id="l993">         * Must revert from AbstractSet's impl to AbstractCollection's, as</span><a href="#l993"></a>
<span id="l994">         * the former contains an optimization that results in incorrect</span><a href="#l994"></a>
<span id="l995">         * behavior when c is a smaller &quot;normal&quot; (non-identity-based) Set.</span><a href="#l995"></a>
<span id="l996">         */</span><a href="#l996"></a>
<span id="l997">        public boolean removeAll(Collection&lt;?&gt; c) {</span><a href="#l997"></a>
<span id="l998">            Objects.requireNonNull(c);</span><a href="#l998"></a>
<span id="l999">            boolean modified = false;</span><a href="#l999"></a>
<span id="l1000">            for (Iterator&lt;K&gt; i = iterator(); i.hasNext(); ) {</span><a href="#l1000"></a>
<span id="l1001">                if (c.contains(i.next())) {</span><a href="#l1001"></a>
<span id="l1002">                    i.remove();</span><a href="#l1002"></a>
<span id="l1003">                    modified = true;</span><a href="#l1003"></a>
<span id="l1004">                }</span><a href="#l1004"></a>
<span id="l1005">            }</span><a href="#l1005"></a>
<span id="l1006">            return modified;</span><a href="#l1006"></a>
<span id="l1007">        }</span><a href="#l1007"></a>
<span id="l1008">        public void clear() {</span><a href="#l1008"></a>
<span id="l1009">            IdentityHashMap.this.clear();</span><a href="#l1009"></a>
<span id="l1010">        }</span><a href="#l1010"></a>
<span id="l1011">        public int hashCode() {</span><a href="#l1011"></a>
<span id="l1012">            int result = 0;</span><a href="#l1012"></a>
<span id="l1013">            for (K key : this)</span><a href="#l1013"></a>
<span id="l1014">                result += System.identityHashCode(key);</span><a href="#l1014"></a>
<span id="l1015">            return result;</span><a href="#l1015"></a>
<span id="l1016">        }</span><a href="#l1016"></a>
<span id="l1017">        public Object[] toArray() {</span><a href="#l1017"></a>
<span id="l1018">            return toArray(new Object[0]);</span><a href="#l1018"></a>
<span id="l1019">        }</span><a href="#l1019"></a>
<span id="l1020">        @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l1020"></a>
<span id="l1021">        public &lt;T&gt; T[] toArray(T[] a) {</span><a href="#l1021"></a>
<span id="l1022">            int expectedModCount = modCount;</span><a href="#l1022"></a>
<span id="l1023">            int size = size();</span><a href="#l1023"></a>
<span id="l1024">            if (a.length &lt; size)</span><a href="#l1024"></a>
<span id="l1025">                a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);</span><a href="#l1025"></a>
<span id="l1026">            Object[] tab = table;</span><a href="#l1026"></a>
<span id="l1027">            int ti = 0;</span><a href="#l1027"></a>
<span id="l1028">            for (int si = 0; si &lt; tab.length; si += 2) {</span><a href="#l1028"></a>
<span id="l1029">                Object key;</span><a href="#l1029"></a>
<span id="l1030">                if ((key = tab[si]) != null) { // key present ?</span><a href="#l1030"></a>
<span id="l1031">                    // more elements than expected -&gt; concurrent modification from other thread</span><a href="#l1031"></a>
<span id="l1032">                    if (ti &gt;= size) {</span><a href="#l1032"></a>
<span id="l1033">                        throw new ConcurrentModificationException();</span><a href="#l1033"></a>
<span id="l1034">                    }</span><a href="#l1034"></a>
<span id="l1035">                    a[ti++] = (T) unmaskNull(key); // unmask key</span><a href="#l1035"></a>
<span id="l1036">                }</span><a href="#l1036"></a>
<span id="l1037">            }</span><a href="#l1037"></a>
<span id="l1038">            // fewer elements than expected or concurrent modification from other thread detected</span><a href="#l1038"></a>
<span id="l1039">            if (ti &lt; size || expectedModCount != modCount) {</span><a href="#l1039"></a>
<span id="l1040">                throw new ConcurrentModificationException();</span><a href="#l1040"></a>
<span id="l1041">            }</span><a href="#l1041"></a>
<span id="l1042">            // final null marker as per spec</span><a href="#l1042"></a>
<span id="l1043">            if (ti &lt; a.length) {</span><a href="#l1043"></a>
<span id="l1044">                a[ti] = null;</span><a href="#l1044"></a>
<span id="l1045">            }</span><a href="#l1045"></a>
<span id="l1046">            return a;</span><a href="#l1046"></a>
<span id="l1047">        }</span><a href="#l1047"></a>
<span id="l1048"></span><a href="#l1048"></a>
<span id="l1049">        public Spliterator&lt;K&gt; spliterator() {</span><a href="#l1049"></a>
<span id="l1050">            return new KeySpliterator&lt;&gt;(IdentityHashMap.this, 0, -1, 0, 0);</span><a href="#l1050"></a>
<span id="l1051">        }</span><a href="#l1051"></a>
<span id="l1052">    }</span><a href="#l1052"></a>
<span id="l1053"></span><a href="#l1053"></a>
<span id="l1054">    /**</span><a href="#l1054"></a>
<span id="l1055">     * Returns a {@link Collection} view of the values contained in this map.</span><a href="#l1055"></a>
<span id="l1056">     * The collection is backed by the map, so changes to the map are</span><a href="#l1056"></a>
<span id="l1057">     * reflected in the collection, and vice-versa.  If the map is</span><a href="#l1057"></a>
<span id="l1058">     * modified while an iteration over the collection is in progress,</span><a href="#l1058"></a>
<span id="l1059">     * the results of the iteration are undefined.  The collection</span><a href="#l1059"></a>
<span id="l1060">     * supports element removal, which removes the corresponding</span><a href="#l1060"></a>
<span id="l1061">     * mapping from the map, via the &lt;tt&gt;Iterator.remove&lt;/tt&gt;,</span><a href="#l1061"></a>
<span id="l1062">     * &lt;tt&gt;Collection.remove&lt;/tt&gt;, &lt;tt&gt;removeAll&lt;/tt&gt;,</span><a href="#l1062"></a>
<span id="l1063">     * &lt;tt&gt;retainAll&lt;/tt&gt; and &lt;tt&gt;clear&lt;/tt&gt; methods.  It does not</span><a href="#l1063"></a>
<span id="l1064">     * support the &lt;tt&gt;add&lt;/tt&gt; or &lt;tt&gt;addAll&lt;/tt&gt; methods.</span><a href="#l1064"></a>
<span id="l1065">     *</span><a href="#l1065"></a>
<span id="l1066">     * &lt;p&gt;&lt;b&gt;While the object returned by this method implements the</span><a href="#l1066"></a>
<span id="l1067">     * &lt;tt&gt;Collection&lt;/tt&gt; interface, it does &lt;i&gt;not&lt;/i&gt; obey</span><a href="#l1067"></a>
<span id="l1068">     * &lt;tt&gt;Collection's&lt;/tt&gt; general contract.  Like its backing map,</span><a href="#l1068"></a>
<span id="l1069">     * the collection returned by this method defines element equality as</span><a href="#l1069"></a>
<span id="l1070">     * reference-equality rather than object-equality.  This affects the</span><a href="#l1070"></a>
<span id="l1071">     * behavior of its &lt;tt&gt;contains&lt;/tt&gt;, &lt;tt&gt;remove&lt;/tt&gt; and</span><a href="#l1071"></a>
<span id="l1072">     * &lt;tt&gt;containsAll&lt;/tt&gt; methods.&lt;/b&gt;</span><a href="#l1072"></a>
<span id="l1073">     */</span><a href="#l1073"></a>
<span id="l1074">    public Collection&lt;V&gt; values() {</span><a href="#l1074"></a>
<span id="l1075">        Collection&lt;V&gt; vs = values;</span><a href="#l1075"></a>
<span id="l1076">        if (vs == null) {</span><a href="#l1076"></a>
<span id="l1077">            vs = new Values();</span><a href="#l1077"></a>
<span id="l1078">            values = vs;</span><a href="#l1078"></a>
<span id="l1079">        }</span><a href="#l1079"></a>
<span id="l1080">        return vs;</span><a href="#l1080"></a>
<span id="l1081">    }</span><a href="#l1081"></a>
<span id="l1082"></span><a href="#l1082"></a>
<span id="l1083">    private class Values extends AbstractCollection&lt;V&gt; {</span><a href="#l1083"></a>
<span id="l1084">        public Iterator&lt;V&gt; iterator() {</span><a href="#l1084"></a>
<span id="l1085">            return new ValueIterator();</span><a href="#l1085"></a>
<span id="l1086">        }</span><a href="#l1086"></a>
<span id="l1087">        public int size() {</span><a href="#l1087"></a>
<span id="l1088">            return size;</span><a href="#l1088"></a>
<span id="l1089">        }</span><a href="#l1089"></a>
<span id="l1090">        public boolean contains(Object o) {</span><a href="#l1090"></a>
<span id="l1091">            return containsValue(o);</span><a href="#l1091"></a>
<span id="l1092">        }</span><a href="#l1092"></a>
<span id="l1093">        public boolean remove(Object o) {</span><a href="#l1093"></a>
<span id="l1094">            for (Iterator&lt;V&gt; i = iterator(); i.hasNext(); ) {</span><a href="#l1094"></a>
<span id="l1095">                if (i.next() == o) {</span><a href="#l1095"></a>
<span id="l1096">                    i.remove();</span><a href="#l1096"></a>
<span id="l1097">                    return true;</span><a href="#l1097"></a>
<span id="l1098">                }</span><a href="#l1098"></a>
<span id="l1099">            }</span><a href="#l1099"></a>
<span id="l1100">            return false;</span><a href="#l1100"></a>
<span id="l1101">        }</span><a href="#l1101"></a>
<span id="l1102">        public void clear() {</span><a href="#l1102"></a>
<span id="l1103">            IdentityHashMap.this.clear();</span><a href="#l1103"></a>
<span id="l1104">        }</span><a href="#l1104"></a>
<span id="l1105">        public Object[] toArray() {</span><a href="#l1105"></a>
<span id="l1106">            return toArray(new Object[0]);</span><a href="#l1106"></a>
<span id="l1107">        }</span><a href="#l1107"></a>
<span id="l1108">        @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l1108"></a>
<span id="l1109">        public &lt;T&gt; T[] toArray(T[] a) {</span><a href="#l1109"></a>
<span id="l1110">            int expectedModCount = modCount;</span><a href="#l1110"></a>
<span id="l1111">            int size = size();</span><a href="#l1111"></a>
<span id="l1112">            if (a.length &lt; size)</span><a href="#l1112"></a>
<span id="l1113">                a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);</span><a href="#l1113"></a>
<span id="l1114">            Object[] tab = table;</span><a href="#l1114"></a>
<span id="l1115">            int ti = 0;</span><a href="#l1115"></a>
<span id="l1116">            for (int si = 0; si &lt; tab.length; si += 2) {</span><a href="#l1116"></a>
<span id="l1117">                if (tab[si] != null) { // key present ?</span><a href="#l1117"></a>
<span id="l1118">                    // more elements than expected -&gt; concurrent modification from other thread</span><a href="#l1118"></a>
<span id="l1119">                    if (ti &gt;= size) {</span><a href="#l1119"></a>
<span id="l1120">                        throw new ConcurrentModificationException();</span><a href="#l1120"></a>
<span id="l1121">                    }</span><a href="#l1121"></a>
<span id="l1122">                    a[ti++] = (T) tab[si+1]; // copy value</span><a href="#l1122"></a>
<span id="l1123">                }</span><a href="#l1123"></a>
<span id="l1124">            }</span><a href="#l1124"></a>
<span id="l1125">            // fewer elements than expected or concurrent modification from other thread detected</span><a href="#l1125"></a>
<span id="l1126">            if (ti &lt; size || expectedModCount != modCount) {</span><a href="#l1126"></a>
<span id="l1127">                throw new ConcurrentModificationException();</span><a href="#l1127"></a>
<span id="l1128">            }</span><a href="#l1128"></a>
<span id="l1129">            // final null marker as per spec</span><a href="#l1129"></a>
<span id="l1130">            if (ti &lt; a.length) {</span><a href="#l1130"></a>
<span id="l1131">                a[ti] = null;</span><a href="#l1131"></a>
<span id="l1132">            }</span><a href="#l1132"></a>
<span id="l1133">            return a;</span><a href="#l1133"></a>
<span id="l1134">        }</span><a href="#l1134"></a>
<span id="l1135"></span><a href="#l1135"></a>
<span id="l1136">        public Spliterator&lt;V&gt; spliterator() {</span><a href="#l1136"></a>
<span id="l1137">            return new ValueSpliterator&lt;&gt;(IdentityHashMap.this, 0, -1, 0, 0);</span><a href="#l1137"></a>
<span id="l1138">        }</span><a href="#l1138"></a>
<span id="l1139">    }</span><a href="#l1139"></a>
<span id="l1140"></span><a href="#l1140"></a>
<span id="l1141">    /**</span><a href="#l1141"></a>
<span id="l1142">     * Returns a {@link Set} view of the mappings contained in this map.</span><a href="#l1142"></a>
<span id="l1143">     * Each element in the returned set is a reference-equality-based</span><a href="#l1143"></a>
<span id="l1144">     * &lt;tt&gt;Map.Entry&lt;/tt&gt;.  The set is backed by the map, so changes</span><a href="#l1144"></a>
<span id="l1145">     * to the map are reflected in the set, and vice-versa.  If the</span><a href="#l1145"></a>
<span id="l1146">     * map is modified while an iteration over the set is in progress,</span><a href="#l1146"></a>
<span id="l1147">     * the results of the iteration are undefined.  The set supports</span><a href="#l1147"></a>
<span id="l1148">     * element removal, which removes the corresponding mapping from</span><a href="#l1148"></a>
<span id="l1149">     * the map, via the &lt;tt&gt;Iterator.remove&lt;/tt&gt;, &lt;tt&gt;Set.remove&lt;/tt&gt;,</span><a href="#l1149"></a>
<span id="l1150">     * &lt;tt&gt;removeAll&lt;/tt&gt;, &lt;tt&gt;retainAll&lt;/tt&gt; and &lt;tt&gt;clear&lt;/tt&gt;</span><a href="#l1150"></a>
<span id="l1151">     * methods.  It does not support the &lt;tt&gt;add&lt;/tt&gt; or</span><a href="#l1151"></a>
<span id="l1152">     * &lt;tt&gt;addAll&lt;/tt&gt; methods.</span><a href="#l1152"></a>
<span id="l1153">     *</span><a href="#l1153"></a>
<span id="l1154">     * &lt;p&gt;Like the backing map, the &lt;tt&gt;Map.Entry&lt;/tt&gt; objects in the set</span><a href="#l1154"></a>
<span id="l1155">     * returned by this method define key and value equality as</span><a href="#l1155"></a>
<span id="l1156">     * reference-equality rather than object-equality.  This affects the</span><a href="#l1156"></a>
<span id="l1157">     * behavior of the &lt;tt&gt;equals&lt;/tt&gt; and &lt;tt&gt;hashCode&lt;/tt&gt; methods of these</span><a href="#l1157"></a>
<span id="l1158">     * &lt;tt&gt;Map.Entry&lt;/tt&gt; objects.  A reference-equality based &lt;tt&gt;Map.Entry</span><a href="#l1158"></a>
<span id="l1159">     * e&lt;/tt&gt; is equal to an object &lt;tt&gt;o&lt;/tt&gt; if and only if &lt;tt&gt;o&lt;/tt&gt; is a</span><a href="#l1159"></a>
<span id="l1160">     * &lt;tt&gt;Map.Entry&lt;/tt&gt; and &lt;tt&gt;e.getKey()==o.getKey() &amp;amp;&amp;amp;</span><a href="#l1160"></a>
<span id="l1161">     * e.getValue()==o.getValue()&lt;/tt&gt;.  To accommodate these equals</span><a href="#l1161"></a>
<span id="l1162">     * semantics, the &lt;tt&gt;hashCode&lt;/tt&gt; method returns</span><a href="#l1162"></a>
<span id="l1163">     * &lt;tt&gt;System.identityHashCode(e.getKey()) ^</span><a href="#l1163"></a>
<span id="l1164">     * System.identityHashCode(e.getValue())&lt;/tt&gt;.</span><a href="#l1164"></a>
<span id="l1165">     *</span><a href="#l1165"></a>
<span id="l1166">     * &lt;p&gt;&lt;b&gt;Owing to the reference-equality-based semantics of the</span><a href="#l1166"></a>
<span id="l1167">     * &lt;tt&gt;Map.Entry&lt;/tt&gt; instances in the set returned by this method,</span><a href="#l1167"></a>
<span id="l1168">     * it is possible that the symmetry and transitivity requirements of</span><a href="#l1168"></a>
<span id="l1169">     * the {@link Object#equals(Object)} contract may be violated if any of</span><a href="#l1169"></a>
<span id="l1170">     * the entries in the set is compared to a normal map entry, or if</span><a href="#l1170"></a>
<span id="l1171">     * the set returned by this method is compared to a set of normal map</span><a href="#l1171"></a>
<span id="l1172">     * entries (such as would be returned by a call to this method on a normal</span><a href="#l1172"></a>
<span id="l1173">     * map).  However, the &lt;tt&gt;Object.equals&lt;/tt&gt; contract is guaranteed to</span><a href="#l1173"></a>
<span id="l1174">     * hold among identity-based map entries, and among sets of such entries.</span><a href="#l1174"></a>
<span id="l1175">     * &lt;/b&gt;</span><a href="#l1175"></a>
<span id="l1176">     *</span><a href="#l1176"></a>
<span id="l1177">     * @return a set view of the identity-mappings contained in this map</span><a href="#l1177"></a>
<span id="l1178">     */</span><a href="#l1178"></a>
<span id="l1179">    public Set&lt;Map.Entry&lt;K,V&gt;&gt; entrySet() {</span><a href="#l1179"></a>
<span id="l1180">        Set&lt;Map.Entry&lt;K,V&gt;&gt; es = entrySet;</span><a href="#l1180"></a>
<span id="l1181">        if (es != null)</span><a href="#l1181"></a>
<span id="l1182">            return es;</span><a href="#l1182"></a>
<span id="l1183">        else</span><a href="#l1183"></a>
<span id="l1184">            return entrySet = new EntrySet();</span><a href="#l1184"></a>
<span id="l1185">    }</span><a href="#l1185"></a>
<span id="l1186"></span><a href="#l1186"></a>
<span id="l1187">    private class EntrySet extends AbstractSet&lt;Map.Entry&lt;K,V&gt;&gt; {</span><a href="#l1187"></a>
<span id="l1188">        public Iterator&lt;Map.Entry&lt;K,V&gt;&gt; iterator() {</span><a href="#l1188"></a>
<span id="l1189">            return new EntryIterator();</span><a href="#l1189"></a>
<span id="l1190">        }</span><a href="#l1190"></a>
<span id="l1191">        public boolean contains(Object o) {</span><a href="#l1191"></a>
<span id="l1192">            if (!(o instanceof Map.Entry))</span><a href="#l1192"></a>
<span id="l1193">                return false;</span><a href="#l1193"></a>
<span id="l1194">            Map.Entry&lt;?,?&gt; entry = (Map.Entry&lt;?,?&gt;)o;</span><a href="#l1194"></a>
<span id="l1195">            return containsMapping(entry.getKey(), entry.getValue());</span><a href="#l1195"></a>
<span id="l1196">        }</span><a href="#l1196"></a>
<span id="l1197">        public boolean remove(Object o) {</span><a href="#l1197"></a>
<span id="l1198">            if (!(o instanceof Map.Entry))</span><a href="#l1198"></a>
<span id="l1199">                return false;</span><a href="#l1199"></a>
<span id="l1200">            Map.Entry&lt;?,?&gt; entry = (Map.Entry&lt;?,?&gt;)o;</span><a href="#l1200"></a>
<span id="l1201">            return removeMapping(entry.getKey(), entry.getValue());</span><a href="#l1201"></a>
<span id="l1202">        }</span><a href="#l1202"></a>
<span id="l1203">        public int size() {</span><a href="#l1203"></a>
<span id="l1204">            return size;</span><a href="#l1204"></a>
<span id="l1205">        }</span><a href="#l1205"></a>
<span id="l1206">        public void clear() {</span><a href="#l1206"></a>
<span id="l1207">            IdentityHashMap.this.clear();</span><a href="#l1207"></a>
<span id="l1208">        }</span><a href="#l1208"></a>
<span id="l1209">        /*</span><a href="#l1209"></a>
<span id="l1210">         * Must revert from AbstractSet's impl to AbstractCollection's, as</span><a href="#l1210"></a>
<span id="l1211">         * the former contains an optimization that results in incorrect</span><a href="#l1211"></a>
<span id="l1212">         * behavior when c is a smaller &quot;normal&quot; (non-identity-based) Set.</span><a href="#l1212"></a>
<span id="l1213">         */</span><a href="#l1213"></a>
<span id="l1214">        public boolean removeAll(Collection&lt;?&gt; c) {</span><a href="#l1214"></a>
<span id="l1215">            Objects.requireNonNull(c);</span><a href="#l1215"></a>
<span id="l1216">            boolean modified = false;</span><a href="#l1216"></a>
<span id="l1217">            for (Iterator&lt;Map.Entry&lt;K,V&gt;&gt; i = iterator(); i.hasNext(); ) {</span><a href="#l1217"></a>
<span id="l1218">                if (c.contains(i.next())) {</span><a href="#l1218"></a>
<span id="l1219">                    i.remove();</span><a href="#l1219"></a>
<span id="l1220">                    modified = true;</span><a href="#l1220"></a>
<span id="l1221">                }</span><a href="#l1221"></a>
<span id="l1222">            }</span><a href="#l1222"></a>
<span id="l1223">            return modified;</span><a href="#l1223"></a>
<span id="l1224">        }</span><a href="#l1224"></a>
<span id="l1225"></span><a href="#l1225"></a>
<span id="l1226">        public Object[] toArray() {</span><a href="#l1226"></a>
<span id="l1227">            return toArray(new Object[0]);</span><a href="#l1227"></a>
<span id="l1228">        }</span><a href="#l1228"></a>
<span id="l1229"></span><a href="#l1229"></a>
<span id="l1230">        @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l1230"></a>
<span id="l1231">        public &lt;T&gt; T[] toArray(T[] a) {</span><a href="#l1231"></a>
<span id="l1232">            int expectedModCount = modCount;</span><a href="#l1232"></a>
<span id="l1233">            int size = size();</span><a href="#l1233"></a>
<span id="l1234">            if (a.length &lt; size)</span><a href="#l1234"></a>
<span id="l1235">                a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);</span><a href="#l1235"></a>
<span id="l1236">            Object[] tab = table;</span><a href="#l1236"></a>
<span id="l1237">            int ti = 0;</span><a href="#l1237"></a>
<span id="l1238">            for (int si = 0; si &lt; tab.length; si += 2) {</span><a href="#l1238"></a>
<span id="l1239">                Object key;</span><a href="#l1239"></a>
<span id="l1240">                if ((key = tab[si]) != null) { // key present ?</span><a href="#l1240"></a>
<span id="l1241">                    // more elements than expected -&gt; concurrent modification from other thread</span><a href="#l1241"></a>
<span id="l1242">                    if (ti &gt;= size) {</span><a href="#l1242"></a>
<span id="l1243">                        throw new ConcurrentModificationException();</span><a href="#l1243"></a>
<span id="l1244">                    }</span><a href="#l1244"></a>
<span id="l1245">                    a[ti++] = (T) new AbstractMap.SimpleEntry&lt;&gt;(unmaskNull(key), tab[si + 1]);</span><a href="#l1245"></a>
<span id="l1246">                }</span><a href="#l1246"></a>
<span id="l1247">            }</span><a href="#l1247"></a>
<span id="l1248">            // fewer elements than expected or concurrent modification from other thread detected</span><a href="#l1248"></a>
<span id="l1249">            if (ti &lt; size || expectedModCount != modCount) {</span><a href="#l1249"></a>
<span id="l1250">                throw new ConcurrentModificationException();</span><a href="#l1250"></a>
<span id="l1251">            }</span><a href="#l1251"></a>
<span id="l1252">            // final null marker as per spec</span><a href="#l1252"></a>
<span id="l1253">            if (ti &lt; a.length) {</span><a href="#l1253"></a>
<span id="l1254">                a[ti] = null;</span><a href="#l1254"></a>
<span id="l1255">            }</span><a href="#l1255"></a>
<span id="l1256">            return a;</span><a href="#l1256"></a>
<span id="l1257">        }</span><a href="#l1257"></a>
<span id="l1258"></span><a href="#l1258"></a>
<span id="l1259">        public Spliterator&lt;Map.Entry&lt;K,V&gt;&gt; spliterator() {</span><a href="#l1259"></a>
<span id="l1260">            return new EntrySpliterator&lt;&gt;(IdentityHashMap.this, 0, -1, 0, 0);</span><a href="#l1260"></a>
<span id="l1261">        }</span><a href="#l1261"></a>
<span id="l1262">    }</span><a href="#l1262"></a>
<span id="l1263"></span><a href="#l1263"></a>
<span id="l1264"></span><a href="#l1264"></a>
<span id="l1265">    private static final long serialVersionUID = 8188218128353913216L;</span><a href="#l1265"></a>
<span id="l1266"></span><a href="#l1266"></a>
<span id="l1267">    /**</span><a href="#l1267"></a>
<span id="l1268">     * Saves the state of the &lt;tt&gt;IdentityHashMap&lt;/tt&gt; instance to a stream</span><a href="#l1268"></a>
<span id="l1269">     * (i.e., serializes it).</span><a href="#l1269"></a>
<span id="l1270">     *</span><a href="#l1270"></a>
<span id="l1271">     * @serialData The &lt;i&gt;size&lt;/i&gt; of the HashMap (the number of key-value</span><a href="#l1271"></a>
<span id="l1272">     *          mappings) (&lt;tt&gt;int&lt;/tt&gt;), followed by the key (Object) and</span><a href="#l1272"></a>
<span id="l1273">     *          value (Object) for each key-value mapping represented by the</span><a href="#l1273"></a>
<span id="l1274">     *          IdentityHashMap.  The key-value mappings are emitted in no</span><a href="#l1274"></a>
<span id="l1275">     *          particular order.</span><a href="#l1275"></a>
<span id="l1276">     */</span><a href="#l1276"></a>
<span id="l1277">    private void writeObject(ObjectOutputStream s)</span><a href="#l1277"></a>
<span id="l1278">        throws java.io.IOException  {</span><a href="#l1278"></a>
<span id="l1279">        // Write out size (number of mappings) and any hidden stuff</span><a href="#l1279"></a>
<span id="l1280">        s.defaultWriteObject();</span><a href="#l1280"></a>
<span id="l1281"></span><a href="#l1281"></a>
<span id="l1282">        // Write out size again (maintained for backward compatibility)</span><a href="#l1282"></a>
<span id="l1283">        s.writeInt(size);</span><a href="#l1283"></a>
<span id="l1284"></span><a href="#l1284"></a>
<span id="l1285">        // Write out keys and values (alternating)</span><a href="#l1285"></a>
<span id="l1286">        Object[] tab = table;</span><a href="#l1286"></a>
<span id="l1287">        for (int i = 0; i &lt; tab.length; i += 2) {</span><a href="#l1287"></a>
<span id="l1288">            Object key = tab[i];</span><a href="#l1288"></a>
<span id="l1289">            if (key != null) {</span><a href="#l1289"></a>
<span id="l1290">                s.writeObject(unmaskNull(key));</span><a href="#l1290"></a>
<span id="l1291">                s.writeObject(tab[i + 1]);</span><a href="#l1291"></a>
<span id="l1292">            }</span><a href="#l1292"></a>
<span id="l1293">        }</span><a href="#l1293"></a>
<span id="l1294">    }</span><a href="#l1294"></a>
<span id="l1295"></span><a href="#l1295"></a>
<span id="l1296">    /**</span><a href="#l1296"></a>
<span id="l1297">     * Reconstitutes the &lt;tt&gt;IdentityHashMap&lt;/tt&gt; instance from a stream (i.e.,</span><a href="#l1297"></a>
<span id="l1298">     * deserializes it).</span><a href="#l1298"></a>
<span id="l1299">     */</span><a href="#l1299"></a>
<span id="l1300">    private void readObject(ObjectInputStream s)</span><a href="#l1300"></a>
<span id="l1301">        throws java.io.IOException, ClassNotFoundException  {</span><a href="#l1301"></a>
<span id="l1302">        // Size (number of mappings) is written to the stream twice</span><a href="#l1302"></a>
<span id="l1303">        // Read first size value and ignore it</span><a href="#l1303"></a>
<span id="l1304">        s.readFields();</span><a href="#l1304"></a>
<span id="l1305"></span><a href="#l1305"></a>
<span id="l1306">        // Read second size value, validate and assign to size field</span><a href="#l1306"></a>
<span id="l1307">        int size = s.readInt();</span><a href="#l1307"></a>
<span id="l1308">        if (size &lt; 0)</span><a href="#l1308"></a>
<span id="l1309">            throw new java.io.StreamCorruptedException</span><a href="#l1309"></a>
<span id="l1310">                (&quot;Illegal mappings count: &quot; + size);</span><a href="#l1310"></a>
<span id="l1311">        int cap = capacity(size);</span><a href="#l1311"></a>
<span id="l1312">        SharedSecrets.getJavaOISAccess().checkArray(s, Object[].class, cap*2);</span><a href="#l1312"></a>
<span id="l1313">        this.size = size;</span><a href="#l1313"></a>
<span id="l1314">        init(cap);</span><a href="#l1314"></a>
<span id="l1315"></span><a href="#l1315"></a>
<span id="l1316">        // Read the keys and values, and put the mappings in the table</span><a href="#l1316"></a>
<span id="l1317">        for (int i=0; i&lt;size; i++) {</span><a href="#l1317"></a>
<span id="l1318">            @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l1318"></a>
<span id="l1319">                K key = (K) s.readObject();</span><a href="#l1319"></a>
<span id="l1320">            @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l1320"></a>
<span id="l1321">                V value = (V) s.readObject();</span><a href="#l1321"></a>
<span id="l1322">            putForCreate(key, value);</span><a href="#l1322"></a>
<span id="l1323">        }</span><a href="#l1323"></a>
<span id="l1324">    }</span><a href="#l1324"></a>
<span id="l1325"></span><a href="#l1325"></a>
<span id="l1326">    /**</span><a href="#l1326"></a>
<span id="l1327">     * The put method for readObject.  It does not resize the table,</span><a href="#l1327"></a>
<span id="l1328">     * update modCount, etc.</span><a href="#l1328"></a>
<span id="l1329">     */</span><a href="#l1329"></a>
<span id="l1330">    private void putForCreate(K key, V value)</span><a href="#l1330"></a>
<span id="l1331">        throws java.io.StreamCorruptedException</span><a href="#l1331"></a>
<span id="l1332">    {</span><a href="#l1332"></a>
<span id="l1333">        Object k = maskNull(key);</span><a href="#l1333"></a>
<span id="l1334">        Object[] tab = table;</span><a href="#l1334"></a>
<span id="l1335">        int len = tab.length;</span><a href="#l1335"></a>
<span id="l1336">        int i = hash(k, len);</span><a href="#l1336"></a>
<span id="l1337"></span><a href="#l1337"></a>
<span id="l1338">        Object item;</span><a href="#l1338"></a>
<span id="l1339">        while ( (item = tab[i]) != null) {</span><a href="#l1339"></a>
<span id="l1340">            if (item == k)</span><a href="#l1340"></a>
<span id="l1341">                throw new java.io.StreamCorruptedException();</span><a href="#l1341"></a>
<span id="l1342">            i = nextKeyIndex(i, len);</span><a href="#l1342"></a>
<span id="l1343">        }</span><a href="#l1343"></a>
<span id="l1344">        tab[i] = k;</span><a href="#l1344"></a>
<span id="l1345">        tab[i + 1] = value;</span><a href="#l1345"></a>
<span id="l1346">    }</span><a href="#l1346"></a>
<span id="l1347"></span><a href="#l1347"></a>
<span id="l1348">    @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l1348"></a>
<span id="l1349">    @Override</span><a href="#l1349"></a>
<span id="l1350">    public void forEach(BiConsumer&lt;? super K, ? super V&gt; action) {</span><a href="#l1350"></a>
<span id="l1351">        Objects.requireNonNull(action);</span><a href="#l1351"></a>
<span id="l1352">        int expectedModCount = modCount;</span><a href="#l1352"></a>
<span id="l1353"></span><a href="#l1353"></a>
<span id="l1354">        Object[] t = table;</span><a href="#l1354"></a>
<span id="l1355">        for (int index = 0; index &lt; t.length; index += 2) {</span><a href="#l1355"></a>
<span id="l1356">            Object k = t[index];</span><a href="#l1356"></a>
<span id="l1357">            if (k != null) {</span><a href="#l1357"></a>
<span id="l1358">                action.accept((K) unmaskNull(k), (V) t[index + 1]);</span><a href="#l1358"></a>
<span id="l1359">            }</span><a href="#l1359"></a>
<span id="l1360"></span><a href="#l1360"></a>
<span id="l1361">            if (modCount != expectedModCount) {</span><a href="#l1361"></a>
<span id="l1362">                throw new ConcurrentModificationException();</span><a href="#l1362"></a>
<span id="l1363">            }</span><a href="#l1363"></a>
<span id="l1364">        }</span><a href="#l1364"></a>
<span id="l1365">    }</span><a href="#l1365"></a>
<span id="l1366"></span><a href="#l1366"></a>
<span id="l1367">    @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l1367"></a>
<span id="l1368">    @Override</span><a href="#l1368"></a>
<span id="l1369">    public void replaceAll(BiFunction&lt;? super K, ? super V, ? extends V&gt; function) {</span><a href="#l1369"></a>
<span id="l1370">        Objects.requireNonNull(function);</span><a href="#l1370"></a>
<span id="l1371">        int expectedModCount = modCount;</span><a href="#l1371"></a>
<span id="l1372"></span><a href="#l1372"></a>
<span id="l1373">        Object[] t = table;</span><a href="#l1373"></a>
<span id="l1374">        for (int index = 0; index &lt; t.length; index += 2) {</span><a href="#l1374"></a>
<span id="l1375">            Object k = t[index];</span><a href="#l1375"></a>
<span id="l1376">            if (k != null) {</span><a href="#l1376"></a>
<span id="l1377">                t[index + 1] = function.apply((K) unmaskNull(k), (V) t[index + 1]);</span><a href="#l1377"></a>
<span id="l1378">            }</span><a href="#l1378"></a>
<span id="l1379"></span><a href="#l1379"></a>
<span id="l1380">            if (modCount != expectedModCount) {</span><a href="#l1380"></a>
<span id="l1381">                throw new ConcurrentModificationException();</span><a href="#l1381"></a>
<span id="l1382">            }</span><a href="#l1382"></a>
<span id="l1383">        }</span><a href="#l1383"></a>
<span id="l1384">    }</span><a href="#l1384"></a>
<span id="l1385"></span><a href="#l1385"></a>
<span id="l1386">    /**</span><a href="#l1386"></a>
<span id="l1387">     * Similar form as array-based Spliterators, but skips blank elements,</span><a href="#l1387"></a>
<span id="l1388">     * and guestimates size as decreasing by half per split.</span><a href="#l1388"></a>
<span id="l1389">     */</span><a href="#l1389"></a>
<span id="l1390">    static class IdentityHashMapSpliterator&lt;K,V&gt; {</span><a href="#l1390"></a>
<span id="l1391">        final IdentityHashMap&lt;K,V&gt; map;</span><a href="#l1391"></a>
<span id="l1392">        int index;             // current index, modified on advance/split</span><a href="#l1392"></a>
<span id="l1393">        int fence;             // -1 until first use; then one past last index</span><a href="#l1393"></a>
<span id="l1394">        int est;               // size estimate</span><a href="#l1394"></a>
<span id="l1395">        int expectedModCount;  // initialized when fence set</span><a href="#l1395"></a>
<span id="l1396"></span><a href="#l1396"></a>
<span id="l1397">        IdentityHashMapSpliterator(IdentityHashMap&lt;K,V&gt; map, int origin,</span><a href="#l1397"></a>
<span id="l1398">                                   int fence, int est, int expectedModCount) {</span><a href="#l1398"></a>
<span id="l1399">            this.map = map;</span><a href="#l1399"></a>
<span id="l1400">            this.index = origin;</span><a href="#l1400"></a>
<span id="l1401">            this.fence = fence;</span><a href="#l1401"></a>
<span id="l1402">            this.est = est;</span><a href="#l1402"></a>
<span id="l1403">            this.expectedModCount = expectedModCount;</span><a href="#l1403"></a>
<span id="l1404">        }</span><a href="#l1404"></a>
<span id="l1405"></span><a href="#l1405"></a>
<span id="l1406">        final int getFence() { // initialize fence and size on first use</span><a href="#l1406"></a>
<span id="l1407">            int hi;</span><a href="#l1407"></a>
<span id="l1408">            if ((hi = fence) &lt; 0) {</span><a href="#l1408"></a>
<span id="l1409">                est = map.size;</span><a href="#l1409"></a>
<span id="l1410">                expectedModCount = map.modCount;</span><a href="#l1410"></a>
<span id="l1411">                hi = fence = map.table.length;</span><a href="#l1411"></a>
<span id="l1412">            }</span><a href="#l1412"></a>
<span id="l1413">            return hi;</span><a href="#l1413"></a>
<span id="l1414">        }</span><a href="#l1414"></a>
<span id="l1415"></span><a href="#l1415"></a>
<span id="l1416">        public final long estimateSize() {</span><a href="#l1416"></a>
<span id="l1417">            getFence(); // force init</span><a href="#l1417"></a>
<span id="l1418">            return (long) est;</span><a href="#l1418"></a>
<span id="l1419">        }</span><a href="#l1419"></a>
<span id="l1420">    }</span><a href="#l1420"></a>
<span id="l1421"></span><a href="#l1421"></a>
<span id="l1422">    static final class KeySpliterator&lt;K,V&gt;</span><a href="#l1422"></a>
<span id="l1423">        extends IdentityHashMapSpliterator&lt;K,V&gt;</span><a href="#l1423"></a>
<span id="l1424">        implements Spliterator&lt;K&gt; {</span><a href="#l1424"></a>
<span id="l1425">        KeySpliterator(IdentityHashMap&lt;K,V&gt; map, int origin, int fence, int est,</span><a href="#l1425"></a>
<span id="l1426">                       int expectedModCount) {</span><a href="#l1426"></a>
<span id="l1427">            super(map, origin, fence, est, expectedModCount);</span><a href="#l1427"></a>
<span id="l1428">        }</span><a href="#l1428"></a>
<span id="l1429"></span><a href="#l1429"></a>
<span id="l1430">        public KeySpliterator&lt;K,V&gt; trySplit() {</span><a href="#l1430"></a>
<span id="l1431">            int hi = getFence(), lo = index, mid = ((lo + hi) &gt;&gt;&gt; 1) &amp; ~1;</span><a href="#l1431"></a>
<span id="l1432">            return (lo &gt;= mid) ? null :</span><a href="#l1432"></a>
<span id="l1433">                new KeySpliterator&lt;K,V&gt;(map, lo, index = mid, est &gt;&gt;&gt;= 1,</span><a href="#l1433"></a>
<span id="l1434">                                        expectedModCount);</span><a href="#l1434"></a>
<span id="l1435">        }</span><a href="#l1435"></a>
<span id="l1436"></span><a href="#l1436"></a>
<span id="l1437">        @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l1437"></a>
<span id="l1438">        public void forEachRemaining(Consumer&lt;? super K&gt; action) {</span><a href="#l1438"></a>
<span id="l1439">            if (action == null)</span><a href="#l1439"></a>
<span id="l1440">                throw new NullPointerException();</span><a href="#l1440"></a>
<span id="l1441">            int i, hi, mc; Object key;</span><a href="#l1441"></a>
<span id="l1442">            IdentityHashMap&lt;K,V&gt; m; Object[] a;</span><a href="#l1442"></a>
<span id="l1443">            if ((m = map) != null &amp;&amp; (a = m.table) != null &amp;&amp;</span><a href="#l1443"></a>
<span id="l1444">                (i = index) &gt;= 0 &amp;&amp; (index = hi = getFence()) &lt;= a.length) {</span><a href="#l1444"></a>
<span id="l1445">                for (; i &lt; hi; i += 2) {</span><a href="#l1445"></a>
<span id="l1446">                    if ((key = a[i]) != null)</span><a href="#l1446"></a>
<span id="l1447">                        action.accept((K)unmaskNull(key));</span><a href="#l1447"></a>
<span id="l1448">                }</span><a href="#l1448"></a>
<span id="l1449">                if (m.modCount == expectedModCount)</span><a href="#l1449"></a>
<span id="l1450">                    return;</span><a href="#l1450"></a>
<span id="l1451">            }</span><a href="#l1451"></a>
<span id="l1452">            throw new ConcurrentModificationException();</span><a href="#l1452"></a>
<span id="l1453">        }</span><a href="#l1453"></a>
<span id="l1454"></span><a href="#l1454"></a>
<span id="l1455">        @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l1455"></a>
<span id="l1456">        public boolean tryAdvance(Consumer&lt;? super K&gt; action) {</span><a href="#l1456"></a>
<span id="l1457">            if (action == null)</span><a href="#l1457"></a>
<span id="l1458">                throw new NullPointerException();</span><a href="#l1458"></a>
<span id="l1459">            Object[] a = map.table;</span><a href="#l1459"></a>
<span id="l1460">            int hi = getFence();</span><a href="#l1460"></a>
<span id="l1461">            while (index &lt; hi) {</span><a href="#l1461"></a>
<span id="l1462">                Object key = a[index];</span><a href="#l1462"></a>
<span id="l1463">                index += 2;</span><a href="#l1463"></a>
<span id="l1464">                if (key != null) {</span><a href="#l1464"></a>
<span id="l1465">                    action.accept((K)unmaskNull(key));</span><a href="#l1465"></a>
<span id="l1466">                    if (map.modCount != expectedModCount)</span><a href="#l1466"></a>
<span id="l1467">                        throw new ConcurrentModificationException();</span><a href="#l1467"></a>
<span id="l1468">                    return true;</span><a href="#l1468"></a>
<span id="l1469">                }</span><a href="#l1469"></a>
<span id="l1470">            }</span><a href="#l1470"></a>
<span id="l1471">            return false;</span><a href="#l1471"></a>
<span id="l1472">        }</span><a href="#l1472"></a>
<span id="l1473"></span><a href="#l1473"></a>
<span id="l1474">        public int characteristics() {</span><a href="#l1474"></a>
<span id="l1475">            return (fence &lt; 0 || est == map.size ? SIZED : 0) | Spliterator.DISTINCT;</span><a href="#l1475"></a>
<span id="l1476">        }</span><a href="#l1476"></a>
<span id="l1477">    }</span><a href="#l1477"></a>
<span id="l1478"></span><a href="#l1478"></a>
<span id="l1479">    static final class ValueSpliterator&lt;K,V&gt;</span><a href="#l1479"></a>
<span id="l1480">        extends IdentityHashMapSpliterator&lt;K,V&gt;</span><a href="#l1480"></a>
<span id="l1481">        implements Spliterator&lt;V&gt; {</span><a href="#l1481"></a>
<span id="l1482">        ValueSpliterator(IdentityHashMap&lt;K,V&gt; m, int origin, int fence, int est,</span><a href="#l1482"></a>
<span id="l1483">                         int expectedModCount) {</span><a href="#l1483"></a>
<span id="l1484">            super(m, origin, fence, est, expectedModCount);</span><a href="#l1484"></a>
<span id="l1485">        }</span><a href="#l1485"></a>
<span id="l1486"></span><a href="#l1486"></a>
<span id="l1487">        public ValueSpliterator&lt;K,V&gt; trySplit() {</span><a href="#l1487"></a>
<span id="l1488">            int hi = getFence(), lo = index, mid = ((lo + hi) &gt;&gt;&gt; 1) &amp; ~1;</span><a href="#l1488"></a>
<span id="l1489">            return (lo &gt;= mid) ? null :</span><a href="#l1489"></a>
<span id="l1490">                new ValueSpliterator&lt;K,V&gt;(map, lo, index = mid, est &gt;&gt;&gt;= 1,</span><a href="#l1490"></a>
<span id="l1491">                                          expectedModCount);</span><a href="#l1491"></a>
<span id="l1492">        }</span><a href="#l1492"></a>
<span id="l1493"></span><a href="#l1493"></a>
<span id="l1494">        public void forEachRemaining(Consumer&lt;? super V&gt; action) {</span><a href="#l1494"></a>
<span id="l1495">            if (action == null)</span><a href="#l1495"></a>
<span id="l1496">                throw new NullPointerException();</span><a href="#l1496"></a>
<span id="l1497">            int i, hi, mc;</span><a href="#l1497"></a>
<span id="l1498">            IdentityHashMap&lt;K,V&gt; m; Object[] a;</span><a href="#l1498"></a>
<span id="l1499">            if ((m = map) != null &amp;&amp; (a = m.table) != null &amp;&amp;</span><a href="#l1499"></a>
<span id="l1500">                (i = index) &gt;= 0 &amp;&amp; (index = hi = getFence()) &lt;= a.length) {</span><a href="#l1500"></a>
<span id="l1501">                for (; i &lt; hi; i += 2) {</span><a href="#l1501"></a>
<span id="l1502">                    if (a[i] != null) {</span><a href="#l1502"></a>
<span id="l1503">                        @SuppressWarnings(&quot;unchecked&quot;) V v = (V)a[i+1];</span><a href="#l1503"></a>
<span id="l1504">                        action.accept(v);</span><a href="#l1504"></a>
<span id="l1505">                    }</span><a href="#l1505"></a>
<span id="l1506">                }</span><a href="#l1506"></a>
<span id="l1507">                if (m.modCount == expectedModCount)</span><a href="#l1507"></a>
<span id="l1508">                    return;</span><a href="#l1508"></a>
<span id="l1509">            }</span><a href="#l1509"></a>
<span id="l1510">            throw new ConcurrentModificationException();</span><a href="#l1510"></a>
<span id="l1511">        }</span><a href="#l1511"></a>
<span id="l1512"></span><a href="#l1512"></a>
<span id="l1513">        public boolean tryAdvance(Consumer&lt;? super V&gt; action) {</span><a href="#l1513"></a>
<span id="l1514">            if (action == null)</span><a href="#l1514"></a>
<span id="l1515">                throw new NullPointerException();</span><a href="#l1515"></a>
<span id="l1516">            Object[] a = map.table;</span><a href="#l1516"></a>
<span id="l1517">            int hi = getFence();</span><a href="#l1517"></a>
<span id="l1518">            while (index &lt; hi) {</span><a href="#l1518"></a>
<span id="l1519">                Object key = a[index];</span><a href="#l1519"></a>
<span id="l1520">                @SuppressWarnings(&quot;unchecked&quot;) V v = (V)a[index+1];</span><a href="#l1520"></a>
<span id="l1521">                index += 2;</span><a href="#l1521"></a>
<span id="l1522">                if (key != null) {</span><a href="#l1522"></a>
<span id="l1523">                    action.accept(v);</span><a href="#l1523"></a>
<span id="l1524">                    if (map.modCount != expectedModCount)</span><a href="#l1524"></a>
<span id="l1525">                        throw new ConcurrentModificationException();</span><a href="#l1525"></a>
<span id="l1526">                    return true;</span><a href="#l1526"></a>
<span id="l1527">                }</span><a href="#l1527"></a>
<span id="l1528">            }</span><a href="#l1528"></a>
<span id="l1529">            return false;</span><a href="#l1529"></a>
<span id="l1530">        }</span><a href="#l1530"></a>
<span id="l1531"></span><a href="#l1531"></a>
<span id="l1532">        public int characteristics() {</span><a href="#l1532"></a>
<span id="l1533">            return (fence &lt; 0 || est == map.size ? SIZED : 0);</span><a href="#l1533"></a>
<span id="l1534">        }</span><a href="#l1534"></a>
<span id="l1535"></span><a href="#l1535"></a>
<span id="l1536">    }</span><a href="#l1536"></a>
<span id="l1537"></span><a href="#l1537"></a>
<span id="l1538">    static final class EntrySpliterator&lt;K,V&gt;</span><a href="#l1538"></a>
<span id="l1539">        extends IdentityHashMapSpliterator&lt;K,V&gt;</span><a href="#l1539"></a>
<span id="l1540">        implements Spliterator&lt;Map.Entry&lt;K,V&gt;&gt; {</span><a href="#l1540"></a>
<span id="l1541">        EntrySpliterator(IdentityHashMap&lt;K,V&gt; m, int origin, int fence, int est,</span><a href="#l1541"></a>
<span id="l1542">                         int expectedModCount) {</span><a href="#l1542"></a>
<span id="l1543">            super(m, origin, fence, est, expectedModCount);</span><a href="#l1543"></a>
<span id="l1544">        }</span><a href="#l1544"></a>
<span id="l1545"></span><a href="#l1545"></a>
<span id="l1546">        public EntrySpliterator&lt;K,V&gt; trySplit() {</span><a href="#l1546"></a>
<span id="l1547">            int hi = getFence(), lo = index, mid = ((lo + hi) &gt;&gt;&gt; 1) &amp; ~1;</span><a href="#l1547"></a>
<span id="l1548">            return (lo &gt;= mid) ? null :</span><a href="#l1548"></a>
<span id="l1549">                new EntrySpliterator&lt;K,V&gt;(map, lo, index = mid, est &gt;&gt;&gt;= 1,</span><a href="#l1549"></a>
<span id="l1550">                                          expectedModCount);</span><a href="#l1550"></a>
<span id="l1551">        }</span><a href="#l1551"></a>
<span id="l1552"></span><a href="#l1552"></a>
<span id="l1553">        public void forEachRemaining(Consumer&lt;? super Map.Entry&lt;K, V&gt;&gt; action) {</span><a href="#l1553"></a>
<span id="l1554">            if (action == null)</span><a href="#l1554"></a>
<span id="l1555">                throw new NullPointerException();</span><a href="#l1555"></a>
<span id="l1556">            int i, hi, mc;</span><a href="#l1556"></a>
<span id="l1557">            IdentityHashMap&lt;K,V&gt; m; Object[] a;</span><a href="#l1557"></a>
<span id="l1558">            if ((m = map) != null &amp;&amp; (a = m.table) != null &amp;&amp;</span><a href="#l1558"></a>
<span id="l1559">                (i = index) &gt;= 0 &amp;&amp; (index = hi = getFence()) &lt;= a.length) {</span><a href="#l1559"></a>
<span id="l1560">                for (; i &lt; hi; i += 2) {</span><a href="#l1560"></a>
<span id="l1561">                    Object key = a[i];</span><a href="#l1561"></a>
<span id="l1562">                    if (key != null) {</span><a href="#l1562"></a>
<span id="l1563">                        @SuppressWarnings(&quot;unchecked&quot;) K k =</span><a href="#l1563"></a>
<span id="l1564">                            (K)unmaskNull(key);</span><a href="#l1564"></a>
<span id="l1565">                        @SuppressWarnings(&quot;unchecked&quot;) V v = (V)a[i+1];</span><a href="#l1565"></a>
<span id="l1566">                        action.accept</span><a href="#l1566"></a>
<span id="l1567">                            (new AbstractMap.SimpleImmutableEntry&lt;K,V&gt;(k, v));</span><a href="#l1567"></a>
<span id="l1568"></span><a href="#l1568"></a>
<span id="l1569">                    }</span><a href="#l1569"></a>
<span id="l1570">                }</span><a href="#l1570"></a>
<span id="l1571">                if (m.modCount == expectedModCount)</span><a href="#l1571"></a>
<span id="l1572">                    return;</span><a href="#l1572"></a>
<span id="l1573">            }</span><a href="#l1573"></a>
<span id="l1574">            throw new ConcurrentModificationException();</span><a href="#l1574"></a>
<span id="l1575">        }</span><a href="#l1575"></a>
<span id="l1576"></span><a href="#l1576"></a>
<span id="l1577">        public boolean tryAdvance(Consumer&lt;? super Map.Entry&lt;K,V&gt;&gt; action) {</span><a href="#l1577"></a>
<span id="l1578">            if (action == null)</span><a href="#l1578"></a>
<span id="l1579">                throw new NullPointerException();</span><a href="#l1579"></a>
<span id="l1580">            Object[] a = map.table;</span><a href="#l1580"></a>
<span id="l1581">            int hi = getFence();</span><a href="#l1581"></a>
<span id="l1582">            while (index &lt; hi) {</span><a href="#l1582"></a>
<span id="l1583">                Object key = a[index];</span><a href="#l1583"></a>
<span id="l1584">                @SuppressWarnings(&quot;unchecked&quot;) V v = (V)a[index+1];</span><a href="#l1584"></a>
<span id="l1585">                index += 2;</span><a href="#l1585"></a>
<span id="l1586">                if (key != null) {</span><a href="#l1586"></a>
<span id="l1587">                    @SuppressWarnings(&quot;unchecked&quot;) K k =</span><a href="#l1587"></a>
<span id="l1588">                        (K)unmaskNull(key);</span><a href="#l1588"></a>
<span id="l1589">                    action.accept</span><a href="#l1589"></a>
<span id="l1590">                        (new AbstractMap.SimpleImmutableEntry&lt;K,V&gt;(k, v));</span><a href="#l1590"></a>
<span id="l1591">                    if (map.modCount != expectedModCount)</span><a href="#l1591"></a>
<span id="l1592">                        throw new ConcurrentModificationException();</span><a href="#l1592"></a>
<span id="l1593">                    return true;</span><a href="#l1593"></a>
<span id="l1594">                }</span><a href="#l1594"></a>
<span id="l1595">            }</span><a href="#l1595"></a>
<span id="l1596">            return false;</span><a href="#l1596"></a>
<span id="l1597">        }</span><a href="#l1597"></a>
<span id="l1598"></span><a href="#l1598"></a>
<span id="l1599">        public int characteristics() {</span><a href="#l1599"></a>
<span id="l1600">            return (fence &lt; 0 || est == map.size ? SIZED : 0) | Spliterator.DISTINCT;</span><a href="#l1600"></a>
<span id="l1601">        }</span><a href="#l1601"></a>
<span id="l1602">    }</span><a href="#l1602"></a>
<span id="l1603"></span><a href="#l1603"></a>
<span id="l1604">}</span><a href="#l1604"></a></pre>
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

