package bytom.io.assetmanage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import bytom.io.R;
import bytom.io.assetmanage.TransferActivity;
import bytom.io.assetmanage.widget.GasCoinPopupWindowWrapper;
import bytom.io.assetmanage.widget.PasswordConfirmDialog;

/**
 * Created by Nil on 2018/6/18
 */
public class TransferFragment extends AssetManageBaseFragment {
    public static final String TAG = TransferFragment.class.getSimpleName();

    @BindView(R.id.as_transfer_gas_number_container)
    ViewGroup mGasContainer;

    @BindView(R.id.as_transfer_gas_coin_type)
    Button mGasCoinType;

    @BindView(R.id.as_transfer_address)
    EditText mTransferAddress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.as_transfer_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Intent intent = getActivity().getIntent();
        String address = intent.getStringExtra(TransferActivity.EXTRA_ADDRESS);
        mTransferAddress.setText(address);
    }

    @Override
    protected void setupToolbar() {
        super.setupToolbar();
        mTitle.setText(R.string.as_transfer_title);
    }

    @OnCheckedChanged({R.id.as_transfer_gas_standard,
            R.id.as_transfer_gas_fast, R.id.as_transfer_gas_customize})
    void onTransferGasTypeChanged(CompoundButton button, boolean checked) {
        if (!checked) {
            return;
        }

        switch (button.getId()) {
            case R.id.as_transfer_gas_standard: {
                Log.i(TAG, "gas standard checked.");
                mGasContainer.setVisibility(View.INVISIBLE);
                break;
            }

            case R.id.as_transfer_gas_fast: {
                Log.i(TAG, "gas fast checked.");
                mGasContainer.setVisibility(View.INVISIBLE);
                break;
            }

            case R.id.as_transfer_gas_customize: {
                Log.i(TAG, "gas customize checked.");
                mGasContainer.setVisibility(View.VISIBLE);
                break;
            }
        }
    }

    @OnClick(R.id.as_transfer_gas_coin_type)
    void onClickGasCoinType(Button button) {
        GasCoinPopupWindowWrapper popupWindowWrapper = new GasCoinPopupWindowWrapper(getContext(), button);
        popupWindowWrapper.showAsDropDown(button);
    }

    private PasswordConfirmDialog mPasswordConfirmDialog;

    @OnClick(R.id.as_transfer_next)
    void onClickNext(Button next) {
        //Show password confirm dialog
        mPasswordConfirmDialog = new PasswordConfirmDialog();
        mPasswordConfirmDialog.show(getFragmentManager(), "passworkConfirm");
    }
}