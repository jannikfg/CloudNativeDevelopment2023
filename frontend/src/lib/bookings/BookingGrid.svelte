<script>
  import BookingItem from "./BookingItem.svelte";
  export let bookings;
</script>

<div class="bookings_list">
  {#each bookings as booking}
    <BookingItem booking={booking} />
  {/each}
</div>

<style>
  .bookings_list {
    display: flex;
    justify-content: center;
    flex-direction: row;
    flex-wrap: wrap;
    gap: 20px;
  }
</style>