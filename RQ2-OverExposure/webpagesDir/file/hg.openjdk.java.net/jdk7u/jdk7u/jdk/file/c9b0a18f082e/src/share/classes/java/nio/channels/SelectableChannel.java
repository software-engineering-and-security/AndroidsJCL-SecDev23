<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk7u/jdk7u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk7u/jdk7u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk7u/jdk7u/jdk/static/mercurial.js"></script>

<title>jdk7u/jdk7u/jdk: c9b0a18f082e src/share/classes/java/nio/channels/SelectableChannel.java</title>
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
<li><a href="/jdk7u/jdk7u/jdk/file/c9b0a18f082e/src/share/classes/java/nio/channels/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk7u/jdk7u/jdk/file/tip/src/share/classes/java/nio/channels/SelectableChannel.java">latest</a></li>
<li><a href="/jdk7u/jdk7u/jdk/diff/c9b0a18f082e/src/share/classes/java/nio/channels/SelectableChannel.java">diff</a></li>
<li><a href="/jdk7u/jdk7u/jdk/comparison/c9b0a18f082e/src/share/classes/java/nio/channels/SelectableChannel.java">comparison</a></li>
<li><a href="/jdk7u/jdk7u/jdk/annotate/c9b0a18f082e/src/share/classes/java/nio/channels/SelectableChannel.java">annotate</a></li>
<li><a href="/jdk7u/jdk7u/jdk/log/c9b0a18f082e/src/share/classes/java/nio/channels/SelectableChannel.java">file log</a></li>
<li><a href="/jdk7u/jdk7u/jdk/raw-file/c9b0a18f082e/src/share/classes/java/nio/channels/SelectableChannel.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u/jdk">jdk</a> </h2>
<h3>view src/share/classes/java/nio/channels/SelectableChannel.java @ 8936:c9b0a18f082e</h3>

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
 <td class="author"><a href="/jdk7u/jdk7u/jdk/file/cac5fa70a888/src/share/classes/java/nio/channels/SelectableChannel.java">cac5fa70a888</a> </td>
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
<span id="l2"> * Copyright (c) 2000, 2008, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l26">package java.nio.channels;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.io.IOException;</span><a href="#l28"></a>
<span id="l29">import java.nio.channels.spi.AbstractInterruptibleChannel;</span><a href="#l29"></a>
<span id="l30">import java.nio.channels.spi.SelectorProvider;</span><a href="#l30"></a>
<span id="l31"></span><a href="#l31"></a>
<span id="l32"></span><a href="#l32"></a>
<span id="l33">/**</span><a href="#l33"></a>
<span id="l34"> * A channel that can be multiplexed via a {@link Selector}.</span><a href="#l34"></a>
<span id="l35"> *</span><a href="#l35"></a>
<span id="l36"> * &lt;p&gt; In order to be used with a selector, an instance of this class must</span><a href="#l36"></a>
<span id="l37"> * first be &lt;i&gt;registered&lt;/i&gt; via the {@link #register(Selector,int,Object)</span><a href="#l37"></a>
<span id="l38"> * register} method.  This method returns a new {@link SelectionKey} object</span><a href="#l38"></a>
<span id="l39"> * that represents the channel's registration with the selector.</span><a href="#l39"></a>
<span id="l40"> *</span><a href="#l40"></a>
<span id="l41"> * &lt;p&gt; Once registered with a selector, a channel remains registered until it</span><a href="#l41"></a>
<span id="l42"> * is &lt;i&gt;deregistered&lt;/i&gt;.  This involves deallocating whatever resources were</span><a href="#l42"></a>
<span id="l43"> * allocated to the channel by the selector.</span><a href="#l43"></a>
<span id="l44"> *</span><a href="#l44"></a>
<span id="l45"> * &lt;p&gt; A channel cannot be deregistered directly; instead, the key representing</span><a href="#l45"></a>
<span id="l46"> * its registration must be &lt;i&gt;cancelled&lt;/i&gt;.  Cancelling a key requests that</span><a href="#l46"></a>
<span id="l47"> * the channel be deregistered during the selector's next selection operation.</span><a href="#l47"></a>
<span id="l48"> * A key may be cancelled explicitly by invoking its {@link</span><a href="#l48"></a>
<span id="l49"> * SelectionKey#cancel() cancel} method.  All of a channel's keys are cancelled</span><a href="#l49"></a>
<span id="l50"> * implicitly when the channel is closed, whether by invoking its {@link</span><a href="#l50"></a>
<span id="l51"> * Channel#close close} method or by interrupting a thread blocked in an I/O</span><a href="#l51"></a>
<span id="l52"> * operation upon the channel.</span><a href="#l52"></a>
<span id="l53"> *</span><a href="#l53"></a>
<span id="l54"> * &lt;p&gt; If the selector itself is closed then the channel will be deregistered,</span><a href="#l54"></a>
<span id="l55"> * and the key representing its registration will be invalidated, without</span><a href="#l55"></a>
<span id="l56"> * further delay.</span><a href="#l56"></a>
<span id="l57"> *</span><a href="#l57"></a>
<span id="l58"> * &lt;p&gt; A channel may be registered at most once with any particular selector.</span><a href="#l58"></a>
<span id="l59"> *</span><a href="#l59"></a>
<span id="l60"> * &lt;p&gt; Whether or not a channel is registered with one or more selectors may be</span><a href="#l60"></a>
<span id="l61"> * determined by invoking the {@link #isRegistered isRegistered} method.</span><a href="#l61"></a>
<span id="l62"> *</span><a href="#l62"></a>
<span id="l63"> * &lt;p&gt; Selectable channels are safe for use by multiple concurrent</span><a href="#l63"></a>
<span id="l64"> * threads. &lt;/p&gt;</span><a href="#l64"></a>
<span id="l65"> *</span><a href="#l65"></a>
<span id="l66"> *</span><a href="#l66"></a>
<span id="l67"> * &lt;a name=&quot;bm&quot;&gt;</span><a href="#l67"></a>
<span id="l68"> * &lt;h4&gt;Blocking mode&lt;/h4&gt;</span><a href="#l68"></a>
<span id="l69"> *</span><a href="#l69"></a>
<span id="l70"> * A selectable channel is either in &lt;i&gt;blocking&lt;/i&gt; mode or in</span><a href="#l70"></a>
<span id="l71"> * &lt;i&gt;non-blocking&lt;/i&gt; mode.  In blocking mode, every I/O operation invoked</span><a href="#l71"></a>
<span id="l72"> * upon the channel will block until it completes.  In non-blocking mode an I/O</span><a href="#l72"></a>
<span id="l73"> * operation will never block and may transfer fewer bytes than were requested</span><a href="#l73"></a>
<span id="l74"> * or possibly no bytes at all.  The blocking mode of a selectable channel may</span><a href="#l74"></a>
<span id="l75"> * be determined by invoking its {@link #isBlocking isBlocking} method.</span><a href="#l75"></a>
<span id="l76"> *</span><a href="#l76"></a>
<span id="l77"> * &lt;p&gt; Newly-created selectable channels are always in blocking mode.</span><a href="#l77"></a>
<span id="l78"> * Non-blocking mode is most useful in conjunction with selector-based</span><a href="#l78"></a>
<span id="l79"> * multiplexing.  A channel must be placed into non-blocking mode before being</span><a href="#l79"></a>
<span id="l80"> * registered with a selector, and may not be returned to blocking mode until</span><a href="#l80"></a>
<span id="l81"> * it has been deregistered.</span><a href="#l81"></a>
<span id="l82"> *</span><a href="#l82"></a>
<span id="l83"> *</span><a href="#l83"></a>
<span id="l84"> * @author Mark Reinhold</span><a href="#l84"></a>
<span id="l85"> * @author JSR-51 Expert Group</span><a href="#l85"></a>
<span id="l86"> * @since 1.4</span><a href="#l86"></a>
<span id="l87"> *</span><a href="#l87"></a>
<span id="l88"> * @see SelectionKey</span><a href="#l88"></a>
<span id="l89"> * @see Selector</span><a href="#l89"></a>
<span id="l90"> */</span><a href="#l90"></a>
<span id="l91"></span><a href="#l91"></a>
<span id="l92">public abstract class SelectableChannel</span><a href="#l92"></a>
<span id="l93">    extends AbstractInterruptibleChannel</span><a href="#l93"></a>
<span id="l94">    implements Channel</span><a href="#l94"></a>
<span id="l95">{</span><a href="#l95"></a>
<span id="l96"></span><a href="#l96"></a>
<span id="l97">    /**</span><a href="#l97"></a>
<span id="l98">     * Initializes a new instance of this class.</span><a href="#l98"></a>
<span id="l99">     */</span><a href="#l99"></a>
<span id="l100">    protected SelectableChannel() { }</span><a href="#l100"></a>
<span id="l101"></span><a href="#l101"></a>
<span id="l102">    /**</span><a href="#l102"></a>
<span id="l103">     * Returns the provider that created this channel.</span><a href="#l103"></a>
<span id="l104">     *</span><a href="#l104"></a>
<span id="l105">     * @return  The provider that created this channel</span><a href="#l105"></a>
<span id="l106">     */</span><a href="#l106"></a>
<span id="l107">    public abstract SelectorProvider provider();</span><a href="#l107"></a>
<span id="l108"></span><a href="#l108"></a>
<span id="l109">    /**</span><a href="#l109"></a>
<span id="l110">     * Returns an &lt;a href=&quot;SelectionKey.html#opsets&quot;&gt;operation set&lt;/a&gt;</span><a href="#l110"></a>
<span id="l111">     * identifying this channel's supported operations.  The bits that are set</span><a href="#l111"></a>
<span id="l112">     * in this integer value denote exactly the operations that are valid for</span><a href="#l112"></a>
<span id="l113">     * this channel.  This method always returns the same value for a given</span><a href="#l113"></a>
<span id="l114">     * concrete channel class. &lt;/p&gt;</span><a href="#l114"></a>
<span id="l115">     *</span><a href="#l115"></a>
<span id="l116">     * @return  The valid-operation set</span><a href="#l116"></a>
<span id="l117">     */</span><a href="#l117"></a>
<span id="l118">    public abstract int validOps();</span><a href="#l118"></a>
<span id="l119"></span><a href="#l119"></a>
<span id="l120">    // Internal state:</span><a href="#l120"></a>
<span id="l121">    //   keySet, may be empty but is never null, typ. a tiny array</span><a href="#l121"></a>
<span id="l122">    //   boolean isRegistered, protected by key set</span><a href="#l122"></a>
<span id="l123">    //   regLock, lock object to prevent duplicate registrations</span><a href="#l123"></a>
<span id="l124">    //   blocking mode, protected by regLock</span><a href="#l124"></a>
<span id="l125"></span><a href="#l125"></a>
<span id="l126">    /**</span><a href="#l126"></a>
<span id="l127">     * Tells whether or not this channel is currently registered with any</span><a href="#l127"></a>
<span id="l128">     * selectors.  A newly-created channel is not registered.</span><a href="#l128"></a>
<span id="l129">     *</span><a href="#l129"></a>
<span id="l130">     * &lt;p&gt; Due to the inherent delay between key cancellation and channel</span><a href="#l130"></a>
<span id="l131">     * deregistration, a channel may remain registered for some time after all</span><a href="#l131"></a>
<span id="l132">     * of its keys have been cancelled.  A channel may also remain registered</span><a href="#l132"></a>
<span id="l133">     * for some time after it is closed.  &lt;/p&gt;</span><a href="#l133"></a>
<span id="l134">     *</span><a href="#l134"></a>
<span id="l135">     * @return &lt;tt&gt;true&lt;/tt&gt; if, and only if, this channel is registered</span><a href="#l135"></a>
<span id="l136">     */</span><a href="#l136"></a>
<span id="l137">    public abstract boolean isRegistered();</span><a href="#l137"></a>
<span id="l138">    //</span><a href="#l138"></a>
<span id="l139">    // sync(keySet) { return isRegistered; }</span><a href="#l139"></a>
<span id="l140"></span><a href="#l140"></a>
<span id="l141">    /**</span><a href="#l141"></a>
<span id="l142">     * Retrieves the key representing the channel's registration with the given</span><a href="#l142"></a>
<span id="l143">     * selector.  &lt;/p&gt;</span><a href="#l143"></a>
<span id="l144">     *</span><a href="#l144"></a>
<span id="l145">     * @return  The key returned when this channel was last registered with the</span><a href="#l145"></a>
<span id="l146">     *          given selector, or &lt;tt&gt;null&lt;/tt&gt; if this channel is not</span><a href="#l146"></a>
<span id="l147">     *          currently registered with that selector</span><a href="#l147"></a>
<span id="l148">     */</span><a href="#l148"></a>
<span id="l149">    public abstract SelectionKey keyFor(Selector sel);</span><a href="#l149"></a>
<span id="l150">    //</span><a href="#l150"></a>
<span id="l151">    // sync(keySet) { return findKey(sel); }</span><a href="#l151"></a>
<span id="l152"></span><a href="#l152"></a>
<span id="l153">    /**</span><a href="#l153"></a>
<span id="l154">     * Registers this channel with the given selector, returning a selection</span><a href="#l154"></a>
<span id="l155">     * key.</span><a href="#l155"></a>
<span id="l156">     *</span><a href="#l156"></a>
<span id="l157">     * &lt;p&gt; If this channel is currently registered with the given selector then</span><a href="#l157"></a>
<span id="l158">     * the selection key representing that registration is returned.  The key's</span><a href="#l158"></a>
<span id="l159">     * interest set will have been changed to &lt;tt&gt;ops&lt;/tt&gt;, as if by invoking</span><a href="#l159"></a>
<span id="l160">     * the {@link SelectionKey#interestOps(int) interestOps(int)} method.  If</span><a href="#l160"></a>
<span id="l161">     * the &lt;tt&gt;att&lt;/tt&gt; argument is not &lt;tt&gt;null&lt;/tt&gt; then the key's attachment</span><a href="#l161"></a>
<span id="l162">     * will have been set to that value.  A {@link CancelledKeyException} will</span><a href="#l162"></a>
<span id="l163">     * be thrown if the key has already been cancelled.</span><a href="#l163"></a>
<span id="l164">     *</span><a href="#l164"></a>
<span id="l165">     * &lt;p&gt; Otherwise this channel has not yet been registered with the given</span><a href="#l165"></a>
<span id="l166">     * selector, so it is registered and the resulting new key is returned.</span><a href="#l166"></a>
<span id="l167">     * The key's initial interest set will be &lt;tt&gt;ops&lt;/tt&gt; and its attachment</span><a href="#l167"></a>
<span id="l168">     * will be &lt;tt&gt;att&lt;/tt&gt;.</span><a href="#l168"></a>
<span id="l169">     *</span><a href="#l169"></a>
<span id="l170">     * &lt;p&gt; This method may be invoked at any time.  If this method is invoked</span><a href="#l170"></a>
<span id="l171">     * while another invocation of this method or of the {@link</span><a href="#l171"></a>
<span id="l172">     * #configureBlocking(boolean) configureBlocking} method is in progress</span><a href="#l172"></a>
<span id="l173">     * then it will first block until the other operation is complete.  This</span><a href="#l173"></a>
<span id="l174">     * method will then synchronize on the selector's key set and therefore may</span><a href="#l174"></a>
<span id="l175">     * block if invoked concurrently with another registration or selection</span><a href="#l175"></a>
<span id="l176">     * operation involving the same selector. &lt;/p&gt;</span><a href="#l176"></a>
<span id="l177">     *</span><a href="#l177"></a>
<span id="l178">     * &lt;p&gt; If this channel is closed while this operation is in progress then</span><a href="#l178"></a>
<span id="l179">     * the key returned by this method will have been cancelled and will</span><a href="#l179"></a>
<span id="l180">     * therefore be invalid. &lt;/p&gt;</span><a href="#l180"></a>
<span id="l181">     *</span><a href="#l181"></a>
<span id="l182">     * @param  sel</span><a href="#l182"></a>
<span id="l183">     *         The selector with which this channel is to be registered</span><a href="#l183"></a>
<span id="l184">     *</span><a href="#l184"></a>
<span id="l185">     * @param  ops</span><a href="#l185"></a>
<span id="l186">     *         The interest set for the resulting key</span><a href="#l186"></a>
<span id="l187">     *</span><a href="#l187"></a>
<span id="l188">     * @param  att</span><a href="#l188"></a>
<span id="l189">     *         The attachment for the resulting key; may be &lt;tt&gt;null&lt;/tt&gt;</span><a href="#l189"></a>
<span id="l190">     *</span><a href="#l190"></a>
<span id="l191">     * @throws  ClosedChannelException</span><a href="#l191"></a>
<span id="l192">     *          If this channel is closed</span><a href="#l192"></a>
<span id="l193">     *</span><a href="#l193"></a>
<span id="l194">     * @throws  ClosedSelectorException</span><a href="#l194"></a>
<span id="l195">     *          If the selector is closed</span><a href="#l195"></a>
<span id="l196">     *</span><a href="#l196"></a>
<span id="l197">     * @throws  IllegalBlockingModeException</span><a href="#l197"></a>
<span id="l198">     *          If this channel is in blocking mode</span><a href="#l198"></a>
<span id="l199">     *</span><a href="#l199"></a>
<span id="l200">     * @throws  IllegalSelectorException</span><a href="#l200"></a>
<span id="l201">     *          If this channel was not created by the same provider</span><a href="#l201"></a>
<span id="l202">     *          as the given selector</span><a href="#l202"></a>
<span id="l203">     *</span><a href="#l203"></a>
<span id="l204">     * @throws  CancelledKeyException</span><a href="#l204"></a>
<span id="l205">     *          If this channel is currently registered with the given selector</span><a href="#l205"></a>
<span id="l206">     *          but the corresponding key has already been cancelled</span><a href="#l206"></a>
<span id="l207">     *</span><a href="#l207"></a>
<span id="l208">     * @throws  IllegalArgumentException</span><a href="#l208"></a>
<span id="l209">     *          If a bit in the &lt;tt&gt;ops&lt;/tt&gt; set does not correspond to an</span><a href="#l209"></a>
<span id="l210">     *          operation that is supported by this channel, that is, if</span><a href="#l210"></a>
<span id="l211">     *          {@code set &amp; ~validOps() != 0}</span><a href="#l211"></a>
<span id="l212">     *</span><a href="#l212"></a>
<span id="l213">     * @return  A key representing the registration of this channel with</span><a href="#l213"></a>
<span id="l214">     *          the given selector</span><a href="#l214"></a>
<span id="l215">     */</span><a href="#l215"></a>
<span id="l216">    public abstract SelectionKey register(Selector sel, int ops, Object att)</span><a href="#l216"></a>
<span id="l217">        throws ClosedChannelException;</span><a href="#l217"></a>
<span id="l218">    //</span><a href="#l218"></a>
<span id="l219">    // sync(regLock) {</span><a href="#l219"></a>
<span id="l220">    //   sync(keySet) { look for selector }</span><a href="#l220"></a>
<span id="l221">    //   if (channel found) { set interest ops -- may block in selector;</span><a href="#l221"></a>
<span id="l222">    //                        return key; }</span><a href="#l222"></a>
<span id="l223">    //   create new key -- may block somewhere in selector;</span><a href="#l223"></a>
<span id="l224">    //   sync(keySet) { add key; }</span><a href="#l224"></a>
<span id="l225">    //   attach(attachment);</span><a href="#l225"></a>
<span id="l226">    //   return key;</span><a href="#l226"></a>
<span id="l227">    // }</span><a href="#l227"></a>
<span id="l228"></span><a href="#l228"></a>
<span id="l229">    /**</span><a href="#l229"></a>
<span id="l230">     * Registers this channel with the given selector, returning a selection</span><a href="#l230"></a>
<span id="l231">     * key.</span><a href="#l231"></a>
<span id="l232">     *</span><a href="#l232"></a>
<span id="l233">     * &lt;p&gt; An invocation of this convenience method of the form</span><a href="#l233"></a>
<span id="l234">     *</span><a href="#l234"></a>
<span id="l235">     * &lt;blockquote&gt;&lt;tt&gt;sc.register(sel, ops)&lt;/tt&gt;&lt;/blockquote&gt;</span><a href="#l235"></a>
<span id="l236">     *</span><a href="#l236"></a>
<span id="l237">     * behaves in exactly the same way as the invocation</span><a href="#l237"></a>
<span id="l238">     *</span><a href="#l238"></a>
<span id="l239">     * &lt;blockquote&gt;&lt;tt&gt;sc.{@link</span><a href="#l239"></a>
<span id="l240">     * #register(java.nio.channels.Selector,int,java.lang.Object)</span><a href="#l240"></a>
<span id="l241">     * register}(sel, ops, null)&lt;/tt&gt;&lt;/blockquote&gt;</span><a href="#l241"></a>
<span id="l242">     *</span><a href="#l242"></a>
<span id="l243">     * @param  sel</span><a href="#l243"></a>
<span id="l244">     *         The selector with which this channel is to be registered</span><a href="#l244"></a>
<span id="l245">     *</span><a href="#l245"></a>
<span id="l246">     * @param  ops</span><a href="#l246"></a>
<span id="l247">     *         The interest set for the resulting key</span><a href="#l247"></a>
<span id="l248">     *</span><a href="#l248"></a>
<span id="l249">     * @throws  ClosedChannelException</span><a href="#l249"></a>
<span id="l250">     *          If this channel is closed</span><a href="#l250"></a>
<span id="l251">     *</span><a href="#l251"></a>
<span id="l252">     * @throws  ClosedSelectorException</span><a href="#l252"></a>
<span id="l253">     *          If the selector is closed</span><a href="#l253"></a>
<span id="l254">     *</span><a href="#l254"></a>
<span id="l255">     * @throws  IllegalBlockingModeException</span><a href="#l255"></a>
<span id="l256">     *          If this channel is in blocking mode</span><a href="#l256"></a>
<span id="l257">     *</span><a href="#l257"></a>
<span id="l258">     * @throws  IllegalSelectorException</span><a href="#l258"></a>
<span id="l259">     *          If this channel was not created by the same provider</span><a href="#l259"></a>
<span id="l260">     *          as the given selector</span><a href="#l260"></a>
<span id="l261">     *</span><a href="#l261"></a>
<span id="l262">     * @throws  CancelledKeyException</span><a href="#l262"></a>
<span id="l263">     *          If this channel is currently registered with the given selector</span><a href="#l263"></a>
<span id="l264">     *          but the corresponding key has already been cancelled</span><a href="#l264"></a>
<span id="l265">     *</span><a href="#l265"></a>
<span id="l266">     * @throws  IllegalArgumentException</span><a href="#l266"></a>
<span id="l267">     *          If a bit in &lt;tt&gt;ops&lt;/tt&gt; does not correspond to an operation</span><a href="#l267"></a>
<span id="l268">     *          that is supported by this channel, that is, if {@code set &amp;</span><a href="#l268"></a>
<span id="l269">     *          ~validOps() != 0}</span><a href="#l269"></a>
<span id="l270">     *</span><a href="#l270"></a>
<span id="l271">     * @return  A key representing the registration of this channel with</span><a href="#l271"></a>
<span id="l272">     *          the given selector</span><a href="#l272"></a>
<span id="l273">     */</span><a href="#l273"></a>
<span id="l274">    public final SelectionKey register(Selector sel, int ops)</span><a href="#l274"></a>
<span id="l275">        throws ClosedChannelException</span><a href="#l275"></a>
<span id="l276">    {</span><a href="#l276"></a>
<span id="l277">        return register(sel, ops, null);</span><a href="#l277"></a>
<span id="l278">    }</span><a href="#l278"></a>
<span id="l279"></span><a href="#l279"></a>
<span id="l280">    /**</span><a href="#l280"></a>
<span id="l281">     * Adjusts this channel's blocking mode.</span><a href="#l281"></a>
<span id="l282">     *</span><a href="#l282"></a>
<span id="l283">     * &lt;p&gt; If this channel is registered with one or more selectors then an</span><a href="#l283"></a>
<span id="l284">     * attempt to place it into blocking mode will cause an {@link</span><a href="#l284"></a>
<span id="l285">     * IllegalBlockingModeException} to be thrown.</span><a href="#l285"></a>
<span id="l286">     *</span><a href="#l286"></a>
<span id="l287">     * &lt;p&gt; This method may be invoked at any time.  The new blocking mode will</span><a href="#l287"></a>
<span id="l288">     * only affect I/O operations that are initiated after this method returns.</span><a href="#l288"></a>
<span id="l289">     * For some implementations this may require blocking until all pending I/O</span><a href="#l289"></a>
<span id="l290">     * operations are complete.</span><a href="#l290"></a>
<span id="l291">     *</span><a href="#l291"></a>
<span id="l292">     * &lt;p&gt; If this method is invoked while another invocation of this method or</span><a href="#l292"></a>
<span id="l293">     * of the {@link #register(Selector, int) register} method is in progress</span><a href="#l293"></a>
<span id="l294">     * then it will first block until the other operation is complete. &lt;/p&gt;</span><a href="#l294"></a>
<span id="l295">     *</span><a href="#l295"></a>
<span id="l296">     * @param  block  If &lt;tt&gt;true&lt;/tt&gt; then this channel will be placed in</span><a href="#l296"></a>
<span id="l297">     *                blocking mode; if &lt;tt&gt;false&lt;/tt&gt; then it will be placed</span><a href="#l297"></a>
<span id="l298">     *                non-blocking mode</span><a href="#l298"></a>
<span id="l299">     *</span><a href="#l299"></a>
<span id="l300">     * @return  This selectable channel</span><a href="#l300"></a>
<span id="l301">     *</span><a href="#l301"></a>
<span id="l302">     * @throws  ClosedChannelException</span><a href="#l302"></a>
<span id="l303">     *          If this channel is closed</span><a href="#l303"></a>
<span id="l304">     *</span><a href="#l304"></a>
<span id="l305">     * @throws  IllegalBlockingModeException</span><a href="#l305"></a>
<span id="l306">     *          If &lt;tt&gt;block&lt;/tt&gt; is &lt;tt&gt;true&lt;/tt&gt; and this channel is</span><a href="#l306"></a>
<span id="l307">     *          registered with one or more selectors</span><a href="#l307"></a>
<span id="l308">     *</span><a href="#l308"></a>
<span id="l309">     * @throws IOException</span><a href="#l309"></a>
<span id="l310">     *         If an I/O error occurs</span><a href="#l310"></a>
<span id="l311">     */</span><a href="#l311"></a>
<span id="l312">    public abstract SelectableChannel configureBlocking(boolean block)</span><a href="#l312"></a>
<span id="l313">        throws IOException;</span><a href="#l313"></a>
<span id="l314">    //</span><a href="#l314"></a>
<span id="l315">    // sync(regLock) {</span><a href="#l315"></a>
<span id="l316">    //   sync(keySet) { throw IBME if block &amp;&amp; isRegistered; }</span><a href="#l316"></a>
<span id="l317">    //   change mode;</span><a href="#l317"></a>
<span id="l318">    // }</span><a href="#l318"></a>
<span id="l319"></span><a href="#l319"></a>
<span id="l320">    /**</span><a href="#l320"></a>
<span id="l321">     * Tells whether or not every I/O operation on this channel will block</span><a href="#l321"></a>
<span id="l322">     * until it completes.  A newly-created channel is always in blocking mode.</span><a href="#l322"></a>
<span id="l323">     *</span><a href="#l323"></a>
<span id="l324">     * &lt;p&gt; If this channel is closed then the value returned by this method is</span><a href="#l324"></a>
<span id="l325">     * not specified. &lt;/p&gt;</span><a href="#l325"></a>
<span id="l326">     *</span><a href="#l326"></a>
<span id="l327">     * @return &lt;tt&gt;true&lt;/tt&gt; if, and only if, this channel is in blocking mode</span><a href="#l327"></a>
<span id="l328">     */</span><a href="#l328"></a>
<span id="l329">    public abstract boolean isBlocking();</span><a href="#l329"></a>
<span id="l330"></span><a href="#l330"></a>
<span id="l331">    /**</span><a href="#l331"></a>
<span id="l332">     * Retrieves the object upon which the {@link #configureBlocking</span><a href="#l332"></a>
<span id="l333">     * configureBlocking} and {@link #register register} methods synchronize.</span><a href="#l333"></a>
<span id="l334">     * This is often useful in the implementation of adaptors that require a</span><a href="#l334"></a>
<span id="l335">     * specific blocking mode to be maintained for a short period of time.</span><a href="#l335"></a>
<span id="l336">     * &lt;/p&gt;</span><a href="#l336"></a>
<span id="l337">     *</span><a href="#l337"></a>
<span id="l338">     * @return  The blocking-mode lock object</span><a href="#l338"></a>
<span id="l339">     */</span><a href="#l339"></a>
<span id="l340">    public abstract Object blockingLock();</span><a href="#l340"></a>
<span id="l341"></span><a href="#l341"></a>
<span id="l342">}</span><a href="#l342"></a></pre>
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

