<?php get_header(); ?>
<?php if ( have_posts() ) : ?>
<?php while ( have_posts() ) : the_post(); ?>
<div id="post-<?php the_ID(); ?>" <?php post_class(); ?>>
<div class="post-header">
<div class="date"><?php the_time( 'M j y' ); ?></div>
<h2><a href="<?php the_permalink(); ?>" rel="bookmark" title="Permanent Link to <?php the_title_attribute(); ?>"><?php the_title(); ?></a></h2>
<div class="author"><?php the_author(); ?></div>
</div><!--end post header-->
<div class="entry clear">
<?php if ( function_exists( 'add_theme_support' ) ) the_post_thumbnail(); ?>
<?php the_content(); ?>
<?php edit_post_link(); ?>
<?php wp_link_pages(); ?> </div>
<!--end entry-->
<div class="post-footer">
<div class="comments"><?php comments_popup_link( 'Leave a Comment', '1 Comment', '% Comments' ); ?></div>
</div><!--end post footer-->
</div><!--end post-->
<?php endwhile; /* rewind or continue if all posts have been fetched */ ?>
<div class="navigation index">
<div class="alignleft"><?php next_posts_link( 'Older Entries' ); ?></div>
<div class="alignright"><?php previous_posts_link( 'Newer Entries' ); ?></div>
</div><!--end navigation-->
<?php else : ?>
<?php endif; ?>
<div class="main__wrapper">
  <main>
    <h1>The Magic Informer</h1>
    <aside>
      <div>
        <div class="issue1">Issue #1</div>
        <div class="date">Friday, 02 December, 2022</div>
        <div class="cost">5 $</div>
      </div>
    </aside>
    <h2 class="title--large main-title">Supernatural Hacks</h2>
    <div class="main-text multi-column">
      <p>Supernatural Hacks is a special event. It is truly one of a kind. The school has crafted its legacy as the best of its kind, and no event highlights this more than this tournament. Do not miss it! This tournament was held every 3 years and consisted of 6 legendary challenges, each of a different subject, and the winning team got to be the school's representative until the next one.
The last 6 tournaments were won by team darkPointyHats, a group of bullies that do not hesitate to cheat to get points. Can you form a team capable of dethroning Dark Pointy Hats and winning the Supernatural Hacks cup?</p>
    
    </div><a class="terrarium" href="https://makelaris.com" target="_blank">
      <figure><img src="/wp-content/uploads/2022/11/academy-768x466.png"/>
        <figcaption>Wizards Hacking School</figcaption>
      </figure></a><a class="item-with-image plan span--2 long--2" href="https://makelaris.com" target="_blank"><img src="/wp-content/uploads/2022/11/dph.png"/>
      <h4>WIZARD HACKER TRASFORMS TEACHERS SIEM INTO SPYWARE</h4>
    <div class="sidebar">
      <h3 class="title--big">Hot News!</h3><a class="codepen-item pie" href="#" target="_blank"><img class="pie__image" src="/wp-content/uploads/2022/11/swords.png"/>
        <div class="pie__subtitle">Top Bug Bounty Hunters of the academic year 2021-2022</div>
        <div class="pie__content">
          <h4>Underground Swords!</h4>
          <p> </p>
        </div></a><a class="item-with-image magazine with-border" href="https://makelaris.com" target="_blank">
      <h4>Finals Week Nearing!</h4>
      <p>As finals week approaches, remember to stay hydrated and take small breaks to eat while studying. Extremely hardened active directory labs can not be pwned without discipline and a full belly.</p></a><a class="workout" target="_blank">
        <div class="workout__image"><img src="https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/3b23e537-1bf4-4de8-b248-2b0691e3b916/d5yg6mv-fab2c857-6533-4d4e-abb0-5e5cebee01b5.gif?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzNiMjNlNTM3LTFiZjQtNGRlOC1iMjQ4LTJiMDY5MWUzYjkxNlwvZDV5ZzZtdi1mYWIyYzg1Ny02NTMzLTRkNGUtYWJiMC01ZTVjZWJlZTAxYjUuZ2lmIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.v9f_E_AH56hL_DIqsdiJso4MgwvfG_tLjyhR_v0_Jrw" alt="Workout"/></div>
        <div class="workout__blurb">Wizards Hacking School to sort students online! </div>
        <div class="workout__title">A Prototype has been released!</div></a>
    </div>
      <div class="multi-column">
        <p>Christmas Eve is a big day for the western world – for both Wizards, Hackers, and Wizard Hackers</p>
        <p>This Christmas Eve, a student member of the team dark point hats, who shall remain nameless, used a spell included in the forbidden dark arts and converted a SIEM into spyware. More specifically, they managed to route the data and analytics from the monitored endpoints to their server and then to the SIEM server. To read more about it, click the icon.</p>
      </div></a><a class="item-with-image pasta with-border" href="https://makelaris.com" target="_blank">
      <h4>How To Make Bugs Disappear From Your buggy code.</h4>
      <p>Everybody hates bugs, especially in their project. It's impossible not to hate them, so The Magic Informer offers its reader its secret spell. So hold your bugs tight as you read this.</p></a><a class="item-with-image style" href="https://makelaris.com" target="_blank">
      </a><a class="item-with-image toggles" target="_blank"><img src="/wp-content/uploads/2022/11/shield.png"/>
      <h4>Another Important Milestone For The Team Invisible Shields!</h4>
      <p>Our school acquired a free license for the notorious EDR named Blockicus Totalus.</p>
    <p>From now on, our students will be able to practice their SOC skills in our school's infrastructure.</p></a><a class="menu" href="#" target="_blank"></a>

  </main>
</div>

<?php get_sidebar(); ?>
<?php get_footer(); ?>
<!-- partial -->

