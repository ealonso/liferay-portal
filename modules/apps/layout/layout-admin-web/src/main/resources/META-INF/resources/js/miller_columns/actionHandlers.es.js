const actionHandlers = {
    'copyLayout': event => {
        event.preventDefault();

        Liferay.Util.openWindow({
            dialog: {
                destroyOnHide: true,
                height: 480,
                resizable: false,
                width: 640
            },
            dialogIframe: {
                bodyCssClass: 'dialog-with-footer'
            },
            id: 'addLayoutDialog',
            title: Liferay.Language.get('copy-page'),
            uri: event.target.href
        });
    },

    'delete': event => {
        const deleteMessage = Liferay.Language.get(
            'are-you-sure-you-want-to-delete-this'
        );

        if (!confirm(deleteMessage)) {
            event.preventDefault();
        }
    },

    'permissions': event => {
        Liferay.Util.openInDialog(
            event,
            {
                dialog: {
                    destroyOnHide: true
                },
                dialogIframe: {
                    bodyCssClass: 'dialog-with-footer'
                },
                uri: event.target.href
            }
        );
    }
}

export default actionHandlers;