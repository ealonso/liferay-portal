import './external-scripts';

import React from 'react';
import WorkspacesApp from './WorkspacesApp';
import {createRoot} from 'react-dom/client';

const root = createRoot(document.getElementById('faroApp'));
root.render(<WorkspacesApp />);
