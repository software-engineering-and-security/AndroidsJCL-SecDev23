<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk7u/jdk7u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk7u/jdk7u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk7u/jdk7u/jdk/static/mercurial.js"></script>

<title>jdk7u/jdk7u/jdk: c9b0a18f082e src/share/classes/java/nio/channels/spi/AbstractSelectableChannel.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk7u/jdk7u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/shortlog/c9b0a18f082e">log</a></li>
<li><a href="/jdk7u/jdk7u/jdk/graph/c9b0a18f082e">graph</a></li>
<li><a href="/jdk7u/jdk7u/jdk/tags">tags</a></li>
<li><a href="/jdk7u/jdk7u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/rev/c9b0a18f082e">changeset</a></li>
<li><a href="/jdk7u/jdk7u/jdk/file/c9b0a18f082e/src/share/classes/java/nio/channels/spi/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk7u/jdk7u/jdk/file/tip/src/share/classes/java/nio/channels/spi/AbstractSelectableChannel.java">latest</a></li>
<li><a href="/jdk7u/jdk7u/jdk/diff/c9b0a18f082e/src/share/classes/java/nio/channels/spi/AbstractSelectableChannel.java">diff</a></li>
<li><a href="/jdk7u/jdk7u/jdk/comparison/c9b0a18f082e/src/share/classes/java/nio/channels/spi/AbstractSelectableChannel.java">comparison</a></li>
<li><a href="/jdk7u/jdk7u/jdk/annotate/c9b0a18f082e/src/share/classes/java/nio/channels/spi/AbstractSelectableChannel.java">annotate</a></li>
<li><a href="/jdk7u/jdk7u/jdk/log/c9b0a18f082e/src/share/classes/java/nio/channels/spi/AbstractSelectableChannel.java">file log</a></li>
<li><a href="/jdk7u/jdk7u/jdk/raw-file/c9b0a18f082e/src/share/classes/java/nio/channels/spi/AbstractSelectableChannel.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u/jdk">jdk</a> </h2>
<h3>view src/share/classes/java/nio/channels/spi/AbstractSelectableChannel.java @ 8936:c9b0a18f082e</h3>

<form class="search" action="/jdk7u/jdk7u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk7u/jdk7u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8196956: (ch) More channels cleanup
8231795: Enhance datagram socket support
Reviewed-by: rriggs, prappo, bpb</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#97;&#108;&#97;&#110;&#98;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Wed, 22 Jan 2020 06:51:23 +0000</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk7u/jdk7u/jdk/file/d5803c262114/src/share/classes/java/nio/channels/spi/AbstractSelectableChannel.java">d5803c262114</a> </td>
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
<span id="l2"> * Copyright (c) 2000, 2018, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l26">package java.nio.channels.spi;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.io.IOException;</span><a href="#l28"></a>
<span id="l29">import java.nio.channels.CancelledKeyException;</span><a href="#l29"></a>
<span id="l30">import java.nio.channels.ClosedChannelException;</span><a href="#l30"></a>
<span id="l31">import java.nio.channels.ClosedSelectorException;</span><a href="#l31"></a>
<span id="l32">import java.nio.channels.IllegalBlockingModeException;</span><a href="#l32"></a>
<span id="l33">import java.nio.channels.IllegalSelectorException;</span><a href="#l33"></a>
<span id="l34">import java.nio.channels.SelectableChannel;</span><a href="#l34"></a>
<span id="l35">import java.nio.channels.SelectionKey;</span><a href="#l35"></a>
<span id="l36">import java.nio.channels.Selector;</span><a href="#l36"></a>
<span id="l37"></span><a href="#l37"></a>
<span id="l38"></span><a href="#l38"></a>
<span id="l39">/**</span><a href="#l39"></a>
<span id="l40"> * Base implementation class for selectable channels.</span><a href="#l40"></a>
<span id="l41"> *</span><a href="#l41"></a>
<span id="l42"> * &lt;p&gt; This class defines methods that handle the mechanics of channel</span><a href="#l42"></a>
<span id="l43"> * registration, deregistration, and closing.  It maintains the current</span><a href="#l43"></a>
<span id="l44"> * blocking mode of this channel as well as its current set of selection keys.</span><a href="#l44"></a>
<span id="l45"> * It performs all of the synchronization required to implement the {@link</span><a href="#l45"></a>
<span id="l46"> * java.nio.channels.SelectableChannel} specification.  Implementations of the</span><a href="#l46"></a>
<span id="l47"> * abstract protected methods defined in this class need not synchronize</span><a href="#l47"></a>
<span id="l48"> * against other threads that might be engaged in the same operations.  &lt;/p&gt;</span><a href="#l48"></a>
<span id="l49"> *</span><a href="#l49"></a>
<span id="l50"> *</span><a href="#l50"></a>
<span id="l51"> * @author Mark Reinhold</span><a href="#l51"></a>
<span id="l52"> * @author Mike McCloskey</span><a href="#l52"></a>
<span id="l53"> * @author JSR-51 Expert Group</span><a href="#l53"></a>
<span id="l54"> * @since 1.4</span><a href="#l54"></a>
<span id="l55"> */</span><a href="#l55"></a>
<span id="l56"></span><a href="#l56"></a>
<span id="l57">public abstract class AbstractSelectableChannel</span><a href="#l57"></a>
<span id="l58">    extends SelectableChannel</span><a href="#l58"></a>
<span id="l59">{</span><a href="#l59"></a>
<span id="l60"></span><a href="#l60"></a>
<span id="l61">    // The provider that created this channel</span><a href="#l61"></a>
<span id="l62">    private final SelectorProvider provider;</span><a href="#l62"></a>
<span id="l63"></span><a href="#l63"></a>
<span id="l64">    // Keys that have been created by registering this channel with selectors.</span><a href="#l64"></a>
<span id="l65">    // They are saved because if this channel is closed the keys must be</span><a href="#l65"></a>
<span id="l66">    // deregistered.  Protected by keyLock.</span><a href="#l66"></a>
<span id="l67">    //</span><a href="#l67"></a>
<span id="l68">    private SelectionKey[] keys = null;</span><a href="#l68"></a>
<span id="l69">    private int keyCount = 0;</span><a href="#l69"></a>
<span id="l70"></span><a href="#l70"></a>
<span id="l71">    // Lock for key set and count</span><a href="#l71"></a>
<span id="l72">    private final Object keyLock = new Object();</span><a href="#l72"></a>
<span id="l73"></span><a href="#l73"></a>
<span id="l74">    // Lock for registration and configureBlocking operations</span><a href="#l74"></a>
<span id="l75">    private final Object regLock = new Object();</span><a href="#l75"></a>
<span id="l76"></span><a href="#l76"></a>
<span id="l77">    // True when non-blocking, need regLock to change;</span><a href="#l77"></a>
<span id="l78">    private volatile boolean nonBlocking;</span><a href="#l78"></a>
<span id="l79"></span><a href="#l79"></a>
<span id="l80">    /**</span><a href="#l80"></a>
<span id="l81">     * Initializes a new instance of this class.</span><a href="#l81"></a>
<span id="l82">     */</span><a href="#l82"></a>
<span id="l83">    protected AbstractSelectableChannel(SelectorProvider provider) {</span><a href="#l83"></a>
<span id="l84">        this.provider = provider;</span><a href="#l84"></a>
<span id="l85">    }</span><a href="#l85"></a>
<span id="l86"></span><a href="#l86"></a>
<span id="l87">    /**</span><a href="#l87"></a>
<span id="l88">     * Returns the provider that created this channel.</span><a href="#l88"></a>
<span id="l89">     *</span><a href="#l89"></a>
<span id="l90">     * @return  The provider that created this channel</span><a href="#l90"></a>
<span id="l91">     */</span><a href="#l91"></a>
<span id="l92">    public final SelectorProvider provider() {</span><a href="#l92"></a>
<span id="l93">        return provider;</span><a href="#l93"></a>
<span id="l94">    }</span><a href="#l94"></a>
<span id="l95"></span><a href="#l95"></a>
<span id="l96"></span><a href="#l96"></a>
<span id="l97">    // -- Utility methods for the key set --</span><a href="#l97"></a>
<span id="l98"></span><a href="#l98"></a>
<span id="l99">    private void addKey(SelectionKey k) {</span><a href="#l99"></a>
<span id="l100">        assert Thread.holdsLock(keyLock);</span><a href="#l100"></a>
<span id="l101">        int i = 0;</span><a href="#l101"></a>
<span id="l102">        if ((keys != null) &amp;&amp; (keyCount &lt; keys.length)) {</span><a href="#l102"></a>
<span id="l103">            // Find empty element of key array</span><a href="#l103"></a>
<span id="l104">            for (i = 0; i &lt; keys.length; i++)</span><a href="#l104"></a>
<span id="l105">                if (keys[i] == null)</span><a href="#l105"></a>
<span id="l106">                    break;</span><a href="#l106"></a>
<span id="l107">        } else if (keys == null) {</span><a href="#l107"></a>
<span id="l108">            keys =  new SelectionKey[3];</span><a href="#l108"></a>
<span id="l109">        } else {</span><a href="#l109"></a>
<span id="l110">            // Grow key array</span><a href="#l110"></a>
<span id="l111">            int n = keys.length * 2;</span><a href="#l111"></a>
<span id="l112">            SelectionKey[] ks =  new SelectionKey[n];</span><a href="#l112"></a>
<span id="l113">            for (i = 0; i &lt; keys.length; i++)</span><a href="#l113"></a>
<span id="l114">                ks[i] = keys[i];</span><a href="#l114"></a>
<span id="l115">            keys = ks;</span><a href="#l115"></a>
<span id="l116">            i = keyCount;</span><a href="#l116"></a>
<span id="l117">        }</span><a href="#l117"></a>
<span id="l118">        keys[i] = k;</span><a href="#l118"></a>
<span id="l119">        keyCount++;</span><a href="#l119"></a>
<span id="l120">    }</span><a href="#l120"></a>
<span id="l121"></span><a href="#l121"></a>
<span id="l122">    private SelectionKey findKey(Selector sel) {</span><a href="#l122"></a>
<span id="l123">        synchronized (keyLock) {</span><a href="#l123"></a>
<span id="l124">            if (keys == null)</span><a href="#l124"></a>
<span id="l125">                return null;</span><a href="#l125"></a>
<span id="l126">            for (int i = 0; i &lt; keys.length; i++)</span><a href="#l126"></a>
<span id="l127">                if ((keys[i] != null) &amp;&amp; (keys[i].selector() == sel))</span><a href="#l127"></a>
<span id="l128">                    return keys[i];</span><a href="#l128"></a>
<span id="l129">            return null;</span><a href="#l129"></a>
<span id="l130">        }</span><a href="#l130"></a>
<span id="l131">    }</span><a href="#l131"></a>
<span id="l132"></span><a href="#l132"></a>
<span id="l133">    void removeKey(SelectionKey k) {                    // package-private</span><a href="#l133"></a>
<span id="l134">        synchronized (keyLock) {</span><a href="#l134"></a>
<span id="l135">            for (int i = 0; i &lt; keys.length; i++)</span><a href="#l135"></a>
<span id="l136">                if (keys[i] == k) {</span><a href="#l136"></a>
<span id="l137">                    keys[i] = null;</span><a href="#l137"></a>
<span id="l138">                    keyCount--;</span><a href="#l138"></a>
<span id="l139">                }</span><a href="#l139"></a>
<span id="l140">            ((AbstractSelectionKey)k).invalidate();</span><a href="#l140"></a>
<span id="l141">        }</span><a href="#l141"></a>
<span id="l142">    }</span><a href="#l142"></a>
<span id="l143"></span><a href="#l143"></a>
<span id="l144">    private boolean haveValidKeys() {</span><a href="#l144"></a>
<span id="l145">        synchronized (keyLock) {</span><a href="#l145"></a>
<span id="l146">            if (keyCount == 0)</span><a href="#l146"></a>
<span id="l147">                return false;</span><a href="#l147"></a>
<span id="l148">            for (int i = 0; i &lt; keys.length; i++) {</span><a href="#l148"></a>
<span id="l149">                if ((keys[i] != null) &amp;&amp; keys[i].isValid())</span><a href="#l149"></a>
<span id="l150">                    return true;</span><a href="#l150"></a>
<span id="l151">            }</span><a href="#l151"></a>
<span id="l152">            return false;</span><a href="#l152"></a>
<span id="l153">        }</span><a href="#l153"></a>
<span id="l154">    }</span><a href="#l154"></a>
<span id="l155"></span><a href="#l155"></a>
<span id="l156"></span><a href="#l156"></a>
<span id="l157">    // -- Registration --</span><a href="#l157"></a>
<span id="l158"></span><a href="#l158"></a>
<span id="l159">    public final boolean isRegistered() {</span><a href="#l159"></a>
<span id="l160">        synchronized (keyLock) {</span><a href="#l160"></a>
<span id="l161">            return keyCount != 0;</span><a href="#l161"></a>
<span id="l162">        }</span><a href="#l162"></a>
<span id="l163">    }</span><a href="#l163"></a>
<span id="l164"></span><a href="#l164"></a>
<span id="l165">    public final SelectionKey keyFor(Selector sel) {</span><a href="#l165"></a>
<span id="l166">        return findKey(sel);</span><a href="#l166"></a>
<span id="l167">    }</span><a href="#l167"></a>
<span id="l168"></span><a href="#l168"></a>
<span id="l169">    /**</span><a href="#l169"></a>
<span id="l170">     * Registers this channel with the given selector, returning a selection key.</span><a href="#l170"></a>
<span id="l171">     *</span><a href="#l171"></a>
<span id="l172">     * &lt;p&gt;  This method first verifies that this channel is open and that the</span><a href="#l172"></a>
<span id="l173">     * given initial interest set is valid.</span><a href="#l173"></a>
<span id="l174">     *</span><a href="#l174"></a>
<span id="l175">     * &lt;p&gt; If this channel is already registered with the given selector then</span><a href="#l175"></a>
<span id="l176">     * the selection key representing that registration is returned after</span><a href="#l176"></a>
<span id="l177">     * setting its interest set to the given value.</span><a href="#l177"></a>
<span id="l178">     *</span><a href="#l178"></a>
<span id="l179">     * &lt;p&gt; Otherwise this channel has not yet been registered with the given</span><a href="#l179"></a>
<span id="l180">     * selector, so the {@link AbstractSelector#register register} method of</span><a href="#l180"></a>
<span id="l181">     * the selector is invoked while holding the appropriate locks.  The</span><a href="#l181"></a>
<span id="l182">     * resulting key is added to this channel's key set before being returned.</span><a href="#l182"></a>
<span id="l183">     * &lt;/p&gt;</span><a href="#l183"></a>
<span id="l184">     *</span><a href="#l184"></a>
<span id="l185">     * @throws  ClosedSelectorException {@inheritDoc}</span><a href="#l185"></a>
<span id="l186">     *</span><a href="#l186"></a>
<span id="l187">     * @throws  IllegalBlockingModeException {@inheritDoc}</span><a href="#l187"></a>
<span id="l188">     *</span><a href="#l188"></a>
<span id="l189">     * @throws  IllegalSelectorException {@inheritDoc}</span><a href="#l189"></a>
<span id="l190">     *</span><a href="#l190"></a>
<span id="l191">     * @throws  CancelledKeyException {@inheritDoc}</span><a href="#l191"></a>
<span id="l192">     *</span><a href="#l192"></a>
<span id="l193">     * @throws  IllegalArgumentException {@inheritDoc}</span><a href="#l193"></a>
<span id="l194">     */</span><a href="#l194"></a>
<span id="l195">    public final SelectionKey register(Selector sel, int ops,</span><a href="#l195"></a>
<span id="l196">                                       Object att)</span><a href="#l196"></a>
<span id="l197">        throws ClosedChannelException</span><a href="#l197"></a>
<span id="l198">    {</span><a href="#l198"></a>
<span id="l199">        synchronized (regLock) {</span><a href="#l199"></a>
<span id="l200">            if (!isOpen())</span><a href="#l200"></a>
<span id="l201">                throw new ClosedChannelException();</span><a href="#l201"></a>
<span id="l202">            if ((ops &amp; ~validOps()) != 0)</span><a href="#l202"></a>
<span id="l203">                throw new IllegalArgumentException();</span><a href="#l203"></a>
<span id="l204">            if (isBlocking())</span><a href="#l204"></a>
<span id="l205">                throw new IllegalBlockingModeException();</span><a href="#l205"></a>
<span id="l206">            SelectionKey k = findKey(sel);</span><a href="#l206"></a>
<span id="l207">            if (k != null) {</span><a href="#l207"></a>
<span id="l208">                k.interestOps(ops);</span><a href="#l208"></a>
<span id="l209">                k.attach(att);</span><a href="#l209"></a>
<span id="l210">            }</span><a href="#l210"></a>
<span id="l211">            if (k == null) {</span><a href="#l211"></a>
<span id="l212">                // New registration</span><a href="#l212"></a>
<span id="l213">                synchronized (keyLock) {</span><a href="#l213"></a>
<span id="l214">                    if (!isOpen())</span><a href="#l214"></a>
<span id="l215">                        throw new ClosedChannelException();</span><a href="#l215"></a>
<span id="l216">                    k = ((AbstractSelector)sel).register(this, ops, att);</span><a href="#l216"></a>
<span id="l217">                    addKey(k);</span><a href="#l217"></a>
<span id="l218">                }</span><a href="#l218"></a>
<span id="l219">            }</span><a href="#l219"></a>
<span id="l220">            return k;</span><a href="#l220"></a>
<span id="l221">        }</span><a href="#l221"></a>
<span id="l222">    }</span><a href="#l222"></a>
<span id="l223"></span><a href="#l223"></a>
<span id="l224"></span><a href="#l224"></a>
<span id="l225">    // -- Closing --</span><a href="#l225"></a>
<span id="l226"></span><a href="#l226"></a>
<span id="l227">    /**</span><a href="#l227"></a>
<span id="l228">     * Closes this channel.</span><a href="#l228"></a>
<span id="l229">     *</span><a href="#l229"></a>
<span id="l230">     * &lt;p&gt; This method, which is specified in the {@link</span><a href="#l230"></a>
<span id="l231">     * AbstractInterruptibleChannel} class and is invoked by the {@link</span><a href="#l231"></a>
<span id="l232">     * java.nio.channels.Channel#close close} method, in turn invokes the</span><a href="#l232"></a>
<span id="l233">     * {@link #implCloseSelectableChannel implCloseSelectableChannel} method in</span><a href="#l233"></a>
<span id="l234">     * order to perform the actual work of closing this channel.  It then</span><a href="#l234"></a>
<span id="l235">     * cancels all of this channel's keys.  &lt;/p&gt;</span><a href="#l235"></a>
<span id="l236">     */</span><a href="#l236"></a>
<span id="l237">    protected final void implCloseChannel() throws IOException {</span><a href="#l237"></a>
<span id="l238">        implCloseSelectableChannel();</span><a href="#l238"></a>
<span id="l239">        synchronized (keyLock) {</span><a href="#l239"></a>
<span id="l240">            int count = (keys == null) ? 0 : keys.length;</span><a href="#l240"></a>
<span id="l241">            for (int i = 0; i &lt; count; i++) {</span><a href="#l241"></a>
<span id="l242">                SelectionKey k = keys[i];</span><a href="#l242"></a>
<span id="l243">                if (k != null)</span><a href="#l243"></a>
<span id="l244">                    k.cancel();</span><a href="#l244"></a>
<span id="l245">            }</span><a href="#l245"></a>
<span id="l246">        }</span><a href="#l246"></a>
<span id="l247">    }</span><a href="#l247"></a>
<span id="l248"></span><a href="#l248"></a>
<span id="l249">    /**</span><a href="#l249"></a>
<span id="l250">     * Closes this selectable channel.</span><a href="#l250"></a>
<span id="l251">     *</span><a href="#l251"></a>
<span id="l252">     * &lt;p&gt; This method is invoked by the {@link java.nio.channels.Channel#close</span><a href="#l252"></a>
<span id="l253">     * close} method in order to perform the actual work of closing the</span><a href="#l253"></a>
<span id="l254">     * channel.  This method is only invoked if the channel has not yet been</span><a href="#l254"></a>
<span id="l255">     * closed, and it is never invoked more than once.</span><a href="#l255"></a>
<span id="l256">     *</span><a href="#l256"></a>
<span id="l257">     * &lt;p&gt; An implementation of this method must arrange for any other thread</span><a href="#l257"></a>
<span id="l258">     * that is blocked in an I/O operation upon this channel to return</span><a href="#l258"></a>
<span id="l259">     * immediately, either by throwing an exception or by returning normally.</span><a href="#l259"></a>
<span id="l260">     * &lt;/p&gt;</span><a href="#l260"></a>
<span id="l261">     */</span><a href="#l261"></a>
<span id="l262">    protected abstract void implCloseSelectableChannel() throws IOException;</span><a href="#l262"></a>
<span id="l263"></span><a href="#l263"></a>
<span id="l264"></span><a href="#l264"></a>
<span id="l265">    // -- Blocking --</span><a href="#l265"></a>
<span id="l266"></span><a href="#l266"></a>
<span id="l267">    public final boolean isBlocking() {</span><a href="#l267"></a>
<span id="l268">        return !nonBlocking;</span><a href="#l268"></a>
<span id="l269">    }</span><a href="#l269"></a>
<span id="l270"></span><a href="#l270"></a>
<span id="l271">    public final Object blockingLock() {</span><a href="#l271"></a>
<span id="l272">        return regLock;</span><a href="#l272"></a>
<span id="l273">    }</span><a href="#l273"></a>
<span id="l274"></span><a href="#l274"></a>
<span id="l275">    /**</span><a href="#l275"></a>
<span id="l276">     * Adjusts this channel's blocking mode.</span><a href="#l276"></a>
<span id="l277">     *</span><a href="#l277"></a>
<span id="l278">     * &lt;p&gt; If the given blocking mode is different from the current blocking</span><a href="#l278"></a>
<span id="l279">     * mode then this method invokes the {@link #implConfigureBlocking</span><a href="#l279"></a>
<span id="l280">     * implConfigureBlocking} method, while holding the appropriate locks, in</span><a href="#l280"></a>
<span id="l281">     * order to change the mode.  &lt;/p&gt;</span><a href="#l281"></a>
<span id="l282">     */</span><a href="#l282"></a>
<span id="l283">    public final SelectableChannel configureBlocking(boolean block)</span><a href="#l283"></a>
<span id="l284">        throws IOException</span><a href="#l284"></a>
<span id="l285">    {</span><a href="#l285"></a>
<span id="l286">        synchronized (regLock) {</span><a href="#l286"></a>
<span id="l287">            if (!isOpen())</span><a href="#l287"></a>
<span id="l288">                throw new ClosedChannelException();</span><a href="#l288"></a>
<span id="l289">            boolean blocking = !nonBlocking;</span><a href="#l289"></a>
<span id="l290">            if (block != blocking) {</span><a href="#l290"></a>
<span id="l291">                if (block &amp;&amp; haveValidKeys())</span><a href="#l291"></a>
<span id="l292">                    throw new IllegalBlockingModeException();</span><a href="#l292"></a>
<span id="l293">                implConfigureBlocking(block);</span><a href="#l293"></a>
<span id="l294">                nonBlocking = !block;</span><a href="#l294"></a>
<span id="l295">            }</span><a href="#l295"></a>
<span id="l296">        }</span><a href="#l296"></a>
<span id="l297">        return this;</span><a href="#l297"></a>
<span id="l298">    }</span><a href="#l298"></a>
<span id="l299"></span><a href="#l299"></a>
<span id="l300">    /**</span><a href="#l300"></a>
<span id="l301">     * Adjusts this channel's blocking mode.</span><a href="#l301"></a>
<span id="l302">     *</span><a href="#l302"></a>
<span id="l303">     * &lt;p&gt; This method is invoked by the {@link #configureBlocking</span><a href="#l303"></a>
<span id="l304">     * configureBlocking} method in order to perform the actual work of</span><a href="#l304"></a>
<span id="l305">     * changing the blocking mode.  This method is only invoked if the new mode</span><a href="#l305"></a>
<span id="l306">     * is different from the current mode.  &lt;/p&gt;</span><a href="#l306"></a>
<span id="l307">     *</span><a href="#l307"></a>
<span id="l308">     * @throws IOException</span><a href="#l308"></a>
<span id="l309">     *         If an I/O error occurs</span><a href="#l309"></a>
<span id="l310">     */</span><a href="#l310"></a>
<span id="l311">    protected abstract void implConfigureBlocking(boolean block)</span><a href="#l311"></a>
<span id="l312">        throws IOException;</span><a href="#l312"></a>
<span id="l313"></span><a href="#l313"></a>
<span id="l314">}</span><a href="#l314"></a></pre>
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

