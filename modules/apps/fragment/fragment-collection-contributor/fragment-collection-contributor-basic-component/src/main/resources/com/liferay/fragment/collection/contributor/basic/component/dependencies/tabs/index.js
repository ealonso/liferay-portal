/*eslint-disable*/
const tabItems = [].slice.call(fragmentElement.querySelectorAll('.nav-link'));
const tabContentItems = [].slice.call(fragmentElement.querySelectorAll('.tab-content-item'));
const dropdownButton = fragmentElement.querySelector('.navbar-toggler-link');
const dropdown = fragmentElement.querySelector('.navbar-collapse');

function activeTab (item) {
	tabItems.forEach(function(tabItem) {
		tabItem.classList.remove('active');
	});
	item.classList.add('active');
};

function activeContentTab(item) {
	tabContentItems.forEach(function(tabContentItem) {
		!tabContentItem.classList.contains('d-none') && tabContentItem.classList.add('d-none')
	});
	item.classList.remove('d-none');
};

function handleDropdown({event, item}) {
    event.preventDefault();
	dropdown.classList.toggle('show');

    if (item) {
        handleDropdownButtonName(item);
    }
};

function handleDropdownButtonName(item) {
    dropdownButton.querySelector('.navbar-text-truncate').innerHTML = item.querySelector('lfr-editable').textContent;
}

function openContentTab(event, i) {
	const {currentTarget, target} = event;
	const isEditable = target.classList.contains('page-editor__editable') || target.parentElement.classList.contains('page-editor__editable');

	if (!isEditable) {
		handleDropdown({event, item: currentTarget});
	}

	activeTab(currentTarget, i);
	activeContentTab(tabContentItems[i]);

	this.tabIndex = i;
};

function main() {
	const initialState = !this.tabIndex || this.tabIndex >= tabItems.length;
	let tabItemSelected = tabItems[0];

	if (initialState) {
		tabItems.forEach(function(item, i) {
			if (!i) {
				activeTab(item);
			}
			item.addEventListener('click', function(event) {
				openContentTab(event, i);
			});
		});
		tabContentItems.forEach(function(item, i) {
			if (!i) {
				activeContentTab(item);
			}
		});
	} else {
		tabItemSelected = tabItems[this.tabIndex];
		tabItems.forEach(function(item, i) {
			activeTab(tabItems[this.tabIndex]);	
			item.addEventListener('click', function(event) {
				openContentTab(event, i);
			});
		});
		tabContentItems.forEach(function() {
			activeContentTab(tabContentItems[this.tabIndex]);
		});
	}

	dropdownButton.addEventListener('click', function(event) {
		handleDropdown({event});
	});
	handleDropdownButtonName(tabItemSelected);
};

main();
