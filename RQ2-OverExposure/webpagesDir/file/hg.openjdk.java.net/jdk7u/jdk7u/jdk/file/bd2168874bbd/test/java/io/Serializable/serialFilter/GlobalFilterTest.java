<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk7u/jdk7u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk7u/jdk7u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk7u/jdk7u/jdk/static/mercurial.js"></script>

<title>jdk7u/jdk7u/jdk: bd2168874bbd test/java/io/Serializable/serialFilter/GlobalFilterTest.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk7u/jdk7u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/shortlog/bd2168874bbd">log</a></li>
<li><a href="/jdk7u/jdk7u/jdk/graph/bd2168874bbd">graph</a></li>
<li><a href="/jdk7u/jdk7u/jdk/tags">tags</a></li>
<li><a href="/jdk7u/jdk7u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/rev/bd2168874bbd">changeset</a></li>
<li><a href="/jdk7u/jdk7u/jdk/file/bd2168874bbd/test/java/io/Serializable/serialFilter/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk7u/jdk7u/jdk/file/tip/test/java/io/Serializable/serialFilter/GlobalFilterTest.java">latest</a></li>
<li><a href="/jdk7u/jdk7u/jdk/diff/bd2168874bbd/test/java/io/Serializable/serialFilter/GlobalFilterTest.java">diff</a></li>
<li><a href="/jdk7u/jdk7u/jdk/comparison/bd2168874bbd/test/java/io/Serializable/serialFilter/GlobalFilterTest.java">comparison</a></li>
<li><a href="/jdk7u/jdk7u/jdk/annotate/bd2168874bbd/test/java/io/Serializable/serialFilter/GlobalFilterTest.java">annotate</a></li>
<li><a href="/jdk7u/jdk7u/jdk/log/bd2168874bbd/test/java/io/Serializable/serialFilter/GlobalFilterTest.java">file log</a></li>
<li><a href="/jdk7u/jdk7u/jdk/raw-file/bd2168874bbd/test/java/io/Serializable/serialFilter/GlobalFilterTest.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u/jdk">jdk</a> </h2>
<h3>view test/java/io/Serializable/serialFilter/GlobalFilterTest.java @ 8938:bd2168874bbd</h3>

<form class="search" action="/jdk7u/jdk7u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk7u/jdk7u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8231422: Better serial filter handling
Reviewed-by: andrew</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#97;&#108;&#118;&#100;&#97;&#118;&#105;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Thu, 23 Jan 2020 04:45:42 +0000</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk7u/jdk7u/jdk/file/8425a2e77f33/test/java/io/Serializable/serialFilter/GlobalFilterTest.java">8425a2e77f33</a> </td>
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
<span id="l2"> * Copyright (c) 2016, 2019, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l24">import static org.testng.Assert.assertEquals;</span><a href="#l24"></a>
<span id="l25">import static org.testng.Assert.assertSame;</span><a href="#l25"></a>
<span id="l26">import static org.testng.Assert.assertTrue;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.io.ByteArrayInputStream;</span><a href="#l28"></a>
<span id="l29">import java.io.EOFException;</span><a href="#l29"></a>
<span id="l30">import java.io.IOException;</span><a href="#l30"></a>
<span id="l31">import java.io.InvalidClassException;</span><a href="#l31"></a>
<span id="l32">import java.io.ObjectInputStream;</span><a href="#l32"></a>
<span id="l33"></span><a href="#l33"></a>
<span id="l34">import java.io.SerializablePermission;</span><a href="#l34"></a>
<span id="l35">import java.security.Security;</span><a href="#l35"></a>
<span id="l36">import java.util.Objects;</span><a href="#l36"></a>
<span id="l37"></span><a href="#l37"></a>
<span id="l38">import org.testng.Assert;</span><a href="#l38"></a>
<span id="l39">import org.testng.annotations.Test;</span><a href="#l39"></a>
<span id="l40">import org.testng.annotations.DataProvider;</span><a href="#l40"></a>
<span id="l41"></span><a href="#l41"></a>
<span id="l42">import sun.misc.ObjectInputFilter;</span><a href="#l42"></a>
<span id="l43"></span><a href="#l43"></a>
<span id="l44">/* @test</span><a href="#l44"></a>
<span id="l45"> * @bug 8231422</span><a href="#l45"></a>
<span id="l46"> * @build GlobalFilterTest SerialFilterTest</span><a href="#l46"></a>
<span id="l47"> * @run testng/othervm GlobalFilterTest</span><a href="#l47"></a>
<span id="l48"> * @run testng/othervm -Djdk.serialFilter=java.**</span><a href="#l48"></a>
<span id="l49"> *          -Dexpected-jdk.serialFilter=java.** GlobalFilterTest</span><a href="#l49"></a>
<span id="l50"> * @run testng/othervm/policy=security.policy GlobalFilterTest</span><a href="#l50"></a>
<span id="l51"> * @run testng/othervm/policy=security.policy</span><a href="#l51"></a>
<span id="l52"> *        -Djava.security.properties=${test.src}/java.security-extra1</span><a href="#l52"></a>
<span id="l53"> *        -Djava.security.debug=properties GlobalFilterTest</span><a href="#l53"></a>
<span id="l54"> *</span><a href="#l54"></a>
<span id="l55"> * @summary Test Global Filters</span><a href="#l55"></a>
<span id="l56"> */</span><a href="#l56"></a>
<span id="l57">@Test</span><a href="#l57"></a>
<span id="l58">public class GlobalFilterTest {</span><a href="#l58"></a>
<span id="l59">    private static final String serialPropName = &quot;jdk.serialFilter&quot;;</span><a href="#l59"></a>
<span id="l60">    private static final String badSerialFilter = &quot;java.lang.StringBuffer;!*&quot;;</span><a href="#l60"></a>
<span id="l61">    private static final String origSerialFilterProperty =</span><a href="#l61"></a>
<span id="l62">            System.setProperty(serialPropName, badSerialFilter);</span><a href="#l62"></a>
<span id="l63"></span><a href="#l63"></a>
<span id="l64">    /**</span><a href="#l64"></a>
<span id="l65">     * DataProvider of patterns and objects derived from the configured process-wide filter.</span><a href="#l65"></a>
<span id="l66">     * @return Array of arrays of pattern, object, allowed boolean, and API factory</span><a href="#l66"></a>
<span id="l67">     */</span><a href="#l67"></a>
<span id="l68">    @DataProvider(name=&quot;globalPatternElements&quot;)</span><a href="#l68"></a>
<span id="l69">    Object[][] globalPatternElements() {</span><a href="#l69"></a>
<span id="l70">        String globalFilter =</span><a href="#l70"></a>
<span id="l71">                System.getProperty(&quot;expected-&quot; + serialPropName,</span><a href="#l71"></a>
<span id="l72">                        Security.getProperty(serialPropName));</span><a href="#l72"></a>
<span id="l73">        if (globalFilter == null) {</span><a href="#l73"></a>
<span id="l74">            return new Object[0][];</span><a href="#l74"></a>
<span id="l75">        }</span><a href="#l75"></a>
<span id="l76"></span><a href="#l76"></a>
<span id="l77">        String[] patterns = globalFilter.split(&quot;;&quot;);</span><a href="#l77"></a>
<span id="l78">        Object[][] objects = new Object[patterns.length][];</span><a href="#l78"></a>
<span id="l79"></span><a href="#l79"></a>
<span id="l80">        for (int i = 0; i &lt; patterns.length; i++) {</span><a href="#l80"></a>
<span id="l81">            Object o;</span><a href="#l81"></a>
<span id="l82">            boolean allowed;</span><a href="#l82"></a>
<span id="l83">            String pattern = patterns[i].trim();</span><a href="#l83"></a>
<span id="l84">            if (pattern.contains(&quot;=&quot;)) {</span><a href="#l84"></a>
<span id="l85">                allowed = false;</span><a href="#l85"></a>
<span id="l86">                o = SerialFilterTest.genTestObject(pattern, false);</span><a href="#l86"></a>
<span id="l87">            } else {</span><a href="#l87"></a>
<span id="l88">                allowed = !pattern.startsWith(&quot;!&quot;);</span><a href="#l88"></a>
<span id="l89">                o = (allowed)</span><a href="#l89"></a>
<span id="l90">                    ? SerialFilterTest.genTestObject(pattern, true)</span><a href="#l90"></a>
<span id="l91">                    : SerialFilterTest.genTestObject(pattern.substring(1), false);</span><a href="#l91"></a>
<span id="l92"></span><a href="#l92"></a>
<span id="l93">                Assert.assertNotNull(o, &quot;fail generation failed&quot;);</span><a href="#l93"></a>
<span id="l94">            }</span><a href="#l94"></a>
<span id="l95">            objects[i] = new Object[3];</span><a href="#l95"></a>
<span id="l96">            objects[i][0] = pattern;</span><a href="#l96"></a>
<span id="l97">            objects[i][1] = allowed;</span><a href="#l97"></a>
<span id="l98">            objects[i][2] = o;</span><a href="#l98"></a>
<span id="l99">        }</span><a href="#l99"></a>
<span id="l100">        return objects;</span><a href="#l100"></a>
<span id="l101">    }</span><a href="#l101"></a>
<span id="l102"></span><a href="#l102"></a>
<span id="l103">    /**</span><a href="#l103"></a>
<span id="l104">     * Test that the process-wide filter is set when the properties are set</span><a href="#l104"></a>
<span id="l105">     * and has the toString matching the configured pattern.</span><a href="#l105"></a>
<span id="l106">     */</span><a href="#l106"></a>
<span id="l107">    @Test()</span><a href="#l107"></a>
<span id="l108">    static void globalFilter() {</span><a href="#l108"></a>
<span id="l109">        ObjectInputFilter filter = ObjectInputFilter.Config.getSerialFilter();</span><a href="#l109"></a>
<span id="l110"></span><a href="#l110"></a>
<span id="l111">        // Check that the System.setProperty(jdk.serialFilter) DOES NOT affect the filter.</span><a href="#l111"></a>
<span id="l112">        String asSetSystemProp = System.getProperty(serialPropName,</span><a href="#l112"></a>
<span id="l113">                Security.getProperty(serialPropName));</span><a href="#l113"></a>
<span id="l114">        Assert.assertNotEquals(Objects.toString(filter, null), asSetSystemProp,</span><a href="#l114"></a>
<span id="l115">                &quot;System.setProperty(\&quot;jdk.serialfilter\&quot;, ...) should not change filter: &quot; +</span><a href="#l115"></a>
<span id="l116">                asSetSystemProp);</span><a href="#l116"></a>
<span id="l117"></span><a href="#l117"></a>
<span id="l118">        String pattern =</span><a href="#l118"></a>
<span id="l119">               System.getProperty(&quot;expected-&quot; + serialPropName,</span><a href="#l119"></a>
<span id="l120">                       Security.getProperty(serialPropName));</span><a href="#l120"></a>
<span id="l121">        System.out.printf(&quot;global pattern: %s, filter: %s%n&quot;, pattern, filter);</span><a href="#l121"></a>
<span id="l122">        Assert.assertEquals(Objects.toString(filter, null), pattern,</span><a href="#l122"></a>
<span id="l123">                &quot;process-wide filter pattern does not match&quot;);</span><a href="#l123"></a>
<span id="l124">    }</span><a href="#l124"></a>
<span id="l125"></span><a href="#l125"></a>
<span id="l126">    /**</span><a href="#l126"></a>
<span id="l127">     * If the Global filter is already set, it should always refuse to be</span><a href="#l127"></a>
<span id="l128">     * set again.</span><a href="#l128"></a>
<span id="l129">     * If there is a security manager, setting the serialFilter should fail</span><a href="#l129"></a>
<span id="l130">     * without the appropriate permission.</span><a href="#l130"></a>
<span id="l131">     * If there is no security manager then setting it should work.</span><a href="#l131"></a>
<span id="l132">     */</span><a href="#l132"></a>
<span id="l133">    @Test()</span><a href="#l133"></a>
<span id="l134">    static void setGlobalFilter() {</span><a href="#l134"></a>
<span id="l135">        SecurityManager sm = System.getSecurityManager();</span><a href="#l135"></a>
<span id="l136">        ObjectInputFilter filter = new SerialFilterTest.Validator();</span><a href="#l136"></a>
<span id="l137">        ObjectInputFilter global = ObjectInputFilter.Config.getSerialFilter();</span><a href="#l137"></a>
<span id="l138">        if (global != null) {</span><a href="#l138"></a>
<span id="l139">            // once set, can never be re-set</span><a href="#l139"></a>
<span id="l140">            try {</span><a href="#l140"></a>
<span id="l141">                ObjectInputFilter.Config.setSerialFilter(filter);</span><a href="#l141"></a>
<span id="l142">                Assert.fail(&quot;set only once process-wide filter&quot;);</span><a href="#l142"></a>
<span id="l143">            } catch (IllegalStateException ise) {</span><a href="#l143"></a>
<span id="l144">                if (sm != null) {</span><a href="#l144"></a>
<span id="l145">                    Assert.fail(&quot;wrong exception when security manager is set&quot;, ise);</span><a href="#l145"></a>
<span id="l146">                }</span><a href="#l146"></a>
<span id="l147">            } catch (SecurityException se) {</span><a href="#l147"></a>
<span id="l148">                if (sm == null) {</span><a href="#l148"></a>
<span id="l149">                    Assert.fail(&quot;wrong exception when security manager is not set&quot;, se);</span><a href="#l149"></a>
<span id="l150">                }</span><a href="#l150"></a>
<span id="l151">            }</span><a href="#l151"></a>
<span id="l152">        } else {</span><a href="#l152"></a>
<span id="l153">            if (sm == null) {</span><a href="#l153"></a>
<span id="l154">                // no security manager</span><a href="#l154"></a>
<span id="l155">                try {</span><a href="#l155"></a>
<span id="l156">                    ObjectInputFilter.Config.setSerialFilter(filter);</span><a href="#l156"></a>
<span id="l157">                    // Note once set, it can not be reset; so other tests</span><a href="#l157"></a>
<span id="l158">                    System.out.printf(&quot;Global Filter set to Validator%n&quot;);</span><a href="#l158"></a>
<span id="l159">                } catch (SecurityException se) {</span><a href="#l159"></a>
<span id="l160">                    Assert.fail(&quot;setGlobalFilter should not get SecurityException&quot;, se);</span><a href="#l160"></a>
<span id="l161">                }</span><a href="#l161"></a>
<span id="l162">                try {</span><a href="#l162"></a>
<span id="l163">                    // Try to set it again, expecting it to throw</span><a href="#l163"></a>
<span id="l164">                    ObjectInputFilter.Config.setSerialFilter(filter);</span><a href="#l164"></a>
<span id="l165">                    Assert.fail(&quot;set only once process-wide filter&quot;);</span><a href="#l165"></a>
<span id="l166">                } catch (IllegalStateException ise) {</span><a href="#l166"></a>
<span id="l167">                    // Normal case</span><a href="#l167"></a>
<span id="l168">                }</span><a href="#l168"></a>
<span id="l169">            } else {</span><a href="#l169"></a>
<span id="l170">                // Security manager</span><a href="#l170"></a>
<span id="l171">                SecurityException expectSE = null;</span><a href="#l171"></a>
<span id="l172">                try {</span><a href="#l172"></a>
<span id="l173">                    sm.checkPermission(new SerializablePermission(&quot;serialFilter&quot;));</span><a href="#l173"></a>
<span id="l174">                } catch (SecurityException se1) {</span><a href="#l174"></a>
<span id="l175">                    expectSE = se1;</span><a href="#l175"></a>
<span id="l176">                }</span><a href="#l176"></a>
<span id="l177">                SecurityException actualSE = null;</span><a href="#l177"></a>
<span id="l178">                try {</span><a href="#l178"></a>
<span id="l179">                    ObjectInputFilter.Config.setSerialFilter(filter);</span><a href="#l179"></a>
<span id="l180">                } catch (SecurityException se2) {</span><a href="#l180"></a>
<span id="l181">                    actualSE = se2;</span><a href="#l181"></a>
<span id="l182">                }</span><a href="#l182"></a>
<span id="l183">                if (expectSE == null | actualSE == null) {</span><a href="#l183"></a>
<span id="l184">                    Assert.assertEquals(expectSE, actualSE, &quot;SecurityException&quot;);</span><a href="#l184"></a>
<span id="l185">                } else {</span><a href="#l185"></a>
<span id="l186">                    Assert.assertEquals(expectSE.getClass(), actualSE.getClass(),</span><a href="#l186"></a>
<span id="l187">                            &quot;SecurityException class&quot;);</span><a href="#l187"></a>
<span id="l188">                }</span><a href="#l188"></a>
<span id="l189">            }</span><a href="#l189"></a>
<span id="l190">        }</span><a href="#l190"></a>
<span id="l191">    }</span><a href="#l191"></a>
<span id="l192"></span><a href="#l192"></a>
<span id="l193">    /**</span><a href="#l193"></a>
<span id="l194">     * For each pattern in the process-wide filter test a generated object</span><a href="#l194"></a>
<span id="l195">     * against the default process-wide filter.</span><a href="#l195"></a>
<span id="l196">     *</span><a href="#l196"></a>
<span id="l197">     * @param pattern a pattern extracted from the configured global pattern</span><a href="#l197"></a>
<span id="l198">     */</span><a href="#l198"></a>
<span id="l199">    @Test(dataProvider = &quot;globalPatternElements&quot;)</span><a href="#l199"></a>
<span id="l200">    static void globalFilterElements(String pattern, boolean allowed,Object obj) {</span><a href="#l200"></a>
<span id="l201">        testGlobalPattern(pattern, obj, allowed);</span><a href="#l201"></a>
<span id="l202">    }</span><a href="#l202"></a>
<span id="l203"></span><a href="#l203"></a>
<span id="l204">    /**</span><a href="#l204"></a>
<span id="l205">     * Serialize and deserialize an object using the default process-wide filter</span><a href="#l205"></a>
<span id="l206">     * and check allowed or reject.</span><a href="#l206"></a>
<span id="l207">     *</span><a href="#l207"></a>
<span id="l208">     * @param pattern the pattern</span><a href="#l208"></a>
<span id="l209">     * @param object the test object</span><a href="#l209"></a>
<span id="l210">     * @param allowed the expected result from ObjectInputStream (exception or not)</span><a href="#l210"></a>
<span id="l211">     */</span><a href="#l211"></a>
<span id="l212">    static void testGlobalPattern(String pattern, Object object, boolean allowed) {</span><a href="#l212"></a>
<span id="l213">        try {</span><a href="#l213"></a>
<span id="l214">//            System.out.printf(&quot;global %s pattern: %s, obj: %s%n&quot;, (allowed ? &quot;allowed&quot; : &quot;not allowed&quot;), pattern, object);</span><a href="#l214"></a>
<span id="l215">            byte[] bytes = SerialFilterTest.writeObjects(object);</span><a href="#l215"></a>
<span id="l216">            try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);</span><a href="#l216"></a>
<span id="l217">                 ObjectInputStream ois = new ObjectInputStream(bais)) {</span><a href="#l217"></a>
<span id="l218">                Object o = ois.readObject();</span><a href="#l218"></a>
<span id="l219">            } catch (EOFException eof) {</span><a href="#l219"></a>
<span id="l220">                // normal completion</span><a href="#l220"></a>
<span id="l221">            } catch (ClassNotFoundException cnf) {</span><a href="#l221"></a>
<span id="l222">                Assert.fail(&quot;Deserializing&quot;, cnf);</span><a href="#l222"></a>
<span id="l223">            }</span><a href="#l223"></a>
<span id="l224">            Assert.assertTrue(allowed, &quot;filter should have thrown an exception&quot;);</span><a href="#l224"></a>
<span id="l225">        } catch (IllegalArgumentException iae) {</span><a href="#l225"></a>
<span id="l226">            Assert.fail(&quot;bad format pattern&quot;, iae);</span><a href="#l226"></a>
<span id="l227">        } catch (InvalidClassException ice) {</span><a href="#l227"></a>
<span id="l228">            Assert.assertFalse(allowed, &quot;filter should not have thrown an exception: &quot; + ice);</span><a href="#l228"></a>
<span id="l229">        } catch (IOException ioe) {</span><a href="#l229"></a>
<span id="l230">            Assert.fail(&quot;Unexpected IOException&quot;, ioe);</span><a href="#l230"></a>
<span id="l231">        }</span><a href="#l231"></a>
<span id="l232">    }</span><a href="#l232"></a>
<span id="l233">}</span><a href="#l233"></a></pre>
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

