
    const cards = document.querySelectorAll('.card');
    const container = document.getElementById('cardContainer');

    let draggedCard = null;

    cards.forEach(card => {
    // When dragging starts
    card.addEventListener('dragstart', () => {
        draggedCard = card;
        card.classList.add('dragging');
    });

    // When dragging ends
    card.addEventListener('dragend', () => {
    card.classList.remove('dragging');
    draggedCard = null;
});

    // Allow drag over another card
    card.addEventListener('dragover', (event) => {
    event.preventDefault(); // Necessary to allow drop
});

    // When a card is dropped
    card.addEventListener('drop', (event) => {
    event.preventDefault();
    const draggingOverCard = event.target.closest('.card');
    if (draggedCard !== draggingOverCard) {
    const allCards = Array.from(container.children);
    const draggedIndex = allCards.indexOf(draggedCard);
    const droppedIndex = allCards.indexOf(draggingOverCard);

    // Reorder cards in the container
    if (draggedIndex > droppedIndex) {
    container.insertBefore(draggedCard, draggingOverCard);
} else {
    container.insertBefore(draggedCard, draggingOverCard.nextSibling);
}
}
});
});
