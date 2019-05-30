package com.movieapp.pokemonapp.viewmodel;

import android.arch.core.executor.ArchTaskExecutor;
import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;

import com.movieapp.pokemonapp.model.DetailsModal;
import com.movieapp.pokemonapp.model.PokemonApiResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.arch.lifecycle.Lifecycle.Event.ON_DESTROY;
import static android.arch.lifecycle.Lifecycle.Event.ON_START;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class MainActivityViewModelTest {

    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();
    private PublicLiveData<PokemonApiResponse> mLiveData;

    private LifecycleOwner mOwner;
    private LifecycleRegistry mRegistry;

    private MethodExec mActiveObserversChanged;

    private LifecycleOwner mOwner2;
    private LifecycleRegistry mRegistry2;
    private LifecycleOwner mOwner3;
    private Lifecycle mLifecycle3;
    private Observer<PokemonApiResponse> mObserver3;
    private LifecycleOwner mOwner4;
    private Lifecycle mLifecycle4;
    private Observer<PokemonApiResponse> mObserver4;
    private boolean mInObserver;


    @Before
    public void init() {

        mLiveData = new PublicLiveData<>();

        mActiveObserversChanged = mock(MethodExec.class);
        mLiveData.activeObserversChanged = mActiveObserversChanged;

        mOwner = mock(LifecycleOwner.class);
        mRegistry = new LifecycleRegistry(mOwner);
        when(mOwner.getLifecycle()).thenReturn(mRegistry);

        mOwner2 = mock(LifecycleOwner.class);
        mRegistry2 = new LifecycleRegistry(mOwner2);
        when(mOwner2.getLifecycle()).thenReturn(mRegistry2);
        mInObserver = false;
    }


    @Before
    public void initNonLifecycleRegistry() {
        mOwner3 = mock(LifecycleOwner.class);
        mLifecycle3 = mock(Lifecycle.class);
        mObserver3 = (Observer<PokemonApiResponse>) mock(Observer.class);
        when(mOwner3.getLifecycle()).thenReturn(mLifecycle3);
        mOwner4 = mock(LifecycleOwner.class);
        mLifecycle4 = mock(Lifecycle.class);
        mObserver4 = (Observer<PokemonApiResponse>) mock(Observer.class);
        when(mOwner4.getLifecycle()).thenReturn(mLifecycle4);
    }

    @After
    public void removeExecutorDelegate() {
        ArchTaskExecutor.getInstance().setDelegate(null);
    }

    @Test
    public void testObserverToggle() {
        Observer<PokemonApiResponse> observer = (Observer<PokemonApiResponse>) mock(Observer.class);
        mLiveData.observe(mOwner, observer);
        verify(mActiveObserversChanged, never()).onCall(anyBoolean());
        assertEquals(mLiveData.hasObservers(), true);
        assertEquals(mLiveData.hasActiveObservers(), false);
        mLiveData.removeObserver(observer);
        verify(mActiveObserversChanged, never()).onCall(anyBoolean());
        assertEquals(mLiveData.hasObservers(), false);
        assertEquals(mLiveData.hasActiveObservers(), false);

    }


    @Test
    public void testReAddSameObserverTuple() {
        Observer<PokemonApiResponse> observer = (Observer<PokemonApiResponse>) mock(Observer.class);
        mLiveData.observe(mOwner, observer);
        mLiveData.observe(mOwner, observer);
        assertEquals(mLiveData.hasObservers(), true);
    }

    @Test
    public void testRemoveDestroyedObserver() {
        Observer<PokemonApiResponse> observer = (Observer<PokemonApiResponse>) mock(Observer.class);
        mLiveData.observe(mOwner, observer);
        mRegistry.handleLifecycleEvent(ON_START);
        verify(mActiveObserversChanged).onCall(true);
        assertEquals(mLiveData.hasObservers(), true);
        assertEquals(mLiveData.hasActiveObservers(), true);
        reset(mActiveObserversChanged);
        mRegistry.handleLifecycleEvent(ON_DESTROY);
        assertEquals(mLiveData.hasObservers(), false);
        assertEquals(mLiveData.hasActiveObservers(), false);
        verify(mActiveObserversChanged).onCall(false);

    }

    interface MethodExec {
        void onCall(boolean value);
    }

    @SuppressWarnings("WeakerAccess")
    static class PublicLiveData<T> extends LiveData<T> {
        // cannot spy due to internal calls
        public MethodExec activeObserversChanged;

        @Override
        protected void onActive() {
            if (activeObserversChanged != null) {
                activeObserversChanged.onCall(true);
            }
        }

        @Override
        protected void onInactive() {
            if (activeObserversChanged != null) {
                activeObserversChanged.onCall(false);
            }
        }
    }
}
