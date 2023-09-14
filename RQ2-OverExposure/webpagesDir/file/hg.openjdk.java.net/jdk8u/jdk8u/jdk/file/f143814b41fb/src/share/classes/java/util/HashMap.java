<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: f143814b41fb src/share/classes/java/util/HashMap.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/java/util/HashMap.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/f143814b41fb/src/share/classes/java/util/HashMap.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/f143814b41fb/src/share/classes/java/util/HashMap.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/f143814b41fb/src/share/classes/java/util/HashMap.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/f143814b41fb/src/share/classes/java/util/HashMap.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/f143814b41fb/src/share/classes/java/util/HashMap.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/java/util/HashMap.java @ 14560:f143814b41fb</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/444b4528c8ec/src/share/classes/java/util/HashMap.java">444b4528c8ec</a> </td>
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
<span id="l28">import java.io.IOException;</span><a href="#l28"></a>
<span id="l29">import java.io.InvalidObjectException;</span><a href="#l29"></a>
<span id="l30">import java.io.ObjectInputStream;</span><a href="#l30"></a>
<span id="l31">import java.io.Serializable;</span><a href="#l31"></a>
<span id="l32">import java.lang.reflect.ParameterizedType;</span><a href="#l32"></a>
<span id="l33">import java.lang.reflect.Type;</span><a href="#l33"></a>
<span id="l34">import java.util.function.BiConsumer;</span><a href="#l34"></a>
<span id="l35">import java.util.function.BiFunction;</span><a href="#l35"></a>
<span id="l36">import java.util.function.Consumer;</span><a href="#l36"></a>
<span id="l37">import java.util.function.Function;</span><a href="#l37"></a>
<span id="l38">import sun.misc.SharedSecrets;</span><a href="#l38"></a>
<span id="l39"></span><a href="#l39"></a>
<span id="l40">/**</span><a href="#l40"></a>
<span id="l41"> * Hash table based implementation of the &lt;tt&gt;Map&lt;/tt&gt; interface.  This</span><a href="#l41"></a>
<span id="l42"> * implementation provides all of the optional map operations, and permits</span><a href="#l42"></a>
<span id="l43"> * &lt;tt&gt;null&lt;/tt&gt; values and the &lt;tt&gt;null&lt;/tt&gt; key.  (The &lt;tt&gt;HashMap&lt;/tt&gt;</span><a href="#l43"></a>
<span id="l44"> * class is roughly equivalent to &lt;tt&gt;Hashtable&lt;/tt&gt;, except that it is</span><a href="#l44"></a>
<span id="l45"> * unsynchronized and permits nulls.)  This class makes no guarantees as to</span><a href="#l45"></a>
<span id="l46"> * the order of the map; in particular, it does not guarantee that the order</span><a href="#l46"></a>
<span id="l47"> * will remain constant over time.</span><a href="#l47"></a>
<span id="l48"> *</span><a href="#l48"></a>
<span id="l49"> * &lt;p&gt;This implementation provides constant-time performance for the basic</span><a href="#l49"></a>
<span id="l50"> * operations (&lt;tt&gt;get&lt;/tt&gt; and &lt;tt&gt;put&lt;/tt&gt;), assuming the hash function</span><a href="#l50"></a>
<span id="l51"> * disperses the elements properly among the buckets.  Iteration over</span><a href="#l51"></a>
<span id="l52"> * collection views requires time proportional to the &quot;capacity&quot; of the</span><a href="#l52"></a>
<span id="l53"> * &lt;tt&gt;HashMap&lt;/tt&gt; instance (the number of buckets) plus its size (the number</span><a href="#l53"></a>
<span id="l54"> * of key-value mappings).  Thus, it's very important not to set the initial</span><a href="#l54"></a>
<span id="l55"> * capacity too high (or the load factor too low) if iteration performance is</span><a href="#l55"></a>
<span id="l56"> * important.</span><a href="#l56"></a>
<span id="l57"> *</span><a href="#l57"></a>
<span id="l58"> * &lt;p&gt;An instance of &lt;tt&gt;HashMap&lt;/tt&gt; has two parameters that affect its</span><a href="#l58"></a>
<span id="l59"> * performance: &lt;i&gt;initial capacity&lt;/i&gt; and &lt;i&gt;load factor&lt;/i&gt;.  The</span><a href="#l59"></a>
<span id="l60"> * &lt;i&gt;capacity&lt;/i&gt; is the number of buckets in the hash table, and the initial</span><a href="#l60"></a>
<span id="l61"> * capacity is simply the capacity at the time the hash table is created.  The</span><a href="#l61"></a>
<span id="l62"> * &lt;i&gt;load factor&lt;/i&gt; is a measure of how full the hash table is allowed to</span><a href="#l62"></a>
<span id="l63"> * get before its capacity is automatically increased.  When the number of</span><a href="#l63"></a>
<span id="l64"> * entries in the hash table exceeds the product of the load factor and the</span><a href="#l64"></a>
<span id="l65"> * current capacity, the hash table is &lt;i&gt;rehashed&lt;/i&gt; (that is, internal data</span><a href="#l65"></a>
<span id="l66"> * structures are rebuilt) so that the hash table has approximately twice the</span><a href="#l66"></a>
<span id="l67"> * number of buckets.</span><a href="#l67"></a>
<span id="l68"> *</span><a href="#l68"></a>
<span id="l69"> * &lt;p&gt;As a general rule, the default load factor (.75) offers a good</span><a href="#l69"></a>
<span id="l70"> * tradeoff between time and space costs.  Higher values decrease the</span><a href="#l70"></a>
<span id="l71"> * space overhead but increase the lookup cost (reflected in most of</span><a href="#l71"></a>
<span id="l72"> * the operations of the &lt;tt&gt;HashMap&lt;/tt&gt; class, including</span><a href="#l72"></a>
<span id="l73"> * &lt;tt&gt;get&lt;/tt&gt; and &lt;tt&gt;put&lt;/tt&gt;).  The expected number of entries in</span><a href="#l73"></a>
<span id="l74"> * the map and its load factor should be taken into account when</span><a href="#l74"></a>
<span id="l75"> * setting its initial capacity, so as to minimize the number of</span><a href="#l75"></a>
<span id="l76"> * rehash operations.  If the initial capacity is greater than the</span><a href="#l76"></a>
<span id="l77"> * maximum number of entries divided by the load factor, no rehash</span><a href="#l77"></a>
<span id="l78"> * operations will ever occur.</span><a href="#l78"></a>
<span id="l79"> *</span><a href="#l79"></a>
<span id="l80"> * &lt;p&gt;If many mappings are to be stored in a &lt;tt&gt;HashMap&lt;/tt&gt;</span><a href="#l80"></a>
<span id="l81"> * instance, creating it with a sufficiently large capacity will allow</span><a href="#l81"></a>
<span id="l82"> * the mappings to be stored more efficiently than letting it perform</span><a href="#l82"></a>
<span id="l83"> * automatic rehashing as needed to grow the table.  Note that using</span><a href="#l83"></a>
<span id="l84"> * many keys with the same {@code hashCode()} is a sure way to slow</span><a href="#l84"></a>
<span id="l85"> * down performance of any hash table. To ameliorate impact, when keys</span><a href="#l85"></a>
<span id="l86"> * are {@link Comparable}, this class may use comparison order among</span><a href="#l86"></a>
<span id="l87"> * keys to help break ties.</span><a href="#l87"></a>
<span id="l88"> *</span><a href="#l88"></a>
<span id="l89"> * &lt;p&gt;&lt;strong&gt;Note that this implementation is not synchronized.&lt;/strong&gt;</span><a href="#l89"></a>
<span id="l90"> * If multiple threads access a hash map concurrently, and at least one of</span><a href="#l90"></a>
<span id="l91"> * the threads modifies the map structurally, it &lt;i&gt;must&lt;/i&gt; be</span><a href="#l91"></a>
<span id="l92"> * synchronized externally.  (A structural modification is any operation</span><a href="#l92"></a>
<span id="l93"> * that adds or deletes one or more mappings; merely changing the value</span><a href="#l93"></a>
<span id="l94"> * associated with a key that an instance already contains is not a</span><a href="#l94"></a>
<span id="l95"> * structural modification.)  This is typically accomplished by</span><a href="#l95"></a>
<span id="l96"> * synchronizing on some object that naturally encapsulates the map.</span><a href="#l96"></a>
<span id="l97"> *</span><a href="#l97"></a>
<span id="l98"> * If no such object exists, the map should be &quot;wrapped&quot; using the</span><a href="#l98"></a>
<span id="l99"> * {@link Collections#synchronizedMap Collections.synchronizedMap}</span><a href="#l99"></a>
<span id="l100"> * method.  This is best done at creation time, to prevent accidental</span><a href="#l100"></a>
<span id="l101"> * unsynchronized access to the map:&lt;pre&gt;</span><a href="#l101"></a>
<span id="l102"> *   Map m = Collections.synchronizedMap(new HashMap(...));&lt;/pre&gt;</span><a href="#l102"></a>
<span id="l103"> *</span><a href="#l103"></a>
<span id="l104"> * &lt;p&gt;The iterators returned by all of this class's &quot;collection view methods&quot;</span><a href="#l104"></a>
<span id="l105"> * are &lt;i&gt;fail-fast&lt;/i&gt;: if the map is structurally modified at any time after</span><a href="#l105"></a>
<span id="l106"> * the iterator is created, in any way except through the iterator's own</span><a href="#l106"></a>
<span id="l107"> * &lt;tt&gt;remove&lt;/tt&gt; method, the iterator will throw a</span><a href="#l107"></a>
<span id="l108"> * {@link ConcurrentModificationException}.  Thus, in the face of concurrent</span><a href="#l108"></a>
<span id="l109"> * modification, the iterator fails quickly and cleanly, rather than risking</span><a href="#l109"></a>
<span id="l110"> * arbitrary, non-deterministic behavior at an undetermined time in the</span><a href="#l110"></a>
<span id="l111"> * future.</span><a href="#l111"></a>
<span id="l112"> *</span><a href="#l112"></a>
<span id="l113"> * &lt;p&gt;Note that the fail-fast behavior of an iterator cannot be guaranteed</span><a href="#l113"></a>
<span id="l114"> * as it is, generally speaking, impossible to make any hard guarantees in the</span><a href="#l114"></a>
<span id="l115"> * presence of unsynchronized concurrent modification.  Fail-fast iterators</span><a href="#l115"></a>
<span id="l116"> * throw &lt;tt&gt;ConcurrentModificationException&lt;/tt&gt; on a best-effort basis.</span><a href="#l116"></a>
<span id="l117"> * Therefore, it would be wrong to write a program that depended on this</span><a href="#l117"></a>
<span id="l118"> * exception for its correctness: &lt;i&gt;the fail-fast behavior of iterators</span><a href="#l118"></a>
<span id="l119"> * should be used only to detect bugs.&lt;/i&gt;</span><a href="#l119"></a>
<span id="l120"> *</span><a href="#l120"></a>
<span id="l121"> * &lt;p&gt;This class is a member of the</span><a href="#l121"></a>
<span id="l122"> * &lt;a href=&quot;{@docRoot}/../technotes/guides/collections/index.html&quot;&gt;</span><a href="#l122"></a>
<span id="l123"> * Java Collections Framework&lt;/a&gt;.</span><a href="#l123"></a>
<span id="l124"> *</span><a href="#l124"></a>
<span id="l125"> * @param &lt;K&gt; the type of keys maintained by this map</span><a href="#l125"></a>
<span id="l126"> * @param &lt;V&gt; the type of mapped values</span><a href="#l126"></a>
<span id="l127"> *</span><a href="#l127"></a>
<span id="l128"> * @author  Doug Lea</span><a href="#l128"></a>
<span id="l129"> * @author  Josh Bloch</span><a href="#l129"></a>
<span id="l130"> * @author  Arthur van Hoff</span><a href="#l130"></a>
<span id="l131"> * @author  Neal Gafter</span><a href="#l131"></a>
<span id="l132"> * @see     Object#hashCode()</span><a href="#l132"></a>
<span id="l133"> * @see     Collection</span><a href="#l133"></a>
<span id="l134"> * @see     Map</span><a href="#l134"></a>
<span id="l135"> * @see     TreeMap</span><a href="#l135"></a>
<span id="l136"> * @see     Hashtable</span><a href="#l136"></a>
<span id="l137"> * @since   1.2</span><a href="#l137"></a>
<span id="l138"> */</span><a href="#l138"></a>
<span id="l139">public class HashMap&lt;K,V&gt; extends AbstractMap&lt;K,V&gt;</span><a href="#l139"></a>
<span id="l140">    implements Map&lt;K,V&gt;, Cloneable, Serializable {</span><a href="#l140"></a>
<span id="l141"></span><a href="#l141"></a>
<span id="l142">    private static final long serialVersionUID = 362498820763181265L;</span><a href="#l142"></a>
<span id="l143"></span><a href="#l143"></a>
<span id="l144">    /*</span><a href="#l144"></a>
<span id="l145">     * Implementation notes.</span><a href="#l145"></a>
<span id="l146">     *</span><a href="#l146"></a>
<span id="l147">     * This map usually acts as a binned (bucketed) hash table, but</span><a href="#l147"></a>
<span id="l148">     * when bins get too large, they are transformed into bins of</span><a href="#l148"></a>
<span id="l149">     * TreeNodes, each structured similarly to those in</span><a href="#l149"></a>
<span id="l150">     * java.util.TreeMap. Most methods try to use normal bins, but</span><a href="#l150"></a>
<span id="l151">     * relay to TreeNode methods when applicable (simply by checking</span><a href="#l151"></a>
<span id="l152">     * instanceof a node).  Bins of TreeNodes may be traversed and</span><a href="#l152"></a>
<span id="l153">     * used like any others, but additionally support faster lookup</span><a href="#l153"></a>
<span id="l154">     * when overpopulated. However, since the vast majority of bins in</span><a href="#l154"></a>
<span id="l155">     * normal use are not overpopulated, checking for existence of</span><a href="#l155"></a>
<span id="l156">     * tree bins may be delayed in the course of table methods.</span><a href="#l156"></a>
<span id="l157">     *</span><a href="#l157"></a>
<span id="l158">     * Tree bins (i.e., bins whose elements are all TreeNodes) are</span><a href="#l158"></a>
<span id="l159">     * ordered primarily by hashCode, but in the case of ties, if two</span><a href="#l159"></a>
<span id="l160">     * elements are of the same &quot;class C implements Comparable&lt;C&gt;&quot;,</span><a href="#l160"></a>
<span id="l161">     * type then their compareTo method is used for ordering. (We</span><a href="#l161"></a>
<span id="l162">     * conservatively check generic types via reflection to validate</span><a href="#l162"></a>
<span id="l163">     * this -- see method comparableClassFor).  The added complexity</span><a href="#l163"></a>
<span id="l164">     * of tree bins is worthwhile in providing worst-case O(log n)</span><a href="#l164"></a>
<span id="l165">     * operations when keys either have distinct hashes or are</span><a href="#l165"></a>
<span id="l166">     * orderable, Thus, performance degrades gracefully under</span><a href="#l166"></a>
<span id="l167">     * accidental or malicious usages in which hashCode() methods</span><a href="#l167"></a>
<span id="l168">     * return values that are poorly distributed, as well as those in</span><a href="#l168"></a>
<span id="l169">     * which many keys share a hashCode, so long as they are also</span><a href="#l169"></a>
<span id="l170">     * Comparable. (If neither of these apply, we may waste about a</span><a href="#l170"></a>
<span id="l171">     * factor of two in time and space compared to taking no</span><a href="#l171"></a>
<span id="l172">     * precautions. But the only known cases stem from poor user</span><a href="#l172"></a>
<span id="l173">     * programming practices that are already so slow that this makes</span><a href="#l173"></a>
<span id="l174">     * little difference.)</span><a href="#l174"></a>
<span id="l175">     *</span><a href="#l175"></a>
<span id="l176">     * Because TreeNodes are about twice the size of regular nodes, we</span><a href="#l176"></a>
<span id="l177">     * use them only when bins contain enough nodes to warrant use</span><a href="#l177"></a>
<span id="l178">     * (see TREEIFY_THRESHOLD). And when they become too small (due to</span><a href="#l178"></a>
<span id="l179">     * removal or resizing) they are converted back to plain bins.  In</span><a href="#l179"></a>
<span id="l180">     * usages with well-distributed user hashCodes, tree bins are</span><a href="#l180"></a>
<span id="l181">     * rarely used.  Ideally, under random hashCodes, the frequency of</span><a href="#l181"></a>
<span id="l182">     * nodes in bins follows a Poisson distribution</span><a href="#l182"></a>
<span id="l183">     * (http://en.wikipedia.org/wiki/Poisson_distribution) with a</span><a href="#l183"></a>
<span id="l184">     * parameter of about 0.5 on average for the default resizing</span><a href="#l184"></a>
<span id="l185">     * threshold of 0.75, although with a large variance because of</span><a href="#l185"></a>
<span id="l186">     * resizing granularity. Ignoring variance, the expected</span><a href="#l186"></a>
<span id="l187">     * occurrences of list size k are (exp(-0.5) * pow(0.5, k) /</span><a href="#l187"></a>
<span id="l188">     * factorial(k)). The first values are:</span><a href="#l188"></a>
<span id="l189">     *</span><a href="#l189"></a>
<span id="l190">     * 0:    0.60653066</span><a href="#l190"></a>
<span id="l191">     * 1:    0.30326533</span><a href="#l191"></a>
<span id="l192">     * 2:    0.07581633</span><a href="#l192"></a>
<span id="l193">     * 3:    0.01263606</span><a href="#l193"></a>
<span id="l194">     * 4:    0.00157952</span><a href="#l194"></a>
<span id="l195">     * 5:    0.00015795</span><a href="#l195"></a>
<span id="l196">     * 6:    0.00001316</span><a href="#l196"></a>
<span id="l197">     * 7:    0.00000094</span><a href="#l197"></a>
<span id="l198">     * 8:    0.00000006</span><a href="#l198"></a>
<span id="l199">     * more: less than 1 in ten million</span><a href="#l199"></a>
<span id="l200">     *</span><a href="#l200"></a>
<span id="l201">     * The root of a tree bin is normally its first node.  However,</span><a href="#l201"></a>
<span id="l202">     * sometimes (currently only upon Iterator.remove), the root might</span><a href="#l202"></a>
<span id="l203">     * be elsewhere, but can be recovered following parent links</span><a href="#l203"></a>
<span id="l204">     * (method TreeNode.root()).</span><a href="#l204"></a>
<span id="l205">     *</span><a href="#l205"></a>
<span id="l206">     * All applicable internal methods accept a hash code as an</span><a href="#l206"></a>
<span id="l207">     * argument (as normally supplied from a public method), allowing</span><a href="#l207"></a>
<span id="l208">     * them to call each other without recomputing user hashCodes.</span><a href="#l208"></a>
<span id="l209">     * Most internal methods also accept a &quot;tab&quot; argument, that is</span><a href="#l209"></a>
<span id="l210">     * normally the current table, but may be a new or old one when</span><a href="#l210"></a>
<span id="l211">     * resizing or converting.</span><a href="#l211"></a>
<span id="l212">     *</span><a href="#l212"></a>
<span id="l213">     * When bin lists are treeified, split, or untreeified, we keep</span><a href="#l213"></a>
<span id="l214">     * them in the same relative access/traversal order (i.e., field</span><a href="#l214"></a>
<span id="l215">     * Node.next) to better preserve locality, and to slightly</span><a href="#l215"></a>
<span id="l216">     * simplify handling of splits and traversals that invoke</span><a href="#l216"></a>
<span id="l217">     * iterator.remove. When using comparators on insertion, to keep a</span><a href="#l217"></a>
<span id="l218">     * total ordering (or as close as is required here) across</span><a href="#l218"></a>
<span id="l219">     * rebalancings, we compare classes and identityHashCodes as</span><a href="#l219"></a>
<span id="l220">     * tie-breakers.</span><a href="#l220"></a>
<span id="l221">     *</span><a href="#l221"></a>
<span id="l222">     * The use and transitions among plain vs tree modes is</span><a href="#l222"></a>
<span id="l223">     * complicated by the existence of subclass LinkedHashMap. See</span><a href="#l223"></a>
<span id="l224">     * below for hook methods defined to be invoked upon insertion,</span><a href="#l224"></a>
<span id="l225">     * removal and access that allow LinkedHashMap internals to</span><a href="#l225"></a>
<span id="l226">     * otherwise remain independent of these mechanics. (This also</span><a href="#l226"></a>
<span id="l227">     * requires that a map instance be passed to some utility methods</span><a href="#l227"></a>
<span id="l228">     * that may create new nodes.)</span><a href="#l228"></a>
<span id="l229">     *</span><a href="#l229"></a>
<span id="l230">     * The concurrent-programming-like SSA-based coding style helps</span><a href="#l230"></a>
<span id="l231">     * avoid aliasing errors amid all of the twisty pointer operations.</span><a href="#l231"></a>
<span id="l232">     */</span><a href="#l232"></a>
<span id="l233"></span><a href="#l233"></a>
<span id="l234">    /**</span><a href="#l234"></a>
<span id="l235">     * The default initial capacity - MUST be a power of two.</span><a href="#l235"></a>
<span id="l236">     */</span><a href="#l236"></a>
<span id="l237">    static final int DEFAULT_INITIAL_CAPACITY = 1 &lt;&lt; 4; // aka 16</span><a href="#l237"></a>
<span id="l238"></span><a href="#l238"></a>
<span id="l239">    /**</span><a href="#l239"></a>
<span id="l240">     * The maximum capacity, used if a higher value is implicitly specified</span><a href="#l240"></a>
<span id="l241">     * by either of the constructors with arguments.</span><a href="#l241"></a>
<span id="l242">     * MUST be a power of two &lt;= 1&lt;&lt;30.</span><a href="#l242"></a>
<span id="l243">     */</span><a href="#l243"></a>
<span id="l244">    static final int MAXIMUM_CAPACITY = 1 &lt;&lt; 30;</span><a href="#l244"></a>
<span id="l245"></span><a href="#l245"></a>
<span id="l246">    /**</span><a href="#l246"></a>
<span id="l247">     * The load factor used when none specified in constructor.</span><a href="#l247"></a>
<span id="l248">     */</span><a href="#l248"></a>
<span id="l249">    static final float DEFAULT_LOAD_FACTOR = 0.75f;</span><a href="#l249"></a>
<span id="l250"></span><a href="#l250"></a>
<span id="l251">    /**</span><a href="#l251"></a>
<span id="l252">     * The bin count threshold for using a tree rather than list for a</span><a href="#l252"></a>
<span id="l253">     * bin.  Bins are converted to trees when adding an element to a</span><a href="#l253"></a>
<span id="l254">     * bin with at least this many nodes. The value must be greater</span><a href="#l254"></a>
<span id="l255">     * than 2 and should be at least 8 to mesh with assumptions in</span><a href="#l255"></a>
<span id="l256">     * tree removal about conversion back to plain bins upon</span><a href="#l256"></a>
<span id="l257">     * shrinkage.</span><a href="#l257"></a>
<span id="l258">     */</span><a href="#l258"></a>
<span id="l259">    static final int TREEIFY_THRESHOLD = 8;</span><a href="#l259"></a>
<span id="l260"></span><a href="#l260"></a>
<span id="l261">    /**</span><a href="#l261"></a>
<span id="l262">     * The bin count threshold for untreeifying a (split) bin during a</span><a href="#l262"></a>
<span id="l263">     * resize operation. Should be less than TREEIFY_THRESHOLD, and at</span><a href="#l263"></a>
<span id="l264">     * most 6 to mesh with shrinkage detection under removal.</span><a href="#l264"></a>
<span id="l265">     */</span><a href="#l265"></a>
<span id="l266">    static final int UNTREEIFY_THRESHOLD = 6;</span><a href="#l266"></a>
<span id="l267"></span><a href="#l267"></a>
<span id="l268">    /**</span><a href="#l268"></a>
<span id="l269">     * The smallest table capacity for which bins may be treeified.</span><a href="#l269"></a>
<span id="l270">     * (Otherwise the table is resized if too many nodes in a bin.)</span><a href="#l270"></a>
<span id="l271">     * Should be at least 4 * TREEIFY_THRESHOLD to avoid conflicts</span><a href="#l271"></a>
<span id="l272">     * between resizing and treeification thresholds.</span><a href="#l272"></a>
<span id="l273">     */</span><a href="#l273"></a>
<span id="l274">    static final int MIN_TREEIFY_CAPACITY = 64;</span><a href="#l274"></a>
<span id="l275"></span><a href="#l275"></a>
<span id="l276">    /**</span><a href="#l276"></a>
<span id="l277">     * Basic hash bin node, used for most entries.  (See below for</span><a href="#l277"></a>
<span id="l278">     * TreeNode subclass, and in LinkedHashMap for its Entry subclass.)</span><a href="#l278"></a>
<span id="l279">     */</span><a href="#l279"></a>
<span id="l280">    static class Node&lt;K,V&gt; implements Map.Entry&lt;K,V&gt; {</span><a href="#l280"></a>
<span id="l281">        final int hash;</span><a href="#l281"></a>
<span id="l282">        final K key;</span><a href="#l282"></a>
<span id="l283">        V value;</span><a href="#l283"></a>
<span id="l284">        Node&lt;K,V&gt; next;</span><a href="#l284"></a>
<span id="l285"></span><a href="#l285"></a>
<span id="l286">        Node(int hash, K key, V value, Node&lt;K,V&gt; next) {</span><a href="#l286"></a>
<span id="l287">            this.hash = hash;</span><a href="#l287"></a>
<span id="l288">            this.key = key;</span><a href="#l288"></a>
<span id="l289">            this.value = value;</span><a href="#l289"></a>
<span id="l290">            this.next = next;</span><a href="#l290"></a>
<span id="l291">        }</span><a href="#l291"></a>
<span id="l292"></span><a href="#l292"></a>
<span id="l293">        public final K getKey()        { return key; }</span><a href="#l293"></a>
<span id="l294">        public final V getValue()      { return value; }</span><a href="#l294"></a>
<span id="l295">        public final String toString() { return key + &quot;=&quot; + value; }</span><a href="#l295"></a>
<span id="l296"></span><a href="#l296"></a>
<span id="l297">        public final int hashCode() {</span><a href="#l297"></a>
<span id="l298">            return Objects.hashCode(key) ^ Objects.hashCode(value);</span><a href="#l298"></a>
<span id="l299">        }</span><a href="#l299"></a>
<span id="l300"></span><a href="#l300"></a>
<span id="l301">        public final V setValue(V newValue) {</span><a href="#l301"></a>
<span id="l302">            V oldValue = value;</span><a href="#l302"></a>
<span id="l303">            value = newValue;</span><a href="#l303"></a>
<span id="l304">            return oldValue;</span><a href="#l304"></a>
<span id="l305">        }</span><a href="#l305"></a>
<span id="l306"></span><a href="#l306"></a>
<span id="l307">        public final boolean equals(Object o) {</span><a href="#l307"></a>
<span id="l308">            if (o == this)</span><a href="#l308"></a>
<span id="l309">                return true;</span><a href="#l309"></a>
<span id="l310">            if (o instanceof Map.Entry) {</span><a href="#l310"></a>
<span id="l311">                Map.Entry&lt;?,?&gt; e = (Map.Entry&lt;?,?&gt;)o;</span><a href="#l311"></a>
<span id="l312">                if (Objects.equals(key, e.getKey()) &amp;&amp;</span><a href="#l312"></a>
<span id="l313">                    Objects.equals(value, e.getValue()))</span><a href="#l313"></a>
<span id="l314">                    return true;</span><a href="#l314"></a>
<span id="l315">            }</span><a href="#l315"></a>
<span id="l316">            return false;</span><a href="#l316"></a>
<span id="l317">        }</span><a href="#l317"></a>
<span id="l318">    }</span><a href="#l318"></a>
<span id="l319"></span><a href="#l319"></a>
<span id="l320">    /* ---------------- Static utilities -------------- */</span><a href="#l320"></a>
<span id="l321"></span><a href="#l321"></a>
<span id="l322">    /**</span><a href="#l322"></a>
<span id="l323">     * Computes key.hashCode() and spreads (XORs) higher bits of hash</span><a href="#l323"></a>
<span id="l324">     * to lower.  Because the table uses power-of-two masking, sets of</span><a href="#l324"></a>
<span id="l325">     * hashes that vary only in bits above the current mask will</span><a href="#l325"></a>
<span id="l326">     * always collide. (Among known examples are sets of Float keys</span><a href="#l326"></a>
<span id="l327">     * holding consecutive whole numbers in small tables.)  So we</span><a href="#l327"></a>
<span id="l328">     * apply a transform that spreads the impact of higher bits</span><a href="#l328"></a>
<span id="l329">     * downward. There is a tradeoff between speed, utility, and</span><a href="#l329"></a>
<span id="l330">     * quality of bit-spreading. Because many common sets of hashes</span><a href="#l330"></a>
<span id="l331">     * are already reasonably distributed (so don't benefit from</span><a href="#l331"></a>
<span id="l332">     * spreading), and because we use trees to handle large sets of</span><a href="#l332"></a>
<span id="l333">     * collisions in bins, we just XOR some shifted bits in the</span><a href="#l333"></a>
<span id="l334">     * cheapest possible way to reduce systematic lossage, as well as</span><a href="#l334"></a>
<span id="l335">     * to incorporate impact of the highest bits that would otherwise</span><a href="#l335"></a>
<span id="l336">     * never be used in index calculations because of table bounds.</span><a href="#l336"></a>
<span id="l337">     */</span><a href="#l337"></a>
<span id="l338">    static final int hash(Object key) {</span><a href="#l338"></a>
<span id="l339">        int h;</span><a href="#l339"></a>
<span id="l340">        return (key == null) ? 0 : (h = key.hashCode()) ^ (h &gt;&gt;&gt; 16);</span><a href="#l340"></a>
<span id="l341">    }</span><a href="#l341"></a>
<span id="l342"></span><a href="#l342"></a>
<span id="l343">    /**</span><a href="#l343"></a>
<span id="l344">     * Returns x's Class if it is of the form &quot;class C implements</span><a href="#l344"></a>
<span id="l345">     * Comparable&lt;C&gt;&quot;, else null.</span><a href="#l345"></a>
<span id="l346">     */</span><a href="#l346"></a>
<span id="l347">    static Class&lt;?&gt; comparableClassFor(Object x) {</span><a href="#l347"></a>
<span id="l348">        if (x instanceof Comparable) {</span><a href="#l348"></a>
<span id="l349">            Class&lt;?&gt; c; Type[] ts, as; Type t; ParameterizedType p;</span><a href="#l349"></a>
<span id="l350">            if ((c = x.getClass()) == String.class) // bypass checks</span><a href="#l350"></a>
<span id="l351">                return c;</span><a href="#l351"></a>
<span id="l352">            if ((ts = c.getGenericInterfaces()) != null) {</span><a href="#l352"></a>
<span id="l353">                for (int i = 0; i &lt; ts.length; ++i) {</span><a href="#l353"></a>
<span id="l354">                    if (((t = ts[i]) instanceof ParameterizedType) &amp;&amp;</span><a href="#l354"></a>
<span id="l355">                        ((p = (ParameterizedType)t).getRawType() ==</span><a href="#l355"></a>
<span id="l356">                         Comparable.class) &amp;&amp;</span><a href="#l356"></a>
<span id="l357">                        (as = p.getActualTypeArguments()) != null &amp;&amp;</span><a href="#l357"></a>
<span id="l358">                        as.length == 1 &amp;&amp; as[0] == c) // type arg is c</span><a href="#l358"></a>
<span id="l359">                        return c;</span><a href="#l359"></a>
<span id="l360">                }</span><a href="#l360"></a>
<span id="l361">            }</span><a href="#l361"></a>
<span id="l362">        }</span><a href="#l362"></a>
<span id="l363">        return null;</span><a href="#l363"></a>
<span id="l364">    }</span><a href="#l364"></a>
<span id="l365"></span><a href="#l365"></a>
<span id="l366">    /**</span><a href="#l366"></a>
<span id="l367">     * Returns k.compareTo(x) if x matches kc (k's screened comparable</span><a href="#l367"></a>
<span id="l368">     * class), else 0.</span><a href="#l368"></a>
<span id="l369">     */</span><a href="#l369"></a>
<span id="l370">    @SuppressWarnings({&quot;rawtypes&quot;,&quot;unchecked&quot;}) // for cast to Comparable</span><a href="#l370"></a>
<span id="l371">    static int compareComparables(Class&lt;?&gt; kc, Object k, Object x) {</span><a href="#l371"></a>
<span id="l372">        return (x == null || x.getClass() != kc ? 0 :</span><a href="#l372"></a>
<span id="l373">                ((Comparable)k).compareTo(x));</span><a href="#l373"></a>
<span id="l374">    }</span><a href="#l374"></a>
<span id="l375"></span><a href="#l375"></a>
<span id="l376">    /**</span><a href="#l376"></a>
<span id="l377">     * Returns a power of two size for the given target capacity.</span><a href="#l377"></a>
<span id="l378">     */</span><a href="#l378"></a>
<span id="l379">    static final int tableSizeFor(int cap) {</span><a href="#l379"></a>
<span id="l380">        int n = cap - 1;</span><a href="#l380"></a>
<span id="l381">        n |= n &gt;&gt;&gt; 1;</span><a href="#l381"></a>
<span id="l382">        n |= n &gt;&gt;&gt; 2;</span><a href="#l382"></a>
<span id="l383">        n |= n &gt;&gt;&gt; 4;</span><a href="#l383"></a>
<span id="l384">        n |= n &gt;&gt;&gt; 8;</span><a href="#l384"></a>
<span id="l385">        n |= n &gt;&gt;&gt; 16;</span><a href="#l385"></a>
<span id="l386">        return (n &lt; 0) ? 1 : (n &gt;= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;</span><a href="#l386"></a>
<span id="l387">    }</span><a href="#l387"></a>
<span id="l388"></span><a href="#l388"></a>
<span id="l389">    /* ---------------- Fields -------------- */</span><a href="#l389"></a>
<span id="l390"></span><a href="#l390"></a>
<span id="l391">    /**</span><a href="#l391"></a>
<span id="l392">     * The table, initialized on first use, and resized as</span><a href="#l392"></a>
<span id="l393">     * necessary. When allocated, length is always a power of two.</span><a href="#l393"></a>
<span id="l394">     * (We also tolerate length zero in some operations to allow</span><a href="#l394"></a>
<span id="l395">     * bootstrapping mechanics that are currently not needed.)</span><a href="#l395"></a>
<span id="l396">     */</span><a href="#l396"></a>
<span id="l397">    transient Node&lt;K,V&gt;[] table;</span><a href="#l397"></a>
<span id="l398"></span><a href="#l398"></a>
<span id="l399">    /**</span><a href="#l399"></a>
<span id="l400">     * Holds cached entrySet(). Note that AbstractMap fields are used</span><a href="#l400"></a>
<span id="l401">     * for keySet() and values().</span><a href="#l401"></a>
<span id="l402">     */</span><a href="#l402"></a>
<span id="l403">    transient Set&lt;Map.Entry&lt;K,V&gt;&gt; entrySet;</span><a href="#l403"></a>
<span id="l404"></span><a href="#l404"></a>
<span id="l405">    /**</span><a href="#l405"></a>
<span id="l406">     * The number of key-value mappings contained in this map.</span><a href="#l406"></a>
<span id="l407">     */</span><a href="#l407"></a>
<span id="l408">    transient int size;</span><a href="#l408"></a>
<span id="l409"></span><a href="#l409"></a>
<span id="l410">    /**</span><a href="#l410"></a>
<span id="l411">     * The number of times this HashMap has been structurally modified</span><a href="#l411"></a>
<span id="l412">     * Structural modifications are those that change the number of mappings in</span><a href="#l412"></a>
<span id="l413">     * the HashMap or otherwise modify its internal structure (e.g.,</span><a href="#l413"></a>
<span id="l414">     * rehash).  This field is used to make iterators on Collection-views of</span><a href="#l414"></a>
<span id="l415">     * the HashMap fail-fast.  (See ConcurrentModificationException).</span><a href="#l415"></a>
<span id="l416">     */</span><a href="#l416"></a>
<span id="l417">    transient int modCount;</span><a href="#l417"></a>
<span id="l418"></span><a href="#l418"></a>
<span id="l419">    /**</span><a href="#l419"></a>
<span id="l420">     * The next size value at which to resize (capacity * load factor).</span><a href="#l420"></a>
<span id="l421">     *</span><a href="#l421"></a>
<span id="l422">     * @serial</span><a href="#l422"></a>
<span id="l423">     */</span><a href="#l423"></a>
<span id="l424">    // (The javadoc description is true upon serialization.</span><a href="#l424"></a>
<span id="l425">    // Additionally, if the table array has not been allocated, this</span><a href="#l425"></a>
<span id="l426">    // field holds the initial array capacity, or zero signifying</span><a href="#l426"></a>
<span id="l427">    // DEFAULT_INITIAL_CAPACITY.)</span><a href="#l427"></a>
<span id="l428">    int threshold;</span><a href="#l428"></a>
<span id="l429"></span><a href="#l429"></a>
<span id="l430">    /**</span><a href="#l430"></a>
<span id="l431">     * The load factor for the hash table.</span><a href="#l431"></a>
<span id="l432">     *</span><a href="#l432"></a>
<span id="l433">     * @serial</span><a href="#l433"></a>
<span id="l434">     */</span><a href="#l434"></a>
<span id="l435">    final float loadFactor;</span><a href="#l435"></a>
<span id="l436"></span><a href="#l436"></a>
<span id="l437">    /* ---------------- Public operations -------------- */</span><a href="#l437"></a>
<span id="l438"></span><a href="#l438"></a>
<span id="l439">    /**</span><a href="#l439"></a>
<span id="l440">     * Constructs an empty &lt;tt&gt;HashMap&lt;/tt&gt; with the specified initial</span><a href="#l440"></a>
<span id="l441">     * capacity and load factor.</span><a href="#l441"></a>
<span id="l442">     *</span><a href="#l442"></a>
<span id="l443">     * @param  initialCapacity the initial capacity</span><a href="#l443"></a>
<span id="l444">     * @param  loadFactor      the load factor</span><a href="#l444"></a>
<span id="l445">     * @throws IllegalArgumentException if the initial capacity is negative</span><a href="#l445"></a>
<span id="l446">     *         or the load factor is nonpositive</span><a href="#l446"></a>
<span id="l447">     */</span><a href="#l447"></a>
<span id="l448">    public HashMap(int initialCapacity, float loadFactor) {</span><a href="#l448"></a>
<span id="l449">        if (initialCapacity &lt; 0)</span><a href="#l449"></a>
<span id="l450">            throw new IllegalArgumentException(&quot;Illegal initial capacity: &quot; +</span><a href="#l450"></a>
<span id="l451">                                               initialCapacity);</span><a href="#l451"></a>
<span id="l452">        if (initialCapacity &gt; MAXIMUM_CAPACITY)</span><a href="#l452"></a>
<span id="l453">            initialCapacity = MAXIMUM_CAPACITY;</span><a href="#l453"></a>
<span id="l454">        if (loadFactor &lt;= 0 || Float.isNaN(loadFactor))</span><a href="#l454"></a>
<span id="l455">            throw new IllegalArgumentException(&quot;Illegal load factor: &quot; +</span><a href="#l455"></a>
<span id="l456">                                               loadFactor);</span><a href="#l456"></a>
<span id="l457">        this.loadFactor = loadFactor;</span><a href="#l457"></a>
<span id="l458">        this.threshold = tableSizeFor(initialCapacity);</span><a href="#l458"></a>
<span id="l459">    }</span><a href="#l459"></a>
<span id="l460"></span><a href="#l460"></a>
<span id="l461">    /**</span><a href="#l461"></a>
<span id="l462">     * Constructs an empty &lt;tt&gt;HashMap&lt;/tt&gt; with the specified initial</span><a href="#l462"></a>
<span id="l463">     * capacity and the default load factor (0.75).</span><a href="#l463"></a>
<span id="l464">     *</span><a href="#l464"></a>
<span id="l465">     * @param  initialCapacity the initial capacity.</span><a href="#l465"></a>
<span id="l466">     * @throws IllegalArgumentException if the initial capacity is negative.</span><a href="#l466"></a>
<span id="l467">     */</span><a href="#l467"></a>
<span id="l468">    public HashMap(int initialCapacity) {</span><a href="#l468"></a>
<span id="l469">        this(initialCapacity, DEFAULT_LOAD_FACTOR);</span><a href="#l469"></a>
<span id="l470">    }</span><a href="#l470"></a>
<span id="l471"></span><a href="#l471"></a>
<span id="l472">    /**</span><a href="#l472"></a>
<span id="l473">     * Constructs an empty &lt;tt&gt;HashMap&lt;/tt&gt; with the default initial capacity</span><a href="#l473"></a>
<span id="l474">     * (16) and the default load factor (0.75).</span><a href="#l474"></a>
<span id="l475">     */</span><a href="#l475"></a>
<span id="l476">    public HashMap() {</span><a href="#l476"></a>
<span id="l477">        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted</span><a href="#l477"></a>
<span id="l478">    }</span><a href="#l478"></a>
<span id="l479"></span><a href="#l479"></a>
<span id="l480">    /**</span><a href="#l480"></a>
<span id="l481">     * Constructs a new &lt;tt&gt;HashMap&lt;/tt&gt; with the same mappings as the</span><a href="#l481"></a>
<span id="l482">     * specified &lt;tt&gt;Map&lt;/tt&gt;.  The &lt;tt&gt;HashMap&lt;/tt&gt; is created with</span><a href="#l482"></a>
<span id="l483">     * default load factor (0.75) and an initial capacity sufficient to</span><a href="#l483"></a>
<span id="l484">     * hold the mappings in the specified &lt;tt&gt;Map&lt;/tt&gt;.</span><a href="#l484"></a>
<span id="l485">     *</span><a href="#l485"></a>
<span id="l486">     * @param   m the map whose mappings are to be placed in this map</span><a href="#l486"></a>
<span id="l487">     * @throws  NullPointerException if the specified map is null</span><a href="#l487"></a>
<span id="l488">     */</span><a href="#l488"></a>
<span id="l489">    public HashMap(Map&lt;? extends K, ? extends V&gt; m) {</span><a href="#l489"></a>
<span id="l490">        this.loadFactor = DEFAULT_LOAD_FACTOR;</span><a href="#l490"></a>
<span id="l491">        putMapEntries(m, false);</span><a href="#l491"></a>
<span id="l492">    }</span><a href="#l492"></a>
<span id="l493"></span><a href="#l493"></a>
<span id="l494">    /**</span><a href="#l494"></a>
<span id="l495">     * Implements Map.putAll and Map constructor.</span><a href="#l495"></a>
<span id="l496">     *</span><a href="#l496"></a>
<span id="l497">     * @param m the map</span><a href="#l497"></a>
<span id="l498">     * @param evict false when initially constructing this map, else</span><a href="#l498"></a>
<span id="l499">     * true (relayed to method afterNodeInsertion).</span><a href="#l499"></a>
<span id="l500">     */</span><a href="#l500"></a>
<span id="l501">    final void putMapEntries(Map&lt;? extends K, ? extends V&gt; m, boolean evict) {</span><a href="#l501"></a>
<span id="l502">        int s = m.size();</span><a href="#l502"></a>
<span id="l503">        if (s &gt; 0) {</span><a href="#l503"></a>
<span id="l504">            if (table == null) { // pre-size</span><a href="#l504"></a>
<span id="l505">                float ft = ((float)s / loadFactor) + 1.0F;</span><a href="#l505"></a>
<span id="l506">                int t = ((ft &lt; (float)MAXIMUM_CAPACITY) ?</span><a href="#l506"></a>
<span id="l507">                         (int)ft : MAXIMUM_CAPACITY);</span><a href="#l507"></a>
<span id="l508">                if (t &gt; threshold)</span><a href="#l508"></a>
<span id="l509">                    threshold = tableSizeFor(t);</span><a href="#l509"></a>
<span id="l510">            }</span><a href="#l510"></a>
<span id="l511">            else if (s &gt; threshold)</span><a href="#l511"></a>
<span id="l512">                resize();</span><a href="#l512"></a>
<span id="l513">            for (Map.Entry&lt;? extends K, ? extends V&gt; e : m.entrySet()) {</span><a href="#l513"></a>
<span id="l514">                K key = e.getKey();</span><a href="#l514"></a>
<span id="l515">                V value = e.getValue();</span><a href="#l515"></a>
<span id="l516">                putVal(hash(key), key, value, false, evict);</span><a href="#l516"></a>
<span id="l517">            }</span><a href="#l517"></a>
<span id="l518">        }</span><a href="#l518"></a>
<span id="l519">    }</span><a href="#l519"></a>
<span id="l520"></span><a href="#l520"></a>
<span id="l521">    /**</span><a href="#l521"></a>
<span id="l522">     * Returns the number of key-value mappings in this map.</span><a href="#l522"></a>
<span id="l523">     *</span><a href="#l523"></a>
<span id="l524">     * @return the number of key-value mappings in this map</span><a href="#l524"></a>
<span id="l525">     */</span><a href="#l525"></a>
<span id="l526">    public int size() {</span><a href="#l526"></a>
<span id="l527">        return size;</span><a href="#l527"></a>
<span id="l528">    }</span><a href="#l528"></a>
<span id="l529"></span><a href="#l529"></a>
<span id="l530">    /**</span><a href="#l530"></a>
<span id="l531">     * Returns &lt;tt&gt;true&lt;/tt&gt; if this map contains no key-value mappings.</span><a href="#l531"></a>
<span id="l532">     *</span><a href="#l532"></a>
<span id="l533">     * @return &lt;tt&gt;true&lt;/tt&gt; if this map contains no key-value mappings</span><a href="#l533"></a>
<span id="l534">     */</span><a href="#l534"></a>
<span id="l535">    public boolean isEmpty() {</span><a href="#l535"></a>
<span id="l536">        return size == 0;</span><a href="#l536"></a>
<span id="l537">    }</span><a href="#l537"></a>
<span id="l538"></span><a href="#l538"></a>
<span id="l539">    /**</span><a href="#l539"></a>
<span id="l540">     * Returns the value to which the specified key is mapped,</span><a href="#l540"></a>
<span id="l541">     * or {@code null} if this map contains no mapping for the key.</span><a href="#l541"></a>
<span id="l542">     *</span><a href="#l542"></a>
<span id="l543">     * &lt;p&gt;More formally, if this map contains a mapping from a key</span><a href="#l543"></a>
<span id="l544">     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :</span><a href="#l544"></a>
<span id="l545">     * key.equals(k))}, then this method returns {@code v}; otherwise</span><a href="#l545"></a>
<span id="l546">     * it returns {@code null}.  (There can be at most one such mapping.)</span><a href="#l546"></a>
<span id="l547">     *</span><a href="#l547"></a>
<span id="l548">     * &lt;p&gt;A return value of {@code null} does not &lt;i&gt;necessarily&lt;/i&gt;</span><a href="#l548"></a>
<span id="l549">     * indicate that the map contains no mapping for the key; it's also</span><a href="#l549"></a>
<span id="l550">     * possible that the map explicitly maps the key to {@code null}.</span><a href="#l550"></a>
<span id="l551">     * The {@link #containsKey containsKey} operation may be used to</span><a href="#l551"></a>
<span id="l552">     * distinguish these two cases.</span><a href="#l552"></a>
<span id="l553">     *</span><a href="#l553"></a>
<span id="l554">     * @see #put(Object, Object)</span><a href="#l554"></a>
<span id="l555">     */</span><a href="#l555"></a>
<span id="l556">    public V get(Object key) {</span><a href="#l556"></a>
<span id="l557">        Node&lt;K,V&gt; e;</span><a href="#l557"></a>
<span id="l558">        return (e = getNode(hash(key), key)) == null ? null : e.value;</span><a href="#l558"></a>
<span id="l559">    }</span><a href="#l559"></a>
<span id="l560"></span><a href="#l560"></a>
<span id="l561">    /**</span><a href="#l561"></a>
<span id="l562">     * Implements Map.get and related methods.</span><a href="#l562"></a>
<span id="l563">     *</span><a href="#l563"></a>
<span id="l564">     * @param hash hash for key</span><a href="#l564"></a>
<span id="l565">     * @param key the key</span><a href="#l565"></a>
<span id="l566">     * @return the node, or null if none</span><a href="#l566"></a>
<span id="l567">     */</span><a href="#l567"></a>
<span id="l568">    final Node&lt;K,V&gt; getNode(int hash, Object key) {</span><a href="#l568"></a>
<span id="l569">        Node&lt;K,V&gt;[] tab; Node&lt;K,V&gt; first, e; int n; K k;</span><a href="#l569"></a>
<span id="l570">        if ((tab = table) != null &amp;&amp; (n = tab.length) &gt; 0 &amp;&amp;</span><a href="#l570"></a>
<span id="l571">            (first = tab[(n - 1) &amp; hash]) != null) {</span><a href="#l571"></a>
<span id="l572">            if (first.hash == hash &amp;&amp; // always check first node</span><a href="#l572"></a>
<span id="l573">                ((k = first.key) == key || (key != null &amp;&amp; key.equals(k))))</span><a href="#l573"></a>
<span id="l574">                return first;</span><a href="#l574"></a>
<span id="l575">            if ((e = first.next) != null) {</span><a href="#l575"></a>
<span id="l576">                if (first instanceof TreeNode)</span><a href="#l576"></a>
<span id="l577">                    return ((TreeNode&lt;K,V&gt;)first).getTreeNode(hash, key);</span><a href="#l577"></a>
<span id="l578">                do {</span><a href="#l578"></a>
<span id="l579">                    if (e.hash == hash &amp;&amp;</span><a href="#l579"></a>
<span id="l580">                        ((k = e.key) == key || (key != null &amp;&amp; key.equals(k))))</span><a href="#l580"></a>
<span id="l581">                        return e;</span><a href="#l581"></a>
<span id="l582">                } while ((e = e.next) != null);</span><a href="#l582"></a>
<span id="l583">            }</span><a href="#l583"></a>
<span id="l584">        }</span><a href="#l584"></a>
<span id="l585">        return null;</span><a href="#l585"></a>
<span id="l586">    }</span><a href="#l586"></a>
<span id="l587"></span><a href="#l587"></a>
<span id="l588">    /**</span><a href="#l588"></a>
<span id="l589">     * Returns &lt;tt&gt;true&lt;/tt&gt; if this map contains a mapping for the</span><a href="#l589"></a>
<span id="l590">     * specified key.</span><a href="#l590"></a>
<span id="l591">     *</span><a href="#l591"></a>
<span id="l592">     * @param   key   The key whose presence in this map is to be tested</span><a href="#l592"></a>
<span id="l593">     * @return &lt;tt&gt;true&lt;/tt&gt; if this map contains a mapping for the specified</span><a href="#l593"></a>
<span id="l594">     * key.</span><a href="#l594"></a>
<span id="l595">     */</span><a href="#l595"></a>
<span id="l596">    public boolean containsKey(Object key) {</span><a href="#l596"></a>
<span id="l597">        return getNode(hash(key), key) != null;</span><a href="#l597"></a>
<span id="l598">    }</span><a href="#l598"></a>
<span id="l599"></span><a href="#l599"></a>
<span id="l600">    /**</span><a href="#l600"></a>
<span id="l601">     * Associates the specified value with the specified key in this map.</span><a href="#l601"></a>
<span id="l602">     * If the map previously contained a mapping for the key, the old</span><a href="#l602"></a>
<span id="l603">     * value is replaced.</span><a href="#l603"></a>
<span id="l604">     *</span><a href="#l604"></a>
<span id="l605">     * @param key key with which the specified value is to be associated</span><a href="#l605"></a>
<span id="l606">     * @param value value to be associated with the specified key</span><a href="#l606"></a>
<span id="l607">     * @return the previous value associated with &lt;tt&gt;key&lt;/tt&gt;, or</span><a href="#l607"></a>
<span id="l608">     *         &lt;tt&gt;null&lt;/tt&gt; if there was no mapping for &lt;tt&gt;key&lt;/tt&gt;.</span><a href="#l608"></a>
<span id="l609">     *         (A &lt;tt&gt;null&lt;/tt&gt; return can also indicate that the map</span><a href="#l609"></a>
<span id="l610">     *         previously associated &lt;tt&gt;null&lt;/tt&gt; with &lt;tt&gt;key&lt;/tt&gt;.)</span><a href="#l610"></a>
<span id="l611">     */</span><a href="#l611"></a>
<span id="l612">    public V put(K key, V value) {</span><a href="#l612"></a>
<span id="l613">        return putVal(hash(key), key, value, false, true);</span><a href="#l613"></a>
<span id="l614">    }</span><a href="#l614"></a>
<span id="l615"></span><a href="#l615"></a>
<span id="l616">    /**</span><a href="#l616"></a>
<span id="l617">     * Implements Map.put and related methods.</span><a href="#l617"></a>
<span id="l618">     *</span><a href="#l618"></a>
<span id="l619">     * @param hash hash for key</span><a href="#l619"></a>
<span id="l620">     * @param key the key</span><a href="#l620"></a>
<span id="l621">     * @param value the value to put</span><a href="#l621"></a>
<span id="l622">     * @param onlyIfAbsent if true, don't change existing value</span><a href="#l622"></a>
<span id="l623">     * @param evict if false, the table is in creation mode.</span><a href="#l623"></a>
<span id="l624">     * @return previous value, or null if none</span><a href="#l624"></a>
<span id="l625">     */</span><a href="#l625"></a>
<span id="l626">    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,</span><a href="#l626"></a>
<span id="l627">                   boolean evict) {</span><a href="#l627"></a>
<span id="l628">        Node&lt;K,V&gt;[] tab; Node&lt;K,V&gt; p; int n, i;</span><a href="#l628"></a>
<span id="l629">        if ((tab = table) == null || (n = tab.length) == 0)</span><a href="#l629"></a>
<span id="l630">            n = (tab = resize()).length;</span><a href="#l630"></a>
<span id="l631">        if ((p = tab[i = (n - 1) &amp; hash]) == null)</span><a href="#l631"></a>
<span id="l632">            tab[i] = newNode(hash, key, value, null);</span><a href="#l632"></a>
<span id="l633">        else {</span><a href="#l633"></a>
<span id="l634">            Node&lt;K,V&gt; e; K k;</span><a href="#l634"></a>
<span id="l635">            if (p.hash == hash &amp;&amp;</span><a href="#l635"></a>
<span id="l636">                ((k = p.key) == key || (key != null &amp;&amp; key.equals(k))))</span><a href="#l636"></a>
<span id="l637">                e = p;</span><a href="#l637"></a>
<span id="l638">            else if (p instanceof TreeNode)</span><a href="#l638"></a>
<span id="l639">                e = ((TreeNode&lt;K,V&gt;)p).putTreeVal(this, tab, hash, key, value);</span><a href="#l639"></a>
<span id="l640">            else {</span><a href="#l640"></a>
<span id="l641">                for (int binCount = 0; ; ++binCount) {</span><a href="#l641"></a>
<span id="l642">                    if ((e = p.next) == null) {</span><a href="#l642"></a>
<span id="l643">                        p.next = newNode(hash, key, value, null);</span><a href="#l643"></a>
<span id="l644">                        if (binCount &gt;= TREEIFY_THRESHOLD - 1) // -1 for 1st</span><a href="#l644"></a>
<span id="l645">                            treeifyBin(tab, hash);</span><a href="#l645"></a>
<span id="l646">                        break;</span><a href="#l646"></a>
<span id="l647">                    }</span><a href="#l647"></a>
<span id="l648">                    if (e.hash == hash &amp;&amp;</span><a href="#l648"></a>
<span id="l649">                        ((k = e.key) == key || (key != null &amp;&amp; key.equals(k))))</span><a href="#l649"></a>
<span id="l650">                        break;</span><a href="#l650"></a>
<span id="l651">                    p = e;</span><a href="#l651"></a>
<span id="l652">                }</span><a href="#l652"></a>
<span id="l653">            }</span><a href="#l653"></a>
<span id="l654">            if (e != null) { // existing mapping for key</span><a href="#l654"></a>
<span id="l655">                V oldValue = e.value;</span><a href="#l655"></a>
<span id="l656">                if (!onlyIfAbsent || oldValue == null)</span><a href="#l656"></a>
<span id="l657">                    e.value = value;</span><a href="#l657"></a>
<span id="l658">                afterNodeAccess(e);</span><a href="#l658"></a>
<span id="l659">                return oldValue;</span><a href="#l659"></a>
<span id="l660">            }</span><a href="#l660"></a>
<span id="l661">        }</span><a href="#l661"></a>
<span id="l662">        ++modCount;</span><a href="#l662"></a>
<span id="l663">        if (++size &gt; threshold)</span><a href="#l663"></a>
<span id="l664">            resize();</span><a href="#l664"></a>
<span id="l665">        afterNodeInsertion(evict);</span><a href="#l665"></a>
<span id="l666">        return null;</span><a href="#l666"></a>
<span id="l667">    }</span><a href="#l667"></a>
<span id="l668"></span><a href="#l668"></a>
<span id="l669">    /**</span><a href="#l669"></a>
<span id="l670">     * Initializes or doubles table size.  If null, allocates in</span><a href="#l670"></a>
<span id="l671">     * accord with initial capacity target held in field threshold.</span><a href="#l671"></a>
<span id="l672">     * Otherwise, because we are using power-of-two expansion, the</span><a href="#l672"></a>
<span id="l673">     * elements from each bin must either stay at same index, or move</span><a href="#l673"></a>
<span id="l674">     * with a power of two offset in the new table.</span><a href="#l674"></a>
<span id="l675">     *</span><a href="#l675"></a>
<span id="l676">     * @return the table</span><a href="#l676"></a>
<span id="l677">     */</span><a href="#l677"></a>
<span id="l678">    final Node&lt;K,V&gt;[] resize() {</span><a href="#l678"></a>
<span id="l679">        Node&lt;K,V&gt;[] oldTab = table;</span><a href="#l679"></a>
<span id="l680">        int oldCap = (oldTab == null) ? 0 : oldTab.length;</span><a href="#l680"></a>
<span id="l681">        int oldThr = threshold;</span><a href="#l681"></a>
<span id="l682">        int newCap, newThr = 0;</span><a href="#l682"></a>
<span id="l683">        if (oldCap &gt; 0) {</span><a href="#l683"></a>
<span id="l684">            if (oldCap &gt;= MAXIMUM_CAPACITY) {</span><a href="#l684"></a>
<span id="l685">                threshold = Integer.MAX_VALUE;</span><a href="#l685"></a>
<span id="l686">                return oldTab;</span><a href="#l686"></a>
<span id="l687">            }</span><a href="#l687"></a>
<span id="l688">            else if ((newCap = oldCap &lt;&lt; 1) &lt; MAXIMUM_CAPACITY &amp;&amp;</span><a href="#l688"></a>
<span id="l689">                     oldCap &gt;= DEFAULT_INITIAL_CAPACITY)</span><a href="#l689"></a>
<span id="l690">                newThr = oldThr &lt;&lt; 1; // double threshold</span><a href="#l690"></a>
<span id="l691">        }</span><a href="#l691"></a>
<span id="l692">        else if (oldThr &gt; 0) // initial capacity was placed in threshold</span><a href="#l692"></a>
<span id="l693">            newCap = oldThr;</span><a href="#l693"></a>
<span id="l694">        else {               // zero initial threshold signifies using defaults</span><a href="#l694"></a>
<span id="l695">            newCap = DEFAULT_INITIAL_CAPACITY;</span><a href="#l695"></a>
<span id="l696">            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);</span><a href="#l696"></a>
<span id="l697">        }</span><a href="#l697"></a>
<span id="l698">        if (newThr == 0) {</span><a href="#l698"></a>
<span id="l699">            float ft = (float)newCap * loadFactor;</span><a href="#l699"></a>
<span id="l700">            newThr = (newCap &lt; MAXIMUM_CAPACITY &amp;&amp; ft &lt; (float)MAXIMUM_CAPACITY ?</span><a href="#l700"></a>
<span id="l701">                      (int)ft : Integer.MAX_VALUE);</span><a href="#l701"></a>
<span id="l702">        }</span><a href="#l702"></a>
<span id="l703">        threshold = newThr;</span><a href="#l703"></a>
<span id="l704">        @SuppressWarnings({&quot;rawtypes&quot;,&quot;unchecked&quot;})</span><a href="#l704"></a>
<span id="l705">        Node&lt;K,V&gt;[] newTab = (Node&lt;K,V&gt;[])new Node[newCap];</span><a href="#l705"></a>
<span id="l706">        table = newTab;</span><a href="#l706"></a>
<span id="l707">        if (oldTab != null) {</span><a href="#l707"></a>
<span id="l708">            for (int j = 0; j &lt; oldCap; ++j) {</span><a href="#l708"></a>
<span id="l709">                Node&lt;K,V&gt; e;</span><a href="#l709"></a>
<span id="l710">                if ((e = oldTab[j]) != null) {</span><a href="#l710"></a>
<span id="l711">                    oldTab[j] = null;</span><a href="#l711"></a>
<span id="l712">                    if (e.next == null)</span><a href="#l712"></a>
<span id="l713">                        newTab[e.hash &amp; (newCap - 1)] = e;</span><a href="#l713"></a>
<span id="l714">                    else if (e instanceof TreeNode)</span><a href="#l714"></a>
<span id="l715">                        ((TreeNode&lt;K,V&gt;)e).split(this, newTab, j, oldCap);</span><a href="#l715"></a>
<span id="l716">                    else { // preserve order</span><a href="#l716"></a>
<span id="l717">                        Node&lt;K,V&gt; loHead = null, loTail = null;</span><a href="#l717"></a>
<span id="l718">                        Node&lt;K,V&gt; hiHead = null, hiTail = null;</span><a href="#l718"></a>
<span id="l719">                        Node&lt;K,V&gt; next;</span><a href="#l719"></a>
<span id="l720">                        do {</span><a href="#l720"></a>
<span id="l721">                            next = e.next;</span><a href="#l721"></a>
<span id="l722">                            if ((e.hash &amp; oldCap) == 0) {</span><a href="#l722"></a>
<span id="l723">                                if (loTail == null)</span><a href="#l723"></a>
<span id="l724">                                    loHead = e;</span><a href="#l724"></a>
<span id="l725">                                else</span><a href="#l725"></a>
<span id="l726">                                    loTail.next = e;</span><a href="#l726"></a>
<span id="l727">                                loTail = e;</span><a href="#l727"></a>
<span id="l728">                            }</span><a href="#l728"></a>
<span id="l729">                            else {</span><a href="#l729"></a>
<span id="l730">                                if (hiTail == null)</span><a href="#l730"></a>
<span id="l731">                                    hiHead = e;</span><a href="#l731"></a>
<span id="l732">                                else</span><a href="#l732"></a>
<span id="l733">                                    hiTail.next = e;</span><a href="#l733"></a>
<span id="l734">                                hiTail = e;</span><a href="#l734"></a>
<span id="l735">                            }</span><a href="#l735"></a>
<span id="l736">                        } while ((e = next) != null);</span><a href="#l736"></a>
<span id="l737">                        if (loTail != null) {</span><a href="#l737"></a>
<span id="l738">                            loTail.next = null;</span><a href="#l738"></a>
<span id="l739">                            newTab[j] = loHead;</span><a href="#l739"></a>
<span id="l740">                        }</span><a href="#l740"></a>
<span id="l741">                        if (hiTail != null) {</span><a href="#l741"></a>
<span id="l742">                            hiTail.next = null;</span><a href="#l742"></a>
<span id="l743">                            newTab[j + oldCap] = hiHead;</span><a href="#l743"></a>
<span id="l744">                        }</span><a href="#l744"></a>
<span id="l745">                    }</span><a href="#l745"></a>
<span id="l746">                }</span><a href="#l746"></a>
<span id="l747">            }</span><a href="#l747"></a>
<span id="l748">        }</span><a href="#l748"></a>
<span id="l749">        return newTab;</span><a href="#l749"></a>
<span id="l750">    }</span><a href="#l750"></a>
<span id="l751"></span><a href="#l751"></a>
<span id="l752">    /**</span><a href="#l752"></a>
<span id="l753">     * Replaces all linked nodes in bin at index for given hash unless</span><a href="#l753"></a>
<span id="l754">     * table is too small, in which case resizes instead.</span><a href="#l754"></a>
<span id="l755">     */</span><a href="#l755"></a>
<span id="l756">    final void treeifyBin(Node&lt;K,V&gt;[] tab, int hash) {</span><a href="#l756"></a>
<span id="l757">        int n, index; Node&lt;K,V&gt; e;</span><a href="#l757"></a>
<span id="l758">        if (tab == null || (n = tab.length) &lt; MIN_TREEIFY_CAPACITY)</span><a href="#l758"></a>
<span id="l759">            resize();</span><a href="#l759"></a>
<span id="l760">        else if ((e = tab[index = (n - 1) &amp; hash]) != null) {</span><a href="#l760"></a>
<span id="l761">            TreeNode&lt;K,V&gt; hd = null, tl = null;</span><a href="#l761"></a>
<span id="l762">            do {</span><a href="#l762"></a>
<span id="l763">                TreeNode&lt;K,V&gt; p = replacementTreeNode(e, null);</span><a href="#l763"></a>
<span id="l764">                if (tl == null)</span><a href="#l764"></a>
<span id="l765">                    hd = p;</span><a href="#l765"></a>
<span id="l766">                else {</span><a href="#l766"></a>
<span id="l767">                    p.prev = tl;</span><a href="#l767"></a>
<span id="l768">                    tl.next = p;</span><a href="#l768"></a>
<span id="l769">                }</span><a href="#l769"></a>
<span id="l770">                tl = p;</span><a href="#l770"></a>
<span id="l771">            } while ((e = e.next) != null);</span><a href="#l771"></a>
<span id="l772">            if ((tab[index] = hd) != null)</span><a href="#l772"></a>
<span id="l773">                hd.treeify(tab);</span><a href="#l773"></a>
<span id="l774">        }</span><a href="#l774"></a>
<span id="l775">    }</span><a href="#l775"></a>
<span id="l776"></span><a href="#l776"></a>
<span id="l777">    /**</span><a href="#l777"></a>
<span id="l778">     * Copies all of the mappings from the specified map to this map.</span><a href="#l778"></a>
<span id="l779">     * These mappings will replace any mappings that this map had for</span><a href="#l779"></a>
<span id="l780">     * any of the keys currently in the specified map.</span><a href="#l780"></a>
<span id="l781">     *</span><a href="#l781"></a>
<span id="l782">     * @param m mappings to be stored in this map</span><a href="#l782"></a>
<span id="l783">     * @throws NullPointerException if the specified map is null</span><a href="#l783"></a>
<span id="l784">     */</span><a href="#l784"></a>
<span id="l785">    public void putAll(Map&lt;? extends K, ? extends V&gt; m) {</span><a href="#l785"></a>
<span id="l786">        putMapEntries(m, true);</span><a href="#l786"></a>
<span id="l787">    }</span><a href="#l787"></a>
<span id="l788"></span><a href="#l788"></a>
<span id="l789">    /**</span><a href="#l789"></a>
<span id="l790">     * Removes the mapping for the specified key from this map if present.</span><a href="#l790"></a>
<span id="l791">     *</span><a href="#l791"></a>
<span id="l792">     * @param  key key whose mapping is to be removed from the map</span><a href="#l792"></a>
<span id="l793">     * @return the previous value associated with &lt;tt&gt;key&lt;/tt&gt;, or</span><a href="#l793"></a>
<span id="l794">     *         &lt;tt&gt;null&lt;/tt&gt; if there was no mapping for &lt;tt&gt;key&lt;/tt&gt;.</span><a href="#l794"></a>
<span id="l795">     *         (A &lt;tt&gt;null&lt;/tt&gt; return can also indicate that the map</span><a href="#l795"></a>
<span id="l796">     *         previously associated &lt;tt&gt;null&lt;/tt&gt; with &lt;tt&gt;key&lt;/tt&gt;.)</span><a href="#l796"></a>
<span id="l797">     */</span><a href="#l797"></a>
<span id="l798">    public V remove(Object key) {</span><a href="#l798"></a>
<span id="l799">        Node&lt;K,V&gt; e;</span><a href="#l799"></a>
<span id="l800">        return (e = removeNode(hash(key), key, null, false, true)) == null ?</span><a href="#l800"></a>
<span id="l801">            null : e.value;</span><a href="#l801"></a>
<span id="l802">    }</span><a href="#l802"></a>
<span id="l803"></span><a href="#l803"></a>
<span id="l804">    /**</span><a href="#l804"></a>
<span id="l805">     * Implements Map.remove and related methods.</span><a href="#l805"></a>
<span id="l806">     *</span><a href="#l806"></a>
<span id="l807">     * @param hash hash for key</span><a href="#l807"></a>
<span id="l808">     * @param key the key</span><a href="#l808"></a>
<span id="l809">     * @param value the value to match if matchValue, else ignored</span><a href="#l809"></a>
<span id="l810">     * @param matchValue if true only remove if value is equal</span><a href="#l810"></a>
<span id="l811">     * @param movable if false do not move other nodes while removing</span><a href="#l811"></a>
<span id="l812">     * @return the node, or null if none</span><a href="#l812"></a>
<span id="l813">     */</span><a href="#l813"></a>
<span id="l814">    final Node&lt;K,V&gt; removeNode(int hash, Object key, Object value,</span><a href="#l814"></a>
<span id="l815">                               boolean matchValue, boolean movable) {</span><a href="#l815"></a>
<span id="l816">        Node&lt;K,V&gt;[] tab; Node&lt;K,V&gt; p; int n, index;</span><a href="#l816"></a>
<span id="l817">        if ((tab = table) != null &amp;&amp; (n = tab.length) &gt; 0 &amp;&amp;</span><a href="#l817"></a>
<span id="l818">            (p = tab[index = (n - 1) &amp; hash]) != null) {</span><a href="#l818"></a>
<span id="l819">            Node&lt;K,V&gt; node = null, e; K k; V v;</span><a href="#l819"></a>
<span id="l820">            if (p.hash == hash &amp;&amp;</span><a href="#l820"></a>
<span id="l821">                ((k = p.key) == key || (key != null &amp;&amp; key.equals(k))))</span><a href="#l821"></a>
<span id="l822">                node = p;</span><a href="#l822"></a>
<span id="l823">            else if ((e = p.next) != null) {</span><a href="#l823"></a>
<span id="l824">                if (p instanceof TreeNode)</span><a href="#l824"></a>
<span id="l825">                    node = ((TreeNode&lt;K,V&gt;)p).getTreeNode(hash, key);</span><a href="#l825"></a>
<span id="l826">                else {</span><a href="#l826"></a>
<span id="l827">                    do {</span><a href="#l827"></a>
<span id="l828">                        if (e.hash == hash &amp;&amp;</span><a href="#l828"></a>
<span id="l829">                            ((k = e.key) == key ||</span><a href="#l829"></a>
<span id="l830">                             (key != null &amp;&amp; key.equals(k)))) {</span><a href="#l830"></a>
<span id="l831">                            node = e;</span><a href="#l831"></a>
<span id="l832">                            break;</span><a href="#l832"></a>
<span id="l833">                        }</span><a href="#l833"></a>
<span id="l834">                        p = e;</span><a href="#l834"></a>
<span id="l835">                    } while ((e = e.next) != null);</span><a href="#l835"></a>
<span id="l836">                }</span><a href="#l836"></a>
<span id="l837">            }</span><a href="#l837"></a>
<span id="l838">            if (node != null &amp;&amp; (!matchValue || (v = node.value) == value ||</span><a href="#l838"></a>
<span id="l839">                                 (value != null &amp;&amp; value.equals(v)))) {</span><a href="#l839"></a>
<span id="l840">                if (node instanceof TreeNode)</span><a href="#l840"></a>
<span id="l841">                    ((TreeNode&lt;K,V&gt;)node).removeTreeNode(this, tab, movable);</span><a href="#l841"></a>
<span id="l842">                else if (node == p)</span><a href="#l842"></a>
<span id="l843">                    tab[index] = node.next;</span><a href="#l843"></a>
<span id="l844">                else</span><a href="#l844"></a>
<span id="l845">                    p.next = node.next;</span><a href="#l845"></a>
<span id="l846">                ++modCount;</span><a href="#l846"></a>
<span id="l847">                --size;</span><a href="#l847"></a>
<span id="l848">                afterNodeRemoval(node);</span><a href="#l848"></a>
<span id="l849">                return node;</span><a href="#l849"></a>
<span id="l850">            }</span><a href="#l850"></a>
<span id="l851">        }</span><a href="#l851"></a>
<span id="l852">        return null;</span><a href="#l852"></a>
<span id="l853">    }</span><a href="#l853"></a>
<span id="l854"></span><a href="#l854"></a>
<span id="l855">    /**</span><a href="#l855"></a>
<span id="l856">     * Removes all of the mappings from this map.</span><a href="#l856"></a>
<span id="l857">     * The map will be empty after this call returns.</span><a href="#l857"></a>
<span id="l858">     */</span><a href="#l858"></a>
<span id="l859">    public void clear() {</span><a href="#l859"></a>
<span id="l860">        Node&lt;K,V&gt;[] tab;</span><a href="#l860"></a>
<span id="l861">        modCount++;</span><a href="#l861"></a>
<span id="l862">        if ((tab = table) != null &amp;&amp; size &gt; 0) {</span><a href="#l862"></a>
<span id="l863">            size = 0;</span><a href="#l863"></a>
<span id="l864">            for (int i = 0; i &lt; tab.length; ++i)</span><a href="#l864"></a>
<span id="l865">                tab[i] = null;</span><a href="#l865"></a>
<span id="l866">        }</span><a href="#l866"></a>
<span id="l867">    }</span><a href="#l867"></a>
<span id="l868"></span><a href="#l868"></a>
<span id="l869">    /**</span><a href="#l869"></a>
<span id="l870">     * Returns &lt;tt&gt;true&lt;/tt&gt; if this map maps one or more keys to the</span><a href="#l870"></a>
<span id="l871">     * specified value.</span><a href="#l871"></a>
<span id="l872">     *</span><a href="#l872"></a>
<span id="l873">     * @param value value whose presence in this map is to be tested</span><a href="#l873"></a>
<span id="l874">     * @return &lt;tt&gt;true&lt;/tt&gt; if this map maps one or more keys to the</span><a href="#l874"></a>
<span id="l875">     *         specified value</span><a href="#l875"></a>
<span id="l876">     */</span><a href="#l876"></a>
<span id="l877">    public boolean containsValue(Object value) {</span><a href="#l877"></a>
<span id="l878">        Node&lt;K,V&gt;[] tab; V v;</span><a href="#l878"></a>
<span id="l879">        if ((tab = table) != null &amp;&amp; size &gt; 0) {</span><a href="#l879"></a>
<span id="l880">            for (int i = 0; i &lt; tab.length; ++i) {</span><a href="#l880"></a>
<span id="l881">                for (Node&lt;K,V&gt; e = tab[i]; e != null; e = e.next) {</span><a href="#l881"></a>
<span id="l882">                    if ((v = e.value) == value ||</span><a href="#l882"></a>
<span id="l883">                        (value != null &amp;&amp; value.equals(v)))</span><a href="#l883"></a>
<span id="l884">                        return true;</span><a href="#l884"></a>
<span id="l885">                }</span><a href="#l885"></a>
<span id="l886">            }</span><a href="#l886"></a>
<span id="l887">        }</span><a href="#l887"></a>
<span id="l888">        return false;</span><a href="#l888"></a>
<span id="l889">    }</span><a href="#l889"></a>
<span id="l890"></span><a href="#l890"></a>
<span id="l891">    /**</span><a href="#l891"></a>
<span id="l892">     * Returns a {@link Set} view of the keys contained in this map.</span><a href="#l892"></a>
<span id="l893">     * The set is backed by the map, so changes to the map are</span><a href="#l893"></a>
<span id="l894">     * reflected in the set, and vice-versa.  If the map is modified</span><a href="#l894"></a>
<span id="l895">     * while an iteration over the set is in progress (except through</span><a href="#l895"></a>
<span id="l896">     * the iterator's own &lt;tt&gt;remove&lt;/tt&gt; operation), the results of</span><a href="#l896"></a>
<span id="l897">     * the iteration are undefined.  The set supports element removal,</span><a href="#l897"></a>
<span id="l898">     * which removes the corresponding mapping from the map, via the</span><a href="#l898"></a>
<span id="l899">     * &lt;tt&gt;Iterator.remove&lt;/tt&gt;, &lt;tt&gt;Set.remove&lt;/tt&gt;,</span><a href="#l899"></a>
<span id="l900">     * &lt;tt&gt;removeAll&lt;/tt&gt;, &lt;tt&gt;retainAll&lt;/tt&gt;, and &lt;tt&gt;clear&lt;/tt&gt;</span><a href="#l900"></a>
<span id="l901">     * operations.  It does not support the &lt;tt&gt;add&lt;/tt&gt; or &lt;tt&gt;addAll&lt;/tt&gt;</span><a href="#l901"></a>
<span id="l902">     * operations.</span><a href="#l902"></a>
<span id="l903">     *</span><a href="#l903"></a>
<span id="l904">     * @return a set view of the keys contained in this map</span><a href="#l904"></a>
<span id="l905">     */</span><a href="#l905"></a>
<span id="l906">    public Set&lt;K&gt; keySet() {</span><a href="#l906"></a>
<span id="l907">        Set&lt;K&gt; ks = keySet;</span><a href="#l907"></a>
<span id="l908">        if (ks == null) {</span><a href="#l908"></a>
<span id="l909">            ks = new KeySet();</span><a href="#l909"></a>
<span id="l910">            keySet = ks;</span><a href="#l910"></a>
<span id="l911">        }</span><a href="#l911"></a>
<span id="l912">        return ks;</span><a href="#l912"></a>
<span id="l913">    }</span><a href="#l913"></a>
<span id="l914"></span><a href="#l914"></a>
<span id="l915">    final class KeySet extends AbstractSet&lt;K&gt; {</span><a href="#l915"></a>
<span id="l916">        public final int size()                 { return size; }</span><a href="#l916"></a>
<span id="l917">        public final void clear()               { HashMap.this.clear(); }</span><a href="#l917"></a>
<span id="l918">        public final Iterator&lt;K&gt; iterator()     { return new KeyIterator(); }</span><a href="#l918"></a>
<span id="l919">        public final boolean contains(Object o) { return containsKey(o); }</span><a href="#l919"></a>
<span id="l920">        public final boolean remove(Object key) {</span><a href="#l920"></a>
<span id="l921">            return removeNode(hash(key), key, null, false, true) != null;</span><a href="#l921"></a>
<span id="l922">        }</span><a href="#l922"></a>
<span id="l923">        public final Spliterator&lt;K&gt; spliterator() {</span><a href="#l923"></a>
<span id="l924">            return new KeySpliterator&lt;&gt;(HashMap.this, 0, -1, 0, 0);</span><a href="#l924"></a>
<span id="l925">        }</span><a href="#l925"></a>
<span id="l926">        public final void forEach(Consumer&lt;? super K&gt; action) {</span><a href="#l926"></a>
<span id="l927">            Node&lt;K,V&gt;[] tab;</span><a href="#l927"></a>
<span id="l928">            if (action == null)</span><a href="#l928"></a>
<span id="l929">                throw new NullPointerException();</span><a href="#l929"></a>
<span id="l930">            if (size &gt; 0 &amp;&amp; (tab = table) != null) {</span><a href="#l930"></a>
<span id="l931">                int mc = modCount;</span><a href="#l931"></a>
<span id="l932">                for (int i = 0; i &lt; tab.length; ++i) {</span><a href="#l932"></a>
<span id="l933">                    for (Node&lt;K,V&gt; e = tab[i]; e != null; e = e.next)</span><a href="#l933"></a>
<span id="l934">                        action.accept(e.key);</span><a href="#l934"></a>
<span id="l935">                }</span><a href="#l935"></a>
<span id="l936">                if (modCount != mc)</span><a href="#l936"></a>
<span id="l937">                    throw new ConcurrentModificationException();</span><a href="#l937"></a>
<span id="l938">            }</span><a href="#l938"></a>
<span id="l939">        }</span><a href="#l939"></a>
<span id="l940">    }</span><a href="#l940"></a>
<span id="l941"></span><a href="#l941"></a>
<span id="l942">    /**</span><a href="#l942"></a>
<span id="l943">     * Returns a {@link Collection} view of the values contained in this map.</span><a href="#l943"></a>
<span id="l944">     * The collection is backed by the map, so changes to the map are</span><a href="#l944"></a>
<span id="l945">     * reflected in the collection, and vice-versa.  If the map is</span><a href="#l945"></a>
<span id="l946">     * modified while an iteration over the collection is in progress</span><a href="#l946"></a>
<span id="l947">     * (except through the iterator's own &lt;tt&gt;remove&lt;/tt&gt; operation),</span><a href="#l947"></a>
<span id="l948">     * the results of the iteration are undefined.  The collection</span><a href="#l948"></a>
<span id="l949">     * supports element removal, which removes the corresponding</span><a href="#l949"></a>
<span id="l950">     * mapping from the map, via the &lt;tt&gt;Iterator.remove&lt;/tt&gt;,</span><a href="#l950"></a>
<span id="l951">     * &lt;tt&gt;Collection.remove&lt;/tt&gt;, &lt;tt&gt;removeAll&lt;/tt&gt;,</span><a href="#l951"></a>
<span id="l952">     * &lt;tt&gt;retainAll&lt;/tt&gt; and &lt;tt&gt;clear&lt;/tt&gt; operations.  It does not</span><a href="#l952"></a>
<span id="l953">     * support the &lt;tt&gt;add&lt;/tt&gt; or &lt;tt&gt;addAll&lt;/tt&gt; operations.</span><a href="#l953"></a>
<span id="l954">     *</span><a href="#l954"></a>
<span id="l955">     * @return a view of the values contained in this map</span><a href="#l955"></a>
<span id="l956">     */</span><a href="#l956"></a>
<span id="l957">    public Collection&lt;V&gt; values() {</span><a href="#l957"></a>
<span id="l958">        Collection&lt;V&gt; vs = values;</span><a href="#l958"></a>
<span id="l959">        if (vs == null) {</span><a href="#l959"></a>
<span id="l960">            vs = new Values();</span><a href="#l960"></a>
<span id="l961">            values = vs;</span><a href="#l961"></a>
<span id="l962">        }</span><a href="#l962"></a>
<span id="l963">        return vs;</span><a href="#l963"></a>
<span id="l964">    }</span><a href="#l964"></a>
<span id="l965"></span><a href="#l965"></a>
<span id="l966">    final class Values extends AbstractCollection&lt;V&gt; {</span><a href="#l966"></a>
<span id="l967">        public final int size()                 { return size; }</span><a href="#l967"></a>
<span id="l968">        public final void clear()               { HashMap.this.clear(); }</span><a href="#l968"></a>
<span id="l969">        public final Iterator&lt;V&gt; iterator()     { return new ValueIterator(); }</span><a href="#l969"></a>
<span id="l970">        public final boolean contains(Object o) { return containsValue(o); }</span><a href="#l970"></a>
<span id="l971">        public final Spliterator&lt;V&gt; spliterator() {</span><a href="#l971"></a>
<span id="l972">            return new ValueSpliterator&lt;&gt;(HashMap.this, 0, -1, 0, 0);</span><a href="#l972"></a>
<span id="l973">        }</span><a href="#l973"></a>
<span id="l974">        public final void forEach(Consumer&lt;? super V&gt; action) {</span><a href="#l974"></a>
<span id="l975">            Node&lt;K,V&gt;[] tab;</span><a href="#l975"></a>
<span id="l976">            if (action == null)</span><a href="#l976"></a>
<span id="l977">                throw new NullPointerException();</span><a href="#l977"></a>
<span id="l978">            if (size &gt; 0 &amp;&amp; (tab = table) != null) {</span><a href="#l978"></a>
<span id="l979">                int mc = modCount;</span><a href="#l979"></a>
<span id="l980">                for (int i = 0; i &lt; tab.length; ++i) {</span><a href="#l980"></a>
<span id="l981">                    for (Node&lt;K,V&gt; e = tab[i]; e != null; e = e.next)</span><a href="#l981"></a>
<span id="l982">                        action.accept(e.value);</span><a href="#l982"></a>
<span id="l983">                }</span><a href="#l983"></a>
<span id="l984">                if (modCount != mc)</span><a href="#l984"></a>
<span id="l985">                    throw new ConcurrentModificationException();</span><a href="#l985"></a>
<span id="l986">            }</span><a href="#l986"></a>
<span id="l987">        }</span><a href="#l987"></a>
<span id="l988">    }</span><a href="#l988"></a>
<span id="l989"></span><a href="#l989"></a>
<span id="l990">    /**</span><a href="#l990"></a>
<span id="l991">     * Returns a {@link Set} view of the mappings contained in this map.</span><a href="#l991"></a>
<span id="l992">     * The set is backed by the map, so changes to the map are</span><a href="#l992"></a>
<span id="l993">     * reflected in the set, and vice-versa.  If the map is modified</span><a href="#l993"></a>
<span id="l994">     * while an iteration over the set is in progress (except through</span><a href="#l994"></a>
<span id="l995">     * the iterator's own &lt;tt&gt;remove&lt;/tt&gt; operation, or through the</span><a href="#l995"></a>
<span id="l996">     * &lt;tt&gt;setValue&lt;/tt&gt; operation on a map entry returned by the</span><a href="#l996"></a>
<span id="l997">     * iterator) the results of the iteration are undefined.  The set</span><a href="#l997"></a>
<span id="l998">     * supports element removal, which removes the corresponding</span><a href="#l998"></a>
<span id="l999">     * mapping from the map, via the &lt;tt&gt;Iterator.remove&lt;/tt&gt;,</span><a href="#l999"></a>
<span id="l1000">     * &lt;tt&gt;Set.remove&lt;/tt&gt;, &lt;tt&gt;removeAll&lt;/tt&gt;, &lt;tt&gt;retainAll&lt;/tt&gt; and</span><a href="#l1000"></a>
<span id="l1001">     * &lt;tt&gt;clear&lt;/tt&gt; operations.  It does not support the</span><a href="#l1001"></a>
<span id="l1002">     * &lt;tt&gt;add&lt;/tt&gt; or &lt;tt&gt;addAll&lt;/tt&gt; operations.</span><a href="#l1002"></a>
<span id="l1003">     *</span><a href="#l1003"></a>
<span id="l1004">     * @return a set view of the mappings contained in this map</span><a href="#l1004"></a>
<span id="l1005">     */</span><a href="#l1005"></a>
<span id="l1006">    public Set&lt;Map.Entry&lt;K,V&gt;&gt; entrySet() {</span><a href="#l1006"></a>
<span id="l1007">        Set&lt;Map.Entry&lt;K,V&gt;&gt; es;</span><a href="#l1007"></a>
<span id="l1008">        return (es = entrySet) == null ? (entrySet = new EntrySet()) : es;</span><a href="#l1008"></a>
<span id="l1009">    }</span><a href="#l1009"></a>
<span id="l1010"></span><a href="#l1010"></a>
<span id="l1011">    final class EntrySet extends AbstractSet&lt;Map.Entry&lt;K,V&gt;&gt; {</span><a href="#l1011"></a>
<span id="l1012">        public final int size()                 { return size; }</span><a href="#l1012"></a>
<span id="l1013">        public final void clear()               { HashMap.this.clear(); }</span><a href="#l1013"></a>
<span id="l1014">        public final Iterator&lt;Map.Entry&lt;K,V&gt;&gt; iterator() {</span><a href="#l1014"></a>
<span id="l1015">            return new EntryIterator();</span><a href="#l1015"></a>
<span id="l1016">        }</span><a href="#l1016"></a>
<span id="l1017">        public final boolean contains(Object o) {</span><a href="#l1017"></a>
<span id="l1018">            if (!(o instanceof Map.Entry))</span><a href="#l1018"></a>
<span id="l1019">                return false;</span><a href="#l1019"></a>
<span id="l1020">            Map.Entry&lt;?,?&gt; e = (Map.Entry&lt;?,?&gt;) o;</span><a href="#l1020"></a>
<span id="l1021">            Object key = e.getKey();</span><a href="#l1021"></a>
<span id="l1022">            Node&lt;K,V&gt; candidate = getNode(hash(key), key);</span><a href="#l1022"></a>
<span id="l1023">            return candidate != null &amp;&amp; candidate.equals(e);</span><a href="#l1023"></a>
<span id="l1024">        }</span><a href="#l1024"></a>
<span id="l1025">        public final boolean remove(Object o) {</span><a href="#l1025"></a>
<span id="l1026">            if (o instanceof Map.Entry) {</span><a href="#l1026"></a>
<span id="l1027">                Map.Entry&lt;?,?&gt; e = (Map.Entry&lt;?,?&gt;) o;</span><a href="#l1027"></a>
<span id="l1028">                Object key = e.getKey();</span><a href="#l1028"></a>
<span id="l1029">                Object value = e.getValue();</span><a href="#l1029"></a>
<span id="l1030">                return removeNode(hash(key), key, value, true, true) != null;</span><a href="#l1030"></a>
<span id="l1031">            }</span><a href="#l1031"></a>
<span id="l1032">            return false;</span><a href="#l1032"></a>
<span id="l1033">        }</span><a href="#l1033"></a>
<span id="l1034">        public final Spliterator&lt;Map.Entry&lt;K,V&gt;&gt; spliterator() {</span><a href="#l1034"></a>
<span id="l1035">            return new EntrySpliterator&lt;&gt;(HashMap.this, 0, -1, 0, 0);</span><a href="#l1035"></a>
<span id="l1036">        }</span><a href="#l1036"></a>
<span id="l1037">        public final void forEach(Consumer&lt;? super Map.Entry&lt;K,V&gt;&gt; action) {</span><a href="#l1037"></a>
<span id="l1038">            Node&lt;K,V&gt;[] tab;</span><a href="#l1038"></a>
<span id="l1039">            if (action == null)</span><a href="#l1039"></a>
<span id="l1040">                throw new NullPointerException();</span><a href="#l1040"></a>
<span id="l1041">            if (size &gt; 0 &amp;&amp; (tab = table) != null) {</span><a href="#l1041"></a>
<span id="l1042">                int mc = modCount;</span><a href="#l1042"></a>
<span id="l1043">                for (int i = 0; i &lt; tab.length; ++i) {</span><a href="#l1043"></a>
<span id="l1044">                    for (Node&lt;K,V&gt; e = tab[i]; e != null; e = e.next)</span><a href="#l1044"></a>
<span id="l1045">                        action.accept(e);</span><a href="#l1045"></a>
<span id="l1046">                }</span><a href="#l1046"></a>
<span id="l1047">                if (modCount != mc)</span><a href="#l1047"></a>
<span id="l1048">                    throw new ConcurrentModificationException();</span><a href="#l1048"></a>
<span id="l1049">            }</span><a href="#l1049"></a>
<span id="l1050">        }</span><a href="#l1050"></a>
<span id="l1051">    }</span><a href="#l1051"></a>
<span id="l1052"></span><a href="#l1052"></a>
<span id="l1053">    // Overrides of JDK8 Map extension methods</span><a href="#l1053"></a>
<span id="l1054"></span><a href="#l1054"></a>
<span id="l1055">    @Override</span><a href="#l1055"></a>
<span id="l1056">    public V getOrDefault(Object key, V defaultValue) {</span><a href="#l1056"></a>
<span id="l1057">        Node&lt;K,V&gt; e;</span><a href="#l1057"></a>
<span id="l1058">        return (e = getNode(hash(key), key)) == null ? defaultValue : e.value;</span><a href="#l1058"></a>
<span id="l1059">    }</span><a href="#l1059"></a>
<span id="l1060"></span><a href="#l1060"></a>
<span id="l1061">    @Override</span><a href="#l1061"></a>
<span id="l1062">    public V putIfAbsent(K key, V value) {</span><a href="#l1062"></a>
<span id="l1063">        return putVal(hash(key), key, value, true, true);</span><a href="#l1063"></a>
<span id="l1064">    }</span><a href="#l1064"></a>
<span id="l1065"></span><a href="#l1065"></a>
<span id="l1066">    @Override</span><a href="#l1066"></a>
<span id="l1067">    public boolean remove(Object key, Object value) {</span><a href="#l1067"></a>
<span id="l1068">        return removeNode(hash(key), key, value, true, true) != null;</span><a href="#l1068"></a>
<span id="l1069">    }</span><a href="#l1069"></a>
<span id="l1070"></span><a href="#l1070"></a>
<span id="l1071">    @Override</span><a href="#l1071"></a>
<span id="l1072">    public boolean replace(K key, V oldValue, V newValue) {</span><a href="#l1072"></a>
<span id="l1073">        Node&lt;K,V&gt; e; V v;</span><a href="#l1073"></a>
<span id="l1074">        if ((e = getNode(hash(key), key)) != null &amp;&amp;</span><a href="#l1074"></a>
<span id="l1075">            ((v = e.value) == oldValue || (v != null &amp;&amp; v.equals(oldValue)))) {</span><a href="#l1075"></a>
<span id="l1076">            e.value = newValue;</span><a href="#l1076"></a>
<span id="l1077">            afterNodeAccess(e);</span><a href="#l1077"></a>
<span id="l1078">            return true;</span><a href="#l1078"></a>
<span id="l1079">        }</span><a href="#l1079"></a>
<span id="l1080">        return false;</span><a href="#l1080"></a>
<span id="l1081">    }</span><a href="#l1081"></a>
<span id="l1082"></span><a href="#l1082"></a>
<span id="l1083">    @Override</span><a href="#l1083"></a>
<span id="l1084">    public V replace(K key, V value) {</span><a href="#l1084"></a>
<span id="l1085">        Node&lt;K,V&gt; e;</span><a href="#l1085"></a>
<span id="l1086">        if ((e = getNode(hash(key), key)) != null) {</span><a href="#l1086"></a>
<span id="l1087">            V oldValue = e.value;</span><a href="#l1087"></a>
<span id="l1088">            e.value = value;</span><a href="#l1088"></a>
<span id="l1089">            afterNodeAccess(e);</span><a href="#l1089"></a>
<span id="l1090">            return oldValue;</span><a href="#l1090"></a>
<span id="l1091">        }</span><a href="#l1091"></a>
<span id="l1092">        return null;</span><a href="#l1092"></a>
<span id="l1093">    }</span><a href="#l1093"></a>
<span id="l1094"></span><a href="#l1094"></a>
<span id="l1095">    @Override</span><a href="#l1095"></a>
<span id="l1096">    public V computeIfAbsent(K key,</span><a href="#l1096"></a>
<span id="l1097">                             Function&lt;? super K, ? extends V&gt; mappingFunction) {</span><a href="#l1097"></a>
<span id="l1098">        if (mappingFunction == null)</span><a href="#l1098"></a>
<span id="l1099">            throw new NullPointerException();</span><a href="#l1099"></a>
<span id="l1100">        int hash = hash(key);</span><a href="#l1100"></a>
<span id="l1101">        Node&lt;K,V&gt;[] tab; Node&lt;K,V&gt; first; int n, i;</span><a href="#l1101"></a>
<span id="l1102">        int binCount = 0;</span><a href="#l1102"></a>
<span id="l1103">        TreeNode&lt;K,V&gt; t = null;</span><a href="#l1103"></a>
<span id="l1104">        Node&lt;K,V&gt; old = null;</span><a href="#l1104"></a>
<span id="l1105">        if (size &gt; threshold || (tab = table) == null ||</span><a href="#l1105"></a>
<span id="l1106">            (n = tab.length) == 0)</span><a href="#l1106"></a>
<span id="l1107">            n = (tab = resize()).length;</span><a href="#l1107"></a>
<span id="l1108">        if ((first = tab[i = (n - 1) &amp; hash]) != null) {</span><a href="#l1108"></a>
<span id="l1109">            if (first instanceof TreeNode)</span><a href="#l1109"></a>
<span id="l1110">                old = (t = (TreeNode&lt;K,V&gt;)first).getTreeNode(hash, key);</span><a href="#l1110"></a>
<span id="l1111">            else {</span><a href="#l1111"></a>
<span id="l1112">                Node&lt;K,V&gt; e = first; K k;</span><a href="#l1112"></a>
<span id="l1113">                do {</span><a href="#l1113"></a>
<span id="l1114">                    if (e.hash == hash &amp;&amp;</span><a href="#l1114"></a>
<span id="l1115">                        ((k = e.key) == key || (key != null &amp;&amp; key.equals(k)))) {</span><a href="#l1115"></a>
<span id="l1116">                        old = e;</span><a href="#l1116"></a>
<span id="l1117">                        break;</span><a href="#l1117"></a>
<span id="l1118">                    }</span><a href="#l1118"></a>
<span id="l1119">                    ++binCount;</span><a href="#l1119"></a>
<span id="l1120">                } while ((e = e.next) != null);</span><a href="#l1120"></a>
<span id="l1121">            }</span><a href="#l1121"></a>
<span id="l1122">            V oldValue;</span><a href="#l1122"></a>
<span id="l1123">            if (old != null &amp;&amp; (oldValue = old.value) != null) {</span><a href="#l1123"></a>
<span id="l1124">                afterNodeAccess(old);</span><a href="#l1124"></a>
<span id="l1125">                return oldValue;</span><a href="#l1125"></a>
<span id="l1126">            }</span><a href="#l1126"></a>
<span id="l1127">        }</span><a href="#l1127"></a>
<span id="l1128">        V v = mappingFunction.apply(key);</span><a href="#l1128"></a>
<span id="l1129">        if (v == null) {</span><a href="#l1129"></a>
<span id="l1130">            return null;</span><a href="#l1130"></a>
<span id="l1131">        } else if (old != null) {</span><a href="#l1131"></a>
<span id="l1132">            old.value = v;</span><a href="#l1132"></a>
<span id="l1133">            afterNodeAccess(old);</span><a href="#l1133"></a>
<span id="l1134">            return v;</span><a href="#l1134"></a>
<span id="l1135">        }</span><a href="#l1135"></a>
<span id="l1136">        else if (t != null)</span><a href="#l1136"></a>
<span id="l1137">            t.putTreeVal(this, tab, hash, key, v);</span><a href="#l1137"></a>
<span id="l1138">        else {</span><a href="#l1138"></a>
<span id="l1139">            tab[i] = newNode(hash, key, v, first);</span><a href="#l1139"></a>
<span id="l1140">            if (binCount &gt;= TREEIFY_THRESHOLD - 1)</span><a href="#l1140"></a>
<span id="l1141">                treeifyBin(tab, hash);</span><a href="#l1141"></a>
<span id="l1142">        }</span><a href="#l1142"></a>
<span id="l1143">        ++modCount;</span><a href="#l1143"></a>
<span id="l1144">        ++size;</span><a href="#l1144"></a>
<span id="l1145">        afterNodeInsertion(true);</span><a href="#l1145"></a>
<span id="l1146">        return v;</span><a href="#l1146"></a>
<span id="l1147">    }</span><a href="#l1147"></a>
<span id="l1148"></span><a href="#l1148"></a>
<span id="l1149">    public V computeIfPresent(K key,</span><a href="#l1149"></a>
<span id="l1150">                              BiFunction&lt;? super K, ? super V, ? extends V&gt; remappingFunction) {</span><a href="#l1150"></a>
<span id="l1151">        if (remappingFunction == null)</span><a href="#l1151"></a>
<span id="l1152">            throw new NullPointerException();</span><a href="#l1152"></a>
<span id="l1153">        Node&lt;K,V&gt; e; V oldValue;</span><a href="#l1153"></a>
<span id="l1154">        int hash = hash(key);</span><a href="#l1154"></a>
<span id="l1155">        if ((e = getNode(hash, key)) != null &amp;&amp;</span><a href="#l1155"></a>
<span id="l1156">            (oldValue = e.value) != null) {</span><a href="#l1156"></a>
<span id="l1157">            V v = remappingFunction.apply(key, oldValue);</span><a href="#l1157"></a>
<span id="l1158">            if (v != null) {</span><a href="#l1158"></a>
<span id="l1159">                e.value = v;</span><a href="#l1159"></a>
<span id="l1160">                afterNodeAccess(e);</span><a href="#l1160"></a>
<span id="l1161">                return v;</span><a href="#l1161"></a>
<span id="l1162">            }</span><a href="#l1162"></a>
<span id="l1163">            else</span><a href="#l1163"></a>
<span id="l1164">                removeNode(hash, key, null, false, true);</span><a href="#l1164"></a>
<span id="l1165">        }</span><a href="#l1165"></a>
<span id="l1166">        return null;</span><a href="#l1166"></a>
<span id="l1167">    }</span><a href="#l1167"></a>
<span id="l1168"></span><a href="#l1168"></a>
<span id="l1169">    @Override</span><a href="#l1169"></a>
<span id="l1170">    public V compute(K key,</span><a href="#l1170"></a>
<span id="l1171">                     BiFunction&lt;? super K, ? super V, ? extends V&gt; remappingFunction) {</span><a href="#l1171"></a>
<span id="l1172">        if (remappingFunction == null)</span><a href="#l1172"></a>
<span id="l1173">            throw new NullPointerException();</span><a href="#l1173"></a>
<span id="l1174">        int hash = hash(key);</span><a href="#l1174"></a>
<span id="l1175">        Node&lt;K,V&gt;[] tab; Node&lt;K,V&gt; first; int n, i;</span><a href="#l1175"></a>
<span id="l1176">        int binCount = 0;</span><a href="#l1176"></a>
<span id="l1177">        TreeNode&lt;K,V&gt; t = null;</span><a href="#l1177"></a>
<span id="l1178">        Node&lt;K,V&gt; old = null;</span><a href="#l1178"></a>
<span id="l1179">        if (size &gt; threshold || (tab = table) == null ||</span><a href="#l1179"></a>
<span id="l1180">            (n = tab.length) == 0)</span><a href="#l1180"></a>
<span id="l1181">            n = (tab = resize()).length;</span><a href="#l1181"></a>
<span id="l1182">        if ((first = tab[i = (n - 1) &amp; hash]) != null) {</span><a href="#l1182"></a>
<span id="l1183">            if (first instanceof TreeNode)</span><a href="#l1183"></a>
<span id="l1184">                old = (t = (TreeNode&lt;K,V&gt;)first).getTreeNode(hash, key);</span><a href="#l1184"></a>
<span id="l1185">            else {</span><a href="#l1185"></a>
<span id="l1186">                Node&lt;K,V&gt; e = first; K k;</span><a href="#l1186"></a>
<span id="l1187">                do {</span><a href="#l1187"></a>
<span id="l1188">                    if (e.hash == hash &amp;&amp;</span><a href="#l1188"></a>
<span id="l1189">                        ((k = e.key) == key || (key != null &amp;&amp; key.equals(k)))) {</span><a href="#l1189"></a>
<span id="l1190">                        old = e;</span><a href="#l1190"></a>
<span id="l1191">                        break;</span><a href="#l1191"></a>
<span id="l1192">                    }</span><a href="#l1192"></a>
<span id="l1193">                    ++binCount;</span><a href="#l1193"></a>
<span id="l1194">                } while ((e = e.next) != null);</span><a href="#l1194"></a>
<span id="l1195">            }</span><a href="#l1195"></a>
<span id="l1196">        }</span><a href="#l1196"></a>
<span id="l1197">        V oldValue = (old == null) ? null : old.value;</span><a href="#l1197"></a>
<span id="l1198">        V v = remappingFunction.apply(key, oldValue);</span><a href="#l1198"></a>
<span id="l1199">        if (old != null) {</span><a href="#l1199"></a>
<span id="l1200">            if (v != null) {</span><a href="#l1200"></a>
<span id="l1201">                old.value = v;</span><a href="#l1201"></a>
<span id="l1202">                afterNodeAccess(old);</span><a href="#l1202"></a>
<span id="l1203">            }</span><a href="#l1203"></a>
<span id="l1204">            else</span><a href="#l1204"></a>
<span id="l1205">                removeNode(hash, key, null, false, true);</span><a href="#l1205"></a>
<span id="l1206">        }</span><a href="#l1206"></a>
<span id="l1207">        else if (v != null) {</span><a href="#l1207"></a>
<span id="l1208">            if (t != null)</span><a href="#l1208"></a>
<span id="l1209">                t.putTreeVal(this, tab, hash, key, v);</span><a href="#l1209"></a>
<span id="l1210">            else {</span><a href="#l1210"></a>
<span id="l1211">                tab[i] = newNode(hash, key, v, first);</span><a href="#l1211"></a>
<span id="l1212">                if (binCount &gt;= TREEIFY_THRESHOLD - 1)</span><a href="#l1212"></a>
<span id="l1213">                    treeifyBin(tab, hash);</span><a href="#l1213"></a>
<span id="l1214">            }</span><a href="#l1214"></a>
<span id="l1215">            ++modCount;</span><a href="#l1215"></a>
<span id="l1216">            ++size;</span><a href="#l1216"></a>
<span id="l1217">            afterNodeInsertion(true);</span><a href="#l1217"></a>
<span id="l1218">        }</span><a href="#l1218"></a>
<span id="l1219">        return v;</span><a href="#l1219"></a>
<span id="l1220">    }</span><a href="#l1220"></a>
<span id="l1221"></span><a href="#l1221"></a>
<span id="l1222">    @Override</span><a href="#l1222"></a>
<span id="l1223">    public V merge(K key, V value,</span><a href="#l1223"></a>
<span id="l1224">                   BiFunction&lt;? super V, ? super V, ? extends V&gt; remappingFunction) {</span><a href="#l1224"></a>
<span id="l1225">        if (value == null)</span><a href="#l1225"></a>
<span id="l1226">            throw new NullPointerException();</span><a href="#l1226"></a>
<span id="l1227">        if (remappingFunction == null)</span><a href="#l1227"></a>
<span id="l1228">            throw new NullPointerException();</span><a href="#l1228"></a>
<span id="l1229">        int hash = hash(key);</span><a href="#l1229"></a>
<span id="l1230">        Node&lt;K,V&gt;[] tab; Node&lt;K,V&gt; first; int n, i;</span><a href="#l1230"></a>
<span id="l1231">        int binCount = 0;</span><a href="#l1231"></a>
<span id="l1232">        TreeNode&lt;K,V&gt; t = null;</span><a href="#l1232"></a>
<span id="l1233">        Node&lt;K,V&gt; old = null;</span><a href="#l1233"></a>
<span id="l1234">        if (size &gt; threshold || (tab = table) == null ||</span><a href="#l1234"></a>
<span id="l1235">            (n = tab.length) == 0)</span><a href="#l1235"></a>
<span id="l1236">            n = (tab = resize()).length;</span><a href="#l1236"></a>
<span id="l1237">        if ((first = tab[i = (n - 1) &amp; hash]) != null) {</span><a href="#l1237"></a>
<span id="l1238">            if (first instanceof TreeNode)</span><a href="#l1238"></a>
<span id="l1239">                old = (t = (TreeNode&lt;K,V&gt;)first).getTreeNode(hash, key);</span><a href="#l1239"></a>
<span id="l1240">            else {</span><a href="#l1240"></a>
<span id="l1241">                Node&lt;K,V&gt; e = first; K k;</span><a href="#l1241"></a>
<span id="l1242">                do {</span><a href="#l1242"></a>
<span id="l1243">                    if (e.hash == hash &amp;&amp;</span><a href="#l1243"></a>
<span id="l1244">                        ((k = e.key) == key || (key != null &amp;&amp; key.equals(k)))) {</span><a href="#l1244"></a>
<span id="l1245">                        old = e;</span><a href="#l1245"></a>
<span id="l1246">                        break;</span><a href="#l1246"></a>
<span id="l1247">                    }</span><a href="#l1247"></a>
<span id="l1248">                    ++binCount;</span><a href="#l1248"></a>
<span id="l1249">                } while ((e = e.next) != null);</span><a href="#l1249"></a>
<span id="l1250">            }</span><a href="#l1250"></a>
<span id="l1251">        }</span><a href="#l1251"></a>
<span id="l1252">        if (old != null) {</span><a href="#l1252"></a>
<span id="l1253">            V v;</span><a href="#l1253"></a>
<span id="l1254">            if (old.value != null)</span><a href="#l1254"></a>
<span id="l1255">                v = remappingFunction.apply(old.value, value);</span><a href="#l1255"></a>
<span id="l1256">            else</span><a href="#l1256"></a>
<span id="l1257">                v = value;</span><a href="#l1257"></a>
<span id="l1258">            if (v != null) {</span><a href="#l1258"></a>
<span id="l1259">                old.value = v;</span><a href="#l1259"></a>
<span id="l1260">                afterNodeAccess(old);</span><a href="#l1260"></a>
<span id="l1261">            }</span><a href="#l1261"></a>
<span id="l1262">            else</span><a href="#l1262"></a>
<span id="l1263">                removeNode(hash, key, null, false, true);</span><a href="#l1263"></a>
<span id="l1264">            return v;</span><a href="#l1264"></a>
<span id="l1265">        }</span><a href="#l1265"></a>
<span id="l1266">        if (value != null) {</span><a href="#l1266"></a>
<span id="l1267">            if (t != null)</span><a href="#l1267"></a>
<span id="l1268">                t.putTreeVal(this, tab, hash, key, value);</span><a href="#l1268"></a>
<span id="l1269">            else {</span><a href="#l1269"></a>
<span id="l1270">                tab[i] = newNode(hash, key, value, first);</span><a href="#l1270"></a>
<span id="l1271">                if (binCount &gt;= TREEIFY_THRESHOLD - 1)</span><a href="#l1271"></a>
<span id="l1272">                    treeifyBin(tab, hash);</span><a href="#l1272"></a>
<span id="l1273">            }</span><a href="#l1273"></a>
<span id="l1274">            ++modCount;</span><a href="#l1274"></a>
<span id="l1275">            ++size;</span><a href="#l1275"></a>
<span id="l1276">            afterNodeInsertion(true);</span><a href="#l1276"></a>
<span id="l1277">        }</span><a href="#l1277"></a>
<span id="l1278">        return value;</span><a href="#l1278"></a>
<span id="l1279">    }</span><a href="#l1279"></a>
<span id="l1280"></span><a href="#l1280"></a>
<span id="l1281">    @Override</span><a href="#l1281"></a>
<span id="l1282">    public void forEach(BiConsumer&lt;? super K, ? super V&gt; action) {</span><a href="#l1282"></a>
<span id="l1283">        Node&lt;K,V&gt;[] tab;</span><a href="#l1283"></a>
<span id="l1284">        if (action == null)</span><a href="#l1284"></a>
<span id="l1285">            throw new NullPointerException();</span><a href="#l1285"></a>
<span id="l1286">        if (size &gt; 0 &amp;&amp; (tab = table) != null) {</span><a href="#l1286"></a>
<span id="l1287">            int mc = modCount;</span><a href="#l1287"></a>
<span id="l1288">            for (int i = 0; i &lt; tab.length; ++i) {</span><a href="#l1288"></a>
<span id="l1289">                for (Node&lt;K,V&gt; e = tab[i]; e != null; e = e.next)</span><a href="#l1289"></a>
<span id="l1290">                    action.accept(e.key, e.value);</span><a href="#l1290"></a>
<span id="l1291">            }</span><a href="#l1291"></a>
<span id="l1292">            if (modCount != mc)</span><a href="#l1292"></a>
<span id="l1293">                throw new ConcurrentModificationException();</span><a href="#l1293"></a>
<span id="l1294">        }</span><a href="#l1294"></a>
<span id="l1295">    }</span><a href="#l1295"></a>
<span id="l1296"></span><a href="#l1296"></a>
<span id="l1297">    @Override</span><a href="#l1297"></a>
<span id="l1298">    public void replaceAll(BiFunction&lt;? super K, ? super V, ? extends V&gt; function) {</span><a href="#l1298"></a>
<span id="l1299">        Node&lt;K,V&gt;[] tab;</span><a href="#l1299"></a>
<span id="l1300">        if (function == null)</span><a href="#l1300"></a>
<span id="l1301">            throw new NullPointerException();</span><a href="#l1301"></a>
<span id="l1302">        if (size &gt; 0 &amp;&amp; (tab = table) != null) {</span><a href="#l1302"></a>
<span id="l1303">            int mc = modCount;</span><a href="#l1303"></a>
<span id="l1304">            for (int i = 0; i &lt; tab.length; ++i) {</span><a href="#l1304"></a>
<span id="l1305">                for (Node&lt;K,V&gt; e = tab[i]; e != null; e = e.next) {</span><a href="#l1305"></a>
<span id="l1306">                    e.value = function.apply(e.key, e.value);</span><a href="#l1306"></a>
<span id="l1307">                }</span><a href="#l1307"></a>
<span id="l1308">            }</span><a href="#l1308"></a>
<span id="l1309">            if (modCount != mc)</span><a href="#l1309"></a>
<span id="l1310">                throw new ConcurrentModificationException();</span><a href="#l1310"></a>
<span id="l1311">        }</span><a href="#l1311"></a>
<span id="l1312">    }</span><a href="#l1312"></a>
<span id="l1313"></span><a href="#l1313"></a>
<span id="l1314">    /* ------------------------------------------------------------ */</span><a href="#l1314"></a>
<span id="l1315">    // Cloning and serialization</span><a href="#l1315"></a>
<span id="l1316"></span><a href="#l1316"></a>
<span id="l1317">    /**</span><a href="#l1317"></a>
<span id="l1318">     * Returns a shallow copy of this &lt;tt&gt;HashMap&lt;/tt&gt; instance: the keys and</span><a href="#l1318"></a>
<span id="l1319">     * values themselves are not cloned.</span><a href="#l1319"></a>
<span id="l1320">     *</span><a href="#l1320"></a>
<span id="l1321">     * @return a shallow copy of this map</span><a href="#l1321"></a>
<span id="l1322">     */</span><a href="#l1322"></a>
<span id="l1323">    @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l1323"></a>
<span id="l1324">    @Override</span><a href="#l1324"></a>
<span id="l1325">    public Object clone() {</span><a href="#l1325"></a>
<span id="l1326">        HashMap&lt;K,V&gt; result;</span><a href="#l1326"></a>
<span id="l1327">        try {</span><a href="#l1327"></a>
<span id="l1328">            result = (HashMap&lt;K,V&gt;)super.clone();</span><a href="#l1328"></a>
<span id="l1329">        } catch (CloneNotSupportedException e) {</span><a href="#l1329"></a>
<span id="l1330">            // this shouldn't happen, since we are Cloneable</span><a href="#l1330"></a>
<span id="l1331">            throw new InternalError(e);</span><a href="#l1331"></a>
<span id="l1332">        }</span><a href="#l1332"></a>
<span id="l1333">        result.reinitialize();</span><a href="#l1333"></a>
<span id="l1334">        result.putMapEntries(this, false);</span><a href="#l1334"></a>
<span id="l1335">        return result;</span><a href="#l1335"></a>
<span id="l1336">    }</span><a href="#l1336"></a>
<span id="l1337"></span><a href="#l1337"></a>
<span id="l1338">    // These methods are also used when serializing HashSets</span><a href="#l1338"></a>
<span id="l1339">    final float loadFactor() { return loadFactor; }</span><a href="#l1339"></a>
<span id="l1340">    final int capacity() {</span><a href="#l1340"></a>
<span id="l1341">        return (table != null) ? table.length :</span><a href="#l1341"></a>
<span id="l1342">            (threshold &gt; 0) ? threshold :</span><a href="#l1342"></a>
<span id="l1343">            DEFAULT_INITIAL_CAPACITY;</span><a href="#l1343"></a>
<span id="l1344">    }</span><a href="#l1344"></a>
<span id="l1345"></span><a href="#l1345"></a>
<span id="l1346">    /**</span><a href="#l1346"></a>
<span id="l1347">     * Save the state of the &lt;tt&gt;HashMap&lt;/tt&gt; instance to a stream (i.e.,</span><a href="#l1347"></a>
<span id="l1348">     * serialize it).</span><a href="#l1348"></a>
<span id="l1349">     *</span><a href="#l1349"></a>
<span id="l1350">     * @serialData The &lt;i&gt;capacity&lt;/i&gt; of the HashMap (the length of the</span><a href="#l1350"></a>
<span id="l1351">     *             bucket array) is emitted (int), followed by the</span><a href="#l1351"></a>
<span id="l1352">     *             &lt;i&gt;size&lt;/i&gt; (an int, the number of key-value</span><a href="#l1352"></a>
<span id="l1353">     *             mappings), followed by the key (Object) and value (Object)</span><a href="#l1353"></a>
<span id="l1354">     *             for each key-value mapping.  The key-value mappings are</span><a href="#l1354"></a>
<span id="l1355">     *             emitted in no particular order.</span><a href="#l1355"></a>
<span id="l1356">     */</span><a href="#l1356"></a>
<span id="l1357">    private void writeObject(java.io.ObjectOutputStream s)</span><a href="#l1357"></a>
<span id="l1358">        throws IOException {</span><a href="#l1358"></a>
<span id="l1359">        int buckets = capacity();</span><a href="#l1359"></a>
<span id="l1360">        // Write out the threshold, loadfactor, and any hidden stuff</span><a href="#l1360"></a>
<span id="l1361">        s.defaultWriteObject();</span><a href="#l1361"></a>
<span id="l1362">        s.writeInt(buckets);</span><a href="#l1362"></a>
<span id="l1363">        s.writeInt(size);</span><a href="#l1363"></a>
<span id="l1364">        internalWriteEntries(s);</span><a href="#l1364"></a>
<span id="l1365">    }</span><a href="#l1365"></a>
<span id="l1366"></span><a href="#l1366"></a>
<span id="l1367">    /**</span><a href="#l1367"></a>
<span id="l1368">     * Reconstitutes this map from a stream (that is, deserializes it).</span><a href="#l1368"></a>
<span id="l1369">     * @param s the stream</span><a href="#l1369"></a>
<span id="l1370">     * @throws ClassNotFoundException if the class of a serialized object</span><a href="#l1370"></a>
<span id="l1371">     *         could not be found</span><a href="#l1371"></a>
<span id="l1372">     * @throws IOException if an I/O error occurs</span><a href="#l1372"></a>
<span id="l1373">     */</span><a href="#l1373"></a>
<span id="l1374">    private void readObject(ObjectInputStream s)</span><a href="#l1374"></a>
<span id="l1375">        throws IOException, ClassNotFoundException {</span><a href="#l1375"></a>
<span id="l1376"></span><a href="#l1376"></a>
<span id="l1377">        ObjectInputStream.GetField fields = s.readFields();</span><a href="#l1377"></a>
<span id="l1378"></span><a href="#l1378"></a>
<span id="l1379">        // Read loadFactor (ignore threshold)</span><a href="#l1379"></a>
<span id="l1380">        float lf = fields.get(&quot;loadFactor&quot;, 0.75f);</span><a href="#l1380"></a>
<span id="l1381">        if (lf &lt;= 0 || Float.isNaN(lf))</span><a href="#l1381"></a>
<span id="l1382">            throw new InvalidObjectException(&quot;Illegal load factor: &quot; + lf);</span><a href="#l1382"></a>
<span id="l1383"></span><a href="#l1383"></a>
<span id="l1384">        lf = Math.min(Math.max(0.25f, lf), 4.0f);</span><a href="#l1384"></a>
<span id="l1385">        HashMap.UnsafeHolder.putLoadFactor(this, lf);</span><a href="#l1385"></a>
<span id="l1386"></span><a href="#l1386"></a>
<span id="l1387">        reinitialize();</span><a href="#l1387"></a>
<span id="l1388"></span><a href="#l1388"></a>
<span id="l1389">        s.readInt();                // Read and ignore number of buckets</span><a href="#l1389"></a>
<span id="l1390">        int mappings = s.readInt(); // Read number of mappings (size)</span><a href="#l1390"></a>
<span id="l1391">        if (mappings &lt; 0) {</span><a href="#l1391"></a>
<span id="l1392">            throw new InvalidObjectException(&quot;Illegal mappings count: &quot; + mappings);</span><a href="#l1392"></a>
<span id="l1393">        } else if (mappings == 0) {</span><a href="#l1393"></a>
<span id="l1394">            // use defaults</span><a href="#l1394"></a>
<span id="l1395">        } else if (mappings &gt; 0) {</span><a href="#l1395"></a>
<span id="l1396">            float fc = (float)mappings / lf + 1.0f;</span><a href="#l1396"></a>
<span id="l1397">            int cap = ((fc &lt; DEFAULT_INITIAL_CAPACITY) ?</span><a href="#l1397"></a>
<span id="l1398">                       DEFAULT_INITIAL_CAPACITY :</span><a href="#l1398"></a>
<span id="l1399">                       (fc &gt;= MAXIMUM_CAPACITY) ?</span><a href="#l1399"></a>
<span id="l1400">                       MAXIMUM_CAPACITY :</span><a href="#l1400"></a>
<span id="l1401">                       tableSizeFor((int)fc));</span><a href="#l1401"></a>
<span id="l1402">            float ft = (float)cap * lf;</span><a href="#l1402"></a>
<span id="l1403">            threshold = ((cap &lt; MAXIMUM_CAPACITY &amp;&amp; ft &lt; MAXIMUM_CAPACITY) ?</span><a href="#l1403"></a>
<span id="l1404">                         (int)ft : Integer.MAX_VALUE);</span><a href="#l1404"></a>
<span id="l1405"></span><a href="#l1405"></a>
<span id="l1406">            // Check Map.Entry[].class since it's the nearest public type to</span><a href="#l1406"></a>
<span id="l1407">            // what we're actually creating.</span><a href="#l1407"></a>
<span id="l1408">            SharedSecrets.getJavaOISAccess().checkArray(s, Map.Entry[].class, cap);</span><a href="#l1408"></a>
<span id="l1409">            @SuppressWarnings({&quot;rawtypes&quot;,&quot;unchecked&quot;})</span><a href="#l1409"></a>
<span id="l1410">            Node&lt;K,V&gt;[] tab = (Node&lt;K,V&gt;[])new Node[cap];</span><a href="#l1410"></a>
<span id="l1411">            table = tab;</span><a href="#l1411"></a>
<span id="l1412"></span><a href="#l1412"></a>
<span id="l1413">            // Read the keys and values, and put the mappings in the HashMap</span><a href="#l1413"></a>
<span id="l1414">            for (int i = 0; i &lt; mappings; i++) {</span><a href="#l1414"></a>
<span id="l1415">                @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l1415"></a>
<span id="l1416">                    K key = (K) s.readObject();</span><a href="#l1416"></a>
<span id="l1417">                @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l1417"></a>
<span id="l1418">                    V value = (V) s.readObject();</span><a href="#l1418"></a>
<span id="l1419">                putVal(hash(key), key, value, false, false);</span><a href="#l1419"></a>
<span id="l1420">            }</span><a href="#l1420"></a>
<span id="l1421">        }</span><a href="#l1421"></a>
<span id="l1422">    }</span><a href="#l1422"></a>
<span id="l1423"></span><a href="#l1423"></a>
<span id="l1424">    // Support for resetting final field during deserializing</span><a href="#l1424"></a>
<span id="l1425">    private static final class UnsafeHolder {</span><a href="#l1425"></a>
<span id="l1426">        private UnsafeHolder() { throw new InternalError(); }</span><a href="#l1426"></a>
<span id="l1427">        private static final sun.misc.Unsafe unsafe</span><a href="#l1427"></a>
<span id="l1428">                = sun.misc.Unsafe.getUnsafe();</span><a href="#l1428"></a>
<span id="l1429">        private static final long LF_OFFSET;</span><a href="#l1429"></a>
<span id="l1430">        static {</span><a href="#l1430"></a>
<span id="l1431">            try {</span><a href="#l1431"></a>
<span id="l1432">                LF_OFFSET = unsafe.objectFieldOffset(HashMap.class.getDeclaredField(&quot;loadFactor&quot;));</span><a href="#l1432"></a>
<span id="l1433">            } catch (NoSuchFieldException nfe) {</span><a href="#l1433"></a>
<span id="l1434">                throw new InternalError();</span><a href="#l1434"></a>
<span id="l1435">            }</span><a href="#l1435"></a>
<span id="l1436">        }</span><a href="#l1436"></a>
<span id="l1437">        static void putLoadFactor(HashMap&lt;?, ?&gt; map, float lf) {</span><a href="#l1437"></a>
<span id="l1438">            unsafe.putFloat(map, LF_OFFSET, lf);</span><a href="#l1438"></a>
<span id="l1439">        }</span><a href="#l1439"></a>
<span id="l1440">    }</span><a href="#l1440"></a>
<span id="l1441"></span><a href="#l1441"></a>
<span id="l1442">    /* ------------------------------------------------------------ */</span><a href="#l1442"></a>
<span id="l1443">    // iterators</span><a href="#l1443"></a>
<span id="l1444"></span><a href="#l1444"></a>
<span id="l1445">    abstract class HashIterator {</span><a href="#l1445"></a>
<span id="l1446">        Node&lt;K,V&gt; next;        // next entry to return</span><a href="#l1446"></a>
<span id="l1447">        Node&lt;K,V&gt; current;     // current entry</span><a href="#l1447"></a>
<span id="l1448">        int expectedModCount;  // for fast-fail</span><a href="#l1448"></a>
<span id="l1449">        int index;             // current slot</span><a href="#l1449"></a>
<span id="l1450"></span><a href="#l1450"></a>
<span id="l1451">        HashIterator() {</span><a href="#l1451"></a>
<span id="l1452">            expectedModCount = modCount;</span><a href="#l1452"></a>
<span id="l1453">            Node&lt;K,V&gt;[] t = table;</span><a href="#l1453"></a>
<span id="l1454">            current = next = null;</span><a href="#l1454"></a>
<span id="l1455">            index = 0;</span><a href="#l1455"></a>
<span id="l1456">            if (t != null &amp;&amp; size &gt; 0) { // advance to first entry</span><a href="#l1456"></a>
<span id="l1457">                do {} while (index &lt; t.length &amp;&amp; (next = t[index++]) == null);</span><a href="#l1457"></a>
<span id="l1458">            }</span><a href="#l1458"></a>
<span id="l1459">        }</span><a href="#l1459"></a>
<span id="l1460"></span><a href="#l1460"></a>
<span id="l1461">        public final boolean hasNext() {</span><a href="#l1461"></a>
<span id="l1462">            return next != null;</span><a href="#l1462"></a>
<span id="l1463">        }</span><a href="#l1463"></a>
<span id="l1464"></span><a href="#l1464"></a>
<span id="l1465">        final Node&lt;K,V&gt; nextNode() {</span><a href="#l1465"></a>
<span id="l1466">            Node&lt;K,V&gt;[] t;</span><a href="#l1466"></a>
<span id="l1467">            Node&lt;K,V&gt; e = next;</span><a href="#l1467"></a>
<span id="l1468">            if (modCount != expectedModCount)</span><a href="#l1468"></a>
<span id="l1469">                throw new ConcurrentModificationException();</span><a href="#l1469"></a>
<span id="l1470">            if (e == null)</span><a href="#l1470"></a>
<span id="l1471">                throw new NoSuchElementException();</span><a href="#l1471"></a>
<span id="l1472">            if ((next = (current = e).next) == null &amp;&amp; (t = table) != null) {</span><a href="#l1472"></a>
<span id="l1473">                do {} while (index &lt; t.length &amp;&amp; (next = t[index++]) == null);</span><a href="#l1473"></a>
<span id="l1474">            }</span><a href="#l1474"></a>
<span id="l1475">            return e;</span><a href="#l1475"></a>
<span id="l1476">        }</span><a href="#l1476"></a>
<span id="l1477"></span><a href="#l1477"></a>
<span id="l1478">        public final void remove() {</span><a href="#l1478"></a>
<span id="l1479">            Node&lt;K,V&gt; p = current;</span><a href="#l1479"></a>
<span id="l1480">            if (p == null)</span><a href="#l1480"></a>
<span id="l1481">                throw new IllegalStateException();</span><a href="#l1481"></a>
<span id="l1482">            if (modCount != expectedModCount)</span><a href="#l1482"></a>
<span id="l1483">                throw new ConcurrentModificationException();</span><a href="#l1483"></a>
<span id="l1484">            current = null;</span><a href="#l1484"></a>
<span id="l1485">            K key = p.key;</span><a href="#l1485"></a>
<span id="l1486">            removeNode(hash(key), key, null, false, false);</span><a href="#l1486"></a>
<span id="l1487">            expectedModCount = modCount;</span><a href="#l1487"></a>
<span id="l1488">        }</span><a href="#l1488"></a>
<span id="l1489">    }</span><a href="#l1489"></a>
<span id="l1490"></span><a href="#l1490"></a>
<span id="l1491">    final class KeyIterator extends HashIterator</span><a href="#l1491"></a>
<span id="l1492">        implements Iterator&lt;K&gt; {</span><a href="#l1492"></a>
<span id="l1493">        public final K next() { return nextNode().key; }</span><a href="#l1493"></a>
<span id="l1494">    }</span><a href="#l1494"></a>
<span id="l1495"></span><a href="#l1495"></a>
<span id="l1496">    final class ValueIterator extends HashIterator</span><a href="#l1496"></a>
<span id="l1497">        implements Iterator&lt;V&gt; {</span><a href="#l1497"></a>
<span id="l1498">        public final V next() { return nextNode().value; }</span><a href="#l1498"></a>
<span id="l1499">    }</span><a href="#l1499"></a>
<span id="l1500"></span><a href="#l1500"></a>
<span id="l1501">    final class EntryIterator extends HashIterator</span><a href="#l1501"></a>
<span id="l1502">        implements Iterator&lt;Map.Entry&lt;K,V&gt;&gt; {</span><a href="#l1502"></a>
<span id="l1503">        public final Map.Entry&lt;K,V&gt; next() { return nextNode(); }</span><a href="#l1503"></a>
<span id="l1504">    }</span><a href="#l1504"></a>
<span id="l1505"></span><a href="#l1505"></a>
<span id="l1506">    /* ------------------------------------------------------------ */</span><a href="#l1506"></a>
<span id="l1507">    // spliterators</span><a href="#l1507"></a>
<span id="l1508"></span><a href="#l1508"></a>
<span id="l1509">    static class HashMapSpliterator&lt;K,V&gt; {</span><a href="#l1509"></a>
<span id="l1510">        final HashMap&lt;K,V&gt; map;</span><a href="#l1510"></a>
<span id="l1511">        Node&lt;K,V&gt; current;          // current node</span><a href="#l1511"></a>
<span id="l1512">        int index;                  // current index, modified on advance/split</span><a href="#l1512"></a>
<span id="l1513">        int fence;                  // one past last index</span><a href="#l1513"></a>
<span id="l1514">        int est;                    // size estimate</span><a href="#l1514"></a>
<span id="l1515">        int expectedModCount;       // for comodification checks</span><a href="#l1515"></a>
<span id="l1516"></span><a href="#l1516"></a>
<span id="l1517">        HashMapSpliterator(HashMap&lt;K,V&gt; m, int origin,</span><a href="#l1517"></a>
<span id="l1518">                           int fence, int est,</span><a href="#l1518"></a>
<span id="l1519">                           int expectedModCount) {</span><a href="#l1519"></a>
<span id="l1520">            this.map = m;</span><a href="#l1520"></a>
<span id="l1521">            this.index = origin;</span><a href="#l1521"></a>
<span id="l1522">            this.fence = fence;</span><a href="#l1522"></a>
<span id="l1523">            this.est = est;</span><a href="#l1523"></a>
<span id="l1524">            this.expectedModCount = expectedModCount;</span><a href="#l1524"></a>
<span id="l1525">        }</span><a href="#l1525"></a>
<span id="l1526"></span><a href="#l1526"></a>
<span id="l1527">        final int getFence() { // initialize fence and size on first use</span><a href="#l1527"></a>
<span id="l1528">            int hi;</span><a href="#l1528"></a>
<span id="l1529">            if ((hi = fence) &lt; 0) {</span><a href="#l1529"></a>
<span id="l1530">                HashMap&lt;K,V&gt; m = map;</span><a href="#l1530"></a>
<span id="l1531">                est = m.size;</span><a href="#l1531"></a>
<span id="l1532">                expectedModCount = m.modCount;</span><a href="#l1532"></a>
<span id="l1533">                Node&lt;K,V&gt;[] tab = m.table;</span><a href="#l1533"></a>
<span id="l1534">                hi = fence = (tab == null) ? 0 : tab.length;</span><a href="#l1534"></a>
<span id="l1535">            }</span><a href="#l1535"></a>
<span id="l1536">            return hi;</span><a href="#l1536"></a>
<span id="l1537">        }</span><a href="#l1537"></a>
<span id="l1538"></span><a href="#l1538"></a>
<span id="l1539">        public final long estimateSize() {</span><a href="#l1539"></a>
<span id="l1540">            getFence(); // force init</span><a href="#l1540"></a>
<span id="l1541">            return (long) est;</span><a href="#l1541"></a>
<span id="l1542">        }</span><a href="#l1542"></a>
<span id="l1543">    }</span><a href="#l1543"></a>
<span id="l1544"></span><a href="#l1544"></a>
<span id="l1545">    static final class KeySpliterator&lt;K,V&gt;</span><a href="#l1545"></a>
<span id="l1546">        extends HashMapSpliterator&lt;K,V&gt;</span><a href="#l1546"></a>
<span id="l1547">        implements Spliterator&lt;K&gt; {</span><a href="#l1547"></a>
<span id="l1548">        KeySpliterator(HashMap&lt;K,V&gt; m, int origin, int fence, int est,</span><a href="#l1548"></a>
<span id="l1549">                       int expectedModCount) {</span><a href="#l1549"></a>
<span id="l1550">            super(m, origin, fence, est, expectedModCount);</span><a href="#l1550"></a>
<span id="l1551">        }</span><a href="#l1551"></a>
<span id="l1552"></span><a href="#l1552"></a>
<span id="l1553">        public KeySpliterator&lt;K,V&gt; trySplit() {</span><a href="#l1553"></a>
<span id="l1554">            int hi = getFence(), lo = index, mid = (lo + hi) &gt;&gt;&gt; 1;</span><a href="#l1554"></a>
<span id="l1555">            return (lo &gt;= mid || current != null) ? null :</span><a href="#l1555"></a>
<span id="l1556">                new KeySpliterator&lt;&gt;(map, lo, index = mid, est &gt;&gt;&gt;= 1,</span><a href="#l1556"></a>
<span id="l1557">                                        expectedModCount);</span><a href="#l1557"></a>
<span id="l1558">        }</span><a href="#l1558"></a>
<span id="l1559"></span><a href="#l1559"></a>
<span id="l1560">        public void forEachRemaining(Consumer&lt;? super K&gt; action) {</span><a href="#l1560"></a>
<span id="l1561">            int i, hi, mc;</span><a href="#l1561"></a>
<span id="l1562">            if (action == null)</span><a href="#l1562"></a>
<span id="l1563">                throw new NullPointerException();</span><a href="#l1563"></a>
<span id="l1564">            HashMap&lt;K,V&gt; m = map;</span><a href="#l1564"></a>
<span id="l1565">            Node&lt;K,V&gt;[] tab = m.table;</span><a href="#l1565"></a>
<span id="l1566">            if ((hi = fence) &lt; 0) {</span><a href="#l1566"></a>
<span id="l1567">                mc = expectedModCount = m.modCount;</span><a href="#l1567"></a>
<span id="l1568">                hi = fence = (tab == null) ? 0 : tab.length;</span><a href="#l1568"></a>
<span id="l1569">            }</span><a href="#l1569"></a>
<span id="l1570">            else</span><a href="#l1570"></a>
<span id="l1571">                mc = expectedModCount;</span><a href="#l1571"></a>
<span id="l1572">            if (tab != null &amp;&amp; tab.length &gt;= hi &amp;&amp;</span><a href="#l1572"></a>
<span id="l1573">                (i = index) &gt;= 0 &amp;&amp; (i &lt; (index = hi) || current != null)) {</span><a href="#l1573"></a>
<span id="l1574">                Node&lt;K,V&gt; p = current;</span><a href="#l1574"></a>
<span id="l1575">                current = null;</span><a href="#l1575"></a>
<span id="l1576">                do {</span><a href="#l1576"></a>
<span id="l1577">                    if (p == null)</span><a href="#l1577"></a>
<span id="l1578">                        p = tab[i++];</span><a href="#l1578"></a>
<span id="l1579">                    else {</span><a href="#l1579"></a>
<span id="l1580">                        action.accept(p.key);</span><a href="#l1580"></a>
<span id="l1581">                        p = p.next;</span><a href="#l1581"></a>
<span id="l1582">                    }</span><a href="#l1582"></a>
<span id="l1583">                } while (p != null || i &lt; hi);</span><a href="#l1583"></a>
<span id="l1584">                if (m.modCount != mc)</span><a href="#l1584"></a>
<span id="l1585">                    throw new ConcurrentModificationException();</span><a href="#l1585"></a>
<span id="l1586">            }</span><a href="#l1586"></a>
<span id="l1587">        }</span><a href="#l1587"></a>
<span id="l1588"></span><a href="#l1588"></a>
<span id="l1589">        public boolean tryAdvance(Consumer&lt;? super K&gt; action) {</span><a href="#l1589"></a>
<span id="l1590">            int hi;</span><a href="#l1590"></a>
<span id="l1591">            if (action == null)</span><a href="#l1591"></a>
<span id="l1592">                throw new NullPointerException();</span><a href="#l1592"></a>
<span id="l1593">            Node&lt;K,V&gt;[] tab = map.table;</span><a href="#l1593"></a>
<span id="l1594">            if (tab != null &amp;&amp; tab.length &gt;= (hi = getFence()) &amp;&amp; index &gt;= 0) {</span><a href="#l1594"></a>
<span id="l1595">                while (current != null || index &lt; hi) {</span><a href="#l1595"></a>
<span id="l1596">                    if (current == null)</span><a href="#l1596"></a>
<span id="l1597">                        current = tab[index++];</span><a href="#l1597"></a>
<span id="l1598">                    else {</span><a href="#l1598"></a>
<span id="l1599">                        K k = current.key;</span><a href="#l1599"></a>
<span id="l1600">                        current = current.next;</span><a href="#l1600"></a>
<span id="l1601">                        action.accept(k);</span><a href="#l1601"></a>
<span id="l1602">                        if (map.modCount != expectedModCount)</span><a href="#l1602"></a>
<span id="l1603">                            throw new ConcurrentModificationException();</span><a href="#l1603"></a>
<span id="l1604">                        return true;</span><a href="#l1604"></a>
<span id="l1605">                    }</span><a href="#l1605"></a>
<span id="l1606">                }</span><a href="#l1606"></a>
<span id="l1607">            }</span><a href="#l1607"></a>
<span id="l1608">            return false;</span><a href="#l1608"></a>
<span id="l1609">        }</span><a href="#l1609"></a>
<span id="l1610"></span><a href="#l1610"></a>
<span id="l1611">        public int characteristics() {</span><a href="#l1611"></a>
<span id="l1612">            return (fence &lt; 0 || est == map.size ? Spliterator.SIZED : 0) |</span><a href="#l1612"></a>
<span id="l1613">                Spliterator.DISTINCT;</span><a href="#l1613"></a>
<span id="l1614">        }</span><a href="#l1614"></a>
<span id="l1615">    }</span><a href="#l1615"></a>
<span id="l1616"></span><a href="#l1616"></a>
<span id="l1617">    static final class ValueSpliterator&lt;K,V&gt;</span><a href="#l1617"></a>
<span id="l1618">        extends HashMapSpliterator&lt;K,V&gt;</span><a href="#l1618"></a>
<span id="l1619">        implements Spliterator&lt;V&gt; {</span><a href="#l1619"></a>
<span id="l1620">        ValueSpliterator(HashMap&lt;K,V&gt; m, int origin, int fence, int est,</span><a href="#l1620"></a>
<span id="l1621">                         int expectedModCount) {</span><a href="#l1621"></a>
<span id="l1622">            super(m, origin, fence, est, expectedModCount);</span><a href="#l1622"></a>
<span id="l1623">        }</span><a href="#l1623"></a>
<span id="l1624"></span><a href="#l1624"></a>
<span id="l1625">        public ValueSpliterator&lt;K,V&gt; trySplit() {</span><a href="#l1625"></a>
<span id="l1626">            int hi = getFence(), lo = index, mid = (lo + hi) &gt;&gt;&gt; 1;</span><a href="#l1626"></a>
<span id="l1627">            return (lo &gt;= mid || current != null) ? null :</span><a href="#l1627"></a>
<span id="l1628">                new ValueSpliterator&lt;&gt;(map, lo, index = mid, est &gt;&gt;&gt;= 1,</span><a href="#l1628"></a>
<span id="l1629">                                          expectedModCount);</span><a href="#l1629"></a>
<span id="l1630">        }</span><a href="#l1630"></a>
<span id="l1631"></span><a href="#l1631"></a>
<span id="l1632">        public void forEachRemaining(Consumer&lt;? super V&gt; action) {</span><a href="#l1632"></a>
<span id="l1633">            int i, hi, mc;</span><a href="#l1633"></a>
<span id="l1634">            if (action == null)</span><a href="#l1634"></a>
<span id="l1635">                throw new NullPointerException();</span><a href="#l1635"></a>
<span id="l1636">            HashMap&lt;K,V&gt; m = map;</span><a href="#l1636"></a>
<span id="l1637">            Node&lt;K,V&gt;[] tab = m.table;</span><a href="#l1637"></a>
<span id="l1638">            if ((hi = fence) &lt; 0) {</span><a href="#l1638"></a>
<span id="l1639">                mc = expectedModCount = m.modCount;</span><a href="#l1639"></a>
<span id="l1640">                hi = fence = (tab == null) ? 0 : tab.length;</span><a href="#l1640"></a>
<span id="l1641">            }</span><a href="#l1641"></a>
<span id="l1642">            else</span><a href="#l1642"></a>
<span id="l1643">                mc = expectedModCount;</span><a href="#l1643"></a>
<span id="l1644">            if (tab != null &amp;&amp; tab.length &gt;= hi &amp;&amp;</span><a href="#l1644"></a>
<span id="l1645">                (i = index) &gt;= 0 &amp;&amp; (i &lt; (index = hi) || current != null)) {</span><a href="#l1645"></a>
<span id="l1646">                Node&lt;K,V&gt; p = current;</span><a href="#l1646"></a>
<span id="l1647">                current = null;</span><a href="#l1647"></a>
<span id="l1648">                do {</span><a href="#l1648"></a>
<span id="l1649">                    if (p == null)</span><a href="#l1649"></a>
<span id="l1650">                        p = tab[i++];</span><a href="#l1650"></a>
<span id="l1651">                    else {</span><a href="#l1651"></a>
<span id="l1652">                        action.accept(p.value);</span><a href="#l1652"></a>
<span id="l1653">                        p = p.next;</span><a href="#l1653"></a>
<span id="l1654">                    }</span><a href="#l1654"></a>
<span id="l1655">                } while (p != null || i &lt; hi);</span><a href="#l1655"></a>
<span id="l1656">                if (m.modCount != mc)</span><a href="#l1656"></a>
<span id="l1657">                    throw new ConcurrentModificationException();</span><a href="#l1657"></a>
<span id="l1658">            }</span><a href="#l1658"></a>
<span id="l1659">        }</span><a href="#l1659"></a>
<span id="l1660"></span><a href="#l1660"></a>
<span id="l1661">        public boolean tryAdvance(Consumer&lt;? super V&gt; action) {</span><a href="#l1661"></a>
<span id="l1662">            int hi;</span><a href="#l1662"></a>
<span id="l1663">            if (action == null)</span><a href="#l1663"></a>
<span id="l1664">                throw new NullPointerException();</span><a href="#l1664"></a>
<span id="l1665">            Node&lt;K,V&gt;[] tab = map.table;</span><a href="#l1665"></a>
<span id="l1666">            if (tab != null &amp;&amp; tab.length &gt;= (hi = getFence()) &amp;&amp; index &gt;= 0) {</span><a href="#l1666"></a>
<span id="l1667">                while (current != null || index &lt; hi) {</span><a href="#l1667"></a>
<span id="l1668">                    if (current == null)</span><a href="#l1668"></a>
<span id="l1669">                        current = tab[index++];</span><a href="#l1669"></a>
<span id="l1670">                    else {</span><a href="#l1670"></a>
<span id="l1671">                        V v = current.value;</span><a href="#l1671"></a>
<span id="l1672">                        current = current.next;</span><a href="#l1672"></a>
<span id="l1673">                        action.accept(v);</span><a href="#l1673"></a>
<span id="l1674">                        if (map.modCount != expectedModCount)</span><a href="#l1674"></a>
<span id="l1675">                            throw new ConcurrentModificationException();</span><a href="#l1675"></a>
<span id="l1676">                        return true;</span><a href="#l1676"></a>
<span id="l1677">                    }</span><a href="#l1677"></a>
<span id="l1678">                }</span><a href="#l1678"></a>
<span id="l1679">            }</span><a href="#l1679"></a>
<span id="l1680">            return false;</span><a href="#l1680"></a>
<span id="l1681">        }</span><a href="#l1681"></a>
<span id="l1682"></span><a href="#l1682"></a>
<span id="l1683">        public int characteristics() {</span><a href="#l1683"></a>
<span id="l1684">            return (fence &lt; 0 || est == map.size ? Spliterator.SIZED : 0);</span><a href="#l1684"></a>
<span id="l1685">        }</span><a href="#l1685"></a>
<span id="l1686">    }</span><a href="#l1686"></a>
<span id="l1687"></span><a href="#l1687"></a>
<span id="l1688">    static final class EntrySpliterator&lt;K,V&gt;</span><a href="#l1688"></a>
<span id="l1689">        extends HashMapSpliterator&lt;K,V&gt;</span><a href="#l1689"></a>
<span id="l1690">        implements Spliterator&lt;Map.Entry&lt;K,V&gt;&gt; {</span><a href="#l1690"></a>
<span id="l1691">        EntrySpliterator(HashMap&lt;K,V&gt; m, int origin, int fence, int est,</span><a href="#l1691"></a>
<span id="l1692">                         int expectedModCount) {</span><a href="#l1692"></a>
<span id="l1693">            super(m, origin, fence, est, expectedModCount);</span><a href="#l1693"></a>
<span id="l1694">        }</span><a href="#l1694"></a>
<span id="l1695"></span><a href="#l1695"></a>
<span id="l1696">        public EntrySpliterator&lt;K,V&gt; trySplit() {</span><a href="#l1696"></a>
<span id="l1697">            int hi = getFence(), lo = index, mid = (lo + hi) &gt;&gt;&gt; 1;</span><a href="#l1697"></a>
<span id="l1698">            return (lo &gt;= mid || current != null) ? null :</span><a href="#l1698"></a>
<span id="l1699">                new EntrySpliterator&lt;&gt;(map, lo, index = mid, est &gt;&gt;&gt;= 1,</span><a href="#l1699"></a>
<span id="l1700">                                          expectedModCount);</span><a href="#l1700"></a>
<span id="l1701">        }</span><a href="#l1701"></a>
<span id="l1702"></span><a href="#l1702"></a>
<span id="l1703">        public void forEachRemaining(Consumer&lt;? super Map.Entry&lt;K,V&gt;&gt; action) {</span><a href="#l1703"></a>
<span id="l1704">            int i, hi, mc;</span><a href="#l1704"></a>
<span id="l1705">            if (action == null)</span><a href="#l1705"></a>
<span id="l1706">                throw new NullPointerException();</span><a href="#l1706"></a>
<span id="l1707">            HashMap&lt;K,V&gt; m = map;</span><a href="#l1707"></a>
<span id="l1708">            Node&lt;K,V&gt;[] tab = m.table;</span><a href="#l1708"></a>
<span id="l1709">            if ((hi = fence) &lt; 0) {</span><a href="#l1709"></a>
<span id="l1710">                mc = expectedModCount = m.modCount;</span><a href="#l1710"></a>
<span id="l1711">                hi = fence = (tab == null) ? 0 : tab.length;</span><a href="#l1711"></a>
<span id="l1712">            }</span><a href="#l1712"></a>
<span id="l1713">            else</span><a href="#l1713"></a>
<span id="l1714">                mc = expectedModCount;</span><a href="#l1714"></a>
<span id="l1715">            if (tab != null &amp;&amp; tab.length &gt;= hi &amp;&amp;</span><a href="#l1715"></a>
<span id="l1716">                (i = index) &gt;= 0 &amp;&amp; (i &lt; (index = hi) || current != null)) {</span><a href="#l1716"></a>
<span id="l1717">                Node&lt;K,V&gt; p = current;</span><a href="#l1717"></a>
<span id="l1718">                current = null;</span><a href="#l1718"></a>
<span id="l1719">                do {</span><a href="#l1719"></a>
<span id="l1720">                    if (p == null)</span><a href="#l1720"></a>
<span id="l1721">                        p = tab[i++];</span><a href="#l1721"></a>
<span id="l1722">                    else {</span><a href="#l1722"></a>
<span id="l1723">                        action.accept(p);</span><a href="#l1723"></a>
<span id="l1724">                        p = p.next;</span><a href="#l1724"></a>
<span id="l1725">                    }</span><a href="#l1725"></a>
<span id="l1726">                } while (p != null || i &lt; hi);</span><a href="#l1726"></a>
<span id="l1727">                if (m.modCount != mc)</span><a href="#l1727"></a>
<span id="l1728">                    throw new ConcurrentModificationException();</span><a href="#l1728"></a>
<span id="l1729">            }</span><a href="#l1729"></a>
<span id="l1730">        }</span><a href="#l1730"></a>
<span id="l1731"></span><a href="#l1731"></a>
<span id="l1732">        public boolean tryAdvance(Consumer&lt;? super Map.Entry&lt;K,V&gt;&gt; action) {</span><a href="#l1732"></a>
<span id="l1733">            int hi;</span><a href="#l1733"></a>
<span id="l1734">            if (action == null)</span><a href="#l1734"></a>
<span id="l1735">                throw new NullPointerException();</span><a href="#l1735"></a>
<span id="l1736">            Node&lt;K,V&gt;[] tab = map.table;</span><a href="#l1736"></a>
<span id="l1737">            if (tab != null &amp;&amp; tab.length &gt;= (hi = getFence()) &amp;&amp; index &gt;= 0) {</span><a href="#l1737"></a>
<span id="l1738">                while (current != null || index &lt; hi) {</span><a href="#l1738"></a>
<span id="l1739">                    if (current == null)</span><a href="#l1739"></a>
<span id="l1740">                        current = tab[index++];</span><a href="#l1740"></a>
<span id="l1741">                    else {</span><a href="#l1741"></a>
<span id="l1742">                        Node&lt;K,V&gt; e = current;</span><a href="#l1742"></a>
<span id="l1743">                        current = current.next;</span><a href="#l1743"></a>
<span id="l1744">                        action.accept(e);</span><a href="#l1744"></a>
<span id="l1745">                        if (map.modCount != expectedModCount)</span><a href="#l1745"></a>
<span id="l1746">                            throw new ConcurrentModificationException();</span><a href="#l1746"></a>
<span id="l1747">                        return true;</span><a href="#l1747"></a>
<span id="l1748">                    }</span><a href="#l1748"></a>
<span id="l1749">                }</span><a href="#l1749"></a>
<span id="l1750">            }</span><a href="#l1750"></a>
<span id="l1751">            return false;</span><a href="#l1751"></a>
<span id="l1752">        }</span><a href="#l1752"></a>
<span id="l1753"></span><a href="#l1753"></a>
<span id="l1754">        public int characteristics() {</span><a href="#l1754"></a>
<span id="l1755">            return (fence &lt; 0 || est == map.size ? Spliterator.SIZED : 0) |</span><a href="#l1755"></a>
<span id="l1756">                Spliterator.DISTINCT;</span><a href="#l1756"></a>
<span id="l1757">        }</span><a href="#l1757"></a>
<span id="l1758">    }</span><a href="#l1758"></a>
<span id="l1759"></span><a href="#l1759"></a>
<span id="l1760">    /* ------------------------------------------------------------ */</span><a href="#l1760"></a>
<span id="l1761">    // LinkedHashMap support</span><a href="#l1761"></a>
<span id="l1762"></span><a href="#l1762"></a>
<span id="l1763"></span><a href="#l1763"></a>
<span id="l1764">    /*</span><a href="#l1764"></a>
<span id="l1765">     * The following package-protected methods are designed to be</span><a href="#l1765"></a>
<span id="l1766">     * overridden by LinkedHashMap, but not by any other subclass.</span><a href="#l1766"></a>
<span id="l1767">     * Nearly all other internal methods are also package-protected</span><a href="#l1767"></a>
<span id="l1768">     * but are declared final, so can be used by LinkedHashMap, view</span><a href="#l1768"></a>
<span id="l1769">     * classes, and HashSet.</span><a href="#l1769"></a>
<span id="l1770">     */</span><a href="#l1770"></a>
<span id="l1771"></span><a href="#l1771"></a>
<span id="l1772">    // Create a regular (non-tree) node</span><a href="#l1772"></a>
<span id="l1773">    Node&lt;K,V&gt; newNode(int hash, K key, V value, Node&lt;K,V&gt; next) {</span><a href="#l1773"></a>
<span id="l1774">        return new Node&lt;&gt;(hash, key, value, next);</span><a href="#l1774"></a>
<span id="l1775">    }</span><a href="#l1775"></a>
<span id="l1776"></span><a href="#l1776"></a>
<span id="l1777">    // For conversion from TreeNodes to plain nodes</span><a href="#l1777"></a>
<span id="l1778">    Node&lt;K,V&gt; replacementNode(Node&lt;K,V&gt; p, Node&lt;K,V&gt; next) {</span><a href="#l1778"></a>
<span id="l1779">        return new Node&lt;&gt;(p.hash, p.key, p.value, next);</span><a href="#l1779"></a>
<span id="l1780">    }</span><a href="#l1780"></a>
<span id="l1781"></span><a href="#l1781"></a>
<span id="l1782">    // Create a tree bin node</span><a href="#l1782"></a>
<span id="l1783">    TreeNode&lt;K,V&gt; newTreeNode(int hash, K key, V value, Node&lt;K,V&gt; next) {</span><a href="#l1783"></a>
<span id="l1784">        return new TreeNode&lt;&gt;(hash, key, value, next);</span><a href="#l1784"></a>
<span id="l1785">    }</span><a href="#l1785"></a>
<span id="l1786"></span><a href="#l1786"></a>
<span id="l1787">    // For treeifyBin</span><a href="#l1787"></a>
<span id="l1788">    TreeNode&lt;K,V&gt; replacementTreeNode(Node&lt;K,V&gt; p, Node&lt;K,V&gt; next) {</span><a href="#l1788"></a>
<span id="l1789">        return new TreeNode&lt;&gt;(p.hash, p.key, p.value, next);</span><a href="#l1789"></a>
<span id="l1790">    }</span><a href="#l1790"></a>
<span id="l1791"></span><a href="#l1791"></a>
<span id="l1792">    /**</span><a href="#l1792"></a>
<span id="l1793">     * Reset to initial default state.  Called by clone and readObject.</span><a href="#l1793"></a>
<span id="l1794">     */</span><a href="#l1794"></a>
<span id="l1795">    void reinitialize() {</span><a href="#l1795"></a>
<span id="l1796">        table = null;</span><a href="#l1796"></a>
<span id="l1797">        entrySet = null;</span><a href="#l1797"></a>
<span id="l1798">        keySet = null;</span><a href="#l1798"></a>
<span id="l1799">        values = null;</span><a href="#l1799"></a>
<span id="l1800">        modCount = 0;</span><a href="#l1800"></a>
<span id="l1801">        threshold = 0;</span><a href="#l1801"></a>
<span id="l1802">        size = 0;</span><a href="#l1802"></a>
<span id="l1803">    }</span><a href="#l1803"></a>
<span id="l1804"></span><a href="#l1804"></a>
<span id="l1805">    // Callbacks to allow LinkedHashMap post-actions</span><a href="#l1805"></a>
<span id="l1806">    void afterNodeAccess(Node&lt;K,V&gt; p) { }</span><a href="#l1806"></a>
<span id="l1807">    void afterNodeInsertion(boolean evict) { }</span><a href="#l1807"></a>
<span id="l1808">    void afterNodeRemoval(Node&lt;K,V&gt; p) { }</span><a href="#l1808"></a>
<span id="l1809"></span><a href="#l1809"></a>
<span id="l1810">    // Called only from writeObject, to ensure compatible ordering.</span><a href="#l1810"></a>
<span id="l1811">    void internalWriteEntries(java.io.ObjectOutputStream s) throws IOException {</span><a href="#l1811"></a>
<span id="l1812">        Node&lt;K,V&gt;[] tab;</span><a href="#l1812"></a>
<span id="l1813">        if (size &gt; 0 &amp;&amp; (tab = table) != null) {</span><a href="#l1813"></a>
<span id="l1814">            for (int i = 0; i &lt; tab.length; ++i) {</span><a href="#l1814"></a>
<span id="l1815">                for (Node&lt;K,V&gt; e = tab[i]; e != null; e = e.next) {</span><a href="#l1815"></a>
<span id="l1816">                    s.writeObject(e.key);</span><a href="#l1816"></a>
<span id="l1817">                    s.writeObject(e.value);</span><a href="#l1817"></a>
<span id="l1818">                }</span><a href="#l1818"></a>
<span id="l1819">            }</span><a href="#l1819"></a>
<span id="l1820">        }</span><a href="#l1820"></a>
<span id="l1821">    }</span><a href="#l1821"></a>
<span id="l1822"></span><a href="#l1822"></a>
<span id="l1823">    /* ------------------------------------------------------------ */</span><a href="#l1823"></a>
<span id="l1824">    // Tree bins</span><a href="#l1824"></a>
<span id="l1825"></span><a href="#l1825"></a>
<span id="l1826">    /**</span><a href="#l1826"></a>
<span id="l1827">     * Entry for Tree bins. Extends LinkedHashMap.Entry (which in turn</span><a href="#l1827"></a>
<span id="l1828">     * extends Node) so can be used as extension of either regular or</span><a href="#l1828"></a>
<span id="l1829">     * linked node.</span><a href="#l1829"></a>
<span id="l1830">     */</span><a href="#l1830"></a>
<span id="l1831">    static final class TreeNode&lt;K,V&gt; extends LinkedHashMap.Entry&lt;K,V&gt; {</span><a href="#l1831"></a>
<span id="l1832">        TreeNode&lt;K,V&gt; parent;  // red-black tree links</span><a href="#l1832"></a>
<span id="l1833">        TreeNode&lt;K,V&gt; left;</span><a href="#l1833"></a>
<span id="l1834">        TreeNode&lt;K,V&gt; right;</span><a href="#l1834"></a>
<span id="l1835">        TreeNode&lt;K,V&gt; prev;    // needed to unlink next upon deletion</span><a href="#l1835"></a>
<span id="l1836">        boolean red;</span><a href="#l1836"></a>
<span id="l1837">        TreeNode(int hash, K key, V val, Node&lt;K,V&gt; next) {</span><a href="#l1837"></a>
<span id="l1838">            super(hash, key, val, next);</span><a href="#l1838"></a>
<span id="l1839">        }</span><a href="#l1839"></a>
<span id="l1840"></span><a href="#l1840"></a>
<span id="l1841">        /**</span><a href="#l1841"></a>
<span id="l1842">         * Returns root of tree containing this node.</span><a href="#l1842"></a>
<span id="l1843">         */</span><a href="#l1843"></a>
<span id="l1844">        final TreeNode&lt;K,V&gt; root() {</span><a href="#l1844"></a>
<span id="l1845">            for (TreeNode&lt;K,V&gt; r = this, p;;) {</span><a href="#l1845"></a>
<span id="l1846">                if ((p = r.parent) == null)</span><a href="#l1846"></a>
<span id="l1847">                    return r;</span><a href="#l1847"></a>
<span id="l1848">                r = p;</span><a href="#l1848"></a>
<span id="l1849">            }</span><a href="#l1849"></a>
<span id="l1850">        }</span><a href="#l1850"></a>
<span id="l1851"></span><a href="#l1851"></a>
<span id="l1852">        /**</span><a href="#l1852"></a>
<span id="l1853">         * Ensures that the given root is the first node of its bin.</span><a href="#l1853"></a>
<span id="l1854">         */</span><a href="#l1854"></a>
<span id="l1855">        static &lt;K,V&gt; void moveRootToFront(Node&lt;K,V&gt;[] tab, TreeNode&lt;K,V&gt; root) {</span><a href="#l1855"></a>
<span id="l1856">            int n;</span><a href="#l1856"></a>
<span id="l1857">            if (root != null &amp;&amp; tab != null &amp;&amp; (n = tab.length) &gt; 0) {</span><a href="#l1857"></a>
<span id="l1858">                int index = (n - 1) &amp; root.hash;</span><a href="#l1858"></a>
<span id="l1859">                TreeNode&lt;K,V&gt; first = (TreeNode&lt;K,V&gt;)tab[index];</span><a href="#l1859"></a>
<span id="l1860">                if (root != first) {</span><a href="#l1860"></a>
<span id="l1861">                    Node&lt;K,V&gt; rn;</span><a href="#l1861"></a>
<span id="l1862">                    tab[index] = root;</span><a href="#l1862"></a>
<span id="l1863">                    TreeNode&lt;K,V&gt; rp = root.prev;</span><a href="#l1863"></a>
<span id="l1864">                    if ((rn = root.next) != null)</span><a href="#l1864"></a>
<span id="l1865">                        ((TreeNode&lt;K,V&gt;)rn).prev = rp;</span><a href="#l1865"></a>
<span id="l1866">                    if (rp != null)</span><a href="#l1866"></a>
<span id="l1867">                        rp.next = rn;</span><a href="#l1867"></a>
<span id="l1868">                    if (first != null)</span><a href="#l1868"></a>
<span id="l1869">                        first.prev = root;</span><a href="#l1869"></a>
<span id="l1870">                    root.next = first;</span><a href="#l1870"></a>
<span id="l1871">                    root.prev = null;</span><a href="#l1871"></a>
<span id="l1872">                }</span><a href="#l1872"></a>
<span id="l1873">                assert checkInvariants(root);</span><a href="#l1873"></a>
<span id="l1874">            }</span><a href="#l1874"></a>
<span id="l1875">        }</span><a href="#l1875"></a>
<span id="l1876"></span><a href="#l1876"></a>
<span id="l1877">        /**</span><a href="#l1877"></a>
<span id="l1878">         * Finds the node starting at root p with the given hash and key.</span><a href="#l1878"></a>
<span id="l1879">         * The kc argument caches comparableClassFor(key) upon first use</span><a href="#l1879"></a>
<span id="l1880">         * comparing keys.</span><a href="#l1880"></a>
<span id="l1881">         */</span><a href="#l1881"></a>
<span id="l1882">        final TreeNode&lt;K,V&gt; find(int h, Object k, Class&lt;?&gt; kc) {</span><a href="#l1882"></a>
<span id="l1883">            TreeNode&lt;K,V&gt; p = this;</span><a href="#l1883"></a>
<span id="l1884">            do {</span><a href="#l1884"></a>
<span id="l1885">                int ph, dir; K pk;</span><a href="#l1885"></a>
<span id="l1886">                TreeNode&lt;K,V&gt; pl = p.left, pr = p.right, q;</span><a href="#l1886"></a>
<span id="l1887">                if ((ph = p.hash) &gt; h)</span><a href="#l1887"></a>
<span id="l1888">                    p = pl;</span><a href="#l1888"></a>
<span id="l1889">                else if (ph &lt; h)</span><a href="#l1889"></a>
<span id="l1890">                    p = pr;</span><a href="#l1890"></a>
<span id="l1891">                else if ((pk = p.key) == k || (k != null &amp;&amp; k.equals(pk)))</span><a href="#l1891"></a>
<span id="l1892">                    return p;</span><a href="#l1892"></a>
<span id="l1893">                else if (pl == null)</span><a href="#l1893"></a>
<span id="l1894">                    p = pr;</span><a href="#l1894"></a>
<span id="l1895">                else if (pr == null)</span><a href="#l1895"></a>
<span id="l1896">                    p = pl;</span><a href="#l1896"></a>
<span id="l1897">                else if ((kc != null ||</span><a href="#l1897"></a>
<span id="l1898">                          (kc = comparableClassFor(k)) != null) &amp;&amp;</span><a href="#l1898"></a>
<span id="l1899">                         (dir = compareComparables(kc, k, pk)) != 0)</span><a href="#l1899"></a>
<span id="l1900">                    p = (dir &lt; 0) ? pl : pr;</span><a href="#l1900"></a>
<span id="l1901">                else if ((q = pr.find(h, k, kc)) != null)</span><a href="#l1901"></a>
<span id="l1902">                    return q;</span><a href="#l1902"></a>
<span id="l1903">                else</span><a href="#l1903"></a>
<span id="l1904">                    p = pl;</span><a href="#l1904"></a>
<span id="l1905">            } while (p != null);</span><a href="#l1905"></a>
<span id="l1906">            return null;</span><a href="#l1906"></a>
<span id="l1907">        }</span><a href="#l1907"></a>
<span id="l1908"></span><a href="#l1908"></a>
<span id="l1909">        /**</span><a href="#l1909"></a>
<span id="l1910">         * Calls find for root node.</span><a href="#l1910"></a>
<span id="l1911">         */</span><a href="#l1911"></a>
<span id="l1912">        final TreeNode&lt;K,V&gt; getTreeNode(int h, Object k) {</span><a href="#l1912"></a>
<span id="l1913">            return ((parent != null) ? root() : this).find(h, k, null);</span><a href="#l1913"></a>
<span id="l1914">        }</span><a href="#l1914"></a>
<span id="l1915"></span><a href="#l1915"></a>
<span id="l1916">        /**</span><a href="#l1916"></a>
<span id="l1917">         * Tie-breaking utility for ordering insertions when equal</span><a href="#l1917"></a>
<span id="l1918">         * hashCodes and non-comparable. We don't require a total</span><a href="#l1918"></a>
<span id="l1919">         * order, just a consistent insertion rule to maintain</span><a href="#l1919"></a>
<span id="l1920">         * equivalence across rebalancings. Tie-breaking further than</span><a href="#l1920"></a>
<span id="l1921">         * necessary simplifies testing a bit.</span><a href="#l1921"></a>
<span id="l1922">         */</span><a href="#l1922"></a>
<span id="l1923">        static int tieBreakOrder(Object a, Object b) {</span><a href="#l1923"></a>
<span id="l1924">            int d;</span><a href="#l1924"></a>
<span id="l1925">            if (a == null || b == null ||</span><a href="#l1925"></a>
<span id="l1926">                (d = a.getClass().getName().</span><a href="#l1926"></a>
<span id="l1927">                 compareTo(b.getClass().getName())) == 0)</span><a href="#l1927"></a>
<span id="l1928">                d = (System.identityHashCode(a) &lt;= System.identityHashCode(b) ?</span><a href="#l1928"></a>
<span id="l1929">                     -1 : 1);</span><a href="#l1929"></a>
<span id="l1930">            return d;</span><a href="#l1930"></a>
<span id="l1931">        }</span><a href="#l1931"></a>
<span id="l1932"></span><a href="#l1932"></a>
<span id="l1933">        /**</span><a href="#l1933"></a>
<span id="l1934">         * Forms tree of the nodes linked from this node.</span><a href="#l1934"></a>
<span id="l1935">         */</span><a href="#l1935"></a>
<span id="l1936">        final void treeify(Node&lt;K,V&gt;[] tab) {</span><a href="#l1936"></a>
<span id="l1937">            TreeNode&lt;K,V&gt; root = null;</span><a href="#l1937"></a>
<span id="l1938">            for (TreeNode&lt;K,V&gt; x = this, next; x != null; x = next) {</span><a href="#l1938"></a>
<span id="l1939">                next = (TreeNode&lt;K,V&gt;)x.next;</span><a href="#l1939"></a>
<span id="l1940">                x.left = x.right = null;</span><a href="#l1940"></a>
<span id="l1941">                if (root == null) {</span><a href="#l1941"></a>
<span id="l1942">                    x.parent = null;</span><a href="#l1942"></a>
<span id="l1943">                    x.red = false;</span><a href="#l1943"></a>
<span id="l1944">                    root = x;</span><a href="#l1944"></a>
<span id="l1945">                }</span><a href="#l1945"></a>
<span id="l1946">                else {</span><a href="#l1946"></a>
<span id="l1947">                    K k = x.key;</span><a href="#l1947"></a>
<span id="l1948">                    int h = x.hash;</span><a href="#l1948"></a>
<span id="l1949">                    Class&lt;?&gt; kc = null;</span><a href="#l1949"></a>
<span id="l1950">                    for (TreeNode&lt;K,V&gt; p = root;;) {</span><a href="#l1950"></a>
<span id="l1951">                        int dir, ph;</span><a href="#l1951"></a>
<span id="l1952">                        K pk = p.key;</span><a href="#l1952"></a>
<span id="l1953">                        if ((ph = p.hash) &gt; h)</span><a href="#l1953"></a>
<span id="l1954">                            dir = -1;</span><a href="#l1954"></a>
<span id="l1955">                        else if (ph &lt; h)</span><a href="#l1955"></a>
<span id="l1956">                            dir = 1;</span><a href="#l1956"></a>
<span id="l1957">                        else if ((kc == null &amp;&amp;</span><a href="#l1957"></a>
<span id="l1958">                                  (kc = comparableClassFor(k)) == null) ||</span><a href="#l1958"></a>
<span id="l1959">                                 (dir = compareComparables(kc, k, pk)) == 0)</span><a href="#l1959"></a>
<span id="l1960">                            dir = tieBreakOrder(k, pk);</span><a href="#l1960"></a>
<span id="l1961"></span><a href="#l1961"></a>
<span id="l1962">                        TreeNode&lt;K,V&gt; xp = p;</span><a href="#l1962"></a>
<span id="l1963">                        if ((p = (dir &lt;= 0) ? p.left : p.right) == null) {</span><a href="#l1963"></a>
<span id="l1964">                            x.parent = xp;</span><a href="#l1964"></a>
<span id="l1965">                            if (dir &lt;= 0)</span><a href="#l1965"></a>
<span id="l1966">                                xp.left = x;</span><a href="#l1966"></a>
<span id="l1967">                            else</span><a href="#l1967"></a>
<span id="l1968">                                xp.right = x;</span><a href="#l1968"></a>
<span id="l1969">                            root = balanceInsertion(root, x);</span><a href="#l1969"></a>
<span id="l1970">                            break;</span><a href="#l1970"></a>
<span id="l1971">                        }</span><a href="#l1971"></a>
<span id="l1972">                    }</span><a href="#l1972"></a>
<span id="l1973">                }</span><a href="#l1973"></a>
<span id="l1974">            }</span><a href="#l1974"></a>
<span id="l1975">            moveRootToFront(tab, root);</span><a href="#l1975"></a>
<span id="l1976">        }</span><a href="#l1976"></a>
<span id="l1977"></span><a href="#l1977"></a>
<span id="l1978">        /**</span><a href="#l1978"></a>
<span id="l1979">         * Returns a list of non-TreeNodes replacing those linked from</span><a href="#l1979"></a>
<span id="l1980">         * this node.</span><a href="#l1980"></a>
<span id="l1981">         */</span><a href="#l1981"></a>
<span id="l1982">        final Node&lt;K,V&gt; untreeify(HashMap&lt;K,V&gt; map) {</span><a href="#l1982"></a>
<span id="l1983">            Node&lt;K,V&gt; hd = null, tl = null;</span><a href="#l1983"></a>
<span id="l1984">            for (Node&lt;K,V&gt; q = this; q != null; q = q.next) {</span><a href="#l1984"></a>
<span id="l1985">                Node&lt;K,V&gt; p = map.replacementNode(q, null);</span><a href="#l1985"></a>
<span id="l1986">                if (tl == null)</span><a href="#l1986"></a>
<span id="l1987">                    hd = p;</span><a href="#l1987"></a>
<span id="l1988">                else</span><a href="#l1988"></a>
<span id="l1989">                    tl.next = p;</span><a href="#l1989"></a>
<span id="l1990">                tl = p;</span><a href="#l1990"></a>
<span id="l1991">            }</span><a href="#l1991"></a>
<span id="l1992">            return hd;</span><a href="#l1992"></a>
<span id="l1993">        }</span><a href="#l1993"></a>
<span id="l1994"></span><a href="#l1994"></a>
<span id="l1995">        /**</span><a href="#l1995"></a>
<span id="l1996">         * Tree version of putVal.</span><a href="#l1996"></a>
<span id="l1997">         */</span><a href="#l1997"></a>
<span id="l1998">        final TreeNode&lt;K,V&gt; putTreeVal(HashMap&lt;K,V&gt; map, Node&lt;K,V&gt;[] tab,</span><a href="#l1998"></a>
<span id="l1999">                                       int h, K k, V v) {</span><a href="#l1999"></a>
<span id="l2000">            Class&lt;?&gt; kc = null;</span><a href="#l2000"></a>
<span id="l2001">            boolean searched = false;</span><a href="#l2001"></a>
<span id="l2002">            TreeNode&lt;K,V&gt; root = (parent != null) ? root() : this;</span><a href="#l2002"></a>
<span id="l2003">            for (TreeNode&lt;K,V&gt; p = root;;) {</span><a href="#l2003"></a>
<span id="l2004">                int dir, ph; K pk;</span><a href="#l2004"></a>
<span id="l2005">                if ((ph = p.hash) &gt; h)</span><a href="#l2005"></a>
<span id="l2006">                    dir = -1;</span><a href="#l2006"></a>
<span id="l2007">                else if (ph &lt; h)</span><a href="#l2007"></a>
<span id="l2008">                    dir = 1;</span><a href="#l2008"></a>
<span id="l2009">                else if ((pk = p.key) == k || (k != null &amp;&amp; k.equals(pk)))</span><a href="#l2009"></a>
<span id="l2010">                    return p;</span><a href="#l2010"></a>
<span id="l2011">                else if ((kc == null &amp;&amp;</span><a href="#l2011"></a>
<span id="l2012">                          (kc = comparableClassFor(k)) == null) ||</span><a href="#l2012"></a>
<span id="l2013">                         (dir = compareComparables(kc, k, pk)) == 0) {</span><a href="#l2013"></a>
<span id="l2014">                    if (!searched) {</span><a href="#l2014"></a>
<span id="l2015">                        TreeNode&lt;K,V&gt; q, ch;</span><a href="#l2015"></a>
<span id="l2016">                        searched = true;</span><a href="#l2016"></a>
<span id="l2017">                        if (((ch = p.left) != null &amp;&amp;</span><a href="#l2017"></a>
<span id="l2018">                             (q = ch.find(h, k, kc)) != null) ||</span><a href="#l2018"></a>
<span id="l2019">                            ((ch = p.right) != null &amp;&amp;</span><a href="#l2019"></a>
<span id="l2020">                             (q = ch.find(h, k, kc)) != null))</span><a href="#l2020"></a>
<span id="l2021">                            return q;</span><a href="#l2021"></a>
<span id="l2022">                    }</span><a href="#l2022"></a>
<span id="l2023">                    dir = tieBreakOrder(k, pk);</span><a href="#l2023"></a>
<span id="l2024">                }</span><a href="#l2024"></a>
<span id="l2025"></span><a href="#l2025"></a>
<span id="l2026">                TreeNode&lt;K,V&gt; xp = p;</span><a href="#l2026"></a>
<span id="l2027">                if ((p = (dir &lt;= 0) ? p.left : p.right) == null) {</span><a href="#l2027"></a>
<span id="l2028">                    Node&lt;K,V&gt; xpn = xp.next;</span><a href="#l2028"></a>
<span id="l2029">                    TreeNode&lt;K,V&gt; x = map.newTreeNode(h, k, v, xpn);</span><a href="#l2029"></a>
<span id="l2030">                    if (dir &lt;= 0)</span><a href="#l2030"></a>
<span id="l2031">                        xp.left = x;</span><a href="#l2031"></a>
<span id="l2032">                    else</span><a href="#l2032"></a>
<span id="l2033">                        xp.right = x;</span><a href="#l2033"></a>
<span id="l2034">                    xp.next = x;</span><a href="#l2034"></a>
<span id="l2035">                    x.parent = x.prev = xp;</span><a href="#l2035"></a>
<span id="l2036">                    if (xpn != null)</span><a href="#l2036"></a>
<span id="l2037">                        ((TreeNode&lt;K,V&gt;)xpn).prev = x;</span><a href="#l2037"></a>
<span id="l2038">                    moveRootToFront(tab, balanceInsertion(root, x));</span><a href="#l2038"></a>
<span id="l2039">                    return null;</span><a href="#l2039"></a>
<span id="l2040">                }</span><a href="#l2040"></a>
<span id="l2041">            }</span><a href="#l2041"></a>
<span id="l2042">        }</span><a href="#l2042"></a>
<span id="l2043"></span><a href="#l2043"></a>
<span id="l2044">        /**</span><a href="#l2044"></a>
<span id="l2045">         * Removes the given node, that must be present before this call.</span><a href="#l2045"></a>
<span id="l2046">         * This is messier than typical red-black deletion code because we</span><a href="#l2046"></a>
<span id="l2047">         * cannot swap the contents of an interior node with a leaf</span><a href="#l2047"></a>
<span id="l2048">         * successor that is pinned by &quot;next&quot; pointers that are accessible</span><a href="#l2048"></a>
<span id="l2049">         * independently during traversal. So instead we swap the tree</span><a href="#l2049"></a>
<span id="l2050">         * linkages. If the current tree appears to have too few nodes,</span><a href="#l2050"></a>
<span id="l2051">         * the bin is converted back to a plain bin. (The test triggers</span><a href="#l2051"></a>
<span id="l2052">         * somewhere between 2 and 6 nodes, depending on tree structure).</span><a href="#l2052"></a>
<span id="l2053">         */</span><a href="#l2053"></a>
<span id="l2054">        final void removeTreeNode(HashMap&lt;K,V&gt; map, Node&lt;K,V&gt;[] tab,</span><a href="#l2054"></a>
<span id="l2055">                                  boolean movable) {</span><a href="#l2055"></a>
<span id="l2056">            int n;</span><a href="#l2056"></a>
<span id="l2057">            if (tab == null || (n = tab.length) == 0)</span><a href="#l2057"></a>
<span id="l2058">                return;</span><a href="#l2058"></a>
<span id="l2059">            int index = (n - 1) &amp; hash;</span><a href="#l2059"></a>
<span id="l2060">            TreeNode&lt;K,V&gt; first = (TreeNode&lt;K,V&gt;)tab[index], root = first, rl;</span><a href="#l2060"></a>
<span id="l2061">            TreeNode&lt;K,V&gt; succ = (TreeNode&lt;K,V&gt;)next, pred = prev;</span><a href="#l2061"></a>
<span id="l2062">            if (pred == null)</span><a href="#l2062"></a>
<span id="l2063">                tab[index] = first = succ;</span><a href="#l2063"></a>
<span id="l2064">            else</span><a href="#l2064"></a>
<span id="l2065">                pred.next = succ;</span><a href="#l2065"></a>
<span id="l2066">            if (succ != null)</span><a href="#l2066"></a>
<span id="l2067">                succ.prev = pred;</span><a href="#l2067"></a>
<span id="l2068">            if (first == null)</span><a href="#l2068"></a>
<span id="l2069">                return;</span><a href="#l2069"></a>
<span id="l2070">            if (root.parent != null)</span><a href="#l2070"></a>
<span id="l2071">                root = root.root();</span><a href="#l2071"></a>
<span id="l2072">            if (root == null</span><a href="#l2072"></a>
<span id="l2073">                || (movable</span><a href="#l2073"></a>
<span id="l2074">                    &amp;&amp; (root.right == null</span><a href="#l2074"></a>
<span id="l2075">                        || (rl = root.left) == null</span><a href="#l2075"></a>
<span id="l2076">                        || rl.left == null))) {</span><a href="#l2076"></a>
<span id="l2077">                tab[index] = first.untreeify(map);  // too small</span><a href="#l2077"></a>
<span id="l2078">                return;</span><a href="#l2078"></a>
<span id="l2079">            }</span><a href="#l2079"></a>
<span id="l2080">            TreeNode&lt;K,V&gt; p = this, pl = left, pr = right, replacement;</span><a href="#l2080"></a>
<span id="l2081">            if (pl != null &amp;&amp; pr != null) {</span><a href="#l2081"></a>
<span id="l2082">                TreeNode&lt;K,V&gt; s = pr, sl;</span><a href="#l2082"></a>
<span id="l2083">                while ((sl = s.left) != null) // find successor</span><a href="#l2083"></a>
<span id="l2084">                    s = sl;</span><a href="#l2084"></a>
<span id="l2085">                boolean c = s.red; s.red = p.red; p.red = c; // swap colors</span><a href="#l2085"></a>
<span id="l2086">                TreeNode&lt;K,V&gt; sr = s.right;</span><a href="#l2086"></a>
<span id="l2087">                TreeNode&lt;K,V&gt; pp = p.parent;</span><a href="#l2087"></a>
<span id="l2088">                if (s == pr) { // p was s's direct parent</span><a href="#l2088"></a>
<span id="l2089">                    p.parent = s;</span><a href="#l2089"></a>
<span id="l2090">                    s.right = p;</span><a href="#l2090"></a>
<span id="l2091">                }</span><a href="#l2091"></a>
<span id="l2092">                else {</span><a href="#l2092"></a>
<span id="l2093">                    TreeNode&lt;K,V&gt; sp = s.parent;</span><a href="#l2093"></a>
<span id="l2094">                    if ((p.parent = sp) != null) {</span><a href="#l2094"></a>
<span id="l2095">                        if (s == sp.left)</span><a href="#l2095"></a>
<span id="l2096">                            sp.left = p;</span><a href="#l2096"></a>
<span id="l2097">                        else</span><a href="#l2097"></a>
<span id="l2098">                            sp.right = p;</span><a href="#l2098"></a>
<span id="l2099">                    }</span><a href="#l2099"></a>
<span id="l2100">                    if ((s.right = pr) != null)</span><a href="#l2100"></a>
<span id="l2101">                        pr.parent = s;</span><a href="#l2101"></a>
<span id="l2102">                }</span><a href="#l2102"></a>
<span id="l2103">                p.left = null;</span><a href="#l2103"></a>
<span id="l2104">                if ((p.right = sr) != null)</span><a href="#l2104"></a>
<span id="l2105">                    sr.parent = p;</span><a href="#l2105"></a>
<span id="l2106">                if ((s.left = pl) != null)</span><a href="#l2106"></a>
<span id="l2107">                    pl.parent = s;</span><a href="#l2107"></a>
<span id="l2108">                if ((s.parent = pp) == null)</span><a href="#l2108"></a>
<span id="l2109">                    root = s;</span><a href="#l2109"></a>
<span id="l2110">                else if (p == pp.left)</span><a href="#l2110"></a>
<span id="l2111">                    pp.left = s;</span><a href="#l2111"></a>
<span id="l2112">                else</span><a href="#l2112"></a>
<span id="l2113">                    pp.right = s;</span><a href="#l2113"></a>
<span id="l2114">                if (sr != null)</span><a href="#l2114"></a>
<span id="l2115">                    replacement = sr;</span><a href="#l2115"></a>
<span id="l2116">                else</span><a href="#l2116"></a>
<span id="l2117">                    replacement = p;</span><a href="#l2117"></a>
<span id="l2118">            }</span><a href="#l2118"></a>
<span id="l2119">            else if (pl != null)</span><a href="#l2119"></a>
<span id="l2120">                replacement = pl;</span><a href="#l2120"></a>
<span id="l2121">            else if (pr != null)</span><a href="#l2121"></a>
<span id="l2122">                replacement = pr;</span><a href="#l2122"></a>
<span id="l2123">            else</span><a href="#l2123"></a>
<span id="l2124">                replacement = p;</span><a href="#l2124"></a>
<span id="l2125">            if (replacement != p) {</span><a href="#l2125"></a>
<span id="l2126">                TreeNode&lt;K,V&gt; pp = replacement.parent = p.parent;</span><a href="#l2126"></a>
<span id="l2127">                if (pp == null)</span><a href="#l2127"></a>
<span id="l2128">                    root = replacement;</span><a href="#l2128"></a>
<span id="l2129">                else if (p == pp.left)</span><a href="#l2129"></a>
<span id="l2130">                    pp.left = replacement;</span><a href="#l2130"></a>
<span id="l2131">                else</span><a href="#l2131"></a>
<span id="l2132">                    pp.right = replacement;</span><a href="#l2132"></a>
<span id="l2133">                p.left = p.right = p.parent = null;</span><a href="#l2133"></a>
<span id="l2134">            }</span><a href="#l2134"></a>
<span id="l2135"></span><a href="#l2135"></a>
<span id="l2136">            TreeNode&lt;K,V&gt; r = p.red ? root : balanceDeletion(root, replacement);</span><a href="#l2136"></a>
<span id="l2137"></span><a href="#l2137"></a>
<span id="l2138">            if (replacement == p) {  // detach</span><a href="#l2138"></a>
<span id="l2139">                TreeNode&lt;K,V&gt; pp = p.parent;</span><a href="#l2139"></a>
<span id="l2140">                p.parent = null;</span><a href="#l2140"></a>
<span id="l2141">                if (pp != null) {</span><a href="#l2141"></a>
<span id="l2142">                    if (p == pp.left)</span><a href="#l2142"></a>
<span id="l2143">                        pp.left = null;</span><a href="#l2143"></a>
<span id="l2144">                    else if (p == pp.right)</span><a href="#l2144"></a>
<span id="l2145">                        pp.right = null;</span><a href="#l2145"></a>
<span id="l2146">                }</span><a href="#l2146"></a>
<span id="l2147">            }</span><a href="#l2147"></a>
<span id="l2148">            if (movable)</span><a href="#l2148"></a>
<span id="l2149">                moveRootToFront(tab, r);</span><a href="#l2149"></a>
<span id="l2150">        }</span><a href="#l2150"></a>
<span id="l2151"></span><a href="#l2151"></a>
<span id="l2152">        /**</span><a href="#l2152"></a>
<span id="l2153">         * Splits nodes in a tree bin into lower and upper tree bins,</span><a href="#l2153"></a>
<span id="l2154">         * or untreeifies if now too small. Called only from resize;</span><a href="#l2154"></a>
<span id="l2155">         * see above discussion about split bits and indices.</span><a href="#l2155"></a>
<span id="l2156">         *</span><a href="#l2156"></a>
<span id="l2157">         * @param map the map</span><a href="#l2157"></a>
<span id="l2158">         * @param tab the table for recording bin heads</span><a href="#l2158"></a>
<span id="l2159">         * @param index the index of the table being split</span><a href="#l2159"></a>
<span id="l2160">         * @param bit the bit of hash to split on</span><a href="#l2160"></a>
<span id="l2161">         */</span><a href="#l2161"></a>
<span id="l2162">        final void split(HashMap&lt;K,V&gt; map, Node&lt;K,V&gt;[] tab, int index, int bit) {</span><a href="#l2162"></a>
<span id="l2163">            TreeNode&lt;K,V&gt; b = this;</span><a href="#l2163"></a>
<span id="l2164">            // Relink into lo and hi lists, preserving order</span><a href="#l2164"></a>
<span id="l2165">            TreeNode&lt;K,V&gt; loHead = null, loTail = null;</span><a href="#l2165"></a>
<span id="l2166">            TreeNode&lt;K,V&gt; hiHead = null, hiTail = null;</span><a href="#l2166"></a>
<span id="l2167">            int lc = 0, hc = 0;</span><a href="#l2167"></a>
<span id="l2168">            for (TreeNode&lt;K,V&gt; e = b, next; e != null; e = next) {</span><a href="#l2168"></a>
<span id="l2169">                next = (TreeNode&lt;K,V&gt;)e.next;</span><a href="#l2169"></a>
<span id="l2170">                e.next = null;</span><a href="#l2170"></a>
<span id="l2171">                if ((e.hash &amp; bit) == 0) {</span><a href="#l2171"></a>
<span id="l2172">                    if ((e.prev = loTail) == null)</span><a href="#l2172"></a>
<span id="l2173">                        loHead = e;</span><a href="#l2173"></a>
<span id="l2174">                    else</span><a href="#l2174"></a>
<span id="l2175">                        loTail.next = e;</span><a href="#l2175"></a>
<span id="l2176">                    loTail = e;</span><a href="#l2176"></a>
<span id="l2177">                    ++lc;</span><a href="#l2177"></a>
<span id="l2178">                }</span><a href="#l2178"></a>
<span id="l2179">                else {</span><a href="#l2179"></a>
<span id="l2180">                    if ((e.prev = hiTail) == null)</span><a href="#l2180"></a>
<span id="l2181">                        hiHead = e;</span><a href="#l2181"></a>
<span id="l2182">                    else</span><a href="#l2182"></a>
<span id="l2183">                        hiTail.next = e;</span><a href="#l2183"></a>
<span id="l2184">                    hiTail = e;</span><a href="#l2184"></a>
<span id="l2185">                    ++hc;</span><a href="#l2185"></a>
<span id="l2186">                }</span><a href="#l2186"></a>
<span id="l2187">            }</span><a href="#l2187"></a>
<span id="l2188"></span><a href="#l2188"></a>
<span id="l2189">            if (loHead != null) {</span><a href="#l2189"></a>
<span id="l2190">                if (lc &lt;= UNTREEIFY_THRESHOLD)</span><a href="#l2190"></a>
<span id="l2191">                    tab[index] = loHead.untreeify(map);</span><a href="#l2191"></a>
<span id="l2192">                else {</span><a href="#l2192"></a>
<span id="l2193">                    tab[index] = loHead;</span><a href="#l2193"></a>
<span id="l2194">                    if (hiHead != null) // (else is already treeified)</span><a href="#l2194"></a>
<span id="l2195">                        loHead.treeify(tab);</span><a href="#l2195"></a>
<span id="l2196">                }</span><a href="#l2196"></a>
<span id="l2197">            }</span><a href="#l2197"></a>
<span id="l2198">            if (hiHead != null) {</span><a href="#l2198"></a>
<span id="l2199">                if (hc &lt;= UNTREEIFY_THRESHOLD)</span><a href="#l2199"></a>
<span id="l2200">                    tab[index + bit] = hiHead.untreeify(map);</span><a href="#l2200"></a>
<span id="l2201">                else {</span><a href="#l2201"></a>
<span id="l2202">                    tab[index + bit] = hiHead;</span><a href="#l2202"></a>
<span id="l2203">                    if (loHead != null)</span><a href="#l2203"></a>
<span id="l2204">                        hiHead.treeify(tab);</span><a href="#l2204"></a>
<span id="l2205">                }</span><a href="#l2205"></a>
<span id="l2206">            }</span><a href="#l2206"></a>
<span id="l2207">        }</span><a href="#l2207"></a>
<span id="l2208"></span><a href="#l2208"></a>
<span id="l2209">        /* ------------------------------------------------------------ */</span><a href="#l2209"></a>
<span id="l2210">        // Red-black tree methods, all adapted from CLR</span><a href="#l2210"></a>
<span id="l2211"></span><a href="#l2211"></a>
<span id="l2212">        static &lt;K,V&gt; TreeNode&lt;K,V&gt; rotateLeft(TreeNode&lt;K,V&gt; root,</span><a href="#l2212"></a>
<span id="l2213">                                              TreeNode&lt;K,V&gt; p) {</span><a href="#l2213"></a>
<span id="l2214">            TreeNode&lt;K,V&gt; r, pp, rl;</span><a href="#l2214"></a>
<span id="l2215">            if (p != null &amp;&amp; (r = p.right) != null) {</span><a href="#l2215"></a>
<span id="l2216">                if ((rl = p.right = r.left) != null)</span><a href="#l2216"></a>
<span id="l2217">                    rl.parent = p;</span><a href="#l2217"></a>
<span id="l2218">                if ((pp = r.parent = p.parent) == null)</span><a href="#l2218"></a>
<span id="l2219">                    (root = r).red = false;</span><a href="#l2219"></a>
<span id="l2220">                else if (pp.left == p)</span><a href="#l2220"></a>
<span id="l2221">                    pp.left = r;</span><a href="#l2221"></a>
<span id="l2222">                else</span><a href="#l2222"></a>
<span id="l2223">                    pp.right = r;</span><a href="#l2223"></a>
<span id="l2224">                r.left = p;</span><a href="#l2224"></a>
<span id="l2225">                p.parent = r;</span><a href="#l2225"></a>
<span id="l2226">            }</span><a href="#l2226"></a>
<span id="l2227">            return root;</span><a href="#l2227"></a>
<span id="l2228">        }</span><a href="#l2228"></a>
<span id="l2229"></span><a href="#l2229"></a>
<span id="l2230">        static &lt;K,V&gt; TreeNode&lt;K,V&gt; rotateRight(TreeNode&lt;K,V&gt; root,</span><a href="#l2230"></a>
<span id="l2231">                                               TreeNode&lt;K,V&gt; p) {</span><a href="#l2231"></a>
<span id="l2232">            TreeNode&lt;K,V&gt; l, pp, lr;</span><a href="#l2232"></a>
<span id="l2233">            if (p != null &amp;&amp; (l = p.left) != null) {</span><a href="#l2233"></a>
<span id="l2234">                if ((lr = p.left = l.right) != null)</span><a href="#l2234"></a>
<span id="l2235">                    lr.parent = p;</span><a href="#l2235"></a>
<span id="l2236">                if ((pp = l.parent = p.parent) == null)</span><a href="#l2236"></a>
<span id="l2237">                    (root = l).red = false;</span><a href="#l2237"></a>
<span id="l2238">                else if (pp.right == p)</span><a href="#l2238"></a>
<span id="l2239">                    pp.right = l;</span><a href="#l2239"></a>
<span id="l2240">                else</span><a href="#l2240"></a>
<span id="l2241">                    pp.left = l;</span><a href="#l2241"></a>
<span id="l2242">                l.right = p;</span><a href="#l2242"></a>
<span id="l2243">                p.parent = l;</span><a href="#l2243"></a>
<span id="l2244">            }</span><a href="#l2244"></a>
<span id="l2245">            return root;</span><a href="#l2245"></a>
<span id="l2246">        }</span><a href="#l2246"></a>
<span id="l2247"></span><a href="#l2247"></a>
<span id="l2248">        static &lt;K,V&gt; TreeNode&lt;K,V&gt; balanceInsertion(TreeNode&lt;K,V&gt; root,</span><a href="#l2248"></a>
<span id="l2249">                                                    TreeNode&lt;K,V&gt; x) {</span><a href="#l2249"></a>
<span id="l2250">            x.red = true;</span><a href="#l2250"></a>
<span id="l2251">            for (TreeNode&lt;K,V&gt; xp, xpp, xppl, xppr;;) {</span><a href="#l2251"></a>
<span id="l2252">                if ((xp = x.parent) == null) {</span><a href="#l2252"></a>
<span id="l2253">                    x.red = false;</span><a href="#l2253"></a>
<span id="l2254">                    return x;</span><a href="#l2254"></a>
<span id="l2255">                }</span><a href="#l2255"></a>
<span id="l2256">                else if (!xp.red || (xpp = xp.parent) == null)</span><a href="#l2256"></a>
<span id="l2257">                    return root;</span><a href="#l2257"></a>
<span id="l2258">                if (xp == (xppl = xpp.left)) {</span><a href="#l2258"></a>
<span id="l2259">                    if ((xppr = xpp.right) != null &amp;&amp; xppr.red) {</span><a href="#l2259"></a>
<span id="l2260">                        xppr.red = false;</span><a href="#l2260"></a>
<span id="l2261">                        xp.red = false;</span><a href="#l2261"></a>
<span id="l2262">                        xpp.red = true;</span><a href="#l2262"></a>
<span id="l2263">                        x = xpp;</span><a href="#l2263"></a>
<span id="l2264">                    }</span><a href="#l2264"></a>
<span id="l2265">                    else {</span><a href="#l2265"></a>
<span id="l2266">                        if (x == xp.right) {</span><a href="#l2266"></a>
<span id="l2267">                            root = rotateLeft(root, x = xp);</span><a href="#l2267"></a>
<span id="l2268">                            xpp = (xp = x.parent) == null ? null : xp.parent;</span><a href="#l2268"></a>
<span id="l2269">                        }</span><a href="#l2269"></a>
<span id="l2270">                        if (xp != null) {</span><a href="#l2270"></a>
<span id="l2271">                            xp.red = false;</span><a href="#l2271"></a>
<span id="l2272">                            if (xpp != null) {</span><a href="#l2272"></a>
<span id="l2273">                                xpp.red = true;</span><a href="#l2273"></a>
<span id="l2274">                                root = rotateRight(root, xpp);</span><a href="#l2274"></a>
<span id="l2275">                            }</span><a href="#l2275"></a>
<span id="l2276">                        }</span><a href="#l2276"></a>
<span id="l2277">                    }</span><a href="#l2277"></a>
<span id="l2278">                }</span><a href="#l2278"></a>
<span id="l2279">                else {</span><a href="#l2279"></a>
<span id="l2280">                    if (xppl != null &amp;&amp; xppl.red) {</span><a href="#l2280"></a>
<span id="l2281">                        xppl.red = false;</span><a href="#l2281"></a>
<span id="l2282">                        xp.red = false;</span><a href="#l2282"></a>
<span id="l2283">                        xpp.red = true;</span><a href="#l2283"></a>
<span id="l2284">                        x = xpp;</span><a href="#l2284"></a>
<span id="l2285">                    }</span><a href="#l2285"></a>
<span id="l2286">                    else {</span><a href="#l2286"></a>
<span id="l2287">                        if (x == xp.left) {</span><a href="#l2287"></a>
<span id="l2288">                            root = rotateRight(root, x = xp);</span><a href="#l2288"></a>
<span id="l2289">                            xpp = (xp = x.parent) == null ? null : xp.parent;</span><a href="#l2289"></a>
<span id="l2290">                        }</span><a href="#l2290"></a>
<span id="l2291">                        if (xp != null) {</span><a href="#l2291"></a>
<span id="l2292">                            xp.red = false;</span><a href="#l2292"></a>
<span id="l2293">                            if (xpp != null) {</span><a href="#l2293"></a>
<span id="l2294">                                xpp.red = true;</span><a href="#l2294"></a>
<span id="l2295">                                root = rotateLeft(root, xpp);</span><a href="#l2295"></a>
<span id="l2296">                            }</span><a href="#l2296"></a>
<span id="l2297">                        }</span><a href="#l2297"></a>
<span id="l2298">                    }</span><a href="#l2298"></a>
<span id="l2299">                }</span><a href="#l2299"></a>
<span id="l2300">            }</span><a href="#l2300"></a>
<span id="l2301">        }</span><a href="#l2301"></a>
<span id="l2302"></span><a href="#l2302"></a>
<span id="l2303">        static &lt;K,V&gt; TreeNode&lt;K,V&gt; balanceDeletion(TreeNode&lt;K,V&gt; root,</span><a href="#l2303"></a>
<span id="l2304">                                                   TreeNode&lt;K,V&gt; x) {</span><a href="#l2304"></a>
<span id="l2305">            for (TreeNode&lt;K,V&gt; xp, xpl, xpr;;) {</span><a href="#l2305"></a>
<span id="l2306">                if (x == null || x == root)</span><a href="#l2306"></a>
<span id="l2307">                    return root;</span><a href="#l2307"></a>
<span id="l2308">                else if ((xp = x.parent) == null) {</span><a href="#l2308"></a>
<span id="l2309">                    x.red = false;</span><a href="#l2309"></a>
<span id="l2310">                    return x;</span><a href="#l2310"></a>
<span id="l2311">                }</span><a href="#l2311"></a>
<span id="l2312">                else if (x.red) {</span><a href="#l2312"></a>
<span id="l2313">                    x.red = false;</span><a href="#l2313"></a>
<span id="l2314">                    return root;</span><a href="#l2314"></a>
<span id="l2315">                }</span><a href="#l2315"></a>
<span id="l2316">                else if ((xpl = xp.left) == x) {</span><a href="#l2316"></a>
<span id="l2317">                    if ((xpr = xp.right) != null &amp;&amp; xpr.red) {</span><a href="#l2317"></a>
<span id="l2318">                        xpr.red = false;</span><a href="#l2318"></a>
<span id="l2319">                        xp.red = true;</span><a href="#l2319"></a>
<span id="l2320">                        root = rotateLeft(root, xp);</span><a href="#l2320"></a>
<span id="l2321">                        xpr = (xp = x.parent) == null ? null : xp.right;</span><a href="#l2321"></a>
<span id="l2322">                    }</span><a href="#l2322"></a>
<span id="l2323">                    if (xpr == null)</span><a href="#l2323"></a>
<span id="l2324">                        x = xp;</span><a href="#l2324"></a>
<span id="l2325">                    else {</span><a href="#l2325"></a>
<span id="l2326">                        TreeNode&lt;K,V&gt; sl = xpr.left, sr = xpr.right;</span><a href="#l2326"></a>
<span id="l2327">                        if ((sr == null || !sr.red) &amp;&amp;</span><a href="#l2327"></a>
<span id="l2328">                            (sl == null || !sl.red)) {</span><a href="#l2328"></a>
<span id="l2329">                            xpr.red = true;</span><a href="#l2329"></a>
<span id="l2330">                            x = xp;</span><a href="#l2330"></a>
<span id="l2331">                        }</span><a href="#l2331"></a>
<span id="l2332">                        else {</span><a href="#l2332"></a>
<span id="l2333">                            if (sr == null || !sr.red) {</span><a href="#l2333"></a>
<span id="l2334">                                if (sl != null)</span><a href="#l2334"></a>
<span id="l2335">                                    sl.red = false;</span><a href="#l2335"></a>
<span id="l2336">                                xpr.red = true;</span><a href="#l2336"></a>
<span id="l2337">                                root = rotateRight(root, xpr);</span><a href="#l2337"></a>
<span id="l2338">                                xpr = (xp = x.parent) == null ?</span><a href="#l2338"></a>
<span id="l2339">                                    null : xp.right;</span><a href="#l2339"></a>
<span id="l2340">                            }</span><a href="#l2340"></a>
<span id="l2341">                            if (xpr != null) {</span><a href="#l2341"></a>
<span id="l2342">                                xpr.red = (xp == null) ? false : xp.red;</span><a href="#l2342"></a>
<span id="l2343">                                if ((sr = xpr.right) != null)</span><a href="#l2343"></a>
<span id="l2344">                                    sr.red = false;</span><a href="#l2344"></a>
<span id="l2345">                            }</span><a href="#l2345"></a>
<span id="l2346">                            if (xp != null) {</span><a href="#l2346"></a>
<span id="l2347">                                xp.red = false;</span><a href="#l2347"></a>
<span id="l2348">                                root = rotateLeft(root, xp);</span><a href="#l2348"></a>
<span id="l2349">                            }</span><a href="#l2349"></a>
<span id="l2350">                            x = root;</span><a href="#l2350"></a>
<span id="l2351">                        }</span><a href="#l2351"></a>
<span id="l2352">                    }</span><a href="#l2352"></a>
<span id="l2353">                }</span><a href="#l2353"></a>
<span id="l2354">                else { // symmetric</span><a href="#l2354"></a>
<span id="l2355">                    if (xpl != null &amp;&amp; xpl.red) {</span><a href="#l2355"></a>
<span id="l2356">                        xpl.red = false;</span><a href="#l2356"></a>
<span id="l2357">                        xp.red = true;</span><a href="#l2357"></a>
<span id="l2358">                        root = rotateRight(root, xp);</span><a href="#l2358"></a>
<span id="l2359">                        xpl = (xp = x.parent) == null ? null : xp.left;</span><a href="#l2359"></a>
<span id="l2360">                    }</span><a href="#l2360"></a>
<span id="l2361">                    if (xpl == null)</span><a href="#l2361"></a>
<span id="l2362">                        x = xp;</span><a href="#l2362"></a>
<span id="l2363">                    else {</span><a href="#l2363"></a>
<span id="l2364">                        TreeNode&lt;K,V&gt; sl = xpl.left, sr = xpl.right;</span><a href="#l2364"></a>
<span id="l2365">                        if ((sl == null || !sl.red) &amp;&amp;</span><a href="#l2365"></a>
<span id="l2366">                            (sr == null || !sr.red)) {</span><a href="#l2366"></a>
<span id="l2367">                            xpl.red = true;</span><a href="#l2367"></a>
<span id="l2368">                            x = xp;</span><a href="#l2368"></a>
<span id="l2369">                        }</span><a href="#l2369"></a>
<span id="l2370">                        else {</span><a href="#l2370"></a>
<span id="l2371">                            if (sl == null || !sl.red) {</span><a href="#l2371"></a>
<span id="l2372">                                if (sr != null)</span><a href="#l2372"></a>
<span id="l2373">                                    sr.red = false;</span><a href="#l2373"></a>
<span id="l2374">                                xpl.red = true;</span><a href="#l2374"></a>
<span id="l2375">                                root = rotateLeft(root, xpl);</span><a href="#l2375"></a>
<span id="l2376">                                xpl = (xp = x.parent) == null ?</span><a href="#l2376"></a>
<span id="l2377">                                    null : xp.left;</span><a href="#l2377"></a>
<span id="l2378">                            }</span><a href="#l2378"></a>
<span id="l2379">                            if (xpl != null) {</span><a href="#l2379"></a>
<span id="l2380">                                xpl.red = (xp == null) ? false : xp.red;</span><a href="#l2380"></a>
<span id="l2381">                                if ((sl = xpl.left) != null)</span><a href="#l2381"></a>
<span id="l2382">                                    sl.red = false;</span><a href="#l2382"></a>
<span id="l2383">                            }</span><a href="#l2383"></a>
<span id="l2384">                            if (xp != null) {</span><a href="#l2384"></a>
<span id="l2385">                                xp.red = false;</span><a href="#l2385"></a>
<span id="l2386">                                root = rotateRight(root, xp);</span><a href="#l2386"></a>
<span id="l2387">                            }</span><a href="#l2387"></a>
<span id="l2388">                            x = root;</span><a href="#l2388"></a>
<span id="l2389">                        }</span><a href="#l2389"></a>
<span id="l2390">                    }</span><a href="#l2390"></a>
<span id="l2391">                }</span><a href="#l2391"></a>
<span id="l2392">            }</span><a href="#l2392"></a>
<span id="l2393">        }</span><a href="#l2393"></a>
<span id="l2394"></span><a href="#l2394"></a>
<span id="l2395">        /**</span><a href="#l2395"></a>
<span id="l2396">         * Recursive invariant check</span><a href="#l2396"></a>
<span id="l2397">         */</span><a href="#l2397"></a>
<span id="l2398">        static &lt;K,V&gt; boolean checkInvariants(TreeNode&lt;K,V&gt; t) {</span><a href="#l2398"></a>
<span id="l2399">            TreeNode&lt;K,V&gt; tp = t.parent, tl = t.left, tr = t.right,</span><a href="#l2399"></a>
<span id="l2400">                tb = t.prev, tn = (TreeNode&lt;K,V&gt;)t.next;</span><a href="#l2400"></a>
<span id="l2401">            if (tb != null &amp;&amp; tb.next != t)</span><a href="#l2401"></a>
<span id="l2402">                return false;</span><a href="#l2402"></a>
<span id="l2403">            if (tn != null &amp;&amp; tn.prev != t)</span><a href="#l2403"></a>
<span id="l2404">                return false;</span><a href="#l2404"></a>
<span id="l2405">            if (tp != null &amp;&amp; t != tp.left &amp;&amp; t != tp.right)</span><a href="#l2405"></a>
<span id="l2406">                return false;</span><a href="#l2406"></a>
<span id="l2407">            if (tl != null &amp;&amp; (tl.parent != t || tl.hash &gt; t.hash))</span><a href="#l2407"></a>
<span id="l2408">                return false;</span><a href="#l2408"></a>
<span id="l2409">            if (tr != null &amp;&amp; (tr.parent != t || tr.hash &lt; t.hash))</span><a href="#l2409"></a>
<span id="l2410">                return false;</span><a href="#l2410"></a>
<span id="l2411">            if (t.red &amp;&amp; tl != null &amp;&amp; tl.red &amp;&amp; tr != null &amp;&amp; tr.red)</span><a href="#l2411"></a>
<span id="l2412">                return false;</span><a href="#l2412"></a>
<span id="l2413">            if (tl != null &amp;&amp; !checkInvariants(tl))</span><a href="#l2413"></a>
<span id="l2414">                return false;</span><a href="#l2414"></a>
<span id="l2415">            if (tr != null &amp;&amp; !checkInvariants(tr))</span><a href="#l2415"></a>
<span id="l2416">                return false;</span><a href="#l2416"></a>
<span id="l2417">            return true;</span><a href="#l2417"></a>
<span id="l2418">        }</span><a href="#l2418"></a>
<span id="l2419">    }</span><a href="#l2419"></a>
<span id="l2420"></span><a href="#l2420"></a>
<span id="l2421">}</span><a href="#l2421"></a></pre>
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

